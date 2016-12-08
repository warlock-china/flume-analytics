package cn.com.warlock.flume.pattern;

import cn.com.warlock.flume.beans.Visit;
import cn.com.warlock.flume.beans.VisitorAnalysis;

public interface Strategy {
	public VisitorAnalysis gourmand();
	public Visit getVisit();
	public void setVisit(Visit visit);
}
