package com.eunwoosoft.ipro.estm.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.eunwoosoft.comm.file.dao.ComAtmaAtchFileDao;
import com.eunwoosoft.comm.util.FwkHttpUrlConnection;
import com.eunwoosoft.comm.util.MailSendUtil;
import com.eunwoosoft.frwk.bul.AbstractFwkService;
import com.eunwoosoft.frwk.fol.dto.FwkDataEntity;
import com.eunwoosoft.frwk.fol.dto.FwkTransferObject;
import com.eunwoosoft.frwk.fol.util.FwkFormatUtil;
import com.eunwoosoft.frwk.fol.util.FwkMessageUtil;
import com.eunwoosoft.frwk.fol.util.FwkStringUtil;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;
import com.eunwoosoft.ipro.comm.dao.IproCommDefaultDao;
import com.eunwoosoft.ipro.estm.dao.IproEstmCmtmProgDao;
import com.eunwoosoft.ipro.estm.service.IproEstmCmtmProgService;

@Service(value="iproEstmCmtmProgService")
public class IproEstmCmtmProgServiceImpl extends AbstractFwkService implements IproEstmCmtmProgService {
	private static final Logger LOG = LoggerFactory.getLogger(IproEstmCmtmProgServiceImpl.class);
	
	@Resource(name="iproEstmCmtmProgDao") 
	private IproEstmCmtmProgDao iproEstmCmtmProgDao;
	
	@Resource(name="comAtmaAtchFileDao")
    private ComAtmaAtchFileDao comAtmaAtchFileDao;
	
	@Resource(name="iproCommDefaultDao")
	private IproCommDefaultDao iproCommDefaultDao;
	
	
	@Override
	public FwkTransferObject estmCmtmProgList(final FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();
		
		parameterMap.put("P_DEL_AT", "N");
		
		if(!"".equals(parameterMap.getString("P_REG_BEGIN_DT_S"))){
			parameterMap.put("P_REG_BEGIN_DT_S", FwkFormatUtil.formatDate(parameterMap.get("P_REG_BEGIN_DT_S").toString(), "yyyy-MM-dd", "yyyyMMdd")); 
		}
		
		if(!"".equals(parameterMap.getString("P_REG_END_DT_S"))){
			parameterMap.put("P_REG_END_DT_S", FwkFormatUtil.formatDate(parameterMap.get("P_REG_END_DT_S").toString(), "yyyy-MM-dd", "yyyyMMdd")); 
		}

		parameterMap.put("P_ESTM_PSCD_GBN_S", "CMTM_PROG");
		FwkDataEntity session = (FwkDataEntity) parameterMap.get("loginResult");
		
		parameterMap.put("P_USR_ID", session.get("USR_ID"));
		
		trans.put("estmCmtmProgList", iproEstmCmtmProgDao.selectEstmCmtmProgList(parameterMap));
		trans.put("estmCmtmProgListTotCnt", iproEstmCmtmProgDao.selectEstmCmtmProgListTotCnt(parameterMap));
	
		return trans;
	}
	
