package com.example.game.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.game.domain.Hero;
import com.example.game.domain.HeroInfo;
import com.example.game.domain.Skill;
import com.example.game.service.HeroService;
import com.example.game.service.SkillService;

@RestController
@RequestMapping("/Hero")
public class HeroController {
	@Autowired
	HeroService heroService;
	@Autowired
	SkillService skillService;

	@RequestMapping()
	public ModelAndView index() {
		ModelAndView mv = new ModelAndView("index");
		return mv;
	}
	@RequestMapping("/editHtml")
	public ModelAndView editHtml() {
		ModelAndView mv = new ModelAndView("edit");
		return mv;
	}
	@RequestMapping("/addHtml")
	public ModelAndView addHtml(String hero_id) {
		ModelAndView mv = new ModelAndView("addSkill");
		mv.addObject("hero_id", hero_id);
		return mv;
	}
	@RequestMapping("/findAll")
	public List<Hero> findAll() {
		return heroService.findAll(); 
	}
	@RequestMapping("/get")
	public int  getAll() {
		 heroService.saveAllHero();
		 return 0;
	}
	@RequestMapping("/findSkill")
	public List<HeroInfo> findSkill() {
		return heroService.findAllSkill(); 
	}
	@RequestMapping("/addSkill")
	public void addSkill(String hero_id,String form,String skill_name,String skill_desc) {
		System.out.println(hero_id);
		System.out.println(form);
		System.out.println(skill_name);
		System.out.println(skill_desc);
//		skillService.save(new Skill(hero_id,skill_name,skill_desc));
	}

}