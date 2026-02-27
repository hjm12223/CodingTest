package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Boj14620_2 {
	static int[][] arr;
	static int[][] moves = new int[][] {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
	static int N, result = Integer.MAX_VALUE;
	static boolean[][] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr = new int[N][N];
		visited = new boolean[N][N];
		for (int i = 0; i < N; i++) {
			arr[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		}
		dfs(0, 0);
		System.out.println(result);
	}

	static void dfs(int depth, int sum) {
		if (depth == 3) {
			result = Math.min(result, sum);
			return;
		}
		for (int i = 1; i < N - 1; i++) {
			for (int j = 1; j < N - 1; j++) {
				if (canPlant(i, j)) {
					int value = plant(i, j);
					dfs(depth + 1, sum + value);
					remove(i, j);
				}
			}
		}
	}

	private static void remove(int i, int j) {
		visited[i][j] = false;
		for (int[] move : moves) {
			int nx = i + move[0];
			int ny = j + move[1];
			visited[nx][ny] = false;
		}
	}

	private static int plant(int i, int j) {
		int sum = arr[i][j];
		visited[i][j] = true;
		for (int[] move : moves) {
			int nx = i + move[0];
			int ny = j + move[1];
			sum += arr[nx][ny];
			visited[nx][ny] = true;
		}
		return sum;
	}

	private static boolean canPlant(int i, int j) {
		if (visited[i][j]) return false;

		for (int[] move : moves) {
			int nx = i + move[0];
			int ny = j + move[1];
			if (visited[nx][ny]) return false;
		}
		return true;
	}
}