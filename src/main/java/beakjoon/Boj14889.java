package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Boj14889 {
	static int N;
	static int[][] arr;
	static List<List<Integer>> combList;
	static int result = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr = new int[N][N];
		for (int i = 0; i < N; i++) {
			arr[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		}
		combList = new ArrayList<>();
		comb(combList, new ArrayList<>(), N, 0, N / 2);
		for (List<Integer> team1 : combList) {
			List<Integer> team2 = new ArrayList<>();
			for (int i = 0; i < N; i++) {
				if (!team1.contains(i)) {
					team2.add(i);
				}
			}
			cal(team1, team2);
		}

		System.out.println(result);
	}

	private static void cal(List<Integer> team1, List<Integer> team2) {
		int team1Score = 0;
		int team2Score = 0;
		for (int i = 0; i < team1.size(); i++) {
			for (int j = i + 1; j < team1.size(); j++) {
				Integer r = team1.get(i);
				Integer c = team1.get(j);
				team1Score += arr[r][c] + arr[c][r];
			}
		}
		for (int i = 0; i < team2.size(); i++) {
			for (int j = i + 1; j < team2.size(); j++) {
				Integer r = team2.get(i);
				Integer c = team2.get(j);
				team2Score += arr[r][c] + arr[c][r];
			}
		}
		result = Math.min(Math.abs(team1Score - team2Score), result);
	}

	private static void findMinValue(Integer row, Integer col) {
		int sum = arr[row][col] + arr[col][row];
		for (int i = 0; i < combList.size(); i++) {

			if (combList.get(i).contains(row) || combList.get(i).contains(col)) continue;
			Integer r = combList.get(i).get(0);
			Integer c = combList.get(i).get(1);
			int twoSum = arr[r][c] + arr[c][r];
			System.out.println(Math.abs(twoSum - sum));
			result = Math.min(Math.abs(twoSum - sum), result);
		}

	}

	private static void comb(List<List<Integer>> combList, List<Integer> list, int n, int depth, int r) {
		if (list.size() == r) {
			combList.add(new ArrayList<>(list));
			return;
		}
		for (int i = depth; i < n; i++) {
			list.add(i);
			comb(combList, list, n, i + 1, r);
			list.remove(list.size() - 1);
		}
	}
}
