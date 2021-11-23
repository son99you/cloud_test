//version: 3.1.8_e54ce34761bf3c9776b1c6dd23fc7a4531eaad89
//update: Thu Jan 16 10:11:57 2020 +0900

/**
 * Created by wjjung on 2014-11-05.
 */

var VestSign = (function (vestSign) {
    return vestSign;
})((function () {
    'use strict';
    var vestSign = undefined;
    if (window.opener) {
        if (!(typeof(window.opener.vestSign) === 'undefined' || window.opener.vestSign == "")) return window.opener.vestSign;
    } else if (window.parent.vestSign) {
        if (!(typeof(window.parent.vestSign) === 'undefined' || window.parent.vestSign == "")) return window.parent.vestSign;
    }
    else {
        return vestSign;
    }
})());

(function (window, vest, undefined) {
    'use strict';

    /**
     * 웹 저장소를 사용하기 위한 접근자이다.
     * @namespace WebStorage
     * @memberof vest.util
     */
    var WebStorage = function (type) {
        var
            localStorage = window.localStorage,
            sessionStorage = window.sessionStorage,
            _storage,
            _this = this;


        if (vest.isUndefined(Storage)) {
            throw ('No web storage support');
        }

        /**
         * 웹 저장소 내부 변수에 사용할 웹 저장소를 설정할 때 사용하는 함수이다.
         * @memberof vest.util.WebStorage
         *
         * @param   {String}   type       웹 저장소 타입
         */
        this.setStorage = function (type) {
            switch (type) {
                case WebStorage.TYPE.LOCALSTORAGE:
                    _storage = localStorage;
                    break;
                case WebStorage.TYPE.SESSIONSTORAGE:
                    _storage = sessionStorage;
                    break;
                default:
                    throw('Not supported storage');
            }
        };
        /**
         * 웹 저장소에 원하는 데이터를 저장할 때 사용하는 함수이다.
         * @memberof vest.util.WebStorage
         *
         * @param   {String}   key       value를 저장할 키 네임
         * @param   {String}   value     웹 저장소에 저장할 값
         *
         * @param   {Function} callback       성공 시 callback 함수
         * @param              callback.      성공 시 함수 실행
         *
         * @param   {Function} errorcallback          실패 시 callback 함수
         * @param   {Object}   errorcallback.result        결과 값
         * @param   {Number}   errorcallback.result.code   기반 에러코드
         * @param   {Array}    errorcallback.result.reason 기반 에러문
         */
        this.set = function (key, value, callback, errorcallback) {
            if (arguments.length < 2) {
                throw ('2 arguments required, but only ' + arguments.length);
            }
            _storage.setItem(key, value);
            (callback || vest.noop)();
        };
        /**
         * 웹 저장소에 key에 해당하는 데이터를 가져올 때 사용하는 함수이다.
         * @memberof vest.util.WebStorage
         *
         * @param   {String}   key       value를 저장한 키 네임
         *
         * @param   {Function} callback       성공 시 callback 함수
         * @param   {String}   callback.result      웹 저장소에서 가져온 데이터
         *
         * @param   {Function} errorcallback          실패 시 callback 함수
         * @param   {Object}   errorcallback.result        결과 값
         * @param   {Number}   errorcallback.result.code   기반 에러코드
         * @param   {Array}    errorcallback.result.reason 기반 에러문
         */
        this.get = function (key, callback, errorcallback) {
            if (arguments.length < 1) {
                throw ('key required');
            }
            (callback || vest.noop)(_storage.getItem(key));
        };
    };

    /**
     * 웹 저장소 접근자의 저장소를 정의한 객체 변수이다.<br>
     * {
        LOCALSTORAGE: 1,
        SESSIONSTORAGE: 2
     * }
     * @memberof vest.util.WebStorage
     */
    WebStorage.TYPE = {
        LOCALSTORAGE: 1,
        SESSIONSTORAGE: 2
    };

    /**
     * File API를 사용하기 위한 접근자이다.
     * @namespace file
     * @memberof vest.util
     */
    var file = {
        /**
         * File API 사용 가능 여부를 확인할 때 사용하는 함수
         * @memberof vest.util.file
         *
         * @return  {Boolean}  결과에 따라서 true/false 반환
         */
        hasFileAPI: function () {
            return (vest.isDefined(window.File) && vest.isDefined(window.FileReader) && vest.isDefined(window.Blob));
        },
        /**
         * 파일의 내용을 확인하기 위해 사용하는 함수
         * @memberof vest.util.file
         *
         * @param   {ArrayBuffer}  file       파일
         * @param   {Function}     callback   결과 값
         * @param   {String}       callback.result   파일 내용
         */
        read: function (file, callback) {
            if (!vest.isFile(file)) {
                throw('invalid arguments: not File object')
            }

            var reader = new FileReader();

            reader.onload = function () {
                (callback || vest.noop)(reader.result);
            };

            reader.readAsArrayBuffer(file);
        },
        /**
         * @memberof vest.util.file
         *
         * @param    {String}  data
         * @return   {String}
         */
        getPFXUrl: function (data) {
            return 'data:application/x-pkcs12;base64,' + yt.util.encode64(data);
        },
        /**
         * 파일에 내용을 저장하여 PC로 내려받기 위해 사용하는 함수
         * @memberof vest.util.file
         *
         * @param   {String}    fileName       저장할 파일명
         * @param   {String}    data           파일 내용
         */
        download: function (fileName, data) {
            var arrayBuffer;
            var uint8Array;
            var blob;
            var a;
            var msSaveBlob = 'msSaveBlob';

            arrayBuffer = new ArrayBuffer(data.length);
            uint8Array = new Uint8Array(arrayBuffer);

            for (var i = uint8Array.length; i--;) {
                uint8Array[i] = data[i].charCodeAt(0);
            }

            blob = new Blob([uint8Array], {type: 'application/octet-stream'});
            a = window.document.createElement('a');

            if (msSaveBlob in window.navigator) a.onclick = window.navigator.msSaveBlob(blob, fileName);
            else a.href = window.URL.createObjectURL(blob);

            a.download = fileName;
            document.body.appendChild(a);
            a.click();
            document.body.removeChild(a);
        }
    };

    /**
     * 버전에 따른 사용 가능 여부를 파악할 때 사용하는 접근자이다.
     * @namespace certVersion
     * @memberof vest.util
     */
    var certVersion = {
        /**
         * 설정해둔 최소 버전과 VestCert의 버전을 비교하여 사용가능 여부를 확인할 때 사용하는 함수
         * @memberof vest.util.certVersion
         *
         * @param   {String}   ver        현재 버전
         * @param   {String}   limitVer   지원가능한 최소버전
         *
         * @return  {Boolean}  결과에 따라서 true/false 반환
         */
        versionCheck: function (ver, limitVer) {
            if (typeof ver === 'undefined') return false;
            var verArr = ver.split('.');
            var limitVerArr = limitVer.split('.');

            for (var i = 0; i < limitVerArr.length; i++) {
                if (parseInt(verArr[i]) < parseInt(limitVerArr[i])) {
                    return false;
                }
                else if (parseInt(verArr[i]) > parseInt(limitVerArr[i])) {
                    return true;
                }
            }
            return true;
        },
        /**
         * 설정해둔 최소 버전과 VestCert의 버전을 비교하여 사용가능 여부를 확인할 때 사용하는 함수
         * @memberof vest.util.certVersion
         *
         * @param   {String}   ver        현재 버전
         * @param   {String}   limitVer   지원가능한 최소버전
         *
         * @return  {Boolean}  결과에 따라서 true/false 반환
         */
        availableVersion: function (ver, limitVer) {

            if(ver == '' || typeof ver === 'undefined') return false;

            var verArr = ver.split('.');
            var limitVerArr = limitVer.split('.');

            for (var i = 0; i < limitVerArr.length; i++) {
                if (parseInt(verArr[i]) < parseInt(limitVerArr[i])) {
                    return false;
                }
                else if (parseInt(verArr[i]) > parseInt(limitVerArr[i])) {
                    return true;
                }
            }
            return true;
        },
        /**
         * 모듈 이름과 설정한 모듈 객체의 버전이 일치할때 설정한 버전을 반환하기 위해 사용하는 함수
         * @memberof vest.util.certVersion
         *
         * @param   {String}   moduleName        모듈 이름
         * @param   {Object}   versions          모듈 버전 객체
         *
         * @return  {String}  버전 반환
         */
        getVersion: function (moduleName, versions) {
            var version;
            vest.forEach(versions, function (object) {
                if (object.moduleName === moduleName) {
                    version = object.version;
                }
            });
            return version;
        }
    };

    /**
     * 객체에 값을 설정 할때 사용하는 접근자이다.
     * @namespace setObject
     * @memberof vest.util
     */
    var setObject = {
        /**
         * 파라미터의 객체에 화면의 중앙 좌표(x, y)를 추가하여 반환 할때 사용하는 함수
         * @memberof vest.util.setObject
         *
         * @param   {Object}   option   옵션객체
         */
        center: function (option) {
            var dualScreenLeft, dualScreenTop,
                innerWidth, innerHeight,
                outerWidth, outerHeight;

            /*
             Chrome      Firefox       InernetExplorer      Opera        Safari
             window.innerWidth                       : 브라우저 윈도우 두께를 제외한(탭, URL 등..) 제외한 가로 길이        1           1.0                 9              9            3
             window.innerHeight                      : 브라우저 윈도우 두께를 제외한(탭, URL 등..) 제외한 세로 길이        1           1.0                 9              9            3
             document.documentElement.clientWidth    : 브라우저 윈도우 두께를 제외한(탭, URL 등..) 제외한 가로 길이       yes          yes                 6             yes          yes
             document.documentElement.clientHeight   : 브라우저 윈도우 두께를 제외한(탭, URL 등..) 제외한 세로 길이       yes          yes                 6             yes          yes
             window.outerWidth                       : 브라우저 윈도우 두께를 포함한(탭, URL 등..) 제외한 가로 길이        1           1.0                 9              9            3
             window.outerHeight                      : 브라우저 윈도우 두께를 포함한(탭, URL 등..) 제외한 세로 길이        1           1.0                 9              9            3
             window.screenLeft                       : 바탕화면에서 X축 위치 값                                           yes          no                 yes            yes          yes
             window.screenTop                        : 바탕화면에서 Y축 위치 값                                           yes          no                 yes            yes          yes
             window.screenX                          : 바탕화면에서 X축 위치 값                                           yes          yes                 9             yes          yes
             window.screenY                          : 바탕화면에서 Y축 위치 값                                           yes          yes                 9             yes          yes
             */

            innerWidth = parent.window.innerWidth ? parent.window.innerWidth : parent.document.documentElement.clientWidth ? parent.document.documentElement.clientWidth : screen.width;
            innerHeight = parent.window.innerHeight ? parent.window.innerHeight : parent.document.documentElement.clientHeight ? parent.document.documentElement.clientHeight : screen.height;
            outerWidth = parent.window.outerWidth;
            outerHeight = parent.window.outerHeight;

            dualScreenLeft = typeof(parent.window.screenLeft) !== 'undefined' && typeof(parent.window.screenLeft) !== 'unknown' ? parent.window.screenLeft : parent.window.screenX;
            dualScreenTop = typeof(parent.window.screenTop) !== 'undefined' && typeof(parent.window.screenTop) !== 'unknown' ? parent.window.screenTop : parent.window.screenY;

            if (!(vest.browser.isMSIE())) {
                dualScreenLeft += outerWidth - innerWidth;
                dualScreenTop += outerHeight - innerHeight;
            }

            option.center = {
                x: (innerWidth / 2) + dualScreenLeft,
                y: (innerHeight / 2) + dualScreenTop
            };
        }
    };

    /**
     * 에러코드와 에러문에 대한 다국어 처리 및 정리 할때 사용하는 접근자이다.
     * @namespace refactoryMsg
     * @memberof vest.util
     */
    var refactoryMsg = {
        /**
         * 설정된 언어에 따라서 다국어 변환 및 스크립트 에러 형식으로 변환하는데 사용하는 함수
         * @memberof vest.util.refactoryMsg
         *
         * @param   {Object}   error   에러객체
         * @param   {Number}   error.code   기반 에러코드
         * @param   {Array}    error.reason 기반 에러문
         */
        convertMsg: function (error) {
            var message = vest.error.getErrorMessage(error.code, VestSign.getConfig().langIndex);
            if (message == 'undefined') {
                if (typeof(error.getReason()) === "object") {
                    message = error.getReason() + "";
                    var subIndex = message.indexOf('reason:');
                    if (subIndex != -1) {
                        message = message.substring(subIndex + 7);
                    }
                }
            }

            var refactoryMessage;

            if (!VestSign.getConfig().useBracket && message.indexOf('(') != -1) {
                var subIndex = message.indexOf('(');
                refactoryMessage = message.substring(0, subIndex) + ".";
            }
            error.msg = (refactoryMessage !== undefined ? refactoryMessage : message) + ' [' + error.code + ']';
        },
        /**
         * 설정된 언어에 따라서 모바일 VestSign의 에러에 대한 다국어 변환 & 스크립트 에러문을 반환하기 위해 사용 하는 함수
         * @memberof vest.util.refactoryMsg
         *
         * @param   {Number}   code   기반 에러코드
         * @return  {String}   다국어 & 변환된 에러문
         */
        koreaMintLibErrorHandler: function (code) {
            var message = vest.error.getMobileErrorMessage(code, VestSign.getConfig().langIndex);
            return message + ' [' + code + ']';
        }
    };

    /**
     * VestCert를 호출하기 전 불필요한 속성을 없애기 위해 사용하는 함수이다.
     * @memberof vest.util
     *
     * @param   {Object}  object  option 객체
     */
    var removeObjectElement = function (object) {
        if (object.storageType != undefined)
            delete object.storageType;
        if (object.serial != undefined)
            delete object.serial;
        if (object.caType != undefined)
            delete object.caType;
    };

    /**
     * Internet Explorer 브라우저의 버전정보를 확인하기 위해 사용하는 함수이다.
     * @memberof vest.util
     *
     * @return {Integer}    IE 버전
     */
    var getIEVersion = function () {
        var ua = window.navigator.userAgent;
        var ie = ua.indexOf("MSIE");
        return ((ie > 0) ? parseInt(ua.substring(ie + 5, ua.indexOf(".", ie))) : 0);
    };

    /**
     * 비밀번호에 대한 복합성 체크를 확인하기 위해 사용하는 함수이다.
     * @memberof vest.util
     *
     * @param   {String}    pin          비밀번호
     * @param   {String}    newPin       새로운 비밀번호
     * @param   {String}    checkPin     새로운 비밀번호 확인
     *
     * @param   {Function}  callback     성공 callback 함수
     * @param   {Object}    callback.result         결과 값
     * @param   {Integer}   callback.result.code    성공시 0
     */
    var checkComplexPassword = function (pin, newPin, checkPin, callback) {
        var decryptPin = function (decryptPin, callback) {
            if (typeof decryptPin.type !== "undefined" && typeof decryptPin.type === "number") {
                var decKeySafer = vest.decryptKeySafer(decryptPin.type, decryptPin.config);
                decKeySafer.getDecryptedPassword(decryptPin.value, callback);
            }
            else {
                callback(decryptPin);
            }
        };
        var length = function (pwd) {
            if (pwd.length < 10 || pwd.length > 30) {
                return false;
            }
            return true;
        };
        var except = function (pwd) {
            if (pwd.indexOf('\"') != -1 ||
                pwd.indexOf('\'') != -1 ||
                pwd.indexOf('\|') != -1 ||
                pwd.indexOf('\\') != -1) {
                return false;
            }
            return true;
        };
        var rule = function (pwd) {
            var isAlpha = function (str) {
                var pattern = /^[a-zA-Z]+$/;
                return (pattern.test(str)) ? true : false;
            };
            var isDigit = function (str) {
                var pattern = /^[0-9]+$/;
                return (pattern.test(str)) ? true : false;
            };

            // 네자리 이상 연속 검사 (1111)
            for (var i = 0; i <= pwd.length - 4; i++) {
                var invalid = true;
                for (var j = i + 1; j < i + 4; j++) {
                    if (pwd[i] != pwd[j]) {
                        invalid = false;
                        break;
                    }
                }
                if (invalid)
                    return false;
            }

            // 두자리 반복 검사 (121212)
            for (var i = 0; i <= pwd.length - 6; i++) {
                var invalid = true;
                for (var j = i + 2; j < i + 6; j += 2) {
                    if (pwd[i] != pwd[j] || pwd[i + 1] != pwd[j + 1]) {
                        invalid = false;
                        break;
                    }
                }
                if (invalid)
                    return false;
            }

            // 세자리 반복 검사 (123123)
            for (var i = 0; i <= pwd.length - 6; i++) {
                var invalid = true;
                for (var j = i + 3; j < i + 6; j += 3) {
                    if (pwd[i] != pwd[j] || pwd[i + 1] != pwd[j + 1] || pwd[i + 2] != pwd[j + 2]) {
                        invalid = false;
                        break;
                    }
                }
                if (invalid)
                    return false;
            }

            // 연이은 네자리 문자 검사
            for (var i = 0; i <= pwd.length - 4; i++) {
                var invalid = true;
                for (var j = i + 1; j < i + 4; j++) {
                    if (pwd[j] - pwd[j - 1] != 1) {
                        invalid = false;
                        break;
                    }
                }
                if (invalid)
                    return false;
            }

            // 거꾸로 연이은 네자리 문자검사
            for (var i = 0; i < pwd.length - 4; i++) {
                var invalid = true;
                for (var j = i + 1; j < i + 4; j++) {
                    if (pwd[j - 1] - pwd[j] != 1) {
                        invalid = false;
                        break;
                    }
                }
                if (invalid)
                    return false;
            }

            var digit = false;
            var alpha = false;
            var nonalpha = false;

            for (var i = 0; i < pwd.length; i++) {
                if (isAlpha(pwd[i]))
                    alpha = true;
                else if (isDigit(pwd[i]))
                    digit = true;
                else
                    nonalpha = true;
            }

            if (!(digit && alpha && nonalpha))
                return false;

            return true;
        };

        var result = {code: 0};
        decryptPin(pin, function (nPin) {
            decryptPin(newPin, function (nNewPin) {
                decryptPin(checkPin, function (nCheckPin) {
                    if (nNewPin != nCheckPin) {
                        result = {code: 2400};
                    } else if (nPin == nNewPin) {
                        result = {code: 2402};
                    } else if (!(length(nNewPin)) || !(except(nNewPin)) || !(rule(nNewPin))) {
                        result = {code: 2401};
                    } else {
                        ;
                    }
                    callback(result);
                });
            });
        });
    };

    /**
     * VestSign의 키보드보안을 사용하면서 가상키패드에 따른 화면 조절을 할때 사용하는 접근자이다.
     * @namespace yettieFrameReSize
     * @memberof vest.util
     */
    var yettieFrameReSize = (function () {
        var defaultX, defaultY;
        var frame;

        /**
         * 내부 변수에 기존 사이즈를 설정하기 위해 사용하는 함수
         * @memberof vest.util.yettieFrameReSize
         */
        var setDefault = function () {
            // frame = parent.document.getElementById('yettie_iframe');
            
            if (parent.document.getElementById('yettie_upperIframe')) {
                frame = parent.document.getElementById('yettie_upperIframe');
            } else {
                frame = parent.document.getElementById('yettie_iframe');
            }

            defaultX = frame.getAttribute('width');
            defaultY = frame.getAttribute('height');

            if (defaultX.indexOf('px') != -1 && defaultY.indexOf('px') != -1) {
                defaultX = defaultX.substr(0, defaultX.length - 2);
                defaultY = defaultY.substr(0, defaultY.length - 2);
            }
        };
        /**
         * 내부 변수에 설정된 초기 값으로 설정하기 위해 사용하는 함수
         * @memberof vest.util.yettieFrameReSize
         */
        var defaultSize = function () {
            //if(parseInt(defaultY.substr(0, defaultY.length-2)) >= 380) return;
            //if(defaultY >= 380) return;
            
            if (parent.document.getElementById('yettie_upperIframe')) {
                frame = parent.document.getElementById('yettie_upperIframe');
            } else {
                frame = parent.document.getElementById('yettie_iframe');
            }

            frame.setAttribute('width', defaultX);
            frame.setAttribute('height', defaultY);
            frame.style.marginTop = "-" + defaultY.substring(0, 3) / 2 + "px";
            frame.style.marginLeft = "-" + defaultX.substring(0, 3) / 2 + "px";

            if (frame.id === 'yettie_upperIframe') {
                if(frame.contentDocument.getElementById("allLayerWrap") !== undefined && frame.contentDocument.getElementById("allLayerWrap") !== null) {
                    frame.contentDocument.getElementById("allLayerWrap").style.backgroundColor = "#FFFFFF";
                } else {
                    frame.contentDocument.body.style.backgroundColor = "#FFFFFF";
                    frame.contentDocument.getElementsByClassName("certWrap")[0].style.backgroundColor = "#FFFFFF";
                }
            } else {
                frame.contentDocument.body.style.backgroundColor = "";
                frame.contentDocument.getElementsByClassName("certWrap")[0].style.backgroundColor = "";
            }
        };
        /**
         * 내부 변수에 설정된 기존 사이즈를 가져오기 위해 사용하는 함수
         * @memberof vest.util.yettieFrameReSize
         *
         * @return {Object} 기존 VestSign의 x, y값
         */
        var getDefault = function () {
            return {x: defaultX, y: defaultY}
        };
        /**
         * 키패드 모듈에 따라서 사이즈를 조절하기 위해 사용하는 함수
         * @memberof vest.util.yettieFrameReSize
         *
         * @param   {String}   str   키보드 업체 이름
         * @param   {Object}   object       크기 값을 가지는 객체
         * @param   {String}   object.x     x값
         * @param   {String}   object.y     y값
         */
        var onKeypad = function (str, object) {
            //if(parseInt(defaultY.substr(0, defaultY.length-2)) >= 380) return;
            if (parent.document.getElementById('yettie_upperIframe')) {
                frame = parent.document.getElementById('yettie_upperIframe');
            } else {
                frame = parent.document.getElementById('yettie_iframe');
            }
            defaultX = frame.getAttribute('width').substring(0, 3);
            defaultY = frame.getAttribute('height').substring(0, 3);
            if (defaultY >= 380 && str != 'xkeypad' && str != 'vikie') return;
            switch (str) {
                case 'nprotect':
                case 'npk':
                    frame.setAttribute('height', '380px');
                    frame.style.marginTop = "-190px";
                    break;
                case 'transkey':
                    frame.setAttribute('height', '300px');
                    frame.style.marginTop = "-150px";
                    break;
                case 'xkeypad':
                    var temp = (defaultY * 1) + (object.y * 1) + 10;
                    if (object.x > defaultX) {
                        frame.setAttribute('width', object.x + 'px');
                        frame.style.marginLeft = "-" + object.x / 2 + "px";
                    }
                    frame.setAttribute('height', temp + 'px');
                    frame.style.marginTop = "-" + temp / 2 + "px";
                    break;
                case 'vikie':
                    frame.setAttribute('width', '800px');
                    frame.clientWidth;
                    frame.style.marginLeft = "-400px";
                    if (defaultY < 250) {
                        frame.setAttribute('height', '250px');
                    }
                    if (frame.id === 'yettie_upperIframe') {
                        frame.contentDocument.body.style.width = '430px';
                        frame.contentDocument.body.style.height = '647px';
                        frame.contentDocument.body.style.margin = '0 auto';
                        frame.contentDocument.body.style.backgroundColor = "transparent";
                        if(frame.contentDocument.getElementById("allLayerWrap") !== undefined && frame.contentDocument.getElementById("allLayerWrap") !== null) {
                            frame.contentDocument.getElementById("allLayerWrap").style.backgroundColor = "#FFFFFF";
                        } else {
                            frame.contentDocument.getElementsByClassName("certWrap")[0].style.backgroundColor = "#FFFFFF";
                            
                        }
                    } else {
                        frame.contentDocument.body.style.backgroundColor = "transparent";
                        frame.contentDocument.getElementsByClassName("certWrap")[0].style.backgroundColor = "#FFFFFF";
                    }
                    break;
                default:
                    break;
            }
        };

        return {
            setDefault: setDefault,
            defaultSize: defaultSize,
            onKeypad: onKeypad,
            offKeypad: defaultSize,
            getDefault: getDefault
        }
    })();

    /**
     * 모바일 VestSign의 해상도에 대한 최적 화면을 보여주기 위해 사용하는 함수이다.
     * @memberof vest.util
     */
    var modifyMobileFrame = function (doc) {
        var iframe = parent.document.getElementById("yettie_iframe");

        if (iframe == null) return;

        var browserHeight = parent.document.documentElement.clientHeight;
        var browserWidth = parent.document.documentElement.clientWidth;

        var bodyHeight = iframe.contentDocument.body.firstElementChild.offsetHeight;
        var bodyWidth = iframe.contentDocument.body.firstElementChild.offsetWidth;

        var ratio;
        if ( browserWidth / browserHeight > 2/3 ) {
            /* 높이 기준으로 화면 조절 */
            if (browserHeight != bodyHeight) {
                ratio = browserHeight / bodyHeight;
                resizeCSS(doc, ratio);
            }
        } else {
            /* 넓이 기준으로 화면 조절 */
            if (browserWidth != bodyWidth) {
                ratio = browserWidth / bodyWidth;
                resizeCSS(doc, ratio);
            }
        }

        iframe.contentDocument.body.style.marginTop = (browserHeight - iframe.contentDocument.body.firstElementChild.offsetHeight) / 2 + 'px';
        iframe.contentDocument.documentElement.style.opacity = 1;

        function resizeCSS (doc, ratio) {
            var tempValue;
            var styleAttr = {
                width: 'width',
                height: 'height',

                marginTop: 'marginTop',
                marginRight: 'marginRight',
                marginBottom: 'marginBottom',
                marginLeft: 'marginLeft',

                paddingTop: 'paddingTop',
                paddingRight: 'paddingRight',
                paddingBottom: 'paddingBottom',
                paddingLeft: 'paddingLeft'
            };

            /* doc에 해당하는 css 리스트 읽기 */
            for (var iter = 0; iter < doc.styleSheets[0].cssRules.length; iter++) {
                /* html의 default fontSize 읽어서 크기 계산 */
                if (doc.styleSheets[0].cssRules[iter].selectorText == 'html') {
                    var fontRatio = doc.styleSheets[0].cssRules[iter].style.fontSize;
                    doc.styleSheets[0].cssRules[iter].style.fontSize = parseFloat(fontRatio) * ratio + '%';
                }

                /* styleAttr에 해당하는 style 값에 대해서 사이즈 계산 */
                for (var key in styleAttr) {
                    if (typeof doc.styleSheets[0].cssRules[iter].style !== 'undefined' && doc.styleSheets[0].cssRules[iter].style[key] != "") {
                        if (typeof doc.styleSheets[0].cssRules[iter].style[key] === 'string' && doc.styleSheets[0].cssRules[iter].style[key].indexOf('px') != -1) {
                            tempValue = parseFloat(doc.styleSheets[0].cssRules[iter].style[key]);
                            doc.styleSheets[0].cssRules[iter].style[key] = (tempValue * ratio) + 'px';

                            /* width, height 지정되 있는 style에 backgroundSize 속성 추가 (이미지 사이즈 계산) */
                            if (key == styleAttr.width || key == styleAttr.height) {
                                doc.styleSheets[0].cssRules[iter].style.backgroundSize = 'cover';
                            }
                        }
                    }
                }

            }
        }
    };


        var matchedDn = function(subjectDn, matchedDn) {
        function getField(str, token){
            var arr, i = 0, result = '', count = 0;
            arr = str.split(',');
            count = arr.length;

            if(token == '') return count;

            while(typeof(arr[i]) != 'undefined'){
                result = arr[i].split('=');
                if(result[0].toLowerCase() == token.toLowerCase()){
                    return result[1];
                }
                i++;
            }

            return count;
        }

        var count = getField(matchedDn, '');
        var cn = getField(subjectDn, 'cn');
        var configCn = getField(matchedDn, 'cn');

        if(configCn == 1) return false;

        if(count > 1){
            if(subjectDn.toLowerCase().indexOf(matchedDn.toLowerCase()) !== -1) return true;
        }else{
            if(cn.indexOf(configCn) !== -1) return true;
        }

        return false;
    };

    var serialFilterForKoscom = function(serial) {
        // serial: hex로된 serial값
        /*
         . 일련번호가 4바이트 이내 정수이면(헥사스트링표현으로 8바이트 이내) 십진수로 표현(decimal-string)
         단, 십진수로 표현시 10자리 이상인 경우, 헥사스트링(hexa-string)으로 표현
         . 일련번호가 4바이트 초과 정수이면(헥사스트링표현으로 9바이트 이상) 헥사스트링으로(hexa-string) 표현
         */
        var res = '';

        if(serial.length > 8 || (parseInt(serial, 16) + "").length >= 10) {
            res = serial;
        } else {
            res = parseInt(serial, 16) + "";
        };

        return res;
    }

    var cookie = {
        setCookie: function (key, val) {
            var str = key + "=" + escape(val);
            parent.document.cookie = str;
        },
        removeCookie: function (key) {
            parent.document.cookie = key + "=" + ";";
        },
        getCookie: function (key) {
            key = key + "=";
            var result = "";
            var cookieData = parent.document.cookie;
            var start = cookieData.indexOf(key);
            if (start != -1) {
                start += key.length;
                var end = cookieData.indexOf(";", start);
                if (end == -1) end = cookieData.length;
                result = cookieData.substring(start, end);
            }
            return unescape(result);
        }
    };

    function base64ToHex(base64) {
        return yt.util.bytesToHex(yt.util.decode64(base64));
    };

    function hexToBase64(hex) {
        return yt.util.encode64(yt.util.hexToBytes(hex));
    };

    function certificateSubjectDNField(str, token) {
        var arr, i = 0, result = '', count = 0;
        arr = str.split(',');
        count = arr.length;

        if(token == '') return count;

        while(typeof(arr[i]) != 'undefined'){
            result = arr[i].split('=');
            if(result[0].toLowerCase().trim() == token.toLowerCase()){
                return result[1];
            }
            i++;
        }

        return count;
    }

    function HibiscusCertListToVestCertCertList(list) {
        /**
        *  base64(certificate) -> 
        * {
        * issuer: "CN=SignKorea Test CA4, OU=AccreditedCA, O=SignKorea, C=KR
        * tokenType: "DISK DRIVE",
        * tokenName: "하드디스크 - C:\\ ()",
        * certIdentifier: "5E4578921E4BA8100A241F2CAAD2FDD21F342F581"
        * keyIdentifier: "5E4578921E4BA8100A241F2CAAD2FDD21F342F582"
        * kmCertIdentifier: "5E4578921E4BA8100A241F2CAAD2FDD21F342F583"
        * kmKeyIdentifier: "5E4578921E4BA8100A241F2CAAD2FDD21F342F584"
        * policy: {
        *           id: "1.2.410.200004.5.1.1.7"
        *           userNotice: "이 인증서 시험용 인증서입니다↵"
        * }
        * serial: "07CEF1"
        * validFrom: "20180110151100"
        * validTo: "20190110235959",
        * dn: "CN=20180110-001, OU=테스트지점, OU=테스트회사, OU=테스트업종, O=SignKorea, C=KR"
        * }
        */

        var resList = [];

        for(var i = 0; i < list.length; i++) {
            var cert = yt.pki.Certificate.fromBytes(list[i].signCertificate);
            var temp = {
                dn: cert.subject,
                issuer: cert.issuer,
                serial: cert.serialNumber,
                validFrom: moment(cert.validity.notBefore),
                validTo:  moment(cert.validity.notAfter),
                policy: {
                    id: (cert.extensions.certificatePolicies != undefined) ?  ( ((cert.extensions.certificatePolicies.policies) != undefined) ? Object.keys(cert.extensions.certificatePolicies.policies)[0] : null ) : null,
                }
            }
            temp.policy.userNotice = cert.extensions.certificatePolicies.policies[temp.policy.id].userNotice[0];
            temp.certIdentifier = list[i].name;
            temp.keyIdentifier = list[i].name;
            temp.kmCertIdentifier = list[i].name;
            temp.kmKeyIdentifier = list[i].name;
            temp.tokenType = 'HIBISCUS';
            temp.tokenName = 'HIBISCUS';
            temp.tokenIdentifier = 'HIBISCUS'

            resList.push(temp);
        }

        return resList;
    }

    var viewsTitle = {
        passwordInputTitle: {
            ISSUE: 'ISSUE',
            REISSUE: 'REISSUE',
            UPDATECERTIFICATE: 'UPDATECERTIFICATE',
            CHANGEPASSWORD: 'CHANGEPASSWORD'
        }
    };

    if (vest) {
        /**
         * VestSign의 util 함수에 접근하기 위한 접근자이다.
         * @namespace util
         * @memberof vest
         */
        vest.util = vest.util || (vest.util = {});
        vest.extend(vest.util, {
            // 'certClasses': certClasses,
            'WebStorage': WebStorage,
            'file': file,
            'certVersion': certVersion,
            'setObject': setObject,
            'refactoryMsg': refactoryMsg,
            'checkComplexPassword': checkComplexPassword,
            'removeObjectElement': removeObjectElement,
            'getIEVersion': getIEVersion,
            'size': yettieFrameReSize,
            'modifyMobileFrame': modifyMobileFrame,
            'matchedDn': matchedDn,
            'serialFilterForKoscom': serialFilterForKoscom,
            'cookie': cookie,
            'base64ToHex': base64ToHex,
            'hexToBase64': hexToBase64,
            'certificateSubjectDNField': certificateSubjectDNField,
            'title': viewsTitle,
            'HibiscusCertListToVestCertCertList': HibiscusCertListToVestCertCertList
        });
    }
})(window, vest);
