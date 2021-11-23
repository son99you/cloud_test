package com.eunwoosoft.ipro.main.controller;

import java.util.Calendar;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.eunwoosoft.comm.menu.controller.AbstractIproMenuController;
import com.eunwoosoft.frwk.fol.dto.FwkDataEntity;
import com.eunwoosoft.frwk.fol.dto.FwkTransferObject;
import com.eunwoosoft.frwk.fol.util.FwkFormatUtil;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;
import com.eunwoosoft.ipro.main.service.IproMainLoginFormService;
import com.eunwoosoft.ipro.noti.service.IproFaqService;
import com.eunwoosoft.ipro.noti.service.IproNotiService;

/**
 * 
 * com.eunwoosoft.ipro.main.controller
 * IproMainPageController.java
 *
 * @Author : SungYoon_Ha
 * @Date   : 2017. 5. 30.
 *
 */
@RequestMapping(value = "/main")
@Controller
public class IproMainPageController extends AbstractIproMenuController {
	String prefixUrl = "/ipro/views/main/";
	String popupPrefixUrl = "/ipro/views/main/popup/";
	
	@Resource(name="iproMainLoginFormService")
	private IproMainLoginFormService iproMainLoginFormService;
	
	@Resource(name="iproNotiService")
	private IproNotiService iproNotiService;
	
	@Resource(name="iproFaqService")
	private IproFaqService iproFaqService;

	/**
	 * 
	 * 
	 * 	<pre>
	 *  1. 개요 :
	 * 	2. 처리내용 :
	 *	</pre>
	 *
	 * @method Name : mainPage
	 * @Author : joo
	 * @Date   : 2020. 10. 20.
	 * @history : 
	 * ------------------------------------------------------------------------
	 * 변경일                                         작성자                                       변경내용
	 * ------------------------------------------------------------------------
	 * 2020. 10. 20.                 joo                최초작성
	 * ------------------------------------------------------------------------
	 * @param request
	 * @param model
	 * @return
	 *
	 */
	@RequestMapping(value="/mainPage")
	public String mainPage(final HttpServletRequest request, final Model model){
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		FwkTransferObject trans = new FwkTransferObject();
		
		FwkDataEntity user = (FwkDataEntity)parameterMap.get("loginResult");
		
		if("1".equals(user.get("AUTH_ID"))){	//관리자
			//전체조회 가능
		}else if("2".equals(user.get("AUTH_ID"))){	//일반사용자
			parameterMap.put("P_DEPT_NO", user.getString("DEPT_NO"));
		}else if("3".equals(user.get("AUTH_ID"))){	//평가위원순위선정자(감사실)
			parameterMap.put("P_DEPT_NO", user.getString("DEPT_NO"));
		}else if("4".equals(user.get("AUTH_ID"))){	//평가위원담당자(마케팅기획팀)
			parameterMap.put("P_DEPT_NO", user.getString("DEPT_NO"));
		}else{	
			parameterMap.put("P_DEPT_NO", user.getString("DEPT_NO"));
		}
		
		
		if(!"Y".equals(parameterMap.getString("P_SEARCH"))){
			//조회기간 - 한달 
			Calendar cal = Calendar.getInstance();
			int currentMonth = (cal.get(Calendar.MONTH)+1); 
			String sMonth = "";
			
			String firstDay = "0" + cal.getMinimum(Calendar.DAY_OF_MONTH);
			int lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH); //기본적으로 이걸 사용
			int year = cal.get(Calendar.YEAR);
			
			if( currentMonth < 10 ){
				sMonth = "0" + currentMonth;
			}else{
				sMonth = Integer.toString(currentMonth);;
			}
			
			parameterMap.put("P_BEGIN_DT_S", year + sMonth + firstDay);
			parameterMap.put("P_END_DT_S", year + sMonth + lastDay);
		}else{
			if(!"".equals(parameterMap.getString("P_BEGIN_DT_S"))){
				parameterMap.put("P_BEGIN_DT_S", FwkFormatUtil.formatDate(parameterMap.get("P_BEGIN_DT_S").toString(), "yyyy-MM-dd", "yyyyMMdd")); 
			}
			
			if(!"".equals(parameterMap.getString("P_END_DT_S"))){
				parameterMap.put("P_END_DT_S", FwkFormatUtil.formatDate(parameterMap.get("P_END_DT_S").toString(), "yyyy-MM-dd", "yyyyMMdd")); 
			}
		}
		

		// 공지사항
		parameterMap.put("P_PAGE_NO", "1");
		parameterMap.put("P_PAGE_SIZE", "4");
		trans = iproNotiService.notiListWithPgng(parameterMap);
		model.addAllAttributes(trans);
		
		// FAQ
		parameterMap.put("P_PAGE_NO", "1");
		parameterMap.put("P_PAGE_SIZE", "5");
		trans = iproFaqService.faqListWithPgng(parameterMap);
		model.addAllAttributes(trans);
		
