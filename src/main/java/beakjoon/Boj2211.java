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

public class Boj2211 {
	static List<List<int[]>> graph = new ArrayList<>();
	static int N, M;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		for (int i = 0; i <= N; i++)
			graph.add(new ArrayList<>());

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			graph.get(from).add(new int[] {to, cost});
			graph.get(to).add(new int[] {from, cost});
		}
		dijkstra();

	}

	private static void dijkstra() {
		Queue<int[]> q = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]);
		q.offer(new int[] {1, 0});
		int[] dist = new int[N + 1];
		Arrays.fill(dist, Integer.MAX_VALUE);
		dist[1] = 0;
		int[] parents = new int[N + 1];
		while (!q.isEmpty()) {
			int[] curr = q.poll();
			if (curr[1] > dist[curr[0]]) continue;
			for (int[] node : graph.get(curr[0])) {
				int nextNode = node[0];
				int nextCost = node[1];
				if (dist[nextNode] > nextCost + curr[1]) {
					parents[nextNode] = curr[0];
					dist[nextNode] = nextCost + curr[1];
					q.offer(new int[] {nextNode, nextCost + curr[1]});
				}
			}
		}
		System.out.println(N - 1);
		for (int i = 2; i <= N; i++) {
			System.out.println(parents[i] + " " + i);
		}
	}
}
