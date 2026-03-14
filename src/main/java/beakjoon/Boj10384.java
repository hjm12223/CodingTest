package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Boj10384 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int i = 1; i <= N; i++) {
			int result = Integer.MAX_VALUE;
			String pangram = br.readLine();
			int[] alphabet = new int[26];
			for (int j = 0; j < pangram.length(); j++) {
				char c = pangram.charAt(j);
				int idx;
				if (Character.isUpperCase(c)) idx = c - 'A';
				else idx = c - 'a';
				if (idx < 0 || idx >= 26) continue;

				alphabet[idx]++;
			}
			for (int j = 0; j < alphabet.length; j++) {
				result = Math.min(result, alphabet[j]);
			}
			switch (result) {
				case 1:
					sb.append("Case " + i + ": " + "Pangram!\n");
					break;
				case 0:
					sb.append("Case " + i + ": " + "Not a pangram\n");
					break;
				case 2:
					sb.append("Case " + i + ": " + "Double pangram!!\n");
					break;
				case 3:
					sb.append("Case " + i + ": " + "Triple pangram!!!\n");
					break;
			}
		}
		System.out.println(sb);
	}
}
