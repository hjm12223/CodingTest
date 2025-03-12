package beakjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Boj15681 {
	static int N, R;
	static List<List<Integer>> tree;
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static int[] subtrees;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 노드의 수
		R = Integer.parseInt(st.nextToken()); // 루트의 번호
		int Q = Integer.parseInt(st.nextToken()); // 쿼리 수
		tree = new ArrayList<>();
		subtrees = new int[N + 1];
		for (int i = 0; i <= N; i++)
			tree.add(new ArrayList<>());
		for (int i = 0; i < N - 1; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			tree.get(from).add(to);
			tree.get(to).add(from);
		}
		dfs(R, -1);
		while (Q-- > 0) {
			bw.write(subtrees[Integer.parseInt(br.readLine())] + "\n"); // 해당 노드의 서브트리가 몇개 있는지 확인
		}
		bw.flush();
		bw.close();
	}

	private static int dfs(int node, int parent) {
		subtrees[node] = 1;
		for (int child : tree.get(node)) {
			if (child != parent) {
				subtrees[node] += dfs(child, node);
			}
		}
		return subtrees[node];
	}
}
