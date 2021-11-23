package com.eunwoosoft.ipro.main.service.impl;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.eunwoosoft.comm.util.FwkSSOUtil;
import com.eunwoosoft.frwk.bul.AbstractFwkService;
import com.eunwoosoft.frwk.fol.dto.FwkDataEntity;
import com.eunwoosoft.frwk.fol.dto.FwkTransferObject;
import com.eunwoosoft.frwk.fol.util.FwkFormatUtil;
import com.eunwoosoft.frwk.fol.util.FwkMessageUtil;
import com.eunwoosoft.frwk.fol.util.SDBCryptoUtil;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;
import com.eunwoosoft.ipro.main.dao.IproMainLoginFormDao;
import com.eunwoosoft.ipro.main.service.IproMainLoginFormService;


/**
 * 
 * com.eunwoosoft.ipro.main.service.impl
 * IproMainLoginFormServiceImpl.java
 *
 * @Author : SungYoon_Ha
 * @Date   : 2017. 5. 30.
 *
 */
@Service(value="iproMainLoginFormService")
public class IproMainLoginFormServiceImpl extends AbstractFwkService implements IproMainLoginFormService {
	private static final Logger LOG = LoggerFactory.getLogger(IproMainLoginFormServiceImpl.class);
	
	@Resource(name="iproMainLoginFormDao")
	private IproMainLoginFormDao iproMainLoginFormDao;
	
	
	@Override
	public FwkTransferObject loginByBizrno(FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();
	
		FwkDataEntity dataEntity = iproMainLoginFormDao.selectEntrpsMberInqireByBizrNo(parameterMap);
		trans.put(IproMainLoginFormService.LOGIN_RESULT, dataEntity);

		return trans;
	}
	
	
	/**
	 * <pre>
	 * 1.개요 : 
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro.com.eunwoosoft.ipro.main.service.IproMainLoginFormService.java
	 * @Method : login
	 * @author : SungYoon_Ha
	 * @date : 2017. 5. 30. 
	 * @param parameterMap
	 * @return
	 * @throws SQLException
	 * @throws IOException
	 * @throws ParseException
	 */
	public FwkTransferObject login(FwkParameterMap parameterMap) throws SQLException, IOException, ParseException {		
		FwkTransferObject trans = new FwkTransferObject();
		FwkParameterMap retObjectMap = new FwkParameterMap();
		String resultMessage = "";
		int resultCode = 0;

		// 사용자 조회
		FwkDataEntity entityById = iproMainLoginFormDao.selectEmplyrInqireById(parameterMap);
		
		if(entityById == null) {
			//계정이 없는 경우
			resultCode = -9999;
			resultMessage = "계정이 없거나 아이디와 비밀번호가 맞지 않습니다.";
		}else if( entityById != null ){ 		
			/*if(parameterMap.get("P_SSO_FLAG") != null && parameterMap.get("P_SSO_FLAG").equals("Y")) {
				resultCode = 0;
				entityById.put("P_SSO_STATUS", "OK");	
			}else {*/
				// 비밀번호 해시값 변환
				parameterMap.put("P_USR_ID",  entityById.getString("USR_ID"));		//계정ID
				/*String PW = parameterMap.getString("P_PWD");
				
				System.err.println("@@@ 암호화된 비밀번호 ::: " + SDBCryptoUtil.encryptSha256(PW));
				
				parameterMap.put("P_PWD", SDBCryptoUtil.encryptSha256(PW));*/
				
				//아이디, 패스워드로 사용자 조회
				FwkDataEntity entityByIdAndPw = iproMainLoginFormDao.selectEmplyrInqireByIdAndPassword(parameterMap);
				
				if(resultCode != -9999 && entityByIdAndPw == null){
					//비밀번호가 틀린경우
					resultCode = -44;
					resultMessage = "계정이 없거나 아이디와 비밀번호가 맞지 않습니다.";
				}else {
					entityById = entityByIdAndPw;
					entityById.put("P_SSO_STATUS", "OK");	
				}	
			/*}*/
		}
		
		if(resultCode == 0){		//로그인 성공!!!
			entityById.put("CONN_IP", parameterMap.get("CONN_IP"));
			
			parameterMap.put("USR_ID", entityById.get("USR_ID"));
			parameterMap.put("USR_NM", entityById.get("USR_NM"));
			
			parameterMap.put("VEND_REG_NO", entityById.get("VEND_REG_NO"));
			
			//세션에 나의 Role 목록 추가
			List<String> roleList = new ArrayList<String>();
			roleList.add("ABID_MNGR");
			roleList.add("GBID_MNGR");
			entityById.put("roleList", roleList);
			
//			parameterMap.put("P_GROUP_ID", entityByIdAndPw.get("GROUP_ID"));
			parameterMap.put("P_GROUP_ID", "1");
			
			super.getCurrentHttpServletRequest().getSession().setAttribute(IproMainLoginFormService.LOGIN_RESULT, entityById);
			super.getCurrentHttpServletRequest().getSession().setAttribute("isLocallyAuthenticated", "true");
			super.getCurrentHttpServletRequest().getSession().setMaxInactiveInterval(60*60*8); // 8시간 설정
			iproMainLoginFormDao.updateLastLoginDt(parameterMap);
		}
		
		retObjectMap.put(IproMainLoginFormService.RESULT_CODE, String.valueOf(resultCode));
		retObjectMap.put(IproMainLoginFormService.RESULT_MESSAGE, resultMessage);
		retObjectMap.put(IproMainLoginFormService.RESULT_DATA, entityById);

		trans.put(IproMainLoginFormService.LOGIN_RESULT, retObjectMap);
		
		
		return trans;
	}
	
	
	/**
	 * 업무요약 Process 표현
	 * 
	 * 2020-10-20
	 * joo
	 *
	 */
	public FwkTransferObject getMainSummary(final FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();

		/**************************************** 진행중인 업무 START ****************************************/
		if(parameterMap.get("P_BEGIN_DT_S") != null && !"".equals(parameterMap.get("P_BEGIN_DT_S"))){ // 요청일자 시작
			parameterMap.put("P_BEGIN_DT_S", parameterMap.get("P_BEGIN_DT_S").toString());
		} 
		
		if(parameterMap.get("P_END_DT_S") != null && !"".equals(parameterMap.get("P_END_DT_S"))){ // 요청일자 종료
			parameterMap.put("P_END_DT_S", parameterMap.get("P_END_DT_S").toString());
		} 
		
		trans.put("estmMainList", iproMainLoginFormDao.selectEstmMainList(parameterMap));
		/**************************************** 진행중인 업무 END ****************************************/
		return trans;
	}
	
	
	/**
	 * <pre>
	 * 1. 개요 : SSO 로그인 정보 받아오기
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : getSSOInfo
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
	 * @throws SDBException 
	 */	
	@Override
	public FwkTransferObject getSSOInfo(final FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();
		FwkSSOUtil ssoUtil = new FwkSSOUtil();
		
		//String cookie = ssoUtil.checkCookie(FwkMessageUtil.getMessage("IPRO.SSO.TOKEN_NAME"), (HttpServletRequest)parameterMap.get("request"));
		
		parameterMap.put("P_CLIENT_ID", FwkMessageUtil.getMessage("IPRO.SSO.CLIENT_ID"));
		parameterMap.put("P_CLIENT_SECRET", FwkMessageUtil.getMessage("IPRO.SSO.CLIENT_SECRET"));
		parameterMap.put("P_ACCESS_TOKEN", parameterMap.get("P_ACCESS_TOKEN"));
		parameterMap.put("P_SCOPE", FwkMessageUtil.getMessage("IPRO.SSO.SCOPE"));
		parameterMap.put("P_CLIENT_IP", parameterMap.get("CONN_IP"));
		parameterMap.put("P_SYS_URL", FwkMessageUtil.getMessage("IPRO.SYS.URL"));
		
		HashMap<String, String> result = ssoUtil.getUserInfo(parameterMap);
		
		trans.put("P_USR_ID", result.get("user_id"));
		trans.put("P_ERROR", result.get("error"));
		trans.put("P_ERROR_MESSAGE", result.get("error_message"));
		
		return trans;
	}


