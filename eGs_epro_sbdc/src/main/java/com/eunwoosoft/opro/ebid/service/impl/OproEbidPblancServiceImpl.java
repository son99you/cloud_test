package com.eunwoosoft.opro.ebid.service.impl; 

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.eunwoosoft.comm.code.dao.ComCmcdDetailCdDao;
import com.eunwoosoft.comm.file.dao.ComAtmaAtchFileDao;
import com.eunwoosoft.comm.util.CmmnUtil;
import com.eunwoosoft.frwk.bul.AbstractFwkService;
import com.eunwoosoft.frwk.fol.dto.FwkDataEntity;
import com.eunwoosoft.frwk.fol.dto.FwkTransferObject;
import com.eunwoosoft.frwk.fol.dto.simple.FwkSimpleDataEntity;
import com.eunwoosoft.frwk.fol.util.FwkDateUtil;
import com.eunwoosoft.frwk.fol.util.FwkFormatUtil;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;
import com.eunwoosoft.opro.ebid.dao.OproEbidMyPartcptReqstSttusDao;
import com.eunwoosoft.opro.ebid.dao.OproEbidMyPblancDao;
import com.eunwoosoft.opro.ebid.dao.OproEbidPblancDao;
import com.eunwoosoft.opro.ebid.service.OproEbidMyPartcptReqstSttusService;
import com.eunwoosoft.opro.ebid.service.OproEbidPblancService;
import com.eunwoosoft.opro.user.dao.OproUserVendInfoDao;


/**
 * 입찰공고 서비스 구현 클래스
 * 
 * @author : 은우소프트 하성윤
 * @date : 2015. 02. 16.
 * @version : 1.0
 */
@Service("oproEbidPblancService")
public class OproEbidPblancServiceImpl extends AbstractFwkService implements OproEbidPblancService {
	
	@Resource(name="oproEbidPblancDao")
	private OproEbidPblancDao oproEbidPblancDao;
	
	@Resource(name="oproEbidMyPblancDao")
	private OproEbidMyPblancDao oproEbidMyPblancDao;
	
	@Resource(name="oproEbidMyPartcptReqstSttusDao")
	private OproEbidMyPartcptReqstSttusDao oproEbidMyPartcptReqstSttusDao;
	
	@Resource(name="oproUserVendInfoDao")
	private OproUserVendInfoDao oproUserVendInfoDao;
	
	@Resource(name="comCmcdDetailCdDao")
	private ComCmcdDetailCdDao comCmcdDetailCdDao;
	
	@Resource(name="comAtmaAtchFileDao")
    private ComAtmaAtchFileDao comAtmaAtchFileDao;

	/**
	 * <pre>
	 * 1. 개요 : 진행중인 입찰공고 목록조회_페이징 
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : inProgrsBidPblancListInqireWithPgng
	 * @date : 2015. 02. 16.
	 * @author : 은우소프트 하성윤
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 02. 16.		은우소프트 하성윤				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 */
	@Override
	public FwkTransferObject inProgrsBidPblancListInqireWithPgng(final FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();
		if(parameterMap.get("loginResult") != null){
			@SuppressWarnings("unchecked")
			FwkDataEntity user = new FwkSimpleDataEntity((Map<String,Object>)parameterMap.get("loginResult"));
			parameterMap.put("P_VEND_REG_NO", user.get("USR_ID")); // 세션 업체등록번호
		}
		
		if(parameterMap.get("P_ANNC_STDT_S") != null && !"".equals(parameterMap.get("P_ANNC_STDT_S"))){ // 공고일자 시작
			parameterMap.put("P_ANNC_STDT_S", FwkFormatUtil.formatDate(parameterMap.get("P_ANNC_STDT_S").toString(), "yyyy-MM-dd", "yyyyMMdd"));
		}
		if(parameterMap.get("P_ANNC_ENDT_S") != null && !"".equals(parameterMap.get("P_ANNC_ENDT_S"))){ // 공고일자 종료
			parameterMap.put("P_ANNC_ENDT_S", FwkFormatUtil.formatDate(parameterMap.get("P_ANNC_ENDT_S").toString(), "yyyy-MM-dd", "yyyyMMdd"));
		}
		
		trans.put(OproEbidPblancService.IN_PROGRS_BID_PBLANC_LIST, oproEbidPblancDao.selectInProgrsBidPblancListWithPgng(parameterMap));
		trans.put(OproEbidPblancService.IN_PROGRS_BID_PBLANC_LIST_TOTCNT, oproEbidPblancDao.selectInProgrsBidPblancListTotcnt(parameterMap));
		
		return trans;
	}

