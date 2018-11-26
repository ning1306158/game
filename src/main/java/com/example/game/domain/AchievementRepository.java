package com.example.game.domain;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AchievementRepository extends JpaRepository<Achievement, String>{
	List<Achievement> getByWjId(String wjId);
}
