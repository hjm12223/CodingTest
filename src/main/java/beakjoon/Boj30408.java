package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.StringTokenizer;

public class Boj30408 {
	static boolean isOkay = false;
	static Set<Long> visited = new HashSet<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		Long N = Long.parseLong(st.nextToken());
		Long M = Long.parseLong(st.nextToken());
		dfs(N, M);
		System.out.println(isOkay ? "YES" : "NO");

	}

	private static void dfs(Long n, Long m) {
		if (isOkay || visited.contains(n)) return;
		if (Objects.equals(n, m)) {
			isOkay = true;
			return;
		}
		if (n < m || n < 1) return;
		visited.add(n);
		if (n % 2 == 0) {
			dfs(n / 2, m);
		} else {
			dfs((n - 1) / 2, m);
			dfs((n - 1) / 2 + 1, m);
		}
	}
}
