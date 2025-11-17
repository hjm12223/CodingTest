package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Boj2503_2 {
	static boolean[] visited = new boolean[10];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		List<List<String>> comb = new ArrayList<>();
		List<BaseBall> baseBalls = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			baseBalls.add(
				new BaseBall(st.nextToken(), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
		}
		dfs(comb, new ArrayList<>());
		int result = 0;
		for (List<String> baseballs : comb) {
			int temp = 0;
			for (BaseBall baseBall : baseBalls) {
				int ball = 0;
				int strike = 0;
				for (int i = 0; i < 3; i++) {
					if (baseBall.str.charAt(i) == baseballs.get(i).charAt(0)) {
						strike++;
					} else {
						for (int j = 0; j < 3; j++) {
							if (baseBall.str.charAt(j) == baseballs.get(i).charAt(0)) ball++;
						}
					}
				}
				if (baseBall.ball == ball && baseBall.strike == strike) temp++;
			}
			if (temp == baseBalls.size()) result++;
		}
		System.out.println(result);
	}

	private static void dfs(List<List<String>> comb, List<String> obj) {
		if (obj.size() == 3) {
			comb.add(new ArrayList<>(obj));
			return;
		}
		for (int i = 1; i <= 9; i++) {
			if (!visited[i]) {
				visited[i] = true;
				obj.add(String.valueOf(i));
				dfs(comb, obj);
				obj.remove(obj.size() - 1);
				visited[i] = false;
			}
		}

	}

	private static class BaseBall {
		String str;
		int strike;
		int ball;

		public BaseBall(String str, int strike, int ball) {
			this.str = str;
			this.strike = strike;
			this.ball = ball;
		}
	}
}
