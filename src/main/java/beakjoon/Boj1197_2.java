package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Boj1197_2 {
	static int[] parent;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int V = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());
		
		List<Node> edges = new ArrayList<>();
		parent = new int[V + 1];

		for (int i = 1; i <= V; i++) {
			parent[i] = i;
		}

		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			edges.add(new Node(from, to, cost));
		}

		edges.sort((o1, o2) ->
			o1.cost - o2.cost);

		int result = 0;
		for (Node edge : edges) {
			if (find(edge.from) != find(edge.to)) {
				union(edge.from, edge.to);
				result += edge.cost;
			}
		}

		System.out.println(result);
	}

	private static void union(int a, int b) {
		int parentA = find(a);
		int parentB = find(b);
		if (parent[parentA] < parent[parentB]) {
			parent[parentA] = parent[parentB];
		} else {
			parent[parentB] = parent[parentA];
		}
	}

	private static int find(int a) {
		if (parent[a] != a) {
			parent[a] = find(parent[a]);
		}
		return parent[a];
	}

	private static class Node {
		int from;
		int to;
		int cost;

		public Node(int from, int to, int cost) {
			this.from = from;
			this.to = to;
			this.cost = cost;
		}
	}
}
