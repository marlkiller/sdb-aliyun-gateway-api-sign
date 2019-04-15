package com.aliyun.api.gateway.openapi;//
//  Created by  fred on 2016/10/26.
//  Copyright © 2016年 Alibaba. All rights reserved.
//

import com.alibaba.cloudapi.sdk.constant.SdkConstant;
import com.alibaba.cloudapi.sdk.model.ApiCallback;
import com.alibaba.cloudapi.sdk.model.ApiRequest;
import com.alibaba.cloudapi.sdk.model.ApiResponse;
import com.alibaba.cloudapi.sdk.model.HttpClientBuilderParams;

import java.io.IOException;


public class Demo_apigroup {


    static{
        //HTTP Client init
        HttpClientBuilderParams httpParam = new HttpClientBuilderParams();
        httpParam.setAppKey("26026601");
        httpParam.setAppSecret("ad738396fa9ec97209bd70c9a618f257");
        HttpApiClient_apigroup.getInstance().init(httpParam);
    }

    public static void main(String[] args) {
        apitestHttpTest();
    }

    public static void apitestHttpTest(){
        HttpApiClient_apigroup.getInstance().apitest(new ApiCallback() {
            @Override
            public void onFailure(ApiRequest request, Exception e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(ApiRequest request, ApiResponse response) {
                try {
                    System.out.println(getResultString(response));
                }catch (Exception ex){
                    ex.printStackTrace();
                }
            }
        });
    }

    public static void apitestHttpSyncTest(){
        ApiResponse response = HttpApiClient_apigroup.getInstance().apitest_syncMode();
        try {
            System.out.println(getResultString(response));
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }




    private static String getResultString(ApiResponse response) throws IOException {
        StringBuilder result = new StringBuilder();
        result.append("Response from backend server").append(SdkConstant.CLOUDAPI_LF).append(SdkConstant.CLOUDAPI_LF);
        result.append("ResultCode:").append(SdkConstant.CLOUDAPI_LF).append(response.getCode()).append(SdkConstant.CLOUDAPI_LF).append(SdkConstant.CLOUDAPI_LF);
        if(response.getCode() != 200){
            result.append("Error description:").append(response.getHeaders().get("X-Ca-Error-Message")).append(SdkConstant.CLOUDAPI_LF).append(SdkConstant.CLOUDAPI_LF);
        }

        result.append("ResultBody:").append(SdkConstant.CLOUDAPI_LF).append(new String(response.getBody() , SdkConstant.CLOUDAPI_ENCODING));

        return result.toString();
    }

}
