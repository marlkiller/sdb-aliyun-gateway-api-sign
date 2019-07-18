//
//  Created by  fred on 2017/1/12.
//  Copyright © 2016年 Alibaba. All rights reserved.
//

package com.aliyun.api.gateway.openapi;

import com.alibaba.cloudapi.sdk.client.ApacheHttpClient;
import com.alibaba.cloudapi.sdk.enums.HttpMethod;
import com.alibaba.cloudapi.sdk.enums.ParamPosition;
import com.alibaba.cloudapi.sdk.enums.Scheme;
import com.alibaba.cloudapi.sdk.model.ApiCallback;
import com.alibaba.cloudapi.sdk.model.ApiRequest;
import com.alibaba.cloudapi.sdk.model.ApiResponse;
import com.alibaba.cloudapi.sdk.model.HttpClientBuilderParams;
import com.alibaba.cloudapi.sdk.enums.WebSocketApiType;
import com.alibaba.fastjson.JSONObject;

public class HttpsApiClient_StoreTrack_API_Group extends ApacheHttpClient{
    public final static String HOST = "storetrack-api.ikea.cn";
    static HttpsApiClient_StoreTrack_API_Group instance = new HttpsApiClient_StoreTrack_API_Group();
    public static HttpsApiClient_StoreTrack_API_Group getInstance(){return instance;}

    @Override
    public void init(HttpClientBuilderParams httpClientBuilderParams){
        httpClientBuilderParams.setScheme(Scheme.HTTPS);
        httpClientBuilderParams.setHost(HOST);
        super.init(httpClientBuilderParams);
    }



    public void store_track_api(String param , ApiCallback callback) {
        String path = "/sync_data/[param]";
        ApiRequest request = new ApiRequest(HttpMethod.GET , path, "".getBytes());
        request.addParam("param" , param , ParamPosition.PATH , true);

        sendAsyncRequest(request , callback);
    }

    public ApiResponse store_track_api_syncMode(String param) {
        String path = "/sync_data/[param]";
        ApiRequest request = new ApiRequest(HttpMethod.GET , path, null);
        request.addParam("param" , param , ParamPosition.PATH , true);
        return sendSyncRequest(request);
    }

}