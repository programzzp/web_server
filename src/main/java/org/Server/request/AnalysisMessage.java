package org.Server.request;

import java.util.HashMap;
import java.util.Map;


/**
 * 请求参数的解析
 */
public class AnalysisMessage {
    private Map<String,String> RequestHead=new HashMap<String, String>();
    /**
     * GET或者POST请求
     */
    private String method;
    /**
     * url
     */
    private String url;
    /**
     * 协议版本号
     * HTTP/1.0
     * HTTP/2.0
     */
    private String http_version;
    /**
     * 请求数据
     */
    private String Request_data;
    /**
     * 消息头信息
     */
    private String[] Head;

    /**
     * 获取第一行和后面发请求数据
     * @param requestData
     */
    public void GetMessage(String requestData){
        String[] head_Message = requestData.split("\r\n");
        this.Head=head_Message;
    }

    /**
     *GET请求------获取所有的请全体
     */
    public void GetRequestHead(){

        if (method.equals("GET")){
            for (int i=1;i<Head.length;i++){
                String[]  request = Head[i].split(": ");
                RequestHead.put(request[0],request[1]);
            }
        }else if(method.equals("POST")){
            for (int i=1;i<Head.length-1;i++){
                String[]  request = Head[i].split(": ");
                RequestHead.put(request[0],request[1]);
            }
        }
    }

    /**
     * 解析第一行
     */
    public void analysis_first_head(){
        final int METHOD=0;
        final int URL=1;
        final int HTTP_VERSION=2;

        String first_head=Head[0];
        /**
         *解析头部信息
         */
        String[] head = first_head.split(" ");
        method=head[METHOD];
        if (method.equals("GET")){
            get_GET_MessageData(head[URL]);
        }else if(method.equals("POST")){
            url=head[URL];
            get_POST_MessageData();
        }
        http_version=head[HTTP_VERSION];
    }

    /**
     * 得到Get请求的数据
     */
    public void get_GET_MessageData(String url){
        boolean contains = url.contains("?");
        if (!contains){
            this.url=url;
        }else{
            String[] split = url.split("\\?");
            this.url=split[0];
            this.Request_data=split[1];
        }
    }

    public void get_POST_MessageData(){}

    @Override
    public String toString() {
        return "AnalysisMessage{" +
                "method='" + method + '\'' +
                ", url='" + url + '\'' +
                ", http_version='" + http_version + '\'' +
                ", Request_data='" + Request_data + '\'' +
                '}';
    }

    /**
     * 返回请求体
     * @return
     */
    public Map<String, String> getRequestHead() {
        return RequestHead;
    }

    public String getHttp_version() {
        return http_version;
    }

    public String getUrl() {
        return url;
    }

    public String getRequest_data() {
        return Request_data;
    }
}
