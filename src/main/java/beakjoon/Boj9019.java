package beakjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj9019 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int T = Integer.parseInt(st.nextToken());
		for (int k = 0; k < T; k++) {
			st = new StringTokenizer(br.readLine());
			int existing = Integer.parseInt(st.nextToken());
			Integer target = Integer.parseInt(st.nextToken());
			boolean[] visited = new boolean[10000];

			Queue<Node> q = new LinkedList<>();
			q.offer(new Node(existing, ""));
			while (!q.isEmpty()) {
				Node curr = q.poll();
				if (Objects.equals(curr.value, target)) {
					bw.write(curr.history + "\n");
					break;
				}
				Node nextNode;

				nextNode = left(curr);
				if (!visited[nextNode.value]) {
					visited[nextNode.value] = true;
					q.offer(nextNode);
				}

				nextNode = right(curr);
				if (!visited[nextNode.value]) {
					visited[nextNode.value] = true;
					q.offer(nextNode);
				}

				nextNode = doubleUp(curr);
				if (!visited[nextNode.value]) {
					visited[nextNode.value] = true;
					q.offer(nextNode);
				}

				nextNode = minus(curr);
				if (!visited[nextNode.value]) {
					visited[nextNode.value] = true;
					q.offer(nextNode);
				}
			}
		}
		bw.flush();
		bw.close();
	}

	private static Node left(Node curr) {
		int value = curr.value;
		int rotated = (value % 1000) * 10 + value / 1000;
		return new Node(rotated, curr.history + "L");
	}

	private static Node right(Node curr) {
		int value = curr.value;
		int rotated = (value % 10) * 1000 + value / 10;
		return new Node(rotated, curr.history + "R");
	}

	private static Node doubleUp(Node curr) {
		Node node = new Node(curr.value, curr.history);
		node.value = (curr.value * 2) % 10000;
		node.history += "D";
		return node;
	}

	private static Node minus(Node curr) {
		Node node = new Node(curr.value, curr.history);
		if (node.value != 0) {
			node.value -= 1;
		} else {
			node.value = 9999;
		}
		node.history += "S";
		return node;
	}

	private static class Node {
		int value;
		String history;

		public Node(int value, String st) {
			this.value = value;
			this.history = st;
		}

		@Override
		public String toString() {
			return "Node{" +
				"value=" + value +
				", history=" + history +
				'}';
		}
	}
}