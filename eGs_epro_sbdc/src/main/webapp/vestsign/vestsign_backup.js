//version:  v1.0.0_bbedce0729323cbad920f06578a66dd2702c9b68
//update: Wed Sep 27 18:45:49 2017 +0900
//VestCert: v2.4.4_42

/**
 * Created by wjjung on 2014-10-22.
 */

/*
 _vestSignUrl ??? ????
 */
var vestSign = (function () {
    var _config = {
        baseUrl: '/vestsign/',                 // 절대경로 입력
        installFilePath: '/vestsign/VestCertSetup.exe',           // 설치파일 경로 입력(VestCert모듈)
        useGPKI: 1,                  // 1: NPKI, 16: GPKI, 17: NPKI+GPKI
        OIDFilter: 1,                 // 0: 스크립트에서 처리, 1: VestCert에서 처리

        // pfx 키 후킹 설정
        pfxKeystrokeEncryption: '',  // dill(예티), npkKeypad(잉카), ahnlabKeyhook(키보드후킹)[ASTX]
        pfxKeySaferConfig: {
            dill: {
                path: 'https://172.16.10.205:6800/dill/',   // 키보드보안 스크립트가 있는 폴더의 경로( 마지막 '/' 까지 표기)
                fileName: ['dill.server.js', 'dill.service_v9.9.9.js']
            },
            ahnlab: {
                path: 'https://172.16.10.205:6800/AOS2/',
                fileName: ['astx2.min.js', 'astx2_custom.js'],
                custCode: 2189
            },
            nProtect: {
                path: 'https://172.16.10.205:6800/pluginfree/js/',
                fileName: ['nppfs-1.9.0.js'],   // 잉카 키보드보안 스크립트 파일명
                param2: false,  // 방화벽
                param3: true,   // 키보드보안
                param4: false,  // 단말정보
                param5: true,   // 가상키패드
                staticParam5: false // 내부 로직에 따른 param5 변경여부 (true: 로직에 따른 변경X / false: 로직에 따른 변경O)
            }
        },

        // pc 키보드보안 설정
        keystrokeEncryption: '',  // npk(잉카), touchen(라온e2e), transkey(라온 가상키패드), kings, ahnlabKeyhook(키보드후킹)[ASTX], ahnlab(e2e)[ASTX], ahnlabOld(AOS)
        keySaferConfig: {
            nProtect: {
                path: 'https://172.16.10.205:6800/pluginfree/js/', // 키보드보안 스크립트가 있는 폴더의 경로( 마지막 '/' 까지 표기)
                fileName: ['nppfs-1.9.0.js'],   // 잉카 키보드보안 스크립트 파일명
                param2: false,  // 방화벽
                param3: true,   // 키보드보안
                param4: false,  // 단말정보
                param5: true    // 가상키패드
            },
            ahnlab: {
                path: 'https://172.16.10.205:6800/AOS2/',
                fileName: ['astx2.min.js', 'astx2_custom.js'],
                custCode: 2189
            },
            ahnlabOld: {
                path: '',
                fileName: ['aosmgr_common.js']
            },
            touchen: {                 // 라온(TouchEn) 옵션
                path: 'https://172.16.10.205:6800/TouchEn/',
                fileName: '[nxKey/js/TouchEnNxYT.js]'
            },
            transkey: {                // 라온(가상키패드) 좌표값
                path: 'https://172.16.10.205:6800/transkey/',
                fileName: ["transkey.js", "rsa_oaep_files/rsa_oaep-min.js", "jsbn/jsbn-min.js", "TranskeyLibPack_op.js"],
                position: {
                    x: -224,
                    y: -250
                }
            },
            kings: {
                path: '',
                fileName: []
            }
        },

        // mobile 키보드보안 설정
        mobileKeystrokeEncryption: 'dill',  // npk(잉카), dill(예티)
        mobileKeySaferConfig: {
            nProtect: {
                path: 'https://172.16.10.205:6800/pluginfree/js/',
                fileName: ['nppfs-1.9.0.js'],   // 잉카 키보드보안 스크립트 파일명
                param2: false,  // 방화벽
                param3: true,   // 키보드보안
                param4: false,  // 단말정보
                param5: true    // 가상키패드
            },
            dill: {
                path: 'https://172.16.10.205:6800/dill/',
                fileName: ['dill.server.js', 'dill.service.js']
            }
        },
        storage: {  // 저장매체 able/disable 처리(gray)
            hardDisk: true,           // 하드디스크
            usbDisk: true,            // 이동식디스크
            secureToken: true,      // 보안토큰
            saveToken: true,         // 저장토큰
            certificateFile: true,     // 인증서찾기
            secureDisk: true,        // 안전디스크
            smartPhone: true,      // 휴대폰인증
            webStorage: false,      // 웹저장소
            // 스마트인증은 매체 on/off 처리
            dreamService: false      // 드림시큐리티 USIM인증
        },
        vestCert: true,                             // vestCert isFunction
        version: {
            vestCert: '2.5.9',                       // vestCert version
            vestCertSecurityService: '2.5.9'   // vestCertSecurityService version
        },
        infovine: {
            wParam: 'INFOVINE|NULL|',               // 인포바인 설정(인포바인이 수정)
            lParam: 'YETTIE|NULL|NULL|',             // 인포바인 설정(인포바인이 수정)
            version: '1,3,0,2',                              // 인포바인 설정(인포바인이 수정)
            url: ''
        },
        dreamsecurity: {
            enable: true,                                   // dream 사용여부
            url: 'http://download.smartcert.kr/',               // dream 설치안내페이지

            tokenorder: 'Mobile_SmartCert',         // dream 모듈 우선 동작
            sitecode: '0000000000',                     // raon에서 발급, 사이트 별 코드
            modcode: '00012',                           // raon에서 발급, 12번은 기타
            siteURL: 'www.crosscert.com',            // dream 에서 발급, 적용된 사이트 URL
            serviceIP: 'service.smartcert.kr',          // dream 에서 발급, 고정된 값
            servicePort: '443'                            // dream 에서 발급, 고정된 값
        },
        mobisign: {
            enable: true,                                               // mobisign 사용여부
            url: 'http://www.mobisign.kr/mobisigndll.htm',  // mobisign 설치안내페이지
            version: '5,0,4,4',    //'3,1,1,1',                          // mobisign 모듈 버전
            sitecode: '1234567',
            acceptList: '0'                                              // mobisign OID 체크

            // mobisign OID list
            // '총 허용가능한 인증서 정책 수;발급자 CA Name;허용가능한 OID;발급자 CA Name;허용가능한 OID;' 형식
            // acceptList: '34;yessignCA;1.2.410.200005.1.1.1;yessignCA;1.2.410.200005.1.1.5;yessignCA;1.2.410.200005.1.1.4;yessignCA;1.2.410.200005.1.1.2;yessignCA;1.2.410.200005.1.1.6.1;yessignCA Class 1;1.2.410.200005.1.1.1;yessignCA Class 1;1.2.410.200005.1.1.5;yessignCA Class 1;1.2.410.200005.1.1.4;yessignCA Class 1;1.2.410.200005.1.1.2;yessignCA Class 1;1.2.410.200005.1.1.6.1;signGATE CA;1.2.410.200004.5.2.1.2;signGATE CA;1.2.410.200004.5.2.1.1;signGATE CA;1.2.410.200004.5.2.1.7.1;signGATE CA4;1.2.410.200004.5.2.1.2;signGATE CA4;1.2.410.200004.5.2.1.1;signGATE CA4;1.2.410.200004.5.2.1.7.1;SignKorea CA;1.2.410.200004.5.1.1.5;SignKorea CA;1.2.410.200004.5.1.1.7;SignKorea CA2;1.2.410.200004.5.1.1.5;SignKorea CA2;1.2.410.200004.5.1.1.7;NCASign CA;1.2.410.200004.5.3.1.2;NCASign CA;1.2.410.200004.5.3.1.9;CrossCert Certificate Authority;1.2.410.200004.5.4.1.1;CrossCert Certificate Authority;1.2.410.200004.5.4.1.2;CrossCert Certificate Authority;1.2.410.200004.5.4.1.101;CrossCertCA2;1.2.410.200004.5.4.1.1;CrossCertCA2;1.2.410.200004.5.4.1.2;CrossCertCA2;1.2.410.200004.5.4.1.101;TradeSignCA;1.2.410.200012.1.1.1;TradeSignCA;1.2.410.200012.1.1.3;TradeSignCA;1.2.410.200012.1.1.101;TradeSignCA2;1.2.410.200012.1.1.1;TradeSignCA2;1.2.410.200012.1.1.3;TradeSignCA2;1.2.410.200012.1.1.101;'
        },

        signErrMaxValue: 5,                     // 서명 에러 제한 횟수
        useDrag: true,                          // html창 드래그 설정 (true: 사용(default), false: 사용안함)
        useBracket: true,                       // 에러메시지 소괄호 설정 (true: 사용(default), false: 사용안함)
        storageInfoTextColor: true,             // 저장매체선택 설명text색 설정 (true: 빨강(default), false: 검정(변경))
        listsViewDopen: false,                  // true: 드라이브가 하나일때 선택함, false: 선택안함
        expiredCertFilter: true,                // 전자서명시 만료된 인증서 필터링 여부(true: 필터링O, false: 필터링X)

        mobileExpiredCertFilter: true,          // 모바일에서 전자서명시 만료된 인증서 필터링 여부(true: 필터링O, false: 필터링X)
        slothPath: '',                          // '' 빈값일 경우 https://location.host 설정
        slothVersion: '1.0',                    // 앱에 전송할 스크립트 버전
        useApp: true,                           // true: app사용, false: webapp사용
        useWebAppSloth: false,                  // true: webApp Sloth사용, false: webApp Sloth미사용
        hiddenImageImportFromPhone: false,      // 휴대폰에서가져오기 이미지 숨기기(true: 숨김, false: 보임)
        // user setting list end

        /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
        // 초기화 값 입니다. 수정하시면 안됩니다..
        title: '',                   // 창 상단부 타이틀(기능에따라 설정됨)
        btnId: '',                 // yettie.sign을 호출하는 elementID
        ablePwd: true,        // 비밀번호창 활성화 여부(기능에따라 설정됨)
        certificateClass: 0,   // 0 – 없음,  1 – 개인 범용,  16 – 법인 범용,  256 – 증권 전용 개인
        OID: '0',                // ex) '0' / '1.2.410.200004.5.1.1.5'/'1.2.410.200004.5.1.1.5;1.2.410.200004.5.1.1.7;1.2.410.200004.5.1.1.9'
        issuerFilter: '0',       // ex) 'SignKorea Test CA3;'
        langIndex: 0          // 0: 한국어, 1: 영어, 2: 일본어, 3: 중국어
        /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
    };

    var VestSign = function () {
        var _iframe,
            _iframeOpen = false,
            _layer,
            _layerCount = 0,
            _plain,
            _option,
            _callback,
            _errorcallback,
            _library,
            _loading,
            _func;

        var _url = '',
            _iframeName = '',
            _width = '',
            _height = '',
            _isMobile = false;

        var _this = this;

        _this._url = {
            sign: {
                url: 'manage.html',
                iframeName: 'yettie_sign',
                width: 430,
                height: 496,
                mobile: false
            },
            storageSelected: {
                url: 'storageSelected.html',
                iframeName: 'yettie_storageSelected',
                width: 430,
                height: 330,
                mobile: false
            },
            securediskStorageSelected: {
                url: 'securediskStorageSelected.html',
                iframeName: 'yettie_securediskStorageSelected',
                width: 430,
                height: 330,
                mobile: false
            },
            confirm: {
                url: 'confirm.html',
                iframeName: 'yettie_confirm',
                width: 430,
                height: 225,
                mobile: false
            },
            passwordType1: {
                url: 'inputPin.html',
                iframeName: 'yettie_inputPin_iframe',
                width: 430,
                height: 210,
                mobile: false
            },
            passwordType2: {
                url: 'checkPin.html',
                iframeName: 'yettie_checkPin_iframe',
                width: 430,
                height: 380,
                mobile: false
            },
            passwordType3: {
                url: 'changePin.html',
                iframeName: 'yettie_changePin_iframe',
                width: 430,
                height: 430,
                mobile: false
            },
            exportToWeb: {
                url: 'exportToWeb.html',
                iframeName: 'yettie_exportToWeb_iframe',
                width: 430,
                height: 430,
                mobile: false
            },
            notMatchedPassword: {
                url: 'notMatchedPassword.html',
                iframeName: 'yettie_notMatchedPassword_iframe',
                width: 430,
                height: 340,
                mobile: false
            }
        };

        _this.setParameters = function (plain, option, callback, errorcallback) {
            _plain = plain;
            _option = JSON.stringify(option);
            _callback = callback;
            _errorcallback = errorcallback;
        };

        _this.addConfig = function (config) {
            for (var i in config) {
                if (config.hasOwnProperty(i)) {
                    _config[i] = config[i];
                }
            }
        };

        _this.setting = function () {
            _this.open = iframeOpen;
            _this.getParameters = iframegetParameters;
            _this.close = iframeClose;
        };

        _this.setURL = function (object) {
            _url = object.url;
            _iframeName = object.iframeName;
            _width = object.width;
            _height = object.height;
            _isMobile = object.mobile;
        };

        _this.getConfig = function () {
            return _config;
        };

        _this.callVestCert = function (secKey) {
            window.location.href = "MangoWire://" + secKey;
        };

        _this.setFunction = function (func) {
            _func = func;
        };

        _this.getFunction = function () {
            return _func;
        };

        function iframeOpen() {
            if (_iframeOpen) return;

            _iframe = document.createElement('iframe');
            _iframe.id = 'yettie_iframe';
            _iframe.name = 'yettie_iframe';
            _iframe.src = _config.baseUrl + 'views/' + _url;
            _iframe.title = _config.title;
            _iframe.scrolling = "no";
            _iframe.style.position = 'fixed';
            _iframe.style.zIndex = "9999";
            _iframe.style.border = "none";

            if (_isMobile) {
                _iframe.style.top = "0%";
                _iframe.style.left = "0%";

                _iframe.width = "100%";
                _iframe.height = "100%";

                _iframe.style.opacity = 0;
            } else {
                _iframe.style.top = "50%";
                _iframe.style.left = "50%";

                _iframe.width = _width;
                _iframe.height = _height;

                _iframe.style.marginTop = "-" + _height / 2 + "px";
                _iframe.style.marginLeft = "-" + _width / 2 + "px";
            }

            vestSign.scrollDisable();
            vestSign.layerDisable();

            var element = document.getElementById(_config.btnId);
            if (_config.btnId === '' || _config.btnId === undefined || element === null) {
                document.body.appendChild(_iframe);
            }
            else {
                element.parentElement.insertBefore(_iframe, element);
            }
            _iframeOpen = true;
        }

        function iframegetParameters() {
            window.frames['yettie_iframe'].params.setParameters(
                _plain, _option, _callback, _errorcallback
            );
        }

        function iframeClose() {
            var btnIdElement = document.getElementById(_config.btnId);

            if (btnIdElement === '' || btnIdElement === undefined || btnIdElement === null) {
                document.getElementById('yettie_iframe').parentNode.removeChild(_iframe);
            } else {
                btnIdElement.parentElement.removeChild(_iframe);
            }

            vestSign.scrollEnable();
            vestSign.layerEnable();
            _iframeOpen = false;
        }

        _this.layerDisable = function () {
            var getIEVersion = function () {
                var ua = window.navigator.userAgent;
                var ie = ua.indexOf("MSIE");
                return ((ie > 0) ? parseInt(ua.substring(ie + 5, ua.indexOf(".", ie))) : 0);
            };

            _layerCount += 1;
            if (_layer != undefined)
                return;

            _layer = document.createElement('div');
            _layer.style.position = 'fixed';
            _layer.style.top = 0;
            _layer.style.left = 0;
            _layer.style.width = '100%';
            _layer.style.height = '100%';
            _layer.style.zIndex = "100";
            _layer.style.backgroundColor = '#000000';
            document.body.appendChild(_layer);

            var iVer = getIEVersion();

            if (iVer == 8) {
                _layer.style.cssText += "; -ms-filter: 'progid:DXImageTransform.Microsoft.Alpha(Opacity=72)';" /* IE 8 */
                + "; filter = progid:DXImageTransform.Microsoft.Alpha(Opacity=72)";
                /* IE 7 and olders */
            } else if (iVer > 0 && iVer <= 7) {
                _layer.style.filter = "progid:DXImageTransform.Microsoft.Alpha(Opacity=72)";
                /* IE 7 and olders */
            } else {
                _layer.style.opacity = 0.72;
            }
        }

        _this.layerEnable = function () {
            if (_layerCount == 1) {
                document.body.removeChild(_layer);
                _layer = undefined;
            }
            _layerCount -= 1;
        }

        _this.scrollEnable = function () {
            var html = document.getElementsByTagName('html')[0];
            html.style.overflow = '';
        }

        _this.scrollDisable = function () {
            var html = document.getElementsByTagName('html')[0];
            html.style.overflow = 'hidden';
        }

        function setLoadingLibrary() {
            _loading = document.createElement('div');
            _loading.id = 'yettie_library_loading';
            _loading.style.position = 'absolute';
            _loading.style.left = '0';
            _loading.style.right = '0';
            _loading.style.top = '0';
            _loading.style.bottom = '0';
            _loading.style.zIndex = '9999';
            _loading.style.background = 'url(' + _config.baseUrl + 'styles/images/loading-animation-6.gif) no-repeat center center';

            vestSign.layerDisable();
            document.body.appendChild(_loading);
            yettie.loadingLibrary();
        }

        _this.libraryOpen = function () {
            _library = document.createElement('iframe');
            _library.id = 'yettie_library_iframe';
            _library.name = 'yettie_library_iframe';

            _library.src = _config.baseUrl + 'views/library.html';
            _library.title = "library";
            _library.width = "0px";      //'390';
            _library.height = "0px";    //'400';
            _library.scrolling = "no";
            _library.frameborder = "no";
            _library.style.top = "50%";
            _library.style.left = "50%";
            _library.style.marginTop = "-200px";
            _library.style.marginLeft = "-195px";
            _library.style.position = 'fixed';
            _library.style.zIndex = "0";
            _library.style.border = "none";

            document.body.appendChild(_library);
            setLoadingLibrary();
        };

        _this.addScripts = function () {
            if (window.addEventListener) {
                window.addEventListener('load', vestSign.libraryOpen, false);
            } else if (window.attachEvent) {
                window.attachEvent('on' + 'load', vestSign.libraryOpen);
            }
        };
    };

    return new VestSign();
})();

