var FwkCmmnUtil = {};

var url = location.href;

if(url.indexOf("/bid/") >= 0 ) {
	FwkCmmnUtil['contextPath'] = '/bid'
}else{
	FwkCmmnUtil['contextPath'] = ''
}

FwkCmmnUtil['fileSeparator'] = '/';
FwkCmmnUtil['imagePath'] = '/statics/images';

FwkCmmnUtil.isNull = function (str) {
	str = $.trim(str);
	if(str == null || str == 'undefined' || str.length == 0) { 
		return true; 
	} 
	return false;
};		


FwkCmmnUtil.isNotNull = function (str) {
	str = FwkCmmnUtil.trim(str);
	if(str == null || str == 'undefined' || str.length == 0) { 
		return false; 
	} 
	return true;
};		

	
		
FwkCmmnUtil.nvl = function (str, rstr) {
	if(FwkCmmnUtil.isNull(str)) { return rstr == null || rstr == 'undefined' ? '' : rstr; } return str;
};		

	
		
FwkCmmnUtil.trim = function (str) {
	return $.trim(str);
};		

	
	
	
	
FwkCmmnUtil.isNumeric = function (str) {
	str += '';
	str = str.replace(/^s*|s*$/g, '');
	if (str == '' || isNaN(str)) return false;
	return true;
};

	
		
FwkCmmnUtil.isFloat = function (str) {
	var re = /^[+-]?[0-9]*[.]?[0-9]*[0-9]$/;
	if (re.test(str)) { return	true; }
	return false;
};

	
		
FwkCmmnUtil.isEmail = function (str) {
	//var re =/^([w-]+(?:.[w-]+)*)@((?:[w-]+.)*w[w-]{0,66}).([a-z]{2,6}(?:.[a-z]{2})?)$/;
	var re =/^[A-Za-z0-9_\.\-]+@[A-Za-z0-9\-]+\.[A-Za-z0-9\-]+/;
	if (re.test(str)) { return	true; }
	return false;
};

	
		
FwkCmmnUtil.isMobilePhoneNumber = function (str) {
	if(FwkCmmnUtil.isNull(str)) {return false;} 
	var re =/^(010|011|016|017|018|019)-d{3,4}-d{4}$/;
	if (re.test(str)) { return	true; }
	return false;
};

	
FwkCmmnUtil.isTelePhoneNumber = function (str) {
	if(FwkCmmnUtil.isNull(str)) {return false;} 
	var re =/^(070|02|031|032|033|041|042|043|051|052|053|054|055|061|062|063|064)-d{3,4}-d{4}$/;
	if (re.test(str)) { return	true; }
	return false;
};

	
		
FwkCmmnUtil.isPostNumber = function (str) {
	if(FwkCmmnUtil.isNull(str)) {return false;} 
	var re =/^d{3}-?d{3}$/;
	if (re.test(str)) { return	true; }
	return false;
};

	
		
FwkCmmnUtil.isBizNumber = function (str) {
	if(FwkCmmnUtil.isNull(str)) {return false;} 
	
	var checkID = new Array(1, 3, 7, 1, 3, 7, 1, 3, 5, 1); 
	var tmpBizID, i, chkSum=0, c2, remander; 
	
	str = str.replace(/-/gi,''); 
	
	for (i=0; i<=7; i++) {chkSum += checkID[i] * str.charAt(i);}
	
	c2 = "0" + (checkID[8] * str.charAt(8)); 
	c2 = c2.substring(c2.length - 2, c2.length); 
	chkSum += Math.floor(c2.charAt(0)) + Math.floor(c2.charAt(1)); 
	remander = (10 - (chkSum % 10)) % 10 ; 
	chkSum += Math.floor(c2.charAt(0)) + Math.floor(c2.charAt(1)); 
	
	if (Math.floor(str.charAt(9)) == remander) {return true; }
	return false;		
		
};

	
	
FwkCmmnUtil.isEnglish = function (str) {
	var re = "^[a-zA-Z_-.s]*$";
	if (re.test(str)) { return	true; }
	return false;
};

	

	
FwkCmmnUtil.isEnglishAndNumber = function (str) {
	var re = "^[a-zA-Z0-9_-.]*$";
	if (re.test(str)) { return	true; }
	return false;
};

	

FwkCmmnUtil.setEnterKeyBinding = function (formId, formFields, callback) {		
	$.each(formFields, function() {
		var obj = $( "#" + formId + " #" + this);
		obj.on("keypress", function(e) {
			var key = window.event ? e.keyCode : e.which;
			if(key == 13) { eval(callback)(); return false; }
		});
	});
};


