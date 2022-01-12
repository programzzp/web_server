package org.Server.response;

import org.Server.information_processing.Request_processing;
import org.Server.request.AnalysisMessage;
import org.Server.response.ReturnResponseImpl.DocumentationResponse;
import org.Server.response.ReturnResponseImpl.StreamResponse;
import org.Server.response.ReturnResponseInterfase.ResponseChannel;
import org.Server.utli.ContextType;
import org.Server.utli.Header;
import org.Server.utli.ReasonPhrase;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class ResponseData {

    private ResponseChannel responseChannel;

    private Request_processing request_processing;

    private AnalysisMessage analysisMessage;

    public ResponseData(Request_processing request_processing, AnalysisMessage analysisMessage) {
        this.request_processing = request_processing;
        this.analysisMessage = analysisMessage;
    }


    /**
     * 响应方法
     * @param outBuffer
     * @param channel
     * @return
     * @throws IOException
     */
    public SocketChannel ReturnResponse(ByteBuffer outBuffer, SocketChannel channel) throws IOException {

        String phrase = ContextType.Phrase(request_processing.getType());
        System.out.println(phrase);
        System.out.println(request_processing.getType());
        String[] split = phrase.split("/");
        if (split[0].equals("image")){
            responseChannel=new StreamResponse();
            responseChannel.init(request_processing,analysisMessage);
            return responseChannel.ReturnResponse(outBuffer,channel);
        }
        responseChannel=new DocumentationResponse();
        responseChannel.init(request_processing,analysisMessage);
        return responseChannel.ReturnResponse(outBuffer,channel);
    }
}
