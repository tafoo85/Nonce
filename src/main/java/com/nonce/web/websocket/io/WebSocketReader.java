package com.nonce.web.websocket.io;

import java.io.Reader;

import com.nonce.web.websocket.io.framing.IFrame;

public abstract class WebSocketReader extends Reader {
    public abstract int read();
    public abstract int read(IFrame frame);
}
