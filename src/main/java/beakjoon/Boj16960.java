package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Boj16960 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		// N-1 개의 스위치를 눌러서 모든 램프가 켜지는지를 확인하는 문제
		// N개를 눌럿을 경우 다 켜지는 케이스만 존재한다
		// 그러면 해당 입력값을 전부 기입한 후 해당 입력값에 중복되는 값이 존재할 경우 이미 켜지는것이 확인이 된다
		// 모든 값들을 저장하고 해당 값들을 빼서 만약 전부 존재할 경우 정답 아닐경우 오답
		int N = Integer.parseInt(st.nextToken()); // 스위치의 수 N
		int M = Integer.parseInt(st.nextToken()); // 램프의 수 M
		Map<Integer, Integer> map = new TreeMap<>();
		Queue<List<Integer>> q = new ArrayDeque<>();
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int K = Integer.parseInt(st.nextToken()); // 해당 스위치가 켤 수 있는 램프의 수
			List<Integer> list = new ArrayList<>();
			for (int k = 0; k < K; k++) {
				int value = Integer.parseInt(st.nextToken());
				list.add(value);
				map.put(value, map.getOrDefault(value, 0) + 1);
			}
			q.offer(list);
		}
		while (!q.isEmpty()) {
			List<Integer> curr = q.poll();
			for (int i = 0; i < curr.size(); i++) {
				Integer key = curr.get(i);
				map.put(key, map.getOrDefault(key, 0) - 1);
			}
			if (checking(map)) {
				System.out.println(1);
				return;
			}
			for (int i = 0; i < curr.size(); i++) {
				Integer key = curr.get(i);
				map.put(key, map.getOrDefault(key, 0) + 1);
			}
		}
		System.out.println(0);
	}

	private static boolean checking(Map<Integer, Integer> map) {
		for (Integer value : map.values()) {
			if (value < 1) return false;
		}
		return true;
	}
}
