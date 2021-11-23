package com.eunwoosoft.ipro.vend.service.impl; 

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.eunwoosoft.comm.code.dao.ComCmcdDetailCdDao;
import com.eunwoosoft.comm.file.dao.ComAtmaAtchFileDao;
import com.eunwoosoft.comm.file.service.ComAtmaAtchFileService;
import com.eunwoosoft.frwk.bul.AbstractFwkService;
import com.eunwoosoft.frwk.fol.dto.FwkDataEntity;
import com.eunwoosoft.frwk.fol.dto.FwkTransferObject;
import com.eunwoosoft.frwk.fol.util.FwkDateUtil;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;
import com.eunwoosoft.ipro.vend.dao.IproVendEvalDao;
import com.eunwoosoft.ipro.vend.service.IproVendEvalService;
/**
 * 협력사관리 > 협력사평가
 * <pre>
 *   IproVendEvalService.java
 * 
 * </pre>
 * @date : 2018. 02. 19
 * @version : 1.0
 * @author : 은우소프트 홍찬일
 */
@Service("iproVendEvalService")
public class IproVendEvalServiceImpl extends AbstractFwkService implements IproVendEvalService {
	
	@Resource(name="iproVendEvalDao")
	private IproVendEvalDao iproVendEvalDao;
	
	@Resource(name="comCmcdDetailCdDao")
	private ComCmcdDetailCdDao comCmcdDetailCdDao;	
	
	@Resource(name="comAtmaAtchFileDao")
	private ComAtmaAtchFileDao comAtmaAtchFileDao; 
	
	@Resource(name="comAtmaAtchFileService")
	private ComAtmaAtchFileService comAtmaAtchFileService;  
	
	/**
	 * <pre>
	 * 1. 개요 : 평가기준 목록조회_페이징
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : vendEvalStndListInqireWithPgng
	 * @date : 2018. 02. 19.
	 * @author : 은우소프트 홍찬일
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2018. 02. 19.		은우소프트 홍찬일  		    최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 * @throws Exception 
	 */
	public FwkTransferObject vendEvalStndListInqireWithPgng(final FwkParameterMap parameterMap)throws Exception{

		FwkTransferObject trans = new FwkTransferObject();
		
		parameterMap.put("orgId", "81");
		
		trans.put(IproVendEvalService.VEND_EVAL_STND_LIST, iproVendEvalDao.vendEvalStndListInqireWithPgng(parameterMap));
		trans.put(IproVendEvalService.VEND_EVAL_STND_LIST_TOTCNT, iproVendEvalDao.vendEvalStndListTotcnt(parameterMap));
		return trans;
	}
	
	/**
	 * 
	 * <pre>
	 * 1. 개요 : 평가기준 등록
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : vendEvalStndRegist
	 * @date : 2018. 02. 19.
	 * @author : 은우소프트 홍찬일
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2018. 02. 19.		은우소프트 홍찬일				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 * @throws Exception 
	 */

