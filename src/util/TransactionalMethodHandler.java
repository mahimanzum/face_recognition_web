package util;

import java.lang.reflect.*;
import java.sql.Savepoint;

import org.apache.log4j.Logger;

import annotation.Transactional;
import common.RequestFailureException;
import connection.DatabaseConnection;
import javassist.util.proxy.MethodHandler;




public class TransactionalMethodHandler implements MethodHandler{
	
	private Object invokeSameConnectionNonTransactional(Object paramObject, Method paramMethod1, Method proceedMethod
			, Object[] paramArrayOfObject) throws Throwable{
		
		
		boolean dbOpenedInThisMethod = false;
		
		try{
			if(DatabaseConnectionFactory.isEmpty()){
				DatabaseConnection databaseConnection = DatabaseConnectionFactory.getNewDatabaseConnection();
				databaseConnection.dbOpen();
				dbOpenedInThisMethod = true;
			}
			Object resultantObject = proceedMethod.invoke(paramObject, paramArrayOfObject);
			return resultantObject;
		}catch(Throwable throwable){
			if(throwable instanceof InvocationTargetException){
				throwable = throwable.getCause();
			}
			
			if(throwable.getCause()!=null ){
				Logger.getLogger(paramObject.getClass().getSuperclass()).debug("fatal",throwable);
			}else if(throwable instanceof RequestFailureException && 
					throwable.getCause()!=null &&   !(throwable.getCause() instanceof RequestFailureException) ){
				Logger.getLogger(paramObject.getClass().getSuperclass()).debug(((RequestFailureException)throwable).getMessage());
			}
			
			throw throwable;
		}finally{
			if(dbOpenedInThisMethod){
				DatabaseConnectionFactory.getCurrentDatabaseConnection().dbClose();
				DatabaseConnectionFactory.removeLastDatabaseConnection();
			}
		}
		
	}
	
	private Object invokeSameConnectionIndividualTransactional(Object paramObject, Method paramMethod1, Method proceedMethod
			, Object[] paramArrayOfObject) throws Throwable{

		boolean dbOpenedInThisMethod = false;
		Savepoint savepoint = null;
		try{
			if(DatabaseConnectionFactory.isEmpty()){
				DatabaseConnection databaseConnection = DatabaseConnectionFactory.getNewDatabaseConnection();
				databaseConnection.dbOpen();
				dbOpenedInThisMethod = true;
			}
			
			savepoint = DatabaseConnectionFactory.getCurrentDatabaseConnection().getSavePoint();
			DatabaseConnectionFactory.getCurrentDatabaseConnection().dbTransationStart();
			Object resultantObject = proceedMethod.invoke(paramObject, paramArrayOfObject);
			DatabaseConnectionFactory.getCurrentDatabaseConnection().dbTransationEnd();
			savepoint = null;
			return resultantObject;
		}catch(Throwable throwable){
			
			if(savepoint != null){
				DatabaseConnectionFactory.getCurrentDatabaseConnection().rollBackSavePoint(savepoint);
			}
			
			if(throwable instanceof InvocationTargetException){
				throwable = throwable.getCause();
			}
			
			if(throwable.getCause()!=null ){
				Logger.getLogger(paramObject.getClass().getSuperclass()).debug("fatal",throwable);
			}else if(throwable instanceof RequestFailureException && 
					throwable.getCause()!=null &&   !(throwable.getCause() instanceof RequestFailureException) ){
				Logger.getLogger(paramObject.getClass().getSuperclass()).debug(((RequestFailureException)throwable).getMessage());
			}
			
			throw throwable;
		}finally{
			if(dbOpenedInThisMethod){
				DatabaseConnectionFactory.getCurrentDatabaseConnection().dbClose();
				DatabaseConnectionFactory.removeLastDatabaseConnection();
			}
		}
	}
	
