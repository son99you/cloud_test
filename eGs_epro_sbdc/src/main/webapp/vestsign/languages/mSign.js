//version: 3.1.8_e54ce34761bf3c9776b1c6dd23fc7a4531eaad89
//update: Thu Jan 16 10:11:57 2020 +0900

/**
 * Created by wjdcks725 on 2015. 10. 5..
 */

function mSignLang(index) {
	var type = {
		'ko':  0,
		'ko-KR': 0,
		'en-US': 1,
        	'ja': 2,
       	'ja-JP': 2,
        	'cn': 3,
        	'zh-cn': 3
	}

	var text = [
		[
			"인증서 전자서명",	// 0
			"확인",
			"취소",

			"구동에 실패하였습니다.",	// 3
			"세션이 종료되었습니다.",
			"비밀번호를 입력하세요.",
			"발급자",
			"만료일",

			"인증서를 선택하세요.",	// 8
			"만료된 인증서입니다.",
			"인증서 비밀번호를 입력하세요.",
			"구분",
			"인증서 삭제", // 12
			"인증서 비밀번호 변경",
			"PC로 인증서 내보내기",
			"인증서를 삭제하시겠습니까?"
		],
		[
			
		],
		[
			
		],
		[
			
		]
	];

	var brwoserLang = (function () {
		if (typeof (window.navigator.browserLanguage) === 'undefined')
			return window.navigator.language;
		return window.navigator.browserLanguage;
		// return 'ja-JP';
	})();

	var _config = VestSign.getConfig();
	if(_config.langIndex === undefined)
		return text[type[brwoserLang]][index];

	return text[_config.langIndex][index];
}