package com.nonce.web.websocket.io.framing;

public class FrameHeader implements IFrameHeader {
	protected int _header; //32 byte header.
    protected long _extendedHeader; //sometimes (if header & payload_mask > 127) we have a 64bit payload length.
    protected int _maskingKey; //sometimes (if header & masked_mask == 1) we have a masked payload.
    protected int _payloadLengthMask; //tenth - sixteenth bit or seventeenth - thirty-second bit
    protected int _payloadLengthOffset;
    
    protected static final int FINAL_MASK = 0x80000000; //first bit
    protected static final int FIRST_RESERVED_BIT_MASK = 0x40000000; //second bit
    protected static final int SECOND_RESERVED_BIT_MASK = 0x20000000; //third bit
    protected static final int THIRD_RESERVED_BIT_MASK = 0x10000000; //fourth bit 
    protected static final int OPCODE_MASK = 0x0F000000; //fifth - eighth bit
    protected static final int MASKED_MASK = 0x00800000; //ninth bit
        
    protected static final int FINAL_OFFSET = 31;
    protected static final int FIRST_RESERVED_BIT_OFFSET = 30;
    protected static final int SECOND_RESERVED_BIT_OFFSET = 29;
    protected static final int THIRD_RESERVED_BIT_OFFSET = 28;
    protected static final int OPCODE_OFFSET = 24;
    protected static final int MASKED_OFFSET = 23;
    
    protected enum OpCode {
    	CONTINUATION_FRAME((short)0x0),
    	TEXT_FRAME((short)0x1),
    	BINARY_FRAME((short)0x2),
    	CLOSE_FRAME((short)0x8),
    	PING_FRAME((short)0x9),
    	PONG_FRAME((short)0xA);
    	
    	private short _code;
    	
    	private OpCode(short code) {
    		_code = code;
    	}    	
    	
    	public short getCode() {
    		return _code;
    	}
    	
    	public static OpCode valueOf(short value) {
    		switch(value) {
    			case 0x0: return OpCode.CONTINUATION_FRAME;
    			case 0x1: return OpCode.TEXT_FRAME;
    			case 0x2: return OpCode.BINARY_FRAME;
    			case 0x8: return OpCode.CLOSE_FRAME;
    			case 0x9: return OpCode.PING_FRAME;
    			case 0xA: return OpCode.PONG_FRAME;
    			default: return OpCode.CLOSE_FRAME; //ERROR
    		}
    	}
    }
        
    public FrameHeader(long payloadLength, int finalFrame, int masked, OpCode code, int maskingKey) {
		this.setFinalBitValue(finalFrame);
		this.setPayloadLength(payloadLength);
		this.setFirstReservedBitValue(0);
		this.setSecondReservedBitValue(0);
		this.setThirdReservedBitValue(0);
		this.setMaskedBitValue(masked);
		this.setOpCode(code);
		this.setMaskKey(maskingKey);
	}

	@Override
    public int getFinalBitValue() {
        return (_header & FINAL_MASK) >>> FINAL_OFFSET;
    }

    @Override
    public int getFirstReservedBitValue() {
        return (_header & FIRST_RESERVED_BIT_MASK) >>> FIRST_RESERVED_BIT_OFFSET;
    }

    @Override
    public int getSecondReservedBitValue() {
        return (_header & SECOND_RESERVED_BIT_MASK) >>> SECOND_RESERVED_BIT_OFFSET;
    }

    @Override
    public int getThirdReservedBitValue() {
        return (_header & THIRD_RESERVED_BIT_MASK) >>> THIRD_RESERVED_BIT_OFFSET;
    }

    @Override
    public int getMaskedBitValue() {
        return (_header & MASKED_MASK) >>> MASKED_OFFSET;
    }

    @Override
    public OpCode getOpCode() {
        short code = (short)((_header & OPCODE_MASK) >>> OPCODE_OFFSET); //move down three bytes
        return OpCode.valueOf(code);
    }

    @Override
    public long getPayloadLength() {
         long payloadLength = _header & _payloadLengthMask;
        
        if (payloadLength == 0x7F)
            payloadLength = this._extendedHeader;
        
        return payloadLength;
    }
    
    @Override
    public int getMaskKey() {
        return this._maskingKey;
    }
    
    @Override
    public void setFinalBitValue(int isFinal) {
    	_header &= ~FINAL_MASK;
    	_header ^= isFinal << FINAL_OFFSET;
    }

    @Override
    public void setFirstReservedBitValue(int isSet) {
    	_header &= ~FIRST_RESERVED_BIT_MASK;
    	_header ^= isSet << FIRST_RESERVED_BIT_OFFSET;
    }

    @Override
    public void setSecondReservedBitValue(int isSet) {
    	_header &= ~SECOND_RESERVED_BIT_MASK; 
    	_header ^= isSet << SECOND_RESERVED_BIT_OFFSET;
    }

    @Override
    public void setThirdReservedBitValue(int isSet) {
    	_header &= ~THIRD_RESERVED_BIT_MASK;
    	_header ^= isSet << THIRD_RESERVED_BIT_OFFSET;	
    }

    @Override
    public void setMaskedBitValue(int isMasked) {
    	_header &= ~MASKED_MASK;
    	_header ^= isMasked << MASKED_OFFSET;
    }

    @Override
    public void setOpCode(OpCode opCode) {
    	_header &= ~OPCODE_MASK; 
    	_header ^= ((int)opCode.getCode() << OPCODE_OFFSET);
    }

    @Override
    public void setPayloadLength(long payloadLength) {
    	if (payloadLength < 126) { //7-bit payload length
			_payloadLengthMask = 0x007F0000;
			_payloadLengthOffset = 20; 
		} else if (payloadLength == 126) {
			_payloadLengthMask = 0x0000FFFF;
			_payloadLengthOffset = 12;
		} else { 
			_payloadLengthMask = 0x007F0000;
			_payloadLengthOffset = 20;
			this._extendedHeader = payloadLength;
			payloadLength = 0x7F;
		}
		_header ^= (int) ((payloadLength << _payloadLengthOffset) & _payloadLengthMask);
    }
    
    @Override
    public void setMaskKey(int maskingKey) {
    	if (this.getMaskedBitValue() == 1)
    		_maskingKey = maskingKey;
    	else 
    		_maskingKey = 0;
    }    
}
