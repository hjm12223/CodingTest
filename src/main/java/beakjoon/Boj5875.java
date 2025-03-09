package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Boj5875 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line = br.readLine();
		int[] prefix = new int[line.length()];
		prefix[0] = line.charAt(0) == '(' ? 1 : -1;
		for (int i = 1; i < line.length(); i++) {
			prefix[i] = prefix[i - 1] + line.charAt(i) == '(' ? 1 : -1;
		}
		int total = prefix[prefix.length - 1];
		int cnt = 0;
		for (int i = 0; i < prefix.length; i++) {
			int sum;
			if (line.charAt(i) == '(') {
				sum = total - 2;
			} else {
				sum = total + 2;
			}
			if (sum == 0) {
				boolean valid = true;
				int minPre = (i == 0) ? 0 : prefix[i - 1];
				if (line.charAt(i) == '(') {
					if (minPre < 0) valid = false;
				} else {
					if (minPre < -1) valid = false;
				}
				if (valid) cnt++;
			}
		}
		System.out.println(cnt);
	}
}
