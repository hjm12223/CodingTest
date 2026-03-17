package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj12896_2 {
	static List<List<int[]>> graph = new ArrayList<>();
	static int N;
	static int M;
	static int result = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		M = (N / 2) + 1;
		for (int i = 0; i <= N; i++)
			graph.add(new ArrayList<>());

		for (int i = 0; i < N - 1; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			graph.get(from).add(new int[] {to, 1});
			graph.get(to).add(new int[] {from, 1});
		}

		int start = bfs(1);
		boolean[] visited = new boolean[N + 1];
		dfs(start, 0, visited);
		System.out.println((result + 1) / 2);
	}

	private static int bfs(int start) {
		boolean[] visited = new boolean[N + 1];
		visited[start] = true;
		Queue<Integer> q = new ArrayDeque<>();
		q.offer(start);
		int farthestNode = 0;
		while (!q.isEmpty()) {
			Integer curr = q.poll();
			for (int[] node : graph.get(curr)) {
				int next = node[0];
				if (!visited[next]) {
					visited[next] = true;
					farthestNode = next;
					q.offer(next);
				}
			}
		}
		return farthestNode;
	}

	private static void dfs(int start, int depth, boolean[] visited) {
		result = Math.max(depth, result);
		visited[start] = true;
		for (int[] node : graph.get(start)) {
			if (!visited[node[0]]) {
				visited[node[0]] = true;
				dfs(node[0], depth + 1, visited);
				visited[node[0]] = false;
			}
		}
	}
}
