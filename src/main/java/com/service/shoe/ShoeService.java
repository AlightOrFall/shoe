package com.service.shoe;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.common.domain.Commodity;
import com.shoe.demo.Shoe;
import com.shoe.transferobj.ShoeTo;

public interface ShoeService {

	/**
	 * 根据条件查找
	 * 
	 * @param params
	 * @return
	 */
	List<Shoe> find(Map<String, Object> params);
	
	List<Shoe> find(ShoeTo shoeTo);

	/**
	 * 根据id查找
	 * 
	 * @param id
	 * @return
	 */
	Shoe get(Long id);

	/**
	 * 根据条件查找
	 * 
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> findParams(@Param("params") Map<String, Object> params);

	/**
	 * 保存
	 * 
	 * @param shoe
	 */
	void create(Shoe shoe);

	/**
	 * 更新信息
	 * 
	 * @param shoe
	 * @param commodity
	 */
	void update(Shoe shoe, Commodity commodity);

	/**
	 * 删除信息
	 * 
	 * @param shoeId
	 */
	void delete(Long shoeId);

	/**
	 * 查询所有
	 * 
	 * @return
	 */
	List<Map<String, Object>> findAll();
}
