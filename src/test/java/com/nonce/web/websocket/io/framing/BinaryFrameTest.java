package com.nonce.web.websocket.io.framing;

import org.junit.Assert;
import org.junit.Test;

public class BinaryFrameTest {
    private static byte[] unmaskedData = { 0x01, 0x02, 0x03, 0x05, 0x07 };
    private static byte[] maskedData;
    private static byte[] key = { 0x55, 0x14, 0x3c, (byte) 0xFE };
    
    static {
        maskedData = new byte[5];
        
        for (int i = 0; i < maskedData.length; ++i) {
             maskedData[i] = (byte) (unmaskedData[i] ^ key[i % 4]);
        }
    }
    
    @Test
    public void testServerFrameCreation() {        
        try {
            IFrame frame = new BinaryFrame(unmaskedData, true);
            
            IFrameHeader header = frame.getHeader();            
            byte[] actuals = frame.getFrameData();
            
            Assert.assertArrayEquals(unmaskedData, actuals);
            Assert.assertNotNull(header);
        } catch(Exception e) {
            Assert.fail(e.getMessage());
        }
    }    
    
    @Test
    public void testClientFrameCreation() {        
        try {
            int key = 0xfe3c1455;
            IFrame frame = new BinaryFrame(maskedData, true, true, key);
            
            IFrameHeader header = frame.getHeader();
            byte[] actuals = frame.getFrameData();
            
            Assert.assertArrayEquals(unmaskedData, actuals);
            Assert.assertNotNull(header);
        } catch(Exception e) {
            Assert.fail(e.getMessage());
        }
    }
    
    @Test
    public void testGetFrameData() {
        try {
            int key = 0xfe3c1455;            
            IFrame frame = new BinaryFrame(maskedData, true, true, key);
            
            int headerKey = frame.getHeader().getMaskKey();
            byte[] data = frame.getFrameData();
            
            Assert.assertEquals(headerKey, key);
            Assert.assertArrayEquals(unmaskedData, data);
        } catch(Exception e) {
            Assert.fail(e.getMessage());
        }
    }
}
