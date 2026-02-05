package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Boj22945 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		int left = 0;
		int right = N - 1;
		long result = 0;
		while (left < right) {
			result = Math.max((long)Math.min(arr[left], arr[right]) * (right - left - 1), result);
			if (arr[left] < arr[right]) {
				left++;
			} else {
				right--;
			}
		}
		System.out.println(result);
	}
}
/*
N = 100_000 == N^2 풀이 불가
10000 * 10000
이분탐색?
투포인터?

 */