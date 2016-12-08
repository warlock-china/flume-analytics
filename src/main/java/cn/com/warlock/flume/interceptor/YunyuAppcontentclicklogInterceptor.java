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

import cn.com.warlock.flume.beans.Appcontentclicklog;
import cn.com.warlock.flume.beans.SourceType;
import cn.com.warlock.flume.beans.UgcType;
import cn.com.warlock.flume.beans.VisitorAnalysis;

import com.google.common.base.Charsets;
import com.google.common.collect.Lists;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * ClassName: YunyuAppcontentclicklogInterceptor <br/>
 * Function: builder kafka stream. <br/>
 * Reason: appcontentclicklog real-time analysis . <br/>
 * date: 2016年12月6日 上午11:50:53 <br/>
 *
 * @author warlock
 * @version 
 * @since JDK 1.7
 */
public class YunyuAppcontentclicklogInterceptor implements Interceptor {
	private static final Logger LOG = Logger.getLogger(YunyuAppcontentclicklogInterceptor.class);
	private static Gson gson;
	private static final String NULL_BODY = "";

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

	private String parser(String body) {
		if(null==body||"".equals(body)) return NULL_BODY;
		try {
			Appcontentclicklog appcontentclicklog = gson.fromJson(body, Appcontentclicklog.class);
			/** bad design 由XX同事口述一些特定规则，不明觉历，先简单匹配，逻辑复杂可改成状态模式 */
			if("1".equals(appcontentclicklog.getContent_type()) || "2".equals(appcontentclicklog.getContent_type()))
				return new VisitorAnalysis(
						SourceType.APP.name(),
						UgcType.KNOWLEDGE.getInfo(),
						appcontentclicklog.getContent_id(),
						StringUtils.isBlank(appcontentclicklog.getUnique_id()) ? "-" : appcontentclicklog.getUnique_id(),
						StringUtils.isBlank(appcontentclicklog.getBpreg_birthday()) ? "1970-01-01" : appcontentclicklog.getBpreg_birthday(), 
						appcontentclicklog.getUser_id(),
						appcontentclicklog.getCreate_ts()).parser();
			else return NULL_BODY;
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
			return new YunyuAppcontentclicklogInterceptor();
		}

	}

}
