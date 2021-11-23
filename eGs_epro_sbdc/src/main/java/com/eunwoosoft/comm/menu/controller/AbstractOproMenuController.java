package com.eunwoosoft.comm.menu.controller; 

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.eunwoosoft.comm.menu.service.CommMyMenuService;
import com.eunwoosoft.frwk.fol.dto.FwkDataEntity;
import com.eunwoosoft.frwk.fol.dto.FwkTransferObject;
import com.eunwoosoft.frwk.prl.AbstractFwkController;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;
import com.eunwoosoft.opro.masc.service.OproMascMainPageService;

/**
 * <pre>
 * com.eunwoosoft.comm.menu.controller
 *    |_ AbstractOproMenuController.java
 * 
 * </pre>
 * @date : 2018. 05. 28
 * @version : 1.0
 * @author : 은우소프트 맹경열
 */
public abstract class AbstractOproMenuController extends AbstractFwkController {
	private static final Logger LOG = LoggerFactory.getLogger(AbstractOproMenuController.class);
	
	@Resource(name="commMyMenuService")
	private CommMyMenuService commMyMenuService;
	
	@Resource(name="oproMascMainPageService")
	private OproMascMainPageService oproMascMainPageService;
	
	@ModelAttribute(value="myMenuList")
	public HashMap<String, Object> myMenuList(final HttpServletRequest request) throws Exception {
		if(request.getRequestURI().indexOf(".json") > -1 || request.getRequestURI().indexOf("/popup/") > -1) {
			return null;
		}else {
			FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);
			FwkDataEntity user = (FwkDataEntity) parameterMap.get("loginResult");
			if(user != null) {
				parameterMap.put("P_VEND_REG_NO", user.get("USR_ID")); // 세션 사용자아이디	
				parameterMap.put("P_AUTH_ID", user.get("AUTH_ID")); // 세션 사용자아이디	
				
			}else {
			}	
			if (parameterMap.getString("P_AUTH_ID") != null && parameterMap.getString("P_AUTH_ID") != ""){
				parameterMap.put("P_USR_ID", user.get("USR_ID"));
				parameterMap.put("P_USR_SECD", "inner");
			}else{
				parameterMap.put("P_USR_SECD", "opro");
			}
			
			FwkTransferObject trans = commMyMenuService.myMenuListInqire(parameterMap);
			
			List<Object> list = (List<Object>) trans.get(CommMyMenuService.MY_MENU_LIST);
			
			// 리다이렉트 상세화면 이동을 위해 분기처리
			Object obj = RequestContextUtils.getInputFlashMap(request);
			if(obj != null) {
				parameterMap =  (FwkParameterMap) RequestContextUtils.getInputFlashMap(request).get("parameterMap");
				parameterMap.put("requestUrl",((FwkParameterMap)request.getAttribute(PARAMETER_MAP)).get("requestUrl"));
			}else {
				parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
			}
			
			HashMap map = new HashMap<String, Object>();
			map.put("myMenuList", list);
			
			
			if(parameterMap.get("resourceName") == null || ((String)parameterMap.get("resourceName")).equals("")){
				String resourceSession = (String) request.getSession().getAttribute("resourceName");
				if(resourceSession != null && !resourceSession.equals("")){
					parameterMap.put("resourceName", resourceSession);
				}
			}
			
			
			if(parameterMap.get("resourceName") != null && !((String)parameterMap.get("resourceName")).equals("") ) {
				for (int idx = 0; idx < list.size(); idx++) {
					FwkDataEntity fde = (FwkDataEntity) list.get(idx);
					if(parameterMap.get("resourceName").toString().substring(0, 6).equals(fde.get("MENU_ID"))) {
						map.put("bigMenuNm", fde.get("MENU_NM"));
					}
				}
			}

			return map;			
		}
	}
	
	@ModelAttribute(value="myMenuSubList")
	public HashMap<String, Object> myMenuSubList(final HttpServletRequest request) throws Exception {
		if(request.getRequestURI().indexOf(".json") > -1 || request.getRequestURI().indexOf("/popup/") > -1) {
			return null;
		}else {
			FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);
			FwkDataEntity user = (FwkDataEntity) parameterMap.get("loginResult");
			if(user != null) {
				parameterMap.put("P_VEND_REG_NO", user.get("USR_ID")); // 세션 사용자아이디			
				parameterMap.put("P_AUTH_ID", user.get("AUTH_ID")); // 세션 사용자아이디	
			}else {
				
			}			
			if (parameterMap.getString("P_AUTH_ID") != null && parameterMap.getString("P_AUTH_ID") != ""){
				parameterMap.put("P_USR_ID", user.get("USR_ID"));
				parameterMap.put("P_USR_SECD", "inner");
					
			}else{
				parameterMap.put("P_USR_SECD", "opro");
			}
			
			// 리다이렉트 상세화면 이동을 위해 분기처리
			Object obj = RequestContextUtils.getInputFlashMap(request);
			if(obj != null) {
				parameterMap =  (FwkParameterMap) RequestContextUtils.getInputFlashMap(request).get("parameterMap");
				parameterMap.put("requestUrl",((FwkParameterMap)request.getAttribute(PARAMETER_MAP)).get("requestUrl"));
			}else {
				parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
			}
			
			if(parameterMap.get("resourceName") == null || ((String)parameterMap.get("resourceName")).equals("")){
				String resourceSession = (String) request.getSession().getAttribute("resourceName");
				if(resourceSession != null && !resourceSession.equals("")){
					parameterMap.put("resourceName", resourceSession);
				}
			}
			
			HttpSession	session	= request.getSession();
			FwkDataEntity loginSession = (FwkDataEntity) session.getAttribute( "loginResult" );
			if(loginSession != null){
				loginSession.put("resourceName", parameterMap.get("resourceName"));
			}
			
			FwkTransferObject trans = commMyMenuService.myMenuSubListInqire(parameterMap);
			
			List<Object> list = (List<Object>) trans.get(CommMyMenuService.MY_MENU_SUB_LIST);
			
			HashMap map = new HashMap<String, Object>();
			map.put("myMenuSubList", list);
			if(parameterMap.get("resourceName") != null) { 
				for (int idx = 0; idx < list.size(); idx++) {
					FwkDataEntity fde = (FwkDataEntity) list.get(idx);
					if(parameterMap.get("resourceName").equals(fde.get("MENU_ID"))) {
						map.put("smallMenuNm", fde.get("MENU_NM"));		
					}				
				}				
			}
			 
			map.put("resourceName", parameterMap.get("resourceName"));
			return map; 
		}
	}
	
	@ModelAttribute("myMenuLocationList")
	public List<Object> myMenuLocationList(final HttpServletRequest request) throws Exception {		
		return null;
	}
	
	@ModelAttribute("serverTime")
	public String serverTime(final HttpServletRequest request, final Model model) throws Exception {
		if(request.getRequestURI().indexOf("Ajax.json") > -1) {
			return null;
		}else {
			FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);
			return oproMascMainPageService.dbTime(parameterMap);
		}
	}
}
