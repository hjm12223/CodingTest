package LeaningDataStructures.List;

import java.util.Arrays;

public class MyArrayList<E> implements MyList {

	private static final int DEFAULT_CAPACITY = 5;
	private static final Object[] EMPTY_ELEMENTDATA = {};
	Object[] elementData;
	private int size;

	public MyArrayList() {
		this.elementData = new Object[DEFAULT_CAPACITY];
		this.size = 0;
	}

	public MyArrayList(int capacity) {
		if (capacity > 0) {
			this.elementData = new Object[capacity];
		} else if (capacity == 0) {
			this.elementData = new Object[DEFAULT_CAPACITY];
		} else {
			throw new RuntimeException(new IllegalAccessException("리스트 용량을 잘못 설정하였습니다"));
		}
		this.size = 0;
	}

	private void resize() {
		int element_capacity = elementData.length;

		if (element_capacity == size) {
			int new_capacity = element_capacity * 2;

			elementData = Arrays.copyOf(elementData, new_capacity);
		}
		if ((element_capacity / 2) > size) {
			int half_capacity = element_capacity / 2;

			elementData = Arrays.copyOf(elementData, Math.max(half_capacity, DEFAULT_CAPACITY));
		}
		if (Arrays.equals(elementData, EMPTY_ELEMENTDATA)) {
			elementData = new Object[DEFAULT_CAPACITY];
			return;
		}
	}

	@Override
	public boolean add(Object value) {
		resize();

		elementData[size] = value;
		size++;

		return true;
	}

	@Override
	public void add(int index, Object value) {
		if (index < 0 || index > size) {
			throw new IndexOutOfBoundsException();
		}
		if (index == size) {
			add(value);
		} else {
			resize();

			for (int i = size; i > index; i--) {
				elementData[i] = elementData[i - 1];
			}

			elementData[index] = value;
			size++;
		}
	}

	@Override
	public boolean remove(Object value) {
		int idx=  indexOf(value);

		if(idx == -1) return false;

		remove(idx);

		return true;
	}

	@Override
	public Object remove(int index) {
		if (index < 0 || index > size) {
			throw new IndexOutOfBoundsException();
		}


		E element = (E) elementData[index]; // 반환 값 백업


		elementData[index] = null;
		for (int i = index ; i < size-1 ; i++){
			elementData[i] = elementData[i +1] ;
			elementData[i +1] = null;
		}
		size--;

		resize();
		return element;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Object get(int index) {
		if (index < 0 || index > size){
			throw new IndexOutOfBoundsException();
		}

		return elementData[index];
	}

	@Override
	public void set(int index, Object value) {

	}

	@Override
	public boolean contains(Object value) {
		return false;
	}

	@Override
	public int indexOf(Object value) {
		if (value == null) {
			for (int i = 0; i < size; i++) {
				if (elementData[i] == null) {
					return i;
				}
			}
		} else {
			for (int i = 0; i < size; i++) {
				if (elementData[i].equals(value)) return i;
			}
		}
		return -1;
	}

	@Override
	public int lastIndexOf(Object value) {
		if (value == null) {
			for (int i = size - 1; i > size; i--) {
				if (elementData[i] == null) {
					return i;
				}
			}
		} else {
			for (int i = size - 1; i >= 0; i--) {
				if (elementData[i].equals(value)) {
					return i; // 인덱스 반환
				}
			}
		}

		return -1;
	}

	@Override
	public int size() {
		return 0;
	}

	@Override
	public boolean isEmpty() {
		return false;
	}

	@Override
	public void clear() {

	}
}
