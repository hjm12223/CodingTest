package beakjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Boj1717 {
	static int[] parent;
	static int[] rank;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		parent = new int[N + 1];
		rank = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			parent[i] = i;
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int command = Integer.parseInt(st.nextToken());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			if (command == 0) {
				union(from, to);
			} else {
				if (find(from) == find(to)) {
					bw.write("YES\n");
				} else {
					bw.write("NO\n");
				}
			}

		}
		bw.flush();
		bw.close();
	}

	private static int find(int a) {
		if (a != parent[a]) {
			return parent[a] = find(parent[a]);
		}
		return a;
	}

	private static void union(int a, int b) {
		int rootA = find(a);
		int rootB = find(b);
		if (rootA == rootB) return;

		if (rank[rootA] < rank[rootB]) {
			parent[rootA] = rootB;
		} else {
			parent[rootB] = rootA;
			if (rank[rootA] == rank[rootB]) {
				rank[rootA]++;
			}
		}
	}
}
