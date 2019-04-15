package com.shoe.view;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.CycleDetectionStrategy;

import org.springframework.web.servlet.view.AbstractView;

public class InterfaceJsonView extends AbstractView {

//	private static Logger logger = Logger.getLogger(InterfaceJsonView.class);

	private Map<String, Object> map;

	private JsonConfig jsonConfig;

	private Header header;

	private String jsonString;

	private Object object;

	// private boolean success;

	public InterfaceJsonView() {
	}

	/**
	 * 需要转换的map,不用在map中加入header
	 * 
	 * @param map
	 */
	public InterfaceJsonView(Map<String, Object> map) {
		this(map, null);
	}

	/**
	 * 需要转换的map
	 * 
	 * @param map
	 * @param jsonConfig
	 */
	public InterfaceJsonView(Map<String, Object> map, JsonConfig jsonConfig) {
		this.map = map;
		this.jsonConfig = jsonConfig;
	}

	/**
	 * 将对象转换成json
	 * 
	 * @param object
	 *            需要转换的json的对象
	 */
	public InterfaceJsonView(Object object) {
		this(object, null);
	}

	/**
	 * 将对象转换成json
	 * 
	 * @param object
	 * @param jsonConfig
	 */
	public InterfaceJsonView(Object object, JsonConfig jsonConfig) {
		this.jsonConfig = jsonConfig;
		this.object = object;
	}

	/**
	 * 返回头信息
	 * 
	 * @param header
	 */
	public InterfaceJsonView(Header header) {
		this.header = header;
	}

	/**
	 * 返回头信息
	 * 
	 * @param header
	 */
	/*
	 * public InterfaceJsonView(Header header,boolean success) { this.header =
	 * header; this.success = success; }
	 */

	/**
	 * 只有一个返回内容的
	 * 
	 * @param name
	 * @param value
	 */
	public InterfaceJsonView(String name, Object value) {
		this.map = new HashMap<String, Object>();
		map.put(name, value);
	}

	@SuppressWarnings("rawtypes")
	@Override
	protected void renderMergedOutputModel(Map model, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		Map<String, Object> content = new HashMap<String, Object>();
		// content.put("success", success);
		if (map != null && !map.isEmpty()) {
			content.put("content", map);
		}
		if (header == null) {
			Map<String, Object> headerMap = new HashMap<String, Object>();
			headerMap.put("flag", 1);
			content.put("header", headerMap);
		} else {
			content.put("header", header);
		}
		if (object != null) {
			content.put("content", object);
		}
		if (jsonConfig == null) {
			jsonConfig = new JsonConfig();
		}
		jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
		jsonConfig.registerJsonValueProcessor(Date.class, new DateJsonValueProcessor("yyyy-MM-dd HH:mm:ss"));
		jsonConfig.registerJsonValueProcessor(Timestamp.class, new DateJsonValueProcessor("yyyy-MM-dd HH:mm:ss"));

		writeJSON(response, content, jsonConfig);
	}

	public Header getHeader() {
		return header;
	}

	/*	*//**
			 * 输出json
			 * 
			 * @param response
			 * @param map
			 *//*
			 * private void writeJSON(HttpServletResponse response, Map<String,
			 * Object> map) { writeJSON(response, map, null); }
			 */

	/**
	 * 输出json
	 * 
	 * @param response
	 * @param map
	 * @param jsonConfig
	 */
	private void writeJSON(HttpServletResponse response, Map<String, Object> map, JsonConfig jsonConfig) {
		response.setContentType("text/html;charset=utf-8");
		JSONObject jsonObject = null;
		jsonObject = JSONObject.fromObject(map, jsonConfig);
		jsonString = jsonObject == null ? "{}" : jsonObject.toString();
		try {
			response.getWriter().write(jsonString);
		} catch (IOException e) {
//			logger.error(getClass(), e);
		}
	}

	/**
	 * 获得json的字符串
	 * 
	 * @return
	 */
	public String getJsonString() {
		return jsonString;
	}

	public Map<String, Object> getMap() {
		return map;
	}

	public void setMap(Map<String, Object> map) {
		this.map = map;
	}

	public JsonConfig getJsonConfig() {
		return jsonConfig;
	}

	public void setJsonConfig(JsonConfig jsonConfig) {
		this.jsonConfig = jsonConfig;
	}

	public Object getObject() {
		return object;
	}

	public void setObject(Object object) {
		this.object = object;
	}

}
