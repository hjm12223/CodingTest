package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj5427 {
	static int[][] fireDist;
	static int[][] sangDist;
	static int[] dx = new int[] {1, 0, 0, -1};
	static int[] dy = new int[] {0, 1, -1, 0};
	static int INF = 987654321;
	static int N,M;
	static char[][] arr;
	static boolean[][] isVisited;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int t = 0; t < T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			M = Integer.parseInt(st.nextToken());
			N = Integer.parseInt(st.nextToken());
			arr = new char[N][M];

			Queue<int[]> exit = new LinkedList<>();
			Queue<int[]> fire = new LinkedList<>();

			sangDist = new int[N][M];
			fireDist = new int[N][M];

			for (int i = 0; i < N; i++) {
				String str = br.readLine();
				Arrays.fill(sangDist[i], INF);
				Arrays.fill(fireDist[i], INF);
				for (int j = 0; j < M; j++) {
					if (str.charAt(j) == '@') {
						exit.offer(new int[] {i, j});
						sangDist[i][j] = 0;
					} else if (str.charAt(j) == '*') {
						fire.offer(new int[] {i, j});
						fireDist[i][j] = 0;
					}
					arr[i][j] = str.charAt(j);
				}
			}
			fireBfs(fire);
			int[] sangBfs = bfs(exit);
			if (sangBfs == null){
				System.out.println("IMPOSSIBLE");
			}else {
				System.out.println(sangDist[sangBfs[0]][sangBfs[1]]+1);
			}
		}
	}

	private static int[] bfs(Queue<int[]> q) {
		isVisited = new boolean[N][M];
		while (!q.isEmpty()){
			int[] curr = q.poll();
			if (fireDist[curr[0]][curr[1]] <= sangDist[curr[0]][curr[1]]) continue;
			if (isVisited[curr[0]][curr[1]]) continue;
			isVisited[curr[0]][curr[1]] = true;
			for (int d = 0 ; d < 4 ; d++){
				int nx = curr[0] + dx[d];
				int ny = curr[1] + dy[d];
				if (nx < 0 || ny < 0 || nx>= N || ny >= M ) {
					return new int[]{curr[0], curr[1]};
				}
				if (arr[nx][ny] == '#')continue;
				if (sangDist[nx][ny] > sangDist[curr[0]][curr[1]] +1){
					sangDist[nx][ny] = sangDist[curr[0]][curr[1]] +1;
					q.offer(new int[]{nx,ny});
				}
			}
		}
		return null;
	}
	private static void fireBfs(Queue<int[]> q) {
		isVisited = new boolean[N][M];
		while (!q.isEmpty()){
			int[] curr = q.poll();
			if (isVisited[curr[0]][curr[1]]) continue;
			isVisited[curr[0]][curr[1]] = true;
			for (int d = 0 ; d < 4 ; d++){
				int nx = curr[0] + dx[d];
				int ny = curr[1] + dy[d];
				if (nx < 0 || ny < 0 || nx>= N || ny >= M )  continue;
				if (arr[nx][ny] == '#')continue;
				if (fireDist[nx][ny] > fireDist[curr[0]][curr[1]] +1){
					fireDist[nx][ny] = fireDist[curr[0]][curr[1]] +1;
					q.offer(new int[]{nx,ny});
				}
			}
		}
	}
}
