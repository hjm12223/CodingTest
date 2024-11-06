package beakjoon.dijkstra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj24042 {
	static long[] dist;
	static List<List<Node>> graph;
	static int E;
	static int V;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		graph = new ArrayList<>();

		for (int i = 0; i <= V; i++)
			graph.add(new ArrayList<>());
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			graph.get(from).add(new Node(to, i, i));
			graph.get(to).add(new Node(from, i, i));
		}
		dist = new long[V + 1];

		Arrays.fill(dist, Long.MAX_VALUE);
		System.out.println(dijkstra(1, V));
	}

	private static long dijkstra(int start, int end) {
		dist[start] = 0;
		Queue<Node> pq = new PriorityQueue<>();
		pq.offer(new Node(start, 0, 0));
		while (!pq.isEmpty()) {
			Node curr = pq.poll();
			if (curr.idx == end) return curr.time;
			if (dist[curr.idx] < curr.time) continue;
			for (Node next : graph.get(curr.idx)) {
				long waitTime =
					(curr.time % E <= next.signal) ? (next.signal - curr.time % E) :
						(E - (curr.time % E) + next.signal);
				long nextTime = curr.time + waitTime + 1;

				if (dist[next.idx] > nextTime) {
					dist[next.idx] = nextTime;
					pq.offer(new Node(next.idx, next.signal, nextTime));
				}
			}
		}
		return -1;
	}

	private static class Node implements Comparable<Node> {
		int idx;
		int signal;
		long time;

		public Node(int idx, int signal, long time) {
			this.idx = idx;
			this.signal = signal;
			this.time = time;
		}

		@Override
		public int compareTo(Node o) {
			return Long.compare(this.time, o.time);
		}
	}
}
