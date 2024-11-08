package beakjoon.greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class Boj1026 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int[] a = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::new).toArray();
		Integer[] b = new Integer[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			b[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(a);
		Arrays.sort(b, Collections.reverseOrder());
		System.out.println("b = " + Arrays.toString(b));
		int sum = 0;
		for (int i = 0; i < N; i++) {
			sum += a[i] * b[i];
		}
		System.out.println(sum);
	}
}
