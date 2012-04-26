package com.nonce.web.websocket.server;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class WebSocketHandshakeResponseHeader extends WebSocketHandshakeHeader {
    private static final String SEC_WEBSOCKET_ACCEPT_HEADER = "Sec-WebSocket-Accept";
            
    public WebSocketHandshakeResponseHeader(HttpServletRequest request) {
        super(request);
    }
    
    public void writeResponse(HttpServletResponse response) throws IOException {
        response.setStatus(101);
        response.addHeader(UPGRADE_HEADER, this.getUpgradeString());
        response.addHeader(CONNECTION_HEADER, this.getConnectionString());
        response.addHeader(SEC_WEBSOCKET_ACCEPT_HEADER, this.getSecurityKey().toBase64String());
        response.flushBuffer();
    }
}
