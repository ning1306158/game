package com.example.game.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.example.game.domain.Achievement;

public interface AchievementService {
	public void save(Achievement achievement);
	public List<Achievement> findByWjId(String wjId);
	public List<Achievement> findByWjnameLike(String wjname,int pageNum,int pageSize);
	public int saveAllAchievement();
}
