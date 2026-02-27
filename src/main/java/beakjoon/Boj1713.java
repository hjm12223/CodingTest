package beakjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj1713 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int K = Integer.parseInt(br.readLine());

		StringTokenizer st = new StringTokenizer(br.readLine());

		Map<Integer, Student> map = new HashMap<>();
		Queue<Student> pq = new PriorityQueue<>((o1, o2) -> {
			if (o1.priority == o2.priority) {
				return o1.time - o2.time;
			}
			return o1.priority - o2.priority;
		});
		int time = 0;
		while (st.hasMoreTokens()) {
			int key = Integer.parseInt(st.nextToken());
			time++;
			if (map.containsKey(key)) {
				Student student = map.get(key);
				pq.remove(student);
				student.priority++;
				pq.offer(student);
			} else {
				if (pq.size() == N) {
					Student student = pq.poll();
					map.remove(student.num);
				}
				Student student = new Student(key, time, 1);
				pq.offer(student);
				map.put(key, student);
			}
		}
		List<Integer> list = new ArrayList<>();

		while (!pq.isEmpty()) {
			list.add(pq.poll().num);
		}

		Collections.sort(list);
		StringBuilder sb = new StringBuilder();

		for (Integer i : list) {
			sb.append(i).append(" ");
		}
		System.out.println(sb);
	}

	static class Student {
		int num;
		int time;
		int priority;

		public Student(int num, int time, int priority) {
			this.num = num;
			this.time = time;
			this.priority = priority;
		}
	}
}
