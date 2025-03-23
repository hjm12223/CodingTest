package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Boj5568 {
	static int N;
	static int[] arr;
	static Set<Integer> res = new HashSet<>();
	static boolean[] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		visited = new boolean[N];
		arr = new int[N];
		int r = Integer.parseInt(br.readLine());
		for (int i = 0; i < arr.length; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}

		dfs(new ArrayList<>(), r);
		System.out.println(res.size());
	}

	private static void dfs(List<Integer> comb, int r) {
		if (comb.size() == r) {
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < comb.size(); i++) {
				sb.append(comb.get(i));
			}
			res.add(Integer.parseInt(sb.toString()));
			return;
		}
		for (int i = 0; i < N; i++) {
			if (!visited[i]) {
				visited[i] = true;
				comb.add(arr[i]);
				dfs(comb, r);
				comb.remove(comb.size() - 1);
				visited[i] = false;
			}
		}
	}
}
