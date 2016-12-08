package cn.com.warlock.flume.tools.test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import cn.com.warlock.flume.beans.Visit;
import cn.com.warlock.flume.interceptor.YunyuVisitInterceptor;

public class Test2 {
	public static void main(String[] args) throws IOException {
		String json = new String(
				Files.readAllBytes(Paths
						.get("D:\\workspace_storm\\flume-analytics\\src\\test\\java\\cn\\com\\warlock\\flume\\tools\\test\\visit.json")));
		Gson gson;
		GsonBuilder builder = new GsonBuilder();
		builder.serializeNulls();
		gson = builder.create();
		Visit v = gson.fromJson(json, Visit.class);
		System.out.println(v);
		YunyuVisitInterceptor visti = new YunyuVisitInterceptor();
		visti.parser(json);
	}
}
