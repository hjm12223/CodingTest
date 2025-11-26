package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj17141_2 {
	static int[][] arr;
	static List<int[]> viruses = new ArrayList<>();
	static int N, M;
	static boolean[] isVisited;
	static int[][] moves = new int[][] {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
	static int initEmptyCount = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[N][N];
		viruses = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				int value = Integer.parseInt(st.nextToken());
				arr[i][j] = value;
				if (value == 2) {
					viruses.add(new int[] {i, j});
					initEmptyCount++;
				} else if (value == 0) {
					initEmptyCount++;
				}
			}
		}
		List<List<int[]>> comb = new ArrayList<>();
		isVisited = new boolean[viruses.size()];
		dfs(comb, new ArrayList<>(), 0);
		int result = Integer.MAX_VALUE;
		for (List<int[]> virus : comb) {
			int value = bfs(virus, initEmptyCount - M);
			if (value != -1) {
				result = Math.min(result, value);
			}
		}
		System.out.println(result == Integer.MAX_VALUE ? -1 : result);
	}

	private static int bfs(List<int[]> virus, int emptyCount) {
		Queue<int[]> q = new ArrayDeque<>();

		boolean[][] visited = new boolean[N][N];

		for (int[] value : virus) {
			int cx = value[0];
			int cy = value[1];
			q.offer(new int[] {cx, cy, 0});
			visited[cx][cy] = true;
		}
		int result = 0;
		while (!q.isEmpty()) {
			int[] curr = q.poll();
			int cx = curr[0];
			int cy = curr[1];
			int ct = curr[2];

			for (int[] move : moves) {
				int nx = cx + move[0];
				int ny = cy + move[1];
				if (nx < 0 || ny < 0 || nx >= N || ny >= N) continue;
				if (visited[nx][ny] || arr[nx][ny] == 1) continue;
				visited[nx][ny] = true;
				emptyCount--;
				result = ct + 1;
				q.offer(new int[] {nx, ny, ct + 1});
			}
		}
		if (emptyCount == 0) {
			return result;
		} else {
			return -1;
		}
	}

	private static void dfs(List<List<int[]>> comb, List<int[]> com, int depth) {
		if (com.size() == M) {
			comb.add(new ArrayList<>(com));
			return;
		}
		for (int i = depth; i < viruses.size(); i++) {
			if (!isVisited[i]) {
				isVisited[i] = true;
				com.add(viruses.get(i));
				dfs(comb, com, i + 1);
				com.remove(com.size() - 1);
				isVisited[i] = false;
			}
		}
	}
}
