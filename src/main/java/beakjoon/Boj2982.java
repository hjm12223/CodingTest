package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj2982 {
	static int N, M;
	static List<List<int[]>> graph = new ArrayList<>(); // 양방향
	static Map<Long, int[]> blocks = new HashMap<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 교차로의 수
		M = Integer.parseInt(st.nextToken()); // 도로의 수
		for (int i = 0; i <= N; i++)
			graph.add(new ArrayList<>());

		st = new StringTokenizer(br.readLine());
		int A = Integer.parseInt(st.nextToken());
		int B = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int G = Integer.parseInt(st.nextToken());

		int[] roads = new int[G];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < G; i++)
			roads[i] = Integer.parseInt(st.nextToken());

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			graph.get(from).add(new int[] {to, cost});
			graph.get(to).add(new int[] {from, cost});
		}
		int time = 0;
		for (int i = 0; i < G - 1; i++) {
			int from = roads[i];
			int to = roads[i + 1];
			int len = calLen(from, to);
			blocks.put(makeKey(from, to), new int[] {time, time + len});
			time += len;
		}
		dijkstra(A, B, K);

	}

	private static void dijkstra(int start, int target, int K) {
		Queue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]);
		pq.offer(new int[] {start, 0});
		int[] dist = new int[N + 1];
		Arrays.fill(dist, (1 << 30));
		dist[start] = K;

		while (!pq.isEmpty()) {
			int[] curr = pq.poll();
			int now = curr[0];
			int currTime = dist[now];

			for (int[] node : graph.get(now)) {
				int next = node[0];
				int nextTime = node[1];
				Long key = makeKey(now, next);
				int enterTime = currTime;

				if (blocks.containsKey(key)) {
					int[] blockedRoad = blocks.get(key);
					int startBlock = blockedRoad[0];
					int endBlock = blockedRoad[1];
					if (enterTime >= startBlock && endBlock >= enterTime) {
						enterTime = endBlock;
					}
				}
				int arriveTime = enterTime + nextTime;
				if (dist[next] > arriveTime) {
					dist[next] = arriveTime;
					pq.offer(new int[] {next, arriveTime});
				}
			}
		}
		System.out.println(dist[target] - K);
	}

	private static Long makeKey(int from, int to) {
		return (long)Math.min(from, to) << 30 | Math.max(from, to);
	}

	private static int calLen(int from, int to) {
		for (int[] next : graph.get(from)) {
			if (next[0] == to) return next[1];
		}
		return -1;
	}
}
