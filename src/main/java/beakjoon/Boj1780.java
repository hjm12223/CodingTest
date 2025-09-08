package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj1780 {
	static int N;
	static int[][] arr;

	static int minus, zero, one = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		arr = new int[N][N];
		for (int i = 0; i < N; i++) {
			arr[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		}
		dfs(N, 0, 0);
		System.out.println(minus);
		System.out.println(zero);
		System.out.println(one);
	}

	private static void dfs(int n, int sx, int sy) {
		if (check(sx, sy, n)) {
			int value = arr[sx][sy];
			if (value == -1) minus++;
			else if (value == 0) zero++;
			else one++;
			return;
		}
		int newSize = n / 3;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				dfs(newSize, sx + i * newSize, sy + j * newSize);
			}
		}

	}

	private static boolean check(int x, int y, int size) {
		int value = arr[x][y];
		for (int i = x; i < x + size; i++) {
			for (int j = y; j < y + size; j++) {
				if (arr[i][j] != value) return false;
			}
		}
		return true;
	}
}
