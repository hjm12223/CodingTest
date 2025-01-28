package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Boj14939 {
	static final int N = 10;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		char[][] arr = new char[N][N];

		for (int i = 0; i < N; i++) {
			arr[i] = br.readLine().toCharArray();
		}
		
		System.out.println(Arrays.deepToString(arr));
	}
}
