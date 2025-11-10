package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Boj16198 {
	static int N;
	static int[] arr;
	static int result = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		arr = new int[N];

		StringTokenizer st = new StringTokenizer(br.readLine());

		List<Integer> list = new ArrayList<>();

		for (int i = 0; i < N; i++)
			list.add(Integer.parseInt(st.nextToken()));

		dfs(list, 0);
		
		System.out.println(result);
	}

	private static void dfs(List<Integer> list, int sum) {
		if (list.size() == 2) {
			result = Math.max(result, sum);
			return;
		}

		for (int i = 1; i < list.size() - 1; i++) {
			int energy = list.get(i - 1) * list.get(i + 1);
			int removed = list.remove(i);
			dfs(list, sum + energy);
			list.add(i, removed);
		}
	}
}
