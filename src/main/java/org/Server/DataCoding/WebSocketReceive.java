package org.Server.DataCoding;


import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;


/**
 * 接收数据
 */
public class WebSocketReceive {

    //标记这个帧是不是消息中的最后一帧。第一个帧也可是最后一帧。
    private byte FIN;

    /**
     * Opcode: 4 bits
     *
     * 定义了如何解释 “有效负荷数据 Payload data”。如果接收到一个未知的操作码，接收端必须标记 WebSocket 为失败。定义了如下的操作码：
     *
     * %x0 表示这是一个继续帧（continuation frame）
     * %x1 表示这是一个文本帧 （text frame）
     * %x2 表示这是一个二进制帧 （binary frame）
     * %x3-7 为将来的非控制帧（non-control frame）而保留的
     * %x8 表示这是一个连接关闭帧 （connection close）
     * %x9 表示这是一个 ping 帧
     * %xA 表示这是一个 pong 帧
     * %xB-F 为将来的控制帧（control frame）而保留的
     */
    private byte opcode;
    private byte MASK;


    /**
     * Payload length: 7 bits, 7+16 bits, or 7+64 bits
     *
     * 如果是 0-125，那么就直接表示了负荷长度。
     * 如果是 126，那么接下来的两个字节表示(16位)负荷长度。
     * 如果是 127，则接下来的 8 个字节表示(64位)负荷长度。
     */
    private int len;


    byte idx = 2; // 首先分析前两个字节

    private byte[] buf;

    WebSocketSend webSocketSend=new WebSocketSend();


    public void processBuffer(SocketChannel channel,ByteBuffer outBuffer){
        System.out.println("channel="+channel.getClass().hashCode());
        System.out.println("outBuffer="+outBuffer.getClass().hashCode());
        byte[] buffer = outBuffer.array();
        this.buf=buffer;
        this.ParseFirst8Byte(buffer[0]);
        this.ParseSecond8Byte(buffer[1]);
        this.DataLength();
        String s = this.MASK_data();
        ConnectLogo session = SocketSession.getSESSION("/webSocket/username");
        webSocketSend.send(session.getChannel(),s,session.getByteBuffer());
    }

    public void ParseFirst8Byte(byte first_byte8){

        String First8Byte=Integer.toBinaryString(Byte.toUnsignedInt(first_byte8));
        this.FIN= (byte) (((byte)First8Byte.charAt(0))-48);
        //System.out.println("FIN="+this.FIN);

        this.opcode= (byte) (first_byte8&0xf);
        //System.out.println("opcode="+opcode);

    }

    public void ParseSecond8Byte(byte second_byte8){
        java.lang.String Second8Byte = Integer.toBinaryString(Byte.toUnsignedInt(second_byte8));
        this.MASK=(byte) (((byte)Second8Byte.charAt(0))-48);
        //System.out.println("MASK="+this.MASK);

        /**
         *
         */
        this.len=second_byte8&0x7F;
        //System.out.println("len="+this.len);
    }

    /**
     * 如果是 0-125，那么就直接表示了负荷长度。
     * 如果是 126，那么接下来的两个字节表示(16位)负荷长度。
     * 如果是 127，则接下来的 8 个字节表示(64位)负荷长度。
     */
    public void DataLength(){

        /**
         * len=126
         * 表述负荷数据长的为   126bit----65535bit
         * len=127
         * 表述负荷数据长的为 65525bit----31TB
         */
        if (len==126){
            this.len=this.readUInt16BE_len();
            this.idx+=2;
        }else if(len==127){
            this.idx+=8;
        }
    }


    /**
     * 通过MASK判断是否为客户端到服务端的数据
     */
    public String MASK_data(){
        //System.out.println("idx="+this.idx);
        if (MASK==1){
            byte[] maskBytes = slice(idx, idx + 4);
            idx+=4;
            byte[] data = slice(idx, idx+this.len);

            return this.unmask(maskBytes, data);
        }
        return null;
    }

    /**
     * 截取数据，得到新的数据
     * @param start
     * @param end
     * @return
     */
    public byte[] slice(int start,int end){

        byte[] bytes=new byte[end-start];

        for (int i=start;i<end;i++){
            bytes[i-start]=this.buf[i];
        }

        return bytes;
    }

//    function unmask(maskBytes, data) {
//        var payload = Buffer.alloc(data.length);
//        for (var i = 0; i < data.length; i++) { // 遍历真实数据
//            payload[i] = maskBytes[i % 4] ^ data[i]; // 掩码有4个字节依次与真实数据进行异或运算即可
//        }
//        return payload;
//    };

    /**
     * 获取真实的数据
     * @param maskBytes
     * @param data
     */
    public String unmask(byte[] maskBytes,byte[] data){
        byte[] payload=data;
        for (int i=0;i<data.length;i++){
            payload[i]= (byte) (maskBytes[i%4]^data[i]);
        }

        try {
           return new String(payload,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }


    public String Byte8Fill_in(byte data){
        String temp=null;

        int unsignedInt = Byte.toUnsignedInt(data);
        String byteData = Integer.toBinaryString(unsignedInt);

        if (byteData.length()!=8){
            int len=8-byteData.length();
            char[] bye=new char[len];
            for (int i = 0; i <len ; i++) {
                bye[i]='0';
            }
            String add=new String(bye);
            temp=add+byteData;
            return temp;
        }
        return byteData;
    }



    public int readUInt16BE_len(){
        byte[] bytes=new byte[2];
        for (int i=2;i<4;i++){
            bytes[i-2]=this.buf[i];
        }

        String data = Byte8Fill_in(bytes[0])+Byte8Fill_in(bytes[1]);

        return Integer.parseUnsignedInt(data, 2);

    }

}