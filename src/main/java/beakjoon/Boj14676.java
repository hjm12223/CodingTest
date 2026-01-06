package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Boj14676 {
	static List<List<Integer>> graph = new ArrayList<>();
	static int N, M, K;
	static int[] indegree, buildingCnt, satisfiedCnt;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		indegree = new int[N + 1];
		buildingCnt = new int[N + 1];
		satisfiedCnt = new int[N + 1];

		for (int i = 0; i <= N; i++) {
			graph.add(new ArrayList<>());
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			graph.get(from).add(to);
			indegree[to]++;
		}

		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int command = Integer.parseInt(st.nextToken());
			int node = Integer.parseInt(st.nextToken());
			if (!check(command, node)) {
				System.out.println("Lier!");
				return;
			}
		}
		System.out.println("King-God-Emperor");
	}

	private static boolean check(int command, int node) {
		if (command == 1) {
			if (satisfiedCnt[node] < indegree[node]) {
				return false;
			}
			if (buildingCnt[node] == 0) {
				for (Integer next : graph.get(node)) {
					satisfiedCnt[next]++;
				}
			}
			buildingCnt[node]++;
		} else {
			if (buildingCnt[node] == 0) {
				return false;
			}
			buildingCnt[node]--;
			if (buildingCnt[node] == 0) {
				for (int next : graph.get(node)) {
					satisfiedCnt[next]--;
				}
			}
		}
		return true;
	}
}
