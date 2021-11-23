package com.eunwoosoft.pki;

import java.io.IOException;

import tradesign.crypto.provider.JeTS;
import tradesign.pki.util.FileUtil;
import tradesign.pki.util.JetsUtil;

public class EwPkiUtil {
	
	public static void initialize(EwPkiVo vo) throws IOException {
		// TODO Auto-generated method stub

		if (vo.getCertGetGb().equals("SV")) {
			
			byte[] signCert = FileUtil.getBytesFromFile(vo.getSignCertPath());
			byte[] signPriv = FileUtil.getBytesFromFile(vo.getSignKeyPath());
			byte[] kmCert = FileUtil.getBytesFromFile(vo.getKmCertPath());
			byte[] kmPriv = FileUtil.getBytesFromFile(vo.getKmKeyPath());

			JeTS.addServerSignCert(signCert);
			JeTS.addServerSignPriKey(signPriv);
			JeTS.addServerSignKeyPassword(vo.getCertPassStr());

			JeTS.addServerkmCert(kmCert);
			JeTS.addServerkmPriKey(kmPriv);
			JeTS.addServerkmKeyPassword(vo.getCertPassStr());
			
		}else{
			byte[] signCert = JetsUtil.base64ToBytes(vo.getSignCertStr());
			byte[] signPriv = JetsUtil.base64ToBytes(vo.getSignKeyStr());
			byte[] kmCert = JetsUtil.base64ToBytes(vo.getKmCertStr());
			byte[] kmPriv =JetsUtil.base64ToBytes(vo.getKmKeyStr());

			JeTS.addServerSignCert(signCert);
			JeTS.addServerSignPriKey(signPriv);
			JeTS.addServerSignKeyPassword(vo.getCertPassStr());

			JeTS.addServerkmCert(kmCert);
			JeTS.addServerkmPriKey(kmPriv);
			JeTS.addServerkmKeyPassword(vo.getCertPassStr());
		}
	}
}
