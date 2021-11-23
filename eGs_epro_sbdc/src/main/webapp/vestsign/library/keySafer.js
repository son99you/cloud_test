//version: 3.1.8_e54ce34761bf3c9776b1c6dd23fc7a4531eaad89
//update: Thu Jan 16 10:11:57 2020 +0900

var keySafer=function(t,e,n){var i=function(t){var i,y=t,m=[];if("string"==typeof e)m.push(document.getElementById(e));else if("object"==typeof e)for(var b=0;b<e.length;b++)m.push(document.getElementById(e[b]));else y="";switch(y){case"kings":i=new u(m,n.kings);break;case"npk":i=new a(m,n.nProtect);break;case"ahnlab":i=new c(m,n.ahnlab);break;case"touchen":i=new l(m,n.touchen);break;case"transkey":i=new d(m,n.transkey);break;case"ahnlabOld":i=new h(m,n.ahnlabOld);break;case"ahnlabKeyhook":i=new p(m,n.ahnlab);break;case"dill":i=new o(m,n.dill);break;case"npkKeypad":i=new f(m,n.nProtect);break;case"xkeypad":i=new g(m,n.xkeypad);break;case"vikie":i=new s(m,n.vikie);break;default:i=new r(m)}return i},r=function(t){var e="undefined",n=0,i=t,r=!1,s="";this.init=function(t){t()},this.getPassword=function(t,e){var n=[],r=[];if("object"==typeof e)for(var s=0;s<e.length;s++)r.push(document.getElementById(e[s]));else"string"==typeof e?r.push(document.getElementById(e)):r=i;for(var s=0;s<r.length;s++)n.push(r[s].value);for(var s=n.length;s<3;s++)n.push(void 0);t(n[0],n[1],n[2])},this.initalizeCheck=function(){return r},this.getType=function(){return e},this.getNumber=function(){return n},this.getScript=function(){return[]},this.isCert=function(){return!1},this.clearPassword=function(){},this.disableElement=function(t){},this.getPath=function(){return s}},s=function(t,e){var n,i=1234,r=t,s=!1,o=e.path,u=e.vikieConfig,a=e.fileName,f="undefined"!=typeof e.positionX?e.positionX:0,c="undefined"!=typeof e.positionY?e.positionY:0,l=function(t,e){if(0!=yt.vikie.isClosed()){t.target.value="";var n={x:window.innerWidth/2-yt.vikie.PC().width/2+f,y:window.innerHeight/2-yt.vikie.PC().height/2+c};yt.vikie.show(n,e)}};this.init=function(t){yt.vikie.loadNC(u,function(e){yettie.setDefault(),n=e;for(var i=0;i<r.length;i++)r[i].addEventListener("focus",function(t){t.target.blur(),yettie.onKeypad("vikie"),l(t,this)});yt.vikie.addEventHandler("close",function(t){t.removeAttribute("hash"),t.value="",yettie.offKeypad()}),yt.vikie.addEventHandler("done",function(t,e,n,i){i.setAttribute("hash",n),i.setAttribute("count",t),i.setAttribute("cipher",e),yettie.offKeypad()}),yt.vikie.addEventHandler("rearrange",function(t){u.initValueRearrange&&(t.value="")}),yt.vikie.addEventHandler("keypress",function(t){t.value=t.value+"*"}),yt.vikie.addEventHandler("backspace",function(t){t.value=t.value.slice(0,t.value.length-1)}),s=!0,"function"==typeof t&&t()},function(t){alert("vikie got error: \n"+t),s=!1})},this.getPassword=function(t,e){var s=[],o=[];if("object"==typeof e)for(var u=0;u<e.length;u++)o.push(document.getElementById(e[u]));else"string"==typeof e?o.push(document.getElementById(e)):o=r;for(var u=0;u<o.length;u++)s.push({type:i,value:n(o[u].getAttribute("count"),o[u].getAttribute("cipher"))});for(var u=s.length;u<3;u++)s.push(void 0);t(s[0],s[1],s[2])},this.initalizeCheck=function(){return s},this.getType=function(){return _typeo},this.getNumber=function(){return i},this.getScript=function(){return a},this.isCert=function(){return!1},this.clearPassword=function(){},this.disableElement=function(){},this.getPath=function(){return o},this.isWebKeypad=function(){return!0},this.disabledFocus=function(){return!0}},o=function(t,e){var n="dill",i=666,r=t,s=!1,o=e.path,u=e,a=e.fileName;this.init=function(t){for(var e=0;e<r.length;e++)dill.service.init(r[e].getAttribute("id"));s=!0},this.getPassword=function(t,e){var n=[],s=[];if("object"==typeof e)for(var a=0;a<e.length;a++)s.push(document.getElementById(e[a]));else"string"==typeof e?s.push(document.getElementById(e)):s=r;for(var a=0;a<s.length;a++)n.push({type:i,value:dill.service.getDillData(s[a].getAttribute("id")),path:o,config:u});for(var a=n.length;a<3;a++)n.push(void 0);t(n[0],n[1],n[2])},this.initalizeCheck=function(){return s},this.getType=function(){return n},this.getNumber=function(){return i},this.getScript=function(){return a},this.isCert=function(){return!1},this.clearPassword=function(){},this.disableElement=function(t){},this.getPath=function(){return o}},u=function(t,e){var n="kings",i=1,r=t,s=!1,o=e.path,u=e.fileName;this.init=function(t){for(var e=0;e<r.length;e++)KOS.registerElementWithKey(r[e],"none","data-kdf-e2e-pubkey",t.publicKey);s=!0},this.initalizeCheck=function(){return s},this.getType=function(){return n},this.getNumber=function(){return i},this.getScript=function(){return u},this.getPassword=function(t,e){var n=[],s=[];if("object"==typeof e)for(var o=0;o<e.length;o++)s.push(document.getElementById(e[o]));else"string"==typeof e?s.push(document.getElementById(e)):s=r;for(var o=0;o<s.length;o++){var u=KOS.getInputSessionKey(s[o]),a=KOS.getProtectedValue(s[o]);""==u||"undefined"==u||null==u?n.push(s[o].value):n.push({type:i,value:{session:u,value:a}})}for(var o=n.length;o<3;o++)n.push(void 0);t(n[0],n[1],n[2])},this.isCert=function(){return!0},this.clearPassword=function(){},this.disableElement=function(t){},this.getPath=function(){return o}},a=function(t,e){function n(){$(document).bind("nppfs-npv-before-show nppfs-npv-closed nppfs-npv-after-enter nppfs-npk-after-regist-field",function(t){switch(t.type){case"nppfs-npv-before-show":yettie.onKeypad("npk");break;case"nppfs-npv-closed":yettie.offKeypad();break;case"nppfs-npv-after-enter":yettie.offKeypad();break;case"nppfs-npk-after-regist-field":"disabled"!=$("#"+t.name).attr("disabled")&&"undefined"==typeof $("#"+t.name).attr("disabled")||$("#"+t.name).css("backgroundColor","#C6C3C3")}})}var i="npk",r=2,s=t,o=!1,u=e.path,a=e.fileName;this.init=function(){vest.util.size.setDefault();for(var t=0;t<s.length;t++)s[t].setAttribute("npkencrypt","re"),s[t].setAttribute("name",s[t].getAttribute("id")),s[t].setAttribute("data-keypad-type","alpha");npPfsStartup(document.passwordForm,e.param2,e.param3,e.param4,e.param5,"npkencrypt","re"),n(),o=!0},this.getPassword=function(t,e){var n=[],i=[];if("object"==typeof e)for(var o=0;o<e.length;o++)i.push(document.getElementById(e[o]));else"string"==typeof e?i.push(document.getElementById(e)):i=s;for(var o=0;o<i.length;o++)n.push({type:r,value:{table:npPfsCtrl.GetResultField("passwordForm",i[o].getAttribute("name")),data:npPfsCtrl.GetReplaceField("passwordForm",i[o].getAttribute("name"))}}),bh.doFocusOut(i[o].getAttribute("name"));for(var o=n.length;o<3;o++)n.push(void 0);t(n[0],n[1],n[2])},this.initalizeCheck=function(){return o},this.getType=function(){return i},this.getNumber=function(){return r},this.getScript=function(){return a},this.isCert=function(){return!1},this.clearPassword=function(){},this.disableElement=function(t){},this.getPath=function(){return u},this.enabledField=function(t){npPfsCtrl.ResetField("passwordForm",t),npPfsCtrl.RegistDynamicField("passwordForm",t)},this.disabledField=function(t){npPfsCtrl.ResetField("passwordForm",t),npPfsCtrl.RegistDynamicField("passwordForm",t)}},f=function(t,e){function n(){$(document).bind("nppfs-npv-before-show nppfs-npv-closed nppfs-npv-after-enter nppfs-npk-after-regist-field",function(t){switch(t.type){case"nppfs-npv-before-show":break;case"nppfs-npv-closed":break;case"nppfs-npv-after-enter":break;case"nppfs-npk-after-regist-field":"disabled"!=$("#"+t.name).attr("disabled")&&"undefined"==typeof $("#"+t.name).attr("disabled")||$("#"+t.name).css("backgroundColor","#C6C3C3")}})}var i="npk",r=10,s=t,o=!1,u=e.path,a=e,f=e.fileName;this.init=function(t){var e=function(t,e){for(var n=0;n<s.length;n++)s[n].setAttribute("npkencrypt",t),s[n].setAttribute("name",s[n].getAttribute("id")),s[n].setAttribute("data-keypad-type","alpha"),s[n].setAttribute("data-keypad-useyn-type","toggle"),s[n].setAttribute("data-keypad-useyn-input",s[n].getAttribute("id"));npPfsStartup(document.passwordForm,a.param2,a.param3,a.param4,a.staticParam5?a.param5:e,"npkencrypt",a.type)};npPfsCtrl.isInstall({success:function(){npPfsCtrl.isVirtualMachine(function(n){npPfsCtrl.hideLoading(),vest.util.size.setDefault(),n?t("npkKeypad"):e(a.type,!1)}),n(),o=!0},fail:function(){"function"==typeof t&&t("npkKeypad")}})},this.getPassword=function(t,e){var n=[],i=[];if("object"==typeof e)for(var o=0;o<e.length;o++)i.push(document.getElementById(e[o]));else"string"==typeof e?i.push(document.getElementById(e)):i=s;for(var o=0;o<i.length;o++)n.push({type:r,value:npPfsCtrl.GetEncryptResult("passwordForm",i[o].getAttribute("name")),path:u,config:a});for(var o=n.length;o<3;o++)n.push(void 0);t(n[0],n[1],n[2])},this.initalizeCheck=function(){return o},this.getType=function(){return i},this.getNumber=function(){return r},this.getScript=function(){return f},this.isCert=function(){return!1},this.clearPassword=function(){for(var t=0;t<s.length;t++)npPfsCtrl.ResetField("pinForm",s[t].getAttribute("name"))},this.disableElement=function(t){},this.getPath=function(){return u}},c=function(t,e){function n(t,e){$ASTX2.init(function(){if($_astxu.log("ASTX.init() success"),t)return t()},function(){return!1})}var i="anlab",r=3,s=t,o=!1,u=e.path,a=e.fileName,f="undefined"==typeof e.E2E||e.E2E;this.init=function(){for(var t=0;t<s.length;t++)s[t].setAttribute("autocomplete","off"),s[t].setAttribute("e2e_type","11");n(function(){f?($ASTX2.initE2E(2189),o=!0):($ASTX2.initNonE2E(2189),o=!0)})},this.getPassword=function(t,e){var n=[],i=[];if("object"==typeof e)for(var o=0;o<e.length;o++)i.push(document.getElementById(e[o]));else"string"==typeof e?i.push(document.getElementById(e)):i=s;for(var o=0;o<i.length;o++)n.push({type:r,value:{pageID:$ASTX2.getE2EPageID()+"",inputID:$ASTX2.getE2EInputID(i[o])}});for(var o=n.length;o<3;o++)n.push(void 0);t(n[0],n[1],n[2])},this.initalizeCheck=function(){return o},this.getType=function(){return i},this.getNumber=function(){return r},this.getScript=function(){return a},this.isCert=function(){return!1},this.clearPassword=function(){if(o)for(var t=0;t<s.length;t++)$ASTX2.clearE2EText(s[t])},this.disableElement=function(t){},this.getPath=function(){return u}},l=function(t,e){var n="touchen",i=8,r=t,s=!1,o="",u=e.path,a=e.fileName;window.TNK_SR="d53b7efe1ae05d1e0a4dc9ec56ffa049",this.init=function(t){o="-----BEGIN CERTIFICATE-----",o+=t.publicKey,o+="-----END CERTIFICATE-----";for(var e=0;e<r.length;e++)r[e].setAttribute("name",r[e].getAttribute("id")),r[e].setAttribute("data-enc","on");TK_Loading(),s=!0},this.getPassword=function(t,e){var n=0,s=[],u=[];if("object"==typeof e)for(var a=0;a<e.length;a++)u.push(document.getElementById(e[a]));else"string"==typeof e?u.push(document.getElementById(e)):u=r;var f=function(e){if(s.push({type:i,value:{data:e}}),n++,n==u.length){for(var r=s.length;r<3;r++)s.push(void 0);t(s[0],s[1],s[2])}else TK_GetEncYT("passwordForm",u[n].getAttribute("name"),o,f)};TK_GetEncYT("passwordForm",u[n].getAttribute("name"),o,f)},this.initalizeCheck=function(){return s},this.getType=function(){return n},this.getNumber=function(){return i},this.getScript=function(){return a},this.isCert=function(){return!0},this.clearPassword=function(){},this.disableElement=function(t,e){t.attr("data-security","off"),e.attr("data-DefaultSecurity","off")},this.getPath=function(){return u}},d=function(t,e){function n(t){for(var e=0;e<s.length;e++){var n={x:t.x,y:t.y};"object"==typeof t.x&&(n.x=t.x[e]),"object"==typeof t.y&&(n.y=t.y[e]),c.push(n)}}var i="transkey",r=8,s=t,o=!1,u="",a=e.path,f=e.fileName,c=[];window.transkey_yettie=!0,this.init=function(t){yettie.setDefault(),l(),n(e.position),u="-----BEGIN CERTIFICATE-----",u+=t.publicKey,u+="-----END CERTIFICATE-----";for(var i=0;i<s.length;i++)s[i].setAttribute("name",s[i].getAttribute("id")),s[i].setAttribute("data-tk-kbdType","qwerty"),s[i].setAttribute("data-tk-isCrt","true"),s[i].setAttribute("data-tk-cssName","crt"),s[i].setAttribute("data-tk-keyboard","qwerty_crt"),s[i].setAttribute("data-tk-kbdxy",c[i].x+" "+c[i].y);initTranskey(),o=!0},this.getPassword=function(t,e){var n=[],i=[];if("object"==typeof e)for(var o=0;o<e.length;o++)i.push(document.getElementById(e[o]));else"string"==typeof e?i.push(document.getElementById(e)):i=s;for(var o=0;o<i.length;o++)n.push({type:r,value:{data:tk_GetencdataYT(u,i[o].getAttribute("name"))}});for(var o=n.length;o<3;o++)n.push(void 0);t(n[0],n[1],n[2])},this.initalizeCheck=function(){return o},this.getType=function(){return i},this.getNumber=function(){return r},this.getScript=function(){return f},this.isCert=function(){return!0},this.clearPassword=function(){if(o)for(var t=0;t<s.length;t++)s[t].value="",document.getElementById("transkey_"+s[t].getAttribute("name")).value="",document.getElementById("transkey_HM_"+s[t].getAttribute("name")).value=""},this.getPath=function(){return a};var l=function(){var t=document.createElement("link");t.setAttribute("rel","stylesheet"),t.setAttribute("type","text/css"),t.setAttribute("href",a+"transkey.css"),document.getElementsByTagName("head")[0].appendChild(t)}},h=function(t,e){var n="anlabOld",i=4,r=t,s=!1,o=e.path,u=e.fileName;this.init=function(){document.getElementById("passwordForm").setAttribute("onsubmit",aos_copy_to_form(document.getElementById("passwordForm"))),aos_set_auth_server(location.hostname),aos_set_authinfo("aosmgr_smbc.html"),aos_set_option("uimode",!0),aos_set_option("asyncmode",!1),aos_set_option("mkd_protect_level","default"),aos_write_object(),aos_start("40"),s=!0},this.getPassword=function(t,e){var n=[],i=[];if("object"==typeof e)for(var s=0;s<e.length;s++)i.push(document.getElementById(e[s]));else"string"==typeof e?i.push(document.getElementById(e)):i=r;for(var s=0;s<i.length;s++)n.push(aos_get_text2(i[s]));for(var s=n.length;s<3;s++)n.push(void 0);t(n[0],n[1],n[2])},this.initalizeCheck=function(){return s},this.getType=function(){return n},this.getNumber=function(){return i},this.getScript=function(){return u},this.isCert=function(){return!1},this.clearPassword=function(){},this.disableElement=function(t){},this.getPath=function(){return o}},p=function(t,e){function n(t,e){$ASTX2.init(function(){if($_astxu.log("ASTX.init() success"),t)return t()},function(){return!1})}var i="ahnlabKeyhook",r=5,s=t,o=!1,u=e.path,a=e.fileName;this.init=function(){for(var t=0;t<s.length;t++)s[t].setAttribute("autocomplete","off"),s[t].setAttribute("e2e_type","21");n(function(){$ASTX2.initNonE2E(e.custCode),o=!0})},this.getPassword=function(e){var n=[],i=[];if("object"==typeof t)for(var r=0;r<t.length;r++)i.push(t[r]);else"string"==typeof t?i.push(document.getElementById(t)):i=s;for(var r=0;r<i.length;r++)n.push(i[r].value);for(var r=n.length;r<3;r++)n.push(void 0);e(n[0],n[1],n[2])},this.initalizeCheck=function(){return o},this.getType=function(){return i},this.getNumber=function(){return o?r:0},this.getScript=function(){return a},this.isCert=function(){return!1},this.clearPassword=function(){if(o)for(var t=0;t<s.length;t++)$ASTX2.clearE2EText(s[t])},this.disableElement=function(t){},this.getPath=function(){return u}},g=function(t,e){function n(t,e){t.setCloseCallback(function(n,i){if(f[e].readOnly=!0,"XK_START"==n);else if("KEY_SIZE"==n){var r=i.split(",");yettie.onKeypad("xkeypad",{x:r[0],y:r[1]})}else"REFRESH_START"==n?s(function(n){h[f[e].getAttribute("id")].sessionID=n.sessionID,h[f[e].getAttribute("id")].indexTable=n.indexTable,t.refresh(n.indexTable)}):"XK_CLOSE"!=n&&"XK_ENTER"!=n||yettie.offKeypad()})}function i(t){var e=document.createElement("input");e.setAttribute("id","xkindexed"),e.setAttribute("name","xkindexed"),e.setAttribute("type","hidden"),f[t].appendChild(e)}var r,s,o,u="xkeypad",a=7,f=t,c=!1,l=e.path,d=e.fileName,h={};this.init=function(t){yettie.setDefault(),s=t.getE2EInfoFun,r=t.sessionID,o=new Array(f.length);var e=function(t,n){return f.length==t?void n():void s(function(i){h[f[t].getAttribute("id")]={sessionID:i.sessionID,indexTable:i.indexTable},t++,e(t,n)})},u=function(){for(var t=0;t<f.length;t++){o[t]=new XKModule,o[t].setTheme("white"),i(t);var e=f[t].getBoundingClientRect().width,r={option:1,left:0,top:parseInt(yettie.getDefault().y)},s="xk_dialog_"+t,u=s+"_button_span",a=document.getElementById(u);null!=a&&a.parentNode.removeChild(a),a=document.createElement("SPAN"),a.id=u,f[t].parentNode.appendChild(a),f[t].readOnly=!0,o[t].initialize(s,a,f[t],e-100,"qwertynew",!1,0,r,h[f[t].getAttribute("id")].indexTable,XK_NEW_ConfigPlugin.sessionTimeout),n(o[t],t),f[t].addEventListener("click",function(){for(var t=0;t<f.length;t++)f[t].getAttribute("id")==this.getAttribute("id")&&document.getElementById("xk_dialog_"+t+"_img").click()}),f[t].addEventListener("keydown",function(){for(var t=0;t<f.length;t++)f[t].getAttribute("id")==this.getAttribute("id")&&document.getElementById("xk_dialog_"+t+"_img").click()}),1==f[t].disabled&&(document.getElementById("xk_dialog_"+t+"_button_span").style.display="none",document.getElementById(f[t].getAttribute("id")).style.width="230px")}c=!0};e(0,u)},this.getPassword=function(t,e){var n=[],i=[];if("object"==typeof e)for(var r=0;r<e.length;r++)i.push(document.getElementById(e[r]));else"string"==typeof e?i.push(document.getElementById(e)):i=f;for(var r=0;r<i.length;r++)for(var s,u=0;u<o.length;u++)o[u]._inputBoxObject.getAttribute("id")==i[r].getAttribute("id")&&(s=o[u].get_sessionInfo(),n.push({type:a,value:{session:h[i[r].getAttribute("id")].sessionID,data:s.input}}));for(var r=n.length;r<3;r++)n.push(void 0);t(n[0],n[1],n[2])},this.initalizeCheck=function(){return c},this.getType=function(){return u},this.getNumber=function(){return a},this.getScript=function(){return d},this.isCert=function(){return!0},this.clearPassword=function(){},this.disableElement=function(t){},this.getPath=function(){return l},this.disabledField=function(t){"object"==typeof xkModule&&"function"==typeof xkModule.blur&&xkModule.blur();for(var e=0;e<f.length;e++)f[e].getAttribute("id")==t&&(document.getElementById("xk_dialog_"+e+"_button_span").style.display="none");document.getElementById(t).style.width="230px"},this.enabledField=function(t){"object"==typeof xkModule&&"function"==typeof xkModule.blur&&xkModule.blur();for(var e=0;e<f.length;e++)f[e].getAttribute("id")==t&&(document.getElementById("xk_dialog_"+e+"_button_span").style.display="initial");document.getElementById(t).style.width="150px"}};return i(t,e)};