package com.nonce.web.websocket.server;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface IWebSocketServer {
	public void processRequest(HttpServletRequest request, HttpServletResponse response);
}
