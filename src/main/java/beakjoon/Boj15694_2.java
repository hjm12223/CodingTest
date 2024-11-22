package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj15694_2 {
	static int N, M;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 숫자
		M = Integer.parseInt(st.nextToken()); // 목표하는 배열
		boolean[] isVisited = new boolean[N + 1];
		int[] arr = new int[M];
		permu(arr, isVisited, 0);

	}

	private static void permu(int[] arr, boolean[] isVisited, int depth) {
		if (depth == M) {
			for (int i = 0; i < M; i++) {
				System.out.print(arr[i] + " ");
			}
			System.out.println();
			return;
		}
		for (int i = 1; i <= N; i++) {
			if (!isVisited[i]) {
				isVisited[i] = true;
				arr[depth] = i;
				permu(arr, isVisited, depth + 1);
				isVisited[i] = false;
			}
		}
	}
}