FwkCmmnUtil.submitAjax = function (actionUrl, jsonParam, callbackSuccess, callbackBeforeSend, callbackComplete, callbackError) {
	FwkCmmnUtil.loadingImage();
	
	var jsonData = JSON.stringify(jsonParam);
	actionUrl = FwkCmmnUtil.contextPath + actionUrl + ".json";
	$.ajax({
		type : "POST",
		contentType : "application/json;charset=UTF-8",
		url : actionUrl,
		data : jsonData,		
		async:false,
		beforeSend : function() { if(typeof callbackBeforeSend == 'function') callbackBeforeSend(); },
		success : function(res) { if(typeof callbackSuccess == 'function') callbackSuccess(res); },
		complete : function() { if(typeof callbackComplete == 'function') callbackComplete(); FwkCmmnUtil.loadingImageRemove(); },
		error : function(xhr , textStatus , error) { 
			if(typeof callbackError == 'function') {
				callbackError(JSON.parse(xhr.responseText), status, error);} 
			else {
				console.log(xhr);
				console.log(textStatus);
				console.log(error);
				//console.log(xhr.responseText);
				//alert(error);
				//console.log(JSON.parse(xhr.responseText).errorMessage);
			} 
		},
	});
};


FwkCmmnUtil.submitAjaxNoLoading = function (actionUrl, jsonParam, callbackSuccess, callbackBeforeSend, callbackComplete, callbackError) {
	var jsonData = JSON.stringify(jsonParam);
	
	actionUrl = FwkCmmnUtil.contextPath + actionUrl + ".json";
	
	$.ajax({
		type : "POST",
		contentType : "application/json;charset=UTF-8",
		url : actionUrl,
		data : jsonData,
		async:false,
		beforeSend : function() { if(typeof callbackBeforeSend == 'function') callbackBeforeSend(); },
 		success : function(res) { if(typeof callbackSuccess == 'function') callbackSuccess(res); },
		complete : function() {
			if(typeof callbackComplete == 'function') {
				callbackComplete(); 
			}
			},
		error : function(xhr , textStatus , error) {
			if(typeof callbackError == 'function') {
				//callbackError(JSON.parse(xhr.responseText), status, error);} 
				callbackError("시스템 오류가 발생하였습니다! \n담당자에게 문의 바랍니다.");} 
			else {//alert(JSON.parse(xhr.responseText).errorMessage);
				  //alert("code = " + xhr.status + " message = " + xhr.responseText + " error = " + error);
				alert("시스템 오류가 발생하였습니다! \n담당자에게 문의 바랍니다.");
			} 
		},
	});		
};

	
	
FwkCmmnUtil.setAllCheck = function (chkAllId, chkValName, tableId) {	
	var selector;
	
	if(undefined !== tableId && tableId != "" ){
		selector = "#"+tableId+" input[name='"+chkValName+"']";
	}else{
		selector = "input[name='"+chkValName+"']";
	}
	
	if($(selector).length < 1) {
		$("#" + chkAllId).removeAttr("checked")
		return ;
	}		
	if( $("#" + chkAllId).is(":checked")) {
		$(selector).each(
				function(idx, obj) {
					if(FwkCmmnUtil.isNull($(obj).prop("disabled")) || $(obj).prop("disabled") == false) {
						$(obj).prop("checked", true);
					}});
	} else {
		$(selector).removeAttr("checked")
	}
	
	$(selector).click(function() {		
		var allCnt = $(selector).length;
		var checkedCnt = $(selector).filter(":checked").length;
		var disableCnt = $(selector).filter(":disabled").length;
		var totCnt = checkedCnt + disableCnt;
		if(allCnt==totCnt) {
			$("#" + chkAllId).prop("checked", "checked")
		} else {
			$("#" + chkAllId).removeAttr("checked")
		}
	});		
};

	
	
FwkCmmnUtil.getCheckedCount = function (chkValName) {		
		
	var cnt = 0;
	$("input[name='" + chkValName + "']").each(		
			function(index) {
				if($(this).is(':checked')) { cnt++; }
			}
	);
	return cnt;		
		
};


	
FwkCmmnUtil.getCheckedIndex = function (chkValName) {		
	var rtn = {};
	var cnt = 0;
	$("input[name='" + chkValName + "']").each(		
			function(index) {
				if($(this).is(':checked')) { rtn[cnt] = index; }
				cnt++;
			}
	);
	return rtn;		
		
};

	
	
