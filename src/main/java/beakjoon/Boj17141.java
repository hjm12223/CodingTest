package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj17141 {
	static int[][] move = new int[][] {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
	static int[][] arr;
	static int N, K;
	static boolean[][] visited;
	static boolean[] vis;
	static int result = Integer.MAX_VALUE;
	static List<int[]> viruses = new ArrayList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken()); // 격자의 크기
		K = Integer.parseInt(st.nextToken()); // 바이러스를 몇개를 터트릴지에 대한 변수

		arr = new int[N][N];

		viruses = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			int[] line = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			for (int j = 0; j < N; j++) {
				arr[i] = line;
				if (line[j] > 1) {
					viruses.add(new int[] {i, j, line[j], 0});
					arr[i][j] = 0;
				}
			}
		}
		vis = new boolean[viruses.size()];
		dfs(new ArrayList<>(), 0);
		System.out.println(result == Integer.MAX_VALUE ? -1 : result);
	}

	private static void dfs(List<int[]> virus, int depth) {
		if (virus.size() == K) {
			Queue<int[]> q = new ArrayDeque<>();
			visited = new boolean[N][N];
			int[][] dist = new int[N][N];
			for (int i = 0; i < dist.length; i++) {
				for (int j = 0; j < dist[i].length; j++) {
					if (arr[i][j] == 1) {
						dist[i][j] = 99999;
					} else {
						dist[i][j] = Integer.MAX_VALUE;
					}
				}
			}
			for (int[] curr : virus) {
				q.offer(curr);
				visited[curr[0]][curr[1]] = true;
				dist[curr[0]][curr[1]] = 0;
			}
			bfs(q, dist);
			return;
		}
		for (int i = depth; i < viruses.size(); i++) {
			if (!vis[i]) {
				vis[i] = true;
				virus.add(viruses.get(i));
				dfs(virus, i + 1);
				virus.remove(virus.size() - 1);
				vis[i] = false;
			}
		}
	}

	private static void bfs(Queue<int[]> q, int[][] dist) {
		int maxValue = 0;
		while (!q.isEmpty()) {
			int[] curr = q.poll();
			maxValue = Math.max(curr[3], maxValue);
			for (int d = 0; d < 4; d++) {
				int nx = move[d][0] + curr[0];
				int ny = move[d][1] + curr[1];
				if (nx < 0 || ny < 0 || nx >= N || ny >= N || arr[nx][ny] != 0) continue;
				if (!visited[nx][ny] && dist[curr[0]][curr[1]] < dist[nx][ny]) {
					visited[nx][ny] = true;
					dist[nx][ny] = curr[3] + 1;
					q.offer(new int[] {nx, ny, curr[2], curr[3] + 1});
				}
			}
		}
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (dist[i][j] == Integer.MAX_VALUE) {
					return;
				}
			}
		}
		result = Math.min(result, maxValue);
	}
}
