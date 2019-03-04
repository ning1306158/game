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
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.game.domain.Hero;
import com.example.game.domain.HeroInfo;
import com.example.game.domain.HeroRepository;
import com.example.game.domain.Skill;
import com.example.game.domain.SkillRepository;
import com.example.game.service.HeroService;
@Service
public class HeroServiceImpl implements HeroService{
	@Autowired
	private HeroRepository heroRepository;
	@Autowired
	private SkillRepository skillRepository;

	String uri = "http://www.sanguoq.com/achievement/";

	public int saveAllHero() {
		Document document = null;
		try {
			document = Jsoup.connect(uri).get();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Elements a = document.select("div.wj");
		for (Element element : a) {
			handleSkill(element.attr("data-id"));
//			break;
		}

		return 0;
	}

	private void handleSkill(String attr) {
		String skillUri = uri + "general/" + attr;
//		String skillUri = uri + "general/tl_skzhugejin";
		CloseableHttpClient httpClient = null;
		CloseableHttpResponse httpResponse = null;
		try {
			httpClient = HttpClients.createDefault();
			HttpGet httpPost = new HttpGet(skillUri);
			System.out.println(skillUri);
			httpResponse = httpClient.execute(httpPost);
			String response = EntityUtils.toString(httpResponse.getEntity());
			JSONObject jObject = JSON.parseObject(response);
			Hero hero=new Hero();
			hero.setName(jObject.getString("name"));
			hero.setNation(jObject.getString("nation"));
			hero.setPainting(jObject.getString("painting"));
			hero.set_package(jObject.getString("package"));
			hero.setIdentify(jObject.getString("identify"));
			heroRepository.save(hero);
			String hero_id=hero.getIds();
			String[] skills=jObject.getString("skill").trim().split("<br/>|<br>");
			System.out.println(skills);
			System.out.println(skills.length);
			for (String skill: skills) {
				System.out.println(skill);
				skill=skill.trim();
				Skill s=new Skill();
				s.setHero_id(hero_id);
				String name=skill.substring(3,5).trim();
				s.setSkill_name(name);
				String content=skill.substring(9);
				s.setSkill_desc(content);
				skillRepository.save(s);
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
	@Test
	public void tt()
	{
		this.saveAllHero();
	}

	@Override
	public void save(Hero hero) {
		
	}

	@Override
	public List<Hero> findAll() {
		return heroRepository.findAll();
	}

	@Override
	public List<HeroInfo> findAllSkill() {
		List<Hero> heroList=heroRepository.findAll();
		List<HeroInfo> heroInfos=new ArrayList<>(250);
		for (Hero hero: heroList) {
			HeroInfo heroInfo=new HeroInfo();
			heroInfo.setHero(hero);
			heroInfo.setListSkill(skillRepository.findByHeroId(hero.getIds()));
			heroInfos.add(heroInfo);
		}
		return heroInfos;
	}

	@Override
	public Hero findById(String hero_id) {
		return heroRepository.getOne(hero_id);
	}
}