FwkCmmnUtil.getCheckedValue = function (chkValName) {		
	var rtn = [];
	$("input[name='" + chkValName + "']:checked").each(		
			function(index) {
				rtn.push($(this).val());
			}
		);
	return rtn;		

};


FwkCmmnUtil.submitFormExDown = function (formId, actionUrl, hiddenParam) {	
	/*if(formId.indexOf("downloadFrm") > -1 || formId.indexOf("download") > -1 || actionUrl.indexOf("Dwld") > -1 || actionUrl.indexOf("download") > -1 || formId.indexOf("menualFrm") > -1) {
		
	}else {
		FwkCmmnUtil.loadingImage();
	}*/
	var $frm = $("#" + formId);
	actionUrl = FwkCmmnUtil.contextPath + actionUrl;
			
	if(hiddenParam) {
		FwkCmmnUtil.addHiddenParam(formId, hiddenParam);
	}
			
	$frm.attr("method",  "POST");
	$frm.attr("action", actionUrl);
	$frm.attr("target", "iHiddenWindow");
	$frm.submit();
	return false;
		
};
	

FwkCmmnUtil.submitForm = function (formId, actionUrl, hiddenParam) {	
	if(formId.indexOf("downloadFrm") > -1 || formId.indexOf("download") > -1 || actionUrl.indexOf("Dwld") > -1 || actionUrl.indexOf("download") > -1 || formId.indexOf("manualFrm") > -1) {
		
	}else {
		FwkCmmnUtil.loadingImage();
	}
	var $frm = $("#" + formId);
	actionUrl = FwkCmmnUtil.contextPath + actionUrl;
			
	if(hiddenParam) {
		FwkCmmnUtil.addHiddenParam(formId, hiddenParam);
	}
			
	$frm.attr("method",  "POST");
	$frm.attr("action", actionUrl);		
	$frm.submit();
	return false;
		
};

FwkCmmnUtil.submitFormBlank = function (formId, actionUrl, hiddenParam) {
	if(formId.indexOf("downloadFrm") > -1 || formId.indexOf("download") > -1 || actionUrl.indexOf("Dwld") > -1 || actionUrl.indexOf("download") > -1) {
		
	}else {
		FwkCmmnUtil.loadingImage();
	}
	var $frm = $("#" + formId);
	actionUrl = FwkCmmnUtil.contextPath + actionUrl;
			
	if(hiddenParam) {
		FwkCmmnUtil.addHiddenParam(formId, hiddenParam);
	}
	
	var gsWin = window.open('about:blank', 'newWindow', 'width=1060, height=780');
	$frm.attr("method",  "POST");
	$frm.attr("action", actionUrl);		
	$frm.target('newWindow');
	$frm.submit();
	return false;
		
};

	
	
FwkCmmnUtil.addHiddenParam = function (formId, params) {
	var formObj = $("#" + formId);
			
	$.each(params, function(i, item) {
		if($("#" + formId + " #" + item.id).length == 0) {
			formObj.append('<input type="hidden" id="' + item.id + '" name="' + item.name + '" value="' + item.value + '" />');
		}
	});		
};

	
	
FwkCmmnUtil.deleteComma = function (str) {
		
	str = FwkCmmnUtil.nvl(str);
	
	return str.replace(/,/gi,"");
		
};

FwkCmmnUtil.deleteHyphen = function (str) {
	
	str = FwkCmmnUtil.nvl(str);
	
	return str.replace(/-/gi,"");
	
};

	
	
FwkCmmnUtil.addComma = function (str) {
	str = String(FwkCmmnUtil.nvl(str));
	return str.replace(/(\d)(?=(?:\d{3})+(?!\d))/g, '$1,');
		
};

	
	
FwkCmmnUtil.getBytes = function (str) {		
	str = FwkCmmnUtil.nvl(str);
			
	var str_character;
	var int_char_count = 0;
	var int_contents_length = str.length;
			
	for (k = 0; k < int_contents_length; k++) {
		str_character = str.charAt(k);
		if (escape(str_character).length > 4) {
			int_char_count +=3
		}
		else {int_char_count++;}}
			
	return int_char_count;
		
};

