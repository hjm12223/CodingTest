package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Boj15686 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken()); // 도시 크기
		int M = Integer.parseInt(st.nextToken()); // 살릴 치킨집 개수

		List<int[]> chickens = new ArrayList<>();
		List<int[]> homes = new ArrayList<>();

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				int value = Integer.parseInt(st.nextToken());
				if (value == 1) homes.add(new int[] {i, j}); // 집
				if (value == 2) chickens.add(new int[] {i, j}); // 치킨집
			}
		}
		List<List<int[]>> result = getComb(chickens, M);
		int value = Integer.MAX_VALUE;
		for (List<int[]> chk : result) {
			int minDis = 0;
			for (int[] home : homes) {
				int homeMinDis = Integer.MAX_VALUE;
				for (int[] ckc : chk) {
					int res = Math.abs(ckc[0] - home[0]) + Math.abs(ckc[1] - home[1]);
					homeMinDis = Math.min(homeMinDis, res);
				}
				minDis += homeMinDis;
			}
			value = Math.min(value, minDis);
		}
		System.out.println(value);
	}

	private static List<List<int[]>> getComb(List<int[]> chickens, int m) {
		List<List<int[]>> res = new ArrayList<>();
		runComb(res, chickens, m, 0, new ArrayList<>());
		return res;
	}

	private static void runComb(List<List<int[]>> res, List<int[]> chickens, int m, int start, ArrayList<int[]> list) {
		if (list.size() == m) {
			res.add(new ArrayList<>(list));
			return;
		}
		for (int i = start; i < chickens.size(); i++) {
			list.add(chickens.get(i));
			runComb(res, chickens, m, i + 1, list);
			list.remove(list.size() - 1);
		}
	}
}
