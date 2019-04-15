package com.common.transferobj;

import java.util.Date;

import com.common.domain.Commodity;

public class CommodityTo {

	private Long id;

	private String name;

	private Date createDate;

	private Date modifyDate;

	private Double price;

	private String imagePath;
	
	public CommodityTo(Commodity commodity){
		this.id = commodity.getId();
		this.name = commodity.getName();
		this.createDate = commodity.getModifyDate();
		this.modifyDate = commodity.getModifyDate();
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	
}
