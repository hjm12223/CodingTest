package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj1068 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		List<List<Integer>> tree = new ArrayList<>();
		for (int i = 0; i <= N; i++) {
			tree.add(new ArrayList<>());
		}
		int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		int root = 0;
		for (int i = 0; i < N; i++) {
			if (arr[i] == -1) {
				root = i;
				continue;
			}
			tree.get(arr[i]).add(i);
		}
		int eliminationNode = Integer.parseInt(br.readLine());
		boolean[] visited = new boolean[N];
		visited[eliminationNode] = true;
		if (root == eliminationNode) {
			System.out.println(0);
			return;
		}
		if (tree.get(root).size() == 1 && tree.get(root).contains(eliminationNode)) {
			System.out.println(1);
			return;
		}
		Queue<Integer> q = new ArrayDeque<>();
		q.offer(root);
		int leefCnt = 0;
		while (!q.isEmpty()) {
			Integer curr = q.poll();
			if (tree.get(curr).isEmpty()) {
				leefCnt += 1;
			}
			for (Integer next : tree.get(curr)) {
				if (!visited[next]) {
					visited[next] = true;
					q.offer(next);
				} else if (visited[next] && !tree.get(root).contains(next) && tree.get(curr).size() == 1) {
					leefCnt += 1;
				}
			}
		}
		System.out.println(leefCnt);
	}
}