	@Override
	public FwkTransferObject estmCmtmProgListExcelDwld(final FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();
		
		parameterMap.put("P_DEL_AT", "N");
		
		if(!"".equals(parameterMap.getString("P_REG_BEGIN_DT_S"))){
			parameterMap.put("P_REG_BEGIN_DT_S", FwkFormatUtil.formatDate(parameterMap.get("P_REG_BEGIN_DT_S").toString(), "yyyy-MM-dd", "yyyyMMdd")); 
		}
		
		if(!"".equals(parameterMap.getString("P_REG_END_DT_S"))){
			parameterMap.put("P_REG_END_DT_S", FwkFormatUtil.formatDate(parameterMap.get("P_REG_END_DT_S").toString(), "yyyy-MM-dd", "yyyyMMdd")); 
		}

		//parameterMap.put("P_ESTM_PSCD_GBN_S", "ESTM_PROG");
		FwkDataEntity session = (FwkDataEntity) parameterMap.get("loginResult");
		
		parameterMap.put("P_ESTM_CMTM_NO", session.get("USR_ID"));
		
		trans.put("estmCmtmProgList", iproEstmCmtmProgDao.selectEstmCmtmProgListExcelDwld(parameterMap));
	
		return trans;
	}
	
	
	
	
	@Override
	public FwkTransferObject cmtmMngCreateMeetingRoom(final FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();
		// T_ESTM_VIDO_MTNG
		// T_ESTM_VIDO_MTNG_CMTM
		// T_ESTM_VIDO_MTNG_OBJ
		
		
		List<FwkDataEntity> estmVidoMtngList = iproEstmCmtmProgDao.selectEstmVidoMtngCreatMeetList(parameterMap);
		List<FwkDataEntity> estmVidoMtngCmtmList = iproEstmCmtmProgDao.selectEstmVidoMtngCmtmCreatMeetList(parameterMap);
		List<FwkDataEntity> estmVidoMtngObjList = iproEstmCmtmProgDao.selectEstmVidoMtngObjCreatMeetList(parameterMap);
		
		
		
		for(int i = 0; i < estmVidoMtngList.size(); i++) {
			
			FwkDataEntity mtngEntity = estmVidoMtngList.get(i);
			FwkParameterMap vidoMtngMap = new FwkParameterMap();
			
			vidoMtngMap.put("P_ESTM_NO", mtngEntity.get("ESTM_NO").toString());
			vidoMtngMap.put("P_ESTM_PROCD_SEQ",mtngEntity.get("ESTM_PROCD_SEQ").toString());
			vidoMtngMap.put("P_VIDO_MTNG_SEQ",mtngEntity.get("VIDO_MTNG_SEQ").toString());
			vidoMtngMap.put("P_VIDO_MTNG_PRST_SECD","V001");
			
			
			String randomPassword = FwkStringUtil.replace(UUID.randomUUID().toString(), "-", "");
			vidoMtngMap.put("P_VIDO_ENTR_KEY", randomPassword.substring(0, 10));
			
			iproEstmCmtmProgDao.updateEstmVidoMtngEntrKey(vidoMtngMap);
		}
		
		
		
		for(int i = 0; i < estmVidoMtngCmtmList.size(); i++) {
			FwkDataEntity mtngCmtmEntity = estmVidoMtngCmtmList.get(i);
			
			FwkParameterMap vidoMtngCmtmMap = new FwkParameterMap();
			vidoMtngCmtmMap.put("P_ESTM_NO", mtngCmtmEntity.get("ESTM_NO").toString());
			vidoMtngCmtmMap.put("P_ESTM_PROCD_SEQ",mtngCmtmEntity.get("ESTM_PROCD_SEQ").toString());
			vidoMtngCmtmMap.put("P_VIDO_MTNG_SEQ",mtngCmtmEntity.get("VIDO_MTNG_SEQ").toString());
			vidoMtngCmtmMap.put("P_ESTM_CMTM_NO",mtngCmtmEntity.get("ESTM_CMTM_NO").toString());
			
			String randomPassword = FwkStringUtil.replace(UUID.randomUUID().toString(), "-", "");
			
			vidoMtngCmtmMap.put("P_VIDO_ENTR_KEY", randomPassword.substring(0, 10));
			
			iproEstmCmtmProgDao.updateEstmVidoMtngCmtmEntrKey(vidoMtngCmtmMap);
		}
		
		for(int i = 0; i < estmVidoMtngObjList.size(); i++) {
			FwkDataEntity mtngObjEntity = estmVidoMtngObjList.get(i);
			
			FwkParameterMap vidoMtngObjMap = new FwkParameterMap();
			vidoMtngObjMap.put("P_ESTM_NO", mtngObjEntity.get("ESTM_NO").toString());
			vidoMtngObjMap.put("P_ESTM_PROCD_SEQ",mtngObjEntity.get("ESTM_PROCD_SEQ").toString());
			vidoMtngObjMap.put("P_VIDO_MTNG_SEQ",mtngObjEntity.get("VIDO_MTNG_SEQ").toString());
			vidoMtngObjMap.put("P_ESTM_OBJ_SEQ",mtngObjEntity.get("ESTM_OBJ_SEQ").toString());
			
			String randomPassword = FwkStringUtil.replace(UUID.randomUUID().toString(), "-", "");
			
			vidoMtngObjMap.put("P_VIDO_ENTR_KEY", randomPassword.substring(0, 10));
			
			iproEstmCmtmProgDao.updateEstmVidoMtngObjEntrKey(vidoMtngObjMap);
		}
		
		// 메일 시작
		
//		FwkParameterMap mailParameterMap = new FwkParameterMap();
//		MailSendUtil mailSendUtil = new MailSendUtil();
//		
//		// 평가관리 마스터 조회
//		FwkDataEntity estmMngMstDetail = (FwkDataEntity) iproEstmCmtmProgDao.selectEstmMngMstDetail(parameterMap);   // T_ESTM_MNG_MST
//		
//		//메일정보 조회
//		mailParameterMap.put("P_MSG_SECD", "MAIL");
//		mailParameterMap.put("P_MSG_SN", "3");
//		//parameterMap.put("P_MSG_OBJ_ID", "");
//		FwkDataEntity msgDetail = (FwkDataEntity) iproCommDefaultDao.selectMsgContents(mailParameterMap);   // T_ESTM_MNG_MST
//		String mailCntn = "";
//		String mailTitle = "";
//		
//		//메일 제목
//		mailTitle = msgDetail.getString("MSG_TTL");
//		mailTitle = mailTitle.replace("#ESTM_NM#", estmMngMstDetail.getString("ESTM_NM"));
//		mailParameterMap.put("P_MSG_TTL",mailTitle);
//		
//		
//		
//		// 다시한번 데이터들을 호출한다.
//		List<FwkDataEntity> estmVidoMtngMailList = iproEstmCmtmProgDao.selectEstmVidoMtngCreatMeetList(parameterMap);
//		
//		String ext_room_id = parameterMap.getString("P_ESTM_NO") + "-" + parameterMap.getString("P_ESTM_PROCD_SEQ") + "-" + parameterMap.getString("P_VIDO_MTNG_SEQ");
//		// 평가담당자 메일호출
//		for(int i = 0; i < estmVidoMtngMailList.size(); i++) {
//			FwkDataEntity mtngEntity = estmVidoMtngMailList.get(i);
//			//메일 수신자
//			mailParameterMap.put("P_TO_MAIL", mtngEntity.getString("EMAL"));
//			
//			String ext_user_id = mtngEntity.getString("ESTM_CHRGR_ID");
//			String ext_password = mtngEntity.getString("VIDO_ENTR_KEY");
//			String nickname = mtngEntity.getString("ESTM_CHRGR_NM");
//			
//			String LINK_URL = FwkMessageUtil.getMessage("VIDO.LINK.URL") 
//							+ "ext_room_id=" + ext_user_id
//							+ "&ext_password=" + ext_password
//							+ "&nickname=" + nickname
//							;
//			//메일 내용
//			mailCntn = msgDetail.getString("MSG_CNTN");
//			mailCntn = mailCntn.replaceAll("#ESTM_NM#", estmMngMstDetail.getString("ESTM_NM"));
//			mailCntn = mailCntn.replaceAll("#ESTM_CHRG_DEPT_NM#", estmMngMstDetail.getString("ESTM_CHRG_DEPT_NM"));
//			mailCntn = mailCntn.replaceAll("#ESTM_CHRGR_NM#", estmMngMstDetail.getString("ESTM_CHRGR_NM"));
//			mailCntn = mailCntn.replaceAll("#LINK_URL#", LINK_URL);
//			
//			System.out.println("mailCntn :::: " + mailCntn);
//			
//			mailParameterMap.put("P_MSG_CNTN",mailCntn);
//			
//			//메일전송
//			try{
//				mailSendUtil.sendMail(mailParameterMap);
//			}catch(Exception e){
//			}
//		}
//		
//		List<FwkDataEntity> estmVidoMtngCmtmMailList = iproEstmCmtmProgDao.selectEstmVidoMtngCmtmCreatMeetList(parameterMap);
//		//List<FwkDataEntity> estmVidoMtngObjMailList = iproEstmProgDao.selectEstmVidoMtngObjCreatMeetList(parameterMap);
//		
//		// 평가위원 메일호출
//		for(int i = 0; i < estmVidoMtngCmtmMailList.size(); i++) {
//			FwkDataEntity mtngCmtmEntity = estmVidoMtngCmtmMailList.get(i);
//			//메일 수신자
//			mailParameterMap.put("P_TO_MAIL", mtngCmtmEntity.getString("EMAL"));
//			
//			String ext_user_id = "CMTM-" + mtngCmtmEntity.getString("ESTM_CMTM_NO");
//			String ext_password = mtngCmtmEntity.getString("VIDO_ENTR_KEY");
//			String nickname = mtngCmtmEntity.getString("ESTM_CHRGR_NM");
//			
//			String LINK_URL = FwkMessageUtil.getMessage("VIDO.LINK.URL") 
//							+ "ext_room_id=" + ext_user_id
//							+ "&ext_password=" + ext_password
//							+ "&nickname=" + nickname
//							;
//			//메일 내용
//			mailCntn = msgDetail.getString("MSG_CNTN");
//			mailCntn = mailCntn.replaceAll("#ESTM_NM#", estmMngMstDetail.getString("ESTM_NM"));
//			mailCntn = mailCntn.replaceAll("#ESTM_CHRG_DEPT_NM#", estmMngMstDetail.getString("ESTM_CHRG_DEPT_NM"));
//			mailCntn = mailCntn.replaceAll("#ESTM_CHRGR_NM#", estmMngMstDetail.getString("ESTM_CHRGR_NM"));
//			mailCntn = mailCntn.replaceAll("#LINK_URL#", LINK_URL);
//			
//			System.out.println("mailCntn :::: " + mailCntn);
//			
//			mailParameterMap.put("P_MSG_CNTN",mailCntn);
//			
//			//메일전송
//			try{
//				mailSendUtil.sendMail(mailParameterMap);
//			}catch(Exception e){
//			}
//		}
		
		
		// 메일 종료
		
		return trans;
	}

	@Override
	public FwkTransferObject estmCmtmProgDetail(final FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();
		
		// 평가관리 마스터 조회
		FwkDataEntity estmMngMstDetail = (FwkDataEntity) iproEstmCmtmProgDao.selectEstmMngMstDetail(parameterMap);   // T_ESTM_MNG_MST
		trans.put("estmMngMstDetail", estmMngMstDetail);
		
		parameterMap.put("P_DEL_AT", "N");
		
		// 첨부파일 조회
		parameterMap.put("P_ESTM_FSCD", "MST");
		FwkDataEntity estmMstFile = (FwkDataEntity) iproEstmCmtmProgDao.selectEstmFile(parameterMap);   // T_ESTM_FILE
		trans.put("estmMstFile", estmMstFile);
		
		// 심사위원 서명파일 첨부파일 그룹번호 조회
		parameterMap.put("P_ESTM_FSCD", "SIGN");
		FwkDataEntity estmSignFile = (FwkDataEntity) iproEstmCmtmProgDao.selectEstmFile(parameterMap);   // T_ESTM_FILE
		trans.put("estmSignFile", estmSignFile);
		
		// 심사위원 서명파일 조회
		if (estmSignFile != null) {
			String ESTM_SIGN_FILE_GRP_NO = estmSignFile.getString("FILE_GRP_NO");
			parameterMap.put("P_FILE_GRP_NO", ESTM_SIGN_FILE_GRP_NO);
			trans.put("estmSignFileList", comAtmaAtchFileDao.selectAtchFileListInqireByAtchFileGroupNo(parameterMap));
		}
		
		// 평가절차리스트 조회(Tab)
		List<FwkDataEntity> estmTabList = iproEstmCmtmProgDao.selectEstmTabList(parameterMap);   // T_ESTM_PROCD 
		trans.put("estmTabList", estmTabList);
		
		
		return trans;
	}

