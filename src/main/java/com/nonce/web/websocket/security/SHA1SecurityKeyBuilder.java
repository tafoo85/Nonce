package com.nonce.web.websocket.security;

import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SHA1SecurityKeyBuilder implements ISecurityKeyBuilder {
    private SecurityKey _key;
    
    private static final String ENCRYPTION_ALGORITHM = "SHA-1";
    private static final String CHARSET_ENCODING = "UTF-8";
    protected static final String SECURITY_KEY = "258EAFA5-E914-47DA-95CA-C5AB0DC85B11";
    
    private static final Logger logger = LoggerFactory.getLogger(SHA1SecurityKeyBuilder.class);
    
    @Override
    public void build(String clientNonce) throws NoSuchAlgorithmException {
        String message = clientNonce.concat(SECURITY_KEY);
       
        try {
            MessageDigest digest = MessageDigest.getInstance(ENCRYPTION_ALGORITHM);
            byte[] hashedMessage = digest.digest(message.getBytes(Charset.forName(CHARSET_ENCODING)));
            
            _key = new SecurityKey(hashedMessage);
        } catch (NoSuchAlgorithmException e) {
            logger.debug("Failed to generate the security key: {}", e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }
    
    @Override
    public ISecurityKey getSecurityKey() {
        return this._key;
    }
}
