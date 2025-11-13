package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj3184_2 {
	static int[][] moves = new int[][] {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
	static boolean[][] visited;
	static int N, M;
	static char[][] arr;
	static int resultWolf = 0;
	static int resultSheep = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		arr = new char[N][M];
		visited = new boolean[N][M];
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < M; j++) {
				arr[i][j] = str.charAt(j);
			}
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (!visited[i][j]) {
					bfs(i, j);
				}
			}
		}
		System.out.println(resultSheep + " " + resultWolf);
	}

	private static void bfs(int x, int y) {
		int wolfCnt = 0;
		int sheepCnt = 0;
		Queue<int[]> q = new ArrayDeque<>();
		q.offer(new int[] {x, y});
		visited[x][y] = true;
		if (arr[x][y] == 'o') sheepCnt++;
		else if (arr[x][y] == 'v') wolfCnt++;
		while (!q.isEmpty()) {
			int[] curr = q.poll();
			for (int[] move : moves) {
				int nx = curr[0] + move[0];
				int ny = curr[1] + move[1];
				if (nx < 0 || ny < 0 || nx >= N || ny >= M || arr[nx][ny] == '#') continue;
				if (!visited[nx][ny]) {
					q.offer(new int[] {nx, ny});
					visited[nx][ny] = true;
					if (arr[nx][ny] == 'o') sheepCnt++;
					else if (arr[nx][ny] == 'v') wolfCnt++;
				}
			}
		}
		if (sheepCnt > wolfCnt) resultSheep += sheepCnt;
		else resultWolf += wolfCnt;
	}
}
