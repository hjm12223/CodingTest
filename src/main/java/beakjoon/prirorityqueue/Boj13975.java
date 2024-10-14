package beakjoon.prirorityqueue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

import javax.lang.model.SourceVersion;

public class Boj13975 {
		public static void main(String[] args) throws IOException {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			StringTokenizer st = new StringTokenizer(br.readLine());

			Integer T = Integer.parseInt(st.nextToken());

			long result = 0;
			Queue<Long> q = new PriorityQueue<>(new Comparator<Long>() {
				@Override
				public int compare(Long o1, Long o2) {
					return Long.compare(o1, o2);
				}
			});

			for (int i = 0; i < T; i++) { // 테스트 케이스
				st = new StringTokenizer(br.readLine());
				int K = Integer.parseInt(st.nextToken());
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < K; j++) {
					q.offer(Long.parseLong(st.nextToken()));
				}

				while (q.size() != 1) {
					Long c1 = q.poll();
					Long c2 = q.poll();
					Long value = c1 + c2;
					result += value;
					q.offer(value);
				}

				System.out.println(result);
				q.poll();
				result = 0;
			}

		}
	}