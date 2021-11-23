//version: 3.1.8_e54ce34761bf3c9776b1c6dd23fc7a4531eaad89
//update: Thu Jan 16 10:11:57 2020 +0900

/**
 * Created by nori on 2015. 8. 7..
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

var storageSelected = (function (doc, $, vest, params, vestSign) {

    if (vestSign === undefined) {
        alert(storageLang(5));
        //window.close();
        return false;
    }

    var _config = vestSign.getConfig();
    var _parent = vestSign;
    var _lastTokenNumber = typeof(_config.storageSelectedTokenNumber) == 'undefined' ? "" : _config.storageSelectedTokenNumber;
    var _deleteCert;
    var _param;
    var _option

    var ytLayer = $("#ytLayer");

    var useMenu = {
        PHONE: '0',
        USB_DISK: '0',
        HARD_DISK: '0',
        SECURE_TOKEN: '0',
        SAVE_TOKEN: '0',
        CERTIFICATE_FILE: '0',
        SECURE_DISK: '0',
        HIBISCUS_STORAGE: '0'
    };

    var notice = {
        "hard_disk": storageLang(7),
        "usb_disk": storageLang(8),
        "secure_token": storageLang(9),
        "save_token": storageLang(10),
        "secure_disk": storageLang(23),
        "hibiscus_storage": 'hibiscus:82'
    };

    vest.init(_config);

    var dragCheck = function () {
        if(window.navigator.userAgent.toLowerCase().indexOf('firefox') !== -1)
            return;
        addHandle(document.getElementById('titleText'), window);
    };

    // 디자인 
    $('#certAgree_cancelBtn').click(function () {
        //_parent.storageSelectedClose();
        _parent.close();
    });
    $('#certAgree_xBtn').click(function () {
        //_parent.storageSelectedClose();
        _parent.close();
    });

    $('#dialog_clostBtn').click(function () {
        $("#dialog").dialog("close");
    });

    $(document).on("click", "#menu_btn li", function () {

        if (this.id == "") return;

        //var args = [
        //    {arg1:'이동식디스크', tokenNumber:'1'}
        //    , {arg1:'A', tokenNumber:'1'}
        //    , {arg1:'A', tokenNumber:'1'}
        //    , {arg1:'이동식디스크', tokenNumber:'1'}
        //    , {arg1:'이동식디스크', tokenNumber:'1'}
        //];


        //switch ($(this).parent().get(0).id) {
        switch (this.id) {
            //alert("[this.id]"+this.id);
            case "phone_certification":
                alert(storageLang(6));
                break;
            case "hard_disk":
                _option.selectedToken = vest.token.TYPE.SYSTEM;
                setMenuNotice(notice["hard_disk"]);
                vest.token.getTokenList(vest.token.TYPE.SYSTEM, function (list) {
                    if (_lastTokenNumber != '') {
                        var option = {pkitype: _config.useGPKI};
                        vest.token.getCertificates(_lastTokenNumber, undefined, undefined, undefined, undefined, option, function () {
                            okEvent(list[0].tokenIdentifier);
                        }, _param.errorcallback);
                    } else {
                        okEvent(list[0].tokenIdentifier);
                    }
                }, _param.errorcallback);
                break;
            case "usb_disk":
                _option.selectedToken = vest.token.TYPE.LOCALDISK;
                setMenuNotice(notice["usb_disk"]);
                vest.token.getTokenList(vest.token.TYPE.LOCALDISK, function (list) {
                    for(var key in list) {
                        if(list[key].tokenIdentifier == _config.storageSelectedTokenNumber) {
                            list.splice(key, 1);
                        }
                    }

                    if (list.length < 2 && !_config.listsViewDopen) {   // 드라이브 선택 안함
                        okEvent(list[0].tokenIdentifier);
                    } else {
                        $('#dialog h1').text(storageLang(16));
                        dOpen(makeDownMenu(list));
                    }
                },_param.errorcallback);
                break;
            case "secure_token":
                _option.selectedToken = vest.token.TYPE.TOKEN;
                setMenuNotice(notice["secure_token"]);
                vest.token.getTokenList(vest.token.TYPE.TOKEN, function (list) {
                    if (list.length < 2 && !_config.listsViewDopen) {   // 드라이브 선택 안함
                        okEvent(list[0].tokenIdentifier);
                    } else {
                        $('#dialog h1').text(storageLang(12));
                        dOpen(makeDownMenu(list));
                    }
                },_param.errorcallback);
                break;
            case "save_token":
                _option.selectedToken = vest.token.TYPE.SAVETOKEN;
                setMenuNotice(notice["save_token"]);
                vest.token.getTokenList(vest.token.TYPE.SAVETOKEN, function (list) {
                    if (list.length < 2 && !_config.listsViewDopen) {   // 드라이브 선택 안함
                        okEvent(list[0].tokenIdentifier);
                    } else {
                        $('#dialog h1').text(storageLang(12));
                        dOpen(makeDownMenu(list));
                    }
                },_param.errorcallback);
                break;
            case "certificate_file":
                alert(storageLang(6));

                break;
            case "secure_disk":
                _option.selectedToken = vest.token.TYPE.SECUREDISK;
                setMenuNotice(notice["secure_disk"]);
                vest.token.getTokenList(vest.token.TYPE.SECUREDISK, function (list) {
                    if (_lastTokenNumber != '') {
                        var option = {pkitype: _config.useGPKI};
                        vest.token.getCertificates(_lastTokenNumber, undefined, undefined, undefined, undefined, option, function () {
                            okEvent(list[0].tokenIdentifier);
                        }, _param.errorcallback);
                    } else {
                        okEvent(list[0].tokenIdentifier);
                    }
                }, _param.errorcallback);
                break;
            case "hibiscus_storage":
                _option.selectedToken = 'HIBISCUS';
                setMenuNotice(notice["hibiscus_storage"]);
                okEvent('HIBISCUS');
                break;
            default:
                alert(storageLang(6));
        }
    });

    function dOpen(args) {

        var sIndex = $("#menu_btn li").find(".on").parent().index();
        var sClass = $("#menu_btn li:eq(" + sIndex + ") a").attr("class").replace("_on on", "");

        $("#dialog").dialog({
            autoOpen: true,
            //height: 230,
            //width: 250,
            modal: true,
            open: function (type, data) {
                $(".ui-dialog-titlebar", $(this).parent()).remove();

                $("#dialog").dialog({width: "auto", height: "auto"});
                $("#dialog").css('overflow', 'hidden');

                $("#dialog_list").empty();

                //다이얼로그 출력
                $.each(args, function (index, item) {

                    $(document.createElement('li'))
                        .append($(document.createElement('a'))
                            .attr({"href": "#"})
                            .on("click", function () {
                                // saveCertificate(item.tokenNumber);
                                okEvent(item.tokenNumber);
                                $("#dialog").dialog("close");
                            })
                            .append($(document.createElement('span'))
                                .append(item.arg1)
                        )
                    )
                        .appendTo($("#dialog_list"));
                })

                if (args.length > 3) $("#dialog_list").css("height", "128px");
                else $("#dialog_list").css("height", (args.length * 32) + "px");
            },
            resizeStop: function () {
                $(this).dialog({height: "auto"});
            }
        });
    }

    function outSaveList(args) {
        /*
         var args = [
         {arg1:'phone_certification', arg2:'0'},
         {arg1:'usb_disk', arg2:'1'},
         {arg1:'hard_disk', arg2:'1'},
         {arg1:'secure_token', arg2:'1'},
         {arg1:'save_token', arg2:'1'},
         {arg1:'certificate_file', arg2:'1'},
         {arg1:'secure_disk', arg2:'0'}
         ];
         */
        //fixArg1:매체아이디, fixArg2: 매체클래스명, fixArg3: 매체이름, fixArg4: 사용여부(1:사용,0:미사용), fixArg5: 순서
        var fixArgs = [
            //{fixArg1:'phone_certification', fixArg2:'ico7', fixArg3:'휴대폰인증', fixArg4:'1', fixArg5:''},
            // 공통저장소 강제 disabled 주석풀면 됨.
            // {fixArg1: 'hibiscus_storage', fixArg2: 'ico8', fixArg3: storageLang(24), fixArg4: '1', fixArg5: ''},
            {fixArg1: 'hard_disk', fixArg2: 'ico4', fixArg3: storageLang(0), fixArg4: '1', fixArg5: ''},
            {fixArg1: 'usb_disk', fixArg2: 'ico1', fixArg3: storageLang(1), fixArg4: '1', fixArg5: ''},
            {fixArg1: 'secure_token', fixArg2: 'ico2', fixArg3: storageLang(2), fixArg4: '1', fixArg5: ''},
            {fixArg1: 'save_token', fixArg2: 'ico3', fixArg3: storageLang(3), fixArg4: '1', fixArg5: ''}
            //{fixArg1:'certificate_file', fixArg2:'ico5', fixArg3:'찾아보기', fixArg4:'1', fixArg5:''},
            // {fixArg1: 'secure_disk', fixArg2: 'ico6', fixArg3: storageLang(4), fixArg4: '1', fixArg5: ''}
        ];


        //출력용 저장매체 목록 배열정보 수정
        $.each(args, function (index, item) {
            $.each(fixArgs, function (indexs, items) {
                if (item.arg1 == items.fixArg1) {
                    items.fixArg4 = item.arg2;
                    items.fixArg5 = index;
                }
            });
        });

