//version: 3.1.8_e54ce34761bf3c9776b1c6dd23fc7a4531eaad89
//update: Thu Jan 16 10:11:57 2020 +0900

/**
 * Created by wjdcks725 on 2015. 10. 5..
 */

function mChangePinLang(index) {
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
			"인증서 비밀번호 변경",	// 0
			"확인",
			"취소",

			"※ 10자 이상~30자 이하",	// 3
			"※ 숫자/영문자, 특수문자 반드시 포함(' \\ | 제외)",

			"이전 암호",	// 5
			"신규 암호",
			"암호 확인",

			"구동에 실패하였습니다.",	// 8
			"세션이 종료되었습니다.",
			"발급자",
			"만료일",

			"이전 인증서의 비밀번호를 입력하세요.",	// 12
			"입력한 두개의 비밀번호가 일치하지 않습니다.",
			"신규 비밀번호 생성 규칙:\n1. 10자 이상\n2. 숫자, 영소문자, 특수문자 반드시 포함(\", \', \\, \| 제외)\n3. 영문자는 대/소문자 구분됨\n4. 1111 또는 aaaa와 같이 4번 이상 같은 문자 연속으로 사용 불가\n5. 1234 또는 abcd 같이 4번 연속된 문자 사용 불가\n6. ababab와 같이 3번 이상 두글자 연속으로 사용 금지\n7. abcabc와 같이 2번 이상 세글자 연속으로 사용 금지\n",

			"※ 영문자는 대·소문자 구분됨",	// 15

            "신규 인증서의 비밀번호를 입력하세요.",  // 16
            "신규 인증서의 비밀번호를 재입력 하세요."
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