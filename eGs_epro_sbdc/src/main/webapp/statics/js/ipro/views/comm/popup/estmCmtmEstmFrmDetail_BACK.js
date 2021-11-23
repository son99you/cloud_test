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
	var ESTM_DTL_ITEM_ARR = new Array(1);
	ESTM_DTL_ITEM_ARR[0] = new Array(6);
	ESTM_DTL_ITEM_ARR[0][0] = "01";
	ESTM_DTL_ITEM_ARR[0][1] = "시장잠재력,제품선호,가격경쟁력";
	ESTM_DTL_ITEM_ARR[0][2] = "";
	ESTM_DTL_ITEM_ARR[0][3] = "";
	ESTM_DTL_ITEM_ARR[0][4] = "B";
	ESTM_DTL_ITEM_ARR[0][5] = "";
	ESTM_DTL_ITEM_ARR[1] = new Array(6);
	ESTM_DTL_ITEM_ARR[1][0] = "0101";
	ESTM_DTL_ITEM_ARR[1][1] = "매우우수";
	ESTM_DTL_ITEM_ARR[1][2] = "";
	ESTM_DTL_ITEM_ARR[1][3] = "30";
	ESTM_DTL_ITEM_ARR[1][4] = "B";
	ESTM_DTL_ITEM_ARR[1][5] = "";
	ESTM_DTL_ITEM_ARR[2] = new Array(6);
	ESTM_DTL_ITEM_ARR[2][0] = "0102";
	ESTM_DTL_ITEM_ARR[2][1] = "우수";
	ESTM_DTL_ITEM_ARR[2][2] = "";
	ESTM_DTL_ITEM_ARR[2][3] = "25";
	ESTM_DTL_ITEM_ARR[2][4] = "B";
	ESTM_DTL_ITEM_ARR[2][5] = "";
	ESTM_DTL_ITEM_ARR[3] = new Array(6);
	ESTM_DTL_ITEM_ARR[3][0] = "0103";
	ESTM_DTL_ITEM_ARR[3][1] = "보통";
	ESTM_DTL_ITEM_ARR[3][2] = "";
	ESTM_DTL_ITEM_ARR[3][3] = "20";
	ESTM_DTL_ITEM_ARR[3][4] = "B";
	ESTM_DTL_ITEM_ARR[3][5] = "";
	ESTM_DTL_ITEM_ARR[4] = new Array(6);
	ESTM_DTL_ITEM_ARR[4][0] = "0104";
	ESTM_DTL_ITEM_ARR[4][1] = "미흡";
	ESTM_DTL_ITEM_ARR[4][2] = "";
	ESTM_DTL_ITEM_ARR[4][3] = "15";
	ESTM_DTL_ITEM_ARR[4][4] = "B";
	ESTM_DTL_ITEM_ARR[4][5] = "";
	ESTM_DTL_ITEM_ARR[5] = new Array(6);
	ESTM_DTL_ITEM_ARR[5][0] = "0105";
	ESTM_DTL_ITEM_ARR[5][1] = "매우미흡";
	ESTM_DTL_ITEM_ARR[5][2] = "";
	ESTM_DTL_ITEM_ARR[5][3] = "10";
	ESTM_DTL_ITEM_ARR[5][4] = "B";
	ESTM_DTL_ITEM_ARR[5][5] = "10";
	
	ESTM_DTL_ITEM_ARR[6] = new Array(6);
	ESTM_DTL_ITEM_ARR[6][0] = "02";
	ESTM_DTL_ITEM_ARR[6][1] = "상품의기능, 특장점, 시장점유능력 등";
	ESTM_DTL_ITEM_ARR[6][2] = "";
	ESTM_DTL_ITEM_ARR[6][3] = "30";
	ESTM_DTL_ITEM_ARR[6][4] = "B";
	ESTM_DTL_ITEM_ARR[6][5] = "";
	ESTM_DTL_ITEM_ARR[7] = new Array(6);
	ESTM_DTL_ITEM_ARR[7][0] = "0201";
	ESTM_DTL_ITEM_ARR[7][1] = "매우우수";
	ESTM_DTL_ITEM_ARR[7][2] = "";
	ESTM_DTL_ITEM_ARR[7][3] = "30";
	ESTM_DTL_ITEM_ARR[7][4] = "B";
	ESTM_DTL_ITEM_ARR[7][5] = "";
	ESTM_DTL_ITEM_ARR[8] = new Array(6);
	ESTM_DTL_ITEM_ARR[8][0] = "0202";
	ESTM_DTL_ITEM_ARR[8][1] = "우수";
	ESTM_DTL_ITEM_ARR[8][2] = "";
	ESTM_DTL_ITEM_ARR[8][3] = "25";
	ESTM_DTL_ITEM_ARR[8][4] = "B";
	ESTM_DTL_ITEM_ARR[8][5] = "";
	ESTM_DTL_ITEM_ARR[9] = new Array(6);
	ESTM_DTL_ITEM_ARR[9][0] = "0203";
	ESTM_DTL_ITEM_ARR[9][1] = "보통";
	ESTM_DTL_ITEM_ARR[9][2] = "";
	ESTM_DTL_ITEM_ARR[9][3] = "20";
	ESTM_DTL_ITEM_ARR[9][4] = "B";
	ESTM_DTL_ITEM_ARR[9][5] = "";
	ESTM_DTL_ITEM_ARR[10] = new Array(6);
	ESTM_DTL_ITEM_ARR[10][0] = "0204";
	ESTM_DTL_ITEM_ARR[10][1] = "미흡";
	ESTM_DTL_ITEM_ARR[10][2] = "";
	ESTM_DTL_ITEM_ARR[10][3] = "15";
	ESTM_DTL_ITEM_ARR[10][4] = "B";
	ESTM_DTL_ITEM_ARR[10][5] = "";
	ESTM_DTL_ITEM_ARR[11] = new Array(6);
	ESTM_DTL_ITEM_ARR[11][0] = "0205";
	ESTM_DTL_ITEM_ARR[11][1] = "매우미흡";
	ESTM_DTL_ITEM_ARR[11][2] = "";
	ESTM_DTL_ITEM_ARR[11][3] = "10";
	ESTM_DTL_ITEM_ARR[11][4] = "B";
	ESTM_DTL_ITEM_ARR[11][5] = "10";
	ESTM_DTL_ITEM_ARR[12] = new Array(6);
	ESTM_DTL_ITEM_ARR[12][0] = "03";
	ESTM_DTL_ITEM_ARR[12][1] = "홈쇼핑 채널 적합성, 판매능력 등";
	ESTM_DTL_ITEM_ARR[12][2] = "";
	ESTM_DTL_ITEM_ARR[12][3] = "30";
	ESTM_DTL_ITEM_ARR[12][4] = "B";
	ESTM_DTL_ITEM_ARR[12][5] = "";
	ESTM_DTL_ITEM_ARR[13] = new Array(6);
	ESTM_DTL_ITEM_ARR[13][0] = "0301";
	ESTM_DTL_ITEM_ARR[13][1] = "매우우수";
	ESTM_DTL_ITEM_ARR[13][2] = "";
	ESTM_DTL_ITEM_ARR[13][3] = "30";
	ESTM_DTL_ITEM_ARR[13][4] = "B";
	ESTM_DTL_ITEM_ARR[13][5] = "";
	ESTM_DTL_ITEM_ARR[14] = new Array(6);
	ESTM_DTL_ITEM_ARR[14][0] = "0302";
	ESTM_DTL_ITEM_ARR[14][1] = "우수";
	ESTM_DTL_ITEM_ARR[14][2] = "";
	ESTM_DTL_ITEM_ARR[14][3] = "25";
	ESTM_DTL_ITEM_ARR[14][4] = "B";
	ESTM_DTL_ITEM_ARR[14][5] = "";
	ESTM_DTL_ITEM_ARR[15] = new Array(6);
	ESTM_DTL_ITEM_ARR[15][0] = "0303";
	ESTM_DTL_ITEM_ARR[15][1] = "보통";
	ESTM_DTL_ITEM_ARR[15][2] = "";
	ESTM_DTL_ITEM_ARR[15][3] = "20";
	ESTM_DTL_ITEM_ARR[15][4] = "B";
	ESTM_DTL_ITEM_ARR[15][5] = "";
	ESTM_DTL_ITEM_ARR[16] = new Array(6);
	ESTM_DTL_ITEM_ARR[16][0] = "0304";
	ESTM_DTL_ITEM_ARR[16][1] = "미흡";
	ESTM_DTL_ITEM_ARR[16][2] = "";
	ESTM_DTL_ITEM_ARR[16][3] = "15";
	ESTM_DTL_ITEM_ARR[16][4] = "B";
	ESTM_DTL_ITEM_ARR[16][5] = "";
	ESTM_DTL_ITEM_ARR[17] = new Array(6);
	ESTM_DTL_ITEM_ARR[17][0] = "0305";
	ESTM_DTL_ITEM_ARR[17][1] = "매우미흡";
	ESTM_DTL_ITEM_ARR[17][2] = "";
	ESTM_DTL_ITEM_ARR[17][3] = "10";
	ESTM_DTL_ITEM_ARR[17][4] = "B";
	ESTM_DTL_ITEM_ARR[17][5] = "10";

	
	
	
	

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
		strHtml  += "<col style=\"width: auto;\">";
		strHtml  += "<col style=\"width: 5%;\">";
		strHtml  += "<col style=\"width: 10%;\">";
		strHtml  += "</colgroup>";
		strHtml  += "<thead>";
		strHtml  += "<tr>";
		strHtml  += "<th class=\"txtc\" colspan=\""  + colspanCnt + "\">평가항목명</th>";
		strHtml  += "<th class=\"txtc\">평가항목내용</th>";
		strHtml  += "<th class=\"txtc\">배점</th>";
		strHtml  += "<th class=\"txtc\">점수</th>";
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
				
				var myItemXmax = myItemX;
				for(var k = 0; k < ESTM_DTL_ITEM_ARR.length; k++) {
					var itemNoStr = ESTM_DTL_ITEM_ARR[k][0];
					itemNoStr = itemNoStr.substring(0, myItemLen);
					if(myItemNo == itemNoStr ) {
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
						console.log("itemNo ==");
						strHtml  += "<td class=\"txt-center\">";
						strHtml  += "</td>";	
					}
					else if(rowCnt == 1 && ESTM_DTL_ITEM_ARR[itemLine][0].substring(myItemLen-2,myItemLen) == "01") {
						console.log("rowCnt == 1 && ESTM_DTL_ITEM_ARR[itemLine][0].substring(myItemLen-2,myItemLen) ==");
						strHtml  += "<td>";
						strHtml  += "<input type=\"hidden\" class=\"component-input\" name=\"P_ESTM_DTL_ITEM_NO\" value=\"" + ESTM_DTL_ITEM_ARR[itemLine][0] + "\">";
						strHtml  +=""+ ESTM_DTL_ITEM_ARR[itemLine][1] + "";
						strHtml  += "</td>";	
					}
					else {
						strHtml  += "<td rowspan=\"" + myRowCnt + "\" >";
						strHtml  += "<input type=\"hidden\" name=\"P_ESTM_DTL_ITEM_NO\" value=\"" + ESTM_DTL_ITEM_ARR[itemLine][0] + "\">";
						strHtml  += ""+ ESTM_DTL_ITEM_ARR[itemLine][1] + "";
						strHtml  += "</td>";
					}
				}else if(colFirstYn == "N") {
					if(rowCnt == 1 && itemLine < colspanCnt) {
						strHtml  += "<td>";
						strHtml  += "</td>";
					}
				}
				
			}
			if(ESTM_DTL_ITEM_ARR[itemLine][2] != null && ESTM_DTL_ITEM_ARR[itemLine][2] != "") {
				strHtml  += "<td><textarea class=\"component-textarea\" name=\"P_ESTM_ITEM_DTL_CNTN\">"+ ESTM_DTL_ITEM_ARR[itemLine][2] +"</textarea></td>";
			}else {
				strHtml  += "<td></td>";
			}
			strHtml  += "<td>" + ESTM_DTL_ITEM_ARR[itemLine][3] +"</td>";
			
			
			if(ESTM_DTL_ITEM_ARR[itemLine][4] == "A") // 합산
			{
				strHtml  += "<td class=\"txt-center\">";
				strHtml  += "<input type=\"text\" class=\"component-input\" numeric name=\"P_ESTM_DTL_ITEM_POINT\" >";
				strHtml  += "</td>";
			}else if (ESTM_DTL_ITEM_ARR[itemLine][4] == "B") // 최고점
			{
				strHtml  += "<td class=\"txt-center\">";
				strHtml  += "<input type=\"hidden\" class=\"component-input\" numeric name=\"P_ESTM_DTL_ITEM_POINT\" value=\"" + ESTM_DTL_ITEM_ARR[itemLine][5] + "\" >";
				var nameKey = ESTM_DTL_ITEM_ARR[itemLine][0].substring(0,myItemLen-2);	
				strHtml  += "<input type=\"hidden\" class=\"component-input\" numeric name=\"NAME_KEY\" value=\"" +nameKey+ "\" >";
				strHtml  += "<label class=\"component-radio\">";
					if(ESTM_DTL_ITEM_ARR[itemLine][5] == ESTM_DTL_ITEM_ARR[itemLine][3])
					{
						console.log(ESTM_DTL_ITEM_ARR[itemLine][5] + " : "+ ESTM_DTL_ITEM_ARR[itemLine][3]);
						strHtml  += "<input type=\"radio\" disabled=\"disabled\"  name=\"P_ESTM_DTL_ITEM_POINT" +nameKey+ "\" id=\"radioId\" value=\"" + ESTM_DTL_ITEM_ARR[itemLine][3] + "\" checked>";
					}else
					{
						console.log("else");
						strHtml  += "<input type=\"radio\" disabled=\"disabled\"  name=\"P_ESTM_DTL_ITEM_POINT" +nameKey+ "\" id=\"radioId\" value=\"" + ESTM_DTL_ITEM_ARR[itemLine][3] + "\" >";
					}
						strHtml  +="<i></i>";
				strHtml  += "</label>";
				strHtml  += "</td>";
				/*var nameKey = ESTM_DTL_ITEM_ARR[itemLine][0].substring(0,myItemLen-2);
				strHtml  += "<td class=\"txt-center\">";
				strHtml  += "<div class=\"rad_g\">";
					strHtml  += "<div class=\"radio_box\">";
						strHtml  += "<input type=\"radio\" readonly=\"readonly\" name=\"P_ESTM_DTL_ITEM_POINT" +nameKey+ "\" id=\"radioId" + ESTM_DTL_ITEM_ARR[itemLine][0] + "\" value=\"" + ESTM_DTL_ITEM_ARR[itemLine][3] + "\" checked=\"\">";
						strHtml  += "<label for=\"radioId" + ESTM_DTL_ITEM_ARR[itemLine][0] + "\" class=\"vam mr5\"></label>";
					strHtml  += "</div>";
				strHtml  += "</div>";
				strHtml  += "</td>";*/
			}
			
			strHtml  += "</tr>";
		}
		strHtml  += "</tbody>";
		strHtml  += "</table>";
		
		
		$("#tableDiv").html(strHtml);
	}
	
	
	pageObj.setEventHandler = function() {
		
		// 닫기버튼
		$("#colseBtn").on("click", function() {
			self.close();
			return false;
		});
		
		$("#reViewBtn").on("click", function() {
			var ITEM_ARR_LENGTH = ESTM_DTL_ITEM_ARR.length -1;
			
			addItemHtml('regBtn',ITEM_ARR_LENGTH);
			return false;
		});
		
		
		/**
		 * 1. 목록버튼 클릭 이벤트
		 */
		$("#updtBtn").on("click", function() {
			//movePage("/stym/estmFrmList.do");
			FwkCmmnUtil.submitForm("updtFrm" , "/sytm/estmFrmUpdtForm.do");
			return false;
		});
		
		
		/**
		 * 1. 목록버튼 클릭 이벤트
		 */
		$("#listBtn").on("click", function() {
			//movePage("/stym/estmFrmList.do");
			FwkCmmnUtil.submitForm("listFrm" , "/sytm/estmFrmList.do");
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
		
		$(document).on("click","button[name='deleteBtn']", function() {
			return false;
		});
		
		$(document).on("click","#tableDiv button[name='regBtn']", function() {
			
			//평가항목번호 가져오기
			var ESTM_DTL_ITEM_NO = $(this).next().next().val();
			
			var ITEM_ARR_LENGTH = ESTM_DTL_ITEM_ARR.length;
			
			var maxNum = Number(ESTM_DTL_ITEM_NO.substring(ESTM_DTL_ITEM_NO.length-2, ESTM_DTL_ITEM_NO.length))+1;
			
			if(maxNum < 10) {
				maxNum = "0" + maxNum;
			}else if(maxNum == 100) {
				alert("레벨생성 갯수를 초과하였습니다.");
				return;
			}


			ESTM_DTL_ITEM_ARR[ITEM_ARR_LENGTH] = new Array(5);
			ESTM_DTL_ITEM_ARR[ITEM_ARR_LENGTH][0] = ESTM_DTL_ITEM_NO.substring(0, ESTM_DTL_ITEM_NO.length-2) + maxNum;


			addItemHtml('regBtn',ITEM_ARR_LENGTH);
			
			
		});

		$(document).on("click","#tableDiv button[name='regChildBtn']", function() {
			
			//평가항목번호 가져오기
			var ESTM_DTL_ITEM_NO = $(this).next().val();
			var ITEM_ARR_LENGTH = ESTM_DTL_ITEM_ARR.length;
			

			ESTM_DTL_ITEM_ARR[ITEM_ARR_LENGTH] = new Array(5);
			ESTM_DTL_ITEM_ARR[ITEM_ARR_LENGTH][0] = ESTM_DTL_ITEM_NO + "01";
			
			addItemHtml('regChildBtn',ITEM_ARR_LENGTH);
			
			
		});
		
	};

	/**
	 * window load
	 *
	 */
	$(function() {		
		pageObj.setEventHandler();
		
		$("input[name='P_ESTM_DTL_ITEM_NO']").each(function(i) {
			ESTM_DTL_ITEM_ARR[i] = new Array(5);
			ESTM_DTL_ITEM_ARR[i][0] = $(this).val();
			console.log("0 ::" + ESTM_DTL_ITEM_ARR[i][0]);
		});
		
		$("input[name='P_ESTM_DTL_ITEM_NM']").each(function(i) {
			ESTM_DTL_ITEM_ARR[i][1] = $(this).val();
			console.log("1 ::" + ESTM_DTL_ITEM_ARR[i][1]);
		});
		
		$("textarea[name='P_ESTM_ITEM_DTL_CNTN']").each(function(i) {
			ESTM_DTL_ITEM_ARR[i][2] = $(this).val();
			console.log("2 ::" + ESTM_DTL_ITEM_ARR[i][2]);
		});
		
		$("input[name='P_ESTM_DTL_ITEM_SCR']").each(function(i) {
			ESTM_DTL_ITEM_ARR[i][3] = $(this).val();
			console.log("3 ::" + ESTM_DTL_ITEM_ARR[i][3]);
		});
		
		$("select[name='P_ESTM_DTL_ITEM_SCR_SECD']").each(function(i) {
			ESTM_DTL_ITEM_ARR[i][4] = $(this).val();
			console.log("4 ::" + ESTM_DTL_ITEM_ARR[i][4]);
		});
		
		$("input[name='P_ESTM_DTL_ITEM_POINT']").each(function(i) {
			ESTM_DTL_ITEM_ARR[i][5] = $(this).val();
			console.log("5 ::" + ESTM_DTL_ITEM_ARR[i][5]);
		});

		var ITEM_ARR_LENGTH = ESTM_DTL_ITEM_ARR.length -1;
		
		addItemHtml('regBtn',ITEM_ARR_LENGTH);
		
		var total = 0;
		for(var i = 0; i < ESTM_DTL_ITEM_ARR.length; i++) {
			total = Number(total) + Number(ESTM_DTL_ITEM_ARR[i][5]);
		}
		$("input[name='P_TOT_SCR']").val(total);
		
		
	});
	
})();