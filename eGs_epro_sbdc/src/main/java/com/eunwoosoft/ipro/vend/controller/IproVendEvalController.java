package com.eunwoosoft.ipro.vend.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.eunwoosoft.comm.menu.controller.AbstractIproMenuController;
import com.eunwoosoft.frwk.fol.dto.FwkTransferObject;
import com.eunwoosoft.frwk.fol.util.FwkDateUtil;
import com.eunwoosoft.frwk.fol.util.FwkMessageUtil;
import com.eunwoosoft.frwk.fol.util.FwkStringUtil;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;
import com.eunwoosoft.ipro.vend.service.IproVendEvalService;

/**
 * 
 * com.eunwoosoft.ipro.vend.controller
 * IproVendEvalController.java
 *
 * @Author : chanil_Hong
 * @Date   : 2017. 6. 15.
 *
 */
@RequestMapping(value = "/vend")
@Controller
public class IproVendEvalController extends AbstractIproMenuController {
	String prefixUrl = "/ipro/views/vend/";
	
	@Resource(name="iproVendEvalService")
	private IproVendEvalService iproVendEvalService;
	
/**
 * 
	 * <pre>
	 * 1.개요 : 평가기준목록 
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_eproDemo.com.eunwoosoft.ipro.vend.controller.IproVendEvalController.java
	 * @Method : vendEvalStndList
	 * @author : chanil_Hong
	 * @date : 2017. 6. 15. 
	 * @param request
	 * @param model
	 * @return
 * @throws Exception 
	 */
	@RequestMapping(value="/vendEvalStndList")
	public String vendEvalStndList(final HttpServletRequest request, final Model model, final HttpServletResponse response) throws Exception{
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		FwkTransferObject trans = new FwkTransferObject();
		
		try{
			trans = iproVendEvalService.vendEvalStndListInqireWithPgng(parameterMap);
		}catch(Exception ex){
			response.setStatus(999);
			throw new Exception(ex.toString());
		}
		
		model.addAllAttributes(trans);
		model.addAllAttributes(parameterMap);
		
		return parameterMap.getViewName(prefixUrl);
	}
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 평가기준등록폼
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri.com.eunwoosoft.ipro.vend.controller.IproVendEvalController.java
	 * @Method : vendEvalStndRegForm
	 * @author : chanil_Hong
	 * @date : 2018. 2. 20. 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/vendEvalStndRegForm2")
	public String vendEvalStndRegForm2(final HttpServletRequest request, final Model model){
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		
		FwkTransferObject trans = new FwkTransferObject();
		model.addAllAttributes(trans);
		//return null;
		return parameterMap.getViewName(prefixUrl);
	}
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 평가기준등록폼
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri.com.eunwoosoft.ipro.vend.controller.IproVendEvalController.java
	 * @Method : vendEvalStndRegForm
	 * @author : chanil_Hong
	 * @date : 2018. 2. 20. 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/vendEvalStndRegForm3")
	public String vendEvalStndRegForm3(final HttpServletRequest request, final Model model){
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		
		FwkTransferObject trans = new FwkTransferObject();
		model.addAllAttributes(trans);
		//return null;
		return parameterMap.getViewName(prefixUrl);
	}
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 평가기준등록폼
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri.com.eunwoosoft.ipro.vend.controller.IproVendEvalController.java
	 * @Method : vendEvalStndRegForm
	 * @author : chanil_Hong
	 * @date : 2018. 2. 20. 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/vendEvalStndRegForm4")
	public String vendEvalStndRegForm4(final HttpServletRequest request, final Model model){
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		
		FwkTransferObject trans = new FwkTransferObject();
		model.addAllAttributes(trans);
		//return null;
		return parameterMap.getViewName(prefixUrl);
	}
	

	
	
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 평가기준등록폼
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri.com.eunwoosoft.ipro.vend.controller.IproVendEvalController.java
	 * @Method : vendEvalStndRegForm
	 * @author : chanil_Hong
	 * @date : 2018. 2. 20. 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/vendEvalStndRegForm")
	public String vendEvalStndRegForm(final HttpServletRequest request, final Model model){
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		
		FwkTransferObject trans = new FwkTransferObject();
		model.addAllAttributes(trans);
		//return null;
		return parameterMap.getViewName(prefixUrl);
	}
	/**
	 * 
	 * <pre>
	 * 1.개요 : 평가기준등록
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri.com.eunwoosoft.ipro.vend.controller.IproVendEvalController.java
	 * @Method : vendEvalStndReg
	 * @author : chanil_Hong
	 * @date : 2018. 2. 20. 
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/vendEvalStndReg")
	public String vendEvalStndReg(final HttpServletRequest request, final Model model, final HttpServletResponse response) throws Exception{
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		
		FwkTransferObject trans = new FwkTransferObject();
		try{
			trans = iproVendEvalService.vendEvalStndRegist(parameterMap);
			model.addAllAttributes(trans);
			model.addAllAttributes(parameterMap);
		}catch(Exception ex){
			response.setStatus(999);
			throw new Exception(ex.toString());
		}
		return JSON_VIEW;
	}
	/**
	 * 
	 * <pre>
	 * 1.개요 : 평기기준 및 평가항목 상세 
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_eproDemo.com.eunwoosoft.ipro.vend.controller.IproVendEvalController.java
	 * @Method : vendEvalStndDetail
	 * @author : chanil_Hong
	 * @date : 2017. 6. 16. 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/vendEvalStndDetail")
	public String vendEvalStndDetail(final HttpServletRequest request, final Model model, final HttpServletResponse response) throws Exception{
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		FwkTransferObject trans = new FwkTransferObject();
		
		try{
			trans = iproVendEvalService.selectVendEvalStndDetail(parameterMap);
			model.addAllAttributes(trans);
		}catch(Exception ex){
			response.setStatus(999);
			throw new Exception(ex.toString());
		}
		
		return parameterMap.getViewName(prefixUrl);
	}
	/**
	 * 
	 * <pre>
	 * 1.개요 : 평기기준상세수정폼
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_eproDemo.com.eunwoosoft.ipro.vend.controller.IproVendEvalController.java
	 * @Method : vendEvalStndModifyForm
	 * @author : chanil_Hong
	 * @date : 2017. 6. 16. 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/vendEvalStndModifyForm")
	public String vendEvalStndModifyForm(final HttpServletRequest request, final Model model, final HttpServletResponse response) throws Exception{
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		
		FwkTransferObject trans = new FwkTransferObject();
		
		try{
			trans = iproVendEvalService.selectVendEvalStndDetail(parameterMap);
			model.addAllAttributes(trans);
		}catch(Exception ex){
			response.setStatus(999);
			throw new Exception(ex.toString());
		}
		return parameterMap.getViewName(prefixUrl);
	}
	/**
	 * 
	 * <pre>
	 * 1.개요 : 평기기준상세수정
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_eproDemo.com.eunwoosoft.ipro.vend.controller.IproVendEvalController.java
	 * @Method : vendEvalStndModify
	 * @author : chanil_Hong
	 * @date : 2017. 6. 16. 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/vendEvalStndModify")
	public String vendEvalStndModify(final HttpServletRequest request, final Model model, final HttpServletResponse response) throws Exception{
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		FwkTransferObject trans = new FwkTransferObject();
		
		try{
			trans = iproVendEvalService.selectVendEvalStndModify(parameterMap);
			model.addAllAttributes(trans);
		}catch(Exception ex){
			response.setStatus(999);
			throw new Exception(ex.toString());
		}
		return JSON_VIEW;
	}
	/**
	 * 
	 * <pre>
	 * 1.개요 : 평기기준 및 항목 삭제
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_eproDemo.com.eunwoosoft.ipro.vend.controller.IproVendEvalController.java
	 * @Method : vendEvalStndDelete
	 * @author : chanil_Hong
	 * @date : 2017. 6. 16. 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/vendEvalStndDelete")
	public String vendEvalStndDelete(final HttpServletRequest request, final Model model, final HttpServletResponse response) throws Exception{
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		FwkTransferObject trans = new FwkTransferObject();
		
		try{
			trans = iproVendEvalService.vendEvalStndDelete(parameterMap);
			model.addAllAttributes(trans);
		}catch(Exception ex){
			response.setStatus(999);
			throw new Exception(ex.toString());
		}
		return JSON_VIEW;
	}
	/**
	 * 
	 * <pre>
	 * 1.개요 : 세부평가기준목록 
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_eproDemo.com.eunwoosoft.ipro.vend.controller.IproVendEvalController.java
	 * @Method : vendDtlEvalStndList
	 * @author : chanil_Hong
	 * @date : 2017. 6. 15. 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/vendDtlEvalStndList")
	public String vendDtlEvalStndList(final HttpServletRequest request, final Model model, final HttpServletResponse response) throws Exception{
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		FwkTransferObject trans = new FwkTransferObject();
		try{
			trans = iproVendEvalService.vendDtlEvalStndListInqireWithPgng(parameterMap);
			model.addAllAttributes(trans);
			model.addAllAttributes(parameterMap);
		}catch(Exception ex){
			response.setStatus(999);
			throw new Exception(ex.toString());
		}
		return parameterMap.getViewName(prefixUrl);
	}
	/**
	 * 
	 * <pre>
	 * 1.개요 : 세부평가기준상세 
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_eproDemo.com.eunwoosoft.ipro.vend.controller.IproVendEvalController.java
	 * @Method : vendDtlEvalStndDetail
	 * @author : chanil_Hong
	 * @date : 2017. 6. 16. 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/vendDtlEvalStndDetail")
	public String vendDtlEvalStndDetail(final HttpServletRequest request, final Model model, final HttpServletResponse response) throws Exception{
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		FwkTransferObject trans = new FwkTransferObject();
		
		try{
			trans = iproVendEvalService.selectVendDtlEvalStndDetail(parameterMap);
			model.addAllAttributes(trans);
		}catch(Exception ex){
			response.setStatus(999);
			throw new Exception(ex.toString());
		}
		
		model.addAllAttributes(trans);
		return parameterMap.getViewName(prefixUrl);
	}
	/**
	 * 
	 * <pre>
	 * 1.개요 : 세부평가기준 수정 폼 
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_eproDemo.com.eunwoosoft.ipro.vend.controller.IproVendEvalController.java
	 * @Method : vendDtlEvalStndModifyForm
	 * @author : chanil_Hong
	 * @date : 2017. 6. 16. 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/vendDtlEvalStndModifyForm")
	public String vendDtlEvalStndModifyForm(final HttpServletRequest request, final Model model, final HttpServletResponse response) throws Exception{
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		FwkTransferObject trans = new FwkTransferObject();
		
		try{
			trans = iproVendEvalService.selectVendDtlEvalStndDetail(parameterMap);
			model.addAllAttributes(trans);
		}catch(Exception ex){
			response.setStatus(999);
			throw new Exception(ex.toString());
		}
		
		return parameterMap.getViewName(prefixUrl);
	}
	/**
	 * 
	 * <pre>
	 * 1.개요 : 세부평가기준 수정 
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_eproDemo.com.eunwoosoft.ipro.vend.controller.IproVendEvalController.java
	 * @Method : vendDtlEvalStndModify
	 * @author : chanil_Hong
	 * @date : 2017. 6. 16. 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/vendDtlEvalStndModify")
	public String vendDtlEvalStndModify(final HttpServletRequest request, final Model model, final HttpServletResponse response) throws Exception{
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		FwkTransferObject trans = new FwkTransferObject();
		
		try{
			trans = iproVendEvalService.vendDtlEvalStndModify(parameterMap);
			model.addAllAttributes(trans);
		}catch(Exception ex){
			response.setStatus(999);
			throw new Exception(ex.toString());
		}
		
		return JSON_VIEW;
	}
	/**
	 * 
	 * <pre>
	 * 1.개요 : 세부평가기준 삭제 
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_eproDemo.com.eunwoosoft.ipro.vend.controller.IproVendEvalController.java
	 * @Method : vendDtlEvalStndDelete
	 * @author : chanil_Hong
	 * @date : 2017. 6. 16. 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/vendDtlEvalStndDelete")
	public String vendDtlEvalStndDelete(final HttpServletRequest request, final Model model, final HttpServletResponse response) throws Exception{
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		FwkTransferObject trans = new FwkTransferObject();
		
		try{
			trans = iproVendEvalService.vendDtlEvalStndDelete(parameterMap);
			model.addAllAttributes(trans);
		}catch(Exception ex){
			response.setStatus(999);
			throw new Exception(ex.toString());
		}
		
		return JSON_VIEW;
	}
	/**
	 * 
	 * <pre>
	 * 1.개요 : 유관부서평가기준 목록
	 * 2.처리내용 : vendEvalDeptStndList.jsp
	 * </pre>
	 * @Location : eGs_eproDemo.com.eunwoosoft.ipro.vend.controller.IproVendEvalController.java
	 * @Method : vendEvalDeptStndList
	 * @author : chanil_Hong
	 * @date : 2017. 6. 19. 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/vendEvalDeptStndList")
	public String vendEvalDeptStndList(final HttpServletRequest request, final Model model, final HttpServletResponse response) throws Exception{
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		FwkTransferObject trans = new FwkTransferObject();
		
		try{
			trans = iproVendEvalService.vendEvalDeptStndListInqireWithPgng(parameterMap);
			model.addAllAttributes(trans);
			model.addAllAttributes(parameterMap);
		}catch(Exception ex){
			response.setStatus(999);
			throw new Exception(ex.toString());
		}
		
		return parameterMap.getViewName(prefixUrl);
	}
	/**
	 * 
	 * <pre>
	 * 1.개요 : 유관부서평가기준 상세
	 * 2.처리내용 : vendEvalDeptStndDetail.jsp
	 * </pre>
	 * @Location : eGs_eproDemo.com.eunwoosoft.ipro.vend.controller.IproVendEvalController.java
	 * @Method : vendEvalDeptStndDetail
	 * @author : chanil_Hong
	 * @date : 2017. 6. 19. 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/vendEvalDeptStndDetail")
	public String vendEvalDeptStndDetail(final HttpServletRequest request, final Model model, final HttpServletResponse response) throws Exception{
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		FwkTransferObject trans = new FwkTransferObject();
		
		try{
			trans = iproVendEvalService.selectVendEvalDeptStndDetail(parameterMap);
			model.addAllAttributes(trans);
		}catch(Exception ex){
			response.setStatus(999);
			throw new Exception(ex.toString());
		}
		
		return parameterMap.getViewName(prefixUrl);
	}
	/**
	 * 
	 * <pre>
	 * 1.개요 : 유관부서평가기준 추가 폼
	 * 2.처리내용 : vendEvalDeptStndRegForm
	 * </pre>
	 * @Location : eGs_eproDemo.com.eunwoosoft.ipro.vend.controller.IproVendEvalController.java
	 * @Method : vendEvalDeptStndRegForm
	 * @author : chanil_Hong
	 * @date : 2017. 6. 19. 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/vendEvalDeptStndRegForm")
	public String vendEvalDeptStndRegForm(final HttpServletRequest request, final Model model, final HttpServletResponse response) throws Exception{
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		FwkTransferObject trans = new FwkTransferObject();
		try{
			model.addAllAttributes(trans);
		}catch(Exception ex){
			response.setStatus(999);
			throw new Exception(ex.toString());
		}
		return parameterMap.getViewName(prefixUrl);
	}
	/**
	 * 
	 * <pre>
	 * 1.개요 : 유관부서평가기준 추가
	 * 2.처리내용 : vendEvalDeptStndReg
	 * </pre>
	 * @Location : eGs_eproDemo.com.eunwoosoft.ipro.vend.controller.IproVendEvalController.java
	 * @Method : vendEvalDeptStndReg
	 * @author : chanil_Hong
	 * @date : 2017. 6. 19. 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/vendEvalDeptStndRegist")
	public String vendEvalDeptStndRegist(final HttpServletRequest request, final Model model, final HttpServletResponse response) throws Exception{
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		FwkTransferObject trans = new FwkTransferObject();
		try{
			trans = iproVendEvalService.vendEvalDeptStndRegist(parameterMap);
			model.addAllAttributes(trans);
			model.addAllAttributes(parameterMap);
		}catch(Exception ex){
			response.setStatus(999);
			throw new Exception(ex.toString());
		}
		return JSON_VIEW;
	}
	/**
	 * 
	 * <pre>
	 * 1.개요 : 유관부서평가기준 상세수정폼
	 * 2.처리내용 : vendEvalDeptStndModifyForm
	 * </pre>
	 * @Location : eGs_eproDemo.com.eunwoosoft.ipro.vend.controller.IproVendEvalController.java
	 * @Method : vendEvalDeptStndModifyForm
	 * @author : chanil_Hong
	 * @date : 2017. 6. 19. 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/vendEvalDeptStndModifyForm")
	public String vendEvalDeptStndModifyForm(final HttpServletRequest request, final Model model, final HttpServletResponse response) throws Exception{
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		FwkTransferObject trans = new FwkTransferObject();

		try{
			trans = iproVendEvalService.selectVendEvalDeptStndDetail(parameterMap);
			model.addAllAttributes(trans);
		}catch(Exception ex){
			response.setStatus(999);
			throw new Exception(ex.toString());
		}
		
		return parameterMap.getViewName(prefixUrl);
	}
	/**
	 * 
	 * <pre>
	 * 1.개요 : 유관부서평가기준 상세수정
	 * 2.처리내용 : vendEvalDeptStndModify
	 * </pre>
	 * @Location : eGs_eproDemo.com.eunwoosoft.ipro.vend.controller.IproVendEvalController.java
	 * @Method : vendEvalDeptStndModifyForm
	 * @author : chanil_Hong
	 * @date : 2017. 6. 19. 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/vendEvalDeptStndModify")
	public String vendEvalDeptStndModify(final HttpServletRequest request, final Model model, final HttpServletResponse response) throws Exception{
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		FwkTransferObject trans = new FwkTransferObject();
		
		try{
			trans = iproVendEvalService.vendEvalDeptStndModify(parameterMap);
			model.addAllAttributes(trans);
		}catch(Exception ex){
			response.setStatus(999);
			throw new Exception(ex.toString());
		}
		
		return JSON_VIEW;
	}
	/**
	 * 
	 * <pre>
	 * 1.개요 : 유관부서평가기준 및 항목 삭제
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_eproDemo.com.eunwoosoft.ipro.vend.controller.IproVendEvalController.java
	 * @Method : vendEvalDeptStndDelete
	 * @author : chanil_Hong
	 * @date : 2017. 6. 16. 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/vendEvalDeptStndDelete")
	public String vendEvalDeptStndDelete(final HttpServletRequest request, final Model model, final HttpServletResponse response) throws Exception{
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		FwkTransferObject trans = new FwkTransferObject();
		
		try{
			trans = iproVendEvalService.vendEvalDeptStndDelete(parameterMap);
			model.addAllAttributes(trans);
		}catch(Exception ex){
			response.setStatus(999);
			throw new Exception(ex.toString());
		}
		return JSON_VIEW;
	}
	/**
	 * 
	 * <pre>
	 * 1.개요 : 평가종합관리 목록
	 * 2.처리내용 : vendEvalList.jsp
	 * </pre>
	 * @Location : eGs_eproDemo.com.eunwoosoft.ipro.vend.controller.IproVendEvalController.java
	 * @Method : vendEvalList
	 * @author : chanil_Hong
	 * @date : 2017. 6. 19. 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/vendEvalList")
	public String vendEvalList(final HttpServletRequest request, final Model model, final HttpServletResponse response) throws Exception{
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		FwkTransferObject trans = new FwkTransferObject();
		
		try{
			trans = iproVendEvalService.vendEvalListInqireWithPgng(parameterMap);
			model.addAllAttributes(trans);
			model.addAllAttributes(parameterMap);
		}catch(Exception ex){
			response.setStatus(999);
			throw new Exception(ex.toString());
		}
		
		return parameterMap.getViewName(prefixUrl);
	}
	/**
	 * 
	 * <pre>
	 * 1.개요 : 평가종합관리 상세
	 * 2.처리내용 : vendEvalDetail.jsp
	 * </pre>
	 * @Location : eGs_eproDemo.com.eunwoosoft.ipro.vend.controller.IproVendEvalController.java
	 * @Method : vendEvalDetail
	 * @author : chanil_Hong
	 * @date : 2017. 6. 19. 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/vendEvalDetail")
	public String vendEvalDetail(final HttpServletRequest request, final Model model, final HttpServletResponse response) throws Exception{
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		FwkTransferObject trans = new FwkTransferObject();
		
		try{
			trans = iproVendEvalService.selectVendEvalDetail(parameterMap);
			model.addAllAttributes(trans);
			model.addAllAttributes(parameterMap);
		}catch(Exception ex){
			response.setStatus(999);
			throw new Exception(ex.toString());
		}
		
		return parameterMap.getViewName(prefixUrl);
	}
	/**
	 * 
	 * <pre>
	 * 1.개요 : 평가종합관리 평가분야상세
	 * 2.처리내용 : vendEvalView.jsp
	 * </pre>
	 * @Location : eGs_eproDemo.com.eunwoosoft.ipro.vend.controller.IproVendEvalController.java
	 * @Method : vendEvalView
	 * @author : chanil_Hong
	 * @date : 2017. 6. 19. 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/vendEvalView")
	public String vendEvalView(final HttpServletRequest request, final Model model, final HttpServletResponse response) throws Exception{
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		FwkTransferObject trans = new FwkTransferObject();
		
		try{
			trans = iproVendEvalService.selectVendEvalView(parameterMap);
			model.addAllAttributes(trans);
		}catch(Exception ex){
			response.setStatus(999);
			throw new Exception(ex.toString());
		}
		
		//return null;
		return parameterMap.getViewName(prefixUrl);
	}
	/**
	 * 
	 * <pre>
	 * 1.개요 : 평가종합관리 평가분야상세 수정 폼
	 * 2.처리내용 : vendEvalModifyForm
	 * </pre>
	 * @Location : eGs_eproDemo.com.eunwoosoft.ipro.vend.controller.IproVendEvalController.java
	 * @Method : vendEvalView
	 * @author : chanil_Hong
	 * @date : 2017. 6. 19. 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/vendEvalModifyForm")
	public String vendEvalModifyForm(final HttpServletRequest request, final Model model, final HttpServletResponse response) throws Exception{
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		FwkTransferObject trans = new FwkTransferObject();
		
		try{
			trans = iproVendEvalService.selectVendEvalView(parameterMap);
			model.addAllAttributes(trans);
		}catch(Exception ex){
			response.setStatus(999);
			throw new Exception(ex.toString());
		}
		
		//return null;
		return parameterMap.getViewName(prefixUrl);
	}
	/**
	 * 
	 * <pre>
	 * 1.개요 : 평가종합관리 평가분야상세 수정
	 * 2.처리내용 : vendEvalModify
	 * </pre>
	 * @Location : eGs_eproDemo.com.eunwoosoft.ipro.vend.controller.IproVendEvalController.java
	 * @Method : vendEvalModify
	 * @author : chanil_Hong
	 * @date : 2017. 6. 19. 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/vendEvalModify")
	public String vendEvalModify(final HttpServletRequest request, final Model model, final HttpServletResponse response) throws Exception{
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		FwkTransferObject trans = new FwkTransferObject();
		
		try{
			trans = iproVendEvalService.vendEvalModify(parameterMap);
			model.addAllAttributes(trans);
			model.addAllAttributes(parameterMap);
		}catch(Exception ex){
			response.setStatus(999);
			throw new Exception(ex.toString());
		}
		
		//return null;
		return JSON_VIEW;
	}
	/**
	 * 
	 * <pre>
	 * 1.개요 : 평가종합관리 상세 -> 평가수행
	 * 2.처리내용 : vendEvalDetailEvalView
	 * </pre>
	 * @Location : eGs_eproDemo.com.eunwoosoft.ipro.vend.controller.IproVendEvalController.java
	 * @Method : vendEvalDetailEvalView
	 * @author : chanil_Hong
	 * @date : 2017. 6. 19. 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/vendEvalDetailEvalView")
	public String vendEvalDetailEvalView(final HttpServletRequest request, final Model model, final HttpServletResponse response) throws Exception{
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		FwkTransferObject trans = new FwkTransferObject();
		
		try{
			trans = iproVendEvalService.vendEvalDetailEvalView(parameterMap);
			model.addAllAttributes(trans);
			model.addAllAttributes(parameterMap);
		}catch(Exception ex){
			response.setStatus(999);
			throw new Exception(ex.toString());
		}
		
		return parameterMap.getViewName(prefixUrl);
	}
	/**
	 * 
	 * <pre>
	 * 1.개요 : 평가종합관리 상세 -> 평가수행저장
	 * 2.처리내용 : vendEvalDetailEvalSave
	 * </pre>
	 * @Location : eGs_eproDemo.com.eunwoosoft.ipro.vend.controller.IproVendEvalController.java
	 * @Method : vendEvalDetailEvalView
	 * @author : chanil_Hong
	 * @date : 2017. 6. 19. 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/vendEvalDetailEvalSave")
	public String vendEvalDetailEvalSave(final HttpServletRequest request, final Model model, final HttpServletResponse response) throws Exception{
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		FwkTransferObject trans = new FwkTransferObject();
		try{
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request; 
			
			List<MultipartFile> fileInfoList = multipartRequest.getFiles("P_FILE");
			String url = "";
			
			List<Map<String, Object>> fileList = new ArrayList<Map<String,Object>>();
			System.err.println("fileInfoList.size()::"+fileInfoList.size());
			for (int i = 0; i < fileInfoList.size(); i++) {
				MultipartFile file = fileInfoList.get(i);
				if(file.getSize() > 0 ){
					String streFileNm = FwkStringUtil.replace(UUID.randomUUID().toString(), "-", "");
					String orgFileName = file.getOriginalFilename();
					String baseDir = FwkMessageUtil.getMessage("EW.SYS.FILE.DIR");
					String dir = File.separator+FwkDateUtil.getCurrentDateAsString("yyyy") + File.separator+FwkDateUtil.getCurrentDateAsString("MM")+
							File.separator+FwkDateUtil.getCurrentDateAsString("dd") + 	File.separator;
					String fileFullPath = "";
					String name = "";
					String ext = "";  
					
					int index = orgFileName.lastIndexOf(".");
					if (index != -1) {
						name = orgFileName.substring(0, index);
						ext = orgFileName.substring(index + 1);
					}
					
					File f = new File(baseDir + dir);		
					
					boolean mkdirSuccess = true;
					if(!f.isDirectory()){
						f.setExecutable(true);
						f.setReadable(true);
						f.setWritable(true);
						mkdirSuccess = f.mkdirs();
					}
					
					fileFullPath = baseDir + dir + File.separator + streFileNm +"." + ext;
					
					if(mkdirSuccess){
						InputStream	stream		= null;
						stream	= file.getInputStream();
						OutputStream	bos	= new FileOutputStream( fileFullPath );
						int	byteread	= 0;
						byte[]	buffer	= new byte[8192];
						while( ( byteread = stream.read( buffer, 0, 8192 ) ) != -1 ) {
							bos.write(buffer, 0, byteread );
						}
						bos.close();
						stream.close();
						FwkParameterMap pmap = new FwkParameterMap();
						pmap.put("P_FILE_CPCTY",  file.getSize());
						pmap.put("P_STRE_FILE_NM",  streFileNm + "." + ext);
						pmap.put("P_ATCHMNFL_NM",   orgFileName);	
						pmap.put("P_ATCHMNFL_COURS_NM", baseDir + dir);
						pmap.put("P_ATCHMNFL_EXTSN_NM",  "application/octet-stream");
						fileList.add(pmap);
					}else{
						trans.put("resultCode", "ERR");
						trans.put("msg", "시스템 오류 입니다.");
						trans.put("sMsg", "폴더가 생성되지 않습니다.");
						model.addAllAttributes(trans);
						return url;
					}
				}
			}
			parameterMap.put("P_ATCH_NEW", "Y");
			//첨부파일(일반첨부파일)
			String atchFileGroupNo = "";
			if(parameterMap.get("P_ATCHMNFL_GROUP_NO") != null && !"".equals(parameterMap.get("P_ATCHMNFL_GROUP_NO"))) {
				parameterMap.put("saveYn", "Y"); 
			}else{
				parameterMap.put("P_ATCHMNFL_GROUP_NO", FwkStringUtil.replace(UUID.randomUUID().toString(), "-", ""));  // 그룹번호 생성
				parameterMap.put("saveYn", "N");
			}
			if(fileList.size() > 0){
				parameterMap.put("fileList", fileList); 
			}
			trans = iproVendEvalService.vendEvalDetailEvalSave(parameterMap);
			model.addAllAttributes(trans);
		}catch(Exception ex){
			response.setStatus(999);
			throw new Exception(ex.toString());
		}
		
		return "forward:/vend/vendEvalDetail.do";
	}
	/**
	 * 
	 * <pre>
	 * 1.개요 : 평가종합관리 신규등록 폼
	 * 2.처리내용 : vendEvalRegForm
	 * </pre>
	 * @Location : eGs_eproDemo.com.eunwoosoft.ipro.vend.controller.IproVendEvalController.java
	 * @Method : vendEvalRegForm
	 * @author : chanil_Hong
	 * @date : 2017. 6. 19. 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/vendEvalRegForm")
	public String vendEvalRegForm(final HttpServletRequest request, final Model model, final HttpServletResponse response) throws Exception{
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		FwkTransferObject trans = new FwkTransferObject();
		
		try{
			trans = iproVendEvalService.selectTEvCode1List(parameterMap);
			model.addAllAttributes(trans);
			model.addAllAttributes(parameterMap);
		}catch(Exception ex){
			response.setStatus(999);
			throw new Exception(ex.toString());
		}
		return parameterMap.getViewName(prefixUrl);
	}
	/**
	 * 
	 * <pre>
	 * 1.개요 : 평가종합관리 신규등록 
	 * 2.처리내용 : vendEvalRegist
	 * </pre>
	 * @Location : eGs_eproDemo.com.eunwoosoft.ipro.vend.controller.IproVendEvalController.java
	 * @Method : vendEvalRegist
	 * @author : chanil_Hong
	 * @date : 2017. 6. 19. 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/vendEvalRegist")
	public String vendEvalRegist(final HttpServletRequest request, final Model model, final HttpServletResponse response) throws Exception{
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		FwkTransferObject trans = new FwkTransferObject();
		
		try{
			trans = iproVendEvalService.vendEvalRegist(parameterMap);
			model.addAllAttributes(trans);
			model.addAllAttributes(parameterMap);
		}catch(Exception ex){
			response.setStatus(999);
			throw new Exception(ex.toString());
		}
		return JSON_VIEW;
	}
	/**
	 * 
	 * <pre>
	 * 1.개요 : 평가진행
	 * 2.처리내용 : vendEvalProceed
	 * </pre>
	 * @Location : eGs_eproDemo.com.eunwoosoft.ipro.vend.controller.IproVendEvalController.java
	 * @Method : vendEvalResultDetail
	 * @author : chanil_Hong
	 * @date : 2017. 6. 19. 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/vendEvalProceed")
	public String vendEvalProceed(final HttpServletRequest request, final Model model, final HttpServletResponse response) throws Exception{
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		FwkTransferObject trans = new FwkTransferObject();
		
		try{
			trans = iproVendEvalService.vendEvalProceed(parameterMap);
			model.addAllAttributes(trans);
			model.addAllAttributes(parameterMap);
		}catch(Exception ex){
			response.setStatus(999);
			throw new Exception(ex.toString());
		}
		return "forward:/vend/vendEvalDetail.do";
		
	}
	/**
	 * 
	 * <pre>
	 * 1.개요 : 유관부서평가 목록
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_eproDemo.com.eunwoosoft.ipro.vend.controller.IproVendEvalController.java
	 * @Method : vendEvalDeptList
	 * @author : chanil_Hong
	 * @date : 2017. 6. 19. 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/vendEvalDeptList")
	public String vendEvalDeptList(final HttpServletRequest request, final Model model, final HttpServletResponse response) throws Exception{
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		FwkTransferObject trans = new FwkTransferObject();
		
		try{
			trans = iproVendEvalService.vendEvalDeptList(parameterMap);
			model.addAllAttributes(trans);
			model.addAllAttributes(parameterMap);
		}catch(Exception ex){
			response.setStatus(999);
			throw new Exception(ex.toString());
		}
		return parameterMap.getViewName(prefixUrl);
	}
	/**
	 * 
	 * <pre>
	 * 1.개요 : 유관부서평가 상세
	 * 2.처리내용 : vendEvalDeptDetail.jsp
	 * </pre>
	 * @Location : eGs_eproDemo.com.eunwoosoft.ipro.vend.controller.IproVendEvalController.java
	 * @Method : vendEvalDeptDetail
	 * @author : chanil_Hong
	 * @date : 2017. 6. 19. 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/vendEvalDeptDetail")
	public String vendEvalDeptDetail(final HttpServletRequest request, final Model model, final HttpServletResponse response) throws Exception{
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		FwkTransferObject trans = new FwkTransferObject();
		
		try{
			trans = iproVendEvalService.vendEvalDeptDetail(parameterMap);
			model.addAllAttributes(trans);
			model.addAllAttributes(parameterMap);
		}catch(Exception ex){
			response.setStatus(999);
			throw new Exception(ex.toString());
		}
		return parameterMap.getViewName(prefixUrl);
	}
	/**
	 * 
	 * <pre>
	 * 1.개요 : 유관부서평가 업체상세 평가수행
	 * 2.처리내용 : vendEvalDeptEvalView
	 * </pre>
	 * @Location : eGs_eproDemo.com.eunwoosoft.ipro.vend.controller.IproVendEvalController.java
	 * @Method : vendEvalDeptEvalView
	 * @author : chanil_Hong
	 * @date : 2017. 6. 19. 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/vendEvalDeptEvalView")
	public String vendEvalDeptEvalView(final HttpServletRequest request, final Model model, final HttpServletResponse response) throws Exception{
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		FwkTransferObject trans = new FwkTransferObject();
		
		try{
			trans = iproVendEvalService.vendEvalDeptEvalView(parameterMap);
			model.addAllAttributes(trans);
			model.addAllAttributes(parameterMap);
		}catch(Exception ex){
			response.setStatus(999);
			throw new Exception(ex.toString());
		}
		return parameterMap.getViewName(prefixUrl);
	}
	/**
	 * 
	 * <pre>
	 * 1.개요 : 유관부서평가 업체상세 평가수행 저장
	 * 2.처리내용 : vendEvalDeptInsert
	 * </pre>
	 * @Location : eGs_eproDemo.com.eunwoosoft.ipro.vend.controller.IproVendEvalController.java
	 * @Method : vendEvalDeptInsert
	 * @author : chanil_Hong
	 * @date : 2017. 6. 19. 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/vendEvalDeptInsert")
	public String vendEvalDeptInsert(final HttpServletRequest request, final Model model, final HttpServletResponse response) throws Exception{
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		FwkTransferObject trans = new FwkTransferObject();
		
		try{
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request; 
			
			List<MultipartFile> fileInfoList = multipartRequest.getFiles("P_FILE");
			String url = "";
			
			List<Map<String, Object>> fileList = new ArrayList<Map<String,Object>>();
			System.err.println("fileInfoList.size()::"+fileInfoList.size());
			for (int i = 0; i < fileInfoList.size(); i++) {
				MultipartFile file = fileInfoList.get(i);
				if(file.getSize() > 0 ){
					String streFileNm = FwkStringUtil.replace(UUID.randomUUID().toString(), "-", "");
					String orgFileName = file.getOriginalFilename();
					String baseDir = FwkMessageUtil.getMessage("EW.SYS.FILE.DIR");
					String dir = File.separator+FwkDateUtil.getCurrentDateAsString("yyyy") + File.separator+FwkDateUtil.getCurrentDateAsString("MM")+
							File.separator+FwkDateUtil.getCurrentDateAsString("dd") + 	File.separator;
					String fileFullPath = "";
					String name = "";
					String ext = "";  
					
					int index = orgFileName.lastIndexOf(".");
					if (index != -1) {
						name = orgFileName.substring(0, index);
						ext = orgFileName.substring(index + 1);
					}
					
					File f = new File(baseDir + dir);		
					
					boolean mkdirSuccess = true;
					if(!f.isDirectory()){
						f.setExecutable(true);
						f.setReadable(true);
						f.setWritable(true);
						mkdirSuccess = f.mkdirs();
					}
					
					fileFullPath = baseDir + dir + File.separator + streFileNm +"." + ext;
					
					if(mkdirSuccess){
						InputStream	stream		= null;
						stream	= file.getInputStream();
						OutputStream	bos	= new FileOutputStream( fileFullPath );
						int	byteread	= 0;
						byte[]	buffer	= new byte[8192];
						while( ( byteread = stream.read( buffer, 0, 8192 ) ) != -1 ) {
							bos.write(buffer, 0, byteread );
						}
						bos.close();
						stream.close();
						FwkParameterMap pmap = new FwkParameterMap();
						pmap.put("P_FILE_CPCTY",  file.getSize());
						pmap.put("P_STRE_FILE_NM",  streFileNm + "." + ext);
						pmap.put("P_ATCHMNFL_NM",   orgFileName);	
						pmap.put("P_ATCHMNFL_COURS_NM", baseDir + dir);
						pmap.put("P_ATCHMNFL_EXTSN_NM",  "application/octet-stream");
						fileList.add(pmap);
					}else{
						trans.put("resultCode", "ERR");
						trans.put("msg", "시스템 오류 입니다.");
						trans.put("sMsg", "폴더가 생성되지 않습니다.");
						model.addAllAttributes(trans);
						return url;
					}
				}
			}
			parameterMap.put("P_ATCH_NEW", "Y");
			//첨부파일(일반첨부파일)
			String atchFileGroupNo = "";
			if(parameterMap.get("P_ATCHMNFL_GROUP_NO") != null && !"".equals(parameterMap.get("P_ATCHMNFL_GROUP_NO"))) {
				parameterMap.put("saveYn", "Y"); 
			}else{
				parameterMap.put("P_ATCHMNFL_GROUP_NO", FwkStringUtil.replace(UUID.randomUUID().toString(), "-", ""));  // 그룹번호 생성
				parameterMap.put("saveYn", "N");
			}
			if(fileList.size() > 0){
				parameterMap.put("fileList", fileList); 
			}
			trans = iproVendEvalService.vendEvalDeptInsert(parameterMap);
			model.addAllAttributes(trans);
			model.addAllAttributes(parameterMap);
		}catch(Exception ex){
			response.setStatus(999);
			throw new Exception(ex.toString());
		}
		return "forward:/vend/vendEvalDeptDetail.do";
	}
	/**
	 * 
	 * <pre>
	 * 1.개요 : 유관부서평가 상세 -> 평가수행
	 * 2.처리내용 : vendEvalDeptDetailEvalView.jsp
	 * </pre>
	 * @Location : eGs_eproDemo.com.eunwoosoft.ipro.vend.controller.IproVendEvalController.java
	 * @Method : vendEvalDeptDetailEvalView
	 * @author : chanil_Hong
	 * @date : 2017. 6. 19. 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/vendEvalDeptDetailEvalView")
	public String vendEvalDeptDetailEvalView(final HttpServletRequest request, final Model model){
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		
		FwkTransferObject trans = new FwkTransferObject();
		model.addAllAttributes(trans);
		//return null;
		return parameterMap.getViewName(prefixUrl);
	}
	/**
	 * 
	 * <pre>
	 * 1.개요 : 안전환경평가 상세
	 * 2.처리내용 : vendEvalSafeDetail.jsp
	 * </pre>
	 * @Location : eGs_eproDemo.com.eunwoosoft.ipro.vend.controller.IproVendEvalController.java
	 * @Method : vendEvalSafeDetail
	 * @author : chanil_Hong
	 * @date : 2017. 6. 19. 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/vendEvalSafeDetail")
	public String vendEvalSafeDetail(final HttpServletRequest request, final Model model){
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		
		FwkTransferObject trans = new FwkTransferObject();
		model.addAllAttributes(trans);
		//return null;
		return parameterMap.getViewName(prefixUrl);
	}
	/**
	 * 
	 * <pre>
	 * 1.개요 : 안전환경평가 상세 -> 평가수행
	 * 2.처리내용 : vendEvalSafeDetailEvalView.jsp
	 * </pre>
	 * @Location : eGs_eproDemo.com.eunwoosoft.ipro.vend.controller.IproVendEvalController.java
	 * @Method : vendEvalSafeDetailEvalView
	 * @author : chanil_Hong
	 * @date : 2017. 6. 19. 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/vendEvalSafeDetailEvalView")
	public String vendEvalSafeDetailEvalView(final HttpServletRequest request, final Model model){
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		
		FwkTransferObject trans = new FwkTransferObject();
		model.addAllAttributes(trans);
		//return null;
		return parameterMap.getViewName(prefixUrl);
	}
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 평가결과 목록 
	 * 2.처리내용 : vendEvalResultList.jsp
	 * </pre>
	 * @Location : eGs_eproDemo.com.eunwoosoft.ipro.vend.controller.IproVendEvalController.java
	 * @Method : vendEvalResultList
	 * @author : chanil_Hong
	 * @date : 2017. 6. 19. 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/vendEvalResultList")
	public String vendEvalResultList(final HttpServletRequest request, final Model model, final HttpServletResponse response) throws Exception{
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		FwkTransferObject trans = new FwkTransferObject();
		
		try{
			trans = iproVendEvalService.vendEvalResultList(parameterMap);
			model.addAllAttributes(trans);
			model.addAllAttributes(parameterMap);
		}catch(Exception ex){
			response.setStatus(999);
			throw new Exception(ex.toString());
		}
		return parameterMap.getViewName(prefixUrl);
	}
	/**
	 * 
	 * <pre>
	 * 1.개요 : 평가결과 상세
	 * 2.처리내용 : vendEvalResultDetail.jsp
	 * </pre>
	 * @Location : eGs_eproDemo.com.eunwoosoft.ipro.vend.controller.IproVendEvalController.java
	 * @Method : vendEvalResultDetail
	 * @author : chanil_Hong
	 * @date : 2017. 6. 19. 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/vendEvalResultDetail")
	public String vendEvalResultDetail(final HttpServletRequest request, final Model model, final HttpServletResponse response) throws Exception{
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		FwkTransferObject trans = new FwkTransferObject();
		
		try{
			trans = iproVendEvalService.vendEvalResultDetail(parameterMap);
			model.addAllAttributes(trans);
			model.addAllAttributes(parameterMap);
		}catch(Exception ex){
			response.setStatus(999);
			throw new Exception(ex.toString());
		}
		return parameterMap.getViewName(prefixUrl);
	}
	/**
	 * 
	 * <pre>
	 * 1.개요 : 상반기현업평가완료
	 * 2.처리내용 : vendEvalComplate
	 * </pre>
	 * @Location : eGs_eproDemo.com.eunwoosoft.ipro.vend.controller.IproVendEvalController.java
	 * @Method : vendEvalComplate
	 * @author : chanil_Hong
	 * @date : 2017. 6. 19. 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/vendEvalComplate")
	public String vendEvalComplate(final HttpServletRequest request, final Model model, final HttpServletResponse response) throws Exception{
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		FwkTransferObject trans = new FwkTransferObject();
		
		try{
			trans = iproVendEvalService.vendEvalComplate(parameterMap);
			model.addAllAttributes(trans);
			model.addAllAttributes(parameterMap);
		}catch(Exception ex){
			response.setStatus(999);
			throw new Exception(ex.toString());
		}
		return "forward:/vend/vendEvalDetail.do";
	}
	/**
	 * 
	 * <pre>
	 * 1.개요 : 상반기현업평가완료
	 * 2.처리내용 : vendEvalVenComp2Add
	 * </pre>
	 * @Location : eGs_eproDemo.com.eunwoosoft.ipro.vend.controller.IproVendEvalController.java
	 * @Method : vendEvalVenComp2Add
	 * @author : chanil_Hong
	 * @date : 2017. 6. 19. 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/vendEvalVenComp2Add")
	public String vendEvalVenComp2Add(final HttpServletRequest request, final Model model, final HttpServletResponse response) throws Exception{
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		FwkTransferObject trans = new FwkTransferObject();
		
		try{
			trans = iproVendEvalService.vendEvalVenComp2Add(parameterMap);
			model.addAllAttributes(trans);
			model.addAllAttributes(parameterMap);
		}catch(Exception ex){
			response.setStatus(999);
			throw new Exception(ex.toString());
		}
		return "forward:/vend/vendEvalDetail.do";
	}
}
