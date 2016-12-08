package cn.com.warlock.flume.pattern;

import cn.com.warlock.flume.beans.Visit;
import cn.com.warlock.flume.beans.VisitorAnalysis;

public class StrategyUnknown implements Strategy{
	private Visit visit;
	
	public Visit getVisit() {
		return visit;
	}
	public void setVisit(Visit visit) {
		this.visit = visit;
	}
	@Override
	public VisitorAnalysis gourmand() {
		return null;
	}


}
