package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Boj13023 {

	static List<List<Integer>> graph = new ArrayList<>();
	static boolean found = false;
	static int N, M;
	static boolean[] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		visited = new boolean[N];
		for (int i = 0; i < N; i++)
			graph.add(new ArrayList<>());

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			graph.get(from).add(to);
			graph.get(to).add(from);
		}
		
		for (int i = 0; i < N; i++) {
			dfs(i, 0);
			if (found) break;
		}
		System.out.println(found ? 1 : 0);

	}

	private static void dfs(int node, int depth) {
		if (depth == 4) {
			found = true;
			return;
		}
		visited[node] = true;
		for (int next : graph.get(node)) {
			if (!visited[next]) {
				dfs(next, depth + 1);
				if (found) break;
			}
		}
		visited[node] = false;
	}
}
