package com.eunwoosoft.comm.file.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.esotericsoftware.minlog.Log;
import com.eunwoosoft.comm.file.dao.ComAtmaAtchFileDao;
import com.eunwoosoft.comm.file.dao.ComAtmaAtchFileHistDao;
import com.eunwoosoft.comm.file.service.ComAtmaAtchFileService;
import com.eunwoosoft.comm.util.CmmnUtil;
import com.eunwoosoft.frwk.bul.AbstractFwkService;
import com.eunwoosoft.frwk.fol.dto.FwkDataEntity;
import com.eunwoosoft.frwk.fol.dto.FwkTransferObject;
import com.eunwoosoft.frwk.fol.util.FwkDateUtil;
import com.eunwoosoft.frwk.fol.util.FwkMessageUtil;
import com.eunwoosoft.frwk.fol.util.FwkStringUtil;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;

/**
 * 
 * com.eunwoosoft.comm.file.service.impl
 * ComAtmaAtchFileServiceImpl.java
 *
 * @Author : sanghoon_joen
 * @Date   : 2018. 2. 21.
 *
 */
@Service("comAtmaAtchFileService")
public class ComAtmaAtchFileServiceImpl extends AbstractFwkService implements ComAtmaAtchFileService {	

	@SuppressWarnings("unused")
	private static final Logger LOG = LoggerFactory.getLogger(ComAtmaAtchFileServiceImpl.class);

	@Resource(name="comAtmaAtchFileDao")
	private ComAtmaAtchFileDao comAtmaAtchFileDao;

	@Resource(name="comAtmaAtchFileHistDao")
	private ComAtmaAtchFileHistDao comAtmaAtchFileHistDao;
	

	/**
	 * 
	 */
	@Override
	public FwkTransferObject fileInfoRegist(final FwkParameterMap parameterMap, final HttpServletRequest request) throws Exception{
		FwkTransferObject trans = new FwkTransferObject();
		String[] extList =  "xls,xlsx,doc,docx,ppt,pptx,hwp,pdf,jpg,jpeg,gif,bmp,png,dwg,zip,wmv,avi,mkv,mpeg,mp4,webm,XLS,XLSX,DOC,DOCX,PPT,PPTX,HWP,PDF,JPG,JPEG,PNG,GIF,BMP,DWG,ZIP,WMV,AVI,MKV,MPEG,MP4,WEBM".split(",");
		trans.put("fileUploadCode","fileSucc");
		
		/*================================첨부파일 insert===============================*/
		String atchFileGroupNo =  "";
				
		if(parameterMap.get("P_FILE_GRP_NO") != null && !"".equals(parameterMap.getString("P_FILE_GRP_NO"))){
			
			atchFileGroupNo = parameterMap.getString("P_FILE_GRP_NO");
			if("Y".equals(parameterMap.get("P_VEND_YN"))){	// 계약상대자첨부파일
				atchFileGroupNo = FwkStringUtil.replace(UUID.randomUUID().toString(), "-", "");
			}
		}else{
			atchFileGroupNo = FwkStringUtil.replace(UUID.randomUUID().toString(), "-", "");
		}
		
		//System.err.println("atchFileGroupNo = > " + atchFileGroupNo);
		parameterMap.put("atchFileGroupNo", atchFileGroupNo);
		parameterMap.put("P_SYSDATE", FwkDateUtil.getCurrentDateAsString("yyyyMM"));
		
		Object P_SYS_FILE_NM = parameterMap.get("P_SYS_FILE_NM");
		Object P_SV_FILE_NM = parameterMap.get("P_SV_FILE_NM");
		Object P_FILE_SZ = parameterMap.get("P_FILE_SZ");
		Object P_FILE_LCTN = parameterMap.get("P_FILE_LCTN");
		
		//System.err.println("P_SYS_FILE_NM = > " + P_SYS_FILE_NM);
		//System.err.println("P_SV_FILE_NM = > " + P_SV_FILE_NM);
		//System.err.println("FwkMessageUtil.getMessageAdd0(ESTT.SYMM.KEY) : " + FwkMessageUtil.getMessageAdd("ESTT.SYMM.KEY"));
		//System.err.println("FwkMessageUtil.getMessageAdd1(ESTT.SYMM.KEY) : " + FwkMessageUtil.getMessageAdd("ESTT.SYMM.KEY", "ESTT.SYMM.KEY_2"));
		//System.err.println("P_FILE_LCTN = > " + P_FILE_LCTN);*/
		if(P_SYS_FILE_NM != null){
			if(P_SYS_FILE_NM instanceof String){
				
				String fileUploadNm = (String) FwkStringUtil.castEuc2Iso(P_SYS_FILE_NM);
				String fileUploadExt = fileUploadNm.substring(fileUploadNm.lastIndexOf('.')+1,fileUploadNm.length());
				boolean flag = true;
				for (int i = 0; i < extList.length; i++) {
					if(fileUploadExt.equals(extList[i])){
						flag = false;
					}
				}
				if(flag){
					trans.put("fileUploadCode","fileFail");
				}
				if(!flag){
					//덱스트 첨부파일 저장
					parameterMap.put("P_SYS_FILE_NM",FwkStringUtil.castEuc2Iso(P_SYS_FILE_NM));
					comAtmaAtchFileDao.insertDextAtchFileRegist(parameterMap);
				
				
					//첨부파일 암호화 적용
					String StrFullpath =  (String) parameterMap.get("P_FILE_LCTN")+ File.separator + (String) parameterMap.get("P_SV_FILE_NM");
					//EwCipher.seedFileEncrypt(StrFullpath, FwkMessageUtil.getMessageAdd("ESTT.SYMM.KEY"));
//					EcCipher.seedFileEncrypt(StrFullpath);
				}
				
			}else if(P_SYS_FILE_NM instanceof String[]){
				String[] P_SYS_FILE_NM_LIST = (String[]) P_SYS_FILE_NM;
				String[] P_SV_FILE_NM_LIST = (String[]) P_SV_FILE_NM;
				String[] P_FILE_SZ_LIST = (String[]) P_FILE_SZ;
				String[] P_FILE_LCTN_LIST = (String[]) P_FILE_LCTN;
				
				String fileUploadNm = "";
				String fileUploadExt = "";
				boolean flag = true;
				for(int idx = 0; idx < P_SYS_FILE_NM_LIST.length; idx++){
					fileUploadNm = (String)FwkStringUtil.castEuc2Iso(P_SYS_FILE_NM_LIST[idx]);
					fileUploadExt = fileUploadNm.substring(fileUploadNm.lastIndexOf('.')+1,fileUploadNm.length());
					flag = true;
					for (int i = 0; i < extList.length; i++) {
						if(fileUploadExt.equals(extList[i])){
							flag = false;
						}
					}
					if(flag){
						trans.put("fileUploadCode","fileFail");
						break;
					}
				}
				if(!flag){
					for(int idx =0; idx < P_SYS_FILE_NM_LIST.length; idx++){
						if(P_SYS_FILE_NM_LIST != null && P_SYS_FILE_NM_LIST[idx] != null){
							parameterMap.put("P_SYS_FILE_NM", FwkStringUtil.castEuc2Iso(P_SYS_FILE_NM_LIST[idx]));
						}
						if(P_SV_FILE_NM_LIST != null && P_SV_FILE_NM_LIST[idx] != null){ 
							parameterMap.put("P_SV_FILE_NM", P_SV_FILE_NM_LIST[idx]);
						}
						if(P_FILE_SZ_LIST != null && P_FILE_SZ_LIST[idx] != null){ 
							parameterMap.put("P_FILE_SZ", P_FILE_SZ_LIST[idx]);
						}
						if(P_FILE_LCTN_LIST != null && P_FILE_LCTN_LIST[idx] != null){
							parameterMap.put("P_FILE_LCTN", P_FILE_LCTN_LIST[idx]);
						}
							
						//덱스트 첨부파일 저장
						comAtmaAtchFileDao.insertDextAtchFileRegist(parameterMap);
						
						//첨부파일 암호화 적용
						String StrFullpath =  (String) parameterMap.get("P_FILE_LCTN")+ File.separator + (String) parameterMap.get("P_SV_FILE_NM");
						//EwCipher.seedFileEncrypt(StrFullpath, FwkMessageUtil.getMessageAdd("ESTT.SYMM.KEY"));
//						EcCipher.seedFileEncrypt(StrFullpath);
					}
				}
			}else if(P_SYS_FILE_NM instanceof ArrayList){
					
				ArrayList P_SYS_FILE_NM_LIST = (ArrayList) P_SYS_FILE_NM;
				ArrayList P_SV_FILE_NM_LIST = (ArrayList) P_SV_FILE_NM;
				ArrayList P_FILE_SZ_LIST = (ArrayList) P_FILE_SZ;
				ArrayList P_FILE_LCTN_LIST = (ArrayList) P_FILE_LCTN;
				
				String fileUploadNm = "";
				String fileUploadExt = "";
				boolean flag = true;
				for(int idx = 0; idx < P_SYS_FILE_NM_LIST.size(); idx++){
					fileUploadNm = (String)FwkStringUtil.castEuc2Iso(P_SYS_FILE_NM_LIST.get(idx));
					fileUploadExt = fileUploadNm.substring(fileUploadNm.lastIndexOf('.')+1,fileUploadNm.length());
					flag = true;
					for (int i = 0; i < extList.length; i++) {
						if(fileUploadExt.equals(extList[i])){
							flag = false;
						}
					}
					if(flag){
						trans.put("fileUploadCode","fileFail");
						break;
					}
				}
				if(!flag){
					for(int idx =0; idx < P_SYS_FILE_NM_LIST.size(); idx++){
						if(P_SYS_FILE_NM_LIST != null && P_SYS_FILE_NM_LIST.get(idx) != null){
							parameterMap.put("P_SYS_FILE_NM", FwkStringUtil.castEuc2Iso(P_SYS_FILE_NM_LIST.get(idx)));
						}
						if(P_SV_FILE_NM_LIST != null && P_SV_FILE_NM_LIST.get(idx) != null){ 
							parameterMap.put("P_SV_FILE_NM", P_SV_FILE_NM_LIST.get(idx));
						}
						if(P_FILE_SZ_LIST != null && P_FILE_SZ_LIST.get(idx) != null){ 
							parameterMap.put("P_FILE_SZ", P_FILE_SZ_LIST.get(idx));
						}
						if(P_FILE_LCTN_LIST != null && P_FILE_LCTN_LIST.get(idx) != null){
							parameterMap.put("P_FILE_LCTN", P_FILE_LCTN_LIST.get(idx));
						}
							
						//덱스트 첨부파일 저장
						comAtmaAtchFileDao.insertDextAtchFileRegist(parameterMap);
						
						//첨부파일 암호화 적용
						String StrFullpath =  (String) parameterMap.get("P_FILE_LCTN")+ File.separator + (String) parameterMap.get("P_SV_FILE_NM");
						//EwCipher.seedFileEncrypt(StrFullpath, FwkMessageUtil.getMessageAdd("ESTT.SYMM.KEY"));
//						EcCipher.seedFileEncrypt(StrFullpath);
				}
			}
		}
	}	
		trans.put("atchFileGroupNo", parameterMap.get("atchFileGroupNo"));
	/*================================첨부파일 insert END===============================*/
		return trans;
	}
	/**
	 * 
	 * 
	 * 	<pre>
	 *  1. 개요 :
	 * 	2. 처리내용 : 라온케이 첨부파일 저장
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
	@Override
	public FwkTransferObject fileInfoEtcRegist(final FwkParameterMap parameterMap, final HttpServletRequest request) throws Exception{
		FwkTransferObject trans = new FwkTransferObject();
		String[] extList =  "xls,xlsx,doc,docx,ppt,pptx,hwp,pdf,jpg,jpeg,gif,bmp,png,dwg,zip,wmv,avi,mkv,mpeg,mp4,webm,XLS,XLSX,DOC,DOCX,PPT,PPTX,HWP,PDF,JPG,JPEG,PNG,GIF,BMP,DWG,ZIP,WMV,AVI,MKV,MPEG,MP4,WEBM".split(",");
		trans.put("fileUploadCode","fileSucc");
		
		/*================================첨부파일 insert===============================*/
		String atchFileGroupNo =  "";
				
		if(parameterMap.get("P_FILE_GRP_NO_ETC") != null && !"".equals(parameterMap.getString("P_FILE_GRP_NO_ETC"))){
			
			atchFileGroupNo = parameterMap.getString("P_FILE_GRP_NO_ETC");
			if("Y".equals(parameterMap.get("P_VEND_YN"))){	// 계약상대자첨부파일
				atchFileGroupNo = FwkStringUtil.replace(UUID.randomUUID().toString(), "-", "");
			}
		}else{
			atchFileGroupNo = FwkStringUtil.replace(UUID.randomUUID().toString(), "-", "");
		}
		
		parameterMap.put("atchFileGroupNo", atchFileGroupNo);
		parameterMap.put("P_SYSDATE", FwkDateUtil.getCurrentDateAsString("yyyyMM"));
		
		Object P_SYS_FILE_NM = parameterMap.get("P_SYS_FILE_NM");
		Object P_SV_FILE_NM = parameterMap.get("P_SV_FILE_NM");
		Object P_FILE_SZ = parameterMap.get("P_FILE_SZ");
		Object P_FILE_LCTN = parameterMap.get("P_FILE_LCTN");
		parameterMap.put("P_FILE_DOC_SECD", "ETC");
		
