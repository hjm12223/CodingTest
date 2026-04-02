package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Boj30089 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder result = new StringBuilder();
		while (T-- > 0) {
			String s = br.readLine();
			StringBuilder sb = new StringBuilder(s);
			int idx = 0;
			for (int i = 0; i < sb.length(); i++) {
				if (isPalindrome(sb, i, sb.length() - 1)) {
					idx = i;
					break;
				}
			}
			// System.out.println(idx);
			for (int i = idx - 1; i >= 0; i--) {
				sb.append(s.charAt(i));
			}
			result.append(sb).append("\n");
		}
		System.out.println(result);
	}

	private static boolean isPalindrome(StringBuilder sb, int left, int right) {
		while (right > left) {
			if (sb.charAt(left++) != sb.charAt(right--)) {
				return false;
			}
		}
		return true;
	}
}