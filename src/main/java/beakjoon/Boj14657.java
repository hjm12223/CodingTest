package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/*
1. 그래프 문제
2. 첫 번째 문제를 푸는 데에는 0초의 시간이 소요된다.
3. 매일 투자할 수 있는 최대한의 시간 동안 문제를 풀고, 남은 문제는 그 다음날에 이어서 풀려고 한다.


 */
public class Boj14657 {
	static List<List<int[]>> graph = new ArrayList<>();
	static int N;
	static int farNode;
	static int maxNodeCount;
	static long minTotalCost;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		int T = Integer.parseInt(st.nextToken());
		int node = 0;
		for (int i = 0; i <= N; i++) {
			graph.add(new ArrayList<>());
		}
		for (int i = 0; i < N - 1; i++) {
			st = new StringTokenizer(br.readLine());

			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());

			graph.get(from).add(new int[] {to, cost});
			graph.get(to).add(new int[] {from, cost}); // 양방향 그래프
			node = from;
		}
		bfs(node);
		bfs(farNode);
		long result = minTotalCost / T;

		if (minTotalCost % T != 0) {
			result++;
		}
		if (result == 0) {
			result = 1;
		}

		System.out.println(result);
	}

	private static void bfs(int start) {
		Queue<long[]> q = new ArrayDeque<>();
		boolean[] visited = new boolean[N + 1];

		q.offer(new long[] {start, 1, 0});
		visited[start] = true;

		farNode = start;
		maxNodeCount = 0;
		minTotalCost = Long.MAX_VALUE;

		while (!q.isEmpty()) {
			long[] curr = q.poll();

			int node = (int)curr[0];
			int nodeCount = (int)curr[1];
			long cost = curr[2];

			if (nodeCount > maxNodeCount) {
				maxNodeCount = nodeCount;
				minTotalCost = cost;
				farNode = node;
			} else if (nodeCount == maxNodeCount && cost < minTotalCost) {
				minTotalCost = cost;
				farNode = node;
			}

			for (int[] next : graph.get(node)) {
				int nextNode = next[0];
				int nextCost = next[1];

				if (!visited[nextNode]) {
					visited[nextNode] = true;
					q.offer(new long[] {nextNode, nodeCount + 1, cost + nextCost});
				}
			}
		}
	}
}
/*
N^2logN = 110억
N^2 = 25억
1. 최대한 많은 문제를 푸는 동시에 최소 날짜 수
최대한 많은 문제를 풀려면? outDegree 가 많은 순으로?
 */
