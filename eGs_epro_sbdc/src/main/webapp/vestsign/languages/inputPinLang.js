//version: 3.1.8_e54ce34761bf3c9776b1c6dd23fc7a4531eaad89
//update: Thu Jan 16 10:11:57 2020 +0900

/**
 * Created by nori on 2016. 1. 14..
 */

function inputPinLang(index) {
    var type = {
        'ko':  0,
        'ko-kr': 0,
        'en-us': 1,
        'ja': 2,
        'ja-jp': 2,
        'cn': 3,
        'zh-cn': 3
    };

    var text = [
        [
            "비밀번호 입력",
            "▶ 공인인증서 비밀번호를 입력하세요.",
            "확인",
            "취소",
            "구동에 실패하였습니다.\n\n IE인터넷 옵션 -> 보안 \n 인터넷 보호모드 check, 신뢰사이트 보호모드 check \n",
            "값이 입력되지 않았습니다.",

            "▶ 보안토큰 PIN번호를 입력하세요.",   // 6
            "▶ 저장토큰 PIN번호를 입력하세요.",
            "▶ 안전디스크 비밀번호를 입력하세요.",

            "공인인증서 비밀번호",   // 9
            "보안토큰 PIN 번호",
            "저장토큰 PIN 번호",
            "안전디스크 비밀번호"
        ],
        [
            "Enter Password",
            "▶ Input password of certificate.",
            "OK",
            "Cancel",
            "Failedtoinitialize,  IE internet Option -> security->   internet protected mode check, -> trusted site protecet mode check ",
            "Please enter a value",

            "▶ Input PIN number of security token.",   // 6
            "▶ Input PIN number of storage token.",
            "▶ Input password of safedisc.",

            "Certificate password",   // 9
            "Security token PIN number",
            "Save token PIN number",
            "Safedisc password"
        ],
        [
            "パスワードを入力",
            "▶ 認証書パスワードを入力してください",
            "確認",
            "取消",
            "駆動に失敗しました。\n\nIEインターネットオプション -> セキュリティ\nインターネット保護モードcheck、信頼サイト保護モードcheck \n",
            "値を入力してください。",

            "▶ セキュリティトークンPIN番号を入力してください",   // 6
            "▶ 保存トークンPIN番号を入力してください",
            "▶ 安全ディスクパスワードを入力してください",

            "公認認証書のパスワード",   // 9
            "セキュリティトークンPIN番号",
            "保存トークンPIN番号",
            "安全ディスクパスワード"
        ],
        [
            "密碼",
            "▶ 请输入公认认证书密码.",
            "确认",
            "取消",
            "故障驅動器\n\n  IE瀏覽器Internet選項 ->安全\n互聯網保護模式檢查，受信任的站點保護模式檢查\n",
            "沒有輸入該值.",

            "▶ 请输入安全令牌PIN码.",   // 6
            "▶ 请输入储存令牌PIN码.",
            "▶ 请输入安全磁盘密码.",

            "證書密碼",   // 9
            "安全令牌PIN碼",
            "保存令牌PIN號碼",
            "磁盤密碼安全"
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