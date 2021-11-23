/*
 * Copyright koica.go.kr.,LTD.
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information
 * of koica.go.kr.,LTD. ("Confidential Information").
 */
package com.eunwoosoft.frwk.prl.view;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jxls.transformer.XLSTransformer;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellRangeAddress;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.servlet.view.document.AbstractExcelView;

import com.eunwoosoft.frwk.fol.dto.FwkDataEntity;
import com.eunwoosoft.frwk.fol.util.FwkDateUtil;
import com.eunwoosoft.frwk.fol.util.FwkMessageUtil;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;
import com.oss.asn1.INTEGER;

/**
 * <pre>
 * com.eunwoosoft.frwk.prl.view 
 *    |_ FwkExcelView.java
 * 
 * </pre>
 * @date : 2015. 1. 14. 오후 2:38:56
 * @version : 1.0
 * @author : 
 */
public class FwkExcelView extends AbstractExcelView {
	
	private static final String EXCEL_PATH = "excel_template";
	 
	@Override
	protected void buildExcelDocument(Map<String, Object> model,
			HSSFWorkbook workbook, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String templateFileName = (String) model.get("templateFileName");
		String destFileName = (String) model.get("destFileName");
		String excelGbn = (String) model.get("excelGbn");
		
		/**
		 * excelGbn == POI (스텐다드)
		 * excelGbn == POI_STTS (동적 통계용)
		 * excelGbn == POI_RESULT (동적 결과용)
		 * excelGbn == TEMPLATE (탬플릿양식 다운로드)
		 */
		// 스텐다드 POI 인경우
		if("POI".equals(excelGbn)) {
			List<FwkDataEntity> dataEntity = (List<FwkDataEntity>) model.get("dataList");
			List<FwkDataEntity> EXCEL_TH_LIST = (List<FwkDataEntity>) model.get("EXCEL_TH_LIST");
			List<FwkDataEntity> EXCEL_TH_COL_LIST = (List<FwkDataEntity>) model.get("EXCEL_TH_COL_LIST");
			List<FwkDataEntity> EXCEL_TH_ROW_LIST = (List<FwkDataEntity>) model.get("EXCEL_TH_ROW_LIST");
			List<FwkDataEntity> EXCEL_TD_LIST = (List<FwkDataEntity>) model.get("EXCEL_TD_LIST");
			
			// 시트명 생성
			HSSFSheet sheet = workbook.createSheet("구매계약대장");
			
			// row 생성
			HSSFRow row = sheet.createRow(0);
			
			// 셀 크기 조절
			// 일정하게 넣는 다면
			for(int i = 0; i < EXCEL_TH_LIST.size(); i++) {
				sheet.setColumnWidth((int)i, (int)5000);
			}
			
            // 타이틀 call 변수 생성
            HSSFCell cell = row.createCell((int)0);
            // 타이틀 스타일 변수 생성
            HSSFCellStyle titleHcs = workbook.createCellStyle();
            
            /*
             * cell = row.createCell(0); // 0 번째 셀 생성
             * cell.setCellValue("계약번호"); // 0번째 셀의 값
             * cell.setCellStyle(getTitleStyle(workbook, titleHcs)); // 0번째 셀의 스타일
             */
            for(int i = 0; i < EXCEL_TH_LIST.size(); i++) {
            	FwkDataEntity thDataEntity = EXCEL_TH_LIST.get(i);
            	FwkDataEntity colEntity = EXCEL_TH_COL_LIST.get(i);
            	FwkDataEntity rowEntity = EXCEL_TH_ROW_LIST.get(i);
            	
            	int colInt =  Integer.parseInt(colEntity.getString("VAL"));
            	int rowInt =  Integer.parseInt(rowEntity.getString("VAL"));
            	
            	cell = row.createCell(i);
            	cell.setCellStyle(getTitleStyle(workbook, titleHcs));
            	System.out.println("colInt :: " + colInt + " rowInt :: " + rowInt);
            	if( (colInt + rowInt) > 2) {
            		System.out.println("colInt + rowInt :: " + (colInt + rowInt));
            		System.out.println("i+colInt :: " + (i+colInt));
            		sheet.addMergedRegion(new CellRangeAddress(0,0,i,i+colInt-1));
            	}
            	cell.setCellValue(thDataEntity.get("VAL").toString());	
            	
            	
            }
            
            // 내용 셀 스타일 생성
	        HSSFCellStyle hcs = workbook.createCellStyle();
	        
	        // 내용 셀 스타일 생성 (money format)
	        HSSFCellStyle hcsMformat = workbook.createCellStyle();
			
	        // 내용 셀 폰트 생성
			HSSFFont hf = workbook.createFont();
	        
			// 내용 셀 포멧형식 생성
			HSSFDataFormat format = workbook.createDataFormat();
			
			for(int i = 0; i < dataEntity.size(); i++) {
				
				FwkDataEntity dataEntityOne =  dataEntity.get(i);
				row = sheet.createRow(i+1);
				for(int j = 0; j < EXCEL_TD_LIST.size(); j++) {
					FwkDataEntity excelTdDataEntity = EXCEL_TD_LIST.get(j);
					
					if(dataEntityOne.get(excelTdDataEntity.getString("VAL")) != null && !"".equals(dataEntityOne.get(excelTdDataEntity.getString("VAL")))) {
						System.out.println("dataEntityOne.get(excelTdDataEntity.getString(VAL) ::" + dataEntityOne.get(excelTdDataEntity.getString("VAL")));
						cell = row.createCell(j);
				        cell.setCellStyle(getCellStyle(workbook,hcs,hf));
				        cell.setCellValue(new HSSFRichTextString(dataEntityOne.getString(excelTdDataEntity.getString("VAL"))));
				        
					}else {
						System.out.println("dataEntityOne.get(excelTdDataEntity.getString(VAL) :: null");
						cell = row.createCell(j);
				        cell.setCellStyle(getCellStyle(workbook,hcs,hf));
				        cell.setCellValue(new HSSFRichTextString(""));
					}
				}
			}
			
			InputStream is = new ClassPathResource(EXCEL_PATH + "/" + templateFileName).getInputStream();
			
			
			String streFileNm = "구매계약대장";
			String orgFileName = "구매계약대장";
			String baseDir = FwkMessageUtil.getMessage("EW.SYS.FILE.DIR");
			String dir = File.separator+FwkDateUtil.getCurrentDateAsString("yyyy") + File.separator+FwkDateUtil.getCurrentDateAsString("MM")+
					File.separator+FwkDateUtil.getCurrentDateAsString("dd") + 	File.separator;
			String fileFullPath = "구매계약대장";
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
			
			OutputStream bos = new FileOutputStream( fileFullPath );
			
			StringBuilder contentDisposition = new StringBuilder();
			contentDisposition.append("attachment;fileName=\"");
			contentDisposition.append(encodeFileName(destFileName) + ".xls");
			contentDisposition.append("\";");
			response.setHeader("Content-Disposition", contentDisposition.toString());
			response.setContentType("application/x-msexcel");
			workbook.write(bos);
			is.close();
			
			
			
		}else if("POI_STTS".equals(excelGbn)) {
			// 통계공통 엑셀다운로드
			List sttsListData = (List) model.get("sttsListData");
			List<FwkDataEntity> sttsKeyList = (List<FwkDataEntity>) model.get("sttsKeyList");
			List<FwkDataEntity> EXCEL_TH_LIST = (List<FwkDataEntity>) model.get("EXCEL_TH_LIST");
			List<FwkDataEntity> EXCEL_TH_COL_LIST = (List<FwkDataEntity>) model.get("EXCEL_TH_COL_LIST");
			List<FwkDataEntity> EXCEL_TH_ROW_LIST = (List<FwkDataEntity>) model.get("EXCEL_TH_ROW_LIST");
			/*List<FwkDataEntity> EXCEL_TD_LIST = (List<FwkDataEntity>) model.get("EXCEL_TD_LIST");*/
			
			// 시트명 생성
			HSSFSheet sheet = workbook.createSheet("구매계약대장");
			
			// row 생성
			HSSFRow row = sheet.createRow(0);
			
			// 셀 크기 조절
			// 일정하게 넣는 다면
			for(int i = 0; i < EXCEL_TH_LIST.size(); i++) {
				sheet.setColumnWidth((int)i, (int)5000);
			}
			
            // 타이틀 call 변수 생성
            HSSFCell cell = row.createCell((int)0);
            // 타이틀 스타일 변수 생성
            HSSFCellStyle titleHcs = workbook.createCellStyle();
            
            /*
             * cell = row.createCell(0); // 0 번째 셀 생성
             * cell.setCellValue("계약번호"); // 0번째 셀의 값
             * cell.setCellStyle(getTitleStyle(workbook, titleHcs)); // 0번째 셀의 스타일
             */
            for(int i = 0; i < EXCEL_TH_LIST.size(); i++) {
            	FwkDataEntity thDataEntity = EXCEL_TH_LIST.get(i);
            	FwkDataEntity colEntity = EXCEL_TH_COL_LIST.get(i);
            	FwkDataEntity rowEntity = EXCEL_TH_ROW_LIST.get(i);
            	
            	int colInt =  Integer.parseInt(colEntity.getString("VAL"));
            	int rowInt =  Integer.parseInt(rowEntity.getString("VAL"));
            	
            	cell = row.createCell(i);
            	cell.setCellStyle(getTitleStyle(workbook, titleHcs));
            	System.out.println("colInt :: " + colInt + " rowInt :: " + rowInt);
            	if( (colInt + rowInt) > 2) {
            		System.out.println("colInt + rowInt :: " + (colInt + rowInt));
            		System.out.println("i+colInt :: " + (i+colInt));
            		sheet.addMergedRegion(new CellRangeAddress(0,0,i,i+colInt-1));
            	}
            	cell.setCellValue(thDataEntity.get("VAL").toString());	
            	
            	
            }
            
            // 내용 셀 스타일 생성
	        HSSFCellStyle hcs = workbook.createCellStyle();
	        
	        // 내용 셀 스타일 생성 (money format)
	        HSSFCellStyle hcsMformat = workbook.createCellStyle();
			
	        // 내용 셀 폰트 생성
			HSSFFont hf = workbook.createFont();
	        
			// 내용 셀 포멧형식 생성
			HSSFDataFormat format = workbook.createDataFormat();
			
			
			for(int i = 0; i < sttsKeyList.size(); i++) { // row ex) 13
				
				row = sheet.createRow(i+1);
				for(int j = 0; j < sttsListData.size(); j++) { // col ex) 8
			
				 
					List sttsDataList = (List) sttsListData.get(j);
					
					cell = row.createCell(j);
			        cell.setCellStyle(getCellStyle(workbook,hcs,hf));
			        
			        if(sttsDataList.get(i) != null && !"".equals(sttsDataList.get(i))){
			        	cell.setCellValue(new HSSFRichTextString(sttsDataList.get(i).toString()));
			        }else {
			        	cell.setCellValue(new HSSFRichTextString(""));
			        }
				}
			}
			
			InputStream is = new ClassPathResource(EXCEL_PATH + "/" + templateFileName).getInputStream();
			
			
			String streFileNm = "통계관리";
			String orgFileName = "통계관리";
			String baseDir = FwkMessageUtil.getMessage("EW.SYS.FILE.DIR");
			String dir = File.separator+FwkDateUtil.getCurrentDateAsString("yyyy") + File.separator+FwkDateUtil.getCurrentDateAsString("MM")+
					File.separator+FwkDateUtil.getCurrentDateAsString("dd") + 	File.separator;
			String fileFullPath = "통계관리";
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
			
			
			streFileNm = streFileNm.replaceAll("\\+", "%20");
			fileFullPath = baseDir + dir + File.separator + streFileNm +"." + ext;
			
			OutputStream bos = new FileOutputStream( fileFullPath );
			
			StringBuilder contentDisposition = new StringBuilder();
			contentDisposition.append("attachment;fileName=\"");
			contentDisposition.append(encodeFileName(destFileName) + ".xls");
			contentDisposition.append("\";");
			response.setHeader("Content-Disposition", contentDisposition.toString());
			response.setContentType("application/x-msexcel");
			workbook.write(bos);
			is.close();
			
			
			
		}else if("POI_RESULT".equals(excelGbn)) {
			// 평가결과 엑셀다운로드
			List<FwkDataEntity> EXCEL_TH_LIST = (List<FwkDataEntity>) model.get("EXCEL_TH_LIST");
			List<FwkDataEntity> EXCEL_TH_COL_LIST = (List<FwkDataEntity>) model.get("EXCEL_TH_COL_LIST");
			List<FwkDataEntity> EXCEL_TH_ROW_LIST = (List<FwkDataEntity>) model.get("EXCEL_TH_ROW_LIST");
			List<FwkDataEntity> EXCEL_X_LIST = (List<FwkDataEntity>) model.get("EXCEL_X_LIST");
			List<FwkDataEntity> EXCEL_Y_LIST = (List<FwkDataEntity>) model.get("EXCEL_Y_LIST");
			
			
			List<FwkDataEntity> estmResultProcdSeqList = (List<FwkDataEntity>) model.get("estmResultProcdSeqList");
			List<FwkDataEntity> estmResultItemNoList = (List<FwkDataEntity>) model.get("estmResultItemNoList");
			List<FwkDataEntity> estmResultCmtmAllList = (List<FwkDataEntity>) model.get("estmResultCmtmAllList");
			List<FwkDataEntity> estmResultObjAllList = (List<FwkDataEntity>) model.get("estmResultObjAllList");
			List<FwkDataEntity> estmResultItemAllList = (List<FwkDataEntity>) model.get("estmResultItemAllList");
			List<FwkDataEntity> estmResultProcdObjSlctList = (List<FwkDataEntity>) model.get("estmResultProcdObjSlctList");
			
			
			// 시트명 생성
			HSSFSheet sheet = workbook.createSheet("평가결과");
			
			// row 생성
			HSSFRow row = sheet.createRow(0);
			
			// 셀 크기 조절
			// 일정하게 넣는 다면
			for(int i = 0; i < EXCEL_TH_LIST.size(); i++) {
				sheet.setColumnWidth((int)i, (int)5000);
				//sheet.autoSizeColumn(i); // 사이즈 오토
			}
			
            // 타이틀 call 변수 생성
            HSSFCell cell = row.createCell((int)0);
            // 타이틀 스타일 변수 생성
            HSSFCellStyle titleHcs = workbook.createCellStyle();
            
            /**
             * 셀 세팅 방법
             * 해당 방법은 header부분이 rowspan 또는 colspan이 걸려있을 때 세팅해주는 방법이다.
             * 데이터 부분에서 셀병합은 광해소스를 참고 바람.
             * 
             * 1. maxCol과 maxRow의 값을 찾는다.
             * 2. 찾은 maxCol과 maxRow의 값을 가지고 row와 cell을 생성한다.
             * 3. 2번에서 세팅해준 내용을 row와 cell을 get해와서 필요한 위치에  데이터와 셀병합을 해준다.
             *    2번을 선행해야지 셀의 style이 깨지지 않음.
             * 
             */
            // 1.maxCol과 maxRow의 값을 찾는다.
            int maxCol = 0;
            int maxRow = 0;
            for(int i = 0; i < EXCEL_X_LIST.size(); i++) {
            	FwkDataEntity xEntity = EXCEL_X_LIST.get(i);
            	
            	//1. maxRow 찾기
            	if(maxRow < Integer.parseInt(xEntity.getString("VAL"))) {
            		maxRow = Integer.parseInt(xEntity.getString("VAL"));
            	}
            	
            	//2. maxCol 찾기
            	if(("0".equals(xEntity.getString("VAL")))) {
            		FwkDataEntity colEntity = EXCEL_TH_COL_LIST.get(i);
            		maxCol = maxCol + Integer.parseInt(colEntity.getString("VAL"));
            	}
            }
            
           int cellCount = 0;
           
           
           /**
            * 2. maxRow와 maxCol을 이용하여 셀병합할 영역에  row와 cell을 생성한다.
            */
           for(int i = 0; i <= maxRow; i++){
        	   row = sheet.createRow(i);
        	   for(int j = 0; j < maxCol; j++) {
        		   cell = row.createCell(j);
        		   cell.setCellStyle(getTitleStyle(workbook, titleHcs));
        	   }
           }
           
           
           /**
            * 3. 2번에서 세팅해준 내용을 row와 cell을 get해와서 필요한 위치에  데이터와 셀병합을 해준다.
            *    2번을 선행해야지 셀의 style이 깨지지 않음.
            * 
            * [코드 설명]
            * 가장 문제가 되었던 부분은 동적으로 동작할때 rowspan과 colspan이후에 cell의 위치를 잡는 것이였다.
            * 그래서 생각한 방법이 maxRow와 maxCol을 이용하여  2차원 배열을 생성하고 0으로 값을 세팅해준 후
            * rowspan 또는 colspan을 해줄 때마다 배열의 값을 1로 바꾸어줘서 EXCEL_TH_LIST에 세팅되어있는
            * 데이터가 들어갈 위치를 찾을 수 있게 구현하였다.
            * 
            * 
            * 
            */

           // 위치를 확인 할 수 있는 2차원 배열 생성
           int[][] array = new int[maxRow+1][maxCol];
           
           // 2차원배열의 값을 0으로 세팅
           for(int i = 0; i< maxRow+1; i++) { //i
        	   for(int j = 0; j<maxCol; j++) { //j
        		   array[i][j] = 0;
        	   }//j
           }//i
           
		   //int arrayCnt = 0;
		   
		   for(int i = 0; i < EXCEL_TH_LIST.size(); i++) { //i
			   FwkDataEntity thDataEntity = EXCEL_TH_LIST.get(i);
			   FwkDataEntity colEntity = EXCEL_TH_COL_LIST.get(i);
	           FwkDataEntity rowEntity = EXCEL_TH_ROW_LIST.get(i);
	           
	           int colE = Integer.parseInt(colEntity.getString("VAL"));
	           int rowE = Integer.parseInt(rowEntity.getString("VAL"));
	           
	           int CountI = i;
	           // array에서 가장 앞에 있는 0값 찾기
	           for(int r = 0; r < maxRow+1; r++) {//r
	        	   for( int c = 0; c < maxCol; c++) { //c
	        		   if(array[r][c] == 0 && CountI == i) { //array[r][c] == 0 && CountI == i

	        			   CountI++;
	        			   row = sheet.getRow(r);
	        			   cell = row.getCell(c);
	        			   cell.setCellValue(thDataEntity.getString("VAL"));
	        			   sheet.addMergedRegion(new CellRangeAddress(r,(r+rowE-1),c,(c+colE-1)));
	        			   
	        			   for(int rowI = 0; rowI < rowE; rowI++) { //rowI
	        				   for(int colI = 0; colI < colE; colI++){ //colI
	        					   
	        					   array[(r+rowI)][(c+colI)] = 1;
	        					   //System.err.println("array["+(r+rowI)+"]["+(c+colI)+"] :: "+ array[(r+rowI)][(c+colI)]);
	        				   }//colI
	        			   }//rowI
	        		   }//array[r][c] == 0 && CountI == i
	        	   }// c
	           }//r
           }//i
           
           
            
            // 내용 셀 스타일 생성
	        HSSFCellStyle hcs = workbook.createCellStyle();
	        
	        // 내용 셀 스타일 생성 (money format)
	        HSSFCellStyle hcsMformat = workbook.createCellStyle();
			
	        // 내용 셀 폰트 생성
			HSSFFont hf = workbook.createFont();
	        
			// 내용 셀 포멧형식 생성
			HSSFDataFormat format = workbook.createDataFormat();
			
			
			/**
			 * 평가결과 데이터 세팅
			 * 
			 */
			// maxRow-1부터 시작하기

			//maxCol
			//System.err.println("maxRow :: " + maxRow);
			int rowCount = maxRow+1;
			for(int i = 0; i < estmResultObjAllList.size(); i++) { //estmResultObjAllList
				int intCountCol = 0;
				FwkDataEntity obj = estmResultObjAllList.get(i);
				row = sheet.createRow(rowCount);
				
				cell = row.createCell(intCountCol); //순위
				cell.setCellStyle(getCellStyle(workbook,hcs,hf));
				cell.setCellValue(obj.getString("RNUM")); // 일단빈값
				intCountCol++;
				cell = row.createCell(intCountCol); //평가대상명
				cell.setCellStyle(getCellStyle(workbook,hcs,hf));
				cell.setCellValue(obj.getString("OBJ_NM")); // 평가대상명
				intCountCol++;
				
				//System.err.println("intCountCol start :: "+ intCountCol);
				
				for(int j = 0; j < estmResultProcdSeqList.size(); j++) { // for int j 
					FwkDataEntity procdSeq = estmResultProcdSeqList.get(j);
					
					// 평가위원일때 시작
					if("A".equals(procdSeq.getString("ESTR_SECD"))) { //if "A"
						for(int k = 0; k < estmResultCmtmAllList.size(); k++) { // int k
							FwkDataEntity cmtm = estmResultCmtmAllList.get(k);
							
							for(int l= 0; l < estmResultItemNoList.size(); l++) { // int l
								FwkDataEntity itemNo = estmResultItemNoList.get(l);
								
								for(int m = 0; m < estmResultItemAllList.size(); m++) { // int m
									FwkDataEntity itemAll = estmResultItemAllList.get(m);
									if( procdSeq.getString("ESTM_PROCD_SEQ").equals(itemAll.getString("ESTM_PROCD_SEQ"))
										&& (obj.getString("ESTM_OBJ_SEQ").equals(itemAll.getString("ESTM_OBJ_SEQ")))
									  ) { // procdSeq
										
										if((cmtm.getString("ESTM_CMTM_NO").equals(itemAll.getString("ESTM_CMTM_NO"))) 
											&& (itemNo.getString("ESTM_NUMBER").equals(itemAll.getString("ESTM_NUMBER")))
										) { //cmtm
											cell = row.createCell(intCountCol); 
											cell.setCellStyle(getCellStyle(workbook,hcs,hf));
											cell.setCellValue(itemAll.getString("ESTM_SCR"));
											intCountCol++;
											
										} //cmtm
									}// procdSeq
										
									
								}// int m
							} // int l
						}// int k
					}//if "A"
					// 평가위원일때 종료
					
					// 평가담당자일때 시작
					if("B".equals(procdSeq.getString("ESTR_SECD"))) { //if "B"
						for(int l= 0; l < estmResultItemNoList.size(); l++) { // int l
							FwkDataEntity itemNo = estmResultItemNoList.get(l);
							
							for(int m = 0; m < estmResultItemAllList.size(); m++) { // int m
								FwkDataEntity itemAll = estmResultItemAllList.get(m);
								if( procdSeq.getString("ESTM_PROCD_SEQ").equals(itemAll.getString("ESTM_PROCD_SEQ"))
									&& (obj.getString("ESTM_OBJ_SEQ").equals(itemAll.getString("ESTM_OBJ_SEQ")))
									&& (itemNo.getString("ESTM_NUMBER").equals(itemAll.getString("ESTM_NUMBER")))
								  ) { // procdSeq
									
										cell = row.createCell(intCountCol);
										cell.setCellStyle(getCellStyle(workbook,hcs,hf));
										cell.setCellValue(itemAll.getString("ESTM_SCR"));
										intCountCol++;
										
								}// procdSeq
							}// int m
						} // int l
					}//if "B"
					// 평가담당자일때 종료
					
					// 평가위원인 평가절차에서   평가총점이 존재시 시작
					if("A".equals(procdSeq.getString("ESTR_SECD"))) {
						for(int l= 0; l < estmResultItemNoList.size(); l++) { // int l
							FwkDataEntity itemNo = estmResultItemNoList.get(l);
							
							for(int m = 0; m < estmResultItemAllList.size(); m++) { // int m
								FwkDataEntity itemAll = estmResultItemAllList.get(m);
								if( procdSeq.getString("ESTM_PROCD_SEQ").equals(itemAll.getString("ESTM_PROCD_SEQ"))
									&& (obj.getString("ESTM_OBJ_SEQ").equals(itemAll.getString("ESTM_OBJ_SEQ")))
									&& (itemAll.getString("ESTM_NUMBER").contains("TOT-"))
									&& (itemNo.getString("ESTM_NUMBER").equals(itemAll.getString("ESTM_NUMBER")))
								  ) { // procdSeq
									
										cell = row.createCell(intCountCol);
										cell.setCellStyle(getCellStyle(workbook,hcs,hf));
										cell.setCellValue(itemAll.getString("ESTM_SCR"));
										intCountCol++;
										

								}// procdSeq
							}// int m
						} // int l
					}
					// 평가위원인 평가절차에서   평가총점이 존재시 종료
					
					//procdSeq.ESTM_PROCD_SECD eq 'C'
					// 평가절차구분이 적격인경우 - 적격여부
					if("C".equals(procdSeq.getString("ESTM_PROCD_SECD"))) { //"C".equals(procdSeq.getString("ESTM_PROCD_SECD")
						for(int slct = 0; slct < estmResultProcdObjSlctList.size(); slct++) { // int slct
							FwkDataEntity slctEntity = estmResultProcdObjSlctList.get(slct);
							if(procdSeq.getString("ESTM_PROCD_SEQ").equals(slctEntity.getString("ESTM_PROCD_SEQ")) 
								&& obj.getString("ESTM_OBJ_SEQ").equals(slctEntity.getString("ESTM_OBJ_SEQ"))
							){ // procdSeq.getString("ESTM_PROCD_SEQ").equals(slctEntity.getString("ESTM_PROCD_SEQ")) && obj.getString("ESTM_OBJ_SEQ").equals(slctEntity.getString("ESTM_OBJ_SEQ"))
								cell = row.createCell(intCountCol); //적격여부
								cell.setCellStyle(getCellStyle(workbook,hcs,hf));
								cell.setCellValue(slctEntity.getString("SLCT_YN_NM")); // 적격여부
								intCountCol++;
							} // procdSeq.getString("ESTM_PROCD_SEQ").equals(slctEntity.getString("ESTM_PROCD_SEQ")) && obj.getString("ESTM_OBJ_SEQ").equals(slctEntity.getString("ESTM_OBJ_SEQ"))
						}
					} //"C".equals(procdSeq.getString("ESTM_PROCD_SECD")
					
				}// for int j
				
				
				cell = row.createCell(intCountCol); //최종결과점수
				cell.setCellStyle(getCellStyle(workbook,hcs,hf));
				cell.setCellValue(obj.getString("CLC_TOT_SCR")); // 최종결과점수
				//intCountCol++;
				
				
				rowCount++;
			} //estmResultObjAllList
			
			
			
			InputStream is = new ClassPathResource(EXCEL_PATH + "/" + templateFileName).getInputStream();
			
			
			String streFileNm = "평가결과";
			String orgFileName = "평가결과";
			String baseDir = FwkMessageUtil.getMessage("EW.SYS.FILE.DIR");
			String dir = File.separator+FwkDateUtil.getCurrentDateAsString("yyyy") + File.separator+FwkDateUtil.getCurrentDateAsString("MM")+
					File.separator+FwkDateUtil.getCurrentDateAsString("dd") + 	File.separator;
			String fileFullPath = "평가결과";
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
			
			
			streFileNm = streFileNm.replaceAll("\\+", "%20");
			fileFullPath = baseDir + dir + File.separator + streFileNm +"." + ext;
			
			OutputStream bos = new FileOutputStream( fileFullPath );
			
			StringBuilder contentDisposition = new StringBuilder();
			contentDisposition.append("attachment;fileName=\"");
			contentDisposition.append(encodeFileName(destFileName) + ".xls");
			contentDisposition.append("\";");
			response.setHeader("Content-Disposition", contentDisposition.toString());
			response.setContentType("application/x-msexcel");
			workbook.write(bos);
			is.close();
			
			
			
		} else if("TEMPLATE".equals(excelGbn)) {
			
			templateFileName = (String) model.get("templateFileName");
			destFileName = (String) model.get("destFileName");
			
			XLSTransformer transformer = new XLSTransformer();
			
			InputStream is = new ClassPathResource(EXCEL_PATH + "/" + templateFileName).getInputStream();
			
			Workbook resultWorkbook = transformer.transformXLS(is, model);
			
			StringBuilder contentDisposition = new StringBuilder();
			contentDisposition.append("attachment;fileName=\"");
			contentDisposition.append(encodeFileName(destFileName) + ".xls");
			contentDisposition.append("\";");
			response.setHeader("Content-Disposition", contentDisposition.toString());
			response.setContentType("application/x-msexcel");
			resultWorkbook.write(response.getOutputStream());
			
		} else {
			templateFileName = (String) model.get("templateFileName");
			destFileName = (String) model.get("destFileName");
			
			XLSTransformer transformer = new XLSTransformer();
			
			InputStream is = new ClassPathResource(EXCEL_PATH + "/" + templateFileName).getInputStream();
			
			Workbook resultWorkbook = transformer.transformXLS(is, model);
			
			StringBuilder contentDisposition = new StringBuilder();
			contentDisposition.append("attachment;fileName=\"");
			contentDisposition.append(encodeFileName(destFileName) + ".xls");
			contentDisposition.append("\";");
			response.setHeader("Content-Disposition", contentDisposition.toString());
			response.setContentType("application/x-msexcel");
			resultWorkbook.write(response.getOutputStream());
		}
		
		
		
		
	}
	
