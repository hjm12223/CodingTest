package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj16562_2 {
	static int[] parents;
	static int[] friendFee;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		friendFee = new int[N + 1];
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			friendFee[i] = Integer.parseInt(st.nextToken());
		}

		parents = new int[N + 1];
		for (int i = 1; i <= N; i++)
			parents[i] = i;

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			union(from, to);
		}
		boolean[] visited = new boolean[N + 1];
		int result = 0;
		for (int i = 1; i <= N; i++) {
			int pa = find(i);
			if (!visited[pa]) {
				visited[pa] = true;
				result += friendFee[pa];
			}
		}
		System.out.println(result > K ? "Oh no" : result);
	}

	static int find(int x) {
		if (x != parents[x]) {
			return parents[x] = find(parents[x]);
		}
		return x;
	}

	private static void union(int a, int b) {
		int pa = find(a);
		int pb = find(b);
		if (friendFee[pa] > friendFee[pb]) {
			parents[pa] = pb;
		} else {
			parents[pb] = pa;
		}
	}
}
