package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj6118 {
	static List<List<Integer>> graph = new ArrayList<>();
	static boolean[] visited;
	static int[] cost, nodes;
	static int depth;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		cost = new int[N + 1];
		nodes = new int[N + 1];
		Arrays.fill(nodes, Integer.MAX_VALUE);
		visited = new boolean[N + 1];
		for (int i = 0; i <= N; i++)
			graph.add(new ArrayList<>());

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			graph.get(from).add(to);
			graph.get(to).add(from);
		}

		bfs(1);

		System.out.println(nodes[depth] + " " + depth + " " + cost[depth]);
	}

	private static void bfs(int start) {
		visited[start] = true;

		Queue<int[]> q = new ArrayDeque<>();
		q.offer(new int[] {start, 0});

		while (!q.isEmpty()) {
			int[] curr = q.poll();
			for (int next : graph.get(curr[0])) {
				if (!visited[next]) {
					visited[next] = true;
					int nxCost = curr[1] + 1;
					cost[nxCost]++;
					nodes[nxCost] = Math.min(next, nodes[nxCost]);
					depth = Math.max(nxCost, depth);
					q.offer(new int[] {next, nxCost});
				}
			}
		}
	}
}
