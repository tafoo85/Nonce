package com.nonce.common.exception;

public class NonceException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    protected String _message;
    
    public NonceException(String message, Exception inner) {
        this.initCause(inner);
        this._message = message;
    }
}
