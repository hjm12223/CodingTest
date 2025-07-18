package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj18234 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int T = Integer.parseInt(st.nextToken());
		Queue<Carrot> pq = new PriorityQueue<>(new Comparator<Carrot>() {
			@Override
			public int compare(Carrot o1, Carrot o2) {
				if (o2.nutrition == o1.nutrition) {
					return Long.compare(o2.taste, o1.taste);
				}
				return Long.compare(o2.nutrition, o1.nutrition);
			}
		});
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int taste = Integer.parseInt(st.nextToken());
			int nutrition = Integer.parseInt(st.nextToken());
			pq.add(new Carrot(taste, nutrition));
		}
		long result = 0;
		for (int t = T - 1; t >= 0; t--) {
			if (pq.isEmpty()) break;
			Carrot carrot = pq.poll();
			result += carrot.taste + carrot.nutrition * t;
		}
		System.out.println(result);
	}

	static class Carrot {
		long taste;
		long nutrition;

		public Carrot(long taste, long nutrition) {
			this.taste = taste;
			this.nutrition = nutrition;
		}

	}
}
