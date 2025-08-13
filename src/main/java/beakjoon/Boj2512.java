package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj2512 {
	static int N, K;
	static int[] arr;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		arr = new int[N];
		st = new StringTokenizer(br.readLine());
		int max = 0;
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			max = Math.max(arr[i], max);
		}

		K = Integer.parseInt(br.readLine());
		long left = 1;
		long right = max;
		long result = 0;
		while (left <= right) {
			long mid = (left + right) / 2;
			if (check(mid)) {
				right = mid - 1;
			} else {
				result = mid;
				left = mid + 1;
			}
		}
		System.out.println(result);
	}

	private static boolean check(long mid) {
		long cnt = 0;
		for (int i = 0; i < N; i++) {
			cnt += Math.min(arr[i], mid);
		}
		return cnt > K;
	}
}
