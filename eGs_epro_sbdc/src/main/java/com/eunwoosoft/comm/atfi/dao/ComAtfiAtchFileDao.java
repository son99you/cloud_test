package com.eunwoosoft.comm.atfi.dao;

import java.util.List;

import com.eunwoosoft.frwk.fol.dto.FwkDataEntity;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;

/**
 * <pre>
 * com.eunwoosoft.comm.atfi.dao 
 *    |_ ComAtfiAtchFileDao.java
 * 
 * </pre>
 * @date : 2015. 2. 27. 오전 9:29:10
 * @version : 1.0
 * @author : LIG시스템 김경용
 */
public interface ComAtfiAtchFileDao {	
	
	/**
	 * <pre>
	 * 1. 개요 : 첨부파일목록조회_페이징
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : selectAtchFileListInqireWithPgng
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
	List<FwkDataEntity> selectAtchFileListInqireWithPgng(FwkParameterMap parameterMap);
	
	/**
	 * <pre>
	 * 1. 개요 : 첨부파일목록조회_총건수
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : selectAtchFileListInqireTotcnt
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
	long selectAtchFileListInqireTotcnt(FwkParameterMap parameterMap);
	
	/**
	 * <pre>
	 * 1. 개요 : 첨부파일상세조회 
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : selectAtchFileDetailInqire
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
	FwkDataEntity selectAtchFileDetailInqire(FwkParameterMap parameterMap);
	
	/**
	 * <pre>
	 * 1. 개요 : 첨부파일그룹번호에의한 첨부파일목록조회 
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : selectAtchFileListInqireByAtchFileGroupNo
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
	List<FwkDataEntity> selectAtchFileListInqireByAtchFileGroupNo(FwkParameterMap parameterMap);
	
	/**
	 * <pre>
	 * 1. 개요 : 첨부파일그룹번호에의한 첨부파일목록조회 (외부용)
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : selectAtchFileListInqireByAtchFileGroupNo
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
	List<FwkDataEntity> selectAtchFileListInqireByAtchFileGroupNoExtern(FwkParameterMap parameterMap);
	
	/**
	 * <pre>
	 * 1. 개요 : 첨부파일삭제
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : deleteAtchFileDelete
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
	void deleteAtchFileDelete(FwkParameterMap parameterMap);

}
