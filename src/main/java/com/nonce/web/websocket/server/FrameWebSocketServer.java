package com.nonce.web.websocket.server;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nonce.web.websocket.FrameWebSocket;
import com.nonce.web.websocket.IWebSocket;

public class FrameWebSocketServer extends WebSocketServer {
    private final static Logger logger = LoggerFactory.getLogger(FrameWebSocketServer.class);
    
    protected IWebSocket _client;
    
    public FrameWebSocketServer() {
        logger.debug("Creating a frame based websocket server");
    }
   
	@Override
	public void onMessage() {
	    logger.debug("Message recieved");
	}

	@Override
	public void onDisconnected() {
	    logger.debug("Disconnected");
	}

    @Override
    public void onHandshakeSuccess(HttpServletRequest request,
            HttpServletResponse response) {
        logger.debug("Handshake Success.");
        try {
            this._client = new FrameWebSocket(request.getInputStream(), response.getOutputStream());
            this._client.onOpen();
        } catch(IOException e) {
            logger.error("Failed to create the websocket.");
            logger.debug(e.getMessage(), e);
        }
    }

}
