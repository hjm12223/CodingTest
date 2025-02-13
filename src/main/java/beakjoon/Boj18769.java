package beakjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Boj18769 {
	static List<Node> nodes;
	static int[] parent;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int T = par(st.nextToken());
		while (T-- > 0) {
			st = new StringTokenizer(br.readLine());
			int R = par(st.nextToken());
			int C = par(st.nextToken());
			int totalParent = R * C;
			parent = new int[totalParent];
			nodes = new ArrayList<>();
			for (int i = 0; i < totalParent; i++)
				parent[i] = i;
			for (int i = 0; i < R; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < C - 1; j++) {
					int cost = par(st.nextToken());
					int u = i * C + j; // 현재노드
					int v = i * C + (j + 1); // 오른쪽노드
					nodes.add(new Node(u, v, cost));
				}
			}
			for (int i = 0; i < R - 1; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < C; j++) {
					int cost = Integer.parseInt(st.nextToken());
					int u = i * C + j;       // 현재 노드
					int v = (i + 1) * C + j; // 아래쪽 노드
					nodes.add(new Node(u, v, cost));
				}
			}
			nodes.sort((o1, o2) -> o1.cost - o2.cost);
			int cost = 0;
			int cnt = 0;

			for (Node node : nodes) {
				if (find(node.curr) != find(node.next)) {
					union(node.curr, node.next);
					cost += node.cost;
					cnt++;
					if (cnt == totalParent - 1) break;
				}
			}
			bw.write(String.valueOf(cost));
			bw.newLine();
		}
		bw.close();
	}

	private static void union(int curr, int next) {
		int px = find(curr);
		int py = find(next);
		if (px != py) {
			parent[px] = py;
		}
	}

	private static int find(int curr) {
		if (parent[curr] == curr) return curr;
		return parent[curr] = find(parent[curr]);
	}

	private static int par(String st) {
		return Integer.parseInt(st);
	}

	private static class Node {
		int curr, next;
		int cost;

		public Node(int curr, int next, int cost) {
			this.curr = curr;
			this.next = next;
			this.cost = cost;
		}
	}
}
