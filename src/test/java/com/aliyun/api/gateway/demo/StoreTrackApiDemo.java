/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package com.aliyun.api.gateway.demo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.aliyun.api.gateway.demo.constant.Constants;
import com.aliyun.api.gateway.demo.constant.ContentType;
import com.aliyun.api.gateway.demo.constant.HttpHeader;
import com.aliyun.api.gateway.demo.constant.HttpSchema;
import com.aliyun.api.gateway.demo.enums.Method;
import com.aliyun.api.gateway.demo.util.MessageDigestUtil;
import com.aliyun.api.gateway.demo.util.SignUtil;
import org.apache.commons.codec.binary.Base64;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.junit.Test;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;
import java.util.*;

/**
 * 前端调用示例
 */
public class StoreTrackApiDemo {
    // APP KEY
    private final static String APP_KEY = "26010215";
    // APP密钥
    private final static String APP_SECRET = "6abb96c01e20f44d0cc8e2ad17ffaac6";
    //API域名
    private final static String HOST = "storetrack-api.ikea.cn";
    //自定义参与签名Header前缀（可选,默认只有"X-Ca-"开头的参与到Header签名）
    private final static List<String> CUSTOM_HEADERS_TO_SIGN_PREFIX = new ArrayList<String>();

    /**
     * 接口测试
     *
     * @throws Exception
     */
    @Test
    public void test() throws Exception {
        //请求path
        String path = "/sync_data/test";

        Map<String, String> headers = new HashMap<String, String>();
        // API 调用者生成的 UUID，结合时间戳防重放
        headers.put("X-Ca-Nonce", UUID.randomUUID().toString());
        headers.put("Content-Type", "application/json");

        Request request = new Request(Method.GET, HttpSchema.HTTPS + HOST, path, APP_KEY, APP_SECRET, Constants.DEFAULT_TIMEOUT);
        request.setHeaders(headers);
        request.setSignHeaderPrefixList(CUSTOM_HEADERS_TO_SIGN_PREFIX);
        request.setQuerys(new HashMap<String, String>(0));
        //调用服务端
        Response response = Client.execute(request);
        String responseBodyStr = response.getBody();
        JSONObject responseBody = JSON.parseObject(responseBodyStr);
        System.out.println(responseBody);
    }


    /**
     * 视频客流同步
     *
     * @throws Exception
     */
    @Test
    public void syncStoreTrack() throws Exception {
        //请求path
        String path = "/sync_data/sync_with_json";
        //Body内容
        String body = "{\n" +
                "    \"time\": \"2019-04-02 12:30:55\",\n" +
                "    \"unique_id\": \"25f9e794323b453885f5181f1b624d0b\",\n" +
                "    \"floor_id\":\"10\",\n" +
                "    \"floor_name\":\"楼层名\",\n" +
                "    \"leaving_num\":10,\n" +
                "    \"vistor_num\":12,\n" +
                "    \"position\":\"点位名称\",\n" +
                "    \"mall_area\":\"区域\",\n" +
                "    \"traffic_flow\": 40\n" +
                "}";
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
}
