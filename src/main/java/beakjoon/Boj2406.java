package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj2406 {
	static int[] parents;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		parents = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			parents[i] = i;
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			union(from, to);
		}
		Queue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[2] - o2[2]);
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				int value = Integer.parseInt(st.nextToken());
				if (i == j || i == 1 || j == 1) continue;
				pq.offer(new int[] {i, j, value});
			}
		}
		int cnt = 0;
		int result = 0;
		StringBuilder sb = new StringBuilder();
		while (!pq.isEmpty()) {
			int[] node = pq.poll();
			int from = find(node[0]);
			int to = find(node[1]);
			int value = node[2];
			if (from != to) {
				sb.append(node[0] + " " + node[1] + "\n");
				union(from, to);
				cnt++;
				result += value;
			}
		}
		System.out.println(result + " " + cnt);
		System.out.println(sb);
	}

	private static void union(int from, int to) {
		int parent_a = find(from);
		int parent_b = find(to);
		if (parent_a > parent_b) {
			parents[parent_b] = parent_a;
		} else {
			parents[parent_a] = parent_b;

		}
	}

	private static int find(int x) {
		if (parents[x] != x) {
			return parents[x] = find(parents[x]);
		}
		return x;
	}
}
