package beakjoon.trie;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class Boj7432 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());

		Trie trie = new Trie();
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			String[] directories = st.nextToken().split("\\\\");
			trie.insert(directories);
		}
		trie.print();
	}

	public static class Trie {
		Node rootNode;

		public Trie() {
			this.rootNode = new Node();
		}

		void insert(String[] directories) {
			Node node = rootNode;
			for (String directory : directories) {
				node = node.childNode.computeIfAbsent(directory, key -> new Node());
			}
		}

		void print() {
			printNode(rootNode, 0);
		}

		private void printNode(Node rootNode, int depth) {
			List<String> list = new ArrayList<>(rootNode.childNode.keySet());
			Collections.sort(list);
			for (String directory : list) {
				System.out.println(" ".repeat(depth) + directory);
				printNode(rootNode.childNode.getOrDefault(directory, null), depth + 1);
			}
		}
	}

	public static class Node {
		Map<String, Node> childNode = new HashMap<>();

	}
}
