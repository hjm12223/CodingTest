package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj6068 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int t = Integer.MAX_VALUE;
		Queue<Job> pq = new PriorityQueue<>((o1, o2) -> o2.endLine - o1.endLine);
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int T = Integer.parseInt(st.nextToken()); // 일을 하는데 소요되는 시간
			int S = Integer.parseInt(st.nextToken()); // 일을 마쳐야 하는 시간
			pq.offer(new Job(T, S));
		}
		while (!pq.isEmpty()) {
			Job job = pq.poll();
			t = Math.min(t, job.endLine);
			t -= job.duringTime;
			System.out.println(t);
			if (t < 0) {
				System.out.println(-1);
				return;
			}
		}
		System.out.println(t);
	}

	private static class Job {
		int duringTime;
		int endLine;

		public Job(int duringTime, int endLine) {
			this.duringTime = duringTime;
			this.endLine = endLine;
		}
	}
}
