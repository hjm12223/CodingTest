package programmers.kakao.Level3;

import java.util.LinkedList;
import java.util.Queue;

public class Block {
	static boolean[][][][] isVisited;
	static int[] dx = new int[] {1, 0, 0, -1};
	static int[] dy = new int[] {0, 1, -1, 0};
	static int N;
	static int[][] arr;

	public static void main(String[] args) {
		int solution = solution(
			new int[][] {{0, 0, 0, 1, 1}, {0, 0, 0, 1, 0}, {0, 1, 0, 1, 1}, {1, 1, 0, 0, 1}, {0, 0, 0, 0, 0}});
		System.out.println(solution);
	}

	public static int solution(int[][] board) {
		N = board.length;
		arr = board;
		isVisited = new boolean[N][N][N][N];
		return bfs();
	}

	private static int bfs() {
		Queue<Robot> q = new LinkedList<>();
		q.offer(new Robot(0, 0, 0, 1, 0));
		isVisited[0][0][0][1] = true;

		while (!q.isEmpty()) {
			Robot curr = q.poll();
	
			if (isTarget(curr)) return curr.dist;

			for (int d = 0; d < 4; d++) {
				int nx1 = curr.x1 + dx[d];
				int ny1 = curr.y1 + dy[d];
				int nx2 = curr.x2 + dx[d];
				int ny2 = curr.y2 + dy[d];

				if (isValid(nx1, ny1) && isValid(nx2, ny2) && !isVisited[nx1][ny1][nx2][ny2]) {
					isVisited[nx1][ny1][nx2][ny2] = true;
					q.offer(new Robot(nx1, ny1, nx2, ny2, curr.dist + 1));
				}
			}

			rotate(curr, q);
		}

		return -1;
	}

	private static void rotate(Robot robot, Queue<Robot> q) {
		if (robot.x1 == robot.x2) {
			for (int d : new int[] {1, -1}) {
				if (isValid(robot.x1 + d, robot.y1) && isValid(robot.x2 + d, robot.y2)) {
					if (!isVisited[robot.x1][robot.y1][robot.x1 + d][robot.y1]) {
						q.offer(new Robot(robot.x1, robot.y1, robot.x1 + d, robot.y1, robot.dist + 1));
						isVisited[robot.x1][robot.y1][robot.x1 + d][robot.y1] = true;
					}
					if (!isVisited[robot.x2][robot.y2][robot.x2 + d][robot.y2]) {
						q.offer(new Robot(robot.x2, robot.y2, robot.x2 + d, robot.y2, robot.dist + 1));
						isVisited[robot.x2][robot.y2][robot.x2 + d][robot.y2] = true;
					}
				}
			}
		}
		if (robot.y1 == robot.y2) {
			for (int d : new int[] {1, -1}) {
				if (isValid(robot.x1, robot.y1 + d) && isValid(robot.x2, robot.y2 + d)) {
					if (!isVisited[robot.x1][robot.y1][robot.x1][robot.y1 + d]) {
						q.offer(new Robot(robot.x1, robot.y1, robot.x1, robot.y1 + d, robot.dist + 1));
						isVisited[robot.x1][robot.y1][robot.x1][robot.y1 + d] = true;
					}
					if (!isVisited[robot.x2][robot.y2][robot.x2][robot.y2 + d]) {
						q.offer(new Robot(robot.x2, robot.y2, robot.x2, robot.y2 + d, robot.dist + 1));
						isVisited[robot.x2][robot.y2][robot.x2][robot.y2 + d] = true;
					}
				}
			}
		}
	}

	private static boolean isTarget(Robot curr) {
		return (curr.x1 == N - 1 && curr.y1 == N - 1) || (curr.x2 == N - 1 && curr.y2 == N - 1);
	}

	private static boolean isValid(int x, int y) {
		return x >= 0 && y >= 0 && x < N && y < N && arr[x][y] == 0;
	}

	private static class Robot {
		int x1, y1, x2, y2, dist;

		public Robot(int x1, int y1, int x2, int y2, int dist) {
			this.x1 = x1;
			this.y1 = y1;
			this.x2 = x2;
			this.y2 = y2;
			this.dist = dist;
		}
	}
}
