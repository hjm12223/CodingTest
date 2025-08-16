package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;

public class Boj2151 {
	static int N;
	static char[][] arr;
	static int[][] moves = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}}; // 상,우,하,좌
	static int[][][] dist;
	static List<int[]> doors = new ArrayList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr = new char[N][N];

		for (int i = 0; i < N; i++) {
			String line = br.readLine();
			for (int j = 0; j < N; j++) {
				arr[i][j] = line.charAt(j);
				if (arr[i][j] == '#') {
					doors.add(new int[] {i, j});
				}
			}
		}
		dist = new int[N][N][4];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				Arrays.fill(dist[i][j], Integer.MAX_VALUE);
			}
		}
		int sx = doors.get(0)[0];
		int sy = doors.get(0)[1];
		Deque<int[]> q = new ArrayDeque<>();
		for (int d = 0; d < 4; d++) {
			dist[sx][sy][d] = 0;
			q.offer(new int[] {sx, sy, d});
		}

		while (!q.isEmpty()) {
			int[] curr = q.poll();
			int x = curr[0];
			int y = curr[1];
			int dir = curr[2];
			int nx = x + moves[dir][0];
			int ny = y + moves[dir][1];
			if (nx < 0 || ny < 0 || nx >= N || ny >= N || arr[nx][ny] == '*') continue;
			if (arr[nx][ny] == '.' || arr[nx][ny] == '#') {
				if (dist[nx][ny][dir] > dist[x][y][dir]) {
					dist[nx][ny][dir] = dist[x][y][dir];
					q.offerFirst(new int[] {nx, ny, dir});
				}
			} else if (arr[nx][ny] == '!') {
				if (dist[nx][ny][dir] > dist[x][y][dir]) {
					dist[nx][ny][dir] = dist[x][y][dir];
					q.offerFirst(new int[] {nx, ny, dir});
				}
				// 회전
				for (int d = 0; d < 4; d++) {
					if (dir == d || dir == (d + 2) % 4) continue;
					if (dist[nx][ny][d] > dist[x][y][dir] + 1) {
						dist[nx][ny][d] = dist[x][y][dir] + 1;
						q.offerLast(new int[] {nx, ny, d});
					}
				}
			}
		}
		System.out.println("q = " + Arrays.deepToString(dist)
		);
		int ex = doors.get(1)[0];
		int ey = doors.get(1)[1];
		int result = Integer.MAX_VALUE;
		for (int d = 0; d < 4; d++) {
			result = Math.min(dist[ex][ey][d], result);
		}
		System.out.println(result);
	}
}
