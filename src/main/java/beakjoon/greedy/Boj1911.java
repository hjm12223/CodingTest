package beakjoon.greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Boj1911 {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());// 물웅덩이의 개수
		int L = Integer.parseInt(st.nextToken());// 널빤지의 길이
		int result = 0;
		List<Puddle> puddles = new ArrayList<>();

		for (int k = 0; k < N; k++) {

			st = new StringTokenizer(br.readLine());

			long start = Integer.parseInt(st.nextToken());
			long end = Integer.parseInt(st.nextToken());
			puddles.add(
				new Puddle(
					start,
					end
				)
			);
		}
		long currentEnd = 0;
		puddles.sort((o1, o2) -> Long.compare(o1.start, o2.start));
		for (Puddle puddle : puddles) {
			if (currentEnd < puddle.start) {
				currentEnd = puddle.start;
			}

			long road = puddle.end - currentEnd;
			long count = (road + L - 1) / L;
			result += (int)count;
			currentEnd += count * L;
		}

		System.out.println(result);
	}

	public static class Puddle {
		long start;
		long end;

		public Puddle(long start, long end) {
			this.start = start;
			this.end = end;
		}

		@Override
		public String toString() {
			return "Puddle{" +
				"start=" + start +
				", end=" + end +
				'}';
		}

	}
	/*
	.111222.333444555....
	111222..333444555.... // 길이 3인 널빤지
	.MMMMM..MMMM.MMMM.... // 웅덩이
	012345678901234567890 // 좌표
	 */
}
