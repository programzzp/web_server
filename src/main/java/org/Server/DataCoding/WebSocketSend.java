package org.Server.DataCoding;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.channels.Channel;
import java.nio.channels.SocketChannel;

/**
 * 暂时只能处理文本数据
 */
public class WebSocketSend {

    public void send(SocketChannel channel, String data, ByteBuffer outBuffer){
        ByteBuffer wrap = outBuffer.wrap(send_DataCode(data));

        try {
            channel.write(wrap);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public byte[] send_DataCode(String news){


        byte[] byte_news = new byte[0];
        try {
            byte_news = news.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        int news_len=byte_news.length;

        int code_01=0x80|1;

        byte[] buf=new byte[news_len+2];
        buf[0]= (byte) code_01;
        buf[1]= (byte) news_len;

        for (int i = 2; i <news_len+2 ; i++) {
            buf[i]=byte_news[i-2];
        }


        return buf;
    }
}
