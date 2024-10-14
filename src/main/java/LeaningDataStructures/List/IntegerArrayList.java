package LeaningDataStructures.List;

import java.io.IOException;

public class IntegerArrayList implements IntegerListInterface{

	private Integer[] item;
	private int numItems; // 총길이
	private static final int DEFAULT_CAPACITY = 64;

	public IntegerArrayList() {
		item = new Integer[DEFAULT_CAPACITY];
		numItems = 0;
	}

	public IntegerArrayList(int n) {
		this.item = new Integer[n];
		numItems = 0;
	}
	@Override
	public void add(int index, Integer x) throws IOException {
		if (numItems >= item.length || index < 0 || index> numItems){
			throw new IOException();
		} else {
			for(int i = numItems -1; i >= index ; i--){
				item[index] = x;
				numItems++;
			}
		}
	}

	@Override
	public void append(Integer x) throws IOException {
		if (numItems >= item.length){
			throw new IOException();
		}else {
			item[numItems++] = x;
		}
	}

	@Override
	public Integer remove(int index) {
		if (isEmpty() || index < 0 || index > numItems- 1) {
			return null;
		}else{
			Integer tmp = item[index]; // 7 번째 원소 삭제
			// 총 원소의 길이는 10
			for (int i = 0 ; i <= numItems -2 ; i++)
				item[i] = item[i+1];
			numItems--;
			return tmp;
		}
	}

	@Override
	public boolean removeItem(Integer x) {
		int k = 0;
		while (k< numItems && item[k].compareTo(x) != 0){
			k++;
			if (k == numItems){
				return false;
			}
			else {
				for (int i = k; i <= numItems -2 ; i++)
					item[i] = item[i+1];
				numItems--;
				return true;
			}
		}
		return false;
	}

	@Override
	public Integer get(int i) {
		return null;
	}

	@Override
	public void set(int i, Integer x) {

	}

	@Override
	public int indexOf(Integer x) {
		return 0;
	}

	@Override
	public int len() {
		return 0;
	}

	@Override
	public boolean isEmpty() {
		return false;
	}

	@Override
	public void clear() {
		item = new Integer[item.length];
		numItems = 0;
	}
}
