package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj14950 {
	static List<List<int[]>> graph = new ArrayList<>();

	static int N, M, T;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		// 해당 문제에서는 현재 위치에서 인접한 다른 도시도 정복이 가능하다 즉 다른 도시로 이동할 필요가 없다.
		// 머무르는 기준을 어떻게 해야할까?
		// outDegree  가장 많을 경우 머무른다?

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken()); // 한번 정복할 때 마다 증가하는 비용

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
		prim();
	}

	private static void prim() {
		Queue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]);
		int conquerCnt = 0;
		long cost = 0;
		boolean[] visited = new boolean[N + 1];
		visited[1] = true;
		for (int[] edge : graph.get(1)) {
			pq.offer(new int[] {edge[0], edge[1]});
		}

		while (!pq.isEmpty()) {
			int[] curr = pq.poll();
			if (visited[curr[0]]) continue;
			visited[curr[0]] = true;
			cost += curr[1] + ((long)T * conquerCnt);
			conquerCnt++;
			for (int[] next : graph.get(curr[0])) {
				int nextIndex = next[0];
				if (!visited[nextIndex]) {
					pq.offer(next);
				}
			}
		}
		System.out.println(cost);
	}
}
