package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj1025 {
	static int N, M;
	static int[][] arr;
	static int result = -1;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[N][M];
		for (int i = 0; i < N; i++) {
			arr[i] = Arrays.stream(br.readLine().split("")).mapToInt(Integer::parseInt).toArray();
		}
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				for (int dr = -N + 1; dr < N; dr++) {
					for (int dc = -M + 1; dc < M; dc++) {
						if (dr == 0 && dc == 0) {
							if (check(arr[i][j])) result = Math.max(result, arr[i][j]);
							continue;
						}
						dfs(i, j, 0, dr, dc);
					}
				}
			}
		}
		System.out.println(result);
	}

	private static void dfs(int r, int c, int value, int dr, int dc) {
		if (r < 0 || r >= N || c < 0 || c >= M) return;

		int nextValue = value * 10 + arr[r][c];

		if (check(nextValue)) {
			result = Math.max(result, nextValue);
		}
		dfs(r + dr, c + dc, nextValue, dr, dc);

	}

	private static boolean check(int n) {
		if (n < 0) return false;
		int sqrt = (int)Math.sqrt(n);
		return sqrt * sqrt == n;
	}
}
/*
1. N * M  매트릭스 A 가 존재하며 해당
2. 공차를 기반으로 매트릭스를 탐색
3. 만약 해당 수가 완전 제곱수 일 시 result 값 갱신
 */