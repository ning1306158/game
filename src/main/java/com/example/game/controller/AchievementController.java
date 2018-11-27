package com.example.game.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.game.domain.Achievement;
import com.example.game.service.AchievementService;

@Controller
@RequestMapping("/achievement")
public class AchievementController {
	@Resource
	AchievementService achievementService;

	@RequestMapping()
	public String index(ModelMap map) {
		List<Achievement> list = achievementService.findByWjId("ds_xunyu");
		map.put("wj", list);
		return "achievement/list";
	}

	@RequestMapping("/findByWjnameLike")
	public String findByWjnameLike(String wjname, Integer pageNum, Integer pageSize, ModelMap map) {
		System.err.println(wjname + " " + pageNum + " " + pageSize);
		if (null == pageSize || pageSize <= 0)
			pageSize = 20;
		if (null == pageNum || pageNum <= 0)
			pageNum = 0;
		List<Achievement> list = achievementService.findByWjnameLike("%" + wjname + "%", pageNum, pageSize);
		List<Achievement> nextlist = achievementService.findByWjnameLike("%" + wjname + "%", pageNum + 1, pageSize);
		if (nextlist.size() > 0) {
			map.put("hasNext", true);
		} else {
			map.put("hasNext", false);
		}
		map.put("wj", list);
		map.put("pageNum", pageNum);
		map.put("wjname", wjname);
		return "achievement/list";
	}

}
