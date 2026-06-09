package com.qywx.app.service;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Base64;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import java.io.StringReader;

public class QywxCallbackCrypto {

    private static final int AES_KEY_LENGTH = 43;
    private static final int RANDOM_BYTES_LENGTH = 16;
    private static final int NETWORK_BYTES_LENGTH = 4;
    private static final int PKCS7_BLOCK_SIZE = 32;

    public String verifyUrl(String token,
                            String encodingAesKey,
                            String corpId,
                            String msgSignature,
                            String timestamp,
                            String nonce,
                            String echoStr) throws Exception {
        if (isBlank(token) || isBlank(encodingAesKey) || isBlank(corpId)
                || isBlank(msgSignature) || isBlank(timestamp) || isBlank(nonce) || isBlank(echoStr)) {
            throw new IllegalArgumentException("missing qywx callback parameter");
        }
        if (encodingAesKey.length() != AES_KEY_LENGTH) {
            throw new IllegalArgumentException("invalid qywx encoding aes key");
        }

        String expectedSignature = sha1(token, timestamp, nonce, echoStr);
        if (!expectedSignature.equals(msgSignature)) {
            throw new IllegalArgumentException("invalid qywx callback signature");
        }

        String decryptedEchoStr = decryptEchoStr(encodingAesKey, echoStr, corpId);
        return decryptedEchoStr;
    }

    public String decryptMessage(String token,
                                 String encodingAesKey,
                                 String corpId,
                                 String msgSignature,
                                 String timestamp,
                                 String nonce,
                                 String postData) throws Exception {
        if (isBlank(token) || isBlank(encodingAesKey) || isBlank(corpId)
                || isBlank(msgSignature) || isBlank(timestamp) || isBlank(nonce) || isBlank(postData)) {
            throw new IllegalArgumentException("missing qywx callback parameter");
        }
        if (encodingAesKey.length() != AES_KEY_LENGTH) {
            throw new IllegalArgumentException("invalid qywx encoding aes key");
        }

        String encrypt = extractEncrypt(postData);
        String expectedSignature = sha1(token, timestamp, nonce, encrypt);
        if (!expectedSignature.equals(msgSignature)) {
            throw new IllegalArgumentException("invalid qywx callback signature");
        }
        return decryptEchoStr(encodingAesKey, encrypt, corpId);
    }

    private String sha1(String token, String timestamp, String nonce, String echoStr) throws Exception {
        String[] values = new String[]{token, timestamp, nonce, echoStr};
        Arrays.sort(values);

        StringBuilder sortedText = new StringBuilder();
        for (String value : values) {
            sortedText.append(value);
        }

        MessageDigest messageDigest = MessageDigest.getInstance("SHA-1");
        byte[] digest = messageDigest.digest(sortedText.toString().getBytes(StandardCharsets.UTF_8));

        StringBuilder hex = new StringBuilder();
        for (byte b : digest) {
            String value = Integer.toHexString(b & 0xff);
            if (value.length() == 1) {
                hex.append('0');
            }
            hex.append(value);
        }
        return hex.toString();
    }

    private String decryptEchoStr(String encodingAesKey, String echoStr, String corpId) throws Exception {
        byte[] aesKey = Base64.getDecoder().decode(encodingAesKey + "=");
        Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
        cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(aesKey, "AES"), new IvParameterSpec(aesKey, 0, 16));

        byte[] decrypted = cipher.doFinal(Base64.getDecoder().decode(echoStr));
        byte[] unpadded = removePkcs7Padding(decrypted);

        ByteBuffer messageLengthBuffer = ByteBuffer.wrap(unpadded, RANDOM_BYTES_LENGTH, NETWORK_BYTES_LENGTH);
        int messageLength = messageLengthBuffer.getInt();
        int messageStart = RANDOM_BYTES_LENGTH + NETWORK_BYTES_LENGTH;
        int messageEnd = messageStart + messageLength;

        String message = new String(Arrays.copyOfRange(unpadded, messageStart, messageEnd), StandardCharsets.UTF_8);
        String decryptedCorpId = new String(Arrays.copyOfRange(unpadded, messageEnd, unpadded.length), StandardCharsets.UTF_8);
        if (!corpId.equals(decryptedCorpId)) {
            throw new IllegalArgumentException("invalid qywx callback corp id");
        }
        return message;
    }

    private byte[] removePkcs7Padding(byte[] decrypted) {
        int pad = decrypted[decrypted.length - 1] & 0xff;
        if (pad < 1 || pad > PKCS7_BLOCK_SIZE) {
            pad = 0;
        }
        return Arrays.copyOfRange(decrypted, 0, decrypted.length - pad);
    }

    private String extractEncrypt(String postData) throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);
        factory.setFeature("http://xml.org/sax/features/external-general-entities", false);
        factory.setFeature("http://xml.org/sax/features/external-parameter-entities", false);
        factory.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);
        factory.setXIncludeAware(false);
        factory.setExpandEntityReferences(false);

        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(new InputSource(new StringReader(postData)));
        NodeList encryptNodes = document.getDocumentElement().getElementsByTagName("Encrypt");
        if (encryptNodes.getLength() == 0) {
            throw new IllegalArgumentException("missing qywx encrypt message");
        }
        return encryptNodes.item(0).getTextContent();
    }

    private boolean isBlank(String value) {
        return value == null || value.trim().isEmpty();
    }
}
