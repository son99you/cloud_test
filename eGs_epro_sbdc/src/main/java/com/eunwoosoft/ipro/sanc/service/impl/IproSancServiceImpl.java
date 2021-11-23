package com.eunwoosoft.ipro.sanc.service.impl; 

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.eunwoosoft.comm.util.SftpUtil;
import com.eunwoosoft.frwk.bul.AbstractFwkService;
import com.eunwoosoft.frwk.fol.dto.FwkDataEntity;
import com.eunwoosoft.frwk.fol.dto.FwkTransferObject;
import com.eunwoosoft.frwk.fol.exception.FwkSysException;
import com.eunwoosoft.frwk.fol.util.FwkDateUtil;
import com.eunwoosoft.frwk.fol.util.FwkFormatUtil;
import com.eunwoosoft.frwk.fol.util.FwkMessageUtil;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;
import com.eunwoosoft.ipro.sanc.dao.IproSancDao;
import com.eunwoosoft.ipro.sanc.service.IproSancService;

/**
 * IproSancServiceImpl
 * 
 * 
 * @author juyeon_Le
 *
 */
@Service("iproSancService")
public class IproSancServiceImpl extends AbstractFwkService implements IproSancService {
	@SuppressWarnings("unused")
	private static final Logger LOG = LoggerFactory.getLogger(IproSancServiceImpl.class);
	
