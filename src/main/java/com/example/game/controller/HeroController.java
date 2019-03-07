package com.example.game.controller;

import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.codec.multipart.SynchronossPartHttpMessageReader;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.game.domain.Hero;
import com.example.game.domain.HeroInfo;
import com.example.game.domain.Skill;
import com.example.game.domain.SkillInfo;
import com.example.game.service.HeroService;
import com.example.game.service.SkillService;

@RestController
@RequestMapping("/Hero")
public class HeroController {
	@Autowired
	HeroService heroService;
	@Autowired
	SkillService skillService;

	@RequestMapping("/editHtml")
	public ModelAndView editHtml() {
		ModelAndView mv = new ModelAndView("edit");
		return mv;
	}

	@RequestMapping(value = "/addHtml/{hero_id}")
	public ModelAndView addHtml(@PathVariable("hero_id") String hero_id) {
		if (hero_id == null || "".equals(hero_id)) {
			ModelAndView mv = new ModelAndView("edit");
			return mv;
		}
		Hero hero = heroService.findById(hero_id);
		ModelAndView mv = new ModelAndView("addSkill");
		mv.addObject("hero", hero);
		return mv;
	}

	@RequestMapping("/findAll")
	public List<Hero> findAll() {
		return heroService.findAll();
	}

	@RequestMapping("/get")
	public int getAll() {
		heroService.saveAllHero();
		return 0;
	}

	@RequestMapping(value = "/addSkill")
	public void addSkill(@RequestBody String skillJson) {
		skillService.save(skillJson);
	}

	@RequestMapping("/updateSkill")
	public void updateSkill(@RequestBody String skillJson) {

	}

	@RequestMapping("/findAllSkill")
	public Page<SkillInfo> findAllSkill(@RequestBody String json) {
		return skillService.findAllSkill(json);
	}

	@RequestMapping("/findSkill")
	public Page<SkillInfo> findSkill(@RequestBody String json) {
		return skillService.findAllSkill(json);
	}
}                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                     