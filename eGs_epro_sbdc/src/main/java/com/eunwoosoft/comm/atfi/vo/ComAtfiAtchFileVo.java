package com.eunwoosoft.comm.atfi.vo;

import java.io.Serializable;

import com.eunwoosoft.frwk.fol.util.FwkFileUtil;

public class ComAtfiAtchFileVo implements Serializable {

	private static final long serialVersionUID = 2733480772155868537L;
	
	private String fileUploadPath = "";
	
	private String originalFileName = "";
	private String saveFileName = "";
	private String contentType = "";
	private long fileSize = 0;

	
	
	private byte[] bytes;
	public String getOriginalFileName() {
		return originalFileName;
	}
	public void setOriginalFileName(String originalFileName) {
		this.originalFileName = originalFileName;
	}
	public String getSaveFileName() {
		return saveFileName;
	}
	public void setSaveFileName(String saveFileName) {
		this.saveFileName = saveFileName;
	}
	public String getContentType() {
		return contentType;
	}
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	public long getFileSize() {
		return fileSize;
	}
	public void setFileSize(long fileSize) {
		this.fileSize = fileSize;
	}
	public byte[] getBytes() {
		return bytes;
	}
	public void setBytes(byte[] bytes) {
		this.bytes = bytes;
	}
	public String getFileExtension() {
		return FwkFileUtil.getFileExtension(originalFileName);
	}
	public String getFileUploadPath() {
		return fileUploadPath;
	}
	public void setFileUploadPath(String fileUploadPath) {
		this.fileUploadPath = fileUploadPath;
	}
	
	
}
