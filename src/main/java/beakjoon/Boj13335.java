package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj13335 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int W = Integer.parseInt(st.nextToken());
		int L = Integer.parseInt(st.nextToken());

		int[] trucks = Arrays.stream(br.readLine().split(" "))
			.mapToInt(Integer::parseInt)
			.toArray();

		Queue<Integer> bridge = new ArrayDeque<>();

		int time = 0;
		int currentWeight = 0;
		int index = 0;

		for (int i = 0; i < W; i++) {
			bridge.add(0);
		}

		while (index < N) {
			time++;
			currentWeight -= bridge.poll();

			if (currentWeight + trucks[index] <= L) {
				bridge.add(trucks[index]);
				currentWeight += trucks[index];
				index++;
			} else {
				bridge.add(0);
			}
		}

		time += W;
		System.out.println(time);
	}

}
