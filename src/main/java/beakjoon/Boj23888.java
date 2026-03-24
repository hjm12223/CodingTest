package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj23888 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		long A = Integer.parseInt(st.nextToken()); // 초항
		long D = Integer.parseInt(st.nextToken()); // 공차

		int N = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int command = Integer.parseInt(st.nextToken());
			int l = Integer.parseInt(st.nextToken());
			int r = Integer.parseInt(st.nextToken());
			long rValue = r * D + A - D;
			long lValue = l * D + A - D;
			if (command == 2) {
				if (l == r) {
					sb.append(lValue + "\n");
				} else
					sb.append(gcd(lValue, D) + "\n");
			} else {
				if (l == r) sb.append(lValue + "\n");
				else {
					int middle = r - l + 1;
					sb.append((lValue + rValue) * middle / 2 + "\n");
				}
			}
		}
		System.out.println(sb);
	}

	static long gcd(long a, long b) {
		if (b == 0) {
			return a;
		}
		return gcd(b, a % b);
	}
}
