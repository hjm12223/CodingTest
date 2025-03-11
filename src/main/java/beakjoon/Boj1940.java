package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Boj1940 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine()); // 갑옷의 갯수
		int M = Integer.parseInt(br.readLine()); // 갑옷을 만들 수 있는 재료의 수
		int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).sorted().toArray();

		int left = 0;
		int right = N - 1;
		int result = 0;
		while (left < right) {
			int leftVal = arr[left];
			int rightVal = arr[right];
			int val = leftVal + rightVal;
			if (val == M) {
				result++;
				left++;
				right--;
			} else if (val < M) {
				left++;
			} else {
				right--;
			}
		}
		System.out.println(result);
	}
}
