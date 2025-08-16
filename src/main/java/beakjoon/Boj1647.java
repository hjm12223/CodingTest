package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.StringTokenizer;

public class Boj1647 {
	static int[] parents;
	static int[][] graph;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		graph = new int[M + 1][3];
		parents = new int[N + 1];
		for (int i = 0; i <= N; i++) {
			parents[i] = i;
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			graph[i][0] = from;
			graph[i][1] = to;
			graph[i][2] = cost;
		}
		Arrays.sort(graph, (o1, o2) -> o1[2] - o2[2]);
		int result = 0;
		Deque<Integer> stack = new ArrayDeque<>();
		for (int i = 0; i < M; i++) {
			if (find(graph[i][0]) != find(graph[i][1])) {
				union(graph[i][0], graph[i][1]);
				result += graph[i][2];
				stack.push(graph[i][2]);
			}
		}
		System.out.println(result - stack.poll());
	}

	private static int find(int x) {
		if (x != parents[x]) {
			return parents[x] = find(parents[x]);
		}
		return x;
	}

	private static void union(int a, int b) {
		int parent_a = find(a);
		int parent_b = find(b);
		if (parent_a != parent_b) {
			if (graph[parent_a][2] > graph[parent_b][2]) {
				parents[parent_a] = parent_b;
			} else {
				parents[parent_b] = parent_a;
			}
		}
	}

	private static class Node {
		int idx;
		int cost;

		public Node(int idx, int cost) {
			this.idx = idx;
			this.cost = cost;
		}
	}
}
