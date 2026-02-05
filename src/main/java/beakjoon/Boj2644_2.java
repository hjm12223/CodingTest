package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj2644_2 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		int start = Integer.parseInt(st.nextToken());
		int target = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(br.readLine());
		boolean[] visited = new boolean[N + 1];

		Queue<int[]> q = new ArrayDeque<>();
		List<List<Integer>> graph = new ArrayList<>();

		for (int i = 0; i <= N; i++)
			graph.add(new ArrayList<>());

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			graph.get(from).add(to);
			graph.get(to).add(from);
		}

		q.offer(new int[] {start, 0});
		visited[start] = true;
		while (!q.isEmpty()) {
			int[] curr = q.poll();
			if (curr[0] == target) {
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
