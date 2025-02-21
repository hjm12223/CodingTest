package beakjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class Boj15651 {
	static int N, M;
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		List<Integer> comb = new LinkedList<>();
		dfs(comb);
		bw.flush();
		bw.close();
	}

	private static void dfs(List<Integer> comb) throws IOException {
		if (comb.size() == M) {
			for (int i = 0; i < comb.size(); i++) {
				bw.write(comb.get(i) + " ");
			}
			bw.newLine();
			return;
		}
		for (int i = 1; i <= N; i++) {
			comb.add(i);
			dfs(comb);
			comb.remove(comb.size() - 1);
		}
	}
}