	@Resource(name="iproSancDao")
	private IproSancDao iproSancDao;
	
	
	// 결재정보 저장
	public FwkTransferObject apprInfoRegist(final FwkParameterMap parameterMap) throws Exception{
		
		FwkTransferObject trans = new FwkTransferObject();
		FwkDataEntity entity = null;
		String[] apprDocNoArr = null;
		String docYearmon = "";
		String docNumber = "";
		
		try {
				FwkParameterMap param1 = new FwkParameterMap();

				param1.put("P_APPR_STCD", "0000");		//실제 기안올리지 않고 , 결재 창만 띄울경우
				param1.put("P_GB", parameterMap.getString("P_GB"));		//페이지 구분
				param1.put("P_SUB_TYPE", parameterMap.getString("P_SUB_TYPE"));	
				param1.put("P_KEY1", parameterMap.getString("P_KEY1"));	
				param1.put("P_KEY2", parameterMap.getString("P_KEY2"));	
				param1.put("P_MODE", parameterMap.getString("P_MODE"));	
				param1.put("P_APPR_CNTC_KEY", parameterMap.getString("P_APPR_CNTC_KEY"));	
				param1.put("P_REGR_ID", parameterMap.getString("P_REGR_ID"));	//사번
				param1.put("P_REGR_NM", parameterMap.getString("P_REGR_NM"));
				param1.put("P_REG_DT", FwkDateUtil.getCurrentDateTimeAsString());
				
				String root = FwkMessageUtil.getMessage("SFTP.APPR.DIR");	//root 경로
				String volume = FwkMessageUtil.getMessage("SFTP.APPR.VOLUME");	// volume 경로
				
				String fileDirPath = root+volume;	//sftp 보낼경로
				String fileDirPath2 = volume + FwkDateUtil.getCurrentDateAsString("yyyy") + FwkDateUtil.getCurrentDateAsString("MM") + "/"; // T_IF_APPR_CNTC_FILE  SYS_FILE_NM컬럼에 저장될 경로
				//[subType] = inner 일반 품의문 , article: 물품결의서)
				//[gb] = PR: 설계 , BF: 사전규격,   BI: 입찰,  CT:계약
				//[apprGbn] = A: 사업시행품의, 사업시행품의+계약실행품의   B:계약실행품의,심사평가 실행품의

				if("inner".equals(parameterMap.getString("P_SUB_TYPE"))){	// 일반 품의문
					if("PR".equals(parameterMap.getString("P_GB"))){ 	//결재구분
						
						if("drft".equals(parameterMap.getString("P_MODE"))){ 	//연계모드 (drft :  기안, view : 조회)
							//1.결재테이블 insert
							iproSancDao.apprPrInfoRegist(param1);
							
							parameterMap.put("P_FILE_GRP_NO", param1.getString("P_KEY1")+"-"+param1.getString("P_APPR_CNTC_KEY")); //연계 키값 - 파일그룹번호
							
							// 2. 계약설계 첨부파일 List select -> 첨부파일 테이블 insert
							List<FwkDataEntity> fileList = iproSancDao.selectPrFileInfo(parameterMap);
							
							//첨부파일 삭제 
							iproSancDao.deleteIfApprCntcFile(parameterMap);
							for(int i=0; i<fileList.size(); i++){
								// 3.첨부파일 테이블 insert
								FwkDataEntity dataEntity = fileList.get(i);
								
								parameterMap.put("P_TSK_VKEY2",fileDirPath);// sftp 보낼 파일경로
								parameterMap.put("P_SYS_FILE_NM", fileDirPath2 + dataEntity.get("SV_FILE_NM"));//sftp 보낼 저장될 파일경로 +파일명
								parameterMap.put("P_FILE_DOC_NM",dataEntity.get("SYS_FILE_NM"));//보여주는 파일명
							    parameterMap.put("P_SV_FILE_NM",dataEntity.get("SV_FILE_NM"));//저장파일명
							    parameterMap.put("P_FILE_LCTN",dataEntity.get("FILE_LCTN"));//저장된 파일 경로
								iproSancDao.insertIfApprCntcFile(parameterMap);
							}
							
							if("Y".equals(FwkMessageUtil.getMessage("SFTP.APPR.YN"))){	//sftp Y일때만 타라~
								// 4.sftp 호출
								apprFileSend(parameterMap);
							}
							
						}else if("view".equals(parameterMap.getString("P_MODE"))){ 
							entity = iproSancDao.selectApprInfo(param1);
							String apprDocNoString = (String) entity.get("APPR_DOC_NO");
							apprDocNoArr = apprDocNoString.split("\\|"); // aaaa|0000
							docYearmon = apprDocNoArr[0];
							docNumber = apprDocNoArr[1];
						}

						trans.put("P_REQSEQ" , param1.getString("P_KEY1")+"-"+param1.getString("P_APPR_CNTC_KEY") +"|");	//전자계약 키 
					}else if("BF".equals(parameterMap.getString("P_GB"))){ 	//사전규격
						//T_BI_BFAN_APPR_CNTC 저장 
						if("drft".equals(parameterMap.getString("P_MODE"))){ 	//연계모드 (drft :  기안, view : 조회)
							iproSancDao.apprBfInfoRegist(param1);
							
							parameterMap.put("P_FILE_GRP_NO", param1.getString("P_KEY1")+"-"+param1.getString("P_APPR_CNTC_KEY")); //연계 키값 - 파일그룹번호
							
							// 2. 사전규격첨부파일 List select -> 첨부파일 테이블 insert
							List<FwkDataEntity> fileList = iproSancDao.selectBfFileInfo(parameterMap);
							//첨부파일 삭제 
							iproSancDao.deleteIfApprCntcFile(parameterMap);
							for(int i=0; i<fileList.size(); i++){
								// 3.첨부파일 테이블 insert
								FwkDataEntity dataEntity = fileList.get(i);
								
								parameterMap.put("P_TSK_VKEY2",fileDirPath);// sftp 보낼 파일경로
								parameterMap.put("P_SYS_FILE_NM", fileDirPath2 + dataEntity.get("SV_FILE_NM"));//sftp 보낼 저장될 파일경로 +파일명
								parameterMap.put("P_FILE_DOC_NM",dataEntity.get("SYS_FILE_NM"));//보여주는 파일명
							    parameterMap.put("P_SV_FILE_NM",dataEntity.get("SV_FILE_NM"));//저장파일명
							    parameterMap.put("P_FILE_LCTN",dataEntity.get("FILE_LCTN"));//저장된 파일 경로
								iproSancDao.insertIfApprCntcFile(parameterMap);
							}
							
							if("Y".equals(FwkMessageUtil.getMessage("SFTP.APPR.YN"))){	//sftp Y일때만 타라~
								// 4.sftp 호출
								apprFileSend(parameterMap);
							}
							
						}else if("view".equals(parameterMap.getString("P_MODE"))){
							entity = iproSancDao.selectBfApprInfo(param1);
							String apprDocNoString = (String) entity.get("APPR_DOC_NO");
							apprDocNoArr = apprDocNoString.split("\\|"); // aaaa|0000
							docYearmon = apprDocNoArr[0];
							docNumber = apprDocNoArr[1];
						}
						
						trans.put("P_REQSEQ" , param1.getString("P_KEY1")+"-"+param1.getString("P_APPR_CNTC_KEY")+"|");	//전자계약 키 
						
					}else if("BI".equals(parameterMap.getString("P_GB"))){ 	//입찰
						//T_BI_APPR_CNTC 저장 
						if("drft".equals(parameterMap.getString("P_MODE"))){ 	//연계모드 (drft :  기안, view : 조회)
							iproSancDao.apprBiInfoRegist(param1);

							parameterMap.put("P_FILE_GRP_NO", param1.getString("P_KEY1")+"-"+param1.getString("P_KEY2")+"-"+param1.getString("P_APPR_CNTC_KEY")); //연계 키값 - 파일그룹번호
							
							// 2. 입찰첨부파일 List select -> 첨부파일 테이블 insert
							List<FwkDataEntity> fileList = iproSancDao.selectBiFileInfo(parameterMap);
							//첨부파일 삭제 
							iproSancDao.deleteIfApprCntcFile(parameterMap);
							for(int i=0; i<fileList.size(); i++){
								// 3.첨부파일 테이블 insert
								FwkDataEntity dataEntity = fileList.get(i);
								
								parameterMap.put("P_TSK_VKEY2",fileDirPath);// sftp 보낼 파일경로
								parameterMap.put("P_SYS_FILE_NM", fileDirPath2 + dataEntity.get("SV_FILE_NM"));//sftp 보낼 저장될 파일경로 +파일명
								parameterMap.put("P_FILE_DOC_NM",dataEntity.get("SYS_FILE_NM"));//보여주는 파일명
							    parameterMap.put("P_SV_FILE_NM",dataEntity.get("SV_FILE_NM"));//저장파일명
							    parameterMap.put("P_FILE_LCTN",dataEntity.get("FILE_LCTN"));//저장된 파일 경로
								iproSancDao.insertIfApprCntcFile(parameterMap);
							}
							
							if("Y".equals(FwkMessageUtil.getMessage("SFTP.APPR.YN"))){	//sftp Y일때만 타라~
								// 4.sftp 호출
								apprFileSend(parameterMap);
							}
							
						}else if("view".equals(parameterMap.getString("P_MODE"))){
							entity = iproSancDao.selectBiApprInfo(param1);
							String apprDocNoString = (String) entity.get("APPR_DOC_NO");
							apprDocNoArr = apprDocNoString.split("\\|"); // aaaa|0000
							docYearmon = apprDocNoArr[0];
							docNumber = apprDocNoArr[1];
						}
						trans.put("P_REQSEQ" , param1.getString("P_KEY1")+"-"+param1.getString("P_KEY2")+"-"+param1.getString("P_APPR_CNTC_KEY")+"|");	//전자계약 키 
					}else if("CT".equals(parameterMap.getString("P_GB"))){//계약
						//T_CT_APPR_CNTC 저장 
						if("drft".equals(parameterMap.getString("P_MODE"))){ 	//연계모드 (drft :  기안, view : 조회)
							iproSancDao.apprCtInfoRegist(param1);
							
							parameterMap.put("P_FILE_GRP_NO", param1.getString("P_KEY1")+"-"+param1.getString("P_KEY2")+"-"+param1.getString("P_APPR_CNTC_KEY")); //연계 키값 - 파일그룹번호
							
							// 2. 계약첨부파일 List select -> 첨부파일 테이블 insert
							//표준계약 또는 결의서일 경우
							if("A".equals(parameterMap.getString("contKdcd")) || "D".equals(parameterMap.getString("contKdcd"))){	 
								//parameterMap.put("P_CONT_FSCD", "A1");	//계약서 pdf 가져오기
								parameterMap.put("P_CONT_FSCD", "A");	//계약서 xml 빼고 모든 첨부파일 가져오기로 수정
							//오프라인 또는 비정형일 경우에는 xml을 떨구지 않음
							}else if("B".equals(parameterMap.getString("contKdcd")) || "C".equals(parameterMap.getString("contKdcd"))){
								//parameterMap.put("P_CONT_FSCD", "A");	//첨부한 계약서 가져오기 
							}

							List<FwkDataEntity> fileList = iproSancDao.selectCtFileInfo(parameterMap);
							//첨부파일 삭제 
							iproSancDao.deleteIfApprCntcFile(parameterMap);
							for(int i=0; i<fileList.size(); i++){
								// 3.첨부파일 테이블 insert
								FwkDataEntity dataEntity = fileList.get(i);
								
								parameterMap.put("P_TSK_VKEY2",fileDirPath);// sftp 보낼 파일경로
								parameterMap.put("P_SYS_FILE_NM", fileDirPath2 + dataEntity.get("SV_FILE_NM"));//sftp 보낼 저장될 파일경로 +파일명
								parameterMap.put("P_FILE_DOC_NM",dataEntity.get("SYS_FILE_NM"));//보여주는 파일명
							    parameterMap.put("P_SV_FILE_NM",dataEntity.get("SV_FILE_NM"));//저장파일명
							    parameterMap.put("P_FILE_LCTN",dataEntity.get("FILE_LCTN"));//저장된 파일 경로
								iproSancDao.insertIfApprCntcFile(parameterMap);
							}
							
							if("Y".equals(FwkMessageUtil.getMessage("SFTP.APPR.YN"))){	//sftp Y일때만 타라~
								// 4.sftp 호출
								apprFileSend(parameterMap);
							}
							
						}else if("view".equals(parameterMap.getString("P_MODE"))){
							entity = iproSancDao.selectCtApprInfo(param1);
							String apprDocNoString = (String) entity.get("APPR_DOC_NO");
							apprDocNoArr = apprDocNoString.split("\\|"); // aaaa|0000
							docYearmon = apprDocNoArr[0];
							docNumber = apprDocNoArr[1];
						}
						trans.put("P_REQSEQ" , param1.getString("P_KEY1")+"-"+param1.getString("P_KEY2")+ "-" +param1.getString("P_APPR_CNTC_KEY")+"|");	//전자계약 키 
					} 
						
				}else{	//결의서(article)일 경우	키값 넘길때 | 붙이지 않고
					if("CT".equals(parameterMap.getString("P_GB"))){
						
						if("drft".equals(parameterMap.getString("P_MODE"))){ 	//연계모드 (drft :  기안, view : 조회)
							//1.결재테이블 insert
							iproSancDao.apprCtInfoRegist(param1);
							parameterMap.put("P_FILE_GRP_NO", param1.getString("P_KEY1")+"-"+param1.getString("P_KEY2")+"-" +param1.getString("P_APPR_CNTC_KEY")); //연계 키값 - 파일그룹번호
							
							//2. 결의서 html 생성 및 떨궈
							parameterMap.put("P_CONT_NO", param1.getString("P_KEY1")); 
							parameterMap.put("P_CHNG_NGR", param1.getString("P_KEY2")); 
							Map<String,String> htmlInfo = ctrtcCreateHtml(parameterMap);
							//3.결의서 HTML 떨군 파일 저장할 필요 없고 바로 sftp 전송
						     parameterMap.put("P_SYS_FILE_NM",htmlInfo.get("P_SYS_FILE_NM"));//시스템파일명_저장파일명포함
						      
							if("Y".equals(FwkMessageUtil.getMessage("SFTP.APPR.YN"))){	//sftp Y일때만 타라~
								// 4.sftp 호출
								htmlFileSend(parameterMap);
							}
							
						     //첨부파일 삭제 
						     iproSancDao.deleteIfApprCntcFile(parameterMap);
							   //표준계약 또는 결의서일 경우
								if("A".equals(parameterMap.getString("contKdcd")) || "D".equals(parameterMap.getString("contKdcd"))){	 
									//parameterMap.put("P_CONT_FSCD", "A1");	//계약서 pdf 가져오기
									parameterMap.put("P_CONT_FSCD", "A");	//계약서 xml 빼고 모든 첨부파일 가져오기로 수정
								//오프라인 또는 비정형일 경우에는 xml을 떨구지 않음
								}else if("B".equals(parameterMap.getString("contKdcd")) || "C".equals(parameterMap.getString("contKdcd"))){
									//parameterMap.put("P_CONT_FSCD", "A");	//첨부한 계약서 가져오기 
								}
							 List<FwkDataEntity> fileList = iproSancDao.selectCtFileInfo(parameterMap);
							 
							 for(int i=0; i<fileList.size(); i++){
								// 3.첨부파일 테이블 insert
								FwkDataEntity dataEntity = fileList.get(i);
								
								parameterMap.put("P_TSK_VKEY2",fileDirPath);// sftp 보낼 파일경로
								parameterMap.put("P_SYS_FILE_NM", fileDirPath2 + dataEntity.get("SV_FILE_NM"));//sftp 보낼 저장될 파일경로 +파일명
								parameterMap.put("P_FILE_DOC_NM",dataEntity.get("SYS_FILE_NM"));//보여주는 파일명
							    parameterMap.put("P_SV_FILE_NM",dataEntity.get("SV_FILE_NM"));//저장파일명
							    parameterMap.put("P_FILE_LCTN",dataEntity.get("FILE_LCTN"));//저장된 파일 경로
								iproSancDao.insertIfApprCntcFile(parameterMap);
							}
							
							// 4. 파일 전송 apprFileSend
							if("Y".equals(FwkMessageUtil.getMessage("SFTP.APPR.YN"))){	//sftp Y일때만 타라~
								// 4.sftp 호출
								apprFileSend(parameterMap);
							}
						}else if("view".equals(parameterMap.getString("P_MODE"))){
							entity = iproSancDao.selectCtApprInfo(param1);
							String apprDocNoString = (String) entity.get("APPR_DOC_NO");
							apprDocNoArr = apprDocNoString.split("\\|"); // aaaa|0000
							docYearmon = apprDocNoArr[0];
							docNumber = apprDocNoArr[1];
						}
						
						trans.put("P_REQSEQ" , param1.getString("P_KEY1")+"-"+param1.getString("P_KEY2")+"-" +param1.getString("P_APPR_CNTC_KEY"));	//전자계약 키 
					}
				}
				
				trans.put("P_SUB_TYPE" , param1.getString("P_SUB_TYPE"));
				trans.put("P_REGR_ID" , param1.getString("P_REGR_ID"));
				trans.put("docYearmon" , docYearmon );
				trans.put("docNumber" , docNumber);
				trans.put("result"   ,"success");
				
			} catch (FwkSysException e) {
				trans.put("result","fail");
			} 
		
		return trans;
	}
	
