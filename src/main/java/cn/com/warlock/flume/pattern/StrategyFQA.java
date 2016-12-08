package cn.com.warlock.flume.pattern;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import cn.com.warlock.flume.beans.SourceType;
import cn.com.warlock.flume.beans.UgcType;
import cn.com.warlock.flume.beans.Visit;
import cn.com.warlock.flume.beans.VisitorAnalysis;
import cn.com.warlock.flume.beans.YunyuHost;
import cn.com.warlock.flume.tools.RequestUtil;

/**
 * ClassName: StrategyFQA <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON(可选). <br/>
 * date: 2016年12月6日 下午7:30:34 <br/>
 *
 * @author warlock
 * @version 
 * @since JDK 1.7
 */
public class StrategyFQA implements Strategy{
	private Visit visit;
	
	public Visit getVisit() {
		return visit;
	}
	public void setVisit(Visit visit) {
		this.visit = visit;
	}
	
	public StrategyFQA() {
	}
	public StrategyFQA(Visit visit) {
		this.visit = visit;
	}
	@Override
	public VisitorAnalysis gourmand() {
		return new VisitorAnalysis(
				parserSource(visit.getHost()),
				UgcType.FAQ.getInfo(),
				parserUgcId(visit.getPath()),
				visit.getUniqueid(),
				parserBirthday(visit.getReferrer()),
				visit.getUser_id(),
				visit.getTs());
	}
	
	private String parserSource(String host){
		if(YunyuHost.HOST_APP.equals(host)) return SourceType.APP.name();
		else if(YunyuHost.HOST_PC.equals(host)) return SourceType.PC.name();
		else if(YunyuHost.HOST_WAP.equals(host)) return SourceType.WAP.name();
		else return SourceType.UNKNOWN.name();
	}
	
	/**
	 * example：
	 * /app/ask/#!/detail/34083552
	 * */
	private String parserUgcId(String path){
		return path.substring(path.lastIndexOf("/")+1, path.length());
	}
	
	/**
	 * example：
	 * http://webview.babytree.com/app/ask/?babytree-app-version=6.6.3&bpreg_brithday=2017-01-18&babytree-client-type=android&ba...
	 * */
	private String parserBirthday(String referer) {
		Map<String, String[]> value = new HashMap<String, String[]>();
		try {
			RequestUtil.parseParameters(value, referer, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		String[] bpreg_brithday = value.get("bpreg_brithday");
		if(null==bpreg_brithday || bpreg_brithday.length==0) return "1970-01-01";
		else return bpreg_brithday[0];
	}
	
}
