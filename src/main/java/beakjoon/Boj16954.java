package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Boj16954 {
	static final int N = 8;
	static char[][] arr;
	static int[][] move = new int[][] {{0, 1}, {1, 0}, {0, -1}, {-1, 0}, {1, 1}, {-1, 1}};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		arr = new char[N][N];
		for (int i = 0; i < N; i++) {
			arr[i] = br.readLine().toCharArray();
		}
	}
}