	@Override
	public void estmDelete(final FwkParameterMap parameterMap) {

		iproEstmCmtmProgDao.updateEstmDelAt(parameterMap);   // T_ESTM_MNG_MST UPDATE
		
		// 평가진행이력 등록
		parameterMap.put("P_RMK", "평가삭제");
		iproEstmCmtmProgDao.insertEstmProgHist(parameterMap);   // T_ESTM_PROG_HIST INSERT
		
	}

	@Override
	public FwkTransferObject estmProgObjDetail(final FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();
		
		// 평가관리 마스터 조회
		FwkDataEntity estmMngMstDetail = (FwkDataEntity) iproEstmCmtmProgDao.selectEstmMngMstDetail(parameterMap);   // T_ESTM_MNG_MST
		trans.put("estmMngMstDetail", estmMngMstDetail);
		
		parameterMap.put("P_DEL_AT", "N");
		
		// 평가대상정보 조회
		List<FwkDataEntity> estmObjList = iproEstmCmtmProgDao.selectEstmObjList(parameterMap);   // T_ESTM_OBJ
		trans.put("estmObjList", estmObjList);
		
		// 평가절차리스트 조회(Tab)
		List<FwkDataEntity> estmTabList = iproEstmCmtmProgDao.selectEstmTabList(parameterMap); 
		trans.put("estmTabList", estmTabList);
		
		return trans;
	}

	@Override
	public FwkTransferObject estmProgCmtmDetail(final FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();
		
		// 평가관리 마스터 조회
		FwkDataEntity estmMngMstDetail = (FwkDataEntity) iproEstmCmtmProgDao.selectEstmMngMstDetail(parameterMap);   // T_ESTM_MNG_MST
		trans.put("estmMngMstDetail", estmMngMstDetail);
		
		// 외부평가위원 조회
		parameterMap.put("P_INO_CMTM_SECD", "OUT");
		List<FwkDataEntity> outEstmCmtmList = iproEstmCmtmProgDao.selectEstmCmtmList(parameterMap);   // T_ESTM_CMTM
		trans.put("outEstmCmtmList", outEstmCmtmList);
		
		// 내부평가위원 조회
		parameterMap.put("P_INO_CMTM_SECD", "INN");
		List<FwkDataEntity> innEstmCmtmList = iproEstmCmtmProgDao.selectEstmCmtmList(parameterMap);   // T_ESTM_CMTM
		trans.put("innEstmCmtmList", innEstmCmtmList);
		
		// 평가절차리스트 조회(Tab)
		List<FwkDataEntity> estmTabList = iproEstmCmtmProgDao.selectEstmTabList(parameterMap); 
		trans.put("estmTabList", estmTabList);
		
		return trans;
	}
	
	/**
	 * 평가자구분이 평가위원인 경우 평가페이지 호출
	 */
	
	@Override
	public FwkTransferObject estmCmtmProgProcdADetail(final FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();
		
		parameterMap.put("P_DEL_AT", "N");
		
		// 평가관리 마스터 조회
		FwkDataEntity estmMngMstDetail = (FwkDataEntity) iproEstmCmtmProgDao.selectEstmMngMstDetail(parameterMap);   // T_ESTM_MNG_MST
		
		// 평가절차 조회
		FwkDataEntity estmProcdDetail = iproEstmCmtmProgDao.selectEstmProcdDetail(parameterMap);   // T_ESTM_PROCD
		
		// 평가대상 목록 조회
		List<FwkDataEntity> estmObjList = iproEstmCmtmProgDao.selectEstmObjValueList(parameterMap);
		
		// 평가위원 목록 조회
		List<FwkDataEntity> estmCmtmLastList = iproEstmCmtmProgDao.selectEstmCmtmLastList(parameterMap);   // T_ESTM_CMTM
		
		// 평가위원+평가대상 목록 조회
		List<FwkDataEntity> estmValueList = iproEstmCmtmProgDao.selectEstmValueList(parameterMap);   // T_ESTM_CMTM
		
		// 평가절차리스트 조회(Tab)
		List<FwkDataEntity> estmTabList = iproEstmCmtmProgDao.selectEstmTabList(parameterMap); 
		trans.put("estmTabList", estmTabList);
		
		
		trans.put("estmProcdDetail", estmProcdDetail);
		trans.put("estmValueList", estmValueList);
		trans.put("estmObjList", estmObjList);
		trans.put("estmObjListTotCnt", iproEstmCmtmProgDao.selectEstmObjValueListTotCnt(parameterMap));
		trans.put("estmCmtmLastList", estmCmtmLastList);
		trans.put("estmCmtmLastListCnt", estmCmtmLastList.size());
		trans.put("estmMngMstDetail", estmMngMstDetail);
		
		return trans;
	}
	
	/**
	 * 평가자구분이 평가담당인 경우 평가페이지 호출
	 */
	
	@Override
	public FwkTransferObject estmCmtmProgProcdBDetail(final FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();
		
		parameterMap.put("P_DEL_AT", "N");
		
		// 평가관리 마스터 조회
		FwkDataEntity estmMngMstDetail = (FwkDataEntity) iproEstmCmtmProgDao.selectEstmMngMstDetail(parameterMap);   // T_ESTM_MNG_MST
		
		// 평가절차 조회
		FwkDataEntity estmProcdDetail = iproEstmCmtmProgDao.selectEstmProcdDetail(parameterMap);   // T_ESTM_PROCD
		
		// 평가대상 목록 조회
		List<FwkDataEntity> estmObjList = iproEstmCmtmProgDao.selectEstmObjValueList(parameterMap);
		
		// 평가담당자 조회
		List<FwkDataEntity> estmChrgrList = iproEstmCmtmProgDao.selectEstmChrgrList(parameterMap);   // T_ESTM_CMTM
		
		// 평가담당자+평가대상 목록 조회
		List<FwkDataEntity> estmChrgrValueList = iproEstmCmtmProgDao.selectEstmChrgrValueList(parameterMap);   // T_ESTM_CMTM
		
		// 평가절차리스트 조회(Tab)
		List<FwkDataEntity> estmTabList = iproEstmCmtmProgDao.selectEstmTabList(parameterMap);
		
		
		trans.put("estmProcdDetail", estmProcdDetail);
		trans.put("estmObjList", estmObjList);
		trans.put("estmObjListTotCnt", iproEstmCmtmProgDao.selectEstmObjValueListTotCnt(parameterMap));
		trans.put("estmChrgrList", estmChrgrList);
		trans.put("estmChrgrValueList", estmChrgrValueList);
		trans.put("estmMngMstDetail", estmMngMstDetail);
		trans.put("estmTabList", estmTabList);
		
		return trans;
	}
	
