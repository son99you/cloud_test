/*
 * <pre>
 * Copyright (c) 2014 KOICA.
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information
 *
 * </pre>
 */
package com.eunwoosoft.frwk.prl.ui.js; 

import java.io.IOException;
import java.io.Writer;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 공통유틸리티자바스크립트생성 클래스
 * 
 * @author : 
 * @date : 2014. 11. 13.
 * @version : 1.0
 */
@Controller
@RequestMapping(value = "/fwk")
public class FwkDateUtilJavascriptController__ {
	
	/**
	 * 날짜 유틸리티 자바스크립 Write
	 * 
	 * @Method Name : writeJs 
	 * @param out
	 * @param request
	 * @throws IOException
	 * @author :  
	 * @date : 2014. 11. 13.
	 */
	@RequestMapping(value = "/DateUtil__.js",method = RequestMethod.POST)
	public void dateUtil(Writer out, HttpServletRequest request) throws IOException {		
		
		out.write("var FwkDateUtil = {};");		
		
		out.write("var MONTH_NAMES = new Array('1','2','3','4','5','6','7','8','9','10','11','12');");
		out.write("var DAY_NAMES = new Array('0','1','2','3','4','5','6');");
		
		LZ(out);
		getCurrentDate(out);
		formatDate(out);
		isDate(out);
		getDateFromFormat(out);
		getInt(out);
		isInteger(out);
		
		
		
	}
	
	private void LZ(Writer out) throws IOException {	
		out.write("FwkDateUtil.LZ = function (x) {");
		out.write("return(x<0||x>9?\"\":\"0\")+x;");		
		out.write("};");
	}
	
	private void getCurrentDate(Writer out) throws IOException {	
		out.write("FwkDateUtil.getCurrentDate = function (format) {");
		
		out.write("var now = new Date ();");
		out.write("return FwkDateUtil.formatDate (now, format);");
		
		out.write("};");
	}
	
	private void formatDate(Writer out) throws IOException {	
		out.write("FwkDateUtil.formatDate = function (date, format) {");
		
		
		out.write(" format=format+\"\";		");
		out.write(" var result=\"\";		");
		out.write(" var i_format=0;		");
		out.write(" var c=\"\";		");
		out.write(" var token=\"\";		");
		out.write(" var y=date.getYear()+\"\";		");
		out.write(" var M=date.getMonth()+1;		");
		out.write(" var d=date.getDate();		");
		out.write(" var E=date.getDay();		");
		out.write(" var H=date.getHours();		");
		out.write(" var m=date.getMinutes();		");
		out.write(" var s=date.getSeconds();		");
		out.write(" var yyyy,yy,MMM,MM,dd,hh,h,mm,ss,ampm,HH,H,KK,K,kk,k; 		");
		
		out.write(" var value=new Object();		");
		
		out.write(" if (y.length < 4) {		");
		out.write(" 	y=\"\"+(y-0+1900);		");
		out.write(" }		");
		
		out.write(" value[\"y\"]=\"\"+y;		");
		out.write(" value[\"yyyy\"]=y;		");
		out.write(" value[\"yy\"]=y.substring(2,4);		");
		out.write(" value[\"M\"]=M;		");
		out.write(" value[\"MM\"]=this.LZ(M);		");
		out.write(" value[\"MMM\"]=MONTH_NAMES[M-1];		");
		out.write(" value[\"NNN\"]=MONTH_NAMES[M+22];		");
		out.write(" value[\"d\"]=d;		");
		out.write(" value[\"dd\"]=this.LZ(d);		");
		out.write(" value[\"E\"]=DAY_NAMES[E+7];		");
		out.write(" value[\"EE\"]=DAY_NAMES[E];		");
		out.write(" value[\"H\"]=H;		");
		out.write(" value[\"HH\"]=this.LZ(H);		");
		out.write(" 		");
		out.write(" if (H==0){value[\"h\"]=12;}		");
		out.write(" else if (H>12){value[\"h\"]=H-12;}		");
		out.write(" else {value[\"h\"]=H;}		");
		out.write(" 		");
		out.write(" value[\"hh\"]=this.LZ(value[\"h\"]);		");
		out.write(" if (H>11){value[\"K\"]=H-12;} 		");
		out.write(" else {value[\"K\"]=H;}		");		
		out.write(" value[\"k\"]=H+1;		");
		out.write(" value[\"KK\"]=this.LZ(value[\"K\"]);		");
		out.write(" value[\"kk\"]=this.LZ(value[\"k\"]);		");
		out.write(" if (H > 11) { value[\"a\"]=\"PM\"; }		");
		out.write(" else { value[\"a\"]=\"AM\"; }		");
		out.write(" value[\"m\"]=m;		");
		out.write(" value[\"mm\"]=this.LZ(m);		");
		out.write(" value[\"s\"]=s;		");
		out.write(" value[\"ss\"]=this.LZ(s);		");
		out.write(" while (i_format < format.length) {      		");
		out.write(" 	c=format.charAt(i_format);       		");
		out.write(" 	token=\"\";       		");
		out.write(" 			");
		out.write(" 	while ((format.charAt(i_format)==c) && (i_format < format.length)) {               		");
		out.write(" 		token += format.charAt(i_format++);       		");
		out.write(" 	}        		");
		out.write(" 			");
		out.write(" 	if (value[token] != null) { 		");
		out.write(" 		result=result + value[token]; 		");
		out.write(" 	}  else { 		");
		out.write(" 		result=result + token; 		");
		out.write(" 	}		");
		out.write(" }       		");
		
		out.write(" return result;   		");
		out.write("};");
	}
	
