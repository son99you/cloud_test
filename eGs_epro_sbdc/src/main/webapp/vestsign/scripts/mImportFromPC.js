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
        alert(mFromPCLang(0));
        return false;
    }

    var _parent = vestSign;
    var _param;
    var _config = vestSign.getConfig();

    var _numBox1 = $('#num1');
    var _numBox2 = $('#num2');
    var _numBox3 = $('#num3');
    var _pin = $('#pin');
    var _failedCount = 0; 

    var _slothReceiver;
    var _mobileKeySafer;

    var _pfx;

    _pin.focusout(function() {
        if (_mobileKeySafer.getType() == "undefined"){
            _config.touchFlag = false;
            inputBoxOutHint();
        } 
    });

    _pin.focusin(function() {
        if (_mobileKeySafer.getType() == "undefined") _config.touchFlag = true;

        //_pin.val('');
        _pin.attr("nori", true);
        inputBoxInHint();
    });

    $('#x_btn').click(function() {
        if (_config.useMobileNPKI) {
            _param.errorcallback(new vest.error(11500, '취소하셨습니다'));
        } else {
            _param.callback({action: 'VIEW.SIGNLIST', option: _param.option});
        }
    });

    $('#cancel_btn').click(function () {
        if (_config.useMobileNPKI) {
            _param.errorcallback(new vest.error(11500, '취소하셨습니다'));
        } else {
            _param.callback({action: 'VIEW.SIGNLIST', option: _param.option});
        }
    });

    $('#confirm_btn').click(function () {
        okButtonEvent();
    });

    function inputBoxInHint() {
        _pin.attr("placeholder", "");
        _pin.css("font-size", "80rem");
    }

    function inputBoxOutHint() {
        if(_pin.val().length <= 0) {
            _pin.attr("placeholder", mFromPCLang(2));
            _pin.css("font-size", "28rem");
        }
    }

    function setSloth() {
        if (_config.slothPath == '')
            _config.slothPath = 'https://' + location.host;

        _slothReceiver = new vest.sloth(_config.slothPath + '/sloth');
        _slothReceiver.create(function(result) {
            // 버튼활성화 클릭못하게 막기..
            if(typeof(result.getReason) === 'function') {
                //error
                //console.log(result.getReason());
            } else {
                _numBox1.val(result[0]);
                _numBox2.val(result[1]);
                _numBox3.val(result[2]);
            }
        });
    };

    var pinTypeCheck = function (pin) {
        if(_pin.attr('nori') == 'undefined' || _pin.attr('nori') != 'true') {
            alert(mFromPCLang(2));
            return false;
        }

        if (typeof(pin) === 'string' && pin == '') {
            alert(mFromPCLang(2));
            return false;
        }
        return true;
    };

    var getPassword = function (callback) {
        if(!_mobileKeySafer.initalizeCheck() || _mobileKeySafer.getType() == "undefined") {
            callback(_pin.val());
        } else {
            _mobileKeySafer.getPassword(function (pin) {
                callback(pin);
            }, 'pin');
        }
    };

    var dropPFX = function (pin) {
        vest.secureCertificateStorage.checkPinPFX(_pfx, pin, {mobileKeystrokeEncryption: _config.mobileKeystrokeEncryption}, function () {
            _slothReceiver.dropSession(function () {
                _param.callback({
                    action: 'IMPORTPFX',
                    pfx: _pfx,
                    pfxPassword: pin
                });
            });
        }, function (error) {
            alert(error.getReason());
            _pin.val('');
            if(typeof(_mobileKeySafer.clearPassword) !== 'undefined') _mobileKeySafer.clearPassword();

            if(error.code == 9030){
                _failedCount++;
                if(_failedCount >= _config.signErrMaxValue) {
                    _param.errorcallback(error);
                }
            }
        });
    };

    function okButtonEvent() {
        var nextEvent = function(pin) {
            if (!pinTypeCheck(pin)) {
                return;
            }

            if (typeof _pfx === "undefined") {
                _slothReceiver.read(function (result) {
                    if (typeof(result.getReason) === 'function' || !result.hasOwnProperty('p12')) {
                        //error
                        setSloth();
                        _pin.val('');
                        if(typeof(_mobileKeySafer.clearPassword) !== 'undefined') _mobileKeySafer.clearPassword();

                        alert(typeof(result.getReason) === 'function' ? result.getReason() : '인증서 가져오기를 실패하였습니다(PC에서 인증서 내보내기를 먼저 진행해주세요).');
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
            alert(mFromPCLang(1));
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
        _pin.attr('placeholder', mFromPCLang(2));

        _mobileKeySafer = mobileKeySafer(_config.mobileKeystrokeEncryption, ['pin'], _config.mobileKeySaferConfig);
        keySaferScriptLoading(_mobileKeySafer, 0, _mobileKeySafer.init);
    });
})(document, jQuery, vest, params, VestSign);

var yettie = vest.util.size;