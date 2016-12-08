package cn.com.warlock.flume.pattern;

import cn.com.warlock.flume.beans.SourceType;
import cn.com.warlock.flume.beans.UgcType;
import cn.com.warlock.flume.beans.Visit;
import cn.com.warlock.flume.beans.VisitorAnalysis;
import cn.com.warlock.flume.beans.YunyuHost;

public class StrategyReply implements Strategy{
	private Visit visit;
	
	public Visit getVisit() {
		return visit;
	}
	public void setVisit(Visit visit) {
		this.visit = visit;
	}
	
	public StrategyReply() {
	}
	public StrategyReply(Visit visit) {
		this.visit = visit;
	}
	@Override
	public VisitorAnalysis gourmand() {
		return new VisitorAnalysis(
				parserSource(visit.getHost()),
				UgcType.FAQ.getInfo(),
				parserUgcId(visit.getPath()),
				visit.getUniqueid(),
				"1970-01-01",//default
				visit.getUser_id(),
				visit.getTs());
	}
	
	private String parserUgcId(String path){
		String tmp = path.substring(path.indexOf("_")+1,path.length());
		if(tmp.indexOf("_")==-1) 
			tmp = tmp.substring(0,tmp.indexOf("."));
		else
			tmp = tmp.substring(0,tmp.indexOf("_"));
		return tmp;
	}
	
	private String parserSource(String host){
		if(YunyuHost.HOST_PC.equals(host)) return SourceType.PC.name();
		else if(YunyuHost.HOST_WAP.equals(host)) return SourceType.WAP.name();
		else return SourceType.UNKNOWN.name();
	}	
}
