//version: 3.1.8_e54ce34761bf3c9776b1c6dd23fc7a4531eaad89
//update: Thu Jan 16 10:11:57 2020 +0900

/**
 * Created by nori on 2016. 1. 14..
 */

function relayImportLang(index) {
    var type = {
        'ko':  0,
        'ko-kr': 0,
        'en-us': 1,
        'ja': 2,
        'ja-jp': 2
    };

    var text = [
        [
            "확인",
            "취소",        // 1
            "구동에 실패하였습니다.\n\n IE인터넷 옵션 -> 보안 \n 인터넷 보호모드 check, 신뢰사이트 보호모드 check",
            "인증서 가져오기",
            "스마트폰에서 “인증서 내보내기”를 실행하고, 아래 승인번호 12자리를 입력해 주십시오.",
            "스마트폰에서 인증서 내보내기를 눌러 인증서를 PC로 전송해 주세요.",
            "승인번호"
        ],
        [
            "OK",
            "Cancel",        // 1
            "Failedtoinitialize,  IE internet Option -> security-> internet protected mode check, -> trusted site protecet mode check",
            "Import Certificate",
            "Run the 'Export Certificate' in the smart phone, please enter the the 12 digit following approval number.",
            "Please enter the under approval number 12 digit SmartPhone",
            "Approval Number"
        ],
        [
            "確認",
            "取消",        // 1
            "駆動に失敗しました。\n\nIEインターネットオプション -> セキュリティ\nインターネット保護モードcheck、信頼サイト保護モードcheck",
            "証明書のインポート",
            "スマートフォンで「証明書のエクスポート」を実行して、次の承認番号12桁のを入力してください。",
            "下承認番号12桁のスマートフォンに \nを入力してください",
            "承認番号"
        ]
    ];

    var brwoserLang = (function () {
        if (typeof (window.navigator.browserLanguage) === 'undefined')
            return window.navigator.language.toLowerCase();
        return window.navigator.browserLanguage;
    })();

    var _config = VestSign.getConfig();
    if(_config.langIndex === undefined)
        return text[type[brwoserLang]][index];

    return text[_config.langIndex][index];
}