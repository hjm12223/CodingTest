package beakjoon.trie;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class Boj14725 {
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());
		Trie trie = new Trie();
		for (int k = 0; k < T; k++) {
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());

			String[] foods = new String[n];
			for (int i = 0; i < n; i++) {
				foods[i] = st.nextToken();
			}
			trie.insert(foods);
		}
		trie.print();
		bw.close();
	}

	private static class Trie {
		Node rootNode;

		public Trie() {
			rootNode = new Node();
		}

		void print() throws IOException {
			printNode(rootNode, 0);
		}

		void printNode(Node node, int depth) throws IOException {
			List<String> list = new ArrayList<>(node.childNode.keySet());
			Collections.sort(list);
			for (String food : list) {
				bw.write("--".repeat(depth) + food + "\n");
				printNode(node.childNode.getOrDefault(food, null), depth + 1);
			}
			bw.flush();
		}

		void insert(String[] foods) {
			Node node = rootNode;
			for (String food : foods) {
				node = node.childNode.computeIfAbsent(food, key -> new Node());
			}
		}
	}

	private static class Node {
		Map<String, Node> childNode = new HashMap<>();
	}
}
