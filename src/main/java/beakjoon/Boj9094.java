package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj9094 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		while (T-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			int result = 0;
			for (int a = 1; a < n; a++) {
				for (int b = a + 1; b < n; b++) {
					int numerator = a * a + b * b + m;
					int denominator = a * b;
					if (numerator % denominator == 0) result++;
				}
			}
			System.out.println(result);
		}
	}
}
