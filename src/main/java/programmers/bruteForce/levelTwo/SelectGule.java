package programmers.bruteForce.levelTwo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class SelectGule {
	public static void main(String[] args) {
		int solution = solution(6, new int[] {1, 3, 2, 5, 4, 5, 2, 3});
		System.out.println("solution = " + solution);
	}

	public static int solution(int k, int[] tangerine) {
		int answer = 0;
		List<Node> nodes = new ArrayList<>();
		Map<Integer,Integer> map = new HashMap<>();

		for (int i : tangerine) {
			if (!map.containsKey(i)) {
				map.put(i, 0);
			}
			map.put(i,map.get(i)+1);
		}

		for (Integer i : map.keySet()) {
			nodes.add(new Node(i,map.get(i)));
		}

		nodes.sort(
			(o1, o2) -> o2.value - o1.value
			);
		int count = 0;
		for (Node node : nodes) {
			answer +=node.value;
			count ++;
			if (answer >= k){
				return count;
			}
		}
		return 0;
	}

	public static class Node {
		int idx;
		int value;

		public Node(int idx, int value) {
			this.idx = idx;
			this.value = value;
		}

		@Override
		public String toString() {
			return "Node{" +
				"idx=" + idx +
				", value=" + value +
				'}';
		}
	}
}
