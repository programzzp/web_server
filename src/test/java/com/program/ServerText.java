package com.program;

import com.program.information_processing.Get_Html_fine;
import com.program.pojo.Head;
import com.program.utli.Header;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public void test(){
//        HashMap<String, String> stringStringHashMap = Get_Html_fine.Get_HTML_FILE();
//        System.out.println(stringStringHashMap);

        for (int i=0;i<3;i++){
            Header instance =new  Header();
            instance.setHeader("key","value");
            System.out.println(instance.headerToString());
        }



    }
}
