package com.eunwoosoft.comm.atfi.dao;

import java.util.List;

import com.eunwoosoft.frwk.fol.dto.FwkDataEntity;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;

/**
 * <pre>
 * com.eunwoosoft.comm.atfi.dao 
 *    |_ ComAtfiAtchFileHistDao.java
 * 
 * </pre>
 * @date : 2015. 2. 27. 오전 9:29:10
 * @version : 1.0
 * @author : LIG시스템 김경용
 */
public interface ComAtfiAtchFileHistDao {	
	
	/**
	 * <pre>
	 * 1. 개요 : 첨부파일이력 등록 
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : insertAtchFileHistRegist
	 * @date : 2015. 2. 27.
	 * @author : LIG시스템 김경용
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 2. 27.		LIG시스템 김경용				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 */
	void insertAtchFileHistRegist(FwkParameterMap parameterMap);
	
	/**
	 * <pre>
	 * 1. 개요 : 첨부파일이력목록조회_페이징
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : selectAtchFileHistListInqireWithPgng
	 * @date : 2015. 2. 27.
	 * @author : LIG시스템 김경용
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 2. 27.		LIG시스템 김경용				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 */
	List<FwkDataEntity> selectAtchFileHistListInqireWithPgng(FwkParameterMap parameterMap);
	
	/**
	 * <pre>
	 * 1. 개요 : 첨부파일이력목록조회_총건수
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : selectAtchFileHistListInqireTotcnt
	 * @date : 2015. 2. 27.
	 * @author : LIG시스템 김경용
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 2. 27.		LIG시스템 김경용				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 */
	long selectAtchFileHistListInqireTotcnt(FwkParameterMap parameterMap);
	
	/**
	 * <pre>
	 * 1. 개요 : 첨부파일이력상세조회 
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : selectAtchFileHistDetailInqire
	 * @date : 2015. 2. 27.
	 * @author : LIG시스템 김경용
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 2. 27.		LIG시스템 김경용				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 */
	FwkDataEntity selectAtchFileHistDetailInqire(FwkParameterMap parameterMap);
	
	

}
