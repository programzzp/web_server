package org.Server.utli;

import java.util.HashMap;
import java.util.Map;

public class ReasonPhrase {

    private static Map<Integer,String> reason;

    static {
        reason=new HashMap<Integer, String>();
        reason.put(200,"OK");
        reason.put(404,"Not Found");
        reason.put(500,"Internal Server Error");
    }

    public static String Phrase(int code){
            return reason.get(code);
        }
}