	@Override
	public FwkTransferObject estmCmtmProgResultDetail(final FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();
		
		
		// 평가관리 마스터 조회
		FwkDataEntity estmMngMstDetail = (FwkDataEntity) iproEstmCmtmProgDao.selectEstmMngMstDetail(parameterMap);   // T_ESTM_MNG_MST
		trans.put("estmMngMstDetail", estmMngMstDetail);
		
		// 평가절차리스트 조회(Tab)
		List<FwkDataEntity> estmTabList = iproEstmCmtmProgDao.selectEstmTabList(parameterMap); 
		trans.put("estmTabList", estmTabList);
		
		// 조회리스트 추가 //
		// 평가결과추가조회 리스트
		List<FwkDataEntity> searchColList = iproEstmCmtmProgDao.selectEstmSearchColList(parameterMap); 
		trans.put("searchColList", searchColList);
		
		// 평가결과 ITEM MAX값 조회
		int maxSearchColLength = iproEstmCmtmProgDao.selectEstmSearchColLength(parameterMap);   // T_ESTM_MNG_MST
		trans.put("maxSearchColLength", maxSearchColLength);
		// 조회리스트 종료//
		
		// 평가서식절차구분코드 가 적격인 경우 시작//
		List<FwkDataEntity> estmResultProcdObjSlctList = iproEstmCmtmProgDao.selectEstmResultProcdObjSlctList(parameterMap);
		trans.put("estmResultProcdObjSlctList", estmResultProcdObjSlctList);
		// 평가서식절차구분코드 가 적격인 경우 종료//
		
		// 평가결과목록 리스트 시작//
		/**
		 *  평가대상 리스트
		 *  (모든 평가대상을 불러온다.)
		 */
		
		parameterMap.put("pageYn", "Y");
		
		List<FwkDataEntity> estmResultObjAllList = iproEstmCmtmProgDao.selectEstmResultObjAllList(parameterMap); 
		trans.put("estmResultObjAllList", estmResultObjAllList);
		trans.put("estmResultObjAllListTotCnt", iproEstmCmtmProgDao.selectEstmResultObjAllListTotCnt(parameterMap));
		
		/**
		 * 평가위원 리스트
		 */
		List<FwkDataEntity> estmResultCmtmAllList = iproEstmCmtmProgDao.selectEstmResultCmtmAllList(parameterMap); 
		trans.put("estmResultCmtmAllList", estmResultCmtmAllList);
		trans.put("estmResultCmtmAllListCnt", estmResultCmtmAllList.size());
		trans.put("estmResultObjAllListCnt", estmResultObjAllList.size());
		
		//(평가항목)P_SEARCH_ITEM
		//(평가위원총점)P_SEARCH_ITEM_CMTMTOT
		//(평가총점)P_SEARCH_ESTM_PROCD_SEQ_TOT
		//(최종점수합계적용여부)P_SEARCH_ITEM_TOTSUMAT
		//(최종점수합계적용비율)P_ESTM_PROCD_SEQ_TOTSUM${ data.ESTM_PROCD_SEQ }
		Object SEARCH_ITEM = parameterMap.get("P_SEARCH_ITEM");
//		Object SEARCH_ITEM_CMTMTOT = parameterMap.get("P_SEARCH_ITEM_CMTMTOT");
//		Object SEARCH_ESTM_PROCD_SEQ_TOT = parameterMap.get("P_SEARCH_ESTM_PROCD_SEQ_TOT");
//		Object SEARCH_ITEM_TOTSUMAT = parameterMap.get("P_SEARCH_ITEM_TOTSUMAT");

		ArrayList<String> P_SEARCH_ITEM_LIST = new ArrayList<String>();
		ArrayList<String> P_SEARCH_ITEM_CHECK_LIST = new ArrayList<String>();
//		ArrayList<String> P_SEARCH_ITEM_CMTMTOT_LIST = new ArrayList<String>();
//		ArrayList<String> P_SEARCH_ITEM_CMTMTOT_CHECK_LIST = new ArrayList<String>();
//		ArrayList<String> P_SEARCH_ESTM_PROCD_SEQ_LIST = new ArrayList<String>();
//		ArrayList<String> P_SEARCH_ESTM_PROCD_SEQ_CHECK_LIST = new ArrayList<String>();
		
		
		String SEARCH_CHECK = "";
		// 평가항목
		if(SEARCH_ITEM instanceof String) {
			P_SEARCH_ITEM_LIST.add((String) SEARCH_ITEM);
			SEARCH_CHECK = SEARCH_ITEM.toString();
			P_SEARCH_ITEM_CHECK_LIST.add(SEARCH_CHECK);
		}else {
			if(SEARCH_ITEM instanceof ArrayList) {
				ArrayList<String> SEARCH_ITEMList = (ArrayList) SEARCH_ITEM;
				for (int idx = 0; idx < SEARCH_ITEMList.size(); idx++) {
					SEARCH_CHECK = SEARCH_ITEMList.get(idx).toString();
					P_SEARCH_ITEM_LIST.add(SEARCH_ITEMList.get(idx));
					P_SEARCH_ITEM_CHECK_LIST.add(SEARCH_CHECK);
				}
			}else if(SEARCH_ITEM instanceof String[]) {
				String[] SEARCH_ITEMList = (String[]) SEARCH_ITEM;
				for (int idx = 0; idx < SEARCH_ITEMList.length; idx++) {
					SEARCH_CHECK = SEARCH_ITEMList[idx].toString();
					P_SEARCH_ITEM_LIST.add(SEARCH_ITEMList[idx]);
					P_SEARCH_ITEM_CHECK_LIST.add(SEARCH_CHECK);
				}
			}
		}
		
		// 평가위원총합
//		if(SEARCH_ITEM_CMTMTOT instanceof String) {
//			P_SEARCH_ESTM_PROCD_SEQ_LIST.add((String) SEARCH_ITEM_CMTMTOT);
//			SEARCH_CHECK = (String) SEARCH_ITEM_CMTMTOT;
//			P_SEARCH_ITEM_CMTMTOT_CHECK_LIST.add(SEARCH_CHECK);
//		}else {
//			if(SEARCH_ITEM_CMTMTOT instanceof ArrayList) {
//				ArrayList<String> SEARCH_ITEM_CMTMTOTList = (ArrayList) SEARCH_ITEM_CMTMTOT;
//				for (int idx = 0; idx < SEARCH_ITEM_CMTMTOTList.size(); idx++) {
//					SEARCH_CHECK = SEARCH_ITEM_CMTMTOTList.get(idx).toString();
//					P_SEARCH_ITEM_CMTMTOT_LIST.add(SEARCH_ITEM_CMTMTOTList.get(idx));
//					P_SEARCH_ITEM_CMTMTOT_CHECK_LIST.add(SEARCH_CHECK);
//				}
//			}else if(SEARCH_ITEM_CMTMTOT instanceof String[]) {
//				String[] SEARCH_ITEM_CMTMTOTList = (String[]) SEARCH_ITEM_CMTMTOT;
//				for (int idx = 0; idx < SEARCH_ITEM_CMTMTOTList.length; idx++) {
//					SEARCH_CHECK = SEARCH_ITEM_CMTMTOTList[idx].toString();
//					P_SEARCH_ITEM_CMTMTOT_LIST.add(SEARCH_ITEM_CMTMTOTList[idx]);
//					P_SEARCH_ITEM_CMTMTOT_CHECK_LIST.add(SEARCH_CHECK);
//				}
//			}
//		}
		
		// 평가총점
//		if(SEARCH_ESTM_PROCD_SEQ_TOT instanceof String) {
//			P_SEARCH_ESTM_PROCD_SEQ_LIST.add((String) SEARCH_ESTM_PROCD_SEQ_TOT);
//			SEARCH_CHECK = (String) SEARCH_ESTM_PROCD_SEQ_TOT;
//			P_SEARCH_ESTM_PROCD_SEQ_CHECK_LIST.add(SEARCH_CHECK);
//		}else {
//			if(SEARCH_ESTM_PROCD_SEQ_TOT instanceof ArrayList) {
//				ArrayList<String> SEARCH_ESTM_PROCD_SEQ_TOTList = (ArrayList) SEARCH_ESTM_PROCD_SEQ_TOT;
//				for (int idx = 0; idx < SEARCH_ESTM_PROCD_SEQ_TOTList.size(); idx++) {
//					SEARCH_CHECK = SEARCH_ESTM_PROCD_SEQ_TOTList.get(idx).toString();
//					P_SEARCH_ESTM_PROCD_SEQ_LIST.add(SEARCH_ESTM_PROCD_SEQ_TOTList.get(idx));
//					P_SEARCH_ESTM_PROCD_SEQ_CHECK_LIST.add(SEARCH_CHECK);
//				}
//			}else if(SEARCH_ESTM_PROCD_SEQ_TOT instanceof String[]) {
//				String[] SEARCH_ESTM_PROCD_SEQ_TOTList = (String[]) SEARCH_ESTM_PROCD_SEQ_TOT;
//				for (int idx = 0; idx < SEARCH_ESTM_PROCD_SEQ_TOTList.length; idx++) {
//					SEARCH_CHECK = SEARCH_ESTM_PROCD_SEQ_TOTList[idx].toString();
//					P_SEARCH_ESTM_PROCD_SEQ_LIST.add(SEARCH_ESTM_PROCD_SEQ_TOTList[idx]);
//					P_SEARCH_ESTM_PROCD_SEQ_CHECK_LIST.add(SEARCH_CHECK);
//				}
//			}
//		}
		
//		System.out.println("P_SEARCH_ITEM_LIST ::: " + P_SEARCH_ITEM_LIST.size());
//		System.out.println("P_SEARCH_ITEM_CMTMTOT_LIST ::: " + P_SEARCH_ITEM_CMTMTOT_LIST.size());
		
		//평가항목 체크
		if(P_SEARCH_ITEM_LIST.size() > 0){
			parameterMap.put("P_SEARCH_ITEM_LIST", P_SEARCH_ITEM_LIST);
			parameterMap.put("P_CHECK_LIST", P_SEARCH_ITEM_CHECK_LIST);
			List<FwkDataEntity> estmResultSearchCheckList = iproEstmCmtmProgDao.selectEstmResultSearchCheckList(parameterMap);
			trans.put("SEARCH_ITEM_CHECK_LIST", estmResultSearchCheckList);
			
		}

		//평가위원총점		
//		if(P_SEARCH_ITEM_CMTMTOT_LIST.size() > 0){
//			parameterMap.put("P_SEARCH_ITEM_CMTMTOT_LIST", P_SEARCH_ITEM_CMTMTOT_LIST);
//			parameterMap.put("P_CHECK_LIST", P_SEARCH_ITEM_CMTMTOT_CHECK_LIST);
//			List<FwkDataEntity> estmResultSearchCheckList = iproEstmProgDao.selectEstmResultSearchCheckList(parameterMap);
//			trans.put("SEARCH_ITEM_CMTMTOT_CHECK_LIST", estmResultSearchCheckList);
//		}
		
		//평가총점		
//		if(P_SEARCH_ESTM_PROCD_SEQ_CHECK_LIST.size() > 0){
//			parameterMap.put("P_SEARCH_ESTM_PROCD_SEQ_LIST", P_SEARCH_ESTM_PROCD_SEQ_LIST);
//			parameterMap.put("P_CHECK_LIST", P_SEARCH_ESTM_PROCD_SEQ_CHECK_LIST);
//			List<FwkDataEntity> estmResultSearchCheckList = iproEstmProgDao.selectEstmResultSearchCheckList(parameterMap);
//			trans.put("SEARCH_ESTM_PROCD_SEQ_CHECK_LIST", estmResultSearchCheckList);
//		}
		
		
//		if(P_SEARCH_ITEM_LIST.size() > 0 || P_SEARCH_ITEM_CMTMTOT_LIST.size() > 0 || P_SEARCH_ESTM_PROCD_SEQ_CHECK_LIST.size() > 0) {
//			
//		}
		
		//평가결과목록 조회 체크된 평가만 찾기 위한 로직 시작//
		List<FwkDataEntity> estmResultProcdSeqList = iproEstmCmtmProgDao.selectEstmResultProcdSeqList(parameterMap);
		trans.put("estmResultProcdSeqList", estmResultProcdSeqList);
		//평가결과목록 조회 체크된 평가만 찾기 위한 로직 종료//
		
		//평가결과목록 조회 체크된 평가항목만 찾기 위한 로직 시작//
		List<FwkDataEntity> estmResultItemNoList = iproEstmCmtmProgDao.selectEstmResultItemNoList(parameterMap);
		trans.put("estmResultItemNoList", estmResultItemNoList);
		//평가결과목록 조회 체크된 평가항목만 찾기 위한 로직 종료//
		
		
		/**
		 * 평가결과리스트
		 */
		List<FwkDataEntity> estmResultItemAllList = iproEstmCmtmProgDao.selectEstmResultItemAllList(parameterMap); 
		trans.put("estmResultItemAllList", estmResultItemAllList);
		// 평가결과목록 리스트 종료//
		
		
		return trans;
	}
	
	
	
