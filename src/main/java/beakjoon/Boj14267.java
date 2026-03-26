package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 시간복잡도
 N, M = 2 <=  N ,M  <= 100,000
 O(N log N)
 */
public class Boj14267 {
	static List<List<Integer>> tree = new ArrayList<>();
	static int[] praise;
	static int[] dp;
	static int N, M;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		dp = new int[N + 1];
		praise = new int[N + 1];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i <= N; i++) tree.add(new ArrayList<>());

		for (int i = 1; i <= N; i++) {
			int parent = Integer.parseInt(st.nextToken());
			if (parent == -1) continue;
			tree.get(parent).add(i);
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int people = Integer.parseInt(st.nextToken());
			int pr = Integer.parseInt(st.nextToken());
			praise[people] += pr;
		}
		dp[1] = praise[1];
		dfs(1);
		StringBuilder sb = new StringBuilder();
		for (int i = 1; i <= N; i++) {
			sb.append(dp[i]).append(" ");
		}
		System.out.println(sb);
	}

	private static void dfs(int parent) {
		for (int next : tree.get(parent)) {
			dp[next] = dp[parent] + praise[next];
			dfs(next);
		}
	}
}
