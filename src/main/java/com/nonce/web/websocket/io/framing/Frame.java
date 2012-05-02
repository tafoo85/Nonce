package com.nonce.web.websocket.io.framing;

public abstract class Frame implements IFrame {
	private static final long serialVersionUID = 1L;
	
	protected IFrameHeader _header;
	protected byte[] _payload;
	
    public Frame(byte[] data, boolean finalFrame, boolean isMasked, int maskingKey) {
		this._payload = data;
		initializeHeader(finalFrame, isMasked, maskingKey);
	}
    
    protected abstract void initializeHeader(boolean finalFrame, boolean isMasked, int maskingKey);

	@Override
    public IFrameHeader getHeader() {
       return this._header;
    }

    @Override
    public byte[] getFrameData() {
       return this._payload;
    }

}
