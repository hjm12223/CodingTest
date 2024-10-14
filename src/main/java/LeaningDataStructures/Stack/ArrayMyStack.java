package LeaningDataStructures.Stack;

import java.util.ArrayList;
import java.util.List;

public class ArrayMyStack<E> {
	List<E> list;
	int top;

	public ArrayMyStack() {
		this.list = new ArrayList<>();
		this.top = -1;
	}

	public boolean isFull() {
		return top == list.size() - 1;
	}

	public boolean isEmpty() {
		return top == -1;
	}

	public E push(E value) {
		list.add(++top, value);
		return value;
	}

	public E pop() {
		E value = list.get(top);

		list.remove(top--);

		return value;
	}

	public E peek() {
		return list.get(top);
	}

	public int search(E value) {
		int result = list.lastIndexOf(value);

		if (result != -1) {
			return top - result + 1;
		} else {
			return result;
		}
	}

	public String toString() {
		return list.toString(); // ArrayList의 toString() 메서드만 호출 하면 된다.
	}
}
