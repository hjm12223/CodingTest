package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Boj10775 {
	static int[] parent;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int G = Integer.parseInt(br.readLine()); // 게이트 수
		int P = Integer.parseInt(br.readLine()); // 비행기 수
		parent = new int[G + 1];
		for (int i = 1; i <= G; i++)
			parent[i] = i;
		int result = 0;
		for (int i = 0; i < P; i++) {
			int g = Integer.parseInt(br.readLine());
			int gate = find(g);
			if (gate == 0) break;
			union(gate, gate - 1);
			result++;
		}
		System.out.println(result);
	}

	private static void union(int a, int b) {
		int a_parent = find(a);
		int b_parent = find(b);
		if (a_parent != b_parent) {
			if (a_parent < b_parent) {
				parent[b_parent] = a_parent;
			} else {
				parent[a_parent] = b_parent;
			}
		}

	}

	private static int find(int x) {
		if (x != parent[x]) {
			return parent[x] = find(parent[x]);
		}
		return x;
	}
}
