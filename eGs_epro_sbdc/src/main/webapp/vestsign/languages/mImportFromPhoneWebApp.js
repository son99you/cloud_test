//version: 3.1.8_e54ce34761bf3c9776b1c6dd23fc7a4531eaad89
//update: Thu Jan 16 10:11:57 2020 +0900

/**
 * Created by wjdcks725 on 2015. 10. 5..
 */

function mFromPhoneWebLang(index) {
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
			"구동에 실패하였습니다.",	// 0
			"세션이 종료되었습니다.",
			"인증서 비밀번호를 입력하세요.",
			"파일을 선택해 주세요.",

			"휴대폰에서 인증서 가져오기",		// 4
			"인증서 패스워드를 입력하세요",	// 5

			"확인",	// 6
			"취소",

            "지원하지 않는 운영체제입니다."  // 8
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