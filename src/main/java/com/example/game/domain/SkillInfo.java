package com.example.game.domain;

import java.io.Serializable;

public class SkillInfo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8255803897084668563L;
	private Hero hero;
	private Skill skill;
	/**
	 * @param hero
	 * @param skill
	 */
	public SkillInfo(Hero hero, Skill skill) {
		this.hero = hero;
		this.skill = skill;
	}
	/**
	 * 
	 */
	public SkillInfo() {
		super();
	}
	public Hero getHero() {
		return hero;
	}
	public void setHero(Hero hero) {
		this.hero = hero;
	}
	public Skill getSkill() {
		return skill;
	}
	public void setSkill(Skill skill) {
		this.skill = skill;
	}
}
