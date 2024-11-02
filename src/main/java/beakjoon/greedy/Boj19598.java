package beakjoon.greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj19598 {
	/*
	완탐시 N^2 == 100000^100000 즉 백억이나와서 안됌
	N log N 정도로 풀어야 한다

	결국에는 정렬로 풀어야할거같다

	시작시간 순으로 정렬
	총시간이 일단 적어야지 많은 미팅을 잡을 수 있다

	 */
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken()); // 2^31 보다 작다
		Queue<Meeting> room = new PriorityQueue<>();
		List<Meeting> list = new ArrayList<>();
		int count = 0;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			list.add(new Meeting(start, end));
		}
		Collections.sort(list, (o1, o2) -> {
				return o1.startTime - o2.startTime;
			}
		);
		for (int i = 0; i < N; i++) {
			Meeting meeting = list.get(i);
			int start = meeting.startTime;
			int end = meeting.endTime;
			if (room.isEmpty()) {
				room.offer(new Meeting(start, end));
				count++;
			} else if (room.peek().endTime > start) {
				room.offer(new Meeting(start, end));
				count++;
			} else {
				room.offer(new Meeting(start, end)); // 해당방을 사용
				room.poll();
			}
		}
		System.out.println(count);
	}

	public static class Meeting implements Comparable<Meeting> {
		int startTime;
		int endTime;

		public Meeting(int startTime, int endTime) {
			this.startTime = startTime;
			this.endTime = endTime;
		}

		@Override
		public int compareTo(Meeting o) {
			return this.endTime - o.endTime;
		}
	}
}
