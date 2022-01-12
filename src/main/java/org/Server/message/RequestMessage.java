package org.Server.message;

import org.Server.information_processing.Request_processing;
import org.Server.request.AnalysisMessage;
import org.Server.response.ResponseData;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class RequestMessage {
    AnalysisMessage analysisMessage;
    /**
     * 初始化
     */
    public SocketChannel request_init(String message, ByteBuffer outBuffer, SocketChannel channel) throws IOException {
        analysisMessage=new AnalysisMessage();
        analysisMessage.GetMessage(message);
        analysisMessage.analysis_first_head();

        System.out.println(analysisMessage);
        analysisMessage.GetRequestHead();


        Request_processing processing=new Request_processing(analysisMessage);
        processing.url_processing();


        ResponseData responseData=new ResponseData(processing,analysisMessage);

        return responseData.ReturnResponse(outBuffer,channel);


    }
}
