package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj15810_2 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		int[] balloons = new int[N];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			balloons[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(balloons);
		long left = 0;
		long right = (long)balloons[N - 1] * M;
		long result = 0;

		while (left <= right) {
			long mid = (right + left) / 2;
			if (check(balloons, M, mid)) {
				right = mid - 1;
				result = mid;
			} else {
				left = mid + 1;
			}
		}
		System.out.println(result);
	}

	private static boolean check(int[] balloons, int M, long mid) {
		long cnt = 0;
		for (int balloon : balloons) {
			cnt += (mid / balloon);
		}
		return cnt >= M;
	}
}

/*
3 8
1 1 1
1초때 3개
2초떄 6개
3초때 9개


 */
