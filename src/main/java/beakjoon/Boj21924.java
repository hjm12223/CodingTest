package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj21924 {
	static List<List<int[]>> graph = new ArrayList<>();
	static int N, M;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		long totalCost = 0;
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
			totalCost += cost;
		}
		long cost = prim();
		System.out.println(cost == -1 ? cost : totalCost - cost);
	}

	private static long prim() {
		Queue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]);
		boolean[] visited = new boolean[N + 1];
		pq.offer(new int[] {1, 0});
		long sum = 0;
		int cnt = 0;

		while (!pq.isEmpty()) {
			int[] curr = pq.poll();
			int node = curr[0];
			int cost = curr[1];

			if (visited[node]) continue;
			visited[node] = true;
			sum += cost;
			cnt++;
			for (int[] next : graph.get(node)) {
				if (!visited[next[0]]) {
					pq.offer(new int[] {next[0], next[1]});
				}
			}

		}
		if (cnt == N) return sum;
		else return -1;
	}
}
