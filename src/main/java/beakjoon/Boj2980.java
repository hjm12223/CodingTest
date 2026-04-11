package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Boj2980 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int L = Integer.parseInt(st.nextToken());
		Map<Integer, Signal> map = new HashMap<>();
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int idx = Integer.parseInt(st.nextToken());
			int red = Integer.parseInt(st.nextToken());
			int green = Integer.parseInt(st.nextToken());
			map.put(idx, new Signal(red, green));
		}
		int time = 0;
		int curr = 0;

		while (curr < L) {
			curr++;
			time++;

			if (map.containsKey(curr)) {
				Signal s = map.get(curr);

				int cycle = s.red + s.green;
				int t = time % cycle;

				if (t < s.red) {
					time += (s.red - t);
				}
			}
		}
		System.out.println(time);
	}

	static class Signal {
		int red;
		int green;

		public Signal(int red, int green) {
			this.red = red;
			this.green = green;
		}
	}
}
