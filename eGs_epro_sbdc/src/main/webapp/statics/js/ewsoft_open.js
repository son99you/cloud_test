//초기화 함수 필수
$(document).ready(function(){
	/*
    nxTSPKI.onInit(function(){ 
		//nxTSPKI.init 함수 완료 후 실행해야 하는 함수나 기능 작성 위치
		//alert("Init 완료");
	});

	nxTSPKI.init(true);
	*/
});
    
function verify_complete_callback(res) {
	var fom_fb = document.form_input;
    if(res.code == 0) {
        //fom_fb.cert.value = res.data.certInfo.cert ;
     // rtnStr= fom_fb.PKI.returnArg(2) ;

        //fom_fb.certMedia.value = res.data ;

        //fom_fb.mediaName.value = getMediaNameKor() ;
        
        fom_fb.certAuth.value = getCertAuthName(getCertAuthority(res.data.certInfo.subjectDN));  	//인증서 발행기관 이름
        
        //fom_fb.certAuthcode.value = getCertAuthority(res.data.subjectDN) ; //인증서 발행기관 코드
        
		fom_fb.certDn.value = res.data.certInfo.subjectDN ;					//인증서 DN
		
		fom_fb.smtDate.value = getCurrentDate();		//제출일자

        rtnStr = res.data.certInfo.validFrom;		//유효기간 From
		fom_fb.appDate.value = rtnStr.substring(0,4)+  rtnStr.substring(5,7) + rtnStr.substring(8,10);
        
        rtnStr ="";
		rtnStr = res.data.certInfo.validTo ; //유효기간 To
		fom_fb.expDate.value = rtnStr.substring(0,4)+  rtnStr.substring(5,7) + rtnStr.substring(8,10);
    
    
    	fom_fb.certOid.value = res.data.certInfo.certPolicy ; //OID
    	
    	fom_fb.certSerial.value = res.data.certInfo.serialNumberHex ; //Serial

    	alert("인증서가 선택되었습니다.\n\n\"인증서제출\" 버튼을 클릭하면 인증서가 등록됩니다.");
    }
    else {
        alert(res.errorMessage);
    }
}

function fncSelectCertificate_open(param_ssn) {
    var ssn = param_ssn;
    //nxTSPKI.verifyVID(ssn,{},verify_complete_callback);
}

/*
function makeBid_complete_callback(res) {
	
	var	f	= document.forms[0];
	var signData;
	
	
    if (res.code == 0) {
    	
    	if( res.data[0].certInfo.subjectDN != f.DN.value){
    		alert("제출된 인증서가 아닙니다.");
    		return false;
    	}
    	
    	f.encEsmtSpec.value = res.data[0].signedData;
    	
    	f.action	= "/supplier/ebid/ebidInfoTenderView.do";
		f.target	= "_self";
		//f.smSubmit();
    	
    }
    else {
        alert("error code = " + res.code + ", message = " + res.errorMessage);
    }
    
    
}

function fncSignAndEnvelopData_open2(strOpenerKmCert, inMsg) {
	var	f	= document.forms[0];
	
	var data = [];
	var kmCertInfo = {};
	var options = {};
	var tempData = {};
	tempData.data = inMsg;
	
	kmCertInfo = {cert : strOpenerKmCert};
	
	data.push(tempData);
	
	
	nxTSPKI.makeBiddingData(data, kmCertInfo,  options, makeBid_complete_callback);
	
	
}
*/

function envelop_complete_callback(res) {
	var	f	= document.forms[0];
	
    if (res.code == 0) { 
        f.encEsmtSpec.value = res.data;
    	
    	f.action	= "/supplier/ebid/ebidInfoTenderView.do";
		f.target	= "_self";
		f.smSubmit();
    }
    else {
        alert("error code = " + res.code + ", message = " + res.errorMessage);
    }
}

