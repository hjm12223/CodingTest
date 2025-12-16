package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj13702_2 {
	static long[] arr;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		arr = new long[N];

		long left = 0;
		long right = Integer.MAX_VALUE;

		for (int i = 0; i < N; i++) {
			int value = Integer.parseInt(br.readLine());
			arr[i] = value;
		}
		long result = 0;
		while (left <= right) {
			long mid = (left + right) / 2;
			if (check(mid, K)) {
				result = mid;
				left = mid + 1;
			} else {
				right = mid - 1;
			}
		}
		System.out.println(result);
	}

	private static boolean check(long mid, int k) {
		long cnt = 0;
		for (long l : arr) {
			cnt += (l / mid);
		}
		return cnt >= k;
	}
}
