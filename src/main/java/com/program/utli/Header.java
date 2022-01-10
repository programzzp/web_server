package com.program.utli;

import com.program.pojo.Head;

import java.util.*;


/**
 * 设置请求头
 */
public class Header {

//    Accept-Ranges: bytes
//    Content-Length: 1429
//    Content-Type: text/html
//    Date: Mon, 10 Jan 2022 09:39:28 GMT
//    ETag: W/"1429-1639646679413"
//    Last-Modified: Thu, 16 Dec 2021 09:24:39 GMT
    private List<Head> header;


    public Header(){
        /**
         * 设置响应头的基本信息
         */
        header=new ArrayList<Head>();
        header.add(new Head("Server","FYLServer"));
        header.add(new Head("Accept-Ranges","bytes"));
    }


    public void setHeader(String key,String value){
        header.add(new Head(key, value));
    }

    public List<Head> getHeader(){
        return header;
    }


    /**
     * 返回响应头的字符串
     * @return
     */
    public String headerToString(){
        String headerString="";
        for (Head head : header) {
            headerString=headerString+head.getKey()+": "+head.getValue()+"\r\n";
        }
        return headerString+"\r\n";
    }
}
