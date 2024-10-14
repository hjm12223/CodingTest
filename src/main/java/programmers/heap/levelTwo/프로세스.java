package programmers.heap.levelTwo;

import java.util.PriorityQueue;
import java.util.Comparator;
import java.util.Queue;

public class 프로세스 {
	public static void main(String[] args) {
		int sol1 = solution(new int[] {1, 1, 9, 1, 1, 1}, 0);
		// int sol2 = solution(new int[] {2, 1, 3, 2}, 2);
		System.out.println("sol1 = " + sol1);
		// System.out.println("sol2 = " + sol2);
	}

	public static int solution(int[] priorities, int location) {
		// 우선순위 큐를 내림차순으로 정렬하고, 우선순위가 같으면 인덱스 기준으로 오름차순 정렬
		Queue<Node> sortedQ = new PriorityQueue<>(new Comparator<Node>() {
			@Override
			public int compare(Node o1, Node o2) {
				if (o1.priority == o2.priority) {
					return o1.index - o2.index; // 인덱스 기준 오름차순
				}
				return o2.priority - o1.priority; // 우선순위 기준 내림차순
			}
		});

		// 우선순위 큐에 노드 추가
		for (int i = 0; i < priorities.length; i++) {
			sortedQ.add(new Node(i, priorities[i]));
		}

		int order = 1;
		// 큐에서 가장 높은 우선순위를 가진 문서를 꺼내어 처리
		while (!sortedQ.isEmpty()) {
			Node curr = sortedQ.poll();
			if (curr.index == location) {
				return order; // 해당 문서가 출력되는 순서 반환
			}
			order++;
		}
		return -1; // 문제 정의에 따라 도달하지 않지만, 기본적으로 -1 반환
	}

	public static class Node {
		int index;
		int priority;

		public Node(int index, int priority) {
			this.index = index;
			this.priority = priority;
		}
	}
}
