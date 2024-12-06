import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj1197 {
	static int[] dist;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int V = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());

		List<List<Node>> graph = new ArrayList<>();
		dist = new int[V + 1];
		for (int i = 0; i <= V; i++)
			graph.add(new ArrayList<>());

		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());

			graph.get(from).add(new Node(to, cost));
			graph.get(to).add(new Node(from, cost));
		}
		prim(graph, V);
	}

	private static void prim(List<List<Node>> graph, int v) {
		Queue<Node> q = new PriorityQueue<>();
		boolean[] isVisited = new boolean[v + 1];
		q.offer(new Node(1, 0));
		int total = 0;
		while (!q.isEmpty()) {
			Node curr = q.poll();
			if (isVisited[curr.index]) continue;
			isVisited[curr.index] = true;
			total += curr.cost;
			for (Node node : graph.get(curr.index)) {
				if (!isVisited[node.index]) {
					q.offer(new Node(node.index, node.cost));
				}
			}
		}
		System.out.println(total);
	}

	private static class Node implements Comparable<Node> {
		int index;
		int cost;

		public Node(int index, int cost) {
			this.index = index;
			this.cost = cost;
		}

		@Override
		public int compareTo(Node o) {
			return this.cost - o.cost;
		}
	}
}
