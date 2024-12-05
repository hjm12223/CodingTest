package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Boj2503 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		List<Baseball> baseballs = new ArrayList<>();
		int N = Integer.parseInt(st.nextToken());
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			String baseball = st.nextToken();
			baseballs.add(
				new Baseball(
					baseball,
					Integer.parseInt(st.nextToken()),
					Integer.parseInt(st.nextToken())
				)
			);
		}
		int result = 0;
		List<List<Integer>> comb = new ArrayList<>();

		dfs(comb, new ArrayList<>(), 3, new boolean[10]);
		for (List<Integer> list : comb) {
			if (isValid(list, baseballs)) {
				result++;
			}
		}
		System.out.println(result);
	}

	private static void dfs(List<List<Integer>> comb, List<Integer> output, int r,
		boolean[] visited) {
		if (output.size() == r) {
			comb.add(new ArrayList<>(output));
			return;
		}
		for (int i = 1; i < 10; i++) {
			if (!visited[i]) {
				visited[i] = true;
				output.add(i);
				dfs(comb, output, r, visited);
				output.remove(output.size() - 1);
				visited[i] = false;
			}
		}
	}

	private static boolean isValid(List<Integer> candidate, List<Baseball> baseballs) {
		for (Baseball baseball : baseballs) {
			int strikeCount = 0;
			int ballCount = 0;

			String target = baseball.baseball;
			for (int i = 0; i < 3; i++) {
				int num = candidate.get(i);
				if (num == target.charAt(i) - '0') {
					strikeCount++;
				} else if (target.contains(String.valueOf(num))) {
					ballCount++;
				}
			}

			if (strikeCount != baseball.strike || ballCount != baseball.ball) {
				return false;
			}
		}
		return true;
	}

	private static class Baseball {
		String baseball;
		int strike;
		int ball;

		public Baseball(String baseball, int strike, int ball) {
			this.baseball = baseball;
			this.strike = strike;
			this.ball = ball;
		}

		@Override
		public String toString() {
			return "Baseball{" +
				"baseball='" + baseball + '\'' +
				", strike=" + strike +
				", ball=" + ball +
				'}';
		}
	}
}
