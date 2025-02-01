package beakjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj2470 {
	static Long[] arr;
	static long N;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Long.parseLong(st.nextToken());
		arr = new Long[(int)N];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Long.parseLong(st.nextToken());
		}
		long minSum = Long.MAX_VALUE;
		Arrays.sort(arr);
		long ansL = 0;
		long ansR = 0;
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int left = 0;
		int right = arr.length - 1;
		while (left < right) {
			long value = arr[left] + arr[right];
			if (minSum > Math.abs(value)) {
				minSum = Math.abs(value);
				ansL = arr[left];
				ansR = arr[right];
			}
			if (minSum == 0) break;
			else if (value < 0) {
				left++;
			} else {
				right--;
			}
		}
		bw.write(ansL + " " + ansR);
		bw.flush();
		bw.close();
	}
}
