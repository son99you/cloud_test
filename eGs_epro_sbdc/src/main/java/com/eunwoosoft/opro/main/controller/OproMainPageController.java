package com.eunwoosoft.opro.main.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Calendar;
import java.util.zip.GZIPOutputStream;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import com.eunwoosoft.comm.menu.controller.AbstractOproMenuController;
import com.eunwoosoft.comm.util.CmmnUtil;
import com.eunwoosoft.frwk.fol.dto.FwkDataEntity;
import com.eunwoosoft.frwk.fol.dto.FwkTransferObject;
import com.eunwoosoft.frwk.fol.util.FwkMessageUtil;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;
import com.eunwoosoft.opro.comm.service.OproCommLogService;
import com.eunwoosoft.opro.main.service.OproMainLoginFormService;
import com.eunwoosoft.opro.noti.service.OproNotiGnrlService;

/**
 * 로그인/메인
 * com.eunwoosoft.opro.main.controller
 * OproMainPageController.java
 *
 * @Author : SungYoon_Ha 
 * @Date   : 2017. 6. 14.
 *
 */
@RequestMapping(value = "/opro/main")
@Controller
public class OproMainPageController extends AbstractOproMenuController {
	String prefixUrl = "/opro/views/main/"; 
	String prefixUrlPopup = "/opro/views/main/popup/"; 
	
	@Resource(name="oproMainLoginFormService")
	private OproMainLoginFormService oproMainLoginFormService;
	
	@Resource(name="oproNotiGnrlService")    
	private OproNotiGnrlService oproNotiGnrlService;
	
	@Resource(name="oproCommLogService")
	private OproCommLogService oproCommLogService;
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_eproDemo.com.eunwoosoft.opro.main.controller.OproMainLoginFormController.java
	 * @Method : mainPage
	 * @author : SungYoon_Ha
	 * @date : 2017. 6. 15. 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/mainPage")
	public String mainPage(final HttpServletRequest request, final Model model) throws Exception {
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		FwkTransferObject trans = new FwkTransferObject();
		
		FwkDataEntity user = (FwkDataEntity)parameterMap.get("loginResult");
		parameterMap.put("P_DEPT_NO", user.getString("DEPT_NO"));
		parameterMap.put("P_VEND_REG_NO", user.getString("USR_ID"));// BIZR_NO = USR_ID
		parameterMap.put("P_BIZRNO", user.getString("BIZR_NO"));
		parameterMap.put("P_AUTH_ID", user.getString("AUTH_ID"));
		//조회기간 - 한달 
        Calendar cal = Calendar.getInstance();
       // System.out.println(dateFormat.format(cal.getTime())); 현재날짜
       //   System.out.println("해당년도: "+cal.get(Calendar.YEAR));
       //  System.out.println("해당월: "+(cal.get(Calendar.MONTH)+1)); //보여줄때 +1로 하여 사람기준으로 설정
       //  System.out.println("첫번째 일: "+cal.getMinimum(Calendar.DAY_OF_MONTH));
       //  System.out.println("마지막 일(현재 날짜 기준 최대수): "+cal.getActualMaximum(Calendar.DAY_OF_MONTH)); //기본적으로 이걸 사용
       //  System.out.println("마지막 일(Calender이 가진 최대수): "+cal.getMaximum(Calendar.DAY_OF_MONTH));
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
        		 
		parameterMap.put("P_BEGIN_DT_S", year + "-" + sMonth + "-" + firstDay);
		parameterMap.put("P_END_DT_S", year + "-" + sMonth + "-" + lastDay);
		
		parameterMap.put("P_PAGE_NO", "1");
		parameterMap.put("P_PAGE_SIZE", "4");
		
		
		// 일반공지사항
		parameterMap.put("P_BBS_SECD", "NOTI");
		trans = oproNotiGnrlService.notiGnrlListWithPgng(parameterMap);
		trans.put("notiList", trans.get(OproNotiGnrlService.NOTI_GNRL_LIST));
		trans.put("notiListTotCnt", trans.get(OproNotiGnrlService.NOTI_GNRL_LIST_TOTCNT));
		model.addAllAttributes(trans);
		
		// 일반공지사항
		parameterMap.put("P_BBS_SECD", "FAQ");
		trans = oproNotiGnrlService.notiGnrlListWithPgng(parameterMap);
		trans.put("faqList", trans.get(OproNotiGnrlService.NOTI_GNRL_LIST));
		trans.put("faqListTotCnt", trans.get(OproNotiGnrlService.NOTI_GNRL_LIST_TOTCNT));
		model.addAllAttributes(trans);
		
		//페이지 카운트
		trans = oproMainLoginFormService.getMainInfoCnt(parameterMap);
		
		model.addAllAttributes(trans);
		model.addAllAttributes(parameterMap);
		
		return parameterMap.getOproViewName(prefixUrl);
	}
	
	//사용자메뉴얼
	@RequestMapping(value = "/userManual")
	public String userManual(final HttpServletRequest request, final HttpServletResponse response) throws Exception {		
		
		String root = "";
		String fullPath = FwkMessageUtil.getMessage("IPRO.SERVER.WEBAPP.PATH") + File.separator +"menual" + File.separator + "manual_opro.pdf";
		String fileName = "비대면평가시스템 외부평가위원 매뉴얼.pdf";

		// 윈도우 파일명으로 사용할수 없는 문자 제거
		if(fileName != null && !"".equals(fileName)){
			fileName = CmmnUtil.exceptCharReplace(fileName);
		}

		String agentType = request.getHeader("Accept-Encoding");

        boolean flag = false;
        if ( agentType != null && agentType.indexOf("gzip") >= 0 ) {
            flag = true;
        }
        OutputStream	out	= null;
        InputStream	in	= null;

        //파일 인코딩
        if(fileName != null){
	        if(agentType != null){
	        	if( agentType.contains("Firefox") || agentType.contains("Chrome")){
	        		fileName = new String(fileName.getBytes("UTF-8"), "ISO-8859-1");
	        	} else {
	        		fileName = URLEncoder.encode(fileName,"UTF-8").replaceAll("\\+", "%20");
	        	}
	        }else{
	    		fileName = URLEncoder.encode(fileName,"UTF-8").replaceAll("\\+", "%20");
	        }
        }
		if (flag) {
			response.setContentType("application/octet-stream");
			response.setHeader("Content-Encoding", "gzip");
			response.setHeader("Content-disposition", "attachment;filename=" + fileName + ";" );
            out	= new GZIPOutputStream( response.getOutputStream() );
        } else {
        	response.setContentType("application/octet-stream");
        	response.setHeader("Content-disposition", "attachment;filename=" + fileName + ";" );
			out	= response.getOutputStream();
        }
		try{
			in	= new BufferedInputStream( new FileInputStream( fullPath ) );
			FileCopyUtils.copy(in, out );
		}catch(Exception e){
		//	LOG.debug("파일 다운로드 예외 발생");
			e.printStackTrace();
			System.err.println(e.getMessage());
		}finally{
			if(in != null){
				in.close();
			}
			if(out != null){
				out.close();
			}
		}
		
		return null;
		
	}

	
}
