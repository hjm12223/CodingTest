package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj6209 {
	static int D, N, M;

	static int[] stones;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		D = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		stones = new int[N + 2];
		stones[0] = 0;
		stones[N + 1] = D;

		for (int i = 1; i <= N; i++) {
			stones[i] = Integer.parseInt(br.readLine());
		}

		Arrays.sort(stones);

		int left = 1;
		int right = D;
		int result = 0;

		while (left <= right) {
			int mid = (left + right) / 2;

			if (check(mid) > M) {
				right = mid - 1;
			} else {
				result = mid;
				left = mid + 1;
			}
		}
		System.out.println(result);
	}

	private static int check(int mid) {
		int cnt = 0;
		int prev = 0;
		for (int i = 1; i < stones.length; i++) {
			if (stones[prev] + mid > stones[i]) {
				cnt++;
			} else {
				prev = i;
			}
		}
		return cnt;
	}
}
