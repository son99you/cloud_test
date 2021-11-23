//version: 3.1.8_e54ce34761bf3c9776b1c6dd23fc7a4531eaad89
//update: Thu Jan 16 10:11:57 2020 +0900

/**
 * Created by nori on 2016-12-01.
 */

function notMatchedPasswordLang(index) {
    var type = {
        'ko':  0,
        'ko-KR': 0,
        'en-US': 1,
        'ja': 2,
        'ja-jp': 2,
        'cn': 3,
        'zh-cn': 3
    };

    var text = [
        [
            "암호화용 인증서 비밀번호 동기화 확인",
            "확인",
            "취소",

            "입력하신 인증서 비밀번호는 일치하지만, 고객님이 추가로 소유하신\n암호용 인증서는 다른 비밀번호를\n이용하고 계십니다.\n\n",
            "암호화용 인증서 비밀번호를 알고계신 경우 '확인' 버튼을 누르시면\n인증서 비밀번호로 동일하게 변경합니다.\n\n",
            "'취소' 버튼을 누르시면 진행이 중단됩니다.\n\n",
            "(※ 비밀번호를 다르게 이용하시는 경우 입찰 등 서비스에 제한이\n있습니다.)\n\n"
        ],
        [
            "Confirm password synchronization of encryption certificate",
            "OK",
            "Cancel",

            "The certificate password you entered is the same,\nbut you are using a different password for\nyour additional password.\n\n",
            "If you know the certificate password for encryption,\nclick the 'OK' button and change the\nsame as the certificate password.\n\n",
            "Press 'Cancel' button to stop the process.\n\n",
            "(※ If you use the password differently,\nthere is a limitation on the service such as bidding.)\n\n"
        ],
        [
            "暗号化用の証明書のパスワード同期の確認",
            "確認",
            "取消",

            "入力された証明書のパスワードは一致するが、お客様が追加で所有された\nパスワードの証明書は、他のパスワードを\n利用しておられます。\n\n",
            "暗号化用の証明書のパスワードをわかっている場合は、「OK」ボタンを押すと、\n証明書のパスワードに同じように変更します。\n\n",
            "「キャンセル」ボタンを押すと進行が中断されます。\n\n",
            "（※パスワードを別の方法でご利用の場合、入札などのサービスに制限が\nあります。）\n\n"
        ],
        [
            "確認密碼同步加密證書",
            "確認",
            "取消",

            "匹配的證書密碼輸入，但也急於為\n密碼為你的所有權證書是用來添加不同的密碼\n。\n\n",
            "如果您知道加密證書應\n作為按下“確定”按鈕，證書密碼一樣更改密碼。\n\n",
            "這一進展將通過按“取消”按鈕中斷。\n\n",
            "（※有限制\n如果使用的服務，如不同的密碼競標。）\n\n"
        ]
    ];

    var brwoserLang = (function () {
        if (typeof (window.navigator.browserLanguage) === 'undefined')
            return window.navigator.language.toLowerCase();
        return window.navigator.browserLanguage;
    })();

    var _config = VestSign.getConfig();
    if(_config.language === undefined)
        return text[type[brwoserLang]][index];

    return text[_config.language][index];
}