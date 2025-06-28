package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj22944 {
	static char[][] arr;
	static int[][] moves = new int[][] {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
	static int[][] hpStatus;
	static int N, H, D, sx, sy, ex, ey;
	static int result = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken()); // 현재 체력
		D = Integer.parseInt(st.nextToken()); // 우산의 내구도
		arr = new char[N][N];
		hpStatus = new int[N][N];
		for (int i = 0; i < N; i++) {
			String line = br.readLine();
			for (int j = 0; j < N; j++) {
				char c = line.charAt(j);
				if (c == 'S') {
					sx = i;
					sy = j;
				} else if (c == 'E') {
					ex = i;
					ey = j;
				}
				arr[i][j] = c;
			}
		}
		hpStatus[sx][sy] = H;
		bfs();
		System.out.println(result == Integer.MAX_VALUE ? -1 : result);
	}

	private static void bfs() {
		Queue<Node> q = new ArrayDeque<>();
		q.offer(new Node(sx, sy, H, 0, 0));
		while (!q.isEmpty()) {
			Node curr = q.poll();
			if (curr.x == ex && curr.y == ey) {
				result = Math.min(curr.cnt, result);
				continue;
			}
			for (int[] move : moves) {
				int nx = move[0] + curr.x;
				int ny = move[1] + curr.y;
				if (nx < 0 || ny < 0 || nx >= N || ny >= N) continue;
				int durability = curr.durability;
				int hp = curr.hp;
				if (arr[nx][ny] == 'U') durability = D;
				if (durability > 0) durability--;
				else hp--;
				if (hp <= 0) continue;
				if (hpStatus[nx][ny] < curr.hp + curr.durability) {
					hpStatus[nx][ny] = curr.hp + curr.durability;
					q.offer(new Node(nx, ny, hp, durability, curr.cnt + 1));
				}
			}
		}
	}

	private static class Node {
		int x;
		int y;
		int hp;
		int durability;
		int cnt;

		public Node(int x, int y, int hp, int durability, int cnt) {
			this.x = x;
			this.y = y;
			this.hp = hp;
			this.durability = durability;
			this.cnt = cnt;
		}
	}
}