//로딩 이미지 공통
FwkCmmnUtil.loadingImage = function() {
    var maskHeight = $(document).height();
    var maskWidth  = window.document.body.clientWidth;
     
    //화면에 출력할 마스크를 설정해줍니다.
    var mask       = "<div id='mask' style='position:absolute; z-index:9000; background-color:#000000; display:none; left:0; top:0;'></div>";
    var loadingImg = '';
      
    loadingImg += "<div id='loadingImg' style='position: absolute; z-index:8999; display: block; left:50%; top:50%; transform:translateX(-50%);'><img src='" + FwkCmmnUtil.contextPath + "/statics/images/ipro/common/loading_keri.gif' /></div>"; 
  
    //화면에 레이어 추가
    $('body')
        .append(mask)
        .append(loadingImg);
        
    //마스크의 높이와 너비를 화면 것으로 만들어 전체 화면을 채웁니다.
    $('#mask').css({
            'width' : maskWidth
            , 'height': maskHeight
            , 'opacity' : '0.3'
    });
  
    //마스크 표시
    $('#mask').show();  
  
    //로딩중 이미지 표시
    $('#loadingImg').show();
};

FwkCmmnUtil.loadingImageRemove = function() {
    //로딩중 이미지 표시
    $('#mask').remove();	
    $('#loadingImg').remove();	
};

	
FwkCmmnUtil.setEventHandler = function (formId, checkData) {										
	$.each(checkData, function(i, item) {																		
		$.each(item, function(key, value) {																		
			var required = value.required;																			
			var number = value.number;																					
			var float = value.float;																					
			var date = value.date;																					
			var biz = value.biz;																						
			var obj = $("#" +formId + "").find("input[name='" + key + "']");							
			if(obj.length == 0) {																						
				obj = $("#" +formId + "").find("select[name='" + key + "']");					
			}																												
			if(obj.length == 0) {																						
				obj = $("#" +formId + "").find("textarea[name='" + key + "']");					
			}																												
			obj.each(function() {																					
				if( FwkCmmnUtil.isNull(number) == false ) {													
					$(this).css("ime-mode", "disabled"); 															
					$(this).keypress(function(event) {		 													
						if(event.which && (event.which < 48 || event.which > 57) ) {					
							event.preventDefault();					 												
						}												 													
					});												 													
					if(number.format == "comma") {			 												
						$(this).keyup(function(event) {		 													
							if( $(this).val() != null && $(this).val() != '' ) {									
								var tmps = $(this).val().replace(/[^0-9]/g, '');										
								var tmps2 = tmps.replace(/(d)(?=(ddd)+(?!d))/g, "$1,");	
								$(this).val(tmps2);																	
							}												 												
						});												 												
					} else {											 													
						$(this).keyup(function(event) {		 													
							if( $(this).val() != null && $(this).val() != '' ) {									
								$(this).val( $(this).val().replace(/[^0-9]/g, '') );							
							}												 												
						});												 												
					}													 															
				}																											
	
				if( FwkCmmnUtil.isNull(float) == false ) {														
					$(this).css("ime-mode", "disabled"); 															
					$(this).keypress(function(event) {		 													
						if(event.which && (event.which < 45 || event.which > 57) ) {					
							event.preventDefault();					 												
						}												 													
					});												 													
					if(float.format == "comma") {			 													
						$(this).keyup(function(event) {		 													
							if( $(this).val() != null && $(this).val() != '' ) {									
								var tmps = $(this).val().replace(/[^.0-9]/g, '');									
								var tmps2 = tmps.replace(/(d)(?=(ddd)+(?!d))/g, "$1,");	
								$(this).val(tmps2);																	
							}												 												
						});												 												
					} else {											 													
						$(this).keyup(function(event) {		 													
							if( $(this).val() != null && $(this).val() != '' ) {									
								$(this).val( $(this).val().replace(/[^.0-9]/g, '') );						
							}												 												
						});												 												
					}													 															
				}																											
	
				if( FwkCmmnUtil.isNull(date) == false ) {														
					$(this).css("ime-mode", "disabled"); 													
					$(this).keypress(function(event) {		 													
						if(event.which && (event.which < 45 || event.which > 57) ) {					
							event.preventDefault();					 												
						}												 													
					});												 													
					$(this).keyup(function(event) {		 														
						if( $(this).val() != null && $(this).val() != '' ) {										
							$(this).val( $(this).val().replace(/[^0-9:-]/gi,"")  );						
						}												 													
					});												 													
				}																											
	
				if( FwkCmmnUtil.isNull(biz) == false ) {															
					$(this).css("ime-mode", "disabled"); 													
					$(this).keypress(function(event) {		 													
						if(event.which && (event.which < 45 || event.which > 57) ) {					
							event.preventDefault();					 												
						}												 													
					});												 													
					$(this).keyup(function(event) {		 														
						if( $(this).val() != null && $(this).val() != '' ) {										
							$(this).val( $(this).val().replace(/[^0-9:-]/gi,"")  );						
						}												 													
					});												 													
				}																												
	
			});																											
		});																												
	});																													
	return rtn;																													
};																															

		
		
	

