package beakjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Boj14699 {
	static List<List<int[]>> graph = new ArrayList<>();
	static int[] cost, dp;
	static int N;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		cost = new int[N + 1];
		dp = new int[N + 1];
		for (int i = 0; i <= N; i++) {
			graph.add(new ArrayList<>());
		}
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			cost[i] = Integer.parseInt(st.nextToken());
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			graph.get(from).add(new int[] {to, 0});
			graph.get(to).add(new int[] {from, 0});
		}
		for (int i = 1; i <= N; i++) {
			bw.write(dfs(i) + "\n");
		}
		bw.flush();
		bw.close();
	}

	private static int dfs(int curr) {
		if (dp[curr] != 0) {
			return dp[curr];
		}
		int max = 1;
		for (int[] next : graph.get(curr)) {
			if (cost[next[0]] > cost[curr]) {
				max = Math.max(max, 1 + dfs(next[0]));
			}
		}
		return dp[curr] = max;
	}
}
