/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package cn.com.warlock.flume.interceptor;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.flume.Context;
import org.apache.flume.Event;
import org.apache.flume.interceptor.Interceptor;
import org.apache.log4j.Logger;

import cn.com.warlock.flume.beans.Visit;
import cn.com.warlock.flume.pattern.GluttonContext;
import cn.com.warlock.flume.pattern.StrategyFQA;
import cn.com.warlock.flume.pattern.StrategyReply;

import com.google.common.base.Charsets;
import com.google.common.collect.Lists;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * ClassName: YunyuVisitInterceptor <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON(可选). <br/>
 * date: 2016年12月6日 下午8:40:25 <br/>
 *
 * @author warlock
 * @version 
 * @since JDK 1.7
 */
public class YunyuVisitInterceptor implements Interceptor {
	private static final Logger LOG = Logger.getLogger(YunyuVisitInterceptor.class);
	/**default.visit 的源可解析出 app,pc,wap 的问答*/
	private static String REGEX_FAQ_APP = "/app/ask/#!/detail/\\S{0,}";
	private static String REGEX_FAQ_PC_AND_WAP = "/ask/detail/\\S{0,}";
	
	/**default.visit 的源可解析出 pc,wap 的帖子*/
	private static String REGEX_REPLY_PC_AND_WAP = "/community/.*/topic_\\S{0,}";
	
	private static GluttonContext gluttonContext = new GluttonContext();
	
	private static final String NULL_BODY = "";
	
	private static Gson gson;
	
	static {
		GsonBuilder builder = new GsonBuilder();
		builder.serializeNulls();
		gson = builder.create();
	}

	@Override
	public Event intercept(Event event) {
		String body = new String(event.getBody(), Charsets.UTF_8);
		body = parser(body);
		if(StringUtils.isBlank(body)) return null;
		else event.setBody(body.getBytes());
		return event;
	}

	@Override
	public List<Event> intercept(List<Event> events) {
		List<Event> intercepted = Lists.newArrayListWithCapacity(events.size());
		for (Event event : events) {
			Event interceptedEvent = intercept(event);
			if (interceptedEvent != null) {
				intercepted.add(interceptedEvent);
			}
		}
		return intercepted;
	}

	public String parser(String body) {
		try {
			if(null==body||"".equals(body)) return NULL_BODY;
			Visit visit = gson.fromJson(body, Visit.class);
			String path = visit.getPath(); 
			if(null==path) return NULL_BODY;
			else{
				if(path.matches(REGEX_FAQ_APP) || path.matches(REGEX_FAQ_PC_AND_WAP))
					gluttonContext.setStrategy(new StrategyFQA(visit));
				else if(path.matches(REGEX_REPLY_PC_AND_WAP))
					gluttonContext.setStrategy(new StrategyReply(visit));
				else return NULL_BODY;
			}
			return gluttonContext.gourmand().parser();
		} catch (Exception e) {
			LOG.error(e.getMessage());
			return NULL_BODY;
		}

	}
	
	@Override
	public void initialize() {
	}

	@Override
	public void close() {
	}

	public static class Builder implements Interceptor.Builder {

		@Override
		public void configure(Context context) {
		}

		@Override
		public Interceptor build() {
			return new YunyuVisitInterceptor();
		}

	}

}
