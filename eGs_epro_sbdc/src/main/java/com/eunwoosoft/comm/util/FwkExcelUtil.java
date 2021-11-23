package com.eunwoosoft.comm.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jxls.transformer.XLSTransformer;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.document.AbstractExcelView;

/**
 * <pre>
 * ew.fwk.prl.view 
 *    |_ FwkExcelView.java
 * 
 * </pre>
 * @date : 2015. 1. 14. 오후 2:38:56
 * @version : 1.0
 * @author : LIG시스템 김경용
 */
public class FwkExcelUtil extends AbstractExcelView {
	
	private static final String EXCEL_PATH = "excel";
	
	@Override
	protected void buildExcelDocument(Map<String, Object> model,
			HSSFWorkbook workbook, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String templateFileName = (String) model.get("templateFileName");
		String destFileName = (String) model.get("destFileName");
		
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
	
	
	private String encodeFileName(String filename) throws UnsupportedEncodingException {		
		return URLEncoder.encode(filename, "UTF-8");	
	}
	
    public static String getName(Cell cell, int cellIndex) {
        int cellNum = 0;
        if(cell != null) {
            cellNum = cell.getColumnIndex();
        }
        else {
            cellNum = cellIndex;
        }
        
        return CellReference.convertNumToColString(cellNum);
    }
    
    public static String getValue(Cell cell) {
        String value = "";
        
        if(cell == null) {
            value = "";
        }
        else {
            if( cell.getCellType() == Cell.CELL_TYPE_FORMULA ) {
                value = cell.getCellFormula();
            }
            else if( cell.getCellType() == Cell.CELL_TYPE_NUMERIC ) {
            	if(DateUtil.isCellDateFormatted(cell)) {
            		Date date = cell.getDateCellValue();
            		value = new SimpleDateFormat("yyyy-MM-dd").format(date);
            	}else {
            		value = cell.getNumericCellValue() + "";
            	}
            }
            else if( cell.getCellType() == Cell.CELL_TYPE_STRING ) {
                value = cell.getStringCellValue();
            }
            else if( cell.getCellType() == Cell.CELL_TYPE_BOOLEAN ) {
                value = cell.getBooleanCellValue() + "";
            }
            else if( cell.getCellType() == Cell.CELL_TYPE_ERROR ) {
                value = cell.getErrorCellValue() + "";
            }
            else if( cell.getCellType() == Cell.CELL_TYPE_BLANK ) {
                value = "";
            }
            else {
                value = cell.getStringCellValue();
            }
        }
        
        return value;
    }	
	
    public static Workbook getWorkbook(MultipartFile multipartFile) {
        /*
         * FileInputStream은 파일의 경로에 있는 파일을
         * 읽어서 Byte로 가져온다.
         * 
         * 파일이 존재하지 않는다면은
         * RuntimeException이 발생된다.
         */
    	/*
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(multipartFile.getInputStream());
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
        */
        
        Workbook wb = null;
        
        /*
         * 파일의 확장자를 체크해서 .XLS 라면 HSSFWorkbook에
         * .XLSX라면 XSSFWorkbook에 각각 초기화 한다.
         */
        if(multipartFile.getOriginalFilename().toUpperCase().endsWith(".XLS")) {
            try {
            	wb = new HSSFWorkbook(multipartFile.getInputStream()); 
                //wb = new HSSFWorkbook(fis);
            } catch (IOException e) {
                throw new RuntimeException(e.getMessage(), e);
            }
        }
        else if(multipartFile.getOriginalFilename().toUpperCase().endsWith(".XLSX")) {
            try {
            	wb = new XSSFWorkbook(multipartFile.getInputStream());
            } catch (IOException e) {
                throw new RuntimeException(e.getMessage(), e);
            }
        }
        
        return wb;
        
    }	

    public static List<Map<String, String>> read(ExcelReadOption excelReadOption) {
        //엑셀 파일 자체
        //엑셀파일을 읽어 들인다.
        //FileType.getWorkbook() <-- 파일의 확장자에 따라서 적절하게 가져온다.
        Workbook wb = getWorkbook(excelReadOption.getMultipartFile());
        /**
         * 엑셀 파일에서 첫번째 시트를 가지고 온다.
         */
        Sheet sheet = wb.getSheetAt(0);
        
        System.out.println("Sheet 이름: "+ wb.getSheetName(0)); 
        System.out.println("데이터가 있는 Sheet의 수 :" + wb.getNumberOfSheets());
        /**
         * sheet에서 유효한(데이터가 있는) 행의 개수를 가져온다.
         */
        int numOfRows = sheet.getPhysicalNumberOfRows();
        int numOfCells = 0;
        
        Row row = null;
        Cell cell = null;
        
        String cellName = "";
        /**
         * 각 row마다의 값을 저장할 맵 객체
         * 저장되는 형식은 다음과 같다.
         * put("A", "이름");
         * put("B", "게임명");
         */
        Map<String, String> map = null;
        /*
         * 각 Row를 리스트에 담는다.
         * 하나의 Row를 하나의 Map으로 표현되며
         * List에는 모든 Row가 포함될 것이다.
         */
        List<Map<String, String>> result = new ArrayList<Map<String, String>>(); 
        /**
         * 각 Row만큼 반복을 한다.
         */
        for(int rowIndex = excelReadOption.getStartRow() - 1; rowIndex < numOfRows; rowIndex++) {
            /*
             * 워크북에서 가져온 시트에서 rowIndex에 해당하는 Row를 가져온다.
             * 하나의 Row는 여러개의 Cell을 가진다.
             */
            row = sheet.getRow(rowIndex);
            
            if(row != null) {
                /*
                 * 가져온 Row의 Cell의 개수를 구한다.
                 */
            	//numOfCells = row.getPhysicalNumberOfCells();
            	numOfCells = row.getLastCellNum();
                /*
                 * 데이터를 담을 맵 객체 초기화
                 */
                map = new HashMap<String, String>();
                /*
                 * cell의 수 만큼 반복한다.
                 */
                for(int cellIndex = 0; cellIndex < numOfCells; cellIndex++) {
                    /*
                     * Row에서 CellIndex에 해당하는 Cell을 가져온다.
                     */
                    cell = row.getCell(cellIndex);
                    /*
                     * 현재 Cell의 이름을 가져온다
                     * 이름의 예 : A,B,C,D,......
                     */
                    cellName = getName(cell, cellIndex);
                    /*
                     * 추출 대상 컬럼인지 확인한다
                     * 추출 대상 컬럼이 아니라면, 
                     * for로 다시 올라간다
                     */
                    if( !excelReadOption.getOutputColumns().contains(cellName) ) {
                        continue;
                    }
                    /*
                     * map객체의 Cell의 이름을 키(Key)로 데이터를 담는다.
                     */
                    map.put(cellName, getValue(cell));
                }
                /*
                 * 만들어진 Map객체를 List로 넣는다.
                 */
                result.add(map);
                
            }
            
        }
        
        return result;
        
    }	
}
