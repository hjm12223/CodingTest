package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Boj10703 {
	static int N, M;
	static char[][] arr;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		arr = new char[N][M];

		for (int i = 0; i < N; i++) {
			arr[i] = br.readLine().toCharArray();
		}

		List<int[]> meteor = new ArrayList<>();

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (arr[i][j] == 'X') {
					meteor.add(new int[] {i, j});
				}
			}
		}
		int minDist = Integer.MAX_VALUE;
		for (int j = 0; j < M; j++) {
			int underMeteor = -1;
			for (int i = N - 1; i >= 0; i--) {
				if (arr[i][j] == 'X') {
					underMeteor = i;
					break;
				}
			}
			if (underMeteor == -1) continue;
			int ground = N;
			for (int i = underMeteor; i < N; i++) {
				if (arr[i][j] == '#') {
					ground = i;
					break;
				}
			}
			minDist = Math.min(minDist, ground - underMeteor - 1);
		}
		for (int[] met : meteor) {
			arr[met[0]][met[1]] = '.';
		}
		for (int[] met : meteor) {
			arr[met[0] + minDist][met[1]] = 'X';
		}
		StringBuilder sb = new StringBuilder();
		for (char[] arr : arr) {
			for (char value : arr) {
				sb.append(value);
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}
}