	/**
	 * <pre>
	 * 1. 개요 : 진행중인 입찰공고 상세 조회
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : inProgrsBidPblancDetailInqire
	 * @date : 2015. 02. 16.
	 * @author : 은우소프트 하성윤
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 02. 16.		은우소프트 하성윤				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 * @throws SDBException 
	 */
	@Override
	public FwkTransferObject inProgrsBidPblancDetailInqire(final FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();

		if(parameterMap.get("loginResult") != null){
			@SuppressWarnings("unchecked")
			FwkDataEntity user = new FwkSimpleDataEntity((Map<String,Object>)parameterMap.get("loginResult"));
			parameterMap.put("P_VEND_REG_NO", user.get("USR_ID")); // 세션 업체등록번호
		}
		
		FwkDataEntity dataEntity = oproEbidPblancDao.selectInProgrsBidPblancDetail(parameterMap);
		
		// 진행중인 공고 상세
		trans.put(OproEbidPblancService.IN_PROGRS_BID_PBLANC_DETAIL, dataEntity);
		
		// 입찰품목 목록
		trans.put(OproEbidPblancService.BID_PRDLS_LIST, oproEbidPblancDao.selectBidPrdlstList(parameterMap));
		
		// 입찰첨부문서 조회
		parameterMap.put("P_BID_FSCD", "DI01");
		FwkDataEntity fileDataEntity = oproEbidPblancDao.selectBidAtchDoc(parameterMap);
		trans.put(OproEbidPblancService.BID_ATCH_DOC, fileDataEntity);
		
		if(fileDataEntity != null){
			dataEntity.put("FILE_GRP_NO", fileDataEntity.getString("FILE_GRP_NO"));
			trans.put(OproEbidPblancService.IN_PROGRS_BID_PBLANC_DETAIL, dataEntity);	
		}
		
		// 공고알림정보 목록   NOT01 정정공고, NOT02 취소공고, NOT03 연기공고, NOT04 유찰
		parameterMap.put("P_BID_PSCD", "PF30");
		trans.put(OproEbidPblancService.PBLANC_NTCN_INFO_LIST, oproEbidPblancDao.selectPblancNtcnInfoList(parameterMap));

		if(!"".equals(parameterMap.getString("P_VEND_REG_NO"))){
			// 입찰 설명회 참석 정보
			trans.put(OproEbidPblancService.BID_DC_PEO_ATNDNC_INFO, oproEbidPblancDao.selectBidDcPeoAtndncInfoinqire(parameterMap));
			
			// 참가신청현황 조회 - 투찰 
			parameterMap.put("P_BID_TPI_SECD", "OP03");  // 입찰참여구분코드 (OP01 입찰참여신청, OP02 입찰포기신청, OP03 투찰, OP04 자가점수입력)
			trans.put(OproEbidPblancService.BDDPR_STTUS, oproEbidPblancDao.selectPartcptReqstSttusinqire(parameterMap));
			
			// 포기신청유무
			trans.put(OproEbidMyPartcptReqstSttusService.BID_PARTCPT_ABANDN_REQSTDOC, oproEbidMyPartcptReqstSttusDao.selectBidPartcptAbandnReqstdocDatile(parameterMap));
			
			// 공동수급업체 유무
			trans.put(OproEbidPblancService.COPERTN_SDEN_LIST, oproEbidPblancDao.selectCopertnSdenList(parameterMap));
			
			trans.put(OproEbidPblancService.BID_PBLANC_OPINION_INFO_LIST, oproEbidPblancDao.selectBidPblancOpinionInfoList(parameterMap));
			
			
			if(!"".equals(parameterMap.getString("P_VEND_REG_NO"))){
				parameterMap.put("P_BID_VEND_PSCD", "OP01");
				
				FwkDataEntity vendDataEntity = oproEbidPblancDao.selectEntrpsInfoInqire(parameterMap);
				if(vendDataEntity != null){
					if("".equals(vendDataEntity.getString("BID_VEND_PSCD"))){
						oproEbidMyPartcptReqstSttusDao.updateEntrpsPartcptnPrstCdUpdt(parameterMap);
						/**** 진행이력 등록 ****/
						oproEbidPblancDao.insertEntrpsProgrsHistRegist(parameterMap);
					}
				}else{
					oproEbidMyPblancDao.insertEntrpsPartcptnInfoRegist(parameterMap);
					/**** 진행이력 등록 ****/
					oproEbidPblancDao.insertEntrpsProgrsHistRegist(parameterMap);
				}
			}
			
		}
		return trans;
	}
	
	/**
	 * <pre>
	 * 1. 개요 : 관심입찰 등록
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : intrstBidRegist
	 * @date : 2015. 02. 23.
	 * @author : 은우소프트 하성윤
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 02. 23.		은우소프트 하성윤				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 */
	@Override
	public FwkTransferObject intrstBidRegist(final FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();
		
		Object intrstBidNoObject =  parameterMap.get("intrstBidNo");
		if(intrstBidNoObject != null){
			if(intrstBidNoObject instanceof String){
				String[] intrstBidNo =intrstBidNoObject.toString().split("#");
				String pblancNo = intrstBidNo[0];
				String pblancOdr = intrstBidNo[1];
				String roundNo = intrstBidNo[2];
				
				parameterMap.put("P_ANNC_NO", pblancNo);
				parameterMap.put("P_ANNC_NGR", pblancOdr);
				parameterMap.put("P_ROUND_NO", roundNo);
				
				FwkDataEntity intrstBid =  oproEbidPblancDao.selectIntrstBidInqire(parameterMap);
				
				if(intrstBid == null){
					oproEbidPblancDao.insertIntrstBidRegist(parameterMap);
				}
			}else if(intrstBidNoObject instanceof ArrayList){
				ArrayList instrstBidList = (ArrayList) parameterMap.get("intrstBidNo");
				for(int i=0; i<instrstBidList.size(); i++){
					String[] intrstBidNo = instrstBidList.get(i).toString().split("#");
					String pblancNo = intrstBidNo[0];
					String pblancOdr = intrstBidNo[1];
					String roundNo = intrstBidNo[2];
					
					parameterMap.put("P_ANNC_NO", pblancNo);
					parameterMap.put("P_ANNC_NGR", pblancOdr);
					parameterMap.put("P_ROUND_NO", roundNo);
					
					FwkDataEntity intrstBid =  oproEbidPblancDao.selectIntrstBidInqire(parameterMap);
					
					if(intrstBid == null){
						oproEbidPblancDao.insertIntrstBidRegist(parameterMap);
					}
				}
			}else if(intrstBidNoObject instanceof String[]){
				String[] instrstBidList = (String[]) parameterMap.get("intrstBidNo");
				for(int i=0; i<instrstBidList.length; i++){
					String[] intrstBidNo = instrstBidList[i].toString().split("#");
					String pblancNo = intrstBidNo[0];
					String pblancOdr = intrstBidNo[1];
					String roundNo = intrstBidNo[2];
					
					parameterMap.put("P_ANNC_NO", pblancNo);
					parameterMap.put("P_ANNC_NGR", pblancOdr);
					parameterMap.put("P_ROUND_NO", roundNo);
					
					FwkDataEntity intrstBid =  oproEbidPblancDao.selectIntrstBidInqire(parameterMap);
					
					if(intrstBid == null){
						oproEbidPblancDao.insertIntrstBidRegist(parameterMap);
					}
				}
			}
		}
		
		
		return trans;
	}
	
