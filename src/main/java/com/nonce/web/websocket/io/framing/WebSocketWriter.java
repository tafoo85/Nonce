package com.nonce.web.websocket.io.framing;

import java.io.IOException;
import java.io.Writer;

public class WebSocketWriter extends Writer {

	@Override
	public void write(char[] cbuf, int off, int len) throws IOException {
		
	}
	
	public void write(IFrame frame) {
		byte[] payload = frame.getFrameData();
	}
	
	public void write(byte[] payload, int offset, int length) {
		
	}
	
	public void write(byte[] payload) {
		
	}

	@Override
	public void flush() throws IOException {
		// TODO Auto-generated method stub

	}

	@Override
	public void close() throws IOException {
		// TODO Auto-generated method stub

	}

}
