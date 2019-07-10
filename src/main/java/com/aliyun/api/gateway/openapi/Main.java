package com.aliyun.api.gateway.openapi;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.aliyun.api.gateway.demo.Client;
import com.aliyun.api.gateway.demo.Request;
import com.aliyun.api.gateway.demo.Response;
import com.aliyun.api.gateway.demo.constant.Constants;
import com.aliyun.api.gateway.demo.constant.ContentType;
import com.aliyun.api.gateway.demo.constant.HttpHeader;
import com.aliyun.api.gateway.demo.constant.HttpSchema;
import com.aliyun.api.gateway.demo.enums.Method;

import java.util.*;

/**
 * @author voidm
 * @date 2019-05-22
 */
public class Main {

    // 203724351
    // 30j96k3rew4jdk6rxh89nw8e0ec2ox0a
    // APP KEY
    private final static String APP_KEY = "";
    // APP密钥
    private final static String APP_SECRET = "";
    //API域名
    private final static String HOST = "xxx.cn";
    //自定义参与签名Header前缀（可选,默认只有"X-Ca-"开头的参与到Header签名）
    private final static List<String> CUSTOM_HEADERS_TO_SIGN_PREFIX = new ArrayList<String>();

    /**
     * 接口测试
     *
     * @throws Exception
     */
    public static void test() throws Exception {
        //请求path
        // String path = "/sync_data/sync_with_json";
        String path = "/sync_data/test";
        //Body内容
        String body = "{}";
        // String body = "{}";
        Map<String, String> headers = new HashMap<String, String>();
        //（必填）根据期望的Response内容类型设置
        headers.put(HttpHeader.HTTP_HEADER_ACCEPT, "application/json");
        //（POST/PUT请求必选）请求Body内容格式
        headers.put(HttpHeader.HTTP_HEADER_CONTENT_TYPE, ContentType.CONTENT_TYPE_TEXT);
        // API 调用者生成的 UUID，结合时间戳防重放
        headers.put("X-Ca-Nonce", UUID.randomUUID().toString());

        Request request = new Request(Method.POST_STRING, HttpSchema.HTTPS + HOST, path, APP_KEY, APP_SECRET, Constants.DEFAULT_TIMEOUT);
        request.setHeaders(headers);
        request.setSignHeaderPrefixList(CUSTOM_HEADERS_TO_SIGN_PREFIX);
        // Query
        // request.setQuerys(new HashMap<String, String>(0));
        request.setStringBody(body);

        //调用服务端
        Response response = Client.execute(request);
        String responseBodyStr = response.getBody();
        JSONObject responseBody = JSON.parseObject(responseBodyStr);
        System.out.println(responseBody);
    }

    public static void main(String[] args) throws Exception {
        test();
    }
}
