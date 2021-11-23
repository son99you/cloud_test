(function() {

	/**
	 * Default Constructor
	 */
	pageObj = {};
	
	movePage = function(url) {
		FwkCmmnUtil.submitForm("detailFrm", url);  
	};
	deleteFn = function(obj){
		$(obj).parent().parent().remove();
	};
	//점수 합계구하기
	 evalScoreSum = function(source, target){
		var total_score = 0;
		
		// 각 점수 합산
		$("input[name='"+source+"']").each(function(index){
			total_score = Number(total_score) + Number(unComma($(this).val().trim()));
			
		});
		
		total_score = Math.round(Number(total_score) * 100) / 100;
		
		$("input[name='"+target+"']").val(total_score);
		
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
	//점수 유효성체크
	 evalScoreChk = function (p_dist_score, scoreObj){
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
	pageObj.setEventHandler = function() {
		evalScoreSum('score2','tot_score');
		evalScoreSum('score2_2','tot_score2');
		/**
		 * 1. 취소버튼 클릭 이벤트
		 */
		$("#listBtn, #listBtn1").on("click", function() {
			movePage("/vend/vendEvalDeptDetail.do");
			return false;
		});
		$("#saveBtn, #saveBtn1").on("click", function() {
			alert("저장되었습니다.");
			movePage("/vend/vendEvalDeptDetail.do");
			return false;
		});
		$("#addBtn").on("click", function() {
			var tr = $("#copyTrget").clone();
			tr.css("display","");
			$("#trgetFrame").append(tr);
			
		});
		//문서추가 버튼
		$("#entrpsPlusBtn").on('click' ,function() {
			var $tr = $('#entrpsTb').find('tr').eq(1).clone();
			$tr.css({'display':''});
			$tr.find('td').eq(1).text($('#entrpsTb').find('tr').length-1);
			$('#entrpsTb').find('tbody').append($tr);
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
	});
	
})();