package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj1018 {
	static char[][] arr;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		arr = new char[N][M];

		for (int i = 0; i < N; i++) {
			arr[i] = br.readLine().toCharArray();
		}
		int result = Integer.MAX_VALUE;

		for (int i = 0; i <= N - 8; i++) {
			for (int j = 0; j <= M - 8; j++) {
				result = Math.min(result, check(i, j));
			}
		}
		System.out.println(result);
	}

	private static int check(int x, int y) {
		int blackCnt = 0;
		int whiteCnt = 0;

		for (int i = x; i < x + 8; i++) {
			for (int j = y; j < y + 8; j++) {
				if ((i + j) % 2 == 0) {
					if (arr[i][j] != 'W') whiteCnt++;
					if (arr[i][j] != 'B') blackCnt++;
				} else {
					if (arr[i][j] != 'W') blackCnt++;
					if (arr[i][j] != 'B') whiteCnt++;
				}
			}
		}
		return Math.min(blackCnt, whiteCnt);
	}
}
