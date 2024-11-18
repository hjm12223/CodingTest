package beakjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Boj2630 {
	static int whiteResult = 0;
	static int blueResult = 0;
	static int[][] arr;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int N = Integer.parseInt(br.readLine());
		arr = new int[N][N];
		// 1사 분면을 체크 또 갈라 또 체크 또 갈라 또 체크 == > 재귀
		/*
		재귀식을 어떻게 세워야하냐?
		만약 해당 부분이 블루 즉 1 혹은 0으로 꽉 차있을경우
		1일경우에는 blueResult ++;
		0일경우에는 whiteResult++;
		여기서 체크했는데 결과가 나왔다

		 */

		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		cal(arr, N, 0, 0);
		bw.write(whiteResult + "\n" + blueResult + "\n");
		bw.flush();
		bw.close();
	}

	private static void cal(int[][] arr, int n, int x, int y) {
		int color = arr[x][y];
		boolean isSameColor = true;
		for (int i = x; i < x + n; i++) {
			for (int j = y; j < y + n; j++) {
				if (color != arr[i][j]) {
					isSameColor = false;
					break;
				}
			}
			if (!isSameColor) break;
		}
		if (isSameColor) {
			if (color == 1) {
				blueResult++;
			} else {
				whiteResult++;
			}
		} else {
			int newSize = n / 2;
			cal(arr, newSize, x, y);
			cal(arr, newSize, x + newSize, y);
			cal(arr, newSize, x, y + newSize);
			cal(arr, newSize, x + newSize, y + newSize);
		}
	}

}

	/*
	 1. 재귀를 사용해서 풀어야 하는 형식의 문제이다
	 */