	private void isDate(Writer out) throws IOException {	
		out.write("FwkDateUtil.isDate = function (val, format) {");
		
		out.write("var date = FwkDateUtil.getDateFromFormat(val, format);");
		out.write("if(date == 0) {");
		out.write("return false;");		
		out.write("}");
		out.write("return true;");
		
		out.write("};");
	}
	
	
	private void getDateFromFormat(Writer out) throws IOException {	
		out.write("FwkDateUtil.getDateFromFormat = function (val, format) {");
		
		out.write("val=val+\"\";");
		out.write("format=format+\"\";");
		out.write("var i_val=0;");
		out.write("var i_format=0;");
		out.write("var c=\"\";");
		out.write("var token=\"\";");
		
		out.write("var x = 0;");
		out.write("var y = 0;");
		out.write("var now=new Date();");
		out.write("var year=now.getYear();");
		out.write("var month=now.getMonth()+1;");
		out.write("var date=1;");
		
		out.write("var hh=now.getHours();");
		out.write("var mm=now.getMinutes();");
		out.write("var ss=now.getSeconds();");
		out.write("var ampm=\"\";");
		
		out.write("while (i_format < format.length) {");
		out.write("c=format.charAt(i_format);");
		out.write("token=\"\";");
		out.write("while ((format.charAt(i_format)==c) && (i_format < format.length)) {");
		out.write("token += format.charAt(i_format++);");
		out.write("}");
		out.write("if (token==\"yyyy\" || token==\"yy\" || token==\"y\") {");
		out.write("if (token==\"yyyy\") { x=4;y=4; }");
		out.write("if (token==\"yy\")   { x=2;y=2; }");
		
		out.write("if (token==\"y\")    { x=2;y=4; }");
		out.write("year=FwkDateUtil._getInt(val,i_val,x,y);");
		out.write("if (year==null) { return 0; }");
		out.write("i_val += year.length;");
		out.write("if (year.length==2) {");
		out.write("if (year > 70) { year=1900+(year-0); }");
		out.write("else { year=2000+(year-0); }");		
		out.write("}}");
		
		out.write("else if (token==\"MMM\"||token==\"NNN\"){");
		out.write("month=0;");
		out.write("for (var i=0; i<FwkDateUtil.MONTH_NAMES.length; i++) {");
		out.write("var month_name=FwkDateUtil.MONTH_NAMES[i];");
		out.write("if (val.substring(i_val,i_val+month_name.length).toLowerCase()==month_name.toLowerCase()) {");
		out.write("if (token==\"MMM\"||(token==\"NNN\"&&i>11)) {");
		out.write("month=i+1;");
		out.write("if (month>12) { month -= 12; }");
		out.write("i_val += month_name.length;");
		out.write("break;");
		out.write("}}}");
		out.write("if ((month < 1)||(month>12)){return 0;}");
		out.write("}");
		
		out.write("else if (token==\"EE\"||token==\"E\"){");
		out.write("for (var i=0; i<FwkDateUtil.DAY_NAMES.length; i++) {");
		out.write("var day_name=FwkDateUtil.DAY_NAMES[i];");
		out.write("if (val.substring(i_val,i_val+day_name.length).toLowerCase()==day_name.toLowerCase()) {");
		out.write("i_val += day_name.length;");
		out.write("break;");
		out.write("}}}");
		out.write("else if (token==\"MM\"||token==\"M\") {");
		out.write("month=FwkDateUtil._getInt(val,i_val,token.length,2);");
		out.write("if(month==null||(month<1)||(month>12)){return 0;}");
		out.write("i_val+=month.length;}");
		out.write("else if (token==\"dd\"||token==\"d\") {");
		out.write("date=FwkDateUtil._getInt(val,i_val,token.length,2);");
		out.write("if(date==null||(date<1)||(date>31)){return 0;}");
		out.write("i_val+=date.length;}");
		out.write("else if (token==\"hh\"||token==\"h\") {");
		out.write("hh=FwkDateUtil._getInt(val,i_val,token.length,2);");
		out.write("if(hh==null||(hh<1)||(hh>12)){return 0;}");
		out.write("i_val+=hh.length;}");
		
		out.write("else if (token==\"HH\"||token==\"H\") {");
		out.write("hh=FwkDateUtil._getInt(val,i_val,token.length,2);");
		out.write("if(hh==null||(hh<0)||(hh>23)){return 0;}");
		out.write("i_val+=hh.length;}");
		
		out.write("else if (token==\"KK\"||token==\"K\") {");
		out.write("hh=FwkDateUtil._getInt(val,i_val,token.length,2);");
		out.write("if(hh==null||(hh<0)||(hh>11)){return 0;}");
		out.write("i_val+=hh.length;}");
		
		out.write("else if (token==\"kk\"||token==\"k\") {");
		out.write("hh=FwkDateUtil._getInt(val,i_val,token.length,2);");
		out.write("if(hh==null||(hh<1)||(hh>24)){return 0;}");
		out.write("i_val+=hh.length;hh--;}");
		
		out.write("else if (token==\"mm\"||token==\"m\") {");
		out.write("mm=FwkDateUtil._getInt(val,i_val,token.length,2);");
		out.write("if(mm==null||(mm<0)||(mm>59)){return 0;}");
		out.write("i_val+=mm.length;}");
		
		out.write("else if (token==\"ss\"||token==\"s\") {");
		out.write("ss=FwkDateUtil._getInt(val,i_val,token.length,2);");
		out.write("if(ss==null||(ss<0)||(ss>59)){return 0;}");
		out.write("i_val+=ss.length;}");
		
		out.write("else if (token==\"a\") {");
		out.write("if (val.substring(i_val,i_val+2).toLowerCase()==\"am\") {ampm=\"AM\";}");
		out.write("else if (val.substring(i_val,i_val+2).toLowerCase()==\"pm\") {ampm=\"PM\";}");
		out.write("else {return 0;}");
		out.write("i_val+=2;}");
		
		out.write("else {");
		out.write("if (val.substring(i_val,i_val+token.length)!=token) {return 0;}");
		out.write("else {i_val+=token.length;}");
		out.write("}}");
		
		out.write("if (i_val != val.length) { return 0; }");
		out.write("if (month==2) {");
		out.write("if ( ( (year%4==0)&&(year%100 != 0) ) || (year%400==0) ) {");
		out.write("if (date > 29){ return 0; }");
		out.write("}");
		out.write("else { if (date > 28) { return 0; } }");
		out.write("}");
		out.write("if ((month==4)||(month==6)||(month==9)||(month==11)) {");
		out.write("if (date > 30) { return 0; }");
		out.write("}");
		out.write("if (hh<12 && ampm==\"PM\") { hh=hh-0+12; }");
		out.write("else if (hh>11 && ampm==\"AM\") { hh-=12; }");
		out.write("var newdate=new Date(year,month-1,date,hh,mm,ss);");
		out.write("return newdate;");
		
		
		out.write("};");
	}
	
	
	
	private void getInt(Writer out) throws IOException {	
		out.write("FwkDateUtil._getInt = function (str,i,minlength,maxlength) {");
		
		out.write("for (var x=maxlength; x>=minlength; x--) {");
		out.write("var token=str.substring(i,i+x);");
		out.write("if (token.length < minlength) { return null; }");
		out.write("if (FwkDateUtil._isInteger(token)) { return token; }");
		out.write("}");
		out.write("return null;");
		
		out.write("};");
	}
	
	private void isInteger(Writer out) throws IOException {	
		out.write("FwkDateUtil._isInteger = function (val) {");
		
		out.write("var digits=\"1234567890\";");
		out.write("for (var i=0; i < val.length; i++) {");
		out.write("if (digits.indexOf(val.charAt(i))==-1) { return false; }");
		out.write("}");
		out.write("return true;");
		
		out.write("};");
	}		 

	
}

