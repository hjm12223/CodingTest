package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Boj34978 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		List<char[]> rules = new ArrayList<>(26);
		boolean[] visited = new boolean[26];

		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());

			char start = st.nextToken().charAt(0);
			int size = Integer.parseInt(st.nextToken());
			char[] chars = new char[size];
			for (int j = 0; j < size; j++) {
				chars[j] = st.nextToken().charAt(0);
			}
			rules.add(start - 'a', chars);
		}
		String s = br.readLine();
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			// char[] s = rules.get(c - 'a');
		}
	}
}
