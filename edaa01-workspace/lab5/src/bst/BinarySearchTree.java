package bst;

import java.util.ArrayList;
import java.util.Comparator;


public class BinarySearchTree<E> {
  BinaryNode<E> root;  // Används också i BSTVisaulizer
  int size;            // Används också i BSTVisaulizer
  private Comparator<E> ccomparator;
    
	/**
	 * Constructs an empty binary search tree.
	 */
	public BinarySearchTree() {
	size = 0;
	root = null;
	ccomparator = (a,b) -> ((Comparable<E>) a).compareTo(b);
		
	}
	
	
	public static void main(String args[]){
	BSTVisualizer visual = new BSTVisualizer("Binary Tree before rebuild", 400,200);
	BSTVisualizer visual2 = new BSTVisualizer("Binary Tree after rebuild", 400,200);
	
	
	BinarySearchTree<Integer> xs = new BinarySearchTree<Integer>();
	xs.add(6);
	xs.add(2);
	xs.add(7);
	xs.add(8);
	xs.add(9);
	xs.add(10);
	xs.add(11);
	xs.add(12);
	
	visual.drawTree(xs);
	xs.rebuild();
	visual2.drawTree(xs);
	
	}
	
	
	/**
	 * Constructs an empty binary search tree, sorted according to the specified comparator.
	 */
	public BinarySearchTree(Comparator<E> comparator) {
	size = 0;
	root = null;
	ccomparator = comparator;
		
	}

	/**
	 * Inserts the specified element in the tree if no duplicate exists.
	 * @param x element to be inserted
	 * @return true if the the element was inserted
	 */
	public boolean add(E x) {
		if(root == null) {
		root = new BinaryNode<E>(x);
		size ++;
		return true;
		} else {
		return add(x, root);
		}
	}
	
	
	private boolean add(E x, BinaryNode<E> n) {
		int comp = ccomparator.compare(x, n.element);
		if(comp == 0) {
				return false;
			} else {
				BinaryNode<E> node = new BinaryNode<E>(x);	
	
			if(comp < 0) {
				if(n.left == null) {
					n.left = node;
				//	return true;
				} else {
				return add(x, n.left);
				}
			} 
			
			if(comp > 0) {
				if(n.right == null) {
					n.right = node;
					//return true;
				} else {
				return add(x, n.right);
				}
			}
			size ++;
			return true;
			
			}

	}
	
	/**
	 * Computes the height of tree.
	 * @return the height of the tree
	 */
	public int height() {
		return height(root);
	}
	
	private int height(BinaryNode<E> n) {
		if(n == null) {
			return 0;
		} else {
			return 1 + Math.max(height(n.left), height(n.right));
		}
		
	}
	
	/**
	 * Returns the number of elements in this tree.
	 * @return the number of elements in this tree
	 */
	public int size() {
		return size;
	}
	
	/**
	 * Removes all of the elements from this list.
	 */
	public void clear() {
	root = null;
	size = 0;
	}
	
	/**
	 * Print tree contents in inorder.
	 */
	public void printTree() {
	printTree(root);

	}
	private void printTree(BinaryNode<E> n) {
		if(n != null) {
			printTree(n.left);
			System.out.print(n.element);
			printTree(n.right);
			
		}
	}

	/** 
	 * Builds a complete tree from the elements in the tree.
	 */
	public void rebuild() {
		ArrayList<E> lista = new ArrayList<E>();
		toArray(root,lista);
		root = buildTree(lista, 0, lista.size());
		
	}
	
	/*
	 * Adds all elements from the tree rooted at n in inorder to the list sorted.
	 */
	private void toArray(BinaryNode<E> n, ArrayList<E> sorted) {
	if(n != null) {
	toArray(n.left, sorted);
	sorted.add(n.element);
	toArray(n.right, sorted);
	}
		
	}
	
	/*
	 * Builds a complete tree from the elements from position first to 
	 * last in the list sorted.
	 * Elements in the list a are assumed to be in ascending order.
	 * Returns the root of tree.
	 */
	private BinaryNode<E> buildTree(ArrayList<E> sorted, int first, int last) {
		if(first > last || first == sorted.size()) {
			return null;
		}
		int mid = (first + last)/2;
		BinaryNode<E> newRoot = new BinaryNode<E>(sorted.get(mid));
		newRoot.left = buildTree(sorted, first, mid - 1);
		newRoot.right = buildTree(sorted, mid + 1, last);
		
		return newRoot;
	}
	
	


	static class BinaryNode<E> {
		E element;
		BinaryNode<E> left;
		BinaryNode<E> right;

		private BinaryNode(E element) {
			this.element = element;
		}	
	}
	
}
