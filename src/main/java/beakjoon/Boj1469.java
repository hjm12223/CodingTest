package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj1469 {
	static int N;
	static int[] arr, seq;
	static boolean[] used;
	static boolean isFind = false;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		arr = new int[N];
		seq = new int[2 * N];
		used = new boolean[N];

		StringTokenizer st = new StringTokenizer(br.readLine());
		Arrays.fill(seq, -1);
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		dfs(0);
		if (!isFind) {
			System.out.println(-1);
		}
	}

	private static void dfs(int idx) {
		if (isFind) return;

		while (idx < 2 * N && seq[idx] != -1) idx++;

		if (idx == 2 * N) {
			StringBuilder sb = new StringBuilder();
			for (int i : seq) {
				sb.append(i).append(" ");
			}
			System.out.println(sb.toString().trim());
			isFind = true;
			return;
		}
		for (int i = 0; i < N; i++) {
			if (used[i]) continue;

			int x = arr[i];
			int next = x + idx + 1;

			if (next >= N * 2) continue;
			if (seq[next] != -1) continue;

			seq[idx] = x;
			seq[next] = x;
			used[i] = true;

			dfs(idx + 1);

			seq[idx] = -1;
			seq[next] = -1;
			used[i] = false;
		}

	}
}
