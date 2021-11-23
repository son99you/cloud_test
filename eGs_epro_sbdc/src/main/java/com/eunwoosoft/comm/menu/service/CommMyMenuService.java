package com.eunwoosoft.comm.menu.service;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

import com.eunwoosoft.frwk.fol.dto.FwkTransferObject;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;

public interface CommMyMenuService {
	static final String MY_MENU_LIST = "myMenuList";
	static final String MY_MENU_SUB_LIST = "myMenuSubList";
	
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
	 * @param parameterMap
	 * @return
	 * @throws ParseException 
	 * @throws IOException 
	 * @throws SQLException 
	 */
	FwkTransferObject myMenuListInqire(final FwkParameterMap parameterMap ) throws SQLException, IOException, ParseException;
	
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
	 * @param parameterMap
	 * @return
	 * @throws ParseException 
	 * @throws IOException 
	 * @throws SQLException 
	 */
	FwkTransferObject myMenuSubListInqire(final FwkParameterMap parameterMap ) throws SQLException, IOException, ParseException;
}
