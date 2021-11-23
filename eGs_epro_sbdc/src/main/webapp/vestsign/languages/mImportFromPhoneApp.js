//version: 3.1.8_e54ce34761bf3c9776b1c6dd23fc7a4531eaad89
//update: Thu Jan 16 10:11:57 2020 +0900

/**
 * Created by wjdcks725 on 2015. 10. 5..
 */

function mFromPhoneLang(index) {
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
			"진행절차",
			"유의사항",
			"앱 실행하기",	// 7

			"· 반드시 VestRelay앱 설치 및 실행이 1회 필요합니다.",	// 8
			"· VestRelay앱을 통해 인증서 내보내기를 진행합니다.",
			"앱 설치 및 실행을 위해서 아래 버튼을 클릭해주세요.",
			"앱에서 인증서를 선택하신 후,<br>본 화면에서 <font>인증서 비밀번호</font>를 입력해주세요.",	// 11

			"앱 설치/실행하기",	// 12
			"인증서 가져오기",

            "지원하지 않는 운영체제입니다.",  // 14
            "앱 업데이트가 필요합니다.",

            "전송용 인증번호를 입력하세요.",
			"앱에서 인증서를 선택하신 후,<br>본 화면에서 <font>전송용 인증번호</font>를 입력해주세요.",
			"진행하기 (설치/실행)" // 18
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