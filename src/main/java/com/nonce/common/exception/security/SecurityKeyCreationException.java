package com.nonce.common.exception.security;

import com.nonce.common.exception.NonceException;

public class SecurityKeyCreationException extends NonceException {
    private static final long serialVersionUID = 1L;

    public SecurityKeyCreationException(String message, Exception inner) {
        super(message, inner);
    }
}
