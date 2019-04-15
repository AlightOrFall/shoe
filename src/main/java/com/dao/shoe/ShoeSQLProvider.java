package com.dao.shoe;

import org.apache.ibatis.jdbc.SQL;
import org.springframework.util.StringUtils;

import com.shoe.transferobj.ShoeTo;

public class ShoeSQLProvider {

	public String get(ShoeTo shoeTo) {
		// 创建SQL对象并设置select语句要查询的列
		SQL sql = new SQL().SELECT(" id,commodity_id,color ");
		addFrom(sql); // 添加from语句
		addWhere(sql, shoeTo); // 添加where语句
		String sql2 = sql.toString();
		/*
		 * if(shoeTo.getPageSize() != 0 && shoeTo.getCurrentPage() != 0){
		 * sql2+=" limit #{currentPage},#{pageSize}"; }
		 */
		return sql2;
	}

	private void addFrom(SQL sql) {
		new SQL().FROM("shoe");
	}

	private void addWhere(SQL sql, ShoeTo shoeTo) {
		if (shoeTo != null) {
			if (shoeTo.getId() != null) {
				sql.WHERE("id = #{id}");
			}
			if (StringUtils.isEmpty(shoeTo.getColor())) {
				sql.WHERE("color = #{color}");
			}
			if (shoeTo.getCommodityId() != null) {
				sql.WHERE("commodity_id = #{commodity_id}");
			}
			if (shoeTo.getPrice() != null) {
				sql.WHERE("price >= #{price}");
			}
			if (shoeTo.getPrice2() != null) {
				sql.WHERE("price <= #{price2}");
			}
		}

	}
}
