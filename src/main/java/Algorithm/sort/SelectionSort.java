package Algorithm.sort;

import java.util.Arrays;

public class SelectionSort {
	public static void main(String[] args) {
		int[] arr = new int[] {4, 2, 6, 71, 23, 46};
		int tmp = 0;
		for (int i = 0; i < arr.length - 1; i++) {
			int least = i;
			for (int j = i + 1; j < arr.length; j++) {
				if (arr[j] < arr[least]) {
					least = j;
				}
			}
			tmp = arr[i];
			arr[i] = arr[least];
			arr[least] = tmp;
			System.out.println(i + " : " + "arr = " + Arrays.toString(arr));
		}
		System.out.println("arr = " + Arrays.toString(arr));
	}
}
