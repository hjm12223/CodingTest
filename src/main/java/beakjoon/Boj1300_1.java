package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Boj1300_1 {
	static int N, K;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		K = Integer.parseInt(br.readLine());

		long left = 1;
		long right = (long)N * N;
		long answer = 0;
		while (left <= right) {
			long mid = (left + right) / 2;
			if (count(mid) >= K) {
				answer = mid;
				right = mid - 1;
			} else {
				left = mid + 1;
			}
		}
		System.out.println(answer);
	}

	private static long count(long mid) {
		int cnt = 0;
		for (int i = 1; i <= N; i++) {
			cnt += (int)Math.min(mid / i, N);
		}
		return cnt;
	}
}