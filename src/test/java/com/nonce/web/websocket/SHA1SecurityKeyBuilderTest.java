package com.nonce.web.websocket;

import java.security.NoSuchAlgorithmException;

import org.junit.Assert;
import org.junit.Test;

import com.nonce.web.websocket.security.ISecurityKey;
import com.nonce.web.websocket.security.SHA1SecurityKeyBuilder;

public class SHA1SecurityKeyBuilderTest {

    @Test
    public void testBuild() {
        SHA1SecurityKeyBuilder builder = new SHA1SecurityKeyBuilder();
        try {
            builder.build("NVdHnWaPLwc9x33kR+IJGQ==");
            ISecurityKey key = builder.getSecurityKey();
            try {
                Assert.assertTrue(key.toString().equals("Zh0rm43P07MAsVDaS3ibWX2Ld8g="));
            } catch(Exception ex) {
                Assert.fail(ex.getMessage());
            }
        } catch (NoSuchAlgorithmException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
