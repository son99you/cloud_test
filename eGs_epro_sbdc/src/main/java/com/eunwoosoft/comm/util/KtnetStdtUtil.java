package com.eunwoosoft.comm.util;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.eunwoosoft.frwk.fol.dto.FwkDataEntity;
import com.eunwoosoft.frwk.fol.util.FwkDateUtil;
import com.eunwoosoft.frwk.fol.util.FwkFormatUtil;
import com.eunwoosoft.frwk.fol.util.FwkMessageUtil;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;
import com.itextpdf.text.DocumentException;
import com.ktnet.ets.hub.common.property.Constants;
import com.ktnet.ets.hub.exception.EtsConfigurationException;
import com.ktnet.ets.hub.exception.EtsProcessingException;
import com.ktnet.ets.hub.exception.ValidationException;
import com.ktnet.ets.hub.inf.manager.EtsHubManager;


/**
 * KTNET 수입인지 발급 유틸
 */
public class KtnetStdtUtil {
	
	/**
	 * 
	 * 
	 * 	<pre>
	 *  1. 개요 :
	 * 	2. 처리내용 : 인지세 PDF납부 확인 
	 *	</pre>
	 *
	 * @method Name : stampPdfCreate
	 * @Author : joo
	 * @Date   : 2020. 11. 6.
	 * @history : 
	 * ------------------------------------------------------------------------
	 * 변경일                                         작성자                                       변경내용
	 * ------------------------------------------------------------------------
	 * 2020. 11. 6.                 joo                최초작성
	 * ------------------------------------------------------------------------
	 *
	 *
	 *
	 * @param contEntity
	 *
	 */
	public static FwkParameterMap stampPdfCreate(FwkDataEntity contEntity) throws EtsConfigurationException, IOException, ValidationException, EtsProcessingException, DocumentException {
		
		FwkParameterMap rsltParamMap = new FwkParameterMap();
		
		try{
			
			String exportPath = FwkMessageUtil.getMessage("EW.SYS.FILE.DIR")+
					FwkDateUtil.getCurrentDateAsString("yyyy")+
					File.separator+FwkDateUtil.getCurrentDateAsString("MM")+
					File.separator+FwkDateUtil.getCurrentDateAsString("dd")+
					File.separator+"stamp"+File.separator;
			
			File f = new File(exportPath);
			
			if(!f.isDirectory()){
				f.setExecutable(false, false);
				f.setReadable(false, false);
				f.setWritable(false, false);			
				f.mkdirs();		
			}
			
			String exportFileNm = contEntity.getString("CONT_NO")+".pdf";
			String exportStampFile = exportPath+exportFileNm;
			
			//Map<String, Object> paramMap = new HashMap<String, Object>();
			Map paramMap = new HashMap();
			//************************ pdf 갑지 생성 시작 ********************
			//Map<String, Object> dataMap = new HashMap<String, Object>();
			Map dataMap = new HashMap();
			
			dataMap.put("inhr_no", contEntity.getString("INHR_NO"));	//고유번호
			
			dataMap.put("cont_no", contEntity.getString("CONT_NO")+"-"+contEntity.getString("CHNG_NGR")); //계약번호
			dataMap.put("cont_nm", contEntity.getString("CONT_NM")); //계약명
			dataMap.put("cont_amt", FwkFormatUtil.formatMoney(contEntity.getString("CONT_AMT"))); //계약금액
			dataMap.put("cont_de", FwkFormatUtil.formatDate(contEntity.getString("CONT_DE"),"yyyyMMdd","yyyy년 MM월 dd일")); //계약일자
			dataMap.put("tot_prch_amt", FwkFormatUtil.formatMoney(contEntity.getString("STPTX_AMT"))); //납부금액
					
			dataMap.put("epro_rprs_nm", contEntity.getString("RPRS_NM")); //대표자
			dataMap.put("epro_entrps_nm",  contEntity.getString("VEND_NM")); //수급사업자명
			dataMap.put("epro_bizrno", FwkFormatUtil.formatBizNumber(contEntity.getString("BIZRNO"))); //사업자등록번호
			
			dataMap.put("rprs_nm", contEntity.getString("RPRS_NM_A")); //대표자   
			dataMap.put("vend_nm", contEntity.getString("VEND_NM_A")); //수급사업자명
			dataMap.put("bizrno", FwkFormatUtil.formatBizNumber(contEntity.getString("BIZRNO_A"))); //사업자등록번호
			
			
			/*dataMap.put("inhr_no", "00001"); //고유번호
			
			dataMap.put("cont_no", "A018020025_0"); //계약번호
			
			dataMap.put("cont_nm", "ktnet문화공감센터 시설관리용역"); //계약명
			dataMap.put("cont_de", "2020-11-16"); //계약일자
			dataMap.put("cont_amt", "100,000,000원"); //계약금액
			
			dataMap.put("tot_prch_amt", "150,000"); //인지세납부금액

			//발주기관
			dataMap.put("epro_entrps_nm", "업체명"); //업체명
			dataMap.put("epro_bizrno", "123-12-312312"); //사업자번호		
			dataMap.put("epro_rprs_nm", "홍길동"); //대표자
			

			//계약상대자
			dataMap.put("vend_nm", "업체명"); //업체명
			dataMap.put("bizrno", "123-12-312312"); //사업자번호
			dataMap.put("rprs_nm", "홍길동"); //대표자
*/			
			//ktnet 제공 갑지 서식 pdf 
			paramMap.put(Constants.FORM_PDF_PATH, FwkMessageUtil.getMessage("STAMP.TEMPLATE.PATH"));
			//파라미터 입력이 적용되어 변환된 pdf
			paramMap.put(Constants.STAMP_PDF_PATH, exportStampFile);
			
			paramMap.put(Constants.DATA_MAP, dataMap);
			
			//Map rsltCrttMap = etsHubManager.setPdfFormDataOverwrite(paramMap);
			EtsHubManager etsHubManager = new EtsHubManager(); 
			Map rsltCrttMap = etsHubManager.setPdfFormDataOverwrite(paramMap); 	//pdf 생성 끝
			rsltParamMap.putAll(rsltCrttMap);  
		        
	        rsltParamMap.put("expertPath", exportPath);
	        rsltParamMap.put("expertFileNm", exportFileNm);
			
		}catch(Exception ex){
	        ex.printStackTrace();
	    } 
	        
        return rsltParamMap;		 
	}
	
}
