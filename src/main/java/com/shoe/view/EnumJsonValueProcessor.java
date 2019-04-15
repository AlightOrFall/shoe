package com.shoe.view;

import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;



public class EnumJsonValueProcessor implements JsonValueProcessor {

	@Override
	public Object processArrayValue(Object value, JsonConfig arg1) {
		return process(value);
	}

	@Override
	public Object processObjectValue(String key, Object value, JsonConfig jsonConfig) {
		return process(value);
	}

	public EnumJsonValueProcessor() {
		
	}
	
	   private Object process(Object value) {
	       if (value == null) {
	           return "";
	       } else {
	    	   EnumTypeInterface enumObj=(EnumTypeInterface)value;
	    	   
	           return enumObj.getValue();
	       }
	   }

}

