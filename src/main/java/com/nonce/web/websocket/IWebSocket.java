package com.nonce.web.websocket;

import com.nonce.web.websocket.io.framing.IFrame;

public interface IWebSocket {
    void send(IFrame message);
    void onMessage(IFrame message);
    void onDisconnect();
    void onOpen();
}
