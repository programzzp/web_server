package org.Server.DataCoding;

import javax.xml.bind.DatatypeConverter;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class WebsocketCoding {

    private final String GUID ="258EAFA5-E914-47DA-95CA-C5AB0DC85B11";

    private String addGUID(String Key){
        return Key+GUID;
    }

    private byte[] SHA_1_Code(String key) throws NoSuchAlgorithmException {
        return toSHA1(key.getBytes());
    }

    public byte[] toSHA1(byte[] convertme) {
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("SHA-1");
        }
        catch(NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return md.digest(convertme);
    }

    private String base64Coding(byte[] key) throws UnsupportedEncodingException {
        return DatatypeConverter.printBase64Binary(key);
    }

    public String secWebSocketAccept(String key){
        try {
            return this.base64Coding(this.SHA_1_Code(this.addGUID(key)));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }


}
