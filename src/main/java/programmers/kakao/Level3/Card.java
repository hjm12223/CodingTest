package programmers.kakao.Level3;

import java.util.ArrayList;
import java.util.List;

public class Card {
	public static void main(String[] args) {
		int result = solution(4, new int[] {3, 6, 7, 2, 1, 10, 5, 9, 8, 12, 11, 4});
		int solution = solution(10, new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18});
		System.out.println(solution);
		System.out.println(result); // 출력: 5
	}

	public static int solution(int coin, int[] cards) {
		int n = cards.length;
		int target = n + 1;
		int initialCard = n / 3;

		List<Integer> hand = new ArrayList<>();
		for (int i = 0; i < initialCard; i++) {
			hand.add(cards[i]);
		}

		return backtrack(hand, coin, cards, initialCard, target, 1);
	}

	private static int backtrack(List<Integer> hand, int coin, int[] cards, int index, int target, int round) {
		if (index >= cards.length) return round;

		int maxRound = round;
		boolean isChange = false;
		for (int i = 0; i < hand.size(); i++) {
			for (int j = i + 1; j < hand.size(); j++) {
				if (hand.get(i) + hand.get(j) == target) {
					// 안 가져갔을때의 경우
					Integer firstCard = hand.remove(j);
					Integer secondCard = hand.remove(i);
					isChange = true;
					maxRound = Math.max(backtrack(hand, coin, cards, index, target, round + 1), maxRound);
					hand.add(i, secondCard);
					hand.add(j, firstCard);
				}
			}
		}
		if (index + 1 < cards.length && isChange) {
			// 두장을 다 버리는 상황
			maxRound = Math.max(backtrack(hand, coin, cards, index + 2, target, round), maxRound);

			if (coin > 0) {
				hand.add(cards[index]);
				maxRound = Math.max(backtrack(hand, coin - 1, cards, index + 1, target, round), maxRound);
				hand.remove(hand.size() - 1);
			}
			if (coin > 0 && index + 1 < cards.length) {
				hand.add(cards[index + 1]);
				maxRound = Math.max(backtrack(hand, coin - 1, cards, index + 2, target, round), maxRound);
				hand.remove(hand.size() - 1);
			}
			if (coin > 1 && index + 1 < cards.length) {
				hand.add(cards[index]);
				hand.add(cards[index + 1]);
				maxRound = Math.max(backtrack(hand, coin - 2, cards, index + 2, target, round), maxRound);
				hand.remove(hand.size() - 1);
				hand.remove(hand.size() - 1);
			}
		}
		return maxRound;
	}
}
