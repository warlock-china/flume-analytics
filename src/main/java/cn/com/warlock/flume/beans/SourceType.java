package cn.com.warlock.flume.beans;

public enum SourceType {
	APP,PC,WAP,UNKNOWN;
	public static void main(String[] args) {
		System.out.println(SourceType.APP.name());
	}
}
