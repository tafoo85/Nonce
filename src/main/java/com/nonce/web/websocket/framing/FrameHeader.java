package com.nonce.web.websocket.framing;

public class FrameHeader implements IFrameHeader {
    private int _header; //32 byte header.
    private long _extendedHeader; //sometimes we have a 64bit extended payload length.
    private int _maskingKey; //sometimes we have a mask.
    
    private static final int FINAL_MASK = 0x80000000; //first bit
    private static final int FIRST_RESERVED_BIT_MASK = 0x80000000; //second bit
    private static final int SECOND_RESERVED_BIT_MASK = 0x40000000; //third bit
    private static final int THIRD_RESERVED_BIT_MASK = 0x20000000; //fourth bit
    private static final int OPCODE_MASK = 0x0F000000; //fifth - eighth bit
    private static final int MASKED_MASK = 0x00800000; //ninth bit
    private static final int PAYLOAD_LENGTH_MASK = 0x007F0000; //tenth - sixteenth bit
    
    @Override
    public boolean isFinal() {
        return (_header & FINAL_MASK) == 1;
    }

    @Override
    public boolean getFirstReservedBit() {
        return (_header & FIRST_RESERVED_BIT_MASK) == 1;
    }

    @Override
    public boolean getSecondReservedBit() {
        return (_header & FIRST_RESERVED_BIT_MASK >> 1) == 1;
    }

    @Override
    public boolean gettThirdReservedBit() {
        return (_header & FIRST_RESERVED_BIT_MASK >> 2) == 1;
    }

    @Override
    public boolean isMasked() {
        return (_header & MASKED_MASK) == 1;
    }

    @Override
    public short getOpCode() {
        return (short)((_header & OPCODE_MASK) >> 24); //move down three bytes
    }

    @Override
    public long getPayloadLength() {
         long payloadLength = _header & PAYLOAD_LENGTH_MASK;
        
        if (payloadLength == 0x7f)
            payloadLength = this._extendedHeader;
        
        return payloadLength;
    }
    
    @Override
    public int getMaskKey() {
        return this._maskingKey;
    }
}
