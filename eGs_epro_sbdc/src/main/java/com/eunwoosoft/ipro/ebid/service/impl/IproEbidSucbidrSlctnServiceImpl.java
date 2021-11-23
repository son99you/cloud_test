package com.eunwoosoft.ipro.ebid.service.impl; 

import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.eunwoosoft.comm.file.dao.ComAtmaAtchFileDao;
import com.eunwoosoft.comm.itfc.dao.CommItfcDao;
import com.eunwoosoft.comm.itfc.service.CommItfcService;
import com.eunwoosoft.comm.util.CmmnUtil;
import com.eunwoosoft.frwk.bul.AbstractFwkService;
import com.eunwoosoft.frwk.fol.dto.FwkDataEntity;
import com.eunwoosoft.frwk.fol.dto.FwkTransferObject;
import com.eunwoosoft.frwk.fol.util.FwkDateUtil;
import com.eunwoosoft.frwk.fol.util.FwkFormatUtil;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;
import com.eunwoosoft.ipro.comm.dao.IproCommDefaultDao;
import com.eunwoosoft.ipro.ebid.dao.IproEbidOpengManageDao;
import com.eunwoosoft.ipro.ebid.dao.IproEbidPblancDao;
import com.eunwoosoft.ipro.ebid.dao.IproEbidPlanDao;
import com.eunwoosoft.ipro.ebid.dao.IproEbidResultDao;
import com.eunwoosoft.ipro.ebid.dao.IproEbidSucbidrSlctnDao;
import com.eunwoosoft.ipro.ebid.service.IproEbidPlanService;
import com.eunwoosoft.ipro.ebid.service.IproEbidSucbidrSlctnService;
import com.eunwoosoft.ipro.g2b.dao.IproG2bDao;
import com.eunwoosoft.ipro.noti.dao.IproNotiDao;

/**
 * 낙찰자선정 서비스 구현 클래스
 * 
 * @author : 은우소프트 손연우
 * @date : 2015. 02. 13.
 * @version : 1.0
 */
@Service("iproEbidSucbidrSlctnService")
public class IproEbidSucbidrSlctnServiceImpl extends AbstractFwkService implements IproEbidSucbidrSlctnService {
	private static final Logger LOG = LoggerFactory.getLogger(IproEbidPblancServiceImpl.class);
	
	@Resource(name="iproEbidSucbidrSlctnDao")
	private IproEbidSucbidrSlctnDao iproEbidSucbidrSlctnDao;
	
	@Resource(name="iproEbidPblancDao")
	private IproEbidPblancDao iproEbidPblancDao;
	
	@Resource(name="comAtmaAtchFileDao")
    private ComAtmaAtchFileDao comAtmaAtchFileDao;
	
	@Resource(name="iproEbidPlanDao")
	private IproEbidPlanDao iproEbidPlanDao;
	
	@Resource(name="iproCommDefaultDao")
	private IproCommDefaultDao iproCommDefaultDao;
	
	@Resource(name="iproNotiDao") 
	private IproNotiDao iproNotiDao;
	
	@Resource(name="iproEbidOpengManageDao") 
	private IproEbidOpengManageDao iproEbidOpengManageDao;
	
	@Resource(name="iproEbidResultDao") 
	private IproEbidResultDao iproEbidResultDao;
	
	@Resource(name="commItfcService") 
	private CommItfcService commItfcService;
	
	@Resource(name="commItfcDao") 
	private CommItfcDao commItfcDao;
	
	@Resource(name="iproG2bDao")
	private IproG2bDao iproG2bDao;
	
	/**
	 * <pre>
	 * 1. 개요 : 낙찰자선정 목록조회_페이징 
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : sucbidrSlctnListInqireWithPgng
	 * @date : 2015. 03. 12.
	 * @author : 은우소프트 손연우
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 03. 12.		은우소프트 손연우				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 */
	@Override
	public FwkTransferObject sucbidrSlctnListInqireWithPgng(final FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();
		if(parameterMap.get("P_OPNG_STDT_S") != null && !"".equals(parameterMap.get("P_OPNG_STDT_S"))){ // 공고일자 시작
			parameterMap.put("P_OPNG_STDT_S", FwkFormatUtil.formatDate(parameterMap.get("P_OPNG_STDT_S").toString(), "yyyy-MM-dd", "yyyyMMdd"));
		}
		if(parameterMap.get("P_OPNG_ENDT_S") != null && !"".equals(parameterMap.get("P_OPNG_ENDT_S"))){ // 공고일자 종료
			parameterMap.put("P_OPNG_ENDT_S", FwkFormatUtil.formatDate(parameterMap.get("P_OPNG_ENDT_S").toString(), "yyyy-MM-dd", "yyyyMMdd"));
		}
		
			trans.put(IproEbidSucbidrSlctnService.SUCBIDR_SLCTN_LIST, 
					iproEbidSucbidrSlctnDao.selectSucbidrSlctnListWithPgng(parameterMap));
			trans.put(IproEbidSucbidrSlctnService.SUCBIDR_SLCTN_LIST_TOTCNT, 
					iproEbidSucbidrSlctnDao.selectSucbidrSlctnListTotcnt(parameterMap));
		return trans;
	}

