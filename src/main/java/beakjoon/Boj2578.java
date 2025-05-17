package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Boj2578 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[][] bingo = new int[5][5];
		StringTokenizer st;
		Map<Integer, int[]> locations = new TreeMap<>();
		for (int i = 0; i < 5; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 5; j++) {
				int value = Integer.parseInt(st.nextToken());
				bingo[i][j] = value;
				locations.put(value, new int[] {i, j});
			}
		}
		boolean[][] visited = new boolean[5][5];
		int result = 0;
		for (int i = 0; i < 5; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 5; j++) {
				int value = Integer.parseInt(st.nextToken());
				int[] location = locations.get(value);
				int x = location[0];
				int y = location[1];
				visited[x][y] = true;
				result++;
				if (isBingo(visited)) {
					System.out.println(result);
					System.exit(1);
					break;
				}
			}
		}
	}

	private static boolean isBingo(boolean[][] visited) {
		int bingoCnt = 0;
		int c = 0;
		for (int i = 0; i < visited.length; i++) {
			if (visited[i][i]) c++;
		}
		if (c >= 4) bingoCnt += 1;
		c = 0;
		int temp = 0;
		for (int i = visited.length - 1; i >= 0; i--) {
			if (visited[temp][i]) c++;
			temp++;
		}
		if (c >= 4) bingoCnt += 1;

		for (int i = 0; i < visited.length; i++) {
			int cnt = 0;
			for (int j = 0; j < visited[i].length; j++) {
				if (visited[i][j]) cnt++;
			}
			if (cnt >= 5) {
				bingoCnt += 1;
			}
		}
		for (int i = 0; i < visited.length; i++) {
			int cnt = 0;
			for (int j = 0; j < visited[i].length; j++) {
				if (visited[j][i]) cnt++;
				else break;
			}
			if (cnt >= 5) bingoCnt += 1;
		}
		return bingoCnt >= 3;
	}
}
