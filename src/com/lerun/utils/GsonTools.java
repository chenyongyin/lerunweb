package com.lerun.utils;

import com.google.gson.Gson;

import net.sf.json.JSONObject;

public class GsonTools {

	public GsonTools() {
		// TODO Auto-generated constructor stub
	}

	
	public static String createJsonString(Object value) {
		Gson gson = new Gson();
		String str = gson.toJson(value);
		return str;
	}

}
