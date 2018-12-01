package com.example.game.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.game.domain.Achievement;
import com.example.game.service.AchievementService;

@Controller
@RequestMapping("/achievement")
public class AchievementController {
	@Resource
	AchievementService achievementService;
	@RequestMapping()
	public String findByWjnameLike(String wjname, Integer pageNum, Integer pageSize, ModelMap map) {
		System.err.println(wjname + " " + pageNum + " " + pageSize);
		if(wjname==null||wjname.matches(",+"))
		{
			wjname="";
		}
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
		return "index";
	}
	@RequestMapping("/findByWjnameLikeAj")
	public String findByWjnameLikeAj(String wjname, Integer pageNum, Integer pageSize, ModelMap map) {
		System.err.println(wjname + " " + pageNum + " " + pageSize);
		if(wjname==null||wjname.matches(",+"))
		{
			wjname="";
		}
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
	@RequestMapping("/saveAllAchievement")
	public String saveAllAchievement() {
		
		achievementService.saveAllAchievement();
		return "redirect:/findByWjnameLike";
	}

}
