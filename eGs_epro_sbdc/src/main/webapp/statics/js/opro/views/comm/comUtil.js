(function( factory ) {
    if ( typeof define === "function" && define.amd ) {
        define([ "../jquery.ui.datepicker" ], factory );
    } else {
        factory( jQuery.datepicker );
    }
}(function( datepicker ) {
	datepicker.regional['ko-KR'] = {
	    //datepicker trigger 지정
	    showOn: "both" //엘리먼트와 이미지 동시 사용(both,button
	    //한글 지정
	    , regional : "ko"	//ko-KR
		, buttonText: '달력선택'	//버튼 텍스트 표시
			
		, closeText: '닫기'
		, prevText: '이전'
		, nextText: '다음'
		, currentText: '오늘'
		, monthNames: ['1','2','3','4','5','6','7','8','9','10','11','12']
		, monthNamesShort: ['1','2','3','4','5','6','7','8','9','10','11','12']
		, dayNames: ['S','M','T','W','T','F','S']
		, dayNamesShort: ['S','M','T','W','T','F','S']
		, dayNamesMin: ['S','M','T','W','T','F','S']
		, weekHeader: 'Wk'
		, dateFormat: 'yy-mm-dd'
		, firstDay: 0
		, isRTL: false
		, showMonthAfterYear: true
		, yearSuffix: '.'
		//showOtherMonths: true
		, constrainInput: false
		, buttonImage: '/statics/images/ipro/sbdc/icon_calen.png'
		, buttonImageOnly: true		
    };
    datepicker.setDefaults(datepicker.regional['ko-KR']);
    return datepicker.regional['ko-KR'];
}));

