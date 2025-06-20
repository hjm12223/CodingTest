package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj2110 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken()); // 공유기의 갯수
		int[] arr = new int[N];
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}

		Arrays.sort(arr);
		long left = 1;
		long right = arr[N - 1] - arr[0];

		long result = 0;
		while (left <= right) {

			long mid = (left + right) / 2;
			if (check(mid, arr, C)) {
				result = mid;
				left = mid + 1;
			} else {
				right = mid - 1;
			}
		}
		System.out.println(result);
	}

	private static boolean check(long mid, int[] arr, int c) {
		int cnt = 1;
		int last = arr[0];
		for (int i = 1; i < arr.length; i++) {
			if (arr[i] - last >= mid) {
				cnt++;
				last = arr[i];
			}
		}
		return c <= cnt;
	}

	private boolean isEmpty() {
		return false;
	}
}
