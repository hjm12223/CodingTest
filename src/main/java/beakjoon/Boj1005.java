package beakjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class Boj1005 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		while (T-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken()); // 노드의 갯수
			int K = Integer.parseInt(st.nextToken()); // 간선의 수
			int[] cost = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			List<List<Integer>> graph = new ArrayList<>();
			for (int i = 0; i <= N; i++)
				graph.add(new ArrayList<>());
			Set<Integer> set = new HashSet<>();
			for (int i = 0; i < K; i++) {
				st = new StringTokenizer(br.readLine());
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				graph.get(to).add(from);
				set.add(to);
			}

			int startNode = 0;
			for (int i = 1; i <= N; i++) {
				if (!set.contains(i)) {
					startNode = i;
					break;
				}
			}
			int target = Integer.parseInt(br.readLine());
			Queue<Node> q = new ArrayDeque<>();
			if (graph.get(target).isEmpty()) {
				bw.write(cost[target - 1] + "\n");
				continue;
			}
			q.offer(new Node(target, 0));

			boolean[] visited = new boolean[N + 1];
			visited[target] = true;
			int[] dic = new int[N + 1];

			while (!q.isEmpty()) {
				Node curr = q.poll();
				dic[curr.layer] = Math.max(dic[curr.layer], cost[curr.curr - 1]);
				if (curr.curr == startNode) {
					bw.write(Arrays.stream(dic).sum() + "\n");
					break;
				}
				for (Integer next : graph.get(curr.curr)) {
					if (!visited[next]) {
						visited[next] = true;
						q.offer(new Node(next, curr.layer + 1));
					}
				}
			}
		}
		bw.flush();
		bw.close();

	}

	private static class Node {
		int layer;
		int curr;

		public Node(int curr, int layer) {
			this.layer = layer;
			this.curr = curr;
		}
	}
}
