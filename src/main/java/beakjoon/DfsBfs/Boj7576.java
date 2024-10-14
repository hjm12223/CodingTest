package beakjoon.DfsBfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj7576 {
	static int[] dx = new int[] {1, 0, -1, 0};
	static int[] dy = new int[] {0, 1, 0, -1};

	static boolean[][] visited;
	static int maxValue = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int M = Integer.parseInt(st.nextToken()); // 상자의 가로 칸의 수
		int N = Integer.parseInt(st.nextToken()); // 상자의 세로 칸의 수
		int[][] arr = new int[N][M];

		Queue<Node> q = new LinkedList<>();

		visited = new boolean[N][M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				int value = Integer.parseInt(st.nextToken());
				if (value == 1) {
					q.offer(new Node(i, j, 0));
					visited[i][j] = true;
				}
				arr[i][j] = value;
			}
		}

		bfs(arr, q, N, M);

		boolean isComplete = true;

		for (int[] ints : arr) {
			for (int anInt : ints) {
				if (anInt == 0){
					isComplete = false;
				}
			}
		}
		if (isComplete){
			System.out.println(maxValue);
		}else {
			System.out.println(-1);
		}
	}

	private static void bfs(int[][] arr, Queue<Node> q, int n, int m) {
		while (!q.isEmpty()){
			Node curr = q.poll();
			if (maxValue < curr.value) maxValue = curr.value;
			for (int k= 0 ; k < 4 ; k++){
				int nx = curr.x + dx[k];
				int ny = curr.y + dy[k];
				if (nx < 0 || ny < 0 || nx >= n || ny >= m || arr[nx][ny] == -1 || visited[nx][ny]) continue;
				visited[nx][ny] = true;
				q.offer(new Node(nx,ny,curr.value+1));
				arr[nx][ny] = 1;
			}
		}
	}

	public static class Node {
		int x;
		int y;
		int value;

		public Node(int x, int y, int value) {
			this.x = x;
			this.y = y;
			this.value = value;
		}
	}
}
