package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj16953 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int start = Integer.parseInt(st.nextToken());
		int target = Integer.parseInt(st.nextToken());

		Queue<Node> q = new LinkedList<>();
		q.offer(new Node(start, 1));
		int result = -1;
		while (!q.isEmpty()) {
			Node curr = q.poll();
			if (curr.value > target) continue;
			if (curr.value == target) {
				result = curr.count;
				break;
			}
			q.offer(new Node(curr.value * 2, curr.count + 1));
			q.offer(new Node(curr.value * 10 + 1, curr.count + 1));
		}
		System.out.println(result);
	}

	private static class Node {
		long value;
		int count;

		public Node(long value, int count) {
			this.value = value;
			this.count = count;
		}
	}
}
