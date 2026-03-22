package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj1613 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		boolean[][] dist = new boolean[N + 1][N + 1];

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			dist[a][b] = true;
		}
		StringBuilder sb = new StringBuilder();
		for (int k = 1; k <= N; k++) {
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= N; j++) {
					if (dist[i][k] && dist[k][j]) {
						dist[i][j] = true;
					}
				}
			}
		}
		int S = Integer.parseInt(br.readLine());
		for (int i = 0; i < S; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			if (dist[a][b]) sb.append("-1\n");
			else if (dist[b][a]) sb.append("1\n");
			else sb.append("0\n");
		}
		System.out.println(sb);
	}
}


/*
N, K가 주어지며 K개에 줄에는 전후 사건을 알 수 있는 사건의 번호가 주어진다
N = 400
N^3 = 64,000,000 플로이드 워셜
 */
