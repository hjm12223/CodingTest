package programmers.kakao.Level3;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;

public class UsingDice {
	public static void main(String[] args) {
		// int[] solution = solution(
		// 	new int[][] {
		// 		{1, 2, 3, 4, 5, 6}, {3, 3, 3, 3, 4, 4}, {1, 3, 3, 4, 4, 4}, {1, 1, 4, 4, 5, 5}
		// 		, {1, 1, 1, 1, 1, 1}, {2, 2, 2, 2, 2, 2}, {3, 3, 3, 4, 4, 4}, {5, 1, 4, 4, 5, 5}
		// 		, {6, 6, 6, 6, 6, 6}, {5, 30, 20, 1, 24, 25}
		// 	}
		// );
		int[] solution = solution(new int[][] {{1, 2, 3, 4, 5, 6}, {2, 2, 4, 4, 6,}});
		System.out.println("solution = " + Arrays.toString(solution));

	}

	public static int[] solution(int[][] dice) {
		int N = dice.length;
		int maxWin = 0;
		Queue<Integer> list = new LinkedList<>();
		Arrays.sort(dice, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return o1[0] != o2[0] ? o1[0] - o2[0] : o1[1] - o2[1];
			}
		});
		for (int i = 0; i < N; i++) {
			int win = 0;
			for (int j = 0; j < N; j++) {
				if (i == j) continue;
				int currentWin = 0;
				for (int number1 : dice[i]) {
					for (int number2 : dice[j]) {
						if (number1 > number2) {
							currentWin++;
						}
					}
				}
				win += currentWin;
			}
			if (win > maxWin) {
				maxWin = win;
				list.add(i + 1);
				if (list.size() > N / 2) {
					list.poll();
				}
			} else if (win == maxWin) {
				// 최대 이긴 횟수와 동일한 경우 추가
				list.add(i + 1);
			}
		}
		int[] result = new int[N / 2];
		for (int i = 0; i < N / 2; i++) {
			result[i] = list.poll();
		}
		return result;

	}
}
