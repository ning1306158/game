package com.example.game.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler" })
public class Achievement {
	@Id
	@GeneratedValue(generator="system_uuid")
    @GenericGenerator(name="system_uuid",strategy="uuid")
	private String ids;
	@Column
	private String wjname_id;
	@Column
	private String wjname;
	@Column
	private String achievename;
	@Column
	private String achievement;
	@Column
	private String level;
	@Column(name="package")
	private String _package;
	@Column
	private String nation;
	public Achievement() {
		super();
	}
	public Achievement(String ids, String wjname_id, String wjname, String achievename, String achievement, String level,
			String _package, String nation) {
		super();
		this.ids = ids;
		this.wjname_id = wjname_id;
		this.wjname = wjname;
		this.achievename = achievename;
		this.achievement = achievement;
		this.level = level;
		this._package = _package;
		this.nation = nation;
	}
	@Override
	public String toString() {
		return "Achievement [ids=" + ids + ", wjname_id=" + wjname_id + ", wjname=" + wjname + ", achievename=" + achievename
				+ ", achievement=" + achievement + ", level=" + level + ", _package=" + _package + ", nation=" + nation
				+ "]";
	}
	public String getIds() {
		return ids;
	}
	public void setIds(String ids) {
		this.ids = ids;
	}
	public String getWjname() {
		return wjname;
	}
	public void setWjname(String wjname) {
		this.wjname = wjname;
	}
	public String getAchievename() {
		return achievename;
	}
	public void setName(String achievename) {
		this.achievename = achievename;
	}
	public String getAchievement() {
		return achievement;
	}
	public void setAchievement(String achievement) {
		this.achievement = achievement;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public String get_package() {
		return _package;
	}
	public void set_package(String _package) {
		this._package = _package;
	}
	public String getWjname_id() {
		return wjname_id;
	}
	public void setWjname_id(String wjname_id) {
		this.wjname_id = wjname_id;
	}
	public void setAchievename(String achievename) {
		this.achievename = achievename;
	}
	public String getNation() {
		return nation;
	}
	public void setNation(String nation) {
		this.nation = nation;
	}

}
