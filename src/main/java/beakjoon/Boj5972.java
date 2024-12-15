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

public class Boj5972 {
	static final int INF = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int V = parsingInt(st.nextToken());
		int E = parsingInt(st.nextToken());

		List<List<Node>> graph = new ArrayList<>();
		for (int i = 0; i <= V; i++)
			graph.add(new ArrayList<>());

		for (int i = 0; i < E; i++) {

			st = new StringTokenizer(br.readLine());
			int from = parsingInt(st.nextToken());
			int to = parsingInt(st.nextToken());
			int cost = parsingInt(st.nextToken());

			graph.get(from).add(new Node(to, cost));
			graph.get(to).add(new Node(from, cost));
		}
		Queue<Node> pq = new PriorityQueue<>();
		pq.offer(new Node(1, 0));

		int[] dist = new int[V + 1];
		Arrays.fill(dist, INF);
		dist[1] = 0;
		while (!pq.isEmpty()) {
			Node curr = pq.poll();
			for (int i = 0; i < graph.get(curr.curr).size(); i++) {
				int nextIndex = graph.get(curr.curr).get(i).curr;
				int nextCost = graph.get(curr.curr).get(i).cost + curr.cost;
				if (dist[nextIndex] > nextCost) {
					dist[nextIndex] = nextCost;
					pq.offer(new Node(nextIndex, nextCost));
				}
			}
		}
		bw.write(String.valueOf(dist[V]));
		bw.flush();
		bw.close();

	}

	private static int parsingInt(String st) {
		return Integer.parseInt(st);
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
