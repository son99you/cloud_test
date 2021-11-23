package com.eunwoosoft.ipro.ebid.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.eunwoosoft.comm.menu.controller.AbstractIproMenuController;
import com.eunwoosoft.frwk.fol.dto.FwkTransferObject;
import com.eunwoosoft.frwk.prl.AbstractFwkController;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;

/**
 * 기술평가현황
 * com.eunwoosoft.ipro.ebid.controller
 * IproEbidTcevController.java
 *
 * @Author : sanghoon_joen
 * @Date   : 2017. 6. 22.
 *
 */
@RequestMapping(value = "/ebid")
@Controller
public class IproEbidTcevController extends AbstractIproMenuController {
	String prefixUrl = "/ipro/views/ebid/";

	/**
	 * 
	 * <pre>
	 * 1.개요 : 평가계획수립 목록 / 코이카 참조
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_eproDemo.com.eunwoosoft.ipro.ebid.controller.IproEbidTcevController.java
	 * @Method : tchqvlnList
	 * @author : sanghoon_joen
	 * @date : 2017. 6. 22. 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="tchqvlnList")
	public String tchqvlnList(final HttpServletRequest request, final Model model) throws Exception {
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		
		FwkTransferObject trans = new FwkTransferObject();
		model.addAllAttributes(trans);
		
				
		
		return parameterMap.getViewName(prefixUrl);
	}
	
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 평가계획수립 등록폼 / 코이카 참조
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_eproDemo.com.eunwoosoft.ipro.ebid.controller.IproEbidTcevController.java
	 * @Method : tchqvlnRegistForm
	 * @author : sanghoon_joen
	 * @date : 2017. 6. 23. 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="tchqvlnRegistForm")
	public String tchqvlnRegistForm(final HttpServletRequest request, final Model model) throws Exception {
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		
		FwkTransferObject trans = new FwkTransferObject();
		model.addAllAttributes(trans);
		
		
		
		return parameterMap.getViewName(prefixUrl);
	}
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 평가계획수립 수정폼 / 코이카 참조
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_eproDemo.com.eunwoosoft.ipro.ebid.controller.IproEbidTcevController.java
	 * @Method : tchqvlnUpdtForm
	 * @author : sanghoon_joen
	 * @date : 2017. 6. 23. 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="tchqvlnUpdtForm")
	public String tchqvlnUpdtForm(final HttpServletRequest request, final Model model) throws Exception {
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		
		FwkTransferObject trans = new FwkTransferObject();
		model.addAllAttributes(trans);
		
		
		
		return parameterMap.getViewName(prefixUrl);
	}
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 평가계획수립 상세 / 코이카 참조
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_eproDemo.com.eunwoosoft.ipro.ebid.controller.IproEbidTcevController.java
	 * @Method : tchqvlnDetail
	 * @author : sanghoon_joen
	 * @date : 2017. 6. 23. 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="tchqvlnDetail")
	public String tchqvlnDetail(final HttpServletRequest request, final Model model) throws Exception {
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		
		FwkTransferObject trans = new FwkTransferObject();
		model.addAllAttributes(trans);
		
		
		
		return parameterMap.getViewName(prefixUrl);
	}
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 평가표 팝업 / 코이카 참조
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_eproDemo.com.eunwoosoft.ipro.ebid.controller.IproEbidTcevController.java
	 * @Method : evlFormInqire
	 * @author : sanghoon_joen
	 * @date : 2017. 6. 26. 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="evlFormInqire")
	public String evlFormInqire(final HttpServletRequest request, final Model model){
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		
		FwkTransferObject trans = new FwkTransferObject();
		model.addAllAttributes(trans);
		
		return parameterMap.getViewName(prefixUrl);
	}
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 평가상세항목내용조회 팝업 / 코이카 참조
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_eproDemo.com.eunwoosoft.ipro.ebid.controller.IproEbidTcevController.java
	 * @Method : evlFormDtlsCnInqireForm
	 * @author : sanghoon_joen
	 * @date : 2017. 6. 26. 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="evlFormDtlsCnInqireForm")
	public String evlFormDtlsCnInqireForm(final HttpServletRequest request, final Model model){
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		
		FwkTransferObject trans = new FwkTransferObject();
		model.addAllAttributes(trans);
		
		return parameterMap.getViewName(prefixUrl);
	}
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 평가의견서 등록 팝업 / 코이카 참조
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_eproDemo.com.eunwoosoft.ipro.ebid.controller.IproEbidTcevController.java
	 * @Method : evlWrtopnRegistForm
	 * @author : sanghoon_joen
	 * @date : 2017. 6. 26. 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="evlWrtopnRegistForm")
	public String evlWrtopnRegistForm(final HttpServletRequest request, final Model model){
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		
		FwkTransferObject trans = new FwkTransferObject();
		model.addAllAttributes(trans);
		
		return parameterMap.getViewName(prefixUrl);
	}
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 평가위원별총괄표 팝업 / 코이카 참조
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_eproDemo.com.eunwoosoft.ipro.ebid.controller.IproEbidTcevController.java
	 * @Method : mfcmmAcctoSmrizeFormList
	 * @author : sanghoon_joen
	 * @date : 2017. 6. 27. 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/popup/mfcmmAcctoSmrizeFormList")
	public String mfcmmAcctoSmrizeFormList(final HttpServletRequest request, final Model model){
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		
		FwkTransferObject trans = new FwkTransferObject();
		model.addAllAttributes(trans);
		
		return parameterMap.getViewName(prefixUrl);
	}
	
	/**
	 * 
	 * <pre>
	 * 1.개요 :  상임위원 후부군 선별 팝업  / 코이카 참조
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_eproDemo.com.eunwoosoft.ipro.ebid.controller.IproEbidTcevController.java
	 * @Method : mfcmmCndcySelectRegistForm
	 * @author : sanghoon_joen
	 * @date : 2017. 6. 27. 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/popup/mfcmmCndcySelectRegistForm")
	public String mfcmmCndcySelectRegistForm(final HttpServletRequest request, final Model model){
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		
		FwkTransferObject trans = new FwkTransferObject();
		model.addAllAttributes(trans);
		
		return parameterMap.getViewName(prefixUrl);
	}
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 비상임위원 후부군 선별 팝업 / 코이카 참조
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_eproDemo.com.eunwoosoft.ipro.ebid.controller.IproEbidTcevController.java
	 * @Method : nonMfcmmCndcySelectRegistForm
	 * @author : sanghoon_joen
	 * @date : 2017. 6. 27. 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/popup/nonMfcmmCndcySelectRegistForm")
	public String nonMfcmmCndcySelectRegistForm(final HttpServletRequest request, final Model model){
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		
		FwkTransferObject trans = new FwkTransferObject();
		model.addAllAttributes(trans);
		
		return parameterMap.getViewName(prefixUrl);
	}
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 결렬사유 팝업 / 코이카 참조 
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_eproDemo.com.eunwoosoft.ipro.ebid.controller.IproEbidTcevController.java
	 * @Method : brdoResnRegistForm
	 * @author : sanghoon_joen
	 * @date : 2017. 6. 27. 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/popup/brdoResnRegistForm")
	public String brdoResnRegistForm(final HttpServletRequest request, final Model model){
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		
		FwkTransferObject trans = new FwkTransferObject();
		model.addAllAttributes(trans);
		
		return parameterMap.getViewName(prefixUrl);
	}
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 평가표 수정 폼 / 코이카 참조
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_eproDemo.com.eunwoosoft.ipro.ebid.controller.IproEbidTcevController.java
	 * @Method : evlFormUpdtForm
	 * @author : sanghoon_joen
	 * @date : 2017. 6. 28. 
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="evlFormUpdtForm")
	public String evlFormUpdtForm(final HttpServletRequest request, final Model model) throws Exception{
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		
		FwkTransferObject trans = new FwkTransferObject();
		model.addAllAttributes(trans);
		
		
		
		
		return parameterMap.getViewName(prefixUrl);
	}
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 근무이력 조회 / 코이카 참조
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_eproDemo.com.eunwoosoft.ipro.ebid.controller.IproEbidTcevController.java
	 * @Method : workHistInqire
	 * @author : sanghoon_joen
	 * @date : 2017. 6. 28. 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/popup/workHistInqire")
	public String workHistInqire(final HttpServletRequest request, final Model model){
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		
		FwkTransferObject trans = new FwkTransferObject();
		model.addAllAttributes(trans);
		
		return parameterMap.getViewName(prefixUrl);
	}
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 평가항목 저장 폼 / 코이카 참조
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_eproDemo.com.eunwoosoft.ipro.ebid.controller.IproEbidTcevController.java
	 * @Method : evlIemRegistForm
	 * @author : sanghoon_joen
	 * @date : 2017. 6. 28. 
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/popup/evlIemRegistForm")
	public String evlIemRegistForm(final HttpServletRequest request, final Model model) throws Exception{
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		
		FwkTransferObject trans = new FwkTransferObject();
		model.addAllAttributes(trans);
		
		return parameterMap.getViewName(prefixUrl);
	}
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 평가항목 수정 폼 / 코이카 참조
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_eproDemo.com.eunwoosoft.ipro.ebid.controller.IproEbidTcevController.java
	 * @Method : evlIemUpdtForm
	 * @author : sanghoon_joen
	 * @date : 2017. 6. 28. 
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/popup/evlIemUpdtForm")
	public String evlIemUpdtForm(final HttpServletRequest request, final Model model) throws Exception{
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		
		FwkTransferObject trans = new FwkTransferObject();
		model.addAllAttributes(trans);
		
		return parameterMap.getViewName(prefixUrl);
	}
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 평가상세항목 내용등록
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_eproDemo.com.eunwoosoft.ipro.ebid.controller.IproEbidTcevController.java
	 * @Method : evlFormDtlsCnUpdtForm
	 * @author : sanghoon_joen
	 * @date : 2017. 6. 28. 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/popup/evlFormDtlsCnUpdtForm")
	public String evlFormDtlsCnUpdtForm(final HttpServletRequest request, final Model model){
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		
		FwkTransferObject trans = new FwkTransferObject();
		model.addAllAttributes(trans);
		
		return parameterMap.getViewName(prefixUrl);
	}
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 활동이력 조회 / 코이카 참조
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_eproDemo.com.eunwoosoft.ipro.ebid.controller.IproEbidTcevController.java
	 * @Method : actHistInqire
	 * @author : sanghoon_joen
	 * @date : 2017. 6. 28. 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/popup/actHistInqire")
	public String actHistInqire(final HttpServletRequest request, final Model model){
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		
		FwkTransferObject trans = new FwkTransferObject();
		model.addAllAttributes(trans);
		
		return parameterMap.getViewName(prefixUrl);
	}
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 정량평가 목록 / 코이카 참조 
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_eproDemo.com.eunwoosoft.ipro.ebid.controller.IproEbidTcevController.java
	 * @Method : tchqvlnTrgetList
	 * @author : sanghoon_joen
	 * @date : 2017. 6. 29. 
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="tchqvlnTrgetList")
	public String tchqvlnTrgetList(final HttpServletRequest request, final Model model) throws Exception{
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		
		FwkTransferObject trans = new FwkTransferObject();
		model.addAllAttributes(trans);
		
		
		
		
		return parameterMap.getViewName(prefixUrl);
	}
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 정량평가점수 등록 / 코이카참조
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_eproDemo.com.eunwoosoft.ipro.ebid.controller.IproEbidTcevController.java
	 * @Method : fdqntEvlScoreRegistForm
	 * @author : sanghoon_joen
	 * @date : 2017. 6. 29. 
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="fdqntEvlScoreRegistForm")
	public String fdqntEvlScoreRegistForm(final HttpServletRequest request, final Model model) throws Exception{
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		
		FwkTransferObject trans = new FwkTransferObject();
		model.addAllAttributes(trans);
		
		
		
		
		return parameterMap.getViewName(prefixUrl);
	}
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 정량평가점수 상세 / 코이카참조 
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_eproDemo.com.eunwoosoft.ipro.ebid.controller.IproEbidTcevController.java
	 * @Method : fdqntEvlScoreDetail
	 * @author : sanghoon_joen
	 * @date : 2017. 6. 29. 
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="fdqntEvlScoreDetail")
	public String fdqntEvlScoreDetail(final HttpServletRequest request, final Model model) throws Exception{
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		
		FwkTransferObject trans = new FwkTransferObject();
		model.addAllAttributes(trans);
		
		
		
		
		return parameterMap.getViewName(prefixUrl);
	}
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 계약실적 및 제재사항 팝업 / 코이카 참조 
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_eproDemo.com.eunwoosoft.ipro.ebid.controller.IproEbidTcevController.java
	 * @Method : fdqntEvlEntrpsInqire
	 * @author : sanghoon_joen
	 * @date : 2017. 6. 29. 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/popup/fdqntEvlEntrpsInqire")
	public String fdqntEvlEntrpsInqire(final HttpServletRequest request, final Model model){
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		
		FwkTransferObject trans = new FwkTransferObject();
		model.addAllAttributes(trans);
		
		return parameterMap.getViewName(prefixUrl);
	}
	
	/**
	 * 
	 * <pre>
	 * 1.개요 :  
	 * 2.처리내용 : 정성평가 목록 / 코이카 참조
	 * </pre>
	 * @Location : eGs_eproDemo.com.eunwoosoft.ipro.ebid.controller.IproEbidTcevController.java
	 * @Method : tchqvlnTrgetInqire
	 * @author : sanghoon_joen
	 * @date : 2017. 6. 29. 
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="tchqvlnTrgetInqire")
	public String tchqvlnTrgetInqire(final HttpServletRequest request, final Model model) throws Exception{
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		
		FwkTransferObject trans = new FwkTransferObject();
		model.addAllAttributes(trans);
		
		
		
		
		return parameterMap.getViewName(prefixUrl);
	}
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 정성평가 등록 / 코이카 참조 
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_eproDemo.com.eunwoosoft.ipro.ebid.controller.IproEbidTcevController.java
	 * @Method : qualEvlScoreRegistForm
	 * @author : sanghoon_joen
	 * @date : 2017. 6. 29. 
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="qualEvlScoreRegistForm")
	public String qualEvlScoreRegistForm(final HttpServletRequest request, final Model model) throws Exception{
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		
		FwkTransferObject trans = new FwkTransferObject();
		model.addAllAttributes(trans);
		
		
		
		
		return parameterMap.getViewName(prefixUrl);
	}
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 정성평가 상세 / 코이카 참조
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_eproDemo.com.eunwoosoft.ipro.ebid.controller.IproEbidTcevController.java
	 * @Method : qualEvlScoreDetail
	 * @author : sanghoon_joen
	 * @date : 2017. 6. 29. 
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="qualEvlScoreDetail")
	public String qualEvlScoreDetail(final HttpServletRequest request, final Model model) throws Exception{
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		
		FwkTransferObject trans = new FwkTransferObject();
		model.addAllAttributes(trans);
		
		
		
		
		return parameterMap.getViewName(prefixUrl);
	}
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 
	 * 2.처리내용 : 평가등급 부여기준 조회 팝업 / 코이카 참조
	 * </pre>
	 * @Location : eGs_eproDemo.com.eunwoosoft.ipro.ebid.controller.IproEbidTcevController.java
	 * @Method : evlGradAlwncStdrInqire
	 * @author : sanghoon_joen
	 * @date : 2017. 6. 29. 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/popup/evlGradAlwncStdrInqire")
	public String evlGradAlwncStdrInqire(final HttpServletRequest request, final Model model){
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		
		FwkTransferObject trans = new FwkTransferObject();
		model.addAllAttributes(trans);
		
		return parameterMap.getViewName(prefixUrl);
	}
}
