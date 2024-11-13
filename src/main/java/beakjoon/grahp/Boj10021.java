package beakjoon.grahp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Boj10021 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());

		int[][] fields = new int[N][2]; // 필드 선언

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			fields[i][0] = Integer.parseInt(st.nextToken()); // x
			fields[i][1] = Integer.parseInt(st.nextToken()); // y
		}

		PriorityQueue<Edge> pq = new PriorityQueue<>();
		boolean[] visited = new boolean[N];
		int totalCost = 0;
		int edgesUsed = 0;

		visited[0] = true;
		for (int i = 1; i < N; i++) {
			int cost = calculateCost(fields[0], fields[i]);
			if (cost >= C) {
				pq.offer(new Edge(0, i, cost));
			}
		}

		while (!pq.isEmpty()) {
			Edge edge = pq.poll();

			if (!visited[edge.to]) {
				visited[edge.to] = true;
				totalCost += edge.cost;
				edgesUsed++;

				for (int i = 0; i < N; i++) {
					if (!visited[i]) {
						int newCost = calculateCost(fields[edge.to], fields[i]);
						if (newCost >= C) {
							pq.offer(new Edge(edge.to, i, newCost));
						}
					}
				}
			}
		}

		System.out.println(edgesUsed == N - 1 ? totalCost : -1);
	}

	private static int calculateCost(int[] point1, int[] point2) {
		int dx = point1[0] - point2[0];
		int dy = point1[1] - point2[1];
		return dx * dx + dy * dy;
	}

	private static class Edge implements Comparable<Edge> {
		int from, to, cost;

		public Edge(int from, int to, int cost) {
			this.from = from;
			this.to = to;
			this.cost = cost;
		}

		@Override
		public int compareTo(Edge other) {
			return Integer.compare(this.cost, other.cost);
		}
	}
}
