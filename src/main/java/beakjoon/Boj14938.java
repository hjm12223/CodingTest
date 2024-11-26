package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj14938 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken()); // 노드
		int M = Integer.parseInt(st.nextToken()); // 수색범위
		int R = Integer.parseInt(st.nextToken()); // 간선

		long[][] graph = new long[N + 1][N + 1];
		long[] item = new long[N + 1];

		for (int i = 0; i <= N; i++) {
			Arrays.fill(graph[i], Integer.MAX_VALUE);
			graph[i][i] = 0;
		}

		st = new StringTokenizer(br.readLine());

		for (int i = 1; i <= N; i++) {
			item[i] = Integer.parseInt(st.nextToken());
		}
		for (int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			graph[from][to] = Math.min(graph[from][to], cost);
			graph[to][from] = Math.min(graph[to][from], cost);
		}

		for (int k = 1; k <= N; k++) {
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= N; j++) {
					if (graph[i][k] != Integer.MAX_VALUE && graph[k][j] != Integer.MAX_VALUE) {
						graph[i][j] = Math.min(graph[i][j], graph[i][k] + graph[k][j]);
					}
				}
			}
		}
		long answer = 0; // 정답
		for (int i = 1; i <= N; i++) {
			long temp = 0; // temp
			for (int j = 1; j <= N; j++) {
				if (graph[i][j] <= M) {
					temp += item[j];
				}
				answer = Math.max(answer, temp);
			}
		}
		System.out.println(answer);
	}
}
