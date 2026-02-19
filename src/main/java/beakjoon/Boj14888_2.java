package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Boj14888_2 {
	static int[] values, operators;
	static int minValue = Integer.MAX_VALUE;
	static int maxValue = Integer.MIN_VALUE;

	static int R = 0;
	static int N;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		values = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		operators = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

		for (int i = 0; i < operators.length; i++) {
			R += operators[i];
		}
		dfs(0, values[0]);
		System.out.println(maxValue);
		System.out.println(minValue);

	}

	static void dfs(int depth, int value) {
		if (depth == R) {
			minValue = Math.min(minValue, value);
			maxValue = Math.max(maxValue, value);
			return;
		}
		depth += 1;
		if (operators[0] != 0) {
			operators[0]--;
			dfs(depth, value + values[depth]);
			operators[0]++;
		}
		if (operators[1] != 0) {
			operators[1]--;
			dfs(depth, value - values[depth]);
			operators[1]++;
		}
		if (operators[2] != 0) {
			operators[2]--;
			dfs(depth, value * values[depth]);
			operators[2]++;
		}
		if (operators[3] != 0) {
			operators[3]--;
			dfs(depth, value / values[depth]);
			operators[3]++;
		}
	}
}
