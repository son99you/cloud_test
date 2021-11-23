//version: 3.1.8_e54ce34761bf3c9776b1c6dd23fc7a4531eaad89
//update: Thu Jan 16 10:11:57 2020 +0900

/**
 * Created by wjdcks725 on 2015. 10. 5..
 */

function jsLang(index) {
    var type = {
        'ko': 0,
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
            "인증서찾기",
            "안전디스크",

            "인증서를 선택해주세요.",  // 5
            "비밀번호를 입력해주세요.",
            "구동에 실패하였습니다.\n\n IE인터넷 옵션 -> 보안 \n 인터넷 보호모드 check, 신뢰사이트 보호모드 check \n",
            "일반 인증서",

            "가입자",  // 9
            "버전",
            "일련번호",
            "발급자 식별명",
            "다음부터 유효함",
            "다음까지 유효함",
            "서명알고리즘",   // 15
            "피발급자 공개키 알고리즘",
            "공개키",
            "서명값",
            "키 사용용도",
            "발급자키 식별자", // 20
            "피발급자키 식별자",
            "확장키 사용용도",
            "추가적인 피발급자명",
            "기본제한",
            "정책",   // 25
            "분배점",
            "OCSP서비스",
            "저장위치",

            "전자서명용",    // 29
            "암호화용",
            "인증서발급기관",
            "최상위인증기관",

            "준비중입니다.",  // 33

            "해쉬값 검증",
            "추가정보",
            "인증서 검증",

            "$해당 인증서는 다음과 같은 내용을 포함하고 있습니다",    // 37
            "-발급자: ",
            "-가입자: ",
            "-발급일: ",    // 40
            "-만료일: ",
            "$검증결과: ",
            "모든 인증서 검증조건을 만족합니다.",
            "인증서 검증에 실패하였습니다.",
            "$오류코드: ",    // 45
            "없음",
            "$오류정보: ",
            "$인증서 검증절차가 완료되었습니다.",
            "[*] 컴퓨터의 시간을 반드시 확인하십시오.",
            "\n",    // 50

            "성공",
            "인증서찾기안내",
            "세션이 종료되었습니다.",
            "만료된 인증서입니다.",
            "전자서명 중 에러가 발생하였습니다.",  // 55
            "인증서 파일을 가져오세요.",
            "PFX파일이 아니거나 비밀번호가 일치하지 않습니다.",
            "허용된 인증서가 아닙니다.",
            "업데이트",

            "보안 매체 비밀번호가 5회이상 틀렸습니다.",  // 60
            "VestCert가 잠겼습니다.",
            "비밀번호가 일치하지 않습니다.",
            "비밀번호가 다수 틀렸습니다.",
            "보안토큰이 잠겼습니다.",
            "보안 매체를 사용하기 위해서는 초기화가 필요합니다.", // 65
            "윈도우 우측(Tray)에서 초기화를 진행해주세요.",
            "infovine 모듈 설치가 필요합니다. 설치 페이지로 연결합니다.",
            "infovine 모듈 버전이 낮습니다. 업그레이드 페이지로 연결합니다.",

            "하드 디스크가 존재하지 않습니다. 하드 디스크를 확인해 주십시오.", // 69
            "이동식 디스크가 존재하지 않습니다. 이동식 디스크를 확인해 주십시오.",
            "보안 토큰이 연결되어 있지 않습니다. 보안 토큰을 연결해 주십시오.",
            "저장 토큰이 연결되어 있지 않습니다. 저장 토큰을 연결해 주십시오.",

            "PFX 가져오기를 눌러주세요.", // 73
            "'https:/'로 접속 가능합니다.",

            "취소되었습니다.",      // 75
            "infovine",
            "저장토큰",
            "휴대폰인증",
            "저장된 인증서가 없습니다.",
            "지원되지 않는 서비스 입니다.",  // 80
            "전자서명",

            // html 언어
            "가입자명",	// 82
            "만료일",
            "정책등급",
            "발급기관",
            "인증서 보기",
            "인증서 삭제",	// 87
            "인증서 암호",	// 88
            "인증서 암호는 대소문자를<br> 구분합니다",
            "확인",
            "취소",

            "“인증서 관리 프로그램이 설치 또는 실행되지 않았습니다.”",	// 92
            "설치안내",
            "설치",
            "실행",

            "“인증서찾기 메뉴를 이용하시면프로그램 설치 없이 <br>전자서명이 가능합니다.”",	// 96
            "인증서 찾기 안내",	// 97
            "여기에 인증서파일(*.PFX, *P12)을 <br>끌어다 놓으세요.",
            "인증서 찾기",

            "인증서 내용보기",	// 100
            "속성",
            "값",	// 102

            // script 언어
            "보안매체 선택",
            "인증서 검증",
            "최상위인증기관 인증서 신뢰성 확인",	// 105
            "선택하신 최상위 인증기관의 인증서 해쉬값은 다음과 같습니다.",
            "안전한 인증서 사용을 위해 KISA 홈페이지(https://rootca.kisa.or.kr/kcac/cert/potency.html) 확인 후, 최상위 인증기관에 게시한 해쉬값과 일치하는지 확인하여 주십시오.",	// 107
            "인증서 만료공지",
            "인증서의 유효기간이 얼마남지 않았습니다.",	// 109
            "인증서 만료일 : ",
            "인증서를 갱신하여 주십시오.",
            "'갱신'버튼을 클릭하면 현재 수행되던 절차를 중단하고 인증서 갱신 화면으로 연결됩니다.",	// 112
            "갱신",	// 113
            "나중에 갱신 하시려면 '확인'을 클릭하십시오.",
            " 이 메시지를 더 이상 표시하지 않습니다.",

            "Report",	// 116
            "기타 에러",

            "선택한 인증서는 사용할 수 없습니다.",
            "오류횟수",		// 119
            "정말 삭제하시겠습니까??",
            "정말 폐기하시겠습니까??",

            "인증서 갱신",
            "인증서 폐기",
            "인증서 내보내기",
            "비밀번호 변경",
            "저장매체 변경",

            // 언어 세팅이 일본어일때 저장매체 영어로
            "하드디스크", // 127
            "이동디스크",
            "보안토큰",
            "인증서찾기",
            "안전디스크",
            "저장토큰",
            "휴대폰인증",

            "이동식매체 선택",	// 134
            "휴대폰인증 매체 선택",

            "해당인증서는 본 사이트에서 허용되지 않습니다.",	// 136
            "비밀번호 확인",	// 137

            "브라우저",	// 138
            "인증서를 저장하시겠습니까?",
            "인증서가 유효한 상태입니다.",
            "인증서 유효기간이 만료되었습니다.",
            "폐지된 인증서입니다.",
            "인증서 유효성 검증 결과 상태 확인 불가.",		// 143
            "PIN 번호",

            "Mobisign 모듈 설치가 필요합니다. 설치 페이지로 연결합니다.",	// 145
            "Mobisign 모듈 버전이 낮습니다. 업그레이드 페이지로 연결합니다.",
            "MobiSign",

            "DreamSecurity 모듈 설치가 필요합니다. 설치 페이지로 연결합니다.",	// 148
            "DreamSecurity",

            "인증서 휴대폰 저장서비스(UbiKey)",	// 150
            "스마트인증(USIM) 서비스",

            "저장토큰 PIN번호 입력",	// 152
            "▶ 저장토큰 PIN번호를 입력하세요.",
            "저장토큰 PIN 번호",

            "저장토큰 선택",	// 155
            "스마트인증",
            "백업리스트",

            "PIN 번호는 대소문자를<br> 구분합니다",	// 158

            "네트워크 드라이브", // 159

            "인증서 리스트 보기",
            "인증서 가져오기",
            "PFX 가져오기",
            "NPKI 가져오기",
            "사용 가능한 인증서가 없습니다", // 164
            "가져오기",
            "브라우저 인증서(개인)",
            "브라우저 인증서(법인)", // 167
            "",
            "[Safari] 설정으로 이동하여 '팝업 차단'을 항상 허용으로 변경해주세요."
        ],
        [
            "HardDisk", // 0
            "Removable",
            "HSM",
            "Find Cert",
            "SecureDisk",

            "Select certificate.",  // 5
            "Input password.",
            "Failedtoinitialize,  IE internet Option -> security->   internet protected mode check, -> trusted site protecet mode check ",
            "General certificate",

            "Subject",  // 9
            "Version",
            "Serial",
            "Issuer",
            "Issue date",
            "Expiration date",
            "Signature Algorithm",  // 15
            "Public Key Algogrithm",
            "Public Key",
            "Signature",
            "Key usage",
            "Issuer ID",    // 20
            "Subject ID",
            "Extented key usage",
            "Additional subject name",
            "Basic limit",
            "Policy",   // 25
            "Distribution point",
            "OCSP service",
            "Storage",

            "Signature",    // 29
            "Encryption",
            "CA",
            "Root CA",

            "Service will be opened soon.", // 33

            "Hash Verification",
            "Additional Info",
            "Cert Verification",

            "$This certificate include below information.",  // 37
            "-Issuer: ",
            "-Subject: ",
            "-Issuedate: ", // 40
            "-Expirationdate: ",
            "$Verification result: ",
            "Verification success",
            "Verification fail",
            "$Error code: ", // 45
            "N/A",
            "$Error info: ",
            "$Verification chain is succeeded.",
            "[*] Check your system date and time.",
            "\n",   // 50

            "Success",
            "Find Certificate Guide",
            "Session is expired.",
            "Expired certificate.",
            "Error while making a signature.",   // 55
            "Select pfx file",
            "The file is not pfx format file or has wrong password.",
            "The certifiacte is not allowed.",
            "Update",

            "You have input worng password over 5 times.",  // 60
            "VestCert program is locked.",
            "Wrong password.",
            "You have input wrong password many times.",
            "Crypto Token is locked.",
            "You need to initialize Security Token.",   // 65
            "Initailize Security Token using VestCert Manager.",
            "You need to install infovine program. This page will be changed to install page.",
            "You need to upgrade infovine program. This page will be changed to upgrade page.",

            "There is no certificate in hard disk. Please check the hard disk.",    // 69
            "There is no certificate in removable disk. Please check the removable disk",
            "There is no connection for Crypto Token. Please check connection of Security Token",
            "There is no connection for Storage Token. Please check connection of Storage Token",

            "Click PFX impmort button.",    // 73
            "You need to connect by 'https:/' protocol.",

            "It has been canceled.",      // 75
            "infovine",
            "SaveToken",
            "Cell Phone",
            "It doesn't have saved certificate.",
            "Not Supported.",  // 80
            "signature",

            // html 언어
            "Subject",	// 82
            "Expiration",
            "Policy",
            "Issuer",
            "View Detatils",
            " Delete　 Cert ",	// 87
            "Password",
            "Password for the certificate <br>is case sensitive.",
            "OK",
            "Cancel",

            "”VestCert program is not installed or not running”",	// 92
            "Installation guide",
            "Installation",
            "Execution",

            "”You can make signature without any installation if you use [Find certificate] menu.”",	// 96
            "Find certificate guide",	// 97
            "Drag and drop your (*.PFX, *P12) file <br>here.",
            "Find certificate",

            "View Certificate",	// 100
            "Attribute",
            "Value",		// 102

            "Select secure stroage",
            "Certificate Verify",
            "Verificateion Root Certificate reliability",	// 105
            "The hash value of selected Root Certificate is below.",
            "Please verify that the ROOT certifcate hash value correspond with distributed hash value in KISA hompage(https://www.rootca.or.kr/cert/potency.html) for safer usage.",	// 107
            "Expiration Notice",
            "This certificate is going to be expire on a near days.",	// 109
            "Expiration date: ",
            "Please renew your certificate.",
            "This page will be chaged when you click 'renew' button, suspending  your signing process.",	// 112
            "Renewal",	// 113
            "If you want renew later, please click 'OK'.",
            "Don't show me this message later.",

            "Report",	// 116
            "other error",

            "You can't use selected certificate.",	// 118
            "Error count",		// 119
            "Are you sure you want to delete??",
            "Are you sure you want to revoke??",

            "Certificate Update",
            "Certificate Revoke",
            "Certificate Export",
            "Change Password",
            "Change Storage",

            // 언어 세팅이 일본어일때 저장매체 영어로
            "HardDisk", // 127
            "Removable",
            "HSM",
            "Find Cert",
            "SecureDisk",
            "SaveToken",
            "Cell Phone",

            "Select Removable Disk",	// 134
            "Select Cell Phone",

            "This certificate is not permitted on this site.",	// 136
            "Verify Password",	// 137

            "Browser",	// 138
            "Do you want to save a certificate?",
            "The certificate is valid.",
            "This certificate has expired.",
            "The certificate revocation.",
            "No verification certificate status validation results.",		// 143
            "PIN password",

            "The Mobisign module installation is required. Connect to the installation page.",	// 145
            "Mobisign version of the module is lower . Connect to the upgrade page.",
            "MobiSign",

            "The DreamSecurity module installation is required. Connect to the installation page.",	// 148
            "DreamSecurity",

            "Certificate store phone service (UbiKey)",	// 150
            "Smart authentication (USIM) service",

            "Input PIN number of storage token.",	// 152
            "▶ Input PIN number of storage token.",
            "Save token PIN number",

            "Select Save token",	// 155
            "Smart authentication",
            "BackupList",

            "PIN numbers are case sensitive.",	// 158

            "NetworkDrive",  // 159

            "CertificateList View",
            "Certificate Import",
            "PFX Import",
            "NPKI Import",
            "No certificates are available", // 164
            "Import",
            "Browser Certificate(individual)",
            "Browser Certificate(Corporation)", // 167
            "",
            "[Safari] Go to Settings and change 'Block popups' to Always Allow.",
        ],
        [
            "ハードディスク",  // 0
            "リムーバブルディスク",
            "セキュリティトークン",
            "認証書検索",
            "安全ディスク",

            "認証書を選択してください。",    // 5
            "パスワードを入力してください。",
            "起動に失敗しました。\n\nIEインターネットオプション -> セキュリティ\nインターネット保護モードcheck、信頼済みサイト保護モードcheck \n",
            "一般認証書",

            "加入者",  // 9
            "バージョン",
            "シリアル番号",
            "発給者識別名",
            "次から有効",
            "次まで有効",
            "署名アルゴリズム", // 15
            "公開キーアルゴリズム",
            "公開キー",
            "署名値",
            "キー使用用途",
            "発給者キー識別者", // 20
            "被発給者キー識別者",
            "拡張キー使用用途",
            "追加被発給者名",
            "基本制限",
            "ポリシー", // 25
            "分配点",
            "OCSPサービス",
            "保存位置",

            "電子署名用",    // 29
            "暗号化用",
            "認証書発給機関",
            "最上位認証機関",

            "準備中です。",   // 33

            "ハッシュ値の検証",
            "追加情報",
            "認証書の検証",

            "$該当の認証書は次と同じ内容を含めています。", // 37
            "-発給者: ",
            "-加入者: ",
            "-発給日: ",   // 40
            "-満了日: ",
            "$検証結果: ",
            "すべての認証書の検証条件を満たしています。",
            "認証書の検証に失敗しました。",
            "$エラーコード: ",    // 45
            "なし",
            "$エラー情報: ",
            "$認証書の検証手順を完了しました。",
            "[*] パソコンの時間を必ず確認してください。",
            "\n",   // 50

            "成功",
            "認証書検索のご案内",
            "セッションが終了されました。",
            "有効期間満了の認証書です。",
            "電子署名中にエラーが発生しました。",    // 55
            "認証書ファイルをインポートしてください。",
            "PFXファイルが間違っているか、またはパスワードが一致しません。",
            "許可されない認証書です。",
            "アップデート",

            "セキュリティ媒体パスワードを5回以上間違えました。",   // 60
            "VestCertがロックしました。",
            "パスワードが一致しません。",
            "パスワードが何度か間違えました。",
            "セキュリティトークンがロックしました。",
            "セキュリティ媒体を使用するためには初期化が必要です。",   // 65
            "ウィンドウの右側(Tray)にて初期化を進行してください。",
            "infovine モジュールのインストールが必要です。インストールページへ移動します。",
            "infovine モジュールバージョンが低いです。アップグレードページへ移動します。",

            "ハードディスクが存在しません。ハードディスクを確認してください。", // 69
            "リムーバブルディスクが存在しません。リムーバブルディスクを確認してください。",
            "セキュリティトークンが繋がっていません。セキュリティトークンを繋がってください。",
            "ICトークンが繋がっていません。ICトークンを繋がってください。",

            "PFXインポートをクリックしてください。", // 73
            "'https:/'に接続可能です。",

            "キャンセルされました。",      // 75
            "infovine",
            "ICトークン",
            "携帯電話",
            "保存された認証書がありません。",
            "サポートされていません。",  // 80
            "電子署名",

            // html 언어
            "加入者名",	// 82
            "満了日",
            "政策等級",
            "発給機関",
            "認証書見る",
            "認証書削除",	// 87

            "パスワード",
            "認証書のパスワードは<br>大文字・小文字を区別します。",
            "確認",
            "取消",

            "“認証書管理プログラムがインストールまたは実行されていません。”",	// 92
            "インストールのご案内",
            "インストール",
            "実行",

            "“認証書検索メニューを利用すれば、プログラムのインストールなしに<br>電子署名が可能です。“",
            "認証書検索のご案内",	// 97
            "ここに認証書ファイル(*.PFX, *P12)を<br>ドラッグ＆ドロップしてください。",
            "認証書検索",

            "認証書内容表示",	// 100
            "属性",
            "値",	// 102

            "セキュリティ媒体選択",
            "認証書検証",
            "最上位認証機関の認証書信頼性を確認",		// 105
            "選択した最上位認証機関の認証書ハッシュ値は次と同じです。",
            "安全な認証書使用のためKISAホームページ(https://www.rootca.or.kr/cert/potency.html)を確認した後、最上位認証機関にて公開したハッシュ値と一致していることを確認してください。",
            "認証書の有効期間満了のお知らせ",
            "認証書の有効期限が近づいています。",	// 109
            "認証書満了日　: ",
            "認証書を更新してください。",
            "'更新'ボタンをクリックすると、実行された作業を中断して認証書更新画面に移動します。",
            "更新",	// 113
            "後で更新する場合は'確認'をクリックしてください。",
            "このメッセージはこれ以上表示できません。",

            "Report",	// 116
            "その他のエラー",

            "あなたが選択した認証書を使用することはできません。",	// 118
            "誤り件数",		// 119
            "あなたが削除してもよろしいですか??",
            "あなたが失効してもよろしいですか??",

            "認証書更新",
            "認証書廃棄",
            "認証書エクスポート",
            "パスワードの変更",
            "記憶媒体の変更",

            // 언어 세팅이 일본어일때 저장매체 영어로
            "HardDisk", // 127
            "Removable",
            "HSM",
            "Find Cert",
            "SecureDisk",
            "SaveToken",
            "Cell Phone",

            "USB媒体の選択",	// 134
            "携帯電話媒体の選択",

            "この証明書は、このサイトで許可されていません。",	// 136
            "パスワードを照合します",	// 137

            "Browser",	// 138
            "証明書を保存しますか？",
            "証明書が有効な状態です。",
            "証明書の有効期限が切れました。",
            "廃止された証明書です。",
            "証明書の検証結果の状態を確認できません。",		// 143
            "PINパスワード",

            "Mobisignモジュールのインストールが必要です。インストールページに接続します。",	// 145
            "モジュールのMobisignバージョンが低いです。アップグレードページに接続します。",
            "MobiSign",

            "DreamSecurity モジュールのインストールが必要です。インストールページに接続します。",	// 148
            "DreamSecurity",

            "証明書ストアの電話サービス（ UbiKey ）",	// 150
            "スマート認証（ USIM ）サービス",

            "保存トークンPIN番号を入力してください",	// 152
            "▶ 保存トークンPIN番号を入力してください",
            "保存トークンPIN番号",

            "保存トークンを選択",	// 155
            "Smart authentication",
            "BackupList",

            "PIN番号は大文字と小文字を<br>区別されます",	// 158

            "ネットワークドライブ",  // 159

            "CertificateList View",
            "Certificate Import",
            "PFX Import",
            "NPKI Import",
            "証明書はありません", // 164
            "Import",
            "Browser Certificate(individual)",
            "Browser Certificate(Corporation)", // 167
            "",
            "[Safari] [設定]に移動し、[ポップアップのブロック]を[常に許可]に変更します。"
        ],
        [
            "硬盘",    // 0
            "移动硬盘",
            "保安令牌",
            "认证书查找",
            "安全磁盘",

            "请选择认证书.",  // 5
            "请输入密码.",
            "驱动失败.\n\n IE互联网选项 -> 安全 \n  互联网保护模式 check, 可信任网址保护模式 check \n",
            "一般认证书",

            "用户",  // 9
            "版本",
            "序列号",
            "签发人 标识符",
            "下次起有效",
            "到下次为止有效",
            "签名算法",   // 15
            "被签发人公开密钥算法",
            "公开密钥",
            "署名值",
            "密钥用途",
            "签发者密码识别人", // 20
            "被签发者密码识别人",
            "扩展密码用途",
            "附加被签发人姓名",
            "基本限制",
            "政策",   // 25
            "分发点",
            "OCSP服务",
            "储存位置",

            "电子签名用",    // 29
            "加密用",
            "认证书发行机构",
            "最高认证机构",

            "准备中.",  // 33

            "hash值验证",
            "附加信息",
            "认证书验证",

            "$相关认证书包含以下内容",    // 37
            " -签发人: ",
            " -用户: ",
            " -签发日: ",    // 40
            " -到期日: ",
            "$验证结果: ",
            "符合所有的认证书验证条件.",
            "认证书验证失败.",
            "$错误代码",    // 45
            "没有",
            "$错误信息",
            "$认证书验证程序完成.",
            "[*] 请务必确认电脑的时间.",
            "\n",    // 50

            "成功",
            "认证书查找指南",
            "会话结束.",
            "到期的认证书.",
            "电子签名中出错.",  // 55
            "导入认证书文件.",
            "不是PFX文件或者密码错误.",
            "未被授权的认证书.",
            "升级",

            "安全媒体密码5次以上错误.",  // 60
            "VestCert被锁.",
            "密码不一致.",
            "密码多次错误.",
            "安全令牌被锁.",
            "使用安全媒体,需要进行初始化.", // 65
            "请在windows右侧(Tray)进行初始化.",
            "需安装infovine模块. 将为您连接安装页面.",
            "infovine模块为旧版本. 将为您连接升级页面.",

            "未发现硬盘. 请确认.", // 69
            "未发现移动硬盘. 请确认移动硬盘.",
            "安全令牌未连接. 请连接安全令牌.",
            "存储令牌未连接. 请连接存储令牌.",

            "请按PFX 导入.", // 73
            "可通过'https:/'连接.",

            "已取消.",      // 75
            "infovine",
            "储存令牌",
            "手机认证",
            "没有存储的认证书.",
            "不受支持的服务.",  // 80
            "电子签名",

            // 文本语言
            "用户名",	// 82
            "到期日",
            "政策等级",
            "发放机构",
            "认证书查看",
            "认证书删除",	// 87
            "认证书密码",
            "认证书密码区分大小写<br> ",
            "确认",
            "取消",

            "认证书管理程序未安装及运行.",	// 92
            "安装指南",
            "安装",
            "执行",

            "使用认证书查找菜单无需安装程序 <br>可使用电子签名.",	// 96
            "认证书查找指南",	// 97
            "将认证书文件(*.PFX, *P12)을 <br>拖进来.",
            "认证书查找",

            "认证书内容查看",	// 100
            "属性",
            "值",	// 102

            // 脚本语言
            "安全媒体选择",
            "认证书验证",
            "最高认证机构认证书可信度确认",	// 105
            "选择的最高认证机构的认证书hash值如下.",
            "为确保认证书的安全使用请到 KISA 主页(https://www.rootca.or.kr/cert/potency.html) , 确认与最高认证机构所提供的hash值是否一致.",	// 107
            "认证书到期通知",
            "认证书有效期即将到期.",	// 109
            "认证书到期日期 : ",
            "请升级认证书.",
            "按下'升级'键后当前执行的程序中断进入升级页面.",	// 112
            "升级",	// 113
            "将来升级请按 '确认'键.",
            "不再显示此信息.",

            "报告",	// 116
            "其他错误",

            "选择的认证书无法使用.",
            "错误次数",		// 119
            "确定删除吗??",
            "确定吊销吗??",

            "认证书升级",
            "任证书吊销",
            "认证书导出",
            "修改密码",
            "储存媒体变更",

            // 语言设置为日语时,储存媒体使用英语
            "硬盘", // 127
            "可移动的",
            "高速存储器",
            "认证书查找",
            "安全磁盘",
            "储存令牌",
            "手机",

            "移动式媒体选择",	// 134
            "手机认证媒体选择",

            "相关认证书在此网站不可使用.",	// 136
            "密码确认",	// 137

            "Browser",	// 138
            "认证书要储存吗?",
            "认证书为有效状态.",
            "认证书有效期到期.",
            "被吊销的认证书.",
            "认证书有效性验证结果无法确认.",		// 143
            "PIN 密碼",

            "該Mobisign模塊的安裝是必需的。連接到安裝頁面。",	// 145
            "模塊Mobisign版本較低。連接到升級頁面。",
            "MobiSign",

            "該DreamSecurity模塊的安裝是必需的。連接到安裝頁面。",	// 148
            "DreamSecurity",

            "證書存儲電話服務（UbiKey）",	// 150
            "智能驗證（USIM）服務",

            "请输入储存令牌PIN码.",	// 152
            "▶ 请输入储存令牌PIN码.",
            "保存令牌PIN號碼",

            "選擇保存令牌",	// 155
            "智能驗證",
            "備份列表",

            "PIN號碼是區分大小寫的結果",	// 158

            "網絡驅動器",  // 159

            "CertificateList View",
            "Certificate Import",
            "PFX Import",
            "NPKI Import",
            "没有证书可用", // 164
            "Import",
            "Browser Certificate(individual)",
            "Browser Certificate(Corporation)", // 167
            "",
            "请转到[Safari]设置，然后将“弹出窗口阻止程序”更改为“始终允许” "
        ]
    ];

    var brwoserLang = (function () {
        if (typeof (window.navigator.browserLanguage) === 'undefined')
            return window.navigator.language;
        return window.navigator.browserLanguage;
        // return 'ja-JP';
    })();

    var _config = VestSign.getConfig();
    if (_config.langIndex === undefined)
        return text[type[brwoserLang]][index];

    return text[_config.langIndex][index];
}