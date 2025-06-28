package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj1477 {
	static int L;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); // 현재 고속도로 갯수
		int M = Integer.parseInt(st.nextToken()); // 더 지을려는 수
		L = Integer.parseInt(st.nextToken());
		int[] arr = new int[N + 2];
		st = new StringTokenizer(br.readLine());

		for (int i = 0; i < N; i++) {
			arr[i + 1] = Integer.parseInt(st.nextToken());
		}
		arr[0] = 0;
		arr[N + 1] = L;

		int left = 1;
		int right = L - 1;
		Arrays.sort(arr);

		int result = 0;
		while (left <= right) {
			int mid = (left + right) / 2;
			if (check(mid, arr, M)) {
				result = mid;
				right = mid - 1;
			} else {
				left = mid + 1;
			}
		}
		System.out.println(result);
	}

	private static boolean check(int mid, int[] arr, int m) {
		int cnt = 0;
		for (int i = 1; i < arr.length; i++) {
			int gap = arr[i] - arr[i - 1];
			if (gap > mid) {
				cnt += (gap - 1) / mid;
			}
		}
		return cnt <= m;
	}
}