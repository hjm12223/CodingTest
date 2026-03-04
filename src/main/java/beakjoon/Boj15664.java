package beakjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

public class Boj15664 {
	static int[] arr;
	static int N, M;
	static Set<String> set = new HashSet<>();
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		Arrays.sort(arr);
		dfs(new ArrayList<>(), 0);
		bw.flush();
		bw.close();
	}

	private static void dfs(List<Integer> comb, int depth) throws IOException {
		if (comb.size() == M) {
			for (int i = 0; i < comb.size(); i++) {
				bw.write(comb.get(i) + " ");
			}
			bw.newLine();
			return;
		}
		for (int i = depth; i < N; i++) {
			if (i > depth && arr[i] == arr[i - 1]) continue;
			comb.add(arr[i]);
			dfs(comb, i + 1);
			comb.remove(comb.size() - 1);
		}
	}
}
