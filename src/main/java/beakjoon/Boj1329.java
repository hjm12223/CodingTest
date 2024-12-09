package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Boj1329 {
	static String[] words;
	static int maxSum = Integer.MIN_VALUE;
	static boolean[] used = new boolean[10];
	static int[] alphabetValue = new int[26];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		words = new String[N];
		for (int i = 0; i < N; i++) {
			words[i] = br.readLine();
		}
		int[] weight = new int[26];
		for (String word : words) {
			int power = 1;
			for (int i = word.length() - 1; i >= 0; i--) {
				weight[word.charAt(i) - 'A'] += power;
				power *= 10;
			}
		}
		List<Integer> alphabetOrder = new ArrayList<>();
		for (int i = 0; i < 26; i++) {
			if (weight[i] > 0) {
				alphabetOrder.add(i);
			}
		}
		alphabetOrder.sort((o1, o2) -> weight[o2] - weight[o1]);
		backtracking(alphabetOrder, 0);
		System.out.println(maxSum);
	}

	private static void backtracking(List<Integer> alphabetOrder, int depth) {
		if (depth == alphabetOrder.size()) {
			int b = calculateSum();
			maxSum = Math.max(maxSum, b);
			return;
		}
		for (int i = 0; i <= 9; i++) {
			if (!used[i]) {
				used[i] = true;
				alphabetValue[alphabetOrder.get(depth)] = i;
				backtracking(alphabetOrder, depth + 1);
				used[i] = false;
				alphabetValue[alphabetOrder.get(depth)] = 0;
			}

		}
	}

	static int calculateSum() {
		int sum = 0;
		for (String word : words) {
			int value = 0;
			for (char c : word.toCharArray()) {
				value = value * 10 + alphabetValue[c - 'A'];
			}
			sum += value;
		}
		return sum;
	}
}
