package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj2096 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[][] maxDp = new int[2][3];
		int[][] minDp = new int[2][3];

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < 3; i++) {
			int value = Integer.parseInt(st.nextToken());
			maxDp[0][i] = value;
			minDp[0][i] = value;
		}

		for (int i = 1; i < N; i++) {
			int[] curr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			maxDp[1][0] = Math.max(maxDp[0][1], maxDp[0][0]) + curr[0];
			maxDp[1][1] = Math.max(Math.max(maxDp[0][1], maxDp[0][0]), maxDp[0][2]) + curr[1];
			maxDp[1][2] = Math.max(maxDp[0][1], maxDp[0][2]) + curr[2];

			minDp[1][0] = Math.min(minDp[0][1], minDp[0][0]) + curr[0];
			minDp[1][1] = Math.min(Math.min(minDp[0][1], minDp[0][0]), minDp[0][2]) + curr[1];
			minDp[1][2] = Math.min(minDp[0][1], minDp[0][2]) + curr[2];
			for (int j = 0; j < 3; j++) {
				maxDp[0][j] = maxDp[1][j];
				minDp[0][j] = minDp[1][j];
			}
		}
		System.out.println(Math.max(Math.max(maxDp[0][0], maxDp[0][1]), maxDp[0][2]));
		System.out.println(Math.min(Math.min(minDp[0][0], minDp[0][1]), minDp[0][2]));
	}
}