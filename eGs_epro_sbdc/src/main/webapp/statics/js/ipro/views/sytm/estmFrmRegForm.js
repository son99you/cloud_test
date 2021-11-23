(function() {

	/**
	 * Default Constructor
	 */
	pageObj = {};
	
	
	// 2차원 배열 생성 함수
	//평가항목 arrayList 초기세팅
	/**
	 * ITEM_ARR[0][0] == 항목번호
	 * ITEM_ARR[0][1] == 항목형식
	 * ITEM_ARR[0][2] == 항목명
	 * ITEM_ARR[0][3] == 필수체크여부
	 * 
	 */
	var ITEM_ARR = new Array(1);
	for(var i = 0; i < 1; i++) {
		ITEM_ARR[i] = new Array(5);
	}

	ITEM_ARR[0][0] = "01";

	movePage = function(url) {
		FwkCmmnUtil.submitForm("listFrm", url);  
	};
	

	/**
	 * 항목형식에 따라 검색버튼 감추기
	 * @param obj
	 */
	changeItemSecd = function(obj) {
		if($(obj).val() == "B") {
			$(obj).parent().find($("button[name='searchBtn']")).css("display","");
		}else {
			$(obj).parent().find($("button[name='searchBtn']")).css("display","none");
		}
		
	}
	
	
	addItemHtml = function(btnGbn, itemArrLength) {
		var ITEM_ARR_LENGTH = itemArrLength;
		var itemNoMax = ITEM_ARR[ITEM_ARR_LENGTH][0];
		
		/**
		 * 최저레벨 댑스를 찾아서 colspan 갯수를 결정한다.
		 */
		var maxStep = ITEM_ARR[0][0].length;
		for(var i = 0; i < ITEM_ARR.length; i++ ){
			if(maxStep < ITEM_ARR[i][0].length) {
				maxStep = ITEM_ARR[i][0].length;
			}
		}
		
		
		/**
		 * 평가항목표 rowCnt 
		 */
		var rowCnt = 1;

		/**
		 * 다중 반복문을 통해서 최대 row값을 찾는다. 
		 */
		for(var k = 0; k < ITEM_ARR.length; k++) {
			var itemNoStr_K = ITEM_ARR[k][0];
			var itemNoLen_K = ITEM_ARR[k][0].length;
			
			/**
			 *  ITEM_ARR[k][0]의 itemNo값 만큼 자릿수를 잘라낸다.
			 *  ex) ITEM_ARR[k][0] ==> 01 이면 01
			 *  	ITEM_ARR[k][0] ==> 0201 이면 0201
			 */
			itemNoStr_K = itemNoStr_K.substring(0, itemNoLen_K);
			
			var rowCntYn=0;
			for(var l = 0; l < ITEM_ARR.length; l++) {
				var itemNoStr_L = ITEM_ARR[l][0];
				/**
				 * ITEM_ARR[l][0]값을 ITEM_ARR[k][0]의 자릿수 만큼 잘라낸다.
				 * ex)	ITEM_ARR[l][0] ==> 0202 이고 ITEM_ARR[k][0] ==> 01 이면 결과는 ITEM_ARR[l][0].substring(0,2) ==> 02
				 *  	ITEM_ARR[l][0] ==> 0201 이고 ITEM_ARR[k][0] ==> 0101 이면 결과는 ITEM_ARR[l][0].substring(0,4) ==> 0201
				 * 
				 * 위와 같은 방법으로 ITEM_ARR[][0]에 들어있는 값들을 확인하여
				 * 최종적으로 표의 row값을 구한다.
				 * 
				 * 그 이유는 01 0101 과 같이 스탭을 낮춘 경우에는 row가 추가되는 것을 방지해야되기 때문이다.
				 * 
				 */
				itemNoStr_L = itemNoStr_L.substring(0, itemNoLen_K);
				
				/**
				 * itemNoStr_L == itemNoStr_K를 비교하여
				 * 같은 자릿수에 있는 값이 많으면 rowCntYn를 카운트하게 한다.
				 * ex) 01 0101 010101 인경우
				 * itemNoStr_K 값이 01일때는 3번
				 * itemNoStr_K 값이 0101일때는 1번
				 * itemNoStr_K 값이 0102일때는 1번의 카운트를 센다.
				 * 그러므로 rowCnt = 2가 된다.
				 */
				if(itemNoStr_L == itemNoStr_K) {
					rowCntYn++;
				}	
			}
			
			/**
			 * rowCntYn이 자기 자신일때는 rowCnt를 올리면 안되기 때문에
			 * rowCntYn > 1 보다 클때만 rowCnt가 증가하도록 함.
			 */
			if(rowCntYn > 1) {
				rowCnt++;
			}
			
		}
		
		// colspan 갯수
		var colspanCnt = maxStep/2;
		
		/**
		 *  2. 위치에 맞게 데이터 Map에 넣기
		 *  2-1. 들어온 순서대로 sortMap 안에 itemNo 와 x, y좌표를 생성한다.
		 */
		
		var sortMap = new Array(1);
		
		var sortX = 0;
		var sortY = 0;
		// 1번기본 세팅
		sortMap[0] = new Array(3);
		sortMap[0][0] = ITEM_ARR[0][0];
		sortMap[0][1] =sortX;
		sortMap[0][2] =sortY;
		for(var i = 1; i < ITEM_ARR.length; i++) {
			
			
			/**
			 * ITEM_ARR배열의 길이 만큼 반복문을 돌면서
			 * sortMap을 생성한다.
			 */
			sortMap[i] = new Array(3);
			/**
			 *  sortMap[][0]에 ITEM_ARR[][0]인 itemNo값을 넣어주어 나중에
			 *  표가 그려질때 itemNo의 위치가 들어있는 x, y좌표를 찾을수 있도록 세팅한다.
			 *  
			 */
			sortMap[i][0] = ITEM_ARR[i][0];
			
			/**
			 * 좌표를 지정하는 방법
			 * 1. 이전값의 자릿수와 현재값의 자릿수가 같을때 ITEM_ARR[i-1][0].length == ITEM_ARR[i][0].length
			 * 	  : 이전값과 현재값의 자릿수가 같다는 것은 row가 한줄 생겼다는 것을 의미하기 때문에
			 * 		x좌표가 +1 되어야 하고, y좌표는 변함이 없음으로 이전값의 y좌표를 그대로 유지한다. 
			 * 	  그러므로 x좌표 : +1 , y좌표 : 이전값의 y값 유지 ==> (이전x좌표+1, 이전y좌표)
			 * 
			 * 2. 이전값의 자릿수보다 현재값의 자릿수가 클때 ITEM_ARR[i-1][0].length < ITEM_ARR[i][0].length
			 *    : 이전값의 자릿수보다 현재값의 자릿수가 더 크다는 것은 이전값보다 step이 하나 더 생겼다는 의미이기 때문에
			 *      y좌표가 +1 되어야 하고, x좌표는 변함이 없으므로 이전값의 x좌표를 그대로 유지한다.
			 *    그러므로 x좌표 : 이전값의 x값 유지 , y좌표 : +1 ==> (이전x좌표, 이전y좌표+1)
			 *    
			 * 3. 이전값의 자릿수가 현재값의 자릿수보다 클때 ITEM_ARR[i-1][0].length > ITEM_ARR[i][0].length
			 *    : 이전값의 자릿수가 현재값의 자릿수보다 더 크다는 것은 현재값이 이전값보다 step이 하나 더 적어졌다는 의미이기 때문에
			 *      row가 한줄 생겼다는 의미와 현재값의 자릿수가 이전값의 자릿수보다 줄어들었다는 것을 의미한다.
			 *      그런데 여기서 우리는 step이 얼마나 줄어들었나를 알아야한다. 증가할때는 일정하게 증가(자릿수는+2, step은+1)하였지만
			 *      줄어들때는 일정하게 줄어들지 않는다.
			 *      ex) step이 5인 경우 01의 + 버튼을 눌렀는지 010101의 + 버튼을 눌렀는지 알수 없기 때문이다.
			 *      그래서 그것을 알려면 현재값의 자릿수를 기준으로 찾아야한다.
			 *      그 방법은 (현재값의자릿수/2)-1 이다.
			 *     
			 *     그러므로 x좌표 : +1 , y좌표 : (현재값의 자릿수/2)-1
			 *   
			 *  
			 */
			if(ITEM_ARR[i-1][0].length == ITEM_ARR[i][0].length ) {
				// 자릿수가 같을때  : x좌표 : +1 , y좌표 : 이전값의 y값 유지 ==> (이전x좌표+1, 이전y좌표)
				sortX++;
			}
			else if(ITEM_ARR[i-1][0].length < ITEM_ARR[i][0].length) {
				// 자릿수가 증가(0, +1), x좌표유지
				sortY++;
			}else if(ITEM_ARR[i-1][0].length > ITEM_ARR[i][0].length) {
				// 자릿수가 감소(+1, 0), 자릿수체크(0, n)
				sortX++;
				sortY = (ITEM_ARR[i][0].length/2) -1;
			}
			
			sortMap[i][1] =sortX;
			sortMap[i][2] =sortY;
			
		}
		
		//좌표이기 때문에 +1 해서 row수 찾아주기
		sortX = sortX+1;
		
		var strHtml = "";
		strHtml  += "<table class=\"component-detail-table type-line-none\">";
		strHtml  += "<colgroup>";
		if(colspanCnt >= 1) {
			for(var i = 0; i< colspanCnt; i++) {
				strHtml  += "<col style=\"width: \"" + 100/colspanCnt + "\"%;\">";
			}
		}else if(colspanCnt < 1){
			strHtml  += "<col style=\"width: auto;\">";
		}
		
		strHtml  += "</colgroup>";
		strHtml  += "<thead>";
		strHtml  += "<tr>";
		strHtml  += "<th colspan=\""  + colspanCnt + "\">상세내용</th>";
		strHtml  += "</tr>";
		strHtml  += "</thead>";
		strHtml  += "<tbody id=\"copyArea\">";

		var itemLine = 0;
		for(var n = 0; n < sortX; n++) {
			strHtml  += "<tr>";

			/**
			 *  해당 줄에서 itemNo값이 처음인지 여부를 체크해야되기 떄문에
			 *  colFirstYn 값을 생성
			 *  
			 *  이유 : 좌표에 의해서 표를 그리고 있기 때문에 이전row에서 rowspan이 되었는지
			 *  	여부를 판단할 수 없으므로 colFirstYn값을 만들어서 체크한다.
			 */
			var colFirstYn = "N";
			for(var m = 0; m < colspanCnt; m++) {
				
				/**
				 * col 위치 찾기
				 */
				var itemNo = "";
				var myItemX = "";
				var myItemY = "";
				
				
				/**
				 * sortMap에서 세팅해놓은 x, y 좌표를 이용해서
				 * 다중중첩반복문을 돌고 있는 n값과 m값이 sortMap x, y 와 같은 경우
				 * itemNo = sortMap[q][0];
				 * myItemX = sortMap[q][1];
				 * myItemY = sortMap[q][2];
				 * colFirstYn = "Y";
				 * 
				 * 값을 받아오도록 세팅한다.
				 */
				for(var q = 0; q < sortMap.length; q++) {
					if(sortMap[q][1] == n && sortMap[q][2] == m) {
						itemNo = sortMap[q][0];
						myItemX = sortMap[q][1];
						myItemY = sortMap[q][2];
						colFirstYn = "Y";
					}
				}
				
				/**
				 * 좌표를 통해 찾은 sortMap의 itemNo값을 가지고
				 * ITEM_ARR[][0]과 비교하여
				 * 표안에 들어갈 ITEM_ARR[itemLine][] 값을 찾아낸다.
				 */
				for(var t = 0; t < ITEM_ARR.length; t++) {
					if(ITEM_ARR[t][0] == itemNo) {
						itemLine = t;
					}
				}
				
				/**
				 * itemNo값을 찾고 (itemNo값의 길이/2)-1을 계산해서
				 * 나의 col위치를 찾는다.
				 */
				var myColCnt = ITEM_ARR[itemLine][0];
				
				myColCnt = Number(myColCnt.length/2)-1;
				
				/**
				 * rowspan 합치기 작업
				 * 
				 */
				
				
				var myItemNo = ITEM_ARR[itemLine][0];
				var myItemLen = ITEM_ARR[itemLine][0].length;
				
				var myItemXmax = myItemX;
				for(var k = 0; k < ITEM_ARR.length; k++) {
					var itemNoStr = ITEM_ARR[k][0];
					itemNoStr = itemNoStr.substring(0, myItemLen);
					if(myItemNo == itemNoStr ) {
						if(Number(myItemNo) < Number(ITEM_ARR[k][0])){
							for(var r = 0; r < sortMap.length; r++) {
								if(sortMap[r][0] == ITEM_ARR[k][0]) {
									myItemXmax = sortMap[r][1];
								}
							}
						}
					}
					
				}
				
				
				
				var myRowCnt = Number(myItemXmax) - Number(myItemX) + 1;
				
				if(colFirstYn == "Y") {
					
					if(itemNo == "") {
						console.log("itemNo ==");
						strHtml  += "<td>";
						strHtml  += "</td>";	
					}
					else if(rowCnt == 1 && ITEM_ARR[itemLine][0].substring(myItemLen-2,myItemLen) == "01") {
						strHtml  += "<td>";
						
						if(ITEM_ARR[itemLine][1] != "" && ITEM_ARR[itemLine][1] != null)
						{
							if(ITEM_ARR[itemLine][1] == "A")
							{
								strHtml += "항목형식&nbsp; : &nbsp; <select class=\"component-select type-division\" style=\"width:120px;\" name=\"P_ITEM_TYPE\" onchange=\"changeItemSecd(this);\"><option value=\"A\" selected>항목명</option><option value=\"B\">항목데이터</option></select>";
							}else if(ITEM_ARR[itemLine][1] == "B")
							{
								strHtml += "항목형식&nbsp; : &nbsp; <select class=\"component-select type-division\" style=\"width:120px;\" name=\"P_ITEM_TYPE\" onchange=\"changeItemSecd(this);\"><option value=\"A\">항목명</option><option value=\"B\" selected>항목데이터</option></select>";	
							}
							
						}else 
						{
							strHtml += "항목형식&nbsp; : &nbsp; <select class=\"component-select type-division\" style=\"width:120px;\" name=\"P_ITEM_TYPE\" onchange=\"changeItemSecd(this);\"><option value=\"A\">항목명</option><option value=\"B\">항목데이터</option></select>";
						}
						
						strHtml += "&nbsp;&nbsp;";
						
						if(ITEM_ARR[itemLine][3] != "" && ITEM_ARR[itemLine][3] != null)
						{
							if(ITEM_ARR[itemLine][3] == "N")
							{
								strHtml += "필수체크여부&nbsp; : &nbsp;<select class=\"component-select type-division\" style=\"width:120px;\" name=\"P_ESS_AT\"><option value=\"N\" selected>아니오</option><option value=\"Y\">예</option></select>";
							}else if(ITEM_ARR[itemLine][3] == "Y")
							{
								strHtml += "필수체크여부&nbsp; : &nbsp;<select class=\"component-select type-division\" style=\"width:120px;\" name=\"P_ESS_AT\"><option value=\"N\">아니오</option><option value=\"Y\" selected>예</option></select>";	
							}
							
						}else 
						{
							strHtml += "필수체크여부&nbsp; : &nbsp;<select class=\"component-select type-division\" style=\"width:120px;\" name=\"P_ESS_AT\"><option value=\"N\">아니오</option><option value=\"Y\">예</option></select>";
						}
						
						if(ITEM_ARR[itemLine][0] != "" && ITEM_ARR[itemLine][0] != null) 
						{
							strHtml  += "<input type=\"hidden\" class=\"component-input\"  name=\"P_ITEM_NO\" value=\"" + ITEM_ARR[itemLine][0] + "\">";
						}else  
						{
							strHtml  += "<input type=\"hidden\" class=\"component-input\"  name=\"P_ITEM_NO\">";
						}
						if(ITEM_ARR[itemLine][1] != "" && ITEM_ARR[itemLine][1] != null)
						{
							strHtml  += "<input type=\"text\" class=\"component-input type-full\" name=\"P_ITEM_NM\" placeholder=\"항목명\" value=\"" + ITEM_ARR[itemLine][1] + "\">";
						}else 
						{
							strHtml  += "<input type=\"text\" class=\"component-input type-full\" name=\"P_ITEM_NM\" placeholder=\"항목명\">";
						}
						
						
						
						
						strHtml += "&nbsp;&nbsp;";
						
						if(ITEM_ARR[itemLine][1] != "" && ITEM_ARR[itemLine][1] != null)
						{
							if(ITEM_ARR[itemLine][1] == "A")
							{
								strHtml += "<button type=\"button\" style=\"width:30px; display: none;\" class=\"component-button-s type-line\" id=\"searchBtn\" name=\"searchBtn\">?</button>";
							}else if(ITEM_ARR[itemLine][1] == "B")
							{
								strHtml += "<button type=\"button\" style=\"width:30px;\" class=\"component-button-s type-line\" id=\"searchBtn\" name=\"searchBtn\">?</button>";	
							}
							
						}else 
						{
							strHtml += "<button type=\"button\" style=\"width:30px; display: none;\" class=\"component-button-s type-line\" id=\"searchBtn\" name=\"searchBtn\">?</button>";
						}
						
						
						if(itemNoMax.substring(0, (2*m+2)) == ITEM_ARR[itemLine][0].substring(0, (2*m+2))) {
							strHtml  += "<button type=\"button\" style=\"width:30px;\" class=\"component-button-s type-line\" name=\"regBtn\">+</button>";
						}else {
							strHtml  += "<input type=\"hidden\" />";
						}
						if(itemNoMax ==  ITEM_ARR[itemLine][0]) {
							strHtml  += "<button type=\"button\" style=\"width:30px;\" class=\"component-button-s type-line\" name=\"regChildBtn\">>></button>";
						}else {
							strHtml  += "<input type=\"hidden\" />";
						}
						strHtml += "</td>";
					}
					else {
						console.log("else ");
						strHtml  += "<td rowspan=\"" + myRowCnt + "\" >";
						
						if(ITEM_ARR[itemLine][1] != "" && ITEM_ARR[itemLine][1] != null)
						{
							if(ITEM_ARR[itemLine][1] == "A")
							{
								strHtml += "항목형식&nbsp; : &nbsp; <select class=\"component-select type-division\" style=\"width:120px;\" name=\"P_ITEM_TYPE\" onchange=\"changeItemSecd(this);\"><option value=\"A\" selected>항목명</option><option value=\"B\">항목데이터</option></select>";
							}else if(ITEM_ARR[itemLine][1] == "B")
							{
								strHtml += "항목형식&nbsp; : &nbsp; <select class=\"component-select type-division\" style=\"width:120px;\" name=\"P_ITEM_TYPE\" onchange=\"changeItemSecd(this);\"><option value=\"A\">항목명</option><option value=\"B\" selected>항목데이터</option></select>";	
							}
							
						}else 
						{
							strHtml += "항목형식&nbsp; : &nbsp; <select class=\"component-select type-division\" style=\"width:120px;\" name=\"P_ITEM_TYPE\" onchange=\"changeItemSecd(this);\"><option value=\"A\">항목명</option><option value=\"B\">항목데이터</option></select>";
						}
						
						strHtml += "&nbsp;&nbsp;<br>";
						
						if(ITEM_ARR[itemLine][3] != "" && ITEM_ARR[itemLine][3] != null)
						{
							if(ITEM_ARR[itemLine][3] == "N")
							{
								strHtml += "필수체크여부&nbsp; : &nbsp;<select class=\"component-select type-division\" style=\"width:120px;\" name=\"P_ESS_AT\"><option value=\"N\" selected>아니오</option><option value=\"Y\">예</option></select>";
							}else if(ITEM_ARR[itemLine][3] == "Y")
							{
								strHtml += "필수체크여부&nbsp; : &nbsp;<select class=\"component-select type-division\" style=\"width:120px;\" name=\"P_ESS_AT\"><option value=\"N\">아니오</option><option value=\"Y\" selected>예</option></select>";	
							}
							
						}else 
						{
							strHtml += "필수체크여부&nbsp; : &nbsp;<select class=\"component-select type-division\" style=\"width:120px;\" name=\"P_ESS_AT\"><option value=\"N\">아니오</option><option value=\"Y\">예</option></select>";
						}
						
						if(ITEM_ARR[itemLine][0] != "" && ITEM_ARR[itemLine][0] != null)
						{
							strHtml  += "<input type=\"hidden\" class=\"component-input\" name=\"P_ITEM_NO\" value=\"" + ITEM_ARR[itemLine][0] + "\">";
						}else 
						{
							strHtml  += "<input type=\"hidden\" class=\"component-input\" name=\"P_ITEM_NO\">";
						}
						if(ITEM_ARR[itemLine][2] != "" && ITEM_ARR[itemLine][2] != null)
						{
							strHtml  += "<input type=\"text\" class=\"component-input type-full\" name=\"P_ITEM_NM\" placeholder=\"항목명\" value=\"" + ITEM_ARR[itemLine][2] + "\">";	
						}else 
						{
							strHtml  += "<input type=\"text\" class=\"component-input type-full\" name=\"P_ITEM_NM\" placeholder=\"항목명\" >";
						}
						
						strHtml += "&nbsp;&nbsp;";
						
						if(ITEM_ARR[itemLine][1] != "" && ITEM_ARR[itemLine][1] != null)
						{
							if(ITEM_ARR[itemLine][1] == "A")
							{
								strHtml += "<button type=\"button\" style=\"width:30px; display: none;\" class=\"component-button-s type-line\" id=\"searchBtn\" name=\"searchBtn\">?</button>";
							}else if(ITEM_ARR[itemLine][1] == "B")
							{
								strHtml += "<button type=\"button\" style=\"width:30px;\" class=\"component-button-s type-line\" id=\"searchBtn\" name=\"searchBtn\">?</button>";	
							}
							
						}else 
						{
							strHtml += "<button type=\"button\" style=\"width:30px; display: none;\" class=\"component-button-s type-line\" id=\"searchBtn\" name=\"searchBtn\">?</button>";
						}
						
						if(itemNoMax.substring(0, (2*m+2)) == ITEM_ARR[itemLine][0].substring(0, (2*m+2))) {
							strHtml  += "<button type=\"button\" style=\"width:30px;\" class=\"component-button-s type-line\" name=\"regBtn\">+</button>";
						}else {
							strHtml  += "<input type=\"hidden\" />";
						}
						if(itemNoMax ==  ITEM_ARR[itemLine][0]) {
							strHtml  += "<button type=\"button\" style=\"width:30px;\" class=\"component-button-s type-line\" name=\"regChildBtn\">>></button>";
							strHtml  += "<button class=\"component-button-s type-line\" style=\"width:30px;\" name=\"deleteBtn\" >X</button>";
						}else {
							strHtml  += "<input type=\"hidden\" />";
						}
						
						strHtml += "</td>";
					}
				}else if(colFirstYn == "N") {
					if(rowCnt == 1 && itemLine < colspanCnt) {
						strHtml  += "<td>";
						strHtml  += "</td>";
					}
				}
			}
			
			strHtml  += "</tr>";
		}
		
		strHtml  += "</tbody>";
		strHtml  += "</table>";
		
		
		$("#tableDiv").html(strHtml);
	}
	
	
	dataFn = function() {
		for(var i = 0; i < ITEM_ARR.length; i++) {
			console.log( ITEM_ARR[i][0] +  " : " + ITEM_ARR[i][1] + " : " + ITEM_ARR[i][2] + " : " + ITEM_ARR[i][3] + " : " + ITEM_ARR[i][4]);
		}
	};
	
	// 저장
	gotoSave = function() {
		
		
		if($("#P_ESTM_PROCD_SECD").val() == ''){
			alert("평가절차구분을 입력하여 주시기 바랍니다.");
			$("#P_ESTM_PROCD_SECD").focus();
			return false;
		}
		
		if($("#P_ESTM_FRM_NM").val() == ''){
			alert("서식명을 입력하여 주시기 바랍니다.");
			$("#P_ESTM_FRM_NM").focus();
			return false;
		}
		
		var returnAt = "T";
		
		//평가항목명
		$("input[name='P_ITEM_NM']").each(function(i) {
			if($(this).val() == ''){
				alert("평가항목명을 입력하여 주시기 바랍니다.");
				returnAt = "F";
				return false;
			}
		});
		
		if(returnAt == "F") {
			return false;
		}
		
		// 평가항목구분
		$("select[name='P_ITEM_TYPE']").each(function(i) {
			if($(this).val() == ''){
				alert("평가항목을 선택하여 주시기 바랍니다.");
				returnAt = "F";
				return false;
			}
		});
		
		if(returnAt == "F") {
			return false;
		}
		
		// 배점
		$("input[name='P_ESTM_DTL_ITEM_SCR']").each(function(i) {
			if($(this).val() == ''){
				alert("배점을 선택하여 주시기 바랍니다.");
				returnAt = "F";
				return false;
			}
		});
		
		if(returnAt == "F") {
			return false;
		}
		
		// 평가항목내용
//		$("textarea[name='P_ESTM_ITEM_DTL_CNTN']").each(function(i) {
//
//			if($(this).val() == ''){
//				alert("평가항목내용을 선택하여 주시기 바랍니다.");
//				returnAt = "F";
//				return false;
//			}
//		});
		
		if(returnAt == "F") {
			return false;
		}
		
		if(!confirm("저장 하시겠습니까?")){
			return false;
		}else {
			FwkCmmnUtil.submitForm("regFrm" , "/sytm/estmFrmRegist.do");	
		}
		
		 
		return false;
		
	};
	
	
	
	/**
	 * 1. 미리보기팝업호출
	 */
	reViewPopup = function() {
		$("#popupFrm").one("submit", function() {
			window.open("", "estmReviewPopup", "width=1024px,height=600px,toolbar=no,status=no,scrollbars=yes,resizeable=no,menubar=no,left="+x(740)+",top="+y(600));
			this.action = FwkCmmnUtil['contextPath']+'/comm/popup/estmReview.do';
	        this.method = 'POST';
	        this.target = 'estmReviewPopup';
	    }).trigger("submit");
	};
	
	

	pageObj.setEventHandler = function() {
		
		/**
		 * 미리보기
		 */
		$("#reViewBtn").on("click", function() {
			reViewPopup();
			return false;
		});
		
		/**
		 * 데이터확인
		 */
		$("#dataBtn").on("click", function() {
			
			//평가항목번호 가져오기
			var ESTM_DTL_ITEM_NO = $(this).parent().find($("input[name='P_ITEM_NO']")).val();
			var ITEM_ARR_LENGTH = ITEM_ARR.length;
			
			
			//항목번호
			$("input[name='P_ITEM_NO']").each(function(i) {
				ITEM_ARR[i][0] = $(this).val();
			});
			
			//항목형식
			$("select[name='P_ITEM_TYPE']").each(function(i) {
				ITEM_ARR[i][1] = $(this).val();
			});
			
			//항목명
			$("input[name='P_ITEM_NM']").each(function(i) {
				ITEM_ARR[i][2] = $(this).val();
			});
			
			//필수체크여부
			$("select[name='P_ESS_AT']").each(function(i) {
				ITEM_ARR[i][3] = $(this).val();
			});
			
			dataFn();
			return false;
		});
		
		/**
		 * 1. 목록버튼 클릭 이벤트
		 */
		$("#listBtn").on("click", function() {
			FwkCmmnUtil.submitForm("listFrm" , "/sytm/estmFrmList.do"); 
			return false;
		});
		
		// 저장
		$("#saveBtn").on("click", function() {
			gotoSave();
		});
		
		//평가항목삭제
		$(document).on("click","#tableDiv button[name='deleteBtn']", function() {
			//평가항목번호 가져오기
			var ESTM_DTL_ITEM_NO = $(this).parent().find($("input[name='P_ITEM_NO']")).val();
			
			//1. 삭제하기
			for(var i = 0; i < ITEM_ARR.length; i++) 
			{
				if(ESTM_DTL_ITEM_NO == ITEM_ARR[i][0].substring(0, ESTM_DTL_ITEM_NO.length))
				{
					ITEM_ARR.splice(i,1);
				}
			}
			
			var ITEM_ARR_LENGTH = ITEM_ARR.length-1;
			
			addItemHtml('deleteBtn',ITEM_ARR_LENGTH);
			return false;
		});
		
		//평가항목 행 추가(+)
		$(document).on("click","#tableDiv button[name='regBtn']", function() {
			//평가항목번호 가져오기
			var ESTM_DTL_ITEM_NO = $(this).parent().find($("input[name='P_ITEM_NO']")).val();
					
			var ITEM_ARR_LENGTH = ITEM_ARR.length;
			
			//항목번호
			$("input[name='P_ITEM_NO']").each(function(i) {
				ITEM_ARR[i][0] = $(this).val();
			});
			
			//항목형식
			$("select[name='P_ITEM_TYPE']").each(function(i) {
				ITEM_ARR[i][1] = $(this).val();
			});
			
			//항목명
			$("input[name='P_ITEM_NM']").each(function(i) {
				ITEM_ARR[i][2] = $(this).val();
			});
			
			//필수체크여부
			$("select[name='P_ESS_AT']").each(function(i) {
				ITEM_ARR[i][3] = $(this).val();
			});
			
			var maxNum = Number(ESTM_DTL_ITEM_NO.substring(ESTM_DTL_ITEM_NO.length-2, ESTM_DTL_ITEM_NO.length))+1;
			
			if(maxNum < 10) {
				maxNum = "0" + maxNum;
			}else if(maxNum == 100) {
				alert("레벨생성 갯수를 초과하였습니다.");
				return;
			}


			ITEM_ARR[ITEM_ARR_LENGTH] = new Array(5);
			ITEM_ARR[ITEM_ARR_LENGTH][0] = ESTM_DTL_ITEM_NO.substring(0, ESTM_DTL_ITEM_NO.length-2) + maxNum;


			
			addItemHtml('regBtn',ITEM_ARR_LENGTH);
			
			
		});

		//평가항목 열 추가(>>)
		$(document).on("click","#tableDiv button[name='regChildBtn']", function() {
			
			//평가항목번호 가져오기
			var ESTM_DTL_ITEM_NO = $(this).parent().find($("input[name='P_ITEM_NO']")).val();
			var ITEM_ARR_LENGTH = ITEM_ARR.length;
			
			//항목번호
			$("input[name='P_ITEM_NO']").each(function(i) {
				ITEM_ARR[i][0] = $(this).val();
			});
			
			//항목형식
			$("select[name='P_ITEM_TYPE']").each(function(i) {
				ITEM_ARR[i][1] = $(this).val();
			});
			
			//항목명
			$("input[name='P_ITEM_NM']").each(function(i) {
				ITEM_ARR[i][2] = $(this).val();
			});
			
			//필수체크여부
			$("select[name='P_ESS_AT']").each(function(i) {
				ITEM_ARR[i][3] = $(this).val();
			});
			
			ITEM_ARR[ITEM_ARR_LENGTH] = new Array(5);
			ITEM_ARR[ITEM_ARR_LENGTH][0] = ESTM_DTL_ITEM_NO + "01";
			
			addItemHtml('regChildBtn',ITEM_ARR_LENGTH);
			
			
		});
		
		
		//평가항목 항목데이터 추가(?)
		$(document).on("click","#tableDiv button[name='searchBtn']", function() {
			// 데이터베이스 조회 팝업
			$("#popupFrm input[name='setMulti']").val("N");
			$("#popupFrm").one("submit", function() {
				window.open("", "dataBasePopup", "width=750px,height=760px,toolbar=no,status=no,scrollbars=yes,resizeable=no,menubar=no,left=500,top=100");
				this.action = FwkCmmnUtil['contextPath']+'/comm/popup/dataBaseList.do';
		        this.method = 'POST';
		        this.target = 'dataBasePopup';
		    }).trigger("submit");
		});
		
	};

	/** 
	 * window load
	 *
	 */
	$(function() {		
		pageObj.setEventHandler();
		
		$("#copyArea input[name='P_ITEM_NO']").val(ITEM_ARR[0][0]);
		
	});
	
})();