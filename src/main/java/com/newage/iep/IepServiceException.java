package com.newage.iep;

/**
 * 服务异常基类，抛出此异常表示 业务层出现问题
 * @author
 */
public class IepServiceException extends IepException {

	private static final long serialVersionUID = -5257385546556278151L;
	/**
	 * Construct emtpy exception object.
	 */	
	public IepServiceException(){
		super();
	}
	/**
	 * Construct ErsException with message string.
	 * @param s Error message string. 
	 */		
	public IepServiceException(String s){
		super(s);
	}
	/**
	 * Construct ErsException, wrapping existing throwable
	 * @param s Error message string.
	 * @param t Existing connection to wrap.
	 */	
	public IepServiceException(String s, Throwable t){
		super(s,t);
	}	
	/**
	 * Construct ErsException, wrapping existing throwable.
	 * @param t Existing connection to wrap.
	 */	
	public IepServiceException(Throwable t){
		super(t);
	}	
}
