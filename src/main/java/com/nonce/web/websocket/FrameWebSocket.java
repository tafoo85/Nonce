package com.nonce.web.websocket;

import javax.servlet.ServletInputStream;
import javax.servlet.ServletOutputStream;

import com.nonce.web.websocket.io.framing.IFrame;

public class FrameWebSocket implements IWebSocket {
    @SuppressWarnings("unused")
    private ServletInputStream _inputStream;
    @SuppressWarnings("unused")
    private ServletOutputStream _outputStream;
    
    public enum ConnectionState {
        Open,
        Connected,
        Disconnected
    }
    
    protected ConnectionState _currentState;
    
    public FrameWebSocket(ServletInputStream inputStream, ServletOutputStream outputStream) {
        this._outputStream = outputStream;
        this._inputStream = inputStream;
    }

    @Override
    public void send(IFrame frame) {
       
    }

    @Override
    public void onMessage(IFrame frame) {
        
    }

    @Override
    public void onDisconnect() {
        
    }

    @Override
    public void onOpen() { 
        this._currentState = ConnectionState.Open; 
    }

}
