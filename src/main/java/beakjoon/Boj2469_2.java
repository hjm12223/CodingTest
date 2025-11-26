package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Boj2469_2 {
	static int B;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int K = Integer.parseInt(br.readLine());
		B = Integer.parseInt(br.readLine());
		char[] start = new char[K];
		char[] target = br.readLine().toCharArray();
		for (int i = 0; i < target.length; i++) {
			start[i] = (char)('A' + i);
		}
		char[][] ladders = new char[B][B];
		int questionsRow = 0;
		for (int i = 0; i < B; i++) {
			String str = br.readLine();
			for (int j = 0; j < B; j++) {
				if (str.charAt(j) == '?') questionsRow = i;
				ladders[i][j] = str.charAt(j);
			}
		}
		List<List<Character>> combs = new ArrayList<>();
		dfs(combs, new ArrayList<>());
		System.out.println(combs);
	}

	private static void dfs(List<List<Character>> combs, List<Character> ladder) {
		if (ladder.size() == B) {
			combs.add(new ArrayList<>(ladder));
		}
		for (int i = 0; i < B; i++) {
			if (i > 0 && !ladder.get(i - 1).equals('-')) {
				ladder.add('*');
				dfs(combs, ladder);
				ladder.remove(ladder.size() - 1);
			} else {
				ladder.add('*');
				dfs(combs, ladder);
				ladder.remove(ladder.size() - 1);
				ladder.add('-');
				dfs(combs, ladder);
				ladder.remove(ladder.size() - 1);
			}
		}
	}
}
