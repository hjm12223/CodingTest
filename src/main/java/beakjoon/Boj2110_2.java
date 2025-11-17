package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj2110_2 {
	static int[] arr;
	static int N, M;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[N];
		int left = 1;
		int right = 0;

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			arr[i] = Integer.parseInt(st.nextToken());
			right = Math.max(arr[i], right);
		}
		Arrays.sort(arr);
		int result = 0;
		while (left <= right) {
			int mid = (left + right) / 2;
			if (check(mid)) {
				result = mid;
				left = mid + 1;
			} else {
				right = mid - 1;
			}
		}
		System.out.println(result);
	}

	private static boolean check(int mid) {
		int idx = 0;
		int cnt = 1;
		for (int i = 1; i < N; i++) {
			int dist = arr[i] - arr[idx];
			if (dist >= mid) {
				cnt++;
				idx = i;
			}
		}
		return cnt >= M;
	}
}
