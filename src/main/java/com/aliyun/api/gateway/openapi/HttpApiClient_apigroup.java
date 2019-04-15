package com.aliyun.api.gateway.openapi;//

import com.alibaba.cloudapi.sdk.client.ApacheHttpClient;
import com.alibaba.cloudapi.sdk.enums.HttpMethod;
import com.alibaba.cloudapi.sdk.enums.Scheme;
import com.alibaba.cloudapi.sdk.model.ApiCallback;
import com.alibaba.cloudapi.sdk.model.ApiRequest;
import com.alibaba.cloudapi.sdk.model.ApiResponse;
import com.alibaba.cloudapi.sdk.model.HttpClientBuilderParams;


public class HttpApiClient_apigroup extends ApacheHttpClient{
    public final static String HOST = "4e99dc00a9e345fc9c754d95c4eb1ca1-cn-hangzhou.alicloudapi.com";
    static HttpApiClient_apigroup instance = new HttpApiClient_apigroup();
    public static HttpApiClient_apigroup getInstance(){return instance;}

    @Override
    public void init(HttpClientBuilderParams httpClientBuilderParams){
        httpClientBuilderParams.setScheme(Scheme.HTTP);
        httpClientBuilderParams.setHost(HOST);
        super.init(httpClientBuilderParams);
    }




    public void apitest(ApiCallback callback) {
        String path = "/testpath/hello";
        ApiRequest request = new ApiRequest(HttpMethod.POST_BODY , path, "{}".getBytes());
        


        sendAsyncRequest(request , callback);
    }

    public ApiResponse apitest_syncMode() {
        String path = "/testpath/*";
        ApiRequest request = new ApiRequest(HttpMethod.POST_BODY , path, "{}".getBytes());
        


        return sendSyncRequest(request);
    }

}