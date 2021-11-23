//version: 3.1.8_e54ce34761bf3c9776b1c6dd23fc7a4531eaad89
//update: Thu Jan 16 10:11:57 2020 +0900

/**
 * Created by nori on 2016. 1. 25..
 */

var webAccess = (function() {

    var _init = true;
    var _firstNumber;
    var _endNumber;
    
    var fistTagEvent = function(key) {
        if(key.shiftKey && key.keyCode == 9){
            $("[tabindex=" + _endNumber + "]").focus();
            key.preventDefault();
        }
        else if(key.keyCode == 9){
            $("[tabindex=" + (_firstNumber+1) + "]").focus();
            key.preventDefault();
        }
    };
    
    var endTagEvent = function(key) {
        if(key.shiftKey && key.keyCode == 9){
            $("[tabindex=" + (_endNumber-1) + "]").focus();
            key.preventDefault();
        }
        else if(key.keyCode == 9){
            $("[tabindex=" + _firstNumber + "]").focus();
            key.preventDefault();
        }
    };

    var outlineInitialize = function () {
        // p, li 우선 2가지발견됨
        //$('*').focus(function() {
        //    //$(this).css('outline', 'auto 5px -webkit-focus-ring-color');
        //    //$(this).css('outline-style', 'dotted');
        //});
        //$('*').blur(function() {
        //    //$(this).css('outline', '0');
        //    //$(this).css('outline-style', '0');
        //});

    };

    function firstFocus() {
        if(_init) {
            $("[tabindex=" + _firstNumber + "]").focus();
            _init = false;
        }
    }
    
    function webAccessInitialize (first, end){
        //_firstNumber =  $("#"+ first).attr("tabindex");
        //_endNumber =  $("#"+ end).attr("tabindex");

        //$("#"+ first).keydown(fistTagEvent);
        //$("#"+ end).keydown(endTagEvent);

        outlineInitialize();

        _firstNumber = first;
        _endNumber = end;
        $("[tabindex=" + _firstNumber + "]").keydown(fistTagEvent);
        $("[tabindex=" + _endNumber + "]").keydown(endTagEvent);

        $("#"+ first).attr({
            "alt": $("#"+ first).text(),
            "title": $("#"+ first).text()
        });

        $("[tabindex=" + _firstNumber + "]").focus();
    }

    function certListFocus () {
        $('p').focus(function() {
            //$(this).css('outline', 'auto 5px -webkit-focus-ring-color');
            //$(this).css('outline-style', 'dotted');
            //$(this).css('outline', '1px dotted');
            //$(this).css('outline', 'auto -webkit-focus-ring-color');
            //$(this).css('outline-color', 'green');
        });
        $('p').blur(function() {
            //$(this).css('outline', '0');
            //$(this).css('outline-style', '0');
        });
    }

    function keyDown(func) {

    }
    
    function certificateListEvent(target) {

    }
    
    function passwordEvent(target) {
        
    }

    function setTitle(num, str) {
        $("[tabindex=" + num + "]").attr("title", str);
    }

    function setAlt(num, str) {
        $("[tabindex=" + num + "]").attr("alt", str);
    }
    
    return {
        firstFocus: firstFocus,
        webAccessInit: webAccessInitialize,
        certListFocus: certListFocus,
        //keyDown: keyDown,
        //certificateListEvent: certificateListEvent,
        //webAccessInit: passwordEvent,
        setTitle: setTitle,
        setAlt: setAlt
    }
})();