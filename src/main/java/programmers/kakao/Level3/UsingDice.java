package programmers.kakao.Level3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UsingDice {
	static int N;
	static List<Dice> list = new ArrayList<>();

	public static void main(String[] args) {
		// System.out.println(Arrays.toString(solution(new int[][]
		// 	{{1, 2, 3, 4, 5, 6}, {3, 3, 3, 3, 4, 4}, {1, 3, 3, 4, 4, 4}, {1, 1, 4, 4, 5, 5}}
		// )));
		int[] solution = solution(
			new int[][] {
				{1, 2, 3, 4, 5, 6}, {3, 3, 3, 3, 4, 4}, {1, 3, 3, 4, 4, 4}, {1, 1, 4, 4, 5, 5}
				, {1, 1, 1, 1, 1, 1}, {2, 2, 2, 2, 2, 2}, {3, 3, 3, 4, 4, 4}, {5, 1, 4, 4, 5, 5}
				, {6, 6, 6, 6, 6, 6}, {5, 30, 20, 1, 24, 25}
			}
		);
		System.out.println(Arrays.toString(solution));
	}

	public static int[] solution(int[][] dice) {
		N = dice.length; // 다이스의 개수

		for (int i = 0; i < N; i++) {
			for (int j = i + 1; j < N; j++) {
				comb(i + 1, j + 1, dice[i], dice[j]);
			}
		}
		List<Result> results = new ArrayList<>();
		for (int i = 0; i < list.size(); i++) {
			Result result = new Result(list.get(i).index);

			for (int j = 0; j < list.size(); j++) {
				boolean isCheck = true;
				if (i == j) continue;
				for (int index : list.get(j).index) {
					if (list.get(i).index.contains(index)) {
						isCheck = false;
						break;
					}
				}
				if (isCheck)
					find(result, list.get(i), list.get(j));
			}
			results.add(result);
		}
		results.sort((o1, o2) -> {
			return o2.win - o1.win;
		});
		return results.get(0).index.stream().mapToInt(Integer::valueOf).toArray();
	}

	private static void find(Result result, Dice dice, Dice dice1) {
		for (int firstHap : dice.comb) {
			for (Integer nextHap : dice1.comb) {
				if (firstHap > nextHap) {
					result.win += 1;
				} else if (firstHap == nextHap) {
					result.draw += 1;
				} else {
					result.lose += 1;
				}
			}
		}
	}

	private static void comb(int idx1, int idx2, int[] dice1, int[] dice2) {
		Dice dice = new Dice();
		dice.index.add(idx1);
		dice.index.add(idx2);
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 6; j++) {
				int hap = dice1[i] + dice2[j];
				dice.comb.add(hap);
			}
		}
		list.add(dice);
	}

	private static class Dice {
		Set<Integer> index = new HashSet<>();
		List<Integer> comb = new ArrayList<>();

	}

	private static class Result {
		Set<Integer> index;
		int win = 0;
		int draw = 0;
		int lose = 0;

		public Result(Set<Integer> index) {
			this.index = index;
		}

	}
}
