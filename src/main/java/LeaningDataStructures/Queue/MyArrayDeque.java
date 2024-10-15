package LeaningDataStructures.Queue;

public class MyArrayDeque<E> {
	private static final int DEFAULT_SIZE = 64;
	private Object[] array;
	private int front;
	private int rear;
	private int size;

	public MyArrayDeque() {
		this.array = new Object[DEFAULT_SIZE];
		this.front = 0;
		this.rear = 0;
		this.size = 0;
	}

	public MyArrayDeque(int capacity) {
		this.array = new Object[capacity];
		this.front = 0;
		this.rear = 0;
		this.size = 0;
	}

	public void resize(int newCapacity) {
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

	public boolean offer(E value) {
		return offerLast(value);
	}

	public boolean offerLast(E value) {
		if ((rear + 1) % array.length == size) {
			resize(size * 2);
		}
		rear = (rear + 1) % array.length;
		array[rear] = value;
		size++;
		return true;
	}
}
