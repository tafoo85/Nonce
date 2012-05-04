package com.nonce.web.websocket.io.framing;



public class FrameHeader implements IFrameHeader {
	protected int _header; //32 bit header.
    protected int _midPayloadBytes; //sometimes (if header & payload_mask > 127) we have a 64bit payload length.
    protected short _leastSignificantPayloadBytes;
    
    protected int _maskingKey; //sometimes (if header & masked_mask == 1) we have a masked payload.
    
    protected int _payloadLengthMask; //tenth - sixteenth bit or seventeenth - thirty-second bit
    protected int _payloadLengthOffset;
    
    protected static final int FINAL_MASK = 0x80000000; //first bit
    protected static final int FIRST_RESERVED_BIT_MASK = 0x40000000; //second bit
    protected static final int SECOND_RESERVED_BIT_MASK = 0x20000000; //third bit
    protected static final int THIRD_RESERVED_BIT_MASK = 0x10000000; //fourth bit 
    protected static final int OPCODE_MASK = 0x0F000000; //fifth - eighth bit
    protected static final int MASKED_MASK = 0x00800000; //ninth bit
    protected static final int PAYLOAD_LENGTH_MASK = 0x007F0000; //tenth - sixteenth bit.
    protected static final int EXTENDED_PAYLOAD_LENGTH_MASK = 0x0000FFFF;
        
    protected static final int FINAL_OFFSET = 31;
    protected static final int FIRST_RESERVED_BIT_OFFSET = 30;
    protected static final int SECOND_RESERVED_BIT_OFFSET = 29;
    protected static final int THIRD_RESERVED_BIT_OFFSET = 28;
    protected static final int OPCODE_OFFSET = 24;
    protected static final int MASKED_OFFSET = 23;
    protected static final int PAYLOAD_LENGTH_OFFSET = 16;
    protected static final int EXTENDED_PAYLOAD_LENGTH_OFFSET = 0;
    
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

	public FrameHeader(byte[] headerBytes) {
	    int finalFrame = headerBytes[0] & 0x80;
	    int masked = headerBytes[1] & 0x80;
	    OpCode code = OpCode.valueOf((short) (headerBytes[0] & 0x0F));
	    long payloadSize = headerBytes[1] & 0x7F;
	    long payloadLength; 
	    if (payloadSize >= 0x7E) {
	        
	    }
	    
	    if (payloadSize == 0x7F) {
	        
	    }
	    
	    int maskingKey;// = masked == 1 ? headerBytes[]: ;
	    
	    this.setFinalBitValue(finalFrame);
       // this.setPayloadLength(payloadLength);
        this.setFirstReservedBitValue(0);
        this.setSecondReservedBitValue(0);
        this.setThirdReservedBitValue(0);
        this.setMaskedBitValue(masked);
        this.setOpCode(code);
        //this.setMaskKey(maskingKey);
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
        long payloadLength = (_header & PAYLOAD_LENGTH_MASK) >>> 16;
        
        if (payloadLength == 0x7F) {
            payloadLength = (long)((this._header & EXTENDED_PAYLOAD_LENGTH_MASK) << 48);
            payloadLength |= (long)((this._midPayloadBytes << 16));
            payloadLength |= (long)((this._leastSignificantPayloadBytes));
        } else if (payloadLength == 0x7E) {
            payloadLength = this._header & EXTENDED_PAYLOAD_LENGTH_MASK;
        }
        
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
        //first check where to pack the length.
        //then change the seven bits to the appropriate value
        //if seven bits == 126 then pack the length in the two low order bytes of header
        //if seven bits == 127 then length should be 64 bits, so just set it to the extended header
        
        _header ^= _header & ~PAYLOAD_LENGTH_MASK; //clear the mask
       
        if (payloadLength < 126) {
            _header ^= (int) (payloadLength << PAYLOAD_LENGTH_OFFSET);
        } else if ( (payloadLength & 0xFFFFFFFFFFFF0000l) != 0 ) { //64bit value needed, so set seven bits in payload length to 127 and set the extended heaader = to the length
            _header ^= 0x007F0000;
            _header ^= (payloadLength >>> 48) & EXTENDED_PAYLOAD_LENGTH_MASK;
            _midPayloadBytes = (int)((payloadLength & 0x0000ffffffff0000L) >>> 16);
            _leastSignificantPayloadBytes = (short)(payloadLength & 0x000000FF);
        } else { //it's a 16bit header, so set the seven bits for payload length in header to 126 and fill the low order bytes in header with payloadLength
            _header ^= 0x007E0000;
            _header ^= (payloadLength & EXTENDED_PAYLOAD_LENGTH_MASK);
        }
    }
    
    @Override
    public void setMaskKey(int maskingKey) {
    	if (this.getMaskedBitValue() == 1)
    		_maskingKey = maskingKey;
    	else 
    		_maskingKey = 0;
    }    
}
