package cn.com.warlock.flume.pattern;

import cn.com.warlock.flume.beans.VisitorAnalysis;

/**
 * ClassName: GluttonContext <br/>
 * Function:  <br/>
 * Reason:  <br/>
 * date: 2016年12月6日 下午5:46:48 <br/>
 *
 * @author warlock
 * @version 
 * @since JDK 1.7
 */
public class GluttonContext {
	private Strategy strategy;

	public Strategy getStrategy() {
		return strategy;
	}

	public void setStrategy(Strategy strategy) {
		this.strategy = strategy;
	}

	public GluttonContext() {
	}
	
	public GluttonContext(Strategy strategy) {
		this.strategy = strategy;
	}

	public VisitorAnalysis gourmand() {
		return strategy.gourmand();
	}

}
