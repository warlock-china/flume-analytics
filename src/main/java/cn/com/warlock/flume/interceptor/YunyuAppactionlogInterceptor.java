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

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.flume.Context;
import org.apache.flume.Event;
import org.apache.flume.interceptor.Interceptor;
import org.apache.log4j.Logger;

import cn.com.warlock.flume.beans.Appactionlog;
import cn.com.warlock.flume.beans.SourceType;
import cn.com.warlock.flume.beans.UgcType;
import cn.com.warlock.flume.beans.VisitorAnalysis;
import cn.com.warlock.flume.beans.YunyuHost;
import cn.com.warlock.flume.tools.RequestUtil;

import com.google.common.base.Charsets;
import com.google.common.collect.Lists;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * ClassName: YunyuVisitInterceptor <br/>
 * Function: builder kafka stream. <br/>
 * Reason: appcontentclicklog real-time analysis . <br/>
 * date: 2016年12月6日 上午11:50:53 <br/>
 *
 * @author warlock
 * @version
 * @since JDK 1.7
 */
public class YunyuAppactionlogInterceptor implements Interceptor {
	private static final Logger LOG = Logger
			.getLogger(YunyuAppactionlogInterceptor.class);
	/** default.appactionlog 的源可解析出 app 的帖子 */
	private static String REGEX_REPLY_APP_EQ = "/api/mobile_community/get_topic_data";
	/** default.appactionlog 的源可解析出 app 的专家 */
	private static String REGEX_EXPERTS_APP_EQ = "/api/dayima_qa/question_detail";

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

	private String parser(String body) {
		try {
			if (null == body || "".equals(body)) return NULL_BODY;
			Appactionlog appactionlog = gson.fromJson(body, Appactionlog.class);
			if (YunyuHost.HOST_API.equals(appactionlog.getHost()) && REGEX_REPLY_APP_EQ.equals(appactionlog.getPath())) {
				Map<String, String[]> value = parserReq(appactionlog.getRequest());
				return new VisitorAnalysis(SourceType.APP.name(),
						UgcType.REPLY.getInfo(), safeValue(value.get("topic_id"),"0"),
						appactionlog.getMac(), appactionlog.getBpreg_brithday(),
						appactionlog.getUser_id(), appactionlog.getCreate_ts())
						.parser();
			} else if (YunyuHost.HOST_API.equals(appactionlog.getHost()) && REGEX_EXPERTS_APP_EQ.equals(appactionlog.getPath())) {
				Map<String, String[]> value = parserReq(appactionlog.getRequest());
				return new VisitorAnalysis(SourceType.APP.name(),
						UgcType.EXPERTS.getInfo(), safeValue(value.get("id"),"0"),
						appactionlog.getMac(), safeValue(value.get("bpreg_brithday"),"1970-01-01"),
						appactionlog.getUser_id(), appactionlog.getCreate_ts())
						.parser();
			} else return NULL_BODY;
		} catch (Exception e) {
			LOG.error(e.getMessage());
			return NULL_BODY;
		}
	}

	private Map<String, String[]> parserReq(String request) {
		Map<String, String[]> value = new HashMap<String, String[]>();
		try {
			RequestUtil.parseParameters(value, request, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return value;
	}
	
	private String safeValue(String[] value,String defaultval){
		if(null==value||value.length==0) return defaultval;
		else return value[0];
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
			return new YunyuAppactionlogInterceptor();
		}

	}

}
