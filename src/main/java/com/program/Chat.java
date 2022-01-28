package com.program;

import org.Server.DataCoding.ConnectLogo;
import org.Server.DataCoding.SocketSession;
import org.Server.DataCoding.WebSocketSend;
import org.Server.information_processing.objectfactory.annotation.WebSocketAnnotation.*;

import java.util.ArrayList;
import java.util.List;

@ServerEndpoint(value = "/websocket")
public class Chat implements ServerChat {

    private static List<String> user=new ArrayList();

    private WebSocketSend webSocketSend=new WebSocketSend();


    @onOpen
    public void onOpen(String username) {
        System.out.println("onOpen="+username);
        user.add(username);
    }


    @onMessage
    public void onMessage(String message) {
        System.out.println(user);
        for (String s : user) {
            System.out.println(s);
        }
        System.out.println("message="+message);
        webSocketSend.send("username","你好");
    }

    @onClose
    public void onClose(String username) {
        System.out.println("close="+username);
        user.remove(username);
    }
}
