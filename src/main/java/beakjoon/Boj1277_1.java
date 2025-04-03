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

public class Boj1277_1 {
	static List<List<Node>> graph = new ArrayList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		double minimum = Double.parseDouble(br.readLine());
		Location[] locations = new Location[N + 1];
		for (int i = 0; i <= N; i++)
			graph.add(new ArrayList<>());
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			locations[i] = new Location(x, y);
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			graph.get(from).add(new Node(to, 0));
			graph.get(to).add(new Node(from, 0));
		}
		for (int i = 1; i <= N; i++) {
			for (int j = i + 1; j <= N; j++) {
				Location prev = locations[i];
				Location next = locations[j];
				double nx = Math.abs(prev.x - next.x);
				double ny = Math.abs(prev.y - next.y);
				double dist = Math.sqrt(nx * nx + ny * ny);
				if (dist <= minimum) {
					graph.get(i).add(new Node(j, dist));
					graph.get(j).add(new Node(i, dist));
				}
			}
		}

		Queue<Node> q = new PriorityQueue<>((o1, o2) -> Double.compare(o1.cost, o2.cost));
		q.offer(new Node(1, 0));
		double[] dist = new double[N + 1];
		Arrays.fill(dist, Double.MAX_VALUE);
		dist[1] = 0;
		while (!q.isEmpty()) {
			Node curr = q.poll();
			for (Node next : graph.get(curr.curr)) {
				if (dist[next.curr] > curr.cost + next.cost) {
					dist[next.curr] = curr.cost + next.cost;
					q.offer(new Node(next.curr, curr.cost + next.cost));
				}
			}
		}
		System.out.println((int)Math.floor(dist[N] * 1000));
	}

	private static class Node {
		int curr;
		double cost;

		public Node(int curr, double cost) {
			this.curr = curr;
			this.cost = cost;
		}

	}

	private static class Location {
		int x;
		int y;

		public Location(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}
