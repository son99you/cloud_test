/**
 * 발주관리 > 발주작성
 *
 * <pre>
 * po
 *    |_ iepPoRegistForm.js
 * 
 * </pre>
 * @date : 2016. 09. 30
 * @version : 1.0
 * @author : 
 */

inputChk = "Regist";

(function() {

	var encoding_status = "UTF-8";
	/**
	 * Default Constructor
	 */
	pageObj = {};

	/**
	 * Default Form Name
	 */
	var defaultFrm = "poRegistFrm";
	var myGrid = null;

    
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
		$('input[type=checkbox]').prop({'checked':true});
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
		$('input[type=checkbox]').prop({'checked':false});
	};
	/**  
     * <pre>
     * 1. 개요 : 그리드에 행을 추가한다.
     * 2. 처리내용 :  
     * </pre>
     * @Function Name : setAddRow
     * @date : 2016. 09. 29.
     * @author : 
     * @history : 
     *  =======================================================
     *  변경일             		작성자                     		변경내용  
     *  =======================================================
     *  2016. 09. 29.                     최초 작성 
     *  =======================================================   
     */
	pageObj.setAddRow = function() {
		var tr_length = $('#set_tbl').find('tr').length;
		
		var $tr = $('<tr></tr>');
		for(var idx = 0; idx < 12; idx++) {
			var $td = $('<td></td>');
			if(tr_length%2 != 0) {
				 $td.addClass('td01 txtc');
			}else {
				$td.addClass('td02 txtc');
			}
			
			if(idx == 0) {
				var $input = $('<input type="checkbox" name="chkbox_'+tr_length+'">');
				$td.append($input);
			}else {
				var $input = $('<input type="text" name="chkbox_'+idx+'_'+tr_length+'">');
				$input.css({'width':'80%','padding':'0px'});
				$td.append($input);				
			}
			$td.css({'border':'1px solid black','padding':'0px'});
			$tr.append($td);
		}
		$('#set_tbl').append($tr);
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
			$(item).closest('tr').remove();
		});
		
    };
    
	/**  
	 * <pre>
	 * 1. 개요 : 발주서 저장
	 * 2. 처리내용 :  
	 *  	
	 * </pre>
	 * @Function Name : poRegist
	 * @date : 2016. 10. 04.
	 * @author : 
	 * @history : 
	 *  =======================================================
	 *  변경일             		작성자                     		변경내용  
	 *  =======================================================
	 *  2016. 10. 04.                     최초 작성 
	 *  =======================================================   
	 */
	pageObj.poRegist = function() {
		FwkCmmnUtil.submitForm("registFrm", "/ordr/acqrList.do");
		
	};
	
	pageObj.setCmpnyPopupData = function(name) {
		if($('#send_list_td').text().trim() != '') {
			name = ","+name;
		}
		$('#send_list_td').append(name);
	};
	  
	/**
	 * 페이지내에서 필요한 항목의 이벤트를 바인딩한다.
	 * 1. 조회버튼(#searchBtn) 의 click 이벤트를 binding 한다.
	 *   - 조회버튼에 click 이벤트 발생시 입찰계획목록조회 함수를 호출한다.
	 * 2. 입찰종류(#select2) 콤보박스의 change 이벤트를 biding한다.
	 *   2.1 change 이벤트 발생시 changeSelect2 함수를 호출한다.
	 * 3. Form 안에서 필요한 필드에 대해 엔터키 이벤트를 바인딩한다.
	 *   - 엔터키 이벤트 발생시 pageObj.clickPage 함수를 호출한다.
	 */	
	pageObj.setEventHandler = function() {

		// 전체선택버튼
		$("#setAllCheckOn").on("click", function() { 
			pageObj.setAllCheckOn();
		});

		// 전체해제버튼
		$("#setAllCheckOff").on("click", function() { 
			pageObj.setAllCheckOff();
		});

		// 행추가버튼
		$("#setAddRow").on("click", function() { 
			pageObj.setAddRow();
		});

		// 행삭제버튼
		$("#setDeleteRow").on("click", function() { 
			pageObj.setDeleteRow();
		});
		
		//저장버튼
		$(".saveBtn").on('click' ,function() {
			pageObj.poRegist();
		});
		
		//취소버튼
		$(".listBtn").on('click' ,function() {
			pageObj.poRegist();
		});
		
		//문서추가 버튼
		$("#entrpsPlusBtn").on('click' ,function() {
			var $tr = $('#entrpsTb').find('tr').eq(1).clone();
			$tr.css({'display':''});
			$tr.find('td').eq(1).text($('#entrpsTb').find('tr').length-1);
			$('#entrpsTb').find('tbody').append($tr);
		});
		
		//문서삭제 버튼
		$("#entrpsDeleteBtn").on('click' ,function() {
			pageObj.setDeleteRow();
		});
		
		//파일감지
		$("#entrpsTb tbody").on('change', 'input[type=file]', function() {
			$(this).closest('tr').find('td').eq(3).text("문서");
			$(this).closest('tr').find('td').eq(4).text("12312342 kb");
		});
		
		//셀렉트박스 감지
		$('#send_select').on('change', function() {
			if($(this).val() == 'A') {
				$('#div_a').css({'display':''});
				$('#div_b').css({'display':'none'});
			}else {
				$('#div_a').css({'display':'none'});
				$('#div_b').css({'display':''});				
			}
		});
		
		//업체팝업
		$('#pop_btn').on('click', function() {
			$("#registFrm").one("submit", function() {
				window.open("", "registFrm", "width=740px,height=450px,toolbar=no,status=no,scrollbars=yes,resizeable=no,menubar=no,left=500,top=200");
				this.action = FwkCmmnUtil['contextPath']+'/comm/popup/mssgeCmpnyList.do'; 
				this.method = 'POST';
				this.target = 'registFrm';
			}).trigger("submit");		
		});
		 
	};

	/**
	 * window load
	 *
	 */
	$(function(){
		pageObj.setEventHandler();
	});
})();