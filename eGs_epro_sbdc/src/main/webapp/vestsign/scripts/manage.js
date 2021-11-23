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

var cert_select_controller = (function (doc, $, vest, params, vestSign) {
    'use strict';

    if (vestSign === undefined) {
        alert(jsLang(7));
        //window.close();
        return false;
    }

    var _pfx;
    var _lastStorage = '';
    var _selected;
    var _certificates = [];
    var _pfxCertificate;
    var _OID = vest.util.policies;
    var _config = vestSign.getConfig();
    var _parent = vestSign;
    var _infoCertFile = _config.baseUrl + "./library/information/certificateFile.html";
    var _infoInstall = _config.baseUrl + "./library/information/vestCertInstall.html";
    var _isLoad;
    var _tokenNumber;
    var _firstTrigger = "H";
    var _keySafer;
    var _pfxKeySafer;
    var _hibiKeySafer;

    var _param;
    var _menuTabIndex = 3;
    var _subMenuTabIndex;
    var _disPersonFlg = true;
    var _firstLoading = true;
    var _lastMenuIndex;
    var _serial;
    var _storageType;
    var _driveName;
    var _caType = {};
    var _selectedStorageNum;
    var _signErrCnt = 0;
    var _hibiscusSignErrCnt = 0;
    var _preventKeyFlag = true;
    var _isGray;
    var _dn;
    var _opencertStatus = 'init';
    var _interval;
    var _opencertLoading;
    var loading;
    var _load = false;

    // var pinForm = $("#passwordForm");
    var certPin = $("#passwordInput");
    var passwordText = $("#passwordText");
    var pfxPin = $("#hiddenPasswordInput");
    var pinHint = $("#pinHint");
    var statusView = $("#outCertComment");
    var submitBtn = $("#submit_btn");
    var cancelBtn = $("#cancel_btn");
    var deleteBtn = $("#certDelete_btn");
    var detailBtn = $("#certDetail_btn");
    var xBtn = $("#x_btn");
    var titleText = $("#titleText");
    var ytLayer = $("#ytLayer");

    var mainCertList = $("#mainCertList"),
        mainInfo = ((vest.browser.isMSIE() > 9) || (vest.browser.isMSIE() == false)) ? $("#mainInfo") : $("#mainInfoIE9");
    var p12View = $("#P12");
    var imgView = $("#imgview");
    var fileInputHidden = $("#fileInputHidden");
    var fileInputBtn = $("#fileInputBtn");
    var fileInputDiv = $('#fileselect');
    var flashInputDiv = $('#flashselect');
    var contentsBackGround = $(".contentsBackGround");
    var saveTokenWrapper = $('#saveTokenWrapper');

    var importBrowserBtn = $("#importBrowserBtn");
    var importBrowserView = $("#importBrowserView");
    var importBrowserViewDefault = $("#importBrowserViewDefault");
    var importBrowserViewIE9 = $("#importBrowserViewIE9");
    var importBrowserViewIE9FlashDiv = $("#importBrowserViewIE9FlashDiv");
    var subCertList = $("#subCertList");
    var browserCertListBtn = $("#browserCertListBtn");
    var browserCertificateImportPFXBtn = $("#browserCertificateImportPFXBtn");
    var browserCertificateImportNPKIBtn = $("#browserCertificateImportNPKIBtn");
    var browserCertificateImportPFXFile = $('#browserCertificateImportPFXFile');
    var browserCertificateImportNPKIFile = $('#browserCertificateImportNPKIFile');
    var browserCertificateImportInfo = $('.BrowserCertificate_Import_Info');
    var browserCertificatePasswordInput = $('#browserCertificatePasswordInput');

    var CERTIFICATE_STATUS = {
        VALID: '',
        WARNNING: 'warnning',
        EXPIRED: 'expired'
    };

    var DRIVE_INFO = {
        SYSTEMDRIVE: 'SYSTEM DRIVE',
        DISKDRIVE: 'DISK DRIVE',
        TOKEN: 'TOKEN',
        SMARTCARDTOKEN: 'SmartCard TOKEN',
        CERTFILE: 'CERT FILE',
        // UBIKEY: 'UBIKEY',
        WEB: 'WEB'
    };

    // 저장매체 버튼 gray처리 여부(config 셋팅 값으로 설정됨)
    var useMenu = {
        USB_DISK: (_config.storage.usbDisk == true) ? '1' : '0',
        SECURE_TOKEN: (_config.storage.secureToken == true) ? '1' : '0',
        SAVE_TOKEN: (_config.storage.saveToken == true) ? '1' : '0',
        HARD_DISK: (_config.storage.hardDisk == true) ? '1' : '0',
        PHONE: (_config.storage.smartPhone == true) ? '1' : '0',
        SECURE_DISK: (_config.storage.secureDisk == true) ? '1' : '0',
        // WEBSTORAGE: (_config.serverStorage.hibiscus.use) ? '1' : '0',
        // WEBSTORAGE: (_config.storage.webStorage == true && (vest.browser.isMSIE() ? (vest.browser.isMSIE() >= 9 ? true : false) : true)) ? '1' : '0',
        HIBISCUS_STORAGE: (_config.serverStorage.hibiscus.use && (getBrowserVersion() == -1 || getBrowserVersion() > 10)) ? '1' : '0',
        CERTIFICATEFILE: (_config.storage.certificateFile == true && vest.util.file.hasFileAPI()) ? '1' : '0',
        SECURE_DISK_RESTORE: (_config.storage.secureDiskRestore == true) ? '1' : '0',
        DREAM_SERVICE: (_config.storage.dreamService == true) ? '1' : '0'
    };

    vest.init(_config);

    var addImportBtn = function () {
        $('#btnList').css('display', 'inline-block');
        // mainCertList.css('height','100px');
        subCertList.css('height', '100px');
    }

    var removeImportBtn = function () {
        $('#btnList').css('display', 'none');
        // mainCertList.css('height','132px');
        subCertList.css('height', '132px');
    }

    var dragCheck = function () {
        if (window.navigator.userAgent.toLowerCase().indexOf('firefox') !== -1)
            return;
        addHandle(document.getElementById('titleText'), window);
    };

    var getError = function (error) {
        var errorMessage;

        if (typeof (error) === 'undefined' || error == '') {  // error형식이 잘못되었을때
            error = { code: 1001 };
            vest.util.refactoryMsg.convertMsg(error);
            errorMessage = error.msg;
        } else if (typeof (error) === 'string') {
            errorMessage = error;
        } else if ((typeof (error.getReason) !== 'undefined') && (typeof (error.code) !== 'undefined')) {
            vest.util.refactoryMsg.convertMsg(error);

            if (error.code == 12020) {   // 비밀번호 에러시 횟수 제한
                _signErrCnt++;
                errorMessage = error.msg + ' - ' + jsLang(119) + '(' + _signErrCnt + ')';
            } else {
                errorMessage = error.msg;
            }
        }

        statusView.val(errorMessage);

        if (_config.signErrMaxValue > 0 && _signErrCnt == _config.signErrMaxValue) {
            error.dn = _dn; // 해당 경우에만 마지막 dn 반환
            _param.errorcallback(error);
            // _parent.close();
            // return;
        }

        certPin.val('');
        certPin.focus();
        if (!(pfxPin.prop('readonly')) && pfxPin.prop('style').display != 'none') {
            pfxPin.val('');
            if (!(typeof _pfxKeySafer !== 'undefined' && typeof _pfxKeySafer.disabledFocus === 'function' && _pfxKeySafer.disabledFocus())) {
                pfxPin.focus();
            }
            _preventKeyFlag = true;
        }

        if (typeof (_keySafer.clearPassword) !== 'undefined') _keySafer.clearPassword();
    };

    var hasFlash = function () {
        if ((vest.browser.isMSIE() === 9)) return true;
        else return false;
    };

    var getPopUpSize = function () {
        var popupSize = [];

        popupSize.width = window.innerWidth || document.documentElement.clientWidth || document.body.clientWidth;
        popupSize.height = window.innerHeight || document.documentElement.clientHeight || document.body.clientHeight;
        popupSize.top = window.screenTop - 62;
        popupSize.left = window.screenLeft + popupSize.width + 20;

        return popupSize;
    };

    //fixArg1:매체아이디, fixArg2: 매체클래스명, fixArg3: 매체이름, fixArg4: 사용여부(1:사용,0:미사용), fixArg5: 순서
    // 매체버튼 삭제시 해당 라인 주석처리

    var fixArgs = [];
    var storageCheck = function () {
        var storageType = _param.option.storageType;
        if (storageType != undefined && storageType != '' && storageType != 'PHONE') {
            storageType = vest.signHelper.decodeString(storageType, 'base64');
            storageType = vest.signHelper.decodeCharset(storageType, _param.option.charset);
            var storageID;

            for (i in useMenu) {
                useMenu[i] = '0';
            }

            if (storageType.indexOf(DRIVE_INFO.SYSTEMDRIVE) != -1) {
                _driveName = storageType.substr(DRIVE_INFO.SYSTEMDRIVE.length, storageType.length);
                _firstTrigger = "H";
                storageID = "hard_disk";
                useMenu['HARD_DISK'] = '1';
            }
            else if (storageType.indexOf(DRIVE_INFO.DISKDRIVE) != -1) {
                _driveName = storageType.substr(DRIVE_INFO.DISKDRIVE.length, storageType.length);
                _firstTrigger = "R";
                storageID = "usb_disk";
                useMenu['USB_DISK'] = '1';
            }
            else if (storageType.indexOf(DRIVE_INFO.TOKEN) != -1) {
                _driveName = storageType.substr(DRIVE_INFO.TOKEN.length, storageType.length);
                _firstTrigger = "U";
                storageID = "secure_token";
                useMenu['SECURE_TOKEN'] = '1';
            }
            else if (storageType.indexOf(DRIVE_INFO.SMARTCARDTOKEN) != -1) {
                _driveName = storageType.substr(DRIVE_INFO.SMARTCARDTOKEN.length, storageType.length);
                _firstTrigger = "S";
                storageID = "save_token";
                useMenu['SAVE_TOKEN'] = '1';
            }
            else if (storageType.indexOf(DRIVE_INFO.CERTFILE) != -1) {
                _driveName = storageType.substr(DRIVE_INFO.CERTFILE.length, storageType.length);
                _firstTrigger = "F";
                storageID = "certificate_file";
                useMenu['CERTIFICATEFILE'] = '1';
            }
            // else if (storageType.indexOf(DRIVE_INFO.UBIKEY) != -1) {
            //     _driveName = storageType.substr(DRIVE_INFO.UBIKEY.length, storageType.length);
            //     storageID = "phone_certification";
            // }
            else if (storageType.indexOf(DRIVE_INFO.WEB) != -1) {
                _driveName = storageType.substr(DRIVE_INFO.WEB.length, storageType.length);
                _firstTrigger = "W";
                storageID = "webStorage";
                useMenu['WEBSTORAGE'] = '1';
            }

            for (var i = 0; i < fixArgs.length; i++) {
                if (fixArgs[i].fixArg1 != storageID) {
                    fixArgs[i].fixArg4 = 0;
                }
            }
        }
    };

    var menuEvent = function () {
        /* 저장매체 버튼 스크롤 */
        var list = $("#button_slide_wrap #menu_btn li");
        var $list = list.length;

        $(".next").on("click", function () {
            $("#button_slide_wrap #menu_btn").animate({
                left: "-=73px"
            }, 0, function () {
                $("#button_slide_wrap #menu_btn li").eq(0).appendTo($("#button_slide_wrap #menu_btn"));
                $("#menu_btn").css("left", "2px")
            });//innerfunc
        });//next
        $(".prev").on("click", function () {
            $("#button_slide_wrap #menu_btn").animate({
                left: "0px"
            }, 0, function () {
                $("#button_slide_wrap #menu_btn li").eq($list - 1).prependTo($("#button_slide_wrap #menu_btn"));
                $("#menu_btn").css("left", "2px")
            })//innerfunc
        });//prev
    };

    var tab = function (num) {
        if ($("#menu_btn li:eq(" + num + ") a").hasClass("on"))
            return;

        _selectedStorageNum = num;
        for (var i = 0; i < $("#menu_btn li").length; i++) {
            if ($("#menu_btn li:eq(" + i + ") a").attr('class') != undefined)
                $("#menu_btn li:eq(" + i + ") a").attr('class', $("#menu_btn li:eq(" + i + ") a").attr('class').replace("_on on", ""));
        }
        if ($("#menu_btn li:eq(" + num + ") a").attr('class') != undefined)
            $("#menu_btn li:eq(" + num + ") a").attr('class', $("#menu_btn li:eq(" + num + ") a").attr('class') + "_on on");
    };

    var orderMenuadvertising = function () {
        if (_config.advertising.enable) {
            for (var i = 0; i < _config.advertising.usetab.length; i++) {
                if (_config.advertising.usetab[i][0] != undefined && _config.advertising.usetab[i][0] != '' &&
                    _config.advertising.usetab[i][1] != undefined && _config.advertising.usetab[i][1] != '' &&
                    _config.advertising.usetab[i][2] != undefined && _config.advertising.usetab[i][2] != '' && _config.advertising.usetab[i][2] == '1') {
                    fixArgs.push({
                        fixArg1: _config.advertising.usetab[i][0],
                        fixArg2: _config.advertising.usetab[i][1],
                        fixArg3: '',
                        fixArg4: _config.advertising.usetab[i][2],
                        fixArg5: ''
                    });
                }
            }
        }
    }

    var orderMenu = function (order) {
        if (typeof (order) === 'undefined' || order == '' || order == 0) {
            order = 'TIBHRFUSPD';
        }
        for (var i = 0; i < order.length; i++) {
            switch (order[i]) {
                case 'T':
                    orderMenuadvertising();
                    break;
                case 'B':
                    if (typeof _config.storage.secureDiskRestore !== 'undefined' && _config.storage.secureDiskRestore == true) {
                        fixArgs.push({
                            fixArg1: 'secure_disk_restore',
                            fixArg2: 'ico9',
                            fixArg3: jsLang(157),
                            fixArg4: useMenu['SECURE_DISK_RESTORE'],
                            fixArg5: ''
                        });
                    }
                    break;
                case 'H':
                    fixArgs.push({
                        fixArg1: 'hard_disk',
                        fixArg2: 'ico4',
                        fixArg3: jsLang(127),
                        fixArg4: useMenu['HARD_DISK'],
                        fixArg5: ''
                    });
                    break;
                case 'R':
                    fixArgs.push({
                        fixArg1: 'usb_disk',
                        fixArg2: 'ico1',
                        fixArg3: jsLang(128),
                        fixArg4: useMenu['USB_DISK'],
                        fixArg5: ''
                    });
                    break;
                case 'F':
                    fixArgs.push({
                        fixArg1: 'certificate_file',
                        fixArg2: 'ico5',
                        fixArg3: jsLang(130),
                        fixArg4: useMenu['CERTIFICATEFILE'],
                        fixArg5: ''
                    });
                    break;
                case 'U':
                    fixArgs.push({
                        fixArg1: 'secure_token',
                        fixArg2: 'ico2',
                        fixArg3: jsLang(129),
                        fixArg4: useMenu['SECURE_TOKEN'],
                        fixArg5: ''
                    });
                    break;
                case 'S':
                    fixArgs.push({
                        fixArg1: 'save_token',
                        fixArg2: 'ico3',
                        fixArg3: jsLang(132),
                        fixArg4: useMenu['SAVE_TOKEN'],
                        fixArg5: ''
                    });
                    break;
                case 'P':
                    fixArgs.push({
                        fixArg1: 'phone_certification',
                        fixArg2: 'ico7',
                        fixArg3: jsLang(133),
                        fixArg4: useMenu['PHONE'],
                        fixArg5: ''
                    });
                    break;
                case 'D':
                    if (typeof _config.storage.dreamService !== 'undefined' && _config.storage.dreamService == true) {
                        fixArgs.push({
                            fixArg1: 'dream_service',
                            fixArg2: 'ico10',
                            fixArg3: jsLang(156),
                            fixArg4: useMenu['DREAM_SERVICE'],
                            fixArg5: ''
                        });
                    }
                    break;
                case 'I':
                    fixArgs.push({
                        fixArg1: 'hibiscus_storage',
                        fixArg2: 'ico8',
                        fixArg3: jsLang(138),
                        fixArg4: useMenu['HIBISCUS_STORAGE'],
                        fixArg5: ''
                    });
                    break;
            }
        }

        // 공통저장소 강제 disabled 주석풀면 활성화
        // fixArgs.push({fixArg1: 'webStorage', fixArg2: 'ico8', fixArg3: jsLang(138), fixArg4: useMenu['WEBSTORAGE'], fixArg5: ''});
        // fixArgs.push({fixArg1: 'secure_disk', fixArg2: 'ico6', fixArg3: jsLang(131), fixArg4: useMenu['SECURE_DISK'], fixArg5: ''});
        // fixArgs.push({
        //     fixArg1: 'hibiscus_storage',
        //     fixArg2: 'ico8',
        //     fixArg3: jsLang(138),
        //     fixArg4: useMenu['HIBISCUS_STORAGE'],
        //     fixArg5: ''
        // });    

        // 기존 순서 참고용으로 남김.
        //args.push({arg1: 'secure_disk_restore', arg2: useMenu['SECURE_DISK_RESTORE']});      // 안전디스크 복구
        //args.push({arg1: 'hard_disk', arg2: useMenu['HARD_DISK']});
        //args.push({arg1: 'usb_disk', arg2: useMenu['USB_DISK']});
        //args.push({arg1: 'certificate_file', arg2: useMenu['CERTIFICATEFILE']});     // 찾아보기
        //args.push({arg1: 'webStorage', arg2: useMenu['WEBSTORAGE']});
        //args.push({arg1: 'secure_disk', arg2: useMenu['SECURE_DISK']});      // 안전디스크
        //args.push({arg1: 'secure_token', arg2: useMenu['SECURE_TOKEN']});
        //args.push({arg1: 'phone_certification', arg2: useMenu['PHONE']});      // 휴대폰인증
        //args.push({arg1: 'save_token', arg2: useMenu['SAVE_TOKEN']});
        //args.push({arg1: 'dream_service', arg2: useMenu['DREAM_SERVICE']});   // 드림서비스
    };
    var showMenu = function () {
        //출력용 저장매체 목록 배열정보 수정, 순서 재배열.
        // showMenu에 정의된 args 순서대로 배치, 나중에 순서관련 배열, 눈에보기 좋게 정리가능할듯.
        //$.each(args, function (index, item) {
        //    $.each(fixArgs, function (indexs, items) {
        //        if (item.arg1 == items.fixArg1) {
        //            items.fixArg5 = index;
        //        }
        //    });
        //});

        //출력용 저장매체 목록 배열정보 정렬
        fixArgs.sort(function (a, b) {
            if (a.fixArg5 > b.fixArg5) return 1;
            if (a.fixArg5 < b.fixArg5) return -1;
            return 0;
        });

        $("#menu_btn").empty();

        //저장매체 출력
        $.each(fixArgs, function (index, item) {
            if (item.fixArg4 == "1") {
                $(document.createElement('li'))
                    .attr("id", item.fixArg1)
                    .append($(document.createElement('a'))
                        .attr({
                            "href": "javascript:;",
                            "class": item.fixArg2,
                            "tabindex": _menuTabIndex,
                            "title": item.fixArg3   // 말풍선
                        })
                        .on("click", function () {
                            tab($(this).parent().index());
                        })
                        .on("keydown", function (key) {
                            if(key.keyCode == 13)
                            {
                                tab($(this).parent().index());
                            }
                            keyEvent(key, function () {
                                // tab($(this).parent().index());
                            });
                        })
                        .append(item.fixArg3)   // 매체 문구(일본어일때 영어로 변환)
                    )
                    .appendTo($("#menu_btn"));
                _menuTabIndex = _menuTabIndex + 1;
            } else {    // disable처리된 항목
                $(document.createElement('li'))
                    .attr("class", item.fixArg2 + "_disable")
                    .append(item.fixArg3)   // 매체문구(일본어일때 영어로 변환)
                    .appendTo($("#menu_btn"));
            }
            _subMenuTabIndex = _menuTabIndex + 1;
        })

        // 탭 default 이미지
        for (var j = 0; j < $(".cert01_01 ul li").length; j++) {
            if ($(".cert01_01 ul li:eq(" + j + ") a").attr('class') != undefined && $(".cert01_01 ul li:eq(" + j + ") a").attr('class') != '') {
                var tabImgUrl = $(".cert01_01 ul li:eq(" + j + ") a").css('background-image');
                if (tabImgUrl == 'none') {
                    for (var i = 0; i < _config.advertising.taburl.length; i++) {
                        if ($(".cert01_01 ul li:eq(" + j + ") a").attr('class') == _config.advertising.taburl[i][0]) {
                            $(".cert01_01 ul li:eq(" + j + ")").css({ "background": "url(" + _config.advertising.taburl[i][1] + ")", "background-size": "65px 80px", "background-repeat": "no-repeat", "background-position": "center" });
                        } else {
                            $(".cert01_01 ul li:eq(" + j + ")").css({ "background": "url(" + _config.advertising.defaulturl[0] + ")", "background-size": "65px 80px", "background-repeat": "no-repeat", "background-position": "center" });
                        }
                    }
                }
            }
        }
        // $("[tabindex=7]").focus(function () {
        //     $("[tabindex=7]").keydown(function (key)
        //         {
        //             if(key.keyCode == 13)
        //             {
        //                 $("#button_slide_wrap #menu_btn").animate({
        //                     left: "0px"
        //                 });
        //             }
        //             })
        //     $("[tabindex=9]").keydown(function (key)
        //         {
        //             if(key.keyCode == 13)
        //             {
        //                 $("#button_slide_wrap #menu_btn").animate({
        //                     left: "0px"
        //                 });
        //             }
        //     })
        //      $("#button_slide_wrap #menu_btn").animate({
        //          left: "-=150px"
        //      })
        // });
            
        //     $("[tabindex=9]").blur(function () {
        //         $("#button_slide_wrap #menu_btn").animate({
        //             left: "0px"
        //         });
        // });
    };


    var firstCheck = function () {
        for (var i in _config.storage) {
            if (_config.storage[i] == true) {
                switch (i) {
                    case 'hardDisk':
                        return 'H';
                    case 'usbDisk':
                        return 'R';
                    case 'secureToken':
                        return 'U';
                    case 'saveToken':
                        return 'S';
                    case 'certificateFile':
                        return 'F';
                    case 'secureDisk':
                        return 'SD';
                    case 'webStorage':
                        return 'web';
                    case 'secureDiskRestore':
                        return 'SDB';
                    case 'advertising':
                        return 'AD'
                    default:
                        return 'H';                   // default는 지정되어있어야되지않을까...
                }
            }
        }
    };
    var startMenu = function () {
        _firstTrigger = (_config.secureDiskBackup == true) ? firstCheck() : _firstTrigger;
        /*
         안전디스크 백업 이용시... 그냥 SDB로 설정하면되는걸 굳이 firstCheck를 태워야했을까..?
         _firstTrigger = 'SDB';
         */

        _firstTrigger = _config.firstTrigger;

        //if (_config.serverStorage.hibiscus.use && (getBrowserVersion() == -1 || getBrowserVersion() > 10)) _firstTrigger = 'hibiscus';

        /*if (_config.advertising.enable) {
                _firstTrigger = 'AD';
        }*/

        switch (_firstTrigger) {
            case 'R':       // 이동식 디스크
                $("a.ico1").trigger("click");
                break;
            case 'U':       // 보안 토큰
                $("a.ico2").trigger("click");
                break;
            case 'S':       // 저장 토큰
                $("a.ico3").trigger("click");
                break;
            case 'H':       // 하드디스크
                $("a.ico4").trigger("click");
                break;
            case 'F':       // 인증서 찾기
                $("a.ico5").trigger("click");
                break;
            case 'SD':    // 안전디스크
                $("a.ico6").trigger("click");
                break;
            case 'W':    // 웹저장소
                $("a.ico8").trigger("click");
                break;
            case 'SDB':    // 안전디스크백업
                $("a.ico9").trigger("click");
                break;
            case 'hibiscus':
            case 'HIBISCUS':
                $("a.ico8").trigger("click");
                break;
            case 'AD':
                $("a.ico11").trigger("click");
                break;
            default:
                $("a.ico4").trigger("click");
                break;
        }
    };
    var setTitle = function () {
        // 기능별 타이틀 설정
        if (_config.signTitle !== undefined) {
            switch (_config.signTitle) {
                case 'SIGN':
                    _config.title = jsLang(81);
                    break;
                case 'UPDATE':
                    _config.title = jsLang(122);
                    break;
                case 'REVOKE':
                    _config.title = jsLang(123);
                    break;
                case 'EXPORT':
                    _config.title = jsLang(124);
                    break;
                case 'CHANGEPIN':
                    _config.title = jsLang(125);
                    break;
                case 'CHANGESTORAGE':
                    _config.title = jsLang(126);
                    break;
                case 'VERIFYPIN':
                    _config.title = jsLang(137);
                    break;
                case 'SHOW':
                    _config.title = jsLang(100);
                    break;
                case 'DELETE':
                    _config.title = jsLang(87);
                    break;
            }
        } else {
            _config.title = jsLang(81);
        }

        titleText.append(_config.title);
    };

    // certificate view util
    var issuerSplit = function (issuer, select) {
        var issuerLsit = issuer.split(',');
        var result;
        for (var i = 0; i < issuerLsit.length; i++) {
            if (issuerLsit[i].indexOf(select.toUpperCase() + '=') !== -1 || issuerLsit[i].indexOf(select.toLowerCase() + '=') !== -1) {
                result = issuerLsit[i].split('=');
                return result[1];
            }
        }
    };
    var getCommonName = function (issuer) {
        return issuerSplit(issuer, 'CN');
    };

    function parseDnString(dn, isCut) {
        var temp,
            result;

        var arr = dn.split(',');
        for (var index = 0; index < arr.length; index++) {
            var str = $.trim(arr[index]);
            if (str.toUpperCase().indexOf('CN') !== -1) {
                temp = str;
                result = $.trim(temp.split('=')[1]);
                break;
            } else if (str.toUpperCase().indexOf('OU') !== -1) {
                temp = str;
                result = $.trim(temp.split('=')[1]);
                break;
            }
            else {
                result = dn;
            }
        }

        if (typeof isCut !== 'undefined' && !isCut) return result;
        return (result.length > 15) ? result.substring(0, 15) + '...' : result;
    };
    var getCaType = function (isser) {
        if (isser.indexOf('yessign') !== -1)
            return 'yessign';
        if (isser.indexOf('signkorea') !== -1)
            return 'signkorea';
        if (isser.indexOf('crosscert') !== -1)
            return 'crosscert';
        if (isser.indexOf('tradesign') !== -1)
            return 'tradesign';
        if (isser.indexOf('signgate') !== -1)
            return 'signgate';
    };
    var isValid = function (subDay) {
        if (subDay < 0) {
            return CERTIFICATE_STATUS.EXPIRED;
        }
        else if (subDay < 30) {
            return CERTIFICATE_STATUS.WARNNING;
        }
        else {
            return CERTIFICATE_STATUS.VALID;
        }
    };

    var classFilter = function (cert) {
        var arr = vest.util.classPolicies.makeClassPolicies(_config.certificateClass);

        for (var i = 0; i < arr.length; i++) {
            if (arr[i] == cert[0].getPolicy()) return true;
        }

        return false;
    };

    var policyFilter = function (cert) {
        var arr = _config.OID.split(';');

        for (var i = 0; i < arr.length; i++) {
            if (arr[i] == cert[0].getPolicy()) return true;
        }

        return false;
    };

    var issuerFilter = function (cert) {
        var arr = _config.issuerFilter.split(';');

        for (var i = 0; i < arr.length; i++) {
            if (arr[i] == parseDnString(cert[0].getIssuer(), false)) return true;
        }

        return false;
    };

    var checkClassPolicies = function (cert) {
        if (_config.certificateClass != 0 && _config.OID != '0' && _config.issuerFilter != '0') {           // 등급, OID, iusser 셋다 0이 아닐때
            return classFilter(cert) || (policyFilter(cert) && issuerFilter(cert));     // 일치하는 값 있으면 그냥 보여주면됨.
        } else if (_config.certificateClass != 0 && _config.OID == '0' && _config.issuerFilter == '0') {   // 등급만 0이 아닐때
            return classFilter(cert);
        } else if (_config.certificateClass == 0 && _config.OID != '0' && _config.issuerFilter == '0') {    // OID만 0이 아닐때
            return policyFilter(cert);
        } else if (_config.certificateClass == 0 && _config.OID == '0' && _config.issuerFilter != '0') {    // issuer만 0이 아닐때
            return issuerFilter(cert);
        } else if (_config.certificateClass != 0 && _config.OID != '0' && _config.issuerFilter == '0') {   // 등급, OID만 0이 아닐때
            return classFilter(cert) || policyFilter(cert);
        } else if (_config.certificateClass != 0 && _config.OID == '0' && _config.issuerFilter != '0') {   // 등급, issuer만 0이 아닐때
            return classFilter(cert) || issuerFilter(cert);
        } else if (_config.certificateClass == 0 && _config.OID != '0' && _config.issuerFilter != '0') {   // OID, issuer만 0이 아닐때
            return policyFilter(cert) && issuerFilter(cert);    // OID중에서 해당 issuer에 해당하는 목록만 보여줄 것
        } else {    // 등급, OID, issuer 셋다 0일 경우
            return true;
        }
    };

    var removeCertList = function () {
        $(".cert01_02 .certificateListText").remove();
    };

    var addNoCertList = function () {
        $(".cert01_02").append($(document.createElement('span'))
            .addClass('certificateListText')
            .append($(document.createElement('a'))
                .attr("href", "#")
                .attr("id", "notCertificateList")
            )
        )
    };

    function browserCertificateFilter(item) {
        if ((item.getSubject().indexOf("OU=personalB") !== -1) || ((item.getSubject().indexOf("ou=personalB") !== -1))) return jsLang(166);
        else if ((item.getSubject().indexOf("OU=corporation4ECB") !== -1) || ((item.getSubject().indexOf("ou=corporation4ECB") !== -1))) return jsLang(167);
    }

    var outCertList = function (args) {
        var _count = 1;
        $('#outCertList').empty();
        statusView.val('');
        certPin.val('');
        browserCertificatePasswordInput.val('');

        if ($(".certificateListText").length > 0)
            $(".certificateListText").remove();

        if (args.length == 0) { // args 저장된 인증서가 없을때
            if ($(".certificateListText").length == 0) {
                addNoCertList();
            }
            $("#notCertificateList").text(jsLang(79));
            ytLayer.hide();
            return 0;
        }

        $.each(args, function (index, item) {
            var caInfo;
            var _temp;
            var certID = typeof item.getIdentifier().cert !== 'undefined' ? item.getIdentifier().cert : item.getIdentifier();
            caInfo = _OID[item.getPolicy()];
            if (typeof (_OID[item.getPolicy()]) === 'undefined')
                caInfo = { caName: "test", usage: jsLang(8) };

            var pClass = "";
            if (certID.substr(certID.length - 1) == '5' && item.getCloud() == true) {
                _temp = browserCertificateFilter(item);
                if (_temp !== undefined) {
                    caInfo.usage = _temp;
                }
                if ((isValid(item.getAvailableDays()) === CERTIFICATE_STATUS.VALID))
                    pClass = "m_cert_cloud";
                else if ((isValid(item.getAvailableDays()) === CERTIFICATE_STATUS.EXPIRED))
                    pClass = "m_cert_exp_cloud";
                else if ((isValid(item.getAvailableDays()) === CERTIFICATE_STATUS.WARNNING))
                    pClass = "m_cert_warn_cloud";

            } else {
                if ((isValid(item.getAvailableDays()) === CERTIFICATE_STATUS.VALID))
                    pClass = "m_bullet";
                else if ((isValid(item.getAvailableDays()) === CERTIFICATE_STATUS.EXPIRED))
                    pClass = "m_bullet_exp";
                else if ((isValid(item.getAvailableDays()) === CERTIFICATE_STATUS.WARNNING))
                    pClass = "m_bullet_time";
            }

            var OIDFlag = checkClassPolicies([item]);   // false: 그레이, true: 정상

            if (pClass == "m_bullet_exp" && _config.expiredCertFilter) return true;  // 만료된 인증서 표시여부(설정 값에 따라서)

            $(document.createElement('li'))
                .append($(document.createElement('input'))
                    .attr({ type: 'hidden' })
                )
                .append($(document.createElement('ul'))
                    .addClass('list_list clfix')
                    .css('background', OIDFlag ? 'none' : '#DCDCDC')
                    .attr({
                        "gray": OIDFlag ? "off" : "on"
                    })
                    .on("click", function () {
                        _caType.name = getCaType(parseDnString(item.getIssuer()).toLowerCase());

                        outCertComment(item.getIdentifier(), item.getSubject(), item.getSerial());
                        if (typeof item.tokenNumber !== 'undefined') _tokenNumber = item.tokenNumber;

                        _isGray = $(this).attr("gray");     // gray처리 alert창 사용하기 위해

                        if ($(this).hasClass("on")) return;
                        $("#outCertList li ul").removeClass("on");
                        $(this).addClass("on");
                    })
                    .on("keydown", function (key) {
                        if(key.keyCode != 9)
                        {
                            if ($(this).hasClass("on")) return;
                            $("#outCertList li ul").removeClass("on");
                            $(this).addClass("on");
                        }
                        keyEvent(key, function () {
                            _caType.name = getCaType(parseDnString(item.getIssuer()).toLowerCase());

                            outCertComment(item.getIdentifier(), item.getSubject(), item.getSerial());
                            if (typeof item.tokenNumber !== 'undefined') _tokenNumber = item.tokenNumber;

                            if (_disPersonFlg) {
                                certPin.focus();
                                // browserCertificatePasswordInput.focus();
                                key.preventDefault();
                            }
                        });
                    })
                    .append($(document.createElement('li'))
                        .append($(document.createElement('p'))
                            .attr({ "tabindex": _subMenuTabIndex + _count })
                            .addClass(pClass)
                            .append(parseDnString(item.getSubject()))
                        )
                    )
                    .append($(document.createElement('li'))
                        .append(item.getValidityTo('YYYY.MM.DD'))
                    )
                    .append($(document.createElement('li'))
                        .append(caInfo ? caInfo.usage : '')
                    )
                    .append($(document.createElement('li'))
                        .append(getCommonName(item.getIssuer()))
                    )
                )
                .appendTo($('#outCertList')).appendTo($('#outCertList'));
            // browserCertificatePasswordInput.attr({ "disabled": false });
            _count++;
        });

        $('#outCertList li:nth-child(odd) ul').attr("class", "list_list_ clfix");
        if (!($("#outCertList li ul").length == 0 || $("#outCertList li ul") == []))
        {
            $("#outCertList li ul")[0].click();
            $("#outCertList li ul li p")[0].focus();
            webAccess.certListFocus();
            webAccess.firstFocus();
        }
        else {
            if ($(".certificateListText").length == 0) {
                addNoCertList();
            }
            $("#notCertificateList").text(jsLang(79));
        }
        ytLayer.hide();

    };

    var getDetailCert = function (callback, certIdentifier) {
        var last = _selected.cert.substr(_selected.cert.length - 1);
        var resultCert;
        if (last == 5) {
            vest.token.openCertSync([], _config.yessign.mode, function (localCertInfos) {
                for (var i = 0; i < localCertInfos.length; i++) {
                    if (localCertInfos[i].getIdentifier().cert == _selected.cert) {
                        resultCert = localCertInfos[i];
                    }
                }
                callback(resultCert);
            }, hibiscusErrorHandler);

        } else {
            vest.token.getCertificate(_tokenNumber, certIdentifier, function (_certificate) {
                callback(_certificate);
            }, vestCertErrorHandler);
        }
    };
    var getDetailCACert = function (callback, type) {
        vest.token.getCACertificate(_tokenNumber, _selected.cert, type, function (_certificate) {
            if (_certificate == undefined) {
                return;
            } else {
                setRootCAHashString(_certificate.cert.getSubject(), _certificate.rootCertHash);
                callback(_certificate.cert);
            }
        }, vestCertErrorHandler);
    };
    var setCACertLinkEvent = function () {
        $("#certAdd_btn").unbind("click");
        $("#certAdd_btn").click(function () {

            if (typeof _caType.url !== 'undefined') {
                window.open(_caType.url);
            } else {
                switch (_caType.name) {
                    case 'yessign':
                        window.open("http://www.yessign.or.kr/home/subIndex/395.do");
                        break;
                    case 'signkorea':
                        window.open("http://www.signkorea.com/footer/rule_idx.jsp");
                        break;
                    case 'crosscert':
                        window.open("https://www.crosscert.com/glca/01_0_00.jsp");
                        break;
                    case 'tradesign':
                        window.open("http://www.tradesign.net/uview/serviceinfo/serviceinfo01.jsp");
                        break;
                    default:
                }
            }
        });
    };
    var detailCertContentList = function (certificate) {
        _caType.url = certificate.getCps();
        var args = [
            { arg1: jsLang(9), arg2: certificate.getSubject() },
            { arg1: jsLang(10), arg2: certificate.getVersion() },
            { arg1: jsLang(11), arg2: certificate.getSerialNumber() },
            { arg1: jsLang(12), arg2: certificate.getIssuer() },
            { arg1: jsLang(13), arg2: certificate.getValidityFrom('YYYY.MM.DD') },
            { arg1: jsLang(14), arg2: certificate.getValidityTo('YYYY.MM.DD') },
            { arg1: jsLang(15), arg2: certificate.getSignatureAlgorithm() },
            { arg1: jsLang(16), arg2: certificate.getPublickeyAlgorithm() },
            { arg1: jsLang(17), arg2: certificate.getPublickey() },
            { arg1: jsLang(18), arg2: certificate.getSignatureValue() },
            { arg1: jsLang(19), arg2: certificate.getKeyUsage() },
            { arg1: jsLang(20), arg2: certificate.getIssuerKeyIdentifier() },  // 발급자키 식별자
            { arg1: jsLang(21), arg2: certificate.getSubjectKeyIdentifier() },
            { arg1: jsLang(22), arg2: certificate.getExtKeyUsage() },  // 확장키 사용용도
            { arg1: jsLang(23), arg2: certificate.getRealName() },
            { arg1: jsLang(24), arg2: certificate.getBasicConstranints() },  // 기본제한
            { arg1: jsLang(25), arg2: certificate.getPolicy() },
            { arg1: jsLang(26), arg2: certificate.getCrlDistributionPoint() },
            { arg1: jsLang(27), arg2: certificate.getAuthorityInfoAccess() },
            { arg1: jsLang(28), arg2: certificate.getSaveCert() }  // 저장위치
        ];
        return args;
    };

    var detailOpenCertContentList = function (certificate) {
        var args = [
            { arg1: jsLang(9), arg2: certificate.getSubject() },
            { arg1: jsLang(11), arg2: certificate.getSerial() },
            { arg1: jsLang(12), arg2: certificate.getIssuer() },
            { arg1: jsLang(13), arg2: certificate.getValidityFrom('YYYY.MM.DD') },
            { arg1: jsLang(14), arg2: certificate.getValidityTo('YYYY.MM.DD') },
            { arg1: jsLang(25), arg2: certificate.getPolicy() }
        ];
        return args;
    };

    function setRootCAHashString(subject, hash) {
        var str = '☞  ' + parseDnString(subject) + ' [SHA 256]' + '\n' + hash;
        $('#dialogCertHash_text').html(str.replace(/\n/g, '<br />'));
    };

    function rootCAHashOpen() {
        $("#dialogCertHash").dialog({
            autoOpen: true,
            scroll: "no",
            modal: true,
            resizable: false,
            button: {
                cancel: function () {
                    dialog.dialog("close");
                }
            },
            close: function () {
            },
            open: function (type, data) {
                $(".ui-dialog-titlebar", $(this).parent()).remove();
                $("#dialogCertHash").dialog({ width: "auto", height: "auto" });
                $("#dialogCertHash").css('overflow', 'hidden');
            },
            resizeStop: function () {
                $(this).dialog({ height: "auto" });
            }
        });

        $("#dialogCertHash_xBtn").unbind("click");
        $("#dialogCertHash_xBtn").click(function () {
            $("#dialogCertHash").dialog("close");
        });

        $("#dialogCertHash_confirmBtn").unbind("click");
        $("#dialogCertHash_confirmBtn").click(function () {
            $("#dialogCertHash").dialog("close");
        });

        $("#dialogCertHash_cancelBtn").unbind("click");
        $("#dialogCertHash_cancelBtn").click(function () {
            $("#dialogCertHash").dialog("close");
        });
    };

    var detailPfxOpen = function (dn) {
        var detailSignFlg = false;
        var _selectedCert;

        // 해당인증서 검색
        $.each(_certificates, function (index, item) {
            // $.each(_selectedCert.toShortCert(), function(index, item){
            // if(vest.secureCertificateStorage.isSecureCertificateItem(item)) {
            //     item = new vest.pki.Certificate(item.signCertificate);
            // }

            if (item.getSubject() == dn) {
                _selectedCert = item;
            }
        });

        // 0: 안보이게, 1: 보이게.
        var arglist0 = [
            { arg1: jsLang(29), arg2: 'dialogCertTab_tab_li1', arg3: '1' }
        ];

        $("#dialogCertTab_tab_ul").empty();
        $("#dialogCertTab_text").val("");

        $.each(arglist0, function (index, item) {
            if (item.arg3 != "1") return;
            $(document.createElement('li'))
                .attr("id", item.arg2)
                .append($(document.createElement('a'))
                    .attr({ "href": "javascript:;" })
                    .append(item.arg1)
                )
                .appendTo($("#dialogCertTab_tab_ul"));
        });

        $("#dialogCertTab_attrList").empty();
        $("#dialogCertTab_text").val("");

        $("#dialogCertTab_tab_subBtn_div").empty();
        $(document.createElement('p'))
            .attr("class", "CERT_btnBg_sbtn4")
            .append($(document.createElement('a'))
                .attr({ "href": "#", "id": "certAdd_btn" })
                .append(jsLang(35))
            )
            .appendTo($("#dialogCertTab_tab_subBtn_div"));

        $(document.createElement('p'))
            .attr("class", "CERT_btnBg_sbtn")
            .append($(document.createElement('a'))
                .attr({ "href": "#", "id": "pfxVerify_btn" })
                .append(jsLang(36))
            )
            .appendTo($("#dialogCertTab_tab_subBtn_div"));

        $("#dialogCertTab_tab_ul li").unbind("click");
        $("#dialogCertTab_tab_ul li").click(function () {
            if (!detailSignFlg) {
                detailMsg(_selectedCert.getUserNotice());
                detailSignFlg = true;
            }
            detailCertTabList(detailCertContentList(_selectedCert));
        });

        setCACertLinkEvent();

        $("#pfxVerify_btn").unbind("click");
        $("#pfxVerify_btn").click(function () {
            $("#dialogCertVid_text").text('');
            $("#dialogCertVid").dialog({
                autoOpen: true,
                modal: true,
                resizable: false,
                open: function (type, data) {
                    jQuery.ajax({
                        type: 'POST',
                        url: '../bmt_sample/CertVerifier.jsp',
                        data: "reqData=" + vest.signHelper.certToBase64(_selectedCert),
                        success: function (data) {
                            $("#dialogCertVid_text").text(pfxValidateMsg(data, _selectedCert));
                        },
                        error: function (xhr, status, error) {
                            alert('검증서버 통신 에러');
                            $("#dialogCertVid").dialog("close");
                        }
                    });

                    $(".ui-dialog-titlebar", $(this).parent()).remove();
                    $("#dialogCertVid").dialog({ width: "auto", height: "auto" });
                    $("#dialogCertVid").css('overflow', 'hidden');
                }
            });
        });

        detailCertView();
    };
    var detailOpen = function () {
        var detailSignFlg = false,
            detailKeyFlg = false,
            detailCAFlg = false,
            detailRootFlg = false;

        var tabCheck = 1;
        var caTabCheck = 1;
        if (_selected.cert == _selected.kmCert)
            tabCheck = 0;
        if (_selected.cert.substr(_selected.cert.length - 1) == '5')
            caTabCheck = 0;

        // 0: 안보이게, 1: 보이게.
        var arglist0 = [
            { arg1: jsLang(29), arg2: 'dialogCertTab_tab_li1', arg3: '1' }
            , { arg1: jsLang(30), arg2: 'dialogCertTab_tab_li2', arg3: tabCheck }
            , { arg1: jsLang(31), arg2: 'dialogCertTab_tab_li3', arg3: caTabCheck }
            , { arg1: jsLang(32), arg2: 'dialogCertTab_tab_li4', arg3: caTabCheck }
        ];

        $("#dialogCertTab_tab_ul").empty();
        $("#dialogCertTab_text").val("");

        $.each(arglist0, function (index, item) {

            if (item.arg3 != "1") return;

            $(document.createElement('li'))
                .attr("id", item.arg2)
                .append($(document.createElement('a'))
                    .attr({ "href": "javascript:;" })
                    .append(item.arg1)
                )
                .appendTo($("#dialogCertTab_tab_ul"));
        });

        $("#dialogCertTab_tab_ul li").unbind("click");
        $("#dialogCertTab_tab_ul li").click(function () {

            $("#dialogCertTab_attrList").empty();
            $("#dialogCertTab_text").val("");

            var _certificate;

            if (this.id == "") return;
            detailCertBtn(this.id);

            for (var i = 0; i < $("#dialogCertTab_tab_ul li").length; i++) {
                $("#dialogCertTab_tab_ul li:eq(" + i + ")").removeClass("CERT_on");
                $("#dialogCertTab_tab_ul li:eq(" + i + ") a").removeClass("CERT_on");
            }

            $("#dialogCertTab_tab_ul li:eq(" + $(this).index() + ")").attr("class", "CERT_on");
            $("#dialogCertTab_tab_ul li:eq(" + $(this).index() + ") a").attr("class", "CERT_on");

            switch (this.id) {
                case "dialogCertTab_tab_li1":
                    getDetailCert(function (certificate) {
                        _certificate = certificate;
                        if (!detailSignFlg) {
                            if (_selected.cert.substr(_selected.cert.length - 1) !== '5')
                                detailMsg(certificate.getUserNotice());
                            detailSignFlg = true;
                        }
                        if (_selected.cert.substr(_selected.cert.length - 1) == '5') detailCertTabList(detailOpenCertContentList(certificate));
                        else detailCertTabList(detailCertContentList(certificate));
                    }, _selected.cert);
                    break;
                case "dialogCertTab_tab_li2":
                    getDetailCert(function (certificate) {
                        _certificate = certificate;
                        if (!detailKeyFlg) {
                            if (_selected.kmCert.substr(_selected.kmCert.length - 1) !== '5')
                                detailMsg(certificate.getUserNotice());
                            detailSignFlg = true;
                        }
                        if (_selected.kmCert.substr(_selected.kmCert.length - 1) == '5') detailCertTabList(detailOpenCertContentList(certificate));
                        else detailCertTabList(detailCertContentList(certificate));
                    }, _selected.kmCert);
                    break;
                case "dialogCertTab_tab_li3":
                    getDetailCACert(function (certificate) {
                        if (certificate == undefined) {
                            return;
                        }
                        _certificate = certificate;
                        if (!detailCAFlg) {
                            detailMsg(certificate.getUserNotice());
                            detailCAFlg = true;
                        }
                        detailCertTabList(detailCertContentList(certificate));
                    }, 'ca');
                    break;
                case "dialogCertTab_tab_li4":
                    getDetailCACert(function (certificate) {
                        if (certificate == undefined) {
                            return;
                        }
                        _certificate = certificate;
                        if (!detailRootFlg) {
                            detailMsg(certificate.getUserNotice());
                            detailRootFlg = true;
                        }
                        detailCertTabList(detailCertContentList(certificate));
                    }, 'root');
                    break;
            }
            setCACertLinkEvent();

            $("#certVerify_btn").unbind("click");
            $("#certVerify_btn").click(function () {
                validateCertificate(function (result) {
                    $("#dialogCertVid").dialog({
                        autoOpen: true,
                        modal: true,
                        resizable: false,
                        open: function (type, data) {
                            $("#dialogCertVid_text").text(validateMsg(result, _certificate));

                            $(".ui-dialog-titlebar", $(this).parent()).remove();
                            $("#dialogCertVid").dialog({ width: "auto", height: "auto" });
                            $("#dialogCertVid").css('overflow', 'hidden');
                        }
                    });
                })
            });

            $("#certHash_btn").unbind("click");
            $("#certHash_btn").click(function () {
                // alert(jsLang(33));
                rootCAHashOpen();
            });

        });

        detailCertView();
    };

    function detailMsg(msg) {
        $("#dialogCertMsg").dialog({
            autoOpen: true,
            modal: true,
            resizable: false,

            open: function (type, data) {
                $("#dialogCertMsg_text").text(msg);

                $(".ui-dialog-titlebar", $(this).parent()).remove();
                $("#dialogCertMsg").dialog({ width: "auto", height: "auto" });
                $("#dialogCertMsg").css('overflow', 'hidden');
            }
        });

        $("#dialogCertMsg_xBtn").unbind("click");
        $("#dialogCertMsg_xBtn").click(function () {
            $("#dialogCertMsg").dialog("close");
        });

        $("#dialogCertMsg_confirmBtn").unbind("click");
        $("#dialogCertMsg_confirmBtn").click(function () {
            $("#dialogCertMsg").dialog("close");
        });
    };
    function detailCertView() {
        $("#dialogCertTab").dialog({
            autoOpen: true,
            //height: 210,
            //width: 368,
            scroll: "no",
            modal: true,
            resizable: false,
            button: {
                cancel: function () {
                    dialog.dialog("close");
                }
            },
            //closeOnExcape: false,
            close: function () {
            },
            open: function (type, data) {
                $(".ui-dialog-titlebar", $(this).parent()).remove();

                $("#dialogCertTab").dialog({ width: "auto", height: "auto" });
                $("#dialogCertTab").css('overflow', 'hidden');

                $("#dialogCertTab_tab_li1").trigger("click");
            },
            resizeStop: function () {
                $(this).dialog({ height: "auto" });
            }
        });

        $("dialogCertMsg_xBtn").click(function () {
            $("#dialogCertMsg").dialog("close");
        });
    }

    function detailCertBtn(thisId) {
        $("#dialogCertTab_tab_subBtn_div").empty();

        if (thisId == "dialogCertTab_tab_li4") {
            $(document.createElement('p'))
                .attr("class", "CERT_btnBg_sbtn")
                .append($(document.createElement('a'))
                    .attr({ "href": "#", "id": "certHash_btn" })
                    .append(jsLang(34))
                )
                .appendTo($("#dialogCertTab_tab_subBtn_div"));
        }

        if (thisId == "dialogCertTab_tab_li2" || thisId == "dialogCertTab_tab_li1" || thisId == "dialogCertTab_tab_li3") {
            $(document.createElement('p'))
                .attr("class", "CERT_btnBg_sbtn4")
                .append($(document.createElement('a'))
                    .attr({ "href": "#", "id": "certAdd_btn" })
                    .append(jsLang(35))
                )
                .appendTo($("#dialogCertTab_tab_subBtn_div"));
        }

        $(document.createElement('p'))
            .attr("class", "CERT_btnBg_sbtn")
            .append($(document.createElement('a'))
                .attr({ "href": "#", "id": "certVerify_btn" })
                .append(jsLang(36))
            )
            .appendTo($("#dialogCertTab_tab_subBtn_div"));
    };
    function detailCertTabList(args) {
        $("#dialogCertTab_attrList").empty();
        if (args == [] || args == undefined || args == "") return;

        //다이얼로그 출력
        $.each(args, function (index, item) {
            $(document.createElement('li'))
                .addClass('clfix')
                .append($(document.createElement('ul'))
                    .addClass('CERT_list_list clfix')
                    .append($(document.createElement('li'))
                        .append($(document.createElement('p'))
                            .addClass("CERT_bullet")
                            .attr("title", item.arg1)
                            .append(item.arg1)
                        )
                    )
                    .append($(document.createElement('li'))
                        .append(item.arg2)
                    )
                )
                .appendTo($('#dialogCertTab_attrList'));
        })
        for (var i = 0; i < (6 - args.length); i++) {
            $(document.createElement('li'))
                .addClass('clfix')
                .append($(document.createElement('ul'))
                    .addClass('CERT_list_list clfix')
                    .append($(document.createElement('li'))
                        .append($(document.createElement('p'))
                            .addClass("CERT_bullet")
                            //.append(item.arg1)
                        )
                    )
                    .append($(document.createElement('li'))
                        //.append(item.arg2)
                    )
                )
                .appendTo($('#dialogCertTab_attrList'));
        }
        $('#dialogCertTab_attrList li:nth-child(odd) ul').attr("class", "CERT_list_list_ clfix");
    };

    function validateCertificate(callback) {
        vest.token.validateCertificate(_tokenNumber, _selected, undefined, callback, callback);
    };
    function validateMsg(result, certificate) {
        var msg = "";

        msg += jsLang(37) + jsLang(50);
        msg += jsLang(38) + certificate.getIssuer() + jsLang(50);
        msg += jsLang(39) + certificate.getSubject() + jsLang(50);
        msg += jsLang(40) + certificate.getValidityFrom('YYYY-MM-DD') + jsLang(50);
        msg += jsLang(41) + certificate.getValidityTo('YYYY-MM-DD') + jsLang(50);
        msg += jsLang(42) + (result.resultCode == 0 ? jsLang(43) : jsLang(44)) + jsLang(50);
        msg += jsLang(45) + (result.validCode == 999 ? jsLang(46) : result.validCode) + jsLang(50);
        msg += jsLang(47) + (result.validCode == 999 ? jsLang(46) : result.validMessage) + jsLang(50);
        msg += jsLang(48) + jsLang(50);
        if (result.validCode == 11) {
            msg += jsLang(49);
        }

        return msg;
    };
    function pfxValidateMsg(resultCode, certificate) {
        var msg = "";

        var resultMsg;
        if (resultCode == 0) resultMsg = jsLang(140);
        else if (resultCode == -10) resultMsg = jsLang(141);
        else if (resultCode == -20) resultMsg = jsLang(142);
        else if (resultCode == -30) resultMsg = jsLang(143);
        else resultMsg = 'N/A';

        msg += jsLang(37) + jsLang(50);
        msg += jsLang(38) + certificate.getIssuer() + jsLang(50);
        msg += jsLang(39) + certificate.getSubject() + jsLang(50);
        msg += jsLang(40) + certificate.getValidityFrom('YYYY-MM-DD') + jsLang(50);
        msg += jsLang(41) + certificate.getValidityTo('YYYY-MM-DD') + jsLang(50);
        msg += jsLang(42) + (resultCode == 0 ? jsLang(43) : jsLang(44)) + jsLang(50);
        msg += jsLang(45) + (resultCode == 0 ? jsLang(46) : resultCode) + jsLang(50);
        msg += jsLang(47) + resultMsg + jsLang(50);
        msg += jsLang(48) + jsLang(50);
        msg += jsLang(49);

        return msg;
    };

    var getCertListFromSaveToken = function () {
        saveTokenWrapper.hide();
        _keySafer.getPassword(function (tokenPin) {
            getCertificateListFromVestCert(_tokenNumber, tokenPin);
        }, 'tokenPin');
    };
    $("#tokenInputDialog_confirmBtn").unbind("click");
    $("#tokenInputDialog_confirmBtn").click(function () {
        getCertListFromSaveToken();
    });
    $("#tokenPin").keydown(function (key) {
        if (key.keyCode == 13) {
            getCertListFromSaveToken();
        }
    });

    // 저장토큰 핀 입력 dialog
    var tokenPinInputOpen = function (tokenNumber) {
        // $("#tokenInputTitle").text(jsLang(152));
        // $("#infoMsg").text(jsLang(153));
        // $("#pwMsg").text(jsLang(154));

        _tokenNumber = tokenNumber;
        $('#tokenInputDialog_cancelBtn').unbind("click");
        $('#tokenInputDialog_cancelBtn').click(function () {
            saveTokenWrapper.hide();
        });

        $('#tokenInputX_btn').unbind("click");
        $('#tokenInputX_btn').click(function () {
            saveTokenWrapper.hide();
        });

        saveTokenWrapper.show();
        $("#tokenPin").focus();
    };

    var removeLoadingEvent = function () {
        removeTokenEvent();
        submitBtn.unbind('click');
    };

    var addLoadingEvent = function (func) {
        if (typeof (func) === 'function') {
            func();
        }
        addTokenEvent();
        addOkayClickEvent(function () {
            okButtonEvent();
        });
        addEnterEvent();
    };

    // ui click event
    function keyEvent(key, func) {
        if (key.keyCode == 13) {
            func();
            if (_disPersonFlg && (func == setCertificateFile)) {
                $("#certificateFileManaual").focus();
            }
            key.preventDefault();
        }
    };

    function addOkayClickEvent(func) {
        submitBtn.unbind('click').unbind('keydown');
        submitBtn.click(function () {
            try {
                func();
            } catch (error) {
                _preventKeyFlag = true;

                if (typeof (error) === 'string') {
                    alert(error);
                }
            }
        });
        submitBtn.keydown(function (key) {
            keyEvent(key, function () {
                func();
                // key.preventDefault();
            });
        });
    };

    function addEnterEvent() {

        browserCertificatePasswordInput.keydown(function (key) {
            if (key.keyCode == 13) {
                browserCertificatePasswordInput.blur();
            }
        });
        certPin.keydown(function (key) {
            if (key.keyCode == 13) {
                certPin.blur();
            }
        });
    };


    var addDetailDeleteClickEvent = function () {
        detailBtn.unbind('click');
        detailBtn.click(function () {
            detailEvent();
        });
        detailBtn.keydown(function (key) {
            keyEvent(key, function () {
                detailEvent();
            });
        });

        deleteBtn.unbind('click');
        deleteBtn.click(function () {
            // 지금은 pfx, wetStorage 같이처리하지만 나중에 두개 분기시켜야함.
            //if (typeof(_selected) === 'string') deleteEventWebStorage();
            if ((typeof _selected === 'object' && _storageType == "WEB") || _storageType == 'HIBISCUS') {
                browserCertitifcateDelete();
            }
            else deleteEvent();
        });
        deleteBtn.keydown(function (key) {
            keyEvent(key, function () {
                //if (typeof(_selected) === 'string') deleteEventWebStorage();
                if ((typeof _selected === 'object' && _storageType == "WEB") || _storageType == 'HIBISCUS') {
                    browserCertitifcateDelete();
                }
                else deleteEvent();
            });
        });
    };

    var passwordKeyDownEvent = function (key) {
        keyEvent(key, function () {
            if (_preventKeyFlag) {
                _preventKeyFlag = false;
                if (_disPersonFlg) {
                    submitBtn.focus();
                } else {
                    submitBtn.click();
                }
            }
        });
    };
    certPin.keydown(passwordKeyDownEvent);
    pfxPin.keydown(passwordKeyDownEvent);
    browserCertificatePasswordInput.keydown(passwordKeyDownEvent);

    cancelBtn.click(function () {
        cancelEvent(function () {
            if(_config.btnId != undefined && _config.btnId != '')
            {
                $("#" + _config.btnId, parent.document).focus();
            }
            //$('#signBtn', parent.document).focus();
        });
    });

    cancelBtn.keydown(function (key) {
        if (!(key.shiftKey) && key.keyCode == 9) {
            $("[tabindex=1]").focus();
            key.preventDefault();
        }
        keyEvent(key, function () {
            cancelEvent(function () {
                if(_config.btnId != undefined && _config.btnId != '')
                {
                    $("#" + _config.btnId, parent.document).focus();
                }
                //$('#signBtn', parent.document).focus();
            });
        });
    });

    xBtn.click(function () {
        cancelEvent(function () {
            if(_config.btnId != undefined && _config.btnId != '')
            {
                $("#" + _config.btnId, parent.document).focus();
            }
            //$('#signBtn', parent.document).focus();
        });
    });

    xBtn.keydown(function (key) {
        if (!(key.shiftKey) && key.keyCode == 9) {
            $("[tabindex=3]").focus();
            key.preventDefault();
        }
        keyEvent(key, function () {
            cancelEvent(function () {
                if(_config.btnId != undefined && _config.btnId != '')
                {
                    $("#" + _config.btnId, parent.document).focus();
                }
                //$('#signBtn', parent.document).focus();
            });
        });
    });

    function cancelEvent(func) {
        var error = { code: -9999 };
        vest.util.refactoryMsg.convertMsg(error);

        var resultcallback = function () {
            if (typeof (func) === "function") func();
            //$('#closeFocus', parent.frames['yettie_library_iframe'].document).focus();
            $('#closeFocus', parent.document).focus();
            _param.errorcallback(error);
        };
        vest.token.tray("yettie", "off", undefined, resultcallback, resultcallback);
    };

    titleText.keydown(function (key) {
        if (key.shiftKey && key.keyCode == 9) {
            cancelBtn.focus();
            key.preventDefault();
        }
    });

    $(document).on("click", "#dialogCertTab_xBtn, #dialogCertTab_confirmBtn, #dialogCertTab_cancelBtn", function () {
        $("#dialogCertTab").dialog("close");
    });

    $(document).on("click", "#dialogCertVid_xBtn, #dialogCertVid_confirmBtn, #dialogCertVid_cancelBtn", function () {
        $("#dialogCertVid").dialog("close");
    });

    $('.CERT_0104_q').hover(function () {
        $('.CERT_0104_q_pop').show();
    }, function () {
        $('.CERT_0104_q_pop').hide();
    });

    function detailEvent() {
        if (_selected == null) {
            getError(jsLang(5));
            return;
        }
        if (_certificates.length == 0) {
            detailOpen();
        } else if (_storageType == 'WEB') {
            detailPfxOpen(_selected.cert);
        } else {
            detailPfxOpen(_selected);
        }
    };

    $('.CERT_list_list').click(function () {
        $('.CERT_detailbox').text(this.children[1].childNodes[0].nodeValue);
    });

    $(".vestCertSetup").click(function () {
        parent.window.location.href = _config.installFilePath;
    });

    $(".vestCertSetup").keydown(function (key) {
        if (key.shiftKey && key.keyCode == 9) {
            $('[tabindex=' + _lastMenuIndex + ']').focus();
            key.preventDefault();
        }
        keyEvent(key, function () {
            parent.window.location.href = _config.installFilePath;
        });
    });

    $(".refresh").click(function () {
        parent.window.location.href = "Mangowire://";
    });

    $(".refresh").keydown(function (key) {
        if (key.shiftKey && key.keyCode == 9) {
            $('[tabindex=' + _lastMenuIndex + ']').focus();
            key.preventDefault();
        }
        keyEvent(key, function () {
            parent.window.location.href = "Mangowire://";
        });
    });

    var infoPopupPageOpen = function (url, title) {
        var newSize = getPopUpSize();
        window.open(url, title, "width=568px, height=" + newSize.height + ", " + "top=" + newSize.top + ", left=" + newSize.left + ",scrollbars=yes");
    };

    $(".vestCertInfo").click(function () {
        infoPopupPageOpen(_infoInstall, jsLang(93));
    });

    $(".vestCertInfo").keydown(function (key) {
        if (key.shiftKey && key.keyCode == 9) {
            $('[tabindex=' + _lastMenuIndex + ']').focus();
            key.preventDefault();
        }
        keyEvent(key, function () {
            infoPopupPageOpen(_infoInstall, jsLang(93));
        });
    });

    $(".certificateFileManaual").click(function () {
        infoPopupPageOpen(_infoCertFile, jsLang(52));
    });

    $(".certificateFileManaual").keydown(function (key) {
        if (key.shiftKey && key.keyCode == 9) {
            $('[tabindex=' + _lastMenuIndex + ']').focus();
            key.preventDefault();
        }
        keyEvent(key, function () {
            infoPopupPageOpen(_infoCertFile, jsLang(52));
        });
    });

    $('#certImport_btn').click(function () {
        _lastStorage = '';
        parent.yettie.browserCertificateDragAnddropImport('', function (result) {
            setHibiscus();
        }, hibiscusErrorHandler);
    })

    fileInputBtn.keydown(function (key) {
        if (key.keyCode == 9) {
            detailBtn.focus();
            key.preventDefault();
        }
    });

    // 인증서 선택했을때
    function outCertComment(selectedInfo, arg, serial) {
        _selected = selectedInfo;
        _dn = arg;
        _serial = serial;

        statusView.val(arg);

        if (_config.ablePwd && !(pfxPin.prop('readonly') && pfxPin.prop('disabled'))) {
            if (_storageType == 'WEB') {
                pfxPin.val('');
                pfxPin.focus();
            } else {
                if (_storageType == 'HIBISCUS') {
                    browserCertificatePasswordInput.val('');
                    // browserCertificatePasswordInput.focus();
                } else {
                    certPin.val('');
                    certPin.focus();
                }
            }

            if (typeof (_keySafer) !== 'undefined') {
                if (typeof (_keySafer.clearPassword) !== 'undefined') _keySafer.clearPassword();
            }
        } else {
            submitBtn.focus();
        }

        addDetailDeleteClickEvent();
    };

    var showMainInfo = function () {
        mainInfo.show();
        hideP12View();
        hideCertListUI();
    };

    var hideMainInfo = function () {
        mainInfo.hide();
    };

    var showP12View = function () {
        p12View.show();
        hideCertListUI();
        hideMainInfo();
    };

    var showImg = function () {
        imgView.show();
        hideCertListUI();
        hideMainInfo();
    };

    var showFileInputDiv = function () {
        fileInputDiv.show();
    };

    var hideFileInputDiv = function () {
        fileInputDiv.hide();
    };

    function hideP12View() {
        p12View.hide();
    };

    function hideimgView() {
        imgView.hide();
    };

    var showFlashInputDiv = function () {
        flashInputDiv.css("left", "50%");
    };

    var hideFlashInputDiv = function () {
        flashInputDiv.css("left", "-200%");
    };

    var emptyFileInputDiv = function () {
        fileInputDiv.empty();
    };

    var showCertListUI = function () {
        mainCertList.show();
        hideP12View();
        hideimgView();
        hideMainInfo();

    };

    var showBrowserCertificateListUI = function () {
        hideP12View();
        hideimgView();
        hideMainInfo();
        showBrowserCertificatePassword();
        mainCertList.show();
    }

    function hideCertListUI() {
        mainCertList.hide();
    };

    var showContentsBackGround = function () {
        contentsBackGround.show();
    };

    var hideContentBackGround = function () {
        contentsBackGround.hide();
    };

    var disableHibiscusPassword = function () {
        browserCertificatePasswordInput.attr("readonly", true);
        browserCertificatePasswordInput.attr("disabled", true);
    };

    var enableHibiscusPassword = function () {
        browserCertificatePasswordInput.attr("readonly", false);
        browserCertificatePasswordInput.attr("disabled", false);
        browserCertificatePasswordInput.val('');
    };

    var disableCertPassword = function () {
        certPin.attr("readonly", true);
        certPin.attr("disabled", true);
    };

    var enableCertPassword = function () {
        certPin.attr("readonly", false);
        certPin.attr("disabled", false);
        certPin.val('');
    };

    var disableP12Password = function () {
        pfxPin.attr("readonly", true);
        pfxPin.attr("disabled", true);
    };

    var enableP12Password = function () {
        pfxPin.attr("readonly", false);
        pfxPin.attr("disabled", false);
        pfxPin.val('');
    };

    function showBrowserCertificatePassword() {
        certPin.hide();
        pfxPin.hide();
        browserCertificatePasswordInput.val('');
        browserCertificatePasswordInput.show();
        // browserCertificatePasswordInput.focus();
    }

    function showBrowserCertificateImportDiv() {
        hideMainInfo();
        // browserCertificateImportContentBackGround.hide();
        // pwdBackGround.show();
        mainCertList.show();
        getError(jsLang(164));
        showBrowserCertificatePassword();
    }

    var pinTypeCheck = function (password) {
        if (typeof (password) === 'string' && password == '') {
            getError(jsLang(6));
            return -1;
        }
        else if (_keySafer.getType() == "anlabOld" && password.value == '') {
            getError(jsLang(6));
            return -1;
        }
        return 0;
    };

    var pfxPinTypeCheck = function (password) {
        if (typeof (password) === 'string' && password === '') {
            getError(jsLang(6));
            return false;
        }
        return true;
    };

    var getPassword = function (callback) {
        if (_config.ablePwd) {
            if (!_keySafer.initalizeCheck() || _keySafer.getType() == "undefined" || _keySafer.getType() == "ahnlabKeyhook") {
                callback(certPin.val());
            } else {
                // _keySafer.getPassword(callback);
                _keySafer.getPassword(function (password) {
                    callback(password);
                }, 'passwordInput');
            }
        } else {
            callback(undefined);
        }
    };

    var hibiGetPassword = function (password, callback) {
        var pwd, decKeySafer;
        if (typeof password !== "string" && typeof password.value !== "undefined") {
            decKeySafer = vest.decryptKeySafer(password.type, password.config);
            pwd = password.value;
        } else {
            decKeySafer = vest.decryptKeySafer();
            pwd = browserCertificatePasswordInput.val();
        }

        decKeySafer.getDecryptedPassword(pwd, callback);
    };

    function deleteEvent() {    // 인증서 삭제
        var password;
        if (_selected == null) {
            getError(jsLang(5));
            return;
        }

        // if (_config.ablePwd) {
        //     if (pinTypeCheck(password) != 0)
        //         return;
        // }

        if (confirm(jsLang(120)) == true) {
            var deleteOptions = {
                skip: 1
            }
            vest.token.removeFromMedia(_tokenNumber, password, _selected, deleteOptions, function () {
                _preventKeyFlag = true;
                getCertificateListFromVestCert(_tokenNumber);
            }, function (error) {
                vestCertErrorHandler(error);
            });

        }
    };

    // 인증서선택창 확인버튼 이벤트
    function okButtonEvent() {
        var nextEvent = function (password) {
            if (_selected == null) {
                throw jsLang(5);
            }

            if (_isGray == "on") {
                try{
                throw jsLang(136);
                } catch(e) {
                }
            }

            if (_config.ablePwd) {
                if (pinTypeCheck(password) != 0) {
                    throw -1;
                }
            }

            _param.option.caType = _caType.name;

            ytLayer.show();

            //$('#closeFocus', parent.frames['yettie_library_iframe'].document).focus();  // IE에서 텍스트박스에 포커스가 안가는 문제를 해결하기 위해 추가
            if ($('#closeFocus', parent.frames['yettie_library_iframe'].document) != null) {
                $('#closeFocus', parent.frames['yettie_library_iframe'].document).focus();
            } else {
                $('#closeFocus', parent.document).focus();
            }

            _param.option.storageType = _storageType;
            _param.option.serial = _serial;

            if (_config.ablePwd && _config.signTitle == "REVOKE") {
                if (!confirm(jsLang(121))) {
                    ytLayer.hide();
                    throw -1;
                }
            }

            _param.callback(_tokenNumber, _selected, password, _param.option, vestCertErrorHandler);

        };

        getPassword(nextEvent);
    }

    // pfx 확인버튼 이벤트, browserCertificate
    var PFXokButtonEvent = function (certificate, password) {
        if (_selected === undefined || typeof certificate === 'undefined') {
            getError(jsLang(5));
            return;
        }

        if (_config.ablePfxPwd) {
            if (!pfxPinTypeCheck(password))
                return;
        }

        _storageType = vest.signHelper.encodeCharset(_storageType, _param.option.charset);
        _storageType = vest.signHelper.encodeBytes(_storageType, 'base64');

        _param.option.storageType = _storageType;
        _param.option.serial = _serial;
        _param.callback(certificate, _selected, password, _param.option, function (error) {
            _preventKeyFlag = true;
            getError(error);
        });

    };

    var getPFXPassword = function (password, callback) {
        var pwd;
        var decKeySafer;

        if (typeof password !== "string" && typeof password.value !== "undefined") {
            decKeySafer = vest.decryptKeySafer(password.type, password.config);
            pwd = password.value;
        } else {
            decKeySafer = vest.decryptKeySafer();
            pwd = pfxPin.val();
        }

        decKeySafer.getDecryptedPassword(pwd, callback);
    };

    var importCertificate = function () {
        var certificate;
        var password;
        var cert = [];

        _pfxKeySafer.getPassword(function (password) {  // 암호화 or 키후킹 된 비밀번호
            getPFXPassword(password, function (password) {
                if (!_pfx) {
                    getError(jsLang(56));
                    return;
                }

                if (!pfxPinTypeCheck(password))
                    return;

                try {
                    certificate = vest.pki.CertificateSet.fromPFX(_pfx, password);
                } catch (e) {
                    getError(jsLang(57));
                    if (typeof (errorcallback) === 'function') {
                        errorcallback(e.message());
                    }
                }


                if (certificate) {
                    _certificates.push(certificate.getCertificate());
                    cert.push(certificate.getCertificate().toShortCert());

                    disableP12Password();

                    showCertListUI();

                    if (!checkClassPolicies(cert)) {
                        if ($(".certificateListText").length == 0) {
                            addNoCertList();
                        }
                        $("#notCertificateList").text(jsLang(58));
                        statusView.val(jsLang(118));
                        return;
                    }

                    if (_config.serial != undefined && _config.serial != '') {
                        var matchedSerial = [];
                        for (var i = 0; i < cert.length; i++) {
                            if (_config.serial == cert[i].getSerial()) {
                                matchedSerial = [cert[i]];
                            }
                        }
                        outCertList(matchedSerial);
                    } else {
                        outCertList(cert);
                    }

                    hideContentBackGround();
                    if (_selected === undefined) {
                        getError(jsLang(58));
                        setCertificateFile();
                        return;
                    }

                    if (typeof $("#notCertificateList")[0] !== "undefined") {
                        // 만료된 인증서
                        $("#notCertificateList").text(jsLang(54));
                        statusView.val(jsLang(118));
                    }

                    addOkayClickEvent(function () {
                        if (typeof $("#notCertificateList")[0] === "undefined")   // 만료된 인증서가 아니라면
                            PFXokButtonEvent(certificate, password);
                    });
                }
            });
        }, 'hiddenPasswordInput');
    };

    var browserCertificateGetCert = function (sel) {
        for (var i = 0; i < _certificates.length; i++) {
            if (_certificates[i].getSubject() == sel) {
                return _certificates[i];
            }
        }
        return 'undefined';
    }

    var browserCertificate = function () {
        _pfxKeySafer.getPassword(function (encPwd) {  // 암호화 or 키후킹 된 비밀번호
            getPFXPassword(encPwd, function (pwd) {
                hibiscusOkButtonEvent(browserCertificateGetCert(_selected.cert), pwd);
            });
        }, 'hiddenPasswordInput');
    };

    var browserCertificateImport = function (json) {
        var overlepCert = function (certSet, password) {
            var toObject = certSet.toObject({ encoding: 'base64' });
            var newJson = {
                cert: toObject.signCert,
                key: toObject.signPri,
            };

            if (typeof toObject.kmCert !== 'undefined' && typeof toObject.kmPri !== 'undefined') {
                newJson.kmCert = toObject.kmCert;
                newJson.kmKey = toObject.kmPri;
            } else {
                newJson.kmCert = '';
                newJson.kmKey = '';
            }

            vest.token.importCertificate('HIBISCUS', newJson, password, {}, setWebStorage, hibiscusErrorHandler);
        };

        showContentsBackGround();
        statusView.val('');
        pfxPin.attr({ "disabled": false });
        if (!(typeof _pfxKeySafer !== 'undefined' && typeof _pfxKeySafer.disabledFocus === 'function' && _pfxKeySafer.disabledFocus())) {
            pfxPin.focus();
        }

        addOkayClickEvent(function () {
            _pfxKeySafer.getPassword(function (password) {  // 암호화 or 키후킹 된 비밀번호
                getPFXPassword(password, function (password) {
                    if (!pfxPinTypeCheck(password))
                        return;

                    if (typeof json.pfx !== 'undefined') {
                        var certSet;
                        try {
                            certSet = vest.pki.CertificateSet.fromPFX(json.pfx, password);
                        } catch (e) {
                            // setWebStorage(true);
                            getError(jsLang(57));
                            return;
                        }
                        overlepCert(certSet, password);
                    } else if (typeof json.signCert !== 'undefined' && typeof json.signPri !== 'undefined') {
                        var certSet = new vest.pki.CertificateSet.fromString(json);

                        certSet.verifyPin(password, function () {
                            overlepCert(certSet, password);
                        }, function (error) {
                            // setWebStorage(true);
                            vestCertErrorHandler(error);
                        });
                    } else {
                        importBrowserBtn.click();
                        statusView.val(jsLang(118));
                        pfxPin.val('');
                    }

                    hideContentBackGround();
                });
            }, 'hiddenPasswordInput');
        });
    };

    var browserCertificateImportFlash = function (files) {
        var json = {};
        json.pfx = files;
        browserCertificateImport(json);
    }

    var browserCertificateImportPFX = function (files) {
        var reader = new FileReader();
        var json = {};
        var tmpName = (files[0].name).substr((files[0].name).length - 4, (files[0].name).length);

        reader.onload = function (theFile) {
            if (tmpName == ".pfx" || tmpName == ".PFX" || tmpName == ".p12" || tmpName == ".P12") {
                json.pfx = theFile.target.result;
                browserCertificateImport(json);
            } else {
                getError(new vest.error(2300));
            }
        };

        reader.readAsArrayBuffer(files[0]);
    };

    var browserCertificateImportNPKI = function (files, indexParam, jsonParam) {
        var reader = new FileReader();
        var state = 'none';
        var json = (typeof jsonParam !== 'undefined' || typeof jsonParam === 'object') ? jsonParam : {};
        var index = (typeof indexParam !== 'undefined') ? indexParam : 0;

        if (typeof index !== 'undefined' && index >= files.length) {
            if (typeof json.signCert !== 'undefined' && typeof json.signPri !== 'undefined') {
                browserCertificateImport(json);
            } else {
                getError(new vest.error(2302));
            }
        } else {
            state = files[index].name;
            reader.readAsArrayBuffer(files[index]);
        }

        reader.onload = function (theFile) {
            index++;
            if (state.toLowerCase() == 'signcert.der') {
                json.signCert = vest.signHelper.encodeArrayBuffer(theFile.target.result, 'hex');
            } else if (state.toLowerCase() == 'signpri.key') {
                json.signPri = vest.signHelper.encodeArrayBuffer(theFile.target.result, 'hex');
            } else if (state.toLowerCase() == 'kmcert.der') {
                json.kmCert = vest.signHelper.encodeArrayBuffer(theFile.target.result, 'hex');
            } else if (state.toLowerCase() == 'kmpri.key') {
                json.kmPri = vest.signHelper.encodeArrayBuffer(theFile.target.result, 'hex');
            } else {
                // error
            }

            browserCertificateImportNPKI(files, index, json);
        };
    };

    var browserCertificateImportAddEvent = function () {
        browserCertificateImportPFXBtn.unbind('click')
            .bind('click', function () {
                browserCertificateImportPFXFile.val('');
                browserCertificateImportPFXFile.change(function (evt) {
                    browserCertificateImportPFX(evt.target.files);
                });
                browserCertificateImportPFXFile.click()
            });
        browserCertificateImportNPKIBtn.unbind('click')
            .bind('click', function () {
                browserCertificateImportNPKIFile.val('');
                browserCertificateImportNPKIFile.change(function (evt) {
                    browserCertificateImportNPKI(evt.target.files);
                });
                browserCertificateImportNPKIFile.click();
            });

        browserCertificateImportPFXBtn.unbind('dragenter dragover drop')
            .bind('dragenter dragover', false)
            .bind('drop', function (evt) {
                evt.stopPropagation();
                evt.preventDefault();
                browserCertificateImportPFX(evt.originalEvent.dataTransfer.files);
            });
        browserCertificateImportNPKIBtn.unbind('dragenter dragover drop')
            .bind('dragenter dragover', false)
            .bind('drop', function (evt) {
                evt.stopPropagation();
                evt.preventDefault();
                browserCertificateImportNPKI(evt.originalEvent.dataTransfer.files);
            });
        browserCertificateImportInfo.unbind('click')
            .bind('click', function (e) {
                infoPopupPageOpen(_infoCertFile, jsLang(52));
            });
    };

    var browserCertitifcateDelete = function () {
        var certificate = browserCertificateGetCert(_selected.cert);
        _hibiKeySafer.getPassword(function (encPwd) {  // 암호화 or 키후킹 된 비밀번호
            hibiGetPassword(encPwd, function (password) {
                if (_selected === undefined || typeof certificate === 'undefined') {
                    getError(jsLang(5));
                    return;
                }

                if (!pfxPinTypeCheck(password))
                    return;

                if (confirm(jsLang(120)) == true) {
                    if (_config.yessign.use) {
                        if (_selected.cert.substr(_selected.cert.length - 1) == 5) {
                            vest.token.openCertRemoveP12(_selected.fp, {}, function (result) {
                                certificateListFromHibiscus();
                            }, hibiscusErrorHandler);
                        } else if (_selected.cert.substr(_selected.cert.length - 1) == 1) {
                            vest.token.verifyPin('HIBISCUS', _selected, password, function () {
                                vest.token.openCertGetFP(_selected, {}, function (fp) {
                                    vest.token.removeFromMedia('HIBISCUS', password, _selected, setWebStorage, function (result) {
                                        vest.token.openCertRemoveP12(fp, {}, function (result) {
                                            certificateListFromHibiscus();
                                            _hibiscusSignErrCnt = 0;
                                        }, hibiscusErrorHandler);
                                    }, hibiscusErrorHandler);
                                }, hibiscusErrorHandler);
                            }, hibiscusErrorHandler);
                        }
                    } else {
                        vest.token.verifyPin('HIBISCUS', _selected, password, function () {
                            vest.token.removeFromMedia('HIBISCUS', password, _selected, setWebStorage, function (result) {
                                document.body.appendChild(loading);
                                certificateListFromHibiscus();
                                _hibiscusSignErrCnt = 0;
                            }, hibiscusErrorHandler);
                        }, hibiscusErrorHandler);
                    }
                }
            })
        }, 'browserCertificatePasswordInput');
    };

    var addFileEvent = function () {
        var dropZone,
            fileSelect,
            btnKeyDown;

        dropZone = p12View;
        fileSelect = fileInputHidden;
        btnKeyDown = fileInputBtn;

        btnKeyDown.keydown(function (key) {
            keyEvent(key, function () {
                fileSelect.click();
            });
        });

        fileSelect.val('');
        fileSelect.prop('disabled', false);

        dropZone.bind('dragenter dragover', false)
            .bind('drop', handleDrop);
        fileSelect.change(handleFileSelect);
    };

    var removeFileEvent = function () {
        var dropZone,
            fileSelect;

        dropZone = p12View;
        fileSelect = fileInputHidden;

        fileSelect.prop('disabled', true);

        dropZone.unbind('dragenter dragover')
            .unbind('drop');
        fileSelect.unbind('change');
    };

    var readFile = function (file) {
        var reader = new FileReader();

        reader.onload = function () {
            _pfx = reader.result;
            showContentsBackGround();
            removeFileEvent();

            if (!(typeof _pfxKeySafer !== 'undefined' && typeof _pfxKeySafer.disabledFocus === 'function' && _pfxKeySafer.disabledFocus())) {
                pfxPin.focus();
            }
        };

        reader.readAsArrayBuffer(file);
    };

    var handleFileSelect = function (evt) {
        var files = evt.target.files;
        readFile(files[0]);
    };

    var handleDrop = function (evt) {
        evt.stopPropagation();
        evt.preventDefault();

        var files = evt.originalEvent.dataTransfer.files;
        readFile(files[0]);
    };

    var addFlash = function (element) {
        var objectTag = $(doc.createElement('object'))
            .attr({
                //'data': _config.baseUrl + './library/VestSign.swf',
                'data': _config.baseUrl + '../../library/VestSign.swf',
                'type': 'application/x-shockwave-flash',
                'name': 'flash_object',
                'id': 'flash_object'
            })
            //.append($('<param name="movie" value=""/>').val(_config.baseUrl + "./library/VestSign.swf"))
            .append($('<param name="movie" value=""/>').val(_config.baseUrl + "../../library/VestSign.swf"))
            .append('<param name="allowScriptAccess" value="always"/>');
        //flashInputDiv.append(objectTag);
        element.append(objectTag);
    };

    var readFileFlash = function (data) {
        if (_storageType == "WEB") {
            browserCertificateImportFlash(vest.signHelper.decodeString(data, 'base64'));
        }
        else if (false) {
            // certificateFile  // _storageType ==  "CERT FILE"
        } else {
            // error 무반응
        }
    }

    var vestCertErrorHandler = function (error) {
        _preventKeyFlag = true;
        ytLayer.hide();
        switch (error.code) {
            case vest.token.ERROR.NOT_LOAD:
                showMainInfo();
                break;
            case vest.token.ERROR.LOST_CONNECTION:
                showMainInfo();
                break;
            case 12021:
                getError(jsLang(60) + jsLang(50) + jsLang(61) + ' [' + error.code + ']');
                break;
            case 12045:
                getError(jsLang(63) + jsLang(50) + jsLang(64) + ' [' + error.code + ']');
                break;
            case 12046:
                getError(jsLang(63) + jsLang(50) + jsLang(64) + ' [' + error.code + ']');
                break;
            case 12026:
                getError(jsLang(62) + ' [' + error.code + ']');
                break;
            case 12030:
                getError(jsLang(65) + jsLang(50) + jsLang(66) + ' [' + error.code + ']');
                break;
            case 11002:
                getError(jsLang(65) + jsLang(50) + jsLang(66) + ' [' + error.code + ']');
                break;
            case 11003:
                getError(jsLang(67));
                window.open(_config.infovine.url, jsLang(76), 'menubar=no');
                break;
            case 11004:
                getError(jsLang(68));
                window.open(_config.infovine.url, jsLang(76), 'menubar=no');
                break;
            case 21101: //모비사인 설치 페이지
                getError(jsLang(145));
                mobisignPopup();
                break;
            case 21102: //모비사인 버전이 낮음
                getError(jsLang(146));
                mobisignPopup();
                break;
            case 1100:  //dream 설치페이지
                getError(jsLang(148));
                usimPopup();
                break;
            default:
                getError(error);
        }
        addLoadingEvent();
    };

    var certFilter = function (select) {
        if (_config.OIDFilter == 0) {    // 스크립트에서 필터링
            select.certificateClass = undefined;
            select.OID = undefined;
            select.issuerFilter = undefined;
        } else {
            select.certificateClass = _config.certificateClass;
            select.OID = _config.OID;
            select.issuerFilter = _config.issuerFilter;
        }
    };

    var getCertificateListFromVestCert = function (tokenNumber, tokenPin) {
        var options = {};
        options.pkitype = _config.useGPKI;
        options.secureDiskList = _config.storage.secureDiskRestore;

        var selectedFilter = {};
        certFilter(selectedFilter);

        _tokenNumber = tokenNumber;
        removeLoadingEvent();

        showCertListUI();

        vest.token.getCertificates(tokenNumber, tokenPin, selectedFilter.certificateClass, selectedFilter.OID, selectedFilter.issuerFilter, options, function (certificates) {
            if (_param.option.serial != undefined && _param.option.serial != '') {
                var matchedSerial = [];
                for (var i = 0; i < certificates.length; i++) {
                    if (_param.option.serial == certificates[i].getSerial()) {
                        matchedSerial = [certificates[i]];
                    }
                }
                outCertList(matchedSerial);
            } else if ((_param.option.updateDN != undefined && _param.option.updateDN != '') || (_param.option.updateSerial != undefined && _param.option.updateSerial != '')) {
                var matchedCert = [];
                for (var i = 0; i < certificates.length; i++) {
                    if ((_param.option.updateDN == certificates[i].getSubject()) && (_param.option.updateSerial == certificates[i].getSerial())) {
                        matchedCert = [certificates[i]];
                    } else if (_param.option.updateDN == certificates[i].getSubject()) {
                        matchedCert = [certificates[i]];
                    } else if (_param.option.updateSerial == certificates[i].getSerial()) {
                        matchedCert = [certificates[i]];
                    }
                }
                outCertList(matchedCert);
            } else {
                outCertList(certificates);
            }

            if (_disPersonFlg) {
                if ($("#outCertList li ul").length > 0)
                    $("#outCertList li ul")[0].focus();

                if (_firstLoading) {
                    $("[tabindex=1]").focus();
                    _firstLoading = false;
                }
            }

            addOkayClickEvent(okButtonEvent);

            addLoadingEvent();
        }, vestCertErrorHandler);
    };

    $(document).on("click", "#dialog_xBtn", function () {
        $("#dialog").dialog("close");
    });

    // 다중 드라이브 선택창
    var dOpen = function (args) {

        var sIndex = $("#menu_btn li").find(".on").parent().index();
        var sClass = $("#menu_btn li:eq(" + sIndex + ") a").attr("class").replace("_on on", "");

        $("#dialog").dialog({
            autoOpen: true,
            modal: true,
            resizable: false,
            open: function (type, data) {
                $("#dialog_list").empty();

                //다이얼로그 출력
                $.each(args, function (index, item) {
                    $(document.createElement('li'))
                        .append($(document.createElement('a'))
                            .attr({
                                "href": "javascript:;",
                                "class": "ico1",
                                "tokenIdentifier": item.tokenIdentifier,
                                // "SecureTokenIdentifier": item.SecureTokenIdentifier,
                                "tokenKey": item.tokenKey
                                // "onClick": "$(\"#dialog\").dialog(\"close\");"
                            })
                            // .append(item.name)
                            .append(typeof item.viewName !== 'undefined' ? item.viewName : item.name)
                        )
                        .appendTo($("#dialog_list"));
                })

                if (args.length > 3) $("#dialog_list").css("height", "128px");
                else $("#dialog_list").css("height", (args.length * 32) + "px");


                $(".ui-dialog-titlebar", $(this).parent()).remove();
                $("#dialog").dialog({ width: "auto", height: "auto" });
                if (args.length < 4) $("#dialog").dialog({ height: $("#dialog").height() - 8 });
                $("#dialog").css('overflow', 'hidden');
            }
        });
    };

    function mobisignPopup() {
        window.open(_config.mobisign.url, jsLang(147), "width=466px, height=438px, left=10px, top=10px");
    };

    function usimPopup() {
        window.open(_config.dreamsecurity.url, jsLang(149), "width=500px, height=380px, left=10px, top=10px");
    };

    var checkSerialDrive = function (list) {
        var serialDrive = [];

        for (var key in list) {
            if (list[key].name == _driveName) {
                serialDrive.push(list[key]);
            }
        }
        return serialDrive;
    };

    var getViewName = function (list) {
        var viewName;

        if (list.name.indexOf('Infovine Ubikey') != -1) {
            viewName = jsLang(150);
        } else if (list.name.indexOf('Mobile_SmartCert') != -1) {
            viewName = jsLang(151);
        } else {
            // viewName = 'undefined';
        }
        return viewName;
    };

    var makeDownMenu = function (list) {
        var result = [];
        var repStr;

        // 다국어 처리 하면 안됨.
        // 한글 하드디스크에서 생성한 이동식드라이브일 경우 이동식 표기를 해야하기 때문.
        for (var i = 0; i < list.length; i++) {
            if (list[i].name.split(" -")[0] == '하드디스크') {
                repStr = list[i].name;
                repStr = repStr.replace('하드디스크', jsLang(1));
            } else if (list[i].name.split(" -")[0] == '이동식디스크') {
                repStr = list[i].name;
                repStr = repStr.replace('이동식디스크', jsLang(1));
            } else if (list[i].name.split(" -")[0] == '네트워크 드라이브') {
                repStr = list[i].name;
                repStr = repStr.replace('네트워크 드라이브', jsLang(159));
            } else {
                repStr = undefined;
            }

            result.push({
                viewName: getViewName(list[i]),  // 휴대폰인증 매체 한글처리
                name: (repStr !== undefined) ? repStr : list[i].name,
                tokenIdentifier: list[i].tokenIdentifier,
                tokenKey: list[i].name
            });
        }

        return result;
    };

    var addStorageType = function (driveName) {
        _storageType += driveName;
        _storageType = vest.signHelper.encodeCharset(_storageType, _param.option.charset);
        _storageType = vest.signHelper.encodeBytes(_storageType, 'base64');
    };

    var selectSubMenu = function (type) {
        $('#dialog_list li a').unbind('click');
        $("#dialog_list li a").click(function () {
            $("#dialog").dialog("close");

            addStorageType($(this).attr("tokenKey"));

            if (type == vest.token.TYPE.SAVETOKEN) {
                tokenPinInputOpen($(this).attr("tokenIdentifier"));
            } else {
                getCertificateListFromVestCert($(this).attr("tokenIdentifier"));
            }
        });
    };

    var usimEvent = function (tokenIdentifier) {
        _tokenNumber = tokenIdentifier;
        _selected = { cert: "", key: "" };
        certPin.val('Mobile_SmartCert');

        okButtonEvent();
    };

    var setPhoneOption = function (tokenIdentifier, option, func) {
        vest.token.setTokenOptions(tokenIdentifier, option, function () {
            if ($("#dialog").css('display') != 'none') {
                $("#dialog").dialog("close");
            }
            func(tokenIdentifier);
        }, vestCertErrorHandler);
    };

    var isSelectDrive = function (type, list, msgNum) {
        // 리스트 하나일때 선택창을 띄울지 안띄울지 결정.
        if (!_config.listsViewDopen) {   //  드라이브 선택창 안띄움
            addStorageType(list[0].name);
            if (type == vest.token.TYPE.SAVETOKEN) {
                tokenPinInputOpen(list[0].tokenIdentifier);
            } else {
                getCertificateListFromVestCert(list[0].tokenIdentifier);
            }
        } else {    // 드라이브 선택창 띄움
            $('#dialog h1').text(jsLang(msgNum));
            dOpen(makeDownMenu(list));

            if (type == vest.token.TYPE.SAVETOKEN) {
                selectSubMenu(type);
            } else {
                selectSubMenu();
            }
        }
    };

    var getTokenListFromVestCert = function (type) {
        removeLoadingEvent();
        statusView.val('');
        certPin.val('');

        _storageType = type;
        _serial = '';

        // 드림USIM인증 매체로 사용시 휴대폰인증에 표시 X
        if (_config.storage.dreamService == true) {
            _config.dreamsecurity.enable = false;
        }

        vest.token.getTokenList(type, function (list) {
            var phoneEnable = function () {
                if (type == vest.token.TYPE.PHONE) {
                    // dream enable 옵션값이 true일때
                    if (_config.dreamsecurity.enable) {
                        var flag = false;
                        for (var i = 0; i < list.length; i++) {
                            if (list[i].type == "Mobile USIM")
                                flag = true;
                        }

                        if (!flag) {
                            list.push({
                                type: 'Mobile USIM',
                                name: 'Mobile_SmartCert'
                            });
                        }
                    }
                }
            };

            var phoneDisable = function () {
                if (type == vest.token.TYPE.PHONE) {
                    for (var i = 0; i < list.length; i++) {
                        if (!_config.dreamsecurity.enable && list[i].type == "Mobile USIM") list.splice(i, 1);
                        if (!_config.mobisign.enable && list[i].type == "MobiSign") list.splice(i, 1);
                    }
                }
            };

            if (_driveName != undefined && _driveName != '') {
                list = checkSerialDrive(list);
            }

            phoneEnable();
            phoneDisable();

            if (list.length == 0) {
                switch (type) {
                    case vest.token.TYPE.SYSTEM:
                        getError(jsLang(69));
                        break;
                    case vest.token.TYPE.LOCALDISK:
                        getError(jsLang(70));
                        break;
                    case vest.token.TYPE.TOKEN:
                        getError(jsLang(71));
                        break;
                    case vest.token.TYPE.SAVETOKEN:
                        getError(jsLang(72));
                        break;
                }
            }
            else if (list.length == 1) {  // list.length 1개 일 때
                if ((type == vest.token.TYPE.LOCALDISK) && !(_driveName != undefined && _driveName != '')) {  // 시리얼API 아니고
                    isSelectDrive(type, list, 134);
                } else if ((type == vest.token.TYPE.TOKEN) && !(_driveName != undefined && _driveName != '')) {
                    isSelectDrive(type, list, 103);
                } else if ((type == vest.token.TYPE.SAVETOKEN) && !(_driveName != undefined && _driveName != '')) {
                    isSelectDrive(type, list, 155);
                } else if (type == vest.token.TYPE.PHONE) {
                    var optionConfig;
                    ytLayer.show();

                    if (list[0].type == "Mobile USIM") {
                        if (typeof list[0].tokenIdentifier === 'undefined') {
                            vestCertErrorHandler({ code: 1100 });
                        } else {
                            optionConfig = _config.dreamsecurity;
                            setPhoneOption(list[0].tokenIdentifier, optionConfig, usimEvent);
                        }
                    } else if (list[0].type == "Ubikey") {
                        optionConfig = _config.infovine;
                        setPhoneOption(list[0].tokenIdentifier, optionConfig, getCertificateListFromVestCert);
                    } else if (list[0].type == "MobiSign") {
                        optionConfig = _config.mobisign;
                        setPhoneOption(list[0].tokenIdentifier, optionConfig, usimEvent);
                    } else {
                        ytLayer.hide();
                        // getError('지원되지 않는 인증 서비스 입니다.');
                    }
                } else {
                    addStorageType(list[0].name);
                    getCertificateListFromVestCert(list[0].tokenIdentifier);
                }
            }
            else {  // list.length 2개 이상 일 때
                if (type == vest.token.TYPE.LOCALDISK) {
                    $('#dialog h1').text(jsLang(134));
                    dOpen(makeDownMenu(list));

                    selectSubMenu();
                } else if (type == vest.token.TYPE.TOKEN) {
                    $('#dialog h1').text(jsLang(103));
                    dOpen(makeDownMenu(list));

                    selectSubMenu();
                } else if (type == vest.token.TYPE.SAVETOKEN) {
                    $('#dialog h1').text(jsLang(155));
                    dOpen(makeDownMenu(list));

                    selectSubMenu(type);
                } else if (type == vest.token.TYPE.PHONE) {
                    $('#dialog h1').text(jsLang(135));
                    dOpen(makeDownMenu(list));

                    $('#dialog_list li a').unbind('click');
                    $("#dialog_list li a").click(function () {
                        var serviceName = $(this).html().split(':')[0];
                        var selectedTokenIdentifier = $(this).attr("tokenIdentifier");
                        var optionConfig;
                        ytLayer.show();

                        if (serviceName == "Mobile_SmartCert" || serviceName == jsLang(151)) {
                            if (typeof selectedTokenIdentifier === 'undefined') {
                                vestCertErrorHandler({ code: 1100 });
                            } else {
                                optionConfig = _config.dreamsecurity;
                                setPhoneOption(selectedTokenIdentifier, optionConfig, usimEvent);
                            }
                        } else if (serviceName == "Infovine Ubikey Token" || serviceName == jsLang(150)) {
                            optionConfig = _config.infovine;
                            setPhoneOption(selectedTokenIdentifier, optionConfig, getCertificateListFromVestCert);
                        } else if (serviceName == "MobiSign Token") {
                            optionConfig = _config.mobisign;
                            setPhoneOption(selectedTokenIdentifier, optionConfig, usimEvent);
                        } else {
                            ytLayer.hide();
                            // getError('지원되지 않는 인증 서비스 입니다.');
                        }
                    });
                } else {

                }
            }

            addLoadingEvent();
        }, vestCertErrorHandler);
    };

    var getBackupedTokenListFromVestCert = function (type) {
        removeLoadingEvent();
        statusView.val('');
        certPin.val('');

        _storageType = type;
        _serial = '';

        vest.token.getBackupedTokenList(type, function (list) {

            if (_driveName != undefined && _driveName != '') {
                list = checkSerialDrive(list);
            }

            if (list.length == 0) {
                switch (type) {
                    case vest.token.TYPE.LOCALDISK:
                        getError(jsLang(70));
                        break;
                }
            } else if (list.length == 1) {  // list.length 1개 일 때
                if ((type == vest.token.TYPE.LOCALDISK) && !(_driveName != undefined && _driveName != '')) {  // 시리얼API 아니고
                    isSelectDrive(type, list, 134);
                }
            } else {  // list.length 2개 이상 일 때
                if (type == vest.token.TYPE.LOCALDISK) {
                    $('#dialog h1').text(jsLang(134));
                    dOpen(makeDownMenu(list));

                    selectSubMenu();
                }
            }

            addLoadingEvent();
        }, vestCertErrorHandler);
    };

    var vestCertVersion = function (func) {
        vest.token.getVersion(function (versions) {
            if (vest.util.certVersion.versionCheck(vest.util.certVersion.getVersion('VestCert', versions), _config.version.vestCert)) {
                vest.token.CloseSessionAndLogoutWithP11(function () {
                }, function () {
                });
                vest.token.tray("yettie", "on", undefined, function () {
                }, vestCertErrorHandler);
                func();
            } else {
                vest.token.unload();
                addLoadingEvent(function () {
                    showMainInfo();
                    setTimeoutVestCert(func);
                });
            }
        }, function (err) {
            vest.token.unload();
            vestCertErrorHandler(err);
            addLoadingEvent(function () {
                showMainInfo();
                setTimeoutVestCert(func)
            });
        });
    };

    var setTimeoutVestCert = function (func) {
        if (_isLoad) {
            clearTimeout(_isLoad);
        }

        _isLoad = setTimeout(function () {
            vestCertVersion(func);
        }, 5000);
    };

    // hibiscus
    var certificateListFromHibiscus = function (domain) {
        if (!_config.ablePwd) {
            // 비활성화
            browserCertificatePasswordInput.attr({ "disabled": true });
        } else {
            browserCertificatePasswordInput.attr({ "disabled": false });
        }

        if (_config.yessign.use) {
            //certificateListFromHibiscus();
            if (_interval != undefined)
                clearInterval(_interval);
            _interval = setInterval(function () {
                if (_lastStorage != vest.token.TYPE.HIBISCUS) {
                    clearInterval(_interval);
                } else {
                    vest.token.openCertSetEvent({}, function (res) {
                        if (res.status != _opencertStatus) {
                            _opencertStatus = res.status;
                            certificateListFromHibiscus();
                        }
                    })
                }
            }, 1000)
        }

        vest.token.getCertificates(vest.token.TYPE.HIBISCUS, {}, "", _config.OID, undefined, {}, function (certList) {
            _tokenNumber = vest.token.TYPE.HIBISCUS;
            var _outCertList = [];
            var tempList = [];
            var index;
            addTokenEvent();
            if (_config.yessign.use) { // 금결원 공동저장소
                vest.token.getCertInfos({}, function (res) {
                    // if (typeof res === 'undefined') res = [];
                    vest.token.openCertSync(res, _config.yessign.mode, function (localCertInfos) {
                        for (var i = 0; i < localCertInfos.length; i++) {
                            if (localCertInfos[i].getSource() == 'OPENCERT') {
                                _outCertList.push(localCertInfos[i]);
                            } else {
                                for (var j = 0; j < certList.length; j++) {
                                    if (certList[j].getSubject() == localCertInfos[i].getSubject() && (certList[j].getSerial() == localCertInfos[i].getSerial() || localCertInfos[i].getSerial() == '')) {
                                        _outCertList.push(certList[j]);
                                    }
                                }
                            }
                        }
                        if(_config.OID != "0")
                        {
                        var oidFilter = [];
                        if (typeof _config.OID !== 'undefined' && _config.OID != null) {
                            var oids = _config.OID.split(';');
                            oids.forEach(function (ele) {
                                if (ele.indexOf('1.2.410.') == -1) oidFilter.push('1.2.410.' + ele);
                                else oidFilter.push(ele);
                            });
                        }
                        for(var i=0; i<oidFilter.length; i++)
                        {
                            for (var j=0; j<_outCertList.length; j++)
                            {
                                if(oidFilter[i] == _outCertList[j].getPolicy())
                                {
                                    tempList.push(_outCertList[j]);
                                }
                            }
                        }
                        outCertList(tempList);
                    } else {
                        outCertList(_outCertList);
                    }
                        checkCertList();
                    }, hibiscusErrorHandler);
                }, hibiscusErrorHandler);
            } else {
                outCertList(certList);
                checkCertList();
                document.body.removeChild(loading);
            }
        }, hibiscusErrorHandler)
    }

    function hibiscusOkButtonEvent() {
        // var nextEvent = function (password) {
        if (_selected == null) {
            throw jsLang(5);
        }

        if (_isGray == "on") {
            throw jsLang(136);
        }

        // if (_config.ablePfxPwd) {
        //     if (!pfxPinTypeCheck(password))
        //         return;
        // }
        var param = params.getParameters();

        _tokenNumber = vest.token.TYPE.HIBISCUS;
        // _config.kos_var.matchedTokenNumber = _tokenNumber;
        // _config.kos_var.matchedIdentifier = _selected;
        // _config.kos_var.matchedList = [];

        var _option = param.option;
        //_option.selectedToken = _lastStorage;
        _storageType = vest.signHelper.encodeBytes(_storageType, 'base64');
        _option.storageType = _storageType;
        _option.serial = _serial;
        _option.selectedToken = vest.token.TYPE.HIBISCUS;
        _option.tokenName = _dn;
        // _option.charset = 'euc-kr';
        // _option.encoding = 'base64';
        _hibiKeySafer.getPassword(function (pin) {
            hibiGetPassword(pin, function (pin) {
                _param.callback(vest.token.TYPE.HIBISCUS, _selected, pin, param.option, hibiscusErrorHandler);
            })

        }, 'browserCertificatePasswordInput');


        // };

        // hibiGetPassword(nextEvent);
    };

    function checkCertList() {
        if (document.getElementById('outCertList').childElementCount == 0) {
            hideCertListUI();
            showBrowserCertificateImportDiv();
            browserCertificateImportAddEvent();
        } else {
            showBrowserCertificateListUI();
            addLoadingEvent();
            addOkayClickEvent(function () {
                hibiscusOkButtonEvent();
            });
        }
    }

    var hibiscusErrorHandler = function (error) {
        var hibiErrorMessage;
        var msg;
        _preventKeyFlag = true;
        if (typeof error === 'object') {
            msg = vest.error.getErrorMessage(error.error, _config.language);
            if (error.error == 3207044 || error.error == 3213107) {   // 비밀번호 에러시 횟수 제한
                _hibiscusSignErrCnt++;
                hibiErrorMessage = jsLang(62) + ' - ' + jsLang(119) + '(' + _hibiscusSignErrCnt + ')';
            } else if(error.error == 43484502){
                hibiErrorMessage = alert(jsLang(169));
                _parent.close();
            }
            else {
                hibiErrorMessage = msg;
            }

            statusView.val(hibiErrorMessage);
            if (_config.signErrMaxValue > 0 && _hibiscusSignErrCnt == _config.signErrMaxValue) {
                error.dn = _dn; // 해당 경우에만 마지막 dn 반환
                _param.errorcallback(vest.error(error.error, hibiErrorMessage));
            }
            browserCertificatePasswordInput.val('');

        } else {
            alert(error);
        }
    };

    // menu
    var init = function () {
        _pfx = null;
        _certificates = [];
        _selected = null;
        clearTimeout(_isLoad);
        addOkayClickEvent(function () {
        });

        addDetailDeleteClickEvent();

        $('#outCertList').empty();

        showCertListUI();
        removeCertList();
        addNoCertList();
        statusView.val('');

        enableCertPassword();
        enableP12Password();

        // 비밀번호 활성화 여부
        if (!_config.ablePwd) certPin.attr({ "disabled": true });
        if (!_config.ablePfxPwd) pfxPin.attr({ "disabled": true });

        // pinForm.show();
        certPin.show();
        pfxPin.hide();
        browserCertificatePasswordInput.hide();

        // 인증서 리스트 크기 초기화, 브라우저 인증서때문에 크기 줄렸음.
        //subCertList.css('height', '132px');
        subCertList.attr('class', 'cert01_02');
        importBrowserBtn.css('display', 'none');
        importBrowserView.hide();
        mainCertList.show();

        hideContentBackGround();


    };


    var removeTokenEvent = function () {
        $('#hard_disk').unbind('click').unbind('keydown');
        $('#usb_disk').unbind('click').unbind('keydown');
        $('#secure_token').unbind('click').unbind('keydown');
        $('#save_token').unbind('click').unbind('keydown');
        $('#certificate_file').unbind('click').unbind('keydown');
        $('#secure_disk').unbind('click').unbind('keydown');
        $('#phone_certification').unbind('click').unbind('keydown');
        $('#webStorage').unbind('click').unbind('keydown');
        $('#hibiscus_storage').unbind('click').unbind('keydown');

        $('#secure_disk_restore').unbind('click').unbind('keydown');
        $('#dream_service').unbind('click').unbind('keydown');
        if (_config.advertising.enable) {
            for (var i = 0; i < _config.advertising.usetab.length; i++) {
                $('#' + _config.advertising.usetab[i][0]).unbind('click').unbind('keydown');
            }
        }
    };

    var disableMenu = function (menu) {
        menu.addClass('none');
        menu.unbind('click');

        menu.attr({ tabindex: -1 });
    };

    var setHibiscus = function () {
        if(!_config.yessign.use)
        {
            loading = document.createElement('div');
            loading.id = 'yettie_opencert_loading';
            loading.style.position = 'absolute';
            loading.style.left = '0';
            loading.style.right = '0';
            loading.style.top = '0';
            loading.style.bottom = '0';
            loading.style.zIndex = '10000';
            loading.style.background = 'url(' + '../styles/images/loading-animation-6.gif) no-repeat center center';

            document.body.appendChild(loading);
        }
        certPin.hide();
        addImportBtn();
        removeLoadingEvent();
        init();
        passwordText.text(jsLang(88));
        pinHint.html(jsLang(89));

        if (_config.serverStorage.hibiscus.use) {
            if (_config.yessign.use && _load == false) {
                setOpenCertLib();
            } else if (!_config.yessign.use) _load = true;
        }

        _storageType = "HIBISCUS";
        //_lastStorage = vest.token.TYPE.HIBISCUS;
        
        certificateListFromHibiscus();
    }

    var setHardDisk = function () {
        init();
        removeLoadingEvent();
        removeImportBtn();
        passwordText.text(jsLang(88));
        pinHint.html(jsLang(89));
        vestCertVersion(function () {
            getTokenListFromVestCert(vest.token.TYPE.SYSTEM);
        });
        _lastStorage = vest.token.TYPE.SYSTEM;
    };

    var setUsbDisk = function () {
        init();
        removeLoadingEvent();
        removeImportBtn();
        passwordText.text(jsLang(88));
        pinHint.html(jsLang(89));
        vestCertVersion(function () {
            getTokenListFromVestCert(vest.token.TYPE.LOCALDISK);
        });
        _lastStorage = vest.token.TYPE.LOCALDISK;
    };

    // 광고 이미지 탭
    var setadvertising = function () {
        if (_config.advertising.enable) {
            init();
            removeImportBtn();
            hideContentBackGround();
            passwordText.text(jsLang(88));
            pinHint.html(jsLang(89));

            if(_config.advertising.taburlCheck == true)
            {
                var id = $(this).attr("id");
                for (var i = 0; i < _config.advertising.usetab.length; i++) {
                    if (_config.advertising.usetab[i][0] == id) {
                if (_config.advertising.siteurl[i] != undefined && _config.advertising.siteurl[i] != '') {
                    var tabpopurl = _config.advertising.siteurl[i];	//팝업창에 출력될 페이지 URL
                } else {
                    var tabpopurl = _config.advertising.defaulturl[2];
                }
                }
            }
            var newSize = getPopUpSize();
            window.open(tabpopurl, "", "width=560px, height=" + "700px" + ", " + "top=" + newSize.top + ", left=" + newSize.left + ",scrollbars=yes");
            }

            for (var i = 0; i < _config.advertising.usetab.length; i++) {
                var id = $(this).attr("id");
                if (_config.advertising.usetab[i][0] == id) {
                    if (_config.advertising.imgurl[i] != undefined && _config.advertising.imgurl[i] != '') {
                        var imgurl = _config.advertising.imgurl[i];
                    } else {
                        var imgurl = _config.advertising.defaulturl[1];
                    }
                    $("#imgview").css({ "background": "url(" + imgurl + ")", "background-size": "400px 132px", "background-repeat": "no-repeat" });
                    $("#imgview").off("click").on("click", (function () {
                        for (var i = 0; i < _config.advertising.usetab.length; i++) {
                            if (_config.advertising.usetab[i][0] == id) {
                                if (_config.advertising.siteurl[i] != undefined && _config.advertising.siteurl[i] != '') {
                                    var popurl = _config.advertising.siteurl[i];	//팝업창에 출력될 페이지 URL
                                } else {
                                    var popurl = _config.advertising.defaulturl[2];
                                }
                                var newSize = getPopUpSize();
                                window.open(popurl, "", "width=560px, height=" + "700px" + ", " + "top=" + newSize.top + ", left=" + newSize.left + ",scrollbars=yes");
                            }
                        };
                    }));
                }
            };

            showImg();

            detailBtn.unbind('click');
            deleteBtn.unbind('click');
        }
    };

    var setSecureToken = function () {
        init();
        removeLoadingEvent();
        removeImportBtn();
        passwordText.text(jsLang(144));
        pinHint.html(jsLang(158));
        vestCertVersion(function () {
            getTokenListFromVestCert(vest.token.TYPE.TOKEN);
        });
        _lastStorage = vest.token.TYPE.TOKEN;
    };

    var setSaveToken = function () {
        init();
        removeLoadingEvent();
        removeImportBtn();
        passwordText.text(jsLang(144));
        pinHint.html(jsLang(158));
        vestCertVersion(function () {
            getTokenListFromVestCert(vest.token.TYPE.SAVETOKEN);
        });
        _lastStorage = vest.token.TYPE.SAVETOKEN;
    };

    var setCertificateFile = function () {
        init();
        removeImportBtn();
        hideContentBackGround();
        passwordText.text(jsLang(88));
        pinHint.html(jsLang(89));
        showP12View();

        detailBtn.unbind('click');
        deleteBtn.unbind('click');

        // pinForm.hide();
        certPin.hide();
        browserCertificatePasswordInput.hide();
        pfxPin.show();

        _storageType = "CERT FILE";
        _preventKeyFlag = true;

        if (vest.util.file.hasFileAPI()) {
            addFileEvent();
            showFileInputDiv();
            hideFlashInputDiv();
        } else if (!vest.util.file.hasFileAPI() && hasFlash()) {
            $("#p12txt").text(jsLang(73));
            hideFileInputDiv();
            showFlashInputDiv();
        } else {
            emptyFileInputDiv();
        }

        addOkayClickEvent(importCertificate);
        _lastStorage = '';
    };

    var setSecureDisk = function () {
        init();
        removeLoadingEvent();
        removeImportBtn();
        passwordText.text(jsLang(144));
        pinHint.html(jsLang(158));
        vestCertVersion(function () {
            getTokenListFromVestCert(vest.token.TYPE.SECUREDISK);
        });
        _lastStorage = vest.token.TYPE.SECUREDISK;
    };

    var setPhoneCertification = function () {
        init();
        removeLoadingEvent();
        removeImportBtn();
        passwordText.text(jsLang(88));
        pinHint.html(jsLang(89));
        vestCertVersion(function () {
            getTokenListFromVestCert(vest.token.TYPE.PHONE);
        });
        _lastStorage = vest.token.TYPE.PHONE;
    };

    var setDreamService = function () {
        init();
        removeLoadingEvent();
        removeImportBtn();
        passwordText.text(jsLang(88));
        pinHint.html(jsLang(89));
        vestCertVersion(function () {
            vest.token.getTokenList(vest.token.TYPE.PHONE, function (list) {
                if (list.length == 0) {
                    // alert("없자뉴");
                } else if (list.length == 1 && list[0].type == "Mobile USIM") {
                    ytLayer.show();

                    setPhoneOption(list[0].tokenIdentifier, _config.dreamsecurity, usimEvent);
                } else {    // list 2개 이상
                    ytLayer.show();

                    for (var i = 0; i < list.length; i++) {
                        if (list[i].type == "Mobile USIM") {
                            setPhoneOption(list[i].tokenIdentifier, _config.dreamsecurity, usimEvent);
                        }
                    }
                }

                addLoadingEvent();
            });
        });
    };

    var setWebStorage = function (first) {
        init();
        removeImportBtn();

        _storageType = "WEB";
        _preventKeyFlag = true;

        certPin.hide();
        browserCertificatePasswordInput.hide();
        pfxPin.show();

        removeLoadingEvent();
        passwordText.text(jsLang(88));
        pinHint.html(jsLang(89));

        if (_config.signTitle == 'SIGN') {
            subCertList.attr('class', 'cert01_05');
            importBrowserBtn.css('display', 'inline-block');
            importBrowserBtn.unbind('click').bind('click', function () {
                detailBtn.unbind('click').unbind('keydown');
                deleteBtn.unbind('click').unbind('keydown');

                importBrowserView.show();
                hideContentBackGround();
                mainCertList.hide();
                statusView.val('');
                pfxPin.attr({ "disabled": true });

                browserCertificateImportAddEvent();
            });
            browserCertListBtn.unbind('click').bind('click', setWebStorage);
        }

        // 인증서 가져오는 부분
        vest.token.getCertificates('HIBISCUS', {}, {}, {}, {}, {}, function (certList) {
            addLoadingEvent();

            if (certList.length == 0 && (typeof first !== 'undefeind' && first == true)) {
                importBrowserBtn.click();
            } else {
                var viewCertList = [];

                for (var i = 0; i < certList.length; i++) {
                    _certificates.push(certList[i].toCertificate());
                    viewCertList.push(certList[i]);
                }
                outCertList(viewCertList);
                addDetailDeleteClickEvent();
                addOkayClickEvent(browserCertificate);
            }
        }, hibiscusErrorHandler);
    };

    var setSecureDiskRestore = function () {
        init();
        removeLoadingEvent();
        removeImportBtn();
        passwordText.text(jsLang(144));
        pinHint.html(jsLang(158));

        detailBtn.unbind('click');
        deleteBtn.unbind('click');

        vestCertVersion(function () {
            // getTokenListFromVestCert(vest.token.TYPE.SECUREDISK);
            getBackupedTokenListFromVestCert(vest.token.TYPE.LOCALDISK);
        });
        _lastStorage = vest.token.TYPE.LOCALDISK;
    };

    var addTokenEvent = function () {
        //기존에 이벤트가 있을시 삭제하고 재등록.
        removeTokenEvent();

        if (_config.storage.hardDisk) {
            $("#hard_disk").keydown(function (key) {
                keyEvent(key, setHardDisk);
            });
            $('#hard_disk').click(setHardDisk);
        } else {
            disableMenu($('#hard_disk'));
        }

        if (_config.storage.usbDisk) {
            $("#usb_disk").keydown(function (key) {
                keyEvent(key, setUsbDisk);
            });
            $('#usb_disk').click(setUsbDisk);
        } else {
            disableMenu($('#usb_disk'));
        }

        if (_config.storage.secureToken) {
            $("#secure_token").keydown(function (key) {
                keyEvent(key, setSecureToken);
            });
            $('#secure_token').click(setSecureToken);
        } else {
            disableMenu($('#secure_token'));
        }

        if (_config.storage.saveToken) {
            $("#save_token").keydown(function (key) {
                keyEvent(key, setSaveToken);
            });
            $('#save_token').click(setSaveToken);
        } else {
            disableMenu($('#save_token'));
        }

        if (_config.storage.certificateFile) {
            $("#certificate_file").keydown(function (key) {
                keyEvent(key, setCertificateFile);
            });
            $('#certificate_file').click(setCertificateFile);
        } else {
            disableMenu($('#certificate_file'));
        }

        if (_config.storage.secureDisk) {
            $("#secure_disk").keydown(function (key) {
                keyEvent(key, setSecureDisk);
            });
            $('#secure_disk').click(setSecureDisk);
        } else {
            disableMenu($('#secure_disk'));
        }

        if (_config.storage.smartPhone) {
            $("#phone_certification").keydown(function (key) {
                keyEvent(key, setPhoneCertification);
            });
            $('#phone_certification').click(setPhoneCertification);
        } else {
            disableMenu($('#phone_certification'));
        }

        if (_config.storage.dreamService) {
            $("#dream_service").keydown(function (key) {
                keyEvent(key, setDreamService);
            });
            $('#dream_service').click(setDreamService);
        } else {
            disableMenu($('#dream_service'));
        }

        if (_config.serverStorage.hibiscus.use && (getBrowserVersion() == -1 || getBrowserVersion() > 10)) {
            $("#hibiscus_storage").keydown(function (key) {
                keyEvent(key, setHibiscus);
            });
            $('#hibiscus_storage').click(setHibiscus);
        } else {
            disableMenu($('#hibiscus_storage'));
        }

        // if (_config.storage.webStorage && (vest.browser.isMSIE() ? (vest.browser.isMSIE() >= 9 ? true : false) : true)) {
        if (_config.serverStorage.hibiscus.use) {
            $('#webStorage').keydown(function (key) {
                keyEvent(key, setWebStorage);
            });
            $('#webStorage').click(function () {
                setWebStorage(true);
            });
        } else {
            disableMenu($('#webStorage'));
        }

        if (_config.storage.secureDiskRestore) {
            $("#secure_disk_restore").keydown(function (key) {
                keyEvent(key, setSecureDiskRestore);
            });
            $('#secure_disk_restore').click(setSecureDiskRestore);
        } else {
            disableMenu($('#secure_disk_restore'));
        }

        for (var i = 0; i < _config.advertising.usetab.length; i++) {
            if (_config.advertising.enable) {
                $('#' + _config.advertising.usetab[i][0]).keydown(function (key) {
                    keyEvent(key, setadvertising);
                });
                $('#' + _config.advertising.usetab[i][0]).click(setadvertising);
            } else {
                disableMenu($('#' + _config.advertising.usetab[i][0]));

            }
        }
    };

    var getE2EInfo = function (callback) {
        var vender;

        if (_keySafer.getNumber() == 7) {
            vender = {
                type: _keySafer.getNumber()
            };
        }

        vest.token.getE2EInfo(vender, callback, vestCertErrorHandler);
    };

    var keySaferScriptLoading = function (keySaferObj, i, func, errorcallback) {
        var script = keySaferObj.getScript();
        var scriptLen = script.length;

        if (scriptLen == 0) {
            errorcallback();
            return;
        }

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
            keySaferScriptLoading(keySaferObj, ++i, func, errorcallback);
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
        } else {
            keySaferObj = keySafer();
        }

        keyScript.onerror = function (error) {
            errorcallback(error.target.src);
        };

        head.appendChild(keyScript);
    };

    
    var hibiKeySaferInit = function () {
        _hibiKeySafer.init(function(res) {
            if(res == _config.hibiscuskeystrokeEncryption) {
                _config.hibiscuskeystrokeEncryption = 'vikie';
                createHibiKeySafer();
                return;
            }
            return;
        })
    }

    var createPfxKeySafer = function () {
        if (useMenu['CERTIFICATEFILE'] * 1 || useMenu['WEBSTORAGE'] * 1) {
            _pfxKeySafer = keySafer(_config.pfxKeystrokeEncryption, ['hiddenPasswordInput'], _config.pfxKeySaferConfig);
            keySaferScriptLoading(_pfxKeySafer, 0, function (data) {
                _pfxKeySafer.init(data);
                createHibiKeySafer();
            }, createHibiKeySafer);
        }
    };

    var createKeySafer = function (func) {
        _keySafer = keySafer(_config.keystrokeEncryption, ['tokenPin', 'passwordInput'], _config.keySaferConfig);
        keySaferScriptLoading(_keySafer, 0, function (data) {
            _keySafer.init(data);
            func();
        }, func);
    };

    var createHibiKeySafer = function () {
        _hibiKeySafer = keySafer(_config.hibiscuskeystrokeEncryption, ['browserCertificatePasswordInput'], _config.pfxKeySaferConfig);
        keySaferScriptLoading(_hibiKeySafer, 0, hibiKeySaferInit, function (data) {
            _hibiKeySafer = keySafer();
        });
    }

    function setOpenCertLib() {
        _opencertLoading = document.createElement('div');
        _opencertLoading.id = 'yettie_opencert_loading';
        _opencertLoading.style.position = 'absolute';
        _opencertLoading.style.left = '0';
        _opencertLoading.style.right = '0';
        _opencertLoading.style.top = '0';
        _opencertLoading.style.bottom = '0';
        _opencertLoading.style.zIndex = '10000';
        _opencertLoading.style.background = 'url(' + '../styles/images/loading-animation-6.gif) no-repeat center center';

        document.body.appendChild(_opencertLoading);
        callOpenCertInit();
    }

    function callOpenCertInit() {
        if (getBrowserVersion() == -1 || getBrowserVersion() > 10) {
            vest.openCertInit(_config, function (res) {
                if (!res.init) {
                    _load = true;
                    _config.yessign.use = false;
                    removeLoadingbar('yettie_opencert_loading', res.call);
                }
                _load = true;
                _lastStorage = vest.token.TYPE.HIBISCUS;
                lodingOpenCert(res.call);
            })
        } else {
            _load = true;
            _config.yessign.use = false;
            _lastStorage = vest.token.TYPE.HIBISCUS;
            removeLoadingbar('yettie_opencert_loading', false);
        }
    }

    function lodingOpenCert(callCheck) {
        if (_load == true) {
            removeLoadingbar('yettie_opencert_loading', callCheck);
        } else {
            if (_load) clearTimeout(_load);
            _load = setTimeout(function () {
                lodingOpenCert(callCheck);
            }, 1000);
        }
    }

    function removeLoadingbar(id, callCheck) {
        var element = document.getElementById(id);
        if (typeof element !== 'undefined' && element != null) {
            _load = true;
            element.parentNode.removeChild(element);
            if (callCheck == true) setHibiscus();
        }
    }

    function getBrowserVersion() {
        var word;
        var agent = navigator.userAgent.toLowerCase();
        var browser = '';

        // IE old version ( IE 10 or Lower ) 
        if (navigator.appName == "Microsoft Internet Explorer") word = "msie ";
        // IE 11 
        else if (agent.search("trident") > -1) word = "trident/.*rv:";
        // Microsoft Edge  
        else if (agent.search("edge/") > -1) word = "edge/";
        else if (agent.indexOf('safari') > -1) { // Chrome or Safari
            if (agent.indexOf('opr') > -1) { // Opera
                browser = 'opera';
            } else if (agent.indexOf('chrome') > -1) { // Chrome
                browser = 'chrome';
            } else { // Safari
                browser = 'safari';
            }
            // safari일 경우 임의로 3리턴
            //if (browser == 'safari') return 3;
            //else 
            return -1;
        }
        // 그외
        else return -1;

        var reg = new RegExp(word + "([0-9]{1,})(\\.{0,}[0-9]{0,1})");
        if (reg.exec(agent) != null) return parseFloat(RegExp.$1 + RegExp.$2);
        return -1;
    }

    // ready
    $(document).ready(function () {
        if (!(vest.isSSL())) {
            getError(jsLang(74));
            _parent.close();
        }

        // param 세팅
        try {
            _parent.getParameters();
        } catch (e) {
            getError(jsLang(53));
            _parent.close();
        }
        _param = params.getParameters();

        // if (_config.serverStorage.hibiscus.use) {
        //     certPin.hide();
        //     addImportBtn();
        //     if (_config.yessign.use && _load == false) {
        //         setOpenCertLib();
        //     } else if (!_config.yessign.use) _load = true;
        // }

        // 설정된 매뉴 등록, 출력, 이벤트 등록
        storageCheck();                             // 기존에 서명한 매체, 인증서 확인작업(필터)
        menuEvent();                                 // 메뉴 회전 이벤트 등록.. 요거 어떻게하고싶은데..
        orderMenu(_config.menuSequence);                                // 출력될 메뉴 순서 함수
        showMenu();                                 // 매체순서, 매체활성화 여부 설정 및 뿌려줌..
        addLoadingEvent();                          // 매체에 대한 이벤트 등록
        _lastMenuIndex = _menuTabIndex - 1;

        startMenu();                                // 활성화된 매체 중 첫번째로 선택될 매체 설정.. 함수명이 참이상해요..
        setTitle();                                 // 타이틀 설정
        titleText.focus();
        // remove wheel event
        mainCertList.mousedown(function (e) {
            if (e.which == 2) {
                e.preventDefault();
            }
        });

        if (_config.useDrag) dragCheck();

        // IE9 플래시
        if (hasFlash() && useMenu.CERTIFICATEFILE == 1) {
            addFlash(flashInputDiv);
        }

        // 브라우저 인증서 초기 설정
        if (useMenu.WEBSTORAGE == 1) {
            if (hasFlash() && vest.browser.isMSIE() == 9) {
                importBrowserViewDefault.hide();
                importBrowserViewIE9.show();
                addFlash(importBrowserViewIE9FlashDiv);
            }
        }

        _config.keySaferConfig.transkey.position.x = [-165, -224];
        _config.keySaferConfig.transkey.position.y = [-105, -250];
        _config.pfxKeySaferConfig.vikie.positionX = 0;
        _config.pfxKeySaferConfig.vikie.positionY = 125;

        createKeySafer(createPfxKeySafer);
    });
    return {
        readFileFlash: readFileFlash
    }
})(document, jQuery, vest, params, VestSign);

if (typeof yettie === "object") {
    yettie.setDefault = vest.util.size.setDefault;
    yettie.defaultSize = vest.util.size.defaultSize;
    yettie.onKeypad = vest.util.size.onKeypad;
    yettie.offKeypad = vest.util.size.defaultSize;
    yettie.getDefault = vest.util.size.getDefault;
} else {
    var yettie = vest.util.size;
}

// yettie.onKeypad = parent.vest.util.size.onKeypad;
// yettie.offKeypad = parent.vest.util.size.offKeypad;

// flash
function getBinaryData(resData) {
    cert_select_controller.readFileFlash(resData);
}