package LeaningDataStructures.Stack;

public class MyLinkedStack<E> {
	Node<E> top;

	public E push(E value) {
		Node<E> prev;
		prev = top;
		top = new Node<>(prev, value);

		return value;
	}

	public E pop() {
		if (top == null) return null;
		E data = top.data;
		top = top.prev;
		return data;
	}

	public E peek() {
		if (top == null) return null;
		return top.data;
	}

	public boolean isEmpty() {
		return top == null;
	}

	public void init() {
		top = null;
	}

	public static class Node<E> {
		Node<E> prev;
		E data;

		public Node(Node<E> prev, E data) {
			this.prev = prev;
			this.data = data;
		}
	}
}
