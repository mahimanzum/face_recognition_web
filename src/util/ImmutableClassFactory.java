package util;

import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;


import javassist.ClassClassPath;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.CtNewMethod;

public class ImmutableClassFactory {
	
	private static Map<Class, Class> immutableClassMap = new ConcurrentHashMap<>();
	private static Set<Class> allowedParameterClass = new HashSet<Class>(){{
		add(Integer.class);
		add(Integer.TYPE);
		add(Double.class);
		add(Double.TYPE);
		add(String.class);
		add(Long.class);
		add(Long.TYPE);
		add(Boolean.class);
		add(Boolean.TYPE);
		add(Character.class);
		add(Character.TYPE);
	}};
	
	
synchronized public static<T>  Class<? extends T> getImmutableClass(Class<T> classObject){
		if(!immutableClassMap.containsKey(classObject)){
			try{
				immutableClassMap.put(classObject, createImmutableProxyClass(classObject));
			}catch(Exception ex){
				throw new RuntimeException(ex);
			}
		}
		return immutableClassMap.get(classObject);
	}
	
	private static synchronized Class createImmutableProxyClass(Class classObject) throws Exception{
		ClassPool classPool = ClassPool.getDefault();
		classPool.insertClassPath(new ClassClassPath(classObject));
		CtClass ctClass = classPool.makeClass(classObject.getCanonicalName()+"Proxy");
		ctClass.setSuperclass(classPool.get(classObject.getCanonicalName()));
		
		for(Method method: classObject.getDeclaredMethods()){
			if(method.getReturnType().equals(void.class) 
					&& method.getName().startsWith("set")
					&& method.getParameterTypes().length == 1
					&& allowedParameterClass.contains(method.getGenericParameterTypes()[0])){
				
				String parameterTypeName = method.getGenericParameterTypes()[0].getTypeName();
				if(parameterTypeName.contains(".")){
					int lastIndex = parameterTypeName.lastIndexOf(".")+1;
					parameterTypeName = parameterTypeName.substring(lastIndex);
				}
				CtMethod ctMethod = CtNewMethod.make("public void "+method.getName()+"("+parameterTypeName+" tmp ){throw new RuntimeException(\"No modification allowed on a object returned by repository\");}", ctClass);
				ctClass.addMethod(ctMethod);
			}
		}

		Class newClass = ctClass.toClass();
		return newClass;
	}
}
