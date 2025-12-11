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

public class Boj18223 {
	static List<List<int[]>> graph = new ArrayList<>();
	static int V, E, P, minCost;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		P = Integer.parseInt(st.nextToken());

		for (int i = 0; i <= V; i++)
			graph.add(new ArrayList<>());

		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			graph.get(from).add(new int[] {to, cost});
			graph.get(to).add(new int[] {from, cost});
		}
		int[] startDist = dijkstra(1);
		int[] pDist = dijkstra(P);

		if (startDist[V] == pDist[1] + pDist[V]) {
			System.out.println("SAVE HIM");
			return;
		}
		System.out.println("GOOD BYE");
	}

	private static int[] dijkstra(int start) {
		Queue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]);
		pq.offer(new int[] {start, 0});
		int[] dist = new int[V + 1];
		Arrays.fill(dist, (1 << 30));
		dist[start] = 0;
		while (!pq.isEmpty()) {
			int[] curr = pq.poll();
			if (curr[1] > dist[curr[0]]) continue;
			for (int[] next : graph.get(curr[0])) {
				int cost = next[1] + curr[1];
				if (cost < dist[next[0]]) {
					dist[next[0]] = cost;
					pq.offer(new int[] {next[0], cost});
				}
			}
		}
		return dist;
	}
}
