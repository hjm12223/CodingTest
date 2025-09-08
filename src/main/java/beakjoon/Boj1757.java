package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj1757 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[][] dp = new int[M][N];
		int[] moves = new int[N + 1];
		for (int i = 1; i <= N; i++)
			moves[i] = Integer.parseInt(br.readLine());
		for (int i = 0; i <= M; i++) {
			for (int j = 1; j <= N; j++) {

			}
		}
	}
}