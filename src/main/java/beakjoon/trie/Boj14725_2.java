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

public class Boj14725_2 {
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		int T = Integer.parseInt(st.nextToken());
		Trie trie = new Trie();
		for (int i = 0; i < T; i++) {
			st = new StringTokenizer(br.readLine());

			int k = Integer.parseInt(st.nextToken());
			String[] foods = new String[k];
			for (int j = 0; j < foods.length; j++) {
				foods[j] = st.nextToken();
			}
			trie.insert(foods);

		}
		trie.print();
		bw.flush();
	}

	public static class Trie {
		Node rootNode;

		public Trie() {
			rootNode = new Node();
		}

		public void insert(String[] foods) {
			Node node = rootNode;
			for (String food : foods) {
				node = node.childNode.computeIfAbsent(food, key -> new Node());
			}
		}

		public void print() throws IOException {
			printResult(rootNode, 0);
		}

		private void printResult(Node node, int depth) throws IOException {
			List<String> list = new ArrayList<>(node.childNode.keySet());
			Collections.sort(list);
			for (String food : list) {
				bw.write("--".repeat(depth) + food + "\n");
				printResult(node.childNode.getOrDefault(food, null), depth + 1);
			}
		}
	}

	public static class Node {
		Map<String, Node> childNode = new HashMap<>();
	}
}
