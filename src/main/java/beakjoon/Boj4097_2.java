package beakjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Boj4097_2 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int N = Integer.parseInt(br.readLine());
		while (true) {
			if (N == 0) {
				bw.flush();
				return;
			}

			int[] arr = new int[N];
			for (int i = 0; i < N; i++) {
				arr[i] = Integer.parseInt(br.readLine());
			}
			long result = Integer.MIN_VALUE;
			long sum = 0;
			for (int i = arr.length - 1; i >= 0; i--) {
				sum += arr[i];
				if (sum < 0)
					sum = arr[i];
				result = Math.max(sum, result);
			}
			bw.write(result + "\n");
			N = Integer.parseInt(br.readLine());
		}
	}
}
