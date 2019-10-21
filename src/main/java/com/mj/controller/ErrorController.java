package com.mj.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ErrorController {

	// 403权限不足页面
	@RequestMapping("/error/403")
	public String error() {
		return "/error/403";
	}

}
