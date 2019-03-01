package com.example.game.domain;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface SkillRepository extends JpaRepository<Skill, String> {

	@Query(value="SELECT a.* FROM SKILL A WHERE A.HERO_ID=?1",nativeQuery = true)
	@Modifying
	List<Skill> findByHeroId(String hero_id);

}
