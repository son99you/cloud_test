package com.eunwoosoft.ipro.noti.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

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
import com.eunwoosoft.ipro.noti.dao.IproQnaDao;
import com.eunwoosoft.ipro.noti.service.IproBidNotiService;
import com.eunwoosoft.ipro.noti.service.IproQnaService;

/**
 *  
 * com.eunwoosoft.ipro.main.service.impl
 * IproMainLoginFormServiceImpl.java
 *
 * @Author : SungYoon_Ha
 * @Date   : 2017. 5. 30.
 *
 */
@Service(value="iproQnaService")
public class IproQnaServiceImpl extends AbstractFwkService implements IproQnaService {
	@SuppressWarnings("unused")
	private static final Logger LOG = LoggerFactory.getLogger(IproQnaServiceImpl.class); 
	
	@Resource(name="iproQnaDao") 
	private IproQnaDao iproQnaDao; 
 
	@Resource(name="comAtmaAtchFileDao")
	private ComAtmaAtchFileDao comAtmaAtchFileDao; 
	
	static final String contextPath = "noti/";
	
	@Override
	public FwkTransferObject qnaListWithPgng(FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();
		
		parameterMap.put("P_BBS_SECD", "QNA");
		parameterMap.put("P_USE_SECD", "N");
		 
		//qnaBoardList
		trans.put(IproQnaService.QNA_LIST,iproQnaDao.qnaBoardListWithPgng(parameterMap)); 
		//qnaBoardListTotCnt 
		trans.put(IproQnaService.QNA_LIST_TOTCNT,iproQnaDao.qnaBoardListTotCnt(parameterMap));
	
		return trans;
	}

	@Override
	public FwkTransferObject qnaListExcelDwld(FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();
		
		parameterMap.put("P_BBS_SECD", "QNA");
		parameterMap.put("P_USE_SECD", "N");
		
		//qnaBoardList
		trans.put("dataList",iproQnaDao.qnaBoardExcelList(parameterMap)); 
		
		return trans;
	}
	
	/**
	 * 내부 공지사항 상세 
	 */
	
	@Override
	public FwkTransferObject qnaDetail(FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject(); 
		FwkDataEntity loginInfo = (FwkDataEntity) parameterMap.get("loginResult");
		
		parameterMap.put("P_BBS_SECD", "QNA");
		
		FwkDataEntity qnaDetail = iproQnaDao.qnaBoardDetail(parameterMap);
		trans.put(IproQnaService.QNA_DETAIL, qnaDetail);
		
		
		//조회수 카운트
		iproQnaDao.updateInqCntBaMst(parameterMap);

		return trans; 
	} 
	
	
	/**
	 * 내부 공지사항 등록
	 * 
	 */
	@Override
	public FwkTransferObject qnaReplyRegist(final FwkParameterMap parameterMap, final HttpServletRequest request) throws Exception{
		FwkTransferObject trans = new FwkTransferObject();
		
		String atchFileGroupNo = "";
		FwkDataEntity loginInfo = (FwkDataEntity) parameterMap.get("loginResult");
		parameterMap.put("P_CONN_IP", loginInfo.get("CONN_IP"));
		parameterMap.put("P_VNED_REG_NO", loginInfo.get("VNED_REG_NO"));
		
		/*기본 정보*/
		parameterMap.put("P_USR_ID", loginInfo.get("USR_ID"));
		parameterMap.put("P_USR_NM", FwkStringUtil.castEuc2Iso(loginInfo.get("USR_NM")));
		parameterMap.put("P_REG_DT", FwkDateUtil.getCurrentDateTimeAsString());
		parameterMap.put("P_MOD_DT", FwkDateUtil.getCurrentDateTimeAsString());
		parameterMap.put("P_DEL_AT", "N");
		parameterMap.put("P_USE_YN", "Y");
		
		
		/*FwkParameterMap fileParameterMap = CmmnUtil.fileMultiUploads(multipartRequest, "P_FILE", contextPath);
//		if(fileParameterMap != null) {
//			List<Map<String, Object>> list = (List<Map<String, Object>>) fileParameterMap.get("list");
//			
//			atchFileGroupNo =  FwkStringUtil.replace(UUID.randomUUID().toString(), "-", "");
//			
//			parameterMap.put("P_FILE_GRP_NO", atchFileGroupNo);
//			List<Map<String, Object>> fileList = list.stream().map( obj-> {
//					parameterMap.forEach((x,y)->obj.put(x, y));
//					return obj;
//				} 
//			).collect(Collectors.toList());
//		
//			fileList.forEach((map)-> comAtmaAtchFileDao.insertAtchFileRegistOne((FwkParameterMap) map));
//		}
		
		
		if(fileParameterMap != null) {
			
			List<Map<String, Object>> pfileList = (List<Map<String, Object>>) fileParameterMap.get("list");
			atchFileGroupNo =  FwkStringUtil.replace(UUID.randomUUID().toString(), "-", "");
			
			parameterMap.put("P_FILE_GRP_NO", atchFileGroupNo);
			
			for (int i = 0; i < pfileList.size(); i++) {
				FwkParameterMap temp = new FwkParameterMap();
				temp = (FwkParameterMap) pfileList.get(i);
				temp.put("P_FILE_GRP_NO", atchFileGroupNo);
				
				comAtmaAtchFileDao.insertAtchFileRegistOne(temp);
			}
		}*/

		/*	게시판 분류 번호 	
	 		A: 공지사항
			B: 자료실
			C: 자주하는질문 
		 */
		parameterMap.put("P_BBS_SECD", "QNA");
		parameterMap.put("P_USE_SECD", "N");
		
		//공지사항 마스터 등록  
		iproQnaDao.qnaReplyRegist(parameterMap);
		
		trans.put("parameterMap", parameterMap);
		//상세페이지로 파라미터 넘겨줄 데이터
		trans.put("P_BBS_SN_TRANS", parameterMap.getInt("P_BBS_SN"));
		return trans;
	}
	
