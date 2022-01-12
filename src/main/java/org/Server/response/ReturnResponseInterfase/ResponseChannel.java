package org.Server.response.ReturnResponseInterfase;

import org.Server.information_processing.Request_processing;
import org.Server.request.AnalysisMessage;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public interface ResponseChannel {
    public SocketChannel ReturnResponse(ByteBuffer outBuffer, SocketChannel channel) throws IOException;

    public void init(Request_processing request_processing, AnalysisMessage analysisMessage);
}
