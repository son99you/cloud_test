/*
 * Copyright koica.go.kr.,LTD.
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information
 * of koica.go.kr.,LTD. ("Confidential Information").
 */
package com.eunwoosoft.frwk.prl.view;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.servlet.view.AbstractView;

/**
 * <pre>
 * com.eunwoosoft.frwk.prl.view 
 *    |_ FwkFileDownloadView.java
 * 
 * </pre>
 * @date : 2015. 1. 14. 오후 2:38:56
 * @version : 1.0
 * @author : 
 */
public class FwkFileDownloadView extends AbstractView {
	private static final Logger LOG = LoggerFactory.getLogger(FwkFileDownloadView.class);
	
	public FwkFileDownloadView() {
		setContentType("applicaiton/download;charset=utf-8");
	}

	@Override
	protected void renderMergedOutputModel(Map<String, Object> model,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		
		
		String orgFileName = (String) model.get("orgFileName");
		File file = (File) model.get("downloadFile");
		
		response.setContentType(getContentType());
		response.setContentLength((int)file.length());
		
//		String fileName = URLEncoder.encode(file.getName(), "UTF-8");
		if (orgFileName == null || orgFileName.length() == 0) {
			orgFileName = file.getName();
		}
		//String fileName = URLEncoder.encode(orgFileName, "UTF-8");
		String fileName = orgFileName;
		
		response.setHeader("Content-Disposition", "attachment;filename=\""+fileName+"\";");
		response.setHeader("Content-Transfer-Encoding", "binary");
		
		OutputStream out = response.getOutputStream();
		
		FileInputStream fis = null;
		
		try {
            fis = new FileInputStream(file);
            FileCopyUtils.copy(fis, out);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
			if (fis != null) {
				try {
					fis.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
        }
        out.flush();		
	}	
}