function envelopData(signedData) {
	var	f	= document.forms[0];
	
    var data = signedData;
    var cert = f.OpenerKmCert.value;
    
    f.encEsmtSpec.value = "";
    //nxTSPKI.envelopData(data,cert,{}, envelop_complete_callback);
}

function fncSignAndEnvelopData_open_complete_callback_1(res) {
	var	f	= document.forms[0];
	
    if (res.code == 0) {
    	if( res.data.certInfo.subjectDN != f.DN.value){
    		alert("제출된 인증서가 아닙니다.");
    		return false;
    	}
    	envelopData(res.data.signedData);
    	
    }
    else {
        alert("error code = " + res.code + ", message = " + res.errorMessage);
    }
}

function fncSignAndEnvelopData_open(strOpenerKmCert, inMsg) {
	var	f	= document.forms[0];
	
	var options = {};
	
	//nxTSPKI.signData(inMsg, options, fncSignAndEnvelopData_open_complete_callback_1);
	
}

function contSignData_open_complete_callback(res) {
	//var	f	= document.forms[0];
	//alert("contSignData_open_complete_callback 호출!!!!!!!!!!!");
	var f = document.forms["signFrm"];
	/**
	 * 공항철도와 코이카 변수명 매칭
	 * 공항철도 서명테이블명 : T_CT_ESIGN , 코이카 서명테이블명 : TECT_ELSI
	 * 공항철도 fileHash ==> 코이카 FILE_HASH_VAL
	 * 공항철도 certDn ====> 코이카 CERT_INHR_VAL 
	 * */
    if(f.P_FILE_HASH_VAL.length == undefined) {
    	//alert(res.signature);
    	f.P_ELEC_SIGN_VAL.value  = res.signature;
    }else{
    	for (var i = 0 ; i < f.P_ELEC_SIGN_VAL.length ; i++ ) {
    		//alert(res.signature[i]);
    		f.P_ELEC_SIGN_VAL[i].value = res.signature[i];
    	}
    }	
	
    /*var option = {
    		encoding : 'base64'
    		, charset : 'utf-8'
    		, signType : '2'
    };*/
    
    pageObj.contSignRegistSend();
	//yettie.verifySignature(res.signature, '', option, pageObj.contSignRegistSend, pageObj.errorcallback);
	
	/*
    if (res.code == 0) {   	
    	if (f.P_FILE_HASH_VAL.length == undefined)
        {
    		f.P_ELEC_SIGN_VAL.value  = res.data[0].signedData;
        }else{
        	for (var i = 0 ; i < f.P_FILE_HASH_VAL.length ; i++ ) {
        		f.P_ELEC_SIGN_VAL[i].value  = res.data[i].signedData;
        	}
        }
    	
    	f.actionCode.value	= "signSupplier";
    	f.action	= "/supplier/contract/contractMainViewProcessSign.do";
    	f.target	= "_self";
    	f.smSubmit();
    	
    	//FwkCmmnUtil.submitForm("signFrm", "/opro/cont/contSignRegist.do");
    	
    	
    	if (f.fileHash.length == undefined)
        {
    		if( res.data[0].certInfo.subjectDN != f.certDn.value){
        		alert("제출된 인증서가 아닙니다.");
        		return false;
        	}
    		
    		f.esignVal.value  = res.data[0].signedData;
        }else{
        	for ( i = 0 ; i < f.fileHash.length ; i++ ) {
        		if( res.data[i].certInfo.subjectDN != f.certDn[i].value){
            		alert("제출된 인증서가 아닙니다.");
            		return false;
            	}
        		
        		f.esignVal[i].value  = res.data[i].signedData;
        	}
        }
    	f.actionCode.value	= "signSupplier";
    	f.action	= "/supplier/contract/contractMainViewProcessSign.do";
    	f.target	= "_self";
    	f.smSubmit();
    	
    }
    else {
        alert("error code = " + res.code + ", message = " + res.errorMessage);
    }
    */
    
    
}

