package com.nonce.web.websocket.security;

public interface ISecurityKey {
    public String toBase64String();
    public boolean isValid();
}
