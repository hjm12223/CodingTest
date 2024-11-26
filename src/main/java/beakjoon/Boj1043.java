package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj1043 {
	static List<Integer> peoples = new ArrayList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); // 파티 사람의 수
		int M = Integer.parseInt(st.nextToken()); // 파티의 수 지민이는 모든 파티를 참석해야한다

		st = new StringTokenizer(br.readLine());
		int K = Integer.parseInt(st.nextToken()); // 지민이가 말하는 이야기의 진실을 아는 사람의 수

		List<Integer> knowingPeople = new ArrayList<>(); //진실을 말하는 사람을 저장하는 자료구조
		List<List<Integer>> list = new ArrayList<>(); // 인접리스트
		for (int i = 0; i <= N; i++)
			list.add(new ArrayList<>());
		for (int i = 0; i < K; i++) {
			knowingPeople.add(Integer.parseInt(st.nextToken()));
		}
		if (knowingPeople.isEmpty()) {
			System.out.println(M);
			return;
		}
		List<List<Integer>> lists = new ArrayList<>();

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int peopleCnt = Integer.parseInt(st.nextToken()); // 파티에 해당 번호와 오는 사람 수
			List<Integer> participants = new ArrayList<>();
			for (int j = 0; j < peopleCnt; j++) {
				int participant = Integer.parseInt(st.nextToken());
				participants.add(participant);
			}
			lists.add(new ArrayList<>(participants));
		}

		for (int i = 0; i < lists.size(); i++) {
			List<Integer> participants = lists.get(i);
			for (int j = 0; j < participants.size(); j++) {
				int from = participants.get(j);
				int to = participants.get((j + 1) % participants.size());
				list.get(from).add(to);
				list.get(to).add(from);
			}
		}

		for (int i = 0; i < knowingPeople.size(); i++) {
			Integer people = knowingPeople.get(i);
			if (!peoples.contains(people)) {
				peoples.add(people);
			}
			dijkstra(people, list);
		}
		int cnt = 0;
		for (List<Integer> participants : lists) {
			boolean isLie = true;
			for (int i = 0; i < peoples.size(); i++) {
				if (participants.contains(peoples.get(i))) isLie = false;
			}
			if (isLie) cnt++;
		}
		System.out.println(cnt);
	}

	private static void dijkstra(Integer start, List<List<Integer>> graph) {
		Queue<Integer> q = new LinkedList<>();
		q.offer(start);
		while (!q.isEmpty()) {
			Integer curr = q.poll();
			for (int i = 0; i < graph.get(curr).size(); i++) {
				Integer next = graph.get(curr).get(i);
				if (!peoples.contains(next)) {
					peoples.add(next);
					q.offer(next);
				}
			}
		}
	}
}
