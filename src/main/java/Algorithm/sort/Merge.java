package Algorithm.sort;

import java.util.Arrays;

public class Merge {
	private static int[] sorted;

	public static void main(String[] args) {
		int[] arr = new int[] {8, 74, 24, 41, 23, 754, 123, 32, 1, 45};
		sorted = new int[arr.length];
		mergeSort(arr, 0, arr.length - 1);
		System.out.println("arr = " + Arrays.toString(arr));
	}

	static void mergeSort(int[] arr, int start, int end) {
		if (start < end) {
			int middle = (start + end) / 2;
			mergeSort(arr, start, middle);
			mergeSort(arr, middle + 1, end);
			merge(arr, start, middle, end);
		}
	}

	private static void merge(int[] a, int left, int mid, int right) {
		int l = left;        // 왼쪽 부분리스트 시작점
		int r = mid + 1;    // 오른쪽 부분리스트의 시작점
		int idx = left;        // 채워넣을 배열의 인덱스

		while (l <= mid && r <= right) {
			/*
			 *  왼쪽 부분리스트 l번째 원소가 오른쪽 부분리스트 r번째 원소보다 작거나 같을 경우
			 *  왼쪽의 l번째 원소를 새 배열에 넣고 l과 idx를 1 증가시킨다.
			 */
			if (a[l] <= a[r]) {
				sorted[idx] = a[l];
				idx++;
				l++;
			}
			/*
			 *  오른쪽 부분리스트 r번째 원소가 왼쪽 부분리스트 l번째 원소보다 작거나 같을 경우
			 *  오른쪽의 r번째 원소를 새 배열에 넣고 r과 idx를 1 증가시킨다.
			 */
			else {
				sorted[idx] = a[r];
				idx++;
				r++;
			}
		}

		/*
		 * 왼쪽 부분리스트가 먼저 모두 새 배열에 채워졌을 경우 (l > mid)
		 * = 오른쪽 부분리스트 원소가 아직 남아있을 경우
		 * 오른쪽 부분리스트의 나머지 원소들을 새 배열에 채워준다.
		 */
		if (l > mid) {
			while (r <= right) {
				sorted[idx] = a[r];
				idx++;
				r++;
			}
		}

		/*
		 * 오른쪽 부분리스트가 먼저 모두 새 배열에 채워졌을 경우 (r > right)
		 * = 왼쪽 부분리스트 원소가 아직 남아있을 경우
		 * 왼쪽 부분리스트의 나머지 원소들을 새 배열에 채워준다.
		 */
		else {
			while (l <= mid) {
				sorted[idx] = a[l];
				idx++;
				l++;
			}
		}

		/*
		 * 정렬된 새 배열을 기존의 배열에 복사하여 옮겨준다.
		 */
		for (int i = left; i <= right; i++) {
			a[i] = sorted[i];
		}
	}
}
