package com.teatime.memo.domain;

import java.util.Date;

/**
 * The Memo Entry bean.
 * 
 * @author Terry Ouyang
 *
 */
public class MemoEntry {

	/**
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * @param date the date to set
	 */
	public void setDate(Date date) {
		this.date = date;
	}

	/**
	 * @return the content
	 */
	public String getContent() {
		return Content;
	}

	/**
	 * @param content the content to set
	 */
	public void setContent(String content) {
		Content = content;
	}

	private Date date;
	
	private String Content;
}
