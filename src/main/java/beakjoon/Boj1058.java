package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj1058 {
	static int INF = 987654321;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int[][] graph = new int[N][N];

		for (int from = 0; from < N; from++) {
			String str = br.readLine();
			for (int to = 0; to < N; to++) {
				if (from == to) graph[from][to] = 0;
				else if (str.charAt(to) == 'Y') graph[from][to] = 1;
				else graph[from][to] = INF;
			}
		}

		for (int k = 0; k < N; k++) {
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					graph[i][j] = Math.min(graph[i][k] + graph[k][j], graph[i][j]);
				}
			}
		}

		int maxValue = Integer.MIN_VALUE;
		for (int i = 0; i < N; i++) {
			int cnt = 0;
			for (int j = 0; j < N; j++) {
				if (graph[i][j] <= 2 && i != j) {
					cnt++;
				}
			}
			maxValue = Math.max(cnt, maxValue);
		}
		System.out.println(maxValue);
	}
}