function contSignData_open() {
	
	var f = document.forms["signFrm"];
	
    var options = {};
    var tempData = [];
    
    if(f.P_FILE_HASH_VAL.length == undefined) {
		if ( f.P_FILE_HASH_VAL.value.length > 0 ){
			tempData[0] = f.P_FILE_HASH_VAL.value;
			//f.fileHash.value;
		} else {
			alert("정상적인 계약서가 아닙니다.");
			return;
		}
    }else{
    	
    	/*var $form_list = $(document).find('form[name=signFrm]');
    	$.each($form_list, function(idx, item){
    		
    	});*/
    	
    	for (var i = 0 ; i < f.P_FILE_HASH_VAL.length ; i++ ) {
    		
    		if ( f.P_FILE_HASH_VAL[i].value.length > 0 ){
    		//if ( $("input[name='P_FILE_HASH_VAL']").val().length > 0 ){
    			tempData[i] = f.P_FILE_HASH_VAL[i].value;
    		} else {
    			alert("정상적인 계약서가 아닙니다.");
    			return;
    		}	
    		
    	}
    }
    /*if (f.fileHash.length == undefined)
    {
    	if (f.certDn.value == "")
		{
			alert("인증서 정보가 없습니다.");
			return;
		}
		
		if ( f.fileHash.value.length > 0 ){
			tempData[0] = f.fileHash.value;
		} else {
			alert("정상적인 계약서가 아닙니다.");
			return;
		}
    }else{
    	for ( i = 0 ; i < f.fileHash.length ; i++ ) {
    		if (f.certDn[i].value == "")
    		{
    			alert("인증서 정보가 없습니다.");
    			return;
    		}
    		
    		if ( f.fileHash[i].value.length > 0 ){
    			tempData[i] = f.fileHash[i].value;
    		} else {
    			alert("정상적인 계약서가 아닙니다.");
    			return;
    		}	
    		
    	}
    }*/
    var option = {
    		encoding : 'base64'
    		, charset : 'utf-8'
    		, signType : '2'
    };
    
    var config = {
    		certificateClass : '16'
    };
	config.OID = FwkMssageUtil.getMessage("CONFIG.OID");
	config.firstTrigger = "H";
	yettie.init(config);
    //vestSign_ew.init_module(option);
    
	//yettie.sign(tempData, option, contSignData_open_complete_callback, pageObj.errorcallback);
    var idn = $('#P_IDN').val();
    yettie.signWithVerifyVID(tempData, idn, option, contSignData_open_complete_callback, pageObj.errorcallback);	
    
	//nxTSPKI.signData(tempData, options, contSignData_open_complete_callback);
	
}


function pfmSignData_open_complete_callback(res) {
	var	f	= document.forms[0];
	
    if (res.code == 0) {   	
    	if (f.hash.length == undefined)
        {
    		if( res.data[0].certInfo.subjectDN != f.dn.value){
        		alert("제출된 인증서가 아닙니다.");
        		return false;
        	}
    		
    		f.sign_value.value  = res.data[0].signedData;
        }else{
        	for ( i = 0 ; i < f.hash.length ; i++ ) {
        		if( res.data[i].certInfo.subjectDN != f.dn[i].value){
            		alert("제출된 인증서가 아닙니다.");
            		return false;
            	}
        		
        		f.sign_value[i].value  = res.data[i].signedData;
        	}
        }
    	
    	 f.action	= "/supplier/pfm/pfmCustSign.do";
    	 f.target	= "_self";
    	 f.smSubmit();
    }
    else {
        alert("error code = " + res.code + ", message = " + res.errorMessage);
    }
    
   
}

