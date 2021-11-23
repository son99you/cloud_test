(function() {

	/**
	 * Default Constructor
	 */
	pageObj = {};
	
	var file = null;
	
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
	fnDeptEvalInsert = function() {
	
		var	f	= document.forms("registFrm");
		
		if(Number(f.tot_score.value )> 100 || Number(f.tot_score2.value ) > 100)
		{
			alert("총합은 100점을 넘을수 없습니다.");
			return;
		}
		if( f.ev_state.value == "B")
		{
		// 
		$("input[name='value2']").each(function(index){
			if($(this).val() == null || $(this).val() ==""){
				$(this).val(" ");
			}
			
		});
		
		$("input[name='score2']").each(function(index){
			if($(this).val() == null || $(this).val() ==""){
				$(this).val("0");
			}
			
		});
		
		}else if( f.ev_state.value == "D1")
		{
			$("input[name='value2_2']").each(function(index){
				if($(this).val() == null || $(this).val() ==""){
					$(this).val(" ");
				}
				
			});
			
			$("input[name='score2_2']").each(function(index){
				if($(this).val() == null || $(this).val() ==""){
					$(this).val("0");
				}
				
			});
		}
		FwkCmmnUtil.submitForm("registFrm", "/vend/vendEvalDeptInsert.do");  
	};
	mergeCol = function(jObj, col) {

	var tObj = jObj.get(0);
    var maxRow = tObj.rows.length;
    var compareData = tObj.rows[0].cells[col].innerText;
    var currentData = "";
    var mergeCount  =  1;

    for (var i=1; i<maxRow; i++) {

        currentData = tObj.rows[i].cells[col].innerText;

        if (currentData == compareData) {

            mergeCount++;

        }

        else {

            merge(tObj, col, i-mergeCount, mergeCount);
            compareData = currentData;
            mergeCount=1;

        }

    }

    merge(tObj, col, maxRow-mergeCount, mergeCount);

	};
	//rowSpan 2-2

	merge = function(tObj, col, start, len) {
	    if (1<len) {
	
	        tObj.rows[start].cells[col].rowSpan = len;
	        for (var j=start+1; j<start+len; j++) {
	
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
			
		};
	 evalScoreSum = function(source, target){
		var total_score = 0;
		
		// 각 점수 합산
		$("input[name='"+source+"']").each(function(index){
			total_score = Number(total_score) + Number(unComma($(this).val().trim()));
			//alert(index+" :: "+total_score);
			
		});
		
		total_score = Math.round(Number(total_score) * 100) / 100;
		
		$("input[name='"+target+"']").val(total_score);
		
	};
	//점수 유효성체크
	evalScoreChk = function(p_dist_score, scoreObj){
		var score = Number(scoreObj.value);
		var dist_score = Number(p_dist_score);
		if(dist_score >= 0){
			if(dist_score < score){
				alert('배점 기준에 맞지 않습니다.');
				scoreObj.value = 0;
				evalScoreSum('score','tot_score');
				scoreObj.focus();
			}
		}else if(dist_score < 0){
			if(dist_score > score){
				alert('배점 기준에 맞지 않습니다.');
				scoreObj.value = 0;
				evalScoreSum('score','tot_score');
				scoreObj.focus();
			}
		}
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
		$("#listBtn").on("click", function() {
			FwkCmmnUtil.submitForm("listFrm", "/vend/vendEvalDeptDetail.do");  
		});
		$("#saveBtn").on("click", function() {
			if(!confirm("저장하시겠습니까?")){
				return false;
			}
			fnDeptEvalInsert();
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
		mergeCol($("#codeTable"), 0);
		mergeCol($("#codeTable2"), 0);
		evalScoreSum('score2','tot_score');
		evalScoreSum('score2_2','tot_score2');
	});
	
})();