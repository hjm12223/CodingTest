package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Boj16937 {
	static int N, M, K;
	static int[][] arr;
	static boolean[] visited;
	static int result = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		K = Integer.parseInt(br.readLine());
		visited = new boolean[K];
		arr = new int[K][2];

		for (int i = 0; i < K; i++) {
			arr[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		}
		dfs(new ArrayList<>(), 0);
		System.out.println(result);
	}

	private static void attach(List<int[]> list) {
		int[] first = list.get(0);
		int[] second = list.get(1);

		int[][] cases = new int[][] {
			{first[0], first[1], second[0], second[1]},
			{first[1], first[0], second[1], second[0]},
			{first[0], first[1], second[1], second[0]},
			{first[1], first[0], second[0], second[1]}
		};
		for (int[] c : cases) {
			int a_h = c[0];
			int a_w = c[1];
			int b_h = c[2];
			int b_w = c[3];

			if (a_w + b_w <= N && Math.max(a_h, b_h) <= M) {
				result = Math.max(result, a_w * a_h + b_w * b_h);
			}
			if (Math.max(a_w, b_w) <= N && a_h + b_h <= M) {
				result = Math.max(result, a_w * a_h + b_w * b_h);
			}
		}
	}

	/**
	 모눈종이
	 6 6
	 7 7
	 20 5

	 두개의 스티거를 정하고 붙이는 과정
	 dfs 를 통해서

	 dfs == comb
	 왜냐면 메커니즘이 똑같음
	 재귀해서 더한다음에 백트래킹
	 dfs
	 ...
	 넘기는지를 확인해야함
	 12
	 12

	 22
	 11
	 NC2 == 조합을 다구해
	 그 조합을 기반으로 수학공식을 활용해서 비교하고 최대 넓이를 구하면된다
	 홍종민 특 ) 수락을 잘못함
	 * 1줄 짜리 간결한 설명 ------- (1)
	 */
	private static void dfs(List<int[]> c, int depth) {
		if (c.size() == 2) {
			attach(c);
			return;
		}
		for (int i = depth; i < K; i++) {
			if (!visited[i]) {
				visited[i] = true;
				c.add(arr[i]);
				dfs(c, i + 1);
				c.remove(c.size() - 1);
				visited[i] = false;
			}
		}
	}
}