	@Override
	public FwkTransferObject vendEvalStndRegist(final FwkParameterMap parameterMap) throws Exception {
		
		FwkTransferObject trans = new FwkTransferObject();
		
		if(parameterMap.get("loginResult") != null){
			FwkDataEntity session = (FwkDataEntity) parameterMap.get("loginResult");
		}
		
		/*평가분야 정보*/
		String ev_name1 = (String) parameterMap.get("ev_name1"); 		//평가분야명
		String use_yn = (String) parameterMap.get("use_yn"); 			//사용유무
		String ev_gubun1 = (String) parameterMap.get("ev_gubun1"); 		//평가구분
		String remark1 = (String) parameterMap.get("remark1"); 			//설명
		
		/*평가항목 정보*/
		Object ev_name2_obj = parameterMap.get("ev_name2"); 				//평가항목명
		Object dist_score2_obj = parameterMap.get("dist_score2"); 			//평가항목배점
		Object remark2_obj = parameterMap.get("remark2"); 					//평가항목설명
		
		
		/*평가분야 INSERT*/
		parameterMap.put("ev_name1", ev_name1);
		parameterMap.put("use_yn", use_yn);
		parameterMap.put("ev_gubun1", ev_gubun1);
		parameterMap.put("remark", remark1);
		parameterMap.put("orgId", "81");
		parameterMap.put("ev_gubun2", "1");
		parameterMap.put("gbn", "EG"); //평가분야 : EG
		
		iproVendEvalDao.insertT_Ev_Code1(parameterMap);
		String ev_code1 = (String) parameterMap.get("ev_code1");
		
		if(ev_name2_obj instanceof String){
			//평가항목정보가 1개일 경우.
			
			String ev_name2 = (String) ev_name2_obj;		//평가항목명
			String dist_score2 = (String) dist_score2_obj;	//평가항목배점
			String remark2 = (String) remark2_obj;			//평가항목설명
			
			parameterMap.put("ev_code1", ev_code1);			// 평가분야번호: 84LINE insert시 생성.
			parameterMap.put("ev_name2", ev_name2);
			parameterMap.put("dist_score", dist_score2);
			parameterMap.put("remark",remark2);
			parameterMap.put("gbn", "EI"); //평가항목 : EI
			
			iproVendEvalDao.insertT_Ev_Code2(parameterMap);
			
			
		}else{
			//평가항목정보가 1개이상일 경우.
			
			ArrayList<String> ev_name2_list = (ArrayList<String>) ev_name2_obj;			//평가항목명
			ArrayList<String> dist_score2_list = (ArrayList<String>) dist_score2_obj;	//평가항목배점
			ArrayList<String> remark2_list = (ArrayList<String>) remark2_obj;			//평가항목설명
			
			for (int i = 0; i < ev_name2_list.size(); i++) {
				
				parameterMap.put("ev_code1", ev_code1);
				parameterMap.put("ev_code2", "");
				parameterMap.put("ev_name2", ev_name2_list.get(i).toString());
				parameterMap.put("dist_score", dist_score2_list.get(i).toString());
				parameterMap.put("remark",remark2_list.get(i).toString());
				parameterMap.put("gbn", "EI"); //평가항목 : EI
				
				iproVendEvalDao.insertT_Ev_Code2(parameterMap);
			}
		}
		return trans;		
	}
	/**
	 * <pre>
	 * 1. 개요 : 평가기준 상세조회
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : selectVendEvalStndDetail
	 * @date : 2018. 02. 19.
	 * @author : 은우소프트 홍찬일
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2018. 02. 19.		은우소프트 홍찬일  		    최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 * @throws Exception 
	 */
	public FwkTransferObject selectVendEvalStndDetail(final FwkParameterMap parameterMap)throws Exception{
		FwkTransferObject trans = new FwkTransferObject();
		
		parameterMap.put("orgId", "81");
		
		trans.put(IproVendEvalService.VEND_EVAL_STND_DETAIL, iproVendEvalDao.selectVendEvalStndDetail(parameterMap));				//평가기준 상세조회
		trans.put(IproVendEvalService.VEND_EVAL_STND_DETAIL_LIST, iproVendEvalDao.selectVendEvalStndDetailList(parameterMap));	//평가항목 상세리스트조회
		
		return trans;		
	}
	/**
	 * <pre>
	 * 1. 개요 : 평가기준 상세조회
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : selectVendEvalStndDetail
	 * @date : 2018. 02. 19.
	 * @author : 은우소프트 홍찬일
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2018. 02. 19.		은우소프트 홍찬일  		    최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 * @throws Exception 
	 */
	public FwkTransferObject selectVendEvalStndModify(final FwkParameterMap parameterMap)throws Exception{
		FwkTransferObject trans = new FwkTransferObject();
		
		/*평가분야 정보*/
		String ev_code1 = (String) parameterMap.get("ev_code1"); 		//평가분야코드
		String ev_name1 = (String) parameterMap.get("ev_name1"); 		//평가분야명
		String use_yn = (String) parameterMap.get("use_yn"); 			//사용유무
		String ev_gubun1 = (String) parameterMap.get("ev_gubun1"); 		//평가구분
		String remark1 = (String) parameterMap.get("remark1"); 			//설명
		
		/*평가항목 정보*/
		Object ev_code2_obj = parameterMap.get("ev_code2"); 				//평가항목코드
		Object ev_name2_obj = parameterMap.get("ev_name2"); 			//평가항목명
		Object dist_score2_obj = parameterMap.get("dist_score2"); 		//평가항목배점
		Object remark2_obj = parameterMap.get("remark2"); 				//평가항목설명
		
		/*평가분야 modify*/
		parameterMap.put("ev_code1", ev_code1);
		parameterMap.put("ev_name1", ev_name1);
		parameterMap.put("use_yn", use_yn);
		parameterMap.put("ev_gubun1", ev_gubun1);
		parameterMap.put("remark", remark1);
		parameterMap.put("orgId", "81");
		parameterMap.put("ev_gubun2", "1");
		
		iproVendEvalDao.updateT_ev_code1(parameterMap);
		
		//평가항목 삭제
		iproVendEvalDao.deleteT_ev_code2(parameterMap); 
		
		if(ev_name2_obj instanceof String){
			//평가항목정보가 1개일 경우.
			
			String ev_code2 = (String) ev_code2_obj;		//평가항목코드
			String ev_name2 = (String) ev_name2_obj;		//평가항목명
			String dist_score2 = (String) dist_score2_obj;	//평가항목배점
			String remark2 = (String) remark2_obj;			//평가항목설명
			
			parameterMap.put("ev_code1", ev_code1);			
			parameterMap.put("ev_code2", ev_code2);			
			parameterMap.put("ev_name2", ev_name2);
			parameterMap.put("dist_score", dist_score2);
			parameterMap.put("remark",remark2);
			parameterMap.put("gbn", "EI"); //평가항목 : EI
			
			iproVendEvalDao.insertT_Ev_Code2(parameterMap);
			
			
		}else{
			//평가항목정보가 1개이상일 경우.
			
			ArrayList<String> ev_code2_list = (ArrayList<String>) ev_code2_obj;			//평가항목코드
			ArrayList<String> ev_name2_list = (ArrayList<String>) ev_name2_obj;			//평가항목명
			ArrayList<String> dist_score2_list = (ArrayList<String>) dist_score2_obj;	//평가항목배점
			ArrayList<String> remark2_list = (ArrayList<String>) remark2_obj;			//평가항목설명
			
			for (int i = 0; i < ev_name2_list.size(); i++) {
				
				parameterMap.put("ev_code1", ev_code1);
				parameterMap.put("ev_name2", ev_name2_list.get(i).toString());
				parameterMap.put("ev_code2", ev_code2_list.get(i).toString());
				parameterMap.put("dist_score", dist_score2_list.get(i).toString());
				parameterMap.put("remark",remark2_list.get(i).toString());
				parameterMap.put("gbn", "EI"); //평가항목 : EI
				
				iproVendEvalDao.insertT_Ev_Code2(parameterMap);
			}
		}
		return trans;	
	}
	/**
	 * <pre>
	 * 1. 개요 : 평기기준 및 항목 삭제
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : vendEvalStndDelete
	 * @date : 2018. 02. 19.
	 * @author : 은우소프트 홍찬일
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2018. 02. 19.		은우소프트 홍찬일  		    최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 * @throws Exception 
	 */
	public FwkTransferObject vendEvalStndDelete(final FwkParameterMap parameterMap)throws Exception{
		FwkTransferObject trans = new FwkTransferObject();
		String ev_code1 = (String) parameterMap.get("ev_code1"); 		//평가분야코드
		
		parameterMap.put("ev_code1", ev_code1);
		parameterMap.put("orgId", "81");
		iproVendEvalDao.deleteT_ev_code1(parameterMap); //평가분야삭제.
		iproVendEvalDao.deleteT_ev_code2(parameterMap); //평가항목삭제.
		
		return trans;	
	}
	/**
	 * <pre>
	 * 1. 개요 : 세부평가기준 목록조회_페이징
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : vendDtlEvalStndListInqireWithPgng
	 * @date : 2018. 02. 19.
	 * @author : 은우소프트 홍찬일
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2018. 02. 19.		은우소프트 홍찬일  		    최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 * @throws Exception 
	 */
	public FwkTransferObject vendDtlEvalStndListInqireWithPgng(final FwkParameterMap parameterMap)throws Exception{

		FwkTransferObject trans = new FwkTransferObject();
		
		parameterMap.put("orgId", "81");
		
		trans.put(IproVendEvalService.VEND_DTL_EVAL_STND_LIST, iproVendEvalDao.vendDtlEvalStndListInqireWithPgng(parameterMap));
		trans.put(IproVendEvalService.VEND_DTL_EVAL_STND_LIST_TOTCNT, iproVendEvalDao.vendDtlEvalStndListTotcnt(parameterMap));
		return trans;
	}
	/**
	 * <pre>
	 * 1. 개요 : 세부평가기준 및 평가항목 상세조회
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : selectVendDtlEvalStndDetail
	 * @date : 2018. 02. 19.
	 * @author : 은우소프트 홍찬일
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2018. 02. 19.		은우소프트 홍찬일  		    최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 * @throws Exception 
	 */
	public FwkTransferObject selectVendDtlEvalStndDetail(final FwkParameterMap parameterMap)throws Exception{
		FwkTransferObject trans = new FwkTransferObject();
		parameterMap.put("orgId", "81");
		
		trans.put(IproVendEvalService.VEND_DTL_EVAL_STND_DETAIL, iproVendEvalDao.selectVendDtlEvalStndDetail(parameterMap));				//평가기준 상세조회
		trans.put(IproVendEvalService.VEND_DTL_EVAL_STND_DETAIL_LIST, iproVendEvalDao.selectVendDtlEvalStndDetailList(parameterMap));	//평가항목 상세리스트조회
		return trans;
	}
	/**
	 * <pre>
	 * 1. 개요 : 세부평가기준 등록 및 수정
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : vendDtlEvalStndModify
	 * @date : 2018. 02. 19.
	 * @author : 은우소프트 홍찬일
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2018. 02. 19.		은우소프트 홍찬일  		    최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 * @throws Exception 
	 */
	public FwkTransferObject vendDtlEvalStndModify(final FwkParameterMap parameterMap)throws Exception{
		FwkTransferObject trans = new FwkTransferObject();
		
		/*세부평가기준 정보*/
		String ev_code1 = (String) parameterMap.get("ev_code1"); 	
		String ev_code2 = (String) parameterMap.get("ev_code2");
		
		parameterMap.put("ev_code1", ev_code1);
		parameterMap.put("ev_code2", ev_code2);
		parameterMap.put("orgId", "81");
		iproVendEvalDao.deleteT_Ev_Code3(parameterMap);
		
		/*세부평가기준 정보*/
		Object ev_code3_obj = parameterMap.get("ev_code3"); 		//세부평가코드
		Object ev_name3_obj = parameterMap.get("ev_name3"); 		//세부평가기준명
		Object dist_score3_obj = parameterMap.get("dist_score3"); 	//세부평가기준배점
		Object remark_obj = parameterMap.get("remark"); 			//세부평가기준비고
		
		
		if(ev_name3_obj instanceof String){
			parameterMap.put("ev_code1", ev_code1);
			parameterMap.put("ev_code2", ev_code2);
			parameterMap.put("ev_code3", (String)ev_code3_obj);
			parameterMap.put("ev_name3", (String)ev_name3_obj);
			parameterMap.put("dist_score", (String)dist_score3_obj);
			parameterMap.put("remark",(String)remark_obj);
			
			iproVendEvalDao.insertT_Ev_Code3(parameterMap);
		}else {
			
			ArrayList<String> ev_code3_list = (ArrayList<String>) ev_code3_obj;			//세부평가코드     
			ArrayList<String> ev_name3_list = (ArrayList<String>) ev_name3_obj;	//세부평가기준명    
			ArrayList<String> dist_score3_list = (ArrayList<String>) dist_score3_obj;  //세부평가기준배점   
			ArrayList<String> remark_list = (ArrayList<String>) remark_obj;	           //세부평가기준비고   
			for (int i = 0; i < ev_name3_list.size(); i++) {
				
				parameterMap.put("ev_code1", ev_code1);
				parameterMap.put("ev_code2", ev_code2);
				parameterMap.put("ev_code3", ev_code3_list.get(i).toString());
				parameterMap.put("ev_name3", ev_name3_list.get(i).toString());
				parameterMap.put("dist_score", dist_score3_list.get(i).toString());
				parameterMap.put("remark",remark_list.get(i).toString());
				
				iproVendEvalDao.insertT_Ev_Code3(parameterMap);
			}
		}
		
		
		return trans;
	}
	/**
	 * <pre>
	 * 1. 개요 : 세부평가기준 삭제
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : vendDtlEvalStndDelete
	 * @date : 2018. 02. 19.
	 * @author : 은우소프트 홍찬일
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2018. 02. 19.		은우소프트 홍찬일  		    최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 * @throws Exception 
	 */
	public FwkTransferObject vendDtlEvalStndDelete(final FwkParameterMap parameterMap)throws Exception{
		FwkTransferObject trans = new FwkTransferObject();
		/*세부평가기준 정보*/
		String ev_code1 = (String) parameterMap.get("ev_code1"); 	
		String ev_code2 = (String) parameterMap.get("ev_code2");
		
		parameterMap.put("ev_code1", ev_code1);
		parameterMap.put("ev_code2", ev_code2);
		parameterMap.put("orgId", "81");
		iproVendEvalDao.deleteT_Ev_Code3(parameterMap);
		return trans;
	}
	/**
	 * <pre>
	 * 1. 개요 :  유관부서평가기준 목록조회_페이징
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : vendEvalDeptStndListInqireWithPgng
	 * @date : 2018. 02. 19.
	 * @author : 은우소프트 홍찬일
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2018. 02. 19.		은우소프트 홍찬일  		    최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 * @throws Exception 
	 */
	public FwkTransferObject vendEvalDeptStndListInqireWithPgng(final FwkParameterMap parameterMap)throws Exception{

		FwkTransferObject trans = new FwkTransferObject();
		
		parameterMap.put("orgId", "81");
		
		trans.put(IproVendEvalService.VEND_EVAL_DEPT_STND_LIST, iproVendEvalDao.vendEvalDeptStndListInqireWithPgng(parameterMap));
		trans.put(IproVendEvalService.VEND_EVAL_DEPT_STND_LIST_TOTCNT, iproVendEvalDao.vendEvalDeptStndListTotcnt(parameterMap));
		return trans;
	}
	/**
	 * <pre>
	 * 1. 개요 : 유관부서평가기준 등록
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : vendEvalDeptStndRegist
	 * @date : 2018. 02. 19.
	 * @author : 은우소프트 홍찬일
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2018. 02. 19.		은우소프트 홍찬일  		    최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 * @throws Exception 
	 */
	public FwkTransferObject vendEvalDeptStndRegist(final FwkParameterMap parameterMap)throws Exception{
		FwkTransferObject trans = new FwkTransferObject();
		
		/*유관부서평가기준정보*/
		String ev_name1 = (String) parameterMap.get("ev_name1"); 		//평가분야명
		String dist_score = (String) parameterMap.get("dist_score"); 	//배점
		String ev_gubun1 = (String) parameterMap.get("ev_gubun1"); 		//평가구분
		String dept_sg_code = (String) parameterMap.get("dept_sg_code"); 			//sgCode
		String use_yn = (String) parameterMap.get("use_yn"); 			//사용유무
		String remark1 = (String) parameterMap.get("remark1"); 			//설명
		
		/*평가항목 정보*/
		Object ev_name2_obj = parameterMap.get("ev_name2"); 				//평가항목명
		Object dist_score2_obj = parameterMap.get("dist_score2"); 			//평가항목배점
		Object remark2_obj = parameterMap.get("remark2"); 					//평가항목설명
		
		
		/*평가분야 INSERT*/
		parameterMap.put("ev_name1", ev_name1);
		parameterMap.put("dist_score", dist_score);
		parameterMap.put("ev_gubun1", ev_gubun1);
		parameterMap.put("dept_sg_code", dept_sg_code);
		parameterMap.put("use_yn", use_yn);
		parameterMap.put("remark", remark1);
		parameterMap.put("orgId", "81");
		parameterMap.put("ev_gubun2", "2");
		parameterMap.put("gbn", "EGP"); //평가분야 : EGP
		
		iproVendEvalDao.insertT_Ev_Code1(parameterMap);
		String ev_code1 = (String) parameterMap.get("ev_code1");
		
		if(ev_name2_obj instanceof String){
			//평가항목정보가 1개일 경우.
			
			String ev_name2 = (String) ev_name2_obj;		//평가항목명
			String dist_score2 = (String) dist_score2_obj;	//평가항목배점
			String remark2 = (String) remark2_obj;			//평가항목설명
			
			parameterMap.put("ev_code1", ev_code1);			// 평가분야번호: 506LINE insert시 생성.
			parameterMap.put("ev_name2", ev_name2);
			parameterMap.put("dist_score", dist_score2);
			parameterMap.put("remark",remark2);
			parameterMap.put("gbn", "EI"); //평가항목 : EI
			
			iproVendEvalDao.insertT_Ev_Code2(parameterMap);
			
			
		}else{
			//평가항목정보가 1개이상일 경우.
			
			ArrayList<String> ev_name2_list = (ArrayList<String>) ev_name2_obj;			//평가항목명
			ArrayList<String> dist_score2_list = (ArrayList<String>) dist_score2_obj;	//평가항목배점
			ArrayList<String> remark2_list = (ArrayList<String>) remark2_obj;			//평가항목설명
			
			for (int i = 0; i < ev_name2_list.size(); i++) {
				
				parameterMap.put("ev_code1", ev_code1);
				parameterMap.put("ev_code2", "");
				parameterMap.put("ev_name2", ev_name2_list.get(i).toString());
				parameterMap.put("dist_score", dist_score2_list.get(i).toString());
				parameterMap.put("remark",remark2_list.get(i).toString());
				parameterMap.put("gbn", "EI"); //평가항목 : EI
				
				iproVendEvalDao.insertT_Ev_Code2(parameterMap);
			}
		}
		return trans;		
	}
	/**
	 * <pre>
	 * 1. 개요 : 유관부서평가기준 상세조회
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : selectVendEvalDeptStndDetail
	 * @date : 2018. 02. 19.
	 * @author : 은우소프트 홍찬일
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2018. 02. 19.		은우소프트 홍찬일  		    최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 * @throws Exception 
	 */
	public FwkTransferObject selectVendEvalDeptStndDetail(final FwkParameterMap parameterMap)throws Exception{
		FwkTransferObject trans = new FwkTransferObject();
		
		parameterMap.put("orgId", "81");
		
		trans.put(IproVendEvalService.VEND_EVAL_DEPT_STND_DETAIL, iproVendEvalDao.selectVendEvalDeptStndDetail(parameterMap));				//유관부서평가기준 상세조회
		trans.put(IproVendEvalService.VEND_EVAL_DEPT_STND_DETAIL_LIST, iproVendEvalDao.selectVendEvalDeptStndDetailList(parameterMap));		//유관부서평가항목 상세리스트조회
		
		return trans;		
	}
	/**
	 * <pre>
	 * 1. 개요 : 유관부서평가기준 수정
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : vendEvalDeptStndModify
	 * @date : 2018. 02. 19.
	 * @author : 은우소프트 홍찬일
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2018. 02. 19.		은우소프트 홍찬일  		    최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 * @throws Exception 
	 */
	public FwkTransferObject vendEvalDeptStndModify(final FwkParameterMap parameterMap)throws Exception{
		FwkTransferObject trans = new FwkTransferObject();
		
		/*유관부서평가기준정보*/
		String ev_code1 = (String) parameterMap.get("ev_code1"); 		//평가분야코드
		String ev_name1 = (String) parameterMap.get("ev_name1"); 		//평가분야명
		String dist_score = (String) parameterMap.get("dist_score"); 	//배점
		String ev_gubun1 = (String) parameterMap.get("ev_gubun1"); 		//평가구분
		String dept_sg_code = (String) parameterMap.get("dept_sg_code"); 			//sgCode
		String use_yn = (String) parameterMap.get("use_yn"); 			//사용유무
		String remark1 = (String) parameterMap.get("remark1"); 			//설명
		
		/*평가항목 정보*/
		Object ev_name2_obj = parameterMap.get("ev_name2"); 				//평가항목명
		Object dist_score2_obj = parameterMap.get("dist_score2"); 			//평가항목배점
		Object remark2_obj = parameterMap.get("remark2"); 					//평가항목설명
		
		
		/*평가분야 modify*/
		parameterMap.put("ev_code1", ev_code1);
		parameterMap.put("ev_name1", ev_name1);
		parameterMap.put("dist_score", dist_score);
		parameterMap.put("ev_gubun1", ev_gubun1);
		parameterMap.put("dept_sg_code", dept_sg_code);
		parameterMap.put("use_yn", use_yn);
		parameterMap.put("remark", remark1);
		parameterMap.put("orgId", "81");
		parameterMap.put("ev_gubun2", "2");
		parameterMap.put("gbn", "EGP"); //평가분야 : EGP
		
		iproVendEvalDao.updateT_ev_code1(parameterMap);
		
		//평가항목 삭제
		iproVendEvalDao.deleteT_ev_code2(parameterMap); 
		
		if(ev_name2_obj instanceof String){
			//평가항목정보가 1개일 경우.
			
			String ev_name2 = (String) ev_name2_obj;		//평가항목명
			String dist_score2 = (String) dist_score2_obj;	//평가항목배점
			String remark2 = (String) remark2_obj;			//평가항목설명
			
			parameterMap.put("ev_code1", ev_code1);			// 평가분야번호: 506LINE insert시 생성.
			parameterMap.put("ev_name2", ev_name2);
			parameterMap.put("dist_score", dist_score2);
			parameterMap.put("remark",remark2);
			parameterMap.put("gbn", "EI"); //평가항목 : EI
			
			iproVendEvalDao.insertT_Ev_Code2(parameterMap);
			
			
		}else{
			//평가항목정보가 1개이상일 경우.
			
			ArrayList<String> ev_name2_list = (ArrayList<String>) ev_name2_obj;			//평가항목명
			ArrayList<String> dist_score2_list = (ArrayList<String>) dist_score2_obj;	//평가항목배점
			ArrayList<String> remark2_list = (ArrayList<String>) remark2_obj;			//평가항목설명
			
			for (int i = 0; i < ev_name2_list.size(); i++) {
				
				parameterMap.put("ev_code1", ev_code1);
				parameterMap.put("ev_code2", "");
				parameterMap.put("ev_name2", ev_name2_list.get(i).toString());
				parameterMap.put("dist_score", dist_score2_list.get(i).toString());
				parameterMap.put("remark",remark2_list.get(i).toString());
				parameterMap.put("gbn", "EI"); //평가항목 : EI
				
				iproVendEvalDao.insertT_Ev_Code2(parameterMap);
			}
		}
		return trans;		
	}
	/**
	 * <pre>
	 * 1. 개요 : 유관부서평가기준 및 항목 삭제
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : vendEvalDeptStndDelete
	 * @date : 2018. 02. 19.
	 * @author : 은우소프트 홍찬일
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2018. 02. 19.		은우소프트 홍찬일  		    최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 * @throws Exception 
	 */
	public FwkTransferObject vendEvalDeptStndDelete(final FwkParameterMap parameterMap)throws Exception{
		FwkTransferObject trans = new FwkTransferObject();
		String ev_code1 = (String) parameterMap.get("ev_code1"); 		//평가분야코드
		
		parameterMap.put("ev_code1", ev_code1);
		parameterMap.put("orgId", "81");
		iproVendEvalDao.deleteT_ev_code1(parameterMap); //평가분야삭제.
		iproVendEvalDao.deleteT_ev_code2(parameterMap); //평가항목삭제.
		
		return trans;	
	}
	
