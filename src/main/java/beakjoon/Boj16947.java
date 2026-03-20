package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj16947 {
	static int[] degree;
	static int N;
	static List<List<Integer>> graph = new ArrayList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		degree = new int[N + 1];
		for (int i = 0; i <= N; i++)
			graph.add(new ArrayList<>());
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());

			graph.get(from).add(to);
			graph.get(to).add(from);
		}
		Queue<Integer> q = new ArrayDeque<>();
		for (int i = 1; i <= N; i++) {
			degree[i] = graph.get(i).size();
		}
		for (int i = 1; i <= N; i++) {
			if (graph.get(i).size() == 1) {
				q.offer(i);
			}
		}
		while (!q.isEmpty()) {
			int curr = q.poll();
			degree[curr] = 0;
			for (int next : graph.get(curr)) {
				degree[next]--;
				if (degree[next] == 1) {
					q.offer(next);
				}
			}
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 1; i <= N; i++) {
			if (degree[i] > 0) {
				sb.append(0).append(" ");
			} else {
				sb.append(bfs(i)).append(" ");
			}
		}
		System.out.println(sb);
	}

	private static int bfs(int start) {
		Queue<int[]> q = new ArrayDeque<>();
		boolean[] visited = new boolean[N + 1];
		visited[start] = true;
		q.offer(new int[] {start, 1});
		while (!q.isEmpty()) {
			int[] node = q.poll();
			for (int next : graph.get(node[0])) {
				if (degree[next] > 0) return node[1];
				if (!visited[next]) {
					visited[next] = true;
					q.offer(new int[] {next, node[1] + 1});
				}
			}
		}
		return 0;
	}
}
