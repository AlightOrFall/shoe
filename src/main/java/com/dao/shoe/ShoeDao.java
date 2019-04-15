package com.dao.shoe;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;
import org.springframework.stereotype.Repository;

import com.shoe.demo.Shoe;
import com.shoe.transferobj.ShoeTo;

@Repository("shoeDao")
public interface ShoeDao {

	/**
	 * 根据条件查找
	 * 
	 * @return
	 */
	List<Shoe> find(@Param("params") Map<String, Object> params);
	
	@SelectProvider(type = ShoeSQLProvider.class, method = "get")
	List<Shoe> find2(ShoeTo shoeTo);

	/**
	 * 根据id查找
	 * 
	 * @param id
	 * @return
	 */
	Shoe get(@Param("id") Long id);

	/**
	 * 根据条件查找
	 * 
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> findParams(@Param("params") Map<String, Object> params);

	/**
	 * 创建商品
	 * 
	 * @param shoe
	 */
	void create(Shoe shoe);

	/**
	 * 更新
	 * 
	 * @param shoe
	 */
	void update(Shoe shoe);

	/**
	 * 刪除信息
	 * 
	 * @param shoeId
	 */
	void delete(Long id);

	/**
	 * 查询所有
	 * 
	 * @return
	 */
	List<Map<String, Object>> findAll();
}
