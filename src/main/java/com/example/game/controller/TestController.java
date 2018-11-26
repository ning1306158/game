package com.example.game.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.game.domain.Account;
import com.example.game.domain.AccountRepository;

@Controller
public class TestController {

	@Autowired
	AccountRepository accountDao;

	@RequestMapping("/QQ")
	public String index() {
		Account a= accountDao.getByName("张三");
		return "/account/hello";
	}

}
