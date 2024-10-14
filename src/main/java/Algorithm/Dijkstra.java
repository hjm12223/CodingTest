package Algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Dijkstra {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int V = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		int K = Integer.parseInt(st.nextToken());
		int[][] graph = new int[E][3];

		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			graph[i][0] = Integer.parseInt(st.nextToken()); // 출발
			graph[i][1] = Integer.parseInt(st.nextToken()); // 도착
			graph[i][2] = Integer.parseInt(st.nextToken()); // 비용
		}
		List<List<Node>> list = new ArrayList<>();
		for (int i = 0; i <= V; i++)
			list.add(new ArrayList<>());

		for (int i = 0; i < E; i++) {
			int from = graph[i][0];
			int to = graph[i][1];
			int cost = graph[i][2];
			list.get(from).add(new Node(to, cost));
		}

		Queue<Node> pq = new PriorityQueue<>();
		int[] dist = new int[V + 1];
		Arrays.fill(dist, Integer.MAX_VALUE);
		dist[0] = 0;
		dist[K] = 0;

		pq.offer(new Node(K, 0));

		while (!pq.isEmpty()) {
			Node curr = pq.poll();
			if (dist[curr.index] > curr.cost) continue;
			for (int i = 0; i < list.get(curr.index).size(); i++) {
				int nextIndex = list.get(curr.index).get(i).index;
				int nextCost = curr.cost + list.get(curr.index).get(i).cost;
				if (dist[nextIndex] > nextCost) {
					dist[nextIndex] = nextCost;
					pq.offer(new Node(nextIndex, nextCost));
				}
			}
		}
		for (int i = K; i <= V; i++) {
			if (dist[i] != Integer.MAX_VALUE) {
				System.out.println(dist[i]);
			} else {
				System.out.println("INF");
			}
		}

	}

	public static class Node implements Comparable<Node> {
		int index;
		int cost;

		public Node(int index, int cost) {
			this.index = index;
			this.cost = cost;
		}

		@Override
		public int compareTo(Node o) {
			return Integer.compare(this.cost, o.cost);
		}
	}
}
