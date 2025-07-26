package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj3020 {
	static int result = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int H = Integer.parseInt(st.nextToken());
		int h = 0;
		int[] arr = new int[N];
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		int left = 0;
		int right = H / 2;
		while (left <= right) {
			int mid = (left + right) / 2;
			if (check(mid, arr, H)) {
				h = mid;
				left = mid + 1;

			} else {
				right = mid - 1;
			}
		}
		System.out.println(result);
		System.out.println(h);
		h = 0;
		left = H / 2;
		right = H;
		while (left <= right) {
			int mid = (left + right) / 2;
			if (check(mid, arr, H)) {
				h = mid;
				left = mid + 1;

			} else {
				right = mid - 1;
			}
		}
		System.out.println(result);
		System.out.println(h);
	}

	private static boolean check(int mid, int[] arr, int h) {
		int cnt = 0;
		if (mid > h) return false;
		for (int i = 0; i < arr.length; i++) {
			if (i % 2 == 0) {
				if (mid <= arr[i]) cnt++;
			} else {
				if (h - arr[i] - mid <= 0) cnt++;
			}
		}
		if (result > cnt) {
			result = cnt;
		}
		return result >= cnt;
	}
}
