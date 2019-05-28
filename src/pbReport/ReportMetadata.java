package pbReport;


import java.util.*;
import javax.servlet.http.HttpServletRequest;
import common.StringUtils;



class DisplayColumn{
	String databaseColumnName;
	String caption;
	ColumnConvertor convertor = new DefaultConvertor();
	

	public DisplayColumn(String[] tokens,String value)
	{				

		
		
		try {

			
			this.caption = value;
			this.databaseColumnName = tokens[2];
			
		
			if(tokens[3].equals("date"))
			{
				this.convertor = new DateConvertor();
			}
			else if(tokens[3].equals("geolocation"))
			{
				this.convertor = new GeoConvertor();
			}
				
			
			
		} catch (SecurityException e) {
			throw new RuntimeException(e);
		}catch(Exception ex){
			throw new RuntimeException(ex);
		}
		
	}
}


class CriteriaColumn{
	String databaseColumnName;
	String criteriaValue;
	String operator;
	Integer moduleID;
	public String delimeter;
	public String dataType;
	public String ParenthesisRight;
	public String ParenthesisLeft;
	public String placeHolderValue;


	


	CriteriaColumn(String[] tokens,String value)
	{
		
		
		String tableName = tokens[1];
		String fieldName = tokens[2];
		String operator = tokens[3];
		this.delimeter = tokens[4];
		this.dataType = tokens[5];
		this.ParenthesisLeft = tokens[6];
		this.ParenthesisRight = tokens[7];
		this.placeHolderValue = tokens[8];
		try 
		{
			if(tableName.equals(""))
			{
				this.databaseColumnName = fieldName;
			}
			else
			{
				this.databaseColumnName = tableName + "." + fieldName;
			}
			
			System.out.println("columnname = " + this.databaseColumnName + " searching value = " + value);
			if(StringUtils.isBlank(value) || value.toLowerCase().contains("select") || value.equals("any"))
			{
				System.out.println("default will be applied");
				value = this.placeHolderValue;
				if(this.placeHolderValue.equals("any"))
				{
					value = "%";
					operator = "like";
					dataType = "String";
				}
			}
			System.out.println("setting value = " + value);
			this.criteriaValue = value;
			
			

			if(operator.toLowerCase().equals("like")){
				this.criteriaValue = "%"+this.criteriaValue+"%";
			}
			
			
			this.operator = operator;
		} 
		catch (SecurityException e) 
		{
			throw new RuntimeException(e);
		}
	}
}

public class ReportMetadata {
	private List<DisplayColumn> outputColumnNames = new ArrayList<>();
	private List<CriteriaColumn> inputColumnNames = new ArrayList<>();
	public String GroupBy = "";
	public String OrderBy = "";
	
	
	public List<DisplayColumn> getOutputColumnNames() {
		return outputColumnNames;
	}


	public void setOutputColumnNames(List<DisplayColumn> outputColumnNames) {
		this.outputColumnNames = outputColumnNames;
	}


	public List<CriteriaColumn> getInputColumnNames() {
		return inputColumnNames;
	}


	public void setInputColumnNames(List<CriteriaColumn> inputColumnNames) {
		this.inputColumnNames = inputColumnNames;
	}




	public ReportMetadata(HttpServletRequest request, String criteria[][], String display[][], String GroupBy, String OrderBY)
	{
		
		Enumeration<String> parameterNames = request.getParameterNames();
		
		System.out.println("-----------------PARAMETERS---------");

		while (parameterNames.hasMoreElements()) 
		{
			String paramName = parameterNames.nextElement();
			System.out.println("Name = " + paramName);


			String[] paramValues = request.getParameterValues(paramName);
			for (int i = 0; i < paramValues.length; i++) {
				String paramValue = paramValues[i];
				System.out.println("value = " + paramValue);
			}
		}
		
		for(int i = 0; i < criteria.length; i ++)
		{
			inputColumnNames.add(new CriteriaColumn(criteria[i], request.getParameter(criteria[i][9])));			
		}
		for(int i = 0; i < display.length; i ++)
		{
			outputColumnNames.add(new DisplayColumn(display[i], display[i][4]));
		}
		
		this.GroupBy = GroupBy;
		this.OrderBy = OrderBY;
		System.out.println("inputColumnNames " + inputColumnNames);
		System.out.println("outputColumnNames " + outputColumnNames);
		
	}
}
