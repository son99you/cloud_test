(function() {

	/**
	 * Default Constructor
	 */
	pageObj = {};

	var defaultFrm = "searchFrm";
	
	/**
	 * 엑셀다운로드 자동세팅
	 * @param formId : 엑셀다운로드 데이터를 조회할 폼
	 * @param url : 엑셀다운로드를 실행할 .do주소
	 * @param cnt : 춮력된 건수
	 */
	sttsExcelDwd = function(formId, url, cnt){
		if(cnt == "0") {
			alert("엑셀 다운로드할 데이터가 존재하지 않습니다.");
			return false;
		}else {
			
			var checkFlag = "Y";
			
			// 조회컬럼추가
			// 체크된 갯수 체크해서 없으면 alert
			var selectColCnt = 0;
			$("input[name='selectCol']").each(function(i) {
				if($(this).is(":checked")) {
					selectColCnt++;
				}
			});
			
			console.log("selectColCnt :: " + selectColCnt);
			if(selectColCnt < 1) {
				alert("조회컬럼을 체크해주세요.");
				checkFlag = "N";
			}
			
			$("input[name='inqText']").each(function(i){
				var inqTextVal = $(this).val();   // 조건입력값
				
				var inqColNum = $(this).parent().parent().find("input[name='inqColNm']").val();   // 조건컬럼명
				var inqCond = $(this).parent().parent().find("select[name='inqCond']").val();   // 조건구분
				
				if(inqCond == "G") {
					var fromDate = $(this).parent().parent().find("input[name='fromDate']").val();
					var toDate = $(this).parent().parent().find("input[name='toDate']").val();
					var re=/[^0-9]/gi;
					
					var inqText = fromDate.replace(re,"")+toDate.replace(re,"");
					$(this).val(inqText);
					
				}else{
					var inqText = $(this).val();
				}
				
				if(inqCond == "G" && (inqText.length > 0 && inqText.length < 16)) {
					var comments = $(this).parent().parent().find("input[name='comments']").val();
					alert(comments + "의 년월일을 모두 입력해주세요.");
					checkFlag = "N";
					return;
				}
				
				
				if(inqColNum != "" && inqCond != "" && inqText !=""){
					$(this).parent().parent().find("input[name='inqCol']").val(inqColNum+"|"+inqCond+"|"+inqText);
				}
			});
			
			if(checkFlag == "N") {
			}else {
				//FwkCmmnUtil.submitForm(defaultFrm, "/stts/estmSttsList.do");
				FwkCmmnUtil.submitFormExDown(formId, url);
				
			}
			
			
		}
	}
	

	// 목록
	pageObj.sttsList = function() {
		
		var checkFlag = "Y";
		
		// 조회컬럼추가
		// 체크된 갯수 체크해서 없으면 alert
		var selectColCnt = 0;
		$("input[name='selectCol']").each(function(i) {
			if($(this).is(":checked")) {
				selectColCnt++;
			}
		});
		
		console.log("selectColCnt :: " + selectColCnt);
		if(selectColCnt < 1) {
			alert("조회컬럼을 체크해주세요.");
			checkFlag = "N";
		}
		
		$("input[name='inqText']").each(function(i){
			var inqTextVal = $(this).val();   // 조건입력값
			
			var inqColNum = $(this).parent().parent().find("input[name='inqColNm']").val();   // 조건컬럼명
			var inqCond = $(this).parent().parent().find("select[name='inqCond']").val();   // 조건구분
			
			if(inqCond == "G") {
				var fromDate = $(this).parent().parent().find("input[name='fromDate']").val();
				var toDate = $(this).parent().parent().find("input[name='toDate']").val();
				var re=/[^0-9]/gi;
				
				var inqText = fromDate.replace(re,"")+toDate.replace(re,"");
				$(this).val(inqText);
				
			}else{
				var inqText = $(this).val();
			}
			
			if(inqCond == "G" && (inqText.length > 0 && inqText.length < 16)) {
				var comments = $(this).parent().parent().find("input[name='comments']").val();
				alert(comments + "의 년월일을 모두 입력해주세요.");
				checkFlag = "N";
				return;
			}
			
			
			if(inqColNum != "" && inqCond != "" && inqText !=""){
				$(this).parent().parent().find("input[name='inqCol']").val(inqColNum+"|"+inqCond+"|"+inqText);
			}
		});
		
		if(checkFlag == "N") {
		}else {
			FwkCmmnUtil.submitForm(defaultFrm, "/stts/estmSttsList.do");
			
		}
	};
	
	pageObj.sttsObj = function(obj) {
		$("#ajaxFrm input[name='P_STTS_OBJ_SECD_S']").val(obj.val());
		$("#ajaxFrm input[name='P_ACTION']").val("CHANGE");
		FwkCmmnUtil.submitForm("ajaxFrm", "/stts/estmSttsList.do");	
	};
	
	/**
	 * 조회컬럼 전체 선택
	 */
	pageObj.selectColAllCbxEvent = function(obj) {
		 if($(obj).is(":checked")){
			 $("input[name='selectCol']").prop({'checked':true});
			 $("input[name='selectColAllCheckAt']").val("Y");
			 
		 } else{
			 $("input[name='selectCol']").prop({'checked':false});
			 $("input[name='selectColAllCheckAt']").val("N");
		 }
	};
	
	
	pageObj.setEventHandler = function() {
		
		// 통계대상 선택 이벤트
		$("select[name='P_STTS_OBJ_SECD_S']").on("change", function() {
			pageObj.sttsObj($(this));
			return false;
		});
		
		// 조회컬럼 전체 선택
		$("#selectColAll").on("click", function() {
			pageObj.selectColAllCbxEvent(this);
		});
		
		// 조회버튼
		$("#searchBtn").on("click", function() {
			pageObj.sttsList();
			return false;
		});
		
	};

	/**
	 * window load
	 *
	 */
	$(function() {		
		pageObj.setEventHandler();
		
		// 엑셀다운로드 공통
		excelDownSetting("searchFrm");
		
		
		if($("input[name='selectColAllCheckAt']").val() == "Y") {
			$("#selectColAll").prop({'checked':true});
		}
	});
	
})();