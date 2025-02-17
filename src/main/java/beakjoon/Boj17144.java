package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj17144 {
	static int airX, airY, N, M;
	static int[][] arr;
	static int[][] move = new int[][] {{0, 1}, {1, 0}, {-1, 0}, {0, -1}};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		int T = Integer.parseInt(st.nextToken());
		arr = new int[N][M];
		boolean isFound = false;
		Queue<int[]> q = new ArrayDeque<>();
		for (int i = 0; i < N; i++) {
			int[] line = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			arr[i] = line;
			for (int j = 0; j < M; j++) {
				if (line[j] != -1 && line[j] > 0) q.offer(new int[] {i, j});
				if (!isFound && line[j] == -1) {
					airX = i;
					airY = j;
				}
			}
		}
		while (T-- > 0) {
			bfs(arr, q);
			moveUp(arr);
			moveDown(arr);
			push(q);
		}
		int result = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (arr[i][j] > 0) result += arr[i][j];
			}
		}
		System.out.println(result);
	}

	private static void push(Queue<int[]> q) {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (arr[i][j] > 0) q.offer(new int[] {i, j});
			}
		}
	}

	private static void moveUp(int[][] arr) {
		for (int i = airX - 1; i > 0; i--) { // 상 -> 하
			arr[i][0] = arr[i - 1][0];
		}

		for (int i = 0; i < M - 1; i++) { // 우 -> 좌
			arr[0][i] = arr[0][i + 1];
		}

		for (int i = 0; i < airX; i++) { // 하 -> 상
			arr[i][M - 1] = arr[i + 1][M - 1];
		}
		for (int i = M - 1; i > 1; i--) {
			arr[airX][i] = arr[airX][i - 1];
		}
		arr[airX][1] = 0;
	}

	private static void moveDown(int[][] arr) {
		for (int i = airX + 1; i < N - 1; i++) {
			arr[i][0] = arr[i + 1][0];
		}

		arr[N - 1][0] = -1;

		for (int i = 0; i < M - 1; i++) {
			arr[N - 1][i] = arr[N - 1][i + 1];
		}

		for (int i = N - 1; i > airX; i--) {
			arr[i][M - 1] = arr[i - 1][M - 1];
		}

		for (int i = M - 1; i > 1; i--) {
			arr[airX][i] = arr[airX][i - 1];
		}
		arr[airX][1] = 0;
	}

	private static void bfs(int[][] arr, Queue<int[]> q) {
		while (!q.isEmpty()) {
			int[] curr = q.poll();
			int spreadCnt = 0;
			for (int d = 0; d < 4; d++) {
				int nx = move[d][0] + curr[0];
				int ny = move[d][1] + curr[1];
				if (isValid(nx, ny)) {
					arr[nx][ny] += arr[curr[0]][curr[1]] / 5;
					spreadCnt++;
				}
			}
			arr[curr[0]][curr[1]] -= (arr[curr[0]][curr[1]] / 5) * spreadCnt;
		}
	}

	private static boolean isValid(int nx, int ny) {
		return nx >= 0 && ny >= 0 && nx < N && ny < M && arr[nx][ny] != -1;
	}
}
