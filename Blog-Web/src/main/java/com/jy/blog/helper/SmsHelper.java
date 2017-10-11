package com.jy.blog.helper;


import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 短信操作封装
 */
public class SmsHelper {

    private String username;

    private String password;

    private String baseUrl;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    @SuppressWarnings("unused")
    public enum ContentType {
        CONTENT_TYPE_NORMAL("8"),
        CONTENT_TYPE_LONG("15");

        private String value;

        ContentType(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }

    public void sendText(String to, String content) throws IOException {
        sendText(to, content, ContentType.CONTENT_TYPE_NORMAL, null, null, null);
    }

    public void sendText(
        String to, String content, ContentType contentType,
        String appendId, String sendTime, String validTime) throws IOException {

        if (username != null && password != null && baseUrl != null) {
            List<BasicNameValuePair> parameters = new ArrayList<>();
            parameters.add(new BasicNameValuePair("OperID", username));
            parameters.add(new BasicNameValuePair("OperPass", password));
            parameters.add(new BasicNameValuePair("DesMobile", to));
            parameters.add(new BasicNameValuePair("Content", content));
            parameters.add(new BasicNameValuePair("ContentType", contentType.getValue()));
            if (appendId != null) {
                parameters.add(new BasicNameValuePair("AppendID", appendId));
            }
            if (sendTime != null) {
                parameters.add(new BasicNameValuePair("SendTime", sendTime));
            }
            if (validTime != null) {
                parameters.add(new BasicNameValuePair("ValidTime", validTime));
            }
            String params = EntityUtils.toString(new UrlEncodedFormEntity(parameters, "UTF-8"));
            HttpGet get = new HttpGet(baseUrl + "?" + params);
            HttpClient client = HttpClients.createDefault();
            client.execute(get);
        }
    }

}
