package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
BFS
 */
public class Boj21736 {
	static int[] dx = new int[] {1, 0, -1, 0};
	static int[] dy = new int[] {0, 1, 0, -1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int result = 0;
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		char[][] arr = new char[N][M];
		Queue<Node> q = new LinkedList<>();
		boolean[][] isVisited = new boolean[N][M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			String toke = st.nextToken();
			for (int j = 0; j < M; j++) {
				char c = toke.charAt(j);
				if (c == 'I') {
					q.offer(new Node(i, j));
					isVisited[i][j] = true;
				}
				arr[i][j] = c;
			}
		}

		while (!q.isEmpty()) {
			Node curr = q.poll();
			for (int i = 0; i < 4; i++) {
				int nextX = dx[i] + curr.x;
				int nextY = dy[i] + curr.y;
				if (nextX < 0 || nextY < 0 || nextX >= N || nextY >= M || arr[nextX][nextY] == 'X') continue;
				if (!isVisited[nextX][nextY]) {
					if (arr[nextX][nextY] == 'P') result++;
					isVisited[nextX][nextY] = true;
					q.offer(new Node(nextX, nextY));
				}
			}
		}
		if (result == 0) {
			System.out.println("TT");
		} else {
			System.out.println(result);
		}
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