function pfmSignData_open() {
	var	f	= document.forms[0];
	
    var options = {};
    var tempData = []; 

    if (f.hash.length == undefined)
    {
    	if (f.dn.value == "")
		{
			alert("인증서 정보가 없습니다.");
			return;
		}
		
		if ( f.hash.value.length > 0 ){
			tempData[0] = f.hash.value;
		} else {
			alert("정상적인 계약서가 아닙니다.");
			return;
		}	
    }else{
    	for ( i = 0 ; i < f.hash.length ; i++ ) {
    		if (f.dn[i].value == "")
    		{
    			alert("인증서 정보가 없습니다.");
    			return;
    		}
    		
    		if ( f.hash[i].value.length > 0 ){
    			tempData[i] = f.hash[i].value;
    		} else {
    			alert("정상적인 계약서가 아닙니다.");
    			return;
    		}	
    		
    	}
    }
	
//	nxTSPKI.signData(tempData, options, pfmSignData_open_complete_callback);
	
}

function joinSelectCertificate_open_complete_callback(res) {
	var	f	= document.forms[0];
    if(res.code == 0) {
     
        
    	document.getElementsByName("cert.certAuth")[0].value = getCertAuthName(getCertAuthority(res.data.certInfo.subjectDN));  	//인증서 발행기관 이름
        
    	document.getElementsByName("cert.certDn")[0].value = res.data.certInfo.subjectDN ;					//인증서 DN
		
    	document.getElementsByName("cert.smtDate")[0].value = getCurrentDate();		//제출일자

        rtnStr = res.data.certInfo.validFrom;		//유효기간 From
        document.getElementsByName("cert.appDate")[0].value = rtnStr.substring(0,4)+  rtnStr.substring(5,7) + rtnStr.substring(8,10);
        
        rtnStr ="";
		rtnStr = res.data.certInfo.validTo ; //유효기간 To
		document.getElementsByName("cert.expDate")[0].value = rtnStr.substring(0,4)+  rtnStr.substring(5,7) + rtnStr.substring(8,10);
        document.getElementsByName("cert.certOid")[0].value = res.data.certInfo.certPolicy ; //OID
        document.getElementsByName("cert.certSerial")[0].value = res.data.certInfo.serialNumberHex ; //Serial
    	
        f.zipcode.value = replaceAll2(f.zipcode.value, "-", "");
    	f.zipcode.value = trim(f.zipcode.value);
	    
		f.actionCode.value	= "insert";
		f.action	= "/supplier/user/join.do";
		f.target	= "_self";
		f.smSubmit();
    }
    else {
        alert(res.errorMessage);
    }
}

function joinSelectCertificate_open(param_ssn) {
    var ssn = param_ssn;
//    nxTSPKI.verifyVID(ssn,{},joinSelectCertificate_open_complete_callback);
}



function getMedia()
{
	var vSTORAGE_TYPE
	if(!tool_get())return ;
	
	if(STORAGE_TYPE == "0"){
		vSTORAGE_TYPE = "3";
	} else if(STORAGE_TYPE == "1"){
		vSTORAGE_TYPE = "6";
	} else if(STORAGE_TYPE == "2"){
		vSTORAGE_TYPE = "1";
	} else if(STORAGE_TYPE == "3"){
		vSTORAGE_TYPE = "5";
	}
	return vSTORAGE_TYPE;
	
}

function getMediaNameKor()
{
	var strMediaName ;
    var strMediaCode = getMedia();
	if(strMediaCode == "3") {
		strMediaName = "하드디스크";
	} else if(strMediaCode == "6") {
		strMediaName = "이동식디스크";
	} else if(strMediaCode == "1") {
		strMediaName = "IC카드";
	} else if(strMediaCode == "5") {
		strMediaName     = "PKCS11";
	}	
	return strMediaName ;
	
}

//function getMediaName(strMediaCode) {
//        var strMediaName ;
//
//        if(strMediaCode == "1") {
//            strMediaName = "Smart Card";
//        } else if(strMediaCode == "2") {
//            strMediaName = "FDD";
//        } else if(strMediaCode == "3") {
//            strMediaName = "HDD";
//        } else if(strMediaCode == "4") {
//            strMediaName     = "Ldap Server";
//        } else if(strMediaCode == "6") {
//			strMediaName     = "Removable Disk";
//		}
//        return strMediaName ;
//    }
    
 function getMediaName(strMediaCode) {
    var strMediaName ;

    if(strMediaCode == "3") {
		strMediaName = "하드디스크";
	} else if(strMediaCode == "6") {
		strMediaName = "이동식디스크";
	} else if(strMediaCode == "1") {
		strMediaName = "IC카드";
	} else if(strMediaCode == "5") {
		strMediaName = "PKCS11";
	}
        return strMediaName ;
}

