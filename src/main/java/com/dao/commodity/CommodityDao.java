package com.dao.commodity;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.common.domain.Commodity;

@Repository("commodityDao")
public interface CommodityDao {

	/**
	 * 保存商品
	 * 
	 * @param commodity
	 * @return
	 */
	Long create(Commodity commodity);

	/**
	 * 根据id查询
	 * 
	 * @param commodityId
	 * @return
	 */
	@Select("select * from commodity where id = #{id}")
	Commodity get(@Param("id") Long commodityId);

	/**
	 * 更新
	 * 
	 * @param commodity
	 */
	void update(Commodity commodity);

	/**
	 * 刪除
	 * 
	 * @param id
	 */
	@Delete("delete from commodity where id = #{id}")
	void delete(Long id);

	/**
	 * 鞋子類型列表
	 * 
	 * @return
	 */
	@Select("select id,name from commodity")
	List<Map<String, Object>> findShoeList();

	/**
	 * 根据名字查找
	 * 
	 * @param name
	 * @return
	 */
	@Select("select * from commodity where name = #{name}")
	Commodity getByName(String name);
}
