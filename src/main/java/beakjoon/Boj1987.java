package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj1987 {
	static int R, C;
	static char[][] board;
	static boolean[] visited = new boolean[26];
	static int[] dx = {1, 0, 0, -1};
	static int[] dy = {0, 1, -1, 0};
	static int maxPath = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		String s = "abcde";
		System.out.println(s.hashCode());

		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		board = new char[R][C];

		for (int i = 0; i < R; i++) {
			String line = br.readLine();
			for (int j = 0; j < C; j++) {
				board[i][j] = line.charAt(j);
			}
		}
		visited[board[0][0] - 'A'] = true;
		maxPath += 1;
		dfs(0, 0, 1);
		System.out.println(maxPath);
	}

	private static void dfs(int row, int col, int dist) {
		maxPath = Math.max(maxPath, dist);
		for (int i = 0; i < 4; i++) {
			int nextRow = row + dx[i];
			int nextCol = col + dy[i];
			if (nextCol >= C || nextRow >= R || nextCol < 0 || nextRow < 0) continue;
			if (!visited[board[nextRow][nextCol] - 'A']) {
				visited[board[nextRow][nextCol] - 'A'] = true;
				dfs(nextRow, nextCol, dist + 1);
				visited[board[nextRow][nextCol] - 'A'] = false;
			}
		}
	}
}