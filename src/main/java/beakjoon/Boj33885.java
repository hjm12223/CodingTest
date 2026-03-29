package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Boj33885 {
	static boolean isComplete = false;
	static int N, M;
	static List<Lecture> lectures;
	static boolean[] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		lectures = new ArrayList<>();
		visited = new boolean[N];
		for (int i = 0; i < N; i++) {

			st = new StringTokenizer(br.readLine());
			int credit = Integer.parseInt(st.nextToken());
			int lec = Integer.parseInt(st.nextToken());
			List<Days> daysList = new ArrayList<>();

			for (int j = 0; j < lec; j++) {
				String day = st.nextToken();
				int time = Integer.parseInt(st.nextToken());
				daysList.add(new Days(day, time));
			}

			lectures.add(new Lecture(credit, daysList));
		}
		dfs(0, new ArrayList<>());
		System.out.println(isComplete ? "YES" : "NO");
	}

	private static void dfs(int credit, List<Lecture> comb) {
		if (isComplete) return;
		if (credit >= M) {
			if (check(comb)) {
				isComplete = true;
				return;
			}
			return;
		}
		for (int i = 0; i < N; i++) {
			if (!visited[i]) {
				visited[i] = true;
				Lecture lec = lectures.get(i);
				comb.add(lec);
				dfs(credit + lec.credit, comb);
				visited[i] = false;
				comb.remove(comb.size() - 1);
			}
		}
	}

	private static boolean check(List<Lecture> comb) {
		Set<String> isDuplicated = new TreeSet<>();
		for (int i = 0; i < comb.size(); i++) {
			Lecture lecture = comb.get(i);
			for (int j = 0; j < lecture.daysList.size(); j++) {
				Days day = lecture.daysList.get(j);
				String key = makeKey(day.day, day.time);
				if (!isDuplicated.contains(key)) {
					isDuplicated.add(key);
				} else {
					return false;
				}
			}
		}
		return true;
	}

	private static String makeKey(String day, int time) {
		return day + "-" + time;
	}

	static class Lecture {
		int credit;
		List<Days> daysList;

		public Lecture(int credit, List<Days> daysList) {
			this.credit = credit;
			this.daysList = daysList;
		}
	}

	static class Days {
		String day;
		int time;

		public Days(String day, int time) {
			this.day = day;
			this.time = time;
		}
	}
}
/**
 10C5^3
 */