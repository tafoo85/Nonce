package com.nonce.web.websocket.io.framing;

import org.junit.Assert;
import org.junit.Test;

public class BinaryFrameTest {
    
    @Test
    public void testServerFrameCreation() {
        byte[] data = {0x01, 0x02, 0x03};
        
        try {
            IFrame frame = new BinaryFrame(data, true);
            
            IFrameHeader header = frame.getHeader();            
            byte[] actuals = frame.getFrameData();
            
            Assert.assertArrayEquals(data, actuals);
            Assert.assertNotNull(header);
        } catch(Exception e) {
            Assert.fail(e.getMessage());
        }
    }    
    
    @Test
    public void testClientFrameCreation() {
           byte[] data = {0x01, 0x02, 0x03};
        
        try {
            IFrame frame = new BinaryFrame(data, true, true, 50023);
            
            IFrameHeader header = frame.getHeader();
            byte[] actuals = frame.getFrameData();
            
            Assert.assertArrayEquals(data, actuals);
            Assert.assertNotNull(header);
        } catch(Exception e) {
            Assert.fail(e.getMessage());
        }
    }
}
