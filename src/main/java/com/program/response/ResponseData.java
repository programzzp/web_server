package com.program.response;

import com.program.information_processing.Request_processing;
import com.program.request.AnalysisMessage;
import com.program.utli.ContextType;
import com.program.utli.Header;
import com.program.utli.ReasonPhrase;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class ResponseData {

    private Request_processing request_processing;

    private AnalysisMessage analysisMessage;

    public ResponseData(Request_processing request_processing, AnalysisMessage analysisMessage) {
        this.request_processing = request_processing;
        this.analysisMessage = analysisMessage;
    }

    public SocketChannel ReturnResponse(ByteBuffer outBuffer, SocketChannel channel) throws IOException {
        String status=analysisMessage.getHttp_version()+" "+request_processing.getCode()+" "+ ReasonPhrase.Phrase(request_processing.getCode())+"\r\n";

        String phrase = ContextType.Phrase(request_processing.getType());
        System.out.println(phrase);
        System.out.println(request_processing.getType());
        String[] split = phrase.split("/");
        if (split[0].equals("image")){
            Header instance = new Header();
            instance.setHeader("Content-Type",phrase);
            String header=instance.headerToString();
            byte[] bytes = (status + header).getBytes();
            ByteBuffer wrap = outBuffer.wrap(bytes);
            channel.write(wrap);
            ByteBuffer wrap1 = outBuffer.wrap(request_processing.getStream());
            channel.write(wrap1);
            return channel;
        }

        Header instance =new  Header();
        instance.setHeader("Content-Type",phrase+"; charset=utf-8");
        String header=instance.headerToString();
        String body=request_processing.getBody();
        ByteBuffer wrap = outBuffer.wrap((status+header+body).getBytes());
        channel.write(wrap);
        return channel;
    }
}
