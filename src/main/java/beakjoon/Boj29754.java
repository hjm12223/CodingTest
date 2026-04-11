package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class Boj29754 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		Map<String, Node> map = new HashMap<>();
		Map<String, Integer> count = new HashMap<>();
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken()); // 오름차순이 아닐 수 도 있다.
		int lastDay = 7;
		List<Info> nodes = new ArrayList<>();
		List<String> list = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			String name = st.nextToken();
			int day = Integer.parseInt(st.nextToken());
			String start = st.nextToken();
			String end = st.nextToken();
			nodes.add(new Info(name, day, dayToInt(start), dayToInt(end)));
		}
		nodes.sort((o1, o2) -> o1.day - o2.day);
		for (Info info : nodes) {
			String name = info.name;
			int day = info.day;
			int total = info.end - info.start;
			while (day > lastDay) {
				check(map, count);
				lastDay += 7;
			}
			if (!map.containsKey(name))
				map.put(name, new Node(1, total));
			else {
				Node node = map.get(name);
				node.day++;
				node.total += total;
				map.put(name, node);
			}

		}
		check(map, count);
		StringBuilder sb = new StringBuilder();

		for (String s : count.keySet()) {
			if (count.get(s) == M / 7) {
				list.add(s);
			}
		}
		if (list.isEmpty()) {
			System.out.println(-1);
		} else {
			list.sort(String::compareTo);
			for (String s : list) {
				sb.append(s + "\n");
			}
			System.out.println(sb);
		}
	}

	private static void check(Map<String, Node> map, Map<String, Integer> count) {
		for (String s : map.keySet()) {
			Node node = map.get(s);
			if (node.total >= 3600 && node.day >= 5) {
				count.put(s, count.getOrDefault(s, 0) + 1);
			}
			map.put(s, new Node(0, 0));
		}
	}

	private static int dayToInt(String s) {
		String[] split = s.split(":");
		return Integer.parseInt(split[0]) * 60 + Integer.parseInt(split[1]);
	}

	static class Node {
		int day;
		int total;

		public Node(int day, int total) {
			this.day = day;
			this.total = total;
		}
	}

	static class Info {
		String name;
		int day;
		int start;
		int end;

		public Info(String name, int day, int start, int end) {
			this.name = name;
			this.day = day;
			this.start = start;
			this.end = end;
		}
	}
}
