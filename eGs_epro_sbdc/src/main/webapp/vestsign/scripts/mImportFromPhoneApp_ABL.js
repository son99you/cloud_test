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
            if (typeof (result.getReason) === 'function') {
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

    $('#install_btn').click(function () {
        okButtonEvent();
    });

    $('#x_btn').click(function () {
        if (_config.useMobileNPKI) {
            _param.errorcallback(new vest.error(11500, '취소하셨습니다'));
        } else {
            _param.callback({ action: 'VIEW.SIGNLIST', option: _param.option });
        }
    });

    var dropPFX = function (pin) {
        var password;
        if (typeof _newestApp !== 'undefined') {
            // 최신 앱
            // password = pin;
            if (_newestApp) { // 인증서 비밀번호 처리
                password = vest.signHelper.encodeBytes(vest.getHash(pin), 'hex');
            } else // 전송용 인증번호 처리
                password = pin;
        } else {
            // 구버전 앱, 앱 업데이트 처리
            if (confirm(mFromPhoneLang(14)))
                parent.window.location.href = "https://play.google.com/store/apps/details?id=com.yettiesoft.koreamint";
            _pin.val('');
            return;
        }
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
    };

    function okButtonEvent() {
        var nextEvent = function (pin) {
            if (typeof _pfx === "undefined") {
                _slothReceiver.read(function (result) {
                    if (typeof (result.getReason) === 'function' || !result.hasOwnProperty('p12')) {
                        //error
                        setAppLink();
                        setSloth();
                        _pin.val('');
                        if(typeof (result.getReason) === 'function') {
                            alert(result.getReason());
                        }
                        // alert(typeof (result.getReason) === 'function' ? result.getReason() : '인증서 가져오기를 실패하였습니다(앱을 통해서 인증서를 먼저 선택해주세요).');
                    } else {
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
    }

    $(document).ready(function () {
        // param 세팅
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
        
        $('#commonBtn').show();

        if (window.navigator.userAgent.match(/Android/)) {
            setSloth();
            if (_config.slothVersion*1 >= 1.0) { // 해쉬 비밀번호 사용
                _pin.attr('placeholder', mFromPhoneLang(2));
            } else {    // 전송용 인증번호 사용
                $('.PHONE_pw_info').html(mFromPhoneLang(16));
                _pin.attr('placeholder', mFromPhoneLang(15));
            }
        } else {
            alert(mFromPhoneLang(14));
            _param.callback({ action: 'VIEW.SIGNLIST', option: _param.option });
            return false;
        }
    });
})(document, jQuery, vest, params, VestSign);

var yettie = vest.util.size;