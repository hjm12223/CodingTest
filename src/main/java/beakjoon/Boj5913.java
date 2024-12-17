package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj5913 {
	static int[][] arr;
	static int[] dx = new int[] {1, 0, 0, -1};
	static int[] dy = new int[] {0, 1, -1, 0};

	static int target = 25;
	static int result = 0;
	static boolean[][] isVisited = new boolean[5][5];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken()); // 가로막을 벽의 갯수
		arr = new int[5][5];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int row = Integer.parseInt(st.nextToken()) - 1;
			int col = Integer.parseInt(st.nextToken()) - 1;
			arr[row][col] = -1;
		}
		target = target - N;
		dfs(0, 0, 1);
		System.out.println(result);
	}

	private static void dfs(int jx, int jy, int cnt) {
		isVisited[jx][jy] = true;
		if (cnt == target && jx == 4 && jy == 4) {
			result++;
			return;
		}
		for (int d = 0; d < 4; d++) {
			int nextJx = jx + dx[d];
			int nextJy = jy + dy[d];
			if (isValid(nextJx, nextJy)) {
				if (!isVisited[nextJx][nextJy]) {
					isVisited[nextJx][nextJy] = true;
					dfs(nextJx, nextJy, cnt + 1);
					isVisited[nextJx][nextJy] = false;
				}
			}
		}
	}

	private static boolean isValid(int x, int y) {
		if (x < 0 || x >= 5 || y < 0 || y >= 5 || arr[x][y] == -1) return false;
		return true;
	}
}
