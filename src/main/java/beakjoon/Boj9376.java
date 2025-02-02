package beakjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj9376 {
	static int h, w;
	static int[][] move = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
	static char[][] map;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int T = Integer.parseInt(br.readLine());
		while (T-- > 0) {
			int result = Integer.MAX_VALUE;
			StringTokenizer st = new StringTokenizer(br.readLine());
			h = Integer.parseInt(st.nextToken());
			w = Integer.parseInt(st.nextToken());
			map = new char[h + 2][w + 2];
			List<Point> points = new ArrayList<>(3);
			for (int i = 0; i < h + 2; i++)
				Arrays.fill(map[i], '.');
			for (int i = 1; i <= h; i++) {
				String line = br.readLine();
				for (int j = 1; j <= w; j++) {
					char point = line.charAt(j - 1);
					map[i][j] = point;
					if (point == '$') {
						points.add(new Point(i, j));
					}
				}
			}
			int[][] dist1 = bfs(points.get(0));
			int[][] dist2 = bfs(points.get(1));
			int[][] dist3 = bfs(new Point(0, 0));
			for (int i = 0; i < h + 2; i++) {
				for (int j = 0; j < w + 2; j++) {
					if (map[i][j] == '*') continue;
					int value = dist1[i][j] + dist2[i][j] + dist3[i][j];
					if (map[i][j] == '#') value -= 2;
					result = Math.min(value, result);
				}
			}
			bw.write(String.valueOf(result));
			bw.newLine();
		}
		bw.flush();
		bw.close();
	}

	private static int[][] bfs(Point point) {
		int[][] dist = new int[h + 2][w + 2];
		for (int i = 0; i < h + 2; i++) {
			Arrays.fill(dist[i], Integer.MAX_VALUE);
		}
		Queue<Point> q = new ArrayDeque<>();
		q.offer(point);
		dist[point.x][point.y] = 0;
		while (!q.isEmpty()) {
			Point curr = q.poll();
			for (int d = 0; d < 4; d++) {
				int nx = move[d][0] + curr.x;
				int ny = move[d][1] + curr.y;
				if (nx < 0 || ny < 0 || nx >= h + 2 || ny >= w + 2 || map[nx][ny] == '*') continue;
				int nextDist = dist[curr.x][curr.y];
				if (map[nx][ny] == '#') nextDist++;
				if (dist[nx][ny] > nextDist) {
					dist[nx][ny] = nextDist;
					q.offer(new Point(nx, ny));
				}
			}
		}
		return dist;
	}

	private static class Point {
		int x;
		int y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}