	// 결재정보 저장
	// 일괄결재는 프리폼만 가능 inner
	
	public FwkTransferObject apprInfoRegistAll(final FwkParameterMap parameterMap) throws Exception{
		
		FwkTransferObject trans = new FwkTransferObject();
		FwkDataEntity entity = null;
		String[] apprDocNoArr = null;
		String[] key1 = null;
		String[] key2 = null;
		String docYearmon = "";
		String docNumber = "";
		String reqSeq = "";
		
		try {
				FwkParameterMap param1 = new FwkParameterMap();
				param1.put("P_APPR_STCD", "0000");		//실제 기안올리지 않고 , 결재 창만 띄울경우
				param1.put("P_GB", parameterMap.getString("P_GB"));		//페이지 구분
				param1.put("P_SUB_TYPE", parameterMap.getString("P_SUB_TYPE"));	
				//	param1.put("P_KEY1", parameterMap.getString("P_KEY1"));	
				//	param1.put("P_KEY2", parameterMap.getString("P_KEY2"));	
				param1.put("P_MODE", parameterMap.getString("P_MODE"));		//무조건 drft
				param1.put("P_APPR_CNTC_KEY", parameterMap.getString("P_APPR_CNTC_KEY"));	
				param1.put("P_REGR_ID", parameterMap.getString("P_REGR_ID"));	//사번
				param1.put("P_REGR_NM", parameterMap.getString("P_REGR_NM"));
				param1.put("P_REG_DT", FwkDateUtil.getCurrentDateTimeAsString());
				String root = FwkMessageUtil.getMessage("SFTP.APPR.DIR");	//root 경로
				String volume = FwkMessageUtil.getMessage("SFTP.APPR.VOLUME");	// volume 경로
				
				String fileDirPath = root+volume;	//sftp 보낼경로
				String fileDirPath2 = volume + FwkDateUtil.getCurrentDateAsString("yyyy") + FwkDateUtil.getCurrentDateAsString("MM") + "/"; // T_IF_APPR_CNTC_FILE  SYS_FILE_NM컬럼에 저장될 경로
				//[subType] = inner 일반 품의문 , article: 물품결의서)
				//[gb] = PR: 설계 , BF: 사전규격,   BI: 입찰,  CT:계약
				//[apprGbn] = A: 사업시행품의, 사업시행품의+계약실행품의   B:계약실행품의,심사평가 실행품의

				if("inner".equals(parameterMap.getString("P_SUB_TYPE"))){	// 일반 품의문
					if("PR".equals(parameterMap.getString("P_GB"))){ 	//설계 일괄결재
							//1.결재테이블 insert
							key1 = parameterMap.getString("P_KEY1").split("\\|");

							for( int k=0; k<key1.length; k++){
								param1.put("P_KEY1", key1[k]);
								parameterMap.put("P_KEY1", key1[k]);
								
								iproSancDao.apprPrInfoRegist(param1);
								parameterMap.put("P_FILE_GRP_NO", param1.getString("P_KEY1")+"-"+param1.getString("P_APPR_CNTC_KEY")); //연계 키값 - 파일그룹번호
								
								// 2. 계약설계 첨부파일 List select -> 첨부파일 테이블 insert
								List<FwkDataEntity> fileList = iproSancDao.selectPrFileInfo(parameterMap);
								
								//첨부파일 삭제 
								iproSancDao.deleteIfApprCntcFile(parameterMap);
								for(int i=0; i<fileList.size(); i++){
									// 3.첨부파일 테이블 insert
									FwkDataEntity dataEntity = fileList.get(i);
									
									parameterMap.put("P_TSK_VKEY2",fileDirPath);// sftp 보낼 파일경로
									parameterMap.put("P_SYS_FILE_NM", fileDirPath2 + dataEntity.get("SV_FILE_NM"));//sftp 보낼 저장될 파일경로 +파일명
									parameterMap.put("P_FILE_DOC_NM",dataEntity.get("SYS_FILE_NM"));//보여주는 파일명
								    parameterMap.put("P_SV_FILE_NM",dataEntity.get("SV_FILE_NM"));//저장파일명
								    parameterMap.put("P_FILE_LCTN",dataEntity.get("FILE_LCTN"));//저장된 파일 경로
									iproSancDao.insertIfApprCntcFile(parameterMap);
								}
								
								if("Y".equals(FwkMessageUtil.getMessage("SFTP.APPR.YN"))){	//sftp Y일때만 타라~
									// 4.sftp 호출
									apprFileSend(parameterMap);
								}
								
								reqSeq += param1.getString("P_KEY1")+"-"+param1.getString("P_APPR_CNTC_KEY")+"|";
							}

						trans.put("P_REQSEQ" , reqSeq);	//전자계약 키 
					}else if("BF".equals(parameterMap.getString("P_GB"))){	//사전규격 일괄결재
						//1.결재테이블 insert
						key1 = parameterMap.getString("P_KEY1").split("\\|");

						for( int k=0; k<key1.length; k++){
							param1.put("P_KEY1", key1[k]);
							parameterMap.put("P_KEY1", key1[k]);
							
							iproSancDao.apprBfInfoRegist(param1);
							parameterMap.put("P_FILE_GRP_NO", param1.getString("P_KEY1")+"-"+param1.getString("P_APPR_CNTC_KEY")); //연계 키값 - 파일그룹번호
							
							// 2. 계약설계 첨부파일 List select -> 첨부파일 테이블 insert
							List<FwkDataEntity> fileList = iproSancDao.selectBfFileInfo(parameterMap);
							
							//첨부파일 삭제 
							iproSancDao.deleteIfApprCntcFile(parameterMap);
							for(int i=0; i<fileList.size(); i++){
								// 3.첨부파일 테이블 insert
								FwkDataEntity dataEntity = fileList.get(i);
								
								parameterMap.put("P_TSK_VKEY2",fileDirPath);// sftp 보낼 파일경로
								parameterMap.put("P_SYS_FILE_NM", fileDirPath2 + dataEntity.get("SV_FILE_NM"));//sftp 보낼 저장될 파일경로 +파일명
								parameterMap.put("P_FILE_DOC_NM",dataEntity.get("SYS_FILE_NM"));//보여주는 파일명
							    parameterMap.put("P_SV_FILE_NM",dataEntity.get("SV_FILE_NM"));//저장파일명
							    parameterMap.put("P_FILE_LCTN",dataEntity.get("FILE_LCTN"));//저장된 파일 경로
								iproSancDao.insertIfApprCntcFile(parameterMap);
							}
							
							if("Y".equals(FwkMessageUtil.getMessage("SFTP.APPR.YN"))){	//sftp Y일때만 타라~
								// 4.sftp 호출
								apprFileSend(parameterMap);
							}
							
							reqSeq += param1.getString("P_KEY1")+"-"+param1.getString("P_APPR_CNTC_KEY")+"|";
						}

					trans.put("P_REQSEQ" , reqSeq);	//전자계약 키 
				}else if("BI".equals(parameterMap.getString("P_GB"))){	//입찰 일괄결재
					//1.결재테이블 insert
					key1 = parameterMap.getString("P_KEY1").split("\\|");
					key2 = parameterMap.getString("P_KEY2").split("\\|");

					for( int k=0; k<key1.length; k++){
						param1.put("P_KEY1", key1[k]);
						param1.put("P_KEY2", key2[k]);
						parameterMap.put("P_KEY1", key1[k]);
						parameterMap.put("P_KEY2", key2[k]);
						
						iproSancDao.apprBiInfoRegist(param1);
						parameterMap.put("P_FILE_GRP_NO", param1.getString("P_KEY1")+"-"+param1.getString("P_KEY2")+"-"+param1.getString("P_APPR_CNTC_KEY")); //연계 키값 - 파일그룹번호

						// 2. 계약설계 첨부파일 List select -> 첨부파일 테이블 insert
						List<FwkDataEntity> fileList = iproSancDao.selectBiFileInfo(parameterMap);
						
						//첨부파일 삭제 
						iproSancDao.deleteIfApprCntcFile(parameterMap);
						for(int i=0; i<fileList.size(); i++){
							// 3.첨부파일 테이블 insert
							FwkDataEntity dataEntity = fileList.get(i);
							
							parameterMap.put("P_TSK_VKEY2",fileDirPath);// sftp 보낼 파일경로
							parameterMap.put("P_SYS_FILE_NM", fileDirPath2 + dataEntity.get("SV_FILE_NM"));//sftp 보낼 저장될 파일경로 +파일명
							parameterMap.put("P_FILE_DOC_NM",dataEntity.get("SYS_FILE_NM"));//보여주는 파일명
						    parameterMap.put("P_SV_FILE_NM",dataEntity.get("SV_FILE_NM"));//저장파일명
						    parameterMap.put("P_FILE_LCTN",dataEntity.get("FILE_LCTN"));//저장된 파일 경로
							iproSancDao.insertIfApprCntcFile(parameterMap);
						}
						
						if("Y".equals(FwkMessageUtil.getMessage("SFTP.APPR.YN"))){	//sftp Y일때만 타라~
							// 4.sftp 호출
							apprFileSend(parameterMap);
						}
						
						reqSeq += param1.getString("P_KEY1")+"-"+param1.getString("P_KEY2")+"-"+param1.getString("P_APPR_CNTC_KEY")+"|";
					}

					trans.put("P_REQSEQ" , reqSeq);	//전자계약 키
					
				}else if("CT".equals(parameterMap.getString("P_GB"))){	//계약요청 일괄결재
					//1.결재테이블 insert
					key1 = parameterMap.getString("P_KEY1").split("\\|");
					key2 = parameterMap.getString("P_KEY2").split("\\|");
					String[] contKdcd = parameterMap.getString("contKdcd").split("\\|");

					for( int k=0; k<key1.length; k++){
						param1.put("P_KEY1", key1[k]);
						param1.put("P_KEY2", key2[k]);
						parameterMap.put("P_KEY1", key1[k]);
						parameterMap.put("P_KEY2", key2[k]);
						
						iproSancDao.apprCtInfoRegist(param1);
						parameterMap.put("P_FILE_GRP_NO", param1.getString("P_KEY1")+"-"+param1.getString("P_KEY2")+"-"+param1.getString("P_APPR_CNTC_KEY")); //연계 키값 - 파일그룹번호

						// 2. 계약설계 첨부파일 List select -> 첨부파일 테이블 insert
						parameterMap.put("contKdcd", contKdcd[k]);
						System.err.println("  =contKdcd= " + parameterMap.getString("contKdcd"));
						//표준계약 또는 결의서일 경우
						if("A".equals(parameterMap.getString("contKdcd")) || "D".equals(parameterMap.getString("contKdcd"))){	 
							//parameterMap.put("P_CONT_FSCD", "A1");	//계약서 pdf 가져오기
							parameterMap.put("P_CONT_FSCD", "A");	//계약서 xml 빼고 모든 첨부파일 가져오기로 수정
						//오프라인 또는 비정형일 경우에는 xml을 떨구지 않음
						}else if("B".equals(parameterMap.getString("contKdcd")) || "C".equals(parameterMap.getString("contKdcd"))){
							//parameterMap.put("P_CONT_FSCD", "A");	//첨부한 계약서 가져오기 
						}

						List<FwkDataEntity> fileList = iproSancDao.selectCtFileInfo(parameterMap);
						//첨부파일 삭제 
						iproSancDao.deleteIfApprCntcFile(parameterMap);
						for(int i=0; i<fileList.size(); i++){
							// 3.첨부파일 테이블 insert
							FwkDataEntity dataEntity = fileList.get(i);
							
							parameterMap.put("P_TSK_VKEY2",fileDirPath);// sftp 보낼 파일경로
							parameterMap.put("P_SYS_FILE_NM", fileDirPath2 + dataEntity.get("SV_FILE_NM"));//sftp 보낼 저장될 파일경로 +파일명
							parameterMap.put("P_FILE_DOC_NM",dataEntity.get("SYS_FILE_NM"));//보여주는 파일명
						    parameterMap.put("P_SV_FILE_NM",dataEntity.get("SV_FILE_NM"));//저장파일명
						    parameterMap.put("P_FILE_LCTN",dataEntity.get("FILE_LCTN"));//저장된 파일 경로
							iproSancDao.insertIfApprCntcFile(parameterMap);
						}
						
						if("Y".equals(FwkMessageUtil.getMessage("SFTP.APPR.YN"))){	//sftp Y일때만 타라~
							// 4.sftp 호출
							apprFileSend(parameterMap);
						}
						
						reqSeq += param1.getString("P_KEY1")+"-"+param1.getString("P_KEY2")+"-"+param1.getString("P_APPR_CNTC_KEY")+"|";
					}

					trans.put("P_REQSEQ" , reqSeq);	//전자계약 키
					
				}
			}
			
			trans.put("P_SUB_TYPE" , param1.getString("P_SUB_TYPE"));
			trans.put("P_REGR_ID" , param1.getString("P_REGR_ID"));
			trans.put("docYearmon" , docYearmon );
			trans.put("docNumber" , docNumber);
			trans.put("result"   ,"success");
			
		} catch (FwkSysException e) {
			trans.put("result","fail");
		} 
		
		return trans;
	}
	
