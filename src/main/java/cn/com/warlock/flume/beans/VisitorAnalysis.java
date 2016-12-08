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
package cn.com.warlock.flume.beans;

import java.io.Serializable;

public class VisitorAnalysis implements Serializable {

	private static final long serialVersionUID = 1L;

	/** 来源：1-APP，2-WWW，3-WAP */
	private String source;

	/** 内容类型：1-问答，2-帖子，3-专家答 */
	private String ugc_type;

	/** 通过path解析 */
	private String ugc_id;

	/** 用户唯一标识,默认为 '-' */
	private String uniqueid = "-";

	/** 默认为 '1970-01-01' */
	private String birthday = "1970-01-01";

	/** 用户id */
	private String user_id;

	/** 用户API请求时间戳 */
	private String ts;

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getUgc_type() {
		return ugc_type;
	}

	public void setUgc_type(String ugc_type) {
		this.ugc_type = ugc_type;
	}

	public String getUgc_id() {
		return ugc_id;
	}

	public void setUgc_id(String ugc_id) {
		this.ugc_id = ugc_id;
	}

	public String getUniqueid() {
		return uniqueid;
	}

	public void setUniqueid(String uniqueid) {
		this.uniqueid = uniqueid;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getTs() {
		return ts;
	}

	public void setTs(String ts) {
		this.ts = ts;
	}

	@Override
	public String toString() {
		return "VisitorAnalysis [source=" + source + ", ugc_type=" + ugc_type
				+ ", ugc_id=" + ugc_id + ", uniqueid=" + uniqueid
				+ ", birthday=" + birthday + ", user_id=" + user_id + ", ts="
				+ ts + "]";
	}
	
	public VisitorAnalysis() {
	}

	public VisitorAnalysis(String source, String ugc_type, String ugc_id,
			String uniqueid, String birthday, String user_id, String ts) {
		this.source = source;
		this.ugc_type = ugc_type;
		this.ugc_id = ugc_id;
		this.uniqueid = uniqueid;
		this.birthday = birthday;
		this.user_id = user_id;
		this.ts = ts;
	}



	public static final String SEPRATOR = "\t";

	public String parser() {
		return new StringBuilder().append(source).append(SEPRATOR)
				.append(ugc_type).append(SEPRATOR).append(ugc_id)
				.append(SEPRATOR).append(uniqueid).append(SEPRATOR)
				.append(birthday).append(SEPRATOR).append(user_id)
				.append(SEPRATOR).append(ts).toString();
	}
}
