package com.shoe.controller.manager;

import java.util.Arrays;

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
import com.util.HttpParameterParser;

@Controller
@RequestMapping("/manager/")
public class ManagerController {
	@Autowired
	private ShoeService shoeService;
	@Autowired
	private CommodityService commodityService;

	@RequestMapping("index")
	public ModelAndView index(){
		return new ModelAndView("/manager/index");
	}
	
	@RequestMapping("create")
	public ModelAndView create(){
		return new ModelAndView("/manager/create_shoe");
	}
	
	@RequestMapping("update")
	public ModelAndView update(HttpServletRequest request){
		Long shoeId = HttpParameterParser.getLong(request, "shoeId");
		Shoe shoe = shoeService.get(shoeId);
		Commodity commodity = null;
		if(shoe != null){
			commodity = commodityService.get(shoe.getCommodityId());
		}
		String imagePath = shoe.getImagePath();
		String  imagePaths[] = new String[1];
		if(!StringUtils.isEmpty(imagePath)){
			if(imagePath.indexOf(",") > 0){
				imagePaths = imagePath.split(",");
			}else{
				imagePaths[0] = imagePath;
			}
		}else{
			imagePaths = new String[0];
		}
		return new ModelAndView("/manager/update_shoe")
				.addObject("shoe", shoe)
				.addObject("commodity", commodity)
				.addObject("imagePaths", Arrays.asList(imagePaths))
				.addObject("imagePath", imagePath);
	}
}
