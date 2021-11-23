package com.eunwoosoft.opro.user.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.eunwoosoft.comm.menu.controller.AbstractOproMenuController;
import com.eunwoosoft.frwk.fol.dto.FwkDataEntity;
import com.eunwoosoft.frwk.fol.dto.FwkTransferObject;
import com.eunwoosoft.frwk.fol.util.FwkDateUtil;
import com.eunwoosoft.frwk.fol.util.FwkMessageUtil;
import com.eunwoosoft.frwk.fol.util.FwkStringUtil;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;

@RequestMapping(value = "/opro/user")
@Controller
public class OproUserCompInfoController extends AbstractOproMenuController{
	String prefixUrl = "/opro/views/user/";
	
	/** 
	 *  
	 * <pre>
	 * 1.개요 : 본사정보관리 페이지 
	 * 2.처리내용 : vendorInfoMgr.jsp
	 * </pre>
	 * @Location : eGs_eproDemo.com.eunwoosoft.opro.user.controller.OproUserCompInfoController.java
	 * @Method : vendorInfoMgr
	 * @author : chanil_Hong
	 * @date : 2017. 6. 22. 
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/vendorInfoMgr") 
	public String vendorInfoMgr(final HttpServletRequest request, final Model model) throws Exception {
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		model.addAllAttributes(parameterMap);  
		//return null;
		return parameterMap.getOproViewName(prefixUrl);
	}
	/** 
	 *  
	 * <pre>
	 * 1.개요 : 본사정보관리 페이지 
	 * 2.처리내용 : vendorInfoMgr.jsp
	 * </pre>
	 * @Location : eGs_eproDemo.com.eunwoosoft.opro.user.controller.OproUserCompInfoController.java
	 * @Method : vendorInfoMgr
	 * @author : chanil_Hong
	 * @date : 2017. 6. 22. 
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/vendSancList") 
	public String vendSancList(final HttpServletRequest request, final Model model) throws Exception {
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		FwkDataEntity user = (FwkDataEntity) parameterMap.get("loginResult");
		model.addAllAttributes(parameterMap);  
		//return null;
		return parameterMap.getOproViewName(prefixUrl);
	}
	
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
			String atchFileGroupNoA = "";
			// 첨부파일 : 신용평가등급자료
			String atchFileGroupNoB = "";
			// 첨부파일 : 최근년도 결산 재무재표
			String atchFileGroupNoC = "";
			// 첨부파일 : 회사소개자료 카타로그
			String atchFileGroupNoD = "";
			// 첨부파일 : 인증서 등 자료 
			String atchFileGroupNoE = "";
			// 첨부파일 : 면허수첩, 면허증사본
			String atchFileGroupNoF = "";
			// 첨부파일 : 기타자료
			String atchFileGroupNoG = "";
			
			
			if(parameterMap.get("P_ATCHMNFL_GROUP_NO_A") != null && !"".equals(parameterMap.get("P_ATCHMNFL_GROUP_NO_A"))) {
				atchFileGroupNoA = parameterMap.getString("P_ATCHMNFL_GROUP_NO_A");
			}else{
				atchFileGroupNoA =  FwkStringUtil.replace(UUID.randomUUID().toString(), "-", "");   // 그룹번호 생성(사업자등록증)
				parameterMap.put("P_ATCH_NEW_A", "Y");
			}
			
			if(parameterMap.get("P_ATCHMNFL_GROUP_NO_B") != null && !"".equals(parameterMap.get("P_ATCHMNFL_GROUP_NO_B"))) {
				atchFileGroupNoB = parameterMap.getString("P_ATCHMNFL_GROUP_NO_B");
			}else{
				atchFileGroupNoB =  FwkStringUtil.replace(UUID.randomUUID().toString(), "-", "");   // 그룹번호 생성(신용평가등급자료)
				parameterMap.put("P_ATCH_NEW_B", "Y");
			}
			
			if(parameterMap.get("P_ATCHMNFL_GROUP_NO_C") != null && !"".equals(parameterMap.get("P_ATCHMNFL_GROUP_NO_C"))) {
				atchFileGroupNoC = parameterMap.getString("P_ATCHMNFL_GROUP_NO_C");
			}else{
				atchFileGroupNoC =  FwkStringUtil.replace(UUID.randomUUID().toString(), "-", "");   // 그룹번호 생성(최근년도 결산 재무재표)
				parameterMap.put("P_ATCH_NEW_C", "Y");
			}
			
			if(parameterMap.get("P_ATCHMNFL_GROUP_NO_D") != null && !"".equals(parameterMap.get("P_ATCHMNFL_GROUP_NO_D"))) {
				atchFileGroupNoD = parameterMap.getString("P_ATCHMNFL_GROUP_NO_D");
			}else{
				atchFileGroupNoD =  FwkStringUtil.replace(UUID.randomUUID().toString(), "-", "");   // 그룹번호 생성(회사소개자료 카타로그)
				parameterMap.put("P_ATCH_NEW_D", "Y");
			}
			
			if(parameterMap.get("P_ATCHMNFL_GROUP_NO_E") != null && !"".equals(parameterMap.get("P_ATCHMNFL_GROUP_NO_E"))) {
				atchFileGroupNoE = parameterMap.getString("P_ATCHMNFL_GROUP_NO_E");
			}else{
				atchFileGroupNoE =  FwkStringUtil.replace(UUID.randomUUID().toString(), "-", "");   // 그룹번호 생성(인증서 등 자료)
				parameterMap.put("P_ATCH_NEW_E", "Y");
			}
			
			if(parameterMap.get("P_ATCHMNFL_GROUP_NO_F") != null && !"".equals(parameterMap.get("P_ATCHMNFL_GROUP_NO_F"))) {
				atchFileGroupNoF = parameterMap.getString("P_ATCHMNFL_GROUP_NO_F");
			}else{
				atchFileGroupNoF =  FwkStringUtil.replace(UUID.randomUUID().toString(), "-", "");   // 그룹번호 생성(면허수첩, 면허증사본)
				parameterMap.put("P_ATCH_NEW_F", "Y");
			}
			
			if(parameterMap.get("P_ATCHMNFL_GROUP_NO_G") != null && !"".equals(parameterMap.get("P_ATCHMNFL_GROUP_NO_G"))) {
				atchFileGroupNoG = parameterMap.getString("P_ATCHMNFL_GROUP_NO_G");
			}else{
				atchFileGroupNoG =  FwkStringUtil.replace(UUID.randomUUID().toString(), "-", "");   // 그룹번호 생성(기타자료1)
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
						String baseDir = FwkMessageUtil.getMessage("IEP.DEXT5.PATH");
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
						String baseDir = FwkMessageUtil.getMessage("IEP.DEXT5.PATH");
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
						String baseDir = FwkMessageUtil.getMessage("IEP.DEXT5.PATH");
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
						String baseDir = FwkMessageUtil.getMessage("IEP.DEXT5.PATH");
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
						String baseDir = FwkMessageUtil.getMessage("IEP.DEXT5.PATH");
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
						String baseDir = FwkMessageUtil.getMessage("IEP.DEXT5.PATH");
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
						String baseDir = FwkMessageUtil.getMessage("IEP.DEXT5.PATH");
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
			
			parameterMap.put("atchFileGroupNoA", atchFileGroupNoA);
			parameterMap.put("atchFileGroupNoB", atchFileGroupNoB);
			parameterMap.put("atchFileGroupNoC", atchFileGroupNoC);
			parameterMap.put("atchFileGroupNoD", atchFileGroupNoD);
			parameterMap.put("atchFileGroupNoE", atchFileGroupNoE);
			parameterMap.put("atchFileGroupNoF", atchFileGroupNoF);
			parameterMap.put("atchFileGroupNoG", atchFileGroupNoG);
			
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
			
			System.err.println("vendMngeUpdt START ==========atchFileGroupNoB======" + parameterMap.get("atchFileGroupNoB"));
			//trans = iproVendMngeService.vendMngeUpdt(parameterMap);
			 
		} catch (Exception ex) {
			response.setStatus(999);
			throw new Exception(ex.toString());
		}
		
		model.addAllAttributes(trans);
		
		return "forward:/opro/user/vendorInfoMgr.do";
	}
	

}
