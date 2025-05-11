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

public class Boj17472_1 {
	static int[][] islands;
	static boolean[][] visited;
	static int[][] moves = new int[][] {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
	static int islandCnt = 2;
	static int N, M;
	static List<int[]> bridges = new ArrayList<>();
	static int[] parent;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		islands = new int[N][M];
		visited = new boolean[N][M];
		for (int i = 0; i < N; i++) {
			islands[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		}
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (!visited[i][j] && islands[i][j] > 0) {
					visited[i][j] = true;
					islands[i][j] = islandCnt;
					bfs(i, j, islandCnt++);
				}
			}
		}
		parent = new int[islandCnt];
		for (int i = 1; i < islandCnt; i++) {
			parent[i] = i;
		}
		dfs();
		bridges.sort((o1, o2) -> o1[2] - o2[2]);
		int result = 0;
		for (int[] bridge : bridges) {
			int a = bridge[0];
			int b = bridge[1];
			if (find(a) != find(b)) {
				union(a, b);
				result += bridge[2];
			}
		}
		System.out.println(isConnected() ? result : -1);
	}

	private static boolean isConnected() {
		int root = find(2);
		for (int i = 3; i < parent.length; i++) {
			if (find(i) != root) return false;
		}
		return true;
	}

	static int find(int x) {
		if (parent[x] != x) {
			parent[x] = find(parent[x]);
		}
		return parent[x];
	}

	static void union(int a, int b) {
		int parent_a = find(a);
		int parent_b = find(b);
		if (parent_a != parent_b) {
			parent[parent_b] = parent_a;
		}
	}

	static void dfs() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (islands[i][j] > 1) {
					int islandIdx = islands[i][j];
					for (int[] move : moves) {
						int x = i;
						int y = j;
						int dist = 0;
						while (true) {
							x += move[0];
							y += move[1];
							if (!isValid(x, y) || islands[x][y] == islandIdx) break;
							if (islands[x][y] > 0) {
								if (dist >= 2) {
									bridges.add(new int[] {islandIdx, islands[x][y], dist});
								}
								break;
							}
							dist++;
						}
					}
				}
			}
		}
	}

	static void bfs(int x, int y, int cnt) {
		Queue<int[]> q = new ArrayDeque<>();
		q.offer(new int[] {x, y});
		while (!q.isEmpty()) {
			int[] curr = q.poll();
			for (int[] move : moves) {
				int nx = curr[0] + move[0];
				int ny = curr[1] + move[1];
				if (!isValid(nx, ny)) continue;
				if (!visited[nx][ny] && islands[nx][ny] != 0) {
					visited[nx][ny] = true;
					islands[nx][ny] = cnt;
					q.offer(new int[] {nx, ny, cnt});
				}
			}
		}
	}

	static boolean isValid(int x, int y) {
		return x >= 0 && y >= 0 && x < N && y < M;
	}
}
