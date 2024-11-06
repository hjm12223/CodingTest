package programmers.kakao.Level3;

public class Mountain {
	public static void main(String[] args) {
		// Test cases
		System.out.println("Test case 1: " + solution(4, new int[] {1, 1, 0, 1}));  // Expected: 149
		System.out.println("Test case 2: " + solution(2, new int[] {0, 1}));        // Expected: 11
		System.out.println("Test case 3: " + solution(10, new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0})); // Expected: 7704
	}

	public static int solution(int n, int[] tops) {
		final int MOD = 10007;

		int[] a = new int[n + 1];
		int[] b = new int[n + 1];

		a[0] = 0;
		b[0] = 1;

		// Fill DP arrays
		for (int k = 1; k <= n; k++) {
			boolean hasTop = tops[k - 1] == 1;

			a[k] = (a[k - 1] + b[k - 1]) % MOD;

			if (hasTop) {
				b[k] = ((2 * a[k - 1]) % MOD + (3 * b[k - 1]) % MOD) % MOD;
			} else {
				b[k] = (a[k - 1] + (2 * b[k - 1]) % MOD) % MOD;
			}
		}

		return (a[n] + b[n]) % MOD;
	}
}