	/**
	 * 
	 * <pre>
	 * 1. 개요 : 관심입찰 삭제 
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : intrstBidDelete
	 * @date : 2015. 9. 4.
	 * @author : 은우소프트 손연우
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 9. 4.		은우소프트 손연우				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @see OproEbidPblancService.oep.elbi.service.OepElbiBidPblancService#intrstBidDelete(oda.fwk.prl.request.FwkParameterMap)
	 * @param parameterMap
	 * @return
	 */
	@Override
	public FwkTransferObject intrstBidDelete(final FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();
		
		Object intrstBidNoObject =  parameterMap.get("intrstBidNo");
		if(intrstBidNoObject != null){
			if(intrstBidNoObject instanceof String){
				String[] intrstBidNo =intrstBidNoObject.toString().split("#");
				String pblancNo = intrstBidNo[0];
				String pblancOdr = intrstBidNo[1];
				String roundNo = intrstBidNo[2];
				
				parameterMap.put("P_ANNC_NO", pblancNo);
				parameterMap.put("P_ANNC_NGR", pblancOdr);
				parameterMap.put("P_ROUND_NO", roundNo);
				
				oproEbidPblancDao.deleteIntrstBid(parameterMap);
			}else if(intrstBidNoObject instanceof ArrayList){
				ArrayList instrstBidList = (ArrayList) parameterMap.get("intrstBidNo");
				for(int i=0; i<instrstBidList.size(); i++){
					String[] intrstBidNo = instrstBidList.get(i).toString().split("#");
					String pblancNo = intrstBidNo[0];
					String pblancOdr = intrstBidNo[1];
					String roundNo = intrstBidNo[2];
					
					parameterMap.put("P_ANNC_NO", pblancNo);
					parameterMap.put("P_ANNC_NGR", pblancOdr);
					parameterMap.put("P_ROUND_NO", roundNo);
					
					oproEbidPblancDao.deleteIntrstBid(parameterMap);
				}
			}else if(intrstBidNoObject instanceof String[]){
				String[] instrstBidList = (String[]) parameterMap.get("intrstBidNo");
				for(int i=0; i<instrstBidList.length; i++){
					String[] intrstBidNo = instrstBidList[i].toString().split("#");
					String pblancNo = intrstBidNo[0];
					String pblancOdr = intrstBidNo[1];
					String roundNo = intrstBidNo[2];
					
					parameterMap.put("P_ANNC_NO", pblancNo);
					parameterMap.put("P_ANNC_NGR", pblancOdr);
					parameterMap.put("P_ROUND_NO", roundNo);
					
					oproEbidPblancDao.deleteIntrstBid(parameterMap);
				}
			}
		}
		
		
		return trans;
	}
	

	/**
	 * <pre>
	 * 1. 개요 : 지문인식 모의공고 상세 조회
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : fngprtRecogImtPblancDetailInqire
	 * @date : 2015. 02. 23.
	 * @author : 은우소프트 하성윤
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 02. 23.		은우소프트 하성윤				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 */
	@Override
	public FwkTransferObject fngprtRecogImtPblancDetailInqire(final FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();
		
		// 진행중인 공고 상세
		trans.put(OproEbidPblancService.IN_PROGRS_BID_PBLANC_DETAIL, 
				oproEbidPblancDao.selectInProgrsBidPblancDetail(parameterMap));
		
		// 입찰품목 목록
		trans.put(OproEbidPblancService.BID_PRDLS_LIST, 
				oproEbidPblancDao.selectBidPrdlstList(parameterMap));
		
		// 공고알림정보 목록
		parameterMap.put("P_BID_CHANGE_NTCN_CD", "NOT01");
		trans.put(OproEbidPblancService.PBLANC_NTCN_INFO_LIST, 
				oproEbidPblancDao.selectPblancNtcnInfoList(parameterMap));
		
		// 입찰공고의견정보 목록
		trans.put(OproEbidPblancService.BID_PBLANC_OPINION_INFO_LIST, 
				oproEbidPblancDao.selectBidPblancOpinionInfoList(parameterMap));
		
		// 입찰공고의견정보 목록 총건수
		trans.put(OproEbidPblancService.BID_PBLANC_OPINION_INFO_LIST_TOTCNT, 
				oproEbidPblancDao.selectBidPblancOpinionInfoListTotcnt(parameterMap));
		return trans;
	}
	
	/**
	 * <pre>
	 * 1. 개요 : 입찰 참가 신청서 작성 폼
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : bidPartcptReqstdocWritngForm
	 * @date : 2015. 04. 09.
	 * @author : 은우소프트 하성윤
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 04. 09.		은우소프트 하성윤				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 */
	@Override
	public FwkTransferObject bidPartcptReqstdocWritngForm(final FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();
		
		@SuppressWarnings("unchecked")
		FwkDataEntity user = new FwkSimpleDataEntity((Map<String,Object>)parameterMap.get("loginResult"));
		
		//FwkDataEntity user = (FwkDataEntity) parameterMap.get("loginResult");
		
		// 진행중인 공고 상세
		trans.put(OproEbidPblancService.IN_PROGRS_BID_PBLANC_DETAIL, 
				oproEbidPblancDao.selectInProgrsBidPblancDetail(parameterMap));
		
		// 업체 정보 조회 
		parameterMap.put("P_VEND_REG_NO", user.get("USR_ID")); // 세션 업체등록번호
		trans.put(OproEbidPblancService.ENTRPS_INFO_INQIRE, 
				oproEbidMyPblancDao.selectEntrpsinfoinqire(parameterMap));
		
		parameterMap.put("P_CD_ID", "22035");
		trans.put(OproEbidPblancService.CMMN_CD_LIST, 
				comCmcdDetailCdDao.selectCdValueListByCdId(parameterMap));
		
		
		return trans;
	}
	
