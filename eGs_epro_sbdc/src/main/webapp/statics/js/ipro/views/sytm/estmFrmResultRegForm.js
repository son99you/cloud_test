(function() {

	/**
	 * Default Constructor
	 */
	pageObj = {};
	
	
	// 2차원 배열 생성 함수
	//평가항목 arrayList 초기세팅
	var ESTM_DTL_ITEM_ARR = new Array(1);
	for(var i = 0; i < 1; i++) {
		ESTM_DTL_ITEM_ARR[i] = new Array(5);
	}

	ESTM_DTL_ITEM_ARR[0][0] = "0101";

	movePage = function(url) {
		FwkCmmnUtil.submitForm("listFrm", url);  
	};
	
	
	
	addItemHtml = function(btnGbn, itemArrLength) {
		var ITEM_ARR_LENGTH = itemArrLength;
		var itemNoMax = ESTM_DTL_ITEM_ARR[ITEM_ARR_LENGTH][0];
		
		/**
		 * 최저레벨 댑스를 찾아서 colspan 갯수를 결정한다.
		 */
/*		var maxStep = ESTM_DTL_ITEM_ARR[0][0].length;
		for(var i = 0; i < ESTM_DTL_ITEM_ARR.length; i++ ){
			if(maxStep < ESTM_DTL_ITEM_ARR[i][0].length) {
				maxStep = ESTM_DTL_ITEM_ARR[i][0].length;
			}
		}*/
		
		/**
		 * ^버튼을 누르고 생성시 기존에 생성되있는 모든 열을 모두
		 * 만들어내기 때문에 최대값은 항상 열의값과 일치하다.
		 * ex) 0101, 0102, 0103을 생성 후 ^버튼 클릭시
		 *     0101, 0102, 0103
		 *     0201, 0202, 0203이 생성될 것이다.
		 *     기존데이터들은 0201, 0202, 0203으로 이동
		 */
		
		var maxStep = ESTM_DTL_ITEM_ARR[0][0];
		for(var i = 0; i < ESTM_DTL_ITEM_ARR.length; i++ ){
			if(maxStep < ESTM_DTL_ITEM_ARR[i][0]) {
				maxStep = ESTM_DTL_ITEM_ARR[i][0];
			}
		}
		
		var maxStepLen = maxStep.length;
		
		maxStep = maxStep.substring(maxStepLen-2,maxStepLen);
		
		/**
		 * 평가항목표 rowCnt 
		 */
		var rowCnt = 1;
		
		rowCnt = ESTM_DTL_ITEM_ARR[ITEM_ARR_LENGTH][0].substring(0,maxStepLen-2);
		console.log("maxStep ::" + maxStep);
		console.log("rowCnt :: "+ rowCnt);
		

		// colspan 갯수
		var colspanCnt = maxStep;
		
		/**
		 *  2. 위치에 맞게 데이터 Map에 넣기
		 *  2-1. 들어온 순서대로 sortMap 안에 itemNo 와 x, y좌표를 생성한다.
		 */
		
		var sortMap = new Array(1);
		
		var sortX = 0;
		var sortY = 0;
		// 1번기본 세팅
		sortMap[0] = new Array(3);
		sortMap[0][0] = ESTM_DTL_ITEM_ARR[0][0];
		sortMap[0][1] =sortX;
		sortMap[0][2] =sortY;
		for(var i = 1; i < ESTM_DTL_ITEM_ARR.length; i++) {
			
			
			/**
			 * ESTM_DTL_ITEM_ARR배열의 길이 만큼 반복문을 돌면서
			 * sortMap을 생성한다.
			 */
			sortMap[i] = new Array(3);
			/**
			 *  sortMap[][0]에 ESTM_DTL_ITEM_ARR[][0]인 itemNo값을 넣어주어 나중에
			 *  표가 그려질때 itemNo의 위치가 들어있는 x, y좌표를 찾을수 있도록 세팅한다.
			 *  
			 */
			sortMap[i][0] = ESTM_DTL_ITEM_ARR[i][0];
			
			/**
			 * 좌표를 지정하는 방법
			 * 1. 이전값의 자릿수와 현재값의 자릿수가 같을때 ESTM_DTL_ITEM_ARR[i-1][0].length == ESTM_DTL_ITEM_ARR[i][0].length
			 * 	  : 이전값과 현재값의 자릿수가 같다는 것은 row가 한줄 생겼다는 것을 의미하기 때문에
			 * 		x좌표가 +1 되어야 하고, y좌표는 변함이 없음으로 이전값의 y좌표를 그대로 유지한다. 
			 * 	  그러므로 x좌표 : +1 , y좌표 : 이전값의 y값 유지 ==> (이전x좌표+1, 이전y좌표)
			 * 
			 * 2. 이전값의 자릿수보다 현재값의 자릿수가 클때 ESTM_DTL_ITEM_ARR[i-1][0].length < ESTM_DTL_ITEM_ARR[i][0].length
			 *    : 이전값의 자릿수보다 현재값의 자릿수가 더 크다는 것은 이전값보다 step이 하나 더 생겼다는 의미이기 때문에
			 *      y좌표가 +1 되어야 하고, x좌표는 변함이 없으므로 이전값의 x좌표를 그대로 유지한다.
			 *    그러므로 x좌표 : 이전값의 x값 유지 , y좌표 : +1 ==> (이전x좌표, 이전y좌표+1)
			 *    
			 * 3. 이전값의 자릿수가 현재값의 자릿수보다 클때 ESTM_DTL_ITEM_ARR[i-1][0].length > ESTM_DTL_ITEM_ARR[i][0].length
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
			if(ESTM_DTL_ITEM_ARR[i-1][0].length == ESTM_DTL_ITEM_ARR[i][0].length ) {
				// 자릿수가 같을때  : x좌표 : +1 , y좌표 : 이전값의 y값 유지 ==> (이전x좌표+1, 이전y좌표)
				sortX++;
			}
			else if(ESTM_DTL_ITEM_ARR[i-1][0].length < ESTM_DTL_ITEM_ARR[i][0].length) {
				// 자릿수가 증가(0, +1), x좌표유지
				sortY++;
			}else if(ESTM_DTL_ITEM_ARR[i-1][0].length > ESTM_DTL_ITEM_ARR[i][0].length) {
				// 자릿수가 감소(+1, 0), 자릿수체크(0, n)
				sortX++;
				sortY = (ESTM_DTL_ITEM_ARR[i][0].length/2) -1;
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
		
		for(var i = 0; i < rowCnt; i++)
		{
			strHtml  += "<tr>";
			
			/*
				버튼 설명
				>> : 열생성 버튼
				 가장마지막행 가장마지막열에만 활성화
				+ : 합치기 버튼(셀병합)
				 가장마지막행을 제외한 모든 셀에 존재(각 행의 마지막셀에는 비활성화)
				^ : 행생성 버튼 (위로 한줄 생성)
				 가장 위에 있는 행에만 존재
				X : 삭제버튼
				 가장 마지막행의 가장마지막열에만 존재
			 */		
			if(colspanCnt >= 1) {
				for(var j = 0; j< colspanCnt; j++) {
					strHtml  += "<th>";
					strHtml  += "<input type=\"hidden\" class=\"component-input\" name=\"P_ESTM_DTL_ITEM_NO\" value=\"" + ESTM_DTL_ITEM_ARR[j][0] + "\">";
					strHtml  += "<input type=\"text\" class=\"component-input\" style=\"width: 90%;\" name=\"P_ESTM_DTL_ITEM_NM\" placeholder=\"항목명\" value=\"" + ESTM_DTL_ITEM_ARR[j][1] + "\" ><br>";
					strHtml  += "<input type=\"text\" class=\"component-input\" style=\"width: 90%;\" name=\"P_ESTM_DTL_ITEM_SCR\" placeholder=\"실제값\" value=\"" + ESTM_DTL_ITEM_ARR[j][2] + "\" ><br>";

					if(j == colspanCnt-1) {
						if(i == rowCnt-1) {
							strHtml  += "<button type=\"button\" class=\"component-button-s type-line\" style=\"width: 30px;\" name=\"regBtn\">>></button>";
						}
					}
					
					if(rowCnt > i+1) {
						if(j == colspanCnt-1) {
						}else {
							strHtml  += "<button type=\"button\" class=\"component-button-s type-line\" style=\"width: 30px;\" name=\"absBtn\">+</button>";
						}
					}
						
					if(j == colspanCnt-1) {
						if(i == 0){
							strHtml  += "<button type=\"button\" class=\"component-button-s type-line\" style=\"width: 30px;\" name=\"regUpBtn\">^</button>";
						}
						if(i == rowCnt-1) {
							strHtml  += "<button type=\"button\" class=\"component-button-s type-line\" style=\"width: 30px;\" name=\"deleteBtn\">X</button>";	
						}
					}
					
					
					strHtml  += "</th>";	
				}
			}else if(colspanCnt < 1){
				strHtml  += "<th>";
				strHtml  += "<input type=\"hidden\" class=\"component-input\" name=\"P_ESTM_DTL_ITEM_NO\">";
				strHtml  += "<input type=\"text\" class=\"component-input\" style=\"width: 90%;\" name=\"P_ESTM_DTL_ITEM_NM\" placeholder=\"항목명\" ><br>";
				strHtml  += "<input type=\"text\" class=\"component-input\" style=\"width: 90%;\" name=\"P_ESTM_DTL_ITEM_SCR\" placeholder=\"실제값\"><br>";
				strHtml  += "<button type=\"button\" class=\"component-button-s type-line\" style=\"width: 30px;\" name=\"regUpBtn\">^</button>";
				strHtml  += "<button type=\"button\" class=\"component-button-s type-line\" style=\"width: 30px;\" name=\"absBtn\">+</button>";
				strHtml  += "<button type=\"button\" class=\"component-button-s type-line\" style=\"width: 30px;\" name=\"regBtn\">>></button>";
				strHtml  += "<button type=\"button\" class=\"component-button-s type-line\" style=\"width: 30px;\" name=\"deleteBtn\">X</button>";
				strHtml  += "</th>";	
			}
			
			
			
			strHtml  += "</tr>";
		
		
		}
		strHtml  += "</thead>";
//		strHtml  += "<tbody id=\"copyArea\">";
//
//		var itemLine = 0;
//		for(var n = 0; n < sortX; n++) {
//			strHtml  += "<tr>";
//
//			/**
//			 *  해당 줄에서 itemNo값이 처음인지 여부를 체크해야되기 떄문에
//			 *  colFirstYn 값을 생성
//			 *  
//			 *  이유 : 좌표에 의해서 표를 그리고 있기 때문에 이전row에서 rowspan이 되었는지
//			 *  	여부를 판단할 수 없으므로 colFirstYn값을 만들어서 체크한다.
//			 */
//			var colFirstYn = "N";
//			for(var m = 0; m < colspanCnt; m++) {
//				
//				/**
//				 * col 위치 찾기
//				 */
//				var itemNo = "";
//				var myItemX = "";
//				var myItemY = "";
//				
//				
//				/**
//				 * sortMap에서 세팅해놓은 x, y 좌표를 이용해서
//				 * 다중중첩반복문을 돌고 있는 n값과 m값이 sortMap x, y 와 같은 경우
//				 * itemNo = sortMap[q][0];
//				 * myItemX = sortMap[q][1];
//				 * myItemY = sortMap[q][2];
//				 * colFirstYn = "Y";
//				 * 
//				 * 값을 받아오도록 세팅한다.
//				 */
//				for(var q = 0; q < sortMap.length; q++) {
//					if(sortMap[q][1] == n && sortMap[q][2] == m) {
//						itemNo = sortMap[q][0];
//						myItemX = sortMap[q][1];
//						myItemY = sortMap[q][2];
//						colFirstYn = "Y";
//					}
//				}
//				
//				/**
//				 * 좌표를 통해 찾은 sortMap의 itemNo값을 가지고
//				 * ESTM_DTL_ITEM_ARR[][0]과 비교하여
//				 * 표안에 들어갈 ESTM_DTL_ITEM_ARR[itemLine][] 값을 찾아낸다.
//				 */
//				for(var t = 0; t < ESTM_DTL_ITEM_ARR.length; t++) {
//					if(ESTM_DTL_ITEM_ARR[t][0] == itemNo) {
//						itemLine = t;
//					}
//				}
//				
//				/**
//				 * itemNo값을 찾고 (itemNo값의 길이/2)-1을 계산해서
//				 * 나의 col위치를 찾는다.
//				 */
//				var myColCnt = ESTM_DTL_ITEM_ARR[itemLine][0];
//				
//				myColCnt = Number(myColCnt.length/2)-1;
//				
//				/**
//				 * rowspan 합치기 작업
//				 * 
//				 */
//				
//				
//				var myItemNo = ESTM_DTL_ITEM_ARR[itemLine][0];
//				var myItemLen = ESTM_DTL_ITEM_ARR[itemLine][0].length;
//				
//				var myItemXmax = myItemX;
//				for(var k = 0; k < ESTM_DTL_ITEM_ARR.length; k++) {
//					var itemNoStr = ESTM_DTL_ITEM_ARR[k][0];
//					itemNoStr = itemNoStr.substring(0, myItemLen);
//					if(myItemNo == itemNoStr ) {
//						if(Number(myItemNo) < Number(ESTM_DTL_ITEM_ARR[k][0])){
//							for(var r = 0; r < sortMap.length; r++) {
//								if(sortMap[r][0] == ESTM_DTL_ITEM_ARR[k][0]) {
//									myItemXmax = sortMap[r][1];
//								}
//							}
//						}
//					}
//					
//				}
//				
//				
//				
//				var myRowCnt = Number(myItemXmax) - Number(myItemX) + 1;
//				
//				if(colFirstYn == "Y") {
//					
//					if(itemNo == "") {
//						console.log("itemNo ==");
//						strHtml  += "<td>";
//						strHtml  += "</td>";	
//					}
//					else if(rowCnt == 1 && ESTM_DTL_ITEM_ARR[itemLine][0].substring(myItemLen-2,myItemLen) == "01") {
//						strHtml  += "<td>";
//						
//						if(ESTM_DTL_ITEM_ARR[itemLine][0] != "" && ESTM_DTL_ITEM_ARR[itemLine][0] != null) 
//						{
//							strHtml  += "<input type=\"hidden\" class=\"component-input\"  name=\"P_ESTM_DTL_ITEM_NO\" value=\"" + ESTM_DTL_ITEM_ARR[itemLine][0] + "\">";
//						}else  
//						{
//							strHtml  += "<input type=\"hidden\" class=\"component-input\"  name=\"P_ESTM_DTL_ITEM_NO\">";
//						}
//						if(ESTM_DTL_ITEM_ARR[itemLine][1] != "" && ESTM_DTL_ITEM_ARR[itemLine][1] != null)
//						{
//							strHtml  += "<input type=\"text\" class=\"component-input type-full\" name=\"P_ESTM_DTL_ITEM_NM\" placeholder=\"평가항목명\" value=\"" + ESTM_DTL_ITEM_ARR[itemLine][1] + "\">";
//						}else 
//						{
//							strHtml  += "<input type=\"text\" class=\"component-input type-full\" name=\"P_ESTM_DTL_ITEM_NM\" placeholder=\"평가항목명\">";
//						}
//						if(ESTM_DTL_ITEM_ARR[itemLine][2] != "" && ESTM_DTL_ITEM_ARR[itemLine][2] != null)
//						{
//							strHtml  += "<textarea class=\"component-textarea\" placeholder=\"평가항목내용\" name=\"P_ESTM_ITEM_DTL_CNTN\">"+ ESTM_DTL_ITEM_ARR[itemLine][2] +"</textarea>";
//						}else 
//						{
//							strHtml  += "<textarea class=\"component-textarea\" placeholder=\"평가항목내용\" name=\"P_ESTM_ITEM_DTL_CNTN\"></textarea>";
//						}
//						if(ESTM_DTL_ITEM_ARR[itemLine][3] != "" && ESTM_DTL_ITEM_ARR[itemLine][3] != null)
//						{
//							strHtml  += "<input type=\"text\" class=\"component-input\" style=\"width:100px;\" name=\"P_ESTM_DTL_ITEM_SCR\" placeholder=\"배점\" numeric value=\"" + ESTM_DTL_ITEM_ARR[itemLine][3] + "\">";
//						}else {
//							strHtml  += "<input type=\"text\" class=\"component-input\" style=\"width:100px;\" name=\"P_ESTM_DTL_ITEM_SCR\" placeholder=\"배점\" numeric >";
//						}
//						
//						strHtml += "&nbsp;&nbsp;";
//						
//						if(ESTM_DTL_ITEM_ARR[itemLine][4] != "" && ESTM_DTL_ITEM_ARR[itemLine][4] != null)
//						{
//							if(ESTM_DTL_ITEM_ARR[itemLine][4] == "A")
//							{
//								strHtml += "<select class=\"component-select type-division\" style=\"width:120px;\" name=\"P_ESTM_DTL_ITEM_SCR_SECD\"><option value=\"A\" selected>합산</option><option value=\"B\">최고점</option></select>";
//							}else if(ESTM_DTL_ITEM_ARR[itemLine][4] == "B")
//							{
//								strHtml += "<select class=\"component-select type-division\" style=\"width:120px;\" name=\"P_ESTM_DTL_ITEM_SCR_SECD\"><option value=\"A\">합산</option><option value=\"B\" selected>최고점</option></select>";	
//							}
//							
//						}else 
//						{
//							strHtml += "<select class=\"component-select type-division\" style=\"width:120px;\" name=\"P_ESTM_DTL_ITEM_SCR_SECD\"><option value=\"A\">합산</option><option value=\"B\">최고점</option></select>";
//						}
//						strHtml += "&nbsp;&nbsp;";
//						if(itemNoMax.substring(0, (2*m+2)) == ESTM_DTL_ITEM_ARR[itemLine][0].substring(0, (2*m+2))) {
//							strHtml  += "<button type=\"button\" class=\"component-button-s type-line\" name=\"regBtn\">+</button>";
//						}else {
//							strHtml  += "<input type=\"hidden\" />";
//						}
//						if(itemNoMax ==  ESTM_DTL_ITEM_ARR[itemLine][0]) {
//							strHtml  += "<button type=\"button\" class=\"component-button-s type-line\" name=\"regChildBtn\">>></button>";
//						}else {
//							strHtml  += "<input type=\"hidden\" />";
//						}
//						strHtml += "</td>";
//					}
//					else {
//						console.log("else ");
//						strHtml  += "<td rowspan=\"" + myRowCnt + "\" >";
//						
//						if(ESTM_DTL_ITEM_ARR[itemLine][0] != "" && ESTM_DTL_ITEM_ARR[itemLine][0] != null)
//						{
//							strHtml  += "<input type=\"hidden\" class=\"component-input\" name=\"P_ESTM_DTL_ITEM_NO\" value=\"" + ESTM_DTL_ITEM_ARR[itemLine][0] + "\">";
//						}else 
//						{
//							strHtml  += "<input type=\"hidden\" class=\"component-input\" name=\"P_ESTM_DTL_ITEM_NO\">";
//						}
//						if(ESTM_DTL_ITEM_ARR[itemLine][1] != "" && ESTM_DTL_ITEM_ARR[itemLine][1] != null)
//						{
//							strHtml  += "<input type=\"text\" class=\"component-input type-full\" name=\"P_ESTM_DTL_ITEM_NM\" placeholder=\"평가항목명\" value=\"" + ESTM_DTL_ITEM_ARR[itemLine][1] + "\">";	
//						}else 
//						{
//							strHtml  += "<input type=\"text\" class=\"component-input type-full\" name=\"P_ESTM_DTL_ITEM_NM\" placeholder=\"평가항목명\" >";
//						}
//						if(ESTM_DTL_ITEM_ARR[itemLine][2] != "" && ESTM_DTL_ITEM_ARR[itemLine][2] != null)
//						{
//							strHtml  += "<textarea class=\"component-textarea\" placeholder=\"평가항목내용\" name=\"P_ESTM_ITEM_DTL_CNTN\">"+ ESTM_DTL_ITEM_ARR[itemLine][2] +"</textarea>";
//						}else
//						{
//							strHtml  += "<textarea class=\"component-textarea\" placeholder=\"평가항목내용\" name=\"P_ESTM_ITEM_DTL_CNTN\"></textarea>";
//						}
//						
//						if(ESTM_DTL_ITEM_ARR[itemLine][3] != "" && ESTM_DTL_ITEM_ARR[itemLine][3] != null)
//						{
//							strHtml  += "<input type=\"text\" class=\"component-input\" style=\"width:100px;\" name=\"P_ESTM_DTL_ITEM_SCR\" placeholder=\"배점\" numeric value=\"" + ESTM_DTL_ITEM_ARR[itemLine][3] + "\">";
//							
//						}else 
//						{
//							strHtml  += "<input type=\"text\" class=\"component-input\" style=\"width:100px;\" name=\"P_ESTM_DTL_ITEM_SCR\" placeholder=\"배점\" numeric \">";
//						}
//						strHtml += "&nbsp;&nbsp;";
//						
//						if(ESTM_DTL_ITEM_ARR[itemLine][4] != "" && ESTM_DTL_ITEM_ARR[itemLine][4] != null)
//						{
//							if(ESTM_DTL_ITEM_ARR[itemLine][4] == "A")
//							{
//								strHtml += "<select class=\"component-select type-division\" style=\"width:120px;\" name=\"P_ESTM_DTL_ITEM_SCR_SECD\"><option value=\"A\" selected>합산</option><option value=\"B\">최고점</option></select>";
//							}else if(ESTM_DTL_ITEM_ARR[itemLine][4] == "B")
//							{
//								strHtml += "<select class=\"component-select type-division\" style=\"width:120px;\" name=\"P_ESTM_DTL_ITEM_SCR_SECD\"><option value=\"A\">합산</option><option value=\"B\" selected>최고점</option></select>";	
//							}
//							
//						}else 
//						{
//							strHtml += "<select class=\"component-select type-division\" style=\"width:120px;\" name=\"P_ESTM_DTL_ITEM_SCR_SECD\"><option value=\"A\">합산</option><option value=\"B\">최고점</option></select>";
//						}
//						strHtml += "&nbsp;&nbsp;";
//						if(itemNoMax.substring(0, (2*m+2)) == ESTM_DTL_ITEM_ARR[itemLine][0].substring(0, (2*m+2))) {
//							strHtml  += "<button type=\"button\" class=\"component-button-s type-line\" name=\"regBtn\">+</button>";
//						}else {
//							strHtml  += "<input type=\"hidden\" />";
//						}
//						if(itemNoMax ==  ESTM_DTL_ITEM_ARR[itemLine][0]) {
//							strHtml  += "<button type=\"button\" class=\"component-button-s type-line\" name=\"regChildBtn\">>></button>";
//							strHtml  += "<button class=\"component-button-s type-line\" name=\"deleteBtn\" >삭제</button>";
//						}else {
//							strHtml  += "<input type=\"hidden\" />";
//						}
//						
//						strHtml += "</td>";
//					}
//				}else if(colFirstYn == "N") {
//					if(rowCnt == 1 && itemLine < colspanCnt) {
//						strHtml  += "<td>";
//						strHtml  += "</td>";
//					}
//				}
//			}
//			
//			strHtml  += "</tr>";
//		}
//		
//		strHtml  += "</tbody>";
		strHtml  += "</table>";
		
		
		$("#tableDiv").html(strHtml);
	}
	
	
	dataFn = function() {
		for(var i = 0; i < ESTM_DTL_ITEM_ARR.length; i++) {
			console.log( ESTM_DTL_ITEM_ARR[i][0] +  " : " + ESTM_DTL_ITEM_ARR[i][1] + " : " + ESTM_DTL_ITEM_ARR[i][2] + " : " + ESTM_DTL_ITEM_ARR[i][3] + " : " + ESTM_DTL_ITEM_ARR[i][4]);
		}
	};
	
	
	/**
	 * 1. 미리보기팝업호출
	 */
	reViewPopup = function() {
		$("#popupFrm").one("submit", function() {
			window.open("", "vendEvalReviewPopup", "width=740px,height=600px,toolbar=no,status=no,scrollbars=yes,resizeable=no,menubar=no,left="+x(740)+",top="+y(600));
			this.action = FwkCmmnUtil['contextPath']+'/vend/popup/vendEvalReviewPopup.do';
	        this.method = 'POST';
	        this.target = 'vendEvalReviewPopup';
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
			var ESTM_DTL_ITEM_NO = $(this).parent().find($("input[name='P_ESTM_DTL_ITEM_NO']")).val();
			var ITEM_ARR_LENGTH = ESTM_DTL_ITEM_ARR.length;
			
			
			$("input[name='P_ESTM_DTL_ITEM_NO']").each(function(i) {
				ESTM_DTL_ITEM_ARR[i][0] = $(this).val();
			});
			
			$("input[name='P_ESTM_DTL_ITEM_NM']").each(function(i) {
				ESTM_DTL_ITEM_ARR[i][1] = $(this).val();
			});
			
			$("textarea[name='P_ESTM_ITEM_DTL_CNTN']").each(function(i) {
				ESTM_DTL_ITEM_ARR[i][2] = $(this).val();
			});
			
			$("input[name='P_ESTM_DTL_ITEM_SCR']").each(function(i) {
				ESTM_DTL_ITEM_ARR[i][3] = $(this).val();
			});
			
			$("select[name='P_ESTM_DTL_ITEM_SCR_SECD']").each(function(i) {
				ESTM_DTL_ITEM_ARR[i][4] = $(this).val();
			});
			
			dataFn();
			return false;
		});
		
		/**
		 * 1. 목록버튼 클릭 이벤트
		 */
		$("#listBtn").on("click", function() {
			movePage("/vend/vendEvalStndList.do");
			return false;
		});
		
		$("#saveBtn").on("click", function() {
			
			$("#copyTrget").remove();
			
			var jsonData = $("#registFrm").serializeObject();
			var actionUrl = "/vend/vendEvalStndReg";
		
			FwkCmmnUtil.submitAjax (actionUrl, jsonData
			, function(res) {	
				alert("저장되었습니다.");
				$("#detailFrm input[name='ev_code1']").val(res.ev_code1);
				FwkCmmnUtil.submitForm("detailFrm", "/vend/vendEvalStndDetail.do");
			});
			return false;
		});
		
		//평가항목삭제
		$(document).on("click","#tableDiv button[name='deleteBtn']", function() {
			//평가항목번호 가져오기
			var ESTM_DTL_ITEM_NO = $(this).parent().find($("input[name='P_ESTM_DTL_ITEM_NO']")).val();
			
			//1. 삭제하기
			for(var i = 0; i < ESTM_DTL_ITEM_ARR.length; i++) 
			{
				if(ESTM_DTL_ITEM_NO == ESTM_DTL_ITEM_ARR[i][0].substring(0, ESTM_DTL_ITEM_NO.length))
				{
					ESTM_DTL_ITEM_ARR.splice(i,1);
				}
			}
			
			var ITEM_ARR_LENGTH = ESTM_DTL_ITEM_ARR.length-1;
			
			addItemHtml('deleteBtn',ITEM_ARR_LENGTH);
			return false;
		});
		
		//평가항목 열 추가(>>)
		$(document).on("click","#tableDiv button[name='regBtn']", function() {
			
			//평가항목번호 가져오기
			var ESTM_DTL_ITEM_NO = $(this).parent().find($("input[name='P_ESTM_DTL_ITEM_NO']")).val();
					
			var ITEM_ARR_LENGTH = ESTM_DTL_ITEM_ARR.length;
			
			$("input[name='P_ESTM_DTL_ITEM_NO']").each(function(i) {
				ESTM_DTL_ITEM_ARR[i][0] = $(this).val();
			});
			
			$("input[name='P_ESTM_DTL_ITEM_NM']").each(function(i) {
				ESTM_DTL_ITEM_ARR[i][1] = $(this).val();
			});
			
			$("textarea[name='P_ESTM_ITEM_DTL_CNTN']").each(function(i) {
				ESTM_DTL_ITEM_ARR[i][2] = $(this).val();
			});
			
			$("input[name='P_ESTM_DTL_ITEM_SCR']").each(function(i) {
				ESTM_DTL_ITEM_ARR[i][3] = $(this).val();
			});
			
			$("select[name='P_ESTM_DTL_ITEM_SCR_SECD']").each(function(i) {
				ESTM_DTL_ITEM_ARR[i][4] = $(this).val();
			});
			
			var maxNum = Number(ESTM_DTL_ITEM_NO.substring(ESTM_DTL_ITEM_NO.length-2, ESTM_DTL_ITEM_NO.length))+1;
			
			if(maxNum < 10) {
				maxNum = "0" + maxNum;
			}else if(maxNum == 100) {
				alert("레벨생성 갯수를 초과하였습니다.");
				return;
			}

			ESTM_DTL_ITEM_ARR[ITEM_ARR_LENGTH] = new Array(5);
			ESTM_DTL_ITEM_ARR[ITEM_ARR_LENGTH][0] = ESTM_DTL_ITEM_NO.substring(0, ESTM_DTL_ITEM_NO.length-2) + maxNum;
			
			console.log("ESTM_DTL_ITEM_ARR[ITEM_ARR_LENGTH][0] :: " + ESTM_DTL_ITEM_ARR[ITEM_ARR_LENGTH][0]);
			
			addItemHtml('regBtn',ITEM_ARR_LENGTH);
			
			
		});

		//평가항목 행 추가(^)
		$(document).on("click","#tableDiv button[name='regUpBtn']", function() {
			
			//평가항목번호 가져오기
			var ESTM_DTL_ITEM_NO = $(this).parent().find($("input[name='P_ESTM_DTL_ITEM_NO']")).val();
			var ITEM_ARR_LENGTH = ESTM_DTL_ITEM_ARR.length;
			
			$("input[name='P_ESTM_DTL_ITEM_NO']").each(function(i) {
				ESTM_DTL_ITEM_ARR[i][0] = $(this).val();
			});
			
			$("input[name='P_ESTM_DTL_ITEM_NM']").each(function(i) {
				ESTM_DTL_ITEM_ARR[i][1] = $(this).val();
			});
			
			$("textarea[name='P_ESTM_ITEM_DTL_CNTN']").each(function(i) {
				ESTM_DTL_ITEM_ARR[i][2] = $(this).val();
			});
			
			$("input[name='P_ESTM_DTL_ITEM_SCR']").each(function(i) {
				ESTM_DTL_ITEM_ARR[i][3] = $(this).val();
			});
			
			$("select[name='P_ESTM_DTL_ITEM_SCR_SECD']").each(function(i) {
				ESTM_DTL_ITEM_ARR[i][4] = $(this).val();
			});
			
			ESTM_DTL_ITEM_ARR[ITEM_ARR_LENGTH] = new Array(5);
			//ESTM_DTL_ITEM_NO
			//ESTM_DTL_ITEM_ARR[ITEM_ARR_LENGTH][0] = ESTM_DTL_ITEM_NO + "01";
			var maxNum = Number(ESTM_DTL_ITEM_NO.substring(0,ESTM_DTL_ITEM_NO.length-2))+1;
			
			if(maxNum < 10) {
				maxNum = "0" + maxNum;
			}else if(maxNum == 100) {
				alert("레벨생성 갯수를 초과하였습니다.");
				return;
			}

			ESTM_DTL_ITEM_ARR[ITEM_ARR_LENGTH] = new Array(5);
			ESTM_DTL_ITEM_ARR[ITEM_ARR_LENGTH][0] = maxNum + ESTM_DTL_ITEM_NO.substring(ESTM_DTL_ITEM_NO.length-2, ESTM_DTL_ITEM_NO.length);
			/**
			 * 위쪽으로 한줄(row)이 생기면서 이전줄의 형태를 똑같이 띄었으면 하기 때문에
			 * 이전줄의 데이터를 그대로 가져오기
			 * 즉, 우리는 위로 한줄을 생성하지만 표의 형태는 아랫줄데이터에서 가져오고
			 * 아랫줄에 들어가 있는 데이터는 삭제한다.
			 * 
			 * 1. ESTM_DTL_ITEM_ARR[i]번 라인에 있는 데이터를 복사하여 ESTM_DTL_ITEM_ARR[i+1]번 라인에 담는다.
			 */
			
			//console.log("ESTM_DTL_ITEM_ARR[ITEM_ARR_LENGTH][0] :: " + ESTM_DTL_ITEM_ARR[ITEM_ARR_LENGTH][0]);
			
			
			addItemHtml('regUpBtn',ITEM_ARR_LENGTH);
			
			
		});
		
	};

	/**
	 * window load
	 *
	 */
	$(function() {		
		pageObj.setEventHandler();
		
		$("#copyArea input[name='P_ESTM_DTL_ITEM_NO']").val(ESTM_DTL_ITEM_ARR[0][0]);
		
	});
	
})();