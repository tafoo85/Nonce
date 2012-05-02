package com.nonce.web.websocket.io.framing;

import com.nonce.web.websocket.io.framing.FrameHeader.OpCode;

public class BinaryFrame extends Frame {
	private static final long serialVersionUID = 1L;

	public BinaryFrame(byte[] data, boolean finalFrame, boolean isMasked, int maskingKey) { //for the deserialization of a frame off the wire
		super(data, finalFrame, isMasked, maskingKey);
	}
	
	public BinaryFrame(byte[] data, boolean finalFrame) { //for the serialization of onto the wire.
		super(data, finalFrame, false, 0);
	}

	@Override
	protected void initializeHeader(boolean finalFrame, boolean isMasked, int maskingKey) {		
		OpCode opCode = finalFrame ? OpCode.BINARY_FRAME : OpCode.CONTINUATION_FRAME;
		int finalBit = finalFrame ? 1 : 0;
		int maskedBit = isMasked ? 1 : 0;
		
		this._header = new FrameHeader(this._payload.length, finalBit, maskedBit, opCode, maskingKey);
	}
}