	@Override
	public FwkTransferObject estmCmtmProgResultDetailExcelDwld(final FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();
		
		
		// 평가관리 마스터 조회
		FwkDataEntity estmMngMstDetail = (FwkDataEntity) iproEstmCmtmProgDao.selectEstmMngMstDetail(parameterMap);   // T_ESTM_MNG_MST
		trans.put("estmMngMstDetail", estmMngMstDetail);
		
		// 평가절차리스트 조회(Tab)
		List<FwkDataEntity> estmTabList = iproEstmCmtmProgDao.selectEstmTabList(parameterMap); 
		trans.put("estmTabList", estmTabList);
		
		// 조회리스트 추가 //
		// 평가결과추가조회 리스트
		List<FwkDataEntity> searchColList = iproEstmCmtmProgDao.selectEstmSearchColList(parameterMap); 
		trans.put("searchColList", searchColList);
		
		// 평가결과 ITEM MAX값 조회
		int maxSearchColLength = iproEstmCmtmProgDao.selectEstmSearchColLength(parameterMap);   // T_ESTM_MNG_MST
		trans.put("maxSearchColLength", maxSearchColLength);
		// 조회리스트 종료//
		
		// 평가서식절차구분코드 가 적격인 경우 시작//
		List<FwkDataEntity> estmResultProcdObjSlctList = iproEstmCmtmProgDao.selectEstmResultProcdObjSlctList(parameterMap);
		trans.put("estmResultProcdObjSlctList", estmResultProcdObjSlctList);
		// 평가서식절차구분코드 가 적격인 경우 종료//
		
		// 평가결과목록 리스트 시작//
		/**
		 *  평가대상 리스트
		 *  (모든 평가대상을 불러온다.)
		 */
		
		parameterMap.put("pageYn", "N");
		
		List<FwkDataEntity> estmResultObjAllList = iproEstmCmtmProgDao.selectEstmResultObjAllList(parameterMap); 
		trans.put("estmResultObjAllList", estmResultObjAllList);
		
		/**
		 * 평가위원 리스트
		 */
		List<FwkDataEntity> estmResultCmtmAllList = iproEstmCmtmProgDao.selectEstmResultCmtmAllList(parameterMap); 
		trans.put("estmResultCmtmAllList", estmResultCmtmAllList);
		trans.put("estmResultCmtmAllListCnt", estmResultCmtmAllList.size());
		trans.put("estmResultObjAllListCnt", estmResultObjAllList.size());
		
		//(평가항목)P_SEARCH_ITEM
		//(평가위원총점)P_SEARCH_ITEM_CMTMTOT
		//(평가총점)P_SEARCH_ESTM_PROCD_SEQ_TOT
		//(최종점수합계적용여부)P_SEARCH_ITEM_TOTSUMAT
		//(최종점수합계적용비율)P_ESTM_PROCD_SEQ_TOTSUM${ data.ESTM_PROCD_SEQ }
		Object SEARCH_ITEM = parameterMap.get("P_SEARCH_ITEM");
//		Object SEARCH_ITEM_CMTMTOT = parameterMap.get("P_SEARCH_ITEM_CMTMTOT");
//		Object SEARCH_ESTM_PROCD_SEQ_TOT = parameterMap.get("P_SEARCH_ESTM_PROCD_SEQ_TOT");
//		Object SEARCH_ITEM_TOTSUMAT = parameterMap.get("P_SEARCH_ITEM_TOTSUMAT");

		ArrayList<String> P_SEARCH_ITEM_LIST = new ArrayList<String>();
		ArrayList<String> P_SEARCH_ITEM_CHECK_LIST = new ArrayList<String>();
//		ArrayList<String> P_SEARCH_ITEM_CMTMTOT_LIST = new ArrayList<String>();
//		ArrayList<String> P_SEARCH_ITEM_CMTMTOT_CHECK_LIST = new ArrayList<String>();
//		ArrayList<String> P_SEARCH_ESTM_PROCD_SEQ_LIST = new ArrayList<String>();
//		ArrayList<String> P_SEARCH_ESTM_PROCD_SEQ_CHECK_LIST = new ArrayList<String>();
		
		
		String SEARCH_CHECK = "";
		// 평가항목
		if(SEARCH_ITEM instanceof String) {
			P_SEARCH_ITEM_LIST.add((String) SEARCH_ITEM);
			SEARCH_CHECK = SEARCH_ITEM.toString();
			P_SEARCH_ITEM_CHECK_LIST.add(SEARCH_CHECK);
		}else {
			if(SEARCH_ITEM instanceof ArrayList) {
				ArrayList<String> SEARCH_ITEMList = (ArrayList) SEARCH_ITEM;
				for (int idx = 0; idx < SEARCH_ITEMList.size(); idx++) {
					SEARCH_CHECK = SEARCH_ITEMList.get(idx).toString();
					P_SEARCH_ITEM_LIST.add(SEARCH_ITEMList.get(idx));
					P_SEARCH_ITEM_CHECK_LIST.add(SEARCH_CHECK);
				}
			}else if(SEARCH_ITEM instanceof String[]) {
				String[] SEARCH_ITEMList = (String[]) SEARCH_ITEM;
				for (int idx = 0; idx < SEARCH_ITEMList.length; idx++) {
					SEARCH_CHECK = SEARCH_ITEMList[idx].toString();
					P_SEARCH_ITEM_LIST.add(SEARCH_ITEMList[idx]);
					P_SEARCH_ITEM_CHECK_LIST.add(SEARCH_CHECK);
				}
			}
		}
		
		// 평가위원총합
//		if(SEARCH_ITEM_CMTMTOT instanceof String) {
//			P_SEARCH_ESTM_PROCD_SEQ_LIST.add((String) SEARCH_ITEM_CMTMTOT);
//			SEARCH_CHECK = (String) SEARCH_ITEM_CMTMTOT;
//			P_SEARCH_ITEM_CMTMTOT_CHECK_LIST.add(SEARCH_CHECK);
//		}else {
//			if(SEARCH_ITEM_CMTMTOT instanceof ArrayList) {
//				ArrayList<String> SEARCH_ITEM_CMTMTOTList = (ArrayList) SEARCH_ITEM_CMTMTOT;
//				for (int idx = 0; idx < SEARCH_ITEM_CMTMTOTList.size(); idx++) {
//					SEARCH_CHECK = SEARCH_ITEM_CMTMTOTList.get(idx).toString();
//					P_SEARCH_ITEM_CMTMTOT_LIST.add(SEARCH_ITEM_CMTMTOTList.get(idx));
//					P_SEARCH_ITEM_CMTMTOT_CHECK_LIST.add(SEARCH_CHECK);
//				}
//			}else if(SEARCH_ITEM_CMTMTOT instanceof String[]) {
//				String[] SEARCH_ITEM_CMTMTOTList = (String[]) SEARCH_ITEM_CMTMTOT;
//				for (int idx = 0; idx < SEARCH_ITEM_CMTMTOTList.length; idx++) {
//					SEARCH_CHECK = SEARCH_ITEM_CMTMTOTList[idx].toString();
//					P_SEARCH_ITEM_CMTMTOT_LIST.add(SEARCH_ITEM_CMTMTOTList[idx]);
//					P_SEARCH_ITEM_CMTMTOT_CHECK_LIST.add(SEARCH_CHECK);
//				}
//			}
//		}
		
		// 평가총점
//		if(SEARCH_ESTM_PROCD_SEQ_TOT instanceof String) {
//			P_SEARCH_ESTM_PROCD_SEQ_LIST.add((String) SEARCH_ESTM_PROCD_SEQ_TOT);
//			SEARCH_CHECK = (String) SEARCH_ESTM_PROCD_SEQ_TOT;
//			P_SEARCH_ESTM_PROCD_SEQ_CHECK_LIST.add(SEARCH_CHECK);
//		}else {
//			if(SEARCH_ESTM_PROCD_SEQ_TOT instanceof ArrayList) {
//				ArrayList<String> SEARCH_ESTM_PROCD_SEQ_TOTList = (ArrayList) SEARCH_ESTM_PROCD_SEQ_TOT;
//				for (int idx = 0; idx < SEARCH_ESTM_PROCD_SEQ_TOTList.size(); idx++) {
//					SEARCH_CHECK = SEARCH_ESTM_PROCD_SEQ_TOTList.get(idx).toString();
//					P_SEARCH_ESTM_PROCD_SEQ_LIST.add(SEARCH_ESTM_PROCD_SEQ_TOTList.get(idx));
//					P_SEARCH_ESTM_PROCD_SEQ_CHECK_LIST.add(SEARCH_CHECK);
//				}
//			}else if(SEARCH_ESTM_PROCD_SEQ_TOT instanceof String[]) {
//				String[] SEARCH_ESTM_PROCD_SEQ_TOTList = (String[]) SEARCH_ESTM_PROCD_SEQ_TOT;
//				for (int idx = 0; idx < SEARCH_ESTM_PROCD_SEQ_TOTList.length; idx++) {
//					SEARCH_CHECK = SEARCH_ESTM_PROCD_SEQ_TOTList[idx].toString();
//					P_SEARCH_ESTM_PROCD_SEQ_LIST.add(SEARCH_ESTM_PROCD_SEQ_TOTList[idx]);
//					P_SEARCH_ESTM_PROCD_SEQ_CHECK_LIST.add(SEARCH_CHECK);
//				}
//			}
//		}
		
//		System.out.println("P_SEARCH_ITEM_LIST ::: " + P_SEARCH_ITEM_LIST.size());
//		System.out.println("P_SEARCH_ITEM_CMTMTOT_LIST ::: " + P_SEARCH_ITEM_CMTMTOT_LIST.size());
		
		//평가항목 체크
		if(P_SEARCH_ITEM_LIST.size() > 0){
			parameterMap.put("P_SEARCH_ITEM_LIST", P_SEARCH_ITEM_LIST);
			parameterMap.put("P_CHECK_LIST", P_SEARCH_ITEM_CHECK_LIST);
			List<FwkDataEntity> estmResultSearchCheckList = iproEstmCmtmProgDao.selectEstmResultSearchCheckList(parameterMap);
			trans.put("SEARCH_ITEM_CHECK_LIST", estmResultSearchCheckList);
			
		}

		//평가위원총점		
//		if(P_SEARCH_ITEM_CMTMTOT_LIST.size() > 0){
//			parameterMap.put("P_SEARCH_ITEM_CMTMTOT_LIST", P_SEARCH_ITEM_CMTMTOT_LIST);
//			parameterMap.put("P_CHECK_LIST", P_SEARCH_ITEM_CMTMTOT_CHECK_LIST);
//			List<FwkDataEntity> estmResultSearchCheckList = iproEstmProgDao.selectEstmResultSearchCheckList(parameterMap);
//			trans.put("SEARCH_ITEM_CMTMTOT_CHECK_LIST", estmResultSearchCheckList);
//		}
		
		//평가총점		
//		if(P_SEARCH_ESTM_PROCD_SEQ_CHECK_LIST.size() > 0){
//			parameterMap.put("P_SEARCH_ESTM_PROCD_SEQ_LIST", P_SEARCH_ESTM_PROCD_SEQ_LIST);
//			parameterMap.put("P_CHECK_LIST", P_SEARCH_ESTM_PROCD_SEQ_CHECK_LIST);
//			List<FwkDataEntity> estmResultSearchCheckList = iproEstmProgDao.selectEstmResultSearchCheckList(parameterMap);
//			trans.put("SEARCH_ESTM_PROCD_SEQ_CHECK_LIST", estmResultSearchCheckList);
//		}
		
		
//		if(P_SEARCH_ITEM_LIST.size() > 0 || P_SEARCH_ITEM_CMTMTOT_LIST.size() > 0 || P_SEARCH_ESTM_PROCD_SEQ_CHECK_LIST.size() > 0) {
//			
//		}
		
		//평가결과목록 조회 체크된 평가만 찾기 위한 로직 시작//
		List<FwkDataEntity> estmResultProcdSeqList = iproEstmCmtmProgDao.selectEstmResultProcdSeqList(parameterMap);
		trans.put("estmResultProcdSeqList", estmResultProcdSeqList);
		//평가결과목록 조회 체크된 평가만 찾기 위한 로직 종료//
		
		//평가결과목록 조회 체크된 평가항목만 찾기 위한 로직 시작//
		List<FwkDataEntity> estmResultItemNoList = iproEstmCmtmProgDao.selectEstmResultItemNoList(parameterMap);
		trans.put("estmResultItemNoList", estmResultItemNoList);
		//평가결과목록 조회 체크된 평가항목만 찾기 위한 로직 종료//
		
		
		/**
		 * 평가결과리스트
		 */
		List<FwkDataEntity> estmResultItemAllList = iproEstmCmtmProgDao.selectEstmResultItemAllList(parameterMap); 
		trans.put("estmResultItemAllList", estmResultItemAllList);
		// 평가결과목록 리스트 종료//
		
		
		return trans;
	}
	
	
	
	
	
