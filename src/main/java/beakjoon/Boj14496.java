package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj14496 {
	static List<List<Integer>> graph = new ArrayList<>();
	static boolean[] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int from = Integer.parseInt(st.nextToken());
		int to = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		visited = new boolean[N + 1];
		for (int i = 0; i <= N; i++) {
			graph.add(new ArrayList<>());
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int g = Integer.parseInt(st.nextToken());
			graph.get(s).add(g);
			graph.get(g).add(s);
		}
		bfs(from, to);
	}

	private static void bfs(int from, int to) {
		Queue<int[]> q = new ArrayDeque<>();
		q.offer(new int[] {from, 0});
		visited[from] = true;

		while (!q.isEmpty()) {
			int[] curr = q.poll();
			if (curr[0] == to) {
				System.out.println(curr[1]);
				return;
			}
			for (int next : graph.get(curr[0])) {
				if (!visited[next]) {
					visited[next] = true;
					q.offer(new int[] {next, curr[1] + 1});
				}
			}
		}
		System.out.println(-1);
	}
}
