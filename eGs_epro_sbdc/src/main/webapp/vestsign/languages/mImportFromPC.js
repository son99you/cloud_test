//version: 3.1.8_e54ce34761bf3c9776b1c6dd23fc7a4531eaad89
//update: Thu Jan 16 10:11:57 2020 +0900

/**
 * Created by wjdcks725 on 2015. 10. 5..
 */
function mFromPCLang(index) {
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
			"저장할 인증서 비밀번호를 입력하세요.",

			"PC에서 인증서 가져오기",		// 3
			"인증번호",
			"PC에서 인증서를 선택 후,<br>스마트폰에  표시된 인증번호를 PC에서 입력해주세요.",

			"사용안내",	// 6
			"PC : 공인인증센터 → 스마트폰 인증서 이동",
			"스마트폰 : 패스워드 입력 → 확인",

			"확인",	// 9
			"취소"
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