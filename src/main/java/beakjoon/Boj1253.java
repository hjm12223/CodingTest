package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Boj1253 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		Arrays.sort(arr);
		int result = 0;

		for (int i = 0; i < N; i++) {
			if (isGood(arr, N, i)) {
				result++;
			}
		}

		System.out.println(result);
	}

	private static boolean isGood(int[] arr, int N, int index) {
		int target = arr[index];
		int left = 0, right = N - 1;

		while (left < right) {
			if (left == index) {
				left++;
				continue;
			}
			if (right == index) {
				right--;
				continue;
			}

			int sum = arr[left] + arr[right];
			if (sum == target) {
				return true;
			}
			if (sum < target) {
				left++;
			} else {
				right--;
			}
		}

		return false;
	}
}