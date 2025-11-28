package beakjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Boj5639_2 {
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Node node = new Node(Integer.parseInt(br.readLine()));
		while (true) {
			String s = br.readLine();
			if (s == null || s.isEmpty()) break;
			int value = Integer.parseInt(s);
			node.insert(value);
		}
		postOrder(node);
		bw.flush();
		bw.close();
	}

	private static void postOrder(Node node) throws IOException {
		if (node != null) {
			postOrder(node.left);
			postOrder(node.right);
			bw.write(node.value + "\n");
		}
	}

	private static class Node {
		int value;
		Node left;
		Node right;

		public Node(int value) {
			this.value = value;
		}

		public void insert(int value) {
			if (value < this.value) {
				if (this.left == null)
					this.left = new Node(value);
				else this.left.insert(value);
			} else if (value > this.value) {
				if (this.right == null) {
					this.right = new Node(value);
				} else this.right.insert(value);
			}
		}
	}
}
