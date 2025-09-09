package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Boj18808_2 {
	static int N, M, K;
	static List<int[][]> list;
	static int[][] arr;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		arr = new int[N][M];
		list = new ArrayList<>();
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int R = Integer.parseInt(st.nextToken());
			int C = Integer.parseInt(st.nextToken());
			int[][] sticker = new int[R][C];
			for (int r = 0; r < R; r++) {
				st = new StringTokenizer(br.readLine());
				for (int c = 0; c < R; c++) {
					sticker[r][c] = Integer.parseInt(st.nextToken());
				}
			}
			list.add(sticker);
		}
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

	private static boolean isCan(int sx, int sy, int[][] sticker) {
		for (int i = 0; i < sticker.length; i++) {
			for (int j = 0; j < sticker[0].length; j++) {
				if (sticker[i][j] == 1 && arr[i + sx][j + sy] == 1) {
					return false;
				}
			}
		}
		return true;
	}

	private static int[] run(int sx, int sy, int[][] sticker, int value) {
		int sticker_x = 0;
		for (int i = sx; i <= N - sticker.length; i++) {
			int sticker_y = 0;
			for (int j = sy; j <= M - sticker[i].length; j++) {
				arr[i][j] = (value == 0) ? Math.abs(sticker[sticker_x][sticker_y] - 1) : sticker[sticker_x][sticker_y];
				sticker_y++;
			}
			sticker_x++;
		}
		System.out.println("arr = " + Arrays.deepToString(arr));
		return new int[] {};
	}
}
