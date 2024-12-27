package beakjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj11000 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int lecture = Integer.parseInt(st.nextToken());

		Queue<Lecture> lectures = new PriorityQueue<>();
		for (int i = 0; i < lecture; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			lectures.offer(new Lecture(start, end));
		}
		Queue<Integer> usingConference = new PriorityQueue<>(new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				return o2 - o1;
			}
		});
		usingConference.offer(lectures.poll().start);
		while (!lectures.isEmpty()) {
			Lecture currLecture = lectures.poll();
			if (!usingConference.isEmpty() && usingConference.peek() >= currLecture.end) {
				usingConference.poll();
			}
			usingConference.offer(currLecture.start);
		}
		bw.write(String.valueOf(usingConference.size()));
		bw.newLine();
		bw.flush();
		bw.close();
	}

	private static class Lecture implements Comparable<Lecture> {
		int start;
		int end;

		public Lecture(int start, int end) {
			this.start = start;
			this.end = end;
		}

		@Override
		public int compareTo(Lecture o) {
			if (o.end == this.end) {
				return this.start - o.start;
			}
			return o.end - this.end;
		}
	}
}
