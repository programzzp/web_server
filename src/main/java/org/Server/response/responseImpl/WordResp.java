package org.Server.response.responseImpl;

import org.Server.response.responseInterface.ResponseHeader;
import org.Server.utli.Header;
import org.Server.utli.ReasonPhrase;

public class WordResp implements ResponseHeader {

//    String status=analysisMessage.getHttp_version()+" "+request_processing.getCode()+" "+ ReasonPhrase.Phrase(request_processing.getCode())+"\r\n";
    public String Header(String http_version, int code, String type) {

        String status=http_version+" "+code+" "+ReasonPhrase.Phrase(code)+"\r\n";
        Header instance = new Header();
        instance.setHeader("Content-Type",type);
        String header=instance.headerToString();
        return status+header;
    }
}
