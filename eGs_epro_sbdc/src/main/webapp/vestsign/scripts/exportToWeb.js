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

var relayExport = (function (doc, $, vest, params, vestSign) {

    if (vestSign === undefined) {
        //alert('구동에 실패하였습니다.\n\n IE인터넷 옵션 -> 보안 \n 인터넷 보호모드 check, 신뢰사이트 보호모드 check \n');
        alert(exportToWebLang(2));
        //window.close();
        return false;
    }

    var _parent = vestSign;
    var _config = vestSign.getConfig();
    var _param;
    var _keySafer;

    function okButtonEvent () {
        var option = _param.option;
        option.code = [];
        option.code.push($("#code1").val());
        option.code.push($("#code2").val());
        option.code.push($("#code3").val());


        _param.callback(_config.slothPath, option);
        //_parent.close();
    }

    $("#certAgree_confirmBtn").click(okButtonEvent).keypress(okButtonEvent);

    $("#certAgree_cancelBtn").click(function(){
        //_param.callback(_param.option, 0);
        //_parent.close();
        closeEvent();
    });

    $("#CERT_util_btnClose").click(function(){
        //_param.callback(_param.option, 0);
        //_parent.close();
        closeEvent();
    });

    $("#x_btn").click(function(){
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
        } catch (e){
            alert(e);
            _parent.close();
        }

        _param = params.getParameters();

        if (_config.slothPath == '')
            _config.slothPath = 'https://' + location.host;

        $("#code1").focus();
        $("#titleText").append(exportToWebLang(3) + ', ' + _config.slothPath);
    });

})(document, jQuery, vest, params, VestSign);
