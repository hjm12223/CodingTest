package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj14502 {
	static List<int[]> viruses = new ArrayList<>();
	static int N, M;
	static int[][] arr;
	static boolean[][] visited;
	static int[][] move = new int[][] {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
	static int result = 0;
	static int wallCnt = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		visited = new boolean[N][M];
		arr = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				int value = Integer.parseInt(st.nextToken());
				arr[i][j] = value;
				if (value == 2) {
					viruses.add(new int[] {i, j});
				} else if (value == 1) {
					wallCnt++;
				}
			}
		}

		dfs(0);
		System.out.println(result);
	}

	private static void dfs(int wall) {
		if (wall == 3) {
			result = Math.max(bfs(), result);
			return;
		}
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (arr[i][j] == 0) {
					arr[i][j] = 1;
					dfs(wall + 1);
					arr[i][j] = 0;
				}
			}
		}
	}

	private static int bfs() {
		boolean[][] visited = new boolean[N][M];
		Queue<int[]> q = new ArrayDeque<>();

		for (int[] node : viruses) {
			q.offer(node);
			visited[node[0]][node[1]] = true;
		}
		int virusCnt = viruses.size();
		while (!q.isEmpty()) {
			int[] curr = q.poll();
			for (int d = 0; d < 4; d++) {
				int nx = move[d][0] + curr[0];
				int ny = move[d][1] + curr[1];
				if (nx < 0 || ny < 0 || nx >= N || ny >= M || arr[nx][ny] == 1) continue;
				if (!visited[nx][ny]) {
					visited[nx][ny] = true;
					virusCnt++;
					q.offer(new int[] {nx, ny});
				}
			}
		}
		return N * M - (virusCnt + wallCnt + 3);
	}
}
