//version: 3.1.8_e54ce34761bf3c9776b1c6dd23fc7a4531eaad89
//update: Thu Jan 16 10:11:57 2020 +0900

/**
 * Created by nori on 2015. 9. 1..
 */

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

var confirm = (function (doc, $, vest, params, vestSign) {

    if (vestSign === undefined) {
        alert( changePinLang(6) );
        return false;
    }

    var _parent = vestSign;
    var _config = vestSign.getConfig();
    var _param;
    var _keySafer;

    var _pin = $("#pin");
    var _newPin = $("#newPin");
    var _checkNewPin = $("#checkNewPin");
    var _checkbox_checked = $("#checked");
    var _checkbox_unchecked = $("#unChecked");

    vest.init(_config);

    var btnEvent = function() {
            // textbox 값이 비어있을 경우
            // if (typeof pin === 'string' && pin == '')
            //     throw changePinLang(7);
            // if (typeof newPin === 'string' && newPin == '')
            //     throw changePinLang(7);
            // if (typeof checkNewPin === 'string' && checkNewPin == '')
            //     throw changePinLang(7);

        _keySafer.getPassword(function (pin, newPin, checkNewPin) {
            if ( _checkbox_checked.val() ) {   // check 되어 있다면 유효성 체크
                // 죄송합니다.. 로직이 똥이네요.
                if(typeof _keySafer.isWebKeypad == 'function') {
                    vest.util.checkComplexPassword(pin, newPin, checkNewPin, function(result){
                        if(result.code != 0) {
                            vest.util.refactoryMsg.convertMsg(result);
                            alert(result.msg);
                        }else {
                            _param.callback(pin, newPin);
                        }
                    });
                } else {
                    vest.token.CheckComplexPin(pin, newPin, checkNewPin, _param.option, function() {
                        _param.callback(pin, newPin);
                    }, function(error) {
                        vest.util.refactoryMsg.convertMsg(error);
                        alert(error.msg);
                    });
                }
            } else {    // checked 안되어 있다면 유효성 체크 X
                // 왜 pin, pin으로 던질까.. (pin)으로 던지면 안되는건가
                // => 아마도 cert에서 하나만 던지면 안된다고 해서 두개 던졌겟지..
                _param.callback(pin, pin);
            }
        });
    };
    
    $("#dialogCertTab_okBtn").click(function(){
        try {
            btnEvent();
        } catch (error) {
            if ( typeof error === 'string' ) {
                alert(error);
            }
        }
    });

    _pin.keydown(function(key){
        if (key.keyCode == 13) {
            try {
                btnEvent();
            } catch (error) {
                if ( typeof error === 'string' ) {
                    alert(error);
                }
            }
        }
    });

    _newPin.keydown(function(key){
        if (key.keyCode == 13) {
            try {
                btnEvent();
            } catch (error) {
                if ( typeof error === 'string' ) {
                    alert(error);
                }
            }
        }
    });

    _checkNewPin.keydown(function(key){
        if (key.keyCode == 13) {
            try {
                btnEvent();
            } catch (error) {
                if ( typeof error === 'string' ) {
                    alert(error);
                }
            }
        }
    });

    $("#x_btn").click(function(){
        closeEvent();
    });

    $("#dialogCertTab_cancelBtn").click(function(){
        closeEvent();
    });

    _checkbox_checked.click(function() {
        checkboxOff();
        textboxDisable();

        _newPin.val('');
        _checkNewPin.val('');
    });

    _checkbox_unchecked.click(function() {
        checkboxOn();
        textboxEnable();
    });

    function textboxDisable() {
        _newPin.prop('disabled', true);
        _checkNewPin.prop('disabled', true);

        if(typeof(_keySafer) !== 'undefined' && typeof(_keySafer.disabledField) === 'function') {
            _keySafer.disabledField(_newPin.attr('id'));
            _keySafer.disabledField(_checkNewPin.attr('id'));
        }
    };

    function textboxEnable() {
        _newPin.prop('disabled', false);
        _checkNewPin.prop('disabled', false);

        if(typeof(_keySafer) !== 'undefined' && typeof(_keySafer.enabledField) === 'function') {
            _keySafer.enabledField(_newPin.attr('id'));
            _keySafer.enabledField(_checkNewPin.attr('id'));
        }
    };


    function initPin() {
        _pin.blur();
        
        _pin.val('');
        _pin.focus();
    };

    function checkboxOn() {
        $(".checkbox_unChecked").hide();
        $(".checkbox_checked").show();

        _checkbox_checked.prop("value", true);
        initPin();
    };

    function checkboxOff() {
        $(".checkbox_checked").hide();
        $(".checkbox_unChecked").show();

        _checkbox_checked.prop("value", false);
        initPin();
    };

    function closeEvent() {
        var error = {
            code: 12025
        };
        _param.errorcallback(error);
        // _parent.close();
    };

    var setUI = function () {
        // 기능별 타이틀 설정
        if(_config.signTitle !== undefined) {
            switch(_config.signTitle) {
                case 'UPDATE':
                    _config.title = changePinLang(16);
                    
                    checkboxOff();
                    textboxDisable();
                    break;

                case 'CHANGEPIN':
                    _config.title = changePinLang(0);

                    checkboxOn();
                    textboxEnable();

                    $(".checkbox_checked").hide();
                    $(".checkbox_disabled").show();
                    break;
            }
        }

        $("#titleText").append(_config.title);
    };

    var getE2EInfo = function (callback) {
        var vender;

        if(_keySafer.getNumber() == 7) {
            vender = {
                type: _keySafer.getNumber()
            };
        }

        vest.token.getE2EInfo(vender, callback, _param.errorcallback);
    };

    var keySaferScriptLoading = function (keySaferObj, i, func) {
        var script = keySaferObj.getScript();
        var scriptLen = script.length;

        if (scriptLen == 0) return;

        var callback = function () {
            if (i == scriptLen - 1) {
                if (keySaferObj.isCert()) {
                    getE2EInfo(function (result) {
                        var data = {
                            publicKey: result.publicKey,
                            keySaferPath: keySaferObj.getPath()
                        };
                        func(data);
                    });
                } else {
                    func();
                }
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
        // 체크 후... 아래부분안보여줘야함.
        try {
            _parent.getParameters();
        } catch (e){
            alert(e);
            _parent.close();
        }
        _param = params.getParameters();

        $(".certWrap").css("padding-bottom", "42px");
        setUI();

        _config.keySaferConfig.transkey.position.x =  -165;
        _config.keySaferConfig.transkey.position.y =  -200;

        if(_config.pfxKeySafer) {
            _keySafer = keySafer(_config.pfxKeystrokeEncryption, ['pin', 'newPin', 'checkNewPin'], _config.pfxKeySaferConfig);
        }else {
            _keySafer = keySafer(_config.keystrokeEncryption, ['pin', 'newPin', 'checkNewPin'], _config.keySaferConfig);
        }

        keySaferScriptLoading(_keySafer, 0, function(){
            _keySafer.init(initPin);
        });
    });

})(document, jQuery, vest, params, VestSign);

var yettie = vest.util.size;
// yettie.onKeypad = parent.vest.util.size.onKeypad;
// yettie.offKeypad = parent.vest.util.size.offKeypad;
