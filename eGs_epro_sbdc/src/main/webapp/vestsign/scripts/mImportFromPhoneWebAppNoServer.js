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

var mobile_sign_webApp_noServer = (function (doc, $, vest, params, vestSign) {
    'use strict';

    if (vestSign === undefined) {
        alert(mFromPhoneLang(0));
        return false;
    }

    var _parent = vestSign;
    var _config = vestSign.getConfig();
    var _param;

    function setWebAppNoServer() {
        var _targetURL = vest.signHelper.encodeBytes(parent.window.location.origin + parent.window.location.pathname, 'hex');
        if (window.navigator.userAgent.match(/Android/)) {
            //console.log("android yettie.setSloth 호출(중계서버 X)");

            yettie.setSloth(_targetURL);
        } else {
            alert(mFromPhoneLang(13));
        }
    }

    $('#x_btn').click(function () {
        _param.callback({action: 'VIEW.SIGNLIST', option: _param.option});
    });

    $('#pre_btn').click(function () {
        _param.callback({action: 'VIEW.SIGNLIST', option: _param.option});
    });

    $('#importToWeb_btn').click(function () {
        okButtonEvent();
    });

    function okButtonEvent() {
        _param.callback({action: 'VIEW.SIGNLIST', option: _param.option});
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

        setWebAppNoServer();
    });
})(document, jQuery, vest, params, VestSign);