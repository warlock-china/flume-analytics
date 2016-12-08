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

public class Appactionlog implements Serializable {

	private static final long serialVersionUID = 1L;

	private String host;
	private String path;
	private String request;
	private String user_id;
	private String app_id;
	private String ip;
	private String mac;
	private String version;
	private String client_type;
	private String latitude;
	private String longitude;
	private String create_ts;
	private String bpreg_brithday;
	private String client_baby_status;
	private String imei;
	//some other fld free ~ ~
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getRequest() {
		return request;
	}
	public void setRequest(String request) {
		this.request = request;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getApp_id() {
		return app_id;
	}
	public void setApp_id(String app_id) {
		this.app_id = app_id;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getMac() {
		return mac;
	}
	public void setMac(String mac) {
		this.mac = mac;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getClient_type() {
		return client_type;
	}
	public void setClient_type(String client_type) {
		this.client_type = client_type;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public String getCreate_ts() {
		return create_ts;
	}
	public void setCreate_ts(String create_ts) {
		this.create_ts = create_ts;
	}
	public String getBpreg_brithday() {
		return bpreg_brithday;
	}
	public void setBpreg_brithday(String bpreg_brithday) {
		this.bpreg_brithday = bpreg_brithday;
	}
	public String getClient_baby_status() {
		return client_baby_status;
	}
	public void setClient_baby_status(String client_baby_status) {
		this.client_baby_status = client_baby_status;
	}
	public String getImei() {
		return imei;
	}
	public void setImei(String imei) {
		this.imei = imei;
	}
}
