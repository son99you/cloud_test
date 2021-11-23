package com.eunwoosoft.comm.menu.service.impl;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.eunwoosoft.comm.menu.dao.CommMyMenuDao;
import com.eunwoosoft.comm.menu.service.CommMyMenuService;
import com.eunwoosoft.frwk.bul.AbstractFwkService;
import com.eunwoosoft.frwk.fol.dto.FwkDataEntity;
import com.eunwoosoft.frwk.fol.dto.FwkTransferObject;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;

@Service("commMyMenuService")
public class CommMyMenuServiceImpl extends AbstractFwkService implements CommMyMenuService {

	private static final Logger LOG = LoggerFactory.getLogger(CommMyMenuServiceImpl.class);
	
	@Resource(name="commMyMenuDao")
	private CommMyMenuDao commMyMenuDao;
	
	/**
	 * <pre>
	 * 1. 개요 : 대메뉴 목록 조회
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : myMenuListInqire
	 * @date : 2018. 05. 28.
	 * @author : 은우소프트 맹경열
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2018. 05. 28.	은우소프트 맹경열		최초 작성 
	 *	-----------------------------------------------------------------------
	 * @see
	 * @param parameterMap
	 * @return
	 * @throws ParseException 
	 * @throws IOException 
	 * @throws SQLException 
	 */
	public FwkTransferObject myMenuListInqire(FwkParameterMap parameterMap) throws SQLException, IOException, ParseException {
		FwkTransferObject trans = new FwkTransferObject();
		
		FwkDataEntity user = (FwkDataEntity) parameterMap.get("loginResult");
		if(user != null) {
			// 5	평가업체
			// 4	외부평가위원
			
			if("opro".equals(parameterMap.getString("P_USR_SECD"))){
				System.err.println("========== [외부] 로그인 ==========");
				if( user.get("LOGIN_GBN") == "VEND" || "VEND".equals(user.get("LOGIN_GBN"))){
					System.err.println("========== [평가업체] 로그인 ==========");
					parameterMap.put("P_AUTH_ID_S", "5");
				}else{
					System.err.println("========== [평가위원] 로그인 ==========");
					parameterMap.put("P_AUTH_ID_S", "5");
				}
			}
		}
		
		trans.put(CommMyMenuService.MY_MENU_LIST, commMyMenuDao.selectMyMenuListInqire(parameterMap));
		return trans;
	}
	
	/**
	 * <pre>
	 * 1. 개요 : 대메뉴 > 서브메뉴 목록 조회
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : myMenuSubListInqire
	 * @date : 2018. 05. 28.
	 * @author : 은우소프트 맹경열
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2018. 05. 28.	은우소프트 맹경열		최초 작성 
	 *	-----------------------------------------------------------------------
	 * @see
	 * @param parameterMap
	 * @return
	 * @throws ParseException 
	 * @throws IOException 
	 * @throws SQLException 
	 */
	public FwkTransferObject myMenuSubListInqire(FwkParameterMap parameterMap) throws SQLException, IOException, ParseException {
		FwkTransferObject trans = new FwkTransferObject();
		
		FwkDataEntity user = (FwkDataEntity) parameterMap.get("loginResult");
		
		if(user != null) {
			// 5	평가업체
			// 4	외부평가위원
			
			if("opro".equals(parameterMap.getString("P_USR_SECD"))){
				System.err.println("========== [외부] 로그인 ==========");
				if( user.get("LOGIN_GBN") == "VEND" || "VEND".equals(user.get("LOGIN_GBN"))){
					System.err.println("========== [평가업체] 로그인 ==========");
					parameterMap.put("P_AUTH_ID_S", "5");
				}else{
					System.err.println("========== [평가위원] 로그인 ==========");
					parameterMap.put("P_AUTH_ID_S", "5");
				}
			}
		}
		
		trans.put(CommMyMenuService.MY_MENU_SUB_LIST, commMyMenuDao.selectMyMenuSubListInqire(parameterMap));
		return trans;
	}
	
}
