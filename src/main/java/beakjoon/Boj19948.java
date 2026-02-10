package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Boj19948 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();
		int spaceBar = Integer.parseInt(br.readLine());
		int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		char prev = 0;
		for (int i = 0; i < str.length(); i++) {
			char c = str.charAt(i);
			if (c == prev) continue;

			if (c == ' ') {
				spaceBar--;
				if (spaceBar < 0) {
					System.out.println(-1);
					return;
				}
			} else {
				int idx = Character.toLowerCase(c) - 'a';
				arr[idx]--;
				if (arr[idx] < 0) {
					System.out.println(-1);
					return;
				}
			}
			prev = c;
		}
		String[] words = str.split(" ");
		StringBuilder sb = new StringBuilder();
		char prevTitle = 0;
		for (String word : words) {
			if (word.isEmpty()) continue;
			char c = Character.toLowerCase(word.charAt(0));

			if (c != prevTitle) {
				int idx = c - 'a';
				arr[idx]--;
				if (arr[idx] < 0) {
					System.out.println(-1);
					return;
				}
			}

			sb.append(Character.toUpperCase(c));
			prevTitle = c;
		}
		System.out.println(sb);
	}
}
