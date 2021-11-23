package com.eunwoosoft.comm.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.eunwoosoft.frwk.fol.dto.FwkDataEntity;
import com.eunwoosoft.frwk.fol.util.FwkDateUtil;
import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpATTRS;
import com.jcraft.jsch.SftpException;

/**
 * sftpUtil
 * @author joo
 *
 */
public class SftpUtil {
	private static final Logger LOG = LoggerFactory.getLogger(SftpUtil.class);
	
	private Session session = null;
    private Channel channel = null;
    private ChannelSftp channelSftp = null;

	/**
	 * 
	 * 서버와 연결에 필요한 값들을 가져와 초기화 시킴
	 * 
	 * @param host : 서버주소 
	 * @param userName : 접속 아이디
	 * @param password : 비밀번호
	 * @param port : 포트번호
	 */
	
	public void init(String host, String userName, String password, int port) {
        JSch jsch = new JSch();
        try {
            session = jsch.getSession(userName, host, port);
            session.setPassword(password);

           java.util.Properties config = new java.util.Properties();
            config.put("StrictHostKeyChecking", "no");
            session.setConfig(config);
            session.connect();

           channel = session.openChannel("sftp");
            channel.connect();
        } catch (JSchException e) {
            e.printStackTrace();
        }

       channelSftp = (ChannelSftp) channel;
    }


	/**
	 *  하나의 파일을 업로드 한다.
	 * @param dir : 저장시킬주소(서버주소)
	 * @param file : 저장할 파일
	 */
	public void upload(String fileName, String serverDir, String newFileName) throws IOException{//String dir, File file) { //, String fileDir) {

        FileInputStream fis = null;
         try {
        	 channelSftp.cd(serverDir);
        	 
        	 // Upload할 파일
        	 File file = new File(fileName);
        	 // 입력 파일을 가져온다. 
        	 fis = new FileInputStream(file);
        	 // 파일을 업로드한다.
        	 channelSftp.put(fis, file.getName());
        	 // 파일 이름 변경
        	 channelSftp.rename(file.getName(), newFileName);
        	 
        	 fis.close();
        	 
         } catch (SftpException e) {
             e.printStackTrace();
         } catch (FileNotFoundException e) {
             e.printStackTrace();
         } 
    }

	public void uploadList(String saveDir, String serverDir, String File_Name, String serverTime, List<FwkDataEntity> cntrctFileData) throws IOException{//String dir, File file) { //, String fileDir) {

        FileInputStream fis = null;
         try {
        	 channelSftp.cd(serverDir);
        	 String ext="";
        	 
        	 for(int i=0; i<cntrctFileData.size(); i++){ 
        		 
        		 FwkDataEntity cntrctFileEntity = cntrctFileData.get(0);
     			saveDir = cntrctFileEntity.getString("FILE_LCTN")+File.separator +cntrctFileEntity.getString("SV_FILE_NM");// 파일 경로
     			ext = cntrctFileEntity.getString("SV_FILE_NM").substring(cntrctFileEntity.getString("SV_FILE_NM").lastIndexOf('.')+1).toLowerCase();
     			File_Name =  serverTime + "_" + cntrctFileEntity.getString("CONT_NO")+"-"+cntrctFileEntity.getString("CHNG_NGR")+"."+ext; // 파일 이름
     			
	        	 // Upload할 파일
	        	 File file = new File(saveDir);
	        	 // 입력 파일을 가져온다. 
	        	 fis = new FileInputStream(file);
	        	 // 파일을 업로드한다.
	        	 channelSftp.put(fis, file.getName());
	        	 // 파일 이름 변경
	        	 channelSftp.rename(file.getName(), File_Name);
	        	 
	        	 fis.close();
        	 }
        	 
         } catch (SftpException e) {
             e.printStackTrace();
         } catch (FileNotFoundException e) {
             e.printStackTrace();
         } 
    }

	
	public void apprUpload(String saveDir, String serverDir, String gbn) throws IOException, SftpException{//String dir, File file) { //, String fileDir) {

        FileInputStream fis = null;
        SftpATTRS attrs=null;
        
        
         try {
        	 channelSftp.cd(serverDir);	//서버경로로 이동
        	 
        	 //if(gbn != "articleHtml"){
        	 if(!"articleHtml".equals(gbn)){
	        	   String dir = FwkDateUtil.getCurrentDateAsString("yyyy") + FwkDateUtil.getCurrentDateAsString("MM"); // 파일 경로
	        	   String fullDir = serverDir+dir;
		 	    	try {
			 		    attrs = channelSftp.stat(fullDir);
			 		} catch (Exception e) {
			 		    System.err.println(fullDir +" not found");
			 		}
		    	 	if (attrs != null) {
		    	 	    System.err.println("Directory exists IsDir="+attrs.isDir());
		    	 	} else {
		    	 	    channelSftp.mkdir(dir);
		    	 	}
		    	 		
	    	 	 channelSftp.cd(fullDir);
        	 }
    	 	 
			// Upload할 파일
			File file = new File(saveDir);
			// 입력 파일을 가져온다. 
			fis = new FileInputStream(file);
			// 파일을 업로드한다.
			channelSftp.put(fis, file.getName());
			
			fis.close();
         } catch (SftpException e) {
             e.printStackTrace();
         } catch (FileNotFoundException e) {
             e.printStackTrace();
         } 
    }
	


