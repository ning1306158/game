package com.example.game.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
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

	String uri = "http://www.sanguoq.com/achievement/";

	@Override
	public int saveAllAchievement() {
		Document document = null;
		try {
			document = Jsoup.connect(uri).get();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Elements a = document.select("div.wj");
		for (Element element : a) {
			handleSkill(element.attr("data-id"));
		}

		return 0;
	}

	private void handleSkill(String attr) {
		String skillUri = uri + "general/" + attr;
		CloseableHttpClient httpClient = null;
		CloseableHttpResponse httpResponse = null;
		try {
			Achievement a = new Achievement();
			httpClient = HttpClients.createDefault();
			HttpGet httpPost = new HttpGet(skillUri);
			System.out.println(skillUri);
			httpResponse = httpClient.execute(httpPost);
			String response = EntityUtils.toString(httpResponse.getEntity());
			JSONObject jObject = JSON.parseObject(response);
			a.setWjId(jObject.get("identify").toString());
			a.set_package(jObject.get("package").toString());
			a.setWjname(jObject.get("name").toString());
			a.setNation(jObject.get("nation").toString());
			JSONArray achievement = (JSONArray) jObject.get("achievement");
			for (Object object : achievement) {
				JSONObject jsonObject = (JSONObject) object;
				Achievement a1=a.clone();
				a1.setName(jsonObject.get("name").toString());
				for (Object achieve : (JSONArray) jsonObject.get("levels")) {
					Achievement a2=a1.clone();
					a2.setAchievement(((JSONObject) achieve).get("level_desc").toString());
					a2.setLevel(((JSONObject) achieve).get("level").toString());
					if (achievementRepository.findByAchievement(a2.getAchievement()).size() == 0) {
						achievementRepository.save(a2);
					}
				}
			}
			if (achievement.size() == 0) {
				if(achievementRepository.getByWjId(a.getWjId()).size()==0)
				{
					achievementRepository.save(a);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {

				if (httpClient != null) {
					httpClient.close();
				}
				if (httpResponse != null) {
					httpResponse.close();
				}
			} catch (Exception e) {
			}
		}

	}

}
