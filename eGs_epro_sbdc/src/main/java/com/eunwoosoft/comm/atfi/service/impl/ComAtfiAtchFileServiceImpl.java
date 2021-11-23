package com.eunwoosoft.comm.atfi.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.eunwoosoft.comm.atfi.dao.ComAtfiAtchFileDao;
import com.eunwoosoft.comm.atfi.dao.ComAtfiAtchFileHistDao;
import com.eunwoosoft.comm.atfi.service.ComAtfiAtchFileService;
import com.eunwoosoft.frwk.bul.AbstractFwkService;
import com.eunwoosoft.frwk.fol.dto.FwkTransferObject;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;

/**
 * <pre>
 * com.eunwoosoft.comm.atfi.service.impl 
 *    |_ ComAtfiAtchFileServiceImpl.java
 * 
 * </pre>
 * @date : 2015. 2. 27. 오후 12:11:05
 * @version : 1.0
 * @author : LIG시스템 김경용
 */
@Service("comAtfiAtchFileService")
public class ComAtfiAtchFileServiceImpl extends AbstractFwkService implements ComAtfiAtchFileService {	
	
	@Resource(name="comAtfiAtchFileDao")
    private ComAtfiAtchFileDao comAtfiAtchFileDao;
	
	@Resource(name="comAtfiAtchFileHistDao")
    private ComAtfiAtchFileHistDao comAtfiAtchFileHistDao;
	
	
	/**
	 * <pre>
	 * 1. 개요 : 첨부파일목록조회_페이징 
	 * 2. 처리내용 : 1. 페이징처리된 첨부파일목록조회
	 *                  2. 첨부파일목록총건수조회
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
	 * @see oda.com.atfi.service.ComAtfiAtchFileService#atchFileListInqireWithPgng(oda.fwk.prl.request.FwkParameterMap)
	 * @param parameterMap
	 * @return
	 */
	@Override
	public FwkTransferObject atchFileListInqireWithPgng(FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();
		trans.put(ATCH_FILE_LIST, comAtfiAtchFileDao.selectAtchFileListInqireWithPgng(parameterMap));
		trans.put(ATCH_FILE_LIST_TOTCNT, comAtfiAtchFileDao.selectAtchFileListInqireTotcnt(parameterMap));
		return trans;
	}
	
	/**
	 * <pre>
	 * 1. 개요 : 첨부파일상세조회 
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
	 * @see oda.com.atfi.service.ComAtfiAtchFileService#atchFileDetailInqire(oda.fwk.prl.request.FwkParameterMap)
	 * @param parameterMap
	 * @return
	 */
	@Override
	public FwkTransferObject atchFileDetailInqire(FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();
		trans.put(ATCH_FILE, comAtfiAtchFileDao.selectAtchFileDetailInqire(parameterMap));
		return trans;
	}
	
	/**
	 * <pre>
	 * 1. 개요 : 첨부파일그룹일련번호에의한 첨부파일목록조회 
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
	 * @see oda.com.atfi.service.ComAtfiAtchFileService#atchFileListInqireByAtchFileGroupSn(oda.fwk.prl.request.FwkParameterMap)
	 * @param parameterMap
	 * @return
	 */
	@Override
	public FwkTransferObject atchFileListInqireByAtchFileGroupNo(
			FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();
		trans.put(ATCH_FILE_LIST, comAtfiAtchFileDao.selectAtchFileListInqireByAtchFileGroupNo(parameterMap));
		return trans;
	}
	
	/**
	 * <pre>
	 * 1. 개요 : 첨부파일그룹일련번호에의한 첨부파일목록조회 (외부용)
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
	 * @see oda.com.atfi.service.ComAtfiAtchFileService#atchFileListInqireByAtchFileGroupSn(oda.fwk.prl.request.FwkParameterMap)
	 * @param parameterMap
	 * @return
	 */
	@Override
	public FwkTransferObject atchFileListInqireByAtchFileGroupNoExtern(
			FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();
		trans.put(ATCH_FILE_LIST, comAtfiAtchFileDao.selectAtchFileListInqireByAtchFileGroupNoExtern(parameterMap));
		return trans;
	}
	
	/**
	 * <pre>
	 * 1. 개요 : 첨부파일삭제
	 * 2. 처리내용 : 1. 첨부파일이력등록
	 *                  2. 첨부파일삭제
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
	 * @see oda.com.atfi.service.ComAtfiAtchFileService#atchFileDelete(oda.fwk.prl.request.FwkParameterMap)
	 * @param parameterMap
	 * @return
	 */
	@Override
	public FwkTransferObject atchFileDelete(FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();
		comAtfiAtchFileHistDao.insertAtchFileHistRegist(parameterMap);
		comAtfiAtchFileDao.deleteAtchFileDelete(parameterMap);		
		return trans;
	}
}
