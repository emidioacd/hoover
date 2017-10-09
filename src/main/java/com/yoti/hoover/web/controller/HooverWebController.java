package com.yoti.hoover.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yoti.hoover.service.IHooverService;
import com.yoti.hoover.web.model.HooverRequest;
import com.yoti.hoover.web.model.IHooverResponse;

@Controller
@RequestMapping("/hoover")
public class HooverWebController {

	@Autowired
	private IHooverService hoverService;

	@RequestMapping(method = RequestMethod.POST)
	public @ResponseBody IHooverResponse hoverRoom(@RequestBody HooverRequest hooverRequest) {
		return hoverService.hoover(hooverRequest);
	}
}
