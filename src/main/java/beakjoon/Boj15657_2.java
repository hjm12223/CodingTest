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

public class Boj15657_2 {
	static int N, M;
	static int[] arr;
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		arr = new int[N];

		st = new StringTokenizer(br.readLine());

		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(arr);
		dfs(new ArrayList<>(), 0);
		bw.flush();
		bw.close();
	}

	private static void dfs(List<Integer> comb, int depth) throws IOException {
		if (comb.size() == M) {
			for (Integer value : comb) {
				bw.write(value + " ");
			}
			bw.newLine();
			return;
		}
		for (int i = depth; i < N; i++) {
			comb.add(arr[i]);
			dfs(comb, i);
			comb.remove(comb.size() - 1);
		}
	}

}
