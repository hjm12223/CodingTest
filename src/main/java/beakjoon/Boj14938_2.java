package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj14938_2 {
	static int N, M;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 노드
		M = Integer.parseInt(st.nextToken()); // 수색범위
		int R = Integer.parseInt(st.nextToken()); // 간선

		List<List<Node>> graph = new ArrayList<>();
		for (int i = 0; i <= N; i++) {
			graph.add(new ArrayList<>());
		}
		int[] item = new int[N + 1];
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			int value = Integer.parseInt(st.nextToken());
			item[i] = value;
		}
		for (int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			graph.get(from).add(new Node(to, cost));
			graph.get(to).add(new Node(from, cost));
		}
		int maxItem = 0;
		for (int i = 1; i <= N; i++) {
			maxItem = Math.max(maxItem, dijkstra(i, graph, item));
		}
		System.out.println(maxItem);
	}

	private static int dijkstra(int start, List<List<Node>> graph, int[] item) {
		Queue<Node> q = new PriorityQueue<>();

		q.offer(new Node(start, 0));

		boolean[] isVisited = new boolean[N + 1];
		int[] dist = new int[N + 1];
		Arrays.fill(dist, Integer.MAX_VALUE);
		dist[start] = 0;
		while (!q.isEmpty()) {
			Node curr = q.poll();
			if (curr.cost > dist[curr.curr]) continue;
			for (int i = 0; i < graph.get(curr.curr).size(); i++) {
				int nextIdx = graph.get(curr.curr).get(i).curr;
				int nextCost = curr.cost + graph.get(curr.curr).get(i).cost;
				if (nextCost < dist[nextIdx] && nextCost <= M) {
					dist[nextIdx] = nextCost;
					q.offer(new Node(nextIdx, nextCost));
				}
			}
		}
		int value = 0;
		for (int i = 1; i <= N; i++) {
			if (dist[i] <= M) {
				value += item[i];
			}
		}
		return value;
	}

	private static class Node implements Comparable<Node> {
		int curr;
		int cost;

		public Node(int curr, int cost) {
			this.curr = curr;
			this.cost = cost;
		}

		@Override
		public int compareTo(Node o) {
			return this.cost - o.cost;
		}
	}
}
