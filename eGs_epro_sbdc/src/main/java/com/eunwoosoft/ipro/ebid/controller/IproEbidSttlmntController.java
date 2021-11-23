package com.eunwoosoft.ipro.ebid.controller;

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
import com.eunwoosoft.ipro.ebid.service.IproEbidSttlmntService;
/**
 * 수의시담
 * com.eunwoosoft.ipro.ebid.controller
 * IproEbidSttlmntController.java
 *
 * @Author : chanil_Hong
 * @Date   : 2017. 6. 20.
 *
 */
@RequestMapping(value = "/ebid")
@Controller
public class IproEbidSttlmntController extends AbstractIproMenuController{
	String prefixUrl = "/ipro/views/ebid/";
	@Resource(name="iproEbidSttlmntService")
	private IproEbidSttlmntService iproEbidSttlmntService;
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 수의시담 목록 
	 * 2.처리내용 : vltrnPrvstlList.jsp
	 * </pre>
	 * @Location : eGs_eproDemo.com.eunwoosoft.ipro.ebid.controller.IproEbidSttlmntController.java
	 * @Method : vltrnPrvstlList
	 * @author : chanil_Hong
	 * @date : 2017. 6. 20. 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/vltrnPrvstlList")
	public String vltrnPrvstlList(final HttpServletRequest request, final Model model, final HttpServletResponse response) throws Exception{
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		FwkTransferObject trans = new FwkTransferObject();
		try{
			trans = iproEbidSttlmntService.vltrnPrvstlListInqireWithPgng(parameterMap);
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
	 * 1.개요 : 수의시담 상세 
	 * 2.처리내용 :  vltrnPrvstlDetail.jsp
	 * </pre>
	 * @Location : eGs_eproDemo.com.eunwoosoft.ipro.ebid.controller.IproEbidSttlmntController.java
	 * @Method : vltrnPrvstlDetail
	 * @author : chanil_Hong
	 * @date : 2017. 6. 20. 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/vltrnPrvstlDetail")
	public String vltrnPrvstlDetail(final HttpServletRequest request, final Model model, final HttpServletResponse response) throws Exception{
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		FwkTransferObject trans = new FwkTransferObject();
		try{
			trans = iproEbidSttlmntService.vltrnPrvstlDetail(parameterMap);
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
	 * 1.개요 : 수의시담 수정폼 
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_eproDemo.com.eunwoosoft.ipro.ebid.controller.IproEbidSttlmntController.java
	 * @Method : vltrnPrvstlModifyForm
	 * @author : chanil_Hong
	 * @date : 2017. 6. 21. 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/vltrnPrvstlModifyForm")
	public String vltrnPrvstlModifyForm(final HttpServletRequest request, final Model model, final HttpServletResponse response) throws Exception{
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		FwkTransferObject trans = new FwkTransferObject();
		try{
			trans = iproEbidSttlmntService.vltrnPrvstlDetail(parameterMap);
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
	 * 1.개요 : 수의시담 수정 
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_eproDemo.com.eunwoosoft.ipro.ebid.controller.IproEbidSttlmntController.java
	 * @Method : vltrnPrvstlModify
	 * @author : chanil_Hong
	 * @date : 2017. 6. 21. 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/vltrnPrvstlModify")
	public String vltrnPrvstlModify(final HttpServletRequest request, final Model model, final HttpServletResponse response) throws Exception{
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		FwkTransferObject trans = new FwkTransferObject();
		try{
			//첨부파일(일반첨부파일)
			String atchFileGroupNo = "";
			
			if(parameterMap.get("P_FILE_GRP_NO") != null && !"".equals(parameterMap.get("P_FILE_GRP_NO"))) {
				atchFileGroupNo = parameterMap.getString("P_FILE_GRP_NO");
			}
			
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request; 
			
			List<MultipartFile> fileInfoList = multipartRequest.getFiles("P_FILE");
			String url = "";
			
			List<Map<String, Object>> fileList = new ArrayList<Map<String,Object>>();
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
						pmap.put("P_FILE_SZ",  file.getSize()); //파일크기
						pmap.put("P_SV_FILE_NM",  streFileNm + "." + ext); //저장된파일명
						pmap.put("P_SYS_FILE_NM",   orgFileName);	 //실제파일명
						pmap.put("P_FILE_LCTN", baseDir + dir); //첨부파일 저장경로
						pmap.put("P_ATCHMNFL_EXTSN_NM",  "application/octet-stream");	//파일확장자
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
			parameterMap.put("atchFileGroupNo", atchFileGroupNo); 
			if(fileList.size() > 0){
				parameterMap.put("fileList", fileList); 
			}
			trans = iproEbidSttlmntService.vltrnPrvstlModify(parameterMap);
			model.addAllAttributes(parameterMap);
		}catch(Exception ex){
			response.setStatus(999);
			throw new Exception(ex.toString());
		}
		return "forward:/ebid/vltrnPrvstlDetail.do";
	}
	/**
	 * 
	 * <pre>
	 * 1.개요 : 수의시담 등록폼
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_eproDemo.com.eunwoosoft.ipro.ebid.controller.IproEbidSttlmntController.java
	 * @Method : vltrnPrvstlRegistForm
	 * @author : chanil_Hong
	 * @date : 2017. 6. 21. 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/vltrnPrvstlRegistForm")
	public String vltrnPrvstlRegistForm(final HttpServletRequest request, final Model model, final HttpServletResponse response) throws Exception{
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		FwkTransferObject trans = new FwkTransferObject();
		try{
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
	 * 1.개요 : 수의시담 시담대상 등록폼
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_eproDemo.com.eunwoosoft.ipro.ebid.controller.IproEbidSttlmntController.java
	 * @Method : vltrnPrvstlRequstRegistForm
	 * @author : chanil_Hong
	 * @date : 2017. 6. 21. 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/vltrnPrvstlRequstRegistForm")
	public String vltrnPrvstlRequstRegistForm(final HttpServletRequest request, final Model model, final HttpServletResponse response) throws Exception{
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		FwkTransferObject trans = new FwkTransferObject();
		try{
			trans = iproEbidSttlmntService.vltrnPrvstlRequstRegistForm(parameterMap);
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
	 * 1.개요 : 수의시담 등록
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_eproDemo.com.eunwoosoft.ipro.ebid.controller.IproEbidSttlmntController.java
	 * @Method : vltrnPrvstlRegist
	 * @author : chanil_Hong
	 * @date : 2018. 3. 19. 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/vltrnPrvstlRegist")
	public String vltrnPrvstlRegist(final HttpServletRequest request, final Model model, final HttpServletResponse response) throws Exception{
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		FwkTransferObject trans = new FwkTransferObject();
		try{
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request; 
			
			List<MultipartFile> fileInfoList = multipartRequest.getFiles("P_FILE");
			String url = "";
			
			List<Map<String, Object>> fileList = new ArrayList<Map<String,Object>>();
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
						pmap.put("P_FILE_SZ",  file.getSize()); //파일크기
						pmap.put("P_SV_FILE_NM",  streFileNm + "." + ext); //저장된파일명
						pmap.put("P_SYS_FILE_NM",   orgFileName);	 //실제파일명
						pmap.put("P_FILE_LCTN", baseDir + dir); //첨부파일 저장경로
						pmap.put("P_ATCHMNFL_EXTSN_NM",  "application/octet-stream");	//파일확장자
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
			parameterMap.put("atchFileGroupNo", FwkStringUtil.replace(UUID.randomUUID().toString(), "-", ""));  // 그룹번호 생성
			if(fileList.size() > 0){
				parameterMap.put("fileList", fileList); 
			}
			trans = iproEbidSttlmntService.vltrnPrvstlRegist(parameterMap);
			model.addAllAttributes(parameterMap);
		}catch(Exception ex){
			response.setStatus(999);
			throw new Exception(ex.toString());
		}
		model.addAllAttributes(trans);
		
		return "forward:/ebid/vltrnPrvstlRegistForm.do";
	}
	/**
	 * 
	 * <pre>
	 * 1.개요 : 수의시담 요청 
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_eproDemo.com.eunwoosoft.ipro.ebid.controller.IproEbidSttlmntController.java
	 * @Method : prvstlRequst
	 * @author : chanil_Hong
	 * @date : 2017. 6. 21. 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/prvstlRequst")
	public String prvstlRequst(final HttpServletRequest request, final Model model, final HttpServletResponse response) throws Exception{
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		FwkTransferObject trans = new FwkTransferObject();
		try{
			trans = iproEbidSttlmntService.prvstlRequst(parameterMap);		
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
	 * 1.개요 : 수의시담 상세 - 시담진행
	 * 2.처리내용 :  vltrnPrvstlDetail.jsp
	 * </pre>
	 * @Location : eGs_eproDemo.com.eunwoosoft.ipro.ebid.controller.IproEbidSttlmntController.java
	 * @Method : vltrnPrvstlDetail
	 * @author : chanil_Hong
	 * @date : 2017. 6. 20. 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/vltrnPrvstlProgrsDetail")
	public String vltrnPrvstlProgrsDetail(final HttpServletRequest request, final Model model, final HttpServletResponse response) throws Exception{
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		FwkTransferObject trans = new FwkTransferObject();
		try{
			trans = iproEbidSttlmntService.vltrnPrvstlProgrsDetail(parameterMap);
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
	 * 1.개요 : 수의시담 상세 - 내용등록
	 * 2.처리내용 :  vltrnPrvstlDetail.jsp
	 * </pre>
	 * @Location : eGs_eproDemo.com.eunwoosoft.ipro.ebid.controller.IproEbidSttlmntController.java
	 * @Method : vltrnPrvstlCnRegist
	 * @author : chanil_Hong
	 * @date : 2017. 6. 20. 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/vltrnPrvstlCnRegist")
	public String vltrnPrvstlCnRegist(final HttpServletRequest request, final Model model, final HttpServletResponse response) throws Exception{
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		FwkTransferObject trans = new FwkTransferObject();
		try{
			trans = iproEbidSttlmntService.vltrnPrvstlCnRegist(parameterMap);
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
	 * 1.개요 : 수의시담 삭제
	 * 2.처리내용 :  vltrnPrvstlDelete.jsp
	 * </pre>
	 * @Location : eGs_eproDemo.com.eunwoosoft.ipro.ebid.controller.IproEbidSttlmntController.java
	 * @Method : vltrnPrvstlDelete
	 * @author : chanil_Hong
	 * @date : 2017. 6. 20. 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/prvstlDelete")
	public String prvstlDelete(final HttpServletRequest request, final Model model) {
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);
		
		FwkTransferObject trans = iproEbidSttlmntService.prvstlDelete(parameterMap);
		
		return JSON_VIEW;
	}
}
