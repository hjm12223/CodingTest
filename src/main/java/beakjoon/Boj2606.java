package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj2606 {
	static int N;
	static List<List<Integer>> graph = new ArrayList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		for (int i = 0; i <= N; i++)
			graph.add(new ArrayList<>());

		for (int i = 0; i < M; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			graph.get(from).add(to);
			graph.get(to).add(from);

		}
		System.out.println(bfs());
	}

	private static int bfs() {
		Queue<Integer> q = new ArrayDeque<>();
		q.offer(1);
		int cnt = 0;
		boolean[] visited = new boolean[N + 1];
		visited[1] = true;
		while (!q.isEmpty()) {
			Integer curr = q.poll();
			for (int next : graph.get(curr)) {
				if (!visited[next]) {
					q.offer(next);
					visited[next] = true;
					cnt++;
				}
			}
		}
		return cnt;
	}
}
