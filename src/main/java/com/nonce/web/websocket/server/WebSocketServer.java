package com.nonce.web.websocket.server;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nonce.common.exception.NonceException;

public abstract class WebSocketServer implements IWebSocketServer {
	private static final Logger logger = LoggerFactory.getLogger(WebSocketServer.class);

	public void processRequest(HttpServletRequest request, HttpServletResponse response) {
		logger.debug("Websocket request being processed");
		try {
		    if (handshake(request, response)) {
		        this.onHandshakeSuccess(request, response);
		    }
		} catch (NonceException ex) {
		    logger.error("Request unable to finish processing.  Failing connection.");
		}
	}
	
	protected boolean handshake(HttpServletRequest request, HttpServletResponse response) {
	    boolean handshakeSuccess = false;
	    
	    IWebSocketHandshakeHeader requestHeader = new WebSocketHandshakeHeader(request);
	    if (requestHeader.isValid()) {
	        WebSocketHandshakeResponseHeader responseHeader = new WebSocketHandshakeResponseHeader(request);
	        try {
                responseHeader.writeResponse(response);
            } catch (IOException e) {
               logger.error("Handshake failed.");
               logger.debug(e.getMessage(), e);
            } 
	        handshakeSuccess = true;
	    } else {
	        try {
                response.sendError(500, "Failed.");
                response.flushBuffer();
            } catch (IOException e) {
                logger.error("Failed to write to response: {}", e.getMessage());
                logger.debug("Stacktrace: ", e);
            }
	    }
	    
	    return handshakeSuccess;
	}
	
	public abstract void onHandshakeSuccess(HttpServletRequest request, HttpServletResponse response);
	public abstract void onMessage();
	public abstract void onDisconnected();
}
