package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj20159 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int[] cards = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

		long[] preEven = new long[N / 2];
		long[] preOdd = new long[N / 2];

		preOdd[0] = cards[0];
		preEven[0] = cards[1];

		for (int i = 2; i < N; i += 2) {
			preOdd[i / 2] = preOdd[i / 2 - 1] + cards[i];
			preEven[i / 2] = preEven[i / 2 - 1] + cards[i + 1];
		}

		long max = Math.max(preOdd[preOdd.length - 1], preEven[preEven.length - 1]);
		max = Math.max(max, cards[0] + preEven[preEven.length - 1] - cards[cards.length - 1]);

		for (int i = 2; i < N; i += 2) {
			max = Math.max(max, preOdd[i / 2 - 1] + preEven[preEven.length - 1] - preEven[i / 2 - 1]);
			max = Math.max(max, preOdd[i / 2] + preEven[preEven.length - 1] - preEven[i / 2 - 1] - cards[cards.length
				- 1]);
		}

		System.out.println(max);
	}
}