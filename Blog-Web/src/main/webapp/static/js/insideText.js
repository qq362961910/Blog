;(function (oojs) {
    oojs.define({
        name: "insideText",
        namespace: "dup.ui.painter",
        deps: {
            config: "dup.common.config",
            lang: "dup.common.utility.lang",
            dom: "dup.common.utility.dom",
            slot: "dup.business.slot",
            param: "dup.business.param",
            data: "dup.common.utility.data",
            viewWatch: "dup.business.viewWatch",
            browser: "dup.common.utility.browser",
            richMaterial: "dup.ui.painter.richMaterial",
            event: "dup.common.utility.event",
            monitor: "dup.business.monitor"
        },
        validate: function (t) {
            return !0
        },
        hashBox: {},
        itemIndex: 0,
        maxBytes: null,
        SET_DATA_JSONP: "BAIDU_NEW_DUP_INSIDE",
        BAIDU_DUP_JSONTEMPLATE: "BAIDU_DUP_JSONTEMPLATE",
        baiduTip: null,
        render: function (t) {
            if (this.slot.setStatus(t.id, this.config.STATUS_RENDERED), this.validate(t)) {
                this.monitor.sendLog(t);
                var e = this.dom.g(t.containerId);
                if (e) {
                    this.data.defineOnce(this.SET_DATA_JSONP, oojs.proxy(this, this.textNodeCompare));
                    var i = this.resBody(e);
                    if (i) {
                        var n = this;
                        document.onreadystatechange = function () {
                            "complete" === document.readyState && (n.getScriptHTML(t, e), n.resChild(i))
                        }, this.event.bind(i, "mousedown", oojs.proxy(this, this.removeHuaci)), this.event.bind(i, "mouseup", oojs.proxy(this, this.huaci)), this.viewWatch.regisetViewWatch(t), this.slot.setStatus(t.id, this.config.STATUS_FINISH)
                    }
                }
            }
        },
        resBody: function (t) {
            var e = t.parentNode;
            return e.tagName && "body" === e.tagName.toLowerCase() ? e : this.resBody(e)
        },
        getScriptHTML: function (t, e) {
            var i = i || "ScriptId" + t.id;
            t.ScriptId = i, t.paramObj.dcb = this.SET_DATA_JSONP, t.paramObj.dtm = this.config.STATIC_JSONP, t.paramObj.cec = "utf-8";
            var n = this.param.getPmpRequestUrl(t), o = document.createElement("script");
            o.id = t.ScriptId, o.src = n, o.charset = t.paramObj.cec, e.appendChild(o)
        },
        resChild: function (t) {
            for (var e = t.childNodes, i = 0; i < e.length; i++)if (this.delScript(e[i])) {
                if ("br" === e[i].nodeName.toLocaleLowerCase())break;
                switch (e[i].nodeType) {
                    case 3:
                        var n;
                        this.browser.ie < 9 && e[i].nodeValue && (n = e[i].nodeValue), e[i].textContent && (n = e[i].textContent), "undefined" != typeof n && null !== n && n.length > 200 && this.addHash(e[i]);
                        break;
                    case 1:
                    default:
                        this.resChild(e[i])
                }
            }
        },
        delScript: function (t) {
            return "a" !== t.nodeName.toLowerCase() && "script" !== t.nodeName.toLowerCase() && "iframe" !== t.nodeName.toLowerCase() && "br" !== t.nodeName.toLowerCase()
        },
        addHash: function (t) {
            var e = this.getTag(t);
            if (e.innerHTML) {
                var i = this.lang.trim(e.innerHTML), n = [e, i];
                this.hashBox["item_" + this.itemIndex] = n, this.itemIndex++
            }
        },
        textNodeCompare: function () {
            if (!arguments[0] || null !== typeof arguments[0]) {
                var t = arguments[0].slots[0].ads[0] || !1;
                if (t) {
                    var e = t.data.meta;
                    this.is_instan = +arguments[0].slots[0].conf.ext.instan, this.iss = +arguments[0].slots[0].conf.ext.iss, this.is_instan && (this.getFirstUrl = e[0].curl, this.is_iss && (this.iss = 1));
                    var i = 300;
                    for (var n in this.hashBox) {
                        for (var o = [], s = this.hashBox[n][1], r = s.split(""), a = Math.ceil(r.length / i), d = 0; a > d; d++) {
                            var c = r.splice(0, i).join("");
                            this.isSrcool = !1;
                            for (var l = 0; l < e.length; l++)this.isSrcool || (c = this.strReplace(e[l], c));
                            o.push(c)
                        }
                        this.hashBox[n][0].innerHTML = o.join("")
                    }
                }
            }
        },
        strReplace: function (t, e) {
            if (t || e && "string" == typeof e) {
                var i, n = t.title, o = new RegExp(n, "i");
                this.iss ? t.curl = t.curl.replace(/&di=\d*/, "&di=128") : t.curl = t.curl.replace(/&di=\d*/, "&di=8");
                var s = '<a href="' + t.curl + '" target="_blank" style="color:#38f;text-decoration:none">' + n + "</a>";
                return o.test(e) && n.length ? (this.isSrcool = !0, i = e.replace(o, s)) : e
            }
        },
        getTag: function (t) {
            return null !== t ? t.parentNode : void 0
        },
        checkData: function (t) {
            for (var e = [], i = {}, n = t.length, o = 0; n > o; o++)i["item_" + o] = t[o].title;
            for (var s in i)if (i.hasOwnProperty(s))for (var r = 0; n > r; r++) {
                new RegExp(t.title, "i")
            }
            return e
        },
        byteLen: function (t) {
            for (var e = 0, i = 0; i < t.length; i++)t.charCodeAt(i) > 127 || 94 === t.charCodeAt(i) ? e += 2 : e++;
            return e
        },
        checkStr: function (t) {
            var t = t[1];
            if ("string" == typeof t) {
                var e, i = this.byteLen(t);
                return e = i > 1e3 ? Math.ceil(i / 1e3) : 2
            }
        },
        huaci: function (t) {
            if (this.getFirstUrl) {
                var e = t || window.event, i = e.target || e.srcElement;
                if ("input" !== i.tagName.toLowerCase() && i.id !== this.baiduTip) {
                    var n = "";
                    document.selection ? n = document.selection.createRange().text : window.getSelection() && (n = window.getSelection().toString()), n.length && "" !== n && n !== this.PreSelection && this.drawIcon(n, e)
                }
            }
        },
        drawIcon: function (t, e) {
            var i = document.documentElement.scrollTop || window.pageYOffset || document.body.scrollTop, n = 5, o = 10,
                s = this.browser.ie < 9 ? e.clientX : e.pageX, r = 38,
                a = this.browser.ie < 9 ? e.clientY + i - document.body.clientTop : e.pageY;
            this.baiduTip = "" + this.SET_DATA_JSONP + Math.ceil(1e5 * Math.random());
            var d = document.createElement("img");
            d.id = this.baiduTip, d.src = "//cpro.baidustatic.com/cpro/ui/noexpire/img/2.0.0/icon.png", d.style.cursor = "pointer", d.style.width = r + "px", d.style.height = r + "px", d.style.position = "absolute", d.style.top = parseInt(a, 10) - r - n + "px", d.style.left = s + n + "px", t.length > o && (t = t.substr(0, o)), d.alt = t, document.body.appendChild(d), this.event.bind(d, "click", oojs.proxy(this, this.openUrl))
        },
        removeHuaci: function (t) {
            if (this.getFirstUrl) {
                var e = t || window.event, i = e.target || e.srcElement, n = document.getElementById(this.baiduTip);
                null !== n && i.id !== this.baiduTip && document.body.removeChild(n)
            }
        },
        openUrl: function (t) {
            var e, i = t || window.event, n = i.target || i.srcElement, o = document.getElementById(this.baiduTip),
                s = encodeURIComponent(n.alt);
            o && (e = this.iss ? this.getFirstUrl.replace(/&k=[^&]*/g, "&k=" + s).replace(/&di=\d*/g, "&di=128") : this.getFirstUrl.replace(/&k=[^&]*/g, "&k=" + s).replace(/&di=\d*/g, "&di=8"), window.open(e), document.body.removeChild(o))
        }
    });
})(_ssp_global.oojs);
