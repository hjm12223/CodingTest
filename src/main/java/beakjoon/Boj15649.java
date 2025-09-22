package beakjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Boj15649 {
	static int N, M;
	static int[] arr;
	static boolean[] visited;
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[N];
		visited = new boolean[N + 1];
		for (int i = 1; i <= N; i++) {
			arr[i - 1] = i;
		}
		dfs(new ArrayList<>());
		bw.flush();
		bw.close();
	}

	private static void dfs(List<Integer> comb) throws IOException {
		if (comb.size() == M) {
			for (Integer value : comb) {
				bw.write(value + " ");
			}
			bw.newLine();
			return;
		}
		for (int i = 1; i <= N; i++) {
			if (!visited[i]) {
				visited[i] = true;
				comb.add(i);
				dfs(comb);
				comb.remove(comb.size() - 1);
				visited[i] = false;
			}
		}

	}
}
