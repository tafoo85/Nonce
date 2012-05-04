package com.nonce.web.websocket.io.framing;

public abstract class Frame implements IFrame {
	private static final long serialVersionUID = 1L;
	
	protected IFrameHeader _header;
	protected byte[] _payload;
	
    public Frame(byte[] data, boolean finalFrame, boolean isMasked, int maskingKey) {
		this._payload = data;
		initializeHeader(finalFrame, isMasked, maskingKey);
	}
    
    public Frame(IFrameHeader header, byte[] data) {
        
    }
    
    protected abstract void initializeHeader(boolean finalFrame, boolean isMasked, int maskingKey);
    protected abstract void initializeHeader(IFrameHeader header);

	@Override
    public IFrameHeader getHeader() {
       return this._header;
    }

    @Override
    public byte[] getFrameData() {
       if (this._header.getMaskedBitValue() == 1)
           return unmask(this._payload);
       else
           return this._payload;
    }
    
    protected byte[] unmask(byte[] maskedFrameData) {
        byte[] unmaskedData;
        
        if (maskedFrameData != null && maskedFrameData.length > 0) {
            unmaskedData = new byte[maskedFrameData.length];
            int key = this._header.getMaskKey();
            
            for (int i = 0; i < maskedFrameData.length; ++i) {
                int byteMask = 0xFF << ((i % 4) * 8);
                int maskingByteKey = key & byteMask;
                unmaskedData[i] = (byte) (maskedFrameData[i] ^ maskingByteKey >>> ((i % 4) * 8));
            }
        } else {
            unmaskedData = new byte[0];
        }
        
        return unmaskedData;
    }
}
