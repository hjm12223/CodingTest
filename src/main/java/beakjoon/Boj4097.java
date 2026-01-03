package beakjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Boj4097 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		while (true) {
			String line = br.readLine();
			int N = Integer.parseInt(line);
			if (N == 0) break;
			int[] arr = new int[N];
			for (int i = 0; i < N; i++)
				arr[i] = Integer.parseInt(br.readLine());
			int result = Integer.MIN_VALUE;
			int sum = 0;
			for (int i = N - 1; i >= 0; i--) {
				sum = Math.max(arr[i] + sum, arr[i]);
				result = Math.max(sum, result);
			}
			if (result == Integer.MIN_VALUE) return;
			bw.write(result + "\n");
		}
		bw.flush();
		bw.close();
	}
}

/*
	[1,-2,1000]
 */