	/**
	 * 
	 * <pre>
	 * 1. 개요 : 낙찰자선정 상세 조회
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : sucbidrSlctnDetailInqire
	 * @date : 2015. 2. 13.
	 * @author : 은우소프트 손연우
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 2. 13.		은우소프트 손연우				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @see oda.iep.elbi.service.IepElbiBidDcPeoService#bidDcPeoDetailInqire(oda.fwk.prl.request.FwkParameterMap)
	 * @param parameterMap
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public FwkTransferObject sucbidrSlctnDetailInqire(final FwkParameterMap parameterMap) {
		
		FwkTransferObject trans = new FwkTransferObject();
		
		//입찰정보상세
		FwkDataEntity biMst = iproEbidSucbidrSlctnDao.selectBidInfoDetail(parameterMap);
		trans.put(IproEbidSucbidrSlctnService.BID_INFO_DETAIL, biMst);
		
		//개찰일 경우 복수예가 목록조회
		FwkDataEntity bidPlanEntity = (FwkDataEntity) trans.get("bidInfoDetail");
		if("180000".equals(bidPlanEntity.get("ESTPC_SECD"))){
			List<FwkDataEntity> mList =  iproEbidSucbidrSlctnDao.selectCompnoPrdprcList(parameterMap);
			
			ArrayList aList = new ArrayList();
			ArrayList bList = new ArrayList();
			ArrayList cList = new ArrayList();
			ArrayList dList = new ArrayList();
			for(int i=0; i<mList.size(); i++){
				FwkDataEntity cEntity = mList.get(i);
				aList.add(cEntity.get("PLR_ESTPC_NO"));
				bList.add(cEntity.get("PREP_PRCE_AMT"));
				cList.add(cEntity.get("DRLT_CNT"));
				dList.add(cEntity.get("ESTPC_RNK"));
			}
			trans.put("aList", aList);
			trans.put("bList", bList);
			trans.put("cList", cList);
			trans.put("dList", dList);
		}
		
		//투찰업체 목록
		List<FwkDataEntity> dList = iproEbidSucbidrSlctnDao.selectLwetScsbidPrearngerList(parameterMap);
		trans.put(IproEbidSucbidrSlctnService.SCSBID_PREARNGER_LIST, dList);
		//투찰업체 수
		trans.put(IproEbidSucbidrSlctnService.SCSBID_PREARNGER_LIST+"Size", dList.size());
		//평가목록
		trans.put(IproEbidSucbidrSlctnService.NT_ELGB_VEND_LIST, iproEbidSucbidrSlctnDao.selectNtElgbVendList(parameterMap));
	
		//제한적최저가 이상 업체 수
		// 2019-08-08 은잔디 수정 : 제한적최저가 이상 업체 수 제거 
		/*if(biMst.get("SBID_MTCD").equals("20") || biMst.get("SBID_MTCD").equals("31")) {
			int cnt = 0;
			for (FwkDataEntity fde : dList) {
				Double d1 = Double.parseDouble(fde.get("BDDPR_RT").toString());
				Double d2 = Double.parseDouble(fde.get("SBID_LWST_RT").toString());
				if(d1.compareTo(d2) > -1) {
					cnt++;
				}
			}
			trans.put(IproEbidSucbidrSlctnService.SCSBID_PREARNGER_APPR_LIST+"Size", cnt);
		}*/
		
		trans.put(IproEbidSucbidrSlctnService.SCSBID_PREARNGER_APPR_LIST+"Size", dList.size());
		
		//parameterMap.put("P_BID_APPR_TSCD", "F");
		//trans.put("biApprCntc",iproEbidPlanDao.selectBiApprCntc(parameterMap));
		
