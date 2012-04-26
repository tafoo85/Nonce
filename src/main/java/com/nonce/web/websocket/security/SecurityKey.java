package com.nonce.web.websocket.security;

import org.apache.commons.codec.binary.Base64;

public class SecurityKey implements ISecurityKey {
    private boolean _valid;
    private byte[] _key;
    
    public SecurityKey(byte[] key) {
        this._key = key;
        this._valid = this._key.length == 20;
    }
    
    @Override
    public String toBase64String() {
        return Base64.encodeBase64String(this._key);
    }
    
    @Override
    public String toString() {
        return this.toBase64String();
    }
    
    @Override
    public boolean isValid() {
        return this._valid;
    }
}
