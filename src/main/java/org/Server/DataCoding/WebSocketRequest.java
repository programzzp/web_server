package org.Server.DataCoding;

import org.Server.request.AnalysisMessage;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class WebSocketRequest {

    private WebsocketCoding websocketCoding;

    private AnalysisMessage analysisMessage;

    public WebSocketRequest(AnalysisMessage analysisMessage){
        websocketCoding=new WebsocketCoding();
        this.analysisMessage=analysisMessage;
    }

//    HTTP/1.1 101 Switching Protocols
//    Upgrade: websocket
//    Connection: Upgrade
//    Sec-WebSocket-Accept: s3pPLMBiTxaQ9kYGzzhZRbK+xOo=
//    Sec-WebSocket-Protocol: chat
    public void webSocket(String WebSocket_Accept, ByteBuffer outBuffer, SocketChannel channel) throws IOException {
        System.out.println(analysisMessage.getUrl());
        SocketSession.setSESSION(analysisMessage.getUrl(),new ConnectLogo(outBuffer,channel));
        System.out.println(channel.hashCode());
        System.out.println(WebSocket_Accept);
        String head1="HTTP/1.1 101 Switching Protocols\r\n";
        String head2="Upgrade: websocket\r\n";
        String head3="Connection: Upgrade\r\n";
        String head4="Sec-WebSocket-Accept: "+websocketCoding.secWebSocketAccept(WebSocket_Accept)+"\r\n\r\n";
        String request=head1+head2+head3+head4;
        System.out.println(request);
        ByteBuffer headerWrap = outBuffer.wrap(request.getBytes());
        channel.write(headerWrap);
    }
}
