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

public class Boj1162 {
	static List<List<Node>> graph = new ArrayList<>();
	static int[][] dist;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		dist = new int[N + 1][K + 1];
		for (int i = 0; i <= N; i++) {
			graph.add(new ArrayList<>());
			Arrays.fill(dist[i], Integer.MAX_VALUE);
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			graph.get(from).add(new Node(to, cost));
			graph.get(to).add(new Node(from, cost));
		}
		dijkstra(N, K);
		int result = Integer.MAX_VALUE;
		for (int i = 0; i <= K; i++) {
			result = Math.min(result, dist[N][i]);
		}
		System.out.println(result);
	}

	private static void dijkstra(int n, int k) {
		Queue<Node> pq = new PriorityQueue<>((o1, o2) -> o1.cost - o2.cost);
		pq.offer(new Node(1, 0, 0));
		dist[1][0] = 0;
		while (!pq.isEmpty()) {
			Node curr = pq.poll();
			for (Node next : graph.get(curr.to)) {
				int nxCost = next.cost + curr.cost;
				if (dist[next.to][curr.k] > nxCost) {
					dist[next.to][curr.k] = nxCost;
					pq.offer(new Node(next.to, nxCost, curr.k));
				}
				if (curr.k < k && dist[next.to][curr.k + 1] > curr.cost) {
					dist[next.to][curr.k + 1] = curr.cost;
					pq.offer(new Node(next.to, curr.cost, curr.k + 1));
				}
			}
		}
	}

	private static class Node {
		int to;
		int cost;
		int k;

		public Node(int to, int cost, int k) {
			this.to = to;
			this.cost = cost;
			this.k = k;
		}

		public Node(int to, int cost) {
			this.to = to;
			this.cost = cost;
		}
	}
}
