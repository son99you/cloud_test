(function() {

	/**
	 * Default Constructor
	 */
	pageObj = {};
	
	/**
	 * 테스트 세팅
	 *  ESTM_CMTM_SPHE_ARR[0][0] : 평가위원번호
	 *  ESTM_CMTM_SPHE_ARR[0][1] : 평가위원명
	 *  ESTM_CMTM_SPHE_ARR[0][2] : 전화번호
	 *  ESTM_CMTM_SPHE_ARR[0][3] : 이메일
	 *  ESTM_CMTM_SPHE_ARR[0][4] : SELECT_AT
	 *  ESTM_CMTM_SPHE_ARR[0][5] : 내/외부구분
	 */
	var ESTM_CMTM_SPHE_ARR = new Array(1);
	var searchCmtmNm = ""; //평가위원조회조건
	var searchLlfSecd = ""; //대분류조회조건
	var searchCntnSecd = ""; //내역조회조건
	var searchMlfSecd = ""; //중분류조회조건
	var searchSlfSecd = ""; //소분류조회조건
	
	
	pageObj.estmCmtmSpheMpgList = function() {
		FwkCmmnUtil.submitForm("listFrm", "/estm/estmCmtmSpheMpgList.do");
	};
	
	pageObj.estmCmtmSpheMpgReg = function() {
		FwkCmmnUtil.submitForm("regFrm", "/estm/estmCmtmSpheMpgRegist.do");
	};
	
	
	/**
	 * 자동row추가
	 * @param btnGbn
	 * @param itemArrLength
	 */
	addItemHtml = function(btnGbn, itemArrLength, searchYn) {

		var strHtmlOne = "";
		var strHtmlTwo = "";

		for(var i = 0; i< itemArrLength; i++) {
			
			var dataCmtmNm = ESTM_CMTM_SPHE_ARR[i][1];
			var dataLlfSecd = ESTM_CMTM_SPHE_ARR[i][6];
			var dataCntnSecd = ESTM_CMTM_SPHE_ARR[i][8];
			var dataMlfSecd = ESTM_CMTM_SPHE_ARR[i][10];
			var dataSlfSecd = ESTM_CMTM_SPHE_ARR[i][12];
			
			if(searchCmtmNm.length == 0) { dataCmtmNm = ""; }
			if(searchLlfSecd.length == 0) { dataLlfSecd = ""; }
			if(searchCntnSecd.length == 0) { dataCntnSecd = ""; }
			if(searchMlfSecd.length == 0) { dataMlfSecd = ""; }	
			if(searchSlfSecd.length == 0) { dataSlfSecd = ""; }
			
			if(searchYn == "Y") {
				if(ESTM_CMTM_SPHE_ARR[i][4] == "N" ) {
					if(dataCmtmNm == searchCmtmNm
						&& dataLlfSecd == searchLlfSecd
						&& dataCntnSecd == searchCntnSecd
						&& dataMlfSecd == searchMlfSecd
						&& dataSlfSecd == searchSlfSecd
					) {
						//console.log("N");
						strHtmlOne  += "<tr class=\"txt-center\">";
						strHtmlOne  += "<td class=\"txt-center\">";
						strHtmlOne  += "<label class=\"component-checkbox\">";
						strHtmlOne  += "<input type=\"checkbox\" name=\"chck\">";
						strHtmlOne  += "<i></i>";
						strHtmlOne  += "</label>";
						strHtmlOne  += "<input type=\"hidden\" name=\"P_ESTM_CMTM_NO\" value=\""+ ESTM_CMTM_SPHE_ARR[i][0] +"\"\>";
						strHtmlOne  += "<input type=\"hidden\" name=\"P_SELECT_AT\" value=\""+ ESTM_CMTM_SPHE_ARR[i][4] +"\"\>";
						strHtmlOne  += "</td>";
						strHtmlOne  += "<td>" + ESTM_CMTM_SPHE_ARR[i][1] + "</td>";
						strHtmlOne  += "<td>" + ESTM_CMTM_SPHE_ARR[i][5] + "</td>";
						strHtmlOne  += "<td>" + ESTM_CMTM_SPHE_ARR[i][2] + "</td>";
						strHtmlOne  += "<td>" + ESTM_CMTM_SPHE_ARR[i][3] + "</td>";
						strHtmlOne  += "<td class=\"txt-center\">" + ESTM_CMTM_SPHE_ARR[i][7] + "</td>";
						strHtmlOne  += "<td class=\"txt-center\">" + ESTM_CMTM_SPHE_ARR[i][9] + "</td>";
						strHtmlOne  += "<td class=\"txt-center\">" + ESTM_CMTM_SPHE_ARR[i][11] + "</td>";
						strHtmlOne  += "<td class=\"txt-center\">" + ESTM_CMTM_SPHE_ARR[i][13] + "</td>";
						strHtmlOne  += "</tr>";
					}
				}else  if(ESTM_CMTM_SPHE_ARR[i][4] == "Y"){
					//console.log("Y");
					strHtmlTwo  += "<tr class=\"txt-center\">";
					strHtmlTwo  += "<td class=\"txt-center\">";
					strHtmlTwo  += "<label class=\"component-checkbox\">";
					strHtmlTwo  += "<input type=\"checkbox\" name=\"chck\">";
					strHtmlTwo  += "<i></i>";
					strHtmlTwo  += "</label>";
					strHtmlTwo  += "<input type=\"hidden\" name=\"P_ESTM_CMTM_NO\" value=\""+ ESTM_CMTM_SPHE_ARR[i][0] +"\"\>";
					strHtmlTwo  += "<input type=\"hidden\" name=\"P_SELECT_AT\" value=\""+ ESTM_CMTM_SPHE_ARR[i][4] +"\"\>";
					strHtmlTwo  += "</td>";
					strHtmlTwo  += "<td>" + ESTM_CMTM_SPHE_ARR[i][1] + "</td>";
					strHtmlTwo  += "<td>" + ESTM_CMTM_SPHE_ARR[i][5] + "</td>";
					strHtmlTwo  += "<td>" + ESTM_CMTM_SPHE_ARR[i][2] + "</td>";
					strHtmlTwo  += "<td>" + ESTM_CMTM_SPHE_ARR[i][3] + "</td>";
					strHtmlTwo  += "<td class=\"txt-center\">" + ESTM_CMTM_SPHE_ARR[i][7] + "</td>";
					strHtmlTwo  += "<td class=\"txt-center\">" + ESTM_CMTM_SPHE_ARR[i][9] + "</td>";
					strHtmlTwo  += "<td class=\"txt-center\">" + ESTM_CMTM_SPHE_ARR[i][11] + "</td>";
					strHtmlTwo  += "<td class=\"txt-center\">" + ESTM_CMTM_SPHE_ARR[i][13] + "</td>";
					strHtmlTwo  += "</tr>";
				}
			}else {
				if(ESTM_CMTM_SPHE_ARR[i][4] == "N" ) {
					//console.log("N");
					strHtmlOne  += "<tr class=\"txt-center\">";
					strHtmlOne  += "<td class=\"txt-center\">";
					strHtmlOne  += "<label class=\"component-checkbox\">";
					strHtmlOne  += "<input type=\"checkbox\" name=\"chck\">";
					strHtmlOne  += "<i></i>";
					strHtmlOne  += "</label>";
					strHtmlOne  += "<input type=\"hidden\" name=\"P_ESTM_CMTM_NO\" value=\""+ ESTM_CMTM_SPHE_ARR[i][0] +"\"\>";
					strHtmlOne  += "<input type=\"hidden\" name=\"P_SELECT_AT\" value=\""+ ESTM_CMTM_SPHE_ARR[i][4] +"\"\>";
					strHtmlOne  += "</td>";
					strHtmlOne  += "<td>" + ESTM_CMTM_SPHE_ARR[i][1] + "</td>";
					strHtmlOne  += "<td>" + ESTM_CMTM_SPHE_ARR[i][5] + "</td>";
					strHtmlOne  += "<td>" + ESTM_CMTM_SPHE_ARR[i][2] + "</td>";
					strHtmlOne  += "<td>" + ESTM_CMTM_SPHE_ARR[i][3] + "</td>";
					strHtmlOne  += "<td class=\"txt-center\">" + ESTM_CMTM_SPHE_ARR[i][7] + "</td>";
					strHtmlOne  += "<td class=\"txt-center\">" + ESTM_CMTM_SPHE_ARR[i][9] + "</td>";
					strHtmlOne  += "<td class=\"txt-center\">" + ESTM_CMTM_SPHE_ARR[i][11] + "</td>";
					strHtmlOne  += "<td class=\"txt-center\">" + ESTM_CMTM_SPHE_ARR[i][13] + "</td>";
					strHtmlOne  += "</tr>";
				}else  if(ESTM_CMTM_SPHE_ARR[i][4] == "Y"){
					//console.log("Y");
					strHtmlTwo  += "<tr class=\"txt-center\">";
					strHtmlTwo  += "<td class=\"txt-center\">";
					strHtmlTwo  += "<label class=\"component-checkbox\">";
					strHtmlTwo  += "<input type=\"checkbox\" name=\"chck\">";
					strHtmlTwo  += "<i></i>";
					strHtmlTwo  += "</label>";
					strHtmlTwo  += "<input type=\"hidden\" name=\"P_ESTM_CMTM_NO\" value=\""+ ESTM_CMTM_SPHE_ARR[i][0] +"\"\>";
					strHtmlTwo  += "<input type=\"hidden\" name=\"P_SELECT_AT\" value=\""+ ESTM_CMTM_SPHE_ARR[i][4] +"\"\>";
					strHtmlTwo  += "</td>";
					strHtmlTwo  += "<td>" + ESTM_CMTM_SPHE_ARR[i][1] + "</td>";
					strHtmlTwo  += "<td>" + ESTM_CMTM_SPHE_ARR[i][5] + "</td>";
					strHtmlTwo  += "<td>" + ESTM_CMTM_SPHE_ARR[i][2] + "</td>";
					strHtmlTwo  += "<td>" + ESTM_CMTM_SPHE_ARR[i][3] + "</td>";
					strHtmlTwo  += "<td class=\"txt-center\">" + ESTM_CMTM_SPHE_ARR[i][7] + "</td>";
					strHtmlTwo  += "<td class=\"txt-center\">" + ESTM_CMTM_SPHE_ARR[i][9] + "</td>";
					strHtmlTwo  += "<td class=\"txt-center\">" + ESTM_CMTM_SPHE_ARR[i][11] + "</td>";
					strHtmlTwo  += "<td class=\"txt-center\">" + ESTM_CMTM_SPHE_ARR[i][13] + "</td>";
					strHtmlTwo  += "</tr>";
				}
			}
			
		}
		
		
		$("#copyAreaOne").html(strHtmlOne);
		$("#copyAreaTwo").html(strHtmlTwo);
		
	}
	
	
	// 체크된 데이터 선정위원표로 이동
	moveDataRight = function() {
		$("#copyAreaOne input[name='chck']").each(function(inx){
			if(this.checked) {
//				console.log($(this).parent().parent().find($("input[name='D_ESTM_CMTM_NO']")).val());
//				console.log($(this).parent().parent().find($("input[name='D_SELECT_AT']")).val());
				var D_ESTM_CMTM_NO  = $(this).parent().parent().find($("input[name='P_ESTM_CMTM_NO']")).val();
				for(var i = 0; i< ESTM_CMTM_SPHE_ARR.length; i++) {
					if(ESTM_CMTM_SPHE_ARR[i][0] == D_ESTM_CMTM_NO) {
						ESTM_CMTM_SPHE_ARR[i][4] = "Y";
					}
				}
			}
			
		});
		
		addItemHtml('moveDataRight', ESTM_CMTM_SPHE_ARR.length, 'N');
		
	};
	
	pageObj.noSlct = function() {
		
		//ESTM_CMTM_SPHE_ARR[i][1]
		searchCmtmNm = $("#noSlctCmtmNm").val();
		searchLlfSecd = $("#P_LLF_SECD").val();
		searchCntnSecd = $("#P_CNTN_SECD").val();
		searchMlfSecd = $("#P_MLF_SECD").val();
		searchSlfSecd = $("#P_SLF_SECD").val();
		
		var ITEM_ARR_LENGTH = ESTM_CMTM_SPHE_ARR.length;
		
		if(searchCmtmNm.length == 0 && searchLlfSecd.length == 0 && searchCntnSecd.length == 0 && searchMlfSecd.length == 0 && searchSlfSecd.length == 0){
			addItemHtml('onload', ITEM_ARR_LENGTH, 'N');
		}else {
			addItemHtml('onload', ITEM_ARR_LENGTH, 'Y');
		}
		$("#noSlctCmtmNm").val("");
		
	};

	// 체크된 데이터 미선정위원표로 이동
	moveDataLeft = function() {
		$("#copyAreaTwo input[name='chck']").each(function(inx){
			if(this.checked) {
//				console.log($(this).parent().parent().find($("input[name='D_ESTM_CMTM_NO']")).val());
//				console.log($(this).parent().parent().find($("input[name='D_SELECT_AT']")).val());
				var D_ESTM_CMTM_NO  = $(this).parent().parent().find($("input[name='P_ESTM_CMTM_NO']")).val();
				for(var i = 0; i< ESTM_CMTM_SPHE_ARR.length; i++) {
					if(ESTM_CMTM_SPHE_ARR[i][0] == D_ESTM_CMTM_NO) {
						ESTM_CMTM_SPHE_ARR[i][4] = "N";
					}
				}
			}
			
		});
		
		addItemHtml('moveDataLeft', ESTM_CMTM_SPHE_ARR.length, 'N');
		
	};
	
	
	pageObj.setEventHandler = function() {
		
		$('#noSlctCmtmNm').keydown(function() {
		  if (event.keyCode === 13) {
		    event.preventDefault();
		  };
		});
			
		//  미평가위원조회
		$("#noSlctBtn").on("click", function() {
			pageObj.noSlct();
		});
		
		//  목록
		$("#listBtn").on("click", function() {
			pageObj.estmCmtmSpheMpgList();
		});

		
		// 저장
		$("#saveBtn").on("click", function() {
			pageObj.estmCmtmSpheMpgReg();
		});
		
	};
	

	/**
	 * window load
	 *
	 */
	$(function() {		
		pageObj.setEventHandler();
		
		// 평가위원번호
		$("input[name='D_ESTM_CMTM_NO']").each(function(i) {
			ESTM_CMTM_SPHE_ARR[i] = new Array(6);
			ESTM_CMTM_SPHE_ARR[i][0] = $(this).val();
			//console.log("0 ::" + ESTM_CMTM_SPHE_ARR[i][0]);
		});
		
		// 평가위원명
		$("input[name='D_ESTM_CMTM_NM']").each(function(i) {
			ESTM_CMTM_SPHE_ARR[i][1] = $(this).val();
			//console.log("1 ::" + ESTM_CMTM_SPHE_ARR[i][1]);
		});
		
		// 전화번호
		$("input[name='D_CP_NO']").each(function(i) {
			ESTM_CMTM_SPHE_ARR[i][2] = $(this).val();
			//console.log("2 ::" + ESTM_CMTM_SPHE_ARR[i][2]);
		});
		
		// 이메일
		$("input[name='D_EMAL']").each(function(i) {
			ESTM_CMTM_SPHE_ARR[i][3] = $(this).val();
			//console.log("3 ::" + ESTM_CMTM_SPHE_ARR[i][3]);
		});

		// 평가위원선정구분
		$("input[name='D_SELECT_AT']").each(function(i) {
			ESTM_CMTM_SPHE_ARR[i][4] = $(this).val();
			//console.log("4 ::" + ESTM_CMTM_SPHE_ARR[i][4]);
		});
		
		// 내/외부구분
		$("input[name='D_INO_CMTM_SENM']").each(function(i) {
			ESTM_CMTM_SPHE_ARR[i][5] = $(this).val();
			//console.log("5 ::" + ESTM_CMTM_SPHE_ARR[i][5]);
		});
		
		// 대분류
		$("input[name='D_LLF_SECD']").each(function(i) {
			ESTM_CMTM_SPHE_ARR[i][6] = $(this).val();
		});
		$("input[name='D_LLF_NM']").each(function(i) {
			ESTM_CMTM_SPHE_ARR[i][7] = $(this).val();
		});
		
		// 내역
		$("input[name='D_CNTN_SECD']").each(function(i) {
			ESTM_CMTM_SPHE_ARR[i][8] = $(this).val();
		});
		$("input[name='D_CNTN_NM']").each(function(i) {
			ESTM_CMTM_SPHE_ARR[i][9] = $(this).val();
		});
		
		// 중분류
		$("input[name='D_MLF_SECD']").each(function(i) {
			ESTM_CMTM_SPHE_ARR[i][10] = $(this).val();
		});
		$("input[name='D_MLF_NM']").each(function(i) {
			ESTM_CMTM_SPHE_ARR[i][11] = $(this).val();
		});
		
		// 소분류
		$("input[name='D_SLF_SECD']").each(function(i) {
			ESTM_CMTM_SPHE_ARR[i][12] = $(this).val();
		});
		$("input[name='D_SLF_NM']").each(function(i) {
			ESTM_CMTM_SPHE_ARR[i][13] = $(this).val();
		});
		var ITEM_ARR_LENGTH = ESTM_CMTM_SPHE_ARR.length;
		
		addItemHtml('onload',ITEM_ARR_LENGTH, 'N');
		
		
	});
	
})();