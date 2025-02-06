package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj3079_1 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int maxTime = 0;
		int[] times = new int[N];
		for (int i = 0; i < N; i++) {
			times[i] = Integer.parseInt(br.readLine());
			maxTime = Math.max(maxTime, times[i]);
		}
		long left = 0;
		long right = (long)M * maxTime;

		while (left < right) {
			long mid = (left + right) / 2;
			long sum = 0;
			for (int i = 0; i < N; i++) {
				sum += mid / times[i];
				if (sum >= M) break;
			}
			if (sum >= M) {
				right = mid;
			} else {
				left = mid + 1;
			}
		}
		System.out.println(left);
	}
}
