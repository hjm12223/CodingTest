package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Boj13908 {
	static List<List<Integer>> comb = new ArrayList<>();
	static List<Integer> hint = new ArrayList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken()); // 비밀번호의 길이
		int M = Integer.parseInt(st.nextToken()); // 들어가는 비밀번호
		if (M != 0) {
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < M; i++) {
				hint.add(Integer.parseInt(st.nextToken()));
			}
		}
		dfs(N, 0, new ArrayList<>());
		System.out.println(comb.size());
	}

	private static void dfs(int r, int depth, List<Integer> c) {
		if (c.size() == r) {
			for (int i = 0; i < hint.size(); i++) {
				if (!c.contains(hint.get(i))) {
					return;
				}
			}
			comb.add(new ArrayList<>(c));
			return;
		}
		for (int i = 0; i < 10; i++) {
			c.add(i);
			dfs(r, depth + 1, c);
			c.remove(c.size() - 1);
		}
	}
}
