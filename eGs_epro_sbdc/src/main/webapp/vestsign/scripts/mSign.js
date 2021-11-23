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
        alert(mSignLang(3));
        return false;
    }

    var _parent = vestSign;
    var _config = vestSign.getConfig();
    var _param;
    var _certificateList = [];
    var _selected;
    var _mobileKeySafer;

    var _pin = $('#pin');
    var _passwordBox = $('#passwordBox');
    var _commonBox = $('#commonBtn');
    var _confirmBox = $('#confirm_btn');
    var _cancelBox = $('#cancel_btn');

    _pin.focusout(function () {
        if (_mobileKeySafer.getType() == "undefined") {
            _config.touchFlag = false;
            inputBoxOutHint();
        } else {

        }
    });

    _pin.focusin(function () {
        if (_mobileKeySafer.getType() == "undefined") _config.touchFlag = true;

        _pin.attr("nori", true);
        inputBoxInHint();
    });

    $('#x_btn, #cancel_btn').click(function () {
        if (_config.useMobileNPKI) {
            _param.errorcallback(new vest.error(11500, '취소하셨습니다'));
        } else {
            _parent.close();
        }
    });

    $('#confirm_btn').click(function () {
        var pin = _pin.val();

        if (typeof _param.option.usepwd === 'undefined' || typeof _param.option.usepwd === true) {
            if (typeof pin === 'undefined' || pin == '') {
                alert(mSignLang(5));
                return;
            }
        }

        if (typeof _param.option.mobileViewManagement !== 'undefined' && _param.option.mobileViewManagement == true && _param.option.usepwd === false) {
            mobileViewManagementEvent();
        }
        else {
            okButtonEvent();
        }
    });

    var parseStr = function (str, select) {
        var strList = str.split(',');
        var result;
        for (var i = 0; i < strList.length; i++) {
            if (strList[i].indexOf(select + '=') !== -1) {
                result = strList[i].split('=');
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

    function inputBoxInHint() {
        _pin.attr("placeholder", "");
        _pin.css("font-size", "80rem");
    }

    function inputBoxOutHint() {
        if (_pin.val().length <= 0) {
            checkExpired($(".list_list.clfix.on").find('p').attr('class'));
            _pin.css("font-size", "28rem");
        }
    }

    function createImportView() {
        $(document.createElement('li'))
            .addClass('clfix')
            .append($(document.createElement('ul'))
                .addClass('CERT_sign_list clfix')
                .append($(document.createElement('li'))
                    .css('float', 'none')
                    .css('width', '100%')
                    .append($(document.createElement('div'))
                        .addClass('importView')
                        .append($(document.createElement('div'))
                            .addClass('importView_img')
                        )
                        .append($(document.createElement('div'))
                            .addClass('importView_info')
                            .text("인증서 추가하기")
                        )
                        .on("click", function () {
                            $("#dialogSignTab_list").children().last().remove();
    
                            createImportSelectView();
                        })
                    )
                )
            )
            .appendTo($('#dialogSignTab_list'));
    }

    function createImportSelectView() {
        $(document.createElement('li'))
            .addClass('clfix')
            .append($(document.createElement('ul'))
                .addClass('CERT_sign_list clfix')
                .append($(document.createElement('li'))
                    .append($(document.createElement('div'))
                        .addClass('importFromPC')
                        .attr("alt", 'pc에서 가져오기')
                        .text("pc에서 가져오기")
                        .on("click", function () {
                            $(this).addClass("importFromPC_on");
                            importFromPC();
                        })
                    )
                )
                .append($(document.createElement('li'))
                    .addClass('importFromPhoneDiv')
                    .append($(document.createElement('div'))
                        .addClass('importFromPhone')
                        .attr("alt", '휴대폰에서 가져오기')
                        .text("휴대폰에서 가져오기")
                        .on("click", function () {
                            $(this).addClass("importFromPhone_on");
                            importFromPhone();
                        })
                    )
                )
            )
            .appendTo($('#dialogSignTab_list'));

        // 휴대폰 가져오기 이미지 옵션
        if (_config.useMobileNPKI == true) {
            $(".importFromPhoneDiv").css("display", "none");
        } else {
            if (!window.navigator.userAgent.match(/Android/) && _config.hiddenImageImportFromPhone) {
                $(".importFromPhoneDiv").css("display", "none");
            }
        }
    }

    function checkExpired(pClass) {
        _pin.val('');
        _pin.css("font-size", "28rem");
        if (pClass == "m_icon_false") {  // 만료된 인증서
            _pin.attr('placeholder', mSignLang(9));
        } else { // 사용가능 인증서 (사용가능/만료예정)
            _pin.attr('placeholder', mSignLang(10));
        }
    }

    function certListClickEvent(listBox, index) {
        // 디자인 부분
        var pClass = listBox.find('p').attr('class');

        // 선택인증서가 클릭했던 인증서라면 return
        //if (listBox.hasClass("on")) return;
        // 선택인증서 배경 전환 class 추가
        $("#dialogSignTab_list li ul").removeClass("on");
        listBox.addClass("on");
        // 선택인증서 선택확인 class 추가
        $("#dialogSignTab_list .list_contents_wrap0 div").removeClass();
        listBox.find('div.list_contents_wrap0 div').addClass(pClass + '_choice');

        checkExpired(pClass);   // 만료 인증서 체크 로직

        _selected = index;
    };

    function managementClickEvent(box, index) {
        // 디자인 부분
        box.addClass("list_contents_manage_on");

        // 로직
        _selected = index;
        managementButtonEvent();
    }

    function outCertList(args) {
        var certList = [];

        // args 저장된 인증서가 있을때
        if (args.length > 0) {
            certList = checkCertType(args);
            $.each(certList, function (index, item) {
                var pClass = "";
                if ((isValid(item.getAvailableDays()) === 'VALID'))
                    pClass = "m_icon_true";
                else if ((isValid(item.getAvailableDays()) === 'EXPIRED'))
                    pClass = "m_icon_false";
                else if ((isValid(item.getAvailableDays()) === 'WARNNING'))
                    pClass = "m_icon_re";

                if (pClass == "m_icon_false" && _config.mobileExpiredCertFilter) return true;  // 만료된 인증서 표시여부(설정 값에 따라서)

                $(document.createElement('li'))
                    .css('background-color', pClass == "m_icon_false" ? '#f1f1f1' : '#fbfbfb')
                    .addClass('clfix')
                    .append($(document.createElement('ul'))
                        .addClass('list_list clfix')
                        .on("click", function () {
                            certListClickEvent($(this), index);
                        })
                        .append($(document.createElement('div'))
                            .append($(document.createElement('div'))
                                .addClass('list_contents_wrap0')
                                .append($(document.createElement('div'))
                                    // .addClass(pClass+'_choice')
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
                                    .append(parseStr(item.getSubject(), 'CN'))
                                )
                                //                                .append($(document.createElement('hr'))
                                //                                    .addClass('list_contents_hr')
                                //                                )
                                .append($(document.createElement('div'))
                                    // .css('margin-top', '14px')
                                    .addClass('div_list_contents_bottom')
                                    .append($(document.createElement('div'))
                                        .addClass('list_contents_bottom2')
                                        .append(mSignLang(6))
                                    )
                                    .append($(document.createElement('div'))
                                        .addClass('list_contents_bottom3')
                                        .append(parseStr(item.getIssuer(), 'CN'))
                                    )
                                )
                                .append($(document.createElement('div'))
                                    .addClass('div_list_contents_bottom')
                                    .append($(document.createElement('div'))
                                        .addClass('list_contents_bottom2')
                                        .append(mSignLang(11))
                                    )
                                    .append($(document.createElement('div'))
                                        .addClass('list_contents_bottom3')
                                        .append(vest.util.policies[item.getPolicy()] == undefined ? '테스트' : vest.util.policies[item.getPolicy()].usage)
                                    )
                                )
                                .append($(document.createElement('div'))
                                    // .css('margin', '0px 0px 20px 0px')
                                    .addClass('div_list_contents_bottom2')
                                    .append($(document.createElement('div'))
                                        .addClass('list_contents_bottom2')
                                        .append(mSignLang(7))
                                    )
                                    .append($(document.createElement('div'))
                                        .addClass('list_contents_bottom3')
                                        .append(item.getValidityTo('YYYY.MM.DD'))
                                    )
                                )
                            )
                            .append($(document.createElement('div'))
                                .addClass('list_contents_wrap3')
                                .on("click", function () {
                                    //상세보기
                                    managementClickEvent($(this).find('div'), index);
                                    return false;   // 이벤트 상속 맞기위해 추가
                                })

                                .append($(document.createElement('div'))
                                    .addClass('list_contents_manage')
                                    .append('관리')
                                )
                            )
                        )
                    )
                    .appendTo($('#dialogSignTab_list'));
            });
        }

        if ($('ul.list_list.clfix').length > 0) {
            _commonBox.show();
            _passwordBox.show();
            //$('ul.list_list.clfix')[0].click();
            $('ul.list_list.clfix').first().click();
        }

        if (_param.option.usepwd == false) {
            _passwordBox.hide();
        }

        // if (_config.useMobileNPKI) {
        //     createImportSelectView();

        //     // if(_param.option.usepwd == false) {
        //     //     _confirmBox.hide();
        //     // }
        // } else if (typeof _param.option.mobileViewManagement !== 'undefined' && _param.option.mobileViewManagement == true) {
        //     // 인증서 관리일때 처리들.. UI
        //     var manageIconArray = document.getElementsByClassName('list_contents_manage');

        //     while (typeof manageIconArray[0] !== 'undefined') {
        //         manageIconArray[0].remove();
        //     }
        // } else {
        //     createImportView();
        // }

        // checkTitle(_param.option.title);

        if (typeof _param.option.mobileViewManagement !== 'undefined' && _param.option.mobileViewManagement == true) {
            // 인증서 관리일때 처리들.. UI
            var manageIconArray = document.getElementsByClassName('list_contents_manage');

            while (typeof manageIconArray[0] !== 'undefined') {
                manageIconArray[0].remove();
            }
        } else {
            if (_config.useMobileNPKI) {
                createImportSelectView();

                // if(_param.option.usepwd == false) {
                //     _confirmBox.hide();
                // }
            } else {
                createImportView();
            }
        }
    }

    function getCertificateList() {
        if (_config.useMobileNPKI) {
            if (vest.iosKoreamintLib.isIOS()) {
                vest.iosKoreamintLib.getCertificateList(undefined, function (certList) {
                    var viewCertList = [];

                    for (var i = 0; i < certList.length; i++) {
                        viewCertList.push(vest.pki.Certificate.fromBytes(vest.signHelper.decodeString(certList[i], 'base64')));
                    }
                    outCertList(viewCertList);
                }, function (error) {
                    // 스크립트도 에러처리가 안되어있네.
                })
            } else {
                vest.androidKoreamintLib.getCertificateList(function (certList) {
                    var viewCertList = [];
                    for (var i = 0; i < certList.length; i++) {
                        console.log('certList[i]::' + certList[i]);
                        viewCertList.push(vest.pki.Certificate.fromBytes(vest.signHelper.decodeString(certList[i], 'base64')));
                    }
                    outCertList(viewCertList);
                }, function (repCode) {
                    alert(vest.util.refactoryMsg.koreaMintLibErrorHandler(repCode));
                });
            }
        } else {
            vest.secureCertificateStorage.getCertificateList(function (certList) {
                var viewCertList = [];
                for (var i = 0; i < certList.length; i++) {
                    viewCertList.push(new vest.pki.Certificate(certList[i].signCertificate));
                }
                _certificateList = certList;
                outCertList(viewCertList);
            });
        }
    };

    var pinTypeCheck = function (pin) {
        if (_pin.attr('nori') == 'undefined' || _pin.attr('nori') != 'true') {
            alert(mSignLang(5));
            return false;
        }

        if (typeof (pin) === 'string' && pin == '') {
            alert(mSignLang(5));
            return false;
        }
        return true;
    };



    function okButtonEvent() {
        var getPassword = function (callback) {
            if (!_mobileKeySafer.initalizeCheck() || _mobileKeySafer.getType() == "undefined") {
                callback(_pin.val());
            } else {
                _mobileKeySafer.getPassword(function (pin) {
                    callback(pin);
                }, 'pin');
            }
        };

        var decryptPassword = function (pin, callback) {
            if (typeof pin.type !== "undefined" && typeof pin.type === "number") {
                var decKeySafer = vest.decryptKeySafer(pin.type, pin.config);
                decKeySafer.getDecryptedPassword(pin.value, callback);
            } else {
                callback(pin);
            }
        }
        var iosCall = function (pin, callback) {
            decryptPassword(pin, function (pwd) {
                vest.iosKoreamintLib.getCertificate(_certificateList[_selected].getSubject(), undefined, function (cert) {
                    vest.iosKoreamintLib.getPrivateKey(_certificateList[_selected].getSubject(), pwd, undefined, function (key) {
                        callback(vest.signHelper.decodeString(cert, 'base64'), vest.signHelper.decodeString(key, 'base64'));
                    }, vest.iosKoreamintLib.errorHandler);
                }, vest.iosKoreamintLib.errorHandler);
            })
        };

        var androidCall = function (pin, callback) {
            decryptPassword(pin, function (pwd) {
                vest.androidKoreamintLib.getCertAndKey(parseStr(_certificateList[_selected].getSubject(), 'CN'), pwd, function (res) {
                    callback(vest.signHelper.decodeString(res.cert, 'base64'), vest.signHelper.decodeString(res.key, 'base64'));
                }, function (repCode) {
                    alert(vest.util.refactoryMsg.koreaMintLibErrorHandler(repCode));
                });
            });
        }

        var secureCertificateStorageCall = function (pin, callback) {
            var secureCertificateStorageItem = _certificateList[_selected];

            secureCertificateStorageItem.getEncryptedPrivateKey(secureCertificateStorageItem.signPrivateKey, {}, function (encryptedPrivateKeyInfo) {
                try {
                callback(secureCertificateStorageItem.signCertificate.toAsn1().toBytes(), encryptedPrivateKeyInfo.toAsn1().toBytes(), secureCertificateStorageItem);
                } catch(e) {
                    var error = {code: 12042};
                    vest.util.refactoryMsg.convertMsg(error);
                    alert(error.msg);
                    var res = confirm(mSignLang(15));
                    if (res) {
                        vest.secureCertificateStorage.removeCertificateItem(secureCertificateStorageItem);
                        _param.callback({
                            SecureCertificateStorageItem: _certificateList[_selected],
                            option: _param.option
                        })
                    } else {
                        _param.callback({
                            SecureCertificateStorageItem: _certificateList[_selected],
                            option: _param.option
                        })
                    }
                }  
            });
        }

        var nextEvent = function (cert, key, secureCertificateStorageItem) {
            var decodeCert = {};

            decodeCert.signCert = cert;
            decodeCert.signPri = key;

            getPassword(function (pin) {
                var resObj = {
                    action: 'SIGN',
                    password: pin,
                    certificate: vest.pki.CertificateSet.fromBytes(decodeCert),
                    option: _param.option
                }

                if (typeof secureCertificateStorageItem !== 'undefined') {
                    resObj.SecureCertificateStorageItem = secureCertificateStorageItem;
                }

                _param.callback(resObj);
            });
        };

        // getPassword(nextEvent);

        if (typeof (_selected) === 'undefined' || (typeof (_selected) !== 'number' && _selected == '')) {
            alert(mSignLang(8));
            _pin.val('');
            return;
        }

        if (!pinTypeCheck(pin)) {
            return;
        }

        getPassword(function (pwd) {
            if (_config.useMobileNPKI && vest.iosKoreamintLib.isIOS()) {
                iosCall(pwd, nextEvent);
            } else if (_config.useMobileNPKI) {
                androidCall(pwd, nextEvent);
            } else {
                secureCertificateStorageCall(pwd, nextEvent);
            }
        });
    };

    function managementButtonEvent() {

        if (_config.useMobileNPKI) {
            _param.option.cn = parseStr(_certificateList[_selected].getSubject(), 'CN');
            _param.callback({
                action: 'VIEW.MANAGEMENT',
                SecureCertificateStorageItem: _certificateList[_selected],
                certificate: _certificateList[_selected].toHex(),
                option: _param.option
            })
        } else {
            _param.callback({
                action: 'VIEW.MANAGEMENT',
                SecureCertificateStorageItem: _certificateList[_selected],
                option: _param.option
            })
        }
    }

    function mobileViewManagementEvent() {
        if (_config.useMobileNPKI) {
            _param.option.cn = parseStr(_certificateList[_selected].getSubject(), 'CN');
            _param.callback({
                SecureCertificateStorageItem: _certificateList[_selected],
                certificate: _certificateList[_selected].toHex(),
                option: _param.option
            })
        } else {
            if (_param.option.title == "mDeleteCertificate") {
                var res = confirm(mSignLang(15));
                if (res) {
                    _param.callback({
                        SecureCertificateStorageItem: _certificateList[_selected],
                        option: _param.option
                    })
                }
            } else {
                _param.callback({
                    SecureCertificateStorageItem: _certificateList[_selected],
                    option: _param.option
                })
            }
        }
    }

    function importFromPC() {
        _param.callback({
            action: 'VIEW.IMPORTFROMPC',
            option: _param.option
        })
    }

    function importFromPhone() {
        _param.callback({
            action: 'VIEW.IMPORTFROMPHONE',
            option: _param.option
        })
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
    /**
     * 인증서 타입 체크 함수
     *
     * vestsign.js에서 설정한 useGPKI값에 따라 인증서를 필터링해서 보여줌
     */
    function checkCertType(certList) {
        var viewCertList = [];
        var result = [];
        var secureCertList = _certificateList;

        for (var i = 0; i < certList.length; i++) {
            if (_config.useMobileNPKI) {
                if (vest.util.policies[certList[i].getPolicy()] == undefined) {
                    if (_config.useGPKI == 1 && !certList[i].getIssuer().includes("OU=GPKI")) {
                        viewCertList.push(certList[i]);
                    }
                    else if (_config.useGPKI == 16 && certList[i].getIssuer().includes("OU=GPKI")) {
                        viewCertList.push(certList[i]);
                    } else if (_config.useGPKI == 17) {
                        viewCertList.push(certList[i]);
                    }
                } else {
                    if (_config.useGPKI == 1 && vest.util.policies[certList[i].getPolicy()].type == 'NPKI') {
                        viewCertList.push(certList[i]);
                    } else if (_config.useGPKI == 16 && vest.util.policies[certList[i].getPolicy()].type == 'GPKI') {
                        viewCertList.push(certList[i]);
                    } else if (_config.useGPKI == 17) {
                        viewCertList.push(certList[i]);
                    }
                }
                _certificateList = viewCertList;
            } else {
                if (vest.util.policies[certList[i].getPolicy()] == undefined) {
                    if (_config.useGPKI == 1 && !certList[i].getIssuer().includes("OU=GPKI")) {
                        viewCertList.push(certList[i]);
                        result.push(secureCertList[i]);
                    }
                    else if (_config.useGPKI == 16 && certList[i].getIssuer().includes("OU=GPKI")) {
                        viewCertList.push(certList[i]);
                        result.push(secureCertList[i]);
                    } else if (_config.useGPKI == 17) {
                        viewCertList.push(certList[i]);
                        result.push(secureCertList[i]);
                    }
                } else {
                    if (_config.useGPKI == 1 && vest.util.policies[certList[i].getPolicy()].type == 'NPKI') {
                        viewCertList.push(certList[i]);
                        result.push(secureCertList[i]);
                    } else if (_config.useGPKI == 16 && vest.util.policies[certList[i].getPolicy()].type == 'GPKI') {
                        viewCertList.push(certList[i]);
                        result.push(secureCertList[i]);
                    } else if (_config.useGPKI == 17) {
                        viewCertList.push(certList[i]);
                        result.push(secureCertList[i]);
                    }
                }
                _certificateList = result;
            }
        }
        return viewCertList;
    }

    function checkTitle(title) {
        switch (title) {
            case 'mSign':
                $('#title').text(mSignLang(0));
                break;
            case 'mDeleteCertificate':
                $('#title').text(mSignLang(12));
                break;
            case 'mChangePassword':
                $('#title').text(mSignLang(13));
                break;
            case 'mExportCertificate':
                $('#title').text(mSignLang(14));
                break;
            default:
                $('#title').text(mSignLang(0));
                break;
        }
    }

    $(document).ready(function () {
        // param 세팅
        try {
            _parent.getParameters();
        } catch (e) {
            alert(mSignLang(4));
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

        parent.history.pushState(null, parent.document.title, parent.location.href);
        parent.window.addEventListener('popstate', function (event) {
            $("#x_btn").click();
        });


        if (_config.mobileBanner.enable) {
            if (typeof _config.mobileBanner.url !== 'undefined' && _config.mobileBanner.url != '') {
                document.getElementsByClassName('BANNER')[0].style.backgroundImage = "url(" + _config.mobileBanner.url + ")";
            }
            document.getElementsByClassName('CERT_list')[0].classList.add('BANNER_div');
            document.getElementsByClassName('PW_div')[0].classList.add('BANNER_margin');
            document.getElementsByClassName('BANNER')[0].classList.add('on');
        }

        checkTitle(_param.option.title);
        getCertificateList();

        if (_config.mobileKeystrokeEncryption != '') {
            // 키패드 로딩전 pin입력시 일반 키패드 막기 위해 추가
            document.getElementById('pin').setAttribute('readonly', 'true');
            document.getElementById('pin').blur();
            document.getElementById('pin').setAttribute('disabled', 'true');
        }
        
        _mobileKeySafer = mobileKeySafer(_config.mobileKeystrokeEncryption, ['pin'], _config.mobileKeySaferConfig);
        keySaferScriptLoading(_mobileKeySafer, 0, _mobileKeySafer.init);

    });

})(document, jQuery, vest, params, VestSign);

var yettie = vest.util.size;