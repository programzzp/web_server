package org.Server.response.ReturnResponseImpl;

import org.Server.information_processing.Request_processing;
import org.Server.request.AnalysisMessage;
import org.Server.response.ReturnResponseInterfase.ResponseChannel;
import org.Server.response.responseImpl.WordResp;
import org.Server.response.responseInterface.ResponseHeader;
import org.Server.utli.ContextType;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class DocumentationResponse implements ResponseChannel {

    private Request_processing request_processing;

    private AnalysisMessage analysisMessage;

    public void init(Request_processing request_processing, AnalysisMessage analysisMessage) {
        this.request_processing = request_processing;
        this.analysisMessage = analysisMessage;
    }


    public SocketChannel ReturnResponse(ByteBuffer outBuffer, SocketChannel channel) throws IOException {
        /**
         * 获取响应头
         */
        ResponseHeader resp=new WordResp();
        /**
         * 获取文件后缀
         */
        String phrase = ContextType.Phrase(request_processing.getType());
        String header = resp.Header(analysisMessage.getHttp_version(), request_processing.getCode(), phrase);
        /**
         * 发送请求头
         */
        ByteBuffer headerWrap = outBuffer.wrap(header.getBytes());
        channel.write(headerWrap);

        /**
         * 获取请求体
         */
        String body = request_processing.getBody();
        ByteBuffer bodyWrap;
        if (body==null){
            bodyWrap = outBuffer.wrap(" ".getBytes());
        }else{
            bodyWrap = outBuffer.wrap(body.getBytes());
        }
        channel.write(bodyWrap);

        channel.close();

        return channel;
    }
}
