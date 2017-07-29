package com.ibeesaas.demo.util;

import org.apache.commons.lang3.StringUtils;

/**
 * TaskUtil封装了submitTask，queryTask，patchTask三个方法
 */
public class TaskUtil {
    /**
     * 提交任务
     */
    public static String submitTask(String taskType, String bodyJson){
        System.out.println("submitTask");
        if(StringUtils.isEmpty(taskType)) {
            throw new IllegalArgumentException("invalid tastType");
        }

        StringBuilder paramBuilder = new StringBuilder();
        paramBuilder.append("taskType=").append(taskType);

        if(0 == paramBuilder.length()){
            paramBuilder.append("appKey=").append(Config.APPKEY);
        }else {
            paramBuilder.append("&").append("appKey=").append(Config.APPKEY);
        }

        String queryParam = paramBuilder.toString();
        int expireTime = (int) (System.currentTimeMillis() / 1000 + 3600L); //expire time: one hone later
        String urlPath = Config.URLPATH_PREFIX;

        StringBuilder urlBuilder = new StringBuilder(Config.BASE_URL);
        urlBuilder.append(urlPath);
        String url = urlBuilder.toString();
        if(StringUtils.isNoneBlank(queryParam)){
            url = urlBuilder.append("?").append(queryParam).toString();
        }
        System.out.println("url:"+url);
        System.out.println("bodyJson:"+bodyJson);

        TokenHelper tokenHelper = new TokenHelper(Config.AK, Config.SK);
        String token = tokenHelper.generateToken(urlPath, "POST", queryParam, bodyJson, expireTime);

        String response = HttpClientUtil.doPost(url, bodyJson, token);
        return response;
    }

    /**
     * 查询任务
     */
    public static String queryTask(String taskNo){
        System.out.println("queryTask");

        if(StringUtils.isEmpty(taskNo)) {
            throw new IllegalArgumentException("invalid taskNo");
        }

        String queryParam = "appKey=" + Config.APPKEY;
        String urlPath= Config.URLPATH_PREFIX+"/"+taskNo+"/result";
        int expireTime = (int) (System.currentTimeMillis() / 1000 + 3600L); //expire time: one hone later

        TokenHelper tokenHelper = new TokenHelper(Config.AK, Config.SK);
        String token = tokenHelper.generateToken(urlPath, "GET", queryParam, "", expireTime);

        StringBuilder urlBuilder = new StringBuilder(Config.BASE_URL);
        urlBuilder.append(urlPath);
        String url = urlBuilder.toString();
        if(StringUtils.isNoneBlank(queryParam)){
            url = urlBuilder.append("?").append(queryParam).toString();
        }

        System.out.println("url:"+url);
        System.out.println("token:"+token);
        String response = HttpClientUtil.doGet(url, token);

        return response;
    }

    /**
     * 补充任务
     */
    public static String patchTask(String taskNo, String bodyJson){
        System.out.println("patchTask");

        if(StringUtils.isEmpty(taskNo)) {
            throw new IllegalArgumentException("invalid taskNo");
        }

        String queryParam = "appKey=" + Config.APPKEY;
        String urlPath = Config.URLPATH_PREFIX + "/" + taskNo;
        int expireTime = (int) (System.currentTimeMillis() / 1000 + 3600L); //expire time: one hone later

        TokenHelper tokenHelper = new TokenHelper(Config.AK, Config.SK);
        String token = tokenHelper.generateToken(urlPath, "POST", queryParam, bodyJson, expireTime);

        StringBuilder urlBuilder = new StringBuilder(Config.BASE_URL);
        urlBuilder.append(urlPath);
        String url = urlBuilder.toString();
        if(StringUtils.isNoneBlank(queryParam)){
            url = urlBuilder.append("?").append(queryParam).toString();
        }

        System.out.println("url:"+url);
        System.out.println("bodyJson:"+bodyJson);
        System.out.println("token:"+token);

        String response = HttpClientUtil.doPost(url, bodyJson, token);
        return response;
    }


}
