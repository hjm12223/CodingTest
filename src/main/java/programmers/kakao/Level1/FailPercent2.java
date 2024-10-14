package programmers.kakao.Level1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

public class FailPercent2 {

	public static void main(String[] args) {
		solution(5,new int[]{2, 1, 2, 6, 2, 4, 3, 3});
	}

	public static int[] solution(int N, int[] stages) {
		Arrays.sort(stages);

		Map<Integer, Integer> map = new HashMap<>();
		int people = stages.length; // 스테이지의 길이는 사람의 수

		// 스테이지별 도전 중인 사용자 수 카운트
		for (int i = 0; i < stages.length; i++) {
			map.put(stages[i], map.getOrDefault(stages[i], 0) + 1);
		}

		Queue<Stage> queue = new PriorityQueue<>(new Comparator<Stage>() {
			@Override
			public int compare(Stage o1, Stage o2) {
				if (o1.clear == o2.clear) {
					return o1.stage - o2.stage; // 실패율이 같으면 스테이지 번호가 작은 순으로
				} else {
					return Float.compare(o2.clear, o1.clear); // 실패율이 높은 순으로
				}
			}
		});

		for (int i = 1; i <= N; i++) {
			if (map.get(i) == null) {
				queue.offer(new Stage(i, 0));
				continue;
			}
			Integer ing = map.get(i);
			queue.offer(new Stage(i, (float) ing / people));
			people -= ing;
		}

		int[] answer = new int[N];
		int i = 0;
		while (!queue.isEmpty()) {
			if (i >= N) break;
			Stage poll = queue.poll();
			answer[i] = poll.stage;
			i++;
		}

		return answer;
	}

	public static class Stage {
		int stage;
		float clear;

		public Stage(int stage, float clear) {
			this.stage = stage;
			this.clear = clear;
		}
	}
}