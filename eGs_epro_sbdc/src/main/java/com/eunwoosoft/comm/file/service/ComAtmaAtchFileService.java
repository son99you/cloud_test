package com.eunwoosoft.comm.file.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.eunwoosoft.frwk.fol.dto.FwkTransferObject;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;

/**
 * 
 * com.eunwoosoft.comm.file.service
 * ComAtmaAtchFileService.java
 *
 * @Author : sanghoon_joen
 * @Date   : 2018. 2. 21.
 *
 */
public interface ComAtmaAtchFileService {

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
 
	FwkTransferObject atchFileRegist2(String atchFileGroupNoB, List<Map<String, Object>> fileList2 , FwkParameterMap map);
	FwkTransferObject atchFileAddRegist(String atchFileGroupNo, List<Map<String, Object>> fileList, FwkParameterMap map);
	FwkTransferObject atchFileAddRegist2(String atchFileGroupNoB, List<Map<String, Object>> fileList2, FwkParameterMap map);
	/**
	 * <pre>
	 * 1. 개요 : 첨부파일등록
	 * 2. 처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri.com.eunwoosoft.comm.file.service.ComAtmaAtchFileService.java
	 * @Method : atchFileRegist
	 * @author : sanghoon_joen
	 * @date : 2018. 2. 21. 
	 * @param atchFileGroupNo
	 * @param fileList
	 * @return
	 */
	FwkTransferObject atchFileRegist(FwkParameterMap parameterMap);
	
	/**
	 * <pre>
	 * 1.개요 : 첨부파일 등록
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri.com.eunwoosoft.comm.file.service.ComAtmaAtchFileService.java
	 * @Method : atchFileMapRegist
	 * @author : jandi_Eun
	 * @date : 2018. 2. 21. 
	 * @param atchFileGroupNo
	 * @param fileList
	 * @param map
	 * @return
	 */
	FwkTransferObject atchFileMapRegist(String atchFileGroupNo, List<Map<String, Object>> fileList , FwkParameterMap map);

	/**
	 * <pre>
	 * 1. 개요 : 첨부파일목록조회_페이징
	 * 2. 처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri.com.eunwoosoft.comm.file.service.ComAtmaAtchFileService.java
	 * @Method : atchFileListInqireWithPgng
	 * @author : sanghoon_joen
	 * @date : 2018. 2. 21. 
	 * @param atchFileGroupNo
	 * @param fileList
	 * @return
	 */
	FwkTransferObject atchFileListInqireWithPgng(final FwkParameterMap parameterMap);

	/**
	 * <pre>
	 * 1. 개요 : 첨부파일 상세조회
	 * 2. 처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri.com.eunwoosoft.comm.file.service.ComAtmaAtchFileService.java
	 * @Method : atchFileDetailInqire
	 * @author : sanghoon_joen
	 * @date : 2018. 2. 21. 
	 * @param atchFileGroupNo
	 * @param fileList
	 * @return
	 */
	FwkTransferObject atchFileDetailInqire(final FwkParameterMap parameterMap);

	/**
	 * <pre>
	 * 1. 개요 : 첨부파일그룹번호에의한 첨부파일목록조회 
	 * 2. 처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri.com.eunwoosoft.comm.file.service.ComAtmaAtchFileService.java
	 * @Method : atchFileListInqireByAtchFileGroupNo
	 * @author : sanghoon_joen
	 * @date : 2018. 2. 21. 
	 * @param atchFileGroupNo
	 * @param fileList
	 * @return
	 */
	FwkTransferObject atchFileListInqireByAtchFileGroupNo(final FwkParameterMap parameterMap);

	/**
	 * <pre>
	 * 1. 개요 : 첨부파일삭제
	 * 2. 처리내용 : 1. 첨부파일삭제
	 *                  2. 첨부파일이력등록
	 * </pre>
	 * @Location : eGs_epro_pri.com.eunwoosoft.comm.file.service.ComAtmaAtchFileService.java
	 * @Method : atchFileDelete
	 * @author : sanghoon_joen
	 * @date : 2018. 2. 21. 
	 * @param atchFileGroupNo
	 * @param fileList
	 * @return
	 */
	FwkTransferObject atchFileDelete(final List<Map<String, Object>> fileList);
	
	/**
	 * <pre>
	 * 1. 개요 : 첨부파일삭제 (파일이름으로)
	 * 2. 처리내용 : 1. 첨부파일삭제
	 *                  2. 첨부파일이력등록
	 * </pre>
	 * @Location : eGs_epro_pri.com.eunwoosoft.comm.file.service.ComAtmaAtchFileService.java
	 * @Method : atchFileDeleteByNm
	 * @author : sanghoon_joen
	 * @date : 2018. 2. 21. 
	 * @param atchFileGroupNo
	 * @param fileList
	 * @return
	 */
	FwkTransferObject atchFileDeleteByNm(final List<Map<String, Object>> fileList, String groupNoKey);

	/**
	 * <pre>
	 * 1. 개요 : 첨부파일 수정 등록
	 * 2. 처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri.com.eunwoosoft.comm.file.service.ComAtmaAtchFileService.java
	 * @Method : atchFileUpt
	 * @author : sanghoon_joen
	 * @date : 2018. 2. 21. 
	 * @param atchFileGroupNo
	 * @param fileList
	 * @return
	 */
	FwkTransferObject atchFileUpt(FwkParameterMap parameterMap);