		//진행현황
		trans = iproMainLoginFormService.mainEstmCnt(parameterMap);
		model.addAllAttributes(trans);
		
		//진행상세
		trans = iproMainLoginFormService.getMainSummary(parameterMap);
		model.addAllAttributes(trans);
		model.addAllAttributes(parameterMap);		
		
		return parameterMap.getViewName(prefixUrl);
	}
	
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 부서안내 팝업 
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri_keri.com.eunwoosoft.ipro.main.controller.IproMainPageController.java
	 * @Method : deptGuidList
	 * @author : JanDi_Eun
	 * @date : 2019. 4. 16. 
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/popup/deptGuidList")
	public String deptGuidList(final HttpServletRequest request, final Model model) throws Exception {		
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);
		FwkTransferObject trans = new FwkTransferObject();

		model.addAllAttributes(trans);
		model.addAllAttributes(parameterMap);
		return parameterMap.getPopupViewName(popupPrefixUrl);
	}
	
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 사용자 PC 환경설정
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri_keri.com.eunwoosoft.ipro.main.controller.IproMainPageController.java
	 * @Method : usrPCSet
	 * @author : JanDi_Eun
	 * @date : 2019. 4. 16. 
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/popup/usrPCSet")
	public String usrPCSet(final HttpServletRequest request, final Model model) throws Exception {		
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);
		FwkTransferObject trans = new FwkTransferObject();

		model.addAllAttributes(trans);
		model.addAllAttributes(parameterMap);
		return parameterMap.getPopupViewName(popupPrefixUrl);
	}
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 용어정리 
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri_keri.com.eunwoosoft.ipro.main.controller.IproMainPageController.java
	 * @Method : trmList
	 * @author : JanDi_Eun
	 * @date : 2019. 4. 17. 
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/popup/trmList")
	public String trmList(final HttpServletRequest request, final Model model) throws Exception {		
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);
		FwkTransferObject trans = new FwkTransferObject();
		
		model.addAllAttributes(trans);
		model.addAllAttributes(parameterMap);
		return parameterMap.getPopupViewName(popupPrefixUrl);
	}
	
	@RequestMapping(value = "/mainEstmList" )
	public String mainBidCommList(final HttpServletRequest req, final Model model) throws Exception{
		
		FwkParameterMap parameterMap = (FwkParameterMap) req.getAttribute(PARAMETER_MAP);
		FwkTransferObject trans = new FwkTransferObject();
		
		if(!"Y".equals(parameterMap.getString("P_SEARCH"))){
			//조회기간 설정
			Calendar cal = Calendar.getInstance();
	        int currentMonth = (cal.get(Calendar.MONTH)+1); 
	        String sMonth = "";
	        
	        String firstDay = "0" + cal.getMinimum(Calendar.DAY_OF_MONTH);
	        int lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH); //기본적으로 이걸 사용
	        int year = cal.get(Calendar.YEAR);
	        
	        if( currentMonth < 10 ){
	        	sMonth = "0" + currentMonth;
	        }else{
	        	sMonth = Integer.toString(currentMonth);;
	        }
			parameterMap.put("P_BEGIN_DT_S", year + sMonth + firstDay);
			parameterMap.put("P_END_DT_S", year + sMonth + lastDay);
		}else{
			if(!"".equals(parameterMap.getString("P_BEGIN_DT_S"))){
				parameterMap.put("P_BEGIN_DT_S", FwkFormatUtil.formatDate(parameterMap.get("P_BEGIN_DT_S").toString(), "yyyy-MM-dd", "yyyyMMdd")); 
			}
			
			if(!"".equals(parameterMap.getString("P_END_DT_S"))){
				parameterMap.put("P_END_DT_S", FwkFormatUtil.formatDate(parameterMap.get("P_END_DT_S").toString(), "yyyy-MM-dd", "yyyyMMdd")); 
			}
		}
		
		FwkDataEntity user = (FwkDataEntity)parameterMap.get("loginResult");
		
		if("1".equals(user.get("AUTH_ID"))){	//관리자
			//전체조회 가능
		}else if("2".equals(user.get("AUTH_ID"))){	//일반사용자
			parameterMap.put("P_DEPT_NO", user.getString("DEPT_NO"));
		}else if("3".equals(user.get("AUTH_ID"))){	//평가위원순위선정자(감사실)
			parameterMap.put("P_DEPT_NO", user.getString("DEPT_NO"));
		}else if("4".equals(user.get("AUTH_ID"))){	//평가위원담당자(마케팅기획팀)
			parameterMap.put("P_DEPT_NO", user.getString("DEPT_NO"));
		}else{	
			parameterMap.put("P_DEPT_NO", user.getString("DEPT_NO"));
		}
		
		//진행상세
		trans = iproMainLoginFormService.mainEstmList(parameterMap);
		
		model.addAllAttributes(trans);
		
		return JSON_VIEW;
	}
}
