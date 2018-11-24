package com.example.game.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class helloController {
	@RequestMapping()
	public String index()
	{
		
		return "safsd";
	}

}
