package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj16319 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String arr = br.readLine();
		int[][] alphabets = new int[arr.length() + 1][26];
		int K = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int i = 1; i <= arr.length(); i++) {
			System.arraycopy(alphabets[i - 1], 0, alphabets[i], 0, 26);
			int value = arr.charAt(i - 1) - 'a';
			alphabets[i][value]++;
		}

		for (int k = 0; k < K; k++) {
			StringTokenizer st = new StringTokenizer(br.readLine());

			char alphabet = st.nextToken().charAt(0);
			int l = Integer.parseInt(st.nextToken());
			int r = Integer.parseInt(st.nextToken());
			int result = alphabets[r + 1][alphabet - 'a'] - alphabets[l][alphabet - 'a'];
			sb.append(result + "\n");
		}
		System.out.println(sb);
	}
}
/*
S = 200,000, S^2 = 40,000,000,000 완탐 불가
 */
