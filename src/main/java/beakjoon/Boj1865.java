package beakjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Boj1865 {
	static List<List<Node>> graph;
	static int[] dist;
	static int INF = 100000000;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int T = Integer.parseInt(br.readLine());
		for (int t = 0; t < T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int V = Integer.parseInt(st.nextToken());
			int E = Integer.parseInt(st.nextToken());
			int W = Integer.parseInt(st.nextToken());
			graph = new ArrayList<>();
			dist = new int[V + 1];
			for (int i = 0; i <= V; i++) {
				graph.add(new ArrayList<>());
			}
			for (int i = 0; i < E; i++) {
				st = new StringTokenizer(br.readLine());
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				int cost = Integer.parseInt(st.nextToken());
				graph.get(from).add(new Node(to, cost));
				graph.get(to).add(new Node(from, cost));
			}
			for (int i = 0; i < W; i++) {
				st = new StringTokenizer(br.readLine());
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				int cost = Integer.parseInt(st.nextToken());
				graph.get(from).add(new Node(to, -cost));
			}
			boolean isCycle = false;
			for (int i = 1; i <= V; i++) {
				if (bellmanFord(i, V)) {
					isCycle = true;
					break;
				}
			}
			if (isCycle) {
				bw.write("YES");
				bw.newLine();
			} else {
				bw.write("NO");
				bw.newLine();
			}
		}
		bw.flush();
		bw.close();
	}

	private static boolean bellmanFord(int start, int V) {
		Arrays.fill(dist, INF);
		dist[start] = 0;
		boolean isCycle = false;
		for (int i = 1; i < V; i++) {
			isCycle = false;
			for (int j = 1; j <= V; j++) {
				for (Node node : graph.get(j)) {
					if (dist[j] != INF && dist[node.n] > dist[j] + node.c) {
						dist[node.n] = dist[j] + node.c;
						isCycle = true;
					}
				}
			}
			if (!isCycle) {
				break;
			}
		}
		for (int i = 1; i <= V; i++) {
			for (Node node : graph.get(i)) {
				if (dist[i] != INF && dist[node.n] > dist[i] + node.c) {
					return true;
				}
			}
		}
		return false;
	}

	private static class Node {
		int n;
		int c;

		public Node(int n, int c) {
			this.n = n;
			this.c = c;
		}
	}
}

