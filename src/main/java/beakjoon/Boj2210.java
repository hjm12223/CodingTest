package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Boj2210 {
	static Set<String> set = new HashSet<>();
	static int[] dx = new int[] {0, 1, 0, -1};
	static int[] dy = new int[] {1, 0, -1, 0};
	static boolean[][] isVisited;
	static int cnt = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[][] arr = new int[5][5];
		for (int i = 0; i < 5; i++) {
			arr[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		}
		isVisited = new boolean[5][5];
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				StringBuilder sb = new StringBuilder();
				sb.append(arr[i][j]);
				dfs(i, j, arr, sb);
			}
		}
		System.out.println(set.size());
	}

	private static void dfs(int x, int y, int[][] arr, StringBuilder stringBuilder) {
		System.out.println("cnt++ = " + cnt++);
		if (stringBuilder.length() == 6) {
			set.add(stringBuilder.toString());
			return;
		}
		for (int d = 0; d < 4; d++) {
			int nx = dx[d] + x;
			int ny = dy[d] + y;
			if (nx < 0 || ny < 0 || nx >= 5 || ny >= 5) continue;
			stringBuilder.append(arr[nx][ny]);
			dfs(nx, ny, arr, stringBuilder);
			stringBuilder.deleteCharAt(stringBuilder.length() - 1);
		}
	}
}
