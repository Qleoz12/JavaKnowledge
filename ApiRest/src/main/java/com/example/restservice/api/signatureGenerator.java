package com.example.restservice.api;

import org.apache.commons.codec.binary.Hex;

import javax.crypto.Mac;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;


public class signatureGenerator
{
    //@API_SECRET, clave secreta suministrada mediante correo electronico enviado a cada entidad
    final static  String API_SECRET = "jds8&ffhjhg&g*a+ho(-%y7tn)bdg45ndw29y9kf_^8z2i05-#";
    private static String json="{\"username\": \"eltorresc@fna.gov.co\", \"password\": \"Uu?vZwoB6]&3E')*O,!b\"}";

    public static String encode(String key, String data) throws Exception {
        Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
        SecretKeySpec secret_key = new SecretKeySpec(key.getBytes("UTF-8"), "HmacSHA256");
        sha256_HMAC.init(secret_key);

        return Hex.encodeHexString(sha256_HMAC.doFinal(data.getBytes("UTF-8")));
    }

    public static void main(String[] args) {
        byte[] api_key_bytes = API_SECRET.getBytes(StandardCharsets.UTF_8);

        String path = "https://qasmart.superfinanciera.gov.co/api/queja/";
        try {
            String Response=encode(API_SECRET,json);
            System.out.println(Response);

            Response=encode(API_SECRET,path);
            System.out.println(Response);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
