package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj4991 {
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	static int h, w;
	static char[][] map;
	static List<Point> dirties;
	static int[][] distances;
	static int answer;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		while (true) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			w = Integer.parseInt(st.nextToken());
			h = Integer.parseInt(st.nextToken());

			if (w == 0 && h == 0) break;

			map = new char[h][w];
			dirties = new ArrayList<>();
			Point start = null;

			for (int i = 0; i < h; i++) {
				String line = br.readLine();
				for (int j = 0; j < w; j++) {
					map[i][j] = line.charAt(j);
					if (map[i][j] == 'o') {
						start = new Point(i, j);
					} else if (map[i][j] == '*') {
						dirties.add(new Point(i, j));
					}
				}
			}
			dirties.add(start);
			distances = new int[dirties.size()][dirties.size()];

			if (!isCalculate()) {
				System.out.println(-1);
				continue;
			}

			answer = Integer.MAX_VALUE;
			boolean[] isVisited = new boolean[dirties.size()];
			dfs(dirties.size() - 1, 0, 0, isVisited);

			System.out.println(answer == Integer.MAX_VALUE ? -1 : answer);
		}
	}

	private static void dfs(int current, int count, int totalDist, boolean[] isVisited) {
		if (count == dirties.size() - 1) {
			answer = Math.min(totalDist, answer);
			System.out.println("answer = " + answer);
			return;
		}

		if (totalDist >= answer) return;
		for (int next = 0; next < dirties.size() - 1; next++) {
			if (!isVisited[next]) {
				isVisited[next] = true;
				dfs(next, count + 1, totalDist + distances[current][next], isVisited);
				isVisited[next] = false;
			}
		}
	}

	public static boolean isCalculate() {
		for (int i = 0; i < dirties.size(); i++) {
			for (int j = i + 1; j < dirties.size(); j++) {
				Point from = dirties.get(i);
				Point to = dirties.get(j);
				int dist = bfs(from, to);
				if (dist == -1) {
					return false;
				} else {
					distances[i][j] = distances[j][i] = dist;
				}
			}
		}
		return true;
	}

	private static int bfs(Point from, Point to) {
		Queue<Point> q = new ArrayDeque<>();
		int[][] dist = new int[h][w];
		q.offer(from);
		for (int[] dis : dist) {
			Arrays.fill(dis, -1);
		}
		dist[from.x][from.y] = 0;
		while (!q.isEmpty()) {
			Point curr = q.poll();
			if (curr.x == to.x && curr.y == to.y) {
				return dist[curr.x][curr.y];
			}
			for (int d = 0; d < 4; d++) {
				int nx = dx[d] + curr.x;
				int ny = dy[d] + curr.y;
				if (nx < 0 || ny < 0 || nx >= h || ny >= w || dist[nx][ny] != -1 || map[nx][ny] == 'x') continue;
				dist[nx][ny] = dist[curr.x][curr.y] + 1;
				q.offer(new Point(nx, ny));
			}
		}
		return -1;
	}

	static class Point {
		int x, y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}