	@Override 
	public FwkTransferObject qnaReplyUpdt(final FwkParameterMap parameterMap) throws Exception {
		FwkTransferObject trans = new FwkTransferObject();
		
		String atchFileGroupNo ="";
		FwkDataEntity loginInfo = (FwkDataEntity) parameterMap.get("loginResult");
		parameterMap.put("P_CONN_IP", loginInfo.get("CONN_IP"));
		parameterMap.put("P_USR_ID", loginInfo.get("USR_ID"));
		parameterMap.put("P_USR_NM", loginInfo.get("USR_NM"));
		parameterMap.put("P_MOD_DT", FwkDateUtil.getCurrentDateTimeAsString());
		parameterMap.put("P_FILE_ALL_DEL", "Y");
		parameterMap.put("P_BBS_SECD", "QNA");

		/*if(parameterMap.get("P_FILE_MOD_YN").equals("Y")) {
			if(!parameterMap.get("P_FILE_GRP_NO").equals("")) {
				ArrayList  delList = CmmnUtil.paramSet(parameterMap.get("P_FILE_SN"));
				
				List<Integer> delfileList= new ArrayList<Integer>();
				
				for (int i = 0; i < delList.size(); i++) {
					delfileList.add(Integer.parseInt((String)delList.get(i)));
					parameterMap.put("P_FILE_ALL_DEL", "N");
				}
				parameterMap.put("delList", delfileList);
				iproQnaDao.deleteFileDelete(parameterMap);
			}else if(parameterMap.get("P_FILE_GRP_NO").equals("")) {
				atchFileGroupNo =  FwkStringUtil.replace(UUID.randomUUID().toString(), "-", "");
				parameterMap.put("P_FILE_GRP_NO", atchFileGroupNo);
			}
			
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) parameterMap.get("multipartRequest");
			
			FwkParameterMap fileParameterMap = CmmnUtil.fileMultiUploads(multipartRequest, "P_FILE", contextPath);
			
			if(fileParameterMap != null) {
//				List<Map<String, Object>> list = (List<Map<String, Object>>) fileParameterMap.get("list");
				
				parameterMap.put("P_FILE_GRP_NO", parameterMap.get("P_FILE_GRP_NO"));
//				List<Map<String, Object>> fileList = list.stream().map( obj-> {
//						parameterMap.forEach((x,y)->obj.put(x, y));
//						return obj;
//					} 
//				).collect(Collectors.toList());
//				
//				fileList.forEach((map)-> comAtmaAtchFileDao.insertAtchFileRegistOne((FwkParameterMap) map));
				
				List<Map<String, Object>> pfileList = (List<Map<String, Object>>) fileParameterMap.get("list");
				
				for (int i = 0; i < pfileList.size(); i++) {
					FwkParameterMap temp = new FwkParameterMap();
					temp = (FwkParameterMap) pfileList.get(i);
					temp.put("P_FILE_GRP_NO", parameterMap.get("P_FILE_GRP_NO"));
					comAtmaAtchFileDao.insertAtchFileRegistOne(temp);
					System.out.println("서비스임플과정");
				}
			}
		}*/
		System.out.println("서비스임플성공");
		//공지사항 마스터 수정
		iproQnaDao.qnaReplyUpdate(parameterMap);
		trans.put("parameterMap", parameterMap);
		trans.put("P_BBS_SN_TRANS", parameterMap.getInt("P_BBS_SN"));
		return trans;
	}
	
	
	
	@Override
	public FwkTransferObject qnaReplyDelete(final FwkParameterMap parameterMap){
		FwkTransferObject trans = new FwkTransferObject();
		
		FwkDataEntity loginInfo = (FwkDataEntity) parameterMap.get("loginResult");
		
		parameterMap.put("P_USR_ID", loginInfo.get("USR_ID"));
		parameterMap.put("P_USR_NM", loginInfo.get("USR_NM"));
		parameterMap.put("P_MOD_DT", FwkDateUtil.getCurrentDateTimeAsString());
		parameterMap.put("P_BBS_SECD", "QNA");
		// 삭제여부 수정
		iproQnaDao.qnaReplyDelete(parameterMap);
		
		return trans; 
	}
	
	@Override
	public FwkTransferObject iproQnaReplyInfo(FwkParameterMap parameterMap) {

		FwkTransferObject trans = new FwkTransferObject();

		parameterMap.put("P_BBS_SECD", "QNA");
		FwkDataEntity unityNttReplyInfo = iproQnaDao.iproQnaReplyInfo(parameterMap);
		trans.put(IproQnaService.QNA_REPLY_INFO, unityNttReplyInfo);

		FwkDataEntity usrInfoDetail = iproQnaDao.usrInfoDetail(parameterMap);
		
		trans.put(IproQnaService.USR_INFO_DETAIL, usrInfoDetail);
		
		return trans;
	}
	
	
}
