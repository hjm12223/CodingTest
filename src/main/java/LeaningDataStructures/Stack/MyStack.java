package LeaningDataStructures.Stack;

import java.util.Arrays;
import java.util.EmptyStackException;

public class MyStack<E> {
	public static final int DEFAULT_VALUE = 6; // 최소 용량 크기
	private Object[] arr; // 요소를 담을 내부 배열
	private int top; // 현재위치를 가르키는 포인터

	public MyStack() {
		this.arr = new Object[DEFAULT_VALUE];
		this.top = -1;
	}

	public boolean isFull() {
		return top == arr.length - 1;
	}

	public boolean isEmpty() {
		return top == -1;
	}

	public E push(E value) {
		if (isFull()) {
			resize();
		}
		top++;
		arr[top] = value;
		return value;
	}

	@SuppressWarnings("unchecked")
	public E pop() {
		if (isEmpty()) {
			throw new EmptyStackException();
		}
		E value = (E)arr[top];

		arr[top] = null;

		top--;

		resize();

		return value;

	}

	@SuppressWarnings("unchecked")
	public E peek() {
		if (isEmpty()) {
			throw new EmptyStackException();
		}
		return (E)arr[top];
	}

	private void resize() {
		int arrCapacity = arr.length - 1;
		// 용량이 꽉찬 경우
		if (top == arrCapacity) {
			int newCapacity = arrCapacity * 2;
			arr = Arrays.copyOf(arr, newCapacity);
			return;
		}
		if (top < (arrCapacity / 2)) {
			int halfCapacity = arrCapacity / 2;
			arr = Arrays.copyOf(arr, halfCapacity);
			return;
		}

	}

	public int search(E value) {
		for (int i = top; i > 0; i--) {
			if (arr[i].equals(value)) {
				return top - i + 1;
			}
		}
		return -1;
	}

	@Override
	public String toString() {
		return Arrays.toString(arr);
	}
}
