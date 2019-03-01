package com.example.game.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;

import com.example.game.domain.Achievement;
import com.example.game.domain.AchievementRepository;
import com.example.game.service.AchievementService;

@Service
public class AchievementServiceImpl implements AchievementService {

	@Autowired
	private AchievementRepository achievementRepository;

	@Override
	public void save(Achievement achievement) {
		achievementRepository.save(achievement);
	}

	@Override
	public List<Achievement> findByWjId(String wjId) {
		return achievementRepository.getByWjId(wjId);

	}

	@Override
	public List<Achievement> findByWjnameLike(String wjname, int pageNum, int pageSize) {

		List<Order> orders = new ArrayList<>();
		orders.add(new Order(Direction.DESC, "wjname"));
		orders.add(new Order(Direction.DESC, "achievename"));
		orders.add(new Order(Direction.ASC, "level"));
		Pageable pageable = PageRequest.of(pageNum, pageSize, Sort.by(orders));

		return achievementRepository.findByWjnameLike(wjname, pageable);
	}

	@Override
	public int saveAllAchievement() {
		// TODO Auto-generated method stub
		return 0;
	}

}