	 private HSSFCellStyle getTitleStyle(HSSFWorkbook wb,HSSFCellStyle hcs) {
	     	//제목 폰트
	        HSSFFont hf = wb.createFont();
	         hf.setFontHeightInPoints((short) 11);
	         hf.setColor((short) HSSFColor.BLACK.index);
	         hf.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
	         //Header style setting
	         
	         hcs.setFont(hf);
	         hcs.setAlignment(HSSFCellStyle.ALIGN_CENTER);
	         //set border style
	         hcs.setBorderBottom(HSSFCellStyle.BORDER_THICK);
	         hcs.setBorderRight(HSSFCellStyle.BORDER_THIN);
	         hcs.setBorderLeft(HSSFCellStyle.BORDER_THIN);
	         hcs.setBorderTop(HSSFCellStyle.BORDER_THIN);
	         hcs.setBorderBottom(HSSFCellStyle.BORDER_THIN);
	         //set color
	         hcs.setFillBackgroundColor((short) HSSFColor.WHITE.index );
	         hcs.setFillForegroundColor((short) HSSFColor.YELLOW.index );
	         hcs.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
	         hcs.setLocked(true);
	         hcs.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
	         return hcs;
  }

	 // 셀스타일 기본 (가운데 정렬)
	 private HSSFCellStyle getCellStyle(HSSFWorkbook wb, HSSFCellStyle hcs, HSSFFont hf) {
	     	//제목 폰트
	        //HSSFFont hf = wb.createFont();
	         hf.setFontHeightInPoints((short) 11);
	         hf.setColor((short) HSSFColor.BLACK.index);
	         //hf.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
	         //Header style setting
	         //HSSFCellStyle hcs = wb.createCellStyle();
	         hcs.setFont(hf);
	         hcs.setAlignment(HSSFCellStyle.ALIGN_CENTER);
	         //set border style
	         hcs.setBorderBottom(HSSFCellStyle.BORDER_THICK);
	         hcs.setBorderRight(HSSFCellStyle.BORDER_THIN);
	         hcs.setBorderLeft(HSSFCellStyle.BORDER_THIN);
	         hcs.setBorderTop(HSSFCellStyle.BORDER_THIN);
	         hcs.setBorderBottom(HSSFCellStyle.BORDER_THIN);
	         //set color
	         hcs.setFillBackgroundColor((short) HSSFColor.WHITE.index );
	         hcs.setFillForegroundColor((short) HSSFColor.WHITE.index );
	         hcs.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
	         hcs.setLocked(true);
	         hcs.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
	         return hcs;
	 }
	 
