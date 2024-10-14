package LeaningDataStructures.List;

import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Objects;

public class DoubleLinkedList<E> {
	private Node<E> head;
	private Node<E> tail;

	private int size;

	public DoubleLinkedList() {
		this.head = null;
		this.tail = null;
		this.size = 0;
	}

	private Node<E> search(int index) {
		Node<E> n;

		if ((size / 2) > index) {
			n = head;
			for (int i = 0; i < index; i++) {
				n = n.next;
			}
		} else {
			n = tail;
			for (int i = size - 1; i > index; i--) {
				n = n.prev;
			}
		}
		return n;
	}

	public void addFirst(E value) {
		Node<E> first = head;

		Node<E> new_node = new Node<>(null, value, first);

		size++;

		head = new_node;

		if (first == null) {
			tail = new_node;
		} else {
			first.prev = new_node;
		}
	}

	public void addLast(E value) {
		Node<E> last = tail;

		Node<E> new_node = new Node<>(last, value, null);

		size++;

		tail = new_node;

		if (last == null) {
			head = new_node;
		} else {
			last.next = new_node;
		}
	}

	public boolean add(E value) {
		addLast(value);
		return true;
	}

	public void add(int index, E value) {
		if (index < 0 || index >= size) {
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

		Node<E> next_node = search(index);
		Node<E> prev_node = next_node.prev;

		Node<E> new_node = new Node<>(prev_node, value, next_node);

		next_node.prev = new_node;
		prev_node.next = new_node;

		size++;
	}

	public E removeFirst() {

		if (head == null) {
			throw new NoSuchElementException();
		}

		E value = head.item;

		Node<E> new_first_node = head.next;

		head.next = null;
		head.item = null;

		size--;

		head = new_first_node;

		if (new_first_node == null) {
			tail = null;
		} else {
			new_first_node.prev = null;
		}

		return value;

	}

	public E remove() {
		return removeFirst();
	}

	public E removeLast() {

		if (tail == null) {
			throw new NoSuchElementException();
		}

		E value = tail.item;

		Node<E> last = tail.prev;

		tail.item = null;
		tail.prev = null;

		size--;

		tail = last;

		if (last == null) {
			head = null;
		} else {
			last.next = null;
		}

		return value;

	}

	public E remove(int index) {

		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException();
		}

		if (index == 0) {
			return removeFirst();
		}
		if (index == size - 1) {
			return removeLast();
		}

		Node<E> del_node = search(index);
		Node<E> prev_node = del_node.prev;
		Node<E> next_node = del_node.next;

		prev_node.next = next_node;
		next_node.prev = prev_node;

		E value = del_node.item;

		del_node.prev = null;
		del_node.next = null;
		del_node.item = null;

		size--;

		return value;

	}

	public boolean remove(Object value) {
		Node<E> del_node = null;

		Node<E> i = head;

		while (i != null) {
			if (Objects.equals(i.item, value)) {
				del_node = i;
				break;
			}
			i = i.next;
		}
		if (del_node == null) {
			return false;
		}
		if (del_node == head) {
			removeFirst();
			return true;
		}
		if (del_node == tail) {
			removeLast();
			return true;
		}
		Node<E> prev_node = del_node.prev;
		Node<E> next_node = del_node.next;

		del_node.prev = null;
		del_node.next = null;
		del_node.item = null;

		size--;

		prev_node.next = next_node;
		next_node.prev = prev_node;

		return true;
	}

	public E get(int index) {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException();
		}
		return search(index).item;
	}

	public void get(int index, E value) {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException();
		}
		Node<E> node = search(index);

		node.item = null;
		node.item = value;

	}

	public int indexOf(Object value) {
		Node<E> n = head;
		int i = 0;
		while (n != null) {
			if (Objects.equals(value, n.item)) {
				return i;
			}
			i++;
			n = n.next;
		}
		return -1;
	}

	public int lastIndexOf(Object value) {
		Node<E> n = tail;
		int i = size - 1;
		while (n != null) {
			if (Objects.equals(value, n.item)) {
				return i;
			}

			i--;
			n = n.prev;
		}

		return -1;
	}

	public E set(Integer index, E value){
		if (index < 0 || index >= size){
			throw new IndexOutOfBoundsException();
		}

		Node<E> node = search(index);
		node.item = value;
		return node.item;
	}

	public int size(){
		return size;
	}
	public boolean isEmpty(){
		return size == 0;
	}

	public void clear(){
		for (Node<E> i = head ; i.next != null;){
			Node<E> next_node = i.next;

			i.item = null;
			i.prev = null;
			i.next = null;

			i = next_node;
		}
		head = null;
		tail = null;

		size = 0;
	}

	public boolean contains(Object value){
		return indexOf(value) != -1;
	}

	@Override
	public String toString() {
		if(head == null) {
			return "[]";
		}

		// 2. 현재 size만큼 배열 생성
		Object[] array = new Object[size];

		// 3. 노드 next를 순회하면서 배열에 노드값을 저장
		int index = 0;
		Node<E> n = head;
		while (n != null) {
			array[index] = (E) n.item;
			index++;
			n = n.next;
		}

		// 3. 배열을 스트링화하여 반환
		return Arrays.toString(array);
	}

	public String toString2() {
		if (head == null) {
			return "[]";
		}

		Node<E> n = head;
		StringBuilder result = new StringBuilder();

		result.append("[\n");

		for(int i = 0; i< size; i++) {

			result.append("  Node@").append(String.format("%-10s", n.hashCode())).append("  →  ");

			if (n.prev != null) {
				result.append("[").append(n.prev.hashCode()).append(" | ");
			} else {
				result.append("[").append("null").append(" | ");
			}

			result.append(n.item).append(" | ");

			if (n.next != null) {
				result.append(n.next.hashCode()).append("]");
			} else {
				result.append("null").append("]");
			}

			result.append(", \n");

			n = n.next;
		}

		result.append("]");

		return result.toString();
	}
	private static class Node<E> {
		private E item;
		private Node<E> next;
		private Node<E> prev;

		public Node(Node<E> prev, E item, Node<E> next) {
			this.item = item;
			this.next = next;
			this.prev = prev;
		}
	}
}
