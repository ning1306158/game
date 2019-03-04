package com.example.game.domain;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SkillRepository extends JpaRepository<Skill, String> {

	@Query(value = "SELECT a.* FROM SKILL A WHERE A.HERO_ID=?1", nativeQuery = true)
	@Modifying
	List<Skill> findByHeroId(String hero_id);

	@Query(value="Select new com.example.game.domain.SkillInfo(s,a) from Hero s left join Skill a on  s.ids=a.hero_id where (s.name like ?1 or a.skill_name like ?1 or a.skill_desc like ?1)",
	countQuery="select count(a.ids) from Hero s left join Skill a on  s.ids=a.hero_id where (s.name like ?1 or a.skill_name like ?1 or a.skill_desc like ?1)")
	Page<SkillInfo> findAllSkill(String key,Pageable pageable);
}