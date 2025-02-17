package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Boj20010 {
	static List<Node> graph = new ArrayList<>();
	static List<List<Node>> mstGraph = new ArrayList<>();
	static int N, M;
	static int[] parent;
	static int maxDistance = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken()); // 도시의 수
		M = Integer.parseInt(st.nextToken()); // 도시를 잇는 다리의 수
		parent = new int[N];

		for (int i = 0; i < N; i++) {
			parent[i] = i;
			mstGraph.add(new ArrayList<>());
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			graph.add(new Node(from, to, cost));
		}
		kruskal();
		for (int i = 0; i < N; i++) {
			dfs(i, new boolean[N], 0);
		}
		System.out.println(maxDistance);
	}

	private static int dfs(int index, boolean[] visited, int maxCost) {
		visited[index] = true;

		for (Node node : mstGraph.get(index)) {
			if (!visited[node.to]) {
				maxDistance = Math.max(maxDistance, maxCost + node.cost);
				dfs(node.to, visited, maxCost + node.cost);
			}
		}
		return maxDistance;
	}

	private static void kruskal() {
		Collections.sort(graph, (o1, o2) -> o1.cost - o2.cost);
		int total = 0;
		int cnt = 0;

		for (Node node : graph) {
			if (union(node.from, node.to)) {
				total += node.cost;
				mstGraph.get(node.from).add(new Node(node.from, node.to, node.cost));
				mstGraph.get(node.to).add(new Node(node.to, node.from, node.cost));
				cnt++;
				if (cnt == N - 1) break;
			}
		}
		System.out.println(total);
	}

	private static boolean union(int a, int b) {
		int aParent = find(a);
		int bParent = find(b);
		if (aParent != bParent) {
			parent[bParent] = aParent;
			return true;
		}
		return false;
	}

	private static int find(int x) {
		if (parent[x] == x) return x;
		return parent[x] = find(parent[x]);
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
