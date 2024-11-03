package LeaningDataStructures.Heap;

import java.util.Arrays;
import java.util.Comparator;
import java.util.NoSuchElementException;

public class Heap<E> {
	private static final int DEFAULT_CAPACITY = 10;
	private final Comparator<? super E> comparator;
	private int size;
	private Object[] array;

	public Heap() {
		this(null);
	}

	public Heap(Comparator<? super E> comparator) {
		this.comparator = comparator;
		this.size = 0;
		this.array = new Object[DEFAULT_CAPACITY];
	}

	public Heap(Comparator<? super E> comparator, int capacity) {
		this.comparator = comparator;
		this.size = 0;
		this.array = new Object[capacity];
	}

	private int getParent(int index) {
		return index / 2;
	}

	private int getLeftChild(int index) {
		return index * 2;
	}

	private int getRightChild(int index) {
		return index * 2 + 1;
	}

	private void resize(int newCapacity) {
		Object[] newArray = new Object[newCapacity];
		for (int i = 1; i <= size; i++) {
			newArray[i] = array[i];
		}

		this.array = null;
		this.array = newArray;
	}

	public void add(E value) {
		if (size + 1 == array.length) {
			resize(array.length * 2);
		}
		shiftUp(size + 1, value);
		size++;
	}

	private void shiftUp(int idx, E target) {
		if (comparator != null) {
			siftUpComparator(idx, target, comparator);
		} else {
			siftUpComparable(idx, target);
		}
	}

	@SuppressWarnings("unchecked")
	private void siftUpComparable(int idx, E target) {
		Comparable<? super E> comp = (Comparable<? super E>)target;
		while (idx > 1) {
			int parent = getParent(idx);
			Object parentVal = array[parent];

			if (comp.compareTo((E)parentVal) >= 0) {
				break;
			}
			array[idx] = parentVal;
			idx = parent;
		}
		array[idx] = target;

	}

	@SuppressWarnings("unchecked")
	public E remove() {
		if (array[1] == null) {
			throw new NoSuchElementException();
		}
		E result = (E)array[1];
		E target;
		if (size == 1) {
			target = null;
		} else {
			target = (E)array[size];
		}
		array[size] = null;
		siftDown(1, target);

		return result;
	}

	private void siftDown(int idx, E target) {
		if (comparator != null) {
			siftDownComparator(idx, target, comparator);
		} else {
			siftDownComparable(idx, target);
		}

	}

	@SuppressWarnings("unchecked")
	private void siftDownComparator(int idx, E target, Comparator<? super E> comp) {
		array[idx] = null;
		size--;

		int parent = idx;
		int child;

		while ((child = getLeftChild(parent)) <= size) {
			int right = getRightChild(parent);// 오른쪽 자식 인덱스

			Object childVal = array[child]; // 왼쪽 자식의 값

			if (right <= size && comp.compare((E)childVal, (E)array[right]) > 0) {
				child = right;
				childVal = array[child];
			}
			if (comp.compare(target, (E)childVal) <= 0) {
				break;
			}
			array[parent] = childVal;
			parent = child;
		}
		// 최종적으로 재배치 되는 위치에 타겟이 된 값을 넣어준다.
		array[parent] = target;

		/*
		 *  용적의 사이즈가 최소 용적보다는 크면서 요소의 개수가 전체 용적의 1/4일경우
		 *  용적을 반으로 줄임(단, 최소용적보단 커야함)
		 */
		if (array.length > DEFAULT_CAPACITY && size < array.length / 4) {
			resize(Math.max(DEFAULT_CAPACITY, array.length / 2));
		}

	}

	// Comparable을 이용한 sift-down
	@SuppressWarnings("unchecked")
	private void siftDownComparable(int idx, E target) {

		Comparable<? super E> comp = (Comparable<? super E>)target;

		array[idx] = null;
		size--;

		int parent = idx;
		int child;

		while ((child = getLeftChild(parent)) <= size) {

			int right = getRightChild(parent);

			Object childVal = array[child];

			if (right <= size && ((Comparable<? super E>)childVal).compareTo((E)array[right]) > 0) {
				child = right;
				childVal = array[child];
			}

			if (comp.compareTo((E)childVal) <= 0) {
				break;
			}
			array[parent] = childVal;
			parent = child;

		}
		array[parent] = comp;

		if (array.length > DEFAULT_CAPACITY && size < array.length / 4) {
			resize(Math.max(DEFAULT_CAPACITY, array.length / 2));
		}

	}

	public int size() {
		return this.size;
	}

	@SuppressWarnings("unchecked")
	public E peek() {
		if (array[1] == null) {
			throw new NoSuchElementException();
		}
		return (E)array[1];
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public Object[] toArray() {
		return Arrays.copyOf(array, size + 1);
	}

	@SuppressWarnings("unchecked")
	private void siftUpComparator(int idx, E target, Comparator<? super E> comp) {
		while (idx > 1) {
			int parent = getParent(idx);
			Object parentVal = array[parent];

			if (comp.compare(target, (E)parentVal) >= 0) {
				break;
			}
			array[idx] = parentVal;
			idx = parent;

		}
		array[idx] = target;
	}
}
