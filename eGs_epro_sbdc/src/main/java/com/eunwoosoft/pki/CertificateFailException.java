package com.eunwoosoft.pki;

import java.rmi.RemoteException;


/**
*기능 : Exception 처리   <br>
*@author    류현수
*@version   1.1.0  2003.05
*/

public class CertificateFailException extends RemoteException {

	/**
	 * AlreadyConfirmException 생성자 주석.
	 */
	public CertificateFailException() {
		super();
	}

	/**
	 * AlreadyConfirmException 생성자 주석.
	 * @param s java.lang.String
	 */
	public CertificateFailException(String s) {
		super(s);
	}

}