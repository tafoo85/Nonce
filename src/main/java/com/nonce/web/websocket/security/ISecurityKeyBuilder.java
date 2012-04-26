package com.nonce.web.websocket.security;

import java.security.NoSuchAlgorithmException;

public interface ISecurityKeyBuilder {
    public void build(String clientNonce) throws NoSuchAlgorithmException;
    public ISecurityKey getSecurityKey();
}
