/**
 * 공통 > 팝업
 *
 * <pre>
 * comm
 *    |_popup
 *        |_popup.js
 * 
 * </pre>
 * @date : 2017. 06.21
 * @version : 1.0
 * @author : 은우소프트 하성윤
 */

(function() {

	/**
	 * Default Constructor
	 */
	pageObj = {};

	/**
	 * Default Form Name
	 */
	/**  
     * <pre>
     * 1. 개요 : 부모창 이벤트
     * 2. 처리내용 :
     *  	
     * </pre>
     * @Function Name : returnRegist
     * @date : 2017. 06.21 
     * @author : 은우소프트 
     * @history : 
     *  ===================================================================
     *  변경일             		작성자                     		변경내용  
     *  ===================================================================
     *  2016. 06.21        	    하성윤	                        최초 작성 
     *  ===================================================================
     */
	returnFuntion = function() { 
		if($("#gbn").val() == "invoice2"){
			window.opener.returnFunction3();
			self.close();
		}else{
			window.opener.returnFunction();
			self.close();
		}
		
		
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
		 
		selectCal('P_ORPR_ERA_M_2', 'P_ORPR_ERA_Y_2');
		$('.select_tr').on('click', function() {
			var name = $(this).find('td').eq(1).text().trim();
			var code = $(this).find('td').eq(2).text().trim();
			var meas = $(this).find('td').eq(3).text().trim();
			var qty = $(this).find('td').eq(4).text().trim();
			
			window.opener.pageObj.setItemPopupData(name, code, meas, qty);
			
			window.close();
		});
		
		$('.select_tr2').on('click', function() {
			var name = $(this).find('td').eq(1).text().trim();
			var code = $(this).find('td').eq(2).text().trim();
			
			window.opener.pageObj.setCmpnyPopupData(name, code);
			
			window.close();
		});
		
		$('.select_tr3').on('click', function() {
			var name = $(this).find('td').eq(1).text().trim();
			
			window.opener.pageObj.setCmpnyPopupData(name);
			
			window.close();
		});
	}
	
	/**
	 * window load
	 */
	$(function(){
		pageObj.setEventHandler();
	});
})();