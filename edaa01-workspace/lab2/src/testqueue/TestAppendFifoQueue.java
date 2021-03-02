package testqueue;

import static org.junit.jupiter.api.Assertions.*;

import java.util.NoSuchElementException;

import queue_singlelinkedlist.FifoQueue;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestAppendFifoQueue {
	private FifoQueue<Integer> xs;
	private FifoQueue<Integer> ys;
	
	@BeforeEach
	void setUp() throws Exception {
	xs = new FifoQueue<Integer>();
	ys = new FifoQueue<Integer>();
	}

	@AfterEach
	void tearDown() throws Exception {
	xs = null;
	ys = null;
	}

	@Test
	void testTwoEmpty() {
		xs.append(ys);
		Assertions.assertTrue(xs.isEmpty(), "Is not empty");
		Assertions.assertTrue(ys.isEmpty(), "Is not empty");
	
	}
	
	@Test
	void testemptyToNonEmpty() {
		xs.add(2);
		xs.append(ys);
		Assertions.assertEquals(1, xs.size(), "should be 1");
		Assertions.assertTrue(ys.isEmpty(), "Is not empty");
	}
	
	
	@Test
	void testNonEmptyToEmpty() {
		ys.add(2);
		xs.append(ys);
		Assertions.assertEquals(1, xs.size(), "Append failed, no value in xs");
		Assertions.assertTrue(ys.isEmpty(), "Is not empty");
	
	}
	
	@Test
	void testTwoNonEmpty() {
		xs.add(2);
		ys.add(4);
		xs.append(ys);
		Assertions.assertEquals(2, xs.size(), "Append failed, value in xs is wrong");
		Assertions.assertTrue(ys.isEmpty(), "Is not empty");
	}
	
	@Test
	void testSameQueue() {
		try {
			xs.append(xs);
			fail("Possible to append a list with itself");
		} catch(IllegalArgumentException e) {
			
		}
	}
	
	
	
	

}
