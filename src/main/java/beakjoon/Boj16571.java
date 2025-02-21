package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj16571 {
	static final int N = 3;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[][] arr = new int[N][N];
		int m = 0;
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		int result = back(arr, 1);
		System.out.println(result);
	}

	private static int back(int[][] arr, int turn) {
		if (isEnd(arr)) {
			return func(arr);
		}
		int result = -1;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (arr[i][j] == 0) {
					arr[i][j] = turn;
					int res = back(arr, 3 - turn);
					arr[i][j] = 0;
					result = Math.max(res, result);
					if (result == 1) return result;
				}
			}
		}
		return result;
	}

	private static int func(int[][] arr) {
		for (int i = 0; i < N; i++) {
			if (arr[i][0] != 0 && arr[i][0] == arr[i][1] && arr[i][1] == arr[i][2]) {
				return (arr[i][0] == 1) ? 1 : -1;
			}
			if (arr[0][i] != 0 && arr[0][i] == arr[1][i] && arr[1][i] == arr[2][i]) {
				return (arr[0][i] == 1) ? 1 : -1;
			}
		}
		if (arr[0][0] != 0 && arr[0][0] == arr[1][1] && arr[1][1] == arr[2][2]) {
			return (arr[0][0] == 1) ? 1 : -1;
		}
		if (arr[0][2] != 0 && arr[0][2] == arr[1][1] && arr[1][1] == arr[2][0]) {
			return (arr[0][2] == 1) ? 1 : -1;
		}
		return 0;
	}

	private static boolean isEnd(int[][] arr) {
		for (int i = 0; i < N; i++) {
			// 가로 확인
			if (arr[i][0] != 0 && arr[i][0] == arr[i][1] && arr[i][1] == arr[i][2]) {
				return true;
			}
			// 세로 확인
			if (arr[0][i] != 0 && arr[0][i] == arr[1][i] && arr[1][i] == arr[2][i]) {
				return true;
			}
		}
		// 대각선 확인
		if (arr[0][0] != 0 && arr[0][0] == arr[1][1] && arr[1][1] == arr[2][2]) {
			return true;
		}
		if (arr[0][2] != 0 && arr[0][2] == arr[1][1] && arr[1][1] == arr[2][0]) {
			return true;
		}

		return false;
	}
}
