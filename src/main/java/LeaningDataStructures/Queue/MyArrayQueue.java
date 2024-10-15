package LeaningDataStructures.Queue;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Queue;

public class MyArrayQueue<E> implements Queue<E> {
	
	private static final int DEFAULT_CAPACITY = 64;

	private Object[] array; // 요소를 저장할 배열
	private int size; // 배열의 크기

	private int front; // 시작 인덱스를 가르키는 포인터
	private int rear; // 끝 인덱스를 가르키는 포인터

	public MyArrayQueue() {
		this.array = new Object[DEFAULT_CAPACITY];
		this.size = 0;
		this.front = 0;
		this.rear = 0;
	}

	public MyArrayQueue(int capacity) {
		this.array = new Object[capacity];
		this.size = 0;
		this.front = 0;
		this.rear = 0;
	}

	private void resize(int newCapacity) {
		int arrayCapacity = array.length;
		Object[] newArray = new Object[newCapacity];

		for (int i = 1, j = front + 1; i <= size; i++, j++) {
			newArray[i] = array[j % arrayCapacity];
		}

		this.array = null;
		this.array = newArray;

		front = 0;
		rear = size;
	}

	@Override
	public boolean offer(E value) {

		if ((rear + 1) % array.length == front) {
			resize(array.length * 2);
		}

		rear = (rear + 1) % array.length;
		array[rear] = value;
		size++;

		return true;
	}

	@Override
	@SuppressWarnings("unchecked")
	public E poll() {

		if (size == 0) {
			return null;
		}

		front = (front + 1) % array.length;

		E value = (E)array[front];

		array[front] = null;
		size--;

		if (array.length > DEFAULT_CAPACITY && size < (array.length / 4)) {
			resize(Math.max(DEFAULT_CAPACITY, array.length / 2));
		}

		return value;

	}

	@Override
	public E remove() {

		E value = poll();

		if (value == null) {
			throw new NoSuchElementException();
		}
		return value;

	}

	@Override
	@SuppressWarnings("unchecked")
	public E peek() {

		if (size == 0) {
			return null;
		}

		front = (front + 1) % array.length;

		return (E)array[front];
	}

	public E element() {
		E value = peek();

		if (value == null) {
			throw new NoSuchElementException();
		}
		return value;

	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public boolean contains(Object value) {

		int start = (front + 1) % array.length;

		for (int i = 0, idx = start; i < size; i++, idx = (idx + 1) % array.length) {
			if (array[idx].equals(value)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public Iterator<E> iterator() {
		return null;
	}

	public Object[] toArray() {
		return toArray(new Object[size]);
	}

	@SuppressWarnings("unchecked")
	public <T> T[] toArray(T[] a) {

		final T[] res;
		// 들어오는 배열의 길이가 큐의 요소 개수보다 작은경우
		if (a.length < size) {
			/*
			 * front가 rear보다 앞에 있을 경우 (또는 요소가 없을 경우 f==r)
			 *  ______________________
			 *  |  |  |  |  |  |  |  |
			 *  ˉˉˉˉˉˉˉˉˉˉˉˉˉˉˉˉˉˉˉˉˉˉ
			 *    	f        r
			 */
			if (front <= rear) {
				return (T[])Arrays.copyOfRange(array, front + 1, rear + 1, a.getClass());
			}

			/*
			 * front가 rear보다 뒤에 있을 경우
			 *  ______________________
			 *  |  |  |  |  |  |  |  |
			 *  ˉˉˉˉˉˉˉˉˉˉˉˉˉˉˉˉˉˉˉˉˉˉ
			 *    	r        f
			 */

			res = (T[])Arrays.copyOfRange(array, 0, size, a.getClass());
			int rearlength = array.length - 1 - front;    // 뒷 부분의 요소 개수

			// 뒷 부분 복사
			if (rearlength > 0) {
				System.arraycopy(array, front + 1, res, 0, rearlength);
			}
			// 앞 부분 복사
			System.arraycopy(array, 0, res, rearlength, rear + 1);

			return res;
		}


		/*
		 * front가 rear보다 앞에 있을 경우 (또는 요소가 없을 경우 f==r)
		 *  ______________________
		 *  |  |  |  |  |  |  |  |
		 *  ˉˉˉˉˉˉˉˉˉˉˉˉˉˉˉˉˉˉˉˉˉˉ
		 *    	f        r
		 */
		if (front <= rear) {
			System.arraycopy(array, front + 1, a, 0, size);
		}


		/*
		 * front가 rear보다 뒤에 있을 경우
		 *  ______________________
		 *  |  |  |  |  |  |  |  |
		 *  ˉˉˉˉˉˉˉˉˉˉˉˉˉˉˉˉˉˉˉˉˉˉ
		 *    	r        f
		 */
		else {

			int rearlength = array.length - 1 - front;    // 뒷 부분의 요소 개수

			// 뒷 부분 복사
			if (rearlength > 0) {
				System.arraycopy(array, front + 1, a, 0, rearlength);
			}
			// 앞 부분 복사
			System.arraycopy(array, 0, a, rearlength, rear + 1);
		}

		return a;
	}

	public void clear() {
		for (int i = 0; i < size; i++) {
			array[i] = null;
		}

		front = rear = size = 0;
	}

	@Override
	public boolean add(E e) {
		return offer(e);
	}

	@Override
	public boolean remove(Object o) {
		return false;
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		return false;
	}

	@Override
	public boolean addAll(Collection<? extends E> c) {
		return false;
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		return false;
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		return false;
	}
}