	 // 셀스타일 기본 (money)
	 private HSSFCellStyle getMoneyCellStyle(HSSFWorkbook wb, HSSFCellStyle hcsMformat, HSSFFont hf, HSSFDataFormat format) {
	     	//제목 폰트
	         hf.setFontHeightInPoints((short) 11);
	         hf.setColor((short) HSSFColor.BLACK.index);
	         hcsMformat.setFont(hf);
	         hcsMformat.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
	         //set border style
	         hcsMformat.setBorderBottom(HSSFCellStyle.BORDER_THICK);
	         hcsMformat.setBorderRight(HSSFCellStyle.BORDER_THIN);
	         hcsMformat.setBorderLeft(HSSFCellStyle.BORDER_THIN);
	         hcsMformat.setBorderTop(HSSFCellStyle.BORDER_THIN);
	         hcsMformat.setBorderBottom(HSSFCellStyle.BORDER_THIN);
	         //set color
	         hcsMformat.setFillBackgroundColor((short) HSSFColor.WHITE.index );
	         hcsMformat.setFillForegroundColor((short) HSSFColor.WHITE.index );
	         hcsMformat.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
	         hcsMformat.setLocked(true);
	         hcsMformat.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
	         hcsMformat.setDataFormat((short)3);
	         return hcsMformat;
	 }
	 
