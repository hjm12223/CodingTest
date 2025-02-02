package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj1300 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(br.readLine());

		long left = 0;
		long right = (long)N * N;
		long answer = 0;
		while (left <= right) {
			long mid = (left + right) / 2;
			if (count(mid, N) >= K) {
				answer = mid;
				right = mid - 1;
			} else {
				left = mid + 1;
			}
		}
		System.out.println(answer);
	}

	private static long count(long mid, int n) {
		long cnt = 0;
		for (int i = 1; i <= n; i++) {
			cnt += Math.min(mid / i, n);
		}
		return cnt;
	}
}
