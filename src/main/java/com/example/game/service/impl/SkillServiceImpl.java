package com.example.game.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.game.domain.Skill;
import com.example.game.domain.SkillRepository;
import com.example.game.service.SkillService;
@Service
public class SkillServiceImpl implements SkillService{

	@Autowired
	private SkillRepository skillRepository;
	@Override
	public void save(Skill skill) {
		skillRepository.save(skill);
	}

}
