package com.eunwoosoft.opro.noti.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;  
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.eunwoosoft.comm.file.dao.ComAtmaAtchFileDao;
import com.eunwoosoft.comm.util.CmmnUtil;
import com.eunwoosoft.frwk.bul.AbstractFwkService;
import com.eunwoosoft.frwk.fol.dto.FwkDataEntity;
import com.eunwoosoft.frwk.fol.dto.FwkTransferObject;
import com.eunwoosoft.frwk.fol.util.FwkDateUtil;
import com.eunwoosoft.frwk.fol.util.FwkStringUtil;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;
import com.eunwoosoft.ipro.noti.service.IproBidNotiService;
import com.eunwoosoft.opro.noti.dao.OproQnaDao;
import com.eunwoosoft.opro.noti.service.OproQnaService;

/**
 *  
 * com.eunwoosoft.ipro.main.service.impl
 * IproMainLoginFormServiceImpl.java
 *
 * @Author : SungYoon_Ha
 * @Date   : 2017. 5. 30.
 *
 */
@Service(value="oproQnaService")

public class OproQnaServiceImpl extends AbstractFwkService implements OproQnaService {
	@SuppressWarnings("unused")
	private static final Logger LOG = LoggerFactory.getLogger(OproQnaServiceImpl.class); 
	
	@Resource(name="oproQnaDao") 
	private OproQnaDao oproQnaDao; 

	@Resource(name="comAtmaAtchFileDao")
	private ComAtmaAtchFileDao comAtmaAtchFileDao; 
	
	static final String contextPath = "noti/";
	
	@Override
	public FwkTransferObject qnaListWithPgng(FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();
		
		FwkDataEntity loginInfo = (FwkDataEntity) parameterMap.get("loginResult");
		
		/*	게시판 분류 번호 	
		A: 공지사항
		B: 자료실
		C: 상담신청
	 */
		parameterMap.put("P_BBS_SECD", "QNA");
		/* 게시판분류코드
		   H: 고객센터
		   N: 알림
		 */
		parameterMap.put("P_USE_SECD", "N");
		
		trans.put(OproQnaService.QNA_LIST,oproQnaDao.qnaListWithPgng(parameterMap));
		trans.put(OproQnaService.QNA_LIST_TOTCNT,oproQnaDao.qnaListTotCnt(parameterMap));
	
		return trans;
	}

	@Override
	public FwkTransferObject qnaListExcelDwld(FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();
		
		parameterMap.put("P_BBS_SECD", "QNA");
		parameterMap.put("P_USE_SECD", "N");
		
		trans.put("dataList",oproQnaDao.qnaExcelList(parameterMap));
		
		return trans;
	}
	
	/**
	 * 일반 공지사항 상세 
	 */
	
	@Override
	public FwkTransferObject qnaDetail(FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject(); 
		
		FwkDataEntity loginInfo = (FwkDataEntity) parameterMap.get("loginResult");
		parameterMap.put("P_BBS_SECD", "QNA");
		
		FwkDataEntity qnaDetail = oproQnaDao.qnaDetail(parameterMap);
		
		trans.put(OproQnaService.QNA_DETAIL, qnaDetail);
		 
		//조회수 카운트
		oproQnaDao.updateInqCntBaMst(parameterMap);
		
		 return trans; 
	}

	@Override
	public FwkTransferObject qnaRegist(FwkParameterMap parameterMap, HttpServletRequest request) throws Exception {
		FwkTransferObject trans = new FwkTransferObject();
		
		FwkDataEntity loginInfo = (FwkDataEntity) parameterMap.get("loginResult");
		parameterMap.put("P_CONN_IP", loginInfo.get("CONN_IP"));
		parameterMap.put("P_VEND_REG_NO", loginInfo.get("BIZR_NO")); 
		 
		/*기본 정보*/
		parameterMap.put("P_REGR_ID", loginInfo.get("USR_ID"));
		parameterMap.put("P_USR_NM", FwkStringUtil.castEuc2Iso(loginInfo.get("USR_NM")));
		parameterMap.put("P_REG_DT", FwkDateUtil.getCurrentDateTimeAsString());
		parameterMap.put("P_MOD_DT", FwkDateUtil.getCurrentDateTimeAsString());
		parameterMap.put("P_DEL_AT", "N");
		parameterMap.put("P_USE_YN", "Y");
		
		parameterMap.put("P_BBS_SECD", "QNA");
		parameterMap.put("P_USE_SECD", "N");
		
		//공지사항 마스터 등록   
		oproQnaDao.qnaBoardRegist(parameterMap);
		trans.put("parameterMap", parameterMap);
		//상세페이지로 파라미터 넘겨줄 데이터
//		System.out.println("@@@qna service @@@");
		trans.put("P_BBS_SN_TRANS", parameterMap.getInt("P_BBS_SN"));
		return trans;
	} 
	
	@Override 
	public FwkTransferObject qnaUpdt(final FwkParameterMap parameterMap) throws Exception {
		FwkTransferObject trans = new FwkTransferObject();
		
		FwkDataEntity loginInfo = (FwkDataEntity) parameterMap.get("loginResult");
		parameterMap.put("P_CONN_IP", loginInfo.get("CONN_IP"));
		parameterMap.put("P_REGR_ID", loginInfo.get("USR_ID"));
		parameterMap.put("P_USR_NM", loginInfo.get("USR_NM"));
		parameterMap.put("P_MOD_DT", FwkDateUtil.getCurrentDateTimeAsString());
		//공지사항 마스터 수정
		parameterMap.put("P_BBS_SECD", "QNA");
		
		System.err.println("@@@ qnaBoardUpdate start @@@");
		
		oproQnaDao.qnaBoardUpdate(parameterMap);
		
		System.err.println("@@@ qnaBoardUpdate end @@@");
		
		trans.put("parameterMap", parameterMap);
		
		trans.put("P_BBS_SN_TRANS", parameterMap.getInt("P_BBS_SN"));
		return trans;
	}
	
	
	
	@Override
	public FwkTransferObject qnaDelete(final FwkParameterMap parameterMap){
		FwkTransferObject trans = new FwkTransferObject();
		
		FwkDataEntity loginInfo = (FwkDataEntity) parameterMap.get("loginResult");
		
		parameterMap.put("P_REGR_ID", loginInfo.get("USR_ID"));
		parameterMap.put("P_USR_NM", loginInfo.get("USR_NM"));
		parameterMap.put("P_MOD_DT", FwkDateUtil.getCurrentDateTimeAsString());
		parameterMap.put("P_BBS_SECD", "QNA");
		//해당 게시물 삭제여부 수정
		oproQnaDao.qnaBoardDelete(parameterMap);
		//해당 게시물 답변삭제여부 수정
		oproQnaDao.qnaReplyDelete(parameterMap);
		
		return trans; 
	}
	
	@Override
	public FwkTransferObject usrInfoDetail(final FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();
		
		FwkDataEntity loginInfo = (FwkDataEntity) parameterMap.get("loginResult");
		parameterMap.put("P_REGR_ID", loginInfo.get("USR_ID"));
		
		FwkDataEntity usrInfoDetail = oproQnaDao.usrInfoDetail(parameterMap);
		
		trans.put(OproQnaService.USR_INFO_DETAIL, usrInfoDetail);
		
		return trans;
	}
	
}
