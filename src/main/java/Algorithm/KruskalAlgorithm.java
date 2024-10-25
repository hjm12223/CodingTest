package Algorithm;

import java.util.Arrays;

public class KruskalAlgorithm {
	public static void main(String[] args) {
		int[][] graph = {{1, 2, 6}, {1, 3, 3}, {1, 4, 1}, {2, 5, 4}, {3, 4, 2}, {3, 5, 5}, {4, 5, 7}};
		int[] parent = new int[6];
		int total = 0;

		Arrays.sort(graph, (o1, o2) ->
			Integer.compare(o1[2], o2[2]));

		for (int i = 0; i < parent.length; i++) {
			parent[i] = i;
		}
		for (int i = 0; i < graph.length; i++) {
			int a = graph[i][0];
			int b = graph[i][1];
			System.out.println("parent = " + Arrays.toString(parent));
			if (find(parent, a) != find(parent, b)) {
				union(parent, a, b);
				total += graph[i][2];
			}
		}
		System.out.println("total = " + total);
	}

	public static void union(int[] parent, int a, int b) {
		int a_parent = find(parent, a);
		System.out.println("a = " + a);
		System.out.println("a_parent = " + a_parent);
		int b_parent = find(parent, b);
		System.out.println("b = " + b);
		System.out.println("b_parent = " + b_parent);
		if (parent[a_parent] < parent[b_parent]) {
			System.out.println("1. parent = " + parent[a_parent]);
			System.out.println("2. parent = " + parent[b_parent]);
			parent[b_parent] = parent[a_parent];
		} else {
			parent[a_parent] = parent[b_parent];
		}
	}

	public static int find(int[] parent, int i) {
		if (parent[i] != i) {
			parent[i] = find(parent, parent[i]);
		}
		System.out.println("parent = " + parent[i]);
		return parent[i];
	}
}
/**
 *  크루스칼 알고리즘이란
 *  최소 비용 신장 트리를 찾는 알고리즘이다
 *
 *  MST 란?
 *
 *  Minimum Spanning Tree 의 약자로 최소 연결 부분 그래프를 의미
 *
 *  정점 N개를 가지는 그래프에서 N-1 개의 간선을 연결해야한다
 *
 *  연결한 간선의 가중치 합이 최소치가 나오게 해야한다
 *
 *  모든 간선이 연결되어야 하나 싸이클이 되어선 안된다
 *
 * 만약 사이클이 발생하면 그 간선은 넘김
 *
 * 아니라면 둘의 부모가 다르다면 union 해줌
 *
 * 만약 현재의 간선의 길이가 n-1 일 경우 합쳐줌
 *
 */