	@Override
	public FwkTransferObject estmCmtmProgVidoMtngDetail(final FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();
		
		parameterMap.put("P_DEL_AT", "N");
		
		// 평가관리 마스터 조회
		FwkDataEntity estmMngMstDetail = (FwkDataEntity) iproEstmCmtmProgDao.selectEstmMngMstDetail(parameterMap);   // T_ESTM_MNG_MST
		
		// 화상회의 참여자 구분
		// 관리자 ""
		// 평가위원 "CMTM"
		// 평가대상 "parameterMap.put("P_ESTM_NO")
		parameterMap.put("P_USER_GBN",""); // 내부평가위원는 CMTM 안붙음
		parameterMap.put("P_ROLE","interviewer"); //admin , interviewer , interviewee
		parameterMap.put("P_VIDO_LINK_URL",FwkMessageUtil.getMessage("VIDO.LINK.URL"));
		
		
		//화상회의정보
		List<FwkDataEntity> estmVidoList = iproEstmCmtmProgDao.selectEstmMngProgVidoMtngList(parameterMap);   // T_ESTM_MNG_MST   
		trans.put("estmVidoList", estmVidoList);
		
		// 평가절차리스트 조회
		List<FwkDataEntity> estmTabList = iproEstmCmtmProgDao.selectEstmTabList(parameterMap); 
		trans.put("estmTabList", estmTabList);
		
		trans.put("estmMngMstDetail", estmMngMstDetail);
		
		return trans;
	}


