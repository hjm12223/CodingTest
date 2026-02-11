package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Boj16202 {
	static List<int[]> edge = new ArrayList<>();
	static int[] parents;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		StringBuilder sb = new StringBuilder();
		for (int i = 1; i <= M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			edge.add(new int[] {from, to, i});
		}
		for (int turn = 0; turn < K; turn++) {
			parents = new int[N + 1];
			for (int i = 1; i <= N; i++)
				parents[i] = i;
			int result = 0;
			int cnt = 0;

			for (int i = turn; i < M; i++) {
				int[] ed = edge.get(i);
				int a = find(ed[0]);
				int b = find(ed[1]);

				if (a != b) {
					union(a, b);
					result += ed[2];
					cnt++;
				}
			}
			if (cnt == N - 1) {
				sb.append(result + " ");
			} else {
				sb.append("0 ");
			}
		}
		System.out.println(sb);
	}

	static int find(int x) {
		if (parents[x] != x) {
			return parents[x] = find(parents[x]);
		}
		return x;
	}

	static void union(int a, int b) {
		int p_a = find(a);
		int p_b = find(b);
		if (p_a != p_b) {
			parents[p_b] = p_a;
		}

	}
}
