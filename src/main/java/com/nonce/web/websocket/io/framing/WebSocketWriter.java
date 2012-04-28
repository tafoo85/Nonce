package com.nonce.web.websocket.io.framing;

import java.io.Writer;

public abstract class WebSocketWriter extends Writer {	
	public abstract void write(IFrame frame);
}
