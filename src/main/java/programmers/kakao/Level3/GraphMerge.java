package programmers.kakao.Level3;

import java.util.ArrayDeque;
import java.util.Deque;

public class GraphMerge {

	public static void main(String[] args) {
		String solution = solution(8, 2, new String[] {"D 2", "C", "U 3", "C", "D 4", "C", "U 2", "Z", "Z"});
		String solution1 = solution(8, 2,
			new String[] {"D 2", "C", "U 3", "C", "D 4", "C", "U 2", "Z", "Z", "U 1", "C"});
		System.out.println("solution = " + solution);
		System.out.println("solution1 	= " + solution1);
	}

	public static String solution(int n, int k, String[] cmd) {
		Node[] nodes = new Node[n];
		for (int i = 0; i < n; i++) {
			nodes[i] = new Node(i);
		}
		for (int i = 1; i < n; i++) {
			nodes[i].prev = nodes[i - 1];
			nodes[i - 1].next = nodes[i];
		}

		Node curr = nodes[k];
		Deque<Node> deleted = new ArrayDeque<>();

		for (int i = 0; i < cmd.length; i++) {
			String[] commands = cmd[i].split(" ");
			String firstCommand = commands[0];

			if (firstCommand.equals("U")) {
				int cnt = Integer.parseInt(commands[1]);
				while (cnt-- > 0) {
					curr = curr.prev;
				}
			} else if (firstCommand.equals("D")) {
				int cnt = Integer.parseInt(commands[1]);
				while (cnt-- > 0) {
					curr = curr.next;
				}
			} else if (firstCommand.equals("C")) {
				deleted.offerLast(curr);
				if (curr.next != null)
					curr.next.prev = curr.prev;
				if (curr.prev != null)
					curr.prev.next = curr.next;
				curr = (curr.next != null) ? curr.next : curr.prev;

			} else if (firstCommand.equals("Z")) {
				Node deletedNode = deleted.pollLast();
				if (deletedNode.prev != null) {
					deletedNode.prev.next = deletedNode;
				}
				if (deletedNode.next != null) {
					deletedNode.next.prev = deletedNode;
				}
			}
		}
		StringBuilder result = new StringBuilder("O".repeat(n));
		while (!deleted.isEmpty()) {
			result.setCharAt(deleted.pop().index, 'X');
		}
		return result.toString();
	}

	private static class Node {
		Node prev;
		Node next;
		int index;

		public Node(int index) {
			this.index = index;
		}
	}
}