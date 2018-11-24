package com.example.game.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.game.domain.User;

@RestController
@Component
public class helloController {
	@Value("${com.neo}")
	String name;
	@RequestMapping()
	public String index()
	{
		return ""+name;
	}

}
