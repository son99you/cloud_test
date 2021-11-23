var FwkDateUtil = {};
var MONTH_NAMES = new Array('1','2','3','4','5','6','7','8','9','10','11','12');
var DAY_NAMES = new Array('0','1','2','3','4','5','6');

FwkDateUtil.LZ = function (x) {
	return(x<0||x>9? "":"0")+x;		
};

	
	
FwkDateUtil.getCurrentDate = function (format) {
		
var now = new Date ();
return FwkDateUtil.formatDate (now, format);
		
};

	
	
FwkDateUtil.formatDate = function (date, format) {


 format=format+"";	
 var result="";		
 var i_format=0;		
 var c="";		
 var token="";		
 var y=date.getYear()+"";		
 var M=date.getMonth()+1;		
 var d=date.getDate();		
 var E=date.getDay();		
 var H=date.getHours();		
 var m=date.getMinutes();		
 var s=date.getSeconds();		
 var yyyy,yy,MMM,MM,dd,hh,h,mm,ss,ampm,HH,H,KK,K,kk,k; 		
		
 var value=new Object();		
		
 if (y.length < 4) {		
 	y=""+(y-0+1900);		
 }		
		
 value["y"]=""+y;		
 value["yyyy"]=y;		
 value["yy"]=y.substring(2,4);		
 value["M"]=M;		
 value["MM"]=this.LZ(M);		
 value["MMM"]=MONTH_NAMES[M-1];		
 value["NNN"]=MONTH_NAMES[M+22];		
 value["d"]=d;		
 value["dd"]=this.LZ(d);		
 value["E"]=DAY_NAMES[E+7];		
 value["EE"]=DAY_NAMES[E];		
 value["H"]=H;		
 value["HH"]=this.LZ(H);		
 		
 if (H==0){value["h"]=12;}		
 else if (H>12){value["h"]=H-12;}		
 else {value["h"]=H;}		
 		
 value["hh"]=this.LZ(value["h"]);		
 if (H>11){value["K"]=H-12;} 		
 else {value["K"]=H;}				
 value["k"]=H+1;		
 value["KK"]=this.LZ(value["K"]);		
 value["kk"]=this.LZ(value["k"]);		
 if (H > 11) { value["a"]="PM"; }		
 else { value["a"]="AM"; }		
 value["m"]=m;		
 value["mm"]=this.LZ(m);		
 value["s"]=s;		
 value["ss"]=this.LZ(s);		
 while (i_format < format.length) {      		
 	c=format.charAt(i_format);       		
 	token="";       		
 			
 	while ((format.charAt(i_format)==c) && (i_format < format.length)) {               		
 		token += format.charAt(i_format++);       		
 	}        		
 			
 	if (value[token] != null) { 		
 		result=result + value[token]; 		
 	}  else { 		
 		result=result + token; 		
 	}		
 }       		
		
 return result;   		
};

	

FwkDateUtil.isDate = function (val, format) {
		
var date = FwkDateUtil.getDateFromFormat(val, format);
if(date == 0) {
return false;		
}
return true;
		
};

	
	

