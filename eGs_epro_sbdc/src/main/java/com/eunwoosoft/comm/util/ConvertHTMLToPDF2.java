package com.eunwoosoft.comm.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.StringReader;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

import com.eunwoosoft.frwk.fol.util.FwkMessageUtil;
import com.itextpdf.text.Document;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorker;
import com.itextpdf.tool.xml.XMLWorkerFontProvider;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import com.itextpdf.tool.xml.css.CssFile;
import com.itextpdf.tool.xml.css.StyleAttrCSSResolver;
import com.itextpdf.tool.xml.html.CssAppliers;
import com.itextpdf.tool.xml.html.CssAppliersImpl;
import com.itextpdf.tool.xml.html.Tags;
import com.itextpdf.tool.xml.parser.XMLParser;
import com.itextpdf.tool.xml.pipeline.css.CSSResolver;
import com.itextpdf.tool.xml.pipeline.css.CssResolverPipeline;
import com.itextpdf.tool.xml.pipeline.end.PdfWriterPipeline;
import com.itextpdf.tool.xml.pipeline.html.HtmlPipeline;
import com.itextpdf.tool.xml.pipeline.html.HtmlPipelineContext;


public class ConvertHTMLToPDF2 {
 
	
  public static Map<String,String> convert(String filePath, String htmlCont) {

	  
	  Map<String,String> map = new HashMap<String,String>();
	  String charSet = FwkMessageUtil.getMessage("IPRO.PDF.CHARSET"); 
	  
	  try{
		  
	      // Document 생성
	      Document document = new Document(PageSize.A4, 50, 50, 50, 50); // 용지 및 여백 설정
	
	      // PdfWriter 생성
	      PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(filePath)); // 바로 다운로드.
	      //PdfWriter writer = PdfWriter.getInstance(document, response.getOutputStream());
	      writer.setInitialLeading(12.5f);
	
	      // Document 오픈
	      document.open();
	      XMLWorkerHelper helper = XMLWorkerHelper.getInstance();
	            
	      // CSS
	      String cssPath = FwkMessageUtil.getMessage("IPRO.SERVER.WEBAPP.PATH") + "statics/css/comm/pdfPring.css";
	      CSSResolver cssResolver = new StyleAttrCSSResolver();
	      CssFile cssFile = helper.getCSS(new FileInputStream(cssPath));
	      cssResolver.addCss(cssFile);
	
	      // HTML, 폰트 설정 - 한글을 지원하는 폰트를 사용해야한다. 확장자는 반드시 ttf
	      String fontPath = FwkMessageUtil.getMessage("IPRO.SERVER.WEBAPP.PATH") + "statics/css/font/malgun.ttf";
	      XMLWorkerFontProvider fontProvider = new XMLWorkerFontProvider(XMLWorkerFontProvider.DONTLOOKFORFONTS);
	      fontProvider.register(fontPath, "MalgunGothic"); // MalgunGothic은 alias,
	      CssAppliers cssAppliers = new CssAppliersImpl(fontProvider);
	
	      HtmlPipelineContext htmlContext = new HtmlPipelineContext(cssAppliers);
	      htmlContext.setTagFactory(Tags.getHtmlTagProcessorFactory());
	
	      // Pipelines
	      PdfWriterPipeline pdf = new PdfWriterPipeline(document, writer);
	      HtmlPipeline html = new HtmlPipeline(htmlContext, pdf);
	      CssResolverPipeline css = new CssResolverPipeline(cssResolver, html);
	
	        
	      XMLWorker worker = new XMLWorker(css, true);
	      //XMLParser xmlParser = new XMLParser(worker, Charset.forName("UTF-8"));
	      XMLParser xmlParser = new XMLParser(worker, Charset.forName(charSet));
	
	      String htmlStr = htmlCont;
	      String srcPath = FwkMessageUtil.getMessage("IPRO.SERVER.WEBAPP.PATH2");
	
	      htmlStr = htmlStr.replaceAll("<br>", "<br/>");
	      htmlStr = htmlStr.replaceAll("<Meta Http-Equiv='content-Type' Content='text/Html; Charset=Euc-Kr'>", "");
	      // 이미지 태그 절대경로 사용해야 한다.(반드시)
	      htmlStr = htmlStr.replaceAll("src='", "src='"+srcPath);
	      //이미지 태그 안닫힌 태그들 찾아서 닫는 작업 진행
	
	      /*Pattern pattern  =  Pattern.compile("</p><img[^>]*src=[\"']?([^>\"']+)[\"']?[^>]*>");
	      Matcher match = pattern.matcher(htmlStr);
	      while(match.find()){ 
	          String imgTag   = match.group();
	          String imgTag2  = imgTag.replaceAll(">", "/>");
	          htmlStr             = htmlStr.replaceAll(imgTag, imgTag2);
	      }
	      Pattern pattern2  =  Pattern.compile("<col[^>]*style=[\"']?([^>\"']+)[\"']?[^>]*>");
	      Matcher match2 = pattern2.matcher(dataContent);
	      while(match2.find()){ 
	          String colTag       = match2.group();
	          String colTag2  = colTag.replaceAll(">", "/>");
	          dataContent         = dataContent.replaceAll(colTag, colTag2);
	      }
	      */
	      
	      //htmlStr = "<html><head></head><body style='font-family:MalgunGothic;'>" + htmlStr + "</body></html>";
	      
	      StringReader strReader = new StringReader(htmlStr);
	      xmlParser.parse(strReader);
	      document.close();
	      
	      File file = new File(filePath);
	      map.put("fileSize" , String.valueOf(file.length()));
	      map.put("fileLoc"  , filePath);
		
	  }catch(Exception e){
		  e.printStackTrace();
	  }
	  
	  return map;
	  
	 }
 }