		return trans;
	}

	/**
	 * 
	 * <pre>
	 * 1. 개요 : 적격심사 평가등록 폼
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : properJdgmnEvlRegistForm
	 * @date : 2015. 3. 13.
	 * @author : 은우소프트 손연우
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 3. 13.		은우소프트 손연우				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @see oda.iep.elbi.service.IproEbidSucbidrSlctnService#properJdgmnEvlRegistForm(oda.fwk.prl.request.FwkParameterMap)
	 * @param parameterMap
	 * @return
	 */
	
	@Override
	public FwkTransferObject properJdgmnEvlRegistForm(final FwkParameterMap parameterMap){
		
		FwkTransferObject trans = new FwkTransferObject();
		
		FwkDataEntity properJdgmnEntrpsDetail = iproEbidSucbidrSlctnDao.selectProperJdgmnEntrpsDetail(parameterMap);
		
		trans.put(IproEbidSucbidrSlctnService.PROPER_JDGMN_ENTRPS_DETAIL
				, properJdgmnEntrpsDetail);
		
		parameterMap.put("P_BID_TPI_SECD", "OP04");
		parameterMap.put("P_BID_SBMT_FSCD", "DO06");
		FwkDataEntity fileDataEntity = iproEbidPblancDao.selectEntrpsAtchDoc(parameterMap);
		trans.put(IproEbidPlanService.BID_ATCH_DOC, fileDataEntity);
		
		if(fileDataEntity != null){
			parameterMap.put("P_FILE_GRP_NO", fileDataEntity.getString("FILE_GRP_NO"));
			trans.put(IproEbidPlanService.BID_ATCH_DOC_LIST, 
					comAtmaAtchFileDao.selectAtchFileListInqireByAtchFileGroupNo(parameterMap));
		}
		
		if(properJdgmnEntrpsDetail != null){
			if(!"".equals(properJdgmnEntrpsDetail.getString("FILE_GRP_NO"))){
				parameterMap.put("P_FILE_GRP_NO", properJdgmnEntrpsDetail.getString("FILE_GRP_NO"));
				trans.put(IproEbidPlanService.BID_ATCH_DOC_LIST+2, 
						comAtmaAtchFileDao.selectAtchFileListInqireByAtchFileGroupNo(parameterMap));
			}
		}
		
		return trans;
	}
	
	/**
	 * 
	 * <pre>
	 * 1. 개요 : 낙찰자선정 결격사유 등록 폼
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : sucbidrSlctnBrdoResnRegistForm
	 * @date : 2015. 3. 16.
	 * @author : 은우소프트 손연우
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 3. 16.		은우소프트 손연우				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @see oda.iep.elbi.service.IproEbidSucbidrSlctnService#sucbidrSlctnBrdoResnRegistForm(oda.fwk.prl.request.FwkParameterMap)
	 * @param parameterMap
	 * @return
	 */
	
	@Override
	public FwkTransferObject sucbidrSlctnBrdoResnRegistForm(final FwkParameterMap parameterMap){
		
		FwkTransferObject trans = new FwkTransferObject();
		
		//입찰정보상세
		trans.put(IproEbidSucbidrSlctnService.BID_INFO_DETAIL, 
						iproEbidSucbidrSlctnDao.selectBidInfoDetail(parameterMap));
				
		//업체정보상세
		trans.put(IproEbidSucbidrSlctnService.ENTRPS_INFO_DETAIL, 
						iproEbidSucbidrSlctnDao.selectEntrpsInfoDetail(parameterMap));		
		
		return trans;
	}
	
	/**
	 * 
	 * <pre>
	 * 1. 개요 : 협상통보 등록 폼 
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : ntatDspthRegistForm
	 * @date : 2015. 9. 8.
	 * @author : 은우소프트 손연우
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 9. 8.		은우소프트 손연우				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @see oda.iep.elbi.service.IproEbidSucbidrSlctnService#ntatDspthRegistForm(oda.fwk.prl.request.FwkParameterMap)
	 * @param parameterMap
	 * @return
	 */
	@Override
	public FwkTransferObject ntatDspthRegistForm(final FwkParameterMap parameterMap) {
	
		FwkTransferObject trans = new FwkTransferObject();
		
				trans.put(IproEbidSucbidrSlctnService.ENTRPS_INFO_DETAIL, iproEbidSucbidrSlctnDao.selectNtatDspthDetail(parameterMap));
		
/*				//입찰정보상세
				trans.put(IepElbiSucbidrSlctnService.BID_INFO_DETAIL, 
								iproEbidSucbidrSlctnDao.selectBidInfoDetail(parameterMap));
				//업체정보상세
				trans.put(IepElbiSucbidrSlctnService.ENTRPS_INFO_DETAIL, 
								iproEbidSucbidrSlctnDao.selectEntrpsInfoNtatDspthDetail(parameterMap));*/
				
				
		
		return trans;
	}
	
	/**
	 * 
	 * <pre>
	 * 1. 개요 : 협상 통보 등록 
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : ntatDspthRegist
	 * @date : 2015. 9. 8.
	 * @author : 은우소프트 손연우
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 9. 8.		은우소프트 손연우				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @see oda.iep.elbi.service.IproEbidSucbidrSlctnService#ntatDspthRegist(oda.fwk.prl.request.FwkParameterMap)
	 * @param parameterMap
	 * @return
	 */
	@Override
	public FwkTransferObject ntatDspthRegist(final FwkParameterMap parameterMap) {
		
		FwkTransferObject trans = new FwkTransferObject();
		
		
		parameterMap.put("P_REG_DT", FwkDateUtil.getCurrentDateTimeAsString());
		parameterMap.put("P_MOD_DT", FwkDateUtil.getCurrentDateTimeAsString());
//		parameterMap.put("P_NTAT_PROPER_DSPTH_SE_CD", "270000");
		
		//
		
		//협상통보저장
//		iproEbidSucbidrSlctnDao.insertNtatDspthRegist(parameterMap);
			
		//
		iproEbidSucbidrSlctnDao.insertEtrpsProgrsHistRegist(parameterMap);
		
		
		return trans;
	}
	/**
	 * 
	 * <pre>
	 * 1. 개요 : 낙찰자선정 결격사유 등록 
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : sucbidrSlctnBrdoResnRegist
	 * @date : 2015. 3. 16.
	 * @author : 은우소프트 손연우
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 3. 16.		은우소프트 손연우				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @see oda.iep.elbi.service.IproEbidSucbidrSlctnService#sucbidrSlctnBrdoResnRegist(oda.fwk.prl.request.FwkParameterMap)
	 * @param parameterMap
	 * @return
	 */
	
	@Override
	public FwkTransferObject sucbidrSlctnBrdoResnRegist(final FwkParameterMap parameterMap){
		FwkTransferObject trans = new FwkTransferObject();
		parameterMap.put("P_BID_VEND_PSCD", "OP15");
		parameterMap.put("P_BID_ELCD", "NT_ELGB");
		iproEbidSucbidrSlctnDao.updateSucbidrSlctnBrdoResnRegist(parameterMap);
		iproEbidSucbidrSlctnDao.insertEtrpsProgrsHistRegist(parameterMap);
		return trans;
	}
	
	
	
	
	/**
	 * 
	 * <pre>
	 * 1. 개요 : 이메일전송대상 등록 
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : emailTrnsmisTrgetRegist
	 * @date : 2015. 3. 18.
	 * @author : 은우소프트 손연우
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 3. 18.		은우소프트 손연우				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @see oda.iep.elbi.service.IproEbidSucbidrSlctnService#emailTrnsmisTrgetRegist(oda.fwk.prl.request.FwkParameterMap)
	 * @param parameterMap
	 * @return
	 */
	

	@Override
	public FwkTransferObject emailTrnsmisTrgetRegist(final FwkParameterMap parameterMap) {
		
		FwkTransferObject trans = new FwkTransferObject();
		FwkDataEntity mst = iproEbidSucbidrSlctnDao.selectBidInfoDetail(parameterMap);
		iproEbidSucbidrSlctnDao.updateEntrpsPartcptnPrstUpdt(parameterMap);
		iproEbidSucbidrSlctnDao.insertEtrpsProgrsHistRegist(parameterMap);
		
		String cntn = "";
		if("OP08".equals(parameterMap.get("P_BID_VEND_PSCD"))){
			cntn = "귀사는 해당 입찰공고에 협상대상자로 선정되었습니다.";
		}else if("OP07".equals(parameterMap.get("P_BID_VEND_PSCD"))){
			cntn = "귀사는 해당 입찰공고에 적격심사대상자로 선정되었습니다.";
		}
		cntn = cntn + "\n담당자 : "+mst.getString("RQSTR_NM")
						 +"\n담당부서 : "+mst.getString("RQST_DEPT_NM")
						 +"\n담당자 전화번호 : "+mst.getString("RQSTR_TEL_NO") ;
		parameterMap.put("P_NEGO_CNTN", cntn);
		iproEbidSucbidrSlctnDao.updateNegoUpdt(parameterMap);
		
		
		FwkDataEntity mail = iproCommDefaultDao.selectVendMail(parameterMap);
		if(mail != null){
			if(!"".equals(mail.getString("EMAL_ADDR"))){
				if("OP08".equals(parameterMap.get("P_BID_VEND_PSCD"))){ // 협상요청
					parameterMap.put("P_STR", "ANNC_NEGO");												//구분
					//제목
					parameterMap.put("P_TITLE", "[EBS] "+mst.getString("ANNC_NO")+"-"+mst.getString("ANNC_NGR")+" 공고 협상대상자 선정 안내 메일");
					//내용
					parameterMap.put("P_CONTENT", "귀사는 해당 공고에 협상대상자로 선정 되었습니다.<br>"
																	+ "EBS 전자입찰계약 홈페이지에 접속하여 협상 내용을 확인하시기 바랍니다.<br>"
																	+ "공고번호  : "+mst.getString("ANNC_NO")+"-"+mst.getString("ANNC_NGR")+"<br>"
																	+ "공고명 : "+mst.getString("BID_NM")+"<br><br>"
																	+ "EBS 전자입찰계약 홈페이지에 접속하려면 <a target=\"_blank\" href=\"https://contract.ebs.co.kr/opro/main/emgncLoginForm.do\">여기</a>를 클릭하세요." );
				}else if("OP07".equals(parameterMap.get("P_BID_VEND_PSCD"))){ // 적격심사요청
					parameterMap.put("P_STR", "ANNC_ELGB");												//구분
					//제목
					parameterMap.put("P_TITLE", "[EBS] "+mst.getString("ANNC_NO")+"-"+mst.getString("ANNC_NGR")+" 공고 적격심사대상자 선정 안내 메일");
					//내용
					parameterMap.put("P_CONTENT", "귀사는 해당 공고에 적격심사대상자로 선정 되었습니다.<br>"
																	+ "EBS 전자입찰계약 홈페이지에 접속하여 적격심사 내용을 확인하시길 바랍니다.<br>"
																	+ "공고번호  : "+mst.getString("ANNC_NO")+"-"+mst.getString("ANNC_NGR")+"<br>"
																	+ "공고명 : "+mst.getString("BID_NM")+"<br><br>"
																	+ "EBS 전자입찰계약 홈페이지에 접속하려면 <a target=\"_blank\" href=\"https://contract.ebs.co.kr/opro/main/emgncLoginForm.do\">여기</a>를 클릭하세요." );
				}
				parameterMap.put("P_RCVID", mail.getString("EMAL_ADDR"));					//메일주소
				iproCommDefaultDao.insertMail(parameterMap);
			}
		}
		
		return trans;
	}
	
	/**
	 * 
	 * <pre>
	 * 1. 개요 : 적격심사 평가등록
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : properJdgmnEvlRegist
	 * @date : 2015. 3. 20.
	 * @author : 은우소프트 손연우
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 3. 20.		은우소프트 손연우				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @see oda.iep.elbi.service.IproEbidSucbidrSlctnService#properJdgmnEvlRegist(oda.fwk.prl.request.FwkParameterMap)
	 * @param parameterMap
	 * @return
	 */
	
	@Override
	public FwkTransferObject properJdgmnEvlRegist(final FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();
		
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest)parameterMap.get("multipartRequest");
		String contextPath = "bid";
		FwkParameterMap fileParameterMap =  CmmnUtil.fileMultiUpload(multipartRequest,"P_FILE", contextPath);
