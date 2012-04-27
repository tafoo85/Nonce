package com.nonce.web.websocket.io.framing;

import com.nonce.web.websocket.io.framing.FrameHeader.OpCode;

public interface IFrameHeader {
    public int getFinalBitValue();
    public int getFirstReservedBitValue();
    public int getSecondReservedBitValue();
    public int getThirdReservedBitValue();
    public int getMaskedBitValue();
    
    public OpCode getOpCode();
    public long getPayloadLength();    
    public int getMaskKey();
    
	void setMaskKey(int maskingKey);
	void setFinalBitValue(int isFinal);
	void setFirstReservedBitValue(int isSet);
	void setSecondReservedBitValue(int isSet);
	void setThirdReservedBitValue(int isSet);
	void setMaskedBitValue(int isMasked);
	void setPayloadLength(long payloadLength);
	void setOpCode(OpCode opCode);
}
