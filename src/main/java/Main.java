import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		System.out.println(gcd(n, m) + "최대 공약수 ");
		System.out.println(lcm(n, m) + "최소 공배수");
		System.out.println(factor(n));
		System.out.println(factorWithDp(n));
	}

	private static int factor(int n) {
		if (n == 0) {
			return 1;
		} else {
			return n * factor(n - 1);
		}
	}

	private static int factorWithDp(int n) {
		if (n == 0) {
			return 1;
		} else {
			int[] dp = new int[n + 1];
			dp[0] = 1;
			for (int i = 1; i <= n; i++) {
				dp[i] = dp[i - 1] * i;
			}
			return dp[n];
		}
	}

	private static int gcd(int n, int m) {
		while (m != 0) {
			int r = n % m;
			n = m;
			m = r;
		}
		return n;
	}

	private static int lcm(int n, int m) {
		return n * (m / gcd(n, m));
	}
}