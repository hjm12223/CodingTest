package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj2660 {
	static final int INF = (1 << 30);

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());

		int[][] graph = new int[N + 1][N + 1];
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				if (i != j) graph[i][j] = INF;
			}
		}
		while (true) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			if (from == -1 && to == -1) break;
			graph[from][to] = 1;
			graph[to][from] = 1;
		}
		int[] cnt = new int[N + 1];
		for (int k = 1; k <= N; k++) {
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= N; j++) {
					if (i == j) continue;
					if (graph[i][k] != INF && graph[k][j] != INF)
						graph[i][j] = Math.min(graph[i][k] + graph[k][j], graph[i][j]);
				}
			}
		}

		int minScore = INF;

		for (int i = 1; i <= N; i++) {
			int max = 0;
			for (int j = 1; j <= N; j++) {
				max = Math.max(max, graph[i][j]);
			}
			cnt[i] = max;
			minScore = Math.min(minScore, max);
		}

		int count = 0;

		for (int i = 1; i <= N; i++) {
			if (cnt[i] == minScore) count++;
		}

		System.out.println(minScore + " " + count);

		for (int i = 1; i <= N; i++) {
			if (cnt[i] == minScore) System.out.print(i + " ");
		}
	}
}
