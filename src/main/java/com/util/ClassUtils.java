package com.util;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.ArrayUtils;

public class ClassUtils {
		
		private static final String proxyClazzField = "$$";
		public static final String getRealClassName(Class<?> clazz) {
			return getRealClass(clazz).getName();
		}
		
		public static final Class<?> getRealClass(Class<?> clazz) {
			String clazzName = clazz.getName();
			return (clazzName.indexOf(proxyClazzField)>0)?
					clazz.getSuperclass(): clazz;
		}
		
		public static final Class<?> getRealClass(Object obj) {
			return getRealClass(obj.getClass());
		}
		
		public static final boolean equals(Class<?> clazz,Class<?> clazzOther) {
			if(clazz == clazzOther) return true;
			return getRealClassName(clazz).equals(getRealClassName(clazzOther))?true:false;
		}
		
		
		
		public static List<String> getClassPathClasseNames(String packageName,
				boolean recursive) {
			List<String> classNames = new ArrayList<String>();
			String packageDirName = packageName.replace('.', '/');
			try {
				Enumeration<URL> urls = Thread.currentThread()
						.getContextClassLoader().getResources(packageDirName);
				while (urls.hasMoreElements()) {
					URL url = urls.nextElement();
					String protocol = url.getProtocol();
					if ("file".equalsIgnoreCase(protocol)) {
						String absolutePackagePath = URLDecoder.decode(url
								.getFile(), "UTF-8");
						addClassFile(packageName, absolutePackagePath, recursive,
								classNames);
					}
				}
			} catch (IOException e) {
				throw new RuntimeException("package not found : " + packageName, e);
			}
			return classNames;
		}
		
		public static List<Class<?>> getClassPathClasses(String packageName,
				boolean recursive) {
			List<String> classNames = getClassPathClasseNames(packageName, recursive);
			List<Class<?>> classes = new ArrayList<Class<?>>();
			for (String className : classNames) {
				try {
					classes.add(Class.forName(className));
				} catch (ClassNotFoundException e) {
					throw new RuntimeException("class not found : " + className, e);
				}
			}
			return classes;
		}
		
		
		public static  List<Class<?>> getClassPathClasses(String packageName,
				boolean recursive,ClassFilter filter) {
			List<String> classNames = getClassPathClasseNames(packageName, recursive);
			List<Class<?>> classes = new ArrayList<Class<?>>();
			for (String className : classNames) {
				try {
					Class<?> clazz = Class.forName(className);
					if(filter == null||filter.accept(clazz)) {
						classes.add(clazz);
					}	
				} catch (ClassNotFoundException e) {
					throw new RuntimeException("class not found : " + className, e);
				}
				catch (Exception e) {
					throw new RuntimeException("class not found : " + className, e);
				}
			}
			return classes;
		}
		

		private static void addClassFile(String packageName,
				String absolutePackagePath, final boolean recursive,
				List<String> classNames) {
			File dir = new File(absolutePackagePath);
			if (!dir.exists() || !dir.isDirectory()) {
				return;
			}
			File[] dirAndfiles = dir.listFiles(new FileFilter() {
				public boolean accept(File file) {
					return (recursive && file.isDirectory())
							|| (file.getName().endsWith(".class"));
				}
			});
			for (File file : dirAndfiles) {
				if (file.isDirectory()) {
					addClassFile(
							getClassOrPackageName(packageName, file.getName()),
							file.getAbsolutePath(), recursive, classNames);
				} else {
					String classFileName = file.getName().substring(0,
							file.getName().length() - 6);
					classNames
							.add(getClassOrPackageName(packageName, classFileName));
				}
			}
		}

		private static String getClassOrPackageName(String packageName,
				String classOrSubPackageName) {
			return (packageName != null && packageName.length() > 0) ? packageName
					+ "." + classOrSubPackageName : classOrSubPackageName;
		}
		
		
		public static Map<Method, String> paraserGet(Class<?> clazz) {
			Map<Method, String> map = new HashMap<Method, String>();
			String[] typeArray = {"String", "Long", "Integer", "int", "long", "Float", "float", 
					"Double", "double","Boolean","boolean","BigDecimal","Date","Calendar"};
			String[] excludeMethods = {"equals","hashCode","toString"};
			Method[] methods = clazz.getDeclaredMethods();
			for(Method method : methods) {
				if(ArrayUtils.contains(excludeMethods, method.getName()))
					continue;
				if (!ArrayUtils.contains(typeArray, method.getReturnType().getSimpleName()))  
					continue;
				if(method.getGenericParameterTypes().length>0) 
					continue;
				method.setAccessible(true);
				String name = method.getName();
				if(name.startsWith("get"))
					name = name.substring(3,4).toLowerCase()+name.substring(4,name.length());
				if(name.startsWith("is"))
					name = name.substring(2,3).toLowerCase()+name.substring(3,name.length());
				map.put(method, name);
			}
			return map;
		}
		
		
		public static Map<Method,Field> paraserSet(Class<?> clazz) {
			Map<Method, Field> map = new HashMap<Method, Field>();
			Method[] methods = clazz.getDeclaredMethods();
			Field[] fields = clazz.getDeclaredFields();
			for (Field field : fields) {
				Method m = getMethod(field,methods);
				if(m == null)continue;
				map.put(m, field);
			} 
			return map;
		}
		
		private static Method getMethod(Field field,Method[] methods) {
			String[] typeArray = {"String", "Long","long", "Integer", "int",  "Float", "float", 
					"Double", "double","Boolean","boolean","BigDecimal","Date","Calendar"};
			String fieldTypeName = field.getType().getSimpleName();
			if (!ArrayUtils.contains(typeArray,fieldTypeName))return null;
			for(Method method : methods) {
				if(method.getParameterTypes().length!=1) continue;
				Class<?> parameterClazz = method.getParameterTypes()[0];
				if (!parameterClazz.getSimpleName().equals(fieldTypeName))  
					continue;
				method.setAccessible(true);
				String methodName = method.getName();
				String fieldName = field.getName();
				String propertieName ="set" + fieldName.substring(0, 1).toUpperCase()+ fieldName.substring(1);
				if(methodName.equals(propertieName))return method;
			}
			return null;
		}
		
		
		public static<T> T instanceObject(Class<T> clazz)  {
			return instanceObject(clazz,null);
		}
		
		public static<T> T instanceObject(Class<T> clazz,Object[] parameters)  {
			try {
				Constructor<T> con = clazz.getDeclaredConstructor(objectConvertClass(parameters));
				con.setAccessible(true);
				return (T) con.newInstance(parameters);
			} catch (Exception e) {
				throw new RuntimeException("init Object error : "+clazz.getName(),e);
			}
		}
		
		private static Class<?>[] objectConvertClass(Object... parameters) {
			if(parameters == null||parameters.length == 0) return null;
			Class<?>[] types = new Class<?>[parameters.length];
			for(int i = 0;i<parameters.length;i++) {
				Object parameter = parameters[i];
				types[i] = parameter.getClass();
			}
			return types;
		}
		
		public static interface ClassFilter {
		    boolean accept(Class<?> clazz);
		}
		
}

