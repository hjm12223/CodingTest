package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Boj10809 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String st = br.readLine();
		int[] alphabet = new int[26];
		Arrays.fill(alphabet, -1);
		for (int i = 0; i < st.length(); i++) {
			char c = st.charAt(i);
			if (alphabet[c - 'a'] == -1) {
				alphabet[c - 'a'] = i;
			}
		}
		StringBuilder sb = new StringBuilder();
		for (int i : alphabet) {
			sb.append(i).append(" ");
		}
		System.out.println(sb);
	}
}
