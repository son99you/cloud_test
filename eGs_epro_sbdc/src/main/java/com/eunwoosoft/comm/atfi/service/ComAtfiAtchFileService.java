package com.eunwoosoft.comm.atfi.service;

import java.util.List;
import java.util.Map;

import com.eunwoosoft.frwk.fol.dto.FwkTransferObject;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;

/**
 * <pre>
 * com.eunwoosoft.comm.atfi.service 	
 *    |_ ComAtfiAtchFileService.java
 * 
 * </pre>
 * @date : 2015. 2. 27. 오후 12:05:50
 * @version : 1.0
 * @author : LIG시스템 김경용
 */
public interface ComAtfiAtchFileService {
	
	/**
	 * 첨부파일목록 Key
	 */
	static final String ATCH_FILE_LIST = "atchFileList";
	
	/**
	 * 첨부파일총건수 Key
	 */
	static final String ATCH_FILE_LIST_TOTCNT = "atchFileListTotcnt";
	
	/**
	 * 첨부파일상세 Key
	 */
	static final String ATCH_FILE = "atchFile";
	
	
	/**
	 * <pre>
	 * 1. 개요 : 첨부파일목록조회_페이징
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : atchFileListInqireWithPgng
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
	FwkTransferObject atchFileListInqireWithPgng(final FwkParameterMap parameterMap);
	
	/**
	 * <pre>
	 * 1. 개요 : 첨부파일 상세조회
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : atchFileDetailInqire
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
	FwkTransferObject atchFileDetailInqire(final FwkParameterMap parameterMap);
	
	/**
	 * <pre>
	 * 1. 개요 : 첨부파일그룹번호에의한 첨부파일목록조회 
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : atchFileListInqireByAtchFileGroupSn
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
	FwkTransferObject atchFileListInqireByAtchFileGroupNo(final FwkParameterMap parameterMap);
	
	/**
	 * <pre>
	 * 1. 개요 : 첨부파일그룹번호에의한 첨부파일목록조회 (외부용)
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : atchFileListInqireByAtchFileGroupSn
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
	FwkTransferObject atchFileListInqireByAtchFileGroupNoExtern(final FwkParameterMap parameterMap);
	
	/**
	 * <pre>
	 * 1. 개요 : 첨부파일삭제
	 * 2. 처리내용 : 1. 첨부파일삭제
	 *                  2. 첨부파일이력등록
	 * </pre>
	 * @Method Name : atchFileDelete
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
	FwkTransferObject atchFileDelete(final FwkParameterMap parameterMap);

}
