package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj16956 {
	static int N, M;
	static int[][] moves = new int[][] {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
	static boolean canInstall = true;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		char[][] arr = new char[N][M];
		for (int i = 0; i < N; i++) {
			arr[i] = br.readLine().toCharArray();
		}
		Queue<int[]> q = new ArrayDeque<>();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (arr[i][j] == 'S') q.offer(new int[] {i, j});
			}
		}

		while (!q.isEmpty()) {
			int[] curr = q.poll();
			for (int[] move : moves) {
				int nx = move[0] + curr[0];
				int ny = move[1] + curr[1];
				if (nx < 0 || ny < 0 || nx >= N || ny >= M || arr[nx][ny] == 'S') continue;
				if (arr[nx][ny] == 'W') {
					canInstall = false;
					break;
				}
				arr[nx][ny] = 'D';
			}
		}
		if (canInstall) {
			StringBuilder sb = new StringBuilder();
			for (char[] chars : arr) {
				for (char aChar : chars) {
					sb.append(aChar);
				}
				sb.append("\n");
			}
			System.out.println(1);
			System.out.println(sb);
		} else {
			System.out.println(0);
		}
	}
}
