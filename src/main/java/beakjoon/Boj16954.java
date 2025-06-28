package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class Boj16954 {
	static final int N = 8;
	static char[][] arr;
	static int[][] moves = new int[][] {{0, 1}, {0, -1}, {-1, 0}, {1, 1}, {-1, 1}, {-1, -1}, {1, -1}, {0, 0}, {1, 0}};
	static boolean[][][] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		arr = new char[N][N];
		Queue<int[]> objects = new ArrayDeque<>();
		visited = new boolean[20][N][N];

		for (int i = 0; i < N; i++) {
			char[] chars = br.readLine().toCharArray();
			for (int j = 0; j < N; j++) {
				arr[i][j] = chars[j];
			}
		}
		objects.offer(new int[] {7, 0, 0});
		int result = bfs(objects);
		System.out.println(result);
	}

	private static int bfs(Queue<int[]> q) {
		int cnt = 0;
		while (!q.isEmpty()) {
			int size = q.size();
			for (int i = 0; i < size; i++) {
				int[] curr = q.poll();
				if (arr[curr[0]][curr[1]] == '#') continue;
				for (int[] move : moves) {
					int nx = move[0] + curr[0];
					int ny = move[1] + curr[1];
					if (nx < 0 || ny < 0 || nx >= N || ny >= N || arr[nx][ny] == '#') continue;
					if (nx == 0 && ny == 7) return 1;
					if (!visited[curr[2]][nx][ny]) {
						q.offer(new int[] {nx, ny, curr[2] + 1});
						visited[curr[2]][nx][ny] = true;
					}
				}
			}
			breaksDown(q.size(), cnt);
			cnt++;
		}
		return 0;
	}

	private static void breaksDown(int size, int cnt) {
		int verti = 0;
		for (int i = N - 1; i >= 0; i--) {
			for (int j = 0; j < N; j++) {
				if (arr[i][j] == '#') {
					arr[i][j] = '.';
					if (i != N - 1) {
						arr[i + 1][j] = '#';
						if (visited[cnt][i + 1][j]) {
							verti++;
						}
					}
				}
			}
		}
		if (size == verti) {
			System.out.println(0);
			System.exit(0);
		}
	}
}