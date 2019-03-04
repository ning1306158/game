package com.example.game.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

import com.alibaba.fastjson.JSONObject;

@Entity
public class Skill {
	@Id
	@GeneratedValue(generator = "system_uuid")
	@GenericGenerator(name = "system_uuid", strategy = "uuid")
	private String ids;
	@Column
	private String hero_id;
	@Column
	private String skill_name;
	@Column
	private String skill_desc;
	@Column
	private int red;
	@Column
	private int yellow;
	@Column
	private int blue;
	@Column
	private int green;

	public Skill() {
		super();
	}

	public Skill(String hero_id, String skill_name, String skill_desc) {
		super();
		this.hero_id = hero_id;
		this.skill_name = skill_name;
		this.skill_desc = skill_desc;
	}

	public Skill(String hero_id, String skill_name, String skill_desc, int red, int yellow, int blue, int green) {
		super();
		this.hero_id = hero_id;
		this.skill_name = skill_name;
		this.skill_desc = skill_desc;
		this.red = red;
		this.yellow = yellow;
		this.blue = blue;
		this.green = green;
	}

	public Skill(String json) {
		JSONObject a = (JSONObject) JSONObject.parse(json);
		this.ids = a.getString("skill_id");
		this.hero_id = a.getString("hero_id");
		this.skill_name = a.getString("skill_name");
		this.skill_desc = a.getString("skill_desc");
		this.red = a.getIntValue("red");
		this.yellow = a.getIntValue("yellow");
		this.blue = a.getIntValue("blue");
		this.green = a.getIntValue("green");
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public String getHero_id() {
		return hero_id;
	}

	public void setHero_id(String hero_id) {
		this.hero_id = hero_id;
	}

	public String getSkill_name() {
		return skill_name;
	}

	public void setSkill_name(String skill_name) {
		this.skill_name = skill_name;
	}

	public String getSkill_desc() {
		return skill_desc;
	}

	public void setSkill_desc(String skill_desc) {
		this.skill_desc = skill_desc;
	}

	public int getRed() {
		return red;
	}

	public void setRed(int red) {
		this.red = red;
	}

	public int getYellow() {
		return yellow;
	}

	public void setYellow(int yellow) {
		this.yellow = yellow;
	}

	public int getBlue() {
		return blue;
	}

	public void setBlue(int blue) {
		this.blue = blue;
	}

	public int getGreen() {
		return green;
	}

	public void setGreen(int green) {
		this.green = green;
	}

	@Override
	public String toString() {
		return "Skill [ids=" + ids + ", hero_id=" + hero_id + ", skill_name=" + skill_name + ", skill_desc="
				+ skill_desc + ", red=" + red + ", yellow=" + yellow + ", blue=" + blue + ", green=" + green + "]";
	}
}
