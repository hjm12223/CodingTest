package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj19535 {
	static List<List<Integer>> graph = new ArrayList<>();
	static int dCount, gCount = 0;
	static boolean[] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		for (int i = 0; i <= N; i++)
			graph.add(new ArrayList<>());

		for (int i = 0; i < N - 1; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			graph.get(from).add(to);
			graph.get(to).add(from);
		}
		for (int i = 1; i <= N; i++) {
			visited = new boolean[N + 1];
			bfs(i, 1);
		}
		if (dCount > gCount * 3) {
			System.out.println("D");
		} else if (dCount < gCount * 3) {
			System.out.println("G");
		} else if (dCount == gCount * 3) {
			System.out.println("DUDUDUNGA");
		}
	}

	private static void bfs(int start, int depth) {
		Queue<int[]> q = new ArrayDeque<>();
		q.offer(new int[] {start, depth});
		visited[start] = true;
		while (!q.isEmpty()) {
			int[] curr = q.poll();
			if (curr[1] == 4) {
				dCount++;
				System.out.println(dCount);
				return;
			}
			if (graph.get(curr[0]).size() >= 3) {
				gCount++;
				return;
			}
			for (int next : graph.get(curr[0])) {
				if (!visited[next]) {
					visited[next] = true;
					q.offer(new int[] {next, curr[1] + 1});
				}
			}
		}
	}
}
