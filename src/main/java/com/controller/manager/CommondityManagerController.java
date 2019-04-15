package com.controller.manager;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("commodity/manager")
public class CommondityManagerController {

	@RequestMapping("create")
	public ModelAndView create(){
		return new ModelAndView("/manager/create_commodity");
	}
}
