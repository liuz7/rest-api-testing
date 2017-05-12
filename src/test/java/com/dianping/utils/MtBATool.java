package com.dianping.utils;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Base64;

/**
 * Created by georgeliu on 2017/5/4.
 */
public class MtBATool {

    private static final Charset CHARSET = StandardCharsets.ISO_8859_1;
    private static final ZoneId TIME_ZONE = ZoneOffset.UTC;
    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.RFC_1123_DATE_TIME;
    private static final String SIGN_ALGORITHM = "HmacSHA1";

    private MtBATool() {
    }

    /**
     * 认证时间串
     *
     * @return
     */
    public static String authDate() {
        return ZonedDateTime.now(TIME_ZONE).format(TIME_FORMATTER);
    }

    /**
     * 签名
     *
     * @param methodName
     * @param path
     * @param formattedTime
     * @param secret
     * @return
     */
    public static String sign(String methodName, String path, String formattedTime, String secret) {
        String stringToSign = methodName + " " + path + "\n" + formattedTime;
        SecretKeySpec signingKey = new SecretKeySpec(secret.getBytes(CHARSET), SIGN_ALGORITHM);
        try {
            Mac mac = Mac.getInstance(SIGN_ALGORITHM);
            mac.init(signingKey);
            byte[] rawHmac = mac.doFinal(stringToSign.getBytes(CHARSET));
            return new String(Base64.getEncoder().encode(rawHmac), CHARSET);
        } catch (NoSuchAlgorithmException | InvalidKeyException e) {
            throw new IllegalStateException("sign failed. secret=" + secret + " stringToSign=" + stringToSign, e);
        }
    }
}
