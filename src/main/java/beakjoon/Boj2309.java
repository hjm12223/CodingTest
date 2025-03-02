package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Boj2309 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[] people = new int[9];
		for (int i = 0; i < people.length; i++) {
			people[i] = Integer.parseInt(br.readLine());
		}
		List<Integer> comb = new ArrayList<>();
		boolean[] visited = new boolean[people.length];
		dfs(people, comb, 0, visited);
	}

	private static void dfs(int[] people, List<Integer> comb, int depth, boolean[] visited) {
		if (comb.size() == 7) {
			int hap = 0;
			for (Integer integer : comb) {
				hap += integer;
			}
			if (hap == 100) {
				Collections.sort(comb);
				for (Integer i : comb) {
					System.out.println(i);
				}
				System.exit(0);
			}
			return;
		}

		for (int i = depth; i < people.length; i++) {
			if (!visited[i]) {
				visited[i] = true;
				comb.add(people[i]);
				dfs(people, comb, depth + 1, visited);
				comb.remove(comb.size() - 1);
				visited[i] = false;
			}
		}
	}
}
