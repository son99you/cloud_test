package com.eunwoosoft.comm.file.dao;

import java.util.List;

import com.eunwoosoft.frwk.fol.dto.FwkDataEntity;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;

/**
 * 
 * com.eunwoosoft.comm.file.dao
 * ComAtmaAtchFileHistDao.java
 *
 * @Author : sanghoon_joen
 * @Date   : 2018. 2. 21.
 *
 */
public interface ComAtmaAtchFileHistDao {	
	
	/**
	 * <pre>
	 * 1. 개요 : 첨부파일이력 등록 
	 * 2. 처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri.com.eunwoosoft.comm.file.dao.ComAtmaAtchFileHistDao.java
	 * @Method : insertAtchFileHistRegist
	 * @author : sanghoon_joen
	 * @date : 2018. 2. 21. 
	 * @param parameterMap
	 */
	void insertAtchFileHistRegist(FwkParameterMap parameterMap);
	
	/**
	 * <pre>
	 * 1. 개요 : 첨부파일이력 등록 (파일이름으로)
	 * 2. 처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri.com.eunwoosoft.comm.file.dao.ComAtmaAtchFileHistDao.java
	 * @Method : insertAtchFileHistByNmRegist
	 * @author : sanghoon_joen
	 * @date : 2018. 2. 21. 
	 * @param parameterMap
	 */
	void insertAtchFileHistByNmRegist(FwkParameterMap parameterMap);
	
	/**
	 * <pre>
	 * 1. 개요 : 첨부파일이력목록조회_페이징
	 * 2. 처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri.com.eunwoosoft.comm.file.dao.ComAtmaAtchFileHistDao.java
	 * @Method : selectAtchFileHistListInqireWithPgng
	 * @author : sanghoon_joen
	 * @date : 2018. 2. 21. 
	 * @param parameterMap
	 */
	List<FwkDataEntity> selectAtchFileHistListInqireWithPgng(FwkParameterMap parameterMap);
	
	/**
	 * <pre>
	 * 1. 개요 : 첨부파일이력목록조회_총건수
	 * 2. 처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri.com.eunwoosoft.comm.file.dao.ComAtmaAtchFileHistDao.java
	 * @Method : selectAtchFileHistListInqireTotcnt
	 * @author : sanghoon_joen
	 * @date : 2018. 2. 21. 
	 * @param parameterMap
	 */
	long selectAtchFileHistListInqireTotcnt(FwkParameterMap parameterMap);
	
	/**
	 * <pre>
	 * 1. 개요 : 첨부파일이력상세조회 
	 * 2. 처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri.com.eunwoosoft.comm.file.dao.ComAtmaAtchFileHistDao.java
	 * @Method : selectAtchFileHistDetailInqire
	 * @author : sanghoon_joen
	 * @date : 2018. 2. 21. 
	 * @param parameterMap
	 */
	FwkDataEntity selectAtchFileHistDetailInqire(FwkParameterMap parameterMap);
	
	

}
