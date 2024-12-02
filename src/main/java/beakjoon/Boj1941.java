package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;

public class Boj1941 {
	static char[][] arr = new char[5][5];
	static int result = 0;
	static int[] dx = new int[] {1, -1, 0, 0};
	static int[] dy = new int[] {0, 0, 1, -1};
	static boolean[] visited = new boolean[25];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for (int i = 0; i < 5; i++) {
			arr[i] = br.readLine().toCharArray();
		}
		dfs(0, 0, 0);
		System.out.println(result);
	}

	private static void dfs(int depth, int index, int dasomCnt) {
		if (depth == 7) {
			if (dasomCnt >= 4 && isConnected()) {
				result++;
			}
			return;
		}
		for (int i = index; i < 25; i++) {
			if (!visited[i]) {
				visited[i] = true;
				dfs(depth + 1, i, dasomCnt + (Objects.equals(arr[i / 5][i % 5], 'S') ? 1 : 0));
				visited[i] = false;
			}
		}
	}

	private static boolean isConnected() {
		Queue<Integer> q = new LinkedList<>();
		boolean[] connectedVisited = new boolean[25];
		int cnt = 1;
		for (int i = 0; i < 25; i++) {
			if (visited[i]) {
				q.offer(i);
				connectedVisited[i] = true;
				break;
			}
		}
		while (!q.isEmpty()) {
			Integer curr = q.poll();
			int x = curr / 5;
			int y = curr % 5;
			for (int d = 0; d < 4; d++) {
				int nx = dx[d] + x;
				int ny = dy[d] + y;
				int next = nx * 5 + ny;
				if (nx >= 0 && ny >= 0 && nx < 5 && ny < 5 && visited[next] && !connectedVisited[next]) {
					connectedVisited[next] = true;
					q.offer(next);
					cnt++;
				}
			}
		}
		return cnt == 7;
	}

}