/**
 * VestSign API의 기본 접근자.
 * @namespace yettie
 */
var yettie = {};

/**
 * VestSign의 기본 설정 값을 변경할 때 사용하는 함수
 * @param   {Object}    config  초기화 할 config객체
 */
yettie.init = function (config) {
    if (typeof(config) !== 'object') {
        alert('Invalid config');
        return;
    }
    vestSign.addConfig(config);
};

/**
 * VestSign의 문자열 인코딩 & 타입을 변경하는 기능을 수행하는 함수이다.
 * @namespace yettie.util
 */
yettie.util = {
    /**
     * 평문을 Base64 인코딩
     * @param {String} plain 평문
     * @return {String} Base64로 인코딩 된 문자열
     */
    encodeBase64: function (plain) {
        return vest.signHelper.encodeBytes(plain, 'base64');
    },
    /**
     * 평문을 Hex 인코딩
     * @param {String} plain 평문
     * @return {String} Hex로 인코딩 된 문자열
     */
    encodeHex: function (plain) {
        return vest.signHelper.encodeBytes(plain, 'hex');
    },
    /**
     * Base64로 인코딩 된 문자열을 디코딩
     * @param {String} plain Base64로 인코딩 된 문자열
     * @return {String} 디코딩 된 문자열
     */
    decodeBase64: function (plain) {
        return vest.signHelper.decodeString(plain, 'base64');
    },
    /**
     * Hex로 인코딩 된 스트링을 디코딩
     * @param {String} plain Hex로 인코딩 된 문자열
     * @return {String} 디코딩 된 문자열
     */
    decodeHex: function (plain) {
        return vest.signHelper.decodeString(plain, 'hex');
    },
    /**
     * Pem 형식의 문자열을 Base64 인코딩
     * @param {String} pem Pem형식의 문자열
     * @return {String} Base64로 인코딩 된 문자열
     */
    pemToBase64: function (pem, title) {
        return vest.signHelper.pemToBase64(pem);
    },
    /**
     * Base64로 인코딩 된 문자열을 Pem 형식의 문자열로 디코딩
     * @param {String} plain    Base64로 인코딩 된 문자열
     * @param {Number} titleNum 옵션(0: "RSA PRIVATE KEY" / 1: "CERTIFICATE" / 2: "PRIVATEKEY")
     * @return {String} Pem 형식의 문자열.
     */
    base64ToPem: function (plain, titleNum) {
        // titleNum
        // 0: "RSA PRIVATE KEY"
        // 1: "CERTIFICATE"
        // 2: "PRIVATEKEY"
        return vest.signHelper.base64ToPem(plain, titleNum);
    }
};

/**
 * VestSign의 안전디스크를 백업하는 기능을 수행하는 함수이다.
 *
 * @param   {Object}   option 해당 기능에 필요한 option 값
 *
 * @param   {Function} callback     안전디스크 백업 완료 시 결과를 받는 callback 함수
 * @param   {Boolean}  callback.result    성공 시 true 반환
 *
 * @param   {Function} errorcallback        실패 시 결과를 받는 callback 함수
 * @param   {Object}   errorcallback.result        결과 값
 * @param   {String}   errorcallback.result.msg    사용자가 사용할 에러문
 * @param   {Number}   errorcallback.result.code   기반 에러코드
 * @param   {Array}    errorcallback.result.reason 기반 에러문
 *
 */
yettie.securediskBackup = function (option, callback, errorcallback) {
    vestSign.addConfig({signTitle: 'SIGN'});
    vestSign.addConfig({ablePwd: true});
    vestSign.addConfig({secureDiskBackup: true});
    vestSign.addConfig({
        storage: {
            hardDisk: false,
            usbDisk: false,
            secureToken: false,
            saveToken: false,
            certificateFile: false,
            secureDisk: true,
            smartPhone: false,
            webStorage: false,

            secureDiskRestore: false
        }
    });

    vestSign.setParameters("", option, function (tokenNumber, identifier, password, option, vestCertErrorHandler) {
        var sto_config = {
            storageSelectedFlag: false,
            storageSelectedTokenNumber: tokenNumber
        };
        vestSign.addConfig(sto_config);

        vest.token.verifyPin(tokenNumber, identifier, password, function () {
            vestSign.close();   // 핀이 맞으면 창닫음.
            yettie.securediskStorageSelected(false, option, function (newTokenNumber, deleteCert) {
                // option = {pkitype: vestSign.getConfig().useGPKI};
                vest.token.BackupSecureDiskCertificate(tokenNumber, newTokenNumber, identifier, password, option, callback, function (error) {
                    vest.util.refactoryMsg.convertMsg(error);
                    errorcallback(error);
                });
            }, errorcallback);
        }, vestCertErrorHandler);
    }, function (error) {
        vest.util.refactoryMsg.convertMsg(error);
        errorcallback(error);
        vestSign.close();
    });

    vestSign.setURL(vestSign._url.sign);
    vestSign.setting();
    vestSign.open();
};

/**
 * VestSign의 안전디스크를 복구하는 기능을 수행하는 함수이다.
 *
 * @param   {Object}   option 해당 기능에 필요한 option 값
 *
 * @param   {Function} callback     안전디스크 복구 완료 시 결과를 받는 callback 함수
 * @param              callback.    성공 시 함수 실행
 *
 * @param   {Function} errorcallback        실패 시 결과를 받는 callback 함수
 * @param   {Object}   errorcallback.result        결과 값
 * @param   {String}   errorcallback.result.msg    사용자가 사용할 에러문
 * @param   {Number}   errorcallback.result.code   기반 에러코드
 * @param   {Array}    errorcallback.result.reason 기반 에러문
 *
 */
yettie.securediskRestore = function (option, callback, errorcallback) {
    vestSign.addConfig({ablePwd: true});
    vestSign.addConfig({secureDiskBackup: true});
    vestSign.addConfig({
        storage: {
            hardDisk: false,
            usbDisk: false,
            secureToken: false,
            saveToken: false,
            certificateFile: false,
            secureDisk: false,
            smartPhone: false,
            webStorage: false,

            secureDiskRestore: true
        }
    });

    vest.token.getTokenList(vest.token.TYPE.SECUREDISK, function (list) {
        vestSign.setParameters('', {}, function (tokenNumber, identifier, password, option, vestCertErrorHandler) {
            vest.util.removeObjectElement(option);
            vest.token.RestoreSecureDiskCertificate(tokenNumber, list[0].tokenIdentifier, identifier, password, option, function () {
                vest.token.tray("yettie", "off", undefined, function () {
                }, function () {
                });
                callback();
                vestSign.close();
            }, vestCertErrorHandler);
        }, function (error) {
            vest.util.refactoryMsg.convertMsg(error);
            errorcallback(error);
            vestSign.close();
        });
        vestSign.setURL(vestSign._url.sign);
        vestSign.setting();
        vestSign.open();
    });
};

/**
 * VestSign의 인증서 리스트를 보여주는 기능을 수행하는 함수이다.
 *
 * @param   {Function} callback     안전디스크 복구 완료 시 결과를 받는 callback 함수
 * @param              callback.    성공 시 함수 실행
 *
 * @param   {Function} errorcallback        실패 시 결과를 받는 callback 함수
 * @param   {Object}   errorcallback.result        결과 값
 * @param   {String}   errorcallback.result.msg    사용자가 사용할 에러문
 * @param   {Number}   errorcallback.result.code   기반 에러코드
 * @param   {Array}    errorcallback.result.reason 기반 에러문
 *
 */
yettie.showContents = function (callback, errorcallback) {
    var option = {};
    vestSign.addConfig({signTitle: 'SHOW'});
    vestSign.addConfig({ablePwd: false});
    vestSign.addConfig({ablePfxPwd: false});

    vestSign.setParameters("", option, function (tokenNumber, identifier, password, option, vestCertErrorHandler) {
        vest.token.tray("yettie", "off", undefined, function () {
            callback();
            vestSign.close();
        }, function () {
            callback();
            vestSign.close();
        });
    }, function (error) {
        vest.util.refactoryMsg.convertMsg(error);
        errorcallback(error);
        vestSign.close();
    });

    vestSign.setURL(vestSign._url.sign);
    vestSign.setting();
    vestSign.open();
};

/**
 * VestSign의 VestCert 트레이 아이콘을 보이게 하는 기능을 수행하는 함수이다.
 *
 * @param   {Function} callback     트레이 ON 성공 시 결과를 받는 callback 함수
 * @param   {Boolean}  callback.result    성공 시 true 반환
 *
 */
yettie.trayOn = function (callback) {
    vest.token.tray("yettie", "on", undefined, function (result) {
        callback(result);
    }, function (error) {
        callback(error);
    });
};

/**
 * VestSign의 VestCert 트레이 아이콘을 보이지 않게 하는 기능을 수행하는 함수이다.
 *
 * @param   {Function} callback     트레이 OFF 성공 시 결과를 받는 callback 함수
 * @param   {Boolean}  callback.result    성공 시 true 반환
 *
 */
yettie.trayOff = function (callback) {
    vest.token.tray("yettie", "off", undefined, function (result) {
        callback(result);
    }, function (error) {
        callback(error);
    });
};

/**
 * VestSign의 VID 검증 기능을 수행하는 함수이다.
 *
 * @param   {String} idn
 *
 * @param   {Object}   option 해당 기능에 필요한 option 값
 *
 * @param   {Function} callback     VID 검증 완료 시 결과를 받는 callback 함수
 * @param   {Boolean}  callback.result    성공 시 true 반환
 *
 * @param   {Function} errorcallback        실패 시 결과를 받는 callback 함수
 * @param   {Object}   errorcallback.result        결과 값
 * @param   {String}   errorcallback.result.msg    사용자가 사용할 에러문
 * @param   {Number}   errorcallback.result.code   기반 에러코드
 * @param   {Array}    errorcallback.result.reason 기반 에러문
 *
 */
yettie.verifyVID = function (idn, option, callback, errorcallback) {
    vestSign.addConfig({signTitle: 'SIGN'});
    vestSign.addConfig({ablePwd: true});
    vestSign.addConfig({ablePfxPwd: true});

    if (typeof(option) !== 'object') {
        alert('Invalid option');
        return;
    }

    if (typeof(callback) !== 'function') {
        alert('Invalid callback function');
        return;
    }

    vestSign.setParameters('', option, function (tokenNumber, identifier, password, option, vestCertErrorHandler) {
        vest.token.verifyVID(tokenNumber, identifier, password, idn, function (vidResult) {
            vest.token.tray("yettie", "off", undefined, function () {
                vestSign.close();
                callback(vidResult);
            }, function (error) {
                vestSign.close();
            });
        }, vestCertErrorHandler);
    }, function (error) {
        vest.util.refactoryMsg.convertMsg(error);
        errorcallback(error);
        vestSign.close();
    });

    vestSign.setURL(vestSign._url.sign);
    vestSign.setting();
    vestSign.open();
};

