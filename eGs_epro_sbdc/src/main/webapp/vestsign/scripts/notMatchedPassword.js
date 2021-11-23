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

var notMatchedPassword = (function (doc, $, vest, params, vestSign) {

    var _parent = vestSign;
    var _config = vestSign.getConfig();
    var _param;

    // 확인버튼 클릭
    $("#certAgree_confirmBtn").click(function() {
        _param.callback();
    });

    // X버튼,취소버튼 클릭
    $("#certAgree_xBtn").click(function() {
        closeEvent();
    });
    $("#certAgree_cancelBtn").click(function() {
        closeEvent();
    });

    function closeEvent() {
        var error = {
            code: -9999
        };

        _param.errorcallback(error);
        _parent.close();
    }

    var setTextAreaInfo = function() {
        var textareaInfo = "";
        textareaInfo += notMatchedPasswordLang(3);
        textareaInfo += notMatchedPasswordLang(4);
        textareaInfo += notMatchedPasswordLang(5);
        textareaInfo += notMatchedPasswordLang(6);
        
        $("#textareaInfo").text(textareaInfo);
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

        setTextAreaInfo();
    });
})(document, jQuery, vest, params, VestSign);