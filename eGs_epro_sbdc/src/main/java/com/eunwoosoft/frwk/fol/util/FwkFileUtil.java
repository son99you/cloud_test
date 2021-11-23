/*
 * Copyright koica.go.kr.,LTD.
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information
 * of koica.go.kr.,LTD. ("Confidential Information").
 */
package com.eunwoosoft.frwk.fol.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;


/**
 * <pre>
 * com.eunwoosoft.frwk.fol.util 
 *    |_ FwkFileUtil.java
 * 
 * </pre>
 * @date : 2015. 2. 25. 오후 3:18:19
 * @version : 1.0
 * @author : 
 */
public class FwkFileUtil {
	
	/**
	 * <pre>
	 * 1. 개요 : 파일 확장자 반환 
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : getFileExtension
	 * @date : 2015. 2. 25.
	 * @author : 
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 2. 25.						최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param fileName
	 * @return
	 */
	public static String getFileExtension(String fileName) {		
		if(fileName == null) {
			return "";
		}		
		int pos = fileName.lastIndexOf("." );
		return fileName.substring( pos + 1 );		
	}
	
	/**
	 * 파일 받아서 삭제 처리
	 * <pre>
	 * 1.개요 : 
	 * 2.처리내용 : 
	 * </pre>
	 * @Method : deleteFile
	 * @author : FIC04237
	 * @date : 2018. 11. 27. 
	 * @history
	 * =============================================
	 *  date           name           description
	 * ---------------------------------------------
	 * 2018. 11. 27.        FIC04237          최초 생성
	 * =============================================
	 */
	public static boolean deleteFile(File file) {
		if(file.isDirectory()) {
			return false;
		}
		return file.delete();		
	}
	
	/**
	 * 파일풀 경로로 삭제처리
	 * <pre>
	 * 1.개요 : 
	 * 2.처리내용 : 
	 * </pre>
	 * @Method : deleteFile
	 * @author : FIC04237
	 * @date : 2018. 11. 27. 
	 * @history
	 * =============================================
	 *  date           name           description
	 * ---------------------------------------------
	 * 2018. 11. 27.        FIC04237          최초 생성
	 * =============================================
	 */
	public static boolean deleteFile(String fileFullname){
		
		File file = new File(fileFullname);
		
		if(file.isDirectory()) {
			return false;
		}
		
		return file.delete();
	}
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 파일결로와 파일명으로 삭제 처리
	 * 2.처리내용 : 
	 * </pre>
	 * @Method : deleteFile
	 * @author : FIC04237
	 * @date : 2018. 11. 27. 
	 * @history
	 * =============================================
	 *  date           name           description
	 * ---------------------------------------------
	 * 2018. 11. 27.        FIC04237          최초 생성
	 * =============================================
	 */
	public static boolean deleteFile(String filePath, String filename){
		
		File file = new File(filePath, filename);
		
		if(file.isDirectory()) {
			return false;
		}
		
		return file.delete();
	}
	
	/**
	 * <pre>
	 * 1.개요 : 파일 복사 실행 
	 * 2.처리내용 : 
	 * </pre>
	 * @Method : transferFile
	 * @author : FIC04237
	 * @date : 2018. 11. 30. 
	 * @history
	 * =============================================
	 *  date           name           description
	 * ---------------------------------------------
	 * 2018. 11. 30.        FIC04237          최초 생성
	 * =============================================
	 */
	public static void transferFile(File sourceFile, File targetFile){
		//FileChannel inChannel = null;
		//FileChannel outChannel = null;
		
		try(FileChannel inchannel = new FileInputStream(sourceFile).getChannel();
			FileChannel outChannel = new FileOutputStream(targetFile).getChannel()){
			
			inchannel.transferTo(0, inchannel.size(), outChannel);
			//outChannel.transferFrom(inchannel, 0, inchannel.size());
		}catch(IOException ioe){
			
		}
	}
	
	public static void transferFile(String sourceFilePath, String targetFilePath){
		File sourceFile = new File(sourceFilePath);
		File targetFile = new File(targetFilePath);
		
		try(FileChannel inchannel = new FileInputStream(sourceFile).getChannel();
			FileChannel outChannel = new FileOutputStream(targetFile).getChannel()){
			
			inchannel.transferTo(0, inchannel.size(), outChannel);
			//outChannel.transferFrom(inchannel, 0, inchannel.size());
		}catch(IOException ioe){
			
		}
	}
}
