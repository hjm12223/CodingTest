package programmers.kakao.Level3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
		getComb(comb, new ArrayList<>(), N / 2, 0); // 다이스들의 조합을 만들어 준다 nCn/2 최대 252
		System.out.println(comb.size());
		for (int i = 0; i < comb.size(); i++) {
			List<Integer> first = comb.get(i);
			List<Integer> second = new ArrayList<>();

			for (int k = 1; k <= N; k++) { // 반대 다이스
				if (!first.contains(k)) {
					second.add(k);
				}
			}
			List<Integer> firstSum = calculateSum(first, dice);
			List<Integer> secondSum = calculateSum(second, dice);

			runDice(first, second, firstSum, secondSum);
			results.add(new Result(first.stream().mapToInt(Integer::intValue).toArray()));
		}
		results.sort((o1, o2) ->
			o2.win - o1.win
		);
		return results.get(0).index;
	}

	private static List<Integer> calculateSum(List<Integer> diceIndexes, int[][] dice) {
		List<Integer> sums = new ArrayList<>();
		for (int index : diceIndexes) {
			sums.addAll(getDiceSums(dice[index - 1]));
		}
		return sums;
	}

	private static Set<Integer> getDiceSums(int[] dice) {
		Set<Integer> sums = new HashSet<>();
		makeDiceSum(dice, 0, 0, sums);
		return sums;
	}

	private static void makeDiceSum(int[] dice, int depth, int sum, Set<Integer> list) {
		if (depth == dice.length) {
			list.add(sum);
			return;
		}
		for (int i = 0; i < 6; i++) {
			if (!list.contains(sum + dice[i])) {
				makeDiceSum(dice, depth + 1, sum + dice[i], list);
			}
		}
	}

	private static void runDice(List<Integer> first, List<Integer> second, List<Integer> firstSum,
		List<Integer> secondSum) {

		Result firstResult = new Result(first.stream().mapToInt(Integer::intValue).toArray());
		Result secondResult = new Result(second.stream().mapToInt(Integer::intValue).toArray());
		for (Integer fir : firstSum) {
			for (Integer sec : secondSum) {
				if (fir > sec) {
					firstResult.win += 1;
				} else if (fir < sec) {
					secondResult.win += 1;
				}
			}
			results.add(firstResult);
			results.add(secondResult);
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

		public Result(int[] index) {
			this.index = index;
		}
	}
}
/**
 * A와 B가 n개의 주사위를 가지고 승부를 합니다. 주사위의 6개 면에 각각 하나의 수가 쓰여 있으며,
 * 주사위를 던졌을 때 각 면이 나올 확률은 동일합니다. 각 주사위는 1 ~ n의 번호를 가지고 있으며,
 * 주사위에 쓰인 수의 구성은 모두 다릅니다.
 *
 * A가 먼저 n / 2개의 주사위를 가져가면 B가 남은 n / 2개의 주사위를 가져갑니다.
 * 각각 가져간 주사위를 모두 굴린 뒤, 나온 수들을 모두 합해 점수를 계산합니다.
 * 점수가 더 큰 쪽이 승리하며, 점수가 같다면 무승부입니다.
 *
 * A는 자신이 승리할 확률이 가장 높아지도록 주사위를 가져가려 합니다.
 *
 * 다음은 n = 4인 예시입니다.
 *
 * 주사위	구성
 * #1	[1, 2, 3, 4, 5, 6]
 * #2	[3, 3, 3, 3, 4, 4]
 * #3	[1, 3, 3, 4, 4, 4]
 * #4	[1, 1, 4, 4, 5, 5]
 *
 * 예를 들어 A가 주사위 #1, #2를 가져간 뒤 6, 3을 굴리고, B가 주사위 #3, #4를 가져간 뒤 4, 1을 굴린다면 A의 승리입니다. (6 + 3 > 4 + 1)
 * A가 가져가는 주사위 조합에 따라, 주사위를 굴린 1296가지 경우의 승패 결과를 세어보면 아래 표와 같습니다.
 *
 * A의 주사위	승	무	패
 * #1, #2	596	196	504
 * #1, #3	560	176	560
 * #1, #4	616	184	496
 * #2, #3	496	184	616
 * #2, #4	560	176	560
 * #3, #4	504	196	596
 * A가 승리할 확률이 가장 높아지기 위해선 주사위 #1, #4를 가져가야 합니다.
 *
 * 주사위에 쓰인 수의 구성을 담은 2차원 정수 배열 dice가 매개변수로 주어집니다.
 * 이때, 자신이 승리할 확률이 가장 높아지기 위해 A가 골라야 하는 주사위 번호를
 * 오름차순으로 1차원 정수 배열에 담아 return 하도록 solution 함수를 완성해 주세요.
 * 승리할 확률이 가장 높은 주사위 조합이 유일한 경우만 주어집니다
 */