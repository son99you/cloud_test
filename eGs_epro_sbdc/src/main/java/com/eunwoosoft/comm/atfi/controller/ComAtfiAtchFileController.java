package com.eunwoosoft.comm.atfi.controller;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.StringTokenizer;
import java.util.UUID;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.eunwoosoft.comm.atfi.vo.ComAtfiAtchFileVo;
import com.eunwoosoft.frwk.fol.util.FwkDateUtil;
import com.eunwoosoft.frwk.fol.util.FwkStringUtil;
import com.eunwoosoft.frwk.prl.AbstractFwkController;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;

/** 
 * <pre> 
 * com.eunwoosoft.comm.atfi.controller
 *    |_ ComAtfiAtchFileController.java
 * 
 * </pre>
 * @date : 2015. 3. 2. 오후 3:38:57
 * @version : 1.0
 * @author : LIG시스템 김경용
 */
@Controller
@RequestMapping("/com/atfi")
public class ComAtfiAtchFileController extends AbstractFwkController {
	private static final Logger LOG = LoggerFactory.getLogger(ComAtfiAtchFileController.class);

	private String fileSavePath = "";
	
	private int fileMaxSize = 0;
	
	private int fileSaveCount = 0;
	
	private String fileWhiteExtensions = "";

	private int fileSaveThumbWidth = 0;

	private int fileSaveThumbHeight = 0;

	private LinkedList<ComAtfiAtchFileVo> files = new LinkedList<ComAtfiAtchFileVo>();

	/**
	 * <pre>
	 * 1. 개요 : 파일업로드 
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : fileUpload
	 * @date : 2015. 3. 2.
	 * @author : LIG시스템 김경용
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 3. 2.		LIG시스템 김경용				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param request
	 * @param response
	 * @param status
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/fileUpload", method = RequestMethod.POST)
	public @ResponseBody LinkedList<ComAtfiAtchFileVo> fileUpload(MultipartHttpServletRequest request, HttpServletResponse response, SessionStatus status) throws Exception {

		//1. build an iterator
		Iterator<String> itr =  request.getFileNames();
		MultipartFile mpf = null;		

		try { 

			while(itr.hasNext()){

				mpf = request.getFile(itr.next()); 

				String uuid = (UUID.randomUUID().toString()).replaceAll("-",""); 
				String contextPath = FwkStringUtil.replace(request.getContextPath(), "/", "");

				if(files.size() > fileSaveCount ){
					//파일의 등록 허용 갯수 초과
					//					return files;
				}

				if(mpf.getSize() > fileMaxSize ){
					//파일의 등록 허용 용량 초과
					//					return files;
				}

				if(!extensionValidate(mpf.getOriginalFilename(), fileWhiteExtensions) ){
					//파일의 등록 확장자 체크
					//					return files;
				}

				ComAtfiAtchFileVo fileMeta = new ComAtfiAtchFileVo();

				fileMeta.setOriginalFileName(mpf.getOriginalFilename());
				fileMeta.setFileSize(mpf.getSize());
				fileMeta.setContentType(mpf.getContentType());
				fileMeta.setFileUploadPath(contextPath + "/" + FwkDateUtil.getCurrentDateAsString("yyyyMM") + "/" + FwkDateUtil.getCurrentDateAsString("dd"));
				fileMeta.setSaveFileName(uuid + "." + fileMeta.getFileExtension());
				fileMeta.setBytes(mpf.getBytes());

				File file = new File(fileSavePath + "/" + fileMeta.getFileUploadPath());		
				if( !file.exists() ){
					file.mkdirs();
				}

				String saveFile = fileSavePath + File.separator + fileMeta.getFileUploadPath() + File.separator + fileMeta.getSaveFileName();

				FileCopyUtils.copy(mpf.getBytes(), new FileOutputStream(saveFile));

				//thumbnail 생성
				String thumbSaveFile = fileSavePath + File.separator + fileMeta.getFileUploadPath() + File.separator + "thumb_" + fileMeta.getSaveFileName();
				BufferedImage buffer_original_image = ImageIO.read(new File(saveFile)); //저장된 파일 정보 취득 
				BufferedImage buffer_thumbnail_image = new BufferedImage(fileSaveThumbWidth, fileSaveThumbHeight, BufferedImage.TYPE_3BYTE_BGR);
				Graphics2D graphic = buffer_thumbnail_image.createGraphics();
				graphic.drawImage(buffer_original_image, 0, 0, fileSaveThumbWidth, fileSaveThumbHeight, null);
				ImageIO.write(buffer_thumbnail_image, "jpg", new File( thumbSaveFile )); //썸네일 파일의 정보 생성

				LOG.info("thumbnail uploaded! " + thumbSaveFile);

				files.add(fileMeta);

			}		


		} catch (IOException e) {
			LOG.error(e.toString());
		}

		return files;
	}

	private static boolean extensionValidate(String filename, String ext){

		int pos = filename.lastIndexOf( "." );
		String fileExt = filename.substring( pos + 1 );

		StringTokenizer st = new StringTokenizer(ext);
		while (st.hasMoreElements()) {
			String token = st.nextToken(";");
			if(token.equals(fileExt)){
				return true;
			}
		}
		return false;
	}

	/**
	 * <pre>
	 * 1. 개요 : 파일다운로드 
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : fileDwld
	 * @date : 2015. 3. 2.
	 * @author : LIG시스템 김경용
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 3. 2.		LIG시스템 김경용				최초 작성 
	 *	2015. 5.13.		창우컴즈  송재연				orgFileName 추가 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/fileDwld")
	public String fileDwld(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
		LOG.debug("..............................................................................................................");
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);

		String fileUploadPath = parameterMap.getTrimString("fileUploadPath");
		String fileName = parameterMap.getTrimString("fileName");
		String orgFileName = (parameterMap.containsKey("orgFileName"))? parameterMap.getTrimString("orgFileName") : parameterMap.getTrimString("fileName");

		LOG.debug("fileUploadPath : " + fileUploadPath);
		LOG.debug("fileName : " + fileName);
		LOG.debug("orgFileName : " + orgFileName);

		File downloadFile = new File(fileSavePath + "/" + fileUploadPath + "/" + fileName);

		model.addAttribute("orgFileName", orgFileName);
		model.addAttribute("downloadFile", downloadFile);

		return FILE_DOWNLOAD_VIEW;
	}

	/**
	 * <pre>
	 * 1. 개요 : 파일삭제 
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : fileDelete
	 * @date : 2015. 3. 2.
	 * @author : LIG시스템 김경용
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 3. 2.		LIG시스템 김경용				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/fileDelete", method = RequestMethod.POST)
	public String fileDelete(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
		LOG.debug("..............................................................................................................");
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);

		String fileUploadPath = parameterMap.getTrimString("fileUploadPath");
		String fileName = parameterMap.getTrimString("fileName");
		int fileIndex = parameterMap.getInt("fileIndex");

		LOG.debug("fileUploadPath : " + fileUploadPath);
		LOG.debug("fileName : " + fileName);

		files.remove(fileIndex);

		return JSON_VIEW;
	}

	/**
	 * <pre>
	 * 1. 개요 : 파일초기화 
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : fileInitl
	 * @date : 2015. 3. 2.
	 * @author : LIG시스템 김경용
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 3. 2.		LIG시스템 김경용				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/fileInitl", method = RequestMethod.POST)
	public String fileInitl(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {

		LOG.debug("clear files................................................");
		files.clear();
		LOG.debug("clear files................................................");

		return JSON_VIEW;
	}
}