	@Override
	public FwkTransferObject mainEstmCnt(final FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();
		
		trans.put(IproMainLoginFormService.ESTM_CNT_RESULT, iproMainLoginFormDao.selectMainEstmCnt(parameterMap));
		
		return trans;
	}


	@Override
	public FwkTransferObject mainEstmList(FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();
		
		trans.put("estmMainList", iproMainLoginFormDao.selectEstmMainList(parameterMap));
		
		return trans;
	}


	public FwkTransferObject iproSBDC_sugarmanWorkConn(FwkParameterMap parameterMap) throws SQLException, IOException, ParseException {		
		
		FwkTransferObject trans = new FwkTransferObject();
		FwkParameterMap retObjectMap = new FwkParameterMap();
		
		String resultMessage = "";
		int resultCode = 0;
		
		String iproSBDC_PWD = "ewSBDCestm2021!@";
		
		// 사용자 조회
		FwkDataEntity entityById = iproMainLoginFormDao.selectEmplyrInqireById(parameterMap);
		
		if(entityById == null) {
			//계정이 없는 경우
			resultCode = -9999;
			resultMessage = "계정이 없거나 아이디와 비밀번호가 맞지 않습니다.";
		}else if( entityById != null ){ 		

			parameterMap.put("P_USR_ID", entityById.getString("USR_ID"));   // 계정ID
			
			String PWD = parameterMap.getString("P_PWD");
			
			if(!iproSBDC_PWD.equals(PWD)){
				resultCode = -9999;
				resultMessage = "계정이 없거나 아이디와 비밀번호가 맞지 않습니다.";
			}else{
				resultCode = 0;
			}
				
			if(resultCode == -9999){
				
				//비밀번호가 틀린경우
				resultCode = -44;
				resultMessage = "계정이 없거나 아이디와 비밀번호가 맞지 않습니다.";
			}else {
				entityById.put("P_SSO_STATUS", "OK");	
			}	
		}
		
		
		if(resultCode == 0){		//로그인 성공!!!
			entityById.put("CONN_IP", parameterMap.get("CONN_IP"));
			
			parameterMap.put("USR_ID", entityById.get("USR_ID"));
			parameterMap.put("USR_NM", entityById.get("USR_NM"));
			
			//세션에 나의 Role 목록 추가
			List<String> roleList = new ArrayList<String>();
			roleList.add("ABID_MNGR");
			roleList.add("GBID_MNGR");
			entityById.put("roleList", roleList);
			
			parameterMap.put("P_GROUP_ID", "1");
			
			super.getCurrentHttpServletRequest().getSession().setAttribute(IproMainLoginFormService.LOGIN_RESULT, entityById);
			super.getCurrentHttpServletRequest().getSession().setAttribute("isLocallyAuthenticated", "true");
			super.getCurrentHttpServletRequest().getSession().setMaxInactiveInterval(60*60*8); // 8시간 설정
			iproMainLoginFormDao.updateLastLoginDt(parameterMap);
		}
		
		retObjectMap.put(IproMainLoginFormService.RESULT_CODE, String.valueOf(resultCode));
		retObjectMap.put(IproMainLoginFormService.RESULT_MESSAGE, resultMessage);
		retObjectMap.put(IproMainLoginFormService.RESULT_DATA, entityById);

		trans.put(IproMainLoginFormService.LOGIN_RESULT, retObjectMap);
		
		
		return trans;
	}
}
