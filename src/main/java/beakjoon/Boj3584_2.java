package beakjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Boj3584_2 {
	static int[] parents;
	static int[] depth;
	static List<List<Integer>> graph;
	static boolean[] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		while (T-- > 0) {
			int N = Integer.parseInt(br.readLine());
			graph = new ArrayList<>();
			for (int i = 0; i <= N; i++)
				graph.add(new ArrayList<>());
			parents = new int[N + 1];
			depth = new int[N + 1];
			visited = new boolean[N + 1];
			boolean[] isChild = new boolean[N + 1];

			for (int i = 1; i <= N - 1; i++) {
				st = new StringTokenizer(br.readLine());
				int parent = Integer.parseInt(st.nextToken());
				int child = Integer.parseInt(st.nextToken());
				graph.get(parent).add(child);
				graph.get(child).add(parent);
				isChild[child] = true;
			}
			int root = 0;
			for (int i = 1; i <= N; i++) {
				if (!isChild[i]) {
					root = i;
					break;
				}

			}
			dfs(root, 0);
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			bw.write(lca(a, b) + "\n");
		}
		bw.flush();
		bw.close();
	}

	private static String lca(int a, int b) {
		while (depth[a] > depth[b]) {
			a = parents[a];
		}
		while (depth[b] > depth[a]) {
			b = parents[b];
		}
		while (a != b) {
			a = parents[a];
			b = parents[b];
		}
		return String.valueOf(a);
	}

	private static void dfs(int node, int parent) {
		visited[node] = true;
		parents[node] = parent;
		depth[node] = depth[parent] + 1;
		for (Integer next : graph.get(node)) {
			if (!visited[next]) {
				dfs(next, node);
			}
		}
	}
}