FwkDateUtil.getDateFromFormat = function (val, format) {
		
val=val+"";
format=format+"";
var i_val=0;
var i_format=0;
var c="";
var token="";
		
var x = 0;
var y = 0;
var now=new Date();
var year=now.getYear();
var month=now.getMonth()+1;
var date=1;
		
var hh=now.getHours();
var mm=now.getMinutes();
var ss=now.getSeconds();
var ampm="";
		
while (i_format < format.length) {
c=format.charAt(i_format);
token="";
while ((format.charAt(i_format)==c) && (i_format < format.length)) {
token += format.charAt(i_format++);
}
if (token=="yyyy" || token=="yy" || token=="y") {
if (token=="yyyy") { x=4;y=4; }
if (token=="yy")   { x=2;y=2; }
		
if (token=="y")    { x=2;y=4; }
year=FwkDateUtil._getInt(val,i_val,x,y);
if (year==null) { return 0; }
i_val += year.length;
if (year.length==2) {
if (year > 70) { year=1900+(year-0); }
else { year=2000+(year-0); }		
}}
		
else if (token=="MMM"||token=="NNN"){
month=0;
for (var i=0; i<FwkDateUtil.MONTH_NAMES.length; i++) {
var month_name=FwkDateUtil.MONTH_NAMES[i];
if (val.substring(i_val,i_val+month_name.length).toLowerCase()==month_name.toLowerCase()) {
if (token=="MMM"||(token=="NNN"&&i>11)) {
month=i+1;
if (month>12) { month -= 12; }
i_val += month_name.length;
break;
}}}
if ((month < 1)||(month>12)){return 0;}
}
		
else if (token=="EE"||token=="E"){
for (var i=0; i<FwkDateUtil.DAY_NAMES.length; i++) {
var day_name=FwkDateUtil.DAY_NAMES[i];
if (val.substring(i_val,i_val+day_name.length).toLowerCase()==day_name.toLowerCase()) {
i_val += day_name.length;
break;
}}}
else if (token=="MM"||token=="M") {
month=FwkDateUtil._getInt(val,i_val,token.length,2);
if(month==null||(month<1)||(month>12)){return 0;}
i_val+=month.length;}
else if (token=="dd"||token=="d") {
date=FwkDateUtil._getInt(val,i_val,token.length,2);
if(date==null||(date<1)||(date>31)){return 0;}
i_val+=date.length;}
else if (token=="hh"||token=="h") {
hh=FwkDateUtil._getInt(val,i_val,token.length,2);
if(hh==null||(hh<1)||(hh>12)){return 0;}
i_val+=hh.length;}
		
else if (token=="HH"||token=="H") {
hh=FwkDateUtil._getInt(val,i_val,token.length,2);
if(hh==null||(hh<0)||(hh>23)){return 0;}
i_val+=hh.length;}
		
else if (token=="KK"||token=="K") {
hh=FwkDateUtil._getInt(val,i_val,token.length,2);
if(hh==null||(hh<0)||(hh>11)){return 0;}
i_val+=hh.length;}
		
else if (token=="kk"||token=="k") {
hh=FwkDateUtil._getInt(val,i_val,token.length,2);
if(hh==null||(hh<1)||(hh>24)){return 0;}
i_val+=hh.length;hh--;}
		
else if (token=="mm"||token=="m") {
mm=FwkDateUtil._getInt(val,i_val,token.length,2);
if(mm==null||(mm<0)||(mm>59)){return 0;}
i_val+=mm.length;}
		
else if (token=="ss"||token=="s") {
ss=FwkDateUtil._getInt(val,i_val,token.length,2);
if(ss==null||(ss<0)||(ss>59)){return 0;}
i_val+=ss.length;}
		
else if (token=="a") {
if (val.substring(i_val,i_val+2).toLowerCase()=="am") {ampm="AM";}
else if (val.substring(i_val,i_val+2).toLowerCase()=="pm") {ampm="PM";}
else {return 0;}
i_val+=2;}
		
else {
if (val.substring(i_val,i_val+token.length)!=token) {return 0;}
else {i_val+=token.length;}
}}
		
if (i_val != val.length) { return 0; }
if (month==2) {
if ( ( (year%4==0)&&(year%100 != 0) ) || (year%400==0) ) {
if (date > 29){ return 0; }
}
else { if (date > 28) { return 0; } }
}
if ((month==4)||(month==6)||(month==9)||(month==11)) {
if (date > 30) { return 0; }
}
if (hh<12 && ampm=="PM") { hh=hh-0+12; }
else if (hh>11 && ampm=="AM") { hh-=12; }
var newdate=new Date(year,month-1,date,hh,mm,ss);
return newdate;
		
		
};

	
	
	

FwkDateUtil._getInt = function (str,i,minlength,maxlength) {
		
for (var x=maxlength; x>=minlength; x--) {
var token=str.substring(i,i+x);
if (token.length < minlength) { return null; }
if (FwkDateUtil._isInteger(token)) { return token; }
}
return null;
		
};

	

FwkDateUtil._isInteger = function (val) {
		
var digits="1234567890";
for (var i=0; i < val.length; i++) {
if (digits.indexOf(val.charAt(i))==-1) { return false; }
}
return true;
		
};
	