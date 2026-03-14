package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Boj1138 {
	static int N;
	static int[] arr;
	static boolean[] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		arr = new int[N];
		visited = new boolean[N + 1];
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		dfs(1, new ArrayList<>(), N);
	}

	private static void dfs(int depth, List<Integer> list, int r) {
		if (list.size() == r) {
			if (check(list)) {
				StringBuilder sb = new StringBuilder();
				for (Integer i : list) {
					sb.append(i + " ");
				}
				System.out.println(sb);
			}
			return;
		}
		for (int i = 1; i <= r; i++) {
			if (!visited[i]) {
				visited[i] = true;
				list.add(i);
				dfs(depth + 1, list, r);
				list.remove(list.size() - 1);
				visited[i] = false;
			}
		}
	}

	private static boolean check(List<Integer> list) {
		for (int i = 1; i <= N; i++) { // 1 ~ N 까지
			int cnt = 0; // idx
			int checking = 0; // arr[i] = 왼쪽에 몇명 판별 요소
			while (list.get(cnt) != i) { // combination 으로 조합된 i번째 인덱스를 찾을 때 까지
				cnt++;
			}
			int height = list.get(cnt);
			for (int j = (cnt - 1) == -1 ? 0 : cnt - 1; j >= 0; j--) {
				if (list.get(j) > height) checking++;
			}
			if (checking != arr[i - 1]) return false;
		}
		return true;
	}
}
