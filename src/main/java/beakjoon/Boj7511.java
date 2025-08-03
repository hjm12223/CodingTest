package beakjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Boj7511 {
	static int[] parent;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int T = Integer.parseInt(st.nextToken());
		for (int t = 1; t <= T; t++) {
			int N = Integer.parseInt(br.readLine());
			int M = Integer.parseInt(br.readLine());
			parent = new int[N + 1];
			for (int i = 1; i <= N; i++)
				parent[i] = i;

			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				union(from, to);
			}
			int K = Integer.parseInt(br.readLine());
			bw.write("Scenario " + t + ":\n");
			for (int k = 0; k < K; k++) {
				st = new StringTokenizer(br.readLine());
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				if (find(from) == find(to)) {
					bw.write(1 + "\n");
				} else {
					bw.write(0 + "\n");
				}
			}
			bw.newLine();
		}
		bw.flush();
		bw.close();

	}

	private static void union(int from, int to) {
		int a_parent = find(from);
		int b_parent = find(to);
		if (a_parent > b_parent) {
			parent[b_parent] = parent[a_parent];
		} else {
			parent[a_parent] = parent[b_parent];
		}
	}

	private static int find(int a) {
		if (a != parent[a]) {
			return parent[a] = find(parent[a]);
		}
		return a;
	}
}
