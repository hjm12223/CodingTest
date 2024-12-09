package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;

public class Boj5639 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Node node = new Node(Integer.parseInt(br.readLine()));
		String str;
		while (true) {
			str = br.readLine();
			if (str == null || Objects.equals(str, "") || str.isEmpty()) break;
			node.insert(Integer.parseInt(str));
		}
		postOrder(node);
	}

	private static void postOrder(Node node) {
		if (node == null) return;
		postOrder(node.left);
		postOrder(node.right);
		System.out.println(node.value);
	}

	private static class Node {
		Integer value;
		Node left;
		Node right;

		public Node(Integer value) {
			this.value = value;
		}

		private void insert(int value) {
			if (value < this.value) {
				if (this.left == null) {
					this.left = new Node(value);
				} else {
					this.left.insert(value);
				}
			} else {
				if (this.right == null) {
					this.right = new Node(value);
				} else {
					this.right.insert(value);
				}
			}
		}
	}
}
