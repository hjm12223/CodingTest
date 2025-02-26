package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Boj21758 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine()); // 벌통의 길이
		int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

		int[] sum = new int[N];
		sum[0] = arr[0];
		for (int i = 1; i < N; i++) {
			sum[i] = sum[i - 1] + arr[i];
		}

		int maxHoney = 0;

		for (int i = 1; i < N - 1; i++) {
			int honey = (sum[N - 1] - arr[0] - arr[N - 1]) + arr[i];
			maxHoney = Math.max(maxHoney, honey);
		}

		for (int i = 1; i < N - 1; i++) {
			int honey = (sum[N - 1] - arr[0]) - arr[i] + (sum[N - 1] - sum[i]);
			maxHoney = Math.max(maxHoney, honey);
		}

		for (int i = 1; i < N - 1; i++) {
			int honey = (sum[N - 1] - arr[N - 1]) - arr[i] + sum[i - 1];
			maxHoney = Math.max(maxHoney, honey);
		}

		System.out.println(maxHoney);
	}
}
