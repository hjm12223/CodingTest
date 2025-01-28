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
import java.util.StringTokenizer;

public class Boj16920 {
	static int N, M, P;
	static String[][] arr;

	static int[][] move = new int[][] {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
	static boolean[][] visited;
	static int[] result;
	static int[] initMoveCnt;

	/*
	result[value] 에 정답을 저장
	 */
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		P = Integer.parseInt(st.nextToken());
		arr = new String[N][M];
		visited = new boolean[N][M];
		result = new int[P];
		List<ArrayDeque<Node>> q = new ArrayList<>(P);

		for (int i = 0; i < P; i++) {
			q.add(new ArrayDeque<>());
		}

		initMoveCnt = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		for (int i = 0; i < N; i++) {
			String[] a = br.readLine().split("");
			arr[i] = a;
			for (int j = 0; j < M; j++) {
				if (!a[j].equals(".") && !a[j].equals("#")) {
					int value = Integer.parseInt(a[j]);
					result[value - 1]++;
					q.get(value - 1).offer(new Node(value, i, j, initMoveCnt[value - 1]));
					visited[i][j] = true;
				}
			}
		}
		while (true) {
			int cnt = 0;
			for (int i = 0; i < q.size(); i++) {
				if (bfs(q.get(i))) {
					cnt++;
				}
			}
			if (cnt == 0) break;
		}
		for (int i : result) {
			bw.write(i + " ");
		}
		bw.flush();
		bw.close();
	}

	private static boolean bfs(ArrayDeque<Node> q) {
		ArrayDeque<Node> nextQueue = new ArrayDeque<>();
		boolean expanded = false;
		while (!q.isEmpty()) {
			Node curr = q.poll();
			if (curr.moveCnt > 1) {
				for (int d = 0; d < 4; d++) {
					int nx = curr.x + move[d][0];
					int ny = curr.y + move[d][1];
					if (nx < 0 || ny < 0 || nx >= N || ny >= M || !arr[nx][ny].equals(".")) continue;
					if (!visited[nx][ny]) {
						visited[nx][ny] = true;
						q.add(new Node(curr.index, nx, ny, curr.moveCnt - 1));
						result[curr.index - 1]++;
						arr[nx][ny] = String.valueOf(curr.index);
						expanded = true;
					}
				}
			} else if (curr.moveCnt == 1) {
				for (int d = 0; d < 4; d++) {
					int nx = curr.x + move[d][0];
					int ny = curr.y + move[d][1];
					if (nx < 0 || ny < 0 || nx >= N || ny >= M || !arr[nx][ny].equals(".")) continue;
					if (!visited[nx][ny]) {
						visited[nx][ny] = true;
						nextQueue.add(new Node(curr.index, nx, ny, initMoveCnt[curr.index - 1]));
						result[curr.index - 1]++;
						arr[nx][ny] = String.valueOf(curr.index);
						expanded = true;
					}
				}
			}
		}
		q.addAll(nextQueue);
		return expanded;
	}

	private static class Node {
		int index;
		int x;
		int y;
		int moveCnt;

		public Node(int index, int x, int y, int moveCnt) {
			this.index = index;
			this.x = x;
			this.y = y;
			this.moveCnt = moveCnt;
		}
	}
}
