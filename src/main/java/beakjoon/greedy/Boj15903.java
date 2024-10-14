package beakjoon.greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

import javax.lang.model.SourceVersion;

public class Boj15903 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		long n = Long.parseLong(st.nextToken()); // 카드의 갯수
		long m = Long.parseLong(st.nextToken()); // 카드 합체를 몇번 해야하는지 나타내는 수

		st = new StringTokenizer(br.readLine());

		Long answer = 0L;

		Queue<Long> q = new PriorityQueue<>(new Comparator<Long>() {
			@Override
			public int compare(Long o1, Long o2) {
				return Long.compare(o1, o2);
			}
		});

		for (int i = 0; i < n; i++) {
			q.offer(Long.parseLong(st.nextToken()));
		}
		while (m != 0){
			Long x = q.poll();
			Long y = q.poll();

			q.offer(x+y);
			q.offer(y+x);
			m--;
		}

		while (!q.isEmpty()){
			answer += q.poll();
		}
		System.out.println(answer);
	}
}
