package Algorithm.sort;

import java.util.Arrays;

public class SortAlgorithm {
	public static void main(String[] args) {
		int[] arr = new int[] {8, 74, 24, 41, 23, 754, 123, 32, 1, 45};
		// bubble(arr);
		// selection(arr);
		insertion(arr);
		quickSort(arr, 0, arr.length - 1);
	}

	public static void bubble(int[] arr) {
		for (int i = 0; i < arr.length - 1; i++) {
			boolean change = false;
			for (int j = 0; j < arr.length - i - 1; j++) {
				System.out.println("arr = " + Arrays.toString(arr));
				if (arr[j] > arr[j + 1]) {
					change = true;
					int temp = arr[j];
					arr[j] = arr[j + 1];
					arr[j + 1] = temp;
				}
			}
			if (!change) break;
		}
	}

	public static void selection(int[] arr) {
		for (int i = 0; i < arr.length - 1; i++) {
			int minIndex = i;
			for (int j = i + 1; j < arr.length; j++) {
				if (arr[j] < arr[minIndex]) minIndex = j;
			}
			int temp = arr[minIndex];
			arr[minIndex] = arr[i];
			arr[i] = temp;
			System.out.println("arr = " + Arrays.toString(arr));
		}
	}

	public static void insertion(int[] arr) {
		for (int i = 1; i < arr.length; i++) {
			int key = arr[i];
			int j = i - 1;

			while (j >= 0 && arr[j] > key) {
				arr[j + 1] = arr[j];
				j--;
			}
			arr[j + 1] = key;

		}
	}

	public static void quickSort(int[] arr, int left, int right) {
		if (left < right) {
			// 왼쪽 피벗을 기준으로 분할 후 피벗의 위치를 얻음
			int pivotIndex = partition(arr, left, right);

			// 피벗을 기준으로 왼쪽과 오른쪽 부분 배열을 재귀적으로 정렬
			quickSort(arr, left, pivotIndex - 1);
			quickSort(arr, pivotIndex + 1, right);
		}
	}

	public static int partition(int[] arr, int left, int right) {
		// 가장 왼쪽 요소를 피벗으로 선택
		int pivot = arr[left];
		int i = left + 1;
		int j = right;

		// i와 j가 교차할 때까지 반복
		while (i <= j) {
			// 피벗보다 큰 값을 찾을 때까지 i 증가
			while (i <= right && arr[i] <= pivot) {
				i++;
			}

			// 피벗보다 작은 값을 찾을 때까지 j 감소
			while (j >= left && arr[j] > pivot) {
				j--;
			}

			// i가 j보다 작으면 두 요소를 교환
			if (i < j) {
				swap(arr, i, j);
			}
		}

		// 피벗과 j 위치의 값을 교환하여 피벗을 올바른 위치에 배치
		swap(arr, left, j);
		return j; // 피벗의 위치 반환
	}

	// 두 인덱스의 배열 요소를 교환하는 메서드
	public static void swap(int[] arr, int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}
}