$(document).ready(function(){
	$(".content h3").prepend("<img src='' />  ");
	datePickerFunc();
	//$(".content h3 img").attr("src", "/statics/images/ipro/main/h3_line.png");
	
	//달력 세팅
	//setDatePicker();
	
	/**  
	 * <pre>
	 * 1. 개요 : eMail에 키 입력 및 포커스 아웃시 이메일 정규식 체크
	 * 2. 처리내용 : 
	 * 		attribute에 eMail 입력 시 이메일 정규식을 체크하여 안내창 보여주기
	 *  	사용 예) <input type="text" eMail />
	 * </pre>
	 * @Function Name : eMail
	 * @date : 2015. 6. 15.
	 * @author : 은우소프트 손연우
	 * @history : 
	 *  =======================================================
	 *  변경일             		작성자                     		변경내용  
	 *  =======================================================
	 *  2015. 6. 15.       은우소프트 손연우              최초 작성 
	 *  =======================================================   
	 */
	
	$("[eMail]").on("blur",function() {
		if($(this).val() !="") {
			if(FwkCmmnUtil.isEmail($(this).val()) != true) {
				
				$(this).focus();
				alert(FwkMssageUtil.getMessage("COM.INF.017","이메일"));
				
			}
		}
	});
	
	
	/**  
	 * <pre>
	 * 1. 개요 : inputbox, textarea에 키 입력 및 포커스 아웃 시 자동 글자수 체크
	 * 2. 처리내용 : 
	 * 		inputbox 및 textarea에 maxLength 입력 시 
	 *		한글 3byte, 영문 1byte 계산하여 글자수 체크 
	 *  	사용 예) <input type="text" maxLangth="10" />
	 * </pre>
	 * @Function Name : maxLength
	 * @date : 2015. 1. 23.
	 * @author : 은우소프트 하성윤
	 * @history : 
	 *  =======================================================
	 *  변경일             		작성자                     		변경내용  
	 *  =======================================================
	 *  2015. 1. 23.       은우소프트 하성윤              최초 작성 
	 *  =======================================================   
	 */
	$("input, textarea").on("keyup", function(){
		// 글자수 체크 (maxLength가 지정되 있을 경우 동작)
		if($(this).attr("maxLength")){
			var value = $(this).val();					// 현재 값
			var l = value.length;						// 현재 글자 수
			var cnt = 0;								// 글자 수 카운터
			var maxLength = $(this).attr("maxLength");	// maxLength 길이
			var temp = "";								// maxLength가 넘었을때 maxLength가길이만큼 저장된 값
	
			// textarea에 byte체크시 4000자를 급접하는 경우 long 관련된 오류가 발생해 임시 조치
			if(maxLength > 1999){
				cnt = cnt + 30;
			}
			for(var i=0; i < l; i++){						// 길이 수 많큼 실행
				if(escape(value.charAt(i)).length > 4){	// escape : 숫자, 영문자를 제외한 한글등을 16진수 형태로 인코딩하여 출력 - 4보다 크면 한글 및 특수문자
					cnt += 3;							// 한글 및 특수문자 3byte로 계산( !@#$%^&*()_+/{}<>?~`=-|"';:,. 제외한 특수문자)
				}else{
					cnt += 1;							// 숫자 및 영어 1byte 계산
				}
				
				if(cnt > maxLength){					// maxLength보다 cnt가 크면 break
					if(maxLength == 4000){
						alert("영문 " + maxLength + "자 한글 1300자 까지 작성할 수 있습니다.");
					}else{
						alert("영문 " + maxLength + "자 한글" + Math.floor(maxLength/3) +"자 까지 작성할 수 있습니다.");
					}
					$(this).val(temp);					// keyup 이기 때문에 키를 계속 눌러 입력값이 많아지면 maxLength만큼 저장된 값 set
					// +된 cnt 되돌리기
					if(escape(value.charAt(i)).length > 4){	// escape : 숫자, 영문자를 제외한 한글등을 16진수 형태로 인코딩하여 출력 - 4보다 크면 한글 및 특수문자
						cnt -= 3;							// 한글 및 특수문자 3byte로 계산( !@#$%^&*()_+/{}<>?~`=-|"';:,. 제외한 특수문자)
					}else{
						cnt -= 1;							// 숫자 및 영어 1byte 계산
					}
					break;
				}else{
					temp += value.charAt(i);			// maxLength만큼 저장된 값
				}
			}
		}
	}).on("change", function(){		// 크롬에서 포커스 이동시 문자가 다시 찍혀나와서 다시 체크
		// 글자수 체크 (maxLength가 지정되 있을 경우 동작)
		if($(this).attr("maxLength")){
			var value = $(this).val();					// 현재 값
			var l = value.length;						// 현재 글자 수
			var cnt = 0;								// 글자 수 카운터
			var maxLength = $(this).attr("maxLength");	// maxLength 길이
			var temp = "";								// maxLength가 넘었을때 maxLength가길이만큼 저장된 값
			
			for(var i=0;i<l;i++){						// 길이 수 많큼 실행
				
				if(escape(value.charAt(i)).length > 4){	// escape : 숫자, 영문자를 제외한 한글등을 16진수 형태로 인코딩하여 출력 - 4보다 크면 한글 및 특수문자
					cnt += 3;							// 한글 및 특수문자 3byte로 계산( !@#$%^&*()_+/{}<>?~`=-|"';:,. 제외한 특수문자)
				}else{
					cnt += 1;							// 숫자 및 영어 1byte 계산
				}
				
				if(cnt > maxLength){					// maxLength보다 cnt가 크면 break
					$(this).val(temp);					// keyup 이기 때문에 키를 계속 눌러 입력값이 많아지면 maxLength만큼 저장된 값 set
					// +된 cnt 되돌리기
					if(escape(value.charAt(i)).length > 4){	// escape : 숫자, 영문자를 제외한 한글등을 16진수 형태로 인코딩하여 출력 - 4보다 크면 한글 및 특수문자
						cnt -= 3;							// 한글 및 특수문자 3byte로 계산( !@#$%^&*()_+/{}<>?~`=-|"';:,. 제외한 특수문자)
					}else{
						cnt -= 1;							// 숫자 및 영어 1byte 계산
					}
					break;
				}else{
					temp += value.charAt(i);			// maxLength만큼 저장된 값
				}
			}
		}
	});
	
	isEnglishAndNumber = function(str){
		var re= /^[a-zA-Z0-9_\-\.\s\n()\[\],%\/\\$~+=*]*$/;
		return re.test(str);
	};
	
	// 영문 판별
	$("[english]").on("keyup", function(){
		if($(this).val() != "" ){
			if(isEnglishAndNumber($(this).val()) != true){ 
				alert("영문만 입력 가능합니다.");
				$(this).val( $(this).val().replace(/[^a-zA-Z0-9_\-\.\s\n()\[\],%\/\\$~+=*]/gi,""));
			}
		}
	}).on("change", function(){
		if($(this).val() != "" ){
			if(isEnglishAndNumber($(this).val()) != true){
				$(this).val( $(this).val().replace(/[^a-zA-Z0-9_\-\.\s\n()\[\],%\/\\$~+=*]/gi,"")); 
			}
		}
	});
	
	
	isBsnsNo =  function(str){
		var re=/^[a-zA-Z0-9-]*$/gi;
		return re.test(str);
	};
	
	$("[bsnsNo]").on("keyup", function(){
		if($(this).val() != "" ){
			if(isBsnsNo($(this).val()) != true){
				alert("영문과 숫자,-만 입력가능합니다.");
				$(this).val( $(this).val().replace(/[^a-zA-Z0-9\\-]/gi,""));
			}
		}
	}).on("change", function(){
		if($(this).val() != "" ){
			if(isBsnsNo($(this).val()) != true){
				$(this).val( $(this).val().replace(/[^a-zA-Z0-9\\-]/gi,"")); 
			}
		}
	});
	
	
	
	/**  
	 * <pre>
	 * 1. 개요 : attribute에 tel 입력시 숫자체크
	 * 2. 처리내용 : 
	 * 		attribute에 tel 입력 시 숫자체크하여 안내창 보여주기
	 *  	사용 예) <input type="text" tel />
	 * </pre>
	 * @Function Name : tel
	 * @date : 2015. 08. 27.
	 * @author : 은우소프트 손연우
	 * @history : 
	 *  =======================================================
	 *  변경일             		작성자                     		변경내용  
	 *  =======================================================
	 *  2015. 08. 27.       은우소프트 손연우              최초 작성 
	 *  =======================================================   
	 */
	isTel =  function(str){
		var re=/^[0-9\-]*$/gi;
		return re.test(str);
	};
	
	$("[tel]").on("keyup", function(){
		if($(this).val() != "" ){
			if(isTel($(this).val()) != true){
				alert("숫자와 -만 입력가능합니다.");
				$(this).val( $(this).val().replace(/[^0-9\\-]/gi,""));
			}
		}
	}).on("change", function(){
		if($(this).val() != "" ){
			if(isTel($(this).val()) != true){
				alert("숫자와 -만 입력가능합니다.");
				$(this).val( $(this).val().replace(/[^0-9\\-]/gi,""));
			} 
		}
	});
	
	
	/**  
	 * <pre>
	 * 1. 개요 : attribute에 notDal 입력시 $체크
	 * 2. 처리내용 : 
	 * 		attribute에 notDal 입력 시 $체크하여 안내창 보여주기
	 *  	사용 예) <input type="text"notDal />
	 * </pre>
	 * @Function Name : notDal
	 * @date : 2015. 10. 03.
	 * @author : 은우소프트 손연우
	 * @history : 
	 *  =======================================================
	 *  변경일             		작성자                     		변경내용  
	 *  =======================================================
	 *  2015. 10. 03.       은우소프트 손연우              최초 작성 
	 *  =======================================================   
	 */
	
	$("[notDal]").on("keyup", function(){
		if($(this).val() != "" ){
			if($(this).val().indexOf("$") != -1) {
				alert("$는 입력 불가능합니다.");
				$(this).val($(this).val().replace(/[\\$]/gi,""));
			}
		}
	}).on("change", function(){
		if($(this).val() != "" ){
			if($(this).val().indexOf("$") != -1) {
				alert("$는 입력 불가능합니다.");
				$(this).val($(this).val().replace(/[\\$]/gi,""));
			} 
		}
	});
	
	/**  
	 * <pre>
	 * 1. 개요 : attribute에 numeric 입력시 숫자체크
	 * 2. 처리내용 : 
	 * 		attribute에 numeric 입력 시 숫자체크하여 안내창 보여주기
	 *  	사용 예) <input type="text" numeric />
	 * </pre>
	 * @Function Name : numeric
	 * @date : 2015. 1. 23.
	 * @author : 은우소프트 하성윤
	 * @history : 
	 *  =======================================================
	 *  변경일             		작성자                     		변경내용  
	 *  =======================================================
	 *  2015. 1. 23.       은우소프트 하성윤              최초 작성 
	 *  =======================================================   
	 */
	$("[numeric]").on("keyup", function(){
		if($(this).val() != "" ){
			if(FwkCmmnUtil.isNumeric($(this).val()) != true){
				alert("숫자만 입력 가능합니다.");
				$(this).val( $(this).val().replace(/[^0-9]/gi,""));
			}
			//if(FwkCmmnUtil.)
		}
	}).on("change", function(){
		if($(this).val() != "" ){
			if(FwkCmmnUtil.isNumeric($(this).val()) != true){
				$(this).val( $(this).val().replace(/[^0-9]/gi,"")); 
			}
		}
	});
	
	$("[numeric2]").on("keyup", function(){
		if($(this).val() != "" ){
			if(FwkCmmnUtil.isNumeric($(this).val()) != true){
				alert("숫자만 입력 가능합니다.");
				$(this).val( $(this).val().replace(/[^0-9]/gi,""));
			}
			if($(this).val().indexOf('.') > 0){
				var dectext = $(this).val().substring($(this).val().indexOf('.')+1, $(this).val().length); 
				if (dectext.length > 2){ 
					alert ("소수점 2자리까지만 입력할 수 있습니다"); 
					$(this).val(""); 
					$(this).focus(); 
				}
			}
		}
	}).on("change", function(){
		if($(this).val() != "" ){
			if(FwkCmmnUtil.isNumeric($(this).val()) != true){
				$(this).val( $(this).val().replace(/[^0-9]/gi,"")); 
			}
			if($(this).val().indexOf('.') > 0){
				var dectext = $(this).val().substring($(this).val().indexOf('.')+1, $(this).val().length); 
				if (dectext.length > 2){ 
					alert ("소수점 2자리까지만 입력할 수 있습니다"); 
					$(this).val(""); 
					$(this).focus(); 
				}
			}
		}
	});
	
	 isNum =  function(str){
		var re=/^[0-9\-\.]*$/gi;
		return re.test(str);
	};
	
	$("[numeric3]").on("keyup", function(){
		if($(this).val() != "" ){
			if(isNum($(this).val()) != true){
				alert("숫자만 입력 가능합니다.");
				$(this).val( $(this).val().replace(/[^0-9\-\.]/gi,""));
			}
			if($(this).val().indexOf('.') > 0){
				var dectext = $(this).val().substring($(this).val().indexOf('.')+1, $(this).val().length);
				if (dectext.length > 2){ 
					alert ("소수점 2자리까지만 입력할 수 있습니다"); 
					$(this).val(""); 
					$(this).focus(); 
				}
			}
		}
	}).on("change", function(){
		if($(this).val() != "" ){
			if(isNum($(this).val()) != true){
				$(this).val( $(this).val().replace(/[^0-9\-\.]/gi,"")); 
			}
			if($(this).val().indexOf('.') > 0){
				var dectext = $(this).val().substring($(this).val().indexOf('.')+1, $(this).val().length);
				if (dectext.length > 2){ 
					alert ("소수점 2자리까지만 입력할 수 있습니다"); 
					$(this).val(""); 
					$(this).focus(); 
				}
			}
		}
	});
	
	/**  
	 * <pre>
	 * 1. 개요 : textarea에 taView 출력시 데이터 양에 맞게 textarea의 크기및 띄어쓰기 조절
	 * 2. 처리내용 : 
	 * 		textarea에 taView 출력 시 데이터 양에 맞게 textarea의 크기및 띄어쓰기 조절
	 *  	사용 예) <textarea taView />
	 * </pre>
	 * @Function Name : taView
	 * @date : 2015. 06. 01.
	 * @author : 은우소프트 손연우
	 * @history : 
	 *  =======================================================
	 *  변경일             		작성자                     		변경내용  
	 *  =======================================================
	 *  2015. 06. 01.       은우소프트 손연우              최초 작성 
	 *  =======================================================   
	 */
	
	$("[taView]").each(function(){
		
		function replaceAll(temp, org, replace){
		    return temp.split(org).join(replace);
		}
		
		$(this).after("<span></span>");
		$(this).next().html(replaceAll($(this).val(), "\n", "<br>"));
	});
	
	
	/**  
	 * <pre>
	 * 1. 개요 : attribute에 money 입력시 숫자체크 및 콤마처리
	 * 2. 처리내용 : 
	 * 		attribute에 money 입력 시 숫자체크하여 안내창 보여주고
	 * 		금액 콤마처리
	 *  	사용 예) <input type="text" money />
	 * </pre>
	 * @Function Name : money
	 * @date : 2015. 1. 23.
	 * @author : 은우소프트 하성윤
	 * @history : 
	 *  =======================================================
	 *  변경일             		작성자                     		변경내용  
	 *  =======================================================
	 *  2015. 1. 23.       은우소프트 하성윤              최초 작성 
	 *  =======================================================   
	 */
	$("[money]").on("keyup", function(){
		if($(this).val() != "" ){
			if(FwkCmmnUtil.isNumeric($(this).val()) != true){
				if(FwkCmmnUtil.isNumeric($(this).val().split(",").join("")) != true){
					alert("숫자만 입력 가능합니다.");
				}
				$(this).val( $(this).val().replace(/[^0-9]|\[,]/gi,"")); 
			}else{
				// 소수점이 안들어가도록 처리
				$(this).val( $(this).val().replace(/[^0-9]|\[,]|\[.]/gi,""));
				if($(this).val().indexOf(",") > -1){
					$(this).val( $(this).val().split(",").join("") );
				}
			}
			$(this).val(Number($(this).val().replace(/,/gi,"")));
		}
		//$(this).val(FwkCmmnUtil.addComma($(this).val()));
		$(this).val(Comma($(this).val()));
		if(Number($(this).val().replace(/,/gi,"")) > 1000000000000){
			alert("1조 미만으로 입력해 주세요.");
			$(this).val("");
		}
	}).on("change", function(){
		if($(this).val() != "" ){
			if(FwkCmmnUtil.isNumeric($(this).val()) != true){
				$(this).val( $(this).val().replace(/[^0-9]|\[,]/gi,"")); 
			}
		}
		//$(this).val(FwkCmmnUtil.addComma($(this).val()));
		$(this).val(Comma($(this).val()));
	}).on("blur", function(){
		if($(this).val() != "" ){
			if(Number($(this).val().replace(/,/gi,"")) == 0 ){
				alert("0원이 들어갈수 없습니다.");
				$(this).val("");
				$(this).focus();
			}
		}
	});
	
	
	$("[money1]").on("keyup", function(){
		if($(this).val() != "" ){
			if(FwkCmmnUtil.isNumeric($(this).val()) != true){
				if(FwkCmmnUtil.isNumeric($(this).val().split(",").join("")) != true){
					alert("숫자만 입력 가능합니다.");
				}
				// 소수점이 하나 이미 있는 경우
				if($(this).val().indexOf('.') > 0){
					var dectext = $(this).val().substring($(this).val().indexOf('.')+1, $(this).val().length); 
					if (dectext.length > 2){ 
						alert ("소수점 2자리까지만 입력할 수 있습니다"); 
						$(this).val($(this).val().substring(0, $(this).val().indexOf('.')+3)); 
						$(this).focus(); 
					}else{
						
					}
				}else{
					$(this).val( $(this).val().replace(/[^0-9]|\[,]/gi,"")); 
				}
			}
		}
		
		if($(this).val().indexOf('.') > 0){
			var dectext = $(this).val().substring($(this).val().indexOf('.')+1, $(this).val().length); 
			if (dectext.length > 2){ 
				alert ("소수점 2자리까지만 입력할 수 있습니다"); 
				$(this).val($(this).val().substring(0, $(this).val().indexOf('.')+3)); 
				$(this).focus(); 
			}
		}
		$(this).val(FwkCmmnUtil.addComma($(this).val()));
		if(Number($(this).val().replace(/,/gi,"")) > 1000000000000){
			alert("1조 미만으로 입력해 주세요.");
			$(this).val("");
		}
		
	}).on("change", function(){
		if($(this).val() != "" ){
			if(FwkCmmnUtil.isNumeric($(this).val()) != true){
				$(this).val( $(this).val().replace(/[^0-9]|\[,]/gi,"")); 
			}
			if($(this).val().indexOf('.') > 0){
				var dectext = $(this).val().substring($(this).val().indexOf('.')+1, $(this).val().length); 
				if (dectext.length > 2){ 
					alert ("소수점 2자리까지만 입력할 수 있습니다"); 
					$(this).val($(this).val().substring(0, $(this).val().indexOf('.')+3)); 
					$(this).focus(); 
				}
			}
			$(this).val(FwkCmmnUtil.addComma($(this).val()));
		}
	});
	
	$("[money2]").on("keyup", function(){
		if($(this).val() != "" ){
			if(FwkCmmnUtil.isNumeric($(this).val()) != true){
				if(FwkCmmnUtil.isNumeric($(this).val().split(",").join("")) != true){
					alert("숫자만 입력 가능합니다.");
				}
				$(this).val( $(this).val().replace(/[^0-9]|\[,]/gi,"")); 
			}else{
				// 소수점이 안들어가도록 처리
				$(this).val( $(this).val().replace(/[^0-9]|\[,]|\[.]/gi,""));
				if($(this).val().indexOf(",") > -1){
					$(this).val( $(this).val().split(",").join("") );
				}
			}
			$(this).val(Number($(this).val().replace(/,/gi,"")));
		}
	//	$(this).val(FwkCmmnUtil.addComma($(this).val()));
		if(Number($(this).val().replace(/,/gi,"")) > 1000000000000){
			alert("1조 미만으로 입력해 주세요.");
			$(this).val("");
		}
	}).on("change", function(){
		if($(this).val() != "" ){
			if(FwkCmmnUtil.isNumeric($(this).val()) != true){
				$(this).val( $(this).val().replace(/[^0-9]|\[,]/gi,"")); 
			}
		}
	//	$(this).val(FwkCmmnUtil.addComma($(this).val()));
	}).on("blur",function(){
		$(this).val(FwkCmmnUtil.addComma($(this).val()));
	});
	
	$("[money0]").on("keyup", function(){
		if($(this).val() != "" ){
			if(FwkCmmnUtil.isNumeric($(this).val()) != true){
				if(FwkCmmnUtil.isNumeric($(this).val().split(",").join("")) != true){
					alert("숫자만 입력 가능합니다.");
				}
				$(this).val( $(this).val().replace(/[^0-9]|\[,]/gi,"")); 
			}else{
				// 소수점이 안들어가도록 처리
				$(this).val( $(this).val().replace(/[^0-9]|\[,]|\[.]/gi,""));
				if($(this).val().indexOf(",") > -1){
					$(this).val( $(this).val().split(",").join("") );
				}
			}
		}
		$(this).val(Number($(this).val().replace(/,/gi,"")));
		$(this).val(FwkCmmnUtil.addComma($(this).val()));
		if(Number($(this).val().replace(/,/gi,"")) > 1000000000000){
			alert("1조 미만으로 입력해 주세요.");
			$(this).val("");
		}
	}).on("change", function(){
		if($(this).val() != "" ){
			if(FwkCmmnUtil.isNumeric($(this).val()) != true){
				$(this).val( $(this).val().replace(/[^0-9]|\[,]/gi,"")); 
			}
		}
		$(this).val(FwkCmmnUtil.addComma($(this).val()));
	});
	$("[money3]").on("keyup", function(){
		if($(this).val() != "" ){
			if(FwkCmmnUtil.isNumeric($(this).val()) != true){
				if(FwkCmmnUtil.isNumeric($(this).val().split(",").join("")) != true){
					alert("숫자만 입력 가능합니다.");
				}
				$(this).val( $(this).val().replace(/[^0-9]|\[,]/gi,"")); 
			}else{
				// 소수점이 안들어가도록 처리
				$(this).val( $(this).val().replace(/[^0-9]|\[,]|\[.]/gi,""));
				if($(this).val().indexOf(",") > -1){
					$(this).val( $(this).val().split(",").join("") );
				}
			}
		}
		//$(this).val(FwkCmmnUtil.addComma($(this).val()));
		$(this).val(Comma($(this).val()));
		if(Number($(this).val().replace(/,/gi,"")) > 1000000000000){
			alert("1조 미만으로 입력해 주세요.");
			$(this).val("");
		}
	}).on("change", function(){
		if($(this).val() != "" ){
			if(FwkCmmnUtil.isNumeric($(this).val()) != true){
				$(this).val( $(this).val().replace(/[^0-9]|\[,]/gi,"")); 
			}
		}
		//$(this).val(FwkCmmnUtil.addComma($(this).val()));
		$(this).val(Comma($(this).val()));
	}).on("blur", function(){
		if($(this).val() != "" ){
			if(Number($(this).val().replace(/,/gi,"")) == 0 ){
				alert("0원이 들어갈수 없습니다.");
				$(this).val("");
				$(this).focus();
			}
		}
	});
	
	/**  
	 * <pre>
	 * 1. 개요 : 퍼센트 제한 (100% 이하 소수첫째자리까지)
	 * 2. 처리내용 : 
	 * 		퍼센트 제한 (100% 이하 소수첫째자리까지)
	 *  	사용 예) <input type="text" class="PT" >
	 * </pre>
	 * @Function Name : PT
	 * @date : 2015. 9. 23.
	 * @author : 은우소프트 전상훈
	 * @history : 
	 *  =======================================================
	 *  변경일             		작성자                     		변경내용  
	 *  =======================================================
	 *  2015. 9. 23.       은우소프트 전상훈              최초 작성 
	 *  =======================================================   
	 */
	$(".PT0").on("blur", function(){
		if($(this).val() != '' ){
			if($(this).val().indexOf(".")!=-1){
				alert ("소수점이 들어갈 수 없습니다."); 
				$(this).val($(this).val().replace(/./gi,""));
				$(this).focus();
			}else if(Number($(this).val()) > 100){
				alert("100 이하로만 입력할 수 있습니다.");
				$(this).val("");
				$(this).focus();
			}
		}
	});
	$(".PT2").on("blur", function(){
		var pattern = /^\d+([.][\d]{1,2})?$/;
		if($(this).val() != '' ){
			if(!pattern.test($(this).val())){
				alert ("소수점 2자리까지만 입력할 수 있습니다"); 
				$(this).val("");
				$(this).focus();
			}else if(Number($(this).val()) > 100){
				alert("100 이하로만 입력할 수 있습니다.");
				$(this).val("");
				$(this).focus();
			}
		}
	});
	$(".PT3").on("blur", function(){
		var pattern = /^\d+([.][\d]{1,3})?$/;
		if($(this).val() != '' ){
			if(!pattern.test($(this).val())){
				alert ("소수점 3자리까지만 입력할 수 있습니다"); 
				$(this).val("");
				$(this).focus();
			}else if(Number($(this).val()) > 100){
				alert("100 이하로만 입력할 수 있습니다.");
				$(this).val("");
				$(this).focus();
			}
		}
	});
	
	/**  
	 * <pre>
	 * 1. 개요 : inputbox, textarea에 특정 특수문제 제한 하기
	 * 2. 처리내용 : 
	 * 		inputbox 및 textarea에 $, &, <, >, ', " 제한 한다.
	 * </pre>
	 * @Function Name : spCharCeck
	
	 * @date : 2016. 01. 20.
	 * @author : 은우소프트 전상훈
	 * @history : 
	 *  =======================================================
	 *  변경일             		작성자                     		변경내용  
	 *  =======================================================
	 *  2016. 01. 20.       은우소프트 전상훈              최초 작성 
	 *  =======================================================   
	 */
	 isSpChar =  function(str){
		var re=/^[^$&<>'"|]*$/gi;
		return re.test(str);
	};
	$("input").on("keyup", function(){
		if("password" != $(this).attr("type").toLowerCase()){
			if($(this).val() != ""){
				if(!isSpChar($(this).val())){
					alert("$, &, <, >, ', \", | 는 입력 하실 수 없습니다.");
					$(this).val($(this).val().replace(/[$&<>'"|]/gi,""));
				}
			}
	    }
	});
	
	$("textarea").on("keyup", function(){
		if($(this).val() != ""){
			if(!isSpChar($(this).val())){
				alert("$, &, <, >, ', \", | 는 입력 하실 수 없습니다.");
				$(this).val($(this).val().replace(/[$&<>'"|]/gi,""));
			}
		}
	});
	
	$("input, textarea").on("focusin", function(){
		if(!$(this).attr("readonly")){
			if(this.type != 'button' && this.type != 'img' && this.type != 'radio' && this.type != 'file'){
				$(this).css({
					"background-color": "#f2f9ff",
					"border" : "2px solid #d8e5f1",
					"border-bottom": "2px solid #d8e5f1"
				});
			}
		}
		
	}).on("focusout", function(){
		if(!$(this).attr("readonly")){
			if(this.type != 'button' && this.type != 'img' && this.type != 'radio' && this.type != 'file'){
				$(this).css({
					"background-color": "white",
					"border" : "1px solid #d7d6d6",
					"border-bottom": "1px solid #d7d6d6"
				});
			}
		}
	});
	
	/*
	 *	 bullet_orange 요소 다음 input에 박스 처리 하기
	 */
	var bullet_orange_list = $('.bullet_orange');
	if(bullet_orange_list.length > 0) {
		$.each(bullet_orange_list, function(idx, value) {
			var $input = $(value).next().find('input');
			var $select = $(value).next().find('select');
			var $textarea = $(value).next().find('textarea');
			
			$.each($input, function(idy, val) {
				$(val).css({'background':'url("'+ FwkCmmnUtil.contextPath + '/statics/images/opro/main/required01.gif") no-repeat','background-position':'top right'});
			});
			$.each($select, function(idy, val) {
				$(val).css({'background':'url("'+ FwkCmmnUtil.contextPath + '/statics/images/opro/main/required01.gif") no-repeat','background-position':'top right'});
			});
			$.each($textarea, function(idy, val) {
				$(val).css({'background':'url("'+ FwkCmmnUtil.contextPath + '/statics/images/ipro/main/required01.gif") no-repeat','background-position':'top right'});
			});			
		});
	}	

});

/**  
* <pre>
* 1. 개요 : 달력 생성(자동 말고 펑션으로)
* 2. 처리내용 : 
* </pre> 
* @Function Name : setDatePicker
* @date : 2019. 04. 30.
* @author : 은우소프트 맹경열
* @history : 
*  =======================================================
*  변경일             		작성자                     		변경내용  
*  =======================================================
*  2019. 04. 30.       은우소프트 맹경열                    최초 작성 
*  =======================================================   
*/
setDatePicker = function() {
	$( "[date]" ).datepicker({
	    //datepicker trigger 지정
	    showOn: "both" //엘리먼트와 이미지 동시 사용(both,button
	    //한글 지정
	    , regional : "ko"	//ko-KR
		, buttonText: '달력선택'	//버튼 텍스트 표시
			
		, closeText: '닫기'
		, prevText: '이전'
		, nextText: '다음'
		, currentText: '오늘'
		, monthNames: ['1','2','3','4','5','6','7','8','9','10','11','12']
		, monthNamesShort: ['1','2','3','4','5','6','7','8','9','10','11','12']
		, dayNames: ['S','M','T','W','T','F','S']
		, dayNamesShort: ['S','M','T','W','T','F','S']
		, dayNamesMin: ['S','M','T','W','T','F','S']
		, weekHeader: 'Wk'
		, dateFormat: 'yy-mm-dd'
		, firstDay: 0
		, isRTL: false
		, showMonthAfterYear: true
		, yearSuffix: '.'
		//showOtherMonths: true
		, constrainInput: false
		, buttonImage: FwkCmmnUtil.contextPath + '/statics/images/opro/sbdc/calendar_icon01.png'
		, buttonImageOnly: true			
	
	});
	
	$("[date]").on("keydown", function(){
		$(this).val($(this).val().replace(/[^0-9-]/gi,""));
	});
	$("[date]").on("blur", function(){
		if($(this).val() != ""){
			if(!FwkDateUtil.isDate(hyphenDelete($(this).val()), "yyyyMMdd")){
				alert(FwkMssageUtil.getMessage("EW.INF.023"));
				$(this).val("");
				$(this).focus();
				return false;
			}
			var d = hyphenDelete($(this).val());
			$(this).val(d.substring(0, 4)+'-'+d.substring(4, 6)+'-'+d.substring(6));
		}
	});
	
	$("[date]").on("keyup", function(){
		var value = $(this).val().trim();
		var size = value.length;
		
		if(size > 10){
			$(this).val(value.substring(0,10));
			return false;
		}
		for(var i=0; i < size; i++){
			if(i == 4){
				if(size == 5){
					if(value.charAt(i) != "-"){
						$(this).val(value.substring(0, 4)+"-"+value.substring(4, 5));
					}
				}
			}else if(i==7){
				if(size == 8){
					if(value.indexOf("-") > 0){
						if(value.charAt(i) != "-"){
							$(this).val(value.substring(0, 7)+"-"+value.substring(7, 8));
						}
					}else{
						$(this).val(value.substring(0, 4)+"-"+value.substring(4, 6)+"-"+value.substring(6,8));
					}
				}
			}else if(i == 9){
				if(size == 10){
					if(value.indexOf("-") < 0){
						$(this).val(value.substring(0, 4)+"-"+value.substring(5, 7)+"-"+value.substring(8));
					}
				}
			}
		}
	});
	$("[date2]").on("blur", function(){
		if($(this).val() != ""){
			if(!FwkDateUtil.isDate(hyphenDelete($(this).val()), "yyyyMMdd")){
				alert(FwkMssageUtil.getMessage("EW.INF.023"));
				$(this).val("");
				$(this).focus();
				return false;
			}
			var d = hyphenDelete($(this).val());
			$(this).val(d.substring(0, 4)+'-'+d.substring(4, 6)+'-'+d.substring(6));
		}
	});
	$("[date2]").on("keydown", function(){
		$(this).val($(this).val().replace(/[^0-9-]/gi,""));
	});
	$("[date2]").on("keyup", function(){
		var value = $(this).val().trim();
		var size = value.length;
		
		if(size > 10){
			$(this).val(value.substring(0,10));
			return false;
		}
		for(var i=0; i < size; i++){
			if(i == 4){
				if(size == 5){
					if(value.charAt(i) != "-"){
						$(this).val(value.substring(0, 4)+"-"+value.substring(4, 5));
					}
				}
			}else if(i==7){
				if(size == 8){
					if(value.indexOf("-") > 0){
						if(value.charAt(i) != "-"){
							$(this).val(value.substring(0, 7)+"-"+value.substring(7, 8));
						}
					}else{
						$(this).val(value.substring(0, 4)+"-"+value.substring(4, 6)+"-"+value.substring(6,8));
					}
				}
			}else if(i == 9){
				if(size == 10){
					if(value.indexOf("-") < 0){
						$(this).val(value.substring(0, 4)+"-"+value.substring(5, 7)+"-"+value.substring(8));
					}
				}
			}
		}
	});	
};


/**  
* <pre>
* 1. 개요 : selectBox 년월
* 2. 처리내용 : 
* </pre> 
* @Function Name : requeird
* @date : 2016. 12. 30.
* @author : 은우소프트 홍찬일
* @history : 
*  =======================================================
*  변경일             		작성자                     		변경내용  
*  =======================================================
*  2016. 12. 30.       은우소프트 홍찬일                    최초 작성 
*  =======================================================   
*/
selectCal = function(monthfield, yearfield){
	var today=new Date();
	var m;
		for(var i = 1; i < 13; i++){
			if(10 > i) m = "0"+i;
			else m = i;
			if((today.getMonth()+1) == i)$("#"+monthfield).append("<option value='"+m+"' selected='selected'>"+m+"</option>");
			else $("#"+monthfield).append("<option value='"+m+"'>"+m+"</option>"); 
	}

	var thisyear=today.getFullYear();
	for (var y=thisyear-10; y<thisyear+11; y++){
		if(thisyear == y) $("#"+yearfield).append("<option value='"+y+"' selected='selected'>"+y+"</option>"); 
	    else $("#"+yearfield).append("<option value='"+y+"'>"+y+"</option>"); 
	}
};

/**  
 * <pre>
 * 1. 개요 : grid 화면에 그린다.
 * 2. 처리내용 : 
 * 		- grid 화면에 그린다.
 * </pre>
 * @Function Name : gridOnload
 * @date : 2017. 06. 13.
 * @author : 은우소프트 맹경열
 * @history : 
 *  =======================================================
 *  변경일             		작성자                     		변경내용  
 *  =======================================================
 *  2016. 08. 08.       은우소프트 전상훈              최초 작성 
 *  =======================================================   
 */
gridOnload = function(data,obj,gridbox,colids,title,width,align,types,sort,rowFlag,rowFn,cellFlag,cellFn) {
	var alignArr = [];
	var arr = title.split(',');
	for (var int = 0; int < arr.length; int++) {
		alignArr[int] = "text-align:center;";
	}
	obj = new dhtmlXGridObject(gridbox);
	obj.setImagePath("../../dhtmlxSuite_v51_pro/codebase/imgs/");
	obj.setColumnIds(colids);
	obj.setHeader(title,null,alignArr);
	obj.setInitWidths(width);
	obj.setColAlign(align);
	obj.setDateFormat("%Y-%m-%d");
	obj.setColTypes(types);
	obj.setColSorting(sort);
	//obj.setSkin("dhx_skyblue");
	obj.enableRowsHover(true,"hover");
	if(rowFlag != null && rowFlag){
		obj.attachEvent("onRowSelect",rowFn);
	}
	if(cellFlag != null && cellFlag){	
		obj.enableMarkedCells();
		obj.attachEvent("onCellMarked", cellFn);
	}
	//obj.enableAutoWidth(true);
	obj.enableAutoHeight(true);
	obj.init();
	obj.parse(data,"json");
	
	return obj;
};	


//콤마제거
unComma =function(input) {
  var inputString = new String;
  var outputString = new String;
  var outputNumber = new Number;
  var counter = 0;
  
  if (input == '' || input == undefined || input == "undefined") {
     return ''; 
  }
  
  inputString = input.toString();

  /*
   * 소수점 추가
   */
  var dpt = "";
  if(inputString.indexOf('.') > 0){
  	dpt = inputString.substring(inputString.indexOf('.'), inputString.length);
  	inputString = inputString.substring(0, inputString.indexOf('.'));
  }
  
  //inputString=input;
  outputString='';
  for (counter=0; counter<inputString.length; counter++) {
     outputString += (inputString.charAt(counter) != ',' ?inputString.charAt(counter) : '');
  }
  outputNumber = parseFloat(outputString);
  
  if(isNaN(outputNumber)){
     outputNumber = "";
  }
  
 outputNumber = outputNumber + dpt;
  
  return (outputNumber);
};


//콤마처리
Comma = function(input) {
	var inputString = new String;
	var outputString = new String;
	var counter = 0;
	var decimalPoint = 0; 
	var end = 0;
	var modval = 0;
	var min = false;
	
	if (input == '' || input == undefined || input == "undefined") {
        return ''; 
    }
	
	inputString = input.toString();
	outputString = '';
	decimalPoint = inputString.indexOf('.', 1);
	
	if(inputString.charAt(0) == "-"){
	  min = true;
	  inputString = inputString.substring(1);
	}
	
	if(decimalPoint == -1) {
	 end = inputString.length - (inputString.charAt(0)=='0' ? 1:0);
	 for (counter=1;counter <=inputString.length; counter++) {
	    var modval =counter - Math.floor(counter/3)*3;
	    outputString = (modval==0 && counter <end ? ',' : '') + inputString.charAt(inputString.length - counter) + outputString;
	 }
	}else {
	 end = decimalPoint - ( inputString.charAt(0)=='-' ? 1 :0);
	 for (counter=1; counter <= decimalPoint ; counter++){
		 var modval =counter - Math.floor(counter/3)*3;
	    outputString = (modval==0  && counter <end ? ',' : '') +  inputString.charAt(decimalPoint - counter) + outputString;
	 }
	 for (counter=decimalPoint; counter < decimalPoint+4; counter++) {
	    outputString += inputString.charAt(counter);
	 }
	}
	
	if(min){
	 outputString = "-"+outputString;
	}
	
	return (outputString);
};

 // x 축 크기
 x = function(input) {
	return (window.innerWidth - input)/2;
 };
 // y 축 크기
 y = function(input) {
	 var aa = (document.body.scrollTop + (window.innerHeight/2));
	 return (aa - (input /2));
 };

/**  
 * <pre>
 * 1. 개요 : 날짜 체크
 * 2. 처리내용 : 
 *  	- 날짜를 체크 한다.
 * </pre>
 * @Function Name : dateCeck
 * @date : 2015. 07. 17.
 * @author : 은우소프트 전상훈
 * @history : 
 *  =======================================================
 *  변경일             		작성자                     		변경내용  
 *  =======================================================
 *  2015. 07. 17.       은우소프트 전상훈              최초 작성 
 *  =======================================================   
 */
dateCeck = function(begin,end) {
	var numbegin = Number(hyphenDelete(begin));
	var numend = Number(hyphenDelete(end));
	if(numbegin != 0 && numend != 0){
		if(numbegin > numend){
			alert(FwkMssageUtil.getMessage("EW.INF.002", "종료일자", "시작일자"));
			return true;
		}
	}
	return false;
};

/**  
 * <pre>
 * 1. 개요 : 입력날짜와 현재날짜 체크
 * 2. 처리내용 : 
 * 		- 입력날짜와 현재날짜 비교하여 현재이전 날짜 입력 못하도록 처리
 * </pre>
 * @Function Name : inputCheckToday
 * @date : 2015. 08. 25.
 * @author : 은우소프트 하성윤
 * @history : 
 *  =======================================================
 *  변경일             		작성자                     		변경내용  
 *  =======================================================
 *  2015. 08. 25.       은우소프트 하성윤                    최초 작성 
 *  =======================================================   
 */
inputCheckToday = function(obj) {
	var numToday = FwkDateUtil.getCurrentDate("yyyyMMdd");
	var numObj = hyphenDelete($(obj).val());
	var result = true;
	if(numToday != "" && numObj != ""){
		if(Number(numToday) > Number(numObj)){
			alert(FwkMssageUtil.getMessage("EW.INF.002", "입력일자", "현재일자"));
			$(obj).val("");
			$(obj).focus();
			result = false;
		}
	}
	return result;
};

/**  
 * <pre>
 * 1. 개요 : 하이픈 제거
 * 2. 처리내용 : 
 * 		하이픈을 제거한 값을 반환한다.
 * </pre>
 * @Function Name : hyphenDelete
 * @date : 2015. 07. 17.
 * @author : 은우소프트 전상훈
 * @history : 
 *  =======================================================
 *  변경일             		작성자                     		변경내용  
 *  =======================================================
 *  2015. 07. 17.       은우소프트 전상훈                    최초 작성 
 *  =======================================================   
 */
hyphenDelete = function (str) {
	var result = "";
	if(str != null && str != "" && str != undefined ){
		result = str.replace(/-/gi,"");
	}
	return result;
};	


/**  
 * <pre>
 * 1. 개요 : 로딩바
 * 2. 처리내용 : 
 * 		
 * </pre>
 * @Function Name : loading
 * @date : 2016. 12. 29
 * @author : 은우소프트 홍찬일
 * @history : 
 *  =======================================================
 *  변경일             		작성자                     		변경내용  
 *  =======================================================
 *  2016. 12. 29       은우소프트 홍찬일                    최초 작성 
 *  =======================================================   
 */
loading = function(){
	var width = $("#panelSubContent").width();
	var height = $("#panelSubContent").height();
	//화면을 가리는 레이어의 사이즈 조정
	$("#panelSubContent").fadeTo(100, 0.3);
	$('#panelSubContent').append('<img id="loadImg" src="/statics/images/loadingBar.gif">');
	$("#loadImg").css("position","absolute").css("z-index","10001").css("top","50%").css("left","50%").css("margin",$("#panelSubContent").height()/2*-1+" 0 0 "+$("#panelSubContent").width()/2*-1);
	
	$("#panelSubContent").find("button, img, tr").prop("disabled",true);
	$("#panelSubContent").find("input[type=text], select").prop("readonly",true);
	$("tr").attr('onclick','').unbind('click'); 
	$("#panelSubContent").click(function(e){e.returnValue = false;});
};

loading_End = function(){
	$("#panelSubContent").fadeTo(0, 1);
	$("#loadImg").remove();
	$("#panelSubContent").find("button, img, tr").prop("disabled",false);
	$("#panelSubContent").find("input[type=text], select").prop("readonly",false);
	$("#panelSubContent").click(function(e){e.returnValue = true;});
};


/**  
 * <pre>
 * 1. 개요 : 필수체크
 * 2. 처리내용 : 
 * 		th에 bullet_orange 처리시 필수체크
 * </pre>
 * @Function Name : requeird
 * @date : 2016. 11. 24.
 * @author : 은우소프트 하성윤
 * @history : 
 *  =======================================================
 *  변경일             		작성자                     		변경내용  
 *  =======================================================
 *  2016. 11. 24.       은우소프트 하성윤                    최초 작성 
 *  =======================================================   
 */
required_ew = function(){
	var flag = true;
	if($(".bullet_orange").length > 0){
		$(".bullet_orange").each(function(){
			var obj = $(this);
			var text = $(this).text();
			var input = obj.next().find("input");
			var select = obj.next().find("select");
			var textarea = obj.next().find("textarea");
			if(obj.parent().css("display") != "none"){
				// input 필수체크
				if(input.length == 1){
					// type=text 필수체크
					if(input.parent().css("display") != "none"){
						if(input.attr("type") == "text"){
							//var text = input.prev().text();
							if(input.val() == "" && (!input.hasClass("notCheck"))){
								alert("["+text+"]" +" 항목은 필수입력 입니다.");
								flag = false;
								input.focus();
								return false;
							}
						}
					}
					
				}else if(input.length > 1){
					$.each(input, function(){
						if(input.parent().css("display") != "none"){
							if($(this).attr("type") == "text"){
								//var text = $(this).prev().text();
								if($(this).val() == "" && (!$(this).hasClass("notCheck"))){
									alert("["+text+"]" +" 항목은 필수입력 입니다.");
									flag = false;
									$(this).focus();
									return false;
								}
							}
						}
					});
				}
				
				if(!flag){
					return false;
				}
				
				// select 필수체크
				if(select.length == 1){
					//var text = $(this).text();
					if(select.parent().css("display") != "none"){
						if(select.val() == ""){
							alert("["+text+"]" +" 항목은 필수선택 입니다.");
							flag = false;
							select.focus();
							return false;
						}
					}
				}
				
				if(textarea.length == 1){
					if(textarea.parent().css("display") != "none"){
						if(textarea.val() == ""){
							alert("["+text+"]" +" 항목은 필수선택 입니다.");
							flag = false;
							textarea.focus();
							return false;
						}
					}
				}
				
				if(textarea.length > 1){
					$.each(textarea, function(index){
						if($(this).parent().css("display") != "none"){
							if($(this).css("display") != "none"){
								if($(this).val() == ""){
									alert("["+text+"]" +" 항목은 필수선택 입니다.");
									flag = false;
									$(this).focus();
									return false;
								}
							}
						}
					});
				}
			}
		});
	}
	return flag;
};

/**  
 * <pre>
 * 1. 개요 : attribute에 moblphon 입력했을 경우 하이픈제거
 * 2. 처리내용 : 
 * 		attribute에 moblphon 입력했을 경우 하이픈제거
 *  	사용 예) <input type="text" moblphon />
 * </pre>
 * @Function Name : removeMoblphon
 * @date : 2015. 03. 17.
 * @author : 은우소프트 손연우
 * @history : 
 *  =======================================================
 *  변경일             		작성자                     		변경내용  
 *  =======================================================
 *  2015. 03. 17.       은우소프트 손연우              최초 작성 
 *  =======================================================   
 */

removeHyphen = function() {
	$("[moblphon],[bizrno],[telno]").each(function() {
		if($(this).val() != "") {
			//$(this).val(FwkCmmnUtil.deleteHypen($(this).val()));
			$(this).val($(this).val().replace( /-/g, ""));
		}
	});
};

/**  
 * <pre>
 * 1. 개요 : attribute에 money 입력했을 경우 콤마제거
 * 2. 처리내용 : 
 * 		attribute에 money 입력했을 경우 콤마제거
 *  	사용 예) <input type="text" money />
 * </pre>
 * @Function Name : removeComma
 * @date : 2015. 02. 04.
 * @author : 은우소프트 하성윤
 * @history : 
 *  =======================================================
 *  변경일             		작성자                     		변경내용  
 *  =======================================================
 *  2015. 02. 04.       은우소프트 하성윤              최초 작성 
 *  =======================================================   
 */
removeComma = function(){
	$("[money]").each(function(){
		if($(this).val() != ""){
			$(this).val(FwkCmmnUtil.deleteComma($(this).val()));
		}
	});
	
	$("[money1]").each(function(){
		if($(this).val() != ""){
			$(this).val(FwkCmmnUtil.deleteComma($(this).val()));
		}
	});

	$("[money2]").each(function(){
		if($(this).val() != ""){
			$(this).val(FwkCmmnUtil.deleteComma($(this).val()));
		}
	});
	
	$("[money0]").each(function(){
		if($(this).val() != ""){
			$(this).val(FwkCmmnUtil.deleteComma($(this).val()));
		}
	});
	
	$(".money").each(function(){
		if($(this).val() != ""){
			$(this).val(FwkCmmnUtil.deleteComma($(this).val()));
		}
	});
	
};

/**  
 * <pre>
 * 1. 개요 : attribute에 moblphon 입력했을 경우 하이픈제거
 * 2. 처리내용 : 
 * 		attribute에 moblphon 입력했을 경우 하이픈제거
 *  	사용 예) <input type="text" moblphon />
 * </pre>
 * @Function Name : removeMoblphon
 * @date : 2015. 03. 17.
 * @author : 은우소프트 손연우
 * @history : 
 *  =======================================================
 *  변경일             		작성자                     		변경내용  
 *  =======================================================
 *  2015. 03. 17.       은우소프트 손연우              최초 작성 
 *  =======================================================   
 */
removeHyphen = function(){
	$("[date]").each(function(){
		if($(this).val() != ""){
			$(this).val(FwkCmmnUtil.deleteHyphen($(this).val()));
		}
	});
};

/**  
 * <pre>
 * 1. 개요 : 금액(숫자)을 한글로 변환
 * 2. 처리내용 : 
 * 		
 * </pre>
 * @Function Name : conversionNumToKor
 * @date : 2015. 02. 04.
 * @author : 은우소프트 하성윤
 * @history : 
 *  =======================================================
 *  변경일             		작성자                     		변경내용  
 *  =======================================================
 *  2015. 02. 04.       은우소프트 하성윤              최초 작성 
 *  =======================================================   
 */
conversionNumToKor = function(obj){
	
	var money = FwkCmmnUtil.deleteComma(obj.value);
	
	var index=0;
	var i=0;
	var result="";
	var newResult="";
	if(money == "") return ;
		
	if(money == "0") return "영";

	if(money.indexOf(",")>=0){
		alert("','를 제외하고 입력하십시요");
		return false;
	}

	if(isNaN(Number(money))){
		//alert("숫자로 입력하세요");
		return false;
	}
	if(money.length > 13){
		//alert("숫자가 너무 큽니다");
		return false;
	}
	if(money.indexOf(".")>=0){
		//alert("정수로 입력하십시오");
		return false;
	}
	if(money.indexOf("-")>=0){
		//alert("양수로 입력하십시오");
		return false;
	}

	su = new Array("0","1","2","3","4","5","6","7","8","9");
	km = new Array("영","일","이","삼","사","오","육","칠","팔","구");
	danwi = new Array("","십","백","천","만","십","백","천","억","십","백","천","조");
	for(j=1;j<=money.length;j++){
		for(index=0;index<10;index++){
			money = money.replace(su[index],km[index]);
		}
	}
	for(index = money.length;index>0;index=index-1){
		result = money.substring(index-1,index);
		if(result=="영"){
			if(i<4 || i>8){
				result = "";				
			}
			else if(i>=4 && i<8 && newResult.indexOf("만")<0){
				result = "만";
			}
			else if(i>=8 && i<12 && newResult.indexOf("억")<0){
				result = "억";
			}
		}else{
			result = result + danwi[i];
		}
		i++;
		newResult = result + newResult;
	}
	for(j=1;j<newResult.length;j++){
		newResult = newResult.replace("영","");
	}
	if((newResult.indexOf("만")-newResult.indexOf("억"))==1)
		newResult = newResult.replace("만","");
	if((newResult.indexOf("억")-newResult.indexOf("조"))==1)
		newResult = newResult.replace("억","");
	
	if( (obj.value == "") || (newResult == false) || (newResult == "") ) {
		
		$(obj).parent().find("font").text("");
        obj.value = "";
        obj.focus();
    }
    else {
    	$(obj).parent().find("font").text(newResult);
    }

    return;
};

/**  
 * <pre>
 * 1. 개요 : 첨부파일 정보 조회
 * 2. 처리내용 : 
 * </pre>
 * @Function Name : atchmnflInfoInqire
 * @date : 2019. 01. 28.
 * @author : 은우소프트 맹경열
 * @history : 
 *  =======================================================
 *  변경일             		작성자                     		변경내용  
 *  =======================================================
 *  2019. 01. 28.       은우소프트 맹경열              최초 작성 
 *  =======================================================    
 */
atchmnflInfoInqire = function(formId) {
	FwkCmmnUtil.submitAjax("/prcm/atchmnflInfoInqire", $("#"+formId).serializeJSON(), function(res) {
		var attnames = "";
		var atturls = "";
		for(var j = 0; j < res.atchmnflInfoList.length; j++){
			var fileInfo = res.atchmnflInfoList[j];
			if(j==0){
				attnames = "'"+fileInfo.ATCHMNFL_NM+"'";
				atturls = "'"+FwkMssageUtil.getMessage("SANCTN.FILE.DWN")+"/iep/prcm/download.do?P_ATCHMNFL_SN="+fileInfo.ATCHMNFL_SN+"&P_ATCHMNFL_GROUP_NO="+fileInfo.ATCHMNFL_GROUP_NO+"'";
			}else{
				attnames = attnames +"," + "'"+fileInfo.ATCHMNFL_NM+"'";
				atturls = atturls + "," + "'"+FwkMssageUtil.getMessage("SANCTN.FILE.DWN")+"/iep/prcm/download.do?P_ATCHMNFL_SN="+fileInfo.ATCHMNFL_SN+"&P_ATCHMNFL_GROUP_NO="+fileInfo.ATCHMNFL_GROUP_NO+"'";
				
			}
		}
		
		$("#"+formId+" #ATTNAMES").val(attnames);
		$("#"+formId+" #ATTURLS").val(atturls);
	});
};

/**  
 * <pre>
 * 1. 개요 : 파일 업로드
 * 2. 처리내용 : 
 * </pre>
 * @Function Name : fileUpload
 * @date : 2019. 01. 28.
 * @author : 은우소프트 맹경열
 * @history : 
 *  =======================================================
 *  변경일             		작성자                     		변경내용  
 *  =======================================================
 *  2019. 01. 28.       은우소프트 맹경열              최초 작성 
 *  =======================================================   
 */
	fileUpload = function(formId, fileGroupNoId, url, moveFormId, moveUrl, callBack) {
	    // 첨부파일 그룹번호 hidden Input 만들어주기
		//FwkCmmnUtil.addHiddenParam(formId, [{"id": fileGroupNoId, "name": fileGroupNoId, "value":""}]);
	
		//업로드 ~전송완료 후 호출되는 함수입니다.
		 RAONKUPLOAD_UploadComplete = function() {
			var resultText = RAONKUPLOAD.GetNewUploadList("text", "upload");
	 	  	//var resultText = RAONKUPLOAD.GetNewUploadListForText("upload");
	 	  	var resultTextAry = resultText.split("");
	//	 	  	var serverGugun = "WINDOW"//SFwkMssageUtil.getMessage("IEP.SERVER.GUBUN");
			var outHtml = "";
	 	  	var fileInfoAry;
	 	  	console.log("resultTextAry.length ==> " + resultTextAry.length);
	
	 	  	for (var idx = 0; idx < resultTextAry.length; idx++) {
	        	fileInfoAry = resultTextAry[idx].split("");
				var path = fileInfoAry[3]; //업로드경로 + 파일명
	
	//				if(serverGugun == "WINDOW"){ // window server인 경우
	//					path = path.slice(path.indexOf("\\")).toLowerCase();
	//					path = path.slice(0, path.indexOf(".")).toLowerCase();
	//					path = path.slice(0, path.lastIndexOf("\\")).toLowerCase();
	//					alert("if => ");
	//				}else{ // unix server인 경우
	//					path = path.slice(0, path.indexOf(".")).toLowerCase();
	//					path = path.slice(0, path.lastIndexOf("/")).toLowerCase();
	//				}
	
				var fileLctn = path.substring(0,path.lastIndexOf("/"));
			 	fileLctn = fileLctn.replace("//", '/');
	
				path = path.slice(0, path.indexOf("."));
				path = path.slice(0, path.lastIndexOf("/"));
	
				console.log("fileLctn::"+fileLctn);
	
				outHtml += "<input type='hidden' name='P_SYS_FILE_NM' 	value='" + fileInfoAry[0] + "' />";					// 시스템파일명
	            outHtml += "<input type='hidden' name='P_SV_FILE_NM' 	value='" + fileInfoAry[1] + "' />";					// 저장파일명
	            outHtml += "<input type='hidden' name='P_FILE_SZ' 		value='" + fileInfoAry[2]  + "' />";				// 파일사이즈
				outHtml += "<input type='hidden' name='P_FILE_LCTN' 	value='" + fileLctn + "' />";						// 파일경로명
			//	outHtml += "<input type='text' name='P_FILE[][P_ATCHMNFL_EXTSN_NM]' value='" + fileInfoAry[6] + "' />";		// 파일타입
	        }
	        $("#upload_fileInfo").html(outHtml);
	
	        //파일의 DB등록
	//	        var jsonData = $("#registFrm").serializeObject();
	
	    	FwkCmmnUtil.submitAjax("/comm/fileInfoRegist", $("#"+formId).serializeObject(), function(res) {
	    		//파일 그룹번호
	    		$("#"+fileGroupNoId).val(res.atchFileGroupNo);
	    		/*	if(moveFormId!=null && moveUrl!=null){
	    			comAjaxSubmit(formId, url, moveFormId, moveUrl);
	    		}else{*/
	
	    			//콜백 처리로 할경우
	    			if(!FwkCmmnUtil.isNull(callBack)){
	    				eval(callBack);
	    			}else{
	    				FwkCmmnUtil.submitForm(formId, url);
	    			}
	
	    		//}
	    	});
	   	};
	
		// 오류발생시 메시지
	   	RAONKUPLOAD_OnError = function(uploadID, code, message) {
			alert(FwkMssageUtil.getMessage("COM.ERR.003") + "\n\n" + uploadID + ', ' + code + ', ' + message);
	    };
	};
	fileEtcUpload = function(formId, fileGroupNoId, url, moveFormId, moveUrl, callBack) {
		// 첨부파일 그룹번호 hidden Input 만들어주기
		//FwkCmmnUtil.addHiddenParam(formId, [{"id": fileGroupNoId, "name": fileGroupNoId, "value":""}]);
		
		//업로드 ~전송완료 후 호출되는 함수입니다.
		RAONKUPLOAD_UploadComplete = function() {
			var resultText = RAONKUPLOAD.GetNewUploadList("text", "upload");
			//var resultText = RAONKUPLOAD.GetNewUploadListForText("upload");
			var resultTextAry = resultText.split("");
			//	 	  	var serverGugun = "WINDOW"//SFwkMssageUtil.getMessage("IEP.SERVER.GUBUN");
			var outHtml = "";
			var fileInfoAry;
			console.log("resultTextAry.length ==> " + resultTextAry.length);
			
			for (var idx = 0; idx < resultTextAry.length; idx++) {
				fileInfoAry = resultTextAry[idx].split("");
				var path = fileInfoAry[3]; //업로드경로 + 파일명
				
				//				if(serverGugun == "WINDOW"){ // window server인 경우
				//					path = path.slice(path.indexOf("\\")).toLowerCase();
				//					path = path.slice(0, path.indexOf(".")).toLowerCase();
				//					path = path.slice(0, path.lastIndexOf("\\")).toLowerCase();
				//					alert("if => ");
				//				}else{ // unix server인 경우
				//					path = path.slice(0, path.indexOf(".")).toLowerCase();
				//					path = path.slice(0, path.lastIndexOf("/")).toLowerCase();
				//				}
				
				var fileLctn = path.substring(0,path.lastIndexOf("/"));
				fileLctn = fileLctn.replace("//", '/');
				
				path = path.slice(0, path.indexOf("."));
				path = path.slice(0, path.lastIndexOf("/"));
				
				console.log("fileLctn::"+fileLctn);
				
				outHtml += "<input type='hidden' name='P_SYS_FILE_NM' 	value='" + fileInfoAry[0] + "' />";					// 시스템파일명
				outHtml += "<input type='hidden' name='P_SV_FILE_NM' 	value='" + fileInfoAry[1] + "' />";					// 저장파일명
				outHtml += "<input type='hidden' name='P_FILE_SZ' 		value='" + fileInfoAry[2]  + "' />";				// 파일사이즈
				outHtml += "<input type='hidden' name='P_FILE_LCTN' 	value='" + fileLctn + "' />";						// 파일경로명
				//	outHtml += "<input type='text' name='P_FILE[][P_ATCHMNFL_EXTSN_NM]' value='" + fileInfoAry[6] + "' />";		// 파일타입
			}
			$("#upload_fileInfo").html(outHtml);
			
			//파일의 DB등록
			//	        var jsonData = $("#registFrm").serializeObject();
			
			FwkCmmnUtil.submitAjax("/comm/fileInfoEtcRegist", $("#"+formId).serializeObject(), function(res) {
				//파일 그룹번호
				$("#"+fileGroupNoId).val(res.atchFileGroupNo);
				/*	if(moveFormId!=null && moveUrl!=null){
	    			comAjaxSubmit(formId, url, moveFormId, moveUrl);
	    		}else{*/
				
				//콜백 처리로 할경우
				if(!FwkCmmnUtil.isNull(callBack)){
					eval(callBack);
				}else{
					FwkCmmnUtil.submitForm(formId, url);
				}
				
				//}
			});
		};
		
		// 오류발생시 메시지
		RAONKUPLOAD_OnError = function(uploadID, code, message) {
			alert(FwkMssageUtil.getMessage("COM.ERR.003") + "\n\n" + uploadID + ', ' + code + ', ' + message);
		};
	};
	fileView = function(fileGrpNo) {

		var arrfileGrpNo = fileGrpNo.split(",");
		var idx = 0;
			//업로드 객체의 생성이 완료되었을 때 발생합니다.
			RAONKUPLOAD_CreationComplete = function(uploadID) {
				
				//idx++;
				if(("uploadView1" == uploadID)){
					idx = 1;
   				var param = {};
   				param.P_FILE_GRP_NO = arrfileGrpNo[(idx - 1)];

   				var actionUrl = "/comm/fileListInqireByFileGrpNo";
   				FwkCmmnUtil.submitAjax(actionUrl, param, function(data) {
   					//loading이미지 제거.
   					// FwkCmmnUtil.loading_end(); 
   					FwkCmmnUtil.loadingImageRemove();
						if(data.atchFileList.length > 0){
							for (var i = 0; i < data.atchFileList.length; i++) {
								var atchFile = data.atchFileList[i];

							 	var sn       = atchFile.FILE_SN;
							 	var name     = atchFile.SYS_FILE_NM;
							 	var path     = atchFile.FILE_LCTN;
							 	var cpcty    = atchFile.FILE_SZ;
							 	var filePath = path + FwkCmmnUtil.fileSeparator + atchFile.SV_FILE_NM;
							 	var custom   =  atchFile.TSK_VKEY3;

							 	//if ("uploadView"+idx == uploadID) {
							 	//업로드 리스트 컨트롤에 이미 업로드 되어 있는 파일을 셋팅합니다.
							 		RAONKUPLOAD.AddUploadedFile(sn, name, filePath , cpcty, custom, uploadID);
					            //}

							}
						}
   					});
   				}
				else if("uploadView2" == uploadID){
					idx = 2;
   				var param = {};
   				param.P_FILE_GRP_NO = arrfileGrpNo[(idx - 1)];

   				var actionUrl = "/comm/fileListInqireByFileGrpNo";
   				FwkCmmnUtil.submitAjax(actionUrl, param, function(data) {
   					//loading이미지 제거.
   					//FwkCmmnUtil.loading_end();
						if(data.atchFileList.length > 0){
							for (var i = 0; i < data.atchFileList.length; i++) {
								var atchFile = data.atchFileList[i];

							 	var sn       = atchFile.FILE_SN;
							 	var name     = atchFile.SYS_FILE_NM;
							 	var path     = atchFile.FILE_LCTN;
							 	var cpcty    = atchFile.FILE_SZ;
							 	var filePath = path + FwkCmmnUtil.fileSeparator + atchFile.SV_FILE_NM;
							 	var custom   =  atchFile.TSK_VKEY3;
							 	//alert("222"+uploadID);
							 	//if ("uploadView"+idx == uploadID) {
							 	//업로드 리스트 컨트롤에 이미 업로드 되어 있는 파일을 셋팅합니다.
							 		RAONKUPLOAD.AddUploadedFile(sn, name, filePath , cpcty, custom, uploadID);
					            //}

							}
						}
   					});
				}
				else if("upload" == uploadID){
					idx = 1;
   				var param = {};
   				param.P_FILE_GRP_NO = arrfileGrpNo[(idx - 1)];

//   				alert("param ::" + param.P_FILE_GRP_NO);
   				
   				var actionUrl = "/comm/fileListInqireByFileGrpNo";
   				FwkCmmnUtil.submitAjax(actionUrl, param, function(data) {
   					//loading이미지 제거.
   					//FwkCmmnUtil.loading_end();

						if(data.atchFileList.length > 0){
							for (var i = 0; i < data.atchFileList.length; i++) {
								var atchFile = data.atchFileList[i];

							 	var sn       = atchFile.FILE_SN;
							 	var name     = atchFile.SYS_FILE_NM;
							 	var path     = atchFile.FILE_LCTN;
							 	var cpcty    = atchFile.FILE_SZ;
							 	var filePath = path + FwkCmmnUtil.fileSeparator + atchFile.SV_FILE_NM;
							 	var custom   =  atchFile.TSK_VKEY3;
							 	//alert("222"+uploadID);
							 	//if ("uploadView"+idx == uploadID) {
							 	//업로드 리스트 컨트롤에 이미 업로드 되어 있는 파일을 셋팅합니다.
							 		RAONKUPLOAD.AddUploadedFile(sn, name, filePath , cpcty, custom, uploadID);
					            //}

							}
						}
   					});
				}
			};
	};

	
	/**
   	 * 첨부파일 view
   	 * @param fileGrpEtcNo
   	 */
   	fileEtcView = function(fileGrpNo) {
   		var arrfileGrpNo = fileGrpNo.split(",");
   		var idx = 0;
   			//업로드 객체의 생성이 완료되었을 때 발생합니다.
   			RAONKUPLOAD_CreationComplete = function(uploadID) {
   				//idx++;
			if(("uploadView1" == uploadID)){
				idx = 1;
   				var param = {}; 
   				param.P_FILE_GRP_NO_ETC = arrfileGrpNo[(idx - 1)];

   				var actionUrl = "/comm/fileEtcListByFileGrpNo";
   				FwkCmmnUtil.submitAjax(actionUrl, param, function(data) {
   					//loading이미지 제거.
   					FwkCmmnUtil.loadingImageRemove();
						if(data.atchFileList.length > 0){
							for (var i = 0; i < data.atchFileList.length; i++) {
								var atchFile = data.atchFileList[i];

							 	var sn       = atchFile.FILE_SN;
							 	var name     = atchFile.SYS_FILE_NM;
							 	var path     = atchFile.FILE_LCTN;
							 	var cpcty    = atchFile.FILE_SZ;
							 	var filePath = path + FwkCmmnUtil.fileSeparator + atchFile.SV_FILE_NM;
							 	var custom   =  atchFile.TSK_VKEY3;

						 		RAONKUPLOAD.AddUploadedFile(sn, name, filePath , cpcty, custom, uploadID);
							}
						}
   					}); 
   				}
   			};
   	};
   	
	/**
	 * 계약자 계약상세조회 화면에서 업체첨부파일을 가져올때 CONT_NO와 CHNG_NGR을 이용하여 파일 리스트를 조회한다.
	 */

	fileView2 = function(fileGrpNo, contNo, chngNgr) {
		var arrfileGrpNo = fileGrpNo.split(",");
		var idx = 0;
			//업로드 객체의 생성이 완료되었을 때 발생합니다.
			RAONKUPLOAD_CreationComplete = function(uploadID) {
				//idx++;

				if(("uploadView1" == uploadID)){
					idx = 1;
   				var param = {};
   				param.P_FILE_GRP_NO = arrfileGrpNo[(idx - 1)];

   				var actionUrl = "/comm/fileListInqireByFileGrpNo";
   				FwkCmmnUtil.submitAjax(actionUrl, param, function(data) {
   					//loading이미지 제거.
   					FwkCmmnUtil.loading_end();
						if(data.atchFileList.length > 0){
							for (var i = 0; i < data.atchFileList.length; i++) {
								var atchFile = data.atchFileList[i];

							 	var sn       = atchFile.FILE_SN;
							 	var name     = atchFile.SYS_FILE_NM;
							 	var path     = atchFile.FILE_LCTN;
							 	var cpcty    = atchFile.FILE_SZ;
							 	var filePath = path + FwkCmmnUtil.fileSeparator + atchFile.SV_FILE_NM;
							 	var custom   =  atchFile.TSK_VKEY3;

							 	//if ("uploadView"+idx == uploadID) {
							 	//업로드 리스트 컨트롤에 이미 업로드 되어 있는 파일을 셋팅합니다.
							 		RAONKUPLOAD.AddUploadedFile(sn, name, filePath , cpcty, custom, uploadID);
					            //}

							}
						}
   					});
   				}
				else if("uploadView2" == uploadID){

					idx = 2;
   				var param = {};
   				param.P_FILE_GRP_NO = arrfileGrpNo[(idx - 1)];
   				param.P_CONT_NO = contNo;
   				param.P_CHNG_NGR = chngNgr;

   				var actionUrl = "/comm/fileListInqireByFileGrpNoAll";
   				FwkCmmnUtil.submitAjax(actionUrl, param, function(data) {
   					//loading이미지 제거.
   					FwkCmmnUtil.loading_end();
						if(data.atchFileList.length > 0){
							for (var i = 0; i < data.atchFileList.length; i++) {
								var atchFile = data.atchFileList[i];

							 	var sn       = atchFile.FILE_SN;
							 	var name     = atchFile.SYS_FILE_NM;
							 	var path     = atchFile.FILE_LCTN;
							 	var cpcty    = atchFile.FILE_SZ;
							 	var filePath = path + FwkCmmnUtil.fileSeparator + atchFile.SV_FILE_NM;
							 	var custom   =  atchFile.TSK_VKEY3;
							 	//alert("222"+uploadID);
							 	//if ("uploadView"+idx == uploadID) {
							 	//업로드 리스트 컨트롤에 이미 업로드 되어 있는 파일을 셋팅합니다.
							 		RAONKUPLOAD.AddUploadedFile(sn, name, filePath , cpcty, custom, uploadID);
					            //}

							}
						}
   					});
				}
			};
	};

/**  
 * <pre>
 * 1. 개요 : 파일 보기
 * 2. 처리내용 : 
 * </pre>
 * @Function Name : fileView
 * @date : 2019. 01. 28.
 * @author : 은우소프트 맹경열
 * @history : 
 *  =======================================================
 *  변경일             		작성자                     		변경내용  
 *  =======================================================
 *  2019. 01. 28.       은우소프트 맹경열              최초 작성 
 *  =======================================================   
 */
	/*fileView = function(fileGrpNo) {
		var arrfileGrpNo = fileGrpNo.split(",");
		var idx = 0;
			//업로드 객체의 생성이 완료되었을 때 발생합니다.
			RAONKUPLOAD_CreationComplete = function(uploadID) {
				//idx++;
				if(("uploadView1" == uploadID)){
					idx = 1;
   				var param = {};
   				param.P_FILE_GRP_NO = arrfileGrpNo[(idx - 1)];

   				var actionUrl = "/comm/fileListInqireByFileGrpNo";
   				FwkCmmnUtil.submitAjax(actionUrl, param, function(data) {
   					//loading이미지 제거.
   					// FwkCmmnUtil.loading_end(); 
   					FwkCmmnUtil.loadingImageRemove();
						if(data.atchFileList.length > 0){
							for (var i = 0; i < data.atchFileList.length; i++) {
								var atchFile = data.atchFileList[i];

							 	var sn       = atchFile.FILE_SN;
							 	var name     = atchFile.SYS_FILE_NM;
							 	var path     = atchFile.FILE_LCTN;
							 	var cpcty    = atchFile.FILE_SZ;
							 	var filePath = path + FwkCmmnUtil.fileSeparator + atchFile.SV_FILE_NM;
							 	var custom   =  atchFile.TSK_VKEY3;

							 	//if ("uploadView"+idx == uploadID) {
							 	//업로드 리스트 컨트롤에 이미 업로드 되어 있는 파일을 셋팅합니다.
							 		RAONKUPLOAD.AddUploadedFile(sn, name, filePath , cpcty, custom, uploadID);
					            //}

							}
						}
   					});
   				}
				else if("uploadView2" == uploadID){
					idx = 2;
   				var param = {};
   				param.P_FILE_GRP_NO = arrfileGrpNo[(idx - 1)];

   				var actionUrl = "/comm/fileListInqireByFileGrpNo";
   				FwkCmmnUtil.submitAjax(actionUrl, param, function(data) {
   					//loading이미지 제거.
   					FwkCmmnUtil.loading_end();
						if(data.atchFileList.length > 0){
							for (var i = 0; i < data.atchFileList.length; i++) {
								var atchFile = data.atchFileList[i];

							 	var sn       = atchFile.FILE_SN;
							 	var name     = atchFile.SYS_FILE_NM;
							 	var path     = atchFile.FILE_LCTN;
							 	var cpcty    = atchFile.FILE_SZ;
							 	var filePath = path + FwkCmmnUtil.fileSeparator + atchFile.SV_FILE_NM;
							 	var custom   =  atchFile.TSK_VKEY3;
							 	//alert("222"+uploadID);
							 	//if ("uploadView"+idx == uploadID) {
							 	//업로드 리스트 컨트롤에 이미 업로드 되어 있는 파일을 셋팅합니다.
							 		RAONKUPLOAD.AddUploadedFile(sn, name, filePath , cpcty, custom, uploadID);
					            //}

							}
						}
   					});
				}
			};
	};*/

	/**
	 * 계약자 계약상세조회 화면에서 업체첨부파일을 가져올때 CONT_NO와 CHNG_NGR을 이용하여 파일 리스트를 조회한다.
	 */

	fileView2 = function(fileGrpNo, contNo, chngNgr) {
		var arrfileGrpNo = fileGrpNo.split(",");
		var idx = 0;
			//업로드 객체의 생성이 완료되었을 때 발생합니다.
			RAONKUPLOAD_CreationComplete = function(uploadID) {
				//idx++;

				if(("uploadView1" == uploadID)){
					idx = 1;
   				var param = {};
   				param.P_FILE_GRP_NO = arrfileGrpNo[(idx - 1)];

   				var actionUrl = "/comm/fileListInqireByFileGrpNo";
   				FwkCmmnUtil.submitAjax(actionUrl, param, function(data) {
   					//loading이미지 제거.
   					FwkCmmnUtil.loading_end();
						if(data.atchFileList.length > 0){
							for (var i = 0; i < data.atchFileList.length; i++) {
								var atchFile = data.atchFileList[i];

							 	var sn       = atchFile.FILE_SN;
							 	var name     = atchFile.SYS_FILE_NM;
							 	var path     = atchFile.FILE_LCTN;
							 	var cpcty    = atchFile.FILE_SZ;
							 	var filePath = path + FwkCmmnUtil.fileSeparator + atchFile.SV_FILE_NM;
							 	var custom   =  atchFile.TSK_VKEY3;

							 	//if ("uploadView"+idx == uploadID) {
							 	//업로드 리스트 컨트롤에 이미 업로드 되어 있는 파일을 셋팅합니다.
							 		RAONKUPLOAD.AddUploadedFile(sn, name, filePath , cpcty, custom, uploadID);
					            //}

							}
						}
   					});
   				}
				else if("uploadView2" == uploadID){

					idx = 2;
   				var param = {};
   				param.P_FILE_GRP_NO = arrfileGrpNo[(idx - 1)];
   				param.P_CONT_NO = contNo;
   				param.P_CHNG_NGR = chngNgr;

   				var actionUrl = "/comm/fileListInqireByFileGrpNoAll";
   				FwkCmmnUtil.submitAjax(actionUrl, param, function(data) {
   					//loading이미지 제거.
   					FwkCmmnUtil.loading_end();
						if(data.atchFileList.length > 0){
							for (var i = 0; i < data.atchFileList.length; i++) {
								var atchFile = data.atchFileList[i];

							 	var sn       = atchFile.FILE_SN;
							 	var name     = atchFile.SYS_FILE_NM;
							 	var path     = atchFile.FILE_LCTN;
							 	var cpcty    = atchFile.FILE_SZ;
							 	var filePath = path + FwkCmmnUtil.fileSeparator + atchFile.SV_FILE_NM;
							 	var custom   =  atchFile.TSK_VKEY3;
							 	//alert("222"+uploadID);
							 	//if ("uploadView"+idx == uploadID) {
							 	//업로드 리스트 컨트롤에 이미 업로드 되어 있는 파일을 셋팅합니다.
							 		RAONKUPLOAD.AddUploadedFile(sn, name, filePath , cpcty, custom, uploadID);
					            //}

							}
						}
   					});
				}
			};
	};

/**  
 * <pre>
 * 1. 개요 : 파일 보기 수동
 * 2. 처리내용 : 
 * 		callback을 받지 않고 수동으로
 * </pre>
 * @Function Name : fileViewHandle
 * @date : 2019. 02. 21.
 * @author : 은우소프트 맹경열
 * @history : 
 *  =======================================================
 *  변경일             		작성자                     		변경내용  
 *  =======================================================
 *  2019. 02. 21.       은우소프트 맹경열              최초 작성 
 *  =======================================================   
 */
fileViewHandle = function(atchmnflGroupNoArr) {
	var param = {};
	var idx = 0;
	DEXT5UPLOAD_OnCreationComplete = function(uploadID) {
		param.P_ATCHMNFL_GROUP_NO = atchmnflGroupNoArr[idx];
		idx++;
		FwkCmmnUtil.submitAjax(
				"/com/atfi/atchFileListInqireByAtchFileGroupNo"
				, param
				, function(data) {
					if(data.atchFileList.length > 0){
						for (var i = 0; i < data.atchFileList.length; i++) {
							var atchFile = data.atchFileList[i];
							
						 	var sn = atchFile.FILE_SN;
						 	var name = atchFile.SYS_FILE_NM;
						 	var path = atchFile.FILE_LCTN;
						 	var cpcty = atchFile.FILE_SZ;
						 	var filePath = path + '/' + atchFile.SV_FILE_NM;
						 	DEXT5UPLOAD.AddUploadedFile(sn, name, filePath , cpcty, filePath, uploadID);
						 	/*
						 	for (var idx = 0; idx < uploadIDarr.length; idx++) {
						 		alert(atchmnflGroupNoArr[idx]);
						 		param.P_ATCHMNFL_GROUP_NO = atchmnflGroupNoArr[idx];
								FwkCmmnUtil.submitAjax(
										"/com/atfi/atchFileListInqireByAtchFileGroupNo"
										, param
										, function(data) {
											if(data.atchFileList.length > 0){
												for (var idy = 0; idy < data.atchFileList.length; idy++) {
													var atchFile = data.atchFileList[idy];
													
												 	var sn = atchFile.FILE_SN;
												 	var name = atchFile.SV_FILE_NM;
												 	var path = atchFile.FILE_LCTN;
												 	var cpcty = atchFile.FILE_SZ;
												 	var filePath = path + '/' + atchFile.SYS_FILE_NM;
												 	DEXT5UPLOAD.AddUploadedFile(sn, name, filePath , cpcty, filePath, uploadIDarr[idx]);
												 	
												}
											}
									});
							}
							*/			 	
						}
					}
			});		
	};
};
	
/**  
 * <pre>
 * 1. 개요 : 파일 수정
 * 2. 처리내용 : 
 *	  	
 * </pre>
 * @Function Name : fileModify
 * @date : 2019. 01. 28.
 * @author : 은우소프트 맹경열
 * @history : 
 *  =======================================================
 *  변경일             		작성자                     		변경내용  
 *  =======================================================
 *  2019. 01. 28.       은우소프트 맹경열              최초 작성 
 *  =======================================================   
 */
fileModify = function(formId, fileGroupNoId, fileNoIdNew, url, moveFormId, moveUrl, callBack) {
		var fileGrpNo = $("#"+formId+" input[name='"+fileGroupNoId+"']").val();
		// 파일보기 호출
		fileView(fileGrpNo);

		// 업로드 ~전송완료 후 호출되는 함수입니다.
		RAONKUPLOAD_UploadComplete = function(uploadID) {
			var outHtml = "";
			//전송 완료 후 새롭게 업로드 된 파일 리스트 정보를 리턴해 주는 함수
	    	var newFileList = RAONKUPLOAD.GetNewUploadList("json",uploadID);

	    	// 삭제된 파일 리스트 정보를 리턴해 주는 함수
	    	var delFileList = RAONKUPLOAD.GetDeleteList("json",uploadID);
	    	// 업로드 영역에 추가된 전체 파일 개수
	    	var allFileCnt = RAONKUPLOAD.GetTotalFileCount(uploadID);
	    	if(newFileList != null && newFileList != "null"){
	   			for(var i = 0; i < newFileList.originalName.length; i++){
	   				var path = newFileList.uploadPath[i]; //업로드경로 + 파일명

   	   			//path = path.slice(0, path.indexOf("."));
   				//path = path.slice(0, path.lastIndexOf("/"));

	   				var fileLctn = path.substring(0,path.lastIndexOf("/"));
	   					fileLctn = fileLctn.replace("//", '/');

   	   			path = path.slice(0, path.indexOf("."));
   				path = path.slice(0, path.lastIndexOf("/"));

   				outHtml += "<input type='hidden' name='P_SYS_FILE_NM' 	value='" + newFileList.originalName[i] + "' />";	// 시스템파일명
   	            outHtml += "<input type='hidden' name='P_SV_FILE_NM' 	value='" + newFileList.uploadName[i] + "' />";		// 저장파일명
   	            outHtml += "<input type='hidden' name='P_FILE_SZ' 		value='" + newFileList.size[i] + "' />";			// 파일사이즈
   	            //outHtml += "<input type='hidden' name='P_FILE_LCTN' value='" + path + "' />";				//파일경로명
   	            outHtml += "<input type='hidden' name='P_FILE_LCTN' 	value='" + fileLctn + "' />";						// 파일경로명
	   			}
	    	}
	   		if(delFileList != null && delFileList != "null"){
	   			for(var i = 0; i < delFileList.uniqKey.length; i++){
	   				outHtml += "<input type='hidden' name='P_FILE_SN_DEL' value='" + delFileList.uniqKey[i] + "' />";
	   				/*outHtml += "<input type='hidden' name='P_SV_FILE_NM' value='" + delFileList.originalName[i] + "' />";*/
	   			}
	   		}

	   		$("#upload_fileInfo").html(outHtml);
	      	var actionUrl  = "/comm/fileInfoRegist";
	      	var actionUrl2 = "/comm/fileInfoUpt";
	   		var fileGrpNo = $("#"+formId+" input[name='"+fileGroupNoId+"']").val();

			if($("#P_FAIL_YN").val() != "Y"){	// 수정페이지일경우
				if(fileGrpNo == null || fileGrpNo == ''){
	   		    	if(allFileCnt > 0){
	   					FwkCmmnUtil.submitAjax(actionUrl, $("#"+formId).serializeObject(), function(res) {
	   						//파일 그룹번호
	   			    		$("#"+fileGroupNoId).val(res.atchFileGroupNo);
	   			    		$("#"+fileNoIdNew).val("Y");

	   			    		if(moveFormId!=null && moveUrl!=null){
	   			    			comAjaxSubmit(formId, url, moveFormId, moveUrl);
	   			    		}else{
	   			    			FwkCmmnUtil.submitForm(formId, url);
	   			    		}
	   					});
	   		    	}else{
	   		    		if(moveFormId!=null && moveUrl!=null){
	   		    			comAjaxSubmit(formId, url, moveFormId, moveUrl);
	   		    		}else{
	   		    			FwkCmmnUtil.submitForm(formId, url);
	   		    		}
	   		    	}

	   			}else{
					FwkCmmnUtil.submitAjax(actionUrl2, $("#"+formId).serializeObject(), function(res){
						//파일 그룹번호
			    		//$("#"+fileGroupNoId).val(res.atchFileGroupNo);
		    			//콜백 처리로 할경우
		    			if(!FwkCmmnUtil.isNull(callBack)){
		    				eval(callBack);
		    			}else if(moveFormId!=null && moveUrl!=null){
							comAjaxSubmit(formId, url, moveFormId, moveUrl);
			    		}else{
			    			FwkCmmnUtil.submitForm(formId, url);
			    		}
					});
	   			};
			}else{ 	//유찰데이터 일경우

   				FwkCmmnUtil.submitAjax(actionUrl2, $("#"+formId).serializeObject(), function(res) {
					//파일 그룹번호
		    		$("#"+fileGroupNoId).val(res.atchFileGroupNo);
		    		$("#"+fileNoIdNew).val("Y");

		    		if(moveFormId!=null && moveUrl!=null){
		    			comAjaxSubmit(formId, url, moveFormId, moveUrl);
		    		}else{
		    			FwkCmmnUtil.submitForm(formId, url);
		    		}
	    		});
			}

   		// 오류발생시 메시지
   		RAONKUPLOAD_OnError = function(uploadID, code, message) {
   			alert(FwkMssageUtil.getMessage("COM.ERR.003") + "\n\n" + uploadID + ', ' + code + ', ' + message);
   	    };
		};
	};
	
	fileEtcModify = function(formId, fileGroupNoId, fileNoIdNew, url, moveFormId, moveUrl, callBack) {
   		var fileGrpNo = $("#"+formId+" input[name='"+fileGroupNoId+"']").val();
   		// 파일보기 호출
   		fileEtcView(fileGrpNo);

   		// 업로드 ~전송완료 후 호출되는 함수입니다.
   		RAONKUPLOAD_UploadComplete = function(uploadID) {
   			var outHtml = "";
   			//전송 완료 후 새롭게 업로드 된 파일 리스트 정보를 리턴해 주는 함수
   	    	var newFileList = RAONKUPLOAD.GetNewUploadList("json",uploadID);

   	    	// 삭제된 파일 리스트 정보를 리턴해 주는 함수
   	    	var delFileList = RAONKUPLOAD.GetDeleteList("json",uploadID);
   	    	// 업로드 영역에 추가된 전체 파일 개수
   	    	var allFileCnt = RAONKUPLOAD.GetTotalFileCount(uploadID);
   	    	if(newFileList != null && newFileList != "null"){
   	   			for(var i = 0; i < newFileList.originalName.length; i++){
   	   				var path = newFileList.uploadPath[i]; //업로드경로 + 파일명

   	   				var fileLctn = path.substring(0,path.lastIndexOf("/"));
   	   					fileLctn = fileLctn.replace("//", '/');

	   	   			path = path.slice(0, path.indexOf("."));
	   				path = path.slice(0, path.lastIndexOf("/"));

	   				outHtml += "<input type='hidden' name='P_SYS_FILE_NM' 	value='" + newFileList.originalName[i] + "' />";	// 시스템파일명
	   	            outHtml += "<input type='hidden' name='P_SV_FILE_NM' 	value='" + newFileList.uploadName[i] + "' />";		// 저장파일명
	   	            outHtml += "<input type='hidden' name='P_FILE_SZ' 		value='" + newFileList.size[i] + "' />";			// 파일사이즈
	   	            outHtml += "<input type='hidden' name='P_FILE_LCTN' 	value='" + fileLctn + "' />";						// 파일경로명
   	   			}
   	    	}
   	   		if(delFileList != null && delFileList != "null"){
   	   			for(var i = 0; i < delFileList.uniqKey.length; i++){
   	   				outHtml += "<input type='hidden' name='P_FILE_SN_DEL' value='" + delFileList.uniqKey[i] + "' />";
   	   			}
   	   		}

   	   		$("#upload_fileInfo").html(outHtml);
   	      	var actionUrl  = "/comm/fileInfoEtcRegist";
   	      	var actionUrl2 = "/comm/fileInfoEtcUpt";
   	   		var fileGrpNo = $("#"+formId+" input[name='"+fileGroupNoId+"']").val();

			if(fileGrpNo == null || fileGrpNo == ''){
   		    	if(allFileCnt > 0){
   					FwkCmmnUtil.submitAjax(actionUrl, $("#"+formId).serializeObject(), function(res) {
   						//파일 그룹번호
   			    		$("#"+fileGroupNoId).val(res.atchFileGroupNo);
   			    		$("#"+fileNoIdNew).val("Y");

   			    		if(moveFormId!=null && moveUrl!=null){
   			    			comAjaxSubmit(formId, url, moveFormId, moveUrl);
   			    		}else{
   			    			FwkCmmnUtil.submitForm(formId, url);
   			    		}
   					});
   		    	}else{
   		    		if(moveFormId!=null && moveUrl!=null){
   		    			comAjaxSubmit(formId, url, moveFormId, moveUrl);
   		    		}else{
   		    			FwkCmmnUtil.submitForm(formId, url);
   		    		}
   		    	}

   			}else{
				FwkCmmnUtil.submitAjax(actionUrl2, $("#"+formId).serializeObject(), function(res){
					//파일 그룹번호
		    		//$("#"+fileGroupNoId).val(res.atchFileGroupNo);
	    			//콜백 처리로 할경우
	    			if(!FwkCmmnUtil.isNull(callBack)){
	    				eval(callBack);
	    			}else if(moveFormId!=null && moveUrl!=null){
						comAjaxSubmit(formId, url, moveFormId, moveUrl);
		    		}else{
		    			FwkCmmnUtil.submitForm(formId, url);
		    		}
				});
   			};

	   		// 오류발생시 메시지
	   		RAONKUPLOAD_OnError = function(uploadID, code, message) {
	   			alert(FwkMssageUtil.getMessage("COM.ERR.003") + "\n\n" + uploadID + ', ' + code + ', ' + message);
	   	    };
   		};
   	};
/**  
 * <pre>
 * 1. 개요 : 파일 수정
 * 2. 처리내용 : 
 *	  	
 * </pre>
 * @Function Name : noViewFileModify
 * @date : 2019. 01. 28.
 * @author : 은우소프트 맹경열
 * @history : 
 *  =======================================================
 *  변경일             		작성자                     		변경내용  
 *  =======================================================
 *  2019. 01. 28.       은우소프트 맹경열              최초 작성 
 *  =======================================================   
 */
	noViewFileModify = function(formId, fileGroupNoId, fileNoIdNew, url, moveFormId, moveUrl, callBack) {
//		var fileGrpNo = $("#"+formId+" input[name='"+fileGroupNoId+"']").val();
//		// 파일보기 호출
//		fileView(fileGrpNo);

		// 업로드 ~전송완료 후 호출되는 함수입니다.
		RAONKUPLOAD_UploadComplete = function(uploadID) {
			var outHtml = "";
		//	var serverGugun = FwkMssageUtil.getMessage("IEP.SERVER.GUBUN");
	    	var newFileList = RAONKUPLOAD.GetNewUploadList("json",uploadID);
	    	var delFileList = RAONKUPLOAD.GetDeleteList("json",uploadID);
	    	var allFileCnt = RAONKUPLOAD.GetTotalFileCount(uploadID);


	   		if(newFileList != null && newFileList != "null"){
	   			for(var i = 0; i < newFileList.originalName.length; i++){

	   				var path = newFileList.uploadPath[i]; //업로드경로 + 파일명
//	   				if(serverGugun == "WINDOW"){ // window server인 경우
//	   					path = path.slice(path.indexOf("\\")).toLowerCase();
//	   					path = path.slice(0, path.indexOf(".")).toLowerCase();
//	   					path = path.slice(0, path.lastIndexOf("\\")).toLowerCase();
//	   				}else{ // unix server인 경우
//	   					path = path.slice(0, path.indexOf(".")).toLowerCase();
//	   					path = path.slice(0, path.lastIndexOf("/")).toLowerCase();
//	   				}

   	   			path = path.slice(0, path.indexOf("."));
   				path = path.slice(0, path.lastIndexOf("/"));
   				outHtml += "<input type='hidden' name='P_SYS_FILE_NM' value='" + newFileList.originalName[i] + "' />";						// 시스템파일명
   	            outHtml += "<input type='hidden' name='P_SV_FILE_NM' value='" + newFileList.uploadName[i] + "' />";					// 저장파일명
   	            outHtml += "<input type='hidden' name='P_FILE_SZ' value='" + newFileList.size[i] + "' />";							// 파일사이즈
   	            outHtml += "<input type='hidden' name='P_FILE_LCTN' value='" + path + "' />";				//파일경로명

//	   				outHtml += "<input type='hidden' name='ATCH_FILE[][P_STRE_FILE_NM]' value='" + newFileList.uploadName[i] + "' />";					//파일명+확장자
//	   				outHtml += "<input type='hidden' name='ATCH_FILE[][P_ATCHMNFL_NM]' value='" + newFileList.originalName[i]+ "' />";					//실제파일명
//	   				outHtml += "<input type='hidden' name='ATCH_FILE[][P_ATCHMNFL_COURS_NM]' value='" + path + "' />";								//파일명
//	   				outHtml += "<input type='hidden' name='ATCH_FILE[][P_FILE_CPCTY]' value='" + newFileList.size[i]  + "' />";								//사이즈
//	   				outHtml += "<input type='hidden' name='ATCH_FILE[][P_ATCHMNFL_EXTSN_NM]' value='" + newFileList.extension[i] + "' />";			//파일타입
	   			}
			}
	   		if(delFileList != null && delFileList != "null"){
	   			for(var i = 0; i < delFileList.uniqKey.length; i++){
	   				outHtml += "<input type='hidden' name='P_FILE_SN_DEL' value='" + delFileList.uniqKey[i] + "' />";
	   				/*outHtml += "<input type='hidden' name='P_SV_FILE_NM]' value='" + delFileList.originalName[i] + "' />";*/					//파일명+확장자
	   			}
	   		}
	   		$("#upload_fileInfo").html(outHtml);

	      	var actionUrl = "/comm/fileInfoRegist";
	      	var actionUrl2 = "/comm/fileInfoUpt";

	   		var fileGrpNo = $("#"+formId+" input[name='"+fileGroupNoId+"']").val();
			if(fileGrpNo == null || fileGrpNo == ''){
				if(allFileCnt > 0){
					FwkCmmnUtil.submitAjax(actionUrl, $("#"+formId).serializeObject(), function(res) {
						//파일 그룹번호
			    		$("#"+fileGroupNoId).val(res.fileGrpNo);
			    		$("#"+fileNoIdNew).val("Y");

			    		//콜백 처리로 할경우
		    			if(!FwkCmmnUtil.isNull(callBack)){
		    				eval(callBack);
		    			}else if(moveFormId!=null && moveUrl!=null){
			    			comAjaxSubmit(formId, url, moveFormId, moveUrl);
			    		}else{
			    			FwkCmmnUtil.submitForm(formId, url);
			    		}
					});
				}else{

					//콜백 처리로 할경우
	    			if(!FwkCmmnUtil.isNull(callBack)){
	    				eval(callBack);
	    			}else if(moveFormId!=null && moveUrl!=null){
						comAjaxSubmit(formId, url, moveFormId, moveUrl);
		    		}else{
		    			FwkCmmnUtil.submitForm(formId, url);
		    		}
				}
			}else{
					FwkCmmnUtil.submitAjax(actionUrl2, $("#"+formId).serializeObject(), function(res){

					//콜백 처리로 할경우
		    		if(!FwkCmmnUtil.isNull(callBack)){
		    				eval(callBack);
		    		}else if(moveFormId!=null && moveUrl!=null){
		    			comAjaxSubmit(formId, url, moveFormId, moveUrl);
		    		}else{
		    			FwkCmmnUtil.submitForm(formId, url);
		    		}
				});
			}
		};

		// 오류발생시 메시지
		RAONKUPLOAD_OnError = function(uploadID, code, message) {
			alert(FwkMssageUtil.getMessage("COM.ERR.003") + "\n\n" + uploadID + ', ' + code + ', ' + message);
	    };

	};


	
	fileContView = function(fileGrpNo, fileGbn, oproGbn) {

   		var arrfileGrpNo = fileGrpNo.split(",");
   		var arrfileGbn = fileGbn.split(",");
   		
   		var idx = 0;
   			//업로드 객체의 생성이 완료되었을 때 발생합니다.
   			RAONKUPLOAD_CreationComplete = function(uploadID) {
   				//idx++;
   				if(("uploadView1" == uploadID)){
   					idx = 1;
	   				var param = {};
	   				param.P_FILE_GRP_NO = arrfileGrpNo[(idx - 1)];
	   				param.P_FILE_DOC_SECD = arrfileGbn[(idx - 1)];
	   				param.P_USR_SECD = oproGbn;
	   				
	   				var actionUrl = "/comm/fileListInqireByFileGrpNo";
	   				FwkCmmnUtil.submitAjax(actionUrl, param, function(data) {
	   					//loading이미지 제거.
	   					// FwkCmmnUtil.loading_end(); 
	   					FwkCmmnUtil.loadingImageRemove();
							if(data.atchFileList.length > 0){
								for (var i = 0; i < data.atchFileList.length; i++) {
									var atchFile = data.atchFileList[i];

								 	var sn       = atchFile.FILE_SN;
								 	var name     = atchFile.SYS_FILE_NM;
								 	var path     = atchFile.FILE_LCTN;
								 	var cpcty    = atchFile.FILE_SZ;
								 	var filePath = path + FwkCmmnUtil.fileSeparator + atchFile.SV_FILE_NM;
								 	var custom   =  atchFile.TSK_VKEY3;

								 	//if ("uploadView"+idx == uploadID) {
								 	//업로드 리스트 컨트롤에 이미 업로드 되어 있는 파일을 셋팅합니다.
								 		RAONKUPLOAD.AddUploadedFile(sn, name, filePath , cpcty, custom, uploadID);
						            //}

								}
							}
	   					});
	   				}
   				else if("uploadView2" == uploadID){
   					idx = 2;
	   				var param = {};
	   				param.P_FILE_GRP_NO = arrfileGrpNo[(idx - 1)];

	   				var actionUrl = "/comm/fileListInqireByFileGrpNo";
	   				FwkCmmnUtil.submitAjax(actionUrl, param, function(data) {
	   					//loading이미지 제거.
	   					FwkCmmnUtil.loading_end();
							if(data.atchFileList.length > 0){
								for (var i = 0; i < data.atchFileList.length; i++) {
									var atchFile = data.atchFileList[i];

								 	var sn       = atchFile.FILE_SN;
								 	var name     = atchFile.SYS_FILE_NM;
								 	var path     = atchFile.FILE_LCTN;
								 	var cpcty    = atchFile.FILE_SZ;
								 	var filePath = path + FwkCmmnUtil.fileSeparator + atchFile.SV_FILE_NM;
								 	var custom   =  atchFile.TSK_VKEY3;
								 	//alert("222"+uploadID);
								 	//if ("uploadView"+idx == uploadID) {
								 	//업로드 리스트 컨트롤에 이미 업로드 되어 있는 파일을 셋팅합니다.
								 		RAONKUPLOAD.AddUploadedFile(sn, name, filePath , cpcty, custom, uploadID);
						            //}

								}
							}
	   					});
   				}
   			};
   	};
	
   	dynamicEstmTabEvent = function(ESTM_PROCD_SEQ, ESTR_SECD, FRM, ESTM_PROCD_PSCD, gbn){
		
   		if(ESTM_PROCD_PSCD == "" || ESTM_PROCD_PSCD == null) {
			alert("해당 평가는 평가진행중이지 않습니다.");
			if(gbn == "cmpl"){
				FwkCmmnUtil.submitForm(FRM, "/opro/estm/estmCmtmCmplDetail.do");
			}else{
				FwkCmmnUtil.submitForm(FRM, "/opro/estm/estmCmtmProgDetail.do");
			}
			
			return false;
		}
   		   		
   		var SIGN_YN = $("#"+FRM+" input[name='P_SIGN_YN']").val();
   		var SIGN_FILE_GRP_NO = $("#"+FRM+" input[name='P_SIGN_FILE_GRP_NO']").val();			//심사위원서명첨부파일
   		var CMTM_FILE_GRP_NO = $("#"+FRM+" input[name='P_CMTM_FILE_GRP_NO']").val();		//심사위원첨부파일
   		
		if(SIGN_YN != "Y"){
			alert("심사위원 서명파일 전자서명이 되어있지 않습니다.");
			if(gbn == "cmpl"){
				FwkCmmnUtil.submitForm(FRM, "/opro/estm/estmCmtmCmplDetail.do");
			}else{
				FwkCmmnUtil.submitForm(FRM, "/opro/estm/estmCmtmProgDetail.do");
			}
			
			return false;
		}else{
			if(CMTM_FILE_GRP_NO == ""){			//심사위원첨부파일이 등록된지 안된지 확인.
				alert("심사위원 첨부파일이 등록 되어있지 않습니다.");
				if(gbn == "cmpl"){
					FwkCmmnUtil.submitForm(FRM, "/opro/estm/estmCmtmCmplDetail.do");
				}else{
					FwkCmmnUtil.submitForm(FRM, "/opro/estm/estmCmtmProgDetail.do");
				}
				
				return false;
			}
		}
		
		$("#"+FRM+" input[name='P_ESTM_PROCD_SEQ']").val(ESTM_PROCD_SEQ);
		$("#"+FRM+" input[name='P_ESTR_SECD']").val(ESTR_SECD);
		
		/**
		 * 평가자구분별로 분기처리를 해준다.
		 * 평가위원 : A
		 * 평가담당 : B
		 */
		if(ESTR_SECD == "A") {
			if(gbn == "cmpl"){
				FwkCmmnUtil.submitForm(FRM, "/opro/estm/estmCmtmCmplProcdADetail.do");
			}else{
				FwkCmmnUtil.submitForm(FRM, "/opro/estm/estmCmtmProgProcdADetail.do");
			}
			
		}else if(ESTR_SECD =="B"){
			if(gbn == "cmpl"){
				FwkCmmnUtil.submitForm(FRM, "/opro/estm/estmCmtmCmplProcdBDetail.do");
			}else{
				FwkCmmnUtil.submitForm(FRM, "/opro/estm/estmCmtmProgProcdBDetail.do");
			}
		}
	};
	
	//진행 tabevent
	progTabEvent = function(tab_no){
		if(tab_no == "1"){   // 기본정보 
			FwkCmmnUtil.submitForm("detailFrm", "/opro/estm/estmCmtmProgDetail.do");
		}else if(tab_no == "8"){   // 화상회의
			FwkCmmnUtil.submitForm("detailFrm", "/opro/estm/estmCmtmProgVidoMtngDetail.do");
		}
	};
	
	//완료 tabevent
	cmplTabEvent = function(tab_no){
		if(tab_no == "1"){   // 기본정보 
			FwkCmmnUtil.submitForm("detailFrm", "/opro/estm/estmCmtmCmplDetail.do");
		}else if(tab_no == "8"){   // 화상회의
			FwkCmmnUtil.submitForm("detailFrm", "/opro/estm/estmCmtmCmplVidoMtngDetail.do");
		}
	};
	
	/**  
	 * <pre>
	 * 1. 개요 : XML 파싱
	 * 2. 처리내용 : 
	 * </pre>
	 * @Function Name : xmlParseReturn
	 * @date : 2020. 02. 26.
	 * @author : 은우소프트 맹경열
	 * @history : 
	 *  =======================================================
	 *  변경일             		작성자                     		변경내용  
	 *  =======================================================
	 *  2020. 02. 26.       은우소프트 맹경열              최초 작성 
	 *  =======================================================   
	 */
	xmlParseReturn = function(input_file_id, nameArr) {
		var regex = /^([a-zA-Z0-9\s_\\.\-:])+(.xml)$/;
		if(regex.test($('#'+input_file_id).val().toLowerCase())) {
			if(typeof (FileReader) != "undefined") {
				var reader = new FileReader();
				var rtnArr = [];
				
				reader.onload = function(evnt) {
					var xmlDoc = $.parseXML(evnt.target.result);
					$.each(nameArr, function(key, value){
						var $name = value.name;
						var $object = $(xmlDoc).find($name);
						var $val = $object[0].textContent;
						alert($val);
						
						var $map = {$name : $val};
						console.log($val);
						rtnArr.push($map);
					});
					
					return rtnArr;
					
					
				}
				
				reader.readAsText($('#'+input_file_id)[0].files[0]);
			}
		}
	};
	
	
	$(document).keydown(function(e){   
		if(e.keyCode == 8){   
			if(e.target.nodeName == "INPUT" && e.target.readOnly == true){       
            	return false;
            }else if(e.target.nodeName == "TEXTAREA" && e.target.readOnly == true){
            	return false;
            }else if(e.target.nodeName == "INPUT" && e.target.disabled == true){
            	return false;
            }else if(e.target.nodeName == "TEXTAREA" && e.target.disabled == true){
            	return false;
            }else if(e.target.nodeName != "INPUT" && e.target.nodeName != "TEXTAREA"){
            	return false;
            }
        }
	});

	/**
	 * 엑셀다운로드 onload시 자동세팅 되는 로직 구현
	 * 2021-04-28 손연우
	 * @param formId
	 */
	excelDownSetting = function(formId){
		/**
		 * 엑셀관련 ONLOAD시 세팅로직
		 * 해당 목록의 thead id를 excelTh로 세팅
		 */
		var formObj = $("#" + formId);
		$("#excelTh tr").each(function(i){
			
			var trIndex = $(this).attr("id");
			var thIndex = 0;
			$("#excelTh th").each(function(i) {
				
				if($(this).parent().attr("id") == trIndex) {
					if($(this).is(".non") == false){
						var xVal = $(this).parent().attr("id");

						if(trIndex == undefined) { xVal = '0'; }
						
						
						formObj.append('<input type="hidden" name="P_EXCEL_X" value="' + xVal + '" />');
						formObj.append('<input type="hidden" name="P_EXCEL_Y" value="' + thIndex + '" />');
						thIndex++;
						formObj.append('<input type="hidden" name="P_EXCEL_TH" value="' + $(this).text() + '" />');
						
						// colspan이 걸려있는 경우 colspan값 가져오기
						var colspanlenght = $(this).attr("colspan");
						if(colspanlenght == undefined) {
							colspanlenght = '1';
						}
						formObj.append('<input type="hidden" name="P_EXCEL_TH_COL" value="' + colspanlenght + '" />');
			
						// rowspan이 걸려있는 경우 colspan값 가져오기
						var rowspanlenght = $(this).attr("rowspan");
						if(rowspanlenght == undefined) {
							rowspanlenght = '1';
						}
						formObj.append('<input type="hidden" name="P_EXCEL_TH_ROW" value="' + rowspanlenght + '" />');
						//formObj.append('<br>');
					}
				}
				
				
			});
		
		});
		
	};
	
	/**
	 * 엑셀다운로드 자동세팅
	 * @param formId : 엑셀다운로드 데이터를 조회할 폼
	 * @param url : 엑셀다운로드를 실행할 .do주소
	 * @param cnt : 춮력된 건수
	 */
	excelDwd = function(formId, url, cnt){
		if(cnt == "0") {
			alert("엑셀 다운로드할 데이터가 존재하지 않습니다.");
			return false;
		}else {
			FwkCmmnUtil.submitFormExDown(formId, url); 
		}
	}
	
	cmtmSignAssoData_open = function(){
		console.log(">>> cmtmSignAssoData_open <<<");
		
		var f = document.forms["signFrm"];
		
		var options = {};
	    var tempData = [];
	    
	    $("#signedData").val("");
        $("#signCertInfo").empty();
        
        //var ssn = $("#signFrm input[name='P_RSDN_NO']").val();   // 주민등록번호
        var ssn = "";
       
        if(ssn != "") {
            options.ssn = ssn;
        }
        
        console.log("P_FILE_HASH_VAL ==> " + f.P_FILE_HASH_VAL.length );
        
	    if (f.P_FILE_HASH_VAL.length == undefined){
			
	    	if ( f.P_FILE_HASH_VAL.value.length > 0 ){
	    		tempData[0] = f.P_FILE_HASH_VAL.value;
			} else {
				alert("정상적인 평가가 아닙니다.");
				return;
			}
	    }else{
	    	for ( i = 0 ; i < f.P_FILE_HASH_VAL.length ; i++ ) {
	    		if ( f.P_FILE_HASH_VAL[i].value.length > 0 ){
	    			tempData[i] = f.P_FILE_HASH_VAL[i].value;
	    		} else {
	    			alert("정상적인 평가가 아닙니다.");
	    			return;
	    		}	
	    		
	    	}
	    }
	    
	    //var loginCertPolicy = $("#P_CERT_POLICY").val();
	    //var certPolicy;
	    //alert("CERT_POLICY ==> " + $("#P_CERT_POLICY").val());
	    
	    //console.log("loginCertPolicy ==> " + loginCertPolicy);
	    
	    /*if(loginCertPolicy == "1 2 410 200005 1 1 4"){
	    	certPolicy = "1 2 410 200005 1 1 4:은행 개인"; 
	    }else if(loginCertPolicy == "1 2 410 200004 5 2 1 2"){
	    	certPolicy = "1 2 410 200004 5 2 1 2:1등급(개인)";
	    }else if(loginCertPolicy == "1 2 410 200004 5 1 1 5"){
	    	certPolicy = "1 2 410 200004 5 1 1 5:개인범용";
	    }else if(loginCertPolicy == "1 2 410 200005 1 1 1"){
	    	certPolicy = "1 2 410 200005 1 1 1:개인범용";
	    }else if(loginCertPolicy == "1 2 410 200004 5 4 1 1"){
	    	certPolicy = "1 2 410 200004 5 4 1 1:개인범용";
	    }else if(loginCertPolicy == "1 2 410 200012 1 1 1"){
	    	certPolicy = "1 2 410 200012 1 1 1:개인범용";
	    }else if(loginCertPolicy == "1 2 410 200004 5 5 1 1"){
	    	certPolicy = "1 2 410 200004 5 5 1 1:범용개인";
	    }
	    
	    nxTSPKIConfig.options.initPolicies = certPolicy;*/
	    
	    nxTSPKIConfig.options.initPolicies = "1 2 410 200005 1 1 4:은행 개인|1 2 410 200004 5 2 1 2:1등급(개인)|1 2 410 200004 5 1 1 5:개인범용|1 2 410 200005 1 1 1:개인범용|1 2 410 200004 5 4 1 1:개인범용|1 2 410 200012 1 1 1:개인범용|1 2 410 200004 5 5 1 1:범용개인|";
	    nxTSPKIConfig.options.subjectDN = $("#signFrm input[name='P_LOGIN_DN']").val();
	    
	    
	    nxTSPKI.signData(tempData, options, cmtmSignAssoData_open_complete_callback);
	}
	
	
	
	function cmtmSignAssoData_open_complete_callback(res) {
		var f = document.forms["signFrm"];
		
		if (res.code == 0) {
			//console.log("res ==> " + JSON.stringify(res));
			//alert("signedData ==> " + res.data.signedData);
			//alert("res.data.certInfo ==> " + res.data.certInfo);
			//alert("res.data.certInfo.subjectDN ==> " + res.data[0].certInfo.subjectDN);
			
			console.log("P_FILE_HASH_VAL ==> " + f.P_FILE_HASH_VAL.length );
			
	    	if (f.P_FILE_HASH_VAL.length == undefined){
	    		f.P_ELEC_SIGN_VAL.value  = res.data[0].signedData;
	        }else{
	        	for ( i = 0 ; i < f.P_FILE_HASH_VAL.length ; i++ ) {
	        		
	        		f.P_CERT_INHR_VAL[i].value = res.data[0].certInfo.subjectDN;
	        		f.P_ELEC_SIGN_VAL[i].value  = res.data[i].signedData;
	        		
	        		//alert("res.data[i].signedData ==> " + res.data[i].signedData);
	        	}
	        }
	    	
	    	pageObj.cmtmSign();
	    }
	    else {
	        alert("error code = " + res.code + ", message = " + res.errorMessage);
	    }
	}
	
	
	cmtmSignBrowserData_open = function(){
		console.log(">>> cmtmSignBrowserData_open <<<");
		
		var f = document.forms["signFrm"];
		
		var options = {};
		var tempData = [];

        $("#signedData").val("");
        $("#signCertInfo").empty();
    
        //var ssn = $("#signFrm input[name='P_RSDN_NO']").val();   // 주민등록번호
        var ssn = "";
        
        if(ssn != "") {
            options.ssn = ssn;
        }

        console.log("P_FILE_HASH_VAL ==> " + f.P_FILE_HASH_VAL.length );
        if (f.P_FILE_HASH_VAL.length == undefined){
			
	    	if ( f.P_FILE_HASH_VAL.value.length > 0 ){
	    		tempData[0] = f.P_FILE_HASH_VAL.value;
			} else {
				alert("정상적인 평가가 아닙니다.");
				return;
			}
	    }else{
	    	for ( i = 0 ; i < f.P_FILE_HASH_VAL.length ; i++ ) {
	    		if ( f.P_FILE_HASH_VAL[i].value.length > 0 ){
	    			tempData[i] = f.P_FILE_HASH_VAL[i].value;
	    		} else {
	    			alert("정상적인 평가가 아닙니다.");
	    			return;
	    		}	
	    		
	    	}
	    }
        
        var encodingType = "UTF-8";
        options.encodingType = encodingType;
        tsHTML5PKI.signData(tempData, options, cmtmSignBrowserData_open_complete_callback);
	}
	
	
	function cmtmSignBrowserData_open_complete_callback(res) {
		var f = document.forms["signFrm"];
		
		if (res.code == 0) {
//            $("#signedData").val(res.data.signedData);
//            $("#signCertInfo").append(objectToTable("",res.data.certInfo));
			console.log("P_FILE_HASH_VAL ==> " + f.P_FILE_HASH_VAL.length );
			if (f.P_FILE_HASH_VAL.length == undefined){
	    		f.P_ELEC_SIGN_VAL.value  = res.data[0].signedData;
	        }else{
	        	for ( i = 0 ; i < f.P_FILE_HASH_VAL.length ; i++ ) {
	        		
	        		f.P_CERT_INHR_VAL[i].value = res.data[0].certInfo.subjectDN;
	        		f.P_ELEC_SIGN_VAL[i].value  = res.data[i].signedData;
	        		
	        		//alert("res.data[i].signedData ==> " + res.data[i].signedData);
	        	}
	        }
			
			pageObj.cmtmSign();
        }
        else {
            alert("error code = " + res.code + ", message = " + res.errorMessage);
        }
		
	}
	
	/**  
	 * <pre>
	 * 1. 개요 : DatePicker 세팅
	 * 2. 처리내용 : 
	 * 		ready로 쓰던 datepicker를 별도 Function으로 분리
	 *     동적 추가 달력을 위해 사용
	 * </pre>
	 * @Function Name : datePickerFunc
	 * @date : 2018. 08. 17.
	 * @author : 은우소프트 맹경열
	 * @history : 
	 *  =======================================================
	 *  변경일             		작성자                     		변경내용  
	 *  =======================================================
	 *  2018. 08. 17.       은우소프트 맹경열              최초 작성 
	 *  =======================================================   
	 */	
	datePickerFunc = function() {
		$( "[date]" ).datepicker({
		    //datepicker trigger 지정
			changeMonth: false
			,changeYear: false ,
			showOn: "button" //엘리먼트와 이미지 동시 사용(both,button)
		    //한글 지정
		    ,regional : "ko-KR"
	        ,buttonImageOnly: true, //이미지표시
			buttonText: '달력선택', //버튼 텍스트 표시
			buttonImage: '../../statics/images/ipro/sbdc/icon_calen.png' //이미지주소
		});
	
		$("[date]").on("keydown", function(){
			$(this).val($(this).val().replace(/[^0-9-]/gi,""));
		});
		$("[date]").on("blur", function(){
			if($(this).val() != ""){
				if(!FwkDateUtil.isDate(hyphenDelete($(this).val()), "yyyyMMdd")){
					alert(FwkMssageUtil.getMessage("EW.INF.023"));
					$(this).val("");
					$(this).focus();
					return false;
				}
				var d = hyphenDelete($(this).val());
				$(this).val(d.substring(0, 4)+'-'+d.substring(4, 6)+'-'+d.substring(6));
			}
		});
		
		$("[date]").on("keyup", function(){
			var value = $(this).val().trim();
			var size = value.length;
			
			if(size > 10){
				$(this).val(value.substring(0,10));
				return false;
			}
			for(var i=0; i < size; i++){
				if(i == 4){
					if(size == 5){
						if(value.charAt(i) != "-"){
							$(this).val(value.substring(0, 4)+"-"+value.substring(4, 5));
						}
					}
				}else if(i==7){
					if(size == 8){
						if(value.indexOf("-") > 0){
							if(value.charAt(i) != "-"){
								$(this).val(value.substring(0, 7)+"-"+value.substring(7, 8));
							}
						}else{
							$(this).val(value.substring(0, 4)+"-"+value.substring(4, 6)+"-"+value.substring(6,8));
						}
					}
				}else if(i == 9){
					if(size == 10){
						if(value.indexOf("-") < 0){
							$(this).val(value.substring(0, 4)+"-"+value.substring(5, 7)+"-"+value.substring(8));
						}
					}
				}
			}
		});
		$("[date2]").on("blur", function(){
			if($(this).val() != ""){
				if(!FwkDateUtil.isDate(hyphenDelete($(this).val()), "yyyyMMdd")){
					alert(FwkMssageUtil.getMessage("EW.INF.023"));
					$(this).val("");
					$(this).focus();
					return false;
				}
				var d = hyphenDelete($(this).val());
				$(this).val(d.substring(0, 4)+'-'+d.substring(4, 6)+'-'+d.substring(6));
			}
		});
		$("[date2]").on("keydown", function(){
			$(this).val($(this).val().replace(/[^0-9-]/gi,""));
		});	
		$("[date2]").on("keyup", function(){
			var value = $(this).val().trim();
			var size = value.length;
			
			if(size > 10){
				$(this).val(value.substring(0,10));
				return false;
			}
			for(var i=0; i < size; i++){
				if(i == 4){
					if(size == 5){
						if(value.charAt(i) != "-"){
							$(this).val(value.substring(0, 4)+"-"+value.substring(4, 5));
						}
					}
				}else if(i==7){
					if(size == 8){
						if(value.indexOf("-") > 0){
							if(value.charAt(i) != "-"){
								$(this).val(value.substring(0, 7)+"-"+value.substring(7, 8));
							}
						}else{
							$(this).val(value.substring(0, 4)+"-"+value.substring(4, 6)+"-"+value.substring(6,8));
						}
					}
				}else if(i == 9){
					if(size == 10){
						if(value.indexOf("-") < 0){
							$(this).val(value.substring(0, 4)+"-"+value.substring(5, 7)+"-"+value.substring(8));
						}
					}
				}
			}
		});
		
		/**
		 * 평가대상 상세 팝업
		 * objDetailPopup('popupFrm', 'CMTM', '${data.ESTM_CMTM_NO}');
		 */
		objDetailPopup = function(frm, gbn, OBJ){
			var formObj = $("#" + frm);
			
			if(gbn == "CMTM") {
				$("#" + frm + " input[name='P_ESTM_CMTM_NO']").remove();
				formObj.append('<input type="hidden" name="P_ESTM_CMTM_NO" value="' + OBJ + '" />');
				
				$("#popupFrm").one("submit", function() {
					window.open("", "objDetailPopup", "width=1024px,height=760px,toolbar=no,status=no,scrollbars=yes,resizeable=no,menubar=no,left=500,top=100");
					this.action = FwkCmmnUtil['contextPath']+'/comm/popup/estmCmtmMngPoolDetail.do';
			        this.method = 'POST';
			        this.target = 'objDetailPopup';
			    }).trigger("submit");
				
			}else if(gbn == "A"){	//업체
			}else if(gbn == "B"){	//상품
			}else if(gbn == "C"){	//사람
				$("#" + frm + " input[name='P_ESTM_OBJ_PE_NO']").remove();
			formObj.append('<input type="hidden" name="P_ESTM_OBJ_PE_NO" value="' + OBJ + '" />');
			
			$("#popupFrm").one("submit", function() {
				window.open("", "objDetailPopup", "width=1024px,height=360px,toolbar=no,status=no,scrollbars=yes,resizeable=no,menubar=no,left=500,top=100");
				this.action = FwkCmmnUtil['contextPath']+'/opro/comm/popup/estmCmtmMngCrtrDetail.do';
				this.method = 'POST';
				this.target = 'objDetailPopup';
			}).trigger("submit");
			}
			
			
		}
		
		
		// 평가대상 탭 - 평가대상정보 아임스타즈 연계 상세 팝업 : 기업정보
		estmObjImstarsMainView = function(frmId, itemNo, bizrno, estmObjSeq){
			var formObj = $("#" + frmId);
			
			var estmInfoCntcNo = $("#"+frmId+" input[name='P_ESTM_INFO_CNTC_NO']").val();   // 평가정보연계번호
			console.log("estmInfoCntcNo ==>" + estmInfoCntcNo);
			
			if(estmInfoCntcNo == null || estmInfoCntcNo == ""){
				estmObjSeq = "";	
			}
			
			if(bizrno != null){
				
				console.log("평가대상정보 아임스타즈 연계 - 기업정보 (평가대상순번) ==> " + estmObjSeq);
				
				$("#popupFrm input[name='P_GOODSNO']").val(itemNo);
				$("#popupFrm input[name='P_BSNM_REGIST_NO']").val(bizrno);
				$("#popupFrm input[name='P_REQST_NO']").val(estmObjSeq);
				
				$("#popupFrm").one("submit", function() {
					window.open("", "viewPopup", "width=1050px,height=900px,toolbar=no,status=no,scrollbars=yes,resizeable=no,menubar=no,left=400,top=50");
					this.action = FwkCmmnUtil['contextPath']+'/opro/comm/popup/estmObjImstarsMainView.do';
			        this.method = 'POST';
			        this.target = 'viewPopup';
			    }).trigger("submit");
			}
			
		}
		
		// 평가대상 탭 - 평가대상정보 아임스타즈 연계 상세 팝업 : 상세내용
		estmObjImstarsDetailView = function(frmId, itemNo, bizrno, estmObjSeq){
			var formObj = $("#" + frmId);
			
			var estmInfoCntcNo = $("#"+frmId+" input[name='P_ESTM_INFO_CNTC_NO']").val();   // 평가정보연계번호
			console.log("estmInfoCntcNo ==>" + estmInfoCntcNo);
			
			if(estmInfoCntcNo == null || estmInfoCntcNo == ""){
				estmObjSeq = "";	
			}
			
			if(itemNo != null){
				
				console.log("평가대상정보 아임스타즈 연계 - 상품정보 (평가대상순번) ==> " + estmObjSeq);
				
				$("#popupFrm input[name='P_GOODSNO']").val(itemNo);
				$("#popupFrm input[name='P_BSNM_REGIST_NO']").val(bizrno);
				$("#popupFrm input[name='P_REQST_NO']").val(estmObjSeq);
				
				$("#popupFrm").one("submit", function() {
					window.open("", "viewPopup", "width=1050px,height=900px,toolbar=no,status=no,scrollbars=yes,resizeable=no,menubar=no,left=400,top=50");
					this.action = FwkCmmnUtil['contextPath']+'/opro/comm/popup/estmObjImstarsDetailView.do';
			        this.method = 'POST';
			        this.target = 'viewPopup';
			    }).trigger("submit");
			}
		}
};
