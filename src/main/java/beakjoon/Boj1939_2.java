package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Boj1939_2 {
	static int[] parents;
	static List<Node> graph = new ArrayList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		parents = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			parents[i] = i;
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			graph.add(new Node(from, to, cost));
		}
		st = new StringTokenizer(br.readLine());
		int from = Integer.parseInt(st.nextToken());
		int to = Integer.parseInt(st.nextToken());
		graph.sort((o1, o2) -> o2.cost - o1.cost);
		for (Node node : graph) {
			union(node.from, node.to);
			union(node.to, node.from);
			if (find(from) == find(to)) {
				System.out.println(node.cost);
				return;
			}
		}
	}

	private static void union(int x, int y) {
		int parent_x = find(x);
		int parent_y = find(y);
		if (parent_x < parent_y) {
			parents[parent_y] = parent_x;
		} else {
			parents[parent_x] = parent_y;
		}

	}

	private static int find(int x) {
		if (parents[x] != x) {
			return find(parents[x]);
		}
		return x;
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