	public Map<String,String> ctrtcCreateHtml(final FwkParameterMap parameterMap) throws Exception {
		// 계약정보 조회
		FwkDataEntity contDetail = iproSancDao.selectContDetail(parameterMap);
		
		// 계약업체 조회
		parameterMap.put("P_ORDR_AGNC_YN","N"); 
		List<FwkDataEntity> vendList = iproSancDao.selectVendList(parameterMap);
		
		// 품목조회
		List<FwkDataEntity> itemList =  iproSancDao.selectItemList(parameterMap);
		
		String filePath = FwkMessageUtil.getMessage("EW.SYS.FILE.DIR") + "appr/";
		String fileName = "article_"+parameterMap.get("P_FILE_GRP_NO") + ".html";
		//매핑
		parameterMap.put("P_CONT_SECD", parameterMap.getString("P_CONT_SECD"));
		
		if(parameterMap.getString("P_CHNG_NGR") == "0"){
			parameterMap.put("P_CHNG_SECD", "0");	
		}else{
			parameterMap.put("P_CHNG_SECD", "1");	
		}
		
		parameterMap.put("P_CONT_KDCD", "D");	//결의서
		
		FwkDataEntity formMst = iproSancDao.selectContForm(parameterMap);
		
		String contForm = "";
		String contCntn ="";
		
		if(formMst != null){
			contCntn = formMst.getString("FRM_CNTN");
			if(contCntn != null){
				contCntn = contCntn.replace("#공급가액#", FwkFormatUtil.formatMoney(contDetail.getString("SPLY_PRCE")));
				contCntn = contCntn.replace("#부가세#", FwkFormatUtil.formatMoney(contDetail.getString("STAX")));
				contCntn = contCntn.replace("#합계#", FwkFormatUtil.formatMoney(contDetail.getString("CONT_AMT"))); 
				contCntn = contCntn.replace("#계약방법#", contDetail.getString("CONT_MTCD_NM")); 
				contCntn = contCntn.replace("#계약근거#", contDetail.getString("PRVT_CONT_RSN"));	//수의계약사유 
				contCntn = contCntn.replace("#계약기간#", FwkFormatUtil.formatDate(contDetail.getString("CONT_STDE"),"yyyyMMdd","yyyy.MM.dd") + "~" + FwkFormatUtil.formatDate(contDetail.getString("CONT_ENDE"),"yyyyMMdd","yyyy.MM.dd")); 
				contCntn = contCntn.replace("#납품장소#", contDetail.getString("DLGD_PLC_NM")); 
				contCntn = contCntn.replace("#계약종료일자#", FwkFormatUtil.formatDate(contDetail.getString("CONT_ENDE"),"yyyyMMdd","yyyy년MM월dd일")); 
				contCntn = contCntn.replace("#계약보증금율#", contDetail.getString("CONT_GRNT_RT")); 
				contCntn = contCntn.replace("#비고#", contDetail.getString("ETC_ITEM")); 
				contCntn = contCntn.replace("#계약일자#", FwkFormatUtil.formatDate(contDetail.getString("CONT_DE"),"yyyyMMdd","yyyy년MM월dd일")); 
				contCntn = contCntn.replace("#감독원#", contDetail.getString("MNGR_CHRGR_NM")); 
				contCntn = contCntn.replace("#검사원#", contDetail.getString("CHCK_CHRGR_NM")); 
				contCntn = contCntn.replace("#납품방법#", "일시"); 
				contCntn = contCntn.replace("#계약보증금#","승낙사항 참조" ); //FwkFormatUtil.formatMoney(contDetail.getString("CONT_GRNT_AMT"))
				contCntn = contCntn.replace("#납품일자#", ""); 
				contCntn = contCntn.replace("#검사일자#", ""); 
				
				
				//품목목록
				StringBuffer itemListHtml = new StringBuffer(1000);
				if(itemList != null){
					for(int i=0; i<itemList.size(); i++){
						
						itemListHtml.append("<tr style=\"height: 30px;\">")
						.append("<td colspan=\"3\" style=\"text-align: left; border: solid #000000 0.4pt;\">")
						.append("<p><span style=\"font-size: 10px; margin:auto;  padding-left:5px;\">")
						.append(itemList.get(i).getString("ITEM_NM"))
						.append("</span></p></td>")
						.append("<td colspan=\"4\" style=\"text-align: left; border: solid #000000 0.4pt;\">")
						.append("<p><span style=\"font-size: 10px; margin:auto;  padding-left:5px;\">")
						.append(itemList.get(i).getString("ITEM_DTL"))
						.append("</span></p></td>")
						.append("<td style=\"text-align: center; border: solid #000000 0.4pt;\">")
						.append("<p><span style=\"font-size: 11px; \">")
						.append(itemList.get(i).getString("ITEM_UNCD"))
						.append("</span></p></td>")
						.append("<td  style=\"text-align: center; border: solid #000000 0.4pt;\">")
						.append("<p><span style=\"font-size: 11px;\">")
						.append(FwkFormatUtil.formatMoney(itemList.get(i).getString("ITEM_QTY")))
						.append("</span></p></td>")
						.append("<td colspan=\"2\" style=\"text-align: right; border: solid #000000 0.4pt;\">")
						.append("<p><span style=\"font-size: 11px;  padding-right: 5px;\">")
						.append(FwkFormatUtil.formatMoney(itemList.get(i).getString("ITEM_UPRC")))
						.append("</span></p></td>")
						.append("<td colspan=\"3\" style=\"text-align: right; border: solid #000000 0.4pt;\">")
						.append("<p><span style=\"font-size: 11px; padding-right: 5px;\">")
						.append(FwkFormatUtil.formatMoney(itemList.get(i).getString("ITEM_AMT")))
						.append("</span></p></td>")
						.append("<td colspan=\"3\" style=\"text-align: left; border: solid #000000 0.4pt;\">")
						.append("<p><span style=\"font-size: 9px; margin:auto;  padding-left:5px;\">")
						.append(itemList.get(i).getString("ACNT_NM"))
						.append("</span></p></td>")
						.append("</tr>");
						
					}
				}
				
				contCntn = contCntn.replace("#itemList#", itemListHtml); 
				
				
				StringBuffer vendListHtml = new StringBuffer(1000);
				if(vendList != null){
					for(int i=0; i<vendList.size(); i++){
						vendListHtml.append("<tr>")
									.append("<td style=\"width:46;height:22;border-left:solid #000000 0.4pt;border-right:none;border-top:none;border-bottom:none;padding:1.4pt 1.4pt 1.4pt 1.4pt\">")
									.append("<p><span style=\"font-size: 12px;\">&nbsp;</span></p>")
									.append("</td>")
									.append("<td colspan=\"3\" style=\"width:46;height:22;border-left:none;border-right:none;border-top:none;border-bottom:none;padding:1.4pt 1.4pt 1.4pt 1.4pt\">")
									.append("<p><span style=\"font-size: 12px;\">&nbsp;</span></p>")
									.append("</td>")
									.append("<td colspan=\"2\" style=\"width:79;height:22;border-left:none;border-right:none;border-top:none;border-bottom:none;padding:1.4pt 1.4pt 1.4pt 1.4pt\">")
									.append("<p><span style=\"font-size: 12px;\">&nbsp;</span></p>")
									.append("</td>")
									.append("<td colspan=\"2\" style=\"width:108;height:22;border-left:none;border-right:none;border-top:none;border-bottom:none;padding:1.4pt 1.4pt 1.4pt 1.4pt\">")
									.append("<p><span style=\"font-size: 12px;\">계약상대자</span></p>")
									.append("</td>")
									.append("<td colspan=\"2\" style=\"width:71;height:22;border-left:none;border-right:none;border-top:none;border-bottom:none;padding:1.4pt 1.4pt 1.4pt 1.4pt\">")
									.append("<p><span style=\"font-size: 12px;\">상&nbsp; 호:</span></p>")
									.append("</td>")
									.append("<td colspan=\"4\" style=\"width:154;height:22;border-left:none;border-right:none;border-top:none;border-bottom:none;padding:1.4pt 1.4pt 1.4pt 1.4pt\">")
									.append("<P style=\"margin-left:1.3pt;margin-right:1.3pt;text-align:left;line-height:128%;\"><span style=\"font-size: 12px;\">")
									.append(vendList.get(i).getString("VEND_NM"))
									.append("</span></p>")
									.append("</td>")
									.append("<td style=\"width:42;height:22;border-left:none;border-right:none;border-top:none;border-bottom:none;padding:1.4pt 1.4pt 1.4pt 1.4pt'>")
									.append("<P style=\"margin-left:1.3pt;margin-right:1.3pt;text-align:left;line-height:128%;\"><span style=\"font-size: 12px;\">CMS:</span></p>")
									.append("</td>")
									.append("<td style=\"width:66;height:22;border-left:none;border-right:none;border-top:none;border-bottom:none;padding:1.4pt 1.4pt 1.4pt 1.4pt'>")
									.append("<P style=\"margin-left:1.3pt;margin-right:1.3pt;text-align:left;line-height:128%;\"><span style=\"font-size: 12px;\">N/A</span></p>")
									.append("</td>")
									.append("<td style=\"width:15;height:22;border-left:none;border-right:solid #000000 0.4pt;border-top:none;border-bottom:none;padding:1.4pt 1.4pt 1.4pt 1.4pt\">")
									.append("<P style=\"text-align:left;line-height:170%;\"><span style=\"font-size:8.0pt;font-family:\"굴림\";letter-spacing:1%;line-height:170%\">&nbsp;</span></p>")
									.append("</td>")
									.append("</tr>");
						vendListHtml.append("<tr>")
									.append("<td style=\"width:46;height:22;border-left:solid #000000 0.4pt;border-right:none;border-top:none;border-bottom:none;padding:1.4pt 1.4pt 1.4pt 1.4pt\">")
									.append("<p><span style=\"font-size: 12px;\">&nbsp;</span></p>")
									.append("</td>")
									.append("<td colspan=\"3\" style=\"width:46;height:22;border-left:none;border-right:none;border-top:none;border-bottom:none;padding:1.4pt 1.4pt 1.4pt 1.4pt\">")
									.append("<p><span style=\"font-size: 12px;\">&nbsp;</span></p>")
									.append("</td>")
									.append("<td colspan=\"2\" style=\"width:79;height:22;border-left:none;border-right:none;border-top:none;border-bottom:none;padding:1.4pt 1.4pt 1.4pt 1.4pt\">")
									.append("<p><span style=\"font-size: 12px;\">&nbsp;</span></p>")
									.append("</td>")
									.append("<td colspan=\"2\" style=\"width:108;height:22;border-left:none;border-right:none;border-top:none;border-bottom:none;padding:1.4pt 1.4pt 1.4pt 1.4pt\">")
									.append("<p><span style=\"font-size: 12px;\">&nbsp;</span></p>")
									.append("</td>")
									.append("<td colspan=\"2\" style=\"width:71;height:22;border-left:none;border-right:none;border-top:none;border-bottom:none;padding:1.4pt 1.4pt 1.4pt 1.4pt\">")
									.append("<p><span style=\"font-size: 12px;\">주&nbsp; 소:</span></p>")
									.append("</td>")
									.append("<td colspan=\"6\" style=\"width:262;height:22;border-left:none;border-right:none;border-top:none;border-bottom:none;padding:1.4pt 1.4pt 1.4pt 1.4pt\">")
									.append("<P style=\"margin-left:1.3pt;margin-right:1.3pt;text-align:left;line-height:128%;\"><span style=\"font-size: 12px;\">")
									.append(vendList.get(i).getString("VEND_ADDR") + " " + vendList.get(i).getString("VEND_DTL_ADDR"))
									.append("</span></p>")
									.append("</td>")
									.append("<td style=\"width:15;height:22;border-left:none;border-right:solid #000000 0.4pt;border-top:none;border-bottom:none;padding:1.4pt 1.4pt 1.4pt 1.4pt\">")
									.append("<P style=\"text-align:center;line-height:170%;\"><span style=\"font-size:8.0pt;font-family:\"굴림\";letter-spacing:1%;line-height:170%\">&nbsp;</span></p>")
									.append("</td>")
									.append("</tr>");
						vendListHtml.append("<tr>")
									.append("<td style=\"width:46;height:22;border-left:solid #000000 0.4pt;border-right:none;border-top:none;border-bottom:none;padding:1.4pt 1.4pt 1.4pt 1.4pt\">")
									.append("<p><span style=\"font-size: 12px;\">&nbsp;</span></p>")
									.append("</td>")
									.append("<td colspan=\"3\" style=\"width:46;height:22;border-left:none;border-right:none;border-top:none;border-bottom:none;padding:1.4pt 1.4pt 1.4pt 1.4pt\">")
									.append("<p><span style=\"font-size: 12px;\">&nbsp;</span></p>")
									.append("</td>")
									.append("<td colspan=\"2\" style=\"width:79;height:22;border-left:none;border-right:none;border-top:none;border-bottom:none;padding:1.4pt 1.4pt 1.4pt 1.4pt\">")
									.append("<p><span style=\"font-size: 12px;\">&nbsp;</span></p>")
									.append("</td>")
									.append("<td colspan=\"2\" style=\"width:108;height:22;border-left:none;border-right:none;border-top:none;border-bottom:none;padding:1.4pt 1.4pt 1.4pt 1.4pt\">")
									.append("<p><span style=\"font-size: 12px;\">&nbsp;</span></p>")
									.append("</td>")
									.append("<td colspan=\"2\" style=\"width:71;height:22;border-left:none;border-right:none;border-top:none;border-bottom:none;padding:1.4pt 1.4pt 1.4pt 1.4pt\">")
									.append("<p><span style=\"font-size: 12px;\">거래은행:</span></p>")
									.append("</td>")
									.append("<td colspan=\"6\" style=\"width:262;height:22;border-left:none;border-right:none;border-top:none;border-bottom:none;padding:1.4pt 1.4pt 1.4pt 1.4pt\">")
									.append("<P style=\"margin-left:1.3pt;margin-right:1.3pt;text-align:left;line-height:128%;\"><span style=\"font-size: 12px;\">")
									.append("N/A")
									.append("</span></p>")
									.append("</td>")
									.append("<td style=\"width:15;height:22;border-left:none;border-right:solid #000000 0.4pt;border-top:none;border-bottom:none;padding:1.4pt 1.4pt 1.4pt 1.4pt\">")
									.append("<P style=\"text-align:center;line-height:170%;\"><span style=\"font-size:8.0pt;font-family:\"굴림\";letter-spacing:1%;line-height:170%\">&nbsp;</span></p>")
									.append("</td>")
									.append("</tr>");
						vendListHtml.append("<tr>")
									.append("<td style=\"width:46;height:22;border-left:solid #000000 0.4pt;border-right:none;border-top:none;border-bottom:none;padding:1.4pt 1.4pt 1.4pt 1.4pt\">")
									.append("<p><span style=\"font-size: 12px;\">&nbsp;</span></p>")
									.append("</td>")
									.append("<td colspan=\"3\" style=\"width:46;height:22;border-left:none;border-right:none;border-top:none;border-bottom:none;padding:1.4pt 1.4pt 1.4pt 1.4pt\">")
									.append("<p><span style=\"font-size: 12px;\">&nbsp;</span></p>")
									.append("</td>")
									.append("<td colspan=\"2\" style=\"width:79;height:22;border-left:none;border-right:none;border-top:none;border-bottom:none;padding:1.4pt 1.4pt 1.4pt 1.4pt\">")
									.append("<p><span style=\"font-size: 12px;\">&nbsp;</span></p>")
									.append("</td>")
									.append("<td colspan=\"2\" style=\"width:108;height:22;border-left:none;border-right:none;border-top:none;border-bottom:none;padding:1.4pt 1.4pt 1.4pt 1.4pt\">")
									.append("<p><span style=\"font-size: 12px;\">&nbsp;</span></p>")
									.append("</td>")
									.append("<td colspan=\"2\" style=\"width:71;height:22;border-left:none;border-right:none;border-top:none;border-bottom:none;padding:1.4pt 1.4pt 1.4pt 1.4pt\">")
									.append("<p><span style=\"font-size: 12px;\">계좌번호:</span></p>")
									.append("</td>")
									.append("<td colspan=\"6\" style=\"width:262;height:22;border-left:none;border-right:none;border-top:none;border-bottom:none;padding:1.4pt 1.4pt 1.4pt 1.4pt\">")
									.append("<P style=\"margin-left:1.3pt;margin-right:1.3pt;text-align:left;line-height:128%;\"><span style=\"font-size: 12px;\">")
									.append("N/A")
									.append("</span></p>")
									.append("</td>")
									.append("<td style=\"width:15;height:22;border-left:none;border-right:solid #000000 0.4pt;border-top:none;border-bottom:none;padding:1.4pt 1.4pt 1.4pt 1.4pt\">")
									.append("<P style=\"text-align:center;line-height:170%;\"><span style=\"font-size:8.0pt;font-family:\"굴림\";letter-spacing:1%;line-height:170%\">&nbsp;</span></p>")
									.append("</td>")
									.append("</tr>");
						vendListHtml.append("<tr>")
									.append("<td style=\"width:46;height:22;border-left:solid #000000 0.4pt;border-right:none;border-top:none;border-bottom:none;padding:1.4pt 1.4pt 1.4pt 1.4pt\">")
									.append("<p><span style=\"font-size: 12px;\">&nbsp;</span></p>")
									.append("</td>")
									.append("<td colspan=\"3\" style=\"width:46;height:22;border-left:none;border-right:none;border-top:none;border-bottom:none;padding:1.4pt 1.4pt 1.4pt 1.4pt\">")
									.append("<p><span style=\"font-size: 12px;\">&nbsp;</span></p>")
									.append("</td>")
									.append("<td colspan=\"2\" style=\"width:79;height:22;border-left:none;border-right:none;border-top:none;border-bottom:none;padding:1.4pt 1.4pt 1.4pt 1.4pt\">")
									.append("<p><span style=\"font-size: 12px;\">&nbsp;</span></p>")
									.append("</td>")
									.append("<td colspan=\"2\" style=\"width:108;height:22;border-left:none;border-right:none;border-top:none;border-bottom:none;padding:1.4pt 1.4pt 1.4pt 1.4pt\">")
									.append("<p><span style=\"font-size: 12px;\">&nbsp;</span></p>")
									.append("</td>")
									.append("<td colspan=\"2\" style=\"width:71;height:22;border-left:none;border-right:none;border-top:none;border-bottom:none;padding:1.4pt 1.4pt 1.4pt 1.4pt\">")
									.append("<p><span style=\"font-size: 12px;\">대표자:</span></p>")
									.append("</td>")
									.append("<td colspan=\"6\" style=\"width:262;height:22;border-left:none;border-right:none;border-top:none;border-bottom:none;padding:1.4pt 1.4pt 1.4pt 1.4pt\">")
									.append("<P style=\"margin-left:1.3pt;margin-right:1.3pt;text-align:left;line-height:128%;\"><span style=\"font-size: 12px;\">")
									.append(vendList.get(i).getString("RPRS_NM"))
									.append("</span></p>")
									.append("</td>")
									.append("<td style=\"width:15;height:22;border-left:none;border-right:solid #000000 0.4pt;border-top:none;border-bottom:none;padding:1.4pt 1.4pt 1.4pt 1.4pt\">")
									.append("<P style=\"text-align:center;line-height:170%;\"><span style=\"font-size:8.0pt;font-family:\"굴림\";letter-spacing:1%;line-height:170%\">&nbsp;</span></p>")
									.append("</td>")
									.append("</tr>");
					}
				}
				
				contCntn = contCntn.replace("#vendList#",vendListHtml); 
				
				
			}
		}
		
		contForm = contCntn;
		
		File f = new File(filePath);
		// directory 생성
		if(!f.isDirectory()){
			f.setExecutable(true);
			f.setReadable(true);
			f.setWritable(true);
			f.mkdirs();
		}
		
		// HTML파일 생성
		return createContractHTMLFile(fileName, filePath, contForm);
	}
	
