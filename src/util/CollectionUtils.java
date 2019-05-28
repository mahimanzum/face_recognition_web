package util;
import java.util.*;
public class CollectionUtils {
	public static<T> List<T> convertToList(Class<T> classObject,String[] objectList) throws Exception{
		List<T> resultList = new ArrayList<>();
		if(objectList == null){
			objectList = new String[0];
		}
		for(String objectString:objectList){
			resultList.add(convertStringToObject(classObject, objectString));
		}
		
		return resultList;
	}
	
	public static<T> T convertStringToObject(Class<T> classObject,String objectString) throws Exception{
		Object returnObject = null;
		
		if(Integer.class.equals(classObject)){
			returnObject = Integer.parseInt(objectString);
		}else if(Long.class.equals(classObject)){
			returnObject = Long.parseLong(objectString);
		}else if(String.class.equals(classObject)){
			returnObject = objectString;
		}else if(Boolean.class.equals(classObject)){
			returnObject = (objectString.trim().equals("1"));
		}
		
		return (T)returnObject;
	}
	public static String getCommanSepartedString(List<?> list){
		String str = "";
		for(int i=0;i<list.size();i++){
			if(i!=0){
				str+=",";
			}
			str+=list.get(i);
		}
		return str;
	}
	
}