/**
 * VestSign의 전자서명과 VID 검증을 동시에 수행하는 함수이다.
 *
 * @param   {String}   plain 전자서명할 서명 원문
 *
 * @param   {String} idn
 *
 * @param   {Object}   option 해당 기능 수행에 필요한 option 값
 * @param   {String}   option.encoding  스크립트에서 사용할 문자열 인코딩 방식 ("base64", "hex")
 * @param   {String}   option.charset   스크립트에서 사용할 한글 인코딩 형식 ("utf-8", "euc-kr")
 *
 * @param   {Function} callback               서명+검증 완료 시 결과를 받는 callback 함수
 * @param   {Object}   callback.result        결과 값
 * @param   {String}   callback.result.signature     전자서명문
 * @param   {String}   callback.result.resultCode    vid 결과 코드
 *
 * @param   {Function} errorcallback        실패 시 결과를 받는 callback 함수
 * @param   {Object}   errorcallback.result        결과 값
 * @param   {String}   errorcallback.result.msg    사용자가 사용할 에러문
 * @param   {Number}   errorcallback.result.code   기반 에러코드
 * @param   {Array}    errorcallback.result.reason 기반 에러문
 *
 */
yettie.signWithVerifyVID = function (plain, idn, option, callback, errorcallback) {
    vestSign.addConfig({signTitle: 'SIGN'});
    vestSign.addConfig({ablePwd: true});
    vestSign.addConfig({ablePfxPwd: true});

    if (Object.prototype.toString.call(plain) === '[object Array]')
        ;
    else if (typeof(plain) !== 'string') {
        alert('Plain have to be a string');
        return;
    }

    if (typeof(option) !== 'object') {
        alert('Invalid option');
        return;
    }

    if (typeof(callback) !== 'function') {
        alert('Invalid callback function');
        return;
    }

    vestSign.setParameters(plain, option, function (tokenNumber, identifier, password, option, vestCertErrorHandler) {
        vest.token.makeSignature(tokenNumber, password, identifier, plain, option, function (result) {
            vest.token.verifyVID(tokenNumber, identifier, password, idn, function (vidResult) {
                var res = {
                    signature: result.signature,
                    resultCode: vidResult.resultCode
                };
                vest.token.tray("yettie", "off", undefined, function () {
                    vestSign.close();
                    callback(res);
                }, function (error) {
                    vestSign.close();
                });
            }, vestCertErrorHandler);
        }, vestCertErrorHandler);
    }, function (error) {
        vest.util.refactoryMsg.convertMsg(error);
        errorcallback(error);
        vestSign.close();
    });

    vestSign.setURL(vestSign._url.sign);
    vestSign.setting();
    vestSign.open();
};

/**
 * VestSign의 전자서명 기능을 수행하는 함수이다.<br>
 * 또한 해당 함수를 통해 인증서 목록을 보여주며 인증서 선택을 통해 전자서명 기능을 수행한다.
 *
 * @param   {String}   plain 전자서명할 서명 원문
 *
 * @param   {Object}   option 해당 기능 수행에 필요한 option 값
 * @param   {String}   option.encoding  스크립트에서 사용할 문자열 인코딩 방식 ("base64", "hex")
 * @param   {String}   option.charset   스크립트에서 사용할 한글 인코딩 형식 ("utf-8", "euc-kr")
 * @param   {String}   option.signType  전자서명 형태 (default(PKCS#7서명): "2")
 *
 * @param   {Function} callback               성공 시 결과를 받는 callback 함수
 * @param   {Object}   callback.result        결과 값
 * @param   {String}   callback.result.signature     전자서명문
 * @param   {String}   callback.result.vidMessage    vid 메시지
 * @param   {Object}   callback.result.extentions
 * @param   {String}   callback.result.extentions.dn           전자서명에 사용된 인증서 DN 값
 * @param   {String}   callback.result.extentions.certificate  전자서명에 사용된 인증서를 byte 인코딩한 값
 * @param   {String}   callback.result.extentions.r            전자서명에 사용된 인증서 R 값
 * @param   {String}   callback.result.extentions.realName     전자서명에 사용된 인증서 Name
 *
 * @param   {Function} errorcallback        실패 시 결과를 받는 callback 함수
 * @param   {Object}   errorcallback.result        결과 값
 * @param   {String}   errorcallback.result.msg    사용자가 사용할 에러문
 * @param   {Number}   errorcallback.result.code   기반 에러코드
 * @param   {Array}    errorcallback.result.reason 기반 에러문
 *
 */
yettie.sign = function (plain, option, callback, errorcallback) {
    vestSign.addConfig({signTitle: 'SIGN'});
    vestSign.addConfig({ablePwd: true});
    vestSign.addConfig({ablePfxPwd: true});

    if (Object.prototype.toString.call(plain) === '[object Array]')
        ;
    else if (typeof(plain) !== 'string') {
        alert('Plain have to be a string');
        return;
    }

    if (typeof(option) !== 'object') {
        alert('Invalid option');
        return;
    }

    if (typeof(callback) !== 'function') {
        alert('Invalid callback function');
        return;
    }

    vestSign.setParameters(plain, option, function (tokenNumber, identifier, password, option, vestCertErrorHandler) {
        if (tokenNumber instanceof vest.pki.CertificateSet) {
            tokenNumber.makeSignature(tokenNumber.getCertificate(), plain, password, option, function (result) {
                result.storageType = option.storageType;
                result.serial = option.serial;

                callback(result);
                vestSign.close();
            }, vestCertErrorHandler);

        }
        else {
            var tempOption = {
                storageType: option.storageType,
                serial: option.serial
            };
            vest.util.removeObjectElement(option);
            vest.token.makeSignature(tokenNumber, password, identifier, plain, option, function (result) {
                vest.token.tray("yettie", "off", undefined, function () {
                    result.storageType = tempOption.storageType;
                    result.serial = tempOption.serial;
                    callback(result);

                    vestSign.close();
                }, function (error) {
                    vestSign.close();
                });
            }, vestCertErrorHandler);
        }
    }, function (error) {
        vest.util.refactoryMsg.convertMsg(error);
        errorcallback(error);
        vestSign.close();
    });

    vestSign.setURL(vestSign._url.sign);
    vestSign.setting();
    vestSign.open();
};

/**
 * VestSign의 파일 전자서명 기능을 수행하는 함수이다.
 *
 * @param   {String}   outfile 파일 전자서명을 위한 파일 경로
 *
 * @param   {String}   plain 전자서명할 서명 원문
 *
 * @param   {Object}   option 해당 기능 수행에 필요한 option 값
 * @param   {String}   option.encoding  스크립트에서 사용할 문자열 인코딩 방식 ("base64", "hex")
 * @param   {String}   option.charset   스크립트에서 사용할 한글 인코딩 형식 ("utf-8", "euc-kr")
 * @param   {String}   option.signType  전자서명 형태 (default(PKCS#7서명): "2")
 *
 * @param   {Function} callback               성공 시 결과를 받는 callback 함수
 * @param   {Object}   callback.result        결과 값
 * @param   {String}   callback.result.signature     서명결과를 저장할 파일경로
 * @param   {String}   callback.result.vidMessage    vid 메시지
 * @param   {Object}   callback.result.extentions
 * @param   {String}   callback.result.extentions.dn           전자서명에 사용된 인증서 DN 값
 * @param   {String}   callback.result.extentions.certificate  전자서명에 사용된 인증서를 byte 인코딩한 값
 * @param   {String}   callback.result.extentions.r            전자서명에 사용된 인증서 R 값
 * @param   {String}   callback.result.extentions.realName     전자서명에 사용된 인증서 Name
 *
 * @param   {Function} errorcallback        실패 시 결과를 받는 callback 함수
 * @param   {Object}   errorcallback.result        결과 값
 * @param   {String}   errorcallback.result.msg    사용자가 사용할 에러문
 * @param   {Number}   errorcallback.result.code   기반 에러코드
 * @param   {Array}    errorcallback.result.reason 기반 에러문
 *
 */
yettie.fileSign = function (outfile, plain, option, callback, errorcallback) {
    option.signInputType = 2;
    option.signOutputType = 1;
    option.outputfile = outfile;

    vestSign.addConfig({signTitle: 'SIGN'});
    vestSign.addConfig({ablePwd: true});
    vestSign.addConfig({ablePfxPwd: true});

    if (Object.prototype.toString.call(plain) === '[object Array]')
        ;
    else if (typeof(plain) !== 'string') {
        alert('Plain have to be a string');
        return;
    }

    if (typeof(option) !== 'object') {
        alert('Invalid option');
        return;
    }

    if (typeof(callback) !== 'function') {
        alert('Invalid callback function');
        return;
    }

    vestSign.setParameters(plain, option, function (tokenNumber, identifier, password, option, vestCertErrorHandler) {
        if (tokenNumber instanceof vest.pki.CertificateSet) {
            identifier.storageType = option.storageType;
            identifier.serial = option.serial;
            callback(identifier);

            vest.util.removeObjectElement(option);
            vestSign.close();
        }
        else {
            var tempOption = {
                storageType: option.storageType,
                serial: option.serial
            };
            vest.util.removeObjectElement(option);

            vest.token.makeSignature(tokenNumber, password, identifier, plain, option, function (result) {
                vest.token.tray("yettie", "off", undefined, function () {
                    result.storageType = tempOption.storageType;
                    result.serial = tempOption.serial;
                    callback(result);

                    vestSign.close();
                }, function (error) {
                    vestSign.close();
                });
            }, vestCertErrorHandler);
        }
    }, function (error) {
        vest.util.refactoryMsg.convertMsg(error);
        errorcallback(error);
        vestSign.close();
    });

    vestSign.setURL(vestSign._url.sign);
    vestSign.setting();
    vestSign.open();
};

/**
 * VestSign의 전자서명 서버 검증 기능을 수행하는 함수이다.
 */
yettie.signVerifyEvent = function () {
    var f = document.Reg_Form;
    document.Reg_Form.action = "./sample/signVerify.jsp";
    document.Reg_Form.submit();
};

/**
 * VestSign의 전자서명에 대한 검증 기능을 수행하는 함수이다.
 *
 * @param   {String}   signedMsg 전자서명 검증을 위한 전자 서명문
 *
 * @param   {undefined} params undefined
 *
 * @param   {Object}   option 해당 기능 수행에 필요한 option 값
 * @param   {String}   option.encoding  스크립트에서 사용할 문자열 인코딩 방식 ("base64", "hex")
 * @param   {String}   option.charset   스크립트에서 사용할 한글 인코딩 형식 ("utf-8", "euc-kr")
 *
 * @param   {Function} callback               성공 시 결과를 받는 callback 함수
 * @param   {String}   callback.result        전자서명 원문
 *
 * @param   {Function} errorcallback        실패 시 결과를 받는 callback 함수
 * @param   {Object}   errorcallback.result        결과 값
 * @param   {String}   errorcallback.result.msg    사용자가 사용할 에러문
 * @param   {Number}   errorcallback.result.code   기반 에러코드
 * @param   {Array}    errorcallback.result.reason 기반 에러문
 *
 */
yettie.verifySignature = function (signedMsg, params, option, callback, errorcallback) {

    var newParams;
    if (typeof(params) === 'object' && params != undefined) {
        newParams = {};
        newParams.type = 0;
        newParams.certOrKey = params.certificate;
        newParams.plainType = 0;
        newParams.plain = params.plain;
        newParams.plainInputType = 0;
    }

    vest.token.verifySignature(signedMsg, newParams, option, callback, function (error) {
        vest.util.refactoryMsg.convertMsg(error);
        errorcallback(error);
    });
};

/**
 * VestSign의 전자서명에 대한 스크립트 검증 기능을 수행하는 함수이다.
 *
 * @param   {String}   signedMsg 전자서명 검증을 위한 전자 서명문
 *
 * @param   {undefined} params undefined
 *
 * @param   {Object}   option 해당 기능 수행에 필요한 option 값
 * @param   {String}   option.encoding  스크립트에서 사용할 문자열 인코딩 방식 ("base64", "hex")
 * @param   {String}   option.charset   스크립트에서 사용할 한글 인코딩 형식 ("utf-8", "euc-kr")
 *
 * @param   {Function} callback               성공 시 결과를 받는 callback 함수
 * @param   {String}   callback.result        전자서명 원문
 *
 * @param   {Function} errorcallback        실패 시 결과를 받는 callback 함수
 * @param   {Object}   errorcallback.result        결과 값
 * @param   {String}   errorcallback.result.msg    사용자가 사용할 에러문
 * @param   {Number}   errorcallback.result.code   기반 에러코드
 * @param   {Array}    errorcallback.result.reason 기반 에러문
 *
 */
yettie.verifySignatureFromScript = function (signedMsg, params, option, callback, errorcallback) {
    var newParams;
    if (typeof(params) === 'object' && params != undefined) {
        newParams = {};
        newParams.type = 0;
        newParams.certOrKey = params.certificate;
        newParams.plainType = 0;
        newParams.plain = params.plain;
        newParams.plainInputType = 0;
    }

    vest.pki.verifySignature(signedMsg, newParams, option, callback, function (error) {
        vest.util.refactoryMsg.convertMsg(error);
        errorcallback(error);
    });
};

/**
 * VestSign의 파일 전자서명에 대한 검증 기능을 수행하는 함수이다.
 *
 * @param   {String}   outfile 파일 전자서명 검증 결과를 저장할 파일 경로
 *
 * @param   {String}   signedMsg 파일 전자서명을 검증할 파일 경로
 *
 * @param   {undefined}   params undefined
 *
 * @param   {Object}   option 해당 기능 수행에 필요한 option 값
 * @param   {String}   option.encoding  스크립트에서 사용할 문자열 인코딩 방식 ("base64", "hex")
 * @param   {String}   option.charset   스크립트에서 사용할 한글 인코딩 형식 ("utf-8", "euc-kr")
 *
 * @param   {Function} callback               성공 시 결과를 받는 callback 함수
 * @param   {String}   callback.result        파일 전자서명 검증 결과가 저장된 파일 경로
 *
 * @param   {Function} errorcallback        실패 시 결과를 받는 callback 함수
 * @param   {Object}   errorcallback.result        결과 값
 * @param   {String}   errorcallback.result.msg    사용자가 사용할 에러문
 * @param   {Number}   errorcallback.result.code   기반 에러코드
 * @param   {Array}    errorcallback.result.reason 기반 에러문
 *
 */
yettie.fileVerifySignature = function (outfile, signedMsg, params, option, callback, errorcallback) {

    var newParams = undefined;
    if (typeof(params) !== 'object' && params != undefined) {
        newParams = {};
        newParams.type = 0;
        newParams.certOrKey = params.certificate;
        newParams.plainType = 0;
        newParams.plain = params.plain;
        newParams.plainInputType = 2;
    }

    option.signInputType = 2;
    option.signOutputType = 1;
    option.outputfile = outfile;

    vest.token.verifySignature(signedMsg, params, option, function (result) {
        callback(result);
    }, function (error) {
        vest.util.refactoryMsg.convertMsg(error);
        errorcallback(error);
    });
};

/**
 * VestSign의 인증서 갱신 기능을 수행하는 함수이다.
 *
 * @param   {Object}   option 해당 기능 수행에 필요한 option 값
 * @param   {String}   option.CAServiceIP     갱신에 사용할 CA IP 정보
 * @param   {String}   option.CAServicePort   갱신에 사용할 CA Port 정보
 *
 * @param   {Function} callback               성공 시 결과를 받는 callback 함수
 * @param   {Boolean}  callback.result        true 반환
 *
 * @param   {Function} errorcallback        실패 시 결과를 받는 callback 함수
 * @param   {Object}   errorcallback.result        결과 값
 * @param   {String}   errorcallback.result.msg    사용자가 사용할 에러문
 * @param   {Number}   errorcallback.result.code   기반 에러코드
 * @param   {Array}    errorcallback.result.reason 기반 에러문
 *
 */