	@Override
	public void estmProgObjRegist(final FwkParameterMap parameterMap) throws Exception {
		
		if(parameterMap.get("P_OBJ_YN") != null) {
			
			Object P_OBJ_YN = parameterMap.get("P_OBJ_YN");   // 평가대상 별 저장데이터가 달라서 대상여부로 판단
			Object P_DEL_AT = parameterMap.get("P_DEL_AT");
			Object P_ESTM_OBJ_SEQ = parameterMap.get("P_ESTM_OBJ_SEQ");
			Object P_VEND_REG_NO = parameterMap.get("P_VEND_REG_NO");
			Object P_BIZRNO = parameterMap.get("P_BIZRNO");
			Object P_VEND_NM = parameterMap.get("P_VEND_NM");
			Object P_ITEM_NO = parameterMap.get("P_ITEM_NO");
			Object P_ITEM_NM = parameterMap.get("P_ITEM_NM");
			Object P_ESTM_CMTM_NO = parameterMap.get("P_ESTM_CMTM_NO");
			Object P_ESTM_CMTM_NM = parameterMap.get("P_ESTM_CMTM_NM");
			Object P_ESTM_OBJ_PE_NO = parameterMap.get("P_ESTM_OBJ_PE_NO");
			Object P_ESTM_OBJ_PE_NM = parameterMap.get("P_ESTM_OBJ_PE_NM");
			Object P_EMPL_NO = parameterMap.get("P_EMPL_NO");
			Object P_RSDN_NO_1 = parameterMap.get("P_RSDN_NO_1");
			Object P_RSDN_NO_2 = parameterMap.get("P_RSDN_NO_2");
			Object P_TEL_NO = parameterMap.get("P_TEL_NO");
			Object P_EMAL = parameterMap.get("P_EMAL");

			if(P_OBJ_YN != null){
				
				if(P_OBJ_YN instanceof String){
					
					if(parameterMap.get("P_ESTM_OBJ_SEQ").equals("")) {
						// 평가대상 등록
						
						parameterMap.put("P_RSDN_NO", parameterMap.get("P_RSDN_NO_1") + "" + parameterMap.get("P_RSDN_NO_2"));
						System.err.println("@@@ T_ESTM_OBJ 등록 @@@");
						
						iproEstmCmtmProgDao.insertEstmObj(parameterMap);   // T_ESTM_OBJ INSERT						
					}else{
						parameterMap.put("P_ESTM_OBJ_SEQ", parameterMap.get("P_ESTM_OBJ_SEQ"));
						parameterMap.put("P_DEL_AT", parameterMap.get("P_DEL_AT"));
						
						System.err.println("@@@ T_ESTM_OBJ 삭제 @@@");
						iproEstmCmtmProgDao.updateEstmObjDelAt(parameterMap);   // T_ESTM_OBJ INSERT
					}
					
				}else if(P_OBJ_YN instanceof ArrayList){
				
					ArrayList<String> objYnList = (ArrayList<String>) P_OBJ_YN;
					ArrayList<String> delAtList = (ArrayList<String>) P_DEL_AT;
					ArrayList<String> estmObjSeqList = (ArrayList<String>) P_ESTM_OBJ_SEQ;
					ArrayList<String> vendRegNoList = (ArrayList<String>) P_VEND_REG_NO;
					ArrayList<String> bizrnoList = (ArrayList<String>) P_BIZRNO;
					ArrayList<String> vendNmList = (ArrayList<String>) P_VEND_NM;
					ArrayList<String> itemNoList = (ArrayList<String>) P_ITEM_NO;
					ArrayList<String> itemNmList = (ArrayList<String>) P_ITEM_NM;
					ArrayList<String> estmCmtmNoList = (ArrayList<String>) P_ESTM_CMTM_NO;
					ArrayList<String> estmCmtmNmList = (ArrayList<String>) P_ESTM_CMTM_NM;
					ArrayList<String> estmObjPeNoList = (ArrayList<String>) P_ESTM_OBJ_PE_NO;
					ArrayList<String> estmObjPeNmList = (ArrayList<String>) P_ESTM_OBJ_PE_NM;
					ArrayList<String> emplNoList = (ArrayList<String>) P_EMPL_NO;
					ArrayList<String> rsdnNo_1_List = (ArrayList<String>) P_RSDN_NO_1;
					ArrayList<String> rsdnNo_2_List = (ArrayList<String>) P_RSDN_NO_2;
					ArrayList<String> telNoList = (ArrayList<String>) P_TEL_NO;
					ArrayList<String> emalList = (ArrayList<String>) P_EMAL;
					
					System.err.println("@@@ objYnList.size() ==> " + objYnList.size());
					for(int i =0; i < objYnList.size(); i++){
						
						String estmObjSeq = estmObjSeqList.get(i);
						
						if(objYnList != null){
							if(vendRegNoList != null) parameterMap.put("P_VEND_REG_NO", vendRegNoList.get(i));
							if(bizrnoList != null) parameterMap.put("P_BIZRNO", bizrnoList.get(i));
							if(vendNmList != null) parameterMap.put("P_VEND_NM", vendNmList.get(i));
							if(itemNoList != null) parameterMap.put("P_ITEM_NO", itemNoList.get(i));
							if(itemNmList != null) parameterMap.put("P_ITEM_NM", itemNmList.get(i));
							if(estmCmtmNoList != null) parameterMap.put("P_ESTM_CMTM_NO", estmCmtmNoList.get(i));
							if(estmCmtmNmList != null) parameterMap.put("P_ESTM_CMTM_NM", estmCmtmNmList.get(i));
							if(estmObjPeNoList != null) parameterMap.put("P_ESTM_OBJ_PE_NO", estmObjPeNoList.get(i));
							if(estmObjPeNmList != null) parameterMap.put("P_ESTM_OBJ_PE_NM", estmObjPeNmList.get(i));
							if(emplNoList != null) parameterMap.put("P_EMPL_NO", emplNoList.get(i));

							if(rsdnNo_1_List != null && rsdnNo_2_List != null)
								parameterMap.put("P_RSDN_NO", rsdnNo_1_List.get(i) + "" + rsdnNo_2_List.get(i));						
							
							if(telNoList != null) parameterMap.put("P_TEL_NO", telNoList.get(i));
							if(emalList != null) parameterMap.put("P_EMAL", emalList.get(i));
							
							System.err.println("@@@ estmObjSeq ==> " + estmObjSeq);
							if(estmObjSeq.equals("")){
								// 평가대상 등록
								System.err.println("@@@ T_ESTM_OBJ 등록 @@@");
								iproEstmCmtmProgDao.insertEstmObj(parameterMap);   // T_ESTM_OBJ INSERT						
							}else{
								parameterMap.put("P_ESTM_OBJ_SEQ", estmObjSeqList.get(i));
								parameterMap.put("P_DEL_AT", delAtList.get(i));
								
								System.err.println("@@@ T_ESTM_OBJ 삭제 @@@");
								iproEstmCmtmProgDao.updateEstmObjDelAt(parameterMap);   // T_ESTM_OBJ INSERT
							}
						}
					}
				}
//				else if(P_OBJ_YN instanceof String[]){
//					
//					String[] objYnStr = (String[]) P_OBJ_YN;
//					String[] vendRegNoStr = (String[]) P_VEND_REG_NO;
//					String[] bizrnoStr = (String[]) P_BIZRNO;
//					String[] vendNmStr = (String[]) P_VEND_NM;
//					String[] itemNoStr = (String[]) P_ITEM_NO;
//					String[] itemNmStr = (String[]) P_ITEM_NM;
//					String[] estmCmtmNoStr = (String[]) P_ESTM_CMTM_NO;
//					String[] estmCmtmNmStr = (String[]) P_ESTM_CMTM_NM;
//					String[] estmObjPeNoStr = (String[]) P_ESTM_OBJ_PE_NO;
//					String[] estmObjPeNmStr = (String[]) P_ESTM_OBJ_PE_NM;
//					String[] emplNoStr = (String[]) P_EMPL_NO;
//					String[] rsdnNoStr = (String[]) P_RSDN_NO;
//					String[] telNoStr = (String[]) P_TEL_NO;
//					String[] emalStr = (String[]) P_EMAL;
//					String[] fileGrpNoStr = (String[]) P_FILE_GRP_NO;
//					
//					System.err.println("@@@ objYnStr.length ==> " + objYnStr.length);
//					for (int i = 0; i < objYnStr.length; i++) {
//						
//						parameterMap.put("P_VEND_REG_NO", vendRegNoStr[i]);
//						parameterMap.put("P_BIZRNO", bizrnoStr[i]);
//						parameterMap.put("P_VEND_NM", vendNmStr[i]);
//						parameterMap.put("P_ITEM_NO", itemNoStr[i]);
//						parameterMap.put("P_ITEM_NM", itemNmStr[i]);
//						parameterMap.put("P_ESTM_CMTM_NO", estmCmtmNoStr[i]);
//						parameterMap.put("P_ESTM_CMTM_NM", estmCmtmNmStr[i]);
//						parameterMap.put("P_ESTM_OBJ_PE_NO", estmObjPeNoStr[i]);
//						parameterMap.put("P_ESTM_OBJ_PE_NM", estmObjPeNmStr[i]);
//						parameterMap.put("P_EMPL_NO", emplNoStr[i]);
//						parameterMap.put("P_RSDN_NO", rsdnNoStr[i]);
//						parameterMap.put("P_TEL_NO", telNoStr[i]);
//						parameterMap.put("P_EMAL", emalStr[i]);
//						parameterMap.put("P_FILE_GRP_NO", fileGrpNoStr[i]);
//						
//						// 평가대상 등록
//						iproEstmProgDao.insertEstmObj(parameterMap);   // T_ESTM_OBJ INSERT
//					}
//				}
			}
		}
		
		// 평가진행이력 등록
		parameterMap.put("P_RMK", "평가대상 저장");
		iproEstmCmtmProgDao.insertEstmProgHist(parameterMap);   // T_ESTM_PROG_HIST INSERT
		
	}

