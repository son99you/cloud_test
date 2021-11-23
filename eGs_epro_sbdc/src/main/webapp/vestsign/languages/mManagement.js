//version: 3.1.8_e54ce34761bf3c9776b1c6dd23fc7a4531eaad89
//update: Thu Jan 16 10:11:57 2020 +0900

/**
 * Created by wjdcks725 on 2015. 10. 5..
 */
function mManagementLang(index) {
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
			
			"기간만료",		// 2
			"만료예정",
			"사용가능",

			"종류",	// 5
			"발급자",
			"발급일",
			"만료일",
			"주체",
			"버전",	// 10
			"일련번호",
			"서명알고리즘",
			"피발급자공개키알고리즘",
			"공개키",
			"서명값",	// 15
			"키사용용도",
			"발급자키식별자",
			"피발급자키식별자",
			"확장키사용용도",
			"추가적인피발급자명",		// 20
			"기본제한",
			"정책",
			"분배점",
			"OCSP서비스",	// 24

			"인증서 관리",
			"비밀번호 변경",
			"인증서 삭제",
			"인증서를 삭제하시겠습니까?" // 28
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