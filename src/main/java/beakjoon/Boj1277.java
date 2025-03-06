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

public class Boj1277 {
	static Location[] loc;
	static List<List<Node>> graph = new ArrayList<>();
	static double M;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int W = Integer.parseInt(st.nextToken());
		M = Double.parseDouble(br.readLine());
		loc = new Location[N + 1];
		for (int i = 0; i <= N; i++)
			graph.add(new ArrayList<>());
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			loc[i] = new Location(x, y);
		}

		for (int i = 0; i < W; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			graph.get(from).add(new Node(to, 0));
			graph.get(to).add(new Node(from, 0));
		}
		for (int i = 1; i <= N; i++) {
			for (int j = i + 1; j <= N; j++) {
				Location prevLoc = loc[i];
				Location nextLoc = loc[j];
				double nx = Math.abs(prevLoc.x - nextLoc.x);
				double ny = Math.abs(prevLoc.y - nextLoc.y);
				double dist = Math.sqrt(nx * nx + ny * ny);
				if (dist <= M) {
					graph.get(i).add(new Node(j, dist));
					graph.get(j).add(new Node(i, dist));
				}
			}
		}
		System.out.println(dijkstra(1, N));
	}

	private static int dijkstra(int start, int N) {
		Queue<Node> pq = new PriorityQueue<>((o1, o2) -> Double.compare(o1.cost, o2.cost));
		double[] dist = new double[N + 1];
		Arrays.fill(dist, Double.MAX_VALUE);
		dist[start] = 0;
		pq.offer(new Node(start, 0));
		while (!pq.isEmpty()) {
			Node curr = pq.poll();
			for (Node next : graph.get(curr.to)) {
				if (dist[next.to] > curr.cost + next.cost) {
					dist[next.to] = curr.cost + next.cost;
					pq.offer(new Node(next.to, curr.cost + next.cost));
				}
			}
		}
		return (int)Math.floor(dist[N] * 1000);
	}

	private static class Location {
		int x;
		int y;

		public Location(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	private static class Node {
		int to;
		double cost;

		public Node(int to, double cost) {
			this.to = to;
			this.cost = cost;
		}
	}
}
