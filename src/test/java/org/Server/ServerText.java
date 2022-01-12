package org.Server;

import com.alibaba.fastjson.JSON;
import org.Server.information_processing.objectfactory.classfactory.GetController.GetControllerClassJava;
import org.Server.information_processing.objectfactory.classfactory.GetController.GetRequestMappingInformation;
import org.Server.information_processing.objectfactory.classfactory.Getfilepath.GetFilePathMap;
import org.Server.pojo.MethodArray;
import org.Server.utli.Header;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ServerText {
    String data="GET / HTTP/1.1\n" +
            "Host: 127.0.0.1:8080\n" +
            "Connection: keep-alive\n" +
            "sec-ch-ua: \" Not;A Brand\";v=\"99\", \"Google Chrome\";v=\"91\", \"Chromium\";v=\"91\"\n" +
            "sec-ch-ua-mobile: ?0\n" +
            "Upgrade-Insecure-Requests: 1\n" +
            "User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.77 Safari/537.36\n" +
            "Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9\n" +
            "Purpose: prefetch\n" +
            "Sec-Fetch-Site: none\n" +
            "Sec-Fetch-Mode: navigate\n" +
            "Sec-Fetch-User: ?1\n" +
            "Sec-Fetch-Dest: document\n" +
            "Accept-Encoding: gzip, deflate, br\n" +
            "Accept-Language: zh-CN,zh;q=0.9";

    @Test
    public void test() throws InvocationTargetException, IllegalAccessException {
        GetRequestMappingInformation getRequestMappingInformation=new GetRequestMappingInformation();
        Map<String, MethodArray> stringMethodMap = getRequestMappingInformation.GetMethodString();

        MethodArray methodArray = stringMethodMap.get("/path01");
        Object invoke = methodArray.getJavaMethod().invoke(methodArray.getJavaClass());
        String s = JSON.toJSONString(invoke);
        System.out.println(s);
    }
}
