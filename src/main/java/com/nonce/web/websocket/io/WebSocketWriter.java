package com.nonce.web.websocket.io;

import java.io.Writer;

import com.nonce.web.websocket.io.framing.IFrame;

public abstract class WebSocketWriter extends Writer {	
	public abstract void write(IFrame frame);
}