yettie.update = function (option, callback, errorcallback) {
    var storage = {
        hardDisk: true,
        usbDisk: true,
        secureToken: true,
        saveToken: true,
        certificateFile: true,
        secureDisk: true,
        smartPhone: true,
        webStorage: false
    };

    vestSign.addConfig({storage: storage});
    vestSign.addConfig({signTitle: 'UPDATE'});
    vestSign.addConfig({ablePwd: false});
    vestSign.addConfig({ablePfxPwd: false});
    vestSign.addConfig({expiredCertFilter: false});

    vestSign.setParameters("", option, function (tokenNumber, identifier, password, option, vestCertErrorHandler) {
        option.changePin = true;
        var _caType = option.caType;
        vest.util.removeObjectElement(option);

        option.pwdInput = 3;
        vestSign.close();
        yettie.passwordInput(option, function (oldPassword, newPassword) {
            vest.token.updateCertificate(tokenNumber, identifier, _caType, oldPassword, newPassword, undefined, option,
                function (result) {
                    vest.token.tray("yettie", "off", undefined, function () {
                    }, function () {
                    });
                    callback(result);
                }, function (error) {
                    vest.util.refactoryMsg.convertMsg(error);
                    errorcallback(error);
                });
        }, errorcallback);
    }, function (error) {
        vest.util.refactoryMsg.convertMsg(error);
        errorcallback(error);
        vestSign.close();
    });

    vestSign.setURL(vestSign._url.sign);
    vestSign.setting();
    vestSign.open();
};

/**
 * VestSign의 인증서 폐지 기능을 수행하는 함수이다.
 *
 * @param   {Object}   option 해당 기능 수행에 필요한 option 값
 * @param   {String}   option.CAServiceIP     폐지에 사용할 CA IP 정보
 * @param   {String}   option.CAServicePort   폐지에 사용할 CA Port 정보
 *
 * @param   {Function} callback               성공 시 결과를 받는 callback 함수
 * @param   {Boolean}  callback.result        true 반환
 *
 * @param   {Function} errorcallback        실패 시 결과를 받는 callback 함수
 * @param   {Object}   errorcallback.result        결과 값
 * @param   {String}   errorcallback.result.msg    사용자가 사용할 에러문
 * @param   {Number}   errorcallback.result.code   기반 에러코드
 * @param   {Array}    errorcallback.result.reason 기반 에러문
 *
 */
yettie.revoke = function (option, callback, errorcallback) {
    var storage = {
        hardDisk: true,
        usbDisk: true,
        secureToken: true,
        saveToken: true,
        certificateFile: true,
        secureDisk: true,
        smartPhone: true,
        webStorage: false
    };

    vestSign.addConfig({storage: storage});
    vestSign.addConfig({signTitle: 'REVOKE'});
    vestSign.addConfig({ablePwd: true});
    vestSign.addConfig({ablePfxPwd: true});
    vestSign.addConfig({expiredCertFilter: false});

    vestSign.setParameters("", option, function (tokenNumber, identifier, password, option, vestCertErrorHandler) {
        var _caType = option.caType;
        vest.util.removeObjectElement(option);
        vest.token.revoke(tokenNumber, identifier, _caType, password, option,
            function (result) {
                vest.token.tray("yettie", "off", undefined, function () {
                    callback(result);
                    vestSign.close();
                }, function (error) {
                    vestSign.close();
                });
            }, vestCertErrorHandler);
    }, function (error) {
        vest.util.refactoryMsg.convertMsg(error);
        errorcallback(error);
        vestSign.close();
    });

    vestSign.setURL(vestSign._url.sign);
    vestSign.setting();
    vestSign.open();
};

/**
 * VestSign의 인증서 재발급 기능을 수행하는 함수이다.
 *
 * @param   {Object}   option 해당 기능 수행에 필요한 option 값
 * @param   {String}   option.ca              재발급에 사용할 CA 정보
 * @param   {String}   option.refNum          재발급에 사용할 참조번호 정보
 * @param   {String}   option.authCode        재발급에 사용할 인가코드 정보
 * @param   {String}   option.CAServiceIP     재발급에 사용할 CA IP 정보
 * @param   {String}   option.CAServicePort   재발급에 사용할 CA Port 정보
 *
 * @param   {Function} callback               성공 시 결과를 받는 callback 함수
 * @param   {Boolean}  callback.result        true 반환
 *
 * @param   {Function} errorcallback        실패 시 결과를 받는 callback 함수
 * @param   {Object}   errorcallback.result        결과 값
 * @param   {String}   errorcallback.result.msg    사용자가 사용할 에러문
 * @param   {Number}   errorcallback.result.code   기반 에러코드
 * @param   {Array}    errorcallback.result.reason 기반 에러문
 *
 */
yettie.recover = function (option, callback, errorcallback) {
    vestSign.addConfig({storageSelectedFlag: true});

    vestSign.setParameters("", option, function (tokenNumber, option) {
        option.pwdInput = 2;
        vestSign.close();
        yettie.passwordInput(option, function (password) {
            vest.token.setRecoverOptions(tokenNumber, option.ca, option.refNum, option.authCode, password, option,
                function (result) {
                    vest.token.tray("yettie", "off", undefined, function () {
                    }, function () {
                    });
                    callback(result);
                }, function (error) {
                    vest.util.refactoryMsg.convertMsg(error);
                    errorcallback(error);
                });
        }, errorcallback);
    }, function (error) {
        vest.util.refactoryMsg.convertMsg(error);
        errorcallback(error);
        vestSign.close();
    });

    vestSign.setting();
    vestSign.setURL(vestSign._url.storageSelected);
    vestSign.open();
};

/**
 * VestSign의 인증서 발급 기능을 수행하는 함수이다.
 *
 * @param   {Object}   option 해당 기능 수행에 필요한 option 값
 * @param   {String}   option.ca              발급에 사용할 CA 정보
 * @param   {String}   option.refNum          발급에 사용할 참조번호 정보
 * @param   {String}   option.authCode        발급에 사용할 인가코드 정보
 * @param   {String}   option.CAServiceIP     발급에 사용할 CA IP 정보
 * @param   {String}   option.CAServicePort   발급에 사용할 CA Port 정보
 *
 * @param   {Function} callback               성공 시 결과를 받는 callback 함수
 * @param   {Boolean}  callback.result        true 반환
 *
 * @param   {Function} errorcallback        실패 시 결과를 받는 callback 함수
 * @param   {Object}   errorcallback.result        결과 값
 * @param   {String}   errorcallback.result.msg    사용자가 사용할 에러문
 * @param   {Number}   errorcallback.result.code   기반 에러코드
 * @param   {Array}    errorcallback.result.reason 기반 에러문
 *
 */
yettie.issue = function (option, callback, errorcallback) {
    vestSign.addConfig({storageSelectedFlag: true});

    vestSign.setParameters("", option, function (tokenNumber, option) {
        vestSign.close();

        if (option.selectedToken == vest.token.TYPE.TOKEN || option.selectedToken == vest.token.TYPE.SAVETOKEN) option.pwdInput = 1;
        else option.pwdInput = 0;
        yettie.passwordInput(option, function (tokenPin) {
            if (option.selectedToken == vest.token.TYPE.SYSTEM || option.selectedToken == vest.token.TYPE.LOCALDISK || option.selectedToken == vest.token.TYPE.SAVETOKEN) option.pwdInput = 2;
            else option.pwdInput = 0;
            yettie.passwordInput(option, function (pin) {
                //option.CAServiceIP = "203.233.91.231";
                //option.CAServicePort = 4512;
                vest.token.setIssueOptions(tokenNumber, option.ca, option.refNum, option.authCode, pin, tokenPin, option, function (result) {
                    vest.token.tray("yettie", "off", undefined, function () {
                    }, function () {
                    });
                    callback(result);
                }, function (error) {
                    vest.util.refactoryMsg.convertMsg(error);
                    errorcallback(error);
                });
            }, errorcallback);
        }, errorcallback);
    }, function (error) {
        vest.util.refactoryMsg.convertMsg(error);
        errorcallback(error);
        vestSign.close();
    });

    vestSign.setting();
    vestSign.setURL(vestSign._url.storageSelected);
    vestSign.open();
};

/**
 * VestSign의 인증서 내보내기 기능을 수행하는 함수이다.
 *
 * @param   {Function} callback               성공 시 결과를 받는 callback 함수
 * @param   {Boolean}  callback.result        true 반환
 *
 * @param   {Function} errorcallback        실패 시 결과를 받는 callback 함수
 * @param   {Object}   errorcallback.result        결과 값
 * @param   {String}   errorcallback.result.msg    사용자가 사용할 에러문
 * @param   {Number}   errorcallback.result.code   기반 에러코드
 * @param   {Array}    errorcallback.result.reason 기반 에러문
 *
 */
yettie.exportP12 = function (callback, errorcallback) {
    var option = {};
    var storage = {
        hardDisk: true,
        usbDisk: true,
        secureToken: false,
        saveToken: false,
        certificateFile: false,
        secureDisk: false,
        smartPhone: false,
        webStorage: false
    };

    vestSign.addConfig({storage: storage});
    vestSign.addConfig({signTitle: 'EXPORT'});
    vestSign.addConfig({ablePwd: true});
    vestSign.addConfig({ablePfxPwd: true});
    vestSign.addConfig({expiredCertFilter: false});

    vestSign.setParameters("", option, function (tokenNumber, identifier, password, option, vestCertErrorHandler) {
        vest.util.removeObjectElement(option);
        vest.token.exportP12(tokenNumber, identifier, password, 1, option, function (result) {
            vest.token.tray("yettie", "off", undefined, function () {
                callback(result);
                vestSign.close();
            }, function (error) {
                vestSign.close();
            });
        }, vestCertErrorHandler);
    }, function (error) {
        vest.util.refactoryMsg.convertMsg(error);
        errorcallback(error);
        vestSign.close();
    });

    vestSign.setURL(vestSign._url.sign);
    vestSign.setting();
    vestSign.open();
};

/**
 * VestSign의 웹 저장소에 저장되어 있는 인증서에 대해서 내보내기 기능을 수행하는 함수이다.
 *
 * @param   {Object}   option 해당 기능 수행에 필요한 option 값
 * @param   {String}   option.encoding  스크립트에서 사용할 문자열 인코딩 방식 ("base64", "hex")
 *
 * @param   {Function} callback               성공 시 결과를 받는 callback 함수
 * @param   {Boolean}  callback.result        true 반환
 *
 * @param   {Function} errorcallback        실패 시 결과를 받는 callback 함수
 * @param   {Object}   errorcallback.result        결과 값
 * @param   {String}   errorcallback.result.msg    사용자가 사용할 에러문
 * @param   {Number}   errorcallback.result.code   기반 에러코드
 * @param   {Array}    errorcallback.result.reason 기반 에러문
 *
 */
yettie.webExportP12 = function (options, callback, errorcallback) {
    var storage = {
        hardDisk: false,
        usbDisk: false,
        secureToken: false,
        saveToken: false,
        certificateFile: false,
        secureDisk: false,
        smartPhone: false,
        webStorage: true
    };

    vestSign.addConfig({storage: storage});
    vestSign.addConfig({signTitle: 'EXPORT'});
    vestSign.addConfig({ablePwd: true});
    vestSign.addConfig({ablePfxPwd: true});
    vestSign.addConfig({bak: true});
    vestSign.addConfig({expiredCertFilter: false});

    vestSign.setParameters('', options, function (certificateSet, dn, password, options, errorHandler) {
        // 꺼내와서 pfx 만들고 filedownload 끝.
        try {
            var pfx = certificateSet.toPFX(password, options);
        } catch (error) {
            errorHandler(error);
            return;
        }

        try {
            vestSign.close();
            vest.util.file.download(dn + '.pfx', vest.signHelper.decodeString(pfx, options.encoding));
            callback(true);
        } catch (e) {   // 웹저장소에서 내보내기에 실패했습니다.
            var error = {
                code: 2400,
                msg: e.message
            };
            vest.util.refactoryMsg.convertMsg(error);
            errorcallback(error);
            return;
        }
    }, function (error) {
        vest.util.refactoryMsg.convertMsg(error);
        errorcallback(error);
        vestSign.close();
    });

    vestSign.setURL(vestSign._url.sign);
    vestSign.setting();
    vestSign.open();
};

/**
 * VestSign의 인증서 가져오기 기능을 수행하는 함수이다.
 *
 * @param   {Function} callback               성공 시 결과를 받는 callback 함수
 * @param   {Boolean}  callback.result        true 반환
 *
 * @param   {Function} errorcallback        실패 시 결과를 받는 callback 함수
 * @param   {Object}   errorcallback.result        결과 값
 * @param   {String}   errorcallback.result.msg    사용자가 사용할 에러문
 * @param   {Number}   errorcallback.result.code   기반 에러코드
 * @param   {Array}    errorcallback.result.reason 기반 에러문
 *
 */
yettie.importP12 = function (callback, errorcallback) {
    var option = {};

    option.fileType = 17;
    vest.token.getFilePath(option, function (path) {
        option.pwdInput = 1;
        yettie.passwordInput(option, function (pfxPin) {
            option.pfxCertPath = vest.signHelper.encodeBytes(vest.signHelper.encodeCharset(path, 'utf-8'), 'base64');
            vest.token.getTokenList(vest.token.TYPE.SYSTEM, function (list) {
                vest.token.importP12(list[0].tokenIdentifier, pfxPin, undefined, 1, option, function (result) {
                    vest.token.tray("yettie", "off", undefined, function () {
                    }, function () {
                    });
                    callback(result);
                }, function (error) {
                    vest.util.refactoryMsg.convertMsg(error);
                    errorcallback(error);
                });
            }, function (error) {
                vest.util.refactoryMsg.convertMsg(error);
                errorcallback(error);
            });
        }, errorcallback);
    }, function (error) {
        vest.util.refactoryMsg.convertMsg(error);
        errorcallback(error);
    });
};

/**
 * VestSign의 인증서 가져온 후, 웹 저장소에 저장하는 기능을 수행하는 함수이다.
 *
 * @param   {Object}   option 해당 기능 수행에 필요한 option 값
 * @param   {String}   option.encoding  스크립트에서 사용할 문자열 인코딩 방식 ("base64", "hex")
 *
 * @param   {Function} callback               성공 시 결과를 받는 callback 함수
 * @param   {Boolean}  callback.result        true 반환
 *
 * @param   {Function} errorcallback        실패 시 결과를 받는 callback 함수
 * @param   {Object}   errorcallback.result        결과 값
 * @param   {String}   errorcallback.result.msg    사용자가 사용할 에러문
 * @param   {Number}   errorcallback.result.code   기반 에러코드
 * @param   {Array}    errorcallback.result.reason 기반 에러문
 *
 */
