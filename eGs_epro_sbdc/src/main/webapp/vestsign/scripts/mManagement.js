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
        alert(mManagementLang(0));
        return false;
    }

    var _parent = vestSign;
    var _param;

    var _titleDn = $('#titleDn');
    var _validToText = $('#validTo');
    var _config = vestSign.getConfig();

    $('#x_btn').click(function() {
        if (_config.useMobileNPKI) {
            _param.errorcallback(new vest.error(11500, '취소하셨습니다'));
        } else {
            _param.callback({action: 'VIEW.SIGNLIST', option: _param.option});
        }
    });

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
            //return 'EXPIRED';
            return mManagementLang(2);
        }
        else if (subDay < 30) {
            //return 'WARNNING';
            return mManagementLang(3);
        }
        else {
            //return 'VALID';
            return mManagementLang(4);
        }
    };

    var setDetailCert = function (certificate) {
        _titleDn.text(parseStr(certificate.getSubject(), 'CN'));
        _validToText.text(isValid(certificate.getAvailableDays()));
        detailList( [
            {arg1: mManagementLang(5), arg2: vest.util.policies[certificate.getPolicy()] == undefined?'테스트':vest.util.policies[certificate.getPolicy()].usage},
            //{arg1: mManagementLang(6), arg2: vest.util.policies[certificate.getPolicy()] == undefined?'테스트CA':vest.util.policies[certificate.getPolicy()].caName},
            {arg1: mManagementLang(6), arg2: vest.util.policies[certificate.getPolicy()] == undefined?'테스트CA':parseStr(certificate.getIssuer(), 'CN')},

            {arg1: mManagementLang(7), arg2: certificate.getValidityFrom()},
            {arg1: mManagementLang(8), arg2: certificate.getValidityTo()},
            {arg1: mManagementLang(9), arg2: certificate.getSubject()},
            {arg1: mManagementLang(10), arg2: certificate.getVersion()},
            {arg1: mManagementLang(11), arg2: certificate.getSerialNumber()},
            {arg1: mManagementLang(12), arg2: certificate.getSignatureAlgorithm()},
            {arg1: mManagementLang(13), arg2: certificate.getPublickeyAlgorithm()},
            {arg1: mManagementLang(14), arg2: certificate.getPublickey()},
            {arg1: mManagementLang(15), arg2: certificate.getSignatureValue()},
            {arg1: mManagementLang(16), arg2: certificate.getKeyUsage()},
            {arg1: mManagementLang(17), arg2: certificate.getIssuerKeyIdentifier()},
            {arg1: mManagementLang(18), arg2: certificate.getSubjectKeyIdentifier()},
            {arg1: mManagementLang(19), arg2: certificate.getExtKeyUsage()},
            {arg1: mManagementLang(20), arg2: certificate.getRealName()},
            {arg1: mManagementLang(21), arg2: certificate.getBasicConstranints()},
            {arg1: mManagementLang(22), arg2: certificate.getPolicy()},
            {arg1: mManagementLang(23), arg2: certificate.getCrlDistributionPoint()},
            {arg1: mManagementLang(24), arg2: certificate.getAuthorityInfoAccess()}
        ]);
    };

    $('#changePin_btn').click(function(){
        _param.callback({
            action: 'VIEW.CHANGEPIN',
            option: _param.option
        });
    });

    $('#deleteCert_btn').click(function(){
        var res = confirm(mManagementLang(28));
        if(res) {
            _param.callback({
                action: 'REMOVECERTIFICATE',
                option: _param.option,
                msg: '성공'
            });
        }
    });

    function detailList(args) {
        //$("#dialogCertTab_attrList").empty();
        if (args == [] || args == undefined || args == "") return;

        //다이얼로그 출력
        $.each(args, function (index, item) {
            $(document.createElement('li'))
                .addClass('clfix')
                .append($(document.createElement('ul'))
                    .addClass('CERT_list_list clfix')
                    .append($(document.createElement('li'))
                        .append($(document.createElement('p'))
                            .attr("title", item.arg1)
                            .append(item.arg1)
                        )
                    )
                    .append($(document.createElement('li'))
                        .append($(document.createElement('p'))
                            .append(item.arg2)
                        )
                    )
                )
                .appendTo($('#dialogCertTab_attrList'));
        });
    }

    $(document).ready(function () {
        // param 세팅
        try {
            _parent.getParameters();
        } catch (e) {
            alert(mManagementLang(1));
            _parent.close();
        }
        _param = params.getParameters();

        vest.util.modifyMobileFrame(document);
        parent.window.addEventListener("resize", function (e) {
            vest.util.modifyMobileFrame(document);
            e.preventDefault();
        });

        setDetailCert(vest.pki.Certificate.fromHex(_param.option.viewOption.cert));
    });
})(document, jQuery, vest, params, VestSign);