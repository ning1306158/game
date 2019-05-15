package com.example.game.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.example.game.domain.Skill;
import com.example.game.domain.SkillInfo;

public interface SkillService {
	public void save(Skill skill);
	public void save(String skillJson);
	public Page<SkillInfo> findAllSkill(String json);
	public Page<SkillInfo> findSkillColor(String json);
}
