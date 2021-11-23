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

var relayImport = (function (doc, $, vest, params, vestSign) {

    if (vestSign === undefined) {
        //alert('구동에 실패하였습니다.\n\n IE인터넷 옵션 -> 보안 \n 인터넷 보호모드 check, 신뢰사이트 보호모드 check \n');
        alert(relayImportLang(2));
        //window.close();
        return false;
    }

    var _parent = vestSign;
    var _config = vestSign.getConfig();
    var _param;
    var _targetURL;
    var _authCode;
    var _slothReceiver;
    var _pfx;

    function setSloth() {
        if (_config.slothPath == '')
            _config.slothPath = 'https://' + location.host;

        _slothReceiver = new vest.sloth(_config.slothPath + '/sloth');
        _slothReceiver.create(function (result) {
            if (typeof (result.getReason) === 'function') {
                //error
                //console.log(result.getReason());
            } else {
                $("#code1").val(result[0]);
                $("#code2").val(result[1]);
                $("#code3").val(result[2]);
            }
        });
    }

    $("#certAgree_confirmBtn").click(function () {
        _slothReceiver.read(function (result) {
            if (typeof (result.getReason) === 'function' || !result.hasOwnProperty('p12')) {
                alert(typeof (result.getReason) === 'function' ? result.getReason() : '인증서 가져오기를 실패하였습니다(PC에서 인증서 내보내기를 먼저 진행해주세요).');
            } else {
                _pfx = result.p12;
                _slothReceiver.dropSession(function () {
                    _param.callback(_pfx, _param.option);
                });
            }
        });
        //_parent.close();
    });

    $("#certAgree_cancelBtn").click(function () {
        //_param.callback(_param.option, 0);
        //_parent.close();
        closeEvent();
    });

    $("#CERT_util_btnClose").click(function () {
        //_param.callback(_param.option, 0);
        //_parent.close();
        closeEvent();
    });

    $("#x_btn").click(function () {
        //_param.callback(_param.option, 0);
        //_parent.close();
        closeEvent();
    });

    function closeEvent() {
        var error = {
            code: -9999
        };
        _param.errorcallback(error);
        _parent.close();
    }

    $(document).ready(function () {
        // 체크 후... 아래부분안보여줘야함.
        try {
            _parent.getParameters();
        } catch (e) {
            alert(e);
            _parent.close();
        }

        _param = params.getParameters();

        setSloth();
    });

})(document, jQuery, vest, params, VestSign);
