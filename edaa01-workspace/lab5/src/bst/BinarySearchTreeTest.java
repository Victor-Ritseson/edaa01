package bst;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BinarySearchTreeTest {
	private BinarySearchTree<Integer> xs;
	private BinarySearchTree<String> ys;

	@BeforeEach
	void setUp() throws Exception {
		xs = new BinarySearchTree<Integer>();
		ys = new BinarySearchTree<String>((a,b) -> ((Comparable) a).compareTo(b));
		
	}

	@AfterEach
	void tearDown() throws Exception {
		xs = null;
		ys = null;
		
	}

	@Test
	void testSize() {
		xs.add(2);
		Assertions.assertTrue(xs.size() == 1 || ys.size() == 0, "Size doesn't work");
	}

	
	@Test
	void testHeight() {
	xs.add(2);
	xs.add(1);
	Assertions.assertTrue(ys.height() == 0 || xs.height() == 2, "height doesn't work");
		
	}
	
	@Test
	void testADD() {
	ys.add("hej");
	//ys.add("hej");
	xs.add(5);
	xs.add(8);
	//System.out.print(xs.size);

	Assertions.assertTrue(ys.size() == 1, "Duplicates addSize doesn't work");
	Assertions.assertTrue(ys.add("hej") == false, "Return statement on add duplicate doesnt work");
	Assertions.assertTrue(xs.add(2), "Return statement doesn't work");		
	Assertions.assertTrue(xs.size() == 3, "size");	
	}
	
	
	@Test
	void testClear() {
	ys.add("hej");
	ys.add("hejsan");
	ys.clear();
	xs.add(5);
	xs.clear();

	Assertions.assertTrue(ys.size() == 0 || xs.size() == 0, "Clear doesn't work");
		
	}
	
}