//		FwkParameterMap fileParameterMap =null;
		if(fileParameterMap != null){
			String atchFileGroupNo = "";
			if("".equals(parameterMap.getString("P_FILE_GRP_NO"))){
				atchFileGroupNo = fileParameterMap.getString("atchFileGroupNo");
			}else{
				atchFileGroupNo = parameterMap.getString("P_FILE_GRP_NO");
			}
			List<Map<String, Object>> list = (List<Map<String, Object>>)fileParameterMap.get("list");
			// 공통 파일정보 저장
			parameterMap.put("atchFileGroupNo", atchFileGroupNo);
			parameterMap.put("fileList", list);
			parameterMap.put("P_SYSDATE", FwkDateUtil.getCurrentDateTimeAsString());
			comAtmaAtchFileDao.insertAtchFileRegist(parameterMap);
			
			parameterMap.put("P_FILE_GRP_NO", atchFileGroupNo);
		}
		
		if(!"".equals(parameterMap.getString("P_DEL_SN"))){
			List<Map<String, Object>> delList = new ArrayList<Map<String,Object>>();
			FwkParameterMap delMap = new FwkParameterMap();
			delMap.put("P_FILE_SN", parameterMap.getString("P_DEL_SN"));
			delList.add(delMap);
			parameterMap.put("fileList", delList);
			comAtmaAtchFileDao.deleteAtchFileDelete(parameterMap);
		}
		
		FwkDataEntity dataEntity = iproEbidPlanDao.selectBidInfoDetail(parameterMap);
		
		int elgbEstmScr = parameterMap.getInt("P_ELGB_ESTM_SCR");
		
