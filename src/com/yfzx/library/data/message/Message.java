package com.yfzx.library.data.message;

import com.yfzx.library.data.DTB;

/**
 * 消息实体Bean
 * 
 * @author: bangwei.yang
 * @version Revision: 0.0.1
 * @Date: 2015-7-4
 */
public class Message extends DTB {

	private static final long serialVersionUID = 7491152915368949244L;

	/**
	 * 消息ID
	 */
	private int id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
