package com.eunwoosoft.comm.file.vo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class DextNxRequest {

	private List<MultipartFile> DEXTUploadNX;
	private List<String> DEXTUploadNX_UserData;
	private List<String> DEXTUploadNX_Alias;
	private List<String> DEXTUploadNX_FolderPath;
	private List<String> DEXTUploadNX_EmptyFolderPath;
	private List<String> DEXTUploadNX_Virtual;
	private List<String> DEXTUploadNX_VirtualDeleted;
	private List<String> DEXTUploadNX_ExifData;

	public DextNxRequest() {}

	public boolean hasFiles() {
		return (DEXTUploadNX != null && DEXTUploadNX.size() > 0);
	}

	public List<MultipartFile> getFiles() {
		if (this.getDEXTUploadNX() == null){
			return new ArrayList<MultipartFile>();
		}else{
			return this.getDEXTUploadNX();
		}

	}

	public List<String> getUserData() {
		if (this.getDEXTUploadNX_UserData() == null){
			return new ArrayList<String>();
		}else{
			return this.getDEXTUploadNX_UserData();
		}
	}

	public List<String> getAliases() {
		if (this.getDEXTUploadNX_Alias() == null){
			return new ArrayList<String>();
		}else{
			return this.getDEXTUploadNX_Alias();
		}
	}

	public List<String> getFolderPathes() {
		if (this.getDEXTUploadNX_FolderPath() == null){
			return new ArrayList<String>();
		}else{
			return this.getDEXTUploadNX_FolderPath();
		}
	}

	public List<String> getEmptyFolderPathes() {
		if (this.getDEXTUploadNX_EmptyFolderPath() == null){
			return new ArrayList<String>();
		}else{
			return this.getDEXTUploadNX_EmptyFolderPath();
		}
	}

	public List<String> getVirtualFileIds() {
		if (this.getDEXTUploadNX_Virtual() == null){
			return new ArrayList<String>();
		}else{
			return this.getDEXTUploadNX_Virtual();
		}
	}

	public List<String> getVirtualDeletedFileIds() {
		if (this.getDEXTUploadNX_VirtualDeleted() == null){
			return new ArrayList<String>();
		}else{
			return this.getDEXTUploadNX_VirtualDeleted();
		}
	}

	public List<String> getExifData() {
		if (this.getDEXTUploadNX_ExifData() == null){
			return new ArrayList<String>();
		}else{
				return this.getDEXTUploadNX_ExifData();
		}
	}

	public List<MultipartFile> getDEXTUploadNX() {
		return DEXTUploadNX;
	}	

	public void setDEXTUploadNX(List<MultipartFile> dDEXTUploadNX) {
		this.DEXTUploadNX = dDEXTUploadNX;
	}

	public List<String> getDEXTUploadNX_UserData() {
		return DEXTUploadNX_UserData;
	}

	public void setDEXTUploadNX_UserData(List<String> dEXTUploadNX_UserData) {
		DEXTUploadNX_UserData = dEXTUploadNX_UserData;
	}

	public List<String> getDEXTUploadNX_Alias() {
		return DEXTUploadNX_Alias;
	}

	public void setDEXTUploadNX_Alias(List<String> dEXTUploadNX_Alias) {
		DEXTUploadNX_Alias = dEXTUploadNX_Alias;
	}

	public List<String> getDEXTUploadNX_FolderPath() {
		return DEXTUploadNX_FolderPath;
	}

	public void setDEXTUploadNX_FolderPath(List<String> dEXTUploadNX_FolderPath) {
		DEXTUploadNX_FolderPath = dEXTUploadNX_FolderPath;
	}

	public List<String> getDEXTUploadNX_EmptyFolderPath() {
		return DEXTUploadNX_EmptyFolderPath;
	}

	public void setDEXTUploadNX_EmptyFolderPath(
			List<String> dEXTUploadNX_EmptyFolderPath) {
		DEXTUploadNX_EmptyFolderPath = dEXTUploadNX_EmptyFolderPath;
	}

	public List<String> getDEXTUploadNX_Virtual() {
		return DEXTUploadNX_Virtual;
	}

	public void setDEXTUploadNX_Virtual(List<String> dEXTUploadNX_Virtual) {
		DEXTUploadNX_Virtual = dEXTUploadNX_Virtual;
	}

	public List<String> getDEXTUploadNX_VirtualDeleted() {
		return DEXTUploadNX_VirtualDeleted;
	}

	public void setDEXTUploadNX_VirtualDeleted(
			List<String> dEXTUploadNX_VirtualDeleted) {
		DEXTUploadNX_VirtualDeleted = dEXTUploadNX_VirtualDeleted;
	}

	public List<String> getDEXTUploadNX_ExifData() {
		return DEXTUploadNX_ExifData;
	}

	public void setDEXTUploadNX_ExifData(List<String> dEXTUploadNX_ExifData) {
		DEXTUploadNX_ExifData = dEXTUploadNX_ExifData;
	}
}
