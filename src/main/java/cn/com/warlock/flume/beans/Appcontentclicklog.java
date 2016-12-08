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

public class Appcontentclicklog implements Serializable {

	private static final long serialVersionUID = 1L;

	private String unique_id;
	private String user_id;
	private String bpreg_birthday;
	private String client_baby_status;
	private String app_id;
	private String client_type;
	private String version;
	private String device_model;
	private String content_type;
	private String content_id;
	private String group_id;
	private String timestamp;
	private String create_ts;
	private String serialize;
	private String mac;

	public String getUnique_id() {
		return unique_id;
	}

	public void setUnique_id(String unique_id) {
		this.unique_id = unique_id;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getBpreg_birthday() {
		return bpreg_birthday;
	}

	public void setBpreg_birthday(String bpreg_birthday) {
		this.bpreg_birthday = bpreg_birthday;
	}

	public String getClient_baby_status() {
		return client_baby_status;
	}

	public void setClient_baby_status(String client_baby_status) {
		this.client_baby_status = client_baby_status;
	}

	public String getApp_id() {
		return app_id;
	}

	public void setApp_id(String app_id) {
		this.app_id = app_id;
	}

	public String getClient_type() {
		return client_type;
	}

	public void setClient_type(String client_type) {
		this.client_type = client_type;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getDevice_model() {
		return device_model;
	}

	public void setDevice_model(String device_model) {
		this.device_model = device_model;
	}

	public String getContent_type() {
		return content_type;
	}

	public void setContent_type(String content_type) {
		this.content_type = content_type;
	}

	public String getContent_id() {
		return content_id;
	}

	public void setContent_id(String content_id) {
		this.content_id = content_id;
	}

	public String getGroup_id() {
		return group_id;
	}

	public void setGroup_id(String group_id) {
		this.group_id = group_id;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public String getCreate_ts() {
		return create_ts;
	}

	public void setCreate_ts(String create_ts) {
		this.create_ts = create_ts;
	}

	public String getSerialize() {
		return serialize;
	}

	public void setSerialize(String serialize) {
		this.serialize = serialize;
	}

	public String getMac() {
		return mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}

	@Override
	public String toString() {
		return "ClickLog [unique_id=" + unique_id + ", user_id=" + user_id
				+ ", bpreg_birthday=" + bpreg_birthday
				+ ", client_baby_status=" + client_baby_status + ", app_id="
				+ app_id + ", client_type=" + client_type + ", version="
				+ version + ", device_model=" + device_model
				+ ", content_type=" + content_type + ", content_id="
				+ content_id + ", group_id=" + group_id + ", timestamp="
				+ timestamp + ", create_ts=" + create_ts + ", serialize="
				+ serialize + ", mac=" + mac + "]";
	}

}
