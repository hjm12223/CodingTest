package Algorithm.sort;

import java.util.Arrays;

public class InsertionSort {
	public static void main(String[] args) {
		int[] arr = new int[] {123, 42, 5, 2, 1, 23, 53, 57};
		for (int i = 1; i < arr.length; i++) {
			int key = arr[i];
			int j = i - 1;
			while (j >= 0 && arr[j] > key) {
				arr[j + 1] = arr[j];
				j--;
			}
			arr[j + 1] = key;
			System.out.println("arr = " + Arrays.toString(arr));
		}
	}
}
