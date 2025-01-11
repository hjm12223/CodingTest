package beakjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Boj2623 {
	static List<List<Integer>> graph = new ArrayList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		Map<Integer, Integer> map = new TreeMap<>();
		int N = Integer.parseInt(st.nextToken());
		for (int i = 0; i <= N; i++)
			graph.add(new ArrayList<>());
		int M = Integer.parseInt(st.nextToken());
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			List<Integer> list = new ArrayList<>();
			int singer = Integer.parseInt(st.nextToken());
			for (int s = 0; s < singer; s++) {
				list.add(Integer.parseInt(st.nextToken()));
			}
			for (int j = 0; j < list.size() - 1; j++) {
				int from = list.get(j);
				int to = list.get(j + 1);
				graph.get(from).add(to);
				map.put(to, map.getOrDefault(to, 0) + 1);
			}
		}
		Queue<Integer> q = new ArrayDeque<>();
		for (int i = 1; i <= N; i++) {
			if (!map.containsKey(i)) {
				q.offer(i);
			}
		}
		while (!q.isEmpty()) {
			Integer curr = q.poll();
			bw.write(String.valueOf(curr));
			bw.newLine();
			for (int next : graph.get(curr)) {
				if (map.containsKey(next)) {
					map.put(next, map.getOrDefault(next, 0) - 1);
				}
				if (map.get(next) <= 0) {
					q.offer(next);
				}
			}
		}
		for (int i = 1; i <= N; i++) {
			if (map.containsKey(i)) {
				if (map.get(i) > 0) {
					System.out.println(0);
					return;
				}
			}
		}
		bw.flush();
		bw.close();
	}
}