	/**
	 * <pre>
	 * 1. 개요 : 입찰 참가 신청 등록
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : bidPartcptReqstRegist
	 * @date : 2015. 04. 09.
	 * @author : 은우소프트 하성윤
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 04. 09.		은우소프트 하성윤				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 * @throws SDBException 
	 */
	@Override
	public FwkTransferObject bidPartcptReqstRegist(final FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();
		
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest)parameterMap.get("multipartRequest");
		String contextPath = "bid";
		FwkParameterMap fileParameterMap =  CmmnUtil.fileMultiUpload(multipartRequest,"P_CARE_ESSNTL_FILE", contextPath);
		
		@SuppressWarnings("unchecked")
		FwkDataEntity user = new FwkSimpleDataEntity((Map<String,Object>)parameterMap.get("loginResult"));
		
		// 참가신청시간 체크
		FwkDataEntity bidInfo = oproEbidPblancDao.selectInProgrsBidPblancDetail(parameterMap);
		Long pareEndDt = bidInfo.getLong("PARE_END_DT");
		//Long bipaPresentnEndDt = bidInfo.getLong("BIPA_PRESENTN_END_DT");
		Long nowDate = Long.parseLong( FwkDateUtil.getCurrentDateTimeAsString());
		
		if(nowDate > pareEndDt){
			trans.put("msg", "입찰참가신청시간이 종료되었습니다.");
			trans.put("resultCode", "ERR");
			return trans;
		}
		
		parameterMap.put("P_VEND_REG_NO", user.get("USR_ID")); // 세션 업체등록번호
		
		String P_TELNO_ENC = ""; 
		String P_MVMN_TELNO_ENC = "";
		String P_EMAIL_ADRES_ENC = "";
		
		/************ DB암호화 시작 *****************/
//		P_TELNO_ENC = SDBCryptoUtil.encryptEx("DBSEC.TB_KEY.ARIA", parameterMap.getString("P_TELNO"));
//		P_MVMN_TELNO_ENC = SDBCryptoUtil.encryptEx("DBSEC.TB_KEY.ARIA", parameterMap.getString("P_MVMN_TELNO"));
//		P_EMAIL_ADRES_ENC = SDBCryptoUtil.encryptEx("DBSEC.TB_KEY.ARIA", parameterMap.getString("P_EMAIL_ADRES"));
//		
//		parameterMap.put("P_TELNO", P_TELNO_ENC);
//		parameterMap.put("P_MVMN_TELNO", P_MVMN_TELNO_ENC);
//		parameterMap.put("P_EMAIL_ADRES", P_EMAIL_ADRES_ENC);
		/************ DB암호화 종료 *****************/
		
		// 업체 참여 정보 등록
		parameterMap.put("P_ENTRPS_PRST_CD", "OP02"); // 업체진행상태코드   OP01 공고열람, OP02 입찰참가신청, OP03 입찰포기신청, OP04 가격서제출, OP05 기술평가중, OP06 개찰, OP07 적격점수입력, OP08 협상통보수신, OP09 입찰종료(낙찰), OP10 입찰종료(미선정), OP11 입찰종료(유찰)
		oproEbidMyPblancDao.insertEntrpsPartcptnInfoRegist(parameterMap);

		// 업체 참여 상세 등록
		parameterMap.put("P_BID_PARTCPTN_SE_CD", "OP01"); // 입찰참여구분코드 (OP01 입찰참여신청, OP02 입찰포기신청, OP03 투찰, OP04 자가점수입력)
		parameterMap.put("P_PARTCPTN_DT", FwkDateUtil.getCurrentDateTimeAsString()); // 참여일시
		parameterMap.put("P_REGIST_DT", FwkDateUtil.getCurrentDateTimeAsString());  // 등록일시
		oproEbidMyPblancDao.insertEntrpsPartcptnDetailRegist(parameterMap);
		
		// 입찰보증정보 등록
		for(int i=0; i<parameterMap.getInt("rowCount"); i++){ // 입찰정보등록 기존 5개 고정에서 추가 개수만큼으로 변경.
			
			if(parameterMap.get("P_BIGU_FILE_GRP_NO"+i) != null && !"".equals(parameterMap.get("P_BIGU_FILE_GRP_NO"+i))){
				FwkParameterMap pmap = new FwkParameterMap();
				
				pmap.put("P_FILE_CPCTY",  parameterMap.get("P_FILE_CPCTY"+i));
		        pmap.put("P_STRE_FILE_NM",  parameterMap.get("P_STRE_FILE_NM"+i));
		        pmap.put("P_ATCHMNFL_NM",   parameterMap.get("P_ATCHMNFL_NM"+i));	
		        pmap.put("P_ATCHMNFL_COURS_NM",  parameterMap.get("P_ATCHMNFL_COURS_NM"+i));
		        pmap.put("P_ATCHMNFL_EXTSN_NM",  parameterMap.get("P_ATCHMNFL_EXTSN_NM"+i));
				
		        List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
				list.add(pmap);
				
				parameterMap.put("atchFileGroupNo", parameterMap.getString("P_BIGU_FILE_GRP_NO"+i));
				parameterMap.put("fileList", list);

				comAtmaAtchFileDao.insertAtchFileGroupRegist(parameterMap);
				comAtmaAtchFileDao.insertAtchFileRegist(parameterMap);
			}
			
			parameterMap.put("P_BID_GTN_PAY_STLE_CD",parameterMap.get("P_BID_GTN_PAY_STLE_CD"+i));
			parameterMap.put("P_BID_GRNTY_NO",parameterMap.get("P_BID_GRNTY_NO"+i));
			parameterMap.put("P_BID_GTN_AM",parameterMap.get("P_BID_GTN_AM"+i));
			parameterMap.put("P_ISSU_INSTT_NM",parameterMap.get("P_ISSU_INSTT_NM"+i));
			String strBidAssurStDe = ""; 
			if(parameterMap.get("P_GRNTY_PD_BEGIN_DE"+i) != null && !"".equals(parameterMap.getString("P_GRNTY_PD_BEGIN_DE"+i))){
				strBidAssurStDe = FwkFormatUtil.formatDate(parameterMap.getString("P_GRNTY_PD_BEGIN_DE"+i), "yyyy-MM-dd", "yyyyMMdd");
			}
			parameterMap.put("P_GRNTY_PD_BEGIN_DE", strBidAssurStDe);
			String strBidAssurEneDe = ""; 
			if(parameterMap.get("P_GRNTY_PD_END_DE"+i) != null && !"".equals(parameterMap.getString("P_GRNTY_PD_END_DE"+i))){
				strBidAssurEneDe = FwkFormatUtil.formatDate(parameterMap.getString("P_GRNTY_PD_END_DE"+i), "yyyy-MM-dd", "yyyyMMdd");
			}
			parameterMap.put("P_GRNTY_PD_END_DE", strBidAssurEneDe);
			parameterMap.put("P_BIGU_FILE_GRP_NO",parameterMap.get("P_BIGU_FILE_GRP_NO"+i));

			if(parameterMap.get("P_BID_GTN_PAY_STLE_CD") != null && !"".equals(parameterMap.getString("P_BID_GTN_PAY_STLE_CD"))){
				oproEbidPblancDao.insertBidGrntyInfoRegist(parameterMap);
			}
		}
		
