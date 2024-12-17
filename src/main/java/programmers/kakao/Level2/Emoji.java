package programmers.kakao.Level2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Emoji {
	static double[] discounts = new double[] {0.4, 0.3, 0.2, 0.1};
	static int maxEmoji = Integer.MIN_VALUE;
	static int maxValue = Integer.MIN_VALUE;
	static boolean[] isVisited;
	static int cnt = 1;

	public static void main(String[] args) {
		// int[] solution = solution(new int[][] {{40, 10000}, {25, 10000}}, new int[] {7000, 9000});
		int[] solution = solution(
			new int[][] {{40, 2900}, {23, 10000}, {11, 5200}, {5, 5900}, {40, 3100}, {27, 9200}, {32, 6900}},
			new int[] {1300, 1500, 1600, 4900});

		System.out.println(Arrays.toString(solution));
	}

	public static int[] solution(int[][] users, int[] emoticons) {
		int r = emoticons.length;
		isVisited = new boolean[emoticons.length];
		comb(emoticons, r, 0, new ArrayList<>(), new int[emoticons.length], users);
		return new int[] {maxEmoji, maxValue};
	}

	private static void comb(int[] emoticons, int r, int depth, List<Integer> list, int[] discount, int[][] users) {
		if (list.size() == r) {
			System.out.println(cnt++);
			cal(list, discount, users);
			return;
		}
		for (int i = depth; i < r; i++) {
			for (int j = 0; j < discounts.length; j++) {
				if (!isVisited[i]) {
					isVisited[i] = true;
					int discountValue = (int)(emoticons[i] - (emoticons[i] * discounts[j]));
					discount[i] = (int)(discounts[j] * 100);
					list.add(discountValue);
					comb(emoticons, r, depth + 1, list, discount, users);
					isVisited[i] = false;
					list.remove(list.size() - 1);
				}
			}
		}
	}

	private static void cal(List<Integer> list, int[] discount, int[][] users) {
		int emoji = 0;
		int value = 0;
		for (int[] user : users) {
			int maxDis = user[0];
			int maxVal = user[1];
			int sum = 0;
			for (int i = 0; i < discount.length; i++) {
				if (discount[i] >= maxDis) {
					sum += list.get(i);
				}
			}
			if (sum >= maxVal) {
				emoji++;
			} else {
				value += sum;
			}
		}
		if (emoji > maxEmoji) {
			maxEmoji = emoji;
			maxValue = value;
		} else if (emoji == maxEmoji && value > maxValue) {
			maxValue = value;
		}
	}
}