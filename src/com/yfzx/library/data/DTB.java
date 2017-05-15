package com.yfzx.library.data;

/**
 * 数据传输Bean（继承DTO）
 * 
 * @author: bangwei.yang
 * @version Revision: 0.0.1
 * @Date: 2015-7-4
 */
public class DTB<V, K> extends DTO<K, V> {

	private String name;

	private String nameCN;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNameCN() {
		return nameCN;
	}

	public void setNameCN(String nameCN) {
		this.nameCN = nameCN;
	}
}
