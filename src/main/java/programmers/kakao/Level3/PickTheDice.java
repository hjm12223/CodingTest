package programmers.kakao.Level3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class PickTheDice {
	static int N;
	static List<Result> results = new ArrayList<>();

	public static void main(String[] args) {
		System.out.println(Arrays.toString(solution(new int[][]
			{{1, 2, 3, 4, 5, 6}, {3, 3, 3, 3, 4, 4}, {1, 3, 3, 4, 4, 4}, {1, 1, 4, 4, 5, 5}}
		)));
		// int[] solution = solution(
		// 	new int[][] {
		// 		{1, 2, 3, 4, 5, 6}, {3, 3, 3, 3, 4, 4}, {1, 3, 3, 4, 4, 4}, {1, 1, 4, 4, 5, 5}
		// 		, {1, 1, 1, 1, 1, 1}, {2, 2, 2, 2, 2, 2}, {3, 3, 3, 4, 4, 4}, {5, 1, 4, 4, 5, 5}
		// 		, {6, 6, 6, 6, 6, 6}, {5, 30, 20, 1, 24, 25}
		// 	}
		// );
		// System.out.println("solution = " + Arrays.toString(solution));
	}

	public static int[] solution(int[][] dice) {
		N = dice.length;
		List<List<Integer>> comb = new ArrayList<>();
		getComb(comb, new ArrayList<>(), N / 2, 0); // 조합 생성
		Set<List<Integer>> set = new HashSet<>(); // 중복 방지

		for (List<Integer> first : comb) {
			List<Integer> second = new ArrayList<>();
			for (int k = 1; k <= N; k++) { // 반대 조합 생성
				if (!first.contains(k)) second.add(k);
			}

			if (set.contains(first) || set.contains(second)) continue;
			set.add(first);
			set.add(second);

			List<Integer> firstSum = new ArrayList<>();
			List<Integer> secondSum = new ArrayList<>();

			makeDiceSum(first, dice, N / 2, firstSum, 0, 0);
			makeDiceSum(second, dice, N / 2, secondSum, 0, 0);

			Map<Integer, Integer> firstScore = makeMap(firstSum);
			Map<Integer, Integer> secondScore = makeMap(secondSum);

			int firstWinCnt = 0;
			int secondWinCnt = 0;

			for (Integer firstKey : firstScore.keySet()) {
				for (Integer secondKey : secondScore.keySet()) {
					if (firstKey > secondKey) {
						firstWinCnt += firstScore.get(firstKey) * secondScore.get(secondKey);
					} else if (firstKey < secondKey) {
						secondWinCnt += firstScore.get(firstKey) * secondScore.get(secondKey);
					}
				}
			}

			results.add(new Result(first.stream().mapToInt(Integer::intValue).toArray(), firstWinCnt));
			results.add(new Result(second.stream().mapToInt(Integer::intValue).toArray(), secondWinCnt));
		}
		results.sort((o1, o2) ->
			o2.win - o1.win);

		return results.get(0).index;
	}

	private static Map<Integer, Integer> makeMap(List<Integer> score) {
		Map<Integer, Integer> map = new TreeMap<>();

		for (Integer sc : score) {
			map.put(sc, map.getOrDefault(sc, 0) + 1);
		}
		return map;
	}

	private static void makeDiceSum(List<Integer> Dice, int[][] dice, int r, List<Integer> list, int depth, int sum) {
		if (depth == r) {
			list.add(sum);
			return;
		}
		int index = Dice.get(depth) - 1;
		for (int i = 0; i < 6; i++) {
			makeDiceSum(Dice, dice, r, list, depth + 1, sum + dice[index][i]);
		}
	}

	private static void getComb(List<List<Integer>> comb, List<Integer> output, int r, int depth) {
		if (output.size() == r) {
			comb.add(new ArrayList<>(output));
			return;
		}
		for (int i = depth; i < N; i++) {
			output.add(i + 1);
			getComb(comb, output, r, i + 1);
			output.remove(output.size() - 1);
		}
	}

	private static class Result {
		int[] index;
		int win;

		public Result(int[] index, int win) {
			this.index = index;
			this.win = win;
		}
	}
}
