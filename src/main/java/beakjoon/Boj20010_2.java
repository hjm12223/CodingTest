package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj20010_2 {
	static int N, M;
	static List<List<int[]>> graph = new ArrayList<>();
	static int[] parent;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		parent = new int[N + 1];
		for (int i = 0; i <= N; i++) {
			graph.add(new ArrayList<>());
			parent[i] = i;
		}

		Queue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[2] - o2[2]);

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			graph.get(from).add(new int[] {to, cost});
			graph.get(to).add(new int[] {from, cost});
			pq.offer(new int[] {from, to, cost});
			pq.offer(new int[] {to, from, cost});
		}

		long minResult = 0;

		while (!pq.isEmpty()) {
			int[] curr = pq.poll();
			int from = curr[0];
			int to = curr[1];
			if (find(from) != find(to)) {
				union(from, to);
				minResult += curr[2];
			}
		}
		System.out.println(minResult);

	}

	private static void union(int x, int y) {
		int fx = find(x);
		int fy = find(y);
		if (fx != fy) {
			parent[fx] = fy;
		}
	}

	private static int find(int x) {
		if (x != parent[x]) {
			parent[x] = find(parent[x]);
		}
		return parent[x];
	}
}
