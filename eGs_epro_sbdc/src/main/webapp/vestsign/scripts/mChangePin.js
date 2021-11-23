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
        alert(mChangePinLang(8));
        return false;
    }

    var _parent = vestSign;
    var _config = vestSign.getConfig();
    var _param;
    var _mobileKeySafer;

    var _pin = $('#pin');
    var _newPin = $('#newPin');
    var _checkNewPin = $('#checkNewPin');

    _pin.focusout(function() {
        if (_mobileKeySafer.getType() == "undefined"){
            _config.touchFlag = false;
            inputBoxOutHint(_pin, mChangePinLang(12));
        } 
    });

    _newPin.focusout(function() {
        if (_mobileKeySafer.getType() == "undefined"){
            _config.touchFlag = false;
            inputBoxOutHint(_newPin, mChangePinLang(16));
        } 
    });

    _checkNewPin.focusout(function() {
        if (_mobileKeySafer.getType() == "undefined"){
            _config.touchFlag = false;
            inputBoxOutHint(_checkNewPin, mChangePinLang(17));
        } 
    });

    _pin.focusin(function() {
        if (_mobileKeySafer.getType() == "undefined") _config.touchFlag = true;

        _pin.attr("nori", true);
        inputBoxInHint(_pin);
    });
    _newPin.focusin(function () {
        if (_mobileKeySafer.getType() == "undefined") _config.touchFlag = true;
        inputBoxInHint(_newPin);
    });
    _checkNewPin.focusin(function () {
        if (_mobileKeySafer.getType() == "undefined") _config.touchFlag = true;
        inputBoxInHint(_checkNewPin);
    });

    $('#x_btn, #cancel_btn').click(function () {
        if (_config.useMobileNPKI) {
            _param.errorcallback(new vest.error(11500, '취소하셨습니다'));
        } else {
            _param.callback({action: 'VIEW.SIGNLIST', option: _param.option});
        }
    });

    $('#confirm_btn').click(okButtonEvent);

    function inputBoxInHint(element) {
        element.attr("placeholder", "");
        element.css("font-size", "50rem");
    }

    function inputBoxOutHint(element, text) {
        if (_mobileKeySafer.getType() != "undefined") return false;
        if(element.val().length <= 0) {
            element.attr("placeholder", text);
            element.css("font-size", "24rem");
        }
    }

    var parseStr = function (str, select) {
        var strLsit = str.split(',');
        var result;
        for (var i = 0; i < strLsit.length; i++) {
            if (strLsit[i].indexOf(select + '=') !== -1) {
                result = strLsit[i].split('=');
                return result[1];
            }
        }
    };

    var isValid = function (subDay) {
        if (subDay < 0) {
            return 'EXPIRED';
        }
        else if (subDay < 30) {
            return 'WARNNING';
        }
        else {
            return 'VALID';
        }
    };

    function outCertList(args) {
        var pClass = "";

        if ((isValid(args.getAvailableDays()) === 'VALID'))
            pClass = "m_icon_true";
        else if ((isValid(args.getAvailableDays()) === 'EXPIRED'))
            pClass = "m_icon_false";
        else if ((isValid(args.getAvailableDays()) === 'WARNNING'))
            pClass = "m_icon_re";

        $(document.createElement('li'))
            .css('background-color', pClass == "m_icon_false" ? '#f1f1f1' : '#fbfbfb')
            .addClass('clfix')
            .append($(document.createElement('ul'))
                .addClass('list_list clfix')
                .append($(document.createElement('div'))
                    .append($(document.createElement('div'))
                        .addClass('list_contents_wrap0')
                        .append($(document.createElement('div'))
                    )
                )
                    .append($(document.createElement('div'))
                        .addClass('list_contents_wrap1')
                        .append($(document.createElement('p'))
                            .addClass(pClass)
                    )
                )
                    .append($(document.createElement('div'))
                        .addClass('list_contents_wrap2')
                        .append($(document.createElement('div'))
                            .addClass('list_contents_top')
                            // .append(limitStr(args.getSubject(), 7))
                            .append(parseStr(args.getSubject(), 'CN'))
                    )
                        .append($(document.createElement('hr'))
                            .addClass('list_contents_hr')
                    )
                        .append($(document.createElement('div'))
                            // .css('margin-top', '14px')
                            .addClass('div_list_contents_bottom1')
                            .append($(document.createElement('div'))
                                .addClass('list_contents_bottom1')
                                .append(mChangePinLang(10))
                        )
                            .append($(document.createElement('div'))
                                .addClass('list_contents_bottom3')
                                .append(parseStr(args.getIssuer(), 'CN'))
                        )
                    )
                        .append($(document.createElement('div'))
                            // .css('margin', '0px 0px 20px 0px')
                            .addClass('div_list_contents_bottom2')
                            .append($(document.createElement('div'))
                                .addClass('list_contents_bottom2')
                                .append(mChangePinLang(11))
                        )
                            .append($(document.createElement('div'))
                                .addClass('list_contents_bottom3')
                                .append(args.getValidityTo('YYYY-MM-DD'))
                        )
                    )
                )
                    .append($(document.createElement('div'))
                        .addClass('list_contents_wrap3')
                )
            )
        )
            .appendTo($('#dialogSignTab_list')).appendTo($('#dialogSignTab_list'));
    }

    function setPlaceHolder() {
        _pin.attr('placeholder', mChangePinLang(12));
        _newPin.attr('placeholder', mChangePinLang(16));
        _checkNewPin.attr('placeholder', mChangePinLang(17));
    }

    var pinTypeCheck = function (pin) {
        if (_pin.attr('nori') == 'undefined' || _pin.attr('nori') != 'true') {
            alert(mChangePinLang(12));
            return false;
        }

        if (typeof(pin) === 'string' && pin == '') {
            alert(mChangePinLang(12));
            return false;
        }
        return true;
    };

    var getPassword = function (callback) {
        if (!_mobileKeySafer.initalizeCheck() || _mobileKeySafer.getType() == "undefined") {
            callback(_pin.val(), _newPin.val(), _checkNewPin.val());
        } else {
            _mobileKeySafer.getPassword(function (pin, newPin, checkNewPin) {
                callback(pin, newPin, checkNewPin);
            });
        }
    };

    function okButtonEvent() {
        var nextEvent = function (pin, newPin, checkNewPin) {
            if (!pinTypeCheck(pin)) {
                return;
            }

            vest.util.checkComplexPassword(pin, newPin, checkNewPin, function(result){
                // error checkout
                if(result.code != 0) {
                    vest.util.refactoryMsg.convertMsg(result);
                    alert(result.msg);
                }else {
                    _param.callback({
                        action: 'CHANGEPASSWORD',
                        oldPassword: pin,
                        newPassword: newPin,
                        option: _param.option,
                        msg: '성공'
                    });
                }
            });
        };

        getPassword(nextEvent);
    }

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
            alert(mChangePinLang(9));
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

        outCertList(vest.pki.Certificate.fromHex(_param.option.viewOption.cert));
        setPlaceHolder();

        _mobileKeySafer = mobileKeySafer(_config.mobileKeystrokeEncryption, ['pin', 'newPin', 'checkNewPin'], _config.mobileKeySaferConfig);
        keySaferScriptLoading(_mobileKeySafer, 0, _mobileKeySafer.init);
    });
})(document, jQuery, vest, params, VestSign);

var yettie = vest.util.size;