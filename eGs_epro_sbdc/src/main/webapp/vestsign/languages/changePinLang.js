//version: 3.1.8_e54ce34761bf3c9776b1c6dd23fc7a4531eaad89
//update: Thu Jan 16 10:11:57 2020 +0900

/**
 * Created by nori on 2016. 1. 14..
 */

function changePinLang(index) {
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
            "인증서 비밀번호 변경",
            "▶ 이전 인증서 비밀번호",
            "▶ 신규 인증서 비밀번호",
            "▶ 인증서 비밀번호(확인)",
            "확인",
            "취소",
            "구동에 실패하였습니다.\n\n IE인터넷 옵션 -> 보안 \n 인터넷 보호모드 check, 신뢰사이트 보호모드 check \n",
            "값이 입력되지 않았습니다.",

            "▶ 인증서의 비밀번호는 프로그램에 의하여 별도 관리되지 않습니다.<br>▶ 인증서 비밀번호를 분실하지 않도록 주의하십시오.<br>", // 8
            " * 10자 이상 ~ 30자 이하<br>",
            " * 숫자, 영문자, 특수문자 반드시 포함( ' \" \\ | 제외 )<br>",
            " * 영문자는 대/소문자 구분됨<br>",

            '입력한 두개의 비밀번호가 일치하지 않습니다.', // 12
            '변경할 비밀번호는 10자리 이상 30자리 이하로 입력해 주세요.',
            '\", \', \|, \\ 는 사용하실 수 없습니다.',
            '신규 비밀번호 생성 규칙:\n1. 10자 이상\n2. 숫자, 영소문자, 특수문자 반드시 포함(\", \', \\, \| 제외)\n3. 영문자는 대/소문자 구분됨\n4. 1111 또는 aaaa와 같이 4번 이상 같은 문자 연속으로 사용 불가\n5. 1234 또는 abcd 같이 4번 연속된 문자 사용 불가\n6. ababab와 같이 3번 이상 두글자 연속으로 사용 금지\n7. abcabc와 같이 2번 이상 세글자 연속으로 사용 금지\n',

            '인증서 갱신',    // 16
            "주의사항 알림",
            "인증서 비밀번호를 변경합니다."
        ],
        [
            "Certificates Change Password",
            "▶ Previous password",
            "▶ New Password",
            "▶ Password(confirmation)",
            "OK",
            "Cancel",
            "Failedtoinitialize,  IE internet Option -> security->   internet protected mode check, -> trusted site protecet mode check ",
            "Please enter a value",

            "▶ Password for the certificate is not managed by the Program.<br>▶ Please do not forget your certificate password.<br>", // 8
            " * More than 10 characters to 30 characters or less<br>",
            " * Numbers, letters, special characters must include( ' \" \\ | X)<br>",
            " * Letters are large / lower case delimited<br><br>",

            'The entered two passwords do not matched each other.', // 12
            'Please enter between 10 letters and 30 letters for changing password.',
            'Not allowed the special characters such as \", \', |, \\',
            'New password creation rules:\n1. more than 10 letters\n2. Numbers, lowercase letters, special characters should be included ( except \", \', \\, | )\n3. Password is case sensitive.\n4. The same character can not be used continuously for more than 4 times, such as 1111 or aaaa\n5. not for use fourth consecutive characters like 1234 or abcd\n6. not for use uses more than three times in two consecutive letters, such as ababab.\n7. Not for use more than once in three consecutive letters, such as abcabc\n',

            'Certificate Update',    // 16
            "Caution alerts",
            "Change the password for your certificate."
        ],
        [
            "認証書パスワード変更",
            "▶ 以前認証書パスワード",
            "▶ 新規認証書パスワード",
            "▶ 認証書パスワード(確認)",
            "確認",
            "取消",
            "駆動に失敗しました。\n\nIEインターネットオプション -> セキュリティ\nインターネット保護モードcheck、信頼サイト保護モードcheck \n",
            "値を入力してください。",

            "▶ 認証書パスワードはプログラムによって管理されていません。<br>▶ 認証書パスワードを紛失しないように注意してください。<br>", // 8
            " * 10字以上 ~ 30字以下<br>",
            " * 数字、英文字、特殊文字は必ず含む( ' \" \\ | 除外 )<br>",
            " * 英文字は大/小文字を区別<br>",

            '入力した2つのパスワードが一致しません。', // 12
            '変更するパスワードは10文字以上30文字以下入力してください。',
            '\", \', |, \\ を使用することができません。',
            '新規パスワード生成規則:\n1. 10字以上\n2. 数字、英小文字, 特殊文字は必ず含む( \", \', \\, | 除外 )\n3. 英文字は大・小文字を区別する\n4. 1111又はaaaaのように同じ文字で4回以上連続する文字列は使用不可\n5. 1234又はabcdように4文字以上連続する文字列は使用不可\n6. abababのように3回以上２文字連続する文字列は使用不可\n7. abcabcのように2回以上３文字連続する文字列は使用不可\n',

            '認証書更新',    // 16
            "注意事項通知",
            "証明書のパスワードを変更します。"
        ],
        [
            "修改认证书密码",
            "▶ 以前的认证书密码",
            "▶ 新认证书密码",
            "▶ 认证书密码(确认)",
            "确认",
            "取消",
            "故障驅動器\n\n  IE瀏覽器Internet選項 ->安全\n互聯網保護模式檢查，受信任的站點保護模式檢查\n",
            "沒有輸入該值.",

            "▶ 认证书的密码不受当前程序管理.<br>▶ 请注意避免丢失认证书密码.<br>",
            " * 10位以上 ~ 30位以下<br>",
            " * 必须包括数字, 字母, 特殊符号( ' \" \\ | 除外)<br>",
            " * 英文字母区分大/小写<br>",

            "两次输入密码不同.",    // 12
            "请输入10位以上30位以下新密码.",
            "\", \', |, \\ 无法使用.",
            '新密码生成规则:\n1. 10位以上\n2. 必须包含数字, 字母, 特殊符号( \", \', \\, | 除外 )\n3. 英文字母区分大/小写\n4. 禁止使用相同文字4次以上如1111 或者 aaaa\n5. 禁止使用连续文字4次如1234 或者 abcd\n6. 禁止使用连续两位文字3次以上如ababab\n7. 禁止使用连续三位文字2次以上如abcabc\n',

            '认证书升级',    // 16
            "注意事項提醒",
            "更改您的證書的密碼."
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