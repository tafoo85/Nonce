package com.nonce.web.websocket.security;

import java.security.NoSuchAlgorithmException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nonce.common.exception.security.SecurityKeyCreationException;

public class SecurityKeyFactory {
    private static ISecurityKeyBuilder _builder;
    private static Logger logger = LoggerFactory.getLogger(SecurityKeyFactory.class);
    static {
        _builder = new SHA1SecurityKeyBuilder();
    }
    
    private SecurityKeyFactory() { }
    
    public static ISecurityKey generateSecurityKey(String privateKey) {
        try {
            _builder.build(privateKey);
        } catch (NoSuchAlgorithmException e) {
            logger.error("No Such Algorithm: {}", e.getMessage());
            throw new SecurityKeyCreationException("Failed to generate security key.", e);
        }
        return _builder.getSecurityKey();
    }
}
