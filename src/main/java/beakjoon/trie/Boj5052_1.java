package beakjoon.trie;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Boj5052_1 {
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int T = Integer.parseInt(st.nextToken());
		for (int k = 0; k < T; k++) {

			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			Trie trie = new Trie();
			boolean isOK = true;
			for (int j = 0; j < n; j++) {

				st = new StringTokenizer(br.readLine());

				String str = st.nextToken();
				if (!trie.insert(str)) {
					isOK = false;
				}
			}
			if (isOK) {
				System.out.println("YES");
			} else {
				System.out.println("NO");
			}
		}
	}

	private static class Node {
		Map<Character, Node> childNode = new HashMap<>();
		boolean endOfWord = false;
	}

	private static class Trie {
		Node root;

		public Trie() {
			this.root = new Node();
		}

		private boolean insert(String str) {
			Node node = this.root;
			for (int i = 0; i < str.length(); i++) {
				if (node.endOfWord) return false;
				node = node.childNode.computeIfAbsent(str.charAt(i), key -> new Node());
			}
			if (!node.childNode.isEmpty()) {
				return false;
			}
			node.endOfWord = true;
			return true;
		}
	}
}