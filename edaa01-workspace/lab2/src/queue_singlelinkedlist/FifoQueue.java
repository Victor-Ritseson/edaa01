package queue_singlelinkedlist;
import java.util.*;

public class FifoQueue<E> extends AbstractQueue<E> implements Queue<E> {
	private QueueNode<E> last;
	private int size;

	public FifoQueue() {
		super();
		last = null;
		size = 0;
	}

	/**	
	 * Inserts the specified element into this queue, if possible
	 * post:	The specified element is added to the rear of this queue
	 * @param	e the element to insert
	 * @return	true if it was possible to add the element 
	 * 			to this queue, else false
	 */
	public boolean offer(E e) {
	QueueNode<E> p = new QueueNode<E>(e);
	if(size != 0) {
		//binder ihop cirkulär länkad lista
		p.next = p;
		last.next = p;
	} else {
		p.next = p;	
	}
	last = p;
	size ++;
	return true;	
	}
	
	/**	
	 * Returns the number of elements in this queue
	 * @return the number of elements in this queue
	 */
	public int size() {
		return size;
	}
	
	/**	
	 * Retrieves, but does not remove, the head of this queue, 
	 * returning null if this queue is empty
	 * @return 	the head element of this queue, or null 
	 * 			if this queue is empty
	 */
	public E peek() {
		if(size == 0) {
			return null;
		} else {
			return last.next.element;
		}
	}

	/**	
	 * Retrieves and removes the head of this queue, 
	 * or null if this queue is empty.
	 * post:	the head of the queue is removed if it was not empty
	 * @return 	the head of this queue, or null if the queue is empty 
	 */
	public E poll() {
		if(size == 0) {
			return null;
		} 
		QueueNode<E> head = last.next;
		last.next = last.next.next;
		size --;
		if(size == 0) {
			last = null;
		} 
		return head.element;
	
	}
	
	/**
	* Appends the specified queue to this queue
	* post: all elements from the specified queue are appended
	* to this queue. The specified queue (q) is empty after the call.
	* @param q the queue to append
	* @throws IllegalArgumentException if this queue and q are identical */
	public void append(FifoQueue<E> q) {
		QueueNode<E> start = null;
		if(this == q) {
			throw new IllegalArgumentException();
		}
		if(last == null) {
			last = q.last;
			size = q.size();
			
		} else if(q.last != null){
			//start blir q:s förstavärde
			start = q.last.next;
			//q första nod som start referar till blir börjar för this
			q.last.next = last.next;
			//last.next ska referera till början alltså start
			last.next = start;
			// q.last.next är lika med början därför blir last = q.last
			last = q.last;
			
			size += q.size();
		}
		// tömmmer den gamla
		q.size = 0;
		q.last = null;
	}
	
	
	
	
	/**	
	 * Returns an iterator over the elements in this queue
	 * @return an iterator over the elements in this queue
	 */	
	public Iterator<E> iterator() {
		return new QueueIterator();
	}
	
	
	private class QueueIterator implements Iterator<E> { 
	private QueueNode<E> pos;
	private int counter;
	
	
	private QueueIterator() {
		pos = last;
		counter = size;
	}
	
	public boolean hasNext() {
		if(counter >= 0 || size <= 1) {
			return false;
		} else {
		return true;
		}
	}	
	
	public E next() {
		if(hasNext()) {
		counter --;
		pos = pos.next;
		return pos.element;	
		} else {
		throw new NoSuchElementException();
		}
	} 
	
	}
	

	private static class QueueNode<E> {
		E element;
		QueueNode<E> next;

		private QueueNode(E x) {
			element = x;
			next = null;
		}
	}

}
