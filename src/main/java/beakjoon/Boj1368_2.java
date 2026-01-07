package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj1368_2 {
	static int[] parents, arr;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		arr = new int[N + 1];
		parents = new int[N + 1];
		Queue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[2] - o2[2]);
		for (int i = 1; i <= N; i++) {
			arr[i] = Integer.parseInt(br.readLine());
			parents[i] = i;
		}
		for (int i = 1; i <= N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				int cost = Integer.parseInt(st.nextToken());
				if (i == j) pq.offer(new int[] {0, j, arr[j]});
				pq.offer(new int[] {i, j, cost});
			}
		}
		int result = 0;
		while (!pq.isEmpty()) {
			int[] curr = pq.poll();
			int from = curr[0];
			int to = curr[1];
			int cost = curr[2];
			if (find(from) != find(to)) {
				union(from, to);
				result += cost;
			}
		}
		System.out.println(result);
	}

	private static void union(int from, int to) {
		int a = find(from);
		int b = find(to);
		if (arr[a] < arr[b]) {
			parents[b] = a;
		} else {
			parents[a] = b;
		}
	}

	private static int find(int x) {
		if (parents[x] != x) {
			return parents[x] = find(parents[x]);
		}
		return x;
	}
}
