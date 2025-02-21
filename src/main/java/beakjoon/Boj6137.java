package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Boj6137 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		StringBuilder result = new StringBuilder();
		while (N-- > 0) {
			sb.append(br.readLine());
		}
		int cnt = 0;
		while (sb.length() > 0) {
			cnt++;
			if (sb.charAt(0) - 'A' < sb.charAt(sb.length() - 1) - 'A') {
				result.append(sb.charAt(0));
				sb.deleteCharAt(0);
			} else if (sb.charAt(0) - 'A' > sb.charAt(sb.length() - 1) - 'A') {
				result.append(sb.charAt(sb.length() - 1));
				sb.deleteCharAt(sb.length() - 1);
			} else {
				int l = 0;
				int r = sb.length() - 1;
				boolean left = true;

				while (l < r) {
					if (sb.charAt(l) < sb.charAt(r)) {
						left = true;
						break;
					} else if (sb.charAt(l) > sb.charAt(r)) {
						left = false;
						break;
					}
					l++;
					r--;
				}

				if (left) {
					result.append(sb.charAt(0));
					sb.deleteCharAt(0);
				} else {
					result.append(sb.charAt(sb.length() - 1));
					sb.deleteCharAt(sb.length() - 1);
				}
			}

			if (cnt == 80) {
				result.append("\n");
				cnt = 0;
			}
		}
		System.out.println(result.toString());
	}
}
