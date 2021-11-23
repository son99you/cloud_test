//version: 3.1.8_e54ce34761bf3c9776b1c6dd23fc7a4531eaad89
//update: Thu Jan 16 10:11:57 2020 +0900

/**
 * Created by wjdcks725 on 2015. 10. 5..
 */

function policiesLang(index) {
    var type = {
        'ko':  0,
        'ko-KR': 0, // IE
        'en-US': 1,
        'ja': 2,
        'ja-JP': 2,
        'cn': 3,
        'zh-cn': 3
    };

    var text = [
        [
            "범용(개인)",    // 0
            "범용(법인)",
            "은행/보험(개인)",
            "은행/보험(법인)",
            "신용카드용",

            "전자세금용",    // 5
            "테스트",
            "범용(범용)",
            "은행/보험",
            "1등급(개인)",
            "1등급(법인)",   // 10

            "범용(법인)",
            "증권(법인)",
            "증권(개인)",
            "카드(개인)",
            "상호연동용 인증서(개인)",
            "상호연동용 인증서(법인)",
            "initech"
        ],
        [
            "General purpose(personal)",    // 0
            "General purpose(corp)",
            "Limited(personal)",
            "Limited(corp)",
            "Limited",

            "Limited",  // 5
            "Test",
            "General purpose(corp)",
            "Limited",
            "1st(personal)",
            "1st(corp)",    // 10

            "General purpose(corp)",
            "Stock(corp)",
            "Stock(personal)",
            "Card(personal)",
            "Certificate for interworking(personal)",
            "Certificate for interworking(corp)",
            "initech"
        ],
        [
            "汎用（個人）",   // 0
            "汎用（法人）",
            "銀行/保険（個人）",
            "銀行/保険（法人）",
            "クレジットカード用",

            "電子税金用",    // 5
            "テスト",
            "汎用（法人）",
            "銀行/保険",
            "１等級（個人）",
            "１等級（法人）",  // 10

            "汎用（法人）",
            "証券（法人）",
            "証券（個人）",
            "カード（個人）",
            "インターワーキングのための証明書（個人）",
            "インターワーキングのための証明書（法人）",
            "initech"
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
};