	/**
	 * <pre>
	 * 1. 개요 : 첨부파일등록 ( 내부 -> 외부 )
	 * 2. 처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri.com.eunwoosoft.comm.file.service.ComAtmaAtchFileService.java
	 * @Method : atchFileRegistLocalToExt
	 * @author : sanghoon_joen
	 * @date : 2018. 2. 21. 
	 * @param atchFileGroupNo
	 * @param fileList
	 * @return
	 */
	FwkTransferObject atchFileRegistLocalToExt(String atchFileGroupNo, String fileSaveEsbInternPath, List<Map<String, Object>> fileList);

	/**
	 * <pre>
	 * 1. 개요 : 첨부파일복사 ( 내부 -> 외부 )
	 * 2. 처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri.com.eunwoosoft.comm.file.service.ComAtmaAtchFileService.java
	 * @Method : atchFileCopyToSend
	 * @author : sanghoon_joen
	 * @date : 2018. 2. 21. 
	 * @param atchFileGroupNo
	 * @param fileList
	 * @return
	 */
	FwkTransferObject atchFileCopyToSend(String fileSavePathPrefix, String fileSaveEsbInternPath, List<Map<String, Object>> fileList);
	
	/**
	 * <pre>
	 * 1. 개요 : 첨부파일등록 ( 내부 )
	 * 2. 처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri.com.eunwoosoft.comm.file.service.ComAtmaAtchFileService.java
	 * @Method : atchFileRegistLocal
	 * @author : sanghoon_joen
	 * @date : 2018. 2. 21. 
	 * @param atchFileGroupNo
	 * @param fileList
	 * @return
	 */
	FwkTransferObject atchFileRegistLocal(String atchFileGroupNo, List<Map<String, Object>> fileList);
	
	/**
	 * <pre>
	 * 1. 개요 : 첨부파일 수정 등록 (내부 -> 외부)
	 * 2. 처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri.com.eunwoosoft.comm.file.service.ComAtmaAtchFileService.java
	 * @Method : atchFileUptLocalToExt
	 * @author : sanghoon_joen
	 * @date : 2018. 2. 21. 
	 * @param atchFileGroupNo
	 * @param fileList
	 * @return
	 */
	FwkTransferObject atchFileUptLocalToExt(String atchFileGroupNo, String fileSaveEsbInternPath, List<Map<String, Object>> newFileList, List<Map<String, Object>> deleteFileList);
	

	/**
	 * 
	 * <pre>
	 * 1.개요 : 
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri_keri.com.eunwoosoft.comm.file.service.ComAtmaAtchFileService.java
	 * @Method : fileListRegist
	 * @author : JanDi_Eun
	 * @date : 2019. 3. 4. 
	 * @param fileGrpNo
	 * @param fileList
	 * @param parameterMap
	 * @return
	 */
	FwkTransferObject fileListRegist(String fileGrpNo, List<Map<String, Object>> fileList, FwkParameterMap parameterMap);

	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri_keri.com.eunwoosoft.comm.file.service.ComAtmaAtchFileService.java
	 * @Method : fileListAddRegist
	 * @author : JanDi_Eun
	 * @date : 2019. 3. 4. 
	 * @param fileGrpNo
	 * @param fileList
	 * @param map
	 * @return
	 */
	FwkTransferObject fileListAddRegist(String fileGrpNo, List<Map<String, Object>> fileList, FwkParameterMap map);
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 구매접수대기현황 상세 - 첨부파일 연계
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri_keri.com.eunwoosoft.comm.file.service.ComAtmaAtchFileService.java
	 * @Method : acptWaitAtchFileListByFileGrpNo
	 * @author : JanDi_Eun
	 * @date : 2019. 3. 21. 
	 * @param parameterMap
	 * @return
	 */
	FwkTransferObject acptWaitAtchFileListByFileGrpNo(FwkParameterMap parameterMap);
	
	FwkTransferObject fileInfoRegist(FwkParameterMap parameterMap,final HttpServletRequest request) throws Exception;
	FwkTransferObject fileInfoUpt(FwkParameterMap parameterMap,final HttpServletRequest request) throws Exception;
	FwkTransferObject fileListInqireByFileGrpNo(final FwkParameterMap parameterMap);
	FwkTransferObject fileListInqireByFileGrpNoAll(final FwkParameterMap parameterMap);

	
	FwkTransferObject fileContInfoRegist(FwkParameterMap parameterMap) throws Exception;
	FwkTransferObject fileContInfoChngRegist(FwkParameterMap parameterMap) throws Exception;
	FwkTransferObject fileContInfoUpdt(FwkParameterMap parameterMap) throws Exception;


	/**
	 * 
	 * 
	 * 	<pre>
	 *  1. 개요 :
	 * 	2. 처리내용 : 라온케이 관련
	 * 2020-09-23
	 *  joo
	 *	</pre>
	 *
	 * @method Name : fileInfoEtcRegist
	 * @Author : joo
	 * @Date   : 2020. 9. 23.
	 * @history : 
	 * ------------------------------------------------------------------------
	 * 변경일                                         작성자                                       변경내용
	 * ------------------------------------------------------------------------
	 * 2020. 9. 23.                 joo                최초작성
	 * ------------------------------------------------------------------------
	 *
	 *
	 *
	 * @param parameterMap
	 * @param request
	 * @return
	 * @throws Exception
	 *
	 */
	//라온케이 첨부파일 등록
	FwkTransferObject fileInfoEtcRegist(FwkParameterMap parameterMap,final HttpServletRequest request) throws Exception;
	FwkTransferObject fileInfoEtcUpt(FwkParameterMap parameterMap,final HttpServletRequest request) throws Exception;
	FwkTransferObject fileEtcListByFileGrpNo(final FwkParameterMap parameterMap);

	//라온케이 첨부파일 copy 
	FwkTransferObject fileInfoEtcCopyRegist(FwkParameterMap parameterMap,final HttpServletRequest request) throws Exception;
}
