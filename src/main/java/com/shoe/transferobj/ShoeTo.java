package com.shoe.transferobj;

import com.shoe.demo.Shoe;
import com.transferobj.PaginationTo;

public class ShoeTo extends PaginationTo{

	private static final long serialVersionUID = 1L;

	private Long id;

	private Long commodityId;

	private String color;

	private boolean isSale;

	private Double price;
	
	private Double price2;

	private String imagePath;

	private Double shoeSize;

	private int count;

	private int saleCount;
	
	public ShoeTo(Shoe shoe){
		this.id = shoe.getId();
		this.commodityId = shoe.getCommodityId();
		this.color = shoe.getColor();
		this.count = shoe.getCount();
		this.shoeSize = shoe.getSize();
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getCommodityId() {
		return commodityId;
	}

	public void setCommodityId(Long commodityId) {
		this.commodityId = commodityId;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public boolean isSale() {
		return isSale;
	}

	public void setSale(boolean isSale) {
		this.isSale = isSale;
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

	public Double getShoeSize() {
		return shoeSize;
	}

	public void setShoeSize(Double shoeSize) {
		this.shoeSize = shoeSize;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getSaleCount() {
		return saleCount;
	}

	public void setSaleCount(int saleCount) {
		this.saleCount = saleCount;
	}

	public Double getPrice2() {
		return price2;
	}

	public void setPrice2(Double price2) {
		this.price2 = price2;
	}

}
