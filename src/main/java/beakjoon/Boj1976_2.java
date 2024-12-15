package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Boj1976_2 {
	static int[] parent;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		parent = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			parent[i] = i;
		}
		int M = Integer.parseInt(br.readLine());
		List<List<Integer>> graph = new ArrayList<>();
		for (int i = 0; i <= N; i++)
			graph.add(new ArrayList<>());
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				int next = Integer.parseInt(st.nextToken());
				if (i == j) continue;
				if (next == 1) {
					graph.get(i + 1).add(j + 1);
				}
			}
		}
		int[] array = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		for (int i = 1; i <= N; i++) {
			for (int j = 0; j < graph.get(i).size(); j++) {
				if (find(graph.get(i).get(j)) != find(i)) {
					union(graph.get(i).get(j), i);
				}
			}
		}
		int isSame = find(array[0]);
		for (int element : array) {
			if (isSame != find(element)) {
				System.out.println("NO");
				return;
			}
		}
		System.out.println("YES");
	}

	private static int find(int i) {
		if (parent[i] != i) {
			parent[i] = find(parent[i]);
		}
		return parent[i];
	}

	private static void union(int a, int b) {
		int a_parent = parent[a];
		int b_parent = parent[b];
		if (a_parent != b_parent) {
			parent[a_parent] = parent[b_parent];
		}
	}
}
