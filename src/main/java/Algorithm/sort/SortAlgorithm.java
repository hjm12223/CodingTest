package Algorithm.sort;

import java.util.Arrays;

public class SortAlgorithm {
	public static void main(String[] args) {
		int[] arr = new int[] {8, 74, 24, 41, 23, 754, 123, 32, 1, 45};
		// bubble(arr);
		// selection(arr);
		// insertion(arr);
		// quickSort(arr, 0, arr.length - 1);
		duallyPivotQuickSort(arr, 0, arr.length - 1);

	}

	public static void bubble(int[] arr) {
		for (int i = 0; i < arr.length; i++) {
			int temp = 0;
			for (int j = arr.length - 1; j > 0; j--) {
				if (arr[i] > arr[j]) {
					temp = arr[j];
					arr[j] = arr[i];
					arr[i] = temp;
				}
			}
		}
		System.out.println("arr = " + Arrays.toString(arr));
	}

	public static void selection(int[] arr) {
		for (int i = arr.length - 1; i > 0; i--) {
			int maxIndex = i;
			for (int j = i - 1; j > 0; j--) {
				if (arr[j] > arr[maxIndex]) maxIndex = j;
			}
			int temp = arr[maxIndex];
			arr[maxIndex] = arr[i];
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
		System.out.println("arr = " + Arrays.toString(arr));

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

	public static void duallyPivotQuickSort(int[] arr, int low, int high) {
		if (low < high) {
			int[] pivots = doublePartition(arr, low, high);
			int lp = pivots[0];  // 작은 피벗의 위치
			int rp = pivots[1];  // 큰 피벗의 위치

			// 피벗을 기준으로 왼쪽, 중간, 오른쪽 영역에 대해 재귀적으로 정렬
			duallyPivotQuickSort(arr, low, lp - 1);
			duallyPivotQuickSort(arr, lp + 1, rp - 1);
			duallyPivotQuickSort(arr, rp + 1, high);
			System.out.println("arr = " + Arrays.toString(arr));

		}
	}

	private static int[] doublePartition(int[] arr, int low, int high) {
		// 두 개의 피봇 선택
		if (arr[low] > arr[high]) {
			int temp = arr[low];
			arr[low] = arr[high];
			arr[high] = temp;
		}
		int p1 = arr[low];
		int p2 = arr[high];

		int i = low + 1; // 시작점
		int j = high - 1; // 끝점
		int k = low + 1;

		while (k <= j) {
			if (arr[k] < p1) {
				int temp = arr[i];
				arr[i] = arr[k];
				arr[k] = temp;
				i++;
				// arr[k] 가 작은 피봇 p1 보다 작다면 왼쪽에 위치시킨다
			} else if (arr[k] >= p2) {
				// arr[k]가 큰 피벗 p2보다 크면, 오른쪽에 위치시킨다.
				while (arr[j] > p2 && k < j) {
					j--;
				}
				int temp = arr[k];
				arr[k] = arr[j];
				arr[j] = temp;
				j--;
				if (arr[k] < p1) {
					int temp2 = arr[i];
					arr[i] = arr[k];
					arr[k] = temp2;
					i++;
				}
			}
			k++;
		}

		// 피벗을 적절한 위치로 이동
		i--;
		j++;
		int temp = arr[low];
		arr[low] = arr[i];
		arr[i] = temp;

		temp = arr[high];
		arr[high] = arr[j];
		arr[j] = temp;

		return new int[] {i, j};  // 피벗들의 새로운 위치 반환
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
