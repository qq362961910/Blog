package com.jy.blog.controller;

import com.jy.blog.controller.util.AppContextUtil;
import com.jy.blog.helper.SmsHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.DatatypeConverter;
import java.io.IOException;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RequestMapping("third_party")
@RestController
public class ThirdPartyController extends BaseController {

    private final Logger logger = LogManager.getLogger(ThirdPartyController.class);

    @Autowired
    private SmsHelper smsHelper;

    @RequestMapping("weixin_login_callback")
    public Object weiXinLoginCallback() {
        return success();
    }

    @RequestMapping("weixin_authentication")
    public Object weiXinAuthentication(HttpServletRequest request) throws Exception {

        String signature = request.getParameter("signature");
        String timestamp = request.getParameter("timestamp");
        String nonce = request.getParameter("nonce");
        String echostr = request.getParameter("echostr");
        /**
         * 1）将token、timestamp、nonce三个参数进行字典序排序
         * 2）将三个参数字符串拼接成一个字符串进行sha1加密
         * 3）开发者获得加密后的字符串可与signature对比，标识该请求来源于微信
         */
        List<String> elements = new ArrayList<>();
        elements.add("123456");
        elements.add(timestamp);
        elements.add(nonce);
        Collections.sort(elements);
        StringBuilder sb = new StringBuilder();
        for (String str : elements) {
            sb.append(str);
        }
        MessageDigest digest = MessageDigest.getInstance("SHA1");
        digest.update(sb.toString().getBytes());
        byte[] encryptBytes = digest.digest();
        String encryptStr = DatatypeConverter.printHexBinary(encryptBytes).toLowerCase();
        if (encryptStr.equals(signature)) {
            return echostr;
        }
        return success();
    }

    @RequestMapping(value = "request_phone_code", method = RequestMethod.GET)
    public Object requestPhoneCode(@RequestParam("phone") String phone) throws IOException {
        String code = AppContextUtil.generatePhoneLoginCode(phone);
        logger.info("phone: {}, code: {}", phone, code);
        smsHelper.sendText(phone, code);
        return success();
    }

}
