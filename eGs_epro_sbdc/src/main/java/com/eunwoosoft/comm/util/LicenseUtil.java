package com.eunwoosoft.comm.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.eunwoosoft.comm.pki.*;
import com.eunwoosoft.frwk.fol.dto.FwkDataEntity;
import com.eunwoosoft.frwk.fol.util.FwkDateUtil;

/**
 * LicenseUtil
 * @author Yeonwoo Son
 *
 */
public class LicenseUtil {
	private static final Logger LOG = LoggerFactory.getLogger(LicenseUtil.class);
	
//	private Session session = null;
//    private Channel channel = null;
//    private ChannelSftp channelSftp = null;

	/**
	 * 
	 * 서버와 연결에 필요한 값들을 가져와 초기화 시킴
	 * 
	 * @param host : 서버주소 
	 * @param userName : 접속 아이디
	 * @param password : 비밀번호
	 * @param port : 포트번호
	 */
	
	public static int init() throws Exception {
        
		return 1;
		
    }


}
