package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj1303 {
	static int[] dx = new int[] {1, 0, 0, -1};
	static int[] dy = new int[] {0, 1, -1, 0};
	static int N, M;
	static boolean[][] isVisited;
	static char[][] arr;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		isVisited = new boolean[N][M];
		arr = new char[N][M];
		for (int i = 0; i < N; i++) {
			arr[i] = br.readLine().toCharArray();
		}
		int wResult = 0;
		int bResult = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (arr[i][j] == 'W') {
					wResult += bfs(i, j, arr[i][j]);
				} else if (arr[i][j] == 'B') {
					bResult += bfs(i, j, arr[i][j]);
				}
			}
		}
		System.out.println(wResult + " " + bResult);
	}

	private static int bfs(int row, int col, char c) {
		int cnt = 0;
		if (isVisited[row][col]) return 0;
		isVisited[row][col] = true;
		Queue<Node> q = new LinkedList<>();
		q.offer(new Node(row, col));
		while (!q.isEmpty()) {
			Node curr = q.poll();
			cnt++;
			int x = curr.x;
			int y = curr.y;
			for (int d = 0; d < 4; d++) {
				int nx = x + dx[d];
				int ny = y + dy[d];
				if (nx < 0 || ny < 0 || nx >= N || ny >= M || isVisited[nx][ny] || arr[nx][ny] != c) continue;
				q.offer(new Node(nx, ny));
				isVisited[nx][ny] = true;
			}
		}
		return cnt * cnt;
	}

	private static class Node {
		int x;
		int y;

		public Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}