yettie.webImportP12 = function (option, callback, errorcallback) {
    var btn = document.createElement('input');
    btn.type = 'file';
    btn.style.visibility = 'hidden';
    btn.onchange = function () {
        vest.util.file.read(event.target.files[0], function (pfx) {
            document.body.removeChild(btn);
            btn = null;
            option.pwdInput = 1;
            yettie.passwordInput(option, function (pin) {
                try {
                    var certificate = vest.pki.CertificateSet.fromPFX(pfx, pin);
                } catch (e) {
                    var error = {};
                    if (e.message.indexOf('Invalid password') != -1) {
                        error.code = 2301;
                    } else {
                        error.code = 2300;
                    }

                    vest.util.refactoryMsg.convertMsg(error);
                    errorcallback(error);
                    return;
                }

                vest.webStorage.importP12(certificate, pin, option, callback, function (error) {
                    vest.util.refactoryMsg.convertMsg(error);
                    errorcallback(error);
                });
            }, errorcallback);
        });
    };

    document.body.appendChild(btn);
    btn.click();
};

/**
 * VestSign의 인증서 비밀번호 변경 기능을 수행하는 함수이다.
 *
 * @param   {Function} callback               성공 시 결과를 받는 callback 함수
 * @param   {Boolean}  callback.result        true 반환
 *
 * @param   {Function} errorcallback        실패 시 결과를 받는 callback 함수
 * @param   {Object}   errorcallback.result        결과 값
 * @param   {String}   errorcallback.result.msg    사용자가 사용할 에러문
 * @param   {Number}   errorcallback.result.code   기반 에러코드
 * @param   {Array}    errorcallback.result.reason 기반 에러문
 *
 */
yettie.changePin = function (callback, errorcallback) {
    var option = {};
    var storage = {
        hardDisk: true,
        usbDisk: true,
        secureToken: false,
        saveToken: true,
        certificateFile: false,
        secureDisk: true,
        smartPhone: false,
        webStorage: false
    };

    vestSign.addConfig({storage: storage});
    vestSign.addConfig({signTitle: 'CHANGEPIN'});
    vestSign.addConfig({ablePwd: false});
    vestSign.addConfig({ablePfxPwd: false});
    vestSign.addConfig({expiredCertFilter: false});

    vestSign.setParameters("", option, function (tokenNumber, identifier, password, option, vestCertErrorHandler) {
        vest.util.removeObjectElement(option);

        option.pwdInput = 3;
        vestSign.close();
        yettie.passwordInput(option, function (oldPassword, newPassword) {
            if (tokenNumber instanceof vest.pki.CertificateSet) {
                vest.webStorage.changePassword(tokenNumber, oldPassword, newPassword, identifier, function (result) {
                    callback(result);
                }, function (error) {
                    vest.util.refactoryMsg.convertMsg(error);
                    errorcallback(error);
                });
            } else {
                vest.token.changePin(tokenNumber, identifier, oldPassword, newPassword, option, function (result) {
                    vest.token.tray("yettie", "off", undefined, function () {
                    }, function () {
                    });
                    callback(result);
                }, function (error) {
                    vest.util.refactoryMsg.convertMsg(error);
                    errorcallback(error);
                });
            }
        }, errorcallback);
    }, function (error) {
        vest.util.refactoryMsg.convertMsg(error);
        errorcallback(error);
        vestSign.close();
    });

    vestSign.setURL(vestSign._url.sign);
    vestSign.setting();
    vestSign.open();
};

/**
 * VestSign의 인증서 비밀번호 확인 기능을 수행하는 함수이다.
 *
 * @param   {Function} callback               성공 시 결과를 받는 callback 함수
 * @param   {Boolean}  callback.result        true 반환
 *
 * @param   {Function} errorcallback        실패 시 결과를 받는 callback 함수
 * @param   {Object}   errorcallback.result        결과 값
 * @param   {String}   errorcallback.result.msg    사용자가 사용할 에러문
 * @param   {Number}   errorcallback.result.code   기반 에러코드
 * @param   {Array}    errorcallback.result.reason 기반 에러문
 *
 */
yettie.checkPin = function (callback, errorcallback) {
    var storage = {
        hardDisk: true,
        usbDisk: true,
        secureToken: true,
        saveToken: true,
        certificateFile: true,
        secureDisk: false,
        smartPhone: true,
        webStorage: false
    };

    vestSign.addConfig({storage: storage});
    vestSign.addConfig({signTitle: 'VERIFYPIN'});
    vestSign.addConfig({ablePwd: true});
    vestSign.addConfig({ablePfxPwd: true});
    vestSign.addConfig({expiredCertFilter: false});

    vestSign.setParameters('', {}, function (tokenNumber, identifier, password, option, vestCertErrorHandler) {
        vest.util.removeObjectElement(option);
        if (tokenNumber instanceof vest.pki.CertificateSet) {
            tokenNumber.verifyPin(password, function (result) {
                callback(result);
                vestSign.close();
            }, vestCertErrorHandler);
        }
        else {
            vest.token.verifyPin(tokenNumber, identifier, password, function (result) {
                vest.token.tray("yettie", "off", undefined, function () {
                    callback(result);
                    vestSign.close();
                }, function (error) {
                    vestSign.close();
                });
                // }, vestCertErrorHandler);
            }, function (error) {
                if (error.code == 13603) {
                    vestSign.close();
                    // yettie.init(config);
                    yettie.notMatchedPassword(option, function () {
                        option.pwdInput = 1;
                        // yettie.init(config);
                        yettie.passwordInput(option, function (oldPassword) {
                            vest.token.certificateSynchronize(tokenNumber, identifier, password, oldPassword, 'undefined', callback, function (error) {
                                vest.util.refactoryMsg.convertMsg(error);
                                errorcallback(error);
                            });
                        }, errorcallback);
                    }, errorcallback);
                } else {
                    vestCertErrorHandler(error);
                }
            });
        }
    }, function (error) {
        vest.util.refactoryMsg.convertMsg(error);
        errorcallback(error);
        vestSign.close();
    });

    vestSign.setURL(vestSign._url.sign);
    vestSign.setting();
    vestSign.open();
};

/**
 * VestSign의 인증서 삭제 기능을 수행하는 함수이다.
 *
 * @param   {Function} callback               성공 시 결과를 받는 callback 함수
 * @param   {Boolean}  callback.result        true 반환
 *
 * @param   {Function} errorcallback        실패 시 결과를 받는 callback 함수
 * @param   {Object}   errorcallback.result        결과 값
 * @param   {String}   errorcallback.result.msg    사용자가 사용할 에러문
 * @param   {Number}   errorcallback.result.code   기반 에러코드
 * @param   {Array}    errorcallback.result.reason 기반 에러문
 *
 */
yettie.remove = function (callback, errorcallback) {
    var storage = {
        hardDisk: true,
        usbDisk: true,
        secureToken: true,
        saveToken: true,
        certificateFile: false,
        secureDisk: false,
        smartPhone: false,
        webStorage: false
    };

    vestSign.addConfig({storage: storage});
    vestSign.addConfig({signTitle: 'DELETE'});
    vestSign.addConfig({ablePwd: true});
    vestSign.addConfig({ablePfxPwd: true});
    vestSign.addConfig({expiredCertFilter: false});

    vestSign.setParameters('', {}, function (tokenNumber, identifier, password, option, vestCertErrorHandler) {
        vest.util.removeObjectElement(option);
        if (tokenNumber instanceof vest.pki.CertificateSet) {
            tokenNumber.verifyPin(password, function (result) {
                vest.localStorage.deleteCertificate(identifier, function () {
                    callback(true);
                    vestSign.close();
                });
            }, vestCertErrorHandler);
        } else {
            vest.token.removeFromMedia(tokenNumber, password, identifier, function (result) {
                vest.token.tray("yettie", "off", undefined, function () {
                    callback(result);
                    vestSign.close();
                }, function (error) {
                    vestSign.close();
                });
            }, vestCertErrorHandler);
        }
    }, function (error) {
        vest.util.refactoryMsg.convertMsg(error);
        errorcallback(error);
        vestSign.close();
    });

    vestSign.setURL(vestSign._url.sign);
    vestSign.setting();
    vestSign.open();
};

/**
 * VestSign의 인증서 저장매체 변경 기능을 수행하는 함수이다.
 *
 * @param   {Function} callback               성공 시 결과를 받는 callback 함수
 * @param   {Boolean}  callback.result        true 반환
 *
 * @param   {Function} errorcallback        실패 시 결과를 받는 callback 함수
 * @param   {Object}   errorcallback.result        결과 값
 * @param   {String}   errorcallback.result.msg    사용자가 사용할 에러문
 * @param   {Number}   errorcallback.result.code   기반 에러코드
 * @param   {Array}    errorcallback.result.reason 기반 에러문
 *
 */
yettie.changeStorage = function (callback, errorcallback) {
    var option = {};
    var storage = {
        hardDisk: true,
        usbDisk: true,
        secureToken: false,
        saveToken: false,
        certificateFile: false,
        secureDisk: false,
        smartPhone: false,
        webStorage: false
    };

    vestSign.addConfig({storage: storage});
    vestSign.addConfig({signTitle: 'CHANGESTORAGE'});
    vestSign.addConfig({ablePwd: true});
    vestSign.addConfig({expiredCertFilter: false});

    vestSign.setParameters("", option, function (tokenNumber, identifier, password, option, vestCertErrorHandler) {
        var sto_config = {
            storageSelectedFlag: false,
            storageSelectedTokenNumber: tokenNumber
        };
        vestSign.addConfig(sto_config);

        vest.token.verifyPin(tokenNumber, identifier, password, function () {
            vestSign.close();   // 핀이 맞으면 창닫음.
            yettie.storageSelected(false, option, function (newTokenNumber, option) {
                //option = {pkitype: vestSign.getConfig().useGPKI};
                option.pkitype = vestSign.getConfig().useGPKI;
                vest.token.getCertificates(tokenNumber, undefined, undefined, undefined, undefined, option, function () {
                    // 인증서 정보.
                    vest.token.getCertificate(tokenNumber, identifier.cert, function (certificate) {
                        if (option.selectedToken == vest.token.TYPE.TOKEN || option.selectedToken == vest.token.TYPE.SECUREDISK
                            || option.selectedToken == vest.token.TYPE.SAVETOKEN) option.pwdInput = 1;

                        yettie.passwordInput(option, function (tokenPin) {
                            vest.token.changeStorageMedia(tokenNumber, newTokenNumber, identifier, 0, password, tokenPin, option, callback, function (error) {
                                vest.util.refactoryMsg.convertMsg(error);
                                errorcallback(error);
                            });
                        }, errorcallback);
                    }, function (error) {
                        vest.util.refactoryMsg.convertMsg(error);
                        errorcallback(error);
                    });
                }, function (error) {
                    vest.util.refactoryMsg.convertMsg(error);
                    errorcallback(error);
                });
            });
        }, vestCertErrorHandler);
    }, function (error) {
        vest.util.refactoryMsg.convertMsg(error);
        errorcallback(error);
        vestSign.close();
    });

    vestSign.setURL(vestSign._url.sign);
    vestSign.setting();
    vestSign.open();
};

/**
 * VestSign의 VestCert 실행 파일의 버전 유효성 확인 기능을 수행하는 함수이다.
 *
 * @param   {Function} callback               성공 시 결과를 받는 callback 함수
 * @param   {Boolean}  callback.result        true/flase 반환
 *
 * @param   {Function} errorcallback        실패 시 결과를 받는 callback 함수
 * @param   {Object}   errorcallback.result        결과 값
 * @param   {String}   errorcallback.result.msg    사용자가 사용할 에러문
 * @param   {Number}   errorcallback.result.code   기반 에러코드
 * @param   {Array}    errorcallback.result.reason 기반 에러문
 *
 */
yettie.checkVersion = function (callback, errorcallback) {
    vest.token.getVersion(function (versions) {
        if (vest.util.certVersion.versionCheck(vest.util.certVersion.getVersion('VestCert', versions), vestSign.getConfig().version.vestCert)) {
            callback(true);
        } else {
            callback(false);
        }
    }, function (error) {
        vest.util.refactoryMsg.convertMsg(error);
        errorcallback(error);
    });
};

/**
 * VestSign의 Hash 기능을 수행하는 함수이다.
 *
 * @param   {Integer}   plainType 평문 타입 (0: hash할 원문, 1: hash할 file경로)
 *
 * @param   {String}    plain     해쉬할 평문
 *
 * @param   {Integer}   mode      해쉬 모드 (0: SHA1, 1: SHA256, 2: MD5)
 *
 * @param   {Function} callback               성공 시 결과를 받는 callback 함수
 * @param   {Object}   callback.result        결과 값
 *
 * @param   {Function} errorcallback        실패 시 결과를 받는 callback 함수
 * @param   {Object}   errorcallback.result        결과 값
 * @param   {String}   errorcallback.result.msg    사용자가 사용할 에러문
 * @param   {Number}   errorcallback.result.code   기반 에러코드
 * @param   {Array}    errorcallback.result.reason 기반 에러문
 *
 */
yettie.getHash = function (plainType, plain, mode, callback, errorcallback) {
    if (typeof(plainType) == 'string')
        plainType *= 1;
    if (typeof(mode) == 'string')
        mode *= 1;

    vest.token.getHash(plainType, plain, mode, function (result) {
        callback(result);
    }, function (error) {
        vest.util.refactoryMsg.convertMsg(error);
        errorcallback(error);
    });
};

/**
 * VestSign의 UCPID 전자서명 기능을 수행하는 함수이다.
 *
 * @param   {String}     userAgreement  사용자 안내문(평문)
 *
 * @param   {Boolean}    realName       이름
 *
 * @param   {Boolean}    birthDate      생일
 *
 * @param   {Boolean}    gender         성별
 *
 * @param   {Boolean}    nationalInfo   국적
 *
 * @param   {Object}   option 해당 기능 수행에 필요한 option 값
 * @param   {String}   option.encoding  스크립트에서 사용할 문자열 인코딩 방식 ("base64", "hex")
 *
 * @param   {Function} callback               성공 시 결과를 받는 callback 함수
 * @param   {Boolean}  callback.result        true 반환
 *
 * @param   {Function} errorcallback        실패 시 결과를 받는 callback 함수
 * @param   {Object}   errorcallback.result        결과 값
 * @param   {String}   errorcallback.result.msg    사용자가 사용할 에러문
 * @param   {Number}   errorcallback.result.code   기반 에러코드
 * @param   {Array}    errorcallback.result.reason 기반 에러문
 *
 */
