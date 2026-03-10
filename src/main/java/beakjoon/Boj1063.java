package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Boj1063 {
	// king = 1, rock = 2;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		Map<String, int[]> map = new HashMap<>();
		String[] commands = new String[] {"R", "L", "B", "T", "RT", "LT", "RB", "LB"};
		int[][] moves = new int[][] {{0, 1}, {0, -1}, {1, 0}, {-1, 0}, {-1, 1}, {-1, -1}, {1, 1}, {1, -1}};
		for (int i = 0; i < commands.length; i++) {
			map.put(commands[i], moves[i]);
		}
		int[][] chess = new int[8][8];
		String[] king = st.nextToken().split("");
		String[] rock = st.nextToken().split("");

		int kx = 8 - Integer.parseInt(king[1]);
		int ky = king[0].charAt(0) - 'A';

		int rx = 8 - Integer.parseInt(rock[1]);
		int ry = rock[0].charAt(0) - 'A';

		chess[rx][ry] = 2;

		int t = Integer.parseInt(st.nextToken());
		for (int i = 0; i < t; i++) {
			st = new StringTokenizer(br.readLine());
			int[] move = map.get(st.nextToken());
			int nx = kx + move[0];
			int ny = ky + move[1];
			if (nx < 0 || ny < 0 || nx >= 8 || ny >= 8) continue;
			if (chess[nx][ny] == 2) {
				int rnx = rx + move[0];
				int rny = ry + move[1];
				if (rnx < 0 || rny < 0 || rnx >= 8 || rny >= 8) continue;
				chess[rnx][rny] = 2;
				chess[rx][ry] = 0;
				rx = rnx;
				ry = rny;
			}
			kx = nx;
			ky = ny;
		}
		char a = (char)(ky + 'A');
		char b = (char)(ry + 'A');
		System.out.println(a + "" + (8 - kx));
		System.out.println(b + "" + (8 - rx));

	}

}
