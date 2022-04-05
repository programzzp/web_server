package org.Server;

import org.Server.DataCoding.WebSocketReceive;
import org.Server.DataCoding.WebsocketCoding;
import org.Server.information_processing.objectfactory.classfactory.GetController.AnnotationContainer.GetAnnotationContainer;
import org.Server.information_processing.objectfactory.classfactory.GetController.AnnotationContainer.GetControllerClassJava;
import org.Server.information_processing.objectfactory.classfactory.GetController.AnnotationContainer.GetServerEndpointClassJava;
import org.Server.information_processing.objectfactory.classfactory.GetController.GetRequestMappingInformation;
import org.Server.information_processing.objectres_info.MapMethodArrayInfo;
import org.Server.information_processing.objectres_info.MapMethodArrayInfoImpl;
import org.Server.pojo.MethodArray;
import org.Server.pojo.RespInformation;
import org.junit.Test;

import javax.xml.bind.DatatypeConverter;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

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
    public void test() throws InvocationTargetException, IllegalAccessException, InstantiationException {

        String name="username=scacdw&password=awdfawv";

        GetRequestMappingInformation getRequestMappingInformation=new GetRequestMappingInformation();
        Map<String, MethodArray> stringMethodMap = getRequestMappingInformation.GetMethodString();

        MethodArray methodArray = stringMethodMap.get("/");
        System.out.println(methodArray);

        MapMethodArrayInfo mapMethodArrayInfo=new MapMethodArrayInfoImpl();
        boolean b = mapMethodArrayInfo.JustStringMethodMap(stringMethodMap,"/path0");
        RespInformation respInformation = mapMethodArrayInfo.ReturnRespInformation(stringMethodMap, "/path01", "GET", "username=qwe&password=123");
        System.out.println(respInformation);
    }


    @Test
    public void test02(){
        GetControllerClassJava getControllerClassJava=new GetControllerClassJava();
        List<String> list = getControllerClassJava.GetClassAnnotationPath();
        for (String s : list) {
            System.out.println(s);
        }
    }


    @Test
    public void test03() throws NoSuchAlgorithmException {
        MessageDigest instance = MessageDigest.getInstance("SHA-1");
        byte[] digest = instance.digest("6qI3SVmZrtYu2QGxt/N+/w==".getBytes());
        BigInteger no = new BigInteger(1, digest);
        String hashtext = no.toString(16);
        hashtext=hashtext+hashtext;
        System.out.println(hashtext);
        while (hashtext.length() < 32) {
            hashtext = "0" + hashtext;
        }
        System.out.println(hashtext);
    }

    @Test
    public void base_64() throws UnsupportedEncodingException {
        String key="6qI3SVmZrtYu2QGxt/N+/w==";
        byte[] bytes = key.getBytes("utf-8");
        String s = DatatypeConverter.printBase64Binary(bytes);
        System.out.println(s);
    }

    @Test
    public  void coding(){
        WebsocketCoding coding=new WebsocketCoding();
        String s = coding.secWebSocketAccept("w4v7O6xFTi36lq3RNcgctw==");
        System.out.println(s);
    }


    @Test
    public void biteDemo(){
        byte data=126;
        WebSocketReceive socketUtil=new WebSocketReceive();
        socketUtil.Byte8Fill_in(data);
        int i = Integer.parseUnsignedInt("01101010", 2);
        System.out.println(i);
    }


    @Test
    public void Byte(){
        short number=0x0000;
        int p1=0;
        byte[] bytes=new byte[2];
        bytes[0]=0x78;
        bytes[1]= (byte) 0xfa;
        

        System.out.println(Short.toUnsignedInt(number));
    }
}
