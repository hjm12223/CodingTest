package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj25331 {
	static int N = 7;
	static int[][] arr;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		arr = new int[N][N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		int minBalls = Integer.MAX_VALUE;
		int ball = Integer.parseInt(br.readLine()); // 떨어트릴 공의 번호
		for (int c = 0; c < 7; c++) {
			int[][] tempBoard = copyBoard(arr);
			dropBall(tempBoard, c, ball);
			processBoard(tempBoard);
			minBalls = Math.min(minBalls, countRemaining(tempBoard));
		}
		System.out.println(minBalls);
	}

	private static int countRemaining(int[][] board) {
		int cnt = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (board[i][j] != 0) cnt++;
			}
		}
		return cnt;
	}

	private static void processBoard(int[][] board) {
		while (true) {
			boolean isChange = false;
			boolean[][] isDelete = new boolean[N][N];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (board[i][j] == 0) continue;
					int rowCount = getRow(board, i, j);
					int colCount = getCol(board, i, j);
					if (board[i][j] == rowCount || board[i][j] == colCount) {
						isChange = true;
						isDelete[i][j] = true;
					}
				}
			}
			if (!isChange) break;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (isDelete[i][j]) board[i][j] = 0;
				}
			}
			applyGravity(board);
		}
	}

	private static void applyGravity(int[][] board) {
		for (int j = 0; j < N; j++) {
			for (int i = 6; i >= 0; i--) {
				if (board[i][j] == 0) {
					for (int k = i - 1; k >= 0; k--) {
						if (board[k][j] != 0) {
							board[i][j] = board[k][j];
							board[k][j] = 0;
							break;
						}
					}
				}
			}
		}
	}

	private static int getRow(int[][] board, int c, int r) {
		int cnt = 0;
		for (int i = r + 1; i < N && board[c][i] != 0; i++) cnt++;
		for (int i = r; i >= 0 && board[c][i] != 0; i--) cnt++;
		return cnt;
	}

	private static int getCol(int[][] board, int c, int r) {
		int cnt = 0;
		for (int i = c + 1; i < N && board[i][r] != 0; i++) cnt++;
		for (int i = c; i >= 0 && board[i][r] != 0; i--) cnt++;
		return cnt;
	}

	private static void dropBall(int[][] board, int c, int ball) {
		for (int i = 6; i >= 0; i--) {
			if (board[i][c] == 0) {
				board[i][c] = ball;
				break;
			}
		}
	}

	private static int[][] copyBoard(int[][] arr) {
		int[][] copy = new int[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				copy[i][j] = arr[i][j];
			}
		}
		return copy;
	}
}

	/*
	1. N = 7^7 * 9^2  = 6천6백만 완탐가능
	2. 1열부터 ~7열까지 공을 떨어트림

	1. 가로 그룹과 세로 그룹을 정한다
	2.

	 */
