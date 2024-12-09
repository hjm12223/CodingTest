package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Boj15655 {
	static int[] arr;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		List<List<Integer>> comb = new ArrayList<>();
		arr = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(arr);
		combination(comb, new ArrayList<>(), M, N);
		StringBuilder sb = new StringBuilder();
		for (List<Integer> list : comb) {
			for (Integer i : list) {
				sb.append(i).append(" ");
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}

	private static void combination(List<List<Integer>> comb, List<Integer> output, int r, int n) {
		if (output.size() == r) {
			comb.add(new ArrayList<>(output));
			return;
		}
		int before = -1;
		for (int i = 0; i < n; i++) {
			if (arr[i] != before) {
				before = arr[i];
				output.add(arr[i]);
				combination(comb, output, r, n);
				output.remove(output.size() - 1);
			}
		}
	}
}
