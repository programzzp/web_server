package org.Server.DataCoding;

import java.util.HashMap;
import java.util.Map;

public class SocketSession {

    private static Map<String,ConnectLogo> SESSION=new HashMap();
    private static int start=0;

    public static void setSESSION(String username,ConnectLogo socketSend) {
        SESSION.put(username,socketSend);
    }

    public static ConnectLogo getSESSION(String username) {
        return SESSION.get(username);
    }
}
