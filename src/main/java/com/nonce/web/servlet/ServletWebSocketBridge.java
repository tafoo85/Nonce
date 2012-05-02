package com.nonce.web.servlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import com.nonce.web.websocket.server.FrameWebSocketServer;
import com.nonce.web.websocket.server.IWebSocketServer;

public class ServletWebSocketBridge extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(ServletWebSocketBridge.class);
	
	private IWebSocketServer _webSocketServer;
	
	public ServletWebSocketBridge() { 
	    logger.debug("Creating the websocket bridge using a default FrameWebSocketServer");
        this._webSocketServer = new FrameWebSocketServer();
    }
	
	public ServletWebSocketBridge(IWebSocketServer server) { 
	    logger.debug("Creating the websocket bridge using the specified server {}.", server);
        this._webSocketServer = server;
    }
	
	public void setWebSocketServer(IWebSocketServer server) {
	    logger.debug("Setting the websocket server to {}.", server);
		this._webSocketServer = server;
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) {
	    logger.debug("Invoking GET");
		this._webSocketServer.processRequest(request, response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		logger.debug("Invoking POST");
	    doGet(request, response);
	}
} 
