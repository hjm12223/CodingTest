package beakjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Boj3584 {
	static List<List<Integer>> graph;
	static boolean[] isVisited;
	static int N;
	static int[] parents;
	static int[] depth;
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int t = 0; t < T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			graph = new ArrayList<>();
			depth = new int[N + 1];
			parents = new int[N + 1];
			isVisited = new boolean[N + 1];
			boolean[] isChild = new boolean[N + 1];

			for (int i = 0; i <= N; i++) {
				graph.add(new ArrayList<>());
			}
			for (int i = 0; i < N - 1; i++) {
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
			lca(a, b);
		}
		bw.flush();
		bw.close();
	}

	private static void lca(int a, int b) throws IOException {
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
		bw.write(String.valueOf(a));
		bw.newLine();
	}

	private static void dfs(int node, int parent) {
		isVisited[node] = true;
		parents[node] = parent;
		depth[node] = depth[parent] + 1;
		for (int child : graph.get(node)) {
			if (!isVisited[child]) {
				dfs(child, node);
			}
		}
	}
}
