package com.program.information_processing;

import com.program.message.RequestMessage;
import com.program.request.AnalysisMessage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Set;

/**
 * 请求处理类
 */
public class Request_processing {


    /**
     * 获取的资源为流的文件
     */
    private byte[] Stream;

    /**
     * 获取资源问文档的资源
     */
    private String body;

    /**
     * 获取网页返回的状态码
     */
    private int Code;

    /**
     * 获取文件的类型
     */
    private String type;

    /**
     * 获取请求的数据
     */
    private AnalysisMessage analysisMessage;

    public Request_processing(AnalysisMessage analysisMessage){
        this.analysisMessage=analysisMessage;
    }

    /**
     * 获取url中的路径名
     */
    public void url_processing(){
        String url = analysisMessage.getUrl();
        /**
         * 如果 为 / 或者 /index.html就访问 index.html文件
         */
        if (url.equals("/")||url.equals("/index.html")){
            url="index.html";
            this.type=url.split("\\.")[1];
            QueryFile(url);
            //如果没有参数就访问index.html
        }else{
            /**
             * 去除请求中路径的  /
             */
            String newUrl = url.substring(1);
            /**
             * 获取文件类型
             */
            this.type=newUrl.split("\\.")[1];
            QueryFile(newUrl);
        }

    }

    /**
     * 查看web路径下是否有对应文件
     * @param url
     */
    private void QueryFile(String url){
        HashMap<String, String> stringStringHashMap = Get_Html_fine.Get_HTML_FILE();
        Set<String> strings = stringStringHashMap.keySet();
        boolean contains = strings.contains(url);
        if (contains){
            this.Code=200;
            GetBody(url);
        }else {
            this.Code=404;
        }
    }

    /**
     * 获取文件
     * @param url 文件名
     */
    private void GetBody(String url){
        System.out.println("=============================GetBody========================="+type+"=============");
        byte[] bytes=new byte[1024*1024];
        HashMap<String, String> stringStringHashMap = Get_Html_fine.Get_HTML_FILE();
        String file = stringStringHashMap.get(url);
        try {
            FileInputStream inputStream=new FileInputStream(file);
            inputStream.read(bytes);
            if (type.equals("html")||type.equals("css")||type.equals("js")){
                System.out.println("new String(bytes)"+new String(bytes));
                body=new String(bytes);
            }else{
                this.Stream=bytes;
            }
            inputStream.close();
            bytes.clone();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getCode() {
        return Code;
    }

    public String getBody() {
        return body;
    }

    public String getType() {
        return type;
    }

    public byte[] getStream() {
        return Stream;
    }
}