	public void apprUploadMulti(String saveDir, String serverDir, String gbn, List<FwkDataEntity> apprFileData) throws IOException, SftpException{//String dir, File file) { //, String fileDir) {

        FileInputStream fis = null;
        SftpATTRS attrs=null;
        
        
         try {
        	 channelSftp.cd(serverDir);	//서버경로로 이동
        	 
        	 //if(gbn != "articleHtml"){
        	 if(!"articleHtml".equals(gbn)){
	        	   String dir = FwkDateUtil.getCurrentDateAsString("yyyy") + FwkDateUtil.getCurrentDateAsString("MM"); // 파일 경로
	        	   String fullDir = serverDir+dir;
		 	    	try {
			 		    attrs = channelSftp.stat(fullDir);
			 		} catch (Exception e) {
			 		    System.err.println(fullDir +" not found");
			 		}
		    	 	if (attrs != null) {
		    	 	    System.err.println("Directory exists IsDir="+attrs.isDir());
		    	 	} else {
		    	 	    channelSftp.mkdir(dir);
		    	 	}
		    	 		
	    	 	 channelSftp.cd(fullDir);
        	 }
        	 
        	 for(int i=0; i<apprFileData.size(); i++){ 
     			
     			FwkDataEntity apprFileEntity = apprFileData.get(i);
     			saveDir = apprFileEntity.getString("FILE_LCTN")+"/"+apprFileEntity.getString("SV_FILE_NM");// 파일 경로
    	 	 
				// Upload할 파일
				File file = new File(saveDir);
				// 입력 파일을 가져온다. 
				fis = new FileInputStream(file);
				// 파일을 업로드한다.
				channelSftp.put(fis, file.getName());
			
				fis.close();
        	 } 
			
         } catch (SftpException e) {
             e.printStackTrace();
         } catch (FileNotFoundException e) {
             e.printStackTrace();
         } 
    }



	 /** create by Junho

	     * 헤당 경로가 없으면 mkdir 하는 함수 리턴값 : fullpath

	     * @param path

	     * @return

	     * @throws SftpException

	     */

	    public String mkdirDir(String path) throws SftpException {
	    	String[] pathArray = path.split("/");
	    	String currentDirectory = channelSftp.pwd();

	    	String totPathArray = "";

	    	for(int i =0; i< pathArray.length; i++) {

	    		totPathArray += pathArray[i] + "/";

				String currentPath = currentDirectory+ "/" + totPathArray;

	    		try {
					channelSftp.mkdir(currentPath);
					channelSftp.cd(currentPath);
				} catch (Exception e) {
					channelSftp.cd(currentPath);
				}
	    	}
	    	return currentDirectory+ "/" + totPathArray;

		}




	    

	
	/**
	 * 하나의 파일을 다운로드 한다.
	 * 
	 * @param dir : 저장할 경로(서버주소)
	 * @param downloadFileName : 다운로드할 파일이름
	 * @param path : 저장할 공간
	 */
	
	public void download(String dir, String downloadFileName, String path) {
        InputStream in = null;
        FileOutputStream out = null;
        try {
            channelSftp.cd(dir);
            in = channelSftp.get(downloadFileName);
        } catch (SftpException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

       try {
            out = new FileOutputStream(new File(path));
            int i;

            if(in != null){
	           while ((i = in.read()) != -1) {
	                out.write(i);
	            }
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            try {
            	if(out != null){
            		out.close();
            	}
            	if(in != null){
        			in.close();
            	}
            } catch (IOException e) {
                e.printStackTrace();
            }

       }

   }

	/**
     * 서버와의 연결을 끊는다.
     */
     public void disconnection() {
         channelSftp.quit();

    }

	
	

}