yettie.signFormUCPID = function (userAgreement, realName, birthDate, gender, nationalInfo, option, callback, errorcallback) {
    if (typeof(userAgreement) !== "string") {
        alert("userAgreement have to be a string");
        return;
    }
    if (typeof(realName) !== "boolean") {
        alert("realName have to be a boolean");
        return;
    }
    if (typeof(birthDate) !== "boolean") {
        alert("birthDate have to be a boolean");
        return;
    }
    if (typeof(gender) !== "boolean") {
        alert("gender have to be a boolean");
        return;
    }
    if (typeof(nationalInfo) !== "boolean") {
        alert("nationalInfo have to be a boolean");
        return;
    }
    if (typeof(option) !== 'object') {
        alert('Invalid option');
        return;
    }
    if (typeof(callback) !== 'function') {
        alert('Invalid callback function');
        return;
    }

    var options = {
        realName: realName,
        birthDate: birthDate,
        gender: gender,
        nationalInfo: nationalInfo,
        charset: option.encoding
    };
    try {
        var result = vest.pki.generateUCPID(userAgreement, options);
    } catch (e) {
        errorcallback(e.message());
    }
    yettie.sign(result, option, callback, errorcallback);
};

/**
 * VestSign의 스크립트에서 파일 암호화 기능을 수행하는 함수이다.
 *
 * @param   {Object}   fileObj          암호화 할 파일 Object
 *
 * @param   {String}   outputFileName   암호화된 암호문을 저장할 파일명
 *
 * @param   {String}   keyValue         키 생성을 위한 string 값
 *
 * @param   {Object}   option   해당 기능 수행에 필요한 option 값
 * @param   {String}   option.encoding  스크립트에서 사용할 문자열 인코딩 방식 ("base64", "hex")
 *
 * @param   {Function} callback               성공 시 결과를 받는 callback 함수
 * @param   {Boolean}  callback.result        true 반환
 *
 * @param   {Function} errorcallback        실패 시 결과를 받는 callback 함수
 * @param   {Object}   errorcallback.result        결과 값
 * @param   {String}   errorcallback.result.msg    사용자가 사용할 에러문
 * @param   {Number}   errorcallback.result.code   기반 에러코드
 * @param   {Array}    errorcallback.result.reason 기반 에러문
 *
 */
yettie.scriptFileEncrypt = function (fileObj, outputFileName, keyValue, option, callback, errorcallback) {
    vest.util.file.read(fileObj, function (fileBuffer) {
        try {
            var kdf = vest.vestKDF(keyValue);
            var plain = vest.arrayBufferToData(fileBuffer);
            var encryptedValue = vest.encryptSEED(plain, kdf.iv, kdf.key, true);

            var arrayBuffer = new ArrayBuffer(encryptedValue.length);
            var uint8Array = new Uint8Array(arrayBuffer);

            for (var i = uint8Array.length; i--;) {
                uint8Array[i] = encryptedValue[i].charCodeAt(0);
            }

            var blob = new Blob([uint8Array], {type: 'application/octet-stream'});
            var a = window.document.createElement('a');

            var msSaveBlob = 'msSaveBlob';
            if (msSaveBlob in window.navigator) a.onclick = window.navigator.msSaveBlob(blob, outputFileName);

            else a.href = window.URL.createObjectURL(blob);

            a.download = outputFileName;
            document.body.appendChild(a);
            a.click();
            document.body.removeChild(a);
        } catch (e) {
            var error = {
                code: 2001,
                msg: 'scriptFileEncrypt error'
            };
            vest.util.refactoryMsg.convertMsg(error);
            errorcallback(error);
        }
        callback(true);
    });
};

/**
 * VestSign의 스크립트에서 파일 복호화 기능을 수행하는 함수이다.
 *
 * @param   {Object}   fileObj          복호화 할 파일 Object
 *
 * @param   {String}   outputFileName   복호화된 평문을 저장할 파일명
 *
 * @param   {String}   keyValue         키 생성을 위한 string 값
 *
 * @param   {Object}   option   해당 기능 수행에 필요한 option 값
 * @param   {String}   option.encoding  스크립트에서 사용할 문자열 인코딩 방식 ("base64", "hex")
 *
 * @param   {Function} callback               성공 시 결과를 받는 callback 함수
 * @param   {Boolean}  callback.result        true 반환
 *
 * @param   {Function} errorcallback        실패 시 결과를 받는 callback 함수
 * @param   {Object}   errorcallback.result        결과 값
 * @param   {String}   errorcallback.result.msg    사용자가 사용할 에러문
 * @param   {Number}   errorcallback.result.code   기반 에러코드
 * @param   {Array}    errorcallback.result.reason 기반 에러문
 *
 */
yettie.scriptFileDecrypt = function (fileObj, outputFileName, keyValue, option, callback, errorcallback) {
    vest.util.file.read(fileObj, function (fileBuffer) {
        try {
            var kdf = vest.vestKDF(keyValue);
            var plain = vest.arrayBufferToData(fileBuffer);
            var decryptedValue = vest.encryptSEED(plain, kdf.iv, kdf.key, false);

            var arrayBuffer = new ArrayBuffer(decryptedValue.length);
            var uint8Array = new Uint8Array(arrayBuffer);

            for (var i = uint8Array.length; i--;) {
                uint8Array[i] = decryptedValue[i].charCodeAt(0);
            }

            var blob = new Blob([uint8Array], {type: 'application/octet-stream'});

            var a = window.document.createElement('a');

            var msSaveBlob = 'msSaveBlob';
            if (msSaveBlob in window.navigator) a.onclick = window.navigator.msSaveBlob(blob, outputFileName);

            else a.href = window.URL.createObjectURL(blob);

            a.download = outputFileName;
            document.body.appendChild(a);
            a.click();
            document.body.removeChild(a);
        } catch (e) {
            var error = {
                code: 2002,
                msg: 'scriptFileDecrypt error'
            };
            vest.util.refactoryMsg.convertMsg(error);
            errorcallback(error);
        }
        callback(true);
    });
};

/**
 * VestSign의 암호화 기능을 수행하는 함수이다.
 *
 * @param   {String}    plain     암호화할 평문
 *
 * @param   {Object}    args      암호화에 필요한 파라미터 객체
 * @param   {String}    args.algorithm  알고리즘 (SEED: "SEED", AES: "AES", TDES: "TDES")
 * @param   {String}    args.mode       모드 (CBC: "CBC", ECB: "ECB")
 * @param   {String}    args.padding    패딩 (no: "no", zero: "zero", pkcs: "pkcs")
 *
 * @param   {Object}    keys      암호화에 필요한 파라미터 객체
 * @param   {String}    keys.key        key 값
 * @param   {String}    keys.iv         iv 값
 *
 * @param   {Object}   option     해당 기능 수행에 필요한 option 값
 * @param   {String}   option.encoding  스크립트에서 사용할 문자열 인코딩 방식 ("base64", "hex")
 * @param   {String}   option.charset   스크립트에서 사용할 한글 인코딩 형식 ("utf-8", "euc-kr")
 *
 * @param   {Function} callback               성공 시 결과를 받는 callback 함수
 * @param   {Object}   callback.result        결과 값
 *
 * @param   {Function} errorcallback        실패 시 결과를 받는 callback 함수
 * @param   {Object}   errorcallback.result        결과 값
 * @param   {String}   errorcallback.result.msg    사용자가 사용할 에러문
 * @param   {Number}   errorcallback.result.code   기반 에러코드
 * @param   {Array}    errorcallback.result.reason 기반 에러문
 *
 */
yettie.encrypt = function (plain, args, keys, option, callback, errorcallback) {
    vest.token.encrypt(plain, args, keys, option, function (result) {
        callback(result);
    }, function (error) {
        vest.util.refactoryMsg.convertMsg(error);
        errorcallback(error);
    });
};

/**
 * VestSign의 복호화 기능을 수행하는 함수이다.
 *
 * @param   {String}    plain     복호화할 암호문
 *
 * @param   {Object}    args      암호화에 필요한 파라미터 객체
 * @param   {String}    args.algorithm  알고리즘 (SEED: "SEED", AES: "AES", TDES: "TDES")
 * @param   {String}    args.mode       모드 (CBC: "CBC", ECB: "ECB")
 * @param   {String}    args.padding    패딩 (no: "no", zero: "zero", pkcs: "pkcs")
 *
 * @param   {Object}    keys      암호화에 필요한 파라미터 객체
 * @param   {String}    keys.key        key 값
 * @param   {String}    keys.iv         iv 값
 *
 * @param   {Object}   option     해당 기능 수행에 필요한 option 값
 * @param   {String}   option.encoding  스크립트에서 사용할 문자열 인코딩 방식 ("base64", "hex")
 * @param   {String}   option.charset   스크립트에서 사용할 한글 인코딩 형식 ("utf-8", "euc-kr")
 *
 * @param   {Function} callback               성공 시 결과를 받는 callback 함수
 * @param   {Object}   callback.result        결과 값
 *
 * @param   {Function} errorcallback        실패 시 결과를 받는 callback 함수
 * @param   {Object}   errorcallback.result        결과 값
 * @param   {String}   errorcallback.result.msg    사용자가 사용할 에러문
 * @param   {Number}   errorcallback.result.code   기반 에러코드
 * @param   {Array}    errorcallback.result.reason 기반 에러문
 *
 */
yettie.decrypt = function (plain, args, keys, option, callback, errorcallback) {
    vest.token.decrypt(plain, args, keys, option, function (result) {
        callback(result);
    }, function (error) {
        vest.util.refactoryMsg.convertMsg(error);
        errorcallback(error);
    });
};

/**
 * VestSign의 전자봉투 암호화 기능을 수행하는 함수이다.
 *
 * @param   {String}   plain     전자봉투 암호화할 평문
 *
 * @param   {undefined}   certificate     default: undefined, pem 구조일 경우 pem String 입력
 *
 * @param   {Object}   option     해당 기능 수행에 필요한 option 값
 * @param   {String}   option.encoding  스크립트에서 사용할 문자열 인코딩 방식 ("base64", "hex")
 * @param   {String}   option.charset   스크립트에서 사용할 한글 인코딩 형식 ("utf-8", "euc-kr")
 *
 * @param   {Function} callback               성공 시 결과를 받는 callback 함수
 * @param   {Object}   callback.result        결과 값
 *
 * @param   {Function} errorcallback        실패 시 결과를 받는 callback 함수
 * @param   {Object}   errorcallback.result        결과 값
 * @param   {String}   errorcallback.result.msg    사용자가 사용할 에러문
 * @param   {Number}   errorcallback.result.code   기반 에러코드
 * @param   {Array}    errorcallback.result.reason 기반 에러문
 *
 */
yettie.envelope = function (certificate, plain, option, callback, errorcallback) {
    option.signInputType = 0;
    option.signOutputType = 0;

    if (typeof(certificate) !== 'undefined') {
        var cert = vest.signHelper.pemToBase64(certificate);
        vest.token.envelope(plain, cert, undefined, 0, {cert: undefined}, option, function (result) {   // 고쳐야함
            callback(result);
        }, function (error) {
            vest.util.refactoryMsg.convertMsg(error);
            errorcallback(error);
        });
    } else {
        vestSign.addConfig({ablePwd: false});
        vestSign.setParameters("", option, function (tokenNumber, identifier, password, option, vestCertErrorHandler) {
            vest.util.removeObjectElement(option);
            vest.token.envelope(plain, undefined, undefined, tokenNumber, identifier, option, function (result) {
                vest.token.tray("yettie", "off", undefined, function () {
                    callback(result);
                    vestSign.close();
                }, function (error) {
                    vestSign.close();
                });
            }, vestCertErrorHandler);
        }, function (error) {
            vest.util.refactoryMsg.convertMsg(error);
            errorcallback(error);
            vestSign.close();
        });

        vestSign.setURL(vestSign._url.sign);
        vestSign.setting();
        vestSign.open();
    }
};

/**
 * VestSign의 전자봉투 복호화 기능을 수행하는 함수이다.
 *
 * @param   {String}   plain     전자봉투 복호화할 암호문
 *
 * @param   {undefined}   certificate     default: undefined, pem 구조일 경우 pem String 입력
 *
 * @param   {Object}   option     해당 기능 수행에 필요한 option 값
 * @param   {String}   option.encoding  스크립트에서 사용할 문자열 인코딩 방식 ("base64", "hex")
 * @param   {String}   option.charset   스크립트에서 사용할 한글 인코딩 형식 ("utf-8", "euc-kr")
 *
 * @param   {Function} callback               성공 시 결과를 받는 callback 함수
 * @param   {Object}   callback.result        결과 값
 *
 * @param   {Function} errorcallback        실패 시 결과를 받는 callback 함수
 * @param   {Object}   errorcallback.result        결과 값
 * @param   {String}   errorcallback.result.msg    사용자가 사용할 에러문
 * @param   {Number}   errorcallback.result.code   기반 에러코드
 * @param   {Array}    errorcallback.result.reason 기반 에러문
 *
 */
yettie.deenvelope = function (certificate, plain, option, callback, errorcallback) {
    option.signInputType = 0;
    option.signOutputType = 0;

    if (typeof(certificate) !== 'undefined') {
        //error;
    } else {
        vestSign.addConfig({ablePwd: true});
        vestSign.setParameters("", option, function (tokenNumber, identifier, password, option, vestCertErrorHandler) {
            vest.util.removeObjectElement(option);
            option.pwdInput = 1;
            vestSign.close();
            // yettie.passwordInput(option, function (password) {
            vest.token.deenvelope(plain, password, undefined, undefined, tokenNumber, identifier, option, function (result) {
                vest.token.tray("yettie", "off", undefined, function () {
                }, function () {
                });
                callback(result);
            }, function (error) {
                vest.util.refactoryMsg.convertMsg(error);
                errorcallback(error);
            });
            // }, errorcallback);
        }, function (error) {
            vest.util.refactoryMsg.convertMsg(error);
            errorcallback(error);
            vestSign.close();
        });

        vestSign.setURL(vestSign._url.sign);
        vestSign.setting();
        vestSign.open();
    }
};

/**
 * VestSign의 파일 전자봉투 암호화 기능을 수행하는 함수이다.
 *
 * @param   {String}   outfile     전자봉투 암호화된 결과를 저장할 파일 경로
 *
 * @param   {undefined}   certificate     default: undefined, pem 구조일 경우 pem String 입력
 *
 * @param   {String}   plain     전자봉투 암호화에 사용할 파일 경로
 *
 * @param   {Object}   option     해당 기능 수행에 필요한 option 값
 * @param   {String}   option.encoding  스크립트에서 사용할 문자열 인코딩 방식 ("base64", "hex")
 * @param   {String}   option.charset   스크립트에서 사용할 한글 인코딩 형식 ("utf-8", "euc-kr")
 *
 * @param   {Function} callback               성공 시 결과를 받는 callback 함수
 * @param   {Object}   callback.result        결과 값
 *
 * @param   {Function} errorcallback        실패 시 결과를 받는 callback 함수
 * @param   {Object}   errorcallback.result        결과 값
 * @param   {String}   errorcallback.result.msg    사용자가 사용할 에러문
 * @param   {Number}   errorcallback.result.code   기반 에러코드
 * @param   {Array}    errorcallback.result.reason 기반 에러문
 *
 */