	@Override
	public void estmCntcObjList(final FwkParameterMap parameterMap) {
		
		// 평가대상 삭제
		iproEstmCmtmProgDao.deleteEstmObj(parameterMap);   // T_ESTM_OBJ DELETE

		// 평가공고참여정보_인터페이스 조회 -> T_ESTM_OBJ INSERT
		iproEstmCmtmProgDao.insertEstmCntcObj(parameterMap);   // V_ESTM_ANNC_TPI_INT INSERT
		
		// 평가진행이력 등록
		parameterMap.put("P_RMK", "평가대상불러오기");
		iproEstmCmtmProgDao.insertEstmProgHist(parameterMap);   // T_ESTM_PROG_HIST INSERT
	}

	@Override
	public FwkTransferObject getEstmSpheSecd(final FwkParameterMap parameterMap) throws Exception {
		
		FwkTransferObject transferObject = new FwkTransferObject();
		
		List<FwkDataEntity> estmSpheSecdList = iproEstmCmtmProgDao.selectEstmSpheSecdList(parameterMap);   // T_ESTM_SPHE_MST
		
        List<Map<String, Object>> temp = new ArrayList<>();
        for(int i=0; i<estmSpheSecdList.size(); i++) {
        	Map<String, Object> secdMap = new HashMap<>();	//NOPMD
        	secdMap.put("ESTM_SPHE_SECD", estmSpheSecdList.get(i).get("ESTM_SPHE_SECD"));
        	if (estmSpheSecdList.get(i).get("ESTM_SPHE_SENM") != null) {
        		secdMap.put("ESTM_SPHE_SENM", FwkStringUtil.fmIso2Euc((String) estmSpheSecdList.get(i).get("ESTM_SPHE_SENM")));
        	}
        	temp.add(secdMap);
        }
        
        transferObject.put("listEstmSpheSecd", temp);
        
		return transferObject;
	}


}