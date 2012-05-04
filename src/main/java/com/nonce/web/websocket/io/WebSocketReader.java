package com.nonce.web.websocket.io;

import java.io.IOException;
import com.nonce.common.exception.NonceSocketException;
import com.nonce.web.websocket.SocketError;
import com.nonce.web.websocket.io.framing.IFrame;

public abstract class WebSocketReader implements IWebSocketReader {
    protected WebSocketInputStream _stream;
    
    protected WebSocketReader(WebSocketInputStream in) {
        this._stream = in;
    }
    
    @Override
    public void close() {
        try {
            this._stream.close();
        } catch (IOException e) {
            throw new NonceSocketException("Error closing the underlying stream.", SocketError.UNEXPECTED_ERROR, e);
        }
    }

    public abstract IFrame read();
}
