(function() {

	/**
	 * Default Constructor
	 */
	pageObj = {};
	
	var file = null;
	setScoreAvg = function(p_dist_score, obj){
	
	var scoreSum = 0;
	var scoreAvg = 0;
	var inputName = $(obj).attr("scoreS");
	
	var score = Number(obj.value);
	var dist_score = Number(p_dist_score);
	if(dist_score >= 0){
		if(dist_score < score){
			alert('배점 기준에 맞지 않습니다.');
			obj.value = 0;
			evalScoreSum('score2','tot_score');
			obj.focus();
		}
	}else if(dist_score < 0){
		if(dist_score > score){
			alert('배점 기준에 맞지 않습니다.');
			obj.value = 0;
			evalScoreSum('score2','tot_score');
			obj.focus();
		}
	}
	
	$("input[scoreS='"+inputName+"']").each(function(inx){
		var temp = $(this).val();
		if(temp == "") temp = 0;
		scoreSum += Number(temp);
	});
	
	var dist_score1 = $(obj).next().val();
	scoreAvg = Math.round(((Number(dist_score1)/100)*Number(scoreSum)) * 100) / 100;
	var num = inputName.substring(inputName.indexOf("_")+1);
	
	if($(obj).val().trim() == ""){
		$("[name='tr_"+num+"']").text("");
	}
	else { 
		$("[name='tr_"+num+"']").text(scoreAvg);
	}
	
}
	setScore = function(obj){
	$(obj).parent().next().next().children("input[type='text']").val($(obj).next().val());
	$(obj).parent().next().children("input[type='text']").val($(obj).next().val());	
	var scoreSum = 0;
	var scoreAvg = 0;
	var inputName = $(obj).parent().next().next().children(0).attr("scoreS");
	$("input[scoreS='"+inputName+"']").each(function(inx){
		var temp = $(this).val();
		if(temp == "") temp = 0;
		scoreSum += Number(temp);
	});
	
	var dist_score1 = $(obj).parent().next().next().children("input[type='hidden']").val();
	
	scoreAvg = Math.round(((Number(dist_score1)/100)*Number(scoreSum)) * 100) / 100;
	var num = inputName.substring(inputName.indexOf("_")+1);
	$("[name='tr_"+num+"']").text(scoreAvg);
	
	evalScoreSum('score2','tot_score');
}
	movePage = function(url) {
		FwkCmmnUtil.submitForm("detailFrm", url);  
	};
	deleteFn = function(obj){
		$(obj).parent().parent().remove();
	};
	rowDel = function(obj){
		$(obj).parent().parent().remove();
	};
	download = function(sn) {
		$("#P_ATCHMNFL_SN").val(sn);
		FwkCmmnUtil.submitForm("downloadFrm", "/comm/download.do" );
	};
	fileDel = function(obj,sn){
		if($("#P_DELETE_FILE_SN").val()){
			$("#P_DELETE_FILE_SN").val($("#P_DELETE_FILE_SN").val()+","+sn);
		}else{
			$("#P_DELETE_FILE_SN").val(sn);
		}
		$(obj).parent().parent().remove();
	};
	
	rowAdd = function( fileDiv, fileRow, fileName ){
		var row = $("#"+fileRow).clone();
		row.css("display",""); 
		row.attr("id","");
		row.find("input").eq(0).attr("name",fileName); 
		row.find("input").eq(1).removeAttr("disabled");  
		$("#"+fileDiv).append(row);
	};
	//rowSpan 1-2
	mergeCol_2 = function(jObj, col) {

		var tObj = jObj.get(0);
		var maxRow = tObj.rows.length; // 테이블 전체행
		var compareData = tObj.rows[1].cells[col].innerText; // 분야
		var currentData = ""; // 분야
		var mergeCount  =  1;
		var nameCount = 1;
		var scoreSum = 0;
		var test = 0;
		
		
		for (var k=1; k<maxRow; k++) {
			  currentData = tObj.rows[k].cells[col].innerText; // 분야 1 ~ i 까지
		
			  if (currentData == compareData) {
				  
			  }else{
				  nameCount++;
				  compareData = currentData;
			  }
			  if(tObj.rows[k].cells[col+7]){
				  if(k == maxRow-1){
					  tObj.rows[k-1].cells[col+7].children(0).setAttribute("scoreS", "score_"+(Number(nameCount)-1));
				  }else{
					  tObj.rows[k].cells[col+7].children(0).setAttribute("scoreS", "score_"+nameCount);
				  }
			  }
		}
		
		nameCount = 1;
		compareData = tObj.rows[0].cells[col].innerText; // 분야
		
		for (var i=1; i<maxRow; i++) {
		
		    currentData = tObj.rows[i].cells[col].innerText; // 분야 1 ~ i 까지
		    
		    if(tObj.rows[i].cells[col+1]){
			      tObj.rows[i].cells[col+1].setAttribute("name", "tr_"+nameCount);
		    }
		    
		    if (currentData == compareData) {
		        mergeCount++;
		    }
		
		    else {
		        merge_2(tObj, col, i-mergeCount, mergeCount);
		        compareData = currentData;
		        mergeCount=1;
		        nameCount++;
		    }
		}
		merge_2(tObj, col, maxRow-mergeCount, mergeCount);
	};
	//rowSpan 2-2
	merge_2 = function(tObj, col, start, len) {
		if (1<len) {
		    tObj.rows[start].cells[col].rowSpan = len;
		    tObj.rows[start].cells[Number(col)+1].rowSpan = len;
		    for (var j=start+1; j<start+len; j++) {
		        tObj.rows[j].deleteCell(col);
		        tObj.rows[j].deleteCell(col);
		    }
		}
	};
	setScoreAvg = function(p_dist_score, obj){
	
		var scoreSum = 0;
		var scoreAvg = 0;
		var inputName = $(obj).attr("scoreS");
		
		var score = Number(obj.value);
		var dist_score = Number(p_dist_score);
		if(dist_score >= 0){
			if(dist_score < score){
				alert('배점 기준에 맞지 않습니다.');
				obj.value = 0;
				evalScoreSum('score2','tot_score');
				obj.focus();
			}
		}else if(dist_score < 0){
			if(dist_score > score){
				alert('배점 기준에 맞지 않습니다.');
				obj.value = 0;
				evalScoreSum('score2','tot_score');
				obj.focus();
			}
		}
		
		$("input[scoreS='"+inputName+"']").each(function(inx){
			var temp = $(this).val();
			if(temp == "") temp = 0;
			scoreSum += Number(temp);
		});
		
		var dist_score1 = $(obj).next().val();
		scoreAvg = Math.round(((Number(dist_score1)/100)*Number(scoreSum)) * 100) / 100;
		var num = inputName.substring(inputName.indexOf("_")+1);
		
		if($(obj).val().trim() == ""){
			$("[name='tr_"+num+"']").text("");
		}
		else { 
			$("[name='tr_"+num+"']").text(scoreAvg);
			}
			
		}
	//점수 합계구하기  'score2','tot_score'
	evalScoreSum = function(source, target){
		var total_score = 0;
		var scoreSum = 0;
		// 각 점수 합산
		$("input[name^='"+source+"']").each(function(index){
			total_score = Number(total_score) + Number(unComma($(this).val().trim()));
	
		});
	};

	//점수 합계구하기  'score2','tot_score'
	evalScoreSum2 = function(source, target){
		var total_score = 0;
		var scoreSum = 0;
		var scoreAvg = 0;
		
		var total_scoreAvg = 0;
	
		// 각 점수 합산
		$("input[name^='"+source+"']").each(function(index){
			total_score = Number(total_score) + Number(unComma($(this).val().trim()));
			
			scoreAvg =  Number(Math.round((Number($(this).val()) / Number($(this).next().next().val())) * (Number($(this).next().next().val())/100) * Number($(this).next().val())*100)/100).toFixed(2);
	
			total_scoreAvg = Number(total_scoreAvg) + Number(scoreAvg);
			
		});
		
		$("input[name='"+target+"']").val(total_scoreAvg.toFixed(2));
		
	};
	/**  
     * <pre>
     * 1. 개요 : 저장버튼
     * 2. 처리내용 : 
     *  	
     * </pre>
     * @Function Name : registFrmInqire
     * @date : 2018.03.06
     * @author : 은우소프트 홍찬일
     * @history : 
     *  =======================================================
     *  변경일             		작성자                     		변경내용  
     *  =======================================================
     *   2018.03.06      은우소프트 홍찬일           최초 작성 
     *  =======================================================   
     */
	pageObj.registFrmInqire = function() {
		FwkCmmnUtil.submitForm("registFrm", "/vend/vendEvalDetailEvalSave.do");
	}; 
	  /**  
     * <pre>
     * 1. 개요 : 그리드의 모든 row를 선택한다.
     * 2. 처리내용 :  
     * </pre>
     * @Function Name : setAllCheckOn
     * @date : 2016. 09. 29.
     * @author : 
     * @history : 
     *  =======================================================
     *  변경일             		작성자                     		변경내용  
     *  =======================================================
     *  2016. 09. 29.                     최초 작성 
     *  =======================================================   
     */
	pageObj.setAllCheckOn = function() {
		$('input[type=checkbox]').not($('input[type=checkbox]').eq(0)).prop({'checked':true});
	};

    /**  
     * <pre>
     * 1. 개요 : 그리드의 모든 row를 선택해제한다.
     * 2. 처리내용 :  
     * </pre>
     * @Function Name : setAllCheckOff
     * @date : 2016. 09. 29.
     * @author : 
     * @history : 
     *  =======================================================
     *  변경일             		작성자                     		변경내용  
     *  =======================================================
     *  2016. 09. 29.                     최초 작성 
     *  =======================================================   
     */
	pageObj.setAllCheckOff = function() {
		$('input[type=checkbox]').not($('input[type=checkbox]').eq(0)).prop({'checked':false});
	};
	/**  
     * <pre>
     * 1. 개요 : 그리드에서 행을 삭제한다.
     * 2. 처리내용 :  
     * </pre>
     * @Function Name : setDeleteRow
     * @date : 2016. 09. 29.
     * @author : 
     * @history : 
     *  =======================================================
     *  변경일             		작성자                     		변경내용  
     *  =======================================================
     *  2016. 09. 29.                     최초 작성 
     *  =======================================================   
     */
	pageObj.setDeleteRow = function() {

		var selChk = $('input[type=checkbox]:checked');
		$.each(selChk, function(idx, item) {
			
			$(item).closest('tr').not().remove();
		});
		
    };
	
	pageObj.setEventHandler = function() {
		/**
		 * 1. 취소버튼 클릭 이벤트
		 */
		$(".listBtn").on("click", function() {
			FwkCmmnUtil.submitForm("detailFrm", "/vend/vendEvalDetail.do");  
		});
		$(".saveBtn").on("click", function() {
			if(!confirm("저장하시겠습니까?")){
				return false;
			}
			pageObj.registFrmInqire();
		});
		$("#addBtn").on("click", function() {
			var tr = $("#copyTrget").clone();
			tr.css("display","");
			$("#trgetFrame").append(tr);
		});
		//첨부파일 
		$("#fileBtn").on("click", function(){
			rowAdd("fileDiv","fileRow","P_FILE");
		});
		// 전체선택버튼
		$("#setAllCheckOn").on("click", function() { 
			pageObj.setAllCheckOn();
		});

		// 전체해제버튼
		$("#setAllCheckOff").on("click", function() { 
			pageObj.setAllCheckOff();
		});
		//문서삭제 버튼
		$("#entrpsDeleteBtn").on('click' ,function() {
			pageObj.setDeleteRow();
		});
		
		//파일감지
		$("#entrpsTb tbody").on('change', 'input[type=file]', function() {
			if($(this).val() != null && $(this).val() != undefined && $(this).val() != "") {
				$(this).closest('tr').find('td').eq(3).text($(this).val().split(".").pop().toLowerCase()+" 파일");
				$(this).closest('tr').find('td').eq(4).text(this.files[0].size+" kb");
			}
		});
	};

	/**
	 * window load
	 *
	 */
	$(function() {		
		pageObj.setEventHandler();
		mergeCol_2($("#codeTable"), 0);
		evalScoreSum('score2','tot_score');
		evalScoreSum2('score2','tot_score');
		
		$("input[name='score2']").each(function(inx){
			setScoreAvg($(this).next().val(), $(this));
		});
	});
	
})();