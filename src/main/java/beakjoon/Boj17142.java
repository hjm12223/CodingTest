package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj17142 {
	static int N, R;
	static boolean[] combVisited;
	static int INF = 987654321;
	static int[][] moves = new int[][] {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		int[][] arr = new int[N][N];
		List<int[]> virus = new ArrayList<>();
		int blankCnt = 0;
		for (int i = 0; i < N; i++) {
			int[] array = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			for (int j = 0; j < N; j++) {
				int value = array[j];
				if (value == 2) {
					arr[i][j] = value;
					virus.add(new int[] {i, j});
				} else if (value == 1) {
					arr[i][j] = INF;
				} else {
					blankCnt++;
					arr[i][j] = value;
				}
			}
		}

		if (blankCnt == 0) {
			System.out.println(0);
			return;
		}
		combVisited = new boolean[virus.size()];
		List<List<int[]>> comb = new ArrayList<>();
		makeComb(comb, virus, new ArrayList<>(), 0);
		List<Integer> results = new ArrayList<>();
		for (List<int[]> com : comb) {
			results.add(bfs(com, arr, blankCnt));
		}
		Collections.sort(results);
		int result = Integer.MAX_VALUE;
		for (Integer i : results) {
			if (i == -1) continue;
			result = Math.min(i, result);
		}
		System.out.println(result == Integer.MAX_VALUE ? -1 : result);
	}

	private static int bfs(List<int[]> com, int[][] arr, int blankCnt) {
		int maxValue = 0;
		int spreadCnt = 0;

		Queue<int[]> q = new ArrayDeque<>();
		boolean[][] visited = new boolean[N][N];

		for (int[] location : com) {
			int x = location[0];
			int y = location[1];
			visited[x][y] = true;
			q.offer(new int[] {x, y, 0});
		}
		while (!q.isEmpty()) {
			int[] curr = q.poll();
			for (int[] move : moves) {
				int nx = move[0] + curr[0];
				int ny = move[1] + curr[1];
				if (nx < 0 || ny < 0 || nx >= N || ny >= N || arr[nx][ny] == INF) continue;
				if (!visited[nx][ny]) {
					visited[nx][ny] = true;
					if (arr[nx][ny] == 0) {
						spreadCnt++;
						maxValue = curr[2] + 1;
					}
					q.offer(new int[] {nx, ny, curr[2] + 1});
				}
			}
		}
		if (spreadCnt != blankCnt) return -1;
		return maxValue;
	}

	private static void makeComb(List<List<int[]>> comb, List<int[]> virus, List<int[]> v, int depth) {
		if (v.size() == R) {
			comb.add(new ArrayList<>(v));
			return;
		}
		for (int i = depth; i < virus.size(); i++) {
			if (!combVisited[i]) {
				combVisited[i] = true;
				v.add(virus.get(i));
				makeComb(comb, virus, v, i + 1);
				v.remove(v.size() - 1);
				combVisited[i] = false;
			}
		}
	}
}
