package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj1459 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		long result = Long.MAX_VALUE;
		int x = Integer.parseInt(st.nextToken());
		int y = Integer.parseInt(st.nextToken());
		int w = Integer.parseInt(st.nextToken()); // 걸어서 가는 방법
		int s = Integer.parseInt(st.nextToken()); // 가로질러 가는 방법
		if (s < w) {
			int value = Math.max(x, y);
			System.out.println(value * s);
			return;
		}
		if (x > y) {
			result = Math.min(result, ((long)y * s) + ((long)(x - y) * w));
		} else if (y > x) {
			result = Math.min(result, ((long)x * s) + ((long)(y - x) * w));
		}
		result = Math.min(((long)x * w) + ((long)y * w), result);
		if (x == y) {
			result = Math.min((long)x * s, result);
		}
		System.out.println(result);
	}
}
