package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Boj1263 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		List<Job> list = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int T = Integer.parseInt(st.nextToken());
			int S = Integer.parseInt(st.nextToken());
			list.add(new Job(T, S));
		}
		list.sort((o1, o2) -> {
			if (o1.S == o2.S) {
				return o1.T - o2.T;
			}
			return o1.S - o2.S;
		});
		int result = -1;
		for (int i = 0; i <= 1_000_000; i++) {
			int curr = i;
			boolean isComplete = true;
			for (int j = 0; j < list.size(); j++) {
				Job job = list.get(j);
				curr += job.T;
				if (curr > job.S) {
					isComplete = false;
					break;
				}
			}
			if (isComplete) {
				result = Math.max(i, result);
			}
		}
		System.out.println(result);
	}

	static class Job {
		int T;
		int S;

		public Job(int t, int s) {
			T = t;
			S = s;
		}
	}
}
