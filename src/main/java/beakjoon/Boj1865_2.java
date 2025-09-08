package beakjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Boj1865_2 {
	static List<List<int[]>> graph;

	static int N, M, W;
	static int INF = (1 << 30);

	public static void main(String[] args) throws IOException {

		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			 BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		) {
			int T = Integer.parseInt(br.readLine());

			while (T-- > 0) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				N = Integer.parseInt(st.nextToken());
				M = Integer.parseInt(st.nextToken());
				W = Integer.parseInt(st.nextToken());
				graph = new ArrayList<>();
				for (int i = 0; i <= N; i++) {
					graph.add(new ArrayList<>());
				}
				for (int i = 0; i < M; i++) {
					st = new StringTokenizer(br.readLine());
					int s = Integer.parseInt(st.nextToken());
					int e = Integer.parseInt(st.nextToken());
					int t = Integer.parseInt(st.nextToken());
					graph.get(s).add(new int[] {e, t});
					graph.get(e).add(new int[] {s, t});
				}
				for (int i = 0; i < W; i++) {
					st = new StringTokenizer(br.readLine());
					int s = Integer.parseInt(st.nextToken());
					int e = Integer.parseInt(st.nextToken());
					int t = Integer.parseInt(st.nextToken());
					graph.get(s).add(new int[] {e, -t});
				}
				boolean isCycle = false;
				for (int i = 1; i <= N; i++) {
					if (bp(i)) {
						isCycle = true;
						break;
					}
				}
				if (isCycle) {
					bw.write("YES" + "\n");
				} else {
					bw.write("NO" + "\n");
				}

			}
		}
	}

	private static boolean bp(int start) {
		int[] dist = new int[N + 1];
		Arrays.fill(dist, INF);
		dist[start] = 0;
		boolean isUpdate = false;
		for (int i = 1; i <= N; i++) {
			isUpdate = false;
			for (int j = 1; j <= N; j++) {
				for (int[] node : graph.get(j)) {
					int next = node[0];
					int cost = node[1];
					if (dist[j] != INF && dist[next] > dist[j] + cost) {
						dist[next] = dist[j] + cost;
						isUpdate = true;
					}
				}
			}
			if (!isUpdate) {
				break;
			}
		}
		for (int j = 1; j <= N; j++) {
			for (int[] node : graph.get(j)) {
				int next = node[0];
				int cost = node[1];
				if (dist[j] != INF && dist[next] > dist[j] + cost) {
					return true;
				}
			}
		}
		return false;
	}
}
