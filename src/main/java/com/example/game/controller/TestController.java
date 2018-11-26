package com.example.game.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.game.domain.Account;
import com.example.game.domain.AccountRepository;

@Controller
public class TestController {

	@Autowired
	AccountRepository accountDao;

	@RequestMapping("/QQ")
	public String index() {
		accountDao.saveAndFlush(new Account("马宁宁",12.12));
		return "test.html";
	}

}
