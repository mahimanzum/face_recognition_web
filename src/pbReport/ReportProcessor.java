package pbReport;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.*;

import org.apache.log4j.Logger;
import common.StringUtils;
import connection.DatabaseConnection;



public class ReportProcessor {
	
	
	public static List<List<Object>> getResult(ReportMetadata reportMetadata
			,String tableName,DatabaseConnection databaseConnection) throws Exception{
		return getResult(reportMetadata, tableName, databaseConnection,Integer.MAX_VALUE,0);
	}
	
	public static int getTotalResultCount(ReportMetadata reportMetadata
			,String tableName,DatabaseConnection databaseConnection) throws Exception{
		SqlPair sqlPair = createReportSQL(reportMetadata, tableName);
		
		System.out.println("sqlpair = " + sqlPair.sql);
		
		sqlPair.sql = sqlPair.sql.replaceAll(" order by.*", "");
		
		System.out.println("sqlpair after removing orderbys = " + sqlPair.sql);
		
		sqlPair.sql = "select count(*) from ("+sqlPair.sql+") tmp";
		
		System.out.println("sqlpair after addiing count = " + sqlPair.sql);
		
		PreparedStatement ps = databaseConnection.getNewPrepareStatement(sqlPair.sql);
		System.out.println("sqlpair before filling = " + ps.toString());
		for(int i=0;i<sqlPair.objectList.size();i++)
		{
			System.out.println("found datatype = " + sqlPair.dataTypes.get(i) + " value = " + sqlPair.objectList.get(i));
			if(!sqlPair.dataTypes.get(i).equals("constant"))
			{
				if(sqlPair.dataTypes.get(i).equals("String"))
				{
					ps.setString(i+1, (String)sqlPair.objectList.get(i));
				}
				else if(sqlPair.dataTypes.get(i).equals("long"))
				{
					ps.setLong(i+1, Long.parseLong((String)sqlPair.objectList.get(i)));
				}
				else if(sqlPair.dataTypes.get(i).equals("int"))
				{
					ps.setInt(i+1, Integer.parseInt((String)sqlPair.objectList.get(i)));
				}
				else if(sqlPair.dataTypes.get(i).equals("double"))
				{
					ps.setDouble(i+1, Double.parseDouble((String)sqlPair.objectList.get(i)));
				}
				else if(sqlPair.dataTypes.get(i).equals("boolean"))
				{
					ps.setBoolean(i+1, Boolean.parseBoolean((String)sqlPair.objectList.get(i)));
				}
				else
				{
					ps.setObject(i+1, sqlPair.objectList.get(i));
				}
			}
		}
		System.out.println(ps.toString());
		ResultSet rs = ps.executeQuery();
		rs.next();
		return rs.getInt(1);
	}
	
