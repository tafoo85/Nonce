package com.nonce.web.websocket;

import javax.servlet.ServletInputStream;
import javax.servlet.ServletOutputStream;

public class FrameWebSocket implements IWebSocket {
    private ServletInputStream _inputStream;
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
    public void send(String message) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void onMessage(String message) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void onDisconnect() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void onOpen() { this._currentState = ConnectionState.Open; }

}
