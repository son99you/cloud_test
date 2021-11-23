//version: 3.1.8_e54ce34761bf3c9776b1c6dd23fc7a4531eaad89
//update: Thu Jan 16 10:11:57 2020 +0900

/**
 * Created by wjdcks725 on 2015. 10. 5..
 */

function storageLang(index) {
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
			"하드디스크",    // 0
	          "이동디스크",
	          "보안토큰",
	          "저장토큰",
	          "안전디스크",

	          "구동에 실패하였습니다.\n\n IE인터넷 옵션 -> 보안 \n 인터넷 보호모드 check, 신뢰사이트 보호모드 check \n",   // 5
	          "지원되지 않습니다.",
	          
	          "인증서의 유출가능성이 있어 안전하지 않습니다.<br><br>PC를 공용으로 상용하는 경우는 더 위험하오니,<br>USB메모리, 보안토큰, IC카드 등을 이용하십시오.", // 7
	          "USB 메모리의 경우,<br>하드디스크와 동일하게 인식되어 이용이 매우 편리합니다.<br>",
	          " - 보안기능이 탑재된 가장 우수한 장치입니다.<br> - 국내 표준 인증을 받은 제품 이외에는<br>&nbsp;&nbsp;해당매체를 지원하지 못합니다<br> - 인증서 발급시 몇 분이 소요될 수 있습니다.",
	          " - 접근속도가 저장매체중 비교적 낮습니다<br> - 저장매체의 안전성(보안성)이 우수합니다.",

	          	// html 언어
			"인증서 저장매체 선택",	// 11
			"저장매체 선택",
			"[설명]",
			"확인",
			"취소",
			"이동식매체 선택",	// 16

			"저장매체를 선택하세요.",	// 17

			// 언어 세팅이 일본어일때 저장매체 영어로
			"HardDisk", // 18
	          "Removable",
	          "CryptoToken",
	          "SaveToken",
	          "SecureDisk",

	          // 안전디스크 설명문
			  "하드디스크의 특정영역을 암호화하고, <br>별도의 인증절차를 통해서만 접근할 수 있도록 만든 <br>안전한 디스크 영역으로 공인인증서를 안전디스크에 저장함으로써 <br>위험요소를 최소화 하였습니다.",	// 23
			  "공통저장소"
		],
		[
			"HardDisk", // 0
	          "Removable",
	          "CryptoToken",
	          "SaveToken",
	          "SecureDisk",

	          "Failedtoinitialize,  IE internet Option -> security->   internet protected mode check, -> trusted site protecet mode check ",  // 5
	          "Not Supported.",

	          "This storage isn't safe, there is the possibility of certificate leakage.<br><br>It could be very unsafe, if you are using public PC.<br>It is stongly recommended using Removable Disk, Crypto Token, IC Card.",
	          "The removable disk is very easy to use, because it is recognized in the same manner as a hard disk.<br>",
	          " - This is the best storage device that is equipped with security functions.<br> - Except for products received national certification standards <br>&nbsp;&nbsp;do not support the device.<br> - It may take a few minutes during certificate issuance.",
	          " - This storage is relatively low access speed of the storage media.<br> - This storage has excellent the safety and security.",

	          // html 언어
			"Select certificate storage",	// 11
			"Select storage",
			"[description]",
			"OK",
			"Cancel",
			"Select Removable Disk",	// 16

			// 새로 추가됨
			"Select storage.",	// 17

			// 언어 세팅이 일본어일때 저장매체 영어로
			"HardDisk", // 18
	          "Removable",
	          "CryptoToken",
	          "SaveToken",
	          "SecureDisk",

	          // 안전디스크 설명문
	          "하드디스크의 특정영역을 암호화하고, <br>별도의 인증절차를 통해서만 접근할 수 있도록 만든 <br>안전한 디스크 영역으로 공인인증서를 안전디스크에 저장함으로써 <br>위험요소를 최소화 하였습니다.",	// 23
			  "공통저장소"
		],
		[
			"ハードディスク",    // 0
	          "リムーバブルディスク",
	          "セキュリティトークン",
	          "ICトークン",
	          "安全ディスク",

	          "起動に失敗しました。\n\nIEインターネットオプション -> セキュリティ\nインターネット保護モードcheck、信頼済みサイト保護モードcheck \n",   // 5
	          "サポートされていません。",
	          
	          "認証書はハッキングの可能性があって危険です。<br><br>共用PCを使用する場合はより危険なので、<br>USBメモリ、セキュリティトークン、ICカードなどを利用してください。", // 7
	          "USBメモリの場合、<br>ハードディスクと同一に認識されて利用が非常に便利です。<br>",
	          " -セキュリティ機能がある最も優れた装置です。<br>- 国内標準認証を受けた製品以外には<br>&nbsp;&nbsp;その媒体をサポートすることができません。<br>- 認証書の発給時には数分時間がかかる場合があります。",
	          " -アクセル速度が保存媒体の中で比較的低いです。<br>-保存媒体の安全（セキュリティ）性が優れます。",

	          // html 언어
			"認証書保存媒体の選択",	// 11
			"保存媒体の選択",
			"[説明]",
			"確認",
			"取消",
			"USB媒体の選択",	// 16

			// 새로 추가됨
			"保存媒体のを選択してください。",	// 17

			// 언어 세팅이 일본어일때 저장매체 영어로
			"HardDisk", // 18
	          "Removable",
	          "CryptoToken",
	          "SaveToken",
	          "SecureDisk",

	          // 안전디스크 설명문
	          "하드디스크의 특정영역을 암호화하고, <br>별도의 인증절차를 통해서만 접근할 수 있도록 만든 <br>안전한 디스크 영역으로 공인인증서를 안전디스크에 저장함으로써 <br>위험요소를 최소화 하였습니다.",	// 23
			  "공통저장소"
		],
		[
			"硬盘",    // 0
	          "移动硬盘",
	          "安全令牌",
			"储存令牌",
			"安全磁盘",

	          "驱动失败.\n\n IE互联网选项 -> 安全 \n 互联网保护模式 check, 可信任网址保护模式 check \n",   // 5
	          "不受支持.",
	          
	          "认证书有泄漏的危险.<br><br>使用公用电脑的话更加危险,<br>请使用USB, 安全令牌, IC卡等.", // 7
	          "USB,<br>将被识别为移动硬盘,使用起来非常便捷.<br>",
	          " - 搭载安保功能的最优秀的设备.<br> - 国内获得标准认证的产品以外<br>&nbsp;&nbsp;不支持相关媒体<br> - 证书签发需要几分钟.",
	          " - 接触速度相对于其他储存媒体较低<br> - 储存媒体的安全性(保安性)良好.",

	          	// html 언어
			"认证书储存媒体选择",
			"储存媒体选择",
			"[说明]",
			"确认",
			"取消",
			"移动媒体选择",

			"请选择储存媒体.",		// 17

			// 언어 세팅이 일본어일때 저장매체 영어로
			"HardDisk", // 18
	          "Removable",
	          "CryptoToken",
	          "SaveToken",
	          "SecureDisk",

	          // 안전디스크 설명문
	          "硬盘特定领域加密, <br>通过额外的认证程序才可以靠近  <br>以此创建安全的磁盘区 <br>使存放在安全磁盘的公认认证书的安全风险降到最低.",	// 23
			  "공통저장소"
		]
	];

	var brwoserLang = (function () {
		if (typeof (window.navigator.browserLanguage) === 'undefined')
			return window.navigator.language;
		return window.navigator.browserLanguage;
	})();

	var _config = VestSign.getConfig();
	if(_config.langIndex === undefined)
		return text[type[brwoserLang]][index];

	return text[_config.langIndex][index];
}