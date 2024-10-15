package LeaningDataStructures.Queue;

import java.util.NoSuchElementException;

public class MyLinkedListQueue<E> {
	private Node<E> head;
	private Node<E> tail;

	private int size;

	public MyLinkedListQueue() {
		this.head = null;
		this.tail = null;
		this.size = 0;
	}

	public boolean offer(E data) {
		Node<E> newNode = new Node<>(data);
		if (size == 0) {
			head = newNode;
		} else {
			tail.next = newNode;
		}

		tail = newNode;
		size++;

		return true;
	}

	public E poll() {
		if (head == null) {
			return null;
		}
		E element = head.data;

		Node<E> newNode = head.next;

		head.next = null;
		head.data = null;

		head = newNode;
		size--;

		return element;
	}

	public E remove() {
		E element = poll();
		if (element == null) {
			throw new NoSuchElementException();
		}
		return element;
	}

	public E peek() {

		if (size == 0) {
			return null;
		}

		return head.data;
	}

	public E element() {
		E element = peek();

		if (element == null) {
			throw new NoSuchElementException();
		}
		return element;
	}

	public int size() {
		return size;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public boolean contains(Object value) {
		for (Node<E> x = head; x != null; x = x.next) {
			if (x.data.equals(value)) {
				return true;
			}
		}
		return false;
	}

	public static class Node<E> {
		E data;
		Node<E> next;

		public Node(E data) {
			this.data = data;
			this.next = null;
		}
	}
}