	 // 셀스타일 기본 (date)
	 private HSSFCellStyle getDateCellStyle(HSSFWorkbook wb, HSSFCellStyle hcsMformat, HSSFFont hf, HSSFDataFormat format) {
	     	//제목 폰트
	         hf.setFontHeightInPoints((short) 11);
	         hf.setColor((short) HSSFColor.BLACK.index);
	         hcsMformat.setFont(hf);
	         hcsMformat.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
	         //set border style
	         hcsMformat.setBorderBottom(HSSFCellStyle.BORDER_THICK);
	         hcsMformat.setBorderRight(HSSFCellStyle.BORDER_THIN);
	         hcsMformat.setBorderLeft(HSSFCellStyle.BORDER_THIN);
	         hcsMformat.setBorderTop(HSSFCellStyle.BORDER_THIN);
	         hcsMformat.setBorderBottom(HSSFCellStyle.BORDER_THIN);
	         //set color
	         hcsMformat.setFillBackgroundColor((short) HSSFColor.WHITE.index );
	         hcsMformat.setFillForegroundColor((short) HSSFColor.WHITE.index );
	         hcsMformat.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
	         hcsMformat.setLocked(true);
	         hcsMformat.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
	         hcsMformat.setDataFormat((short)3);
	         return hcsMformat;
	 }
	
	
	
	private String encodeFileName(String filename) throws UnsupportedEncodingException {	
		filename = URLEncoder.encode(filename, "UTF-8");
		filename = filename.replaceAll("\\+", "%20");
		
		return filename;	
	}

	
}