FwkCmmnUtil.validate = function (formId, checkData) {																	
	var rtn = true;								
	$.each(checkData, function(i, item) {																		
		$.each(item, function(key, value) {																		
			var required = value.required;																			
	
			var number = value.number;																			
			var english = value.english;																			
			var englishAndNumber = value.englishAndNumber;																			
			var float = value.float;																					
			var biz = value.biz;																						
			var len = value.len;																						
			var date = value.date;																					
			var obj = $("#" +formId + "").find("input[name='" + key + "']");	
			if(obj.length == 0) {																						
				obj = $("#" +formId + "").find("select[name='" + key + "']");					
			}																												
			if(obj.length == 0) {																						
				obj = $("#" +formId + "").find("textarea[name='" + key + "']");					
			}																												
			obj.each(function() {																					
				var val = FwkCmmnUtil.nvl($(this).val());														
				if( FwkCmmnUtil.isNull(required) == false ) {															 
					if( FwkCmmnUtil.isNull(val) == true) { 														
	
						alert(required.message);																	
						$(this).focus();																						
						rtn = false;																								
						return false;																					
					}																										
				}																											
	
				if( FwkCmmnUtil.isNull(number) == false ) {													
					val = FwkCmmnUtil.deleteComma(val); 														
					if(val != null && val.length > 0 ) {																	
						if(FwkCmmnUtil.isNumeric(val) == false) {	
							
							alert(number.message);																	
							$(this).focus();																			
							rtn = false;																							
							return false;																				
						}																									
					}																										
				}																											
	
				if( FwkCmmnUtil.isNull(english) == false ) {													
					if(val != null && val.length > 0 ) {																	
						if(FwkCmmnUtil.isEnglish(val) == false) {	
							
							alert(english.message);																	
							$(this).focus();																			
							rtn = false;																							
							return false;																				
						}																									
					}																										
				}																											
	
				if( FwkCmmnUtil.isNull(englishAndNumber) == false ) {													
					if(val != null && val.length > 0 ) {																	
						if(FwkCmmnUtil.isEnglishAndNumber(val) == false) {	
							
							alert(englishAndNumber.message);																	
							$(this).focus();																			
							rtn = false;																							
							return false;																				
						}																									
					}																										
				}																											
	
				if( FwkCmmnUtil.isNull(float) == false ) {														
					val = FwkCmmnUtil.deleteComma(val); 														
					if(val != null && val.length > 0 ) {															
						if(FwkCmmnUtil.isFloat(val) == false) {		
							
							alert(float.message);																		
							$(this).focus();																				
							rtn = false;																								
							return false;																					
						}																										
					}																											
				}																											
	
				if( FwkCmmnUtil.isNull(biz) == false ) {															
					if(val != null && val.length > 0 ) {															
						if(FwkCmmnUtil.isBizNumber(val) == false) {	
							
							alert(biz.message);																			
							$(this).focus();																				
							rtn = false;																								
							return false;																					
						}																										
					}																											
				}																											
	
				if( FwkCmmnUtil.isNull(len) == false ) {															
					if(val != null && val.length > 0 ) {	
						
						var max = len.max;																				
						var min = len.min;																							
						var blen = FwkCmmnUtil	.getBytes(val);													
						if(blen < min || blen > max) {		
							
							alert(len.message);																			
							$(this).focus();																				
							rtn = false;																								
							return false;																					
						}																												
					}																											
				}																											
	
				if( FwkCmmnUtil.isNull(date) == false ) {														
					if(val != null && val.length > 0 ) {															
						if(FwkDateUtil.isDate(val, date.format) == false) {							
							
							alert(date.message);																		
							$(this).focus();																				
							rtn = false;																								
							return false;																					
						}																										
					}																										
				}																													
			});																											
			if(rtn == false) return false;																			
		});																												
	});																													
	return rtn;																													
};																															
	
			
	

$.fn.serializeObject = function() {
	var arrayData, objectData;
	arrayData = this.serializeArray();
			
	objectData = {};
	$.each(arrayData, function() {
		var value;
		 if (this.value != null) { value = this.value; } else { value = ''; }
		if (objectData[this.name] != null) {
		if (!objectData[this.name].push) { objectData[this.name] = [objectData[this.name]];  }
		objectData[this.name].push(value);
		} else {
		objectData[this.name] = value;
	}});
	return objectData;		
};
		
		
		
	