//인증서 발급기관영문명
function getCertAuthority(issuer){
	var index, strCertAuthority ;
	index = issuer.indexOf("o=");
	strCertAuthority =  issuer.substring(index+2) ;
	index = strCertAuthority.indexOf(",") ;
	strCertAuthority = strCertAuthority.substring(0,index) ;
	
	return strCertAuthority.toUpperCase() ;
}

function getCertAuthName(strCertAuthority) {
	var strCertAuthorityName ;

	if(strCertAuthority == "KICA") {
		strCertAuthorityName = "한국정보인증";
	} else if(strCertAuthority == "SIGNKOREA") {
		strCertAuthorityName = "한국증권전산";
	} else if(strCertAuthority == "YESSIGN") {
		strCertAuthorityName = "금융결제원";
	} else if(strCertAuthority == "CROSSCERT") {
		strCertAuthorityName     = "한국전자인증";
	} else if(strCertAuthority == "TRADESIGN") {
		strCertAuthorityName = "한국무역정보통신";
	} else if(strCertAuthority == "NCASIGN") {
		strCertAuthorityName = "한국전산원";
	} else if(strCertAuthority == "KAL") {
		strCertAuthorityName = "대한항공사설";
	} else if(strCertAuthority == "GOVERNMENT OF KOREA") {
		strCertAuthorityName = "행정자치부";
	}

	return strCertAuthorityName ;
}
function getCertAuthCode(strCertAuthority){
	var strCertAuthorityCode ;

	if(strCertAuthority == "KICA") {
		strCertAuthorityCode = "1";
	} else if(strCertAuthority == "SIGNKOREA") {
		strCertAuthorityCode = "2";
	} else if(strCertAuthority == "YESSIGN") {
		strCertAuthorityCode = "3";
	} else if(strCertAuthority == "CROSSCERT") {
		strCertAuthorityCode = "4";
	} else if(strCertAuthority == "TRADESIGN") {
		strCertAuthorityCode = "5";
	} else if(strCertAuthority == "NCASIGN") {
		strCertAuthorityCode = "6";
	} else if(strCertAuthority == "KAL") {
		strCertAuthorityCode = "7";
	} else if(strCertAuthority == "GOVERNMENT OF KOREA") {
		strCertAuthorityCode = "8";
	}
	return strCertAuthorityCode ;
}

//현재일자 확인
function getTodayDate()
{   
    var todate = new Date();
    var year = todate.getFullYear() + "";
    var mon  = (todate.getMonth() + 1) + "";
    var day  = todate.getDate() + "";
  
    if (mon.length == 1) { mon = "0" + mon; }
    if (day.length == 1) { day = "0" + day; }
  
    return year + mon + day;
}
//현재일자 확인
function getTodayDate2()
{   
    var todate = new Date();
    var year = todate.getFullYear() + "";
    var mon  = (todate.getMonth() + 1) + "";
    var day  = todate.getDate() + "";
  
    if (mon.length == 1) { mon = "0" + mon; }
    if (day.length == 1) { day = "0" + day; }
  
    return year + "."+ mon + "." + day;
}

    // yyyy-mm-dd 형식으로 오늘 날짜 반환
function getCurrentDate(){

	 var date = new Date();

	 var year = date.getFullYear();
	 var month = date.getMonth()+1;
	 var date = date.getDate();

	 if(month<10){
	  month = "0"+month;
	 }
	 if(date<10){
	  date = "0" + date;
	 }
 	return year+"/"+month+"/"+date;
}

 
	