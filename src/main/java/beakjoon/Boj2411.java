package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Boj2411 {
	static int N, M, A, B;
	static int[][] moves = new int[][] {{0, 1}, {1, 0}};
	static int[][] arr;
	static int[][] dp;
	static List<int[]> points = new ArrayList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		A = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());

		arr = new int[N][M];
		dp = new int[N][M];
		for (int i = 0; i < A; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken()) - 1;
			int y = Integer.parseInt(st.nextToken()) - 1;
			points.add(new int[] {x, y});
		}
		Collections.sort(points, (o1, o2) -> {
			if (o1[0] == o2[0]) {
				return o1[1] - o2[1];
			}
			return o1[0] - o2[0];
		});

		for (int i = 0; i < B; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken()) - 1;
			int y = Integer.parseInt(st.nextToken()) - 1;
			arr[x][y] = 1;
		}
		points.add(0, new int[] {0, 0});
		points.add(new int[] {N - 1, M - 1});

		int result = 1;

		for (int i = 0; i < points.size() - 1; i++) {
			int[] start = points.get(i);
			int[] end = points.get(i + 1);

			int ways = countPaths(start, end);

			if (ways == 0) {
				result = 0;
				break;
			}

			result *= ways;
		}
		System.out.println(result);
	}

	private static int countPaths(int[] start, int[] end) {
		int[][] dp = new int[N][M];
		dp[start[0]][start[1]] = 1;
		for (int i = start[0]; i <= end[0]; i++) {
			for (int j = start[1]; j <= end[1]; j++) {
				if (arr[i][j] == 1) {
					dp[i][j] = 0;
					continue;
				}
				if (i == start[0] && j == start[1]) continue;

				int down = (i > start[0]) ? dp[i - 1][j] : 0;
				int right = (j > start[1]) ? dp[i][j - 1] : 0;
				dp[i][j] = down + right;
			}
		}
		System.out.println(Arrays.deepToString(dp));
		return dp[end[0]][end[1]];
	}
}
/**
 [[0, 1, 0, 0, 2, 0, 0, 0],
 [0, 2, 0, 0, 1, 0, 2, 0],
 [0, 0, 0, 0, 1, 2, 0, 0],
 [0, 0, 0, 0, 0, 0, 1, 0],
 [0, 0, 0, 0, 0, 0, 0, 0]]
 */