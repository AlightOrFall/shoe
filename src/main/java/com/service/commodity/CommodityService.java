package com.service.commodity;

import java.util.List;
import java.util.Map;

import com.common.domain.Commodity;

public interface CommodityService {

	/**
	 * 添加商品
	 * 
	 * @param commodity
	 * @return
	 */
	Long create(Commodity commodity);

	/**
	 * 根据id获取
	 * 
	 * @param commodityId
	 * @return
	 */
	Commodity get(Long commodityId);

	/**
	 * 更新商品信息
	 * 
	 * @param commodity
	 */
	void update(Commodity commodity);

	/**
	 * 鞋子類型列表
	 * 
	 * @return
	 */
	List<Map<String, Object>> findShoeList();

	/**
	 * 刪除信息
	 * 
	 * @param id
	 */
	void delete(Long id);
	
	/**
	 * 根据名字查找
	 * 
	 * @param name
	 * @return
	 */
	Commodity getByName(String name);
}
