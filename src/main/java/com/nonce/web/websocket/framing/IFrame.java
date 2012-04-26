package com.nonce.web.websocket.framing;


public interface IFrame {
    public IFrameHeader getHeader();
    public byte[] getFrameData();
}