yettie.fileEnvelope = function (outfile, certificate, plain, option, callback, errorcallback) {
    option.signInputType = 2;
    option.plainSizeLimit = 10;
    option.signOutputType = 1;

    if (typeof(certificate) !== 'undefined') {
        vest.token.envelope(plain, vest.signHelper.pemToBase64(certificate), outfile, 0, {cert: undefined}, option, function (result) {   // 고쳐야함
            callback(result);
        }, function (error) {
            vest.util.refactoryMsg.convertMsg(error);
            errorcallback(error);
        });
    } else {
        vestSign.addConfig({ablePwd: false});
        vestSign.setParameters("", option, function (tokenNumber, identifier, password, option, vestCertErrorHandler) {
            vest.util.removeObjectElement(option);
            vest.token.envelope(plain, undefined, outfile, tokenNumber, identifier, option, function (result) {
                vest.token.tray("yettie", "off", undefined, function () {
                    callback(result);
                    vestSign.close();
                }, function (error) {
                    vestSign.close();
                });
            }, vestCertErrorHandler);
        }, function (error) {
            vest.util.refactoryMsg.convertMsg(error);
            errorcallback(error);
            vestSign.close();
        });

        vestSign.setURL(vestSign._url.sign);
        vestSign.setting();
        vestSign.open();
    }
};

/**
 * VestSign의 파일 전자봉투 복호화 기능을 수행하는 함수이다.
 *
 * @param   {String}   outfile     전자봉투 복호화된 결과를 저장할 파일 경로
 *
 * @param   {undefined}   certificate     default: undefined, pem 구조일 경우 pem String 입력
 *
 * @param   {String}   plain     전자봉투 복호화에 사용할 파일 경로
 *
 * @param   {Object}   option     해당 기능 수행에 필요한 option 값
 * @param   {String}   option.encoding  스크립트에서 사용할 문자열 인코딩 방식 ("base64", "hex")
 * @param   {String}   option.charset   스크립트에서 사용할 한글 인코딩 형식 ("utf-8", "euc-kr")
 *
 * @param   {Function} callback               성공 시 결과를 받는 callback 함수
 * @param   {Object}   callback.result        결과 값
 *
 * @param   {Function} errorcallback        실패 시 결과를 받는 callback 함수
 * @param   {Object}   errorcallback.result        결과 값
 * @param   {String}   errorcallback.result.msg    사용자가 사용할 에러문
 * @param   {Number}   errorcallback.result.code   기반 에러코드
 * @param   {Array}    errorcallback.result.reason 기반 에러문
 *
 */
yettie.fileDeenvelope = function (outfile, certificate, plain, option, callback, errorcallback) {
    option.signInputType = 2;
    option.signOutputType = 1;

    if (typeof(certificate) !== 'undefined') {
        //error;
    } else {
        vestSign.addConfig({ablePwd: true});
        vestSign.setParameters("", option, function (tokenNumber, identifier, password, option, vestCertErrorHandler) {
            vest.util.removeObjectElement(option);
            option.pwdInput = 1;
            vestSign.close();
            // yettie.passwordInput(option, function (password) {
            vest.token.deenvelope(plain, password, undefined, outfile, tokenNumber, identifier, option, function (result) {
                vest.token.tray("yettie", "off", undefined, function () {
                }, function () {
                });
                callback(result);
            }, function (error) {
                vest.util.refactoryMsg.convertMsg(error);
                errorcallback(error);
            });
            // }, errorcallback);
        }, function (error) {
            vest.util.refactoryMsg.convertMsg(error);
            errorcallback(error);
            vestSign.close();
        });

        vestSign.setURL(vestSign._url.sign);
        vestSign.setting();
        vestSign.open();
    }
};

/**
 * VestSign의 VestCert 실행파일 언어를 수정하는 기능을 수행하는 함수이다.
 *
 * @param   {String}    localeStr    VestCert의 언어 선택 (한국어: "kor", 영어: "eng", 일본어: "jpn", 중국어: "chn")
 *
 */
yettie.setLanguage = function (localeStr) {
    if (typeof(localeStr) !== "string") {
        alert("Locale type is not string.");
        return;
    }
    vest.token.setLanguage(localeStr);
};

/**
 * VestSign의 PC MAC 주소 정보를 가져오는 기능을 수행하는 함수이다.
 *
 * @param   {Object}   option 해당 기능 수행에 필요한 option 값
 *
 * @param   {Function} callback               성공 시 결과를 받는 callback 함수
 * @param   {String}   callback.result        Mac 주소 반환
 *
 * @param   {Function} errorcallback        실패 시 결과를 받는 callback 함수
 * @param   {Object}   errorcallback.result        결과 값
 * @param   {String}   errorcallback.result.msg    사용자가 사용할 에러문
 * @param   {Number}   errorcallback.result.code   기반 에러코드
 * @param   {Array}    errorcallback.result.reason 기반 에러문
 *
 */
yettie.getPCInfo = function (option, callback, errorcallback) {
    vest.token.getMacAddress(6 + 64, callback, function (error) {
        vest.util.refactoryMsg.convertMsg(error);
        errorcallback(error);
    });
};

/**
 * VestSign의 VestCert 실행파일 버전 정보를 가져오는 기능을 수행하는 함수이다.
 *
 * @param   {Object}   option 해당 기능 수행에 필요한 option 값
 *
 * @param   {Function} callback               성공 시 결과를 받는 callback 함수
 * @param   {String}   callback.result        VestCert 버전
 *
 * @param   {Function} errorcallback        실패 시 결과를 받는 callback 함수
 * @param   {Object}   errorcallback.result        결과 값
 * @param   {String}   errorcallback.result.msg    사용자가 사용할 에러문
 * @param   {Number}   errorcallback.result.code   기반 에러코드
 * @param   {Array}    errorcallback.result.reason 기반 에러문
 *
 */
yettie.getVestCertVersion = function (option, callback, errorcallback) {
    vest.token.getVersion(function (versionList) {
        var resultVersion = '0';
        for (var i = 0; i < versionList.length; i++) {
            if (versionList[i].moduleName == "VestCert") {
                resultVersion = versionList[i].version;
            }
        }
        callback(resultVersion);
    }, function (error) {
        vest.util.refactoryMsg.convertMsg(error);
        errorcallback(error);
    });
};

/**
 * VestSign의 선택한 파일 경로를 보여주는 기능을 수행하는 함수이다.
 *
 * @param   {Object}   option 해당 기능 수행에 필요한 option 값
 *
 * @param   {Function} callback               성공 시 결과를 받는 callback 함수
 * @param   {String}   callback.result        선택한 파일 경로
 *
 * @param   {Function} errorcallback        실패 시 결과를 받는 callback 함수
 * @param   {Object}   errorcallback.result        결과 값
 * @param   {String}   errorcallback.result.msg    사용자가 사용할 에러문
 * @param   {Number}   errorcallback.result.code   기반 에러코드
 * @param   {Array}    errorcallback.result.reason 기반 에러문
 *
 */
yettie.getFilePath = function (option, callback, errorcallback) {
    vest.token.getFilePath(option, function (filePath) {
        callback(filePath);
    }, function (error) {
        vest.util.refactoryMsg.convertMsg(error);
        errorcallback(error);
    });
};

/**
 * VestSign의 저장매체 선택창을 보여주는 기능을 수행하는 함수이다.
 *
 * @param   {Boolean}  clause   약관 사용 여부 (default: false)
 *
 * @param   {Object}   option   해당 기능 수행에 필요한 option 값
 *
 * @param   {Function} callback               성공 시 결과를 받는 callback 함수
 * @param   {String}   callback.result        선택한 저장매체 번호
 *
 * @param   {Function} errorcallback        실패 시 결과를 받는 callback 함수
 * @param   {Object}   errorcallback.result        결과 값
 * @param   {String}   errorcallback.result.msg    사용자가 사용할 에러문
 * @param   {Number}   errorcallback.result.code   기반 에러코드
 * @param   {Array}    errorcallback.result.reason 기반 에러문
 *
 */
yettie.storageSelected = function (clause, option, callback, errorcallback) {
    if (typeof(option) !== 'object') {
        alert('Invalid option');
        return;
    }

    if (typeof(callback) !== 'function') {
        alert('Invalid callback function');
        return;
    }

    vestSign.setParameters("", option, function (tokenNumber, option) {
        vestSign.close();
        callback(tokenNumber, option);
    }, function (error) {
        vest.util.refactoryMsg.convertMsg(error);
        errorcallback(error);
        vestSign.close();
    });
    vestSign.setting();

    if (clause) {
        vestSign.setURL(vestSign._url.clause);
        vestSign.open();
    } else {
        vestSign.setURL(vestSign._url.storageSelected);
        vestSign.open();
    }
};

/**
 * VestSign의 안전디스크에서 저장매체 선택창을 보여주는 기능을 수행하는 함수이다.
 *
 * @param   {Boolean}  clause   약관 사용 여부 (default: false)
 *
 * @param   {Object}   option   해당 기능 수행에 필요한 option 값
 *
 * @param   {Function} callback               성공 시 결과를 받는 callback 함수
 * @param   {String}   callback.result        선택한 저장매체 번호
 *
 * @param   {Function} errorcallback        실패 시 결과를 받는 callback 함수
 * @param   {Object}   errorcallback.result        결과 값
 * @param   {String}   errorcallback.result.msg    사용자가 사용할 에러문
 * @param   {Number}   errorcallback.result.code   기반 에러코드
 * @param   {Array}    errorcallback.result.reason 기반 에러문
 *
 */
yettie.securediskStorageSelected = function (clause, option, callback, errorcallback) {
    if (typeof(option) !== 'object') {
        alert('Invalid option');
        return;
    }

    if (typeof(callback) !== 'function') {
        alert('Invalid callback function');
        return;
    }

    vestSign.setParameters("", option, function (tokenNumber, deleteCert) {
        vestSign.close();
        callback(tokenNumber, deleteCert);
    }, function (error) {
        vest.util.refactoryMsg.convertMsg(error);
        errorcallback(error);
        vestSign.close();
    });
    vestSign.setting();

    if (clause) {
        vestSign.setURL(vestSign._url.clause);
        vestSign.open();
    } else {
        vestSign.setURL(vestSign._url.securediskStorageSelected);
        vestSign.open();
    }
};

/**
 * VestSign의 옵션에 따라서 비밀번호 입력창을 보여주는 기능을 수행하는 함수이다.
 *
 * @param   {Object}   option 해당 기능 수행에 필요한 option 값
 * @param   {String}   option.pwdInput 사용할 비밀번호 입력창 갯수 입력 (1, 2, 3)
 *
 * @param   {Function} callback               성공 시 결과를 받는 callback 함수
 * @param   {String}   callback.result        입력한 비밀번호
 *
 * @param   {Function} errorcallback        실패 시 결과를 받는 callback 함수
 * @param   {Object}   errorcallback.result        결과 값
 * @param   {String}   errorcallback.result.msg    사용자가 사용할 에러문
 * @param   {Number}   errorcallback.result.code   기반 에러코드
 * @param   {Array}    errorcallback.result.reason 기반 에러문
 *
 */
yettie.passwordInput = function (options, callback, errorcallback) {
    vestSign.setParameters('', options, function (oldPassword, newPassword) {
        vestSign.close();
        callback(oldPassword, newPassword);
    }, function (error) {
        vestSign.close();
        vest.util.refactoryMsg.convertMsg(error);
        errorcallback(error);
    });

    vestSign.setting();

    var type = typeof(options.pwdInput) !== 'undefined' ? options.pwdInput : 0;
    switch (type) {
        case 1:
            vestSign.setURL(vestSign._url.passwordType1);
            break;
        case 2:
            vestSign.setURL(vestSign._url.passwordType2);
            break;
        case 3:
            vestSign.setURL(vestSign._url.passwordType3);
            break;
        default:
            callback(undefined);
            return;
    }
    vestSign.open();
};

/**
 * VestSign의 키분배용 비밀번호가 다를 경우 안내창을 보여주는 기능을 수행하는 함수이다.
 *
 * @param   {Object}   option 해당 기능 수행에 필요한 option 값
 *
 * @param   {Function} callback               성공 시 결과를 받는 callback 함수
 * @param   {Boolean}  callback.result        true 반환
 *
 * @param   {Function} errorcallback        실패 시 결과를 받는 callback 함수
 * @param   {Object}   errorcallback.result        결과 값
 * @param   {String}   errorcallback.result.msg    사용자가 사용할 에러문
 * @param   {Number}   errorcallback.result.code   기반 에러코드
 * @param   {Array}    errorcallback.result.reason 기반 에러문
 *
 */
yettie.notMatchedPassword = function (options, callback, errorcallback) {
    vestSign.setParameters('', options, function (result) {
        vestSign.close();
        callback(result);
    }, function (error) {
        // vestSign.close();
        vest.util.refactoryMsg.convertMsg(error);
        errorcallback(error);
    });

    vestSign.setting();
    vestSign.setURL(vestSign._url.notMatchedPassword);
    vestSign.open();
};

/**
 * VestSign의 Sloth 중계 서버에 인증서를 내보내는 기능을 수행하는 함수이다.
 *
 * @param   {Object}   option 해당 기능 수행에 필요한 option 값
 * @param   {String}   option.encoding  스크립트에서 사용할 문자열 인코딩 방식 ("base64", "hex")
 * @param   {String}   option.charset   스크립트에서 사용할 한글 인코딩 형식 ("utf-8", "euc-kr")
 *
 * @param   {Function} callback               성공 시 결과를 받는 callback 함수
 * @param   {Boolean}  callback.result        true 반환

 * @param   {Function} errorcallback        실패 시 결과를 받는 callback 함수
 * @param   {Object}   errorcallback.result        결과 값
 * @param   {String}   errorcallback.result.msg    사용자가 사용할 에러문
 * @param   {Number}   errorcallback.result.code   기반 에러코드
 * @param   {Array}    errorcallback.result.reason 기반 에러문
 *
 */
yettie.exportToWeb = function (option, callback, errorcallback) {
    var storage = {
        hardDisk: true,
        usbDisk: true,
        secureToken: false,
        saveToken: false,
        certificateFile: false,
        secureDisk: false,
        smartPhone: false,
        webStorage: false
    };
    vestSign.addConfig({storage: storage});

    vestSign.setParameters('', option, function (tokenNumber, identifier, password, option, vestCertErrorHandler) {
        option.returnType = 'text';
        vest.token.exportP12(tokenNumber, identifier, password, 1, option, function (pfxStr) {
            vestSign.close();
            vestSign.setParameters('', option, function (slothPath, option) {
                // 로직 진행.
                var slothSender = new vest.sloth(slothPath + '/sloth');
                slothSender.update(option.code, vest.signHelper.decodeString(pfxStr, option.encoding), function (result) {
                    if (result instanceof vest.error) {
                        vest.util.refactoryMsg.convertMsg(result);
                        errorcallback(result);
                    } else {
                        alert(result);
                        callback(result);
                    }
                    vestSign.close();
                });
            }, function (error) {
                vestSign.close();
                vest.util.refactoryMsg.convertMsg(error);
                errorcallback(error);
            });
            vestSign.setURL(vestSign._url.exportToWeb);
            vestSign.setting();
            vestSign.open();
        }, vestCertErrorHandler);
    }, function (error) {
        vestSign.close();
        vest.util.refactoryMsg.convertMsg(error);
        errorcallback(error);
    });

    vestSign.setURL(vestSign._url.sign);
    vestSign.setting();
    vestSign.open();

};

