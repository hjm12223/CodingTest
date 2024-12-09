package beakjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Boj2580 {
	static int[][] sudoku;
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		sudoku = new int[9][9];
		for (int i = 0; i < 9; i++) {
			sudoku[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		}
		run(0, 0);
	}

	private static void run(int row, int col) throws IOException {
		if (col == 9) {
			run(row + 1, 0);
			return;
		}
		if (row == 9) {
			for (int i = 0; i < 9; i++) {
				for (int j = 0; j < 9; j++) {
					bw.write(sudoku[i][j] + (j == 8 ? "" : " "));
				}
				bw.newLine();
			}
			bw.flush();
			bw.close();
			System.exit(0);
		}
		if (sudoku[row][col] == 0) {
			for (int i = 1; i <= 9; i++) {
				if (possible(row, col, i)) {
					sudoku[row][col] = i;
					run(row, col + 1);
				}
			}
			sudoku[row][col] = 0;
			return;
		}
		run(row, col + 1);
	}

	private static boolean possible(int row, int col, int value) {
		for (int i = 0; i < 9; i++) {
			if (sudoku[row][i] == value) return false;
		}
		for (int i = 0; i < 9; i++) {
			if (sudoku[i][col] == value) return false;
		}
		int initialRow = (row / 3) * 3;
		int initialCol = (col / 3) * 3;

		for (int i = initialRow; i < initialRow + 3; i++) {
			for (int j = initialCol; j < initialCol + 3; j++) {
				if (sudoku[i][j] == value) return false;
			}
		}
		return true;
	}

}

