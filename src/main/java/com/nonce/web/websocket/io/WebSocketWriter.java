package com.nonce.web.websocket.io;

import java.io.IOException;

import com.nonce.common.exception.NonceSocketException;
import com.nonce.web.websocket.SocketError;
import com.nonce.web.websocket.io.framing.IFrame;

public abstract class WebSocketWriter implements IWebSocketWriter {
    protected WebSocketOuputStream _outStream;
    
    protected WebSocketWriter(WebSocketOuputStream out) {
        _outStream = out;
    }
    
    @Override
    public void close() throws IOException {
        try {
            this._outStream.close();
        } catch(IOException e)  {
            throw new NonceSocketException("Failed to close the underlying stream. An unexpected error occurred.", SocketError.UNEXPECTED_ERROR, e);
        }
    }

    @Override
    public void flush() {
        try {
            _outStream.flush();
        } catch (IOException e) {
           throw new NonceSocketException("Failed to flush the underlying stream.", SocketError.UNEXPECTED_ERROR, e);
        }
    }

    public abstract void write(IFrame frame);
}
