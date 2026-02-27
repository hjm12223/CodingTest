package beakjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Boj15649_2 {
	static int N, M;
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static boolean[] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		visited = new boolean[N + 1];
		M = Integer.parseInt(st.nextToken());

		dfs(1, new StringBuilder());
		bw.flush();
		bw.close();
	}

	private static void dfs(int depth, StringBuilder sb) throws IOException {
		if (depth - 1 == M) {
			for (int i = 0; i < sb.length(); i++) {
				bw.write(sb.charAt(i) + " ");
			}
			bw.write("\n");
			return;
		}
		for (int i = 1; i <= N; i++) {
			if (!visited[i]) {
				visited[i] = true;
				sb.append(i);
				dfs(depth + 1, sb);
				sb.deleteCharAt(sb.length() - 1);
				visited[i] = false;
			}

		}
	}
}
