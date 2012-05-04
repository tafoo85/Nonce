package com.nonce.web.websocket;

public interface IWebSocket {
    void send(byte[] message);
    void onMessage(byte[] message);
    void onDisconnect();
    void onOpen();
}
