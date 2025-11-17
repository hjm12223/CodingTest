package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj16398 {

	static int N;
	static int[] parent;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Queue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[2] - o2[2]);
		N = Integer.parseInt(br.readLine());
		parent = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			parent[i] = i;
			for (int j = 1; j <= N; j++) {
				int cost = Integer.parseInt(st.nextToken());
				if (i == j) continue;
				pq.offer(new int[] {i, j, cost});
			}
		}
		long result = 0;
		while (!pq.isEmpty()) {
			int[] curr = pq.poll();
			if (find(curr[0]) != find(curr[1])) {
				union(curr[0], curr[1]);
				result += curr[2];
			}
		}
		System.out.println(result);
	}

	private static void union(int x, int y) {
		int px = find(x);
		int py = find(y);
		if (px != py)
			parent[px] = py;
	}

	private static int find(int x) {
		if (parent[x] != x)
			parent[x] = find(parent[x]);
		return parent[x];
	}
}