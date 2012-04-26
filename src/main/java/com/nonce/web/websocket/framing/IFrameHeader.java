package com.nonce.web.websocket.framing;

public interface IFrameHeader {
    public boolean isFinal();
    public boolean getFirstReservedBit();
    public boolean getSecondReservedBit();
    public boolean gettThirdReservedBit();
    public boolean isMasked();
    
    public short getOpCode();
    public long getPayloadLength();
    
    public int getMaskKey();
}
