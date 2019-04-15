package com.controller.commodity;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.common.domain.Commodity;
import com.service.commodity.CommodityService;
import com.shoe.view.InterfaceJsonView;
import com.util.HttpParameterParser;

@Controller
@RequestMapping("outside/web/commodity")
public class CommondityController {

	@Autowired
	private CommodityService commodityService;
	
	@RequestMapping("create")
	public ModelAndView create(HttpServletRequest request){
		String name = HttpParameterParser.getString(request, "name");
		int count = HttpParameterParser.getIntValue(request, "count");
		Commodity commodity = new Commodity();
		commodity.setCount(count);
		commodity.setCreateDate(Calendar.getInstance().getTime());
		commodity.setName(name);
		commodityService.create(commodity );
		Map<String, Object> content = new HashMap<String, Object>();
		content.put("success",true);
		return new ModelAndView(new InterfaceJsonView(content));
	}
}
