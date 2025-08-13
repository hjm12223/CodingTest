package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class Boj18513 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken()); // 샘물의 갯수
		int K = Integer.parseInt(st.nextToken()); // 집의 갯수

		Queue<int[]> pq = new PriorityQueue<>((o1, o2) -> {
			return o1[1] - o2[1];
		});
		st = new StringTokenizer(br.readLine());

		Set<Integer> visited = new HashSet<>();
		for (int i = 0; i < N; i++) {
			int visit = Integer.parseInt(st.nextToken());
			pq.offer(new int[] {visit, 0});
			visited.add(visit);
		}

		int cnt = 0;
		long result = 0;
		while (!pq.isEmpty()) {
			int[] curr = pq.poll();
			int nextOne = curr[0] + 1;
			int nextTwo = curr[0] - 1;
			int nextValue = curr[1] + 1;
			if (!visited.contains(nextOne)) {
				visited.add(nextOne);
				pq.offer(new int[] {nextOne, nextValue});
				result += nextValue;
				cnt++;
				if (cnt == K) break;
			}
			if (!visited.contains(nextTwo)) {
				visited.add(nextTwo);
				pq.offer(new int[] {nextTwo, nextValue});
				result += nextValue;
				cnt++;
				if (cnt == K) break;
			}
		}
		System.out.println(result);

	}
}
