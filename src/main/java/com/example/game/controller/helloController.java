package com.example.game.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.game.domain.Achievement;
import com.example.game.domain.AchievementRepository;


@RestController
@RequestMapping("/hello")
public class helloController {

	@Autowired
	AchievementRepository achievementRepository;
	@RequestMapping("/index")
	public Achievement index()
	{
//		Achievement a=achievementRepository.getOne("0072fa249ad6431f876465e8d65a8c28");
		Achievement a=achievementRepository.getByWjnameId("tb_spdiaochan");
		System.out.println(a);
		return a;
	}

}
