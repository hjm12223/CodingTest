package beakjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Boj5618 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N];

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++)
			arr[i] = Integer.parseInt(st.nextToken());

		int g = arr[0];
		for (int i = 1; i < N; i++) {
			g = gcd(g, arr[i]);
		}

		List<Integer> divisor = new ArrayList<>();
		for (int i = 1; i * i <= g; i++) {
			if (g % i == 0) {
				divisor.add(i);
				if (i != g / i) divisor.add(g / i);
			}
		}
		Collections.sort(divisor);
		for (Integer i : divisor) {
			bw.write(i + "\n");
		}
		bw.flush();
	}

	private static int gcd(int a, int b) {
		while (b != 0) {
			int r = a % b;
			a = b;
			b = r;
		}
		return a;
	}
}
