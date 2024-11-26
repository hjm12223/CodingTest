package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj17070 {
	static int[][][] directions = new int[][][] {
		{{0, 1}, {1, 1}}, // 가로 방향(0)에서 가능한 이동
		{{0, 1}, {1, 1}, {1, 0}}, // 대각선 방향(1)에서 가능한 이동
		{{1, 1}, {1, 0}} // 세로 방향(2)에서 가능한 이동
	};
	static int N;
	static int result = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		int[][] arr = new int[N][N];
		for (int i = 0; i < N; i++) {
			int[] temp = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			for (int j = 0; j < N; j++) {
				arr[i][j] = temp[j];
			}
		}
		dfs(0, 1, 0, arr);
		System.out.println(result);
	}

	private static void dfs(int x, int y, int dircetion, int[][] arr) {
		if (x == N - 1 && y == N - 1) {
			result++;
			return;
		}
		for (int i = 0; i < directions[dircetion].length; i++) {
			int nextX = directions[dircetion][i][0] + x;
			int nextY = directions[dircetion][i][1] + y;
			int nextDir;

			if (directions[dircetion][i][0] == 0) {
				nextDir = 0;
			} else if (directions[dircetion][i][1] == 0) {
				nextDir = 2;
			} else nextDir = 1;

			if (nextX >= N || nextY >= N) continue;
			if (!checkWall(nextX, nextY, nextDir, arr)) continue;

			dfs(nextX, nextY, nextDir, arr);
		}
	}

	private static boolean checkWall(int nextX, int nextY, int nextDir, int[][] arr) {
		if (arr[nextX][nextY] == 1) return false;
		if (nextDir == 2) {
			return arr[nextX][nextY - 1] != 1 && arr[nextX - 1][nextY] != 1;
		}
		return true;
	}
}