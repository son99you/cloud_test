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
        alert( inputPinLang(4) );
        return false;
    }

    var _parent = vestSign;
    var _config = vestSign.getConfig();
    var _param;
    var _option;
    var _keySafer;

    vest.init(_config);

    var btnEvent = function() {
        var pin = $("#pin").val();
        
        // textbox 값이 비어있을 경우
        if ( pin == "" ) {
            throw inputPinLang(5);
        }

        _keySafer.getPassword(function(encPin) {
            _param.callback(encPin);
        });
        // _param.callback(pin);
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

    $("#pin").keydown(function(key){
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

    function closeEvent() {
        var error = {
            code: 12025
        };
        _param.errorcallback(error);
        // _parent.close();
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

    function setMsg() {
        if ( typeof _option.selectedToken !== 'undefined' ) {
            var infoMsg, pwMsg;

            switch (_option.selectedToken) {
                case vest.token.TYPE.TOKEN:
                    infoMsg = inputPinLang(6);
                    pwMsg = inputPinLang(10);
                    break;
                case vest.token.TYPE.SAVETOKEN:
                    infoMsg = inputPinLang(7);
                    pwMsg = inputPinLang(11);
                    break;
                case vest.token.TYPE.SECUREDISK:
                    infoMsg = inputPinLang(8);
                    pwMsg = inputPinLang(12);
                    break;
                default:
                    infoMsg = inputPinLang(1);
                    pwMsg = inputPinLang(9);
            }
            $("#infoMsg").text('');
            $("#infoMsg").append(infoMsg);
            $("#pwMsg").text('');
            $("#pwMsg").append(pwMsg);
        }
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
        _option = _param.option;

        _config.keySaferConfig.transkey.position.x =  -165;
        _config.keySaferConfig.transkey.position.y =  -105;
        _keySafer = keySafer(_config.keystrokeEncryption, ['pin'], _config.keySaferConfig);
        keySaferScriptLoading(_keySafer, 0, _keySafer.init);

        $(".marginTextBox").css("font-weight", "bold");
        $(".marginTextBox").css("padding-left", "22px");
        $(".CERT_btns_common").css("margin-top", "11px");

        setMsg();

        $("#dialogCertTab_okBtn").focus();

        // $("#pin").blur();
        // $("#pin").focus();
        if(_keySafer.getType() != 'npk') $("#pin").focus();
        
    });

})(document, jQuery, vest, params, VestSign);

var yettie = vest.util.size;
// yettie.onKeypad = parent.vest.util.size.onKeypad;
// yettie.offKeypad = parent.vest.util.size.offKeypad;
