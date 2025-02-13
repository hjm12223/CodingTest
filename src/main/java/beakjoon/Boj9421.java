package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Boj9421 {
	static final int MAX = 1000000;
	static boolean[] isPrime;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int cnt = 2;

		List<Integer> result = new ArrayList<>();
		isPrime = new boolean[MAX + 1];
		Arrays.fill(isPrime, true);
		for (int i = 2; i * i <= MAX; i++) {
			if (isPrime[i]) {
				for (int j = i * i; j <= MAX; j += i) {
					isPrime[j] = false;
				}
			}
		}
		while (cnt++ < N) {
			Set<Integer> set = new TreeSet<>();
			if (isPrime[cnt]) {
				System.out.println(cnt);
				if (canPrime(cnt, set)) result.add(cnt);
			}
		}
		for (int i = 0; i < result.size(); i++)
			System.out.println(result.get(i));
	}

	private static boolean canPrime(int cnt, Set<Integer> set) {
		if (cnt == 1) return true;
		if (set.contains(cnt)) return false;
		set.add(cnt);
		if (cnt < 10) {
			return canPrime(cnt * cnt, set);
		} else {
			String value = String.valueOf(cnt);
			int result = 0;
			for (int i = 0; i < value.length(); i++) {
				int val = Integer.parseInt(String.valueOf(value.charAt(i)));
				result += val * val;
			}
			return canPrime(result, set);
		}
	}
}



