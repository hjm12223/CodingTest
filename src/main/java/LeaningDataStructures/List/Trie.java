package LeaningDataStructures.List;

import java.util.HashMap;
import java.util.Map;

public class Trie {
	public static void main(String[] args) {
		String str = "girl";

		Node rootNode = new Node();

		insert(str, rootNode);
		System.out.println("rootNode = " + rootNode);
		boolean search = search(str, rootNode);
		System.out.println("search = " + search);
	}

	private static void insert(String str, Node rootNode) {
		Node node = rootNode;
		for (int i = 0 ; i < str.length(); i++){
			node = node.childNode.computeIfAbsent(str.charAt(i) , key -> new Node());
		}
		node.endOfWord = true;
	}
	private static boolean search(String str, Node rootNode){
		Node node = rootNode;
		for (int i = 0 ; i < str.length() ; i++){
			node = node.childNode.getOrDefault(str.charAt(i),null);
			if (node == null){
				return false;
			}
		}
	return node.endOfWord;
	}

	public static class Node{
		@Override
		public String toString() {
			return "Node{" +
				"childNode=" + childNode +
				", endOfWord=" + endOfWord +
				'}';
		}

		Map<Character, Node> childNode = new HashMap<>();
		boolean endOfWord;
	}
}

