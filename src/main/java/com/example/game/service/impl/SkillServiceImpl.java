package com.example.game.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

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
public class SkillServiceImpl implements SkillService {

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
		Skill skill = new Skill(skillJson);
		skillRepository.save(skill);
	}

	/**
	 * 获取所有 的详细信息
	 * 
	 * @return
	 */
	@Override
	public Page<SkillInfo> findAllSkill(String json) {
		JSONObject jsonObject = JSONObject.parseObject(json);
		int page_num = jsonObject.getIntValue("page_num");
		int page_size = jsonObject.getIntValue("page_size");
		String key = "%" + jsonObject.getString("key").trim() + "%";
		List<Order> orders = new ArrayList<>();
		orders.add(new Order(Direction.DESC, "ids"));
		Sort sort = Sort.by(orders);
		Pageable pageable = PageRequest.of(page_num - 1, page_size, sort);
		Page<SkillInfo> li = skillRepository.findAllSkill(key, pageable);
		return li;
	}

	/**
	 * 根据颜色筛选技能
	 */
	@Override
	public Page<SkillInfo> findSkillColor(String json) {
		JSONObject jsonObject = JSONObject.parseObject(json);
		int[] color = new int[4];
		Pattern pattern = Pattern.compile("[0-9]*");
		color[0] = pattern.matcher(jsonObject.getString("red")).matches() ? jsonObject.getIntValue("red") : 0;
		color[1] = pattern.matcher(jsonObject.getString("green")).matches() ? jsonObject.getIntValue("green") : 0;
		color[2] = pattern.matcher(jsonObject.getString("blue")).matches() ? jsonObject.getIntValue("blue") : 0;
		color[3] = pattern.matcher(jsonObject.getString("yellow")).matches() ? jsonObject.getIntValue("yellow") : 0;
		int[] tmpcolor = new int[4];
		for (int i = 0; i < 4; i++) {
			tmpcolor[i] = color[i];
		}
		List<Order> orders = new ArrayList<>();
		String[] maxcolor = { "red", "green", "blue", "yellow" };
		for (int i = 1; i < 4; i++) {
			for (int j = 0; j < 4 - i; j++) {
				if (color[j] < color[j + 1]) {
					int t = color[j];
					String tmp = maxcolor[j];
					color[j] = color[j + 1];
					maxcolor[j] = maxcolor[j + 1];
					color[j + 1] = t;
					maxcolor[j + 1] = tmp;
				}
			}
		}
		for (int i = 0; i < 4; i++) {
			orders.add(new Order(Direction.DESC, "a." + maxcolor[i]));
		}
		Sort sort = Sort.by(orders);
		Pageable pageable = PageRequest.of(0, 300, sort);
		Page<SkillInfo> li = skillRepository.findSkillColor(tmpcolor[0], tmpcolor[1], tmpcolor[2], tmpcolor[3], pageable);
		return li;
	}

}
