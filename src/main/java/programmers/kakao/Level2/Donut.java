package programmers.kakao.Level2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

public class Donut {
	static final int MAX_CAPACITY = 1_000_001;
	static boolean[] isVisited = new boolean[MAX_CAPACITY];
	static List<List<Integer>> list = new ArrayList<>(MAX_CAPACITY);
	static int eightCount = 0;
	static int donutCount = 0;
	static int barCount = 0;
	static boolean isRecursion;

	public static void main(String[] args) {
		int[] solution = solution(
			new int[][] {{4, 11}, {1, 12}, {8, 3}, {12, 7}, {4, 2}, {7, 11}, {4, 8}, {9, 6}, {10, 11}, {6, 10}, {3, 5},
				{11, 1}, {5, 3}, {11, 9}, {3, 8}});
		System.out.println("solution = " + Arrays.toString(solution));
		// int[] solution = solution(new int[][] {{2, 3}, {4, 3}, {1, 1}, {2, 1}});
		// System.out.println("solution = " + Arrays.toString(solution));
	}

	public static int[] solution(int[][] edges) {
		int vertex = 0; // 정점

		for (int i = 0; i <= MAX_CAPACITY; i++) {
			list.add(new ArrayList<>());
		}

		Set<Integer> set = new HashSet<>(MAX_CAPACITY); // 정점을 판별하기 위한 자료구조

		for (int i = 0; i < edges.length; i++) {
			int from = edges[i][0];
			int to = edges[i][1];
			list.get(from).add(to);
			set.add(to);
		} // 삽입

		for (int[] edge : edges) {
			int from = edge[0];
			if (!set.contains(from) && list.get(from).size() >= 2) {
				vertex = from;
				break;
			}
		} // 정점을 찾기위한 전처리 과정
		for (int i = 0; i < list.get(vertex).size(); i++) {
			isRecursion = false;
			dfs(list.get(vertex).get(i));
			if (!isRecursion) {
				barCount++;
			}
		}
		return new int[] {vertex, donutCount, barCount, eightCount};
	}

	private static void dfs(int start) {
		Stack<Integer> stack = new Stack<>(); // 자료구조
		stack.push(start);
		while (!stack.isEmpty()) {
			Integer curr = stack.pop();
			for (int i = 0; i < list.get(curr).size(); i++) {
				Integer nextNode = list.get(curr).get(i);
				if (list.get(curr).size() >= 2) {
					eightCount++;
					isRecursion = true;
					return;
				}
				if (isVisited[nextNode]) { // 만약 다음노드를 이미 방문을 했다면?
					donutCount++;
					isRecursion = true;
					return;
				}
				isVisited[curr] = true;
				stack.push(nextNode); // 다음 노드를 넣어준다
			}
		}
	}
}