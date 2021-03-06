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

var mobile_sign = (function (doc, $, vest, params, vestSign) {
    'use strict';

    if (vestSign === undefined) {
        alert(mFromPhoneLang(0));
        return false;
    }

    var _parent = vestSign;
    var _config = vestSign.getConfig();
    var _param;

    var _targetURL;
    var _authCode;
    var _slothReceiver;
    var _mobileKeySafer;
    var _pin = $('#pin');

    var _pfx;
    var _newestApp;

    function setSloth() {
        if (_config.slothPath == '')
            _config.slothPath = 'https://' + location.host;

        _slothReceiver = new vest.sloth(_config.slothPath + '/sloth');
        _slothReceiver.create(function (result) {
            if (typeof(result.getReason) === 'function') {
                //error
                //console.log(result.getReason());
            } else {
                var _url = (_config.recvUrl != "") ? _config.recvUrl : parent.window.location.origin + parent.window.location.pathname;

                _targetURL = vest.signHelper.encodeBytes(_url, 'hex');
                _authCode = vest.signHelper.encodeBytes(result[0] + result[1] + result[2], 'hex');
            }
        });
    }

    function setAppLink() {
        setTimeout(function () {
            parent.window.location.href = 'intent://koreamint?TargetURL=' + _targetURL + '&AuthCode=' + _authCode + '&JsVersion=' + _config.slothVersion + '#Intent;scheme=mangowire;action=android.intent.action.VIEW;category=android.intent.category.BROWSABLE;package=com.yettiesoft.koreamint;end;';
        }, 1000);
    }

    function isAndroid() {
        if (!window.navigator.userAgent.match(/Android/))
            return false;
        else
            return true;
    }

    // _pin.focusout(function () {
    //     if (_mobileKeySafer.getType() == "undefined"){
    //         _config.touchFlag = false;
    //         inputBoxOutHint();
    //     } 
    // });

    // _pin.focusin(function () {
    //     if (_mobileKeySafer.getType() == "undefined") _config.touchFlag = true;

    //     _pin.attr("nori", true);
    //     inputBoxInHint();
    // });

    $('#install_btn').click(function () {
        // $("#pin").removeClass('pwd_disabled');
        // $('#pin').attr('disabled', false);
        // $('#pin').attr('readonly', false);

        setAppLink();

        $('#get_btn').show();

        $('#get_btn').click(function () {
            okButtonEvent();
        });
    });

    $('#x_btn').click(function () {
        if (_config.useMobileNPKI) {
            _param.errorcallback(new vest.error(11500, '?????????????????????'));
        } else {
            _param.callback({action: 'VIEW.SIGNLIST', option: _param.option});
        }
    });

    // $('#pre_btn').click(function () {
    //     if (_config.useMobileNPKI) {
    //         _param.errorcallback(new vest.error(11500, '?????????????????????'));
    //     } else {
    //         _param.callback({action: 'VIEW.SIGNLIST', option: _param.option});
    //     }
    // });

    function inputBoxInHint() {
        _pin.attr("placeholder", "");
        _pin.css("font-size", "80rem");
    }

    function inputBoxOutHint() {
        if (_pin.val().length <= 0) {
            _pin.attr("placeholder", mFromPhoneLang(2));
            _pin.css("font-size", "28rem");
        }
    }

    var pinTypeCheck = function (pin) {
        if (_pin.attr('nori') == 'undefined' || _pin.attr('nori') != 'true') {
            alert(mFromPhoneLang(2));
            return false;
        }

        if (typeof(pin) === 'string' && pin == '') {
            alert(mFromPhoneLang(2));
            return false;
        }
        return true;
    };

    // var getPassword = function (callback) {
    //     if (!_mobileKeySafer.initalizeCheck() || _mobileKeySafer.getType() == "undefined") {
    //         callback(_pin.val());
    //     } else {
    //         _mobileKeySafer.getPassword(function (pin) {
    //             callback(pin);
    //         }, 'pin');
    //     }
    // };

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
        var password;
        // decryptPassword(pin, function (password) {
            if (typeof _newestApp !== 'undefined') {
                // ?????? ???
                // password = pin;
                if (_newestApp){ // ????????? ???????????? ??????
                    // password = vest.signHelper.encodeBytes(vest.getHash(password), 'hex');
                    password = vest.signHelper.encodeBytes(vest.getHash(pin), 'hex');
                }else // ????????? ???????????? ??????
                    password = pin;
            } else {
                // ????????? ???, ??? ???????????? ??????
                if (confirm(mFromPhoneLang(14)))
                    parent.window.location.href = "https://play.google.com/store/apps/details?id=com.yettiesoft.koreamint";
                _pin.val('');
                return;
            }
            // vest.secureCertificateStorage.checkPinPFX(_pfx, password, {mobileKeystrokeEncryption: _config.mobileKeystrokeEncryption}, function () {
            vest.secureCertificateStorage.checkPinPFX(_pfx, password, {}, function () {
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
                // if (_newestApp) {
                //     if (typeof(_mobileKeySafer.clearPassword) !== 'undefined') _mobileKeySafer.clearPassword();
                // }
            });
        // });
    };

    function okButtonEvent() {
        var nextEvent = function (pin) {
            // if (!pinTypeCheck(pin)) {
            //     return;
            // }
            if (typeof _pfx === "undefined") {
                _slothReceiver.read(function (result) {
                    // if(Object.entries(result).length === 0) {
                    //     setAppLink();
                    //     setSloth();
                    //     _pin.val('');
                    // }
                     if (typeof(result.getReason) === 'function' || !result.hasOwnProperty('p12')) {
                        //error
                        setAppLink();
                        setSloth();
                        _pin.val('');
                        // alert(typeof(result.getReason) === 'function' ? result.getReason() : '????????? ??????????????? ?????????????????????(?????? ????????? ???????????? ?????? ??????????????????).');
                    }
                    else {
                        _pfx = result.p12;
                        _newestApp = JSON.parse(result.option).newestApp;
                        dropPFX(pin);
                    }
                });
            } else {
                dropPFX(pin);
            }
        };

        nextEvent(vest.signHelper.decodeString(_authCode, 'hex'));                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                      
        // getPassword(nextEvent);
    }

    // var keySaferScriptLoading = function (keySaferObj, i, func) {
    //     var script = keySaferObj.getScript();
    //     var scriptLen = script.length;

    //     if (scriptLen == 0) return;

    //     var callback = function () {
    //         if (i == scriptLen - 1) {
    //             func();
    //             return;
    //         }
    //         keySaferScriptLoading(keySaferObj, ++i, func);
    //     };

    //     var head = document.getElementsByTagName("head")[0];
    //     var keyScript = document.createElement("script");
    //     keyScript.src = keySaferObj.getPath() + script[i];

    //     var flag = false;
    //     if (keyScript.addEventListener) {
    //         keyScript.addEventListener("load", callback);
    //     }
    //     else if (keyScript.readyState) {
    //         keyScript.onreadystatechange = function () {
    //             if (this.readyState == "loaded" || this.readyState == "complete") {
    //                 if (!flag) {
    //                     flag = true;
    //                     callback();
    //                 }
    //             }
    //         };
    //     }
    //     else {
    //         keySaferObj = keySafer();
    //     }

    //     head.appendChild(keyScript);
    // };

    $(document).ready(function () {
        // param ??????
        try {
            _parent.getParameters();
        } catch (e) {
            alert(mFromPhoneLang(1));
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

        // $('#passwordBox').show();
        $('#get_btn').hide();        
        $('#commonBtn').show();

        if (window.navigator.userAgent.match(/Android/)) {
            setSloth();

            if (_config.slothVersion*1 >= 1.0) { // ?????? ???????????? ??????
                _pin.attr('placeholder', mFromPhoneLang(2));
                // _mobileKeySafer = mobileKeySafer(_config.mobileKeystrokeEncryption, ['pin'], _config.mobileKeySaferConfig);
            } else {    // ????????? ???????????? ??????
                $('.PHONE_pw_info').html(mFromPhoneLang(16));
                _pin.attr('placeholder', mFromPhoneLang(15));
                // _mobileKeySafer = mobileKeySafer('', ['pin'], _config.mobileKeySaferConfig);
            }

            // keySaferScriptLoading(_mobileKeySafer, 0, _mobileKeySafer.init);
        } else {
            alert(mFromPhoneLang(14));
            _param.callback({action: 'VIEW.SIGNLIST', option: _param.option});
            return false;
        }
    });
})(document, jQuery, vest, params, VestSign);

var yettie = vest.util.size;