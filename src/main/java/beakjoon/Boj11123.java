package beakjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj11123 {
	static boolean[][] visited;
	static int N, M;
	static char[][] arr;
	static int[][] move = new int[][] {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int T = Integer.parseInt(br.readLine());
		while (T-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			visited = new boolean[N][M];
			Queue<int[]> q = new ArrayDeque<>();
			arr = new char[N][M];
			for (int i = 0; i < N; i++) {
				char[] line = br.readLine().toCharArray();
				arr[i] = line;
				for (int j = 0; j < M; j++) {
					if (line[j] == '#') {
						q.offer(new int[] {i, j});
					}
				}
			}
			int result = 0;
			while (!q.isEmpty()) {
				int[] curr = q.poll();
				if (!visited[curr[0]][curr[1]]) {
					bfs(curr);
					result++;
				}
			}
			bw.write(result + "\n");
		}
		bw.flush();
		bw.close();
	}

	private static void bfs(int[] node) {
		Queue<int[]> q = new ArrayDeque<>();
		visited[node[0]][node[1]] = true;
		q.offer(node);
		while (!q.isEmpty()) {
			int[] curr = q.poll();
			for (int d = 0; d < 4; d++) {
				int nx = curr[0] + move[d][0];
				int ny = curr[1] + move[d][1];
				if (nx < 0 || ny < 0 || nx >= N || ny >= M || arr[nx][ny] == '.') continue;
				if (!visited[nx][ny]) {
					visited[nx][ny] = true;
					q.offer(new int[] {nx, ny});
				}
			}
		}
	}
}
