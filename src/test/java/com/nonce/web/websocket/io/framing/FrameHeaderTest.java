package com.nonce.web.websocket.io.framing;

import junit.framework.Assert;
import junit.framework.AssertionFailedError;

import org.junit.Test;

import com.nonce.web.websocket.io.framing.FrameHeader.OpCode;

public class FrameHeaderTest {

	@Test
	public void testFrameHeaderCreation() {
		IFrameHeader header = new FrameHeader(7, 0, 1, OpCode.BINARY_FRAME, 50000);
		try {
			Assert.assertTrue(header.getFinalBitValue() == 0);
			Assert.assertTrue(header.getSecondReservedBitValue() == 0);
			Assert.assertTrue(header.getFirstReservedBitValue() == 0);
			Assert.assertTrue(header.getThirdReservedBitValue() == 0);
			Assert.assertTrue(header.getMaskedBitValue() == 1);
		} catch (AssertionFailedError e) {
			Assert.fail(e.getMessage());
		}
	}
	
	@Test
	public void testSetFirstReservedBitValue() {
		IFrameHeader header = new FrameHeader(7, 0, 1, OpCode.BINARY_FRAME, 50000);
		header.setFirstReservedBitValue(1);
		try {
			int val = header.getFirstReservedBitValue();
			Assert.assertTrue(val == 1);
		} catch (AssertionFailedError e) {
			Assert.fail(e.getMessage());
		}
	}
	
	@Test
	public void testSetSecondReservedBitValue() {
		IFrameHeader header = new FrameHeader(7, 0, 1, OpCode.BINARY_FRAME, 50000);
		header.setSecondReservedBitValue(1);
		try {
			int val = header.getSecondReservedBitValue();
			Assert.assertTrue(val == 1);
		} catch (AssertionFailedError e) {
			Assert.fail(e.getMessage());
		}
	}
	
	@Test
	public void testSetThirdReservedBitValue() {
		IFrameHeader header = new FrameHeader(7, 0, 1, OpCode.BINARY_FRAME, 50000);
		header.setThirdReservedBitValue(1);
		try {
			int val = header.getThirdReservedBitValue();
			Assert.assertTrue(val == 1);
		} catch (AssertionFailedError e) {
			Assert.fail(e.getMessage());
		}
	}
	
	@Test
	public void testSetMaskedBitValue() {
		IFrameHeader header = new FrameHeader(7, 0, 1, OpCode.BINARY_FRAME, 50000);
		header.setMaskedBitValue(0);
		try {
			int val = header.getMaskedBitValue();
			Assert.assertTrue(val == 0);
		} catch (AssertionFailedError e) {
			Assert.fail(e.getMessage());
		}
	}
	
	@Test
	public void testSetFinalBitValue() {
		IFrameHeader header = new FrameHeader(7, 0, 1, OpCode.BINARY_FRAME, 50000);
		header.setFinalBitValue(1);
		try {
			int val = header.getFinalBitValue();
			Assert.assertTrue(val == 1);
		} catch (AssertionFailedError e) {
			Assert.fail(e.getMessage());
		}
	}
	
	@Test
	public void getSetOpCode() {
		IFrameHeader header = new FrameHeader(7, 0, 1, OpCode.BINARY_FRAME, 50000);
		header.setOpCode(OpCode.CLOSE_FRAME);
		try {
			OpCode code = header.getOpCode();
			Assert.assertTrue(code == OpCode.CLOSE_FRAME);
		} catch (AssertionFailedError e) {
			Assert.fail(e.getMessage());
		}
	}
	
	@Test
	public void testFrameWith7BitLength() {
		
	}
	
	@Test
	public void testFrameWith16BitLength() {
		
	}
	
	@Test
	public void testFrameWith64BitLength() {
		
	}	
}
