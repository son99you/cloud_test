package com.eunwoosoft.pki;

public class EwPkiVo {
	
 	//인증서 값
	private String signCertStr;
	private String signKeyStr;
	private String kmCertStr;
	private String kmKeyStr;
	
	//인증서 비밀번호 셋팅
	private String certPassStr;
	
	//서버인증서 경로 셋팅
	private String signCertPath;
	private String signKeyPath;
	private String kmCertPath;
	private String kmKeyPath;
	
	//인증서 정보 조회 방법 구분
	//DB:DB에서 값 조회, SV:서버 properties에서 경로 조회
	private String certGetGb;
	
	//인증할 사업자번호 혹은 주민등록 번호
	private String certIdn;	
	

	public String getCertIdn() {
		return certIdn;
	}

	public void setCertIdn(String certIdn) {
		this.certIdn = certIdn;
	}

	public String getSignCertStr() {
		return signCertStr;
	}

	public void setSignCertStr(String signCertStr) {
		this.signCertStr = signCertStr;
	}

	public String getSignKeyStr() {
		return signKeyStr;
	}

	public void setSignKeyStr(String signKeyStr) {
		this.signKeyStr = signKeyStr;
	}

	public String getKmCertStr() {
		return kmCertStr;
	}

	public void setKmCertStr(String kmCertStr) {
		this.kmCertStr = kmCertStr;
	}

	public String getKmKeyStr() {
		return kmKeyStr;
	}

	public void setKmKeyStr(String kmKeyStr) {
		this.kmKeyStr = kmKeyStr;
	}

	public String getCertPassStr() {
		return certPassStr;
	}

	public void setCertPassStr(String certPassStr) {
		this.certPassStr = certPassStr;
	}

	public String getSignCertPath() {
		return signCertPath;
	}

	public void setSignCertPath(String signCertPath) {
		this.signCertPath = signCertPath;
	}

	public String getSignKeyPath() {
		return signKeyPath;
	}

	public void setSignKeyPath(String signKeyPath) {
		this.signKeyPath = signKeyPath;
	}

	public String getKmCertPath() {
		return kmCertPath;
	}

	public void setKmCertPath(String kmCertPath) {
		this.kmCertPath = kmCertPath;
	}

	public String getKmKeyPath() {
		return kmKeyPath;
	}

	public void setKmKeyPath(String kmKeyPath) {
		this.kmKeyPath = kmKeyPath;
	}

	public String getCertGetGb() {
		return certGetGb;
	}

	public void setCertGetGb(String certGetGb) {
		this.certGetGb = certGetGb;
	}  
	
	
}
