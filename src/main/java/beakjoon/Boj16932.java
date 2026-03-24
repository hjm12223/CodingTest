package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class Boj16932 {
	static int[][] arr;
	static boolean[][] visited;
	static int[][] group;
	static Map<Integer, Integer> groupSize = new HashMap<>();
	static int groupId = 1;
	static int N, M;
	static int[][] moves = new int[][] {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[N][M];
		for (int i = 0; i < N; i++) {
			arr[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		}
		visited = new boolean[N][M];
		group = new int[N][M];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (!visited[i][j] && arr[i][j] == 1) {
					visited[i][j] = true;
					bfs(i, j);
					groupId++;
				}
			}
		}
		int result = 0;
		visited = new boolean[N][M];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (arr[i][j] == 0 && !visited[i][j]) {
					visited[i][j] = true;
					int size = 1;
					Set<Integer> check = new HashSet<>();
					for (int[] move : moves) {
						int nx = i + move[0];
						int ny = j + move[1];
						if (nx < 0 || ny < 0 || nx >= N || ny >= M || visited[nx][ny] || group[nx][ny] == 0) continue;
						int nextGroupId = group[nx][ny];
						if (!check.contains(nextGroupId)) {
							check.add(nextGroupId);
							size += groupSize.get(nextGroupId);
						}
					}
					result = Math.max(result, size);
				}
			}
		}
		System.out.println(result);
	}

	/*
	상하좌우를 기반으로 + 을 했을때 해당 방향이 다른방향과 이어져 있는걸 어떻게 판단?
	groupId를 기반으로 HashMap<그룹이름,해당 그룹의 크기>
	그럼 groupId 는?
	groupId matrix 를 만들어서 bfs 돌 시 헤당 groupId를 넣어줌
	 */
	private static void bfs(int x, int y) {
		Queue<int[]> q = new ArrayDeque<>();
		q.offer(new int[] {x, y});
		int cnt = 0;
		List<int[]> locations = new ArrayList<>();
		locations.add(new int[] {x, y});
		group[x][y] = groupId;
		while (!q.isEmpty()) {
			int[] curr = q.poll();
			cnt++;
			for (int[] move : moves) {
				int nx = curr[0] + move[0];
				int ny = curr[1] + move[1];
				if (nx < 0 || ny < 0 || nx >= N || ny >= M || arr[nx][ny] != 1) continue;
				if (!visited[nx][ny]) {
					visited[nx][ny] = true;
					q.offer(new int[] {nx, ny});
					locations.add(new int[] {nx, ny});
					group[nx][ny] = groupId;
				}
			}
		}
		groupSize.put(groupId, cnt);
	}
}
