package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj1922_2 {
	static int[] parents;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());

		parents = new int[N + 1];

		for (int i = 1; i <= N; i++)
			parents[i] = i;

		Queue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[2] - o2[2]);

		for (int i = 0; i < M; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());

			pq.offer(new int[] {from, to, cost});
		}
		int result = 0;
		while (!pq.isEmpty()) {
			int[] curr = pq.poll();
			int from = find(curr[0]);
			int to = find(curr[1]);
			if (from != to) {
				union(from, to);
				result += curr[2];
			}
		}
		System.out.println(result);

	}

	private static void union(int a, int b) {
		int p_a = find(a);
		int p_b = find(b);
		if (p_a != p_b) {
			parents[p_a] = p_b;
		}
	}

	static int find(int x) {
		if (parents[x] != x) {
			return parents[x] = find(parents[x]);
		}
		return x;
	}
}