		// 진행이력 등록
		parameterMap.put("P_ENTRPS_PRST_CD", "OP01"); // 입찰참여구분코드 (OP01 입찰참여신청, OP02 입찰포기신청, OP03 투찰, OP04 자가점수입력)
		oproEbidPblancDao.insertEntrpsProgrsHistRegist(parameterMap);
		
		if(fileParameterMap != null){
			String atchFileGroupNo = fileParameterMap.getString("atchFileGroupNo");
			List<Map<String, Object>> list = (List<Map<String, Object>>)fileParameterMap.get("list");
				
			// 공통 파일정보 저장
			parameterMap.put("atchFileGroupNo", atchFileGroupNo);
			parameterMap.put("fileList", list);
			parameterMap.put("P_SYSDATE", FwkDateUtil.getCurrentDateTimeAsString());
			comAtmaAtchFileDao.insertAtchFileGroupRegist(parameterMap);
			comAtmaAtchFileDao.insertAtchFileRegist(parameterMap);
		
			parameterMap.put("P_FILE_GRP_NO", atchFileGroupNo);
			if(parameterMap.get("P_FILE_GRP_NO") != null && !"".equals(parameterMap.get("P_FILE_GRP_NO"))){
				// 첨부파일 저장
				parameterMap.put("P_ENTRPS_PRESENTN_DOC_SE_CD", "DO03");
				oproEbidPblancDao.insertBidPareFileRegist(parameterMap);
			}
		}
		
