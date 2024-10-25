package beakjoon.DfsBfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj4179 {
	static int[] dx = new int[] {1, 0, 0, -1};
	static int[] dy = new int[] {0, 1, -1, 0};
	static Queue<Node> jihoon;
	static Queue<Node> fire;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int R = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		jihoon = new LinkedList<>();
		fire = new LinkedList<>();
		String[][] miro = new String[C][R];
		for (int i = 0; i < C; i++) {
			st = new StringTokenizer(br.readLine());
			String[] rows = st.nextToken().split("");
			for (int j = 0; j < R; j++) {
				if (rows[j].equals("J")) {
					jihoon.offer(new Node(i, j));
				} else if (rows[j].equals("F")) {
					fire.offer(new Node(i, j));
				}
				miro[i][j] = rows[j];
			}
		}
		bfs(miro);
	}

	private static void bfs(String[][] miro) {

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
