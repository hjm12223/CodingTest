package beakjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Boj1543 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		String target = br.readLine();
		String pattern = br.readLine();
		int result = 0;
		int left = 0;

		while (left <= target.length() - pattern.length()) {
			boolean isMatched = true;
			for (int i = 0; i < pattern.length(); i++) {
				if (target.charAt(left + i) != pattern.charAt(i)) {
					isMatched = false;
					break;
				}
			}
			if (isMatched) {
				result++;
				left += pattern.length();
			} else {
				left++;
			}
		}
		bw.write(String.valueOf(result));
		bw.flush();
		bw.close();
	}
}
