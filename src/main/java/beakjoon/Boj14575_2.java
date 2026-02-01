package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj14575_2 {
	static int[] L, R;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int T = Integer.parseInt(st.nextToken());
		L = new int[N];
		R = new int[N];

		long sumL = 0;
		long sumR = 0;
		int maxR = 0;

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			L[i] = Integer.parseInt(st.nextToken());
			R[i] = Integer.parseInt(st.nextToken());

			sumL += L[i];
			sumR += R[i];
			maxR = Math.max(maxR, R[i]);
		}
		if (sumL > T || sumR < T) {
			System.out.println(-1);
			return;
		}
		int left = 0;
		int right = maxR;
		int result = maxR;

		while (left <= right) {
			int mid = (left + result) / 2;
			long maxSum = 0;

			for (int i = 0; i < N; i++) {
				maxSum += Math.min(R[i], mid);
			}
			if (sumL <= T && T <= maxSum) {
				result = mid;
				right = mid - 1;
			} else {
				left = mid + 1;
			}
		}
		System.out.println(result);
	}
}
