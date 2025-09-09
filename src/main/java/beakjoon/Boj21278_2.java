package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj21278_2 {
	static final int INF = (1 << 20);

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[][] arr = new int[N + 1][N + 1];
		for (int i = 0; i <= N; i++)
			Arrays.fill(arr[i], INF);
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			arr[from][to] = 1;
			arr[to][from] = 1;
		}
		for (int k = 1; k <= N; k++) {
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= N; j++) {
					if (arr[i][j] > arr[i][k] + arr[k][j]) {
						arr[i][j] = arr[i][k] + arr[k][j];
					}
				}
			}
		}
		System.out.println(Arrays.deepToString(arr));
		int res = Integer.MAX_VALUE;
		int a = 0, b = 0;
		for (int i = 1; i <= N; i++) {
			for (int j = i + 1; j <= N; j++) {
				int temp = 0;
				for (int k = 1; k <= N; k++) {
					if (i == k || j == k) continue;
					temp += Math.min(arr[i][k], arr[j][k]) * 2;
				}
				if (res > temp) {
					res = temp;
					a = i;
					b = j;
				}
			}
		}
		System.out.println(a + " " + b + " " + res);
	}
}