	/**
	 * <pre>
	 * 1. 개요 : 평가종합관리 목록조회_페이징
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : vendEvalListInqireWithPgng
	 * @date : 2018. 02. 19.
	 * @author : 은우소프트 홍찬일
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2018. 02. 19.		은우소프트 홍찬일  		    최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 * @throws Exception 
	 */
	public FwkTransferObject vendEvalListInqireWithPgng(final FwkParameterMap parameterMap)throws Exception{

		FwkTransferObject trans = new FwkTransferObject();
		
		parameterMap.put("orgId", "81");
		
		trans.put(IproVendEvalService.VEND_EVAL_LIST, iproVendEvalDao.vendEvalListInqireWithPgng(parameterMap));
		trans.put(IproVendEvalService.VEND_EVAL_LIST_TOTCNT, iproVendEvalDao.vendEvalListTotcnt(parameterMap));
		return trans;
	}
	/**
	 * <pre>
	 * 1. 개요 : 평가종합관리 분야 조회
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : selectTEvCode1List
	 * @date : 2018. 02. 19.
	 * @author : 은우소프트 홍찬일
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2018. 02. 19.		은우소프트 홍찬일  		    최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 * @throws Exception 
	 */
	public FwkTransferObject selectTEvCode1List(final FwkParameterMap parameterMap)throws Exception{
		FwkTransferObject trans = new FwkTransferObject();
		
		parameterMap.put("orgId", "81");
		parameterMap.put("P_CD_ID", "N00001");
		parameterMap.put("ev_gubun2", "1");
		
		trans.put(IproVendEvalService.VEND_EVAL_CODE1_LIST, iproVendEvalDao.selectT_Ev_Code1_List(parameterMap));
		trans.put(IproVendEvalService.VEND_EVAL_SG_CODE_LIST, comCmcdDetailCdDao.selectCdValueListByCdId(parameterMap));
		
		return trans;
	}
	/**
	 * <pre>
	 * 1. 개요 : 평가종합관리 등록
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : vendEvalRegist
	 * @date : 2018. 02. 19.
	 * @author : 은우소프트 홍찬일
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2018. 02. 19.		은우소프트 홍찬일  		    최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 * @throws Exception 
	 */
	public FwkTransferObject vendEvalRegist(final FwkParameterMap parameterMap)throws Exception{
		FwkTransferObject trans = new FwkTransferObject();
		
		/*기본정보*/
		String dyyyy = (String) parameterMap.get("dyyyy"); 					//년도
		String ev_name = (String) parameterMap.get("ev_name"); 				//평가명
		String ev_type = (String) parameterMap.get("ev_type"); 				//평가유형
		String exec_date_f = (String) parameterMap.get("exec_date_f"); 		//평가수행기간 시작
		String exec_date_t = (String) parameterMap.get("exec_date_t"); 		//평가수행기간 끝
		String work_date_f = (String) parameterMap.get("work_date_f"); 		//평가실적기간 시작
		String work_date_t = (String) parameterMap.get("work_date_t"); 		//평가실적기간 끝
		String remark = (String) parameterMap.get("remark"); 				//비고
		
		/*평가분야*/
		Object ev_code1_obj =  parameterMap.get("ev_code1"); 			//평가분야코드
		Object sg_name_obj  =  parameterMap.get("sg_name"); 			//SG명
		Object sg_code_obj  =  parameterMap.get("sg_code"); 			//SG코드
		Object dist_score_obj  =  parameterMap.get("dist_score"); 			//배점
	
		parameterMap.put("dyyyy",dyyyy);
		parameterMap.put("ev_name",ev_name);
		parameterMap.put("ev_type",ev_type);
		parameterMap.put("exec_date_f",exec_date_f.replaceAll("-", ""));
		parameterMap.put("exec_date_t",exec_date_t.replaceAll("-", ""));
		parameterMap.put("work_date_f",work_date_f.replaceAll("-", ""));
		parameterMap.put("work_date_t",work_date_t.replaceAll("-", ""));
		parameterMap.put("remark",remark);
		parameterMap.put("orgId", "81");
		parameterMap.put("ev_state", "A");
		
		
		iproVendEvalDao.insertT_Ev_Master(parameterMap); //T_EV_MASTER INSERT
		
		if(ev_code1_obj instanceof String){
			
			String ev_code1 = (String) ev_code1_obj;			//평가항목코드
			String sg_name  = (String) sg_name_obj;				//평가항목SG명
			String sg_code  = (String) sg_code_obj;				//평가항목SG코드
			String dist_score  = (String) dist_score_obj;		//평가항목배점
			
			parameterMap.put("ev_code1", ev_code1);
			parameterMap.put("sg_name", sg_name);
			parameterMap.put("sg_code", sg_code);
			parameterMap.put("dist_score", dist_score);
			
			iproVendEvalDao.insertT_Ev_Yyyy_Code1(parameterMap);
			iproVendEvalDao.insertT_Ev_Yyyy_Code2(parameterMap);
			iproVendEvalDao.insertT_Ev_Yyyy_Code3(parameterMap);
			
		}else{
			ArrayList<String> evCode1_list = (ArrayList<String>) ev_code1_obj;			//평가항목코드
			ArrayList<String> sgName_list  = (ArrayList<String>) sg_name_obj;			//평가항목SG명
			ArrayList<String> sgCode_list  = (ArrayList<String>) sg_code_obj;			//평가항목SG코드
			ArrayList<String> dist_score_list  = (ArrayList<String>) dist_score_obj;	//평가항목배점
			

			for (int i = 0; i < evCode1_list.size(); i++) {
				
				parameterMap.put("ev_code1", evCode1_list.get(i));
				parameterMap.put("sg_name", sgName_list.get(i).toString());
				parameterMap.put("sg_code", sgCode_list.get(i).toString());
				parameterMap.put("dist_score", dist_score_list.get(i).toString());
				parameterMap.put("ev_gubun2", "1"); // 일반평가만 저장 ("1" : 일반평가, "2" : 유관부서평가)
				
				iproVendEvalDao.insertT_Ev_Yyyy_Code1(parameterMap);
				iproVendEvalDao.insertT_Ev_Yyyy_Code2(parameterMap);
				iproVendEvalDao.insertT_Ev_Yyyy_Code3(parameterMap);
			}
		}
		
		System.err.println("================================================================================");
		parameterMap.put("ev_gubun2", "2"); // 유관부서평가만 저장 ("1" : 일반평가, "2" : 유관부서평가)
		iproVendEvalDao.insertT_Ev_Yyyy_Code1(parameterMap);
		iproVendEvalDao.insertT_Ev_Yyyy_Code2(parameterMap);
		iproVendEvalDao.insertT_Ev_Yyyy_Code3(parameterMap);
		
		// 평가업체 저장
		parameterMap.put("min_amt", "100"); //기존 신분당선은 공통코드에서가져옴.

		
		iproVendEvalDao.insertT_Ev_Vendor_Temp(parameterMap);
		
		
		return trans;
	}
	/**
	 * <pre>
	 * 1. 개요 : 평가종합관리 상세조회
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : selectVendEvalView
	 * @date : 2018. 02. 19.
	 * @author : 은우소프트 홍찬일
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2018. 02. 19.		은우소프트 홍찬일  		    최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 * @throws Exception 
	 */
	public FwkTransferObject selectVendEvalView(final FwkParameterMap parameterMap)throws Exception{
		FwkTransferObject trans = new FwkTransferObject();
		
		parameterMap.put("orgId", "81");
				
		// 평가 마스터 조회
		trans.put(IproVendEvalService.VEND_EVAL_MASTER_DETAIL, iproVendEvalDao.selectT_Ev_Master(parameterMap));
		
		parameterMap.put("ev_gubun2", "1");
		// 년도별 평가분야 목록 조회
		
		List<FwkDataEntity> evYyyyCode1SgList =iproVendEvalDao.selectSgT_Ev_Yyyy_Code1(parameterMap);
		trans.put(IproVendEvalService.VEND_EVAL_CODE1_LIST, evYyyyCode1SgList );
		
		if(evYyyyCode1SgList.size() > 0){
			trans.put(IproVendEvalService.VEND_EVAL_SG_CODE1_LIST, iproVendEvalDao.selectT_Ev_Yyyy_Code1List(parameterMap));
		}
				
		return trans;
	}
	/**
	 * <pre>
	 * 1. 개요 : 평가종합관리 수정
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : vendEvalModify
	 * @date : 2018. 02. 19.
	 * @author : 은우소프트 홍찬일
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2018. 02. 19.		은우소프트 홍찬일  		    최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 * @throws Exception 
	 */
	public FwkTransferObject vendEvalModify(final FwkParameterMap parameterMap)throws Exception{
		FwkTransferObject trans = new FwkTransferObject();
		
		/*기본정보*/
		String ev_seq = (String) parameterMap.get("ev_seq"); 					//평가차수
		String dyyyy = (String) parameterMap.get("dyyyy"); 					//년도
		String dyyyy_hid = (String) parameterMap.get("dyyyy_hid"); 					//년도
		String ev_name = (String) parameterMap.get("ev_name"); 				//평가명
		String ev_type = (String) parameterMap.get("ev_type"); 				//평가유형
		String exec_date_f = (String) parameterMap.get("exec_date_f"); 		//평가수행기간 시작
		String exec_date_t = (String) parameterMap.get("exec_date_t"); 		//평가수행기간 끝
		String work_date_f = (String) parameterMap.get("work_date_f"); 		//평가실적기간 시작
		String work_date_t = (String) parameterMap.get("work_date_t"); 		//평가실적기간 끝
		String remark = (String) parameterMap.get("remark"); 				//비고
		
		/*평가분야*/
		Object ev_code1_obj =  parameterMap.get("ev_code1"); 			//평가분야코드
		Object sg_name_obj  =  parameterMap.get("sg_name"); 			//SG명
		Object sg_code_obj  =  parameterMap.get("sg_code"); 			//SG코드
		Object dist_score_obj  =  parameterMap.get("dist_score"); 			//배점
	
		FwkParameterMap param = new FwkParameterMap();
		
		param.put("ev_seq", ev_seq);
		param.put("dyyyy", dyyyy_hid);
		param.put("orgId", "81");
		
		iproVendEvalDao.deleteEvMaster(param);
		iproVendEvalDao.deleteEvYyyyCode1(param);
		iproVendEvalDao.deleteEvYyyyCode2(param);
		iproVendEvalDao.deleteEvYyyyCode3(param);
		iproVendEvalDao.deleteEvVendor(param);
		iproVendEvalDao.deleteEvVendorDept(param);
		iproVendEvalDao.deleteEvVendorTemp(param);
		
		
		parameterMap.put("ev_seq",ev_seq);
		parameterMap.put("dyyyy",dyyyy);
		parameterMap.put("orgId", "81");
		parameterMap.put("ev_name",ev_name);
		parameterMap.put("ev_type",ev_type);
		parameterMap.put("exec_date_f",exec_date_f.replaceAll("-", ""));
		parameterMap.put("exec_date_t",exec_date_t.replaceAll("-", ""));
		parameterMap.put("work_date_f",work_date_f.replaceAll("-", ""));
		parameterMap.put("work_date_t",work_date_t.replaceAll("-", ""));
		parameterMap.put("remark",remark);
		parameterMap.put("ev_state", "A");
		
		iproVendEvalDao.insertT_Ev_Master(parameterMap); //T_EV_MASTER INSERT
		
		if(ev_code1_obj instanceof String){
			
			String ev_code1 = (String) ev_code1_obj;			//평가항목코드
			String sg_name  = (String) sg_name_obj;				//평가항목SG명
			String sg_code  = (String) sg_code_obj;				//평가항목SG코드
			String dist_score  = (String) dist_score_obj;		//평가항목배점
			
			parameterMap.put("ev_code1", ev_code1);
			parameterMap.put("sg_name", sg_name);
			parameterMap.put("sg_code", sg_code);
			parameterMap.put("dist_score", dist_score);
			
			iproVendEvalDao.insertT_Ev_Yyyy_Code1(parameterMap);
			iproVendEvalDao.insertT_Ev_Yyyy_Code2(parameterMap);
			iproVendEvalDao.insertT_Ev_Yyyy_Code3(parameterMap);
			
		}else{
			ArrayList<String> evCode1_list = (ArrayList<String>) ev_code1_obj;			//평가항목코드
			ArrayList<String> sgName_list  = (ArrayList<String>) sg_name_obj;			//평가항목SG명
			ArrayList<String> sgCode_list  = (ArrayList<String>) sg_code_obj;			//평가항목SG코드
			ArrayList<String> dist_score_list  = (ArrayList<String>) dist_score_obj;	//평가항목배점
			

			for (int i = 0; i < evCode1_list.size(); i++) {
				
				parameterMap.put("ev_code1", evCode1_list.get(i));
				parameterMap.put("sg_name", sgName_list.get(i).toString());
				parameterMap.put("sg_code", sgCode_list.get(i).toString());
				parameterMap.put("dist_score", dist_score_list.get(i).toString());
				parameterMap.put("ev_gubun2", "1"); // 일반평가만 저장 ("1" : 일반평가, "2" : 유관부서평가)
				
				iproVendEvalDao.insertT_Ev_Yyyy_Code1(parameterMap);
				iproVendEvalDao.insertT_Ev_Yyyy_Code2(parameterMap);
				iproVendEvalDao.insertT_Ev_Yyyy_Code3(parameterMap);
			}
		}
		
		System.err.println("================================================================================");
		parameterMap.put("ev_gubun2", "2"); // 유관부서평가만 저장 ("1" : 일반평가, "2" : 유관부서평가)
		iproVendEvalDao.insertT_Ev_Yyyy_Code1(parameterMap);
		iproVendEvalDao.insertT_Ev_Yyyy_Code2(parameterMap);
		iproVendEvalDao.insertT_Ev_Yyyy_Code3(parameterMap);
		
		// 평가업체 저장
		parameterMap.put("min_amt", "100"); //기존 신분당선은 공통코드에서가져옴.

		
		iproVendEvalDao.insertT_Ev_Vendor_Temp(parameterMap);
		
		
		return trans;
	}
	/**
	 * <pre>
	 * 1. 개요 : 평가종합관리 상세
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : selectVendEvalDetail
	 * @date : 2018. 02. 19.
	 * @author : 은우소프트 홍찬일
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2018. 02. 19.		은우소프트 홍찬일  		    최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 * @throws Exception 
	 */
	public FwkTransferObject selectVendEvalDetail(final FwkParameterMap parameterMap)throws Exception{
		FwkTransferObject trans = new FwkTransferObject();
		
		/*기본정보*/
		String ev_seq = (String) parameterMap.get("ev_seq"); 					//평가차수
		String dyyyy = (String) parameterMap.get("dyyyy"); 					//년도
		String ev_name = (String) parameterMap.get("ev_name"); 				//평가명
		
		parameterMap.put("orgId", "81");
		
		trans.put(IproVendEvalService.VEND_EVAL_MASTER_DETAIL, iproVendEvalDao.selectT_Ev_Master(parameterMap));
		trans.put(IproVendEvalService.VEND_EVAL_VENDOR_LIST, iproVendEvalDao.selectT_ev_vendor_List(parameterMap));
		trans.put(ComAtmaAtchFileService.ATCH_FILE_LIST, comAtmaAtchFileDao.selectAtchFileListInqireByAtchFileGroupNo(parameterMap));
		
		return trans;
	}
	/**
	 * <pre>
	 * 1. 개요 : 평가진행
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : vendEvalProceed
	 * @date : 2018. 02. 19.
	 * @author : 은우소프트 홍찬일
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2018. 02. 19.		은우소프트 홍찬일  		    최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 * @throws Exception 
	 */
	public FwkTransferObject vendEvalProceed(final FwkParameterMap parameterMap)throws Exception{
		FwkTransferObject trans = new FwkTransferObject();
		
		String gbn = parameterMap.getString("gbn");
		if(gbn.equals("add")) {
			parameterMap.put("ev_state", "D1");
		} else {
			parameterMap.put("ev_state", "B");
		}
		parameterMap.put("orgId", "81");
		parameterMap.put("work_date_f",parameterMap.get("work_date_f").toString().replaceAll("-", ""));
		parameterMap.put("work_date_t",parameterMap.get("work_date_t").toString().replaceAll("-", ""));
		
		if(parameterMap.get("istVenCode") != null && !parameterMap.get("istVenCode").equals("")) {
			String[] vendorCode = parameterMap.get("istVenCode").toString().split("#"); 
			String[] sgCode = parameterMap.get("istSgCode").toString().split("#"); 
			String[] sgName = parameterMap.get("istSgName").toString().split("#"); 
			
			System.err.println("vendorCode.length::"+vendorCode.length);
			for(int i=0; i< vendorCode.length; i++) {
				
				parameterMap.put("vendor_code", vendorCode[i]);
				parameterMap.put("sg_code", sgCode[i]);
				parameterMap.put("sg_name", sgName[i]);
				
				iproVendEvalDao.insertEvVendor(parameterMap);
				iproVendEvalDao.insertEvVendorDept(parameterMap);
				
			}
		}
		iproVendEvalDao.evalStateUpdate(parameterMap);
		
		return trans;
	}
	/**
	 * <pre>
	 * 1. 개요 : 상반기현업평가완료
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : vendEvalComplate
	 * @date : 2018. 02. 19.
	 * @author : 은우소프트 홍찬일
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2018. 02. 19.		은우소프트 홍찬일  		    최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 * @throws Exception 
	 */
	public FwkTransferObject vendEvalComplate(final FwkParameterMap parameterMap)throws Exception{
		FwkTransferObject trans = new FwkTransferObject();
		
		String	dyyyy		= parameterMap.getString("dyyyy");
		String	ev_seq		= parameterMap.getString("ev_seq");
		String	ev_state	= parameterMap.getString("ev_state");
		
		parameterMap.put("orgId", "81");
		parameterMap.put("dyyyy", dyyyy);
		parameterMap.put("ev_seq", ev_seq);
		parameterMap.put("ev_state", ev_state);
	
		// 평가 상태 업데이트
		iproVendEvalDao.evalStateUpdate(parameterMap);
		
		return trans;
	}
	/**
	 * <pre>
	 * 1. 개요 : 하반기평가업체추가
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : vendEvalVenComp2Add
	 * @date : 2018. 02. 19.
	 * @author : 은우소프트 홍찬일
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2018. 02. 19.		은우소프트 홍찬일  		    최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 * @throws Exception 
	 */
	public FwkTransferObject vendEvalVenComp2Add(final FwkParameterMap parameterMap)throws Exception{
		FwkTransferObject trans = new FwkTransferObject();
		
		if(parameterMap.get("loginResult") != null){
			FwkDataEntity session = (FwkDataEntity) parameterMap.get("loginResult");
		}
		
		String dyyyy_sch		=	parameterMap.getString("dyyyy_sch");
		String ev_name_sch	=	parameterMap.getString("ev_name_sch");
		String ev_state_sch	=	parameterMap.getString("ev_state_sch");
		String work_date_f		= 	parameterMap.getString("work_date_f");
		String work_date_t		=	parameterMap.getString("work_date_t");
		String dyyyy		=	parameterMap.getString("dyyyy");
		String ev_seq	=	parameterMap.getString("ev_seq");
		String sg_code	=	parameterMap.getString("sg_code");
		
		parameterMap.put("dyyyy", dyyyy);
		parameterMap.put("ev_seq", ev_seq);
		
		parameterMap.put("dyyyy_sch", dyyyy_sch);
		parameterMap.put("ev_name_sch", ev_name_sch);
		parameterMap.put("ev_state_sch", ev_state_sch);
		parameterMap.put("work_date_f", work_date_f);
		parameterMap.put("work_date_t", work_date_t);
		parameterMap.put("orgId", Integer.valueOf("81"));
		parameterMap.put("sg_code", sg_code);
		parameterMap.put("min_amt", "100");//기존 신분당선은 공통코드에서가져옴.
		
		trans.put(IproVendEvalService.VEND_EVAL_MASTER_DETAIL, iproVendEvalDao.selectT_Ev_Master(parameterMap));
		
		trans.put(IproVendEvalService.VEND_EVAL_VENDOR_ADD_LIST, iproVendEvalDao.getT_ev_vendor_add_List(parameterMap));
		
		return trans;
	}
	/**
	 * <pre>
	 * 1. 개요 : 유관부서평가 목록
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : vendEvalDeptList
	 * @date : 2018. 02. 19.
	 * @author : 은우소프트 홍찬일
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2018. 02. 19.		은우소프트 홍찬일  		    최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 * @throws Exception 
	 */
	public FwkTransferObject vendEvalDeptList(final FwkParameterMap parameterMap)throws Exception{
		FwkTransferObject trans = new FwkTransferObject();
		
		if(parameterMap.get("loginResult") != null){
			FwkDataEntity session = (FwkDataEntity) parameterMap.get("loginResult");
		}
		
		parameterMap.put("orgId", Integer.valueOf("81"));
		
		iproVendEvalDao.selectT_Ev_Master(parameterMap);
		
		trans.put(IproVendEvalService.VEND_EVAL_DEPT_LIST, iproVendEvalDao.vendEvalDeptListInqireWithPgng(parameterMap));
		trans.put(IproVendEvalService.VEND_EVAL_DEPT_LIST_TOTCNT, iproVendEvalDao.vendEvalDeptListTotcnt(parameterMap));
		
		return trans;
	}
	/**
	 * <pre>
	 * 1. 개요 : 유관부서평가 상세
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : vendEvalDeptDetail
	 * @date : 2018. 02. 19.
	 * @author : 은우소프트 홍찬일
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2018. 02. 19.		은우소프트 홍찬일  		    최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 * @throws Exception 
	 */
	public FwkTransferObject vendEvalDeptDetail(final FwkParameterMap parameterMap)throws Exception{
		FwkTransferObject trans = new FwkTransferObject();
		
		if(parameterMap.get("loginResult") != null){
			FwkDataEntity session = (FwkDataEntity) parameterMap.get("loginResult");
		}
		
		parameterMap.put("orgId", Integer.valueOf("81"));
		
		
		
		trans.put(IproVendEvalService.VEND_EVAL_MASTER_DETAIL, iproVendEvalDao.selectT_Ev_Master(parameterMap));
		
		trans.put(IproVendEvalService.VENDOR_DEPT_LIST, iproVendEvalDao.getT_ev_vendor_deptList(parameterMap));
		return trans;
	}
	/**
	 * <pre>
	 * 1. 개요 : 평가종합관리 평가수행
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : vendEvalDetailEvalView
	 * @date : 2018. 02. 19.
	 * @author : 은우소프트 홍찬일
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2018. 02. 19.		은우소프트 홍찬일  		    최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 * @throws Exception 
	 */
	public FwkTransferObject vendEvalDetailEvalView(final FwkParameterMap parameterMap)throws Exception{
		FwkTransferObject trans = new FwkTransferObject();
		
		if(parameterMap.get("loginResult") != null){
			FwkDataEntity session = (FwkDataEntity) parameterMap.get("loginResult");
		}
		
		parameterMap.put("orgId", Integer.valueOf("81"));
		parameterMap.put("ev_gubun2", Integer.valueOf("1"));
		
		trans.put(IproVendEvalService.VEND_EVAL_MASTER_DETAIL, iproVendEvalDao.selectT_Ev_Master(parameterMap));
		
		trans.put(IproVendEvalService.VEND_EVAL_VENDOR_DETAIL, iproVendEvalDao.getT_ev_vendor(parameterMap));
		
		trans.put(IproVendEvalService.VEND_EVAL_VENDOR_CODE_LIST, iproVendEvalDao.getT_ev_vendor_codeList(parameterMap));
		
		trans.put(IproVendEvalService.VEND_EVAL_EV_CODE_LENGTH, iproVendEvalDao.getEv_code_length(parameterMap));
		
		trans.put(IproVendEvalService.VEND_EVAL_EV_DYYYY_CODE3_LIST, iproVendEvalDao.getEvYyyyCode3List(parameterMap));
		
		trans.put(ComAtmaAtchFileService.ATCH_FILE_LIST, comAtmaAtchFileDao.selectAtchFileListInqireByAtchFileGroupNo(parameterMap));
		
		
		List<FwkDataEntity> dept_list = (List<FwkDataEntity>) iproVendEvalDao.getT_ev_vendor_deptList(parameterMap);
		
		FwkParameterMap tempDept = new FwkParameterMap();
		if(dept_list != null ){
			for (int i = 0; i < dept_list.size(); i++) {
				FwkDataEntity temp =  (FwkDataEntity)dept_list.get(i);
				tempDept.put("P_ATCHMNFL_GROUP_NO", temp.get("ATCHMNFL_GROUP_NO"));
				trans.put(ComAtmaAtchFileService.ATCH_FILE_LIST+(i+1), comAtmaAtchFileDao.selectAtchFileListInqireByAtchFileGroupNo(tempDept));
			}
		}
		
		trans.put(IproVendEvalService.VENDOR_DEPT_LIST, dept_list);
		return trans;
	}
	/**
	 * <pre>
	 * 1. 개요 : 평가종합관리 평가수행저장
	 * 2. 처리내용 :
	 * </pre>
	 * @Method Name : vendEvalDetailEvalSave
	 * @date : 2018. 02. 19.
	 * @author : 은우소프트 홍찬일
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2018. 02. 19.		은우소프트 홍찬일  		    최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 * @throws Exception 
	 */
	public FwkTransferObject vendEvalDetailEvalSave(final FwkParameterMap parameterMap)throws Exception{
		FwkTransferObject trans = new FwkTransferObject();
		
		FwkDataEntity session = (FwkDataEntity) parameterMap.get("loginResult");
		
		String atchFileGroupNo = parameterMap.getString("P_ATCHMNFL_GROUP_NO");
		
		List<Map<String, Object>> fileList = null;
		if(parameterMap.get("fileList") != null){
			fileList = (List<Map<String, Object>>) parameterMap.get("fileList");
		}
				if(fileList != null){
					parameterMap.put("atchFileGroupNo", atchFileGroupNo);
					parameterMap.put("P_ATCHMNFL_GROUP_NO", atchFileGroupNo);
					if(fileList.size() > 0 ){
						
						if("Y".equals(parameterMap.get("saveYn"))){
							parameterMap.put("atchFileGroupNo", atchFileGroupNo);
							parameterMap.put("fileList", fileList);
							parameterMap.put("P_SYSDATE", FwkDateUtil.getCurrentDateTimeAsString());
							parameterMap.put("P_EMPL_ID", parameterMap.getString("P_EMPL_ID"));
							
							comAtmaAtchFileDao.insertAtchFileRegist(parameterMap);
						}else{
							comAtmaAtchFileService.atchFileMapRegist(atchFileGroupNo, fileList , parameterMap);
						}
						
					}
				}
				
				// 삭제한 파일 DELETE_AT = 'N' 처리
				if("Y".equals(parameterMap.get("saveYn"))){
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
				}
		//=================================파일업로드 END==============================================
		
		Object EV_CODE1_OBJ 	= parameterMap.get("ev_code1");
		Object EV_CODE2_OBJ 	= parameterMap.get("ev_code2");
		Object EV_NAME2_OBJ 	= parameterMap.get("ev_name2");
		Object VALUE2_OBJ 	= parameterMap.get("value2"); 		
		Object SCORE2_OBJ 	= parameterMap.get("score2"); 		
		
		parameterMap.put("orgId", Integer.valueOf("81"));
		parameterMap.put("ev_gubun2", Integer.valueOf("1"));
		parameterMap.put("ev_code3", "");
		parameterMap.put("remark2", "");
		parameterMap.put("CREATE_DATE", FwkDateUtil.getCurrentDateTimeAsString());
		parameterMap.put("UPDATE_DATE", FwkDateUtil.getCurrentDateTimeAsString());
		parameterMap.put("create_user", session.get("USER_ID"));
		parameterMap.put("update_user", session.get("USER_ID"));
		parameterMap.put("atchFileGroupNo", atchFileGroupNo);
		iproVendEvalDao.uploadT_Ev_Vendor(parameterMap);
		
		if(EV_CODE1_OBJ instanceof String){
			iproVendEvalDao.deleteEvVendorCode2(parameterMap);
			iproVendEvalDao.insertEvVendorCode2(parameterMap);
			
		}else{ 
			String[] EV_CODE1_LIST	= (String[]) EV_CODE1_OBJ; 
			String[] EV_CODE2_LIST 	= (String[]) EV_CODE2_OBJ; 
			String[] EV_NAME2_LIST 	= (String[]) EV_NAME2_OBJ; 
			String[] VALUE2_LIST 	= (String[]) VALUE2_OBJ; 	
			String[] SCORE2_LIST	= (String[]) SCORE2_OBJ; 	
			
			if(EV_CODE1_LIST != null){
				for (int i = 0; i < EV_CODE1_LIST.length; i++) {
					
					
					parameterMap.put("ev_code1", EV_CODE1_LIST[i]);
					parameterMap.put("ev_code2", EV_CODE2_LIST[i]);
					parameterMap.put("ev_name2", EV_NAME2_LIST[i]);
					parameterMap.put("value2", VALUE2_LIST[i]);
					parameterMap.put("score2", SCORE2_LIST[i]);
					iproVendEvalDao.deleteEvVendorCode2(parameterMap);
					iproVendEvalDao.insertEvVendorCode2(parameterMap);
				}
			}
		}
		trans.put("parameterMap", parameterMap);
		return trans;
	}
	/**
	 * <pre>
	 * 1. 개요 : 유관부서 업체상세 평가수행
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : vendEvalDeptEvalView
	 * @date : 2018. 02. 19.
	 * @author : 은우소프트 홍찬일
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2018. 02. 19.		은우소프트 홍찬일  		    최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 * @throws Exception 
	 */
	public FwkTransferObject vendEvalDeptEvalView(final FwkParameterMap parameterMap)throws Exception{
		FwkTransferObject trans = new FwkTransferObject();
		
		if(parameterMap.get("loginResult") != null){
			FwkDataEntity session = (FwkDataEntity) parameterMap.get("loginResult");
		}
		
		parameterMap.put("ev_gubun2", Integer.valueOf("2")); //유관부서평가
		
		parameterMap.put("orgId", Integer.valueOf("81"));
		
		trans.put(IproVendEvalService.VEND_EVAL_MASTER_DETAIL, iproVendEvalDao.selectT_Ev_Master(parameterMap));
		
		trans.put(IproVendEvalService.VEND_EVAL_VENDOR_DEPT_DETAIL, iproVendEvalDao.getT_ev_vendor_dept(parameterMap));
		
		trans.put(ComAtmaAtchFileService.ATCH_FILE_LIST, comAtmaAtchFileDao.selectAtchFileListInqireByAtchFileGroupNo(parameterMap));
		
		parameterMap.put("seq", "1");
		trans.put(IproVendEvalService.VEND_EVAL_VENDOR_DEPT_CODE_LIST1, iproVendEvalDao.getT_ev_vendor_dept_codeList( parameterMap )); // 상반기
		parameterMap.put("seq", "2");
		trans.put(IproVendEvalService.VEND_EVAL_VENDOR_DEPT_CODE_LIST2, iproVendEvalDao.getT_ev_vendor_dept_codeList( parameterMap )); // 하반기
		
		return trans;
	}
	/**
	 * <pre>
	 * 1. 개요 : 유관부서 업체상세 평가수행 저장
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : vendEvalDeptInsert
	 * @date : 2018. 02. 19.
	 * @author : 은우소프트 홍찬일
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2018. 02. 19.		은우소프트 홍찬일  		    최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 * @throws Exception 
	 */
	public FwkTransferObject vendEvalDeptInsert(final FwkParameterMap parameterMap)throws Exception{
		FwkTransferObject trans = new FwkTransferObject();
		
		FwkDataEntity session = (FwkDataEntity) parameterMap.get("loginResult");
		
		String atchFileGroupNo = parameterMap.getString("P_ATCHMNFL_GROUP_NO");
		
		List<Map<String, Object>> fileList = null;
		if(parameterMap.get("fileList") != null){
			fileList = (List<Map<String, Object>>) parameterMap.get("fileList");
		}
				if(fileList != null){
					parameterMap.put("atchFileGroupNo", atchFileGroupNo);
					parameterMap.put("P_ATCHMNFL_GROUP_NO", atchFileGroupNo);
					if(fileList.size() > 0 ){
						
						if("Y".equals(parameterMap.get("saveYn"))){
							parameterMap.put("atchFileGroupNo", atchFileGroupNo);
							parameterMap.put("fileList", fileList);
							parameterMap.put("P_SYSDATE", FwkDateUtil.getCurrentDateTimeAsString());
							parameterMap.put("P_EMPL_ID", parameterMap.getString("P_EMPL_ID"));
							
							comAtmaAtchFileDao.insertAtchFileRegist(parameterMap);
						}else{
							comAtmaAtchFileService.atchFileMapRegist(atchFileGroupNo, fileList , parameterMap);
						}
						
					}
				}
				
				// 삭제한 파일 DELETE_AT = 'N' 처리
				if("Y".equals(parameterMap.get("saveYn"))){
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
				}
		//=================================파일업로드 END==============================================
		
		Object EV_CODE1_OBJ 	= parameterMap.get("ev_code1");
		Object EV_CODE2_OBJ 	= parameterMap.get("ev_code2");
		Object EV_NAME2_OBJ 	= parameterMap.get("ev_name2");
		Object VALUE2_OBJ = null; 	
		Object SCORE2_OBJ = null; 	 	
		
		parameterMap.put("orgId", Integer.valueOf("81"));
		parameterMap.put("ev_gubun2", Integer.valueOf("2"));
		parameterMap.put("ev_code3", "");
		parameterMap.put("remark2", "");
		parameterMap.put("CREATE_DATE", FwkDateUtil.getCurrentDateTimeAsString());
		parameterMap.put("UPDATE_DATE", FwkDateUtil.getCurrentDateTimeAsString());
		parameterMap.put("create_user", session.get("USER_ID"));
		parameterMap.put("update_user", session.get("USER_ID"));
		parameterMap.put("atchFileGroupNo", atchFileGroupNo);
		iproVendEvalDao.uploadT_Ev_Vendor_Dept(parameterMap);
		
		
		String[]	value2 = null;
		String[]	score2 = null;
		String		dept_seq = null;
		String		tot_score = null;
		
		FwkParameterMap veCode2Map	= new FwkParameterMap();
		if("B".equals(parameterMap.getString("ev_state"))) {
			tot_score 	= 	parameterMap.getString("tot_score");
			VALUE2_OBJ	 =  parameterMap.get("value2"); 
			SCORE2_OBJ	 =  parameterMap.get("score2");
			dept_seq = "1";
		
		} else if("D1".equals(parameterMap.getString("ev_state"))) {
			tot_score 	= 	parameterMap.getString("tot_score2");
			VALUE2_OBJ	 = 	parameterMap.get("value2_2"); 
			SCORE2_OBJ	 =  parameterMap.get("score2_2");
			dept_seq = "2";
		}
		parameterMap.put("seq", dept_seq);
		parameterMap.put("value",tot_score);
		parameterMap.put("score",tot_score);
		//if(tot_score.equals("0")) {
		if("0".equals(tot_score)){
			iproVendEvalDao.evalDeptDeleteAll(parameterMap);
		}else{
				if(Double.parseDouble(tot_score) > 100 ) {
					tot_score = "100";
				}
			
				
				FwkDataEntity searchCode = iproVendEvalDao.getEv_gubun1A( parameterMap );
				
				veCode2Map.put("ev_code1", searchCode.get("EV_CODE1"));
				veCode2Map.put("ev_code2","EI0001");
				veCode2Map.put("ev_name2", searchCode.get("EV_NAME1"));
				veCode2Map.put("ev_gubun2","2");
					
			iproVendEvalDao.evalDeptDeleteAll(parameterMap);
			
			if(EV_CODE1_OBJ instanceof String){
				iproVendEvalDao.evalDeptInsert(parameterMap);
			}else{ 
				String[] EV_CODE1_LIST	= (String[]) EV_CODE1_OBJ; 
				String[] EV_CODE2_LIST 	= (String[]) EV_CODE2_OBJ; 
				String[] EV_NAME2_LIST 	= (String[]) EV_NAME2_OBJ; 
				String[] VALUE2_LIST 	= (String[]) VALUE2_OBJ; 	
				String[] SCORE2_LIST	= (String[]) SCORE2_OBJ; 	
				
				if(EV_CODE1_LIST != null){
					for (int i = 0; i < EV_CODE1_LIST.length; i++) {
						System.err.println("evalDeptInsert CNT::["+i+"]");
						
						parameterMap.put("ev_code1", EV_CODE1_LIST[i]);
						parameterMap.put("ev_code2", EV_CODE2_LIST[i]);
						parameterMap.put("ev_name2", EV_NAME2_LIST[i]);
						parameterMap.put("value2", VALUE2_LIST[i]);
						parameterMap.put("score2", SCORE2_LIST[i]);
						iproVendEvalDao.evalDeptInsert(parameterMap);
					}
				}
			}
			
			//업체중 소싱그룹이 같고 유관부서가 여러곳인 경우 총점을 구하기 위해서 사용		
			Double score = iproVendEvalDao.getT_ev_vendor_dept_code2Sum(parameterMap);
		
			parameterMap.put("score",score );
			parameterMap.put("value",score );
			
			parameterMap.put("create_user", session.get("USER_ID"));
			parameterMap.put("update_user", session.get("USER_ID"));
			
			
			//현업평가 총점 삭제
			iproVendEvalDao.deleteT_ev_vendor_evalCode2(parameterMap);
			//현업평가 총점 저장
			iproVendEvalDao.insertT_ev_vendor_evalCode2(parameterMap);
		}
		
		return trans;
	}
	/**
	 * <pre>
	 * 1. 개요 : 평가결과 목록
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : vendEvalResultList
	 * @date : 2018. 02. 19.
	 * @author : 은우소프트 홍찬일
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2018. 02. 19.		은우소프트 홍찬일  		    최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 * @throws Exception 
	 */
	public FwkTransferObject vendEvalResultList(final FwkParameterMap parameterMap)throws Exception{
		FwkTransferObject trans = new FwkTransferObject();
		
		if(parameterMap.get("loginResult") != null){
			FwkDataEntity session = (FwkDataEntity) parameterMap.get("loginResult");
		}
		
		parameterMap.put("orgId", Integer.valueOf("81"));
		
		trans.put(IproVendEvalService.VEND_EVAL_MASTER_DETAIL, iproVendEvalDao.selectT_Ev_Master(parameterMap));
		trans.put(IproVendEvalService.VEND_EVAL_RESULT_LIST, iproVendEvalDao.evalListInqireWithPgng(parameterMap));
		trans.put(IproVendEvalService.VEND_EVAL_RESULT_LIST_TOTCNT, iproVendEvalDao.evalListTotcnt(parameterMap));
		
		return trans;
	}
	/**
	 * <pre>
	 * 1. 개요 : 평가결과 상세
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : vendEvalResultDetail
	 * @date : 2018. 02. 19.
	 * @author : 은우소프트 홍찬일
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2018. 02. 19.		은우소프트 홍찬일  		    최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 * @throws Exception 
	 */
	public FwkTransferObject vendEvalResultDetail(final FwkParameterMap parameterMap)throws Exception{
		FwkTransferObject trans = new FwkTransferObject();
		
		if(parameterMap.get("loginResult") != null){
			FwkDataEntity session = (FwkDataEntity) parameterMap.get("loginResult");
		}
		
		parameterMap.put("orgId", Integer.valueOf("81"));
		
		trans.put(IproVendEvalService.VEND_EVAL_MASTER_DETAIL, iproVendEvalDao.selectT_Ev_Master(parameterMap));
		trans.put(IproVendEvalService.VEND_EVAL_VENDOR_RSLT_LIST, iproVendEvalDao.getT_ev_vendor_rslt_list(parameterMap));
		List<FwkDataEntity> dataList = (List<FwkDataEntity>) trans.get(IproVendEvalService.VEND_EVAL_VENDOR_RSLT_LIST);
		
		//분야별 평가점수 평가결과 목록에 추가
				if ( dataList != null && dataList.size() > 0 ) {
					FwkDataEntity	ev_vendor_rslt	= null;
					
					for ( int i = 0 ; i < dataList.size() ; i++ ) {
						ev_vendor_rslt	= (FwkDataEntity) dataList.get(i);
					
						//분야별 평가점수 가져오기
						parameterMap.put("vendor_code",ev_vendor_rslt.get("VENDOR_CODE") );
						parameterMap.put("sg_code",ev_vendor_rslt.get("SG_CODE"));				
						List<FwkDataEntity>	dataList_sub	= iproVendEvalDao.getT_ev_vendor_rslt_sub_list( parameterMap );
						
						if ( dataList_sub != null && dataList_sub.size() > 0 ) {
							trans.put("dataList_sub", dataList_sub);
							HashMap	ev_vendor_rslt_sub	= null;
						
							for ( int j = 0 ; j < dataList_sub.size() ; j++ ) {
								ev_vendor_rslt_sub	= (HashMap) dataList_sub.get(j);
								ev_vendor_rslt.put("DIST_SCORE"+j, ev_vendor_rslt_sub.get("DIST_SCORE"));
								ev_vendor_rslt.put("EVAL_SCORE"+j, ev_vendor_rslt_sub.get("EVAL_SCORE"));
								ev_vendor_rslt.put("RSLT_SCORE"+j, ev_vendor_rslt_sub.get("RSLT_SCORE"));
								trans.put("dataList_sub_cnt", j);
							}
							
						}

						
					}
				}
		
		return trans;
	}
}