		if(P_SYS_FILE_NM != null){
			if(P_SYS_FILE_NM instanceof String){
				
				String fileUploadNm = (String) FwkStringUtil.castEuc2Iso(P_SYS_FILE_NM);
				String fileUploadExt = fileUploadNm.substring(fileUploadNm.lastIndexOf('.')+1,fileUploadNm.length());
				boolean flag = true;
				for (int i = 0; i < extList.length; i++) {
					if(fileUploadExt.equals(extList[i])){
						flag = false;
					}
				}
				if(flag){
					trans.put("fileUploadCode","fileFail");
				}
				if(!flag){
					//덱스트 첨부파일 저장
					parameterMap.put("P_SYS_FILE_NM",FwkStringUtil.castEuc2Iso(P_SYS_FILE_NM));
					comAtmaAtchFileDao.insertEtcAtchFileRegist(parameterMap);
				}
				
			}else if(P_SYS_FILE_NM instanceof String[]){
				String[] P_SYS_FILE_NM_LIST = (String[]) P_SYS_FILE_NM;
				String[] P_SV_FILE_NM_LIST = (String[]) P_SV_FILE_NM;
				String[] P_FILE_SZ_LIST = (String[]) P_FILE_SZ;
				String[] P_FILE_LCTN_LIST = (String[]) P_FILE_LCTN;
				
				String fileUploadNm = "";
				String fileUploadExt = "";
				boolean flag = true;
				for(int idx = 0; idx < P_SYS_FILE_NM_LIST.length; idx++){
					fileUploadNm = (String)FwkStringUtil.castEuc2Iso(P_SYS_FILE_NM_LIST[idx]);
					fileUploadExt = fileUploadNm.substring(fileUploadNm.lastIndexOf('.')+1,fileUploadNm.length());
					flag = true;
					for (int i = 0; i < extList.length; i++) {
						if(fileUploadExt.equals(extList[i])){
							flag = false;
						}
					}
					if(flag){
						trans.put("fileUploadCode","fileFail");
						break;
					}
				}
				if(!flag){
					for(int idx =0; idx < P_SYS_FILE_NM_LIST.length; idx++){
						if(P_SYS_FILE_NM_LIST != null && P_SYS_FILE_NM_LIST[idx] != null){
							parameterMap.put("P_SYS_FILE_NM", FwkStringUtil.castEuc2Iso(P_SYS_FILE_NM_LIST[idx]));
						}
						if(P_SV_FILE_NM_LIST != null && P_SV_FILE_NM_LIST[idx] != null){ 
							parameterMap.put("P_SV_FILE_NM", P_SV_FILE_NM_LIST[idx]);
						}
						if(P_FILE_SZ_LIST != null && P_FILE_SZ_LIST[idx] != null){ 
							parameterMap.put("P_FILE_SZ", P_FILE_SZ_LIST[idx]);
						}
						if(P_FILE_LCTN_LIST != null && P_FILE_LCTN_LIST[idx] != null){
							parameterMap.put("P_FILE_LCTN", P_FILE_LCTN_LIST[idx]);
						}
							
						//덱스트 첨부파일 저장
						comAtmaAtchFileDao.insertEtcAtchFileRegist(parameterMap);
					}
				}
			}else if(P_SYS_FILE_NM instanceof ArrayList){
					
				ArrayList P_SYS_FILE_NM_LIST = (ArrayList) P_SYS_FILE_NM;
				ArrayList P_SV_FILE_NM_LIST = (ArrayList) P_SV_FILE_NM;
				ArrayList P_FILE_SZ_LIST = (ArrayList) P_FILE_SZ;
				ArrayList P_FILE_LCTN_LIST = (ArrayList) P_FILE_LCTN;
				
				String fileUploadNm = "";
				String fileUploadExt = "";
				boolean flag = true;
				for(int idx = 0; idx < P_SYS_FILE_NM_LIST.size(); idx++){
					fileUploadNm = (String)FwkStringUtil.castEuc2Iso(P_SYS_FILE_NM_LIST.get(idx));
					fileUploadExt = fileUploadNm.substring(fileUploadNm.lastIndexOf('.')+1,fileUploadNm.length());
					flag = true;
					for (int i = 0; i < extList.length; i++) {
						if(fileUploadExt.equals(extList[i])){
							flag = false;
						}
					}
					if(flag){
						trans.put("fileUploadCode","fileFail");
						break;
					}
				}
				if(!flag){
					for(int idx =0; idx < P_SYS_FILE_NM_LIST.size(); idx++){
						if(P_SYS_FILE_NM_LIST != null && P_SYS_FILE_NM_LIST.get(idx) != null){
							parameterMap.put("P_SYS_FILE_NM", FwkStringUtil.castEuc2Iso(P_SYS_FILE_NM_LIST.get(idx)));
						}
						if(P_SV_FILE_NM_LIST != null && P_SV_FILE_NM_LIST.get(idx) != null){ 
							parameterMap.put("P_SV_FILE_NM", P_SV_FILE_NM_LIST.get(idx));
						}
						if(P_FILE_SZ_LIST != null && P_FILE_SZ_LIST.get(idx) != null){ 
							parameterMap.put("P_FILE_SZ", P_FILE_SZ_LIST.get(idx));
						}
						if(P_FILE_LCTN_LIST != null && P_FILE_LCTN_LIST.get(idx) != null){
							parameterMap.put("P_FILE_LCTN", P_FILE_LCTN_LIST.get(idx));
						}
							
						//덱스트 첨부파일 저장
						comAtmaAtchFileDao.insertEtcAtchFileRegist(parameterMap);
						
				}
			}
		}
	}	
		trans.put("atchFileGroupNo", parameterMap.get("atchFileGroupNo"));
	/*================================첨부파일 insert END===============================*/
		return trans; 
	}
	
	
	
	/**
	 * 
	 * 
	 * 	<pre>
	 *  1. 개요 :
	 * 	2. 처리내용 : 라온케이 copy 
	 *
	 *	</pre>
	 *
	 * @method Name : fileInfoEtcCopyRegist
	 * @Author : joo
	 * @Date   : 2020. 9. 24.
	 * @history : 
	 * ------------------------------------------------------------------------
	 * 변경일                                         작성자                                       변경내용
	 * ------------------------------------------------------------------------
	 * 2020. 9. 24.                 joo                최초작성
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
	@Override
	public FwkTransferObject fileInfoEtcCopyRegist(final FwkParameterMap parameterMap, final HttpServletRequest request) throws Exception{
		FwkTransferObject trans = new FwkTransferObject();
		String[] extList =  "xls,xlsx,doc,docx,ppt,pptx,hwp,pdf,jpg,jpeg,gif,bmp,png,dwg,zip,wmv,avi,mkv,mpeg,mp4,webm,XLS,XLSX,DOC,DOCX,PPT,PPTX,HWP,PDF,JPG,JPEG,PNG,GIF,BMP,DWG,ZIP,WMV,AVI,MKV,MPEG,MP4,WEBM".split(",");
		trans.put("fileUploadCode","fileSucc");
		
		/*================================첨부파일 insert===============================*/
		String atchFileGroupNo =  "";
				
		if(parameterMap.get("P_NEW_FILE_GRP_NO_ETC") != null && !"".equals(parameterMap.getString("P_NEW_FILE_GRP_NO_ETC"))){
			
			atchFileGroupNo = parameterMap.getString("P_NEW_FILE_GRP_NO_ETC");
			if("Y".equals(parameterMap.get("P_VEND_YN"))){	// 계약상대자첨부파일
				atchFileGroupNo = FwkStringUtil.replace(UUID.randomUUID().toString(), "-", "");
			}
		}else{
			atchFileGroupNo = FwkStringUtil.replace(UUID.randomUUID().toString(), "-", "");
		}
		
		System.err.println("atchFileGroupNo=============" + atchFileGroupNo);
		parameterMap.put("atchFileGroupNo", atchFileGroupNo);
		parameterMap.put("P_SYSDATE", FwkDateUtil.getCurrentDateAsString("yyyyMM"));
		
		String contextPath = "";
		contextPath = parameterMap.getString("P_CONTEXT_PATH");
		
		List<Map<String, Object>> fileCopy = fileCopy(parameterMap.getString("P_ORG_FILE_GRP_NO_ETC"), contextPath, parameterMap);
		
		for (int i = 0; i < fileCopy.size(); i++) {
			FwkParameterMap temp = new FwkParameterMap();
			temp = (FwkParameterMap) fileCopy.get(i);
			temp.put("P_FILE_GRP_NO", atchFileGroupNo);
			temp.put("P_FILE_DOC_SECD", "ETC");
			//T_MM_FILE_MST 저장
			comAtmaAtchFileDao.insertAtchFileRegistOne(temp);
		}
		
		//COPY  END
		/**
		 * 새로 추가한 첨부파일 추가
		 * 
		 */
		Object P_SYS_FILE_NM = parameterMap.get("P_SYS_FILE_NM");
		Object P_SV_FILE_NM = parameterMap.get("P_SV_FILE_NM");
		Object P_FILE_SZ = parameterMap.get("P_FILE_SZ");
		Object P_FILE_LCTN = parameterMap.get("P_FILE_LCTN");
		parameterMap.put("P_FILE_DOC_SECD", "ETC");
		
		if(P_SYS_FILE_NM != null){
			System.err.println("P_SYS_FILE_NM=============" );
			if(P_SYS_FILE_NM instanceof String){
				System.err.println("P_SYS_FILE_NM String=============" );
				
				String fileUploadNm = (String) FwkStringUtil.castEuc2Iso(P_SYS_FILE_NM);
				String fileUploadExt = fileUploadNm.substring(fileUploadNm.lastIndexOf('.')+1,fileUploadNm.length());
				boolean flag = true;
				for (int i = 0; i < extList.length; i++) {
					if(fileUploadExt.equals(extList[i])){
						flag = false;
					}
				}
				if(flag){
					trans.put("fileUploadCode","fileFail");
				}
				if(!flag){
					//덱스트 첨부파일 저장
					parameterMap.put("P_SYS_FILE_NM",FwkStringUtil.castEuc2Iso(P_SYS_FILE_NM));
					comAtmaAtchFileDao.insertEtcAtchFileRegist(parameterMap);
				}
				
			}else if(P_SYS_FILE_NM instanceof String[]){ 
				System.err.println("P_SYS_FILE_NM String[]=============" );
				String[] P_SYS_FILE_NM_LIST = (String[]) P_SYS_FILE_NM;
				String[] P_SV_FILE_NM_LIST = (String[]) P_SV_FILE_NM;
				String[] P_FILE_SZ_LIST = (String[]) P_FILE_SZ;
				String[] P_FILE_LCTN_LIST = (String[]) P_FILE_LCTN;
				
				String fileUploadNm = "";
				String fileUploadExt = "";
				boolean flag = true;
				for(int idx = 0; idx < P_SYS_FILE_NM_LIST.length; idx++){
					fileUploadNm = (String)FwkStringUtil.castEuc2Iso(P_SYS_FILE_NM_LIST[idx]);
					fileUploadExt = fileUploadNm.substring(fileUploadNm.lastIndexOf('.')+1,fileUploadNm.length());
					flag = true;
					for (int i = 0; i < extList.length; i++) {
						if(fileUploadExt.equals(extList[i])){
							flag = false;
						}
					}
					if(flag){
						trans.put("fileUploadCode","fileFail");
						break;
					}
				}
				if(!flag){
					for(int idx =0; idx < P_SYS_FILE_NM_LIST.length; idx++){
						if(P_SYS_FILE_NM_LIST != null && P_SYS_FILE_NM_LIST[idx] != null){
							parameterMap.put("P_SYS_FILE_NM", FwkStringUtil.castEuc2Iso(P_SYS_FILE_NM_LIST[idx]));
						}
						if(P_SV_FILE_NM_LIST != null && P_SV_FILE_NM_LIST[idx] != null){ 
							parameterMap.put("P_SV_FILE_NM", P_SV_FILE_NM_LIST[idx]);
						}
						if(P_FILE_SZ_LIST != null && P_FILE_SZ_LIST[idx] != null){ 
							parameterMap.put("P_FILE_SZ", P_FILE_SZ_LIST[idx]);
						}
						if(P_FILE_LCTN_LIST != null && P_FILE_LCTN_LIST[idx] != null){
							parameterMap.put("P_FILE_LCTN", P_FILE_LCTN_LIST[idx]);
						}
							
						//덱스트 첨부파일 저장
						comAtmaAtchFileDao.insertEtcAtchFileRegist(parameterMap);
					}
				}
			}else if(P_SYS_FILE_NM instanceof ArrayList){
				System.err.println("P_SYS_FILE_NM ArrayList=============" );
					
				ArrayList P_SYS_FILE_NM_LIST = (ArrayList) P_SYS_FILE_NM;
				ArrayList P_SV_FILE_NM_LIST = (ArrayList) P_SV_FILE_NM;
				ArrayList P_FILE_SZ_LIST = (ArrayList) P_FILE_SZ;
				ArrayList P_FILE_LCTN_LIST = (ArrayList) P_FILE_LCTN;
				
				String fileUploadNm = "";
				String fileUploadExt = "";
				boolean flag = true;
				for(int idx = 0; idx < P_SYS_FILE_NM_LIST.size(); idx++){
					fileUploadNm = (String)FwkStringUtil.castEuc2Iso(P_SYS_FILE_NM_LIST.get(idx));
					fileUploadExt = fileUploadNm.substring(fileUploadNm.lastIndexOf('.')+1,fileUploadNm.length());
					flag = true;
					for (int i = 0; i < extList.length; i++) {
						if(fileUploadExt.equals(extList[i])){
							flag = false;
						}
					}
					if(flag){
						trans.put("fileUploadCode","fileFail");
						break;
					}
				}
				if(!flag){
					for(int idx =0; idx < P_SYS_FILE_NM_LIST.size(); idx++){
						if(P_SYS_FILE_NM_LIST != null && P_SYS_FILE_NM_LIST.get(idx) != null){
							parameterMap.put("P_SYS_FILE_NM", FwkStringUtil.castEuc2Iso(P_SYS_FILE_NM_LIST.get(idx)));
						}
						if(P_SV_FILE_NM_LIST != null && P_SV_FILE_NM_LIST.get(idx) != null){ 
							parameterMap.put("P_SV_FILE_NM", P_SV_FILE_NM_LIST.get(idx));
						}
						if(P_FILE_SZ_LIST != null && P_FILE_SZ_LIST.get(idx) != null){ 
							parameterMap.put("P_FILE_SZ", P_FILE_SZ_LIST.get(idx));
						}
						if(P_FILE_LCTN_LIST != null && P_FILE_LCTN_LIST.get(idx) != null){
							parameterMap.put("P_FILE_LCTN", P_FILE_LCTN_LIST.get(idx));
						}
							
						//덱스트 첨부파일 저장
						comAtmaAtchFileDao.insertEtcAtchFileRegist(parameterMap);
						
				}
			}
		}
	}	
		trans.put("atchFileGroupNo", parameterMap.get("atchFileGroupNo"));
	/*================================첨부파일 insert END===============================*/
		return trans; 
	}
	
	
	private List<Map<String, Object>> fileCopy(String copyFileGrpNo, String contextPath, final FwkParameterMap parameterMap) throws Exception{
		System.err.println("filecopty start");
		List<Map<String, Object>> returnList = new ArrayList<Map<String,Object>>();
		
		if(copyFileGrpNo != null) {
			
			FwkParameterMap param = new FwkParameterMap();
			
			String root = FwkMessageUtil.getMessage("EW.SYS.FILE.DIR");
			String fileDirPath = root + FwkDateUtil.getCurrentDateAsString("yyyy") + File.separator + FwkDateUtil.getCurrentDateAsString("MM") + File.separator + FwkDateUtil.getCurrentDateAsString("dd") + File.separator + contextPath; // 파일 경로
			
			File fileDir = null;			
			
			if(fileDirPath != null){
				fileDir = new File(FilenameUtils.getFullPath(fileDirPath), FilenameUtils.getName(fileDirPath));
			}
			
			boolean mkdirSuccess = true;
			if(fileDir != null && !fileDir.isDirectory()){
				fileDir.setExecutable(true);
				fileDir.setReadable(true);
				fileDir.setWritable(true);
				mkdirSuccess = fileDir.mkdirs();
			}
			
			FileInputStream inputStream = null; 
			FileOutputStream outputStream = null; 
			FileChannel fcout = null; 
			FileChannel fcin = null; 
			try {
		   			param.put("P_FILE_GRP_NO", copyFileGrpNo);
		   			
		   			// 라온케이
		   			// 	DEL_SN 값 제외하고 SELECT INSERT
					Object delFileSn = parameterMap.get("P_FILE_SN_DEL");
					if(delFileSn != null){
						if(delFileSn instanceof String){
							String snArray = (String) delFileSn;
							List<Map<String, Object>> delList = new ArrayList<Map<String,Object>>();
							FwkParameterMap delMap = new FwkParameterMap();	//NOPMD

							delMap.put("P_FILE_SN_DEL", snArray);
							delList.add(delMap);
							
							param.put("delList", delList);
							
						}else if(delFileSn instanceof String[]){ 
							//String[] snArray = ((String) delFileSn).split(",");
							String[] snArray =parameterMap.getString("P_FILE_SN_DEL").split(",");
							List<Map<String, Object>> delList = new ArrayList<Map<String,Object>>();
							for (int i = 0; i < snArray.length; i++) {
								FwkParameterMap delMap = new FwkParameterMap();	//NOPMD
								delMap.put("P_FILE_SN_DEL", snArray[i]);
								delList.add(delMap);
							}
							param.put("delList", delList);
		
						}else if(delFileSn instanceof ArrayList){
							
							List<String> snArray  = (List<String>) delFileSn;
							List<Map<String, Object>> delList = new ArrayList<Map<String,Object>>();

							for (int i = 0; i < snArray.size(); i++) {
								FwkParameterMap delMap = new FwkParameterMap();	//NOPMD
								delMap.put("P_FILE_SN_DEL", snArray.get(i));
								delList.add(delMap);
							} 
							param.put("delList", delList);
						}
					}
					//P_FILE_SN_DEL값 제외하고  SELECT
		   			List<FwkDataEntity> list = comAtmaAtchFileDao.selectCopyAtchFile(param);
		   			
		   			System.err.println("<= copy list size = >  " + list.size()); 
		   			for(int i = 0; i< list.size(); i++){
			   				
			   			String copyPath = list.get(i).getString("FILE_LCTN");
			   			String copyNm = list.get(i).getString("SV_FILE_NM");
			   			String ext = copyNm.substring(copyNm.lastIndexOf('.')+1).toLowerCase();
			   			
			   			String inputNm = copyNm;
			   			String outputNm = FwkStringUtil.replace(UUID.randomUUID().toString(), "-", "")+"."+ext;
			   			
			   			inputStream = new FileInputStream(copyPath+File.separator+inputNm);         
			   			outputStream = new FileOutputStream(fileDirPath+File.separator+outputNm);
		
			   			fcin =  inputStream.getChannel();
			   			fcout = outputStream.getChannel();
		
			   			long size = fcin.size();
		
			   			fcin.transferTo(0, size, fcout);
			   			
			   			FwkParameterMap map = new FwkParameterMap();
			   			map.put("P_FILE_SZ", list.get(i).getInt("FILE_SZ")); 										//용량
						map.put("P_SV_FILE_NM",  outputNm); 					//파일변환명
						map.put("P_SYS_FILE_NM",   list.get(i).getString("SYS_FILE_NM")); 			//파일명
						map.put("P_FILE_LCTN",  fileDirPath );										//경로
						map.put("P_FILE_DOC_SECD", list.get(i).getString("FILE_DOC_SECD")); 	//파일구분
						map.put("P_TSK_VKEY3", list.get(i).getString("TSK_VKEY3")); 	//view 여부
						returnList.add(map);
		   			}
			}finally {
				if(fcout != null) {
					fcout.close();
				}
				if(fcin != null) {
					fcin.close();
				}
				if(outputStream != null) {
					outputStream.close();
				}
				if(inputStream != null) {
					inputStream.close();
				}
			}
		}
   		return returnList;
   	}
	
	
	/**
	 * 라온케이 첨부파일 수정 
	 * 2020-09-23
	 * joo
	 * 
	 */
	@Override
	public FwkTransferObject fileInfoEtcUpt(final FwkParameterMap parameterMap, final HttpServletRequest request) throws Exception{
		FwkTransferObject trans = new FwkTransferObject();
		String[] extList =  "xls,xlsx,doc,docx,ppt,pptx,hwp,pdf,jpg,jpeg,gif,bmp,png,dwg,zip,wmv,avi,mkv,mpeg,mp4,webm,XLS,XLSX,DOC,DOCX,PPT,PPTX,HWP,PDF,JPG,JPEG,PNG,GIF,BMP,DWG,ZIP,WMV,AVI,MKV,MPEG,MP4,WEBM".split(",");
		trans.put("fileUploadCode","fileSucc");
		String atchFileGroupNo =  "";
		    
		if(parameterMap.get("P_FILE_GRP_NO_ETC") != null && !"".equals(parameterMap.getString("P_FILE_GRP_NO_ETC"))){
			atchFileGroupNo = parameterMap.getString("P_FILE_GRP_NO_ETC");
		}else{
			atchFileGroupNo = FwkStringUtil.replace(UUID.randomUUID().toString(), "-", "");
		}
		parameterMap.put("atchFileGroupNo", atchFileGroupNo);
		parameterMap.put("P_SYSDATE", FwkDateUtil.getCurrentDateAsString("yyyyMM"));
		
		Object P_FILE_SN = parameterMap.get("P_FILE_SN_DEL");
		if(P_FILE_SN != null) {
			if(P_FILE_SN instanceof String){
			//덱스트 첨부파일 삭제
				parameterMap.put("P_FILE_SN", (String)P_FILE_SN);
				comAtmaAtchFileDao.deleteEtcFileDelete(parameterMap);	
			}else if(P_FILE_SN instanceof String[]){ 
				String[] P_FILE_SN_LIST = (String[]) P_FILE_SN;

				for(int idx =0; idx < P_FILE_SN_LIST.length; idx++){
					if(P_FILE_SN_LIST != null && P_FILE_SN_LIST[idx] != null){
						parameterMap.put("P_FILE_SN", P_FILE_SN_LIST[idx]);
					}
					comAtmaAtchFileDao.deleteEtcFileDelete(parameterMap);	
				}
			}else if(P_FILE_SN instanceof ArrayList){
				ArrayList P_FILE_SN_LIST = (ArrayList) P_FILE_SN;
				for(int idx =0; idx < P_FILE_SN_LIST.size(); idx++){
					if(P_FILE_SN_LIST != null && P_FILE_SN_LIST.get(idx) != null){
						parameterMap.put("P_FILE_SN", P_FILE_SN_LIST.get(idx));
					}
					comAtmaAtchFileDao.deleteEtcFileDelete(parameterMap);	
				}
			}
		}
	
		Object P_SYS_FILE_NM = parameterMap.get("P_SYS_FILE_NM");
		Object P_SV_FILE_NM = parameterMap.get("P_SV_FILE_NM");
		Object P_FILE_SZ = parameterMap.get("P_FILE_SZ");
		Object P_FILE_LCTN = parameterMap.get("P_FILE_LCTN");
		parameterMap.put("P_FILE_DOC_SECD", "ETC");
		
		if(P_SYS_FILE_NM != null) {
			if(P_SYS_FILE_NM instanceof String){
				String fileUploadNm = (String) FwkStringUtil.castEuc2Iso(P_SYS_FILE_NM);
				String fileUploadExt = fileUploadNm.substring(fileUploadNm.lastIndexOf('.')+1,fileUploadNm.length());
				boolean flag = true;
				for (int i = 0; i < extList.length; i++) {
					if(fileUploadExt.equals(extList[i])){
						flag = false;
					}
				}
				if(flag){
					trans.put("fileUploadCode","fileFail");
				}
				if(!flag){
					//덱스트 첨부파일 저장
					parameterMap.put("P_SYS_FILE_NM",FwkStringUtil.castEuc2Iso(P_SYS_FILE_NM));
					comAtmaAtchFileDao.insertEtcAtchFileRegist(parameterMap);
					
				}
			}else if(P_SYS_FILE_NM instanceof String[]){
				String[] P_SYS_FILE_NM_LIST = (String[]) P_SYS_FILE_NM;
				String[] P_SV_FILE_NM_LIST = (String[]) P_SV_FILE_NM;
				String[] P_FILE_SZ_LIST = (String[]) P_FILE_SZ;
				String[] P_FILE_LCTN_LIST = (String[]) P_FILE_LCTN;
				
				String fileUploadNm = "";
				String fileUploadExt = "";
				boolean flag = true;
				for(int idx = 0; idx < P_SYS_FILE_NM_LIST.length; idx++){
					fileUploadNm = (String)FwkStringUtil.castEuc2Iso(P_SYS_FILE_NM_LIST[idx]);
					fileUploadExt = fileUploadNm.substring(fileUploadNm.lastIndexOf('.')+1,fileUploadNm.length());
					flag = true;
					for (int i = 0; i < extList.length; i++) {
						if(fileUploadExt.equals(extList[i])){
							flag = false;
						}
					}
					if(flag){
						trans.put("fileUploadCode","fileFail");
						break;
					}
				}
				if(!flag){
				
					for(int idx =0; idx < P_SYS_FILE_NM_LIST.length; idx++){
						if(P_SYS_FILE_NM_LIST != null && P_SYS_FILE_NM_LIST[idx] != null){
							parameterMap.put("P_SYS_FILE_NM", FwkStringUtil.castEuc2Iso(P_SYS_FILE_NM_LIST[idx]));
						}
						if(P_SV_FILE_NM_LIST != null && P_SV_FILE_NM_LIST[idx] != null){ 
							parameterMap.put("P_SV_FILE_NM", P_SV_FILE_NM_LIST[idx]);
						}
						if(P_FILE_SZ_LIST != null && P_FILE_SZ_LIST[idx] != null){ 
							parameterMap.put("P_FILE_SZ", P_FILE_SZ_LIST[idx]);
						}
						if(P_FILE_LCTN_LIST != null && P_FILE_LCTN_LIST[idx] != null){
							parameterMap.put("P_FILE_LCTN", P_FILE_LCTN_LIST[idx]);
						}
							
						//덱스트 첨부파일 저장
						comAtmaAtchFileDao.insertEtcAtchFileRegist(parameterMap);
					}
				}
			}else if(P_SYS_FILE_NM instanceof ArrayList){
				ArrayList P_SYS_FILE_NM_LIST = (ArrayList) P_SYS_FILE_NM;
				ArrayList P_SV_FILE_NM_LIST = (ArrayList) P_SV_FILE_NM;
				ArrayList P_FILE_SZ_LIST = (ArrayList) P_FILE_SZ;
				ArrayList P_FILE_LCTN_LIST = (ArrayList) P_FILE_LCTN;
				String fileUploadNm = "";
				String fileUploadExt = "";
				boolean flag = true;
				for(int idx = 0; idx < P_SYS_FILE_NM_LIST.size(); idx++){
					fileUploadNm = (String)FwkStringUtil.castEuc2Iso(P_SYS_FILE_NM_LIST.get(idx));
					fileUploadExt = fileUploadNm.substring(fileUploadNm.lastIndexOf('.')+1,fileUploadNm.length());
					flag = true;
					for (int i = 0; i < extList.length; i++) {
						if(fileUploadExt.equals(extList[i])){
							flag = false;
						}
					}
					if(flag){
						trans.put("fileUploadCode","fileFail");
						break;
					}
				}
				if(!flag){
				 
					for(int idx =0; idx < P_SYS_FILE_NM_LIST.size(); idx++){
						if(P_SYS_FILE_NM_LIST != null && P_SYS_FILE_NM_LIST.get(idx) != null){
							parameterMap.put("P_SYS_FILE_NM", FwkStringUtil.castEuc2Iso(P_SYS_FILE_NM_LIST.get(idx)));
						}
						if(P_SV_FILE_NM_LIST != null && P_SV_FILE_NM_LIST.get(idx) != null){ 
							parameterMap.put("P_SV_FILE_NM", P_SV_FILE_NM_LIST.get(idx));
						} 
						if(P_FILE_SZ_LIST != null && P_FILE_SZ_LIST.get(idx) != null){ 
							parameterMap.put("P_FILE_SZ", P_FILE_SZ_LIST.get(idx));
						}
						if(P_FILE_LCTN_LIST != null && P_FILE_LCTN_LIST.get(idx) != null){
							parameterMap.put("P_FILE_LCTN", P_FILE_LCTN_LIST.get(idx));
						}
							
						//덱스트 첨부파일 저장
						comAtmaAtchFileDao.insertEtcAtchFileRegist(parameterMap);
					}
				}
			}
		}
		
		trans.put("atchFileGroupNo", parameterMap.get("atchFileGroupNo"));
		return trans; 
	}
	
	@Override
	public FwkTransferObject fileInfoUpt(final FwkParameterMap parameterMap, final HttpServletRequest request) throws Exception{
		FwkTransferObject trans = new FwkTransferObject();
		String[] extList =  "xls,xlsx,doc,docx,ppt,pptx,hwp,pdf,jpg,jpeg,gif,bmp,png,dwg,zip,wmv,avi,mkv,mpeg,mp4,webm,XLS,XLSX,DOC,DOCX,PPT,PPTX,HWP,PDF,JPG,JPEG,PNG,GIF,BMP,DWG,ZIP,WMV,AVI,MKV,MPEG,MP4,WEBM".split(",");
		trans.put("fileUploadCode","fileSucc");
		String atchFileGroupNo =  "";
		    
		if(parameterMap.get("P_FILE_GRP_NO") != null && !"".equals(parameterMap.getString("P_FILE_GRP_NO"))){
			if("Y".equals(parameterMap.get("P_FAIL_YN"))){	// 유찰데이터일경우
				atchFileGroupNo = FwkStringUtil.replace(UUID.randomUUID().toString(), "-", "");
				parameterMap.put("P_FILE_GRP_NO_NEW", atchFileGroupNo);
			}else{
				atchFileGroupNo = parameterMap.getString("P_FILE_GRP_NO");
			}
		}else{
			atchFileGroupNo = FwkStringUtil.replace(UUID.randomUUID().toString(), "-", "");
		}
		parameterMap.put("atchFileGroupNo", atchFileGroupNo);
		
		parameterMap.put("P_SYSDATE", FwkDateUtil.getCurrentDateAsString("yyyyMM"));
		
		String P_FAIL_YN = parameterMap.getString("P_FAIL_YN");
		
		if("Y".equals(P_FAIL_YN)){	//유찰데이터 일경우에만
			 	// DEL_SN 값 제외하고 SELECT INSERT
				Object delFileSn = parameterMap.get("P_FILE_SN_DEL");
				if(delFileSn != null){
					if(delFileSn instanceof String){
						Log.info("fileInfoUpt String Pass");
					}else if(delFileSn instanceof String[]){ 
						//String[] snArray = ((String) delFileSn).split(",");
						String[] snArray =parameterMap.getString("P_FILE_SN_DEL").split(",");
						List<Map<String, Object>> delList = new ArrayList<Map<String,Object>>();
						for (int i = 0; i < snArray.length; i++) {
							FwkParameterMap delMap = new FwkParameterMap();	//NOPMD
							delMap.put("P_FILE_SN_DEL", snArray[i]);
							delList.add(delMap);
						}
						parameterMap.put("fileList", delList);
	
					}else if(delFileSn instanceof ArrayList){
						
						List<String> snArray  = (List<String>) delFileSn;
						List<Map<String, Object>> delList = new ArrayList<Map<String,Object>>();

						for (int i = 0; i < snArray.size(); i++) {
							FwkParameterMap delMap = new FwkParameterMap();	//NOPMD
							delMap.put("P_FILE_SN_DEL", snArray.get(i));
							delList.add(delMap);
						} 
						parameterMap.put("fileList", delList);
					}
				}
				//P_FILE_SN_DEL값 제외하고  SELECT-> INSERT
				comAtmaAtchFileDao.selectInsertDextAtchFile(parameterMap);
				
				// 새로 추가된 파일들 
				Object P_SYS_FILE_NM = parameterMap.get("P_SYS_FILE_NM");
				Object P_SV_FILE_NM = parameterMap.get("P_SV_FILE_NM");
				Object P_FILE_SZ = parameterMap.get("P_FILE_SZ");
				Object P_FILE_LCTN = parameterMap.get("P_FILE_LCTN");
			
				if(P_SYS_FILE_NM != null) {
					if(P_SYS_FILE_NM instanceof String){
						String fileUploadNm = (String) FwkStringUtil.castEuc2Iso(P_SYS_FILE_NM);
						String fileUploadExt = fileUploadNm.substring(fileUploadNm.lastIndexOf('.')+1,fileUploadNm.length());
						boolean flag = true;
						for (int i = 0; i < extList.length; i++) {
							if(fileUploadExt.equals(extList[i])){
								flag = false;
							}
						}
						if(flag){
							trans.put("fileUploadCode","fileFail");
						}
						if(!flag){
							//덱스트 첨부파일 저장
							parameterMap.put("P_SYS_FILE_NM",FwkStringUtil.castEuc2Iso(P_SYS_FILE_NM));
							comAtmaAtchFileDao.insertDextAtchFileRegist(parameterMap);
							
							//첨부파일 암호화 적용
							String StrFullpath =  (String) parameterMap.get("P_FILE_LCTN")+ File.separator + (String) parameterMap.get("P_SV_FILE_NM");
							//EwCipher.seedFileEncrypt(StrFullpath, FwkMessageUtil.getMessageAdd("ESTT.SYMM.KEY"));
//							EcCipher.seedFileEncrypt(StrFullpath);
						}
						
					}else if(P_SYS_FILE_NM instanceof String[]){
						String[] P_SYS_FILE_NM_LIST = (String[]) P_SYS_FILE_NM;
						String[] P_SV_FILE_NM_LIST = (String[]) P_SV_FILE_NM; 
						String[] P_FILE_SZ_LIST = (String[]) P_FILE_SZ;
						String[] P_FILE_LCTN_LIST = (String[]) P_FILE_LCTN;
						
						String fileUploadNm = "";
						String fileUploadExt = "";
						boolean flag = true;
						for(int idx = 0; idx < P_SYS_FILE_NM_LIST.length; idx++){
							fileUploadNm = (String)FwkStringUtil.castEuc2Iso(P_SYS_FILE_NM_LIST[idx]);
							fileUploadExt = fileUploadNm.substring(fileUploadNm.lastIndexOf('.')+1,fileUploadNm.length());
							flag = true;
							for (int i = 0; i < extList.length; i++) {
								if(fileUploadExt.equals(extList[i])){
									flag = false;
								}
							}
							if(flag){
								trans.put("fileUploadCode","fileFail");
								break;
							}
						}
						if(!flag){
							for(int idx =0; idx < P_SYS_FILE_NM_LIST.length; idx++){
								if(P_SYS_FILE_NM_LIST != null && P_SYS_FILE_NM_LIST[idx] != null){
									parameterMap.put("P_SYS_FILE_NM", FwkStringUtil.castEuc2Iso(P_SYS_FILE_NM_LIST[idx]));
								}
								if(P_SV_FILE_NM_LIST != null && P_SV_FILE_NM_LIST[idx] != null){ 
									parameterMap.put("P_SV_FILE_NM", P_SV_FILE_NM_LIST[idx]);
								}
								if(P_FILE_SZ_LIST != null && P_FILE_SZ_LIST[idx] != null){ 
									parameterMap.put("P_FILE_SZ", P_FILE_SZ_LIST[idx]);
								}
								if(P_FILE_LCTN_LIST != null && P_FILE_LCTN_LIST[idx] != null){
									parameterMap.put("P_FILE_LCTN", P_FILE_LCTN_LIST[idx]);
								}
									
								//덱스트 첨부파일 저장
								comAtmaAtchFileDao.insertDextAtchFileRegist(parameterMap);
								
								//첨부파일 암호화 적용
								String StrFullpath =  (String) parameterMap.get("P_FILE_LCTN")+ File.separator + (String) parameterMap.get("P_SV_FILE_NM");
								//EwCipher.seedFileEncrypt(StrFullpath, FwkMessageUtil.getMessageAdd("ESTT.SYMM.KEY"));
//								EcCipher.seedFileEncrypt(StrFullpath);
							}
						}
					}else if(P_SYS_FILE_NM instanceof ArrayList){
						ArrayList P_SYS_FILE_NM_LIST = (ArrayList) P_SYS_FILE_NM;
						ArrayList P_SV_FILE_NM_LIST = (ArrayList) P_SV_FILE_NM;
						ArrayList P_FILE_SZ_LIST = (ArrayList) P_FILE_SZ;
						ArrayList P_FILE_LCTN_LIST = (ArrayList) P_FILE_LCTN;
						 
						String fileUploadNm = "";
						String fileUploadExt = "";
						boolean flag = true;
						for(int idx = 0; idx < P_SYS_FILE_NM_LIST.size(); idx++){
							fileUploadNm = (String)FwkStringUtil.castEuc2Iso(P_SYS_FILE_NM_LIST.get(idx));
							fileUploadExt = fileUploadNm.substring(fileUploadNm.lastIndexOf('.')+1,fileUploadNm.length());
							flag = true;
							for (int i = 0; i < extList.length; i++) {
								if(fileUploadExt.equals(extList[i])){
									flag = false;
								}
							}
							if(flag){
								trans.put("fileUploadCode","fileFail");
								break;
							}
						}
						if(!flag){
							for(int idx =0; idx < P_SYS_FILE_NM_LIST.size(); idx++){
								if(P_SYS_FILE_NM_LIST != null && P_SYS_FILE_NM_LIST.get(idx) != null){
									parameterMap.put("P_SYS_FILE_NM", FwkStringUtil.castEuc2Iso(P_SYS_FILE_NM_LIST.get(idx)));
								}
								if(P_SV_FILE_NM_LIST != null && P_SV_FILE_NM_LIST.get(idx) != null){ 
									parameterMap.put("P_SV_FILE_NM", P_SV_FILE_NM_LIST.get(idx));
								} 
								if(P_FILE_SZ_LIST != null && P_FILE_SZ_LIST.get(idx) != null){ 
									parameterMap.put("P_FILE_SZ", P_FILE_SZ_LIST.get(idx));
								}
								if(P_FILE_LCTN_LIST != null && P_FILE_LCTN_LIST.get(idx) != null){
									parameterMap.put("P_FILE_LCTN", P_FILE_LCTN_LIST.get(idx));
								}
									
								//덱스트 첨부파일 저장
								comAtmaAtchFileDao.insertDextAtchFileRegist(parameterMap);
								
								//첨부파일 암호화 적용
								String StrFullpath =  (String) parameterMap.get("P_FILE_LCTN")+ File.separator + (String) parameterMap.get("P_SV_FILE_NM");
								//EwCipher.seedFileEncrypt(StrFullpath, FwkMessageUtil.getMessageAdd("ESTT.SYMM.KEY"));
//								EcCipher.seedFileEncrypt(StrFullpath);
							}
						}
					}
				}
				
				
		}else{ 	
				// 유찰데이터 아닐 경우
				Object P_FILE_SN = parameterMap.get("P_FILE_SN_DEL");
				if(P_FILE_SN != null) {
					if(P_FILE_SN instanceof String){
					//덱스트 첨부파일 삭제
						parameterMap.put("P_FILE_SN", (String)P_FILE_SN);
						comAtmaAtchFileDao.deleteDextFileDelete(parameterMap);	
					}else if(P_FILE_SN instanceof String[]){ 
						String[] P_FILE_SN_LIST = (String[]) P_FILE_SN;
	
						for(int idx =0; idx < P_FILE_SN_LIST.length; idx++){
							if(P_FILE_SN_LIST != null && P_FILE_SN_LIST[idx] != null){
								parameterMap.put("P_FILE_SN", P_FILE_SN_LIST[idx]);
							}
							comAtmaAtchFileDao.deleteDextFileDelete(parameterMap);	
						}
					}else if(P_FILE_SN instanceof ArrayList){
						ArrayList P_FILE_SN_LIST = (ArrayList) P_FILE_SN;
						for(int idx =0; idx < P_FILE_SN_LIST.size(); idx++){
							if(P_FILE_SN_LIST != null && P_FILE_SN_LIST.get(idx) != null){
								parameterMap.put("P_FILE_SN", P_FILE_SN_LIST.get(idx));
							}
							comAtmaAtchFileDao.deleteDextFileDelete(parameterMap);	
						}
					}
				}
			
				Object P_SYS_FILE_NM = parameterMap.get("P_SYS_FILE_NM");
				Object P_SV_FILE_NM = parameterMap.get("P_SV_FILE_NM");
				Object P_FILE_SZ = parameterMap.get("P_FILE_SZ");
				Object P_FILE_LCTN = parameterMap.get("P_FILE_LCTN");
				if(P_SYS_FILE_NM != null) {
					if(P_SYS_FILE_NM instanceof String){
						String fileUploadNm = (String) FwkStringUtil.castEuc2Iso(P_SYS_FILE_NM);
						String fileUploadExt = fileUploadNm.substring(fileUploadNm.lastIndexOf('.')+1,fileUploadNm.length());
						boolean flag = true;
						for (int i = 0; i < extList.length; i++) {
							if(fileUploadExt.equals(extList[i])){
								flag = false;
							}
						}
						if(flag){
							trans.put("fileUploadCode","fileFail");
						}
						if(!flag){
							//덱스트 첨부파일 저장
							parameterMap.put("P_SYS_FILE_NM",FwkStringUtil.castEuc2Iso(P_SYS_FILE_NM));
							comAtmaAtchFileDao.insertDextAtchFileRegist(parameterMap);
							
							//첨부파일 암호화 적용
							String StrFullpath =  (String) parameterMap.get("P_FILE_LCTN")+ File.separator + (String) parameterMap.get("P_SV_FILE_NM");
							//EwCipher.seedFileEncrypt(StrFullpath, FwkMessageUtil.getMessageAdd("ESTT.SYMM.KEY"));
//							EcCipher.seedFileEncrypt(StrFullpath);
						}
					}else if(P_SYS_FILE_NM instanceof String[]){
						String[] P_SYS_FILE_NM_LIST = (String[]) P_SYS_FILE_NM;
						String[] P_SV_FILE_NM_LIST = (String[]) P_SV_FILE_NM;
						String[] P_FILE_SZ_LIST = (String[]) P_FILE_SZ;
						String[] P_FILE_LCTN_LIST = (String[]) P_FILE_LCTN;
						
						String fileUploadNm = "";
						String fileUploadExt = "";
						boolean flag = true;
						for(int idx = 0; idx < P_SYS_FILE_NM_LIST.length; idx++){
							fileUploadNm = (String)FwkStringUtil.castEuc2Iso(P_SYS_FILE_NM_LIST[idx]);
							fileUploadExt = fileUploadNm.substring(fileUploadNm.lastIndexOf('.')+1,fileUploadNm.length());
							flag = true;
							for (int i = 0; i < extList.length; i++) {
								if(fileUploadExt.equals(extList[i])){
									flag = false;
								}
							}
							if(flag){
								trans.put("fileUploadCode","fileFail");
								break;
							}
						}
						if(!flag){
						
							for(int idx =0; idx < P_SYS_FILE_NM_LIST.length; idx++){
								if(P_SYS_FILE_NM_LIST != null && P_SYS_FILE_NM_LIST[idx] != null){
									parameterMap.put("P_SYS_FILE_NM", FwkStringUtil.castEuc2Iso(P_SYS_FILE_NM_LIST[idx]));
								}
								if(P_SV_FILE_NM_LIST != null && P_SV_FILE_NM_LIST[idx] != null){ 
									parameterMap.put("P_SV_FILE_NM", P_SV_FILE_NM_LIST[idx]);
								}
								if(P_FILE_SZ_LIST != null && P_FILE_SZ_LIST[idx] != null){ 
									parameterMap.put("P_FILE_SZ", P_FILE_SZ_LIST[idx]);
								}
								if(P_FILE_LCTN_LIST != null && P_FILE_LCTN_LIST[idx] != null){
									parameterMap.put("P_FILE_LCTN", P_FILE_LCTN_LIST[idx]);
								}
									
								//덱스트 첨부파일 저장
								comAtmaAtchFileDao.insertDextAtchFileRegist(parameterMap);
								
								//첨부파일 암호화 적용
								String StrFullpath =  (String) parameterMap.get("P_FILE_LCTN")+ File.separator + (String) parameterMap.get("P_SV_FILE_NM");
								//EwCipher.seedFileEncrypt(StrFullpath, FwkMessageUtil.getMessageAdd("ESTT.SYMM.KEY"));
//								EcCipher.seedFileEncrypt(StrFullpath);
							}
						}
					}else if(P_SYS_FILE_NM instanceof ArrayList){
						ArrayList P_SYS_FILE_NM_LIST = (ArrayList) P_SYS_FILE_NM;
						ArrayList P_SV_FILE_NM_LIST = (ArrayList) P_SV_FILE_NM;
						ArrayList P_FILE_SZ_LIST = (ArrayList) P_FILE_SZ;
						ArrayList P_FILE_LCTN_LIST = (ArrayList) P_FILE_LCTN;
						String fileUploadNm = "";
						String fileUploadExt = "";
						boolean flag = true;
						for(int idx = 0; idx < P_SYS_FILE_NM_LIST.size(); idx++){
							fileUploadNm = (String)FwkStringUtil.castEuc2Iso(P_SYS_FILE_NM_LIST.get(idx));
							fileUploadExt = fileUploadNm.substring(fileUploadNm.lastIndexOf('.')+1,fileUploadNm.length());
							flag = true;
							for (int i = 0; i < extList.length; i++) {
								if(fileUploadExt.equals(extList[i])){
									flag = false;
								}
							}
							if(flag){
								trans.put("fileUploadCode","fileFail");
								break;
							}
						}
						if(!flag){
						 
							for(int idx =0; idx < P_SYS_FILE_NM_LIST.size(); idx++){
								if(P_SYS_FILE_NM_LIST != null && P_SYS_FILE_NM_LIST.get(idx) != null){
									parameterMap.put("P_SYS_FILE_NM", FwkStringUtil.castEuc2Iso(P_SYS_FILE_NM_LIST.get(idx)));
								}
								if(P_SV_FILE_NM_LIST != null && P_SV_FILE_NM_LIST.get(idx) != null){ 
									parameterMap.put("P_SV_FILE_NM", P_SV_FILE_NM_LIST.get(idx));
								} 
								if(P_FILE_SZ_LIST != null && P_FILE_SZ_LIST.get(idx) != null){ 
									parameterMap.put("P_FILE_SZ", P_FILE_SZ_LIST.get(idx));
								}
								if(P_FILE_LCTN_LIST != null && P_FILE_LCTN_LIST.get(idx) != null){
									parameterMap.put("P_FILE_LCTN", P_FILE_LCTN_LIST.get(idx));
								}
									
								//덱스트 첨부파일 저장
								comAtmaAtchFileDao.insertDextAtchFileRegist(parameterMap);
								
								//첨부파일 암호화 적용
								String StrFullpath =  (String) parameterMap.get("P_FILE_LCTN")+ File.separator + (String) parameterMap.get("P_SV_FILE_NM");
								//EwCipher.seedFileEncrypt(StrFullpath, FwkMessageUtil.getMessageAdd("ESTT.SYMM.KEY"));
//								EcCipher.seedFileEncrypt(StrFullpath);
							}
						}
					}
				}
		}
		
		if("Y".equals(parameterMap.get("P_FAIL_YN"))){	// 유찰데이터일경우
			trans.put("atchFileGroupNo", parameterMap.get("P_FILE_GRP_NO_NEW"));
		}else{ 
			trans.put("atchFileGroupNo", parameterMap.get("atchFileGroupNo"));
		}
		return trans; 
	}
	
	
	 
	@Override
	public FwkTransferObject fileContInfoRegist(final FwkParameterMap parameterMap) throws Exception{
		FwkTransferObject trans = new FwkTransferObject();
		String[] extList =  "xls,xlsx,doc,docx,ppt,pptx,hwp,pdf,jpg,jpeg,gif,bmp,png,dwg,zip,wmv,avi,mkv,mpeg,mp4,webm,XLS,XLSX,DOC,DOCX,PPT,PPTX,HWP,PDF,JPG,JPEG,PNG,GIF,BMP,DWG,ZIP,WMV,AVI,MKV,MPEG,MP4,WEBM".split(",");
		trans.put("fileUploadCode","fileSucc");
		String atchFileGroupNo =  "";
		
		atchFileGroupNo = FwkStringUtil.replace(UUID.randomUUID().toString(), "-", "");
		parameterMap.put("P_FILE_GRP_NO_NEW", atchFileGroupNo);

	
		parameterMap.put("atchFileGroupNo", atchFileGroupNo);
		
		parameterMap.put("P_SYSDATE", FwkDateUtil.getCurrentDateAsString("yyyyMM"));
		
		// DEL_SN 값 제외하고 SELECT INSERT
		Object delFileSn = parameterMap.get("P_FILE_SN_DEL");
		if(delFileSn != null){
			if(delFileSn instanceof String){
				Log.info("fileInfoUpt String Pass");
			}else if(delFileSn instanceof String[]){ 
				//String[] snArray = ((String) delFileSn).split(",");
				String[] snArray =parameterMap.getString("P_FILE_SN_DEL").split(",");
				List<Map<String, Object>> delList = new ArrayList<Map<String,Object>>();
				for (int i = 0; i < snArray.length; i++) {
					FwkParameterMap delMap = new FwkParameterMap();	//NOPMD
					delMap.put("P_FILE_SN_DEL", snArray[i]);
					delList.add(delMap);
				}
				parameterMap.put("fileList", delList);
	
			}else if(delFileSn instanceof ArrayList){
						
				List<String> snArray  = (List<String>) delFileSn;
				List<Map<String, Object>> delList = new ArrayList<Map<String,Object>>();

				for (int i = 0; i < snArray.size(); i++) {
					FwkParameterMap delMap = new FwkParameterMap();	//NOPMD
					delMap.put("P_FILE_SN_DEL", snArray.get(i));
					delList.add(delMap);
				} 
				parameterMap.put("fileList", delList);
			}
		}
			
				
		//P_FILE_SN_DEL값 제외하고  SELECT-> INSERT
		FwkDataEntity user = (FwkDataEntity) parameterMap.get("loginResult");
		FwkParameterMap copyParameterMap = new FwkParameterMap();
		
		copyParameterMap.put("P_FILE_GRP_NO",atchFileGroupNo);		
		copyParameterMap.put("USR_ID", user.get("USR_ID"));
		copyParameterMap.put("USR_NM", user.get("USR_NM"));
		copyParameterMap.put("CONN_IP", user.get("CONN_IP"));
		copyParameterMap.put("P_FILE_DOC_SECD", "C");
		
		//parameterMap.put("P_FILE_DOC_SECD", "C");
		parameterMap.put("P_CONT_FSCD", "C");
		List<FwkDataEntity> fileCDetail = comAtmaAtchFileDao.selectAtchFileCDetail(parameterMap);
		String systemFileName="";
		String ext = "";
		String copyPath ="";
		String root = FwkMessageUtil.getMessage("EW.SYS.FILE.DIR");
		copyPath = root + FwkDateUtil.getCurrentDateAsString("yyyy") + File.separator + FwkDateUtil.getCurrentDateAsString("MM") + File.separator + FwkDateUtil.getCurrentDateAsString("dd") + File.separator + "contC"; // 파일 경로
		
		parameterMap.put("P_FILE_DOC_SECD", "C");
		for(int i=0; i<fileCDetail.size(); i++){
			systemFileName = FwkStringUtil.replace(UUID.randomUUID().toString(), "-", "");
			ext = fileCDetail.get(i).get("SYS_FILE_NM").toString().substring(fileCDetail.get(i).get("SYS_FILE_NM").toString().lastIndexOf('.')+1).toLowerCase();
			
			//CmmnUtil.FileCopy(fileCDetail.get(i).get("SV_FILE_NM").toString(), systemFileName+"."+ext, fileCDetail.get(i).get("FILE_LCTN").toString()+File.separator, fileCDetail.get(i).get("FILE_LCTN").toString()+File.separator);
			CmmnUtil.FileCopy(fileCDetail.get(i).get("SV_FILE_NM").toString(), systemFileName+"."+ext, fileCDetail.get(i).get("FILE_LCTN").toString()+File.separator, copyPath+File.separator);
			
			copyParameterMap.put("P_SV_FILE_NM", systemFileName+"."+ext);
			copyParameterMap.put("P_SYS_FILE_NM", fileCDetail.get(i).get("SYS_FILE_NM").toString());
			copyParameterMap.put("P_FILE_SZ", fileCDetail.get(i).get("FILE_SZ").toString());
			copyParameterMap.put("P_TSK_VKEY3", fileCDetail.get(i).get("TSK_VKEY3").toString());
			System.err.println("P_TSK_VKEY3 >>> " +  copyParameterMap.get("P_TSK_VKEY3"));
			//copyParameterMap.put("P_FILE_LCTN", fileCDetail.get(i).get("FILE_LCTN").toString());
			copyParameterMap.put("P_FILE_LCTN", copyPath);
			
			comAtmaAtchFileDao.insertAtchFileRegistOne(copyParameterMap);
		}
				
		// 새로 추가된 파일들 
		Object P_SYS_FILE_NM = parameterMap.get("P_SYS_FILE_NM");
		Object P_SV_FILE_NM = parameterMap.get("P_SV_FILE_NM");
		Object P_FILE_SZ = parameterMap.get("P_FILE_SZ");
		Object P_FILE_LCTN = parameterMap.get("P_FILE_LCTN");
			
		if(P_SYS_FILE_NM != null) {
			parameterMap.put("P_FILE_DOC_SECD", "C");
			if(P_SYS_FILE_NM instanceof String){
				String fileUploadNm = (String) FwkStringUtil.castEuc2Iso(P_SYS_FILE_NM);
				String fileUploadExt = fileUploadNm.substring(fileUploadNm.lastIndexOf('.')+1,fileUploadNm.length());
				boolean flag = true;
				for (int i = 0; i < extList.length; i++) {
					if(fileUploadExt.equals(extList[i])){
						flag = false;
					}
				}
				if(flag){
					trans.put("fileUploadCode","fileFail");
				}
				if(!flag){
					//덱스트 첨부파일 저장
					parameterMap.put("P_SYS_FILE_NM",FwkStringUtil.castEuc2Iso(P_SYS_FILE_NM));
					comAtmaAtchFileDao.insertDextAtchFileRegist(parameterMap);
					
					//첨부파일 암호화 적용
					String StrFullpath =  (String) parameterMap.get("P_FILE_LCTN")+ File.separator + (String) parameterMap.get("P_SV_FILE_NM");
					
				}
				
			}else if(P_SYS_FILE_NM instanceof String[]){
				String[] P_SYS_FILE_NM_LIST = (String[]) P_SYS_FILE_NM;
				String[] P_SV_FILE_NM_LIST = (String[]) P_SV_FILE_NM; 
				String[] P_FILE_SZ_LIST = (String[]) P_FILE_SZ;
				String[] P_FILE_LCTN_LIST = (String[]) P_FILE_LCTN;
				
				String fileUploadNm = "";
				String fileUploadExt = "";
				boolean flag = true;
				for(int idx = 0; idx < P_SYS_FILE_NM_LIST.length; idx++){
					fileUploadNm = (String)FwkStringUtil.castEuc2Iso(P_SYS_FILE_NM_LIST[idx]);
					fileUploadExt = fileUploadNm.substring(fileUploadNm.lastIndexOf('.')+1,fileUploadNm.length());
					flag = true;
					for (int i = 0; i < extList.length; i++) {
						if(fileUploadExt.equals(extList[i])){
							flag = false;
						}
					}
					if(flag){
						trans.put("fileUploadCode","fileFail");
						break;
					}
				}
				if(!flag){
					for(int idx =0; idx < P_SYS_FILE_NM_LIST.length; idx++){
						if(P_SYS_FILE_NM_LIST != null && P_SYS_FILE_NM_LIST[idx] != null){
							parameterMap.put("P_SYS_FILE_NM", FwkStringUtil.castEuc2Iso(P_SYS_FILE_NM_LIST[idx]));
						}
						if(P_SV_FILE_NM_LIST != null && P_SV_FILE_NM_LIST[idx] != null){ 
							parameterMap.put("P_SV_FILE_NM", P_SV_FILE_NM_LIST[idx]);
						}
						if(P_FILE_SZ_LIST != null && P_FILE_SZ_LIST[idx] != null){ 
							parameterMap.put("P_FILE_SZ", P_FILE_SZ_LIST[idx]);
						}
						if(P_FILE_LCTN_LIST != null && P_FILE_LCTN_LIST[idx] != null){
							parameterMap.put("P_FILE_LCTN", P_FILE_LCTN_LIST[idx]);
						}
							
						//덱스트 첨부파일 저장
						comAtmaAtchFileDao.insertDextAtchFileRegist(parameterMap);
						
						//첨부파일 암호화 적용
						String StrFullpath =  (String) parameterMap.get("P_FILE_LCTN")+ File.separator + (String) parameterMap.get("P_SV_FILE_NM");
					}
				}
			}else if(P_SYS_FILE_NM instanceof ArrayList){
				ArrayList P_SYS_FILE_NM_LIST = (ArrayList) P_SYS_FILE_NM;
				ArrayList P_SV_FILE_NM_LIST = (ArrayList) P_SV_FILE_NM;
				ArrayList P_FILE_SZ_LIST = (ArrayList) P_FILE_SZ;
				ArrayList P_FILE_LCTN_LIST = (ArrayList) P_FILE_LCTN;
				 
				String fileUploadNm = "";
				String fileUploadExt = "";
				boolean flag = true;
				for(int idx = 0; idx < P_SYS_FILE_NM_LIST.size(); idx++){
					fileUploadNm = (String)FwkStringUtil.castEuc2Iso(P_SYS_FILE_NM_LIST.get(idx));
					fileUploadExt = fileUploadNm.substring(fileUploadNm.lastIndexOf('.')+1,fileUploadNm.length());
					flag = true;
					for (int i = 0; i < extList.length; i++) {
						if(fileUploadExt.equals(extList[i])){
							flag = false;
						}
					}
					if(flag){
						trans.put("fileUploadCode","fileFail");
						break;
					}
				}
				if(!flag){
					for(int idx =0; idx < P_SYS_FILE_NM_LIST.size(); idx++){
						if(P_SYS_FILE_NM_LIST != null && P_SYS_FILE_NM_LIST.get(idx) != null){
							parameterMap.put("P_SYS_FILE_NM", FwkStringUtil.castEuc2Iso(P_SYS_FILE_NM_LIST.get(idx)));
						}
						if(P_SV_FILE_NM_LIST != null && P_SV_FILE_NM_LIST.get(idx) != null){ 
							parameterMap.put("P_SV_FILE_NM", P_SV_FILE_NM_LIST.get(idx));
						} 
						if(P_FILE_SZ_LIST != null && P_FILE_SZ_LIST.get(idx) != null){ 
							parameterMap.put("P_FILE_SZ", P_FILE_SZ_LIST.get(idx));
						}
						if(P_FILE_LCTN_LIST != null && P_FILE_LCTN_LIST.get(idx) != null){
							parameterMap.put("P_FILE_LCTN", P_FILE_LCTN_LIST.get(idx));
						}
							
						//덱스트 첨부파일 저장
						comAtmaAtchFileDao.insertDextAtchFileRegist(parameterMap);
						
						//첨부파일 암호화 적용
						String StrFullpath =  (String) parameterMap.get("P_FILE_LCTN")+ File.separator + (String) parameterMap.get("P_SV_FILE_NM");
					}
				}
			}
		}
		
		
		trans.put("atchFileGroupNo", parameterMap.get("atchFileGroupNo"));
		
		return trans; 
	}
	
	
	@Override
	public FwkTransferObject fileContInfoChngRegist(final FwkParameterMap parameterMap) throws Exception{
		FwkTransferObject trans = new FwkTransferObject();
		String[] extList =  "xls,xlsx,doc,docx,ppt,pptx,hwp,pdf,jpg,jpeg,gif,bmp,png,dwg,zip,wmv,avi,mkv,mpeg,mp4,webm,XLS,XLSX,DOC,DOCX,PPT,PPTX,HWP,PDF,JPG,JPEG,PNG,GIF,BMP,DWG,ZIP,WMV,AVI,MKV,MPEG,MP4,WEBM".split(",");
		trans.put("fileUploadCode","fileSucc");
		String atchFileGroupNo =  "";
		
		atchFileGroupNo = FwkStringUtil.replace(UUID.randomUUID().toString(), "-", "");
		parameterMap.put("P_FILE_GRP_NO_NEW", atchFileGroupNo);

	
		parameterMap.put("atchFileGroupNo", atchFileGroupNo);
		
		parameterMap.put("P_SYSDATE", FwkDateUtil.getCurrentDateAsString("yyyyMM"));
		
		// DEL_SN 값 제외하고 SELECT INSERT
		Object delFileSn = parameterMap.get("P_FILE_SN_DEL");
		if(delFileSn != null){ 
			if(delFileSn instanceof String){
				Log.info("fileInfoUpt String Pass");
			}else if(delFileSn instanceof String[]){
				String[] snArray =parameterMap.getString("P_FILE_SN_DEL").split(",");
				List<Map<String, Object>> delList = new ArrayList<Map<String,Object>>();
				for (int i = 0; i < snArray.length; i++) {
					FwkParameterMap delMap = new FwkParameterMap();	//NOPMD
					delMap.put("P_FILE_SN_DEL", snArray[i]);
					delList.add(delMap);
				}
				parameterMap.put("fileList", delList);
	
			}else if(delFileSn instanceof ArrayList){
						
				List<String> snArray  = (List<String>) delFileSn;
				List<Map<String, Object>> delList = new ArrayList<Map<String,Object>>();

				for (int i = 0; i < snArray.size(); i++) {
					FwkParameterMap delMap = new FwkParameterMap();	//NOPMD
					delMap.put("P_FILE_SN_DEL", snArray.get(i));
					delList.add(delMap);
				} 
				parameterMap.put("fileList", delList);
			}
		}
			
				
		//P_FILE_SN_DEL값 제외하고  SELECT-> INSERT
		FwkDataEntity user = (FwkDataEntity) parameterMap.get("loginResult");
		FwkParameterMap copyParameterMap = new FwkParameterMap();
		
		copyParameterMap.put("P_FILE_GRP_NO",atchFileGroupNo);		
		copyParameterMap.put("USR_ID", user.get("USR_ID"));
		copyParameterMap.put("USR_NM", user.get("USR_NM"));
		copyParameterMap.put("CONN_IP", user.get("CONN_IP"));
		copyParameterMap.put("P_FILE_DOC_SECD", "C");
		
		//parameterMap.put("P_FILE_DOC_SECD", "C");
		parameterMap.put("P_CONT_FSCD", "C");
		List<FwkDataEntity> fileCDetail = comAtmaAtchFileDao.selectAtchFileCDetail2(parameterMap);
		String systemFileName="";
		String ext = "";
		String copyPath ="";
		String root = FwkMessageUtil.getMessage("EW.SYS.FILE.DIR");
		copyPath = root + FwkDateUtil.getCurrentDateAsString("yyyy") + File.separator + FwkDateUtil.getCurrentDateAsString("MM") + File.separator + FwkDateUtil.getCurrentDateAsString("dd") + File.separator + "contC"; // 파일 경로
		
		parameterMap.put("P_FILE_DOC_SECD", "C");
		for(int i=0; i<fileCDetail.size(); i++){
			systemFileName = FwkStringUtil.replace(UUID.randomUUID().toString(), "-", "");
			ext = fileCDetail.get(i).get("SYS_FILE_NM").toString().substring(fileCDetail.get(i).get("SYS_FILE_NM").toString().lastIndexOf('.')+1).toLowerCase();
			
			//CmmnUtil.FileCopy(fileCDetail.get(i).get("SV_FILE_NM").toString(), systemFileName+"."+ext, fileCDetail.get(i).get("FILE_LCTN").toString()+File.separator, fileCDetail.get(i).get("FILE_LCTN").toString()+File.separator);
			CmmnUtil.FileCopy(fileCDetail.get(i).get("SV_FILE_NM").toString(), systemFileName+"."+ext, fileCDetail.get(i).get("FILE_LCTN").toString()+File.separator, copyPath+File.separator);
			
			copyParameterMap.put("P_SV_FILE_NM", systemFileName+"."+ext);
			copyParameterMap.put("P_SYS_FILE_NM", fileCDetail.get(i).get("SYS_FILE_NM").toString());
			copyParameterMap.put("P_FILE_SZ", fileCDetail.get(i).get("FILE_SZ").toString());
			//copyParameterMap.put("P_FILE_LCTN", fileCDetail.get(i).get("FILE_LCTN").toString());
			copyParameterMap.put("P_FILE_LCTN", copyPath);
			
			comAtmaAtchFileDao.insertAtchFileRegistOne(copyParameterMap);
		}
				
		// 새로 추가된 파일들 
		Object P_SYS_FILE_NM = parameterMap.get("P_SYS_FILE_NM");
		Object P_SV_FILE_NM = parameterMap.get("P_SV_FILE_NM");
		Object P_FILE_SZ = parameterMap.get("P_FILE_SZ");
		Object P_FILE_LCTN = parameterMap.get("P_FILE_LCTN");
			
		if(P_SYS_FILE_NM != null) {
			parameterMap.put("P_FILE_DOC_SECD", "C");
			if(P_SYS_FILE_NM instanceof String){
				String fileUploadNm = (String) FwkStringUtil.castEuc2Iso(P_SYS_FILE_NM);
				String fileUploadExt = fileUploadNm.substring(fileUploadNm.lastIndexOf('.')+1,fileUploadNm.length());
				boolean flag = true;
				for (int i = 0; i < extList.length; i++) {
					if(fileUploadExt.equals(extList[i])){
						flag = false;
					}
				}
				if(flag){
					trans.put("fileUploadCode","fileFail");
				}
				if(!flag){
					//덱스트 첨부파일 저장
					parameterMap.put("P_SYS_FILE_NM",FwkStringUtil.castEuc2Iso(P_SYS_FILE_NM));
					comAtmaAtchFileDao.insertDextAtchFileRegist(parameterMap);
					
					//첨부파일 암호화 적용
					String StrFullpath =  (String) parameterMap.get("P_FILE_LCTN")+ File.separator + (String) parameterMap.get("P_SV_FILE_NM");
					
				}
				
			}else if(P_SYS_FILE_NM instanceof String[]){
				String[] P_SYS_FILE_NM_LIST = (String[]) P_SYS_FILE_NM;
				String[] P_SV_FILE_NM_LIST = (String[]) P_SV_FILE_NM; 
				String[] P_FILE_SZ_LIST = (String[]) P_FILE_SZ;
				String[] P_FILE_LCTN_LIST = (String[]) P_FILE_LCTN;
				
				String fileUploadNm = "";
				String fileUploadExt = "";
				boolean flag = true;
				for(int idx = 0; idx < P_SYS_FILE_NM_LIST.length; idx++){
					fileUploadNm = (String)FwkStringUtil.castEuc2Iso(P_SYS_FILE_NM_LIST[idx]);
					fileUploadExt = fileUploadNm.substring(fileUploadNm.lastIndexOf('.')+1,fileUploadNm.length());
					flag = true;
					for (int i = 0; i < extList.length; i++) {
						if(fileUploadExt.equals(extList[i])){
							flag = false;
						}
					}
					if(flag){
						trans.put("fileUploadCode","fileFail");
						break;
					}
				}
				if(!flag){
					for(int idx =0; idx < P_SYS_FILE_NM_LIST.length; idx++){
						if(P_SYS_FILE_NM_LIST != null && P_SYS_FILE_NM_LIST[idx] != null){
							parameterMap.put("P_SYS_FILE_NM", FwkStringUtil.castEuc2Iso(P_SYS_FILE_NM_LIST[idx]));
						}
						if(P_SV_FILE_NM_LIST != null && P_SV_FILE_NM_LIST[idx] != null){ 
							parameterMap.put("P_SV_FILE_NM", P_SV_FILE_NM_LIST[idx]);
						}
						if(P_FILE_SZ_LIST != null && P_FILE_SZ_LIST[idx] != null){ 
							parameterMap.put("P_FILE_SZ", P_FILE_SZ_LIST[idx]);
						}
						if(P_FILE_LCTN_LIST != null && P_FILE_LCTN_LIST[idx] != null){
							parameterMap.put("P_FILE_LCTN", P_FILE_LCTN_LIST[idx]);
						}
							
						//덱스트 첨부파일 저장
						comAtmaAtchFileDao.insertDextAtchFileRegist(parameterMap);
						
						//첨부파일 암호화 적용
						String StrFullpath =  (String) parameterMap.get("P_FILE_LCTN")+ File.separator + (String) parameterMap.get("P_SV_FILE_NM");
					}
				}
			}else if(P_SYS_FILE_NM instanceof ArrayList){
				ArrayList P_SYS_FILE_NM_LIST = (ArrayList) P_SYS_FILE_NM;
				ArrayList P_SV_FILE_NM_LIST = (ArrayList) P_SV_FILE_NM;
				ArrayList P_FILE_SZ_LIST = (ArrayList) P_FILE_SZ;
				ArrayList P_FILE_LCTN_LIST = (ArrayList) P_FILE_LCTN;
				 
				String fileUploadNm = "";
				String fileUploadExt = "";
				boolean flag = true;
				for(int idx = 0; idx < P_SYS_FILE_NM_LIST.size(); idx++){
					fileUploadNm = (String)FwkStringUtil.castEuc2Iso(P_SYS_FILE_NM_LIST.get(idx));
					fileUploadExt = fileUploadNm.substring(fileUploadNm.lastIndexOf('.')+1,fileUploadNm.length());
					flag = true;
					for (int i = 0; i < extList.length; i++) {
						if(fileUploadExt.equals(extList[i])){
							flag = false;
						}
					}
					if(flag){
						trans.put("fileUploadCode","fileFail");
						break;
					}
				}
				if(!flag){
					for(int idx =0; idx < P_SYS_FILE_NM_LIST.size(); idx++){
						if(P_SYS_FILE_NM_LIST != null && P_SYS_FILE_NM_LIST.get(idx) != null){
							parameterMap.put("P_SYS_FILE_NM", FwkStringUtil.castEuc2Iso(P_SYS_FILE_NM_LIST.get(idx)));
						}
						if(P_SV_FILE_NM_LIST != null && P_SV_FILE_NM_LIST.get(idx) != null){ 
							parameterMap.put("P_SV_FILE_NM", P_SV_FILE_NM_LIST.get(idx));
						} 
						if(P_FILE_SZ_LIST != null && P_FILE_SZ_LIST.get(idx) != null){ 
							parameterMap.put("P_FILE_SZ", P_FILE_SZ_LIST.get(idx));
						}
						if(P_FILE_LCTN_LIST != null && P_FILE_LCTN_LIST.get(idx) != null){
							parameterMap.put("P_FILE_LCTN", P_FILE_LCTN_LIST.get(idx));
						}
							
						//덱스트 첨부파일 저장
						comAtmaAtchFileDao.insertDextAtchFileRegist(parameterMap);
						
						//첨부파일 암호화 적용
						String StrFullpath =  (String) parameterMap.get("P_FILE_LCTN")+ File.separator + (String) parameterMap.get("P_SV_FILE_NM");
					}
				}
			}
		}
		
		
		trans.put("atchFileGroupNo", parameterMap.get("atchFileGroupNo"));
		
		return trans; 
	}
	
	@Override
	public FwkTransferObject fileContInfoUpdt(final FwkParameterMap parameterMap) throws Exception{
		FwkTransferObject trans = new FwkTransferObject();
		String[] extList =  "xls,xlsx,doc,docx,ppt,pptx,hwp,pdf,jpg,jpeg,gif,bmp,png,dwg,zip,wmv,avi,mkv,mpeg,mp4,webm,XLS,XLSX,DOC,DOCX,PPT,PPTX,HWP,PDF,JPG,JPEG,PNG,GIF,BMP,DWG,ZIP,WMV,AVI,MKV,MPEG,MP4,WEBM".split(",");
		trans.put("fileUploadCode","fileSucc");
		String atchFileGroupNo =  "";
		    
		if(parameterMap.get("P_FILE_GRP_NO") != null && !"".equals(parameterMap.getString("P_FILE_GRP_NO"))){
			atchFileGroupNo = parameterMap.getString("P_FILE_GRP_NO");
		}else{
			atchFileGroupNo = FwkStringUtil.replace(UUID.randomUUID().toString(), "-", "");
		}
		parameterMap.put("atchFileGroupNo", atchFileGroupNo);
		parameterMap.put("P_SYSDATE", FwkDateUtil.getCurrentDateAsString("yyyyMM"));
		
		Object P_FILE_SN = parameterMap.get("P_FILE_SN_DEL");
		if(P_FILE_SN != null) {
			if(P_FILE_SN instanceof String){
			//덱스트 첨부파일 삭제
				parameterMap.put("P_FILE_SN", (String)P_FILE_SN);
				comAtmaAtchFileDao.deleteEtcFileDelete(parameterMap);	
			}else if(P_FILE_SN instanceof String[]){ 
				String[] P_FILE_SN_LIST = (String[]) P_FILE_SN;

				for(int idx =0; idx < P_FILE_SN_LIST.length; idx++){
					if(P_FILE_SN_LIST != null && P_FILE_SN_LIST[idx] != null){
						parameterMap.put("P_FILE_SN", P_FILE_SN_LIST[idx]);
					}
					comAtmaAtchFileDao.deleteEtcFileDelete(parameterMap);	
				}
			}else if(P_FILE_SN instanceof ArrayList){
				ArrayList P_FILE_SN_LIST = (ArrayList) P_FILE_SN;
				for(int idx =0; idx < P_FILE_SN_LIST.size(); idx++){
					if(P_FILE_SN_LIST != null && P_FILE_SN_LIST.get(idx) != null){
						parameterMap.put("P_FILE_SN", P_FILE_SN_LIST.get(idx));
					}
					comAtmaAtchFileDao.deleteEtcFileDelete(parameterMap);	
				}
			}
		}
	
		Object P_SYS_FILE_NM = parameterMap.get("P_SYS_FILE_NM");
		Object P_SV_FILE_NM = parameterMap.get("P_SV_FILE_NM");
		Object P_FILE_SZ = parameterMap.get("P_FILE_SZ");
		Object P_FILE_LCTN = parameterMap.get("P_FILE_LCTN");
		parameterMap.put("P_FILE_DOC_SECD", "C");
		
		if(P_SYS_FILE_NM != null) {
			if(P_SYS_FILE_NM instanceof String){
				String fileUploadNm = (String) FwkStringUtil.castEuc2Iso(P_SYS_FILE_NM);
				String fileUploadExt = fileUploadNm.substring(fileUploadNm.lastIndexOf('.')+1,fileUploadNm.length());
				boolean flag = true;
				for (int i = 0; i < extList.length; i++) {
					if(fileUploadExt.equals(extList[i])){
						flag = false;
					}
				}
				if(flag){
					trans.put("fileUploadCode","fileFail");
				}
				if(!flag){
					//덱스트 첨부파일 저장
					parameterMap.put("P_SYS_FILE_NM",FwkStringUtil.castEuc2Iso(P_SYS_FILE_NM));
					comAtmaAtchFileDao.insertDextAtchFileRegist(parameterMap);
					
				}
			}else if(P_SYS_FILE_NM instanceof String[]){
				String[] P_SYS_FILE_NM_LIST = (String[]) P_SYS_FILE_NM;
				String[] P_SV_FILE_NM_LIST = (String[]) P_SV_FILE_NM;
				String[] P_FILE_SZ_LIST = (String[]) P_FILE_SZ;
				String[] P_FILE_LCTN_LIST = (String[]) P_FILE_LCTN;
				
				String fileUploadNm = "";
				String fileUploadExt = "";
				boolean flag = true;
				for(int idx = 0; idx < P_SYS_FILE_NM_LIST.length; idx++){
					fileUploadNm = (String)FwkStringUtil.castEuc2Iso(P_SYS_FILE_NM_LIST[idx]);
					fileUploadExt = fileUploadNm.substring(fileUploadNm.lastIndexOf('.')+1,fileUploadNm.length());
					flag = true;
					for (int i = 0; i < extList.length; i++) {
						if(fileUploadExt.equals(extList[i])){
							flag = false;
						}
					}
					if(flag){
						trans.put("fileUploadCode","fileFail");
						break;
					}
				}
				if(!flag){
				
					for(int idx =0; idx < P_SYS_FILE_NM_LIST.length; idx++){
						if(P_SYS_FILE_NM_LIST != null && P_SYS_FILE_NM_LIST[idx] != null){
							parameterMap.put("P_SYS_FILE_NM", FwkStringUtil.castEuc2Iso(P_SYS_FILE_NM_LIST[idx]));
						}
						if(P_SV_FILE_NM_LIST != null && P_SV_FILE_NM_LIST[idx] != null){ 
							parameterMap.put("P_SV_FILE_NM", P_SV_FILE_NM_LIST[idx]);
						}
						if(P_FILE_SZ_LIST != null && P_FILE_SZ_LIST[idx] != null){ 
							parameterMap.put("P_FILE_SZ", P_FILE_SZ_LIST[idx]);
						}
						if(P_FILE_LCTN_LIST != null && P_FILE_LCTN_LIST[idx] != null){
							parameterMap.put("P_FILE_LCTN", P_FILE_LCTN_LIST[idx]);
						}
							
						//덱스트 첨부파일 저장
						comAtmaAtchFileDao.insertDextAtchFileRegist(parameterMap);
					}
				}
			}else if(P_SYS_FILE_NM instanceof ArrayList){
				ArrayList P_SYS_FILE_NM_LIST = (ArrayList) P_SYS_FILE_NM;
				ArrayList P_SV_FILE_NM_LIST = (ArrayList) P_SV_FILE_NM;
				ArrayList P_FILE_SZ_LIST = (ArrayList) P_FILE_SZ;
				ArrayList P_FILE_LCTN_LIST = (ArrayList) P_FILE_LCTN;
				String fileUploadNm = "";
				String fileUploadExt = "";
				boolean flag = true;
				for(int idx = 0; idx < P_SYS_FILE_NM_LIST.size(); idx++){
					fileUploadNm = (String)FwkStringUtil.castEuc2Iso(P_SYS_FILE_NM_LIST.get(idx));
					fileUploadExt = fileUploadNm.substring(fileUploadNm.lastIndexOf('.')+1,fileUploadNm.length());
					flag = true;
					for (int i = 0; i < extList.length; i++) {
						if(fileUploadExt.equals(extList[i])){
							flag = false;
						}
					}
					if(flag){
						trans.put("fileUploadCode","fileFail");
						break;
					}
				}
				if(!flag){
				 
					for(int idx =0; idx < P_SYS_FILE_NM_LIST.size(); idx++){
						if(P_SYS_FILE_NM_LIST != null && P_SYS_FILE_NM_LIST.get(idx) != null){
							parameterMap.put("P_SYS_FILE_NM", FwkStringUtil.castEuc2Iso(P_SYS_FILE_NM_LIST.get(idx)));
						}
						if(P_SV_FILE_NM_LIST != null && P_SV_FILE_NM_LIST.get(idx) != null){ 
							parameterMap.put("P_SV_FILE_NM", P_SV_FILE_NM_LIST.get(idx));
						} 
						if(P_FILE_SZ_LIST != null && P_FILE_SZ_LIST.get(idx) != null){ 
							parameterMap.put("P_FILE_SZ", P_FILE_SZ_LIST.get(idx));
						}
						if(P_FILE_LCTN_LIST != null && P_FILE_LCTN_LIST.get(idx) != null){
							parameterMap.put("P_FILE_LCTN", P_FILE_LCTN_LIST.get(idx));
						}
							
						//덱스트 첨부파일 저장
						comAtmaAtchFileDao.insertDextAtchFileRegist(parameterMap);
					}
				}
			}
		}
		
		trans.put("atchFileGroupNo", parameterMap.get("atchFileGroupNo"));
		return trans; 
	}
	
	@Override
	public FwkTransferObject fileListInqireByFileGrpNo(final FwkParameterMap parameterMap){
		FwkTransferObject trans = new FwkTransferObject();
		List<FwkDataEntity> entity = comAtmaAtchFileDao.selectFileListInqireByFileGrpNo(parameterMap);
		List<FwkDataEntity> atchFileList = new ArrayList<FwkDataEntity>();
			
		for(int i = 0; i< entity.size(); i++){ 
			FwkDataEntity map =(FwkDataEntity) entity.get(i);
			map.put("SYS_FILE_NM",  FwkStringUtil.fmIso2Euc((String) map.get("SYS_FILE_NM")));
			atchFileList.add(map);
		}

		trans.put("atchFileList", atchFileList);
		return trans;
		
	}
	
	
	
	@Override
	public FwkTransferObject fileEtcListByFileGrpNo(final FwkParameterMap parameterMap){
		FwkTransferObject trans = new FwkTransferObject();
		System.err.println("fileEtcListByFileGrpNo " + parameterMap.get("P_FILE_GRP_NO_ETC"));
		List<FwkDataEntity> entity = comAtmaAtchFileDao.selectFileEtcListByFileGrpNo(parameterMap);
		List<FwkDataEntity> atchFileList = new ArrayList<FwkDataEntity>();
			
		for(int i = 0; i< entity.size(); i++){ 
			FwkDataEntity map =(FwkDataEntity) entity.get(i);
			map.put("SYS_FILE_NM",  FwkStringUtil.fmIso2Euc((String) map.get("SYS_FILE_NM")));
			atchFileList.add(map);
		}

		trans.put("atchFileList", atchFileList);
		return trans;
		
	}
	
	/**
	 * 계약자 계약상세화면에서 업체첨부파일은 계약번호화 차수를 이용하여 첨부파일 목록을 조회 
	 */
	@Override
	public FwkTransferObject fileListInqireByFileGrpNoAll(final FwkParameterMap parameterMap){
		FwkTransferObject trans = new FwkTransferObject();
		List<FwkDataEntity> entity = comAtmaAtchFileDao.selectFileListInqireByFileGrpNoAll(parameterMap);
		List<FwkDataEntity> atchFileList = new ArrayList<FwkDataEntity>();
			
		for(int i = 0; i< entity.size(); i++){ 
			FwkDataEntity map =(FwkDataEntity) entity.get(i);
			map.put("SYS_FILE_NM",  FwkStringUtil.fmIso2Euc((String) map.get("SYS_FILE_NM")));
			atchFileList.add(map);
		}

		trans.put("atchFileList", atchFileList);
		return trans;
		
	}
	
	
	/**
	 * <pre>
	 * 1. 개요 : 첨부파일등록 (계약보증서) 
	 * 2. 처리내용 : 1. 첨부파일등록
	 *                  2. 첨부파일이력등록
	 * </pre>
	 * 
	 * @see ew.com.atma.service.ComAtmaAtchFileService#atchFileRegist(ew.fwk.prl.request.FwkParameterMap)
	 * @param parameterMap
	 * @return
	 */
	public FwkTransferObject atchFileRegist2(String atchFileGroupNoB, List<Map<String, Object>> fileList2, FwkParameterMap map) {
		FwkTransferObject trans = new FwkTransferObject();
		
		FwkParameterMap parameterMap = new FwkParameterMap();
		parameterMap.put("atchFileGroupNo", atchFileGroupNoB);
		parameterMap.put("fileList", fileList2);  
		parameterMap.put("P_SYSDATE", FwkDateUtil.getCurrentDateTimeAsString());
		parameterMap.put("P_EMPL_ID", map.getString("P_EMPL_ID"));
		
//		comAtmaAtchFileDao.insertAtchFileGroupRegist(parameterMap);
		comAtmaAtchFileDao.insertAtchFileRegist(parameterMap);
		return trans;
	}
	
	/**
	 * <pre>
	 * 1. 개요 : 첨부파일등록 
	 * 2. 처리내용 : 1. 첨부파일등록
	 *                  2. 첨부파일이력등록
	 * </pre>
	 * 
	 * @see ew.com.atma.service.ComAtmaAtchFileService#atchFileRegist(ew.fwk.prl.request.FwkParameterMap)
	 * @param parameterMap
	 * @return
	 */
	public FwkTransferObject atchFileAddRegist(String atchFileGroupNo, List<Map<String, Object>> fileList, FwkParameterMap map) {
		FwkTransferObject trans = new FwkTransferObject();

		FwkParameterMap parameterMap = new FwkParameterMap();
		parameterMap.put("atchFileGroupNo", atchFileGroupNo);
		parameterMap.put("fileList", fileList);
		parameterMap.put("P_SYSDATE", FwkDateUtil.getCurrentDateTimeAsString());
		parameterMap.put("P_EMPL_ID", map.getString("P_USER_ID"));
		comAtmaAtchFileDao.insertAtchFileRegist(parameterMap);
		return trans;
	}
	
	/**
	 * <pre> 
	 * 1. 개요 : 첨부파일등록 
	 * 2. 처리내용 : 1. 첨부파일등록
	 *                  2. 첨부파일이력등록
	 * </pre>
	 * 
	 * @see ew.com.atma.service.ComAtmaAtchFileService#atchFileRegist(ew.fwk.prl.request.FwkParameterMap)
	 * @param parameterMap
	 * @return
	 */
	public FwkTransferObject atchFileAddRegist2(String atchFileGroupNoB, List<Map<String, Object>> fileList2, FwkParameterMap map) {
		FwkTransferObject trans = new FwkTransferObject();

		FwkParameterMap parameterMap = new FwkParameterMap(); 
		parameterMap.put("atchFileGroupNo", atchFileGroupNoB);
		parameterMap.put("fileList", fileList2);
		parameterMap.put("P_SYSDATE", FwkDateUtil.getCurrentDateTimeAsString());
		parameterMap.put("P_EMPL_ID", map.getString("P_EMPL_ID"));
		comAtmaAtchFileDao.insertAtchFileRegist(parameterMap);
		return trans;
	}

	/**
	 * <pre>
	 * 1. 개요 : 첨부파일등록 
	 * 2. 처리내용 : 1. 첨부파일등록
	 *                  2. 첨부파일이력등록
	 * </pre>
	 * @Location : eGs_epro_pri.com.eunwoosoft.comm.file.service.impl.ComAtmaAtchFileServiceImpl.java
	 * @Method : atchFileRegist
	 * @author : sanghoon_joen
	 * @date : 2018. 2. 21. 
	 * @param atchFileGroupNo
	 * @param fileList
	 * @return
	 */
	/*@Override
	public FwkTransferObject atchFileRegist(String atchFileGroupNo, List<Map<String, Object>> fileList) {
		FwkTransferObject trans = new FwkTransferObject();

		FwkParameterMap parameterMap = new FwkParameterMap();
		parameterMap.put("atchFileGroupNo", atchFileGroupNo);
		parameterMap.put("fileList", fileList);

//		comAtmaAtchFileDao.insertAtchFileGroupRegist(parameterMap);
		comAtmaAtchFileDao.insertAtchFileRegist(parameterMap);
		return trans;
	}*/
	
	/**
	 * <pre>
	 * 1. 개요 : 첨부파일등록 
	 * 2. 처리내용 : 1. 첨부파일등록
	 *                  2. 첨부파일이력등록
	 * </pre>
	 * @Location : eGs_epro_pri.com.eunwoosoft.comm.file.service.impl.ComAtmaAtchFileServiceImpl.java
	 * @Method : atchFileMapRegist
	 * @author : jandi_Eun
	 * @date : 2018. 2. 21. 
	 * @param atchFileGroupNo
	 * @param fileList
	 * @return
	 */
	@Override
	public FwkTransferObject atchFileMapRegist(String atchFileGroupNo, List<Map<String, Object>> fileList, FwkParameterMap map) {
		FwkTransferObject trans = new FwkTransferObject();
		
		FwkParameterMap parameterMap = new FwkParameterMap();
		parameterMap.put("atchFileGroupNo", atchFileGroupNo);
		parameterMap.put("fileList", fileList);
		parameterMap.put("P_SYSDATE", FwkDateUtil.getCurrentDateTimeAsString());
		parameterMap.put("P_EMPL_ID", map.getString("P_USER_ID"));
		
//		comAtmaAtchFileDao.insertAtchFileGroupRegist(parameterMap);
		comAtmaAtchFileDao.insertAtchFileRegist(parameterMap);
		return trans;
	}

	/**
	 * <pre>
	 * 1. 개요 : 첨부파일목록조회_페이징 
	 * 2. 처리내용 : 1. 페이징처리된 첨부파일목록조회
	 *                  2. 첨부파일목록총건수조회
	 * </pre>
	 * @Location : eGs_epro_pri.com.eunwoosoft.comm.file.service.impl.ComAtmaAtchFileServiceImpl.java
	 * @Method : 
	 * @author : sanghoon_joen
	 * @date : 2018. 2. 21. 
	 * @param atchFileGroupNo
	 * @param fileList
	 * @return
	 */
	@Override
	public FwkTransferObject atchFileListInqireWithPgng(FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();
//		trans.put(ATCH_FILE_LIST, comAtmaAtchFileDao.selectAtchFileListInqireWithPgng(parameterMap));
//		trans.put(ATCH_FILE_LIST_TOTCNT, comAtmaAtchFileDao.selectAtchFileListInqireTotcnt(parameterMap));
		return trans;
	}

	/**
	 * <pre>
	 * 1. 개요 : 첨부파일상세조회 
	 * 2. 처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri.com.eunwoosoft.comm.file.service.impl.ComAtmaAtchFileServiceImpl.java
	 * @Method : 
	 * @author : sanghoon_joen
	 * @date : 2018. 2. 21. 
	 * @param atchFileGroupNo
	 * @param fileList
	 * @return
	 */
	@Override
	public FwkTransferObject atchFileDetailInqire(FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();
//		trans.put(ATCH_FILE, comAtmaAtchFileDao.selectAtchFileDetailInqire(parameterMap));
		return trans;
	}

	/**
	 * <pre>
	 * 1. 개요 : 첨부파일그룹일련번호에의한 첨부파일목록조회 
	 * 2. 처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri.com.eunwoosoft.comm.file.service.impl.ComAtmaAtchFileServiceImpl.java
	 * @Method : 
	 * @author : sanghoon_joen
	 * @date : 2018. 2. 21. 
	 * @param atchFileGroupNo
	 * @param fileList
	 * @return
	 */
	@Override
	public FwkTransferObject atchFileListInqireByAtchFileGroupNo(FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();
		trans.put(ATCH_FILE_LIST, comAtmaAtchFileDao.selectAtchFileListInqireByAtchFileGroupNo(parameterMap));
		return trans;
	}

	/**
	 * <pre>
	 * 1. 개요 : 첨부파일삭제
	 * 2. 처리내용 : 1. 첨부파일이력등록
	 *                  2. 첨부파일삭제
	 * </pre>
	 * @Location : eGs_epro_pri.com.eunwoosoft.comm.file.service.impl.ComAtmaAtchFileServiceImpl.java
	 * @Method : 
	 * @author : sanghoon_joen
	 * @date : 2018. 2. 21. 
	 * @param atchFileGroupNo
	 * @param fileList
	 * @return
	 */
	@Override
	public FwkTransferObject atchFileDelete(List<Map<String, Object>> fileList) {
		FwkParameterMap parameterMap = new FwkParameterMap();

		FwkTransferObject trans = new FwkTransferObject();
		comAtmaAtchFileHistDao.insertAtchFileHistRegist(parameterMap);

		parameterMap.put("fileList", fileList);
		comAtmaAtchFileDao.deleteAtchFileDelete(parameterMap);		

		return trans;
	}
	
	/**
	 * <pre>
	 * 1. 개요 : 첨부파일삭제
	 * 2. 처리내용 : 1. 첨부파일이력등록
	 *                  2. 첨부파일삭제
	 * </pre>
	 * @Location : eGs_epro_pri.com.eunwoosoft.comm.file.service.impl.ComAtmaAtchFileServiceImpl.java
	 * @Method : 
	 * @author : sanghoon_joen
	 * @date : 2018. 2. 21. 
	 * @param atchFileGroupNo
	 * @param fileList
	 * @return
	 */
	@Override
	public FwkTransferObject atchFileDeleteByNm(List<Map<String, Object>> fileList, String groupNoKey) {
		FwkParameterMap parameterMap = new FwkParameterMap();
		
		FwkTransferObject trans = new FwkTransferObject();
		comAtmaAtchFileHistDao.insertAtchFileHistByNmRegist(parameterMap);
		
		parameterMap.put("P_ATCHMNFL_GROUP_NO", groupNoKey);
		parameterMap.put("fileList", fileList);
//		comAtmaAtchFileDao.deleteAtchFileDeleteByNm(parameterMap);		
		
		return trans;
	}

	/**
	 * <pre>
	 * 1. 개요 : 첨부파일수정등록 
	 * 2. 처리내용 : 1. 첨부파일등록
	 *                  2. 첨부파일이력등록
	 *                  3. 첨부파일삭제
	 * </pre>
	 * @Location : eGs_epro_pri.com.eunwoosoft.comm.file.service.impl.ComAtmaAtchFileServiceImpl.java
	 * @Method : 
	 * @author : sanghoon_joen
	 * @date : 2018. 2. 21. 
	 * @param atchFileGroupNo
	 * @param fileList
	 * @return
	 */
	/*@Override
	public FwkTransferObject atchFileUpt(String atchFileGroupNo, List<Map<String, Object>> newFileList, List<Map<String, Object>> deleteFileList) {
		FwkTransferObject trans = new FwkTransferObject();

		FwkParameterMap parameterMap = new FwkParameterMap();
		parameterMap.put("atchFileGroupNo", atchFileGroupNo);

		if(deleteFileList != null) {
			parameterMap.put("fileList", deleteFileList);
			comAtmaAtchFileDao.deleteAtchFileDelete(parameterMap);	
		}
		if(newFileList != null) {
			parameterMap.put("fileList", newFileList);

//			int cnt = comAtmaAtchFileDao.selectAtchFileGroupNoCnt(parameterMap);
//			if (cnt == 0) {
//				comAtmaAtchFileDao.insertAtchFileGroupRegist(parameterMap);
//			}
			comAtmaAtchFileDao.insertAtchFileRegist(parameterMap);			
		}

		return trans;
	}*/

	/**
	 * <pre>
	 * 1. 개요 : 첨부파일등록 
	 * 2. 처리내용 : 1. 첨부파일등록
	 *                  2. 첨부파일이력등록
	 * </pre>
	 * @Location : eGs_epro_pri.com.eunwoosoft.comm.file.service.impl.ComAtmaAtchFileServiceImpl.java
	 * @Method : 
	 * @author : sanghoon_joen
	 * @date : 2018. 2. 21. 
	 * @param atchFileGroupNo
	 * @param fileList
	 * @return
	 */
	@Override
	public FwkTransferObject atchFileRegistLocalToExt(String atchFileGroupNo, String fileSaveEsbInternPath, List<Map<String, Object>> fileList) {		
		FwkTransferObject trans = new FwkTransferObject();

		FwkParameterMap parameterMap = new FwkParameterMap();
		parameterMap.put("atchFileGroupNo", atchFileGroupNo);
		parameterMap.put("fileList", fileList);

		// 첨부파일 정보 저장
//		comAtmaAtchFileDao.insertAtchFileGroupRegist(parameterMap);
//		comAtmaAtchFileDao.insertAtchFileRegistLocal(parameterMap);
		
		// 외부전송 파일 복사 (send영역)
//		atchFileCopyToSend(null, fileSaveEsbInternPath, fileList);		
		
		return trans;
	}
	
	/**
	 * <pre>
	 * 1. 개요 : 첨부파일등록 ( 내부 )
	 * 2. 처리내용 : 1. 첨부파일등록
	 *                  2. 첨부파일이력등록
	 * </pre>
	 * @Location : eGs_epro_pri.com.eunwoosoft.comm.file.service.impl.ComAtmaAtchFileServiceImpl.java
	 * @Method : 
	 * @author : sanghoon_joen
	 * @date : 2018. 2. 21. 
	 * @param atchFileGroupNo
	 * @param fileList
	 * @return
	 */
	@Override
	public FwkTransferObject atchFileRegistLocal(String atchFileGroupNo, List<Map<String, Object>> fileList) {
		FwkTransferObject trans = new FwkTransferObject();

		FwkParameterMap parameterMap = new FwkParameterMap();
		parameterMap.put("atchFileGroupNo", atchFileGroupNo);
		parameterMap.put("fileList", fileList);

//		comAtmaAtchFileDao.insertAtchFileGroupRegist(parameterMap);
//		comAtmaAtchFileDao.insertAtchFileRegistLocal(parameterMap);
		
		return trans;
	}

	/**
	 * 
	 * <pre>
	 * 1.개요 : 
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri.com.eunwoosoft.comm.file.service.impl.ComAtmaAtchFileServiceImpl.java
	 * @Method : atchFileUptLocalToExt
	 * @author : sanghoon_joen
	 * @date : 2018. 2. 21. 
	 * @param atchFileGroupNo
	 * @param fileSaveEsbInternPath
	 * @param newFileList
	 * @param deleteFileList
	 * @return
	 */
	@Override
	public FwkTransferObject atchFileUptLocalToExt(String atchFileGroupNo, String fileSaveEsbInternPath, List<Map<String, Object>> newFileList, List<Map<String, Object>> deleteFileList) {
		FwkTransferObject trans = new FwkTransferObject();
		
		FwkParameterMap parameterMap = new FwkParameterMap();
		parameterMap.put("atchFileGroupNo", atchFileGroupNo);
		
		//DB에 파일 삭제
		if(deleteFileList != null) {
			parameterMap.put("fileList", deleteFileList);
			comAtmaAtchFileDao.deleteAtchFileDelete(parameterMap);	
		}
		//DB에 파일 추가
		if(newFileList != null) {
			parameterMap.put("fileList", newFileList);
			
//			int cnt = comAtmaAtchFileDao.selectAtchFileGroupNoCnt(parameterMap);
//			if (cnt == 0) {
//				comAtmaAtchFileDao.insertAtchFileGroupRegist(parameterMap);
//			}
			comAtmaAtchFileDao.insertAtchFileRegist(parameterMap);			
			
			// 외부전송 파일 복사 (send영역)
//			atchFileCopyToSend(null, fileSaveEsbInternPath, newFileList);		
		}
		
		return trans;
	}

	@Override
	public FwkTransferObject atchFileRegist(FwkParameterMap paramaterMap) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FwkTransferObject atchFileUpt(FwkParameterMap paramaterMap) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FwkTransferObject acptWaitAtchFileListByFileGrpNo(FwkParameterMap paramaterMap) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FwkTransferObject atchFileCopyToSend(String fileSavePathPrefix, String fileSaveEsbInternPath,
			List<Map<String, Object>> fileList) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FwkTransferObject fileListRegist(String fileGrpNo, List<Map<String, Object>> fileList,
			FwkParameterMap parameterMap) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FwkTransferObject fileListAddRegist(String fileGrpNo, List<Map<String, Object>> fileList,
			FwkParameterMap map) {
		// TODO Auto-generated method stub
		return null;
	}

}