	public static Map createContractHTMLFile(String fileName, String path, String contForm) throws Exception {
		Map<String,String> valueMap = new HashMap<String,String>();

	      /*UUID uuid = UUID.randomUUID();
	      String temp = uuid.toString();
	      String tempFile = temp + ".html";
	      */
	      //html 파일 떨구기 시작
	      File file = new File(path + fileName);
			
	      try {
	         BufferedWriter buffWrite = new BufferedWriter(
	               new OutputStreamWriter(new FileOutputStream(file.getPath()), "euc-kr"));
	         buffWrite.write(contForm, 0, contForm.length());
	         buffWrite.flush();
	         buffWrite.close();

	         valueMap.put("P_FILE_SZ", String.valueOf(file.length()));
	         
	      } catch (Exception e) {
	         throw e;
	      }

	      valueMap.put("P_SYS_FILE_NM", path+fileName); //시스템파일명_저장파일명포함
	      valueMap.put("P_SV_FILE_NM", fileName); //저장파일명
	      valueMap.put("P_FILE_DOC_NM", fileName); //파일문서명
	      valueMap.put("P_FILE_LCTN", path);	//파일 경로
	      valueMap.put("P_TSK_VKEY1", "HTML");
	      valueMap.put("P_ATCH_FSCD", "");	//첨부파일 구분
	      
	      return valueMap;
	   }       
	
	
	public void apprFileSend(final FwkParameterMap parameterMap) throws Exception {
		List<FwkDataEntity> apprFileData =  iproSancDao.selectApprFileData(parameterMap);
		String saveDir = "";
		
		String host = FwkMessageUtil.getMessage("SFTP.APPR.HOST");
		String strPort = FwkMessageUtil.getMessage("SFTP.APPR.PORT");
		String userName = FwkMessageUtil.getMessage("SFTP.APPR.USERNAME");
		String sftpPassword = FwkMessageUtil.getMessage("SFTP.APPR.PASSWORD");
		
		//sftpUtil 초기화
		SftpUtil sftpUtil = new SftpUtil(); 
		int port = Integer.parseInt(strPort);
		sftpUtil.init(host, userName, sftpPassword, port);
		/*for(int i=0; i<apprFileData.size(); i++){ 
			
			FwkDataEntity apprFileEntity = apprFileData.get(i);
			saveDir = apprFileEntity.getString("FILE_LCTN")+"/"+apprFileEntity.getString("SV_FILE_NM");// 파일 경로
		 
			String serverDir = apprFileEntity.getString("TSK_VKEY2");		//sftp 저장경로
			//FwkMessageUtil.getMessage("SFTP.APPR.DIR");
			//파일업로드
			sftpUtil.apprUpload(saveDir, serverDir, ""); 
		} */
		String serverDir = FwkMessageUtil.getMessage("SFTP.APPR.DIR")+FwkMessageUtil.getMessage("SFTP.APPR.VOLUME");
		sftpUtil.apprUploadMulti(saveDir, serverDir, "", apprFileData); 
		// sftp해지하기 
		sftpUtil.disconnection(); 
	}
	
	public void htmlFileSend(final FwkParameterMap parameterMap) throws Exception {
		String saveDir = parameterMap.getString("P_SYS_FILE_NM");	//파일이 저장된 경로+ 파일명
		
		String host = FwkMessageUtil.getMessage("SFTP.APPR.HOST");
		String strPort = FwkMessageUtil.getMessage("SFTP.APPR.PORT");
		String userName = FwkMessageUtil.getMessage("SFTP.APPR.USERNAME");
		String sftpPassword = FwkMessageUtil.getMessage("SFTP.APPR.PASSWORD");
		
		//sftpUtil 초기화
		SftpUtil sftpUtil = new SftpUtil(); 
		int port = Integer.parseInt(strPort);
		sftpUtil.init(host, userName, sftpPassword, port);
		
		String serverDir = FwkMessageUtil.getMessage("SFTP.APPR.HTML.DIR");
		//파일업로드
		sftpUtil.apprUpload(saveDir, serverDir, "articleHtml"); 
		// sftp해지하기 
		sftpUtil.disconnection(); 
	}
	
}
