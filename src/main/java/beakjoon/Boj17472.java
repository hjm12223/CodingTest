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

public class Boj17472 {
	static int N, M;
	static int islandCnt = 2;
	static int[][] arr;
	static int[][] move = new int[][] {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
	static List<int[]> bridges = new ArrayList<>();
	static int[] parent;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[N][M];
		for (int i = 0; i < N; i++) {
			arr[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		}
		boolean[][] visited = new boolean[N][M];

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (arr[i][j] != 0 && !visited[i][j]) {
					bfs(i, j, visited, arr[i][j], islandCnt++); // 섬 마스킹 작업
				}
			}
		}
		/*
		1. 다리를 이어준다
		 1. 이을때 각 섬간의 다리 사이즈는 1을 초과해야한다
		 2. 각 섬들이 직선으로 연결 되어야한다 ㄱ ㄴ 과 같은 방법은 할 수 없다.
		 3. 각 다리들이 중복이 될 수 있다
		       3 3 3 3
		 1 1 1 		 B	2 2 2
		 1 1 1 A A A AB 2 2 2
		 	   4 4 4 4

		BFS 와 DFS 를 섞어서 각 섬에서 출발을 하되 다리를 이을때 DFS를  통해서 한방향으로만 이어준다
		N,M 은 최대 10 그럼 완탐가능 -> 백트래킹을 통해서 해당 조건을 활용해서 백트래킹
		1. 다리를 잇는다
			1.
		2. 다리가 다 이어져있느닞 확인한다
		if(checkBridge(arr)){
			result = Math.min(result, dist)
		}
		return
		 */
		dfs();
		parent = new int[islandCnt];
		for (int i = 0; i < islandCnt; i++) parent[i] = i;
		bridges.sort((a, b) -> Integer.compare(a[2], b[2])); // 다리 길이로 정렬
		System.out.println(kruskal());
	}

	private static int kruskal() {
		int totalLength = 0;
		int bridgeCount = 0;

		for (int[] bridge : bridges) {
			int a = bridge[0];
			int b = bridge[1];
			int length = bridge[2];

			if (find(a) != find(b)) { // 사이클을 형성하지 않는 경우
				union(a, b);
				totalLength += length;
				bridgeCount++;
			}
		}

		// 모든 섬이 연결되었는지 확인 (섬의 개수 - 2)개의 다리가 필요
		return isAllConnected() ? totalLength : -1;
	}

	private static boolean isAllConnected() {
		if (islandCnt <= 2) return true; // 섬이 없거나 하나뿐이면 이미 연결됨 (불가능한 케이스)

		int root = find(2); // 첫 번째 섬(번호 2)의 루트
		for (int i = 3; i < islandCnt; i++) {
			if (find(i) != root) return false; // 다른 섬이 연결되지 않은 경우
		}
		return true;
	}

	private static void union(int a, int b) {
		a = find(a);
		b = find(b);
		if (a != b) parent[b] = a;
	}

	private static int find(int x) {
		if (parent[x] != x) {
			parent[x] = find(parent[x]);
		}
		return parent[x];
	}

	private static void dfs() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (arr[i][j] > 1) {
					int islandIdx = arr[i][j];
					for (int d = 0; d < 4; d++) {
						int x = i;
						int y = j;
						int dist = 0;
						while (true) {
							x += move[d][0];
							y += move[d][1];

							if (!isValid(x, y) || arr[x][y] == islandIdx) break;
							if (arr[x][y] > 0) { // 다른 섬을 만난 경우
								if (dist >= 2) {
									bridges.add(new int[] {islandIdx, arr[x][y], dist});
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

	private static void bfs(int x, int y, boolean[][] visited, int value, int maskValue) {
		Queue<int[]> q = new ArrayDeque<>();
		q.offer(new int[] {x, y});
		arr[x][y] = maskValue;
		visited[x][y] = true;
		while (!q.isEmpty()) {
			int[] curr = q.poll();
			for (int d = 0; d < 4; d++) {
				int nx = move[d][0] + curr[0];
				int ny = move[d][1] + curr[1];
				if (!isValid(nx, ny)) continue;
				if (!visited[nx][ny] && arr[nx][ny] == value) {
					visited[nx][ny] = true;
					q.offer(new int[] {nx, ny});
					arr[nx][ny] = maskValue;
				}
			}
		}
	}

	public static boolean isValid(int nx, int ny) {
		return nx >= 0 && ny >= 0 && nx < N && ny < M;
	}
}