	public static List<List<Object>> getResult(ReportMetadata reportMetadata
			,String tableName,DatabaseConnection databaseConnection,int limit,int offset) throws Exception{
		
		SqlPair sqlPair = createReportSQL(reportMetadata, tableName);
		
		sqlPair.sql+=(" limit "+limit+" offset "+offset);
		
		PreparedStatement ps = databaseConnection.getNewPrepareStatement(sqlPair.sql);
		
		System.out.println("Before filling in getresult\n" + ps.toString());
		
		for(int i=0;i<sqlPair.objectList.size();i++)
		{
			System.out.println("found datatype = " + sqlPair.dataTypes.get(i) + " value = " + sqlPair.objectList.get(i));
			if(!sqlPair.dataTypes.get(i).equals("constant"))
			{
				if(sqlPair.dataTypes.get(i).equals("String"))
				{
					ps.setString(i+1, (String)sqlPair.objectList.get(i));
				}
				else if(sqlPair.dataTypes.get(i).equals("long"))
				{
					ps.setLong(i+1, Long.parseLong((String)sqlPair.objectList.get(i)));
				}
				else if(sqlPair.dataTypes.get(i).equals("int"))
				{
					ps.setInt(i+1, Integer.parseInt((String)sqlPair.objectList.get(i)));
				}
				else if(sqlPair.dataTypes.get(i).equals("double"))
				{
					ps.setDouble(i+1, Double.parseDouble((String)sqlPair.objectList.get(i)));
				}
				else if(sqlPair.dataTypes.get(i).equals("boolean"))
				{
					ps.setBoolean(i+1, Boolean.parseBoolean((String)sqlPair.objectList.get(i)));
				}
				else
				{				
					ps.setObject(i+1, sqlPair.objectList.get(i));
				}
			}
		}
		System.out.println(ps.toString());
		ResultSet rs = ps.executeQuery();
		return getResultByResultSet(rs,reportMetadata.getOutputColumnNames());
	}
	
	
	public static SqlPair createReportSQL(ReportMetadata reportMetadata
			,String tableName) throws Exception{
		
		StringBuilder sqlBuilder = new StringBuilder();
		sqlBuilder.append("SELECT ");
		if(reportMetadata.getOutputColumnNames().isEmpty()){
			sqlBuilder.append(" * ");
		}else{
			for(int i=0;i<reportMetadata.getOutputColumnNames().size();i++){
				if(i!=0){
					sqlBuilder.append(",");
				}
				DisplayColumn outputColum = reportMetadata.getOutputColumnNames().get(i);
				sqlBuilder.append(outputColum.databaseColumnName)
							.append(" '")
							.append(StringUtils.trim(outputColum.caption))
							.append("' ");
			}
		}
		
		List<Object> values = new ArrayList<>();
		List<String> dataTypes = new ArrayList<String>();
		sqlBuilder.append(" FROM ")
					.append(tableName)
					.append(" ");
		for(int i=0;i<reportMetadata.getInputColumnNames().size();i++)
		{
			CriteriaColumn inputColumn = reportMetadata.getInputColumnNames().get(i);
			if(i==0)
			{
				sqlBuilder.append(" WHERE ");
			}
			
			
			sqlBuilder.append(" " + inputColumn.delimeter + " ");
			sqlBuilder.append(" " + inputColumn.ParenthesisLeft + " ");	
			
			
			
			
			
			
			if(inputColumn.operator.equalsIgnoreCase("null"))
			{
				System.out.println("null operator");
				sqlBuilder.append(" ")
							.append(inputColumn.databaseColumnName)
							.append(" IS ");
				
				
				if(inputColumn.criteriaValue.trim().equals("1"))
				{
					sqlBuilder.append(" NOT ");
				}
				
				sqlBuilder.append(" NULL ");
				
			}
			else
			{
				

				sqlBuilder.append(" ")
						.append(inputColumn.databaseColumnName)
						.append(" ")
						.append(inputColumn.operator)
						.append(" ");
				
				if(inputColumn.operator.equalsIgnoreCase("in") || inputColumn.dataType.equalsIgnoreCase("constant"))
				{
					sqlBuilder.append(inputColumn.criteriaValue);
				}
				else
				{						
					sqlBuilder.append(" ? ");
					
					System.out.println("adding " + inputColumn.criteriaValue + " as " + inputColumn.dataType);
					values.add(inputColumn.criteriaValue);
					dataTypes.add(inputColumn.dataType);
				}
				

			}
			
			sqlBuilder.append(" " + inputColumn.ParenthesisRight + " ");	
			
		}
		
		if(reportMetadata.GroupBy.matches(".*[a-zA-Z]+.*"))
		{
			sqlBuilder.append(" group by ");
			sqlBuilder.append(reportMetadata.GroupBy);
		}
		
		if(reportMetadata.OrderBy.matches(".*[a-zA-Z]+.*"))
		{
			sqlBuilder.append(" order by ");
			sqlBuilder.append(reportMetadata.OrderBy);
		}
		
		
		SqlPair sqlPair = new SqlPair();
				
		sqlPair.sql = sqlBuilder.toString();
		sqlPair.objectList = values;
		sqlPair.dataTypes = dataTypes;
		
		Logger logger = Logger.getLogger(ReportProcessor.class);
		logger.debug(sqlPair);
		
		System.out.println("generated sqlpair here = " + sqlPair.sql);
		
		return sqlPair;
	}
	
	
	public static List<List<Object>> getResultByResultSet(ResultSet rs,List<DisplayColumn> outputColums) throws Exception{
		List<List<Object>> result = new ArrayList<>();
		ResultSetMetaData resultSetMetaData = rs.getMetaData();
		
		List<Object> captions = new ArrayList<>();
		for(int i=0;i<resultSetMetaData.getColumnCount();i++){
			captions.add(resultSetMetaData.getColumnLabel(i+1));
		}
		
		result.add(captions);
		
		while (rs.next()) {
			List<Object> row = new ArrayList<>();
			for(int i=0;i<resultSetMetaData.getColumnCount();i++){
				Object object = rs.getObject(i+1);
				object = outputColums.get(i).convertor.convert(object);
				row.add(object);
			}
			result.add(row);
			
		}
		
		
		return result;
	}
	
	
}
