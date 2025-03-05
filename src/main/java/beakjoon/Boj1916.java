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

public class Boj1916 {
	static List<List<Node>> graph = new ArrayList<>();
	static int N;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		for (int i = 0; i <= N; i++)
			graph.add(new ArrayList<>());
		int M = Integer.parseInt(br.readLine());

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			graph.get(from).add(new Node(to, cost));
		}
		st = new StringTokenizer(br.readLine());
		int from = Integer.parseInt(st.nextToken());
		int to = Integer.parseInt(st.nextToken());
		System.out.println(dijkstra(from, to));
	}

	private static int dijkstra(int from, int to) {
		Queue<Node> pq = new PriorityQueue<>((o1, o2) -> o1.cost - o2.cost);
		int[] dist = new int[N + 1];
		Arrays.fill(dist, Integer.MAX_VALUE);
		dist[from] = 0;
		pq.offer(new Node(from, 0));
		while (!pq.isEmpty()) {
			Node curr = pq.poll();
			if (curr.to == to) {
				return dist[to];
			}
			for (Node next : graph.get(curr.to)) {
				if (dist[next.to] > curr.cost + next.cost) {
					dist[next.to] = curr.cost + next.cost;
					pq.offer(new Node(next.to, next.cost + curr.cost));
				}
			}
		}
		return dist[to];
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
