package com.eunwoosoft.comm.util;

import java.util.HashMap;
import java.util.Map;

import com.itextpdf.text.pdf.PdfWriter;


public class ConvertHTMLToPDF {
 
  public static Map<String,String> convert(String filePath, String htmlCont) {     
	  
	  Map<String,String> map = new HashMap<String,String>();
	  
	  //String file = "c:\\test.pdf";   
  
	  PdfWriter pdfWriter = null;       
	  
	  try{
	  
	    //create a new document   
	    /*Document document = new Document();       
	      
	    //get Instance of the PDFWriter    
	    pdfWriter = PdfWriter.getInstance(document, new FileOutputStream(filePath));         
	 
	    document.setPageSize(PageSize.A4);      
	 
	    document.open();         
	
	    HTMLWorker htmlWorker = new HTMLWorker(document);
	    
	    HashMap<String,Object> interfaceProps = new HashMap<String,Object>();
	 
	    StyleSheet styles = new StyleSheet();
	
	    String fontPath = FwkMessageUtil.getMessage("IPRO.SERVER.WEBAPP.PATH") + "statics/css/font/malgun.ttf";
	    PdfDefaultFontProvider dfp = new PdfDefaultFontProvider(fontPath);
	    //폰트 파일 설정 (한글 나오게 하기 위해 설정 필요함
	    interfaceProps.put(HTMLWorker.FONT_PROVIDER,dfp);
	
	    StringBuffer sb = new StringBuffer();
	    sb.append(htmlCont);
	    
	    System.out.println("html:"+sb.toString());
	    
	
	    StringReader strReader = new StringReader(sb.toString());
	
	    List<Element> objects = htmlWorker.parseToList(strReader, styles, interfaceProps);
	    
	    for (int k = 0; k < objects.size(); ++k){
	     document.add((Element) objects.get(k));
	    }
	    document.close();   
	    
	    //close the writer    
		
	    File file = new File(filePath);
		map.put("fileSize" , String.valueOf(file.length()));
		map.put("fileLoc"  , filePath);*/
		
	    //pdfWriter.close();      
	  }catch(Exception e){
	   e.printStackTrace();
	  }
	  
	  return map;
	  
	 }
 }