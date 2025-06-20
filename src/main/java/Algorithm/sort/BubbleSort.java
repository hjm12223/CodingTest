package Algorithm.sort;

import java.util.Arrays;

public class BubbleSort {
	public static void main(String[] args) {
		int[] arr = new int[] {42, 123, 5, 2, 1, 23, 53, 57};
		for (int i = arr.length - 1; i >= 0; i--) {
			boolean changed = false;
			for (int j = 0; j < arr.length - 1; j++) {
				if (arr[j] > arr[j + 1]) {
					int temp = arr[j];
					arr[j] = arr[j + 1];
					arr[j + 1] = temp;
					changed = true;
				}
			}
			System.out.println(Arrays.toString(arr));
			if (!changed) break;
		}
	}
}
