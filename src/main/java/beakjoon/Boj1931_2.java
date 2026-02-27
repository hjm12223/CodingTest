package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj1931_2 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		Queue<Room> pq = new PriorityQueue<>((o1, o2) -> {
			if (o1.end == o2.end) {
				return o1.start - o2.start;
			}
			return o1.end - o2.end;
		});
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			pq.offer(new Room(start, end));
		}
		int prev = 0;
		int cnt = 0;
		while (!pq.isEmpty()) {
			Room currRoom = pq.poll();
			if (prev <= currRoom.start) {
				prev = currRoom.end;
				cnt++;
			}
		}
		System.out.println(cnt);
	}

	static class Room {
		int start;
		int end;

		public Room(int start, int end) {
			this.start = start;
			this.end = end;
		}
	}
}
