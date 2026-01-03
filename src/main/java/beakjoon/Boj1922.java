package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj1922 {
	static int[] parents;
	static int N, M;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		parents = new int[N + 1];
		for (int i = 0; i <= N; i++) {
			parents[i] = i;
		}
		Queue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[2] - o2[2]);
		for (int i = 0; i < M; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			pq.offer(new int[] {from, to, cost});
			pq.offer(new int[] {to, from, cost});

		}
		int result = 0;
		while (!pq.isEmpty()) {
			int[] curr = pq.poll();
			int from = curr[0];
			int to = curr[1];
			if (find(from) != find(to)) {
				union(from, to);
				result += curr[2];
			}
		}
		System.out.println(result);
	}

	static int find(int a) {
		if (parents[a] != a) {
			return parents[a] = find(parents[a]);
		}
		return a;
	}

	static void union(int a, int b) {
		int p_a = parents[a];
		int p_b = parents[b];
		if (p_a != p_b) {
			parents[p_b] = p_a;
		}
	}
}