vestSign.addScripts();
var _load;

yettie.loadingLibrary = function (func) {
    if (typeof func !== 'undefined')
        vestSign.setFunction(func);

    if (typeof(vest) === 'object') {
        // 로딩 이미지 제거
        var element = document.getElementById('yettie_library_loading');
        element.parentNode.removeChild(element);
        vestSign.layerEnable();

        if (typeof vestSign.getFunction() !== "undefined")
            vestSign.getFunction()();
    } else {
        if (_load) clearTimeout(_load);
        _load = setTimeout(function () {
            yettie.loadingLibrary(func);
        }, 1000);
    }
};
/**
 * Created by nori on 2016-08-14.
 */

vestSign._url.mSign = {
    url: 'mSign.html',
    iframeName: 'yettie_mobile_sign',
    width: 640,
    height: 960,
    mobile: true
};
vestSign._url.mImportFromPC = {
    url: 'mImportFromPC.html',
    iframeName: 'yettie_mobile_importFromPC',
    width: 640,
    height: 960,
    mobile: true
};
vestSign._url.mImportFromPhoneApp = {
    url: 'mImportFromPhoneApp.html',
    iframeName: 'yettie_mobile_importFromPhoneApp',
    width: 640,
    height: 960,
    mobile: true
};
vestSign._url.mImportFromPhoneWebApp = {
    url: 'mImportFromPhoneWebApp.html',
    iframeName: 'yettie_mobile_importFromPhoneWebApp',
    width: 640,
    height: 568,
    mobile: true
};
vestSign._url.mImportFromPhoneWebAppNoServer = {
    url: 'mImportFromPhoneWebAppNoServer.html',
    iframeName: 'yettie_mobile_importFromPhoneWebAppNoServer',
    width: 640,
    height: 428,
    mobile: true
};
vestSign._url.mManagement = {
    url: 'mManagement.html',
    iframeName: 'yettie_mobile_management',
    width: 640,
    height: 960,
    mobile: true
};
vestSign._url.mChangePin = {
    url: 'mChangePin.html',
    iframeName: 'yettie_mobile_changePin',
    width: 640,
    height: 960,
    mobile: true
};

/**
 * VestSign의 모바일 API로 각 기능을 수행하면서 호출되는 접근자의 기능을 수행하는 함수이다.
 * @namespace yettie.mobile
 */
yettie.mobile = {
    /**
     * VestSign의 호출한 기능에 맞는 화면 보여주는 기능을 수행하는 함수이다.
     *
     * @param   {String}   select     표시할 화면 네이밍
     *
     * @param   {Object}   option 해당 기능 수행에 필요한 option 값
     * @param   {String}   option.encoding  스크립트에서 사용할 문자열 인코딩 방식 ("base64", "hex")
     * @param   {String}   option.charset   스크립트에서 사용할 한글 인코딩 형식 ("utf-8", "euc-kr")
     *
     * @param   {Function} callback               성공 시 결과를 받는 callback 함수
     * @param   {Object}   callback.result        기능에 맞는 각기 다른 반환값 (password, pfx, msg 등)
     *
     * @param   {Function} errorcallback        실패 시 결과를 받는 callback 함수
     * @param   {Object}   errorcallback.result        결과 값
     * @param   {Number}   errorcallback.result.code   기반 에러코드
     * @param   {Array}    errorcallback.result.reason 기반 에러문
     *
     */
    selected: function (select, option, callback, errorcallback) {
        vestSign.setParameters('', option, function (result) {
            var temp = result;
            vestSign.close();
            callback(temp);
        }, function (error) {
            var temp = error;
            vestSign.close();
            errorcallback(temp);
        });

        vestSign.setURL(select);
        vestSign.setting();
        vestSign.open();
    },

    /**
     * VestSign 모바일 브라우저 지원 여부를 확인하는 기능을 수행하는 함수이다.
     *
     * @param   {Function} callback               성공 시 결과를 받는 callback 함수
     * @param   {Object}   callback.result              결과 값
     * @param   {Boolean}  callback.result.support      true 반환
     * @param   {String}   callback.result.userAgent    window.navigator.userAgent 반환
     *
     * @param   {Function} errorcallback        실패 시 결과를 받는 callback 함수
     * @param   {Object}   errorcallback.result              결과 값
     * @param   {Boolean}  errorcallback.result.support      false 반환
     * @param   {String}   errorcallback.result.userAgent    window.navigator.userAgent 반환
     * @param   {String}   errorcallback.result.error        에러문
     *
     */
    isSupportBrowser: function (callback, errorcallback) {
        vest.secureCertificateStorage.isSupportBrowser(function () {
            callback({
                support: true,
                userAgent: window.navigator.userAgent
            });
        }, function (error) {
            document.getElementById('supportText').innerHTML = ('지원 X / 정보: ' + window.navigator.userAgent);
            errorcallback({
                support: false,
                userAgent: window.navigator.userAgent,
                error: error
            });
        });
    }
};

/**
 * VestSign의 모바일 API로 인증서 리스트를 보여주고 전자서명 및 인증서 관리 기능을 수행하는 함수이다.
 *
 * @param   {String}   plain 전자서명할 서명 원문
 *
 * @param   {Object}   option 해당 기능 수행에 필요한 option 값
 * @param   {String}   option.encoding  스크립트에서 사용할 문자열 인코딩 방식 ("base64", "hex")
 * @param   {String}   option.charset   스크립트에서 사용할 한글 인코딩 형식 ("utf-8", "euc-kr")
 *
 * @param   {Function} callback               성공 시 결과를 받는 callback 함수
 * @param   {Object}   callback.result        결과 값
 * @param   {String}   callback.result.signature     전자서명문
 * @param   {String}   callback.result.vidMessage    vid 메시지
 * @param   {Object}   callback.result.extentions
 * @param   {String}   callback.result.extentions.dn           전자서명에 사용된 인증서 DN 값
 * @param   {String}   callback.result.extentions.certificate  전자서명에 사용된 인증서를 byte 인코딩한 값
 * @param   {String}   callback.result.extentions.r            전자서명에 사용된 인증서 R 값
 * @param   {String}   callback.result.extentions.realName     전자서명에 사용된 인증서 Name
 *
 * @param   {Function} errorcallback        실패 시 결과를 받는 callback 함수
 * @param   {Object}   errorcallback.result        결과 값
 * @param   {Number}   errorcallback.result.code   기반 에러코드
 * @param   {Array}    errorcallback.result.reason 기반 에러문
 *
 */
yettie.mSign = function (plain, option, callback, errorcallback) {
    var _secureCertificateStorageItem;
    var _defaultOption = option;

    var _viewErrorcallback = function (error) {
        alert(error.getReason());
    };

    var _okCallback = function (str) {
        alert(str);
    };

    var action = function (type, config, option) {
        switch (type) {
            case 'SIGN' :
                // 서명
                vest.secureCertificateStorage.makeSignature(vest.signHelper.encodeCharset(plain, _defaultOption.charset), _secureCertificateStorageItem, config.password, _defaultOption, function (result) {
                    callback({
                        signature: vest.signHelper.encodeBytes(result.signature, _defaultOption.encoding),
                        vidMessage: vest.signHelper.encodeBytes(result.vidMessage, _defaultOption.encoding),
                        extentions: result.extentions
                    });
                }, function (error) {
                    // error 처리
                    //console.log(error.getReason());
                    _viewErrorcallback(error);
                    action('VIEW.SIGNLIST', undefined, _defaultOption);
                });
                break;
            case 'CHANGEPASSWORD' :
                // 비밀번호 변경
                vest.secureCertificateStorage.changePassword(_secureCertificateStorageItem, config.oldPassword, config.newPassword, option, function () {
                    _okCallback(config.msg);
                    action('VIEW.SIGNLIST', undefined, _defaultOption);
                }, function (error) {
                    //error 처리해주고
                    _viewErrorcallback(error);
                    action('VIEW.SIGNLIST', undefined, _defaultOption);
                });
                break;
            case 'REMOVECERTIFICATE' :
                // 인증서 삭제
                vest.secureCertificateStorage.removeCertificateItem(_secureCertificateStorageItem);
                _okCallback(config.msg);
                action('VIEW.SIGNLIST', undefined, _defaultOption);
                break;
            case 'IMPORTPFX' :
                vest.secureCertificateStorage.addCertificateItemFromPFX({pfx: config.pfx}, config.pfxPassword, _defaultOption, function () {
                    action('VIEW.SIGNLIST', undefined, _defaultOption);
                }, function (error) {
                    //error 처리해주고
                    _viewErrorcallback(error);
                    action('VIEW.SIGNLIST', undefined, _defaultOption);
                });
                break;
            case 'VIEW.SIGNLIST':
                yettie.mobile.selected(vestSign._url.mSign, option, function (result) {
                    if (typeof(result.SecureCertificateStorageItem) === 'object') {
                        _secureCertificateStorageItem = result.SecureCertificateStorageItem;
                        result.option.viewOption = {
                            cert: _secureCertificateStorageItem.signCertificate.toAsn1().toHex()
                        };
                    }
                    if (typeof(result.viewErrorcallback) === 'function') {
                        _viewErrorcallback = result.viewErrorcallback;
                    }
                    action(result.action, {password: result.password}, result.option);
                }, errorcallback);
                break;
            case 'VIEW.MANAGEMENT':
                yettie.mobile.selected(vestSign._url.mManagement, option, function (result) {
                    action(result.action, {msg: result.msg}, result.option);
                }, errorcallback);
                break;
            case 'VIEW.CHANGEPIN':
                yettie.mobile.selected(vestSign._url.mChangePin, option, function (result) {
                    action(result.action, {
                        oldPassword: result.oldPassword,
                        newPassword: result.newPassword,
                        msg: result.msg
                    }, result.option);
                }, errorcallback);
                break;
            case 'VIEW.IMPORTFROMPC':
                yettie.mobile.selected(vestSign._url.mImportFromPC, option, function (result) {
                    var pfx = typeof(result.pfx) === 'string' ? result.pfx : undefined;
                    action(result.action, {pfx: pfx, pfxPassword: result.pfxPassword}, result.option);
                }, errorcallback);
                break;
            case 'VIEW.IMPORTFROMPHONE':
                if (vestSign.getConfig().useApp) {  // app 사용
                    yettie.mobile.selected(vestSign._url.mImportFromPhoneApp, option, function (result) {
                        var pfx = typeof(result.pfx) === 'string' ? result.pfx : undefined;
                        var pfxPassword = typeof(result.pfxPassword) === 'string' ? result.pfxPassword : undefined;
                        action(result.action, {pfx: pfx, pfxPassword: pfxPassword}, result.option);
                    }, errorcallback);
                } else {    // webApp 사용
                    if (vestSign.getConfig().useWebAppSloth) { // sloth 중계서버 사용O
                        yettie.mobile.selected(vestSign._url.mImportFromPhoneWebApp, option, function (result) {
                            var pfx = typeof(result.pfx) === 'string' ? result.pfx : undefined;
                            action(result.action, {pfx: pfx, pfxPassword: result.pfxPassword}, result.option);
                        }, errorcallback);
                    } else {    // sloth 중계서버 사용X
                        yettie.mobile.selected(vestSign._url.mImportFromPhoneWebAppNoServer, option, function (result) {
                            action(result.action, undefined, result.option);
                        }, errorcallback);
                    }
                }
                break;
            default:
                //error
                //errorcallback()
                break;
        }
    };

    action('VIEW.SIGNLIST', undefined, option);
};

/**
 * VestSign의 모바일 API로 안드로이드에서 호출되는 접근자의 기능을 수행하는 함수이다.
 * @namespace yettie.app
 */
yettie.app = {
    /**
     * VestSign의 Sloth 중계서버를 사용하여 인증서를 저장하는 기능을 수행하는 함수이다.
     *
     * @param   {String}   code     Sloth 중계서버에서 사용할 인증코드
     *
     * @param   {String}   p12      안드로이드에서 선택한 base64 인코딩 된 인증서
     *
     * @param   {String}   newestApp 최신 앱인지 확인하기 위한 문자열
     *
     */
    saveBrowserRepository: function (code, p12, newestApp) {
        var temp = [];
        temp.push(code.slice(0, 4));
        temp.push(code.slice(4, 8));
        temp.push(code.slice(8, 12));

        var option = {};
        option.newestApp = (newestApp == 'true') ? true : false;

        var slothPath = vestSign.getConfig().slothPath;
        if (slothPath == '')
            slothPath = 'https://' + location.host;

        var sender = new vest.sloth(slothPath + '/sloth');
        //sender.update(temp, vest.signHelper.decodeString(p12, 'base64'), function (result) {
        sender.updateData(temp, vest.signHelper.decodeString(p12, 'base64'), option, function (result) {
            console.log('updateData');
            if (typeof(result.getReason) === 'function') {
                window.koreamint.getResult(result.code, result.getReason());
                // alert(result.code + result.getReason());
            } else {
                window.koreamint.getResult(0, '성공');
                // alert('에러코드 0이랑 ok메시지');
            }
        });
    },
    /**
     * VestSign의 Sloth 중계서버 사용 없이 인증서를 저장하는 기능을 수행하는 함수이다.
     *
     * @param   {Integer}   repCode     안드로에드에서 처리하면서 발생된 결과코드
     *
     * @param   {String}    p12         안드로이드에서 선택한 base64 인코딩 된 인증서
     *
     */
    saveBrowserRepositoryNoServer: function (repCode, pfx) {
        // sloth 이용하지 않는 휴대폰가져오기 화면에서 호출됨.
        console.log('saveBrowserRepositoryNoServer 호출됨.');
        var doc = document.getElementById("yettie_iframe");

        if (repCode == 0 && pfx != "") {
            var _pfx = vest.signHelper.decodeString(pfx, 'base64');
            var _url = vest.signHelper.encodeBytes(parent.window.location.origin + parent.window.location.pathname, 'hex');
            var _pin = vest.signHelper.encodeBytes(vest.getHash(_url), 'base64');

            vest.secureCertificateStorage.addCertificateItemFromPFX({pfx: _pfx}, _pin, {}, function () {
                // 인증서 업데이트 성공시 SIGNLIST로 이동
                doc.contentDocument.getElementById("importToWeb_btn").click();
            }, function (error) {
                // 인증서 업데이트 실패시 SIGNLIST로 이동
                alert(error.getReason());
                doc.contentDocument.getElementById("pre_btn").click();
            });
        } else {
            alert(vest.util.refactoryMsg.koreaMintLibErrorHandler(repCode));
            doc.contentDocument.getElementById("pre_btn").click();
        }
    }
};