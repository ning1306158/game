package com.example.game.domain;

import java.io.Serializable;
import java.util.List;

public class HeroInfo implements Serializable{
	private static final long serialVersionUID = -4409095361935488227L;
	private Hero hero;
	private List<Skill> listSkill;
	public Hero getHero() {
		return hero;
	}
	public void setHero(Hero hero) {
		this.hero = hero;
	}
	public List<Skill> getListSkill() {
		return listSkill;
	}
	public void setListSkill(List<Skill> listSkill) {
		this.listSkill = listSkill;
	}
}
