/**
 * 
 */
package com.teatime.memo.service.exception;


/**
 * @author Terry Ouyang
 *
 */
public class ResourceAccessException extends Exception {
	private static final long serialVersionUID = -900653557922338444L;

	/**
	 * @param e
	 */
	public ResourceAccessException(Throwable e) {
		// TODO Auto-generated constructor stub
		super(e);
	}
	
	/**
	 * @param e
	 */
	public ResourceAccessException(String msg, Throwable e) {
		// TODO Auto-generated constructor stub
		super(msg, e);
	}
	
	/**
	 * @param e
	 */
	public ResourceAccessException(String msg) {
		// TODO Auto-generated constructor stub
		super(msg);
	}

}
