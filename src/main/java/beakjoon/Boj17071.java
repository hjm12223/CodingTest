package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Boj17071 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		ArrayDeque<Node> q = new ArrayDeque<>();

		q.offer(new Node(N, 0, M));
		int time = 0;
		boolean[][] isVisited = new boolean[2][500_001];
		while (!q.isEmpty()) {
			Node curr = q.poll();
			time++;
			if (curr.curr == curr.target) {
				System.out.println(curr.cnt);
				return;
			}
			if (curr.curr > 500_000 || curr.target > 500_000) continue;
			if (isVisited[time % 2][curr.curr]) continue;
			isVisited[time % 2][curr.curr] = true;
			if (curr.curr * 2 <= 500_000) {
				int nextIndex = curr.curr * 2;
				int nextCnt = curr.cnt + 1;
				int nextTarget = curr.target + time;
				q.addFirst(new Node(nextIndex, nextCnt, nextTarget));
			}

			if (curr.curr + 1 <= 500_000) {
				int nextIndex = curr.curr + 1;
				int nextCnt = curr.cnt + 1;
				int nextTarget = curr.target + time;
				q.addLast(new Node(nextIndex, nextCnt, nextTarget));
			}

			if (curr.curr - 1 >= 0) {
				int nextIndex = curr.curr - 1;
				int nextCnt = curr.cnt + 1;
				int nextTarget = curr.target + time;
				q.addLast(new Node(nextIndex, nextCnt, nextTarget));
			}
		}
		System.out.println(-1);
	}

	private static class Node {
		int curr;
		int cnt;
		int target;

		public Node(int curr, int cnt, int target) {
			this.curr = curr;
			this.cnt = cnt;
			this.target = target;
		}
	}
}
/*
수빈이는 동생과 숨바꼭질을 하고 있다.
 수빈이는 현재 점 N(0 ≤ N ≤ 500,000)에 있고,
 동생은 점 K(0 ≤ K ≤ 500,000)에 있다. 수빈이는 걷거나 순간이동을 할 수 있다.
 만약, 수빈이의 위치가 X일 때 걷는다면 1초 후에 X-1 또는 X+1로 이동하게 된다.
 순간이동을 하는 경우에는 1초 후에 2*X의 위치로 이동하게 된다. 동생은 항상 걷기만 한다.
 동생은 항상 매 초마다 이동을 하며, 이동은 가속이 붙는다. 동생이 이동하는 거리는 이전에 이동한 거리보다 1을 더한 만큼 이동한다.
 즉, 동생의 처음 위치는 K, 1초가 지난 후 위치는 K+1, 2초가 지난 후 위치는 K+1+2, 3초가 지난 후의 위치는 K+1+2+3이다.
 */
