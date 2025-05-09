package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj22944 {
	static int sx, sy, N, H, D;
	static char[][] arr;
	static int[][] move = new int[][] {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
	static int result = Integer.MAX_VALUE;
	static boolean[][] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken()); // 현재 체력
		D = Integer.parseInt(st.nextToken()); // 우산의 내구도

		arr = new char[N][N];

		for (int i = 0; i < N; i++) {
			String line = br.readLine();
			for (int j = 0; j < N; j++) {
				char c = line.charAt(j);
				if (c == 'S') {
					sx = i;
					sy = j;
				}
				arr[i][j] = c;
			}
		}
		visited = new boolean[N][N];
		visited[sx][sy] = true;
		back(sx, sy, H, 0);
		System.out.println(result);
	}

	private static void back(int x, int y, int h, int umbrella) {
		if (h == 0)
			return;

		if (arr[x][y] == 'E') {
			result = Math.min(result, h);
			return;
		}

		if (arr[x][y] == 'U')
			umbrella = D;
		
		for (int[] move : move) {
			int nx = x + move[0];
			int ny = y + move[1];
			if (nx >= N || ny >= N || nx < 0 || ny < 0) continue;
			if (!visited[nx][ny]) {
				visited[nx][ny] = true;
				if (umbrella == 0) {
					back(nx, ny, h - 1, umbrella);
				} else {
					back(nx, ny, h, umbrella - 1);
				}
				visited[nx][ny] = false;
			}
		}
	}
}
