package com.nonce.common.exception;

import com.nonce.web.websocket.SocketError;

public class NonceSocketException extends NonceException {
    private static final long serialVersionUID = 1L;
    
    private SocketError _error;
    
    public NonceSocketException(String message, SocketError error, Exception inner) {
        super(message, inner);
        this._error = error;
    }
    
    public NonceSocketException(String message, SocketError error) {
        super(message);
        this._error = error;
    }
    
    public SocketError getSocketError() {
        return this._error;
    }
}
