package com.eunwoosoft.ipro.vend.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
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
import com.eunwoosoft.frwk.prl.AbstractFwkController;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;
import com.eunwoosoft.ipro.vend.service.IproVendMngeService;

/**
 * 
 * com.eunwoosoft.ipro.vend.controller
 * IproVendMngeController.java
 *
 * @Author : chanil_Hong
 * @Date   : 2017. 6. 13.
 *
 */
@Controller
@RequestMapping(value = "/vend")
public class IproVendMngeController extends AbstractIproMenuController{
	String prefixUrl = "/ipro/views/vend/";
	
	@Resource(name="iproVendMngeService")
	private IproVendMngeService iproVendMngeService;
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 협력사 현황 목록
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_eproDemo.com.eunwoosoft.ipro.vend.controller.IproVendMngeController.java
	 * @Method : vendMngeList
	 * @author : chanil_Hong
	 * @date : 2017. 6. 13. 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/vendMngeList")
	public String vendMngeList(final HttpServletRequest request, final Model model) throws Exception {
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);

		FwkTransferObject trans = iproVendMngeService.vendMngeListInqireWithPgng(parameterMap);
		
		model.addAllAttributes(trans);
		model.addAllAttributes(parameterMap);
		return parameterMap.getViewName(prefixUrl);
	}
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 협력사 등록
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_eproDemo.com.eunwoosoft.ipro.vend.controller.IproVendMngeController.java
	 * @Method : vendMngeList
	 * @author : chanil_Hong
	 * @date : 2017. 6. 13. 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/vendMngeReg")
	public String vendMngeReg(final HttpServletRequest request, final Model model){
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		
		FwkTransferObject trans = new FwkTransferObject();
		model.addAllAttributes(trans);
		return parameterMap.getViewName(prefixUrl);
	}
	
	/**
	 * <pre>
	 * 1.개요 : 협력사등록 저장
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_eproDemo.com.eunwoosoft.ipro.vend.controller.IproVendMngeController.java
	 * @Method : vendMngeRegist
	 * @author : jandi_Eun
	 * @date : 2018. 2. 20. 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/vendMngeRegist")
	public String vendMngeRegist(final HttpServletRequest request, final Model model, HttpServletResponse response) throws Exception {
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);
		FwkTransferObject trans = new FwkTransferObject();
		try{
			
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			List<MultipartFile> fileInfoList = multipartRequest.getFiles("P_FILE");
			
			String[] fileGbn = request.getParameterValues("fileGbn");
			
			System.err.println("fileGbn :::: "+ Arrays.toString(fileGbn));
			System.err.println("size :::: "+ fileInfoList.size());
			
			String url = "";
			// 첨부파일 : 사업자등록증
			String fileGrpNoA = "";
			// 첨부파일 : 신용평가등급자료
			String fileGrpNoB = "";
			// 첨부파일 : 최근년도 결산 재무재표
			String fileGrpNoC = "";
			// 첨부파일 : 회사소개자료 카타로그
			String fileGrpNoD = "";
			// 첨부파일 : 인증서 등 자료 
			String fileGrpNoE = "";
			// 첨부파일 : 면허수첩, 면허증사본
			String fileGrpNoF = "";
			// 첨부파일 : 기타자료
			String fileGrpNoG = "";
			
			
			fileGrpNoA =  FwkStringUtil.replace(UUID.randomUUID().toString(), "-", "");   // 그룹번호 생성(사업자등록증)
			parameterMap.put("P_ATCH_NEW_A", "Y");
			
			fileGrpNoB =  FwkStringUtil.replace(UUID.randomUUID().toString(), "-", "");   // 그룹번호 생성(신용평가등급자료)
			parameterMap.put("P_ATCH_NEW_B", "Y");
			
			fileGrpNoC =  FwkStringUtil.replace(UUID.randomUUID().toString(), "-", "");   // 그룹번호 생성(최근년도 결산 재무재표)
			parameterMap.put("P_ATCH_NEW_C", "Y");
			
			fileGrpNoD =  FwkStringUtil.replace(UUID.randomUUID().toString(), "-", "");   // 그룹번호 생성(회사소개자료 카타로그)
			parameterMap.put("P_ATCH_NEW_D", "Y");
			
			fileGrpNoE =  FwkStringUtil.replace(UUID.randomUUID().toString(), "-", "");   // 그룹번호 생성(인증서 등 자료)
			parameterMap.put("P_ATCH_NEW_E", "Y");

			fileGrpNoF =  FwkStringUtil.replace(UUID.randomUUID().toString(), "-", "");   // 그룹번호 생성(면허수첩, 면허증사본)
			parameterMap.put("P_ATCH_NEW_F", "Y");
			
			fileGrpNoG =  FwkStringUtil.replace(UUID.randomUUID().toString(), "-", "");   // 그룹번호 생성(기타자료1)
			parameterMap.put("P_ATCH_NEW_G", "Y");
			
			
			List<Map<String, Object>> fileList1 = new ArrayList<Map<String,Object>>();
			List<Map<String, Object>> fileList2 = new ArrayList<Map<String,Object>>();
			List<Map<String, Object>> fileList3 = new ArrayList<Map<String,Object>>();
			List<Map<String, Object>> fileList4 = new ArrayList<Map<String,Object>>();
			List<Map<String, Object>> fileList5 = new ArrayList<Map<String,Object>>();
			List<Map<String, Object>> fileList6 = new ArrayList<Map<String,Object>>();
			List<Map<String, Object>> fileList7 = new ArrayList<Map<String,Object>>();
			
			System.err.println("fileInfoList.size() ==> " + fileInfoList.size());
			for (int i = 0; i < fileInfoList.size(); i++) {
				if("A".equals(fileGbn[i])){
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
							pmap.put("P_FILE_SZ",  file.getSize());
							pmap.put("P_SV_FILE_NM",  streFileNm + "." + ext);
							pmap.put("P_SYS_FILE_NM",   orgFileName);	
							pmap.put("P_FILE_LCTN", baseDir + dir);
							pmap.put("P_ATCHMNFL_EXTSN_NM",  "application/octet-stream");
							fileList1.add(pmap);
						}else{
							trans.put("resultCode", "ERR");
							trans.put("msg", "시스템 오류 입니다.");
							trans.put("sMsg", "폴더가 생성되지 않습니다.");
							model.addAllAttributes(trans);
							return url;
						}
					}
				}else if("B".equals(fileGbn[i])){  
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
							pmap.put("P_FILE_SZ",  file.getSize());
							pmap.put("P_SV_FILE_NM",  streFileNm + "." + ext);
							pmap.put("P_SYS_FILE_NM",   orgFileName);	
							pmap.put("P_FILE_LCTN", baseDir + dir);
							pmap.put("P_ATCHMNFL_EXTSN_NM",  "application/octet-stream");
							fileList2.add(pmap);
						}else{
							trans.put("resultCode", "ERR");
							trans.put("msg", "시스템 오류 입니다.");
							trans.put("sMsg", "폴더가 생성되지 않습니다.");
							model.addAllAttributes(trans);
							return url;
						}
					}
				}else if("C".equals(fileGbn[i])){
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
							pmap.put("P_FILE_SZ",  file.getSize());
							pmap.put("P_SV_FILE_NM",  streFileNm + "." + ext);
							pmap.put("P_SYS_FILE_NM",   orgFileName);	
							pmap.put("P_FILE_LCTN", baseDir + dir);
							pmap.put("P_ATCHMNFL_EXTSN_NM",  "application/octet-stream");
							fileList3.add(pmap);
						}else{
							trans.put("resultCode", "ERR");
							trans.put("msg", "시스템 오류 입니다.");
							trans.put("sMsg", "폴더가 생성되지 않습니다.");
							model.addAllAttributes(trans);
							return url;
						}
					}
				}else if("D".equals(fileGbn[i])){  
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
							pmap.put("P_FILE_SZ",  file.getSize());
							pmap.put("P_SV_FILE_NM",  streFileNm + "." + ext);
							pmap.put("P_SYS_FILE_NM",   orgFileName);	
							pmap.put("P_FILE_LCTN", baseDir + dir);
							pmap.put("P_ATCHMNFL_EXTSN_NM",  "application/octet-stream");
							fileList4.add(pmap);
						}else{
							trans.put("resultCode", "ERR");
							trans.put("msg", "시스템 오류 입니다.");
							trans.put("sMsg", "폴더가 생성되지 않습니다.");
							model.addAllAttributes(trans);
							return url;
						}
					}
				}else if("E".equals(fileGbn[i])){  
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
							pmap.put("P_FILE_SZ",  file.getSize());
							pmap.put("P_SV_FILE_NM",  streFileNm + "." + ext);
							pmap.put("P_SYS_FILE_NM",   orgFileName);	
							pmap.put("P_FILE_LCTN", baseDir + dir);
							pmap.put("P_ATCHMNFL_EXTSN_NM",  "application/octet-stream");
							fileList5.add(pmap);
						}else{
							trans.put("resultCode", "ERR");
							trans.put("msg", "시스템 오류 입니다.");
							trans.put("sMsg", "폴더가 생성되지 않습니다.");
							model.addAllAttributes(trans);
							return url;
						}
					}
				}else if("F".equals(fileGbn[i])){  
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
							pmap.put("P_FILE_SZ",  file.getSize());
							pmap.put("P_SV_FILE_NM",  streFileNm + "." + ext);
							pmap.put("P_SYS_FILE_NM",   orgFileName);	
							pmap.put("P_FILE_LCTN", baseDir + dir);
							pmap.put("P_ATCHMNFL_EXTSN_NM",  "application/octet-stream");
							fileList6.add(pmap);
						}else{
							trans.put("resultCode", "ERR");
							trans.put("msg", "시스템 오류 입니다.");
							trans.put("sMsg", "폴더가 생성되지 않습니다.");
							model.addAllAttributes(trans);
							return url;
						}
					}
				}else if("G".equals(fileGbn[i])){  
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
							pmap.put("P_FILE_SZ",  file.getSize());
							pmap.put("P_SV_FILE_NM",  streFileNm + "." + ext);
							pmap.put("P_SYS_FILE_NM",   orgFileName);	
							pmap.put("P_FILE_LCTN", baseDir + dir);
							pmap.put("P_ATCHMNFL_EXTSN_NM",  "application/octet-stream");
							fileList7.add(pmap);
						}else{
							trans.put("resultCode", "ERR");
							trans.put("msg", "시스템 오류 입니다.");
							trans.put("sMsg", "폴더가 생성되지 않습니다.");
							model.addAllAttributes(trans);
							return url;
						}
					}
				}
			}
			
			parameterMap.put("fileGrpNoA", fileGrpNoA);
			parameterMap.put("fileGrpNoB", fileGrpNoB);
			parameterMap.put("fileGrpNoC", fileGrpNoC);
			parameterMap.put("fileGrpNoD", fileGrpNoD);
			parameterMap.put("fileGrpNoE", fileGrpNoE);
			parameterMap.put("fileGrpNoF", fileGrpNoF);
			parameterMap.put("fileGrpNoG", fileGrpNoG);
			
			
			if(fileList1.size() > 0){
				parameterMap.put("fileList1", fileList1); 
			}
			if(fileList2.size() > 0){
				parameterMap.put("fileList2", fileList2); 
			}
			if(fileList3.size() > 0){
				parameterMap.put("fileList3", fileList3); 
			}
			if(fileList4.size() > 0){
				parameterMap.put("fileList4", fileList4); 
			}
			if(fileList5.size() > 0){
				parameterMap.put("fileList5", fileList5); 
			}
			if(fileList6.size() > 0){
				parameterMap.put("fileList6", fileList6); 
			}
			if(fileList7.size() > 0){
				parameterMap.put("fileList7", fileList7); 
			}
			
			System.err.println("vendMngeRegist START ================");
			trans = iproVendMngeService.vendMngeRegist(parameterMap);
			
		}catch(Exception ex){
			response.setStatus(999);
			throw new Exception(ex.toString());
		}
		
		model.addAllAttributes(trans);
		
		return "forward:/vend/vendMngeList.do";
	}
	
	/**
	 * <pre>
	 * 1.개요 : 협력사 현황상세
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_eproDemo.com.eunwoosoft.ipro.vend.controller.IproVendMngeController.java
	 * @Method : vendMngeDetail
	 * @author : chanil_Hong
	 * @date : 2017. 6. 13. 
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/vendMngeDetail")
	public String vendMngeDetail(final HttpServletRequest request, final Model model) throws Exception{
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		
		System.err.println("vendMngeDetailInqire START ================");
		FwkTransferObject trans = iproVendMngeService.vendMngeDetailInqire(parameterMap);
		model.addAllAttributes(trans);
		model.addAllAttributes(parameterMap);
		return parameterMap.getViewName(prefixUrl);
	}
	
	/**
	 * <pre>
	 * 1.개요 : 협력사현황 > 상세 > 수정
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri.com.eunwoosoft.ipro.vend.controller.IproVendMngeController.java
	 * @Method : vendMngeUpdt
	 * @author : jandi_Eun
	 * @date : 2018. 2. 23. 
	 * @param request
	 * @param model
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/vendMngeUpdt")
	public String vendMngeUpdt(final HttpServletRequest request, final Model model, HttpServletResponse response) throws Exception {
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);
		FwkTransferObject trans = new FwkTransferObject();
		try {
			
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			List<MultipartFile> fileInfoList = multipartRequest.getFiles("P_FILE");
			
			String[] fileGbn = request.getParameterValues("fileGbn");
			
			System.err.println("fileGbn :::: "+ Arrays.toString(fileGbn));
			System.err.println("size :::: "+ fileInfoList.size());
			
			String url = "";
			// 첨부파일 : 사업자등록증
			String fileGrpNoA = "";
			// 첨부파일 : 신용평가등급자료
			String fileGrpNoB = "";
			// 첨부파일 : 최근년도 결산 재무재표
			String fileGrpNoC = "";
			// 첨부파일 : 회사소개자료 카타로그
			String fileGrpNoD = "";
			// 첨부파일 : 인증서 등 자료 
			String fileGrpNoE = "";
			// 첨부파일 : 면허수첩, 면허증사본
			String fileGrpNoF = "";
			// 첨부파일 : 기타자료
			String fileGrpNoG = "";
			
			
			if(parameterMap.get("P_FILE_GRP_NO_A") != null && !"".equals(parameterMap.get("P_FILE_GRP_NO_A"))) {
				fileGrpNoA = parameterMap.getString("P_FILE_GRP_NO_A");
			}else{
				fileGrpNoA =  FwkStringUtil.replace(UUID.randomUUID().toString(), "-", "");   // 그룹번호 생성(사업자등록증)
				parameterMap.put("P_ATCH_NEW_A", "Y");
			}
			
			if(parameterMap.get("P_FILE_GRP_NO_B") != null && !"".equals(parameterMap.get("P_FILE_GRP_NO_B"))) {
				fileGrpNoB = parameterMap.getString("P_FILE_GRP_NO_B");
			}else{
				fileGrpNoB =  FwkStringUtil.replace(UUID.randomUUID().toString(), "-", "");   // 그룹번호 생성(신용평가등급자료)
				parameterMap.put("P_ATCH_NEW_B", "Y");
			}
			
			if(parameterMap.get("P_FILE_GRP_NO_C") != null && !"".equals(parameterMap.get("P_FILE_GRP_NO_C"))) {
				fileGrpNoC = parameterMap.getString("P_FILE_GRP_NO_C");
			}else{
				fileGrpNoC =  FwkStringUtil.replace(UUID.randomUUID().toString(), "-", "");   // 그룹번호 생성(최근년도 결산 재무재표)
				parameterMap.put("P_ATCH_NEW_C", "Y");
			}
			
			if(parameterMap.get("P_FILE_GRP_NO_D") != null && !"".equals(parameterMap.get("P_FILE_GRP_NO_D"))) {
				fileGrpNoD = parameterMap.getString("P_FILE_GRP_NO_D");
			}else{
				fileGrpNoD =  FwkStringUtil.replace(UUID.randomUUID().toString(), "-", "");   // 그룹번호 생성(회사소개자료 카타로그)
				parameterMap.put("P_ATCH_NEW_D", "Y");
			}
			
			if(parameterMap.get("P_FILE_GRP_NO_E") != null && !"".equals(parameterMap.get("P_FILE_GRP_NO_E"))) {
				fileGrpNoE = parameterMap.getString("P_FILE_GRP_NO_E");
			}else{
				fileGrpNoE =  FwkStringUtil.replace(UUID.randomUUID().toString(), "-", "");   // 그룹번호 생성(인증서 등 자료)
				parameterMap.put("P_ATCH_NEW_E", "Y");
			}
			
			if(parameterMap.get("P_FILE_GRP_NO_F") != null && !"".equals(parameterMap.get("P_FILE_GRP_NO_F"))) {
				fileGrpNoF = parameterMap.getString("P_FILE_GRP_NO_F");
			}else{
				fileGrpNoF =  FwkStringUtil.replace(UUID.randomUUID().toString(), "-", "");   // 그룹번호 생성(면허수첩, 면허증사본)
				parameterMap.put("P_ATCH_NEW_F", "Y");
			}
			
			if(parameterMap.get("P_FILE_GRP_NO_G") != null && !"".equals(parameterMap.get("P_FILE_GRP_NO_G"))) {
				fileGrpNoG = parameterMap.getString("P_FILE_GRP_NO_G");
			}else{
				fileGrpNoG =  FwkStringUtil.replace(UUID.randomUUID().toString(), "-", "");   // 그룹번호 생성(기타자료1)
				parameterMap.put("P_ATCH_NEW_G", "Y");
			}
			
			List<Map<String, Object>> fileList1 = new ArrayList<Map<String,Object>>();
			List<Map<String, Object>> fileList2 = new ArrayList<Map<String,Object>>();
			List<Map<String, Object>> fileList3 = new ArrayList<Map<String,Object>>();
			List<Map<String, Object>> fileList4 = new ArrayList<Map<String,Object>>();
			List<Map<String, Object>> fileList5 = new ArrayList<Map<String,Object>>();
			List<Map<String, Object>> fileList6 = new ArrayList<Map<String,Object>>();
			List<Map<String, Object>> fileList7 = new ArrayList<Map<String,Object>>();
			
			System.err.println("fileInfoList.size() ==> " + fileInfoList.size());
			for (int i = 0; i < fileInfoList.size(); i++) {
				if("A".equals(fileGbn[i])){
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
							pmap.put("P_FILE_SZ",  file.getSize());
							pmap.put("P_SV_FILE_NM",  streFileNm + "." + ext);
							pmap.put("P_SYS_FILE_NM",   orgFileName);	
							pmap.put("P_FILE_LCTN", baseDir + dir);
							pmap.put("P_ATCHMNFL_EXTSN_NM",  "application/octet-stream");
							fileList1.add(pmap);
						}else{
							trans.put("resultCode", "ERR");
							trans.put("msg", "시스템 오류 입니다.");
							trans.put("sMsg", "폴더가 생성되지 않습니다.");
							model.addAllAttributes(trans);
							return url;
						}
					}
				}else if("B".equals(fileGbn[i])){  
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
							pmap.put("P_FILE_SZ",  file.getSize());
							pmap.put("P_SV_FILE_NM",  streFileNm + "." + ext);
							pmap.put("P_SYS_FILE_NM",   orgFileName);	
							pmap.put("P_FILE_LCTN", baseDir + dir);
							pmap.put("P_ATCHMNFL_EXTSN_NM",  "application/octet-stream");
							fileList2.add(pmap);
						}else{
							trans.put("resultCode", "ERR");
							trans.put("msg", "시스템 오류 입니다.");
							trans.put("sMsg", "폴더가 생성되지 않습니다.");
							model.addAllAttributes(trans);
							return url;
						}
					}
				}else if("C".equals(fileGbn[i])){
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
							pmap.put("P_FILE_SZ",  file.getSize());
							pmap.put("P_SV_FILE_NM",  streFileNm + "." + ext);
							pmap.put("P_SYS_FILE_NM",   orgFileName);	
							pmap.put("P_FILE_LCTN", baseDir + dir);
							pmap.put("P_ATCHMNFL_EXTSN_NM",  "application/octet-stream");
							fileList3.add(pmap);
						}else{
							trans.put("resultCode", "ERR");
							trans.put("msg", "시스템 오류 입니다.");
							trans.put("sMsg", "폴더가 생성되지 않습니다.");
							model.addAllAttributes(trans);
							return url;
						}
					}
				}else if("D".equals(fileGbn[i])){  
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
							pmap.put("P_FILE_SZ",  file.getSize());
							pmap.put("P_SV_FILE_NM",  streFileNm + "." + ext);
							pmap.put("P_SYS_FILE_NM",   orgFileName);	
							pmap.put("P_FILE_LCTN", baseDir + dir);
							pmap.put("P_ATCHMNFL_EXTSN_NM",  "application/octet-stream");
							fileList4.add(pmap);
						}else{
							trans.put("resultCode", "ERR");
							trans.put("msg", "시스템 오류 입니다.");
							trans.put("sMsg", "폴더가 생성되지 않습니다.");
							model.addAllAttributes(trans);
							return url;
						}
					}
				}else if("E".equals(fileGbn[i])){  
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
							pmap.put("P_FILE_SZ",  file.getSize());
							pmap.put("P_SV_FILE_NM",  streFileNm + "." + ext);
							pmap.put("P_SYS_FILE_NM",   orgFileName);	
							pmap.put("P_FILE_LCTN", baseDir + dir);
							pmap.put("P_ATCHMNFL_EXTSN_NM",  "application/octet-stream");
							fileList5.add(pmap);
						}else{
							trans.put("resultCode", "ERR");
							trans.put("msg", "시스템 오류 입니다.");
							trans.put("sMsg", "폴더가 생성되지 않습니다.");
							model.addAllAttributes(trans);
							return url;
						}
					}
				}else if("F".equals(fileGbn[i])){  
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
							pmap.put("P_FILE_SZ",  file.getSize());
							pmap.put("P_SV_FILE_NM",  streFileNm + "." + ext);
							pmap.put("P_SYS_FILE_NM",   orgFileName);	
							pmap.put("P_FILE_LCTN", baseDir + dir);
							pmap.put("P_ATCHMNFL_EXTSN_NM",  "application/octet-stream");
							fileList6.add(pmap);
						}else{
							trans.put("resultCode", "ERR");
							trans.put("msg", "시스템 오류 입니다.");
							trans.put("sMsg", "폴더가 생성되지 않습니다.");
							model.addAllAttributes(trans);
							return url;
						}
					}
				}else if("G".equals(fileGbn[i])){  
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
							pmap.put("P_FILE_SZ",  file.getSize());
							pmap.put("P_SV_FILE_NM",  streFileNm + "." + ext);
							pmap.put("P_SYS_FILE_NM",   orgFileName);	
							pmap.put("P_FILE_LCTN", baseDir + dir);
							pmap.put("P_ATCHMNFL_EXTSN_NM",  "application/octet-stream");
							fileList7.add(pmap);
						}else{
							trans.put("resultCode", "ERR");
							trans.put("msg", "시스템 오류 입니다.");
							trans.put("sMsg", "폴더가 생성되지 않습니다.");
							model.addAllAttributes(trans);
							return url;
						}
					}
				}
			}
			
			parameterMap.put("fileGrpNoA", fileGrpNoA);
			parameterMap.put("fileGrpNoB", fileGrpNoB);
			parameterMap.put("fileGrpNoC", fileGrpNoC);
			parameterMap.put("fileGrpNoD", fileGrpNoD);
			parameterMap.put("fileGrpNoE", fileGrpNoE);
			parameterMap.put("fileGrpNoF", fileGrpNoF);
			parameterMap.put("fileGrpNoG", fileGrpNoG);
			
			if(fileList1.size() > 0){
				parameterMap.put("fileList1", fileList1); 
			}
			if(fileList2.size() > 0){
				parameterMap.put("fileList2", fileList2); 
			}
			if(fileList3.size() > 0){
				parameterMap.put("fileList3", fileList3); 
			}
			if(fileList4.size() > 0){
				parameterMap.put("fileList4", fileList4); 
			}
			if(fileList5.size() > 0){
				parameterMap.put("fileList5", fileList5); 
			}
			if(fileList6.size() > 0){
				parameterMap.put("fileList6", fileList6);  
			}
			if(fileList7.size() > 0){
				parameterMap.put("fileList7", fileList7); 
			}
			
			System.err.println("vendMngeUpdt START ================");
			trans = iproVendMngeService.vendMngeUpdt(parameterMap);
			
		} catch (Exception ex) {
			response.setStatus(999);
			throw new Exception(ex.toString());
		}
		
		model.addAllAttributes(trans);
		
		return "forward:/vend/vendMngeList.do";
	}
	
	
}
