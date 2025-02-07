package beakjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj2583 {
	static int[][] arr;
	static int N, M;
	static int[][] move = new int[][] {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
	static int cnt = 0;
	static List<Integer> list = new ArrayList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine());
		M = par(st.nextToken());
		N = par(st.nextToken());
		int K = par(st.nextToken());
		arr = new int[M][N];
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int sy = par(st.nextToken());
			int sx = par(st.nextToken());
			int ey = par(st.nextToken());
			int ex = par(st.nextToken());
			for (int x = sx; x < ex; x++) {
				for (int y = sy; y < ey; y++) {
					arr[x][y] = Integer.MAX_VALUE;
				}
			}
		}
		for (int i = 0; i < M; i++) {
			for (int j = 0; j < N; j++) {
				if (arr[i][j] != Integer.MAX_VALUE) bfs(i, j);
			}
		}
		Collections.sort(list);
		bw.write(String.valueOf(cnt));
		bw.newLine();
		for (int i = 0; i < list.size(); i++) {
			bw.write(list.get(i) + " ");
		}
		bw.flush();
		bw.close();

	}

	private static void bfs(int x, int y) {
		cnt++;
		Queue<int[]> q = new ArrayDeque<>();
		q.offer(new int[] {x, y});
		arr[x][y] = Integer.MAX_VALUE;
		int value = 1;
		while (!q.isEmpty()) {
			int[] curr = q.poll();
			for (int d = 0; d < 4; d++) {
				int nx = move[d][0] + curr[0];
				int ny = move[d][1] + curr[1];
				if (isValid(nx, ny)) {
					value++;
					arr[nx][ny] = Integer.MAX_VALUE;
					q.offer(new int[] {nx, ny});
				}
			}
		}
		list.add(value);
	}

	private static boolean isValid(int nx, int ny) {
		return nx >= 0 && ny >= 0 && nx < M && ny < N && arr[nx][ny] != Integer.MAX_VALUE;
	}

	private static int par(String st) {
		return Integer.parseInt(st);
	}
}