	private Object invokeSameConnectionPreviousTransactional(Object paramObject, Method paramMethod1, Method proceedMethod
			, Object[] paramArrayOfObject) throws Throwable{
		boolean dbOpenedInThisMethod = false;
		boolean isTransactionStartedInThisMethod = false;
		
		try{
			if(DatabaseConnectionFactory.isEmpty()){
				DatabaseConnection databaseConnection = DatabaseConnectionFactory.getNewDatabaseConnection();
				databaseConnection.dbOpen();
				dbOpenedInThisMethod = true;
			}
			
			if(!DatabaseConnectionFactory.getCurrentDatabaseConnection().hasStartedTransaction()){
				isTransactionStartedInThisMethod = true;
				DatabaseConnectionFactory.getCurrentDatabaseConnection().dbTransationStart();
			}
			
			Object resultantObject = proceedMethod.invoke(paramObject, paramArrayOfObject);
			
			if(isTransactionStartedInThisMethod){
				DatabaseConnectionFactory.getCurrentDatabaseConnection().dbTransationEnd();
			}
			
			return resultantObject;
		}catch(Throwable throwable){
			
			if(isTransactionStartedInThisMethod){
				DatabaseConnectionFactory.getCurrentDatabaseConnection().dbTransationRollBack();
			}
			
			if(throwable instanceof InvocationTargetException){
				throwable = throwable.getCause();
			}
			
			if(throwable.getCause()!=null ){
				Logger.getLogger(paramObject.getClass().getSuperclass()).debug("fatal",throwable);
			}else if(throwable instanceof RequestFailureException && 
					throwable.getCause()!=null &&   !(throwable.getCause() instanceof RequestFailureException) ){
				Logger.getLogger(paramObject.getClass().getSuperclass()).debug(((RequestFailureException)throwable).getMessage());
			}
			
			throw throwable;
		}finally{
			if(dbOpenedInThisMethod){
				DatabaseConnectionFactory.getCurrentDatabaseConnection().dbClose();
				DatabaseConnectionFactory.removeLastDatabaseConnection();
			}
		}
		
	}
	
	private Object invokeNewConnectionIndividualTransaction(Object paramObject, Method paramMethod1, Method proceedMethod
			, Object[] paramArrayOfObject) throws Throwable{
		DatabaseConnectionFactory.getNewDatabaseConnection();
		try{
			return invokeSameConnectionIndividualTransactional(paramObject, paramMethod1, proceedMethod, paramArrayOfObject);
		}finally{
			DatabaseConnectionFactory.removeLastDatabaseConnection();
		}
	}
	
	
	private Object invokeReadOnlyTransaction(Object paramObject, Method paramMethod1, Method proceedMethod, Object[] paramArrayOfObject)
			throws Throwable {
		DatabaseConnection databaseConnection = DatabaseConnectionFactory.getNewDatabaseConnection();
		try{
			databaseConnection.dbOpen();
			Object resultantObject = proceedMethod.invoke(paramObject, paramArrayOfObject);
			if(databaseConnection.hasStartedTransaction()){
				databaseConnection.dbTransationEnd();
			}
			return resultantObject;
		}catch(Throwable throwable){
			if(throwable instanceof InvocationTargetException){
				throwable = throwable.getCause();
			}
			
			if(throwable.getCause()!=null ){
				Logger.getLogger(paramObject.getClass().getSuperclass()).debug("fatal",throwable);
			}else if(throwable instanceof RequestFailureException && 
					throwable.getCause()!=null &&   !(throwable.getCause() instanceof RequestFailureException) ){
				Logger.getLogger(paramObject.getClass().getSuperclass()).debug(((RequestFailureException)throwable).getMessage());
			}
			
			
			throw throwable;
		}finally{
			databaseConnection.dbClose();
			DatabaseConnectionFactory.removeLastDatabaseConnection();
		}
	}
	
	
	private TransactionType getTransactionType(Method method) {
		Transactional transactional = (Transactional)method.getAnnotation(Transactional.class);
		return transactional.transactionType();
	}
	
	
	private ConnectionType getConnectionType(Method method){
		Transactional transactional = (Transactional)method.getAnnotation(Transactional.class);
		return transactional.connectionType();
	}
	
	
	@Override
	public Object invoke(Object paramObject, Method superClassMethod, Method proceedMethod, Object[] paramArrayOfObject)
			throws Throwable {
		TransactionType transactionType = getTransactionType(superClassMethod);
		
		ConnectionType connectionType = getConnectionType(superClassMethod);
		
		
		if(connectionType == ConnectionType.OLD_CONNECTION  && transactionType == TransactionType.READONLY ){
			return invokeSameConnectionNonTransactional(paramObject, superClassMethod, proceedMethod, paramArrayOfObject);
		}else if(connectionType == ConnectionType.OLD_CONNECTION  && transactionType == TransactionType.INDIVIDUAL_TRANSACTION ){
			return invokeSameConnectionIndividualTransactional(paramObject, superClassMethod, proceedMethod, paramArrayOfObject);
		}else if(connectionType == ConnectionType.OLD_CONNECTION  && transactionType == TransactionType.PART_OF_PREVIOUS_TRANSACTION ){
			return invokeSameConnectionPreviousTransactional(paramObject, superClassMethod, proceedMethod, paramArrayOfObject);
		}else if(connectionType == ConnectionType.NEW_CONNECTION  && transactionType == TransactionType.INDIVIDUAL_TRANSACTION ){
			return invokeNewConnectionIndividualTransaction(paramObject, superClassMethod, proceedMethod, paramArrayOfObject);
		}else{
			return proceedMethod.invoke(paramObject, paramArrayOfObject);
		}

	}

}
