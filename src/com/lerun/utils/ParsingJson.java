package com.lerun.utils;

import java.util.ArrayList;
import java.util.List;

import com.lerun.model.LeRun;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 *@Author: wschenyongyin
 *@Date: 2016-7-16
 *@explain:
 *@TestState:
 */
public class ParsingJson {

	public ParsingJson() {
	}

	// 解析注册活动的数据
	public static LeRun getPayCostInfo(String key, String jsonString) {
		LeRun lerun = new LeRun();
		try {
			JSONObject jsonObject = JSONObject.fromObject(jsonString);

			JSONObject Object = jsonObject.getJSONObject(key);

			lerun.setLerun_address(Object.getString("lerun_address"));
			lerun.setLerun_begintime(Object.getString("lerun_begintime"));
			lerun.setCharge_id(Integer.parseInt(Object.getString("lerun_charge")));
			lerun.setLerun_city(Object.getString("lerun_city"));
			lerun.setLerun_content(Object.getString("lerun_content"));
			lerun.setLerun_dimage(Object.getString("lerun_dimage"));
			lerun.setLerun_endtime(Object.getString("lerun_endtime"));
			lerun.setLerun_endtime(Object.getString("lerun_endtime"));
			lerun.setLerun_host(Object.getString("lerun_host"));
			lerun.setLerun_id(Integer.parseInt(Object.getString("lerun_id")));
			lerun.setLerun_map(Object.getString("lerun_map"));
			lerun.setLerun_maxuser(Integer.parseInt(Object.getString("lerun_maxuser")));
			lerun.setLerun_poster(Object.getString("lerun_poster"));
			lerun.setLerun_process(Object.getString("lerun_process"));
			lerun.setLerun_routine(Object.getString("lerun_routine"));
			lerun.setLerun_ruler(Object.getString("lerun_ruler"));
			lerun.setLerun_sponsor(Object.getString("lerun_sponsor"));
			lerun.setLerun_state(Integer.parseInt(Object.getString("lerun_state")));
			lerun.setLerun_time(Object.getString("lerun_time"));
			lerun.setLerun_title(Object.getString("lerun_title"));
			lerun.setLerun_type(Object.getString("lerun_type"));
			lerun.setLerun_video(Object.getString("lerun_video"));
			lerun.setLerun_agent(Object.getString("lerun_agent"));
			lerun.setLerun_province(Object.getString("lerun_province"));
			
			return lerun;

		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
	
	
	

}
