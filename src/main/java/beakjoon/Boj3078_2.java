package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj3078_2 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		String[] arr = new String[N];
		for (int i = 0; i < N; i++) {
			arr[i] = br.readLine();
		}
		int[] count = new int[21];
		long answer = 0;
		for (int i = 0; i < N; i++) {
			int len = arr[i].length();
			if (i > K) {
				int out = arr[i - 1 - K].length();
				count[out]--;
			}
			answer += count[len];
			count[len]++;
		}
		System.out.println(answer);
	}

}
