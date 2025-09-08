package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj22868 {
	static int N, M;
	static List<List<Integer>> graph = new ArrayList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		for (int i = 0; i <= N; i++) {
			graph.add(new ArrayList<>());
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			graph.get(from).add(to);
			graph.get(to).add(from);
		}
		for (int i = 1; i <= N; i++) {
			Collections.sort(graph.get(i));
		}
		st = new StringTokenizer(br.readLine());
		int S = Integer.parseInt(st.nextToken());
		int H = Integer.parseInt(st.nextToken());
		int result = 0;
		Node first = bfs(S, H, new boolean[N + 1]);
		result += first.cost;

		boolean[] visited = new boolean[N + 1];
		for (int node : first.history) {
			if (node != S && node != H) visited[node] = true;
		}
		Node second = bfs(H, S, visited);
		result += second.cost;

		System.out.println(result);
	}

	private static Node bfs(int from, int to, boolean[] visited) {
		Queue<Node> q = new ArrayDeque<>();
		q.offer(new Node(from, 0, new ArrayList<>()));
		int[] parent = new int[N + 1];
		visited[from] = true;
		Arrays.fill(parent, -1);
		boolean[] localVisited = new boolean[N + 1];
		localVisited[from] = true;
		while (!q.isEmpty()) {
			Node node = q.poll();
			int curr = node.curr;
			int cost = node.cost;
			if (curr == to) {
				List<Integer> path = new ArrayList<>();
				int p = to;
				while (p != -1) {
					path.add(p);
					p = parent[p];
				}
				return new Node(curr, cost, path);
			}
			for (int next : graph.get(curr)) {
				if (!localVisited[next] && !visited[next]) {
					localVisited[next] = true;
					parent[next] = curr;
					q.offer(new Node(next, cost + 1));
				}
			}
		}
		return null;
	}

	private static class Node {
		int curr;
		int cost;
		List<Integer> history;

		public Node(int curr, int cost) {
			this.curr = curr;
			this.cost = cost;
		}

		public Node(int curr, int cost, List<Integer> history) {
			this.curr = curr;
			this.cost = cost;
			this.history = history;
		}
	}
}
