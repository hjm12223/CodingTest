package beakjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Boj11502 {
	static List<Integer> primes;
	static int N;
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int T = Integer.parseInt(st.nextToken());
		while (T-- > 0) {
			st = new StringTokenizer(br.readLine());

			N = Integer.parseInt(st.nextToken());
			primes = new ArrayList<>();
			boolean[] isPrime = new boolean[N + 1];
			Arrays.fill(isPrime, true);
			isPrime[0] = isPrime[1] = false;
			for (int i = 2; i <= Math.sqrt(N); i++) {
				if (isPrime[i]) {
					for (int j = i * i; j <= N; j += i) {
						isPrime[j] = false;
					}
				}
			}
			for (int i = 2; i <= N; i++) {
				if (isPrime[i]) {
					primes.add(i);
				}
			}
			boolean isCheck = false;
			for (int i = 0; i < primes.size(); i++) {
				for (int j = i; j < primes.size(); j++) {
					for (int k = j; k < primes.size(); k++) {
						int sum = primes.get(i) + primes.get(j) + primes.get(k);
						if (sum == N) {
							bw.write(primes.get(i) + " " + primes.get(j) + " " + primes.get(k) + "\n");
							isCheck = true;
							break;
						}
					}
					if (isCheck) break;
				}
				if (isCheck) break;
			}
		}
		bw.flush();
		bw.close();
	}
}
