package com.example.game.domain;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AchievementRepository extends JpaRepository<Achievement, String>{
	List<Achievement> getByWjId(String wjId);
	List<Achievement> findByWjnameLike(String wjname,Pageable pageable);
	List<Achievement> findByAchievename(String achievename);
	List<Achievement> findByAchievement(String achievement);
}
