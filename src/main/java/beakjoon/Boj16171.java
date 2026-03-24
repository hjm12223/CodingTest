package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Boj16171 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		String str = br.readLine();
		String target = br.readLine();
		for (int i = 0; i < str.length(); i++) {
			if (!Character.isDigit(str.charAt(i))) {
				sb.append(str.charAt(i));
			}
		}
		boolean isFind = false;
		for (int i = 0; i < sb.length() - target.length() + 1; i++) {
			if (sb.charAt(i) == target.charAt(0)) {
				int cnt = 1;
				for (int j = 1; j < target.length(); j++) {
					if (sb.charAt(i + j) != target.charAt(j)) break;
					else cnt++;
				}
				if (cnt == target.length()) isFind = true;
			}
		}
		System.out.println(isFind ? 1 : 0);
	}
}
