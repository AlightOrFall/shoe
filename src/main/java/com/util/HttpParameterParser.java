/*
 * @(#)HttpParameterParser.java
 *
 * Copyright 2008 Xinhua Online, Inc. All rights reserved.
 */

package com.util;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class HttpParameterParser {
	private static final Log LOG = LogFactory.getLog(HttpParameterParser.class);
	private static  DateFormat DEFAULT_DATE_FORMAT = new SimpleDateFormat(
			"yyyy-MM-dd");
	
	public static void setDateFormat(String format) {
		DEFAULT_DATE_FORMAT = new SimpleDateFormat(format);
	}

	public static  String[] getStringArray(HttpServletRequest request,String key) {
		Map<String,String[]> parameters = request.getParameterMap();
		return  parameters.get(key);
	}
	
	public static String[] getStringArray(HttpServletRequest request, String key,String split){
		String string=getString(request,key);
		if(StringUtils.isEmpty(string)) return null;
		return string.split(split);
	}
	
	public static List<String> getStringList(HttpServletRequest request,String key){
		String[] stringArray=getStringArray(request,key);
		return getStringList(request,stringArray);
		 
	}
	
	public static List<String> getStringList(HttpServletRequest request,String key,String split){
		String[] stringArray=getStringArray(request,key,split);
		return getStringList(request,stringArray);
	}
	
	private static List<String> getStringList(HttpServletRequest request,String[] stringArray){
		if(stringArray==null||stringArray.length == 0) return null;
		List<String> result = new ArrayList<String>();
		for(int i=0;i<stringArray.length;i++)
			result.add(stringArray[i]);
		return result;
	}

	public static int[] getIntArray(HttpServletRequest request,String key) {
		String[] values = getStringArray(request,key);
		if(values==null||values.length == 0) return null;
		int[] results = new int[values.length];
		for (int i = 0; i < values.length; i++) {
			String string = values[i];
			try {
				results[i] = Integer.parseInt(string);
			}
			catch (Exception e) {
				results[i] = 0; 
				LOG.info("parse int error : "+string, e);
			}
		}
		return results;
	}
	
	public static Long[] getLongArray(HttpServletRequest request,String key) {
		String[] values = getStringArray(request,key);
		if(values==null||values.length == 0) return null;
		Long[] results = new Long[values.length];
		for (int i = 0; i < values.length; i++) {
			String string = values[i];
			try {
				results[i] = Long.valueOf(string);
			}
			catch (Exception e) {
				results[i] = 0L; 
				LOG.info("parse int error : "+string, e);
			}
		}
		return results;
	}
	public static String getString(HttpServletRequest request,String key) {
		String[] values = getStringArray(request,key);
		if (values != null && values.length > 0)
			return values[0].trim();
		return null;
	}
	
	public static Boolean getBoolean(HttpServletRequest request,String key) {
		String str = getString(request,key);
		if(StringUtils.isEmpty(str)) return null;
		if(str.equalsIgnoreCase("true")||str.equals("1")||str.equals("是")||str.equalsIgnoreCase("yes"))
			return Boolean.TRUE;
		return Boolean.FALSE;
	}
	 
	
	public static boolean getBooleanValue(HttpServletRequest request,String key) {
		Boolean value = getBoolean(request,key);
		if(value == null) return false;
		return value.booleanValue();
	}
	
	public static Integer getInteger(HttpServletRequest request,String key) {
		String str = getString(request,key);
		if(StringUtils.isEmpty(str)) return null;
		try {
			return Integer.valueOf(str);
		} catch (Exception e) {
			LOG.info("parse int error : "+str, e);
			return null;
		}
	}
	
	public static Integer[] getIntegerArray(HttpServletRequest request,String key){
		String[] values = getStringArray(request,key);
		if(values==null||values.length == 0) return null;
		Integer[] results = new Integer[values.length];
		for (int i = 0; i < values.length; i++) {
			String string = values[i];
			try {
				results[i] = Integer.valueOf(string);
			} catch (Exception e) {
				LOG.info("parse int error : "+string, e);
				results[i] = null;
			}
		}
		return results;
	}
	
	public static int getIntValue(HttpServletRequest request,String key, int defaultValue) {
		Integer integer = getInteger(request,key);
		if (integer != null)
			return integer.intValue();
		return defaultValue;
	}
	
	public static int getIntValue(HttpServletRequest request,String key) {
		return getIntValue(request,key, 0);
	}
	
	public static Double getDouble(HttpServletRequest request,String key) {
		String str = getString(request,key);
		if(StringUtils.isEmpty(str)) return null;
		try {
			return Double.valueOf(str);
		} catch (Exception e) {
			LOG.info("parse double error : "+str, e);
			return null;
		}
	}
	
	public static double getDoubleValue(HttpServletRequest request,String key, double defaultValue) {
		Double d = getDouble(request,key);
		if (d != null)
			return d.doubleValue();
		return defaultValue;
	}

	public static double getDoubleValue(HttpServletRequest request,String key) {
		return getDoubleValue(request,key, 0d);
	}
	
	
	public static Long getLong(HttpServletRequest request,String key) {
		String str = getString(request,key);
		if(StringUtils.isEmpty(str)) return null;
		try {
			return Long.valueOf(str);
		} catch (Exception e) {
			LOG.info("parse long error : "+str, e);
			return null;
		}
	}

	public static long getLongValue(HttpServletRequest request,String key, long defaultValue) {
		Long l = getLong(request,key);
		if (l != null)
			return l.longValue();
		return defaultValue;
	}

	public static long getLongValue(HttpServletRequest request,String key) {
		return getLongValue(request,key, 0);
	}

	public static Short getShort(HttpServletRequest request,String key) {
		String str = getString(request,key);
		if(StringUtils.isEmpty(str)) return null;
		try {
			return Short.valueOf(str);
		} catch (Exception e) {
			LOG.info("parse short error : "+str, e);
			return null;
		}
	}
	
	public static Short[] getShortArray(HttpServletRequest request,String key){
		String[] values = getStringArray(request,key);
		if(values==null||values.length == 0) return null;
		Short[] results = new Short[values.length];
		for (int i = 0; i < values.length; i++) {
			String string = values[i];
			try {
				results[i] = Short.valueOf(string);
			} catch (Exception e) {
				LOG.info("parse short error : "+string, e);
				results[i] = null;
			}
		}
		return results;
	}
	
	public static short getShortValue(HttpServletRequest request,String key, short defaultValue) {
		Short s = getShort(request,key);
		if (s != null)
			return s.shortValue();
		return defaultValue;
	}

	public static short getShortValue(HttpServletRequest request,String key) {
		return getShortValue(request,key, (short) 0);
	}
	
	
	public static Date getDate(HttpServletRequest request,String key, String format) {
		Date date = null;
		String str = getString(request,key);
		if(StringUtils.isEmpty(str)) return null;
		DateFormat dateFormat = format == null ? DEFAULT_DATE_FORMAT
				: new SimpleDateFormat(format);
		try {
			date = dateFormat.parse(str);
		} catch (Exception e) {
			LOG.info("parse date error : "+str, e);
			return null;
		}
		return date;
	}
	
	
	public static Date getDate(HttpServletRequest request,String key) {
		return getDate(request,key, null);
	}

	public static java.sql.Date getSqlDate(HttpServletRequest request,String key, String format) {
		Date date = getDate(request,key, format);
		if (date != null)
			return new java.sql.Date(date.getTime());
		else
			return null;
	}

	public static java.sql.Date getSqlDate(HttpServletRequest request,String key) {
		return getSqlDate(request,key, null);
	}

	public static BigDecimal getBigDecimal(HttpServletRequest request,String key) {
		String value = getString(request,key);
		if(StringUtils.isEmpty(value)) return null;
		try {
			return new BigDecimal(value);
		} catch (Exception e) {
			LOG.info("parse date error : "+value, e);
			return null;
		}
	}
	
	public static Calendar getCalendar(HttpServletRequest request,String key) {
		Date date = getDate(request,key);
		if (date == null) return null;
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal;
	}
	
	
	@Deprecated
	public static<T> T getObject(HttpServletRequest request,Class<T> clazz) {
		T object =  ClassUtils.instanceObject(clazz);
		Map<Method,Field> map = ClassUtils.paraserSet(clazz);
		for(Method method : map.keySet()) {
			 Field field = map.get(method);
			 Object requestVlaue = getRequestValue(request,field);
			 if(requestVlaue == null) continue;
			 try {
				method.invoke(object, requestVlaue);
			} catch (Exception e) {
				LOG.info("method invoke error.",e);
				continue;
			}
		}
		return object;
	}
	
	private static Object getRequestValue(HttpServletRequest request,Field field) {
		 String fieldType = field.getType().getName();
		 String fieldName = field.getName();
		 if (fieldType.equals("java.lang.String")) {
			 return getString(request,fieldName);
		 }
		 if (fieldType.equals("long") || fieldType.equals("java.lang.Long")) {
			 return getLong(request,fieldName);
		 }
		 if (fieldType.equals("int") || fieldType.equals("java.lang.Integer")) {
			 return getInteger(request,fieldName);
		 }
		 if (fieldType.equals("short") || fieldType.equals("java.lang.Short")) {
			 return getShort(request,fieldName);
		 }
		 if (fieldType.equals("double") || fieldType.equals("java.lang.Double")) {
			 return getDouble(request,fieldName);
		 }
		 if (fieldType.equals("boolean") || fieldType.equals("java.lang.Boolean")) {
			 return getBoolean(request,fieldName);
		 }
		 if (fieldType.equals("java.math.BigDecimal")) {
			 return getBigDecimal(request,fieldName);
		 }
		 if (fieldType.equals("java.sql.Date")) {
			 return getSqlDate(request,fieldName);
		 }
		 if (fieldType.equals("java.util.Date")) {
			 return getDate(request,fieldName);
		 }
		 if (fieldType.equals("java.util.Calendar")) {
			 return getCalendar(request,fieldName);
		 }
		 return null;
	}
    
	
	public static <E extends Serializable> E get(HttpServletRequest request, Class<E> clazz) {
        if (request == null)
            throw new IllegalArgumentException("FormBeanUtil.get中的request为空");
        E obj = null;
        try {
            obj = clazz.newInstance();
            Map<String, String[]> parameterMap = request.getParameterMap();
            BeanUtils.populate(obj, parameterMap);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj;
    }
	
	 
}
