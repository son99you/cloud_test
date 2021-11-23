package com.eunwoosoft.ipro.noti.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.eunwoosoft.comm.file.dao.ComAtmaAtchFileDao;
import com.eunwoosoft.comm.file.service.ComAtmaAtchFileService;
import com.eunwoosoft.frwk.bul.AbstractFwkService;
import com.eunwoosoft.frwk.fol.dto.FwkDataEntity;
import com.eunwoosoft.frwk.fol.dto.FwkTransferObject;
import com.eunwoosoft.frwk.fol.util.FwkDateUtil;
import com.eunwoosoft.frwk.fol.util.FwkStringUtil;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;
import com.eunwoosoft.ipro.noti.dao.IproInNotiDao;
import com.eunwoosoft.ipro.noti.service.IproInNotiService;

/**
 *  
 * com.eunwoosoft.ipro.main.service.impl
 * IproMainLoginFormServiceImpl.java
 *
 * @Author : SungYoon_Ha
 * @Date   : 2017. 5. 30.
 *
 */
@Service(value="iproInNotiService")
public class IproInNotiServiceImpl extends AbstractFwkService implements IproInNotiService {
	private static final Logger LOG = LoggerFactory.getLogger(IproInNotiServiceImpl.class); 
	
	@Resource(name="iproInNotiDao") 
	private IproInNotiDao iproInNotiDao; 

	@Resource(name="comAtmaAtchFileService")
	private ComAtmaAtchFileService comAtmaAtchFileService;  
	
	@Resource(name="comAtmaAtchFileDao")
	private ComAtmaAtchFileDao comAtmaAtchFileDao; 
	
	@Override
	public FwkTransferObject inNoticeBoardListWithPgng(FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();
		
		//noticeBoardList
		trans.put(IproInNotiService.IN_NOTICE_BOARD_LIST,iproInNotiDao.inNoticeBoardListWithPgng(parameterMap));
		//noticeBoardListTotCnt
		trans.put(IproInNotiService.IN_NOTICE_BOARD_LIST_TOTCNT,iproInNotiDao.inNoticeBoardListTotCnt(parameterMap));
	
		return trans;
	}
	
	/**
	 * 내부 공지사항 상세 
	 */
	
	@Override
	public FwkTransferObject inNoticeBoardDetail(FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject(); 
		
		//InNoticeBoardDetail
		FwkDataEntity unityNttDetail = iproInNotiDao.inNoticeBoardDetail(parameterMap);
		trans.put(IproInNotiService.IN_NOTICE_BOARD_DETAIL, unityNttDetail);

		//첨부파일 상세조회
		if(unityNttDetail.getString("ATCHMNFL_GROUP_NO") != null){
			parameterMap.put("P_ATCHMNFL_GROUP_NO", unityNttDetail.get("ATCHMNFL_GROUP_NO")); 
			trans.put("noticeFileList", comAtmaAtchFileDao.selectAtchFileListInqireByAtchFileGroupNo(parameterMap));
		}
		 System.err.println(" <=== noticeFileList ===> " + trans.get("noticeFileList"));
		 
		 return trans; 
	} 
	
	
	/**
	 * 내부 공지사항 등록
	 * 
	 */
	@Override
	public FwkTransferObject inNoticeBoardRegist(final FwkParameterMap parameterMap, final HttpServletRequest request) throws Exception{
		FwkTransferObject trans = new FwkTransferObject();
		
		String atchFileGroupNo = parameterMap.getString("atchFileGroupNo");		
		List<Map<String, Object>> fileList = (List<Map<String, Object>>) parameterMap.get("fileList");
		parameterMap.put("atchFileGroupNo", atchFileGroupNo);
		
		//첨부파일 등록
		if(fileList != null){
			if(fileList.size() > 0){
				comAtmaAtchFileService.atchFileMapRegist(atchFileGroupNo, fileList , parameterMap);
			}  
		}
		  
		//공지사항 마스터 등록  
		iproInNotiDao.inNoticeBoardRegist(parameterMap);
		
		return trans;
	}
	
	@Override 
	public FwkTransferObject inNoticeBoardUpdt(final FwkParameterMap parameterMap) throws Exception {
		FwkTransferObject trans = new FwkTransferObject();
		
		// 오류시 Exception 처리
		try {
			parameterMap.put("P_REGIST_DT", FwkDateUtil.getCurrentDateTimeAsString()); // 등록일자
			parameterMap.put("P_UPDT_DT", FwkDateUtil.getCurrentDateTimeAsString()); // 수정일자
		
			
			String atchFileGroupNo = parameterMap.getString("atchFileGroupNo");
			
			List<Map<String, Object>> fileList = (List<Map<String, Object>>) parameterMap.get("fileList");
			parameterMap.put("P_ATCHMNFL_GROUP_NO", atchFileGroupNo); 
		
			if(fileList != null){
				if(fileList.size() > 0){
					if("Y".equals(parameterMap.getString("P_ATCH_NEW"))){
						comAtmaAtchFileService.atchFileMapRegist(atchFileGroupNo, fileList , parameterMap); 
					}else{ 
						comAtmaAtchFileService.atchFileAddRegist(atchFileGroupNo, fileList , parameterMap);
					}
				}
			} 
			
			// 삭제한 파일 DELETE_AT = 'N' 처리
			String deleteFileSn = parameterMap.getString("P_DELETE_FILE_SN");
			if(!"".equals(deleteFileSn)){
				String[] arrDeleteFileSn = deleteFileSn.split(",");
				List<Map<String, Object>> deleteFileSnList = new ArrayList<Map<String,Object>>();
				
				for (int i = 0; i < arrDeleteFileSn.length; i++) {
					FwkParameterMap pmap = new FwkParameterMap();
					pmap.put("P_ATCHMNFL_SN", arrDeleteFileSn[i]);
					deleteFileSnList.add(pmap);
				}
				comAtmaAtchFileService.atchFileDelete(deleteFileSnList);
			}
			
			//공지사항 마스터 수정
			iproInNotiDao.inNoticeBoardUpdate(parameterMap);
			
			trans.put("P_NTT_SN", parameterMap.get("P_NTT_SN"));
			
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			throw new Exception("== IproNotiServiceImpl.noticeBoardUpdt Error == ");
		}
		
		return trans;
	}
	
	
	
	@Override
	public FwkTransferObject inNoticeBoardDelete(final FwkParameterMap parameterMap){
		FwkTransferObject trans = new FwkTransferObject();
		// 삭제여부 수정
		iproInNotiDao.inNoticeBoardDelete(parameterMap);
		
		return trans;
	}
	
 
	
	
}
