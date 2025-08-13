package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj17265 {
	static int N;
	static char[][] arr;
	static boolean[][] visited;
	static int minValue = Integer.MAX_VALUE;
	static int maxValue = Integer.MIN_VALUE;
	static int[][] moves = new int[][] {{0, 1}, {1, 0}};

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
		visited[0][0] = true;
		StringBuilder sb = new StringBuilder();
		sb.append(arr[0][0]);
		dfs(0, 0, sb);
		System.out.println(maxValue + " " + minValue);
	}

	private static void dfs(int x, int y, StringBuilder sb) {
		if (x == N - 1 && y == N - 1) {
			check(sb);
			return;
		}
		for (int[] move : moves) {
			int nx = move[0] + x;
			int ny = move[1] + y;
			if (nx >= N || ny >= N) continue;
			sb.append(arr[nx][ny]);
			dfs(nx, ny, sb);
			sb.deleteCharAt(sb.length() - 1);
		}
	}

	private static void check(StringBuilder sb) {
		int value = sb.charAt(0) - '0';
		for (int i = 1; i < sb.length(); i += 2) {
			value = operator(value, sb.charAt(i), sb.charAt(i + 1) - '0');
		}
		maxValue = Math.max(maxValue, value);
		minValue = Math.min(minValue, value);

	}

	private static int operator(int left, char operator, int right) {
		switch (operator) {
			case '+':
				return left + right;
			case '-':
				return left - right;
			case '/':
				return left / right;
			case '*':
				return left * right;
			default:
				return 0;
		}
	}
}
