package com.example.game.service;

import java.util.List;

import com.example.game.domain.Hero;
import com.example.game.domain.HeroInfo;

public interface HeroService {
	public void save(Hero hero);
	public int saveAllHero();
	public List<Hero> findAll();
	public List<HeroInfo> findAllSkill();
	public Hero findById(String hero_id);
}
