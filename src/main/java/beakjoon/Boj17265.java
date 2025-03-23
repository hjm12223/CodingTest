package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Boj17265 {
	static char[][] arr;
	static int N;
	static int minValue = Integer.MAX_VALUE;
	static int maxValue = 0;
	static int[][] move = new int[][] {{0, 1}, {1, 0}};
	static boolean[][] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr = new char[N][N];
		visited = new boolean[N][N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				arr[i][j] = st.nextToken().charAt(0);
			}
		}
		List<Character> comb = new ArrayList<>();
		comb.add(arr[0][0]);
		dfs(comb, 0, 0);
		System.out.println(maxValue + " " + minValue);
	}

	private static void dfs(List<Character> comb, int x, int y) {
		if (x == N - 1 && y == N - 1) {
			cal(comb);
			return;
		}

		for (int d = 0; d < 2; d++) {
			int nx = x + move[d][0];
			int ny = y + move[d][1];
			if (nx < 0 || ny < 0 || nx >= N || ny >= N) continue;
			if (!visited[nx][ny]) {
				visited[nx][ny] = true;
				comb.add(arr[nx][ny]);
				dfs(comb, nx, ny);
				comb.remove(comb.size() - 1);
				visited[nx][ny] = false;
			}
		}
	}

	private static void cal(List<Character> comb) {
		int sum = Integer.parseInt(String.valueOf(comb.get(0)));

		for (int i = 1; i < comb.size(); i += 2) {
			Character c = comb.get(i);
			if (c == '+') {
				sum += Integer.parseInt(String.valueOf(comb.get(i + 1)));
			} else if (c == '*') {
				sum *= Integer.parseInt(String.valueOf(comb.get(i + 1)));
			} else {
				sum -= Integer.parseInt(String.valueOf(comb.get(i + 1)));
			}
		}
		minValue = Math.min(sum, minValue);
		maxValue = Math.max(sum, maxValue);
	}
}
