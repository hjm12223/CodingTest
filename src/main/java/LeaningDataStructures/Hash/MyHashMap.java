package LeaningDataStructures.Hash;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class MyHashMap<E> extends HashMap<E, E> {
	Map<String, Integer> map = new HashMap<>();

	public static class Node {
		int size;
		int current;

		public Node(int size, int current) {
			this.size = size;
			this.current = current;
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || getClass() != o.getClass()) return false;
			Node node = (Node)o;
			return size == node.size && current == node.current;
		}

		@Override
		public int hashCode() {
			return Objects.hash(size, current);
		}
	}
}
