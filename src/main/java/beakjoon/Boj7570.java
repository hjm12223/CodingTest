package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj7570 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		int[] arr = new int[N];
		int[] idx = new int[N + 1];
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			idx[arr[i]] = i;
		}
		int maxLen = 1;
		int currLen = 1;
		for (int i = 2; i < idx.length; i++) {
			if (idx[i - 1] < idx[i]) {
				currLen++;
				maxLen = Math.max(currLen, maxLen);
			} else {
				currLen = 1;
			}
		}
		System.out.println("idx = " + Arrays.toString(idx));
	}
}
