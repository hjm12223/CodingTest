package beakjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj1966 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int T = Integer.parseInt(br.readLine());

		while (T-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken()); // 문서 개수
			int M = Integer.parseInt(st.nextToken()); // 찾고자 하는 문서 위치

			Queue<int[]> q = new ArrayDeque<>();
			Queue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());

			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				int priority = Integer.parseInt(st.nextToken());
				q.offer(new int[] {i, priority});
				pq.offer(priority);
			}

			int cnt = 0;
			while (!q.isEmpty()) {
				int[] current = q.poll();

				if (current[1] == pq.peek()) {
					cnt++;
					pq.poll();
					if (current[0] == M) {
						bw.write(cnt + "\n");
						break;
					}
				} else {
					q.offer(current);
				}
			}
		}
		bw.flush();
		bw.close();
	}
}
