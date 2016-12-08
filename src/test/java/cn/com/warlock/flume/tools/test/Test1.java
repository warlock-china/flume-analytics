package cn.com.warlock.flume.tools.test;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

import cn.com.warlock.flume.tools.RequestUtil;

public class Test1 {
	public static void main2(String[] args) throws UnsupportedEncodingException {
		String str = "/service/T0001?json=%7B%22body%22%3A%7B%22ja%22%3A%5B%7B%22userId%22%3A%22%22%2C%22trackerCode%22%3A%22yy_homepage_dsp%22%2C%22trackTime%22%3A%2220161030211710%22%2C%22sourceType%22%3A%22btm-ios%22%2C%22href%22%3A%22positoin%3D3-pg%3D1-bannerid%3D9999998%22%2C%22url%22%3A%22%22%2C%22tcode%22%3A%22%22%2C%22version%22%3A%226.7.4%22%2C%22yversion%22%3A%226.7.4%22%2C%22longitude%22%3A%22%22%2C%22latitude%22%3Anull%2C%22cookieId%22%3A%22%22%2C%22uuid%22%3A%229693b5a76d400e0700d9ec579864daa0%22%2C%22referrer%22%3A%22%22%2C%22logEvent%22%3A2%2C%22device%22%3A%229693b5a76d400e0700d9ec579864daa0%22%2C%22sessionId%22%3A%22%22%2C%22orderId%22%3A%22%22%2C%22cityname%22%3A%22%E5%B9%BF%E4%B8%9C%E7%9C%81%E5%B9%BF%E5%B7%9E%E5%B8%82%22%7D%5D%7D%2C%22head%22%3A%7B%22sign%22%3A%22%22%2C%22appid%22%3A%22IOS-0905-0001%22%2C%22verision%22%3A%222.0%22%7D%7D";
		System.out.println(URLDecoder.decode(str, "UTF-8"));
		
		
	}
	
	public static void main(String[] args) throws UnsupportedEncodingException {
//		System.out.println(new StrategyFQA().parserUgcId("/app/ask/#!/detail/34083552"));
//		System.out.println(new StrategyFQA().parserUgcId("/app/ask/#!/detail/34083552/"));
//		System.out.println(new StrategyFQA().parserUgcId("/app/ask/#!/detail/\\34083552"));
		
		Map<String, String> values = new HashMap<String, String>();  
		String url = "http://webview.babytree.com/app/ask/?babytree-app-version=6.6.3&bpreg_brithday=2017-01-18&babytree-client-type=android";
		RequestUtil.parseParameters(values, url, "UTF-8");
		
		Map<String, String[]> valueTomcat = new HashMap<String, String[]>();
		RequestUtil.parseParameters(valueTomcat, url, "UTF-8");
		for (Map.Entry<String, String[]> entry : valueTomcat.entrySet()) {
			System.out.println(entry.getKey() + ":" + entry.getValue()[0] + ";");
		}
		System.out.println(valueTomcat.get("bpreg_brithday")[0]);
	}
}
