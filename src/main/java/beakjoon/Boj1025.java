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
				for (int dr = -N + 1; dr < N; dr++) { // 공차 row
					for (int dc = -M + 1; dc < M; dc++) { // 공차 col
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

	/**
	 move {{오른쪽 왼쪽 아래 위 대각선 } } 8 가지의 무브먼트를 두고 * 공차 문제를 풀었는데
	 틀린거야
	 진짜 내가 보기에는 답이없음

	 그래서 그냥 챗지피티를 썻다
	 1시간썻나

	 1
	 10
	 100
	 1000

	 2차원이다 보니깐
	 ...
	 ...
	 ...
	 ---
	 1..
	 ..1

	 1....
	 ..1..
	 ....1
	 */
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