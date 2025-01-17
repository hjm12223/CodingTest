package LeaningDataStructures.List;

public class TestList<E> {
	public int size;
	private Node<E> head;
	private Node<E> tail;

	public TestList(int size) {
		this.size = size;
		this.head = null;
		this.tail = null;
	}

	public TestList() {
		this.size = 0;
		this.head = null;
		this.tail = null;
	}

	public void addLast(E value) {
		Node<E> last = tail;
		Node<E> newNode = new Node<>(last, null, value);

		size++;

		tail = newNode;
		if (last == null) {
			head = newNode;
		} else {
			last.next = newNode;
		}
	}

	public boolean add(E value) {
		addLast(value);
		return true;
	}

	private static class Node<E> {
		Node<E> prev;
		Node<E> next;
		Object data;

		public Node(Node<E> prev, Node<E> next, Object data) {
			this.prev = prev;
			this.next = next;
			this.data = data;
		}
	}
}
