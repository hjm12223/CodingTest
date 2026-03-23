package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj1124 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int result = 0;

		boolean[] isPrime = new boolean[100_001];

		Arrays.fill(isPrime, true);
		isPrime[0] = isPrime[1] = false;
		for (int i = 1; i * i <= 100_000; i++) {
			if (isPrime[i]) {
				for (int j = i * i; j <= 100_000; j += i) {
					isPrime[j] = false;
				}
			}
		}
		for (int i = N; i <= M; i++) {
			int cnt = 0;
			int temp = i;
			for (int k = 2; k * k <= temp; k++) {
				while (temp % k == 0) {
					temp /= k;
					cnt++;
				}
			}
			if (temp > 1) cnt++;
			if (isPrime[cnt]) result++;
		}
		System.out.println(result);
	}
}
