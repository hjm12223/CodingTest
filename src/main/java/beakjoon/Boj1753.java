package beakjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj1753 {
	static List<List<Node>> graph = new ArrayList<>();
	static int V;
	static int[] dist;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		V = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());
		int start = Integer.parseInt(br.readLine());

		for (int i = 0; i <= V; i++)
			graph.add(new ArrayList<>());

		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			graph.get(from).add(new Node(to, cost));
		}
		dijkstra(start);
		for (int i = 1; i <= V; i++) {
			if (dist[i] != Integer.MAX_VALUE) {
				bw.write(dist[i] + "\n");
			} else {
				bw.write("INF" + "\n");
			}
		}
		bw.flush();
		bw.close();
	}

	private static void dijkstra(int start) {
		Queue<Node> pq = new PriorityQueue<>();
		pq.offer(new Node(start, 0));
		dist = new int[V + 1];
		Arrays.fill(dist, Integer.MAX_VALUE);
		dist[start] = 0;
		while (!pq.isEmpty()) {
			Node curr = pq.poll();
			for (Node next : graph.get(curr.curr)) {
				int nextNode = next.curr;
				int nextCost = next.cost + curr.cost;
				if (dist[nextNode] > nextCost) {
					dist[nextNode] = nextCost;
					pq.offer(new Node(nextNode, nextCost));
				}
			}
		}
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
