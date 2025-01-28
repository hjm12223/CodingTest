package beakjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj17071 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		Queue<Integer> q = new ArrayDeque<>();

		if (N == K) {
			System.out.println(0);
			return;
		}

		boolean[][] visited = new boolean[2][500_001];
		q.offer(N);
		int time = 0;
		visited[0][N] = true;
		while (!q.isEmpty()) {
			time++;
			K += time;
			if (K > 500_000) {
				bw.write(String.valueOf(-1));
				break;
			}
			int size = q.size();
			for (int i = 0; i < size; i++) {
				int curr = q.poll();
				int[] move = new int[] {curr + 1, curr - 1, curr * 2};
				for (int nx : move) {
					if (nx < 0 || nx > 500_000 || visited[time % 2][nx]) continue;
					q.offer(nx);
					visited[time % 2][nx] = true;
				}
			}
			if (visited[time % 2][K]) {
				bw.write(String.valueOf(time));
				break;
			}
		}
		bw.flush();
		bw.close();
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
