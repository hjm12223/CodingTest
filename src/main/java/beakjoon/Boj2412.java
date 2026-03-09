package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;
import java.util.TreeMap;

/*
1. 시작점은 0,0
2. |a-x| <= 2 && |b-y| <= 2 일 경우 해당 암벽으로 이동가능
3. T를 도달하기 위해서 최소 몇번을 움직여야 하나

Key값을 만들고 해당 키값을 기반으로 하여 갈 수 있는지 없는지를 판단?
TODO :

 */
public class Boj2412 {
	static Map<Integer, List<Integer>> map = new TreeMap<>();
	static int T;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken()); // 암벽
		T = Integer.parseInt(st.nextToken()); // 목표
		int[][] arr = new int[N][2];
		for (int i = 0; i < N; i++) {
			arr[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		}
		Arrays.sort(arr, (o1, o2) -> {
				return o1[1] - o2[1];
			}
		);
		for (int i = 0; i < arr.length; i++) {
			map.putIfAbsent(arr[i][1], new ArrayList<>());
		}
		for (int i = 0; i < arr.length; i++) {
			map.get(arr[i][1]).add(arr[i][0]);
		}
		System.out.println(bfs(0, 0));
	}

	private static int bfs(int sx, int sy) {
		Queue<int[]> q = new ArrayDeque<>();
		q.offer(new int[] {sx, sy, 0});
		while (!q.isEmpty()) {
			int[] curr = q.poll();
			int cx = curr[0];
			int cy = curr[1];
			if (cy == T) {
				return curr[2];
			}
			int start = cy - 2;
			for (int ny = Math.max(start, 0); ny <= cy + 2; ny++) {
				if (!map.containsKey(ny)) continue;
				List<Integer> x_list = map.get(ny);
				for (int j = 0; j < x_list.size(); ) {
					int nx = x_list.get(j);
					if (Math.abs(nx - cx) <= 2) {
						q.offer(new int[] {nx, ny, curr[2] + 1});
						x_list.remove(j);
					} else {
						j++;
					}
				}
			}
		}
		return -1;
	}
}
