package com.shoe.controller.page;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/page/")
public class PageController {
	
	@RequestMapping("index")
	public ModelAndView index(){
		return new ModelAndView("index");
	}
}
