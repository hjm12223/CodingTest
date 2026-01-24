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

public class Boj20182_2 {
	static List<List<int[]>> graph = new ArrayList<>();
	static int C;
	static int[] coins;
	static boolean[] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int A = Integer.parseInt(st.nextToken());
		int B = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		coins = new int[N + 1];
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
		visited = new boolean[N + 1];
		dijkstra(A, B);

	}

	private static void dijkstra(int start, int target) {
		Queue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]);
		pq.offer(new int[] {start, 0, C});
		Arrays.fill(coins, Integer.MAX_VALUE);
		coins[start] = 0;
		visited[start] = true;
		while (!pq.isEmpty()) {
			int[] curr = pq.poll();
			for (int[] node : graph.get(curr[0])) {
				int next = node[0];
				int cost = node[1];
				int nextCoin = curr[2] - cost;
				if (nextCoin < 0) continue;
				if (!visited[next]) {
					visited[next] = true;
					coins[next] = Math.min(cost, coins[next]);
					pq.offer(new int[] {next, cost, nextCoin});
				}
			}
		}
		System.out.println(coins[target] == Integer.MAX_VALUE ? -1 : coins[target]);
	}
}
