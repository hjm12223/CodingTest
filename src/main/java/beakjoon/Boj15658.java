package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj15658 {
	static int N;
	static int[] numbers, operators;

	static int maxRes = Integer.MIN_VALUE;
	static int minRes = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		numbers = new int[N];
		operators = new int[4];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			numbers[i] = Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < 4; i++) {
			operators[i] = Integer.parseInt(st.nextToken());
		}
		dfs(1, new StringBuilder());
		System.out.println(maxRes);
		System.out.println(minRes);
	}

	private static void dfs(int depth, StringBuilder sb) {
		if (depth == N) {
			int res = numbers[0];
			for (int i = 0; i < sb.length(); i++) {
				char oper = sb.charAt(i);
				switch (oper) {
					case '+':
						res += numbers[i + 1];
						break;
					case '-':
						res -= numbers[i + 1];
						break;
					case '*':
						res *= numbers[i + 1];
						break;
					case '/':
						res /= numbers[i + 1];
						break;
				}
			}
			maxRes = Math.max(maxRes, res);
			minRes = Math.min(minRes, res);
			return;
		}
		for (int i = 0; i < 4; i++) {
			if (operators[i] == 0) continue;
			switch (i) {
				case 0:
					operators[i]--;
					sb.append("+");
					dfs(depth + 1, sb);
					sb.deleteCharAt(sb.length() - 1);
					operators[i]++;
					break;
				case 1:
					operators[i]--;
					sb.append("-");
					dfs(depth + 1, sb);
					sb.deleteCharAt(sb.length() - 1);
					operators[i]++;
					break;
				case 2:
					operators[i]--;
					sb.append("*");
					dfs(depth + 1, sb);
					sb.deleteCharAt(sb.length() - 1);
					operators[i]++;
					break;
				case 3:
					operators[i]--;
					sb.append("/");
					dfs(depth + 1, sb);
					sb.deleteCharAt(sb.length() - 1);
					operators[i]++;
					break;
			}
		}
	}
}
