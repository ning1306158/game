package com.example.game.domain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AchievementRepository extends JpaRepository<Achievement, String>{
	Achievement getByWjnameId(String wjname_id);
}
