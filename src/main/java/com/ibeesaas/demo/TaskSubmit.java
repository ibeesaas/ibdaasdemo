package com.ibeesaas.demo;

import com.ibeesaas.demo.util.Config;
import com.ibeesaas.demo.util.JsonUtils;
import com.ibeesaas.demo.util.TaskUtil;

import java.util.LinkedHashMap;
import java.util.Map;


public class TaskSubmit
{
    public static void main( String[] args )
    {
        //本demo以淘宝账号密码登录方式为示例，其他数据类型的登录方式，请自行阅读api文档。
        String taskType = Config.TYPE_TAOBAO;

        Map<String, Object> pMap = new LinkedHashMap<String, Object>();
        pMap.put("loginType", 0);
        pMap.put("account", "淘宝账号");
        pMap.put("password", "登录密码");
        Map<String, Object> pBody = new LinkedHashMap<String, Object>();
        pBody.put("data", pMap);
        pBody.put("callbackUrl", ""); //callbackUrl根据实际情况填写，如果想通过查询方式获取结果，此处可设为""
        String bodyJson = JsonUtils.objectToJson(pBody);

        String response = TaskUtil.submitTask(taskType, bodyJson);
        System.out.println(response);
    }
}
