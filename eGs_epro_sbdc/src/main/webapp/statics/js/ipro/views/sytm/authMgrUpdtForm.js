(function() {

	/**
	 * Default Constructor
	 */
	pageObj = {};
	
	/**
	 * 테스트 세팅
	 *  ESTM_CMTM_SPHE_ARR[0][0] : 권한명
	 *  ESTM_CMTM_SPHE_ARR[0][1] : 메뉴ID
	 *  ESTM_CMTM_SPHE_ARR[0][2] : 메뉴명
	 *  ESTM_CMTM_SPHE_ARR[0][3] : SELECT_AT
	 */
	var MENU_ARR = new Array(1);
	var searchCmtmNm = ""; //평가위원조회조건
	var searchLlfSecd = ""; //대분류조회조건
	var searchCntnSecd = ""; //내역조회조건
	var searchMlfSecd = ""; //중분류조회조건
	var searchSlfSecd = ""; //소분류조회조건
	
	
	// 목록
	pageObj.estmCmtmSpheMpgList = function() {
		FwkCmmnUtil.submitForm("listFrm", "/sytm/authMgrList.do");
	};
	
	
	// 수정
	pageObj.estmCmtmSpheMpgUpdt = function() {
		//FwkCmmnUtil.submitForm("updtFrm", "/estm/estmCmtmSpheMpgUpdt.do");
		
		var jsonData = $("#updtFrm").serializeObject();
		var actionUrl = "/sytm/authMgrUpdt.do";

		FwkCmmnUtil.submitAjax (actionUrl, jsonData
				, function(res) {
			pageObj.estmCmtmSpheMpgList();
		});
	};
	
	
	/**
	 * 자동row추가
	 * @param btnGbn
	 * @param itemArrLength
	 */
	addItemHtml = function(btnGbn, itemArrLength, searchYn) {

		var strHtmlOne = "";
		var strHtmlTwo = "";
		
		//alert("searchYn " + searchYn + " searchCmtmNm " + searchCmtmNm);

		for(var i = 0; i< itemArrLength; i++) {
			
//			console.log("ESTM_CMTM_SPHE_ARR["+i+"][0] = "+ ESTM_CMTM_SPHE_ARR[i][0]);
//			console.log("ESTM_CMTM_SPHE_ARR["+i+"][1] = "+ ESTM_CMTM_SPHE_ARR[i][1]);
//			console.log("ESTM_CMTM_SPHE_ARR["+i+"][2] = "+ ESTM_CMTM_SPHE_ARR[i][2]);
//			console.log("ESTM_CMTM_SPHE_ARR["+i+"][3] = "+ ESTM_CMTM_SPHE_ARR[i][3]);
//			console.log("ESTM_CMTM_SPHE_ARR["+i+"][4] = "+ ESTM_CMTM_SPHE_ARR[i][4]);
			
//			var dataCmtmNm = MENU_ARR[i][0];
//			var dataLlfSecd = MENU_ARR[i][1];
//			var dataCntnSecd = MENU_ARR[i][2];
//			var dataMlfSecd = MENU_ARR[i][3];
//			var dataSlfSecd = MENU_ARR[i][12];
			
//			if(searchCmtmNm.length == 0) { dataCmtmNm = ""; }
//			if(searchLlfSecd.length == 0) { dataLlfSecd = ""; }
//			if(searchCntnSecd.length == 0) { dataCntnSecd = ""; }
//			if(searchMlfSecd.length == 0) { dataMlfSecd = ""; }	
//			if(searchSlfSecd.length == 0) { dataSlfSecd = ""; }
			
//			if(searchYn == "Y") {
				if(MENU_ARR[i][3] == "N" ) {
//					if(dataCmtmNm == searchCmtmNm
//						&& dataLlfSecd == searchLlfSecd
//						&& dataCntnSecd == searchCntnSecd
//						&& dataMlfSecd == searchMlfSecd
//						&& dataSlfSecd == searchSlfSecd
//					) {
					strHtmlOne  += "<tr class=\"txt-center\">";
					strHtmlOne  += "<td class=\"txt-center\">";
					strHtmlOne  += "<label class=\"component-checkbox\">";
					strHtmlOne  += "<input type=\"checkbox\" name=\"chck\">";
					strHtmlOne  += "<i></i>";
					strHtmlOne  += "</label>";
					strHtmlOne  += "<input type=\"hidden\" name=\"P_SELECT_AT\" value=\""+ MENU_ARR[i][3] +"\"\>";
					strHtmlOne  += "<input type=\"hidden\" name=\"P_MENU_ID\" value=\""+ MENU_ARR[i][1] +"\"\>";
					strHtmlOne  += "</td>";
					strHtmlOne  += "<td class=\"txt-center\">" + MENU_ARR[i][0] + "</td>";
					strHtmlOne  += "<td class=\"txt-center\">" + MENU_ARR[i][1];
					strHtmlOne  += "</td>";
					strHtmlOne  += "<td class=\"txt-center\">" + MENU_ARR[i][2] + "</td>";
					strHtmlOne  += "</tr>";
//					}
				}else  if(MENU_ARR[i][3] == "Y"){
					//console.log("Y");
					strHtmlTwo  += "<tr class=\"txt-center\">";
					strHtmlTwo  += "<td class=\"txt-center\">";
					strHtmlTwo  += "<label class=\"component-checkbox\">";
					strHtmlTwo  += "<input type=\"checkbox\" name=\"chck\">";
					strHtmlTwo  += "<i></i>";
					strHtmlTwo  += "</label>";
					strHtmlTwo  += "<input type=\"hidden\" name=\"P_SELECT_AT\" value=\""+ MENU_ARR[i][3] +"\"\>";
					strHtmlTwo  += "<input type=\"hidden\" name=\"P_MENU_ID\" value=\""+ MENU_ARR[i][1] +"\"\>";
					strHtmlTwo  += "</td>";
					strHtmlTwo  += "<td class=\"txt-center\">" + MENU_ARR[i][0] + "</td>";
					strHtmlTwo  += "<td class=\"txt-center\">" + MENU_ARR[i][1];
					strHtmlTwo  += "</td>";
					strHtmlTwo  += "<td class=\"txt-center\">" + MENU_ARR[i][2] + "</td>";
					strHtmlTwo  += "</tr>";
				}
//			}else {
//				if(MENU_ARR[i][4] == "N" ) {
//					//console.log("N");
//					strHtmlOne  += "<tr class=\"txt-center\">";
//					strHtmlOne  += "<td class=\"txt-center\">";
//					strHtmlOne  += "<label class=\"component-checkbox\">";
//					strHtmlOne  += "<input type=\"checkbox\" name=\"chck\">";
//					strHtmlOne  += "<i></i>";
//					strHtmlOne  += "</label>";
//					strHtmlOne  += "<input type=\"hidden\" name=\"P_ESTM_CMTM_NO\" value=\""+ MENU_ARR[i][0] +"\"\>";
//					strHtmlOne  += "<input type=\"hidden\" name=\"P_SELECT_AT\" value=\""+ MENU_ARR[i][4] +"\"\>";
//					strHtmlOne  += "</td>";
//					strHtmlOne  += "<td class=\"txt-center\">" + MENU_ARR[i][1] + "</td>";
//					strHtmlOne  += "<td class=\"txt-center\">" + MENU_ARR[i][5] + "</td>";
//					strHtmlOne  += "<td>" + MENU_ARR[i][2] + "</td>";
//					strHtmlOne  += "<td>" + MENU_ARR[i][3] + "</td>";
//					strHtmlOne  += "<td class=\"txt-center\">" + MENU_ARR[i][7] + "</td>";
//					strHtmlOne  += "<td class=\"txt-center\">" + MENU_ARR[i][9] + "</td>";
//					strHtmlOne  += "<td class=\"txt-center\">" + MENU_ARR[i][11] + "</td>";
//					strHtmlOne  += "<td class=\"txt-center\">" + MENU_ARR[i][13] + "</td>";
//					strHtmlOne  += "</tr>";
//				}else  if(MENU_ARR[i][4] == "Y"){
//					//console.log("Y");
//					strHtmlTwo  += "<tr class=\"txt-center\">";
//					strHtmlTwo  += "<td class=\"txt-center\">";
//					strHtmlTwo  += "<label class=\"component-checkbox\">";
//					strHtmlTwo  += "<input type=\"checkbox\" name=\"chck\">";
//					strHtmlTwo  += "<i></i>";
//					strHtmlTwo  += "</label>";
//					strHtmlTwo  += "<input type=\"hidden\" name=\"P_ESTM_CMTM_NO\" value=\""+ MENU_ARR[i][0] +"\"\>";
//					strHtmlTwo  += "<input type=\"hidden\" name=\"P_SELECT_AT\" value=\""+ MENU_ARR[i][4] +"\"\>";
//					strHtmlTwo  += "</td>";
//					strHtmlTwo  += "<td class=\"txt-center\">" + MENU_ARR[i][1] + "</td>";
//					strHtmlTwo  += "<td class=\"txt-center\">" + MENU_ARR[i][5] + "</td>";
//					strHtmlTwo  += "<td>" + MENU_ARR[i][2] + "</td>";
//					strHtmlTwo  += "<td>" + MENU_ARR[i][3] + "</td>";
//					strHtmlTwo  += "<td class=\"txt-center\">" + MENU_ARR[i][7] + "</td>";
//					strHtmlTwo  += "<td class=\"txt-center\">" + MENU_ARR[i][9] + "</td>";
//					strHtmlTwo  += "<td class=\"txt-center\">" + MENU_ARR[i][11] + "</td>";
//					strHtmlTwo  += "<td class=\"txt-center\">" + MENU_ARR[i][13] + "</td>";
//					strHtmlTwo  += "</tr>";
//				}
//			}
			
			
			
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
				var D_MENU_ID  = $(this).parent().parent().find($("input[name='P_MENU_ID']")).val();
				//alert("D_MENU_ID :: " + D_MENU_ID);
				for(var i = 0; i< MENU_ARR.length; i++) {
					if(MENU_ARR[i][1] == D_MENU_ID) {
						MENU_ARR[i][3] = "Y";
					}
				}
			}
			
		});
		
		addItemHtml('moveDataRight', MENU_ARR.length, 'N');
		
	};

	// 체크된 데이터 미선정위원표로 이동
	moveDataLeft = function() {
		$("#copyAreaTwo input[name='chck']").each(function(inx){
			if(this.checked) {
//				console.log($(this).parent().parent().find($("input[name='D_ESTM_CMTM_NO']")).val());
//				console.log($(this).parent().parent().find($("input[name='D_SELECT_AT']")).val());
				var D_MENU_ID  = $(this).parent().parent().find($("input[name='P_MENU_ID']")).val();
				for(var i = 0; i< MENU_ARR.length; i++) {
					if(MENU_ARR[i][1] == D_MENU_ID) {
						MENU_ARR[i][3] = "N";
					}
				}
			}
			
		});
		
		addItemHtml('moveDataLeft', MENU_ARR.length, 'N');
		
	};
	
	pageObj.noSlct = function() {
		
		//ESTM_CMTM_SPHE_ARR[i][1]
		//alert($("#noSlctCmtmNm").val());
		searchCmtmNm = $("#noSlctCmtmNm").val();
		
		searchLlfSecd = $("#P_LLF_SECD").val();
		searchCntnSecd = $("#P_CNTN_SECD").val();
		searchMlfSecd = $("#P_MLF_SECD").val();
		searchSlfSecd = $("#P_SLF_SECD").val();
		
		var ITEM_ARR_LENGTH = MENU_ARR.length;
		if(searchCmtmNm.length == 0 && searchLlfSecd.length == 0 && searchCntnSecd.length == 0 && searchMlfSecd.length == 0 && searchSlfSecd.length == 0){
			addItemHtml('onload', ITEM_ARR_LENGTH, 'N');
		}else {
			addItemHtml('onload', ITEM_ARR_LENGTH, 'Y');
		}
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
			if(confirm("권한을 수정하시겠습니까?")){
				pageObj.estmCmtmSpheMpgUpdt();
			}
				
		});

		
	};
	

	/**
	 * window load
	 *
	 */
	$(function() {		
		pageObj.setEventHandler();
		
		// 권한명
		$("input[name='D_AUTH_NM']").each(function(i) {
			MENU_ARR[i] = new Array(4);
			MENU_ARR[i][0] = $(this).val();
			//console.log("0 ::" + ESTM_CMTM_SPHE_ARR[i][0]);
		});
		
		// 메뉴ID
		$("input[name='D_MENU_ID']").each(function(i) {
			MENU_ARR[i][1] = $(this).val();
			//console.log("1 ::" + ESTM_CMTM_SPHE_ARR[i][1]);
		});
		
		// 메뉴명
		$("input[name='D_MENU_NM']").each(function(i) {
			MENU_ARR[i][2] = $(this).val();
			//console.log("2 ::" + ESTM_CMTM_SPHE_ARR[i][2]);
		});
		
		// 메뉴선정구분
		$("input[name='D_SELECT_AT']").each(function(i) {
			MENU_ARR[i][3] = $(this).val();
			//console.log("4 ::" + ESTM_CMTM_SPHE_ARR[i][4]);
		});
		
		
		var ITEM_ARR_LENGTH = MENU_ARR.length;
		
		//alert(ITEM_ARR_LENGTH);
		
		addItemHtml('onload', ITEM_ARR_LENGTH, 'N');
		
		
	});
	
})();