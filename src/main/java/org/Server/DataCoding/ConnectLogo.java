package org.Server.DataCoding;

import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class ConnectLogo {
    private ByteBuffer byteBuffer;
    private SocketChannel channel;

    public ConnectLogo(ByteBuffer byteBuffer,SocketChannel channel){
        this.byteBuffer=byteBuffer;
        this.channel=channel;
    }

    public ByteBuffer getByteBuffer() {
        return byteBuffer;
    }

    public SocketChannel getChannel() {
        return channel;
    }
}
