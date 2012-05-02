package com.nonce.web.websocket.io.framing;

import java.io.Serializable;


public interface IFrame extends Serializable {
    public IFrameHeader getHeader();
    public byte[] getFrameData();
}
