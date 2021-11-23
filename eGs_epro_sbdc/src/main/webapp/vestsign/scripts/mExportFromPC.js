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

    var _slothReceiver;
    var _mobileKeySafer;


    _pin.focusout(function() {
        if (_mobileKeySafer.getType() == "undefined") _config.touchFlag = false;
        inputBoxOutHint();
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
        var code = [];
        code.push($("#num1").val());
        code.push($("#num2").val());
        code.push($("#num3").val());
        _param.callback({action: 'VIEW.SIGNLIST', option: _param.option, code: code});
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

        // 디자인이 밀려서 추가해둠...
        $('#passwordBox').show();
        $('#commonBtn').show();


    });
})(document, jQuery, vest, params, VestSign);

var yettie = vest.util.size;