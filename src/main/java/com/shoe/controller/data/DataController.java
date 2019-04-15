package com.shoe.controller.data;

import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.common.domain.Commodity;
import com.service.commodity.CommodityService;
import com.service.shoe.ShoeService;
import com.shoe.demo.Shoe;
import com.shoe.transferobj.ShoeTo;
import com.shoe.utils.Constant;
import com.shoe.utils.FileUploadUtils;
import com.shoe.view.InterfaceJsonView;
import com.util.HttpParameterParser;

@Controller
@RequestMapping("outside/web/shoe")
public class DataController {

	@Autowired
	private ShoeService shoeService;
	@Autowired
	private CommodityService commodityService;
	
	@RequestMapping("find")
	public ModelAndView findAll(){
		List<Map<String, Object>> shoes = shoeService.findAll();
		return new ModelAndView(new InterfaceJsonView(shoes));
	}

	@RequestMapping("find_params")
	public ModelAndView findParams(HttpServletRequest request) {
		String color = HttpParameterParser.getString(request, "color");
		String name = HttpParameterParser.getString(request, "name");
		Double price1 = HttpParameterParser.getDouble(request, "price1");
		Double price2 = HttpParameterParser.getDouble(request, "price2");
		int currentPage = HttpParameterParser.getIntValue(request, "currentPage");
		int pageSize = HttpParameterParser.getIntValue(request, "pageSize");
		Map<String, Object> params = new HashMap<String, Object>();
		if (!StringUtils.isEmpty(name)) {
			params.put("name", name);
		}
		if (!StringUtils.isEmpty(color)) {
			params.put("color", color);
		}
		if(price1 != null){
			params.put("price1", price1);
		}
		if(price2 != null){
			params.put("price2", price2);
		}
		List<Map<String, Object>> params2 = shoeService.findParams(params);
		int currentPage1 = (currentPage-1)*pageSize;
		if(currentPage > params2.size()){
			currentPage1 = 0;
		}
		int pageSzie = currentPage*pageSize;
		if(pageSzie > params2.size()){
			pageSzie = params2.size();
		}
		Map<String, Object> pagination = new HashMap<String, Object>();
		pagination.put("currentPage",currentPage);
		pagination.put("pageSzie",pageSzie);
		pagination.put("count",params2.size());
		int pageCount = params2.size()/pageSzie;
		if((params2.size()%pageSzie) < pageSzie && (params2.size()) > pageSzie){
			pageCount = pageCount + 1;
		}
		params2 = params2.subList(currentPage1, pageSzie);
		pagination.put("pageCount",pageCount);
		Map<String, Object> content = new HashMap<String, Object>();
		content.put("shoes", params2);
		content.put("pagination", pagination);
		return new ModelAndView(new InterfaceJsonView(content));
	}
	@RequestMapping("find2")
	public ModelAndView find2(HttpServletRequest request) {
		Long id = HttpParameterParser.getLong(request, "id");
		String color = HttpParameterParser.getString(request, "color");
		Shoe shoe = shoeService.get(id);
		ShoeTo shoeTo = new ShoeTo(shoe);
		shoeTo.setId(id);
		List<Shoe> find = shoeService.find(shoeTo);
		return new ModelAndView(new InterfaceJsonView(find));
	}

	@RequestMapping("create")
	public ModelAndView create(HttpServletRequest request) {
		String color = HttpParameterParser.getString(request, "color");
		double price = HttpParameterParser.getDoubleValue(request, "price");
		String filePaths = HttpParameterParser.getString(request, "filePaths");
		Long commodityId = HttpParameterParser.getLong(request, "commodityId");
		int count = HttpParameterParser.getIntValue(request, "count");
		if(commodityId == null){
			throw new RuntimeException("参数异常");
		}
		Shoe shoe = new Shoe();
		shoe.setColor(color);
		shoe.setPrice(price);
		shoe.setSale(true);
		shoe.setCommodityId(commodityId);
		shoe.setImagePath(filePaths);
		shoe.setCount(count);
		shoeService.create(shoe);
		Map<String, Object> content = new HashMap<String, Object>();
		content.put("success", true);
		return new ModelAndView(new InterfaceJsonView(content));
	}
	
	@RequestMapping("update")
	public ModelAndView update(HttpServletRequest request) {
		Long shoeId = HttpParameterParser.getLong(request, "shoeId");
		String color = HttpParameterParser.getString(request, "color");
		String name = HttpParameterParser.getString(request, "name");
		Double price = HttpParameterParser.getDouble(request, "price");
		int count = HttpParameterParser.getIntValue(request, "count");
		int saleCount = HttpParameterParser.getIntValue(request, "saleCount");
		String filePaths = HttpParameterParser.getString(request, "filePaths");
		boolean isSale = HttpParameterParser.getBooleanValue(request, "isSale");
		if (price == null || shoeId == null || StringUtils.isEmpty(name) || StringUtils.isEmpty(color)) {
			throw new RuntimeException("参数异常");
		}
		if(count < saleCount){
			throw new RuntimeException("总数不能小于出售数量");
		}
		Shoe shoe = shoeService.get(shoeId);
		Long commodityId = shoe.getCommodityId();
		Commodity commodity = commodityService.get(commodityId);
		commodity.setName(name);
//		commodity.setCount(count);
		commodity.setModifyDate(Calendar.getInstance().getTime());
		shoe.setPrice(price);
		shoe.setCount(count);
		shoe.setSaleCount(saleCount);
		shoe.setImagePath(filePaths);
		shoe.setColor(color);
		shoe.setSale(isSale);
		shoeService.update(shoe, commodity);
		Map<String, Object> content = new HashMap<String, Object>();
		content.put("success", true);
		return new ModelAndView(new InterfaceJsonView(content));
	}

	@RequestMapping("delete")
	public ModelAndView delete(HttpServletRequest request) {
		Long shoeId = HttpParameterParser.getLong(request, "shoeId");
		if(shoeId == null){
			throw new RuntimeException("参数异常");
		}
		shoeService.delete(shoeId);
		return new ModelAndView(new InterfaceJsonView());
	}
	
	@RequestMapping("find_shoe_type_list")
	public ModelAndView findShoeTypeList(){
		List<Map<String, Object>> shoeList = commodityService.findShoeList();
		return new ModelAndView(new InterfaceJsonView(shoeList));
	}
	
	@RequestMapping("upload")
	public ModelAndView upLoad(HttpServletRequest request) {
		String filePath = null;
		try {
			filePath = FileUploadUtils.uploadFile(request, "upload_image", Constant.SHOE_TEMP);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new ModelAndView(new InterfaceJsonView(filePath));
	}
	
	
}
