package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Boj20117 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		Arrays.sort(arr);
		int result = 0;
		if (N % 2 == 0) {
			for (int i = 0; i < N / 2; i++) {
				result += arr[N - i - 1] * 2;
			}
		} else {
			for (int i = 0; i < N / 2; i++) {
				result += arr[N - i - 1] * 2;
			}
			result += arr[N / 2];
		}
		System.out.println(result);
	}
}
