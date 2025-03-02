package beakjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Boj3980 {
	static final int N = 11;
	static int result;
	static int[][] map;
	static boolean[] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int T = Integer.parseInt(br.readLine());
		// 포지션이 안겹치는 선에서 가장 높은 오버롤의 총합을 출력 -> DFS
		while (T-- > 0) {
			result = 0;
			map = new int[N][N];
			visited = new boolean[N];
			for (int i = 0; i < N; i++) {
				map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			}
			dfs(0, 0);
			bw.write(String.valueOf(result));
			bw.newLine();
		}
		bw.flush();
		bw.close();
	}

	private static void dfs(int sum, int depth) {
		if (depth == N) {
			result = Math.max(sum, result);
			return;
		}
		for (int i = 0; i < N; i++) {
			if (!visited[i] && map[depth][i] > 0) {
				visited[i] = true;
				dfs(sum + map[depth][i], depth + 1);
				visited[i] = false;
			}
		}
	}
}
