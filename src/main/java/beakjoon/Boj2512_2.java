package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj2512_2 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		int result = Integer.MAX_VALUE;
		int[] arr = new int[N];
		int right = 0;

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			right = Math.max(arr[i], right);
		}
		int maxBudget = Integer.parseInt(br.readLine());

		int left = 0;
		while (left <= right) {
			int mid = (left + right) / 2;
			if (check(arr, mid, maxBudget)) {
				left = mid + 1;
				result = mid;
			} else {
				right = mid - 1;
			}
		}
		System.out.println(result);
	}

	private static boolean check(int[] arr, int mid, int maxBudget) {
		int cnt = 0;
		for (int i = 0; i < arr.length; i++) {
			cnt += Math.min(arr[i], mid);
		}
		return cnt <= maxBudget;
	}
}
