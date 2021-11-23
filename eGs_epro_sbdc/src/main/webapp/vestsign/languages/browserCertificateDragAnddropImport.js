//version: 3.1.8_e54ce34761bf3c9776b1c6dd23fc7a4531eaad89
//update: Thu Jan 16 10:11:57 2020 +0900

function browserCertificateDragAnddropImportLang (index) {
    var type = {
        'ko': 0,
        'ko-KR': 0,
        'en-US': 1,
        'ja': 2,
        'ja-JP': 2,
        'cn': 3,
        'zh-cn': 3
    };

    var text = [
        [
            "인증서 삭제", // 0
            "인증서 암호",
            "인증서 비밀번호 (대소문자 구분)",
            "확인",
            "취소",
            "인증서 가져오기", // 5
            "여기를 클릭 하거나 끌어다 놓아서 인증서 파일 (*.pfx,* .p12)",
            "안내",
            "인증서 파일이 아니거나 비밀번호가 일치하지 않습니다.",
            "PFX파일이 아니거나 비밀번호가 일치하지 않습니다.",
            "대소문자를 구분하여 입력해주세요.", // 10
            "사용 가능한 인증서가 없습니다.",
            "세션이 종료되었습니다.",
            "인증서 파일을 정확히 선택해주세요.",
            "인증서 파일 형식이 올바르지 않습니다.",
            "선택한 파일은 사용할 수 없습니다.", // 15
            "인증서 가져오기가 사용자에 의해 취소 되었습니다.",
            "브라우저로 인증서 가져오기",
            "인증서 또는 인증서 파일(*.pfx, *.p12) 선택해주세요.",
            "프로그램 다운로드",
            "아래 순서에 따라 진행하시면, 하드디스크나 이동식 디스크에 저장된 인증서(der)나 인증서 파일(*.pfx, *.p12)을 브라우저로 가져올 수 있습니다.", // 20
            "인증서 파일이란?",
            " (상세설명 Click)", // 22
            "인증서 파일은 인증서를 하나의 파일로 이동하는 국제 규격입니다. 아래 인증서 파일 생성 프로그램을 다운로드 받으신 후 순서에 따라 진행하면 생성 가능합니다. ",
            "또는 인증서 묶음(signCert.der, signCert.key)을 선택해 주세요.",
            "비밀번호를 입력해주세요.", // 25
            ""
        ],
        [
            " Delete　Cert ", // 0
            "Password",
            "Password for the certificate is case sensitive.",
            "OK",
            "Cancel",
            "Importing Certificates", // 5
            "Certificate (der) and private key file pairs or * .p12, * pfx files",
            "Info",
            "The certificate file is missing or the passwords do not match.",
            "The file is not pfx format file or has wrong password.",
            "Please enter case sensitive.", // 10
            "No certificates are available.",
            "Your session has ended.",
            "Please select the correct certificate file.",
            "The certificate file format is not valid.",
            "Selected files are not available.", // 15
            "The certificate import has been canceled by the user.",
            "Importing certificates into the browser",
            "Please select a certificate or certificate file (* .pfx, * .p12).",
            "Download the program",
            "Follow the steps below to import the certificate (der) or certificate file (* .pfx, * .p12) saved on your hard disk or removable disk to your browser.", // 20
            "What is certificate file?",
            " (Detailed explanation Click)", // 22
            "A certificate file is an international standard that moves a certificate to a single file. Download the certificate file creation program and follow the steps to create it.",
            "Please enter your password.", // 25
            ""
        ],
        [
            "認証書の削除", // 0
            "パスワード",
            "認証書のパスワードは",
            "確認",
            "取消",
            "导入证书", // 5
            "证书（der）和私钥文件对或* .p12，* pfx文件",
            "Info",
            "証明書ファイルがないか、またはパスワードが一致しません。",
            "PFXファイルがないか、またはパスワードが一致しません。",
            "请输入区分大小写。", // 10
            "使用可能な証明書がありません。",
            "セッションが終了されました。",
            "请选择正确的证书文件。",
            "证书文件格式无效",
            "所选文件不可用。", // 15
            "证书已被用户取消。",
            "将证书导入浏览器",
            "请选择证书或证书文件（* .pfx，* .p12）。",
            "プログラムのダウンロード",
            "按照以下步骤将保存在硬盘或可移动磁盘上的证书（der）或证书文件（* .pfx，* .p12）导入浏览器。", // 20
            "什么是证书文件？",
            " （详细说明点击）", // 22
            "证书文件是将证书移动到单个文件的国际标准。下载证书文件创建程序并按照步骤创建它。",
            "请输入您的密码", // 25
            ""
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