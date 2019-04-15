/*
 * @(#)PropertiesUtils.java
 *
 * Copyright 2013 Vision, Inc. All rights reserved.
 */

/**
 * 
 */
package com.shoe.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

/**
 * description
 * @author  shiliang
 * @version 1.0,2013-7-1
 */

public class PropertiesUtils {
	
	/**
	 * 读取版本信息配置文件
	 * @return
	 * @throws Exception 
	 */
	private static Properties getVersionInfo(){
		InputStream inputStream = PropertiesUtils.class.getClassLoader().getResourceAsStream("/version.properties");
		Properties p = new Properties();   
		  try {
			  if(inputStream != null)
			  p.load(inputStream);
			  return p;
		  } catch (IOException e1) {   
			  e1.printStackTrace();   
		  }
		  return null;
	}
	
	/**
	 * 读取socket配置信息文件
	 * @return
	 */
	private static Properties getSocketInfo(){
		InputStream inputStream = PropertiesUtils.class.getClassLoader().getResourceAsStream("/client_socket.properties");
		Properties p = new Properties();   
		  try {
			  if(inputStream != null)
			  p.load(inputStream);
			  return p;
		  } catch (IOException e1) {   
			  e1.printStackTrace();   
		  }
		  return null;
	}
	
	/**
	 * 根据键获得属性文件中的值(socket文件信息)
	 * @param key
	 * @return
	 */
	public static String getSocketAttribute(String key){
		Properties pro =  getSocketInfo();
		return pro.getProperty(key);
	}
	
	/**
	 * 根据键获得属性文件中的值(version文件信息)
	 * @param key
	 * @return
	 */
	public static String getVersionAttribute(String key){
		Properties pro =  getVersionInfo();
		return pro.getProperty(key);
	}
	
	public static String getUTF8String(String str){
		try {
			return new String(str.getBytes("ISO-8859-1"),"UTF-8");
		} catch (UnsupportedEncodingException e) {
			return null;
		}
	}
	

}