//출력용 저장매체 목록 배열정보 정렬
        fixArgs.sort(function (a, b) {
            if (a.fixArg5 > b.fixArg5) return 1;
            if (a.fixArg5 < b.fixArg5) return -1;
            return 0;
        });

        $("#menu_btn").empty();

//저장매체 출력
        $.each(fixArgs, function (index, item) {
            var jaToen;
            if(_config.langIndex ==2) {
                switch(item.fixArg1) {
                    case 'hard_disk': jaToen = storageLang(18); break;
                    case 'usb_disk': jaToen = storageLang(19); break;
                    case 'secure_token': jaToen = storageLang(20); break;
                    case 'secure_disk': jaToen = storageLang(22); break;
                    case 'save_token': jaToen = storageLang(21); break;
                }
            } else jaToen = item.fixArg3;

            if (item.fixArg4 == "1") {
                $(document.createElement('li'))
                    .attr("id", item.fixArg1)
                    .append($(document.createElement('a'))
                        .attr({
                            "href": "javascript:;",
                            "class": item.fixArg2,
                            "onclick": "javascript:tab($(this).parent().index());",
                            "title": item.fixArg3   // 말풍선
                        })
                        .append( jaToen )
                )
                    .appendTo($("#menu_btn"));
            } else {
                $(document.createElement('li'))
                    .attr("class", item.fixArg2 + "_disable")
                    .append( jaToen )
                    .appendTo($("#menu_btn"));
            }
        })
    }

    function makeDownMenu(list) {
        var result = [];
        var repStr;

        for (var i = 0; i < list.length; i++) {
            if(list[i].name.split(" -")[0] == '하드디스크') {
                repStr = list[i].name;
                repStr = repStr.replace('하드디스크', storageLang(1));
            } else if(list[i].name.split(" -")[0] == '이동식디스크') {
                repStr = list[i].name;
                repStr = repStr.replace('이동식디스크', storageLang(1));
            } else {
                repStr = undefined;
            }
            result.push({arg1: (repStr !== undefined) ? repStr : list[i].name, tokenNumber: list[i].tokenIdentifier});
        }

        return result;
    }

    var disableToken = function (list) {
        for (var i = 0; i < list.length; i++) {
            var object = list[i];

            if (list[i].tokenIdentifier === _config.storageSelectedTokenNumber) {
                if (object.type == "DISK DRIVE" && object.systemDrive) {
                    // 하드 디스크
                    useMenu['HARD_DISK'] = "0";
                }
                if (object.type == "DISK DRIVE" && !(object.systemDrive)) {
                    // 이동식 디스크
                    useMenu['USB_DISK'] = "0";
                }
                if (object.type === "PKCS#11 TOKEN") {
                    // 하드웨어 토큰...
                    useMenu['SECURE_TOKEN'] = "0";
                }
                if (object.type == "SmartCard TOKEN") {
                    // 저장토큰
                    useMenu["SAVE_TOKEN"] = "0";
                }
                if (object.type == "Secure Disk") {
                    // Secure Disk
                    useMenu["SECURE_DISK"] = "0";
                }
                //이런식으로 활성화 세팅.
            }
        }
    };

    var setTokenList = function (list) {

        // 연결된 매체에 대해서만 인에이블 시키도록 하는 로직
        for (var i = 0; i < list.length; i++) {
            var object = list[i];

            if (object.type == "DISK DRIVE" && object.systemDrive) {
                // 하드 디스크
                useMenu['HARD_DISK'] = "1";
            }
            if (object.type == "DISK DRIVE" && !(object.systemDrive)) {
                // 이동식 디스크
                useMenu['USB_DISK'] = "1";
            }
            if (object.type === "PKCS#11 TOKEN") {
                // 하드웨어 토큰...
                useMenu['SECURE_TOKEN'] = "1";
            }
            if (object.type == "SmartCard TOKEN") {
                // 저장토큰
                useMenu["SAVE_TOKEN"] = "1";
            }
            if (object.type == "Secure Disk") {
                // Secure Disk
                useMenu["SECURE_DISK"] = "1";
            }
        }
    };

    var showMenu = function () {
        // 설정된 값에 따라 매체 표시
        var args = [
            {arg1: 'phone_certification', arg2: useMenu['PHONE']},
            {arg1: 'usb_disk', arg2: useMenu['USB_DISK']},
            {arg1: 'hard_disk', arg2: useMenu['HARD_DISK']},
            {arg1: 'secure_token', arg2: useMenu['SECURE_TOKEN']},
            {arg1: 'save_token', arg2: useMenu['SAVE_TOKEN']},
            {arg1: 'certificate_file', arg2: useMenu['CERTIFICATE_FILE']},
            {arg1: 'secure_disk', arg2: useMenu['SECURE_DISK']}
        ]; 

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

        /* 추가-테스트중 */
        /* 저장매체리스트 출력 */
        outSaveList(args);


        /* 로딩시 저장매체선택 */
        //$("a.ico4").trigger("click");
        //setUsbDisk();tab(0);
    };

    var setMenuNotice = function (str) {
        if( !_config.storageInfoTextColor )
            $("#menu_notice").css("color", "#000000");

        $("#menu_notice").html(str);
    };

    var okEvent = function (tokenNumber) {
        //확인버튼에 saveCertificate() 이벤트 주기.
        $("#certAgree_confirmBtn").unbind('click');
        $("#certAgree_confirmBtn").click(function () {
            if (typeof tokenNumber === 'undefined') {
                alert(storageLang(17));
                return;
            }

            ytLayer.show();
            saveCertificate(tokenNumber);
        });
    };

    var saveCertificate = function (tokenNumber) {
        _param.callback(tokenNumber, _param.option);
        //_parent.storageSelectedClose();

    };

    var errorCallback = function(error) {
        ytLayer.hide();
        vest.util.refactoryMsg.convertMsg(error);
        alert(error.msg);
    };
    
    $(document).ready(function () {
        //옵션에 따라 약관 페이지 호출.
        try {
            //_parent.storageSelectedgetParameters();
            _parent.getParameters();
        } catch (e) {
            _parent.close();
        }
        _param = params.getParameters();
        _option = _param.option;


        if(_config.useDrag) {
            dragCheck();
        }

        okEvent();
        vest.token.getTokenList(vest.token.TYPE.ALL, function (list) {

            setTokenList(list);

            if (!_config.storageSelectedFlag) {
                disableToken(list);
            }

            showMenu();

        }, _param.errorcallback);
    });
})(document, jQuery, vest, params, VestSign);


