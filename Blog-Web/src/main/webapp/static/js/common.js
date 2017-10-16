/**
 * Created by wxsk100 on 2016/11/15.
 */
//常量
var UNDEFINED = 'undefined';
var STRING = 'string';
var NUMBER_CHARS = ['1', '2', '3', '4', '5', '6', '7', '8', '9', '0'];

//请求类型
var GET = "GET";
var POST = "POST";
var PUT = "PUT";
var DELETE = "DELETE";

//code
//服务器内部异常
var SERVER_INTERNAL_EXCEPTION_CODE = "500";
//404
var RESOURCE_NOT_FOUND_EXCEPTION_CODE = "404";

//选择器
function $(id) {
    if (typeof id === UNDEFINED || typeof id !== STRING || id === null) {
        return null;
    }
    if (id.startwith('#')) {
        return document.getElementById(id.substring(1));
    }
    else if (id.startwith(".")) {
        return document.getElementsByClassName(id.substring(1));
    }
    else {
        var eles = document.getElementsByTagName(id);
        if (eles.length > 0) {
            return eles[0];
        }
        return null;
    }
}

$.getUri = function () {
    return document.location.pathname;
};

//函数
if (!JSON) {
    JSON = {};
}
function indexOf(arr, val) {
    for (var i = 0; i < arr.length; i++) {
        if (arr[i] === val) {
            return i;
        }
    }
    return -1;
}
if (!JSON.parse) {
    JSON.parse = function (json) {
        return eval('(' + json + ')');
    };
}
if (!typeof String.prototype.startwith) {

    String.prototype.startwith = function (prefix) {
        if (this === prefix) {
            return true;
        }
        if (typeof prefix !== STRING) {
            return false;
        }
        if (this.length < prefix.length) {
            return false;
        }
        return this.substring(0, prefix.length) === prefix;
    }
}

String.prototype.startwith = function (prefix) {
    if (this === prefix) {
        return true;
    }
    if (typeof prefix !== STRING) {
        return false;
    }
    if (this.length < prefix.length) {
        return false;
    }
    return this.substring(0, prefix.length) === prefix;
};

if (!typeof String.prototype.trim) {
    String.prototype.trim = function (str) {
        //header " "
        for (var i = 0; i < str.length; i++) {
            if (str[i] !== " ") {
                if (i !== 0) {
                    str = str.substring(i);
                }
                break;
            }
        }
        for (var i = str.length - 1; i > -1; i--) {
            if (str[i] !== " ") {
                if (i !== str.length - 1) {
                    str = str.substring(0, i + 1);
                }
                break;
            }
        }
        return str;
    }
}

function parseSimpleObject(object) {
    var type = typeof object;
    if (type === "string" || type === "function") {
        return "\"" + object.toString().replace("\"", "\\\"") + "\"";
    }

    if (type === "number" || type === "boolean") {
        return object.toString();
    }

    if (type === "undefined") {
        return "undefined";
    }

    return "\"" + object.toString().replace("\"", "\\\"") + "\"";
}

function createXmlHttpRequest() {
    if (window.XMLHttpRequest) {
        xmlHttp = new XMLHttpRequest();

        if (xmlHttp.overrideMimeType) {
            xmlHttp.overrideMimeType("text/xml");
        }
    }
    else if (window.ActiveXObject) {
        try {
            xmlHttp = new ActiveXObject("Msxml2.XMLHTTP");
        }
        catch (e) {
            xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
        }
    }
    if (!xmlHttp) {
        window.alert("你的浏览器不支持创建XMLhttpRequest对象");
    }
    return xmlHttp;
}

function executeRequest(url, param, method, callback) {

    var xmlHttp = createXmlHttpRequest();
    xmlHttp.open(method, url, true);
    xmlHttp.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
    xmlHttp.setRequestHeader("x-requested-with", "XMLHttpRequest");
    xmlHttp.send(JSON.stringify(param));
    xmlHttp.onreadystatechange = function () {
        if (xmlHttp.readyState === 4)
            if (xmlHttp.status === 200) {
                callback(eval("(" + xmlHttp.responseText + ")"));
            }
    };
}

$.redirect404 = function () {
    document.location.href = "/404";
};

$.redirect500 = function () {
    document.location.href = "/500";
};

$.alertError = function (message) {
    alert(message);
};

$.getUserIndexUrl = function () {
    return "/user/" + username;
};
$.randomNumberChars = function (len) {
    if (typeof len === UNDEFINED || len === null) {
        len = 8;
    }
    var result = '';
    for (var i = 0; i < len; i++) {
        result += Math.floor(Math.random() * 10);
    }
    return result;
};

$.bindHtml = function (bindConfig) {
    for (var key in bindConfig) {
        var ele = $("#" + key);
        if (typeof ele !== 'undefined' && ele !== null) {
            ele.innerHTML = bindConfig[key];
        }
    }
};

$.registerEvent = function (id, event, handler) {
    var dom;
    if(id instanceof String) {
        dom = $(id);
    }
    else {
        dom = id;
    }
    if (dom) {
        if (dom.attachEvent) {
            // Microsoft
            dom.attachEvent(event, handler);
        } else if (dom.addEventListener) {
            // Mozilla: DOM level 2
            dom.addEventListener(event, handler, false);
        }
    }
};

// stop listening to event
$.unRegisterEvent = function (id, event, handler) {
    var dom;
    if(id instanceof String) {
        dom = $(id);
    }
    else {
        dom = id;
    }
    if (dom) {
        if (dom.detachEvent) {
            // Microsoft
            dom.detachEvent(event, handler);
        } else if (dom.removeEventListener) {
            // Mozilla: DOM level 2
            dom.removeEventListener(event, handler, false);
        }
    }
};

$.extend = function(o,n){
    for (var p in n){
        o[p] = n[p];
    }
    return o;
};

$.addClass = function(id, clazz) {
    var dom;
    if(id instanceof String) {
        dom = $("#" + id);
    }
    else {
        dom = id;
    }
    var domClass = dom.getAttribute("class");
    if (!domClass) {
        domClass = clazz;
    }
    else {
        domClass = domClass.concat(" ").concat(clazz);
    }
    dom.setAttribute("class", domClass);
};
$.removeClass = function(id, clazz) {
    var dom;
    if(id instanceof String) {
        dom = $("#" + id);
    }
    else {
        dom = id;
    }
    var domClass = dom.getAttribute("class");
    if (domClass) {
        domClass.replace(clazz, "");
        dom.setAttribute("class", domClass);
    }
};
$.replaceClass = function(id, clazz, newClazz) {
    var dom;
    if(id instanceof String) {
        dom = $("#" + id);
    }
    else {
        dom = id;
    }
    var domClass = dom.getAttribute("class");
    if (domClass) {
        domClass = domClass.replace(clazz, newClazz);
        dom.setAttribute("class", domClass);
    }
};

$.getCookie = function(c_name) {
    if (document.cookie.length>0) {
        var c_start=document.cookie.indexOf(c_name + "=");
        if (c_start !== -1) {
            c_start=c_start + c_name.length+1;
            var c_end=document.cookie.indexOf(";",c_start)
            if (c_end === -1) c_end=document.cookie.length;
            return decodeURI(document.cookie.substring(c_start,c_end));
        }
    }
    return "";
};
