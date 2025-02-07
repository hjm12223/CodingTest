package beakjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Boj9079 {
	static char[][] arr;
	static int result;

	public static void main(String[] args) throws IOException {
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		while (T-- > 0) {
			result = Integer.MAX_VALUE;
			arr = new char[3][3];
			for (int i = 0; i < 3; i++) {
				String[] line = br.readLine().split(" ");
				for (int j = 0; j < 3; j++) {
					arr[i][j] = line[j].charAt(0);
				}
			}
			dfs(0, 0);
			if (result != Integer.MAX_VALUE) {
				bw.write(String.valueOf(result));
			} else {
				bw.write(String.valueOf(-1));
			}
			bw.newLine();
		}
		bw.flush();
		bw.close();
	}

	private static void dfs(int depth, int start) {
		if (isOne()) {
			result = Math.min(depth, result);
			return;
		}
		if (depth >= result) return;
		for (int i = start; i < 9; i++) {
			flip(i);
			dfs(depth + 1, i + 1);
			flip(i);
		}
	}

	private static void flip(int index) {
		if (index < 3) {
			for (int j = 0; j < 3; j++) {
				arr[index][j] = arr[index][j] == 'H' ? 'T' : 'H';
			}
		} else if (index < 6) {
			int col = index - 3;
			for (int i = 0; i < 3; i++) {
				arr[i][col] = (arr[i][col] == 'H') ? 'T' : 'H';
			}
		} else if (index == 6) {
			for (int i = 0; i < 3; i++) {
				arr[i][i] = (arr[i][i] == 'H') ? 'T' : 'H';
			}
		} else {
			for (int i = 0; i < 3; i++) {
				arr[i][2 - i] = (arr[i][2 - i] == 'H') ? 'T' : 'H';
			}
		}
	}

	private static boolean isOne() {
		char first = arr[0][0];
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (first != arr[i][j]) return false;
			}
		}
		return true;
	}

}
