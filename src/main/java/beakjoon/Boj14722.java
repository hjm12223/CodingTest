package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj14722 {
	static int result = Integer.MIN_VALUE;
	static boolean[][] visited;

	static int[][] moves = new int[][] {{0, 1}, {1, 0}};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());

		int[][] arr = new int[N][N];
		visited = new boolean[N][N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		visited[0][0] = true;
		dfs(arr, N, 0, 0, 1, arr[0][0]);
		System.out.println(result);
	}

	private static void dfs(int[][] arr, int n, int x, int y, int cnt, int prevDrink) {
		if (x == n - 1 && y == n - 1) {
			result = Math.max(result, cnt);
			return;
		}
		for (int d = 0; d < moves.length; d++) {
			int nx = moves[d][0] + x;
			int ny = moves[d][1] + y;
			if (nx >= n || ny >= n) continue;
			if (!visited[nx][ny] && arr[nx][ny] == (prevDrink + 1) % 3) {
				visited[nx][ny] = true;
				dfs(arr, n, nx, ny, cnt + 1, arr[nx][ny]); // 마시는 경우
				visited[nx][ny] = false;
			}
			dfs(arr, n, nx, ny, cnt, prevDrink); // 마시지 않는 경우
		}
	}
}
