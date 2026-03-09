package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Boj1940_2 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		Arrays.sort(arr);
		int result = 0;
		int left = 0;
		int right = N - 1;
		while (left < right) {
			int lV = arr[left];
			int rV = arr[right];
			int sum = lV + rV;
			if (sum == M) {
				result++;
				left++;
				right--;
			} else if (sum > M) {
				right--;
			} else {
				left++;
			}
		}
		System.out.println(result);
	}
}