//		if( elgbEstmScr < 85){
		if( elgbEstmScr < dataEntity.getInt("ELGB_LMT_SCR")){
			parameterMap.put("P_BID_VEND_PSCD", "OP15");
			parameterMap.put("P_NT_ELGB_RSN", "적격점수 미달");
			iproEbidSucbidrSlctnDao.updateProperJdgmnEvlNtElgbUpdt(parameterMap);
		}else{
			parameterMap.put("P_BID_VEND_PSCD", "OP07");
			parameterMap.put("P_NT_ELGB_RSN", "");
			iproEbidSucbidrSlctnDao.updateProperJdgmnEvlNtElgbUpdt(parameterMap);
		}
		
		//업체 적격심사 총점 등록
		iproEbidSucbidrSlctnDao.updateEntrpsProperJdgmnTotScoreRegist(parameterMap);
		
		return trans;
	}
	
	/**
	 * 
	 * <pre>
	 * 1. 개요 : 예가선택업체 조회
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : bidResultPrdprcChoiseEntrpsInqire
	 * @date : 2015. 3. 23.
	 * @author : 은우소프트 손연우
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 3. 23.		은우소프트 손연우				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @see oda.iep.elbi.service.IproEbidSucbidrSlctnService#bidResultPrdprcChoiseEntrpsInqire(oda.fwk.prl.request.FwkParameterMap)
	 * @param parameterMap
	 * @return
	 */
	
	@Override
	public FwkTransferObject bidResultPrdprcChoiseEntrpsInqire(final FwkParameterMap parameterMap){
		
		FwkTransferObject trans = new FwkTransferObject();
		
		trans.put(IproEbidSucbidrSlctnService.PRDPRC_CHOISE_ENTRPS_INQIRE
				,iproEbidSucbidrSlctnDao.selectBidResultPrdprcChoiseEntrpsInqire(parameterMap));
		
		return trans;
	}
	
	/**
	 * <pre>
	 * 1. 개요 : 낙찰 
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : sucbidInfoUpdt
	 * @date : 2015. 4. 30.
	 * @author : 은우소프트 손연우
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 4. 30.		은우소프트 손연우				최초 작성 
	 *	-----------------------------------------------------------------------
	 * @see oda.iep.elbi.service.IproEbidSucbidrSlctnService#scsbid(oda.fwk.prl.request.FwkParameterMap)
	 * @param parameterMap
	 * @return
	 */
	@Override
	public FwkTransferObject sucbidInfoUpdt(final FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();
		
		parameterMap.put("P_BID_VEND_PSCD","OP09");
		parameterMap.put("P_BID_PSCD","PF61");
		
		FwkDataEntity biMst = iproEbidPlanDao.selectBidInfoDetail(parameterMap);
		trans.put("CONT_SECD", biMst.get("CONT_SECD")+"");
		trans.put("PRCH_RQR_NO", biMst.get("PRCH_RQR_NO")+"");
		
		//낙찰자정보 수정 
		iproEbidSucbidrSlctnDao.updateSucbidInfoUpdt(parameterMap);
		
		//업체참여진행상태 수정 
		iproEbidSucbidrSlctnDao.updateEntrpsPartcptnPrstUpdt(parameterMap);
		
		//업체 진행이력상태 등록 
		iproEbidSucbidrSlctnDao.insertEtrpsProgrsHistRegist(parameterMap);
		
		//입찰진행상태 수정 
		iproEbidSucbidrSlctnDao.updateBidPrstUpdt(parameterMap);
		
		if(parameterMap.get("P_SBID_MTCD") != null && parameterMap.get("P_SBID_MTCD").equals("55")) {
			iproEbidOpengManageDao.insertEntrpsOpengInfoRegist(parameterMap);
		}
		
		//입찰 진행이력상태 등록 
		iproEbidSucbidrSlctnDao.insertBidProgrsHistSttusRegist(parameterMap);
		
		//낙찰정보 전송 - MIS
		parameterMap.put("P_CHRGR_ID", biMst.get("CHRGR_ID"));
		commItfcService.sendSbidInfoToMIS(parameterMap);
		
		if(biMst.get("BID_MTCD").equals("BID") &&  biMst.get("G2B_NTFY_YN").equals("Y")) {
			try {
				
				// G2B 헤더정보조회
				//iproG2bDao.selectG2bHeaderInfoBid(parameterMap);
				
				// 낙찰정보 G2B 연계
				FwkDataEntity fde = iproG2bDao.selectG2bCreateSucc(parameterMap);
				parameterMap.put("P_SBID_YN_S", "Y");
				List<FwkDataEntity> list = iproG2bDao.selectLwetScsbidPrearngerList(parameterMap);
//				CreateXML.CreateSuccXml(fde, list);
				
				LOG.debug("G2B Success ===================<");
				
			} catch (Exception e1) {
				LOG.debug("G2B Fail Msg Start===================<");
				LOG.debug(e1.toString());
				LOG.debug(">>> 나라장터연계에 실패하였습니다");
				LOG.debug("G2B Fail Msg End===================>");
			}
		}
		
		//알림톡 메시지 발송
		try {
			// 카카오 알림톡 : 낙찰완료(13590)
			parameterMap.put("P_MSG_SN", "13590");
			FwkDataEntity msgEntity = commItfcDao.selectMsgForm(parameterMap);
			List<FwkDataEntity> vendList = iproEbidPblancDao.selectVendDtlUsrInfo(parameterMap);
			for (FwkDataEntity vendInfo : vendList) {
				vendInfo.put("P_CHRGR_NM", vendInfo.get("CHRGR_NM"));
				vendInfo.put("P_CHRGR_TEL_NO", vendInfo.get("CHRGR_TEL_NO"));
				vendInfo.put("P_VEND_NM", vendInfo.get("VEND_NM"));
				vendInfo.put("P_ANNC_NO", vendInfo.get("ANNC_NO"));
				vendInfo.put("P_BID_NM", vendInfo.get("BID_NM"));
				parameterMap.put("P_MSG_ENTITY", vendInfo);
				parameterMap.put("P_MSG_CNTN", msgEntity.get("MSG_CNTN"));
				parameterMap.put("P_RECEIVE_MOBILE_NO", vendInfo.get("CP_NO"));
				parameterMap.put("P_TEMPLATE_CODE", "13590");
				//알림톡 DB 입력
				commItfcService.insertTsmsMsgInfo(parameterMap);				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}		
		
		return trans;
	}

	@Override
	public FwkTransferObject ebidApprSendNego(FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();
		FwkParameterMap htmlMap = new FwkParameterMap();
		
		FwkDataEntity user = parameterMap.getLoginResult();
		
		// 계약수정
		parameterMap.put("P_APPR_STCD", "NEW");	//결재진행상태코드	
		iproEbidPlanDao.updateBidSttusChangeUpdt(parameterMap);
		iproEbidPlanDao.updateBidRndSttusChangeUpdt(parameterMap);
		
		FwkDataEntity biMst = iproEbidPlanDao.selectBidInfoDetail(parameterMap);
		parameterMap.put(IproEbidPlanService.BID_PLAN_DETAIL, biMst);
		
		parameterMap.put(IproEbidSucbidrSlctnService.SCSBID_PREARNGER_LIST
				, iproEbidSucbidrSlctnDao.selectLwetScsbidPrearngerList(parameterMap));
		
		parameterMap.put(IproEbidSucbidrSlctnService.NT_ELGB_VEND_LIST
				, iproEbidSucbidrSlctnDao.selectNtElgbVendList(parameterMap));
		
		parameterMap.put("P_BBS_SN", "1");
		FwkDataEntity dataEntity7 =  iproNotiDao.notiDetail(parameterMap);
		parameterMap.put("P_FILE_GRP_NO", dataEntity7.getString("FILE_GRP_NO"));
		parameterMap.put("noticeFileList7", comAtmaAtchFileDao.selectAtchFileListInqireByAtchFileGroupNo(parameterMap));
		parameterMap.put("P_BBS_SN", "2");
		FwkDataEntity dataEntity8 =  iproNotiDao.notiDetail(parameterMap);
		parameterMap.put("P_FILE_GRP_NO", dataEntity8.getString("FILE_GRP_NO"));
		parameterMap.put("noticeFileList8", comAtmaAtchFileDao.selectAtchFileListInqireByAtchFileGroupNo(parameterMap));
		
		/*
		 *	전자결재 진행 
		 */
		//Form Html 양식 불러오기
		parameterMap.put("P_FRM_ID", "ERP_REQ_NEGO_EDIT");
		FwkDataEntity applHtmlMap = iproCommDefaultDao.selectApplFormHtml(parameterMap);
		parameterMap.put("P_APPL_HTML", applHtmlMap.get("HTML_CNTN"));
		
		//결재문서 매핑
		//htmlMap= CmmnUtil.ebidApprovalHtmlCreate(parameterMap);
		
		//LEGACY 테이블 저장
		parameterMap.put("P_PREFIX_STR", "EBID_ANNC_EVAL_");
		parameterMap.put("P_HTML_DATA1", htmlMap.get("APPL_HTML_END"));
		parameterMap.put("P_DRAFT_SABUN", user.getString("SABUN"));						//사번 : V_SSO_USER 
		parameterMap.put("P_DRAFT_DEPT", user.getString("DEPT_NO"));			//부서번호	: V_SSO_USER LEGACY_CODE
		parameterMap.put("P_TITLE", biMst.getString("BID_NM")+ " 협상요청");			//NULL 안됨
		parameterMap.put("P_MAIN_TITLE", biMst.getString("BID_NM")+ " 협상요청");	//NULL 안됨
		iproCommDefaultDao.insertIfLegacyMst(parameterMap);
		
		parameterMap.put("P_DOC_NO", biMst.getString("DOC_NO"));							// 문서번호
		FwkDataEntity ifLegacyMst = iproCommDefaultDao.selectIfLegacyMst(parameterMap);
		if(ifLegacyMst != null){
			parameterMap.put("P_APP_ID", ifLegacyMst.getString("APP_ID"));				// APP_ID
			iproCommDefaultDao.insertIfLegacyDoc(parameterMap);
		}
		
		//연동정보 테이블 저장
		parameterMap.put("P_BID_APPR_TSCD", "F");
		iproEbidPlanDao.insertBiApprCntc(parameterMap);
		
		trans.put("P_INTERFACE_ID", parameterMap.get("P_INTERFACE_ID"));
		trans.put("P_STATUS", "NEW");
		trans.put("P_DRAFT_SABUN", user.getString("SABUN"));
		
		return trans;
	}
	
	@Override
	public FwkTransferObject sameRankLot(FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();
		
		List<FwkDataEntity> vendList = iproEbidSucbidrSlctnDao.selectSameRankVendList(parameterMap);
		
		String orgVal = "";
		
		for (int i = 0; i < vendList.size(); i++) {
			FwkDataEntity vend = vendList.get(i);
			orgVal += vend.getString("BIZRNO")+vend.getString("SIGN_DT");
		}
		int sumVal = 0;
		try {
	    	MessageDigest md = MessageDigest.getInstance("SHA-256");
	    	md.update(orgVal.getBytes(Charset.forName("UTF-8")));
	    	byte[] msgb = md.digest();
	    	for (byte b : msgb) {
	    		sumVal += b;
			}
		} catch (Exception e) {
			System.out.println(">>>>>hash 에러<<<<<");
//			e.printStackTrace();
		}
		
		int sucVal = Math.abs(sumVal)%vendList.size();
		
		trans.put("vendInfo", vendList.get(sucVal));
		
		return trans;
	}
	
	/**
	 * <pre>
	 * 1. 개요 : 낙찰 - 오프라인 업체 입력
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : scsBidOfflineInsert
	 * @date : 2019. 03. 28.
	 * @author : 은우소프트 맹경열
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 03. 28.		은우소프트 맹경열				최초 작성 
	 *	-----------------------------------------------------------------------
	 * @param parameterMap
	 * @return
	 */	
	@Override
	public FwkTransferObject scsBidOfflineInsert(final FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();

		//계약상태 변경
		parameterMap.put("P_BID_PSCD", "PF61");
		iproEbidPlanDao.updateBidSttusChangeUpdt(parameterMap);
		iproEbidPlanDao.updateBidRndSttusChangeUpdt(parameterMap);
		
		//입찰 오프라인 업체 등록
		parameterMap.put("P_BID_ELCD", "ELGB");
		parameterMap.put("P_BID_TPI_SECD", "OP03");
		Object entrpsRegistNo = parameterMap.get("P_VEND_REG_NO");
		Object tndrAmt = parameterMap.get("P_TNDR_AMT");
		Object chrgrNm = parameterMap.get("P_CHRGR_NM");
		Object telNo = parameterMap.get("P_TEL_NO");
		Object emalAddr = parameterMap.get("P_EMAL_ADDR");
		Object sbidYn = parameterMap.get("P_SBID_YN");
		if(entrpsRegistNo != null){
			if(entrpsRegistNo instanceof String){
				parameterMap.put("P_VEND_REG_NO", entrpsRegistNo);
				parameterMap.put("P_BID_VEND_PSCD", "OP09");

				//업체 마스터 입력
				iproEbidResultDao.insertG2BVendInfoMst(parameterMap);
				//업체 개찰정보 입력
				iproEbidResultDao.insertG2BVendBiopInfo(parameterMap);
				//업체 디테일 입력
				iproEbidOpengManageDao.insertAtmcBiabRegist(parameterMap);
			}else if(entrpsRegistNo instanceof ArrayList){
				ArrayList<String> entrpsRegistNoList = (ArrayList<String>) entrpsRegistNo;
				ArrayList<String> tndrAmtList = (ArrayList<String>) tndrAmt;
				ArrayList<String> chrgrNmList = (ArrayList<String>) chrgrNm;
				ArrayList<String> telNoList = (ArrayList<String>) telNo;
				ArrayList<String> emalAddrList= (ArrayList<String>) emalAddr;
				ArrayList<String> sbidYnList= (ArrayList<String>) sbidYn;
				for(int i =0; i < entrpsRegistNoList.size(); i++){
					parameterMap.put("P_VEND_REG_NO", entrpsRegistNoList.get(i));
					parameterMap.put("P_TNDR_AMT", tndrAmtList.get(i));
					parameterMap.put("P_SBID_YN", sbidYnList.get(i));
					if(sbidYnList.get(i).equals("Y")) {
						parameterMap.put("P_SBID_AMT", tndrAmtList.get(i));
						parameterMap.put("P_OPNG_RNK", "1");
					}else {
						parameterMap.put("P_SBID_AMT", "");
						parameterMap.put("P_OPNG_RNK", "");
					}
					parameterMap.put("P_SBID_YN", sbidYnList.get(i));
					parameterMap.put("P_CHRGR_NM", chrgrNmList.get(i));
					parameterMap.put("P_TEL_NO", telNoList.get(i));
					parameterMap.put("P_EMAL_ADDR", emalAddrList.get(i));
					
					//업체 마스터 입력
					iproEbidResultDao.insertG2BVendInfoMst(parameterMap);
					//업체 개찰정보 입력
					iproEbidResultDao.insertG2BVendBiopInfo(parameterMap);
					//업체 디테일 입력
					iproEbidOpengManageDao.insertAtmcBiabRegist(parameterMap);
				}
			}else if(entrpsRegistNo instanceof String[]){
				String[] entrpsRegistNoList = (String[]) entrpsRegistNo;
				String[] tndrAmtList = (String[]) tndrAmt;
				String[] chrgrNmList = (String[]) chrgrNm;
				String[] telNoList = (String[]) telNo;
				String[] emalAddrList = (String[]) emalAddr;
				String[] sbidYnList = (String[]) sbidYn;
				for(int i =0; i < entrpsRegistNoList.length; i++){
					parameterMap.put("P_VEND_REG_NO", entrpsRegistNoList[i]);
					parameterMap.put("P_TNDR_AMT", tndrAmtList[i]);
					if(sbidYnList[i].equals("Y")) {
						parameterMap.put("P_SBID_AMT", tndrAmtList[i]);
						parameterMap.put("P_OPNG_RNK", "1");
					}else {
						parameterMap.put("P_SBID_AMT", "");
						parameterMap.put("P_OPNG_RNK", "");
					}					
					parameterMap.put("P_SBID_YN", sbidYnList[i]);
					parameterMap.put("P_CHRGR_NM", chrgrNmList[i]);
					parameterMap.put("P_TEL_NO", telNoList[i]);
					parameterMap.put("P_EMAL_ADDR", emalAddrList[i]);					
					
					//업체 마스터 입력
					iproEbidResultDao.insertG2BVendInfoMst(parameterMap);
					//업체 개찰정보 입력
					iproEbidResultDao.insertG2BVendBiopInfo(parameterMap);
					//업체 디테일 입력
					iproEbidOpengManageDao.insertAtmcBiabRegist(parameterMap);
					
				}
			}
		}
		
		//입찰 진행이력상태 등록 
		iproEbidSucbidrSlctnDao.insertBidProgrsHistSttusRegist(parameterMap);		
		
		return trans;
	}	
	
}