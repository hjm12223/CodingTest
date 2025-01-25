package beakjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Boj1939 {
	static int N, M;
	static boolean[] visited;
	static List<List<Node>> graph;
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		graph = new ArrayList<>();

		for (int i = 0; i <= N; i++) {
			graph.add(new ArrayList<>());
		}

		int maxCost = 0;
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());

			graph.get(from).add(new Node(to, cost));
			graph.get(to).add(new Node(from, cost));
			maxCost = Math.max(maxCost, cost);
		}

		st = new StringTokenizer(br.readLine());
		int from = Integer.parseInt(st.nextToken());
		int to = Integer.parseInt(st.nextToken());

		int result = bi(from, to, maxCost);
		bw.write(String.valueOf(result));
		bw.newLine();
		bw.flush();
		bw.close();
	}

	private static int bi(int from, int to, int maxCost) {
		int low = 1;
		int high = maxCost;
		int result = 0;
		while (low <= high) {
			int mid = (low + high) / 2;
			visited = new boolean[N + 1];
			if (dfs(from, to, mid)) {
				low = mid + 1;
				result = mid;
			} else {
				high = mid - 1;
			}
		}
		return result;
	}

	private static boolean dfs(int from, int to, int value) {
		if (from == to) {
			return true;
		}
		visited[from] = true;
		for (int i = 0; i < graph.get(from).size(); i++) {
			Node next = graph.get(from).get(i);
			if (!visited[next.curr] && next.cost >= value) {
				if (dfs(next.curr, to, value)) {
					return true;
				}
			}
		}
		return false;
	}

	private static class Node {
		int curr;
		int cost;

		public Node(int curr, int cost) {
			this.curr = curr;
			this.cost = cost;
		}
	}
}
/*
N(2 ≤ N ≤ 10,000)개의 섬으로 이루어진 나라가 있다. 이들 중 몇 개의 섬 사이에는 다리가 설치되어 있어서 차들이 다닐 수 있다.

영식 중공업에서는 두 개의 섬에 공장을 세워 두고 물품을 생산하는 일을 하고 있다.
물품을 생산하다 보면 공장에서 다른 공장으로 생산 중이던 물품을 수송해야 할 일이 생기곤 한다.
그런데 각각의 다리마다 중량제한이 있기 때문에 무턱대고 물품을 옮길 순 없다.
만약 중량제한을 초과하는 양의 물품이 다리를 지나게 되면 다리가 무너지게 된다.

한 번의 이동에서 옮길 수 있는 물품들의 중량의 최댓값을 구하는 프로그램을 작성하시오.

첫째 줄에 N, M(1 ≤ M ≤ 100,000)이 주어진다.
다음 M개의 줄에는 다리에 대한 정보를 나타내는 세 정수 A, B(1 ≤ A, B ≤ N), C(1 ≤ C ≤ 1,000,000,000)가 주어진다.
 이는 A번 섬과 B번 섬 사이에 중량제한이 C인 다리가 존재한다는 의미이다.
  서로 같은 두 섬 사이에 여러 개의 다리가 있을 수도 있으며, 모든 다리는 양방향이다.
  마지막 줄에는 공장이 위치해 있는 섬의 번호를 나타내는 서로 다른 두 정수가 주어진다.
  공장이 있는 두 섬을 연결하는 경로는 항상 존재하는 데이터만 입력으로 주어진다.
 */
