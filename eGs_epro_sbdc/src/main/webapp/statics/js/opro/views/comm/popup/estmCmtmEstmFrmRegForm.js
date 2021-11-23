(function() {

	/**
	 * Default Constructor
	 */
	pageObj = {};
	
	
	// 2차원 배열 생성 함수
	//평가항목 arrayList 초기세팅
	var ESTM_DTL_ITEM_ARR = new Array(1);
//	for(var i = 0; i < 1; i++) {
//		ESTM_DTL_ITEM_ARR[i] = new Array(5);
//	}

//	ESTM_DTL_ITEM_ARR[0][0] = "01";
	

	/**
	 * 미리보기 테스트 세팅
	 *  ESTM_DTL_ITEM_ARR[0][0] = "01"; // 평가항목번호
	 *	ESTM_DTL_ITEM_ARR[0][1] = "시장잠재력,제품선호,가격경쟁력"; //평가항목명
	 *	ESTM_DTL_ITEM_ARR[0][2] = ""; //평가항목내용
	 *	ESTM_DTL_ITEM_ARR[0][3] = ""; //배점
	 *	ESTM_DTL_ITEM_ARR[0][4] = "B"; //라디오냐 인풋이냐
	 *	ESTM_DTL_ITEM_ARR[0][5] = "30"; //체크 또는 입력 점수
	 * 
	 */
	
	movePage = function(url) {
		FwkCmmnUtil.submitForm("listFrm", url);  
	};
	
	
	addItemHtml = function(btnGbn, itemArrLength) {
		var ITEM_ARR_LENGTH = itemArrLength;
		var itemNoMax = ESTM_DTL_ITEM_ARR[ITEM_ARR_LENGTH][0];
		
		/**
		 * 최저레벨 댑스를 찾아서 colspan 갯수를 결정한다.
		 */
		var maxStep = ESTM_DTL_ITEM_ARR[0][0].length;
		for(var i = 0; i < ESTM_DTL_ITEM_ARR.length; i++ ){
			if(maxStep < ESTM_DTL_ITEM_ARR[i][0].length) {
				maxStep = ESTM_DTL_ITEM_ARR[i][0].length;
			}
		}
		
		
		/**
		 * 평가항목표 rowCnt 
		 */
		var rowCnt = 1;

		/**
		 * 다중 반복문을 통해서 최대 row값을 찾는다. 
		 */
		for(var k = 0; k < ESTM_DTL_ITEM_ARR.length; k++) {
			var itemNoStr_K = ESTM_DTL_ITEM_ARR[k][0];
			var itemNoLen_K = ESTM_DTL_ITEM_ARR[k][0].length;
			
			/**
			 *  ESTM_DTL_ITEM_ARR[k][0]의 itemNo값 만큼 자릿수를 잘라낸다.
			 *  ex) ESTM_DTL_ITEM_ARR[k][0] ==> 01 이면 01
			 *  	ESTM_DTL_ITEM_ARR[k][0] ==> 0201 이면 0201
			 */
			itemNoStr_K = itemNoStr_K.substring(0, itemNoLen_K);
			
			var rowCntYn=0;
			for(var l = 0; l < ESTM_DTL_ITEM_ARR.length; l++) {
				var itemNoStr_L = ESTM_DTL_ITEM_ARR[l][0];
				/**
				 * ESTM_DTL_ITEM_ARR[l][0]값을 ESTM_DTL_ITEM_ARR[k][0]의 자릿수 만큼 잘라낸다.
				 * ex)	ESTM_DTL_ITEM_ARR[l][0] ==> 0202 이고 ESTM_DTL_ITEM_ARR[k][0] ==> 01 이면 결과는 ESTM_DTL_ITEM_ARR[l][0].substring(0,2) ==> 02
				 *  	ESTM_DTL_ITEM_ARR[l][0] ==> 0201 이고 ESTM_DTL_ITEM_ARR[k][0] ==> 0101 이면 결과는 ESTM_DTL_ITEM_ARR[l][0].substring(0,4) ==> 0201
				 * 
				 * 위와 같은 방법으로 ESTM_DTL_ITEM_ARR[][0]에 들어있는 값들을 확인하여
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
				strHtml  += "<col style=\"width: \"" + 40/colspanCnt + "\"%;\">";
			}
		}else if(colspanCnt < 1){
			strHtml  += "<col style=\"width: 40%;\">";
		}
		strHtml  += "<col style=\"width: 5%;\">";
		strHtml  += "<col style=\"width: 10%;\">";
		strHtml  += "<col style=\"width: auto;\">";
		
		strHtml  += "</colgroup>";
		strHtml  += "<thead>";
		strHtml  += "<tr>";
		strHtml  += "<th class=\"txtc\" colspan=\""  + colspanCnt + "\">평가항목명</th>";
		strHtml  += "<th class=\"txtc\">배점</th>";
		strHtml  += "<th class=\"txtc\">점수</th>";
		strHtml  += "<th class=\"txtc\">평가항목내용</th>";
		
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
				 * ESTM_DTL_ITEM_ARR[][0]과 비교하여
				 * 표안에 들어갈 ESTM_DTL_ITEM_ARR[itemLine][] 값을 찾아낸다.
				 */
				for(var t = 0; t < ESTM_DTL_ITEM_ARR.length; t++) {
					if(ESTM_DTL_ITEM_ARR[t][0] == itemNo) {
						itemLine = t;
					}
				}
				
				/**
				 * itemNo값을 찾고 (itemNo값의 길이/2)-1을 계산해서
				 * 나의 col위치를 찾는다.
				 */
				var myColCnt = ESTM_DTL_ITEM_ARR[itemLine][0];
				
				myColCnt = Number(myColCnt.length/2)-1;
				
				/**
				 * rowspan 합치기 작업
				 * 
				 */
				
				
				var myItemNo = ESTM_DTL_ITEM_ARR[itemLine][0];
				var myItemLen = ESTM_DTL_ITEM_ARR[itemLine][0].length;
				var myMaxStepAt = 0; // 1이상이면 자리 아래스텝이 존재한다고 생각하면됨.
				var myItemXmax = myItemX;
				for(var k = 0; k < ESTM_DTL_ITEM_ARR.length; k++) {
					var itemNoStr = ESTM_DTL_ITEM_ARR[k][0];
					itemNoStr = itemNoStr.substring(0, myItemLen);
					if(myItemNo == itemNoStr ) {
						myMaxStepAt++;
						if(Number(myItemNo) < Number(ESTM_DTL_ITEM_ARR[k][0])){
							for(var r = 0; r < sortMap.length; r++) {
								if(sortMap[r][0] == ESTM_DTL_ITEM_ARR[k][0]) {
									myItemXmax = sortMap[r][1];
								}
							}
						}
					}
					
				}
				
				
				
				var myRowCnt = Number(myItemXmax) - Number(myItemX) + 1;
				
				if(colFirstYn == "Y") {
					
					if(itemNo == "") {
						strHtml  += "<td class=\"txt-center\">";
						strHtml  += "</td>";	
					}
					else if(rowCnt == 1 && ESTM_DTL_ITEM_ARR[itemLine][0].substring(myItemLen-2,myItemLen) == "01") {
						strHtml  += "<td>";
						strHtml  += "<input type=\"hidden\" class=\"component-input\" name=\"P_ESTM_DTL_ITEM_NO\" value=\"" + ESTM_DTL_ITEM_ARR[itemLine][0] + "\">";
						// DB에 insert되는 점수
						strHtml  += "<input type=\"hidden\" class=\"component-input\" name=\"P_ESTM_DTL_ITEM_POINT\" value=\"" + ESTM_DTL_ITEM_ARR[itemLine][5] + "\">";
						if(ESTM_DTL_ITEM_ARR[itemLine][4] == "A") // 합산 : 평가항목
						{
							if(myMaxStepAt > 1) {
								strHtml  +=""+ ESTM_DTL_ITEM_ARR[itemLine][1] + " (" + ESTM_DTL_ITEM_ARR[itemLine][3] + ") ";
								strHtml  += "<input type=\"text\" readonly=\"readonly\" style=\"width:50px; float:right;\" class=\"component-input\" numeric name=\"ESTM_DTL_ITEM_POINT" +ESTM_DTL_ITEM_ARR[itemLine][0]+ "\"  value=\"" + ESTM_DTL_ITEM_ARR[itemLine][5] + "\" >";
							}else {
								strHtml  += ""+ ESTM_DTL_ITEM_ARR[itemLine][1] + "";
							}
						}
						else if (ESTM_DTL_ITEM_ARR[itemLine][4] == "B") // 최고점 : 평가기준
						{
							strHtml  +=""+ ESTM_DTL_ITEM_ARR[itemLine][1] + "";
						}
						
						strHtml  += "</td>";	
					}
					else {
						strHtml  += "<td rowspan=\"" + myRowCnt + "\" >";
						strHtml  += "<input type=\"hidden\" name=\"P_ESTM_DTL_ITEM_NO\" value=\"" + ESTM_DTL_ITEM_ARR[itemLine][0] + "\">";
						// DB에 insert되는 점수
						strHtml  += "<input type=\"hidden\" class=\"component-input\" name=\"P_ESTM_DTL_ITEM_POINT\" value=\"" + ESTM_DTL_ITEM_ARR[itemLine][5] + "\">";
						if(ESTM_DTL_ITEM_ARR[itemLine][4] == "A") // 합산 : 평가항목
						{
							if(myMaxStepAt > 1) {
								strHtml  += ""+ ESTM_DTL_ITEM_ARR[itemLine][1] + " (" + ESTM_DTL_ITEM_ARR[itemLine][3] + ") ";
								strHtml  += "<input type=\"text\"  readonly=\"readonly\"  style=\"width:50px; float:right;\" class=\"component-input\" numeric name=\"ESTM_DTL_ITEM_POINT" +ESTM_DTL_ITEM_ARR[itemLine][0]+ "\" value=\"" + ESTM_DTL_ITEM_ARR[itemLine][5] + "\" >";
								/*strHtml  += "<input type=\"hidden\" class=\"component-input\" name=\"POINT_ESTM_DTL_ITEM_NO\" value=\"" + ESTM_DTL_ITEM_ARR[itemLine][0] + "\">";*/
							}else {
								strHtml  += ""+ ESTM_DTL_ITEM_ARR[itemLine][1] + "";
							}
						}
						else if (ESTM_DTL_ITEM_ARR[itemLine][4] == "B") // 최고점 : 평가기준
						{
							strHtml  +=""+ ESTM_DTL_ITEM_ARR[itemLine][1] + ""; 
						}
						
						strHtml  += "</td>";
					}
				}else if(colFirstYn == "N") {
					if(rowCnt == 1 && itemLine < colspanCnt) {
						strHtml  += "<td>";
						strHtml  += "</td>";
					}
				}
				
			}
			//배점
			strHtml  += "<td class=\"txt-center\">" + ESTM_DTL_ITEM_ARR[itemLine][3] +"</td>";
			
			// 점수
			strHtml  += "<td class=\"txt-center\">";
			
			if(ESTM_DTL_ITEM_ARR[itemLine][4] == "A") // 합산 : 평가항목
			{
				strHtml  += "<input type=\"text\" style=\"width:50px;\" class=\"component-input\" numeric name=\"ESTM_DTL_ITEM_POINT" +ESTM_DTL_ITEM_ARR[itemLine][0]+ "\"  value=\"" + ESTM_DTL_ITEM_ARR[itemLine][5] + "\" >";
			}else if (ESTM_DTL_ITEM_ARR[itemLine][4] == "B") // 최고점 : 평가기준
			{
				var nameKey = ESTM_DTL_ITEM_ARR[itemLine][0].substring(0,myItemLen-2);
				
				strHtml  += "<label class=\"component-radio\">";
				if(ESTM_DTL_ITEM_ARR[itemLine][3] == ESTM_DTL_ITEM_ARR[itemLine][5]){
					strHtml  += "<input type=\"radio\" name=\"ESTM_DTL_ITEM_POINT_" +nameKey+ "\" id=\"radioId\" value=\"" + ESTM_DTL_ITEM_ARR[itemLine][3] + "\"  checked=\"checked\">";
				}else{
					strHtml  += "<input type=\"radio\" name=\"ESTM_DTL_ITEM_POINT_" +nameKey+ "\" id=\"radioId\" value=\"" + ESTM_DTL_ITEM_ARR[itemLine][3] + "\" >";
				}
				
				strHtml  +="<i></i>";
				strHtml  += "</label>";
			}
			// 점수 입력시 평가항목번호 찾기 위한 값 (필수)
			strHtml  += "<input type=\"hidden\" class=\"component-input\" name=\"POINT_ESTM_DTL_ITEM_NO\" value=\"" + ESTM_DTL_ITEM_ARR[itemLine][0] + "\">";
			// 점수 입력시 평가항목/평가기준을 찾기 위한 값 (필수)			
			strHtml  += "<input type=\"hidden\" class=\"component-input\" name=\"POINT_ESTM_MTHD_SECD\" value=\"" + ESTM_DTL_ITEM_ARR[itemLine][4] + "\">";
			// 점수 입력시 배점 비교를 위한 값
			strHtml  += "<input type=\"hidden\" class=\"component-input\" name=\"ESTM_ITEM_DSMK\" value=\"" + ESTM_DTL_ITEM_ARR[itemLine][3] + "\">";
			strHtml  += "</td>";
			
			//평가항목내용
			if(ESTM_DTL_ITEM_ARR[itemLine][2] != null && ESTM_DTL_ITEM_ARR[itemLine][2] != "") {
				strHtml  += "<td>" +ESTM_DTL_ITEM_ARR[itemLine][2]+ "</td>";
				//<textarea class=\"component-textarea\" name=\"P_ESTM_ITEM_DTL_CNTN\">"+ ESTM_DTL_ITEM_ARR[itemLine][2] +"</textarea>
			}else {
				strHtml  += "<td></td>";
			}
			
			strHtml  += "</tr>";
		}
		strHtml  += "</tbody>";
		strHtml  += "</table>";
		
		
		$("#tableDiv").html(strHtml);
		
	}
	
	pageObj.registFrmInqire = function() {
		if(!confirm("저장 하시겠습니까?")){
			return false;
		}else {
			fileUploadStart();
		}
	};
	
	pageObj.pageMove = function(gbn) {
		if(gbn == "NEXT") {
			$("#P_ESTM_OBJ_SEQ").val($("#NEXT_ESTM_OBJ_SEQ").val());
		}else if(gbn == "PREV") {
			$("#P_ESTM_OBJ_SEQ").val($("#PREV_ESTM_OBJ_SEQ").val());
		}
		
		var urlStr = FwkCmmnUtil['contextPath']+'/opro/comm/popup/estmCmtmEstmFrmRegForm.do';
		FwkCmmnUtil.submitForm("registFrm", urlStr);
		
	};
	
	// 파일 업로드
	fileUploadStart = function() {
		/*// 첨부파일이 존재하는 경우만 업로드
		if (RAONKUPLOAD.GetTotalFileCount("uploadView1") == 0) {
			FwkCmmnUtil.submitForm("registFrm", "/comm/popup/estmCmtmEstmFrmSave.do");
        } else {
            //업로드에서 추가된 파일이 정상으로 넘어오면 파일전송 이벤트를 발생시킵니다.
        	RAONKUPLOAD.Transfer("uploadView1");
        }*/
		RAONKUPLOAD.Transfer("uploadView1");
	};
	
	pageObj.setEventHandler = function() {
		
		$(document).on("blur","input[name*='ESTM_DTL_ITEM_POINT']", function() {
			var pointItemNo = $(this).next().val();		//평가항목번호
			var pointVal = $(this).val();	//점수
			var pointMthdSecd = $(this).next().next().val(); //해당 input의 평가구분코드
			var pointDsmkVal = $(this).next().next().next().val();	//해당input의 배점
			var flag = true;
			
			// 항목번호
			$("input[name='P_ESTM_DTL_ITEM_NO']").each(function(i) {
				if($(this).val() == pointItemNo) {
					if(pointDsmkVal >= Number(pointVal)){
						$(this).next().val(pointVal);
					}else{
						flag = false;
						$(this).next().val(0);
						alert("배점보다 높은 점수는 입력하실수 없습니다.");
					}
				}
			});
			
			if(!flag){		//배점이 높다면
				$(this).val("");
				$(this).focus();
			}
			// 평가구분이 B이면 자신과 같은 항렬들의 점수를 제거 필수!
			
			// 자신의 pointItemNo length-2 해서 나온 값을 기준으로 부모번호의 점수를 세팅하기
			var pointItemNoLenght = pointItemNo.length;			
			for(var i = 1; i < pointItemNoLenght/2; i++) {			
				var len = pointItemNo.length-(i*2);						
				var pointItemNoL = pointItemNo.substring(0,len);
				var pointSum = 0;
				
				// 항목번호
				$("input[name='P_ESTM_DTL_ITEM_NO']").each(function(i) {
					var myLen = $(this).val().length;
				//	console.log("myLen :: " + myLen + " len ::" + len);
					if(len < myLen) { // 자기자신보다 작은 자식 평가항목을 찾기위한 로직
						if(pointItemNoL == $(this).val().substring(0,len) && Number(len+2) == myLen ) {
							pointSum += Number($(this).next().val());
						}
					}
				});
				
				// 합산한 값을 바로 위에 부모에게 넣어주기
				$("input[name='P_ESTM_DTL_ITEM_NO']").each(function(i) {
					//var myLen = $(this).val().length;
					if(pointItemNoL == $(this).val()) {
						$(this).next().val(pointSum);
					}
				});
				
				if(pointMthdSecd == "A") {
					$("input[name='ESTM_DTL_ITEM_POINT"+ pointItemNoL +"']").val(pointSum);
				}else{
					
				}
				
			}
			
			//합계
			var totPoint = 0;
			$("input[name='P_ESTM_DTL_ITEM_NO']").each(function(i) {
				var myLen = $(this).val().length;
				if(myLen == 2) { // 자기자신보다 작은 자식 평가항목을 찾기위한 로직
					totPoint += Number($(this).next().val());
				}
			});
			
			$("input[name='P_TOT_SCR']").val(totPoint);
		});

		
		$(document).on("click","input[type='radio']","input[type='ESTM_DTL_ITEM_POINT']", function() {
			var pointItemNo = $(this).parent().next().val();		//평가항목번호
			var pointVal = $(this).val();	//점수
			var pointMthdSecd = $(this).next().next().val(); //해당 input의 평가구분코드

			// 항목번호
			$("input[name='P_ESTM_DTL_ITEM_NO']").each(function(i) {
				if($(this).val() == pointItemNo) {
					$(this).next().val(pointVal);
				}else if($(this).val().substring(0,$(this).val().length-2) == pointItemNo.substring(0, pointItemNo.length-2) ){			//해당 기준만 점수 남기고 나머지는 0으로 바꿔주기
					$(this).next().val(0);
				}
			});
			
			// 평가구분이 B이면 자신과 같은 항렬들의 점수를 제거 필수!
			
			// 자신의 pointItemNo length-2 해서 나온 값을 기준으로 부모번호의 점수를 세팅하기
			var pointItemNoLenght = pointItemNo.length;			
			for(var i = 1; i < pointItemNoLenght/2; i++) {			
				var len = pointItemNo.length-(i*2);						
				var pointItemNoL = pointItemNo.substring(0,len);
				var pointSum = 0;
				
				// 항목번호
				$("input[name='P_ESTM_DTL_ITEM_NO']").each(function(i) {
					var myLen = $(this).val().length;
					//	console.log("myLen :: " + myLen + " len ::" + len);
					if(len < myLen) { // 자기자신보다 작은 자식 평가항목을 찾기위한 로직
						if(pointItemNoL == $(this).val().substring(0,len) && Number(len+2) == myLen ) {
							pointSum += Number($(this).next().val());
						}
					}
				});
				
				// 합산한 값을 바로 위에 부모에게 넣어주기
				$("input[name='P_ESTM_DTL_ITEM_NO']").each(function(i) {
					//var myLen = $(this).val().length;
					if(pointItemNoL == $(this).val()) {
						$(this).next().val(pointSum);
					}
				});
				
				$("input[name='ESTM_DTL_ITEM_POINT"+ pointItemNoL +"']").val(pointSum);
				
			}
			
			//합계
			var totPoint = 0;
			$("input[name='P_ESTM_DTL_ITEM_NO']").each(function(i) {
				var myLen = $(this).val().length;
				if(myLen == 2) { // 자기자신보다 작은 자식 평가항목을 찾기위한 로직
					totPoint += Number($(this).next().val());
				}
			});
			
			$("input[name='P_TOT_SCR']").val(totPoint);
		});
		
		// 닫기버튼
		$("#colseBtn").on("click", function() {
			if($("#reloadURL").val() != null || $("#reloadURL").val() != ""){
				window.opener.reloadDetail();
				self.close();
			}else{
				self.close();
				return false;
			}
		});
		
		$("#saveBtn").on("click", function() {
			pageObj.registFrmInqire();
		});
		
		$("#saveFalseBtn").on("click", function() {
			alert("평가위원대상이 아닙니다.");
			return false;
		});
		
		$("button[name='nextBtn']").on("click", function() {
			pageObj.pageMove("NEXT");
		});
		
		$("button[name='prevBtn']").on("click", function() {
			pageObj.pageMove("PREV");
		});
		
	};

	/**
	 * window load
	 *
	 */
	$(function() {		
		pageObj.setEventHandler();
		
		if($("#resultCode").val() == 'Success'){
			FwkCmmnUtil.submitForm("registFrm", "/opro/comm/popup/estmCmtmEstmFrmRegForm.do");
		}
		
		//fileUpload("registFrm", "P_FILE_GRP_NO", "/comm/popup/estmCmtmEstmFrmSave.do");
		fileModify("registFrm", "P_FILE_GRP_NO", "P_FILE_GRP_NO_NEW", "/opro/comm/popup/estmCmtmEstmFrmSave.do");
		
		// 항목번호
		$("input[name='D_ESTM_ITEM_NO']").each(function(i) {
			ESTM_DTL_ITEM_ARR[i] = new Array(5);
			ESTM_DTL_ITEM_ARR[i][0] = $(this).val();
			console.log("0 ::" + ESTM_DTL_ITEM_ARR[i][0]);
		});
		
		// 항목명
		$("input[name='D_ESTM_ITEM_NM']").each(function(i) {
			ESTM_DTL_ITEM_ARR[i][1] = $(this).val();
			console.log("1 ::" + ESTM_DTL_ITEM_ARR[i][1]);
		});
		
		// 내용
		$("input[name='D_ESTM_ITEM_DESC']").each(function(i) {
			ESTM_DTL_ITEM_ARR[i][2] = $(this).val();
			console.log("2 ::" + ESTM_DTL_ITEM_ARR[i][2]);
		});
		
		// 배점
		$("input[name='D_ESTM_ITEM_DSMK']").each(function(i) {
			ESTM_DTL_ITEM_ARR[i][3] = $(this).val();
			console.log("3 ::" + ESTM_DTL_ITEM_ARR[i][3]);
		});
		
		// 방법구분
		$("input[name='D_ESTM_MTHD_SECD']").each(function(i) {
			ESTM_DTL_ITEM_ARR[i][4] = $(this).val();
			console.log("4 ::" + ESTM_DTL_ITEM_ARR[i][4]);
		});
		
		// 평가위원입력점수
		$("input[name='D_ESTM_SCR']").each(function(i) {
			ESTM_DTL_ITEM_ARR[i][5] = $(this).val();
			console.log("5 ::" + ESTM_DTL_ITEM_ARR[i][5]);
		});
		
		var ITEM_ARR_LENGTH = ESTM_DTL_ITEM_ARR.length -1;
		
		addItemHtml('regBtn',ITEM_ARR_LENGTH);
		
		var total = 0;
		for(var i = 0; i < ESTM_DTL_ITEM_ARR.length; i++) {
			
			var iLength = ESTM_DTL_ITEM_ARR[i][0].length
			
			if(iLength == 2) {
				total = Number(total) + Number(ESTM_DTL_ITEM_ARR[i][5]);
			}
		}
		$("input[name='P_TOT_SCR']").val(total);
	});
	
})();