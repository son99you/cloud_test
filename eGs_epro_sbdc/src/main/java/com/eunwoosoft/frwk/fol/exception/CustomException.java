package com.eunwoosoft.frwk.fol.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SuppressWarnings("serial")
public class CustomException extends RuntimeException {

//	private static final long serialVersionUID = -5374528257631803790L;
	
	private static final Logger LOG = LoggerFactory.getLogger(CustomException.class);
	
	private String errorCode;
	private String errorMessage;
	
	public CustomException() {
		super();
	}
	
	public CustomException(String s) {
		super(s);
	}
	
	public CustomException(String errorCode, String message) {
		this.errorCode = errorCode;
		this.errorMessage = message;
	}	
	
	/**
	 * 
	 * @param errorCode
	 */
	public CustomException(FwkSysErrorCode sysErrorCode) {
		this.errorCode = sysErrorCode.getErrorCode();
		this.errorMessage = sysErrorCode.getErrorMessage();
	}	
	
	/**
	 * <pre>
	 * 1. 개요 : 
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : getErrorCode
	 * @date : 2014. 12. 17.
	 * @author : 
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2014. 12. 17.						최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @return
	 */
	public String getErrorCode() {
		return this.errorCode;
	}	
	
	/**
	 * <pre>
	 * 1. 개요 : 
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : getMessage
	 * @date : 2014. 12. 17.
	 * @author : 
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2014. 12. 17.						최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @see java.lang.Throwable#getMessage()
	 * @return
	 */
	public String getMessage() {
		return this.errorMessage;
	}
	
}
