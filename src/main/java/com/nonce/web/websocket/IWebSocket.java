package com.nonce.web.websocket;

public interface IWebSocket {
    void send(String message);
    void onMessage(String message);
    void onDisconnect();
    void onOpen();
}
