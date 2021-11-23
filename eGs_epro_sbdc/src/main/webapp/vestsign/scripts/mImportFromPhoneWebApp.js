//version: 3.1.8_e54ce34761bf3c9776b1c6dd23fc7a4531eaad89
//update: Thu Jan 16 10:11:57 2020 +0900

var params = (function () {
    'use strict';

    var Parameters = function () {
        var _plain,
            _option,
            _callback,
            _errorcallback;
        var params = this;

        params.setParameters = function (plain, option, callback, errorcallback) {
            _plain = plain;
            _option = JSON.parse(option);
            _callback = callback;
            _errorcallback = errorcallback;
        };

        params.getPlain = function () {
            return _plain;
        };

        params.getOption = function () {
            return _option;
        };

        params.getCallback = function () {
            return _callback;
        };

        params.getErrorcallback = function () {
            return _errorcallback;
        };

        params.getParameters = function () {
            return {
                plain: _plain,
                option: _option,
                callback: _callback,
                errorcallback: _errorcallback
            };
        };
    };

    return new Parameters();
})();

var mobile_sign_webApp = (function (doc, $, vest, params, vestSign) {
    'use strict';

    if (vestSign === undefined) {
        alert(mFromPhoneWebLang(0));
        return false;
    }

    var _parent = vestSign;
    var _config = vestSign.getConfig();
    var _param;

    var _targetURL;
    var _authCode;
    var _slothReceiver;
    var _mobileKeySafer;
    var _pin = $('#selectedCertFile_pw');

    var _pfx;

    _pin.focusout(function() {
        if (_mobileKeySafer.getType() == "undefined"){
            _config.touchFlag = false;
            inputBoxOutHint();
        } 
    });

    _pin.focusin(function() {
        if (_mobileKeySafer.getType() == "undefined") _config.touchFlag = true;

        _pin.attr("nori", true);
        inputBoxInHint();
    });

    function setSloth() {
        if (_config.slothPath == '')
            _config.slothPath = 'https://' + location.host;

        _slothReceiver = new vest.sloth(_config.slothPath + '/sloth');
        _slothReceiver.create(function (result) {
            // 버튼활성화 클릭못하게 막기..
            if (typeof(result.getReason) === 'function') {
                //error
                //console.log(result.getReason());
            } else {
                _targetURL = vest.signHelper.encodeBytes(parent.window.location.origin + parent.window.location.pathname, 'hex');
                _authCode = vest.signHelper.encodeBytes(result[0] + result[1] + result[2], 'hex');

                if (window.navigator.userAgent.match(/Android/)) {
                    //console.log("android yettie.setSloth 호출(중계서버 O)");
                    yettie.setSloth(_targetURL, _authCode);
                } else {
                    alert(mFromPhoneWebLang(8));
                }
            }
        });
    }

    $('#x_btn').click(function () {
        _param.callback({action: 'VIEW.SIGNLIST', option: _param.option});
    });

    $('#pre_btn').click(function () {
        _param.callback({action: 'VIEW.SIGNLIST', option: _param.option});
    });

    $('#importToWeb_btn').click(function () {
        okButtonEvent();
    });

    function inputBoxInHint() {
        _pin.attr("placeholder", "");
        _pin.css("font-size", "80rem");
    }

    function inputBoxOutHint() {
        if(_pin.val().length <= 0) {
            _pin.attr("placeholder", mFromPhoneWebLang(2));
            _pin.css("font-size", "28rem");
        }
    }

    var pinTypeCheck = function (pin) {
        if (_pin.attr('nori') == 'undefined' || _pin.attr('nori') != 'true') {
            alert(mFromPhoneWebLang(2));
            return false;
        }

        if (typeof(pin) === 'string' && pin == '') {
            alert(mFromPhoneWebLang(2));
            return false;
        }
        return true;
    };

    var getPassword = function (callback) {
        if (!_mobileKeySafer.initalizeCheck() || _mobileKeySafer.getType() == "undefined") {
            callback(_pin.val());
        } else {
            _mobileKeySafer.getPassword(function (pin) {
                callback(pin);
            }, 'selectedCertFile_pw');
        }
    };

    var decryptPassword = function (password, callback) {
        var pin = password;
        var decKeySafer;

        if (typeof pin.type !== "undefined" && typeof pin.type === "number") {
            decKeySafer = vest.decryptKeySafer(pin.type, pin.config);
            decKeySafer.getDecryptedPassword(pin.value, callback);
        } else {
            callback(pin);
        }
    };

    var dropPFX = function (pin) {
        decryptPassword(pin, function (password) {
            vest.secureCertificateStorage.checkPinPFX(_pfx, password, {mobileKeystrokeEncryption: _config.mobileKeystrokeEncryption}, function () {
                _slothReceiver.dropSession(function () {
                    _param.callback({
                        action: 'IMPORTPFX',
                        pfx: _pfx,
                        pfxPassword: password
                    });
                });
            }, function (error) {
                alert(error.getReason());
                _pin.val('');
                if (typeof(_mobileKeySafer.clearPassword) !== 'undefined') _mobileKeySafer.clearPassword();
            });
        });
    };

    function okButtonEvent() {
        var nextEvent = function (pin) {
            if (!pinTypeCheck(pin)) {
                return;
            }

            if (typeof _pfx === "undefined") {
                _slothReceiver.read(function (result) {
                    if (typeof(result.getReason) === 'function' || !result.hasOwnProperty('p12')) {
                        //error
                        setSloth();
                        _pin.val('');
                        alert(typeof(result.getReason) === 'function' ? result.getReason() : '인증서 가져오기를 실패하였습니다(인증서를 먼저 선택 해주세요).');
                    } else {
                        _pfx = result.p12;
                        dropPFX(pin);
                    }
                });
            } else {
                dropPFX(pin);
            }
        };

        getPassword(nextEvent);
    };

    var keySaferScriptLoading = function (keySaferObj, i, func) {
        var script = keySaferObj.getScript();
        var scriptLen = script.length;

        if (scriptLen == 0) return;

        var callback = function () {
            if (i == scriptLen - 1) {
                func();
                return;
            }
            keySaferScriptLoading(keySaferObj, ++i, func);
        };

        var head = document.getElementsByTagName("head")[0];
        var keyScript = document.createElement("script");
        keyScript.src = keySaferObj.getPath() + script[i];

        var flag = false;
        if (keyScript.addEventListener) {
            keyScript.addEventListener("load", callback);
        }
        else if (keyScript.readyState) {
            keyScript.onreadystatechange = function () {
                if (this.readyState == "loaded" || this.readyState == "complete") {
                    if (!flag) {
                        flag = true;
                        callback();
                    }
                }
            };
        }
        else {
            keySaferObj = keySafer();
        }

        head.appendChild(keyScript);
    };

    $(document).ready(function () {
        // param 세팅
        try {
            _parent.getParameters();
        } catch (e) {
            alert(mFromPhoneWebLang(1));
            _parent.close();
        }
        _param = params.getParameters();

        vest.util.modifyMobileFrame(document);
        _config.touchFlag = false;
        parent.window.addEventListener("resize", function (e) {
            if (_config.touchFlag) return;
            vest.util.modifyMobileFrame(document);
            e.preventDefault();
        });

        $('#passwordBox').show();
        $('#commonBtn').show();
        
        setSloth();
        _pin.attr('placeholder', mFromPhoneWebLang(2));

        _mobileKeySafer = mobileKeySafer(_config.mobileKeystrokeEncryption, ['selectedCertFile_pw'], _config.mobileKeySaferConfig);
        keySaferScriptLoading(_mobileKeySafer, 0, _mobileKeySafer.init);
    });
})(document, jQuery, vest, params, VestSign);

if (typeof yettie === "object") {
    yettie.setDefault = vest.util.size.setDefault;
    yettie.defaultSize = vest.util.size.defaultSize;
    yettie.onKeypad = vest.util.size.onKeypad;
    yettie.offKeypad = vest.util.size.defaultSize;
    yettie.getDefault = vest.util.size.getDefault;
} else {
    var yettie = vest.util.size;
}
