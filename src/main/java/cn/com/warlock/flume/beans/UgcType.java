package cn.com.warlock.flume.beans;

public enum UgcType {
	/**问答 、帖子、 专家、知识*/
	FAQ("1"), REPLY("2"), EXPERTS("3"), KNOWLEDGE("4");
	private final String info;

	private UgcType(String info) {
		this.info = info;
	}

	public String getInfo() {
		return info;
	}
	
}
