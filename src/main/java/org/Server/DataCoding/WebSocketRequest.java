package org.Server.DataCoding;

import org.Server.information_processing.objectfactory.classfactory.GetController.AnnotationContainer.GetServerEndpointClassJava;
import org.Server.request.AnalysisMessage;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.List;

public class WebSocketRequest {

    private WebsocketCoding websocketCoding;

    private AnalysisMessage analysisMessage;

    GetServerEndpointClassJava getServerEndpointClassJava=new GetServerEndpointClassJava();

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
        String username = this.GetUsername(analysisMessage.getUrl());
        SocketSession.setSESSION(username,new ConnectLogo(outBuffer,channel));
        this.onOpen(username);
        String head1="HTTP/1.1 101 Switching Protocols\r\n";
        String head2="Upgrade: websocket\r\n";
        String head3="Connection: Upgrade\r\n";
        String head4="Sec-WebSocket-Accept: "+websocketCoding.secWebSocketAccept(WebSocket_Accept)+"\r\n\r\n";
        String request=head1+head2+head3+head4;
        System.out.println(request);
        ByteBuffer headerWrap = outBuffer.wrap(request.getBytes());
        channel.write(headerWrap);
    }

    private String GetUsername(String username){
        String[] split = username.split("/");
        return split[split.length-1];
    }

    private void onOpen(String username){
        List<String> list = getServerEndpointClassJava.GetClassAnnotationPath();
        for (String message : list) {
            Class<?> aClass = null;
            try {
                aClass = Class.forName(message);
                Method[] methods = aClass.getMethods();
                for (Method method : methods) {
                    if (method.getName().equals("onOpen")){
                        method.invoke(aClass.newInstance(),username);
                    }
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
    }
}
