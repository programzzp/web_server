package com.program.utli;

import java.util.HashMap;
import java.util.Map;

public class ContextType {
//    text/html ： HTML格式
//    text/plain ：纯文本格式
//    text/xml ： XML格式
//    image/gif ：gif图片格式
//    image/jpeg ：jpg图片格式
//    image/png：png图片格式
//    以application开头的媒体格式类型：
//
//    application/xhtml+xml ：XHTML格式
//    application/xml： XML数据格式
//    application/atom+xml ：Atom XML聚合格式
//    application/json： JSON数据格式
//    application/pdf：pdf格式
//    application/msword ： Word文档格式
//    application/octet-stream ： 二进制流数据（如常见的文件下载）
    private static Map<String,String> type;

    static {
        type=new HashMap<String, String>();
        type.put("html","text/html");
        type.put("css","text/css");
        type.put("js","application/x-javascript");
        type.put("plain","text/plain");
        type.put("xml","text/xml");
        //图片type
        /**
         * jpg、jpeg、png、gif、
         */
        type.put("ico"," image/ico");
        type.put("gif"," image/gif");
        type.put("jpeg","image/jpeg");
        type.put("png","image/png");
        type.put("jpg","image/jpg");
        //---------------------------------------------
        type.put("xhtml+xml","application/xhtml+xml");
        type.put("json","application/json");
        type.put("msword","application/msword");
        type.put("octet-stream","application/octet-stream");
    }

    public static String Phrase(String format){
        return type.get(format);
    }
}
