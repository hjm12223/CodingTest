package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj17471 {
	static List<List<Node>> graph = new ArrayList<>();
	static int result = Integer.MAX_VALUE;
	static int[] cost;
	static boolean[] selected;
	static int sum = 0;
	static int N;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		selected = new boolean[N];

		cost = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		for (int i = 0; i < cost.length; i++)
			sum += cost[i];
		for (int i = 0; i <= N; i++)
			graph.add(new ArrayList<>());

		for (int n = 0; n < N; n++) {
			st = new StringTokenizer(br.readLine());
			int K = Integer.parseInt(st.nextToken());
			while (K-- > 0) {
				int nextN = Integer.parseInt(st.nextToken()) - 1;
				graph.get(n).add(new Node(nextN, cost[nextN]));
			}
		}
		back(0);
		System.out.println(result);
	}

	private static void back(int depth) {
		if (depth == N) {
			List<Integer> g1 = new ArrayList<>();
			List<Integer> g2 = new ArrayList<>();
			for (int i = 0; i < N; i++) {
				if (selected[i]) g1.add(i);
				else g2.add(i);
			}
			if (g1.isEmpty() || g2.isEmpty()) return;
			if (isConnected(g1) && isConnected(g2)) {
				int sumA = 0, sumB = 0;
				for (int node : g1) sumA += cost[node];
				for (int node : g2) sumB += cost[node];

				result = Math.min(result, Math.abs(sumA - sumB));
			}
			return;
		}

		selected[depth] = true;
		back(depth + 1);

		selected[depth] = false;
		back(depth + 1);
	}

	private static boolean isConnected(List<Integer> group) {
		Queue<Integer> q = new ArrayDeque<>();
		boolean[] visited = new boolean[N];
		int cnt = 1;
		visited[group.get(0)] = true;
		q.offer(group.get(0));
		while (!q.isEmpty()) {
			Integer curr = q.poll();
			for (Node node : graph.get(curr)) {
				if (group.contains(node.to) && !visited[node.to]) {
					visited[node.to] = true;
					q.offer(node.to);
					cnt++;
				}
			}
		}
		return cnt == group.size();
	}

	private static class Node {
		int to;
		int cost;

		public Node(int to, int cost) {
			this.to = to;
			this.cost = cost;
		}
	}
}
