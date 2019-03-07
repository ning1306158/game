package com.example.game.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.example.game.domain.Skill;
import com.example.game.domain.SkillInfo;
import com.example.game.domain.SkillRepository;
import com.example.game.service.SkillService;
@Service
public class SkillServiceImpl implements SkillService{

	@Autowired
	private SkillRepository skillRepository;
	/**
	 * 通过实体添加
	 */
	@Override
	public void save(Skill skill) {
		skillRepository.save(skill);
	}
	/**
	 * 通过json串添加
	 */
	@Override
	public void save(String skillJson) {
		Skill skill=new Skill(skillJson);
		skillRepository.save(skill);
	}
	/**
	 * 获取所有 的详细信息
	 * @return 
	 */
	@Override
	public Page<SkillInfo> findAllSkill(String json) {
		JSONObject jsonObject=JSONObject.parseObject(json);
		int page_num=jsonObject.getIntValue("page_num");
		int page_size=jsonObject.getIntValue("page_size");
		String key="%"+jsonObject.getString("key").trim()+"%";
		List<Order> orders=new ArrayList<>();
		orders.add(new Order(Direction.DESC,"ids"));
		Sort sort=Sort.by(orders);
		Pageable pageable=PageRequest.of(page_num-1, page_size,sort);
		Page<SkillInfo> li=skillRepository.findAllSkill(key,pageable);
		return li;
	}

}
