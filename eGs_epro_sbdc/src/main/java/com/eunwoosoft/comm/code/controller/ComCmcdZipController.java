package com.eunwoosoft.comm.code.controller;

import javax.annotation.Resource;

import javax.servlet.http.HttpServletRequest;

import com.eunwoosoft.comm.code.service.ComCmcdZipService;
import com.eunwoosoft.frwk.fol.util.FwkStringUtil;
import com.eunwoosoft.frwk.prl.AbstractFwkController;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;

import org.apache.ibatis.mapping.ParameterMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 공통코드 > 우편번호
 * <pre>
 * com.eunwoosoft.comm.code.controller 
 *    |_ ComCmcdZipController.java
 * 
 * </pre>
 * @date : 2014. 12. 15. 오전 10:26:01
 * @version : 1.0
 * @author : 
 */
@Controller
@RequestMapping(value = "/com/cmcd")
public class ComCmcdZipController extends AbstractFwkController {

	@SuppressWarnings("unused")
	private static final Logger LOG = LoggerFactory.getLogger(ComCmcdZipController.class);

	@Resource(name="comCmcdLmnZipService")
	private ComCmcdZipService comCmcdLmnZipService;

	@Resource(name="comCmcdRnZipService")
	private ComCmcdZipService comCmcdRnZipService;

	/**
	 * <pre>
	 * 1. 개요 : 지번 주소 목록 조회 
	 * 2. 처리내용 : 
	 * 		- 검색한 지번 주소 목록 조회 서비스를 호출한다.
	 * 3. 입출력 : 
	 *	-----------------------------------------------------------------------
	 * I/O	 항목	                              타입             설명                             참고사항
	 * -----------------------------------------------------------------------
	  *  I       P_DONG_NM               String        읍면동			     required
	 *  
	 *  O      NEW_ZIP      				String			신규 우편번호			    ~
	 *  		ZIP							String 		우편번호						~
	 *         CTPRVN_NM                String        시/도			    			~
	 *			SIGNGU_NM		    		String        시/군/구			    	 	~
	 *         ROAD_NM		    		String        도로명			     			~
	 *         BDNBR_WEEK_LNM		String        건물번호 주지번			    ~
	 *         BDNBR_FATHER_LNM		String        건물번호 부지번			    ~
	 *         SIGNGU_BULD_NM		    String        건물명			     			~
	 *         MNTN_AT		    		String        산 여부     					~
	 *         EMD_NM		    			String        읍면동			     			~
	 *         ADSTRD_NM				String        행정동명		     			~
	 *         LEGALDONG_NM		    String        법정동			    	 		~
	 *         LNM_WEEK_LNM		    String        지번 주지번			     	~
	 *         LNM_FATHER_LNM		String        지번 부지번			     	~
	 *         LI_NM		    				String        리명			     			~
	 *         ADRES		    			String        지번주소			     		~
	 *         ROAD_ADRES		    	String        도로명주소			     	~
	 * -----------------------------------------------------------------------
	 * -----------------------------------------------------------------------
	 * 
	 * </pre>
	 * @Method Name : lmnZipList
	 * @date : 2015. 02. 13.
	 * @author : 야긴스텍 정윤교
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 02. 13.		야긴스텍 정윤교				최초 작성 
	 *	-----------------------------------------------------------------------
	 *
	 * @param request
	 * 					요청정보를 {@link ParameterMap} 형태로 담고 있는 {@link HttpServletRequest}
	 * @param model
	 * @return viewName
	 */
	@RequestMapping(value = "/lmnZipList")
	public String lmnZipList(final HttpServletRequest request, final Model model) {

		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);		
		String viewName = parameterMap.getTrimString("returnUrl");

		if(FwkStringUtil.isNull((String) parameterMap.get("P_DONG_NM")) == false){
			model.addAllAttributes(comCmcdLmnZipService.lmnZipListInqireWithPgng(parameterMap));	
		}

		return viewName;
	}

	/**
	 * <pre>
	 * 1. 개요 : 도로명 우편 번호 목록 조회 
	 * 2. 처리내용 : 
	 * 		- 검색한 도로명 주소 목록 서비스를 호출한다.
	 * 3. 입출력 : 
	 *	-----------------------------------------------------------------------
	 * I/O	 항목	                              타입             설명                             참고사항
	 * -----------------------------------------------------------------------
	 *  I      P_SEARCH_TYPE          String        검색구분			     	required
	 *			P_CTPRVN_NM				String			시/도						required
	 *  		P_SIGNGU_NM				String			시/군/구					required
	 *  		P_ROAD_NM				String			도로명						required
	 *     		P_SIGNGU_BULD_NM		String			건물명						required
	 *  		P_DONG_NM               String        	읍면동			     		required
	 *  
	 *  O     NEW_ZIP      				String			신규 우편번호			    ~
	 *  		ZIP							String 		우편번호						~
	 *         CTPRVN_NM                String        시/도			    			~
	 *			SIGNGU_NM		    		String        시/군/구			    	 	~
	 *         ROAD_NM		    		String        도로명			     			~
	 *         BDNBR_WEEK_LNM		String        건물번호 주지번			    ~
	 *         BDNBR_FATHER_LNM		String        건물번호 부지번			    ~
	 *         SIGNGU_BULD_NM		    String        건물명			     			~
	 *         MNTN_AT		    		String        산 여부     					~
	 *         EMD_NM		    			String        읍면동			     			~
	 *         ADSTRD_NM				String        행정동명		     			~
	 *         LEGALDONG_NM		    String        법정동			    	 		~
	 *         LNM_WEEK_LNM		    String        지번 주지번			     	~
	 *         LNM_FATHER_LNM		String        지번 부지번			     	~
	 *         LI_NM		    				String        리명			     			~
	 *         ADRES		    			String        지번주소			     		~
	 *         ROAD_ADRES		    	String        도로명주소			     	~
	 * -----------------------------------------------------------------------
	 * 
	 * </pre>
	 * @Method Name : rnZipList
	 * @date : 2015. 02. 13.
	 * @author : 야긴스텍 정윤교
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 02. 13.		야긴스텍 정윤교				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param request
	 * 					요청정보를 {@link ParameterMap} 형태로 담고 있는 {@link HttpServletRequest}
	 * @param model
	 * @return viewName
	 */
	@RequestMapping(value = "/rnZipList")
	public String rnZipList(final HttpServletRequest request, final Model model) {

		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);		
		String viewName = parameterMap.getTrimString("returnUrl");		

		if( FwkStringUtil.isNull((String) parameterMap.get("P_CTPRVN_NM")) == false
				|| FwkStringUtil.isNull((String) parameterMap.get("P_SIGNGU_NM")) == false
				|| FwkStringUtil.isNull((String) parameterMap.get("P_ROAD_NM")) == false
				|| FwkStringUtil.isNull((String) parameterMap.get("P_SIGNGU_BULD_NM")) == false
				){
			model.addAllAttributes(comCmcdRnZipService.rnZipListInqireWithPgng(parameterMap));	
		}

		return viewName;
	}

}
