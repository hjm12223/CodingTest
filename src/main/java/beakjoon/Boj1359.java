package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Boj1359 {
	static int[] arr;
	static boolean[] visited;
	static int N, M, K;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		visited = new boolean[N + 1];
		List<List<Integer>> comb = new ArrayList<>();
		dfs(comb, new ArrayList<>());
		List<Integer> list = comb.get(0);
		int numerator = 0;
		int denominator = comb.size();

		for (List<Integer> next : comb) {
			int cnt = 0;
			for (Integer value : next) {
				if (list.contains(value)) {
					cnt++;
				}
			}
			if (cnt >= K)
				numerator++;
		}

		double result = (double)numerator / denominator;
		System.out.println(result);
	}

	private static void dfs(List<List<Integer>> comb, List<Integer> c) {
		if (c.size() == M) {
			comb.add(new ArrayList<>(c));
			return;
		}

		for (int i = 1; i <= N; i++) {
			if (!visited[i]) {
				visited[i] = true;
				c.add(i);
				dfs(comb, c);
				c.remove(c.size() - 1);
				visited[i] = false;

			}
		}
	}

}
/*
1 부터 N까지의 정수중 서로 다른 M개의 수를 골라

 */
