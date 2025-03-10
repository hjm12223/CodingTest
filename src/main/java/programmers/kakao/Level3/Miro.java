package programmers.kakao.Level3;

import java.util.LinkedList;
import java.util.Queue;

public class Miro {
	static boolean[][][] visited;
	static int[] dx = {1, 0, 0, -1};
	static int[] dy = {0, -1, 1, 0};
	static char[] dir = {'d', 'l', 'r', 'u'};

	public static void main(String[] args) {
		String solution = solution(3, 4, 2, 3, 3, 1, 5);
		// String solution = solution(3, 3, 1, 2, 3, 3, 4);
		System.out.println(solution);
	}

	public static String solution(int n, int m, int x, int y, int r, int c, int k) {
		visited = new boolean[k + 1][n][m];
		Queue<Node> q = new LinkedList<>();
		q.offer(new Node(x - 1, y - 1, "", 0));
		while (!q.isEmpty()) {
			Node curr = q.poll();

			if (curr.x == r - 1 && curr.y == c - 1 && curr.moveCount == k) {
				return curr.history;
			}

			if (curr.moveCount >= k || visited[curr.moveCount][curr.x][curr.y]) continue;
			visited[curr.moveCount][curr.x][curr.y] = true;

			for (int i = 0; i < 4; i++) {
				int nx = curr.x + dx[i];
				int ny = curr.y + dy[i];
				int nextMoveCount = curr.moveCount + 1;

				// 유효한 범위 확인
				if (nx < 0 || ny < 0 || nx >= n || ny >= m) continue;
				q.offer(new Node(nx, ny, curr.history + dir[i], nextMoveCount));
			}
		}
		return "impossible";
	}

	private static class Node {
		int x, y;
		String history;
		int moveCount;

		public Node(int x, int y, String history, int moveCount) {
			this.x = x;
			this.y = y;
			this.history = history;
			this.moveCount = moveCount;
		}
	}
}
