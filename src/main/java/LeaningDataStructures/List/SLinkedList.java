// package LeaningDataStructures.List;
//
// import java.util.List;
// import java.util.NoSuchElementException;
//
// public class SLinkedList<E> implements List<E> {
// 	private Node<E> head;
// 	private Node<E> tail;
// 	private int size;
//
// 	public SLinkedList() {
// 		head = null;
// 		tail = null;
// 		size = 0;
// 	}
//
// 	private Node<E> search(int index) {
// 		if (index < 0 || index >= size)
// 			throw new IndexOutOfBoundsException();
//
// 		Node<E> x = head;
// 		for (int i = 0; i < index; i++) {
// 			x = x.next;
// 		}
// 		return x;
// 	}
//
// 	public void addFirst(E value) {
// 		Node<E> newNode = new Node<>(value);
// 		newNode.next = head;
// 		head = newNode;
// 		size++;
//
// 		if (head.next == null)
// 			tail = head;
// 	}
//
// 	public E remove() {
// 		Node<E> headNode = head;
// 		if (headNode == null)
// 			throw new NoSuchElementException();
//
// 		E element = headNode.item;
//
// 		Node<E> nextNode = headNode.next;
//
// 		head.item = null;
// 		head.next = null;
//
// 		head = nextNode;
// 		size--;
// 		if (size == 0) {
// 			tail = null;
// 		}
//
// 		return element;
//
// 	}
//
// 	public E remove(int index) {
// 		if (index == 0)
// 			return remove();
// 		if (index >= size || index < 0)
// 			throw new IndexOutOfBoundsException();
//
// 		Node<E> prevNode = search(index - 1);
//
// 		Node<E> removeNode = prevNode.next;
// 		E element = removeNode.item;
// 		Node<E> nextNode = removeNode.next;
//
// 		prevNode.next = null;
// 		prevNode.next = nextNode;
//
// 		if (prevNode.next == null)
// 			tail = prevNode;
//
// 		removeNode.next = null;
// 		removeNode.item = null;
//
// 		size--;
//
// 		return element;
// 	}
//
// 	public void addLast(E value) {
// 		Node<E> newNode = new Node<>(value);
// 		if (size == 0) {
// 			addFirst(value);
// 			return;
// 		}
//
// 		tail.next = newNode;
// 		tail = newNode;
// 		size++;
// 	}
//
// 	public boolean add(E value) {
// 		addLast(value);
// 		return true;
// 	}
//
// 	public void add(int index, E value) {
// 		Node<E> newNode = new Node<>(value);
// 		if (size == 0) {
// 			addFirst(value);
// 		} else if (index == size) {
// 			addLast(value);
// 		}
// 		Node<E> prevNode = search(index - 1);
//
// 		Node<E> prevNextNode = prevNode.next;
// 		prevNode.next = null;
// 		prevNode.next = newNode;
// 		newNode.next = prevNextNode;
//
// 		size++;
// 	}
// }
