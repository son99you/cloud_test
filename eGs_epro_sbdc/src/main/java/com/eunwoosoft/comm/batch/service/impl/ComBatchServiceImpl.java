package com.eunwoosoft.comm.batch.service.impl;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.eunwoosoft.comm.batch.dao.ComBatchDao;
import com.eunwoosoft.comm.batch.service.ComBatchService;
import com.eunwoosoft.comm.itfc.dao.CommItfcDao;
import com.eunwoosoft.comm.itfc.service.CommItfcService;
import com.eunwoosoft.frwk.bul.AbstractFwkService;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;

/**
 * <pre>
 * com.eunwoosoft.comm.batch.service.impl 
 *    |_ ComBatchServiceImpl.java
 * 
 * </pre>
 * @date : 2015. 6. 15. 오전 11:20:34
 * @version : 1.0
 * @author : 은우소프트 김봉수
 */
@Service("comBatchService")
public class ComBatchServiceImpl extends AbstractFwkService implements ComBatchService{
	private static final Logger LOG = LoggerFactory.getLogger(ComBatchServiceImpl.class);
	
	@Resource(name="comBatchDao")
	private ComBatchDao comBatchDao;
	
	@Resource(name="commItfcService")
	private CommItfcService commItfcService;
	
	@Resource(name="commItfcDao")
	private CommItfcDao commItfcDao;
	
	/**
	 * <pre>
	 * 1.개요 : 사전공고 마감 배치
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.comm.batch.service.impl.ComBatchServiceImpl.java
	 * @Method : bereNotiClose
	 * @author : 천재
	 * @date : 2019. 09. 05. 
	 * @return
	 */
	public void bereNotiClose() {
		FwkParameterMap parameterMap = new FwkParameterMap();
		parameterMap.put("", "");
		comBatchDao.updtBereNotiClosePscd(parameterMap);
	}
	
	/**
	 * <pre>
	 * 1.개요 : 사전공고 마감 배치
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.comm.batch.service.impl.ComBatchServiceImpl.java
	 * @Method : payClose
	 * @author : 천재
	 * @date : 2019. 09. 05. 
	 * @return
	 */
	public void payClose() {
		FwkParameterMap parameterMap = new FwkParameterMap();
		parameterMap.put("", "");
		comBatchDao.updtPayClosePscd(parameterMap);
	}
	
	/**
	 * 오늘날짜 보기
	 * 
	 * @param pattern
	 * @return
	 * @throws Exception
	 */
	public String getToday(String pattern) throws Exception {
		Calendar calendar = Calendar.getInstance();
		return formatDate(calendar.getTime(), pattern);
	}	
	
	/**
	 * 날짜를 원하는 포맷으로 변경
	 * 
	 * @param date
	 * @param pattern
	 * @return
	 * @throws Exception
	 */
	public String formatDate(Date date, String pattern) throws Exception {
		if(date == null)
			return "";
		
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		return sdf.format(date);
	}	
}
