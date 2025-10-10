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

public class Boj15656 {
	static int N, M;
	static int[] arr;
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		Arrays.sort(arr);

		dfs(new ArrayList<>());
		bw.flush();
	}

	private static void dfs(List<Integer> comb) throws IOException {
		if (comb.size() == M) {
			for (Integer value : comb) {
				bw.write(value + " ");
			}
			bw.newLine();
			return;
		}
		for (int i = 0; i < N; i++) {
			comb.add(arr[i]);
			dfs(comb);
			comb.remove(comb.size() - 1);
		}
	}
}
