package beakjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj3079 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		long N = Integer.parseInt(st.nextToken()); // 입국 심사대
		long M = Integer.parseInt(st.nextToken()); // 친구의 수 심사대
		Queue<Immigration> pq = new PriorityQueue<>((o1, o2) -> Long.compare(o1.curr + o1.time, o2.curr + o2.time));
		for (long i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			long time = Integer.parseInt(st.nextToken());
			pq.offer(new Immigration(time, 0));
		}

		long totalTime = 0;
		for (int i = 0; i < M; i++) {
			Immigration curr = pq.poll();
			curr.curr += curr.time;
			totalTime = Math.max(totalTime, curr.curr);
			pq.offer(curr);
		}
		bw.write(String.valueOf(totalTime));
		bw.flush();
		bw.close();
	}

	private static class Immigration {
		long time;
		long curr;

		public Immigration(long time, long curr) {
			this.time = time;
			this.curr = curr;
		}
	}
}
