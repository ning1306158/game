package com.example.game.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.game.domain.Achievement;
import com.example.game.domain.AchievementRepository;


@Controller
@RequestMapping("/hello")
public class HelloController {

	@Autowired
	AchievementRepository achievementRepository;
	@RequestMapping("/index")
	public String index(ModelMap map)
	{
//		List<Achievement> a=achievementRepository.getByWjId("tb_spdiaochan");
//		Pageable pageable=new PageRequest(1,10);
		Sort s=new Sort(Direction.DESC,"wjname","achievename","level");
		Pageable pageable=PageRequest.of(0,20,s);
		
		System.out.println(pageable.toString());
		List<Achievement> a=achievementRepository.findByWjnameLike("%赵%", pageable);
		System.out.println(a.size());
		map.put("wj", a);
		return "achievement/list";
	}

}
