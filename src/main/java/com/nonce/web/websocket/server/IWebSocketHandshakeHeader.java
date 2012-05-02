package com.nonce.web.websocket.server;

import com.nonce.web.websocket.security.ISecurityKey;

public interface IWebSocketHandshakeHeader {
    public ISecurityKey getSecurityKey();
    public String getConnectionString();
    public String getUpgradeString();
    public String getHost();
    public String getOrigin();
    public int getSecurityVersion();
    
    public boolean isValid();
}
