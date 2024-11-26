package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Boj11657 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		int V = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());

		List<Node> list = new ArrayList<>();

		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int value = Integer.parseInt(st.nextToken());
			list.add(new Node(from, to, value));
		}
		long[] dist = new long[V + 1];
		Arrays.fill(dist, Integer.MAX_VALUE);
		dist[1] = 0;

		boolean isCycle = false;
		for (int i = 1; i <= V; i++) {
			for (Node edge : list) {
				if (dist[edge.from] != Integer.MAX_VALUE &&
					dist[edge.to] > dist[edge.from] + edge.cost) {
					dist[edge.to] = dist[edge.from] + edge.cost;
					if (i == V) {
						isCycle = true;
					}
				}

			}
		}
		if (isCycle) {
			System.out.println(-1);
		} else {
			for (int i = 2; i <= V; i++) {
				if (dist[i] == Integer.MAX_VALUE) {
					System.out.println(-1);
				} else {
					System.out.println(dist[i]);
				}
			}
		}
	}

	private static class Node {
		int from;
		int to;
		int cost;

		public Node(int from, int to, int cost) {
			this.from = from;
			this.to = to;
			this.cost = cost;
		}
	}
}
