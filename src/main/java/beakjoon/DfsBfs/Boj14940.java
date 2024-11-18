package beakjoon.DfsBfs;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj14940 {
	static int[] dx = new int[] {1, 0, 0, -1};
	static int[] dy = new int[] {0, 1, -1, 0};
	static int[][] dist;
	static int N;
	static int M;
	static boolean[][] isVisited;
	static int[][] arr;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[N][M];
		dist = new int[N][M];
		isVisited = new boolean[N][M];
		for (int i = 0; i < N; i++) {
			Arrays.fill(dist[i], -1);
		}
		Queue<Node> q = new LinkedList<>();
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				int value = Integer.parseInt(st.nextToken());
				if (value == 2) {
					q.offer(new Node(i, j));
					isVisited[i][j] = true;
					dist[i][j] = 0;
				} else if (value == 0) {
					dist[i][j] = -2;
				}
				arr[i][j] = value;
			}
		}
		bfs(q);
		for (int i = 0; i < N; i++) {
			StringBuilder sb = new StringBuilder();
			for (int j = 0; j < M; j++) {
				if (dist[i][j] == -2) {
					sb.append(0);
				} else {
					sb.append(dist[i][j]);
				}
				if (j < M - 1) {
					sb.append(" "); // 마지막 요소에는 공백 추가하지 않음
				}
			}
			bw.write(sb.toString());
			bw.write("\n");
		}
		bw.flush();
		bw.close();
	}

	private static void bfs(Queue<Node> q) {
		while (!q.isEmpty()) {
			Node curr = q.poll();
			for (int i = 0; i < 4; i++) {
				int nextX = dx[i] + curr.x;
				int nextY = dy[i] + curr.y;
				if (nextX < 0 || nextY < 0 || nextX >= N || nextY >= M || dist[nextX][nextY] == -2) continue;
				if (isVisited[nextX][nextY]) continue;
				isVisited[nextX][nextY] = true;
				if (dist[nextX][nextY] < dist[curr.x][curr.y]) {
					dist[nextX][nextY] = dist[curr.x][curr.y] + 1;
					q.offer(new Node(nextX, nextY));
				}
			}
		}
	}

	public static class Node {
		int x;
		int y;

		public Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}
