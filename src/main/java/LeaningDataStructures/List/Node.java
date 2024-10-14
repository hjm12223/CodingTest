package LeaningDataStructures.List;

public class Node<E> {
	public E item;
	public Node<E> next;

	public Node(E newItem) {
		item = newItem;
		next = null;
	}

	public Node(E item, Node<E> next) {
		this.item = item;
		this.next = next;
	}
}
