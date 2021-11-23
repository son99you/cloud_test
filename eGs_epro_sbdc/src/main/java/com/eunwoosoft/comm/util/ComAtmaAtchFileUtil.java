package com.eunwoosoft.comm.util;

import java.io.File;
import java.util.List;
import java.util.Map;

import javax.activation.MimetypesFileTypeMap;

/**
 * <pre>
 * com.eunwoosoft.comm.util 
 *    |_ ComAtmaAtchFileUtil.java
 * 
 * </pre>
 * @date : 2015. 5. 20. 오후 4:51:02
 * @version : 1.0
 * @author : 야긴스텍 정윤교
 */
public class ComAtmaAtchFileUtil {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static List<Map<String, Object>> getAtchFileInfo (List<Map<String, Object>> atch_file){
//		LOG.debug("getAtchFileInfo - atch_file : " + atch_file);
//		List<Map<String, Object>> atchFileList = new ArrayList<Map<String, Object>>() ;

		if (atch_file == null || atch_file.size() == 0)
			return atch_file;
		
		for( Map atchMap : atch_file ){
//			LOG.debug("getAtchFileInfo - atchMap : " + atchMap);
			
			if (!atchMap.containsKey("P_STRE_FILE_NM") || atchMap.get("P_STRE_FILE_NM") ==  null)
				continue;
			
			Map map = atchMap;
			String p_atchmnfl_cours_nm = atchMap.get("P_ATCHMNFL_COURS_NM").toString();
			String p_stre_file_nm = atchMap.get("P_STRE_FILE_NM").toString();
			String filePath = p_atchmnfl_cours_nm + File.separator + p_stre_file_nm;
			File upFile = new File(filePath);
			
			// 이미지일경우만 강제세팅, 이외는 파일 정보조회
			if (filePath.endsWith(".png")) {
				map.put("P_ATCHMNFL_EXTSN_NM", "image/png");
			} else if (filePath.endsWith(".jpg") || filePath.endsWith(".jpe") || filePath.endsWith(".jpeg")) {
				map.put("P_ATCHMNFL_EXTSN_NM", "image/jpeg");
			} else if (filePath.endsWith(".gif")) {
				map.put("P_ATCHMNFL_EXTSN_NM", "image/gif");
			} else if (filePath.endsWith(".tif") || filePath.endsWith(".tiff")) {
				map.put("P_ATCHMNFL_EXTSN_NM", "image/tiff");
			} else if (filePath.endsWith(".bmp")) {
				map.put("P_ATCHMNFL_EXTSN_NM", "image/bmp");
			} else {
				map.put("P_ATCHMNFL_EXTSN_NM", new MimetypesFileTypeMap().getContentType(upFile));
			}

//			atchFileList.add(map);
		}
		return atch_file;
	}
}