		return trans;
	}
	
	/**
	 * 
	 * <pre>
	 * 1. 개요 : 업체 제재 여부 확인 
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : entrpsPunshAtCnfirm
	 * @date : 2015. 9. 2.
	 * @author : 은우소프트 손연우
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 9. 2.		은우소프트 손연우				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @see OproEbidPblancService.oep.elbi.service.OepElbiBidPblancService#entrpsPunshAtCnfirm(oda.fwk.prl.request.FwkParameterMap)
	 * @param parameterMap
	 * @return
	 */
	
	@Override
	public FwkTransferObject entrpsPunshAtCnfirm(final FwkParameterMap parameterMap) {
		
		FwkTransferObject trans = new FwkTransferObject();
		FwkDataEntity entrpsPunshAtCnfirm = (FwkDataEntity) oproEbidMyPblancDao.selectEntrpsPunshAtCnfirm(parameterMap);
		
		/* 
		22251	140000	없음
		22251	140001	경고
		22251	140002	입찰참가정지
		22251	140003	영구퇴출
		22251	140004	정지
		22251	140005	부적격
		*/
		if(entrpsPunshAtCnfirm != null){
			
			if("140002".equals(entrpsPunshAtCnfirm.get("CCPY_PUNSH_STTUS_CD"))){
				trans.put("entrpsPunshAtCnfirm","false");
			}else{
				trans.put("entrpsPunshAtCnfirm","true");
			}
		}else {
			trans.put("entrpsPunshAtCnfirm","true");
		}
		
		return trans;
	}
	/**
	 * <pre>
	 * 1. 개요 : 공동수급협정서 작성 폼
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : copertnSpldmdTrtyOfeWritngForm
	 * @date : 2015. 04. 15.
	 * @author : 은우소프트 하성윤
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 04. 15.		은우소프트 하성윤				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 */
	@Override
	public FwkTransferObject copertnSpldmdTrtyOfeWritngForm(final FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();
		
		@SuppressWarnings("unchecked")
		FwkDataEntity user = new FwkSimpleDataEntity((Map<String,Object>)parameterMap.get("loginResult"));
		
		//FwkDataEntity user = (FwkDataEntity) parameterMap.get("loginResult");
		
		parameterMap.put("P_BIZRNO", user.get("USER_ID")); // 세션 사업자등록번호
		parameterMap.put("P_VEND_REG_NO",user.get("USR_ID"));

		// 진행중인 공고 상세
		trans.put(OproEbidPblancService.IN_PROGRS_BID_PBLANC_DETAIL, oproEbidPblancDao.selectInProgrsBidPblancDetail(parameterMap));
		
		// 업체 정보 조회(본사)
		trans.put(OproEbidPblancService.VEND_INFO, oproUserVendInfoDao.selectVendInfoDetail(parameterMap));
		
		// 업체 정보 조회(공동업체)
		trans.put(OproEbidPblancService.ENTRPS_INFO_INQIRE, oproEbidMyPblancDao.selectEntrpsinfoinqire(parameterMap));
		
		return trans;
	}

	/**
	 * <pre>
	 * 1. 개요 : 공동수급협정서 제출 등록
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : copertnSpldmdTrtyOfePresentn
	 * @date : 2015. 04. 15.
	 * @author : 은우소프트 하성윤
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 04. 15.		은우소프트 하성윤				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 */
	@Override
	public FwkTransferObject copertnSpldmdTrtyOfePresentn(final FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();
		
		@SuppressWarnings("unchecked")
		FwkDataEntity user = new FwkSimpleDataEntity((Map<String,Object>)parameterMap.get("loginResult"));
		
		//FwkDataEntity user = (FwkDataEntity) parameterMap.get("loginResult");
		
		parameterMap.put("P_VEND_REG_NO", user.get("USR_ID")); //세션 업체등록번호
		
		Object assoVendBizrno = parameterMap.get("P_ASSO_VEND_BIZRNO");
		Object assoVendNm = parameterMap.get("P_ASSO_VEND_NM");
		Object rprsNm = parameterMap.get("P_RPRS_NM");
		Object shrRt = parameterMap.get("P_SHR_RT");
		Object rmk = parameterMap.get("P_RMK");
		if(assoVendBizrno != null){
			if(assoVendBizrno instanceof String){
				// 공동수급업체 등록
				oproEbidPblancDao.insertCopertnSpldmdEntrpsRegist(parameterMap);				
			}else {
				if(assoVendBizrno instanceof String[]){
					String[] assoVendBizrnoList = (String[]) assoVendBizrno;
					String[] assoVendNmList = (String[]) assoVendNm;
					String[] rprsNmList = (String[]) rprsNm;
					String[] shrRtList = (String[]) shrRt;
					String[] rmkList = (String[]) rmk;
					for (int idx = 0; idx < assoVendBizrnoList.length; idx++) {
						parameterMap.put("P_ASSO_VEND_BIZRNO", assoVendBizrnoList[idx]);
						parameterMap.put("P_ASSO_VEND_NM", assoVendNmList[idx]);
						parameterMap.put("P_RPRS_NM", rprsNmList[idx]);
						parameterMap.put("P_SHR_RT", shrRtList[idx]);
						parameterMap.put("P_RMK", rmkList[idx]);
						parameterMap.put("P_ASSO_VEND_REG_NO", assoVendBizrnoList[idx]);

						// 공동수급업체 등록
						oproEbidPblancDao.insertCopertnSpldmdEntrpsRegist(parameterMap);
					}
				}else if(assoVendBizrno instanceof ArrayList){
					ArrayList assoVendBizrnoList = (ArrayList) assoVendBizrno;
					ArrayList assoVendNmList = (ArrayList) assoVendNm;
					ArrayList rprsNmList = (ArrayList) rprsNm;
					ArrayList shrRtList = (ArrayList) shrRt;
					ArrayList rmkList = (ArrayList) rmk;
					for (int idx = 0; idx < assoVendBizrnoList.size(); idx++) {
						parameterMap.put("P_ASSO_VEND_BIZRNO", assoVendBizrnoList.get(idx));
						parameterMap.put("P_ASSO_VEND_NM", assoVendNmList.get(idx));
						parameterMap.put("P_RPRS_NM", rprsNmList.get(idx));
						parameterMap.put("P_SHR_RT", shrRtList.get(idx));
						parameterMap.put("P_RMK", rmkList.get(idx));
						parameterMap.put("P_ASSO_VEND_REG_NO", assoVendBizrnoList.get(idx));

						// 공동수급업체 등록
						oproEbidPblancDao.insertCopertnSpldmdEntrpsRegist(parameterMap);
					}					
				}
			}
		}
		
		// 공동수급협정 일자 등록
		oproEbidPblancDao.updateCopertnSpldmdTrtyDeRegist(parameterMap);
		
		parameterMap.put("P_BID_TPI_SECD", "OP03"); // 입찰참여구분코드 (OP01 입찰참여신청, OP02 입찰포기신청, OP03 투찰, OP04 자가점수입력)
		if(parameterMap.get("P_FILE_GRP_NO") != null && !"".equals(parameterMap.get("P_FILE_GRP_NO"))){
			// 첨부파일 저장
			parameterMap.put("P_BID_SBMT_FSCD", "DO07"); //제안서
			//oproEbidPblancDao.insertBidPareFileRegist(parameterMap);
		}
		
		trans.put("P_BID_GBN", parameterMap.get("P_BID_GBN"));
		
		return trans;
	}
	
	/**
	 * <pre>
	 * 1. 개요 : 공동수급협정서 제출 수정
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : copertnSpldmdTrtyOfeUpdt
	 * @date : 2015. 04. 15.
	 * @author : 은우소프트 전상훈
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 04. 15.		은우소프트 전상훈				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 */
	@Override
	public FwkTransferObject copertnSpldmdTrtyOfeUpdt(final FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();
		
		@SuppressWarnings("unchecked")
		FwkDataEntity user = new FwkSimpleDataEntity((Map<String,Object>)parameterMap.get("loginResult"));
		
		//FwkDataEntity user = (FwkDataEntity) parameterMap.get("loginResult");
		
		parameterMap.put("P_VEND_REG_NO", user.get("USR_ID")); //세션 업체등록번호
		
		// 공동수급업체 삭제
		oproEbidPblancDao.deleteCopertnSpldmdEntrps(parameterMap);
		
		Object sdenRegistNo = parameterMap.get("P_SDEN_REGIST_NO");
		if(sdenRegistNo != null){
			if(sdenRegistNo instanceof String){
				// 공동수급업체 등록
				oproEbidPblancDao.insertCopertnSpldmdEntrpsRegist(parameterMap);
			}else{
				
				/*List<String> sdenRegistNoList = new ArrayList<String>();
				sdenRegistNoList = (ArrayList<String>)parameterMap.get("P_SDEN_REGIST_NO");*/
				
				String[] sdenRegistNoList = (String[]) parameterMap.get("P_SDEN_REGIST_NO");
				/*List<String> sdenNmList = new ArrayList<String>();
				sdenNmList = (ArrayList<String>)parameterMap.get("P_SDEN_NM");;*/
				String[] sdenNmList = (String[]) parameterMap.get("P_SDEN_NM");
				/*List<String> sdenRprsntvNmList = new ArrayList<String>();
				sdenRprsntvNmList = (ArrayList<String>)parameterMap.get("P_SDEN_RPRSNTV_NM");;*/
				String[] sdenRprsntvNmList = (String[]) parameterMap.get("P_SDEN_RPRSNTV_NM");
				/*List<String> invstmntRateList = new ArrayList<String>();
				invstmntRateList = (ArrayList<String>)parameterMap.get("P_INVSTMNT_RATE");;*/
				String[] invstmntRateList = (String[]) parameterMap.get("P_INVSTMNT_RATE");
				/*for(int i =0; i < sdenRegistNoList.size(); i++){
					parameterMap.put("P_SDEN_REGIST_NO", sdenRegistNoList.get(i));
					parameterMap.put("P_SDEN_NM", sdenNmList.get(i));
					parameterMap.put("P_SDEN_RPRSNTV_NM", sdenRprsntvNmList.get(i));
					parameterMap.put("P_INVSTMNT_RATE", invstmntRateList.get(i));
					// 공동수급업체 등록
					oproEbidPblancDao.insertCopertnSpldmdEntrpsRegist(parameterMap);
				}*/
				for(int i = 0; i< sdenRegistNoList.length; i++) {
					parameterMap.put("P_SDEN_REGIST_NO", sdenRegistNoList[i]);
					parameterMap.put("P_SDEN_NM", sdenNmList[i]);
					parameterMap.put("P_SDEN_RPRSNTV_NM", sdenRprsntvNmList[i]);
					if(invstmntRateList != null){
						parameterMap.put("P_INVSTMNT_RATE", invstmntRateList[i]);
					}
					// 공동수급업체 등록
					oproEbidPblancDao.insertCopertnSpldmdEntrpsRegist(parameterMap);
				}
			}
		}
		
		// 공동수급협정 일자 등록
		oproEbidPblancDao.updateCopertnSpldmdTrtyDeRegist(parameterMap);
		
		return trans;
	}
	
	/**
	 * 
	 * <pre>
	 * 1. 개요 : 입찰참가신청서 조회
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : bidPartcptReqstdocInqire
	 * @date : 2015. 2. 26.
	 * @author : 은우소프트 손연우
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 2. 26.		은우소프트 손연우				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @see oda.iep.elbi.service.IproEbidPartcptSttusService#bidPartcptReqstdocInqire(oda.fwk.prl.request.FwkParameterMap)
	 * @param parameterMap
	 * @return
	 */
	
	@Override
	public FwkTransferObject bidPartcptReqstdocInqire(final FwkParameterMap parameterMap){
		FwkTransferObject trans = new FwkTransferObject();
		
		//FwkDataEntity user = (FwkDataEntity) parameterMap.get("loginResult");
		
//		parameterMap.put("P_VEND_REG_NO", user.get("USR_ID")); // 세션 업체등록번호
		
		FwkDataEntity dataEntity = (FwkDataEntity) oproEbidPblancDao.selectBidPartcptReqstdocInqire(parameterMap);
		if(dataEntity.size() > 0){
//			dataEntity.put("EMAIL_ADRES", SDBCryptoUtil.decrtypEx("DBSEC.TB_KEY.ARIA", (String) dataEntity.get("EMAIL_ADRES")));
//			dataEntity.put("TELNO", SDBCryptoUtil.decrtypEx("DBSEC.TB_KEY.ARIA", (String) dataEntity.get("TELNO")));
//			dataEntity.put("ENTRPS_DETAIL_ADRES", SDBCryptoUtil.decrtypEx("DBSEC.TB_KEY.ARIA", (String) dataEntity.get("ENTRPS_DETAIL_ADRES")));
			
			trans.put(OproEbidPblancService.BID_PARTCPT_REQSTDOC_INQIRE ,dataEntity);
		}
		
		
		// 입찰첨부문서 조회
		parameterMap.put("P_BID_FSCD", "DI01");
		trans.put(OproEbidPblancService.BID_ATCH_DOC, 
				oproEbidPblancDao.selectBidAtchDoc(parameterMap));
		
		// 업체첨부문서 조회
		parameterMap.put("P_ENTRPS_PRESENTN_DOC_SE_CD", "DO03"); // 산출내역서
		FwkDataEntity datEntityDO03 = oproEbidPblancDao.selectEntrpsAtchDoc(parameterMap);
		trans.put(OproEbidPblancService.ENTRPS_ATCH_DOC, datEntityDO03);
		
		if(datEntityDO03 != null){
			parameterMap.put("P_FILE_GRP_NO", datEntityDO03.getString("FILE_GRP_NO"));
			trans.put(OproEbidPblancService.BID_ATCH_DOC_LIST, 
			comAtmaAtchFileDao.selectAtchFileListInqireByAtchFileGroupNo(parameterMap));
		}
		
		return trans;
	}
	
	/**
	 * 
	 * <pre>
	 * 1. 개요 : 공동수급협정서 조회
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : copertnSpldmdTrtyOfeInqire
	 * @date : 2015. 4. 16.
	 * @author : 은우소프트 하성윤
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 04. 16.		은우소프트 하성윤				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 */
	
	@Override
	public FwkTransferObject copertnSpldmdTrtyOfeInqire(final FwkParameterMap parameterMap){
		FwkTransferObject trans = new FwkTransferObject();
		
		@SuppressWarnings("unchecked")
		FwkDataEntity user = new FwkSimpleDataEntity((Map<String,Object>)parameterMap.get("loginResult"));
		
		//FwkDataEntity user = (FwkDataEntity) parameterMap.get("loginResult");
		
		parameterMap.put("P_VEND_REG_NO", user.get("USR_ID")); // 세션 업체등록번호
		
		// 진행중인 공고 상세
		trans.put(OproEbidPblancService.IN_PROGRS_BID_PBLANC_DETAIL, 
				oproEbidPblancDao.selectInProgrsBidPblancDetail(parameterMap));
		
		// 업체 정보 조회 
		trans.put(OproEbidPblancService.ENTRPS_INFO_INQIRE, 
				oproEbidMyPblancDao.selectEntrpsinfoinqire(parameterMap));
		
		// 참여업체 마스터
		trans.put(OproEbidPblancService.ENTRPS_INFO_INQIRE+2, 
				oproEbidPblancDao.selectEntrpsInfoInqire(parameterMap));
		
		// 공동수급업체 목록 조회
		trans.put(OproEbidPblancService.COPERTN_SDEN_LIST, 
				oproEbidPblancDao.selectCopertnSdenList(parameterMap));
		
		parameterMap.put("P_BID_TPI_SECD", "OP03");
		parameterMap.put("P_BID_SBMT_FSCD", "DO07");
		FwkDataEntity datEntity = oproEbidPblancDao.selectEntrpsAtchDoc(parameterMap);
		trans.put(OproEbidPblancService.ENTRPS_ATCH_DOC, datEntity);
		
		if(datEntity != null){
			parameterMap.put("P_FILE_GRP_NO", datEntity.getString("FILE_GRP_NO"));
			trans.put(OproEbidPblancService.BID_ATCH_DOC_LIST, 
			comAtmaAtchFileDao.selectAtchFileListInqireByAtchFileGroupNo(parameterMap));
		}
		
		return trans;
	}
	
	/**
	 * 
	 * <pre>
	 * 1. 개요 : 조달청 연계 체크
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : g2bCeck
	 * @date : 2015. 4. 16.
	 * @author : 은우소프트 전상훈
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 04. 16.		은우소프트 전상훈				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 */
	
	@Override
	public String g2bCeck(final FwkParameterMap parameterMap){
		
		@SuppressWarnings("unchecked")
		FwkDataEntity user = new FwkSimpleDataEntity((Map<String,Object>)parameterMap.get("loginResult"));
		
		//FwkDataEntity user = (FwkDataEntity) parameterMap.get("loginResult");
		
		parameterMap.put("P_VEND_REG_NO", user.get("USR_ID")); // 세션 업체등록번호
		
		// 업체 입찰 기본정보 조회
				
		
		return oproEbidPblancDao.selectG2bAt(parameterMap);
	}

	@Override
	public FwkTransferObject bidNotiList(FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();
		trans.put(OproEbidPblancService.BID_PBLANC_OPINION_INFO_LIST, 
				oproEbidPblancDao.selectBidPblancOpinionInfoList(parameterMap));
		return trans;
	}

	@Override
	public FwkTransferObject bidNotiDetail(FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();
		trans.put(OproEbidPblancService.BID_PBLANC_OPINION_INFO_DETAIL, 
				oproEbidPblancDao.selectBidPblancOpinionInfoDetail(parameterMap));
		return trans;
	}
	
	@Override
	public void chckOk(FwkParameterMap parameterMap) {
		oproEbidPblancDao.insertEntrpsProgrsHistRegist(parameterMap);
	}
	
	/**
	 * <pre>
	 * 1. 개요 : 제안서 제출 폼 이동
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : prprcPresentnForm
	 * @date : 2019. 02. 21
	 * @author : 은우소프트 맹경열
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 02. 21.		은우소프트 맹경열				최초 작성 
	 *	-----------------------------------------------------------------------
	 * @param parameterMap
	 * @return
	 * @throws SDBException 
	 */
	@Override
	public FwkTransferObject prprcPresentnForm(final FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();
		
		@SuppressWarnings("unchecked")
		FwkDataEntity user = new FwkSimpleDataEntity((Map<String,Object>)parameterMap.get("loginResult"));
		
		parameterMap.put("P_VEND_REG_NO", user.get("USR_ID")); // 세션연동

		FwkDataEntity mypaRestDetail = (FwkDataEntity) oproEbidPblancDao.selectInProgrsBidPblancDetail(parameterMap);
		trans.put(OproEbidMyPartcptReqstSttusService.MY_PARTCPT_REQST_STTUS_DETAIL ,mypaRestDetail);
		
		//업체정보
		FwkDataEntity dataEntity = (FwkDataEntity) oproEbidMyPartcptReqstSttusDao.selectEntrpsinfoinqire(parameterMap);
		trans.put(OproEbidPblancService.BID_PARTCPTN_STTUS ,dataEntity);
		
		//제안서 제출정보
		parameterMap.put("P_BID_TPI_SECD", "OP05");
		trans.put(OproEbidMyPartcptReqstSttusService.BID_PARTCPT_TNDR_INFO, oproEbidMyPartcptReqstSttusDao.selectMyPartcptTndrInfo(parameterMap));
		
		//파일정보
		parameterMap.put("P_BID_TPI_SECD", "OP05");	//제안서
		parameterMap.put("P_BID_SBMT_FSCD", "DO02");	//제안서
		trans.put(OproEbidMyPartcptReqstSttusService.BID_DO01_FILE_INFO, oproEbidMyPartcptReqstSttusDao.selectMyPartcptVendFileInfo(parameterMap));		
		
		return trans;
	}	
	
}