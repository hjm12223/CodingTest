package beakjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Boj17396 {
	static final long INF = Long.MAX_VALUE;
	static int N, M;
	static int[] ward;
	static List<List<long[]>> graph = new ArrayList<>();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		ward = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) ward[i] = Integer.parseInt(st.nextToken());

		for (int i = 0; i < N; i++) graph.add(new ArrayList<>());

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			long c = Long.parseLong(st.nextToken());

			if ((ward[a] == 1 && a != N - 1) || (ward[b] == 1 && b != N - 1)) continue;

			graph.get(a).add(new long[] {b, c});
			graph.get(b).add(new long[] {a, c});
		}

		long[] dist = new long[N];
		Arrays.fill(dist, INF);
		dist[0] = 0;

		PriorityQueue<long[]> pq = new PriorityQueue<>(Comparator.comparingLong(o -> o[1]));
		pq.offer(new long[] {0, 0});

		while (!pq.isEmpty()) {
			long[] cur = pq.poll();
			int now = (int)cur[0];
			long cost = cur[1];

			if (dist[now] < cost) continue;

			for (long[] next : graph.get(now)) {
				int nx = (int)next[0];
				long nc = cost + next[1];
				if (dist[nx] > nc) {
					dist[nx] = nc;
					pq.offer(new long[] {nx, nc});
				}
			}
		}

		System.out.println(dist[N - 1] == INF ? -1 : dist[N - 1]);
	}
}
