$(function (){
//	lnb(); // lnb
	calendar(); // 달력

})

function lnb (){
	// lnb 높이값 구하기
	var lnbHeight = $('#lnb .depth1_wrap').height();
	var contentsH =  $('.contents_wrap').height();

	$('.sub_wrap #container').css({ 
		'min-height' : lnbHeight > contentsH ? lnbHeight + 110  : contentsH + 80
	}); 

	// lnb 클릭시
	$("#lnb .depth1_wrap .depth1").click(function() {
		$("#lnb .depth1_wrap .depth1").removeClass('on');
		$(this).addClass('on');
	}); 
}

function calendar (){
	// 달력
	$('.datepicker1, .datepicker2').datepicker({
		closeText: '닫기',
		prevText: '이전',
		nextText: '다음',
		currentText: '오늘',
		monthNames: ['1','2','3','4','5','6','7','8','9','10','11','12'],
		monthNamesShort: ['1','2','3','4','5','6','7','8','9','10','11','12'],
		dayNames: ['S','M','T','W','T','F','S'],
		dayNamesShort: ['S','M','T','W','T','F','S'],
		dayNamesMin: ['S','M','T','W','T','F','S'],
		weekHeader: 'Wk',
		dateFormat: 'yy-mm-dd',
		firstDay: 0,
		isRTL: false,
		showMonthAfterYear: true,
		yearSuffix: '.',
		//showOtherMonths: true,
		constrainInput: false,
		showOn: 'both',
		buttonImage: '/statics/images/ipro/keri/calendar_icon01.png',
		buttonImageOnly: true
	})
	$.datepicker.setDefaults($.datepicker.regional['ko']);
}