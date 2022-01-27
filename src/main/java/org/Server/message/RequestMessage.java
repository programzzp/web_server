package org.Server.message;

import org.Server.DataCoding.WebSocketRequest;
import org.Server.information_processing.Request_processing;
import org.Server.request.AnalysisMessage;
import org.Server.response.ResponseData;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Map;

public class RequestMessage {
    AnalysisMessage analysisMessage;


    /**
     * 初始化
     */
    public SocketChannel request_init(String message, ByteBuffer outBuffer, SocketChannel channel) throws IOException {
        analysisMessage=new AnalysisMessage();
        analysisMessage.GetMessage(message);
        analysisMessage.analysis_first_head();

        analysisMessage.GetRequestHead();
        WebSocketRequest webSocketRequest=new WebSocketRequest(analysisMessage);



        if (analysisMessage.getHttp_version().equals("HTTP/1.1")&&analysisMessage.getMethod().equals("GET")&&analysisMessage.getRequestHead().get("Upgrade")!=null){
            webSocketRequest.webSocket(analysisMessage.getRequestHead().get("Sec-WebSocket-Key"),outBuffer,channel);
        }else{
            Request_processing processing=new Request_processing(analysisMessage);
            processing.url_processing();


            ResponseData responseData=new ResponseData(processing,analysisMessage);

            return responseData.ReturnResponse(outBuffer,channel);
        }

        return null;

    }
}
