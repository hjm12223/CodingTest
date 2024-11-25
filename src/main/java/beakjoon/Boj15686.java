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
		List<List<int[]>> comb = getComb(chickens, M);
		int result = Integer.MAX_VALUE;
		for (List<int[]> combs : comb) {
			int cityHomeDis = 0;
			for (int[] home : homes) {
				int minHomeDis = Integer.MAX_VALUE;
				for (int[] com : combs) {
					int dis = Math.min(Math.abs(com[0] - home[0]) + Math.abs(com[1] - home[1]), minHomeDis);
					minHomeDis = Math.min(dis, minHomeDis);
				}
				cityHomeDis += minHomeDis;
			}
			result = Math.min(result, cityHomeDis);
		}
		System.out.println(result);
	}

	private static List<List<int[]>> getComb(List<int[]> chickens, int m) {
		List<List<int[]>> result = new ArrayList<>();
		runComb(result, chickens, m, 0, new ArrayList<>());
		return result;
	}

	private static void runComb(List<List<int[]>> result, List<int[]> chickens, int m, int start, List<int[]> temp) {
		if (temp.size() == m) {
			result.add(new ArrayList<>(temp));
			return;
		}
		for (int i = start; i < chickens.size(); i++) {
			temp.add(chickens.get(i));
			runComb(result, chickens, m, start + 1, temp);
			temp.remove(temp.size() - 1);
		}
	}
}
