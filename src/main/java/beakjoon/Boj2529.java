package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Boj2529 {
	static char[] operations;
	static int K;
	static int N;
	static List<String> list = new ArrayList<>();
	static boolean[] visited = new boolean[10];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K += N + 1;
		operations = new char[N];
		st = new StringTokenizer(br.readLine());

		for (int i = 0; i < N; i++) {
			operations[i] = st.nextToken().charAt(0);
		}

		backTracking("", 0);
		Collections.sort(list);
		System.out.println(list.get(list.size() - 1));
		System.out.println(list.get(0));
	}

	private static void backTracking(String number, int depth) {
		if (depth == K) {
			list.add(number);
			return;
		}
		for (int i = 0; i <= 9; i++) {
			if (visited[i]) continue;
			if (depth == 0 || checkOperation(number.charAt(depth - 1) - '0', operations[depth - 1], i)) {
				visited[i] = true;
				backTracking(number + i, depth + 1);
				visited[i] = false;
			}
		}
	}

	private static boolean checkOperation(int a, char operation, int b) {
		if (operation == '>') return a > b;
		else return a < b;
	}
}