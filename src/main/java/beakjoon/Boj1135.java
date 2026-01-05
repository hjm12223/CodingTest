package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Boj1135 {
	static List<List<Integer>> tree = new ArrayList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		for (int i = 0; i <= N; i++) {
			tree.add(new ArrayList<>());
		}
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			int parent = Integer.parseInt(st.nextToken());
			if (parent == -1) continue;
			tree.get(parent).add(i);
		}
		System.out.println(dfs(0));
	}

	private static int dfs(int node) {
		List<Integer> times = new ArrayList<>();

		int maxTime = 0;

		for (Integer child : tree.get(node)) {
			times.add(dfs(child));
		}
		Collections.sort(times, Collections.reverseOrder());

		for (int i = 0; i < times.size(); i++) {
			maxTime = Math.max(maxTime, times.get(i) + 1 + i);
		}
		
		return maxTime;

	}
}