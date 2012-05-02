package com.nonce.web.websocket.server;

import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import com.nonce.web.websocket.security.ISecurityKey;
import com.nonce.web.websocket.security.SecurityKeyFactory;

public class WebSocketHandshakeHeader implements IWebSocketHandshakeHeader {
    protected static final String UPGRADE_HEADER = "upgrade";
    protected static final String CONNECTION_HEADER = "connection";
    protected static final String ORIGIN_HEADER = "origin";
    protected static final String HOST_HEADER = "host";
    protected static final String SEC_WEBSOCKET_KEY_HEADER = "sec-websocket-key";
    protected static final String SEC_WEBSOCKET_VERSION_HEADER = "sec-websocket-version";
    protected static final String SEC_WEBSOCKET_EXTENSIONS_HEADER = "Sec-WebSocket-Extensions";
    
    protected static final String PERMISSABLE_UPGRADE_VALUE = "websocket";
    protected static final String PERMISSABLE_CONNECTION_VALUE = "Upgrade";
    
    protected TreeMap<String, String> _headers;
    protected ISecurityKey _key;
    
    public WebSocketHandshakeHeader(HttpServletRequest request) {
        _headers = new TreeMap<String, String>();
        
        _headers.put(UPGRADE_HEADER, request.getHeader(UPGRADE_HEADER));
        _headers.put(CONNECTION_HEADER, request.getHeader(CONNECTION_HEADER));
        _headers.put(ORIGIN_HEADER, request.getHeader(ORIGIN_HEADER));
        _headers.put(HOST_HEADER, request.getHeader(HOST_HEADER));
        _headers.put(SEC_WEBSOCKET_VERSION_HEADER, request.getHeader(SEC_WEBSOCKET_VERSION_HEADER));
        _headers.put(SEC_WEBSOCKET_KEY_HEADER, request.getHeader(SEC_WEBSOCKET_KEY_HEADER));
        _headers.put(SEC_WEBSOCKET_EXTENSIONS_HEADER, request.getHeader(SEC_WEBSOCKET_EXTENSIONS_HEADER));
        
        _key = SecurityKeyFactory.generateSecurityKey(request.getHeader(SEC_WEBSOCKET_KEY_HEADER));
    }
    
    @Override
    public ISecurityKey getSecurityKey() {
        return _key;
    }

    @Override
    public String getConnectionString() {
        return _headers.get(CONNECTION_HEADER);
    }

    @Override
    public String getUpgradeString() {
        return _headers.get(UPGRADE_HEADER);
    }

    @Override
    public String getHost() {
        return _headers.get(HOST_HEADER);
    }

    @Override
    public String getOrigin() {
        return _headers.get(ORIGIN_HEADER);
    }
    
    @Override 
    public int getSecurityVersion() {
        return Integer.parseInt(_headers.get(SEC_WEBSOCKET_VERSION_HEADER));
    }
    
    @Override
    public boolean isValid() {
        boolean upgradeHeader =_headers.get(UPGRADE_HEADER).equals(PERMISSABLE_UPGRADE_VALUE);
        boolean connectionHeader = _headers.get(CONNECTION_HEADER).equals(PERMISSABLE_CONNECTION_VALUE);
        boolean validKey =_key.isValid();
        
        return upgradeHeader && connectionHeader && validKey;
    }
}
