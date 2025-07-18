package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj18808 {
	static int N, M;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		int stickers = Integer.parseInt(st.nextToken());
		int[][] arr = new int[N][M];
		while (stickers-- > 0) {
			st = new StringTokenizer(br.readLine());
			int R = Integer.parseInt(st.nextToken());
			int C = Integer.parseInt(st.nextToken());
			int[][] sticker = new int[R][C];
			for (int i = 0; i < R; i++) {
				int[] array = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
				for (int j = 0; j < C; j++) {
					sticker[i][j] = array[j];
				}
			}
			for (int d = 0; d < 4; d++) {
				boolean attach = false;
				for (int i = 0; i <= N - sticker.length && !attach; i++) {
					for (int j = 0; j <= M - sticker[0].length && !attach; j++) {
						if (canAttach(i, j, arr, sticker)) {
							attach = true;
							doAttach(i, j, arr, sticker);
						}
					}
				}
				if (attach) break;
				sticker = rotate(sticker);
			}
		}
		int cnt = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (arr[i][j] == 1) {
					cnt++;
				}
			}
		}
		System.out.println(cnt);
	}

	private static int[][] rotate(int[][] sticker) {
		int R = sticker.length;
		int C = sticker[0].length;
		int[][] rotatedSticker = new int[C][R];
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				rotatedSticker[j][R - 1 - i] = sticker[i][j];
			}
		}
		return rotatedSticker;
	}

	private static void doAttach(int x, int y, int[][] arr, int[][] sticker) {
		for (int i = 0; i < sticker.length; i++) {
			for (int j = 0; j < sticker[0].length; j++) {
				if (sticker[i][j] == 1) {
					arr[i + x][j + y] = 1;
				}
			}
		}
	}

	private static boolean canAttach(int x, int y, int[][] arr, int[][] sticker) {
		for (int i = 0; i < sticker.length; i++) {
			for (int j = 0; j < sticker[0].length; j++) {
				if (sticker[i][j] == 1 && arr[i + x][j + y] == 1) {
					return false;
				}
			}
		}
		return true;
	}
}