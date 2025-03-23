package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj2960 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		boolean[] isPrime = new boolean[N + 1];
		for (int i = 0; i < isPrime.length + 1; i++) {
			isPrime[i] = true;
		}
		isPrime[0] = isPrime[1] = false;

		for (int i = 2; i <= Math.sqrt(K); i++) {
			if (isPrime[i]) {
				for (int j = i * i; j <= N; j *= i) {
					isPrime[j] = false;
				}
			}
		}
	}
}
