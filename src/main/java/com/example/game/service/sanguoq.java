package com.example.game.service;
import java.io.IOException;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.game.domain.Achievement;

public class sanguoq {
	String uri = "http://www.sanguoq.com/achievement/";

	public void getWj() {
		Document document = null;
		try {
			document = Jsoup.connect(uri).get();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Elements a = document.select("div.wj");
		for (Element element : a) {
			handleSkill(element.attr("data-id"));
			Elements b = element.select("span");
		}
	}

	/**
	 * 处理每个武将的技能和成就
	 * 
	 * @param attr
	 * @author MNN 2018年11月22日
	 */
	private void handleSkill(String attr) {
		String skillUri = uri + "general/"+attr;
		CloseableHttpClient httpClient = null;
		CloseableHttpResponse httpResponse = null;
		try {
			Achievement a=new Achievement();
			httpClient = HttpClients.createDefault();
			HttpGet httpPost = new HttpGet(skillUri);
			System.out.println(skillUri);
			httpResponse = httpClient.execute(httpPost);
			String response = EntityUtils.toString(httpResponse.getEntity());
			JSONObject jObject = JSON.parseObject(response);
			System.out.println(jObject.get("identify"));
			a.setWjId(jObject.get("identify").toString());
			System.out.println(jObject.get("package"));
			a.set_package(jObject.get("package").toString());
			System.out.println(jObject.get("name"));
			a.setWjname(jObject.get("name").toString());
			System.out.println(jObject.get("skill"));
			System.out.println(jObject.get("nation"));
			a.setNation(jObject.get("nation").toString());
			JSONArray achievement=(JSONArray) jObject.get("achievement");
			for (Object object : achievement) {
				JSONObject jsonObject = (JSONObject) object;
				System.out.println(jsonObject.get("name"));
				a.setName(jsonObject.get("name").toString());
				for (Object achieve : (JSONArray) jsonObject.get("levels")) {
					System.out.println(((JSONObject) achieve).get("level_desc"));
					a.setAchievement(((JSONObject) achieve).get("level_desc").toString());
					a.setLevel(((JSONObject) achieve).get("level").toString());
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

	@Test
	public void TT() {
		this.getWj();
	}
}
