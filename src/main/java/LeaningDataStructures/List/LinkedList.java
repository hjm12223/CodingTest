package LeaningDataStructures.List;

import java.util.Arrays;
import java.util.Objects;

public class LinkedList<E> {
	private Node<E> head;
	private Node<E> tail;
	private int size;

	private Node<E> search(int index) {
		Node<E> n = head;
		for (int i = 0; i < index; i++) {
			n = n.next; // next 필드의 값을 재대입하면서 순차적으로 요소를 탐색
		}
		return n;
	}

	public void addFirst(E value) {
		Node<E> first = head;
		Node<E> newNode = new Node<>(value, first);
		size++;
		head = newNode;

		if (first == null) {
			tail = newNode;
		}
	}

	public void addLast(E value) {
		Node<E> last = tail;
		Node<E> newNode = new Node<>(value, null);
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

	public void add(int index, E value) {
		if (index < 0 || index > size) {
			throw new IndexOutOfBoundsException();
		}
		if (index == 0) {
			addFirst(value);
			return;
		}
		if (index == size - 1) {
			addLast(value);
			return;
		}

		Node<E> prev_node = search(index - 1);
		Node<E> next_node = prev_node.next;
		Node<E> newNode = new Node<>(value, next_node);

		size++;

		prev_node.next = newNode;

	}

	public E removeFirst() {
		if (head == null) {
			throw new IndexOutOfBoundsException();
		}
		E returnValue = head.item;

		Node<E> first = head.next;

		head.next = null;
		head.item = null;

		head = first;

		size--;

		if (head == null) {
			tail = null;
		}

		return returnValue;

	}

	public E remove() {
		return removeFirst();
	}

	public E remove(int index) {
		if (index < 0 || index > size) {
			throw new IndexOutOfBoundsException();
		}
		if (index == 0) {
			return removeFirst();
		}
		Node<E> prevNode = search(index - 1);
		Node<E> currNode = prevNode.next;
		Node<E> nextNode = currNode.next;

		E returnValue = currNode.item;

		currNode.next = null;
		currNode.item = null;

		size--;

		prevNode.next = nextNode;

		return returnValue;

	}

	public boolean remove(Object value) {
		if (head == null) {
			throw new RuntimeException();
		}
		Node<E> prev_node = null;
		Node<E> del_node = null;
		Node<E> next_node = null;

		Node<E> i = head;

		while (i != null) {
			if (Objects.equals(i.item, value)) {
				del_node = i;
				break;
			}
			prev_node = i;

			i = i.next;
		}

		if (del_node == null) {
			return false;
		}

		if (del_node == head) {
			removeFirst();
			return true;
		}
		next_node = del_node.next;

		del_node.next = null;
		del_node.item = null;

		size--;

		prev_node.next = next_node;

		return true;
	}

	public E removeLast() {
		return remove(size - 1);
	}

	public E get(int index) {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException();
		}
		return search(index).item;
	}

	public void set(int index, E value) {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException();
		}
		Node<E> replace_node = search(index);

		replace_node.item = null;
		replace_node.item = value;

	}

	@Override
	public String toString() {
		if (head == null) {
			return "[]";
		}
		Object[] array = new Object[size];

		int index = 0;
		Node<E> n = head;
		while (n != null) {
			array[index] = (E)n.item;
			index++;
			n = n.next;
		}
		return Arrays.toString(array);
	}

	private static class Node<E> {
		private E item;
		private Node<E> next;

		public Node(E item, Node<E> next) {
			this.item = item;
			this.next = next;
		}
	}
}
