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

public class Boj20007 {
	static List<List<Node>> graph;
	static int[] dist;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = par(st.nextToken());
		int M = par(st.nextToken());
		int stamina = par(st.nextToken());
		int start = par(st.nextToken());
		graph = new ArrayList<>();
		dist = new int[N + 1];
		Arrays.fill(dist, Integer.MAX_VALUE);
		for (int i = 0; i <= N; i++)
			graph.add(new ArrayList<>());
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = par(st.nextToken());
			int to = par(st.nextToken());
			int cost = par(st.nextToken());
			graph.get(from).add(new Node(to, cost));
			graph.get(to).add(new Node(from, cost));
		}
		dijkstra(start);
		Arrays.sort(dist);
		int day = 0;
		int todayDist = 0;
		for (int i = 1; i < N; i++) {
			int roundTrip = dist[i] * 2;
			if (dist[i] == Integer.MAX_VALUE || roundTrip > stamina) {
				System.out.println(-1);
				return;
			}
			if (todayDist + roundTrip > stamina) {
				day++;
				todayDist = 0;
			}
			todayDist += roundTrip;
		}
		System.out.println(day + 1);
	}

	private static void dijkstra(int start) {
		dist[start] = 0;
		Queue<Node> pq = new PriorityQueue<>((o1, o2) -> o1.cost - o2.cost);
		pq.offer(new Node(start, 0));
		while (!pq.isEmpty()) {
			Node curr = pq.poll();
			for (Node next : graph.get(curr.to)) {
				int nxCost = next.cost + curr.cost;
				if (dist[next.to] > nxCost) {
					dist[next.to] = nxCost;
					pq.offer(new Node(next.to, nxCost));
				}
			}
		}
	}

	private static int par(String st) {
		return Integer